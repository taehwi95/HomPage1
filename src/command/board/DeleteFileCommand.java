package command.board;

import java.io.File;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import common.ViewForward;
import dao.BoardDao;
import dto.BoardDto;

public class DeleteFileCommand implements BoardCommand {

	@Override
	public ViewForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// 상세보기중인 Board 정보는 session에 bDto 속성으로 저장되어 있다.
		HttpSession session = request.getSession();
		BoardDto bDto = (BoardDto) session.getAttribute("bDto");
		int bNo = bDto.getbNo();
		String bFilename = bDto.getbFilename();
		
		// 서버에 저장된 첨부파일 삭제 + DB에 저장된 파일명 삭제
		final String DIRECTORY = "storage";
		String realPath = request.getServletContext().getRealPath(DIRECTORY + "/" + bFilename);
		File file = new File(realPath);
		if(file.exists()) {
			file.delete();	// 파일 삭제
			BoardDao.getInstance().deleteFile(bNo);
		}
		ViewForward vf = new ViewForward();
		vf.setPath("/HP/boardView.board?bNo=" + bNo);
		vf.setRedirect(true);
		
		return vf;
	}

}
