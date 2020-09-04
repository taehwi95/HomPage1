package command.board;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import common.ViewForward;

public interface BoardCommand {
	
	public ViewForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception;
}
