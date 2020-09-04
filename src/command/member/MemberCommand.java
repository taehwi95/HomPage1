package command.member;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import common.ViewForward;

public interface MemberCommand {
	
	public ViewForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception;
}
