package command.member;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.simple.JSONObject;

import dao.MemberDao;
import dto.MemberDto;

public class AjaxLeaveCommand implements AjaxCommand {

	@SuppressWarnings("unchecked")
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		HttpSession session = request.getSession();
		
		int mNo = ((MemberDto)session.getAttribute("loginUser")).getmNo();
		
		int result = MemberDao.getInstance().leaveInfo(mNo);
		
		JSONObject obj = new JSONObject();
		
		if(result > 0) {
			obj.put("isSuccess", true);
			session.invalidate();
		}else {
			obj.put("isSuccess", false);
		}
		return obj.toJSONString();
	}

}
