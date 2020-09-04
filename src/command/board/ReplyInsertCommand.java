package command.board;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import common.ViewForward;
import dao.BoardDao;
import dto.BoardDto;

public class ReplyInsertCommand implements BoardCommand {

	@Override
	public ViewForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		request.setCharacterEncoding("utf-8");
		String mId = request.getParameter("mId");
		String bTitle = request.getParameter("bTitle");
		String bContent = request.getParameter("bContent");
		String bIp = request.getRemoteAddr();
		
		int bNo = Integer.parseInt(request.getParameter("bNo"));	// 원글 번호
		String page = request.getParameter("page");
		
		// 댓글 정보
		BoardDto rDto = new BoardDto();
		rDto.setmId(mId);
		rDto.setbTitle(bTitle);
		rDto.setbContent(bContent);
		rDto.setbIp(bIp);
		
		// 댓글의 원글 가져오기
		BoardDto bDto = BoardDao.getInstance().selectBybNo(bNo);
		
		// 댓글의 bGroup, bGroupOrd, bDepth정보는 원글의 정보를 바탕으로 저장한다.
		rDto.setbGroup(bDto.getbGroup());
		rDto.setbGroupOrd(bDto.getbGroupOrd() + 1);
		rDto.setbDepth(bDto.getbDepth() + 1);
		
		// 기존 댓글들의 bGroupOrd 는 모두 1 증가 시킨다.(오래된 댓글의 bGroupOrd 값이 가장 클 수 있도록(정렬을위해))
		BoardDao.getInstance().replyUpdateGroupOrd(bDto);
		
		// 댓글 삽입
		int result = BoardDao.getInstance().replyInsert(rDto);
		
		
		ViewForward vf = new ViewForward();
		if(result > 0) {
			vf.setPath("/HP/boardList.board?page=" + page);
			vf.setRedirect(true);
		} else {
			response.setHeader("Content-Type", "text/html; charset=utf-8");
			PrintWriter out = response.getWriter();
			out.println("<script>");
			out.println("alert('댓글을 삽입 할 수 없습니다.')");
			out.println("history.back()");
			out.println("</script>");
			out.close();
		}
		
		return vf;
	}

}
