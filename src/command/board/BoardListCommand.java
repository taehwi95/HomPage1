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

public class BoardListCommand implements BoardCommand {

	@Override
	public ViewForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		request.setCharacterEncoding("utf-8");
		String page = request.getParameter("page");
		if(page == null || page.isEmpty()) {
			page = "1";
		}
		
		// recordPerPage 가 5인 상황
		// page = 1, begin : 1, end : 5
		// page = 2, begin : 6, end : 10
		// ...
		
		// page와 recordPerPage를 알면, begin과 end를 구할 수 있다.
		int recordPerPage = 5;
		int begin = (Integer.parseInt(page) - 1) * recordPerPage + 1;
		int end = begin + recordPerPage -1;
		
		// begin + end = Map (DB에 전달을 위해)
		Map<String, Integer> map = new HashMap<String, Integer>();
		map.put("begin", begin);
		map.put("end", end);
		
		// DB에 Map 전달
		List<BoardDto> list = BoardDao.getInstance().selectBoardList(map);
		
		// 전체 게시글 개수 구하기
		int totalRecord = BoardDao.getInstance().selectBoardCount();
		
		// 페이징 생성(◀ 1 2 3 ▶)
		String paging = Paging.getPaging("/HP/boardList.board", Integer.parseInt(page), recordPerPage, totalRecord);
		
		// boardList.jsp 에 넘길 데이터들
		request.setAttribute("list", list);
		request.setAttribute("totalRecord", totalRecord);
		request.setAttribute("paging", paging);
		request.setAttribute("page", page);
		
		// 열린 게시글을 닫는다.
		HttpSession session = request.getSession();
		session.removeAttribute("open");
		
		ViewForward vf = new ViewForward();
		vf.setPath("board/boardList.jsp");
		vf.setRedirect(false);
		
		return vf;
	}

}
