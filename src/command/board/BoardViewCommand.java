package command.board;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import common.ViewForward;
import dao.BoardDao;
import dto.BoardDto;

public class BoardViewCommand implements BoardCommand {

	@Override
	public ViewForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		request.setCharacterEncoding("utf-8");
		
		int bNo = Integer.parseInt(request.getParameter("bNo"));
		String page = request.getParameter("page");
		
		BoardDto bDto = BoardDao.getInstance().selectBybNo(bNo);
		HttpSession session = request.getSession();
		session.setAttribute("bDto", bDto);
		
		// boardView.jsp로 전달할 데이터들
		//request.setAttribute("bDto", bDto);
		request.setAttribute("page", page);
		
		ViewForward vf = new ViewForward();
		
		// 조회수 증가
		String open = (String)session.getAttribute("open");
		if(open == null || open.isEmpty()) {			// 해당 게시글이 열려있지 않으면,
			session.setAttribute("open", "true");		// 해당 게시글을 연다(true값으로줌)
			BoardDao.getInstance().boardUpdatebHit(bNo);// 해당 게시글의 조회수를 증가시킨다.
		}
		
		if(bDto != null) {
			vf.setPath("board/boardView.jsp");
			vf.setRedirect(false);
		} else {
			response.setHeader("Content-Type", "text/html; charset=utf-8");
			PrintWriter out = response.getWriter();
			out.println("<script>");
			out.println("alert('해당게시글을 확인 할 수 없습니다.')");
			out.println("history.back()");
			out.println("</script>");

		}
		return vf;
	}

}
