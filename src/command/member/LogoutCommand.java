package command.member;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import common.ViewForward;
import dto.MemberDto;

public class LogoutCommand implements MemberCommand {

	@Override
	public ViewForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		HttpSession session = request.getSession();
		MemberDto loginUser = (MemberDto)session.getAttribute("loginUser");
		
		ViewForward vf = new ViewForward();
		
		if(loginUser != null) {
			session.invalidate();	// session 초기화
			vf.setPath("index.jsp");
			vf.setRedirect(false);
		} else {
			response.setHeader("Content-Type", "text/html; charset=utf-8");
			PrintWriter out = response.getWriter();
			out.println("<script>");
			out.println("alert('로그인된 정보가 없습니다.')");
			out.println("history.back()");
			out.println("</script>");
		}
		
		return vf;
	}

}
