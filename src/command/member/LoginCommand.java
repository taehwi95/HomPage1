package command.member;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import common.ViewForward;
import dao.MemberDao;
import dto.MemberDto;

public class LoginCommand implements MemberCommand {

	@Override
	public ViewForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		request.setCharacterEncoding("utf-8");
		
		String mId = request.getParameter("mId");
		String mPw = request.getParameter("mPw");
		
		MemberDto mDto = new MemberDto();
		mDto.setmId(mId);
		mDto.setmPw(mPw);
		
		MemberDto IdCheck = MemberDao.getInstance().IDCheck(mId);
		MemberDto loginUser = MemberDao.getInstance().login(mDto);
		
		PrintWriter out = response.getWriter();
		ViewForward vf = new ViewForward();
		
		if(IdCheck != null) {				
			
			if( loginUser == null ) {
				response.setHeader("Content-Type", "text/html; charset=utf-8");
				out.println("<script>");
				out.println("alert('비밀번호가 틀립니다.')");
				out.println("history.back()");
				out.println("</script>");		
			} else {
				HttpSession session = request.getSession();
				session.setAttribute("loginUser", loginUser);
				vf.setPath("index.jsp");
				vf.setRedirect(false);
			}
		} else {
			response.setHeader("Content-Type", "text/html; charset=utf-8");
			out.println("<script>");
			out.println("alert('등록된 회원이 아닙니다.')");
			out.println("history.back()");
			out.println("</script>");
			out.close();
		}
		return vf;
	}

}
