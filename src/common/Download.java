package common;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.net.URLEncoder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Download {
	
	public static void download(HttpServletRequest request, HttpServletResponse response) throws Exception{
		
		request.setCharacterEncoding("utf-8");
		String bFilename = request.getParameter("bFilename");
		
		final String DIRECTORY = "storage";
		String realPath = request.getServletContext().getRealPath("/" + DIRECTORY);
		
		File path = new File(realPath);
		if( !path.exists() ) {
			path.mkdirs();
		}
		
		File file = new File(realPath, bFilename);
		
		response.setHeader("Content-Type", "application/x-msdownload");
		response.setHeader("Content-Disposition", "attachment; filename=" + URLEncoder.encode(bFilename, "utf-8"));
		response.setHeader("Content-Length", file.length() + "");
		
		BufferedInputStream bis = null;
		BufferedOutputStream bos = null;
		
		bis = new BufferedInputStream(new FileInputStream(file));
		bos = new BufferedOutputStream(response.getOutputStream());
		
		byte[] byteArray = new byte[1024];	// 1024바이트 = 1kb
		while((bis.read(byteArray)) != -1) {
			bos.write(byteArray);
		}
		
		if(bos != null) {bos.close();}
		if(bis != null) {bis.close();}
	}

}
