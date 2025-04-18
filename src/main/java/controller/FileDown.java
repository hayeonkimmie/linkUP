package controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class FileDown
 */
@WebServlet("/fileDown")
public class FileDown extends HttpServlet {
	private static final long serialVersionUID = 1L;
    public FileDown() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String filename = request.getParameter("filename");
		String path = request.getSession().getServletContext().getRealPath("upload");
		try {
			InputStream is = new FileInputStream(new File(path, filename));
			String mimeType = request.getServletContext().getMimeType(path+"\\"+filename);
			if(mimeType == null) {
				mimeType = "application/octet-stream";
			} 
			
			response.setContentType(mimeType);
            String encoding = new String(filename.getBytes("UTF-8"), "ISO8859-1");//한글 파일명 깨짐 방지
			response.setHeader("content-Disposition", "attachment; filename= "+encoding);
			OutputStream out = response.getOutputStream();
			byte[] buff = new byte[4096];
			int len;
			while((len=is.read(buff))>0){
				out.write(buff,0,len);
			}
			is.close();
		} catch (Exception e) {
            e.printStackTrace();
			response.getWriter().write("파일 다운로드 실패");
        }finally {

        }
//		FileInputStream input = new FileInputStream(path+"\\"+filename);
	}

}
