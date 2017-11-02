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
@MultipartConfig(location="/tmp", maxFileSize=1048576)
//@MultipartConfig(location="", maxFileSize=1048576)
public class UploadServlet extends HttpServlet {
	//@Override
	private static final long serialVersionUID = 1L;
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		request.getRequestDispatcher("/WEB-INF/jsp/upload.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println(request.getParameter("success")!=null?(String)request.getParameter("success"):"?????");
        Part part = request.getPart("file");
        System.out.println(part);
        String name = this.getFileName(part);
        System.out.println(name);
        //part.write(getServletContext().getRealPath("/WEB-INF/uploaded") + "/" + name);
        part.write(EcHelper.UPLOAD_PAGE + "/" + name);
        System.out.println(getServletContext().getRealPath("./img") + "/" + name);
        request.setAttribute("name",name);
        request.getRequestDispatcher("/WEB-INF/jsp/upload.jsp").forward(request, response);
        //response.sendRedirect("UploadServlet");
    }

    private String getFileName(Part part) {
        String name = null;
        for (String dispotion : part.getHeader("Content-Disposition").split(";")) {
        	System.out.println(dispotion);
            if (dispotion.trim().startsWith("filename")) {
                name = dispotion.substring(dispotion.indexOf("=") + 1).replace("\"", "").trim();
                System.out.println("name:"+name);
                name = name.substring(name.lastIndexOf("\\") + 1);
                break;
            }
        }
        return name;
    }
}
