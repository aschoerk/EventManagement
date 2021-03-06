package ExhibitionHall;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import tn.esprit.b2.esprit1718b2eventmanagement.entities.Booth;
import tn.esprit.b2.esprit1718b2eventmanagement.service.exhibitionhallservice.BoothServiceLocal;

/**
 * Servlet implementation class DisplayBoothServelet
 */
@WebServlet(urlPatterns = { "/img" })

	
public class DisplayBoothServelet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	@EJB
	private BoothServiceLocal boothServiceLocal;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DisplayBoothServelet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		Booth booth = boothServiceLocal.find(id);
		 if (booth == null) {
             // No record found, redirect to default image.
             response.sendRedirect(request.getContextPath() + "/resources/imgs/noimage.jpg");
             return;
         }
		 response.setHeader("Content-Type", "image/jpg");
		 response.setHeader("Content-Length", String.valueOf(getBytesFromFile(booth.getBanner()).length));
		 response.setHeader("Content-Disposition", "inline; filename=\"" + booth.getId() + "\"");
		
		 // Write image data to Response.
		 response.getOutputStream().write(getBytesFromFile(booth.getBanner()));
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
	public static byte[] getBytesFromFile(File file) throws IOException {        
        // Get the size of the file
        long length = file.length();

        // You cannot create an array using a long type.
        // It needs to be an int type.
        // Before converting to an int type, check
        // to ensure that file is not larger than Integer.MAX_VALUE.
        if (length > Integer.MAX_VALUE) {
            // File is too large
            throw new IOException("File is too large!");
        }

        // Create the byte array to hold the data
        byte[] bytes = new byte[(int)length];

        // Read in the bytes
        int offset = 0;
        int numRead = 0;

        InputStream is = new FileInputStream(file);
        try {
            while (offset < bytes.length
                   && (numRead=is.read(bytes, offset, bytes.length-offset)) >= 0) {
                offset += numRead;
            }
        } finally {
            is.close();
        }

        // Ensure all the bytes have been read in
        if (offset < bytes.length) {
            throw new IOException("Could not completely read file "+file.getName());
        }
        return bytes;
    }

}
