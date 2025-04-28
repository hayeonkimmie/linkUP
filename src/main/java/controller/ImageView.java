/*
package controller;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.WebServlet;
import java.io.*;
import java.net.URLEncoder;

@WebServlet("/image")
public class ImageView extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public ImageView() {
        super();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        String filename = request.getParameter("filename");
        String path = request.getServletContext().getRealPath("upload");
        System.out.println(path);
        response.setContentType("application/octet-stream");
        response.setHeader("Content-Disposition", "attachment; filename=\"" + filename + "\"");
        try (
                InputStream is = new FileInputStream(new File(path, filename));
                OutputStream out = response.getOutputStream()
        ) {
            byte[] buffer = new byte[4096];
            int len;
            while ((len = is.read(buffer)) > 0) {
                out.write(buffer, 0, len);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
*/
package controller;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.WebServlet;
import java.io.*;
import java.net.URLEncoder;
import java.nio.file.Files;

@WebServlet("/image")
public class ImageView extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public ImageView() {
        super();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");

        String filename = request.getParameter("filename");
        if (filename == null || filename.isEmpty()) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Filename required");
            return;
        }

        String path = request.getServletContext().getRealPath("/upload");
        File file = new File(path, filename);

        if (!file.exists()) {
            response.sendError(HttpServletResponse.SC_NOT_FOUND);
            return;
        }

        // 파일 확장자에 따라 Content-Type 자동 설정
        String mimeType = Files.probeContentType(file.toPath());
        if (mimeType == null) {
            mimeType = "application/octet-stream"; // fallback
        }
        response.setContentType(mimeType);

        // 이미지 파일은 inline 표시
        response.setHeader("Content-Disposition", "inline; filename=\"" + URLEncoder.encode(filename, "UTF-8") + "\"");

        try (
                InputStream is = new FileInputStream(file);
                OutputStream os = response.getOutputStream()
        ) {
            byte[] buffer = new byte[4096];
            int len;
            while ((len = is.read(buffer)) > 0) {
                os.write(buffer, 0, len);
            }
        }
    }
}
