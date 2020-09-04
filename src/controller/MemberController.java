package controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import command.member.AjaxCommand;
import command.member.AjaxEmailCheckCommand;
import command.member.AjaxFindIdCommand;
import command.member.AjaxFindPwCommand;
import command.member.AjaxIdCheckCommand;
import command.member.AjaxJoinCommand;
import command.member.AjaxLeaveCommand;
import command.member.AjaxPhoneCheckCommand;
import command.member.AjaxUpdateInfoCommand;
import command.member.AjaxUpdatePwCommand;
import command.member.LoginCommand;
import command.member.LogoutCommand;
import command.member.MemberCommand;
import common.ViewForward;

@WebServlet("*.member")
public class MemberController extends HttpServlet {
	private static final long serialVersionUID = 1L;
    public MemberController() {
        super();
    }
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding("utf-8");
		
		String requestURI = request.getRequestURI();
		String contextPath = request.getContextPath();
		String mapping = requestURI.substring(contextPath.length());
		
		// MVC
		MemberCommand memberCommand = null;
		ViewForward vf = null;
		
		// AJAX
		AjaxCommand ajaxCommand = null;
		String result = null;
		// json 데이터를 응답하는 경우
		response.setContentType("application/json; charset=utf-8");
		PrintWriter out = response.getWriter();
		
		try {
			switch (mapping) {
			
			// MVC 처리
			case "/login.member":
				memberCommand = new LoginCommand();
				vf = memberCommand.execute(request, response);
				break;
				
			case "/logout.member":
				memberCommand = new LogoutCommand();
				vf = memberCommand.execute(request, response);
				break;
				
			
			// ajax 처리				
			case "/idCheck.member":
				ajaxCommand = new AjaxIdCheckCommand();
				result = ajaxCommand.execute(request, response);
				out.println(result);
				break;
				
			case "/emailCheck.member":
				ajaxCommand = new AjaxEmailCheckCommand();
				result = ajaxCommand.execute(request, response);
				out.println(result);
				break;
				
			case "/phoneCheck.member":
				ajaxCommand = new AjaxPhoneCheckCommand();
				result = ajaxCommand.execute(request, response);
				out.println(result);
				break;
				
			case "/join.member":
				ajaxCommand = new AjaxJoinCommand();
				result = ajaxCommand.execute(request, response);
				out.println(result);
				break;
				
			case "/updateInfo.member":
				ajaxCommand = new AjaxUpdateInfoCommand();
				result = ajaxCommand.execute(request, response);
				out.println(result);
				break;
				
			case "/updatePw.member":
				ajaxCommand = new AjaxUpdatePwCommand();
				result = ajaxCommand.execute(request, response);
				out.println(result);
				break;
				
			case "/leave.member":
				ajaxCommand = new AjaxLeaveCommand();
				result = ajaxCommand.execute(request, response);
				out.println(result);
				break;
				
			case "/findid.member":
				ajaxCommand = new AjaxFindIdCommand();
				result = ajaxCommand.execute(request, response);
				out.println(result);
				break;
				
			case "/findPw.member":
				ajaxCommand = new AjaxFindPwCommand();
				result = ajaxCommand.execute(request, response);
				out.println(result);
				break;
				
			// 단순이동
			case "/loginPage.member":
				vf = new ViewForward();
				vf.setPath("member/login.jsp");
				vf.setRedirect(false);
				break;
				
			case "/joinPage.member":
				vf = new ViewForward();
				vf.setPath("member/join.jsp");
				vf.setRedirect(false);
				break;
				
			case "/myPage.member":
				vf = new ViewForward();
				vf.setPath("member/mypage.jsp");
				vf.setRedirect(false);
				break;
				
			case "/updatePwPage.member":
				vf = new ViewForward();
				vf.setPath("member/updatePwPage.jsp");
				vf.setRedirect(false);
				break;
				
			case "/leavePage.member":
				vf = new ViewForward();
				vf.setPath("member/leavePage.jsp");
				vf.setRedirect(false);
				break;
				
			case "/findIdPage.member":
				vf = new ViewForward();
				vf.setPath("member/findId.jsp");
				vf.setRedirect(false);
				break;
				
			case "/findPwPage.member":
				vf = new ViewForward();
				vf.setPath("member/findPw.jsp");
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
