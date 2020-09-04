package command.board;

import java.io.File;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import common.ViewForward;
import dao.BoardDao;
import dto.BoardDto;

public class UpdateBoardCommand implements BoardCommand {

	@Override
	public ViewForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		final String DIRECTORY = "storage";
		String realPath = request.getServletContext().getRealPath("/" + DIRECTORY);
		File path = new File(realPath);
		if( !path.exists() ) {
			path.mkdirs();		// storage 폴더가 없으면 새로 생성한다.
		}
		
		ViewForward vf = new ViewForward();
		
		try {
			
		MultipartRequest mr = new MultipartRequest(request, realPath, 1024*1024*10, "utf-8", new DefaultFileRenamePolicy());
		

		BoardDto bDto = new BoardDto();
		String page = mr.getParameter("page");
		bDto.setbNo(Integer.parseInt(mr.getParameter("bNo")));
		bDto.setbContent(mr.getParameter("bContent"));
		
		// 새로 첨부 할 파일
		File newFile = mr.getFile("bFilename");
		
		// 기존 첨부된 파일
		HttpSession session = request.getSession();
		String oldFile = ((BoardDto)session.getAttribute("bDto")).getbFilename();
		
		// 새로운 첨부와 기존 첨부가 모두 있으면 기존 첨부를 서버에서 삭제하기
		if(newFile != null && oldFile != null) {
			File file = new File(realPath, oldFile);
			if(file.exists()) {
				file.delete();
			}
		}
		
		// 새로운 첨부가 있으면 bFilename 을 수정 할 bDto에 저장한다.
		if(newFile != null) {
			bDto.setbFilename(mr.getFilesystemName("bFilename"));
		}
		
		if(newFile == null && oldFile != null) {
			bDto.setbFilename(oldFile);
		}
		
		if(newFile == null && oldFile == null) {
			bDto.setbFilename("");
		}
		
		// 수정
		int result = BoardDao.getInstance().updateBoard(bDto);
		
		vf.setPath("board/updateBoardResultPage.jsp?result=" + result + "&bNo=" + bDto.getbNo() + "&page=" + page);
		vf.setRedirect(true);
		
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("오류 메세지" + e.getMessage());
		}
		return vf;
	}

}
