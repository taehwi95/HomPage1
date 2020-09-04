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

public class QueryBoardListCommand implements BoardCommand {

	@Override
	public ViewForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		request.setCharacterEncoding("utf-8");
		
		String column = request.getParameter("column");
		String query = request.getParameter("query");
		String page = request.getParameter("page");
		if(page == null || page.isEmpty()) {
			page = "1";
		}
		
		// recordPerPage 가 5인 상황
		// page = 1, begin : 1, end : 5
		// page = 2, begin : 6, end : 10
		// page = 3, begin : 11, end : 15
		
		// page와 recordPerPage를 알면, begin과 end를 구할 수 있다.
		int recordPerPage = 5;
		int begin = (Integer.parseInt(page) - 1) * recordPerPage + 1;
		int end = begin + recordPerPage - 1;
		
		// begin + end + column + query = Map(DB전달을 위해)
		Map<String, String> map = new HashMap<String, String>();
		map.put("begin", begin + ""); 	// String처리를 위해 ""를 더해준다.
		map.put("end", end + "");
		map.put("column", column);
		map.put("query", query);
		
		List<BoardDto> list = BoardDao.getInstance().selectQueryBoardList(map);
		int totalQueryRecord = BoardDao.getInstance().selectQueryBoardCount(map);
		String paging = Paging.getPaging("/HP/queryBoardList.board?column=" + column + 
										 "&query=" + query, Integer.parseInt(page), recordPerPage, totalQueryRecord);
		
		// boardList.jsp에 넘길 데이터들
		request.setAttribute("list", list);
		request.setAttribute("totalRecord", totalQueryRecord);
		request.setAttribute("paging", paging);
		request.setAttribute("page", page);
		
		// 열린 게시글 닫기
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
