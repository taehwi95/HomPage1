package command.member;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.simple.JSONObject;

import dao.MemberDao;
import dto.MemberDto;

public class AjaxUpdateInfoCommand implements AjaxCommand {

	@SuppressWarnings("unchecked")
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		request.setCharacterEncoding("utf-8");
			
		String mId = request.getParameter("mId");
		String mName = request.getParameter("mName");
		String mEmail = request.getParameter("mEmail");
		String mPhone = request.getParameter("mPhone");
		String mAddress = request.getParameter("mAddress");
				
		MemberDto mDto = new MemberDto();
		mDto.setmId(mId);
		mDto.setmName(mName);
		mDto.setmEmail(mEmail);
		mDto.setmPhone(mPhone);
		mDto.setmAddress(mAddress);		

		int result = MemberDao.getInstance().memberUpdateInfo(mDto);

		JSONObject obj = new JSONObject();
		
		if(result > 0) {
			obj.put("isSuccess", true);		// 정보가 수정되었으니 session에 다시 담아준다.
			MemberDto loginUser = MemberDao.getInstance().IDCheck(mId);
			HttpSession session = request.getSession();
			session.setAttribute("loginUser", loginUser);
		} else {
			obj.put("isSuccess", false);
		}
		
		return obj.toJSONString();
	}

}
