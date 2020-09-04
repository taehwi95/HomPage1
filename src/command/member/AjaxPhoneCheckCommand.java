package command.member;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONObject;

import dao.MemberDao;
import dto.MemberDto;

public class AjaxPhoneCheckCommand implements AjaxCommand {

	@SuppressWarnings("unchecked")
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		request.setCharacterEncoding("utf-8");
		String mPhone = request.getParameter("mPhone");
		
		MemberDto mDto = MemberDao.getInstance().PhoneCheck(mPhone);
		
		JSONObject obj = new JSONObject();
		
		if(mDto == null) {
			obj.put("isPossible", true);
		} else {
			obj.put("isPossible", false);
		}
		
		return obj.toJSONString();
	}

}
