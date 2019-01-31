package tn.esprit.b2.esprit1718b2eventmanagement.hr;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

import javax.ejb.EJB;
import javax.faces.bean.ManagedProperty;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import com.google.common.io.Files;

import tn.esprit.b2.esprit1718b2eventmanagement.entities.User;
import tn.esprit.b2.esprit1718b2eventmanagement.mBeans.Identity;
import tn.esprit.b2.esprit1718b2eventmanagement.services.UserServiceLocal;

/**
 * Servlet implementation class UploadProfilePic
 */
@WebServlet("/haja")
@MultipartConfig(maxFileSize = 16177215)
public class UploadProfilePic extends HttpServlet {
	private static final long serialVersionUID = 1L;
       @EJB
       UserServiceLocal userServiceLocal;
       @ManagedProperty("#{identity}")
   	private Identity identity;
     private  User u ;
     
   	public User getU() {
		return u;
	}

	public void setU(User u) {
		this.u = u;
	}

	public Identity getIdentity() {
   		return identity;
   	}

   	public void setIdentity(Identity identity) {
   		this.identity = identity;
   	}
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UploadProfilePic() {
    	
        super();
        u=new User();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String id = request.getParameter("co");
		Part file = request.getPart("pic");
		InputStream inputStream = null;
		if(file!=null) {
			inputStream = file.getInputStream();
		}
		try {
			if (inputStream != null) {
				byte[] buffer = new byte[inputStream.available()] ;
				inputStream.read(buffer);
				File f = File.createTempFile("hajao5ra", ".tmp");
				Files.write(buffer, f);
				
				u= userServiceLocal.find(Integer.parseInt(id));
				System.out.println("aaaaaaaaaaaaaaaaaaa"+u.getId());
				u.setProfilePic(f);
				
				userServiceLocal.update(u);
				
				inputStream.close();
				f.deleteOnExit();
				getServletContext().getRequestDispatcher("/profileCompanyOwner.jsf").forward(request, response);
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

}
