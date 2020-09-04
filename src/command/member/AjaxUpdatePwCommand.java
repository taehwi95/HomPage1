package command.member;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.simple.JSONObject;

import dao.MemberDao;
import dto.MemberDto;

public class AjaxUpdatePwCommand implements AjaxCommand {

	@SuppressWarnings("unchecked")
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

		request.setCharacterEncoding("utf-8");
		HttpSession session = request.getSession();
		
		String mPw = request.getParameter("NewmPw");
		String mId = ((MemberDto)session.getAttribute("loginUser")).getmId();
		
		MemberDto mDto = new MemberDto();
		mDto.setmPw(mPw);
		mDto.setmId(mId);
		
		int result = MemberDao.getInstance().memberUpdatePw(mDto);
		
		JSONObject obj = new JSONObject();
		
		if(result > 0) {
			obj.put("isSuccess", true);
			MemberDto loginUser = MemberDao.getInstance().IDCheck(mId);
			session.setAttribute("loginUser", loginUser);	// sesssion은 원래 있던게 있으면 덮어쓴다.
		} else {
			obj.put("isSuccess", false);
		}

		return obj.toJSONString();
	}

}
