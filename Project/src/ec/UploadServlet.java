package ec;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

/**
 * Servlet implementation class UploadServlet
 */
@WebServlet("/UploadServlet")
//@MultipartConfig(location="/tmp", maxFileSize=1048576)
@MultipartConfig(location="", maxFileSize=1048576)
public class UploadServlet extends HttpServlet {
	//@Override
	private static final long serialVersionUID = 1L;
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		request.getRequestDispatcher("/WEB-INF/jsp/upload.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Part part = request.getPart("file");
        String name = this.getFileName(part);
        System.out.println(name);
        //part.write(getServletContext().getRealPath("/WEB-INF/uploaded") + "/" + name);/Users/sakurai/git/MyWebSite/Project/WebContent/WEB-INF/uploadsample
        part.write("/Users/sakurai/git/MyWebSite/Project/WebContent/WEB-INF/uploadsample" + "/" + name);
        System.out.println(getServletContext().getRealPath("/WEB-INF/uploadsample") + "/" + name);
        response.sendRedirect("Index");
    }

    private String getFileName(Part part) {
        String name = null;
        for (String dispotion : part.getHeader("Content-Disposition").split(";")) {
            if (dispotion.trim().startsWith("filename")) {
                name = dispotion.substring(dispotion.indexOf("=") + 1).replace("\"", "").trim();
                name = name.substring(name.lastIndexOf("\\") + 1);
                break;
            }
        }
        return name;
    }
}
