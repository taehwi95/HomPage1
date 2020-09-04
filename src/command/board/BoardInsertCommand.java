package command.board;

import java.io.File;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import common.ViewForward;
import dao.BoardDao;
import dto.BoardDto;

public class BoardInsertCommand implements BoardCommand {

	@Override
	public ViewForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
	
		final String DIRECTORY = "storage";
		String realPath = request.getServletContext().getRealPath("/" + DIRECTORY);
		
		File path = new File(realPath);
		if( !path.exists() ) {
			path.mkdirs();		// storage 폴더가 없으면 새로 생성한다.
		}
		
		MultipartRequest mr = new MultipartRequest(request, realPath, 1024*1024*10, "utf-8", new DefaultFileRenamePolicy());
		
		BoardDto bDto = new BoardDto();
		
		bDto.setmId(mr.getParameter("mId"));
		bDto.setbTitle(mr.getParameter("bTitle"));
		bDto.setbContent(mr.getParameter("bContent"));
		if(mr.getFile("bFilename") == null) {
			bDto.setbFilename("");
		} else {
			bDto.setbFilename(mr.getFilesystemName("bFilename"));
		}
		bDto.setbIp(request.getRemoteAddr());
		
		int result = BoardDao.getInstance().boardInsert(bDto);
		
		ViewForward vf = new ViewForward();
		vf.setPath("board/boardInsertResult.jsp?result=" + result);
		vf.setRedirect(true);
		
		return vf;
	}

}
