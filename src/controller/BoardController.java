package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import command.board.BoardCommand;
import command.board.BoardDeleteCommand;
import command.board.BoardInsertCommand;
import command.board.BoardListCommand;
import command.board.BoardViewCommand;
import command.board.DeleteFileCommand;
import command.board.MyBoardListCommand;
import command.board.QueryBoardListCommand;
import command.board.ReplyInsertCommand;
import command.board.UpdateBoardCommand;
import common.Download;
import common.ViewForward;

@WebServlet("*.board")
public class BoardController extends HttpServlet {
	private static final long serialVersionUID = 1L;
    public BoardController() {
        super();
    }
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=utf-8");
		
		String requestURI = request.getRequestURI();
		String contextPath = request.getContextPath();
		String mapping = requestURI.substring(contextPath.length());
		
		// MVC
		BoardCommand boardCommand = null;
		ViewForward vf = null;
		
		
		try {
			
			// 요청에 따른 Command 호출 및 실행
			switch (mapping) {
			
			case "/boardList.board":
				boardCommand = new BoardListCommand();
				vf = boardCommand.execute(request, response);
				break;
				
			case "/boardInsert.board":
				boardCommand = new BoardInsertCommand();
				vf = boardCommand.execute(request, response);
				break;
				
			case "/boardView.board":
				boardCommand = new BoardViewCommand();
				vf = boardCommand.execute(request, response);
				break;
				
			case "/queryBoardList.board":
				boardCommand = new QueryBoardListCommand();
				vf = boardCommand.execute(request, response);
				break;
			
			case "/myBoardList.board":
				boardCommand = new MyBoardListCommand();
				vf = boardCommand.execute(request, response);
				break;
				
			case "/boardDelete.board":
				boardCommand = new BoardDeleteCommand();
				vf = boardCommand.execute(request, response);
				break;
				
			case "/updateBoard.board":
				boardCommand = new UpdateBoardCommand();
				vf = boardCommand.execute(request, response);
				break;
				
			case "/download.board":
				Download.download(request, response);
				break;
				
			case "/deleteFile.board":
				boardCommand = new DeleteFileCommand();
				vf = boardCommand.execute(request, response);
				break;
				
			case "/replyInsert.board":
				boardCommand = new ReplyInsertCommand();
				vf = boardCommand.execute(request, response);
				break;
				
			// 단순 이동			
			case "/boardInsertPage.board":
				vf = new ViewForward();
				vf.setPath("board/boardInsert.jsp");
				vf.setRedirect(false);
				break;
				
			case "/replyInsertPage.board":
				vf = new ViewForward();
				vf.setPath("board/replyInsert.jsp");
				vf.setRedirect(false);
				break;
			}
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		if( vf != null ) {
			if( vf.isRedirect() ) {
				response.sendRedirect(vf.getPath());
			} else {
				request.getRequestDispatcher(vf.getPath()).forward(request, response);
			}
		}
		
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}
