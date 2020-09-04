package command.member;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONObject;

import dao.MemberDao;
import dto.MemberDto;

public class AjaxFindPwCommand implements AjaxCommand {

	@SuppressWarnings("unchecked")
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		request.setCharacterEncoding("utf-8");
		
		String mId = request.getParameter("mId");
		String mPhone = request.getParameter("mPhone");
		
		MemberDto mDto = new MemberDto();
		mDto.setmId(mId);
		mDto.setmPhone(mPhone);
		
		MemberDto resultDto = MemberDao.getInstance().findPw(mDto);
		
		JSONObject obj = new JSONObject();
		
		if (resultDto != null) {
			char[] charList = new char[] {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9',
										  'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J',
										  'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T',
										  'U', 'V', 'W', 'X', 'Y', 'Z'};
			int idx = 0;
			StringBuffer sb = new StringBuffer();
			for(int i = 0; i < 10; i++) {
				idx = (int) (charList.length * Math.random());		// charList의 길이 * 난수(Math) = 36 * (0~1미만) = 0 ~ 35.9999.. -> int로 변환하면 0 ~ 35
				sb.append(charList[idx]);
			}
			String randomPw = sb.toString();	// for문을 돌려 만든 랜덤한 sb 값을 randomPw라는 변수에 저장한다.
			obj.put("randomPw", randomPw);
			mDto.setmPw(randomPw);
			MemberDao.getInstance().memberUpdatePw(mDto);
			obj.put("isMember", true);
		} else {
			obj.put("isMember", false);
		}
		
		return obj.toJSONString();
	}

}
