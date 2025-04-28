package controller;

import java.io.*;
import java.nio.file.Files;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

@WebServlet("/fileDown")
public class FileDown extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public FileDown() {
		super();
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");

		String filename = request.getParameter("filename");
		if (filename == null || filename.trim().isEmpty()) {
			response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Filename is required.");
			return;
		}

		String path = request.getServletContext().getRealPath("/upload");
		File file = new File(path, filename);

		if (!file.exists() || !file.isFile()) {
			response.sendError(HttpServletResponse.SC_NOT_FOUND, "File not found.");
			return;
		}

		// MIME 타입 자동 감지
		String mimeType = Files.probeContentType(file.toPath());
		if (mimeType == null) {
			mimeType = "application/octet-stream"; // fallback
		}
		response.setContentType(mimeType);

		// 파일 다운로드 설정 (ISO8859-1 인코딩 변환해서 한글깨짐 방지)
		String encodedFilename = new String(filename.getBytes("UTF-8"), "ISO8859-1");
		response.setHeader("Content-Disposition", "attachment; filename=\"" + encodedFilename + "\"");

		// 파일 스트림 복사
		try (InputStream is = new FileInputStream(file);
			 OutputStream os = response.getOutputStream()) {
			byte[] buffer = new byte[4096];
			int len;
			while ((len = is.read(buffer)) != -1) {
				os.write(buffer, 0, len);
			}
		}
	}
}
