package command.board;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import common.Paging;
import common.ViewForward;
import dao.BoardDao;
import dto.BoardDto;

public class MyBoardListCommand implements BoardCommand {

	@Override
	public ViewForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		request.setCharacterEncoding("utf-8");
		String mId = request.getParameter("mId");
		String page = request.getParameter("page");
		if(page == null || page.isEmpty()) {
			page = "1";
		}
		
		// page와 recordPerPage를 알면, begin과 end를 구할 수 있다.
		int recordPerPage = 5;
		int begin = (Integer.parseInt(page) - 1) * recordPerPage + 1;
		int end = begin + recordPerPage -1;
		
		// begin + end + mId = Map (DB에 전달하기 위해)
		Map<String, String> map = new HashMap<String, String>();
		map.put("begin", begin + "");
		map.put("end", end + "");
		map.put("mId", mId);
		
		List<BoardDto> list = BoardDao.getInstance().selectMyBoardList(map);
		int totalMyRecord = BoardDao.getInstance().selectMyBoardCount(map);
		String paging = Paging.getPaging("/HP/myBoardList.board?mId=" + mId, Integer.parseInt(page), recordPerPage, totalMyRecord);
		
		// boardList.jsp에 넘길 데이터들
		request.setAttribute("list", list);
		request.setAttribute("totalRecord", totalMyRecord);
		request.setAttribute("paging", paging);
		request.setAttribute("page", page);
		
		// 열린 게시글을 닫는다.
		HttpSession session = request.getSession();
		if(session.getAttribute("open") != null) {
			session.removeAttribute("open");
		}
		
		ViewForward vf = new ViewForward();
		vf.setPath("board/boardList.jsp");
		vf.setRedirect(false);
		
		return vf;
	}

}
