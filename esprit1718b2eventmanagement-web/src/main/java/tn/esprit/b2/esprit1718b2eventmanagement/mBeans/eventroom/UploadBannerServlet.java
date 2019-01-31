package tn.esprit.b2.esprit1718b2eventmanagement.mBeans.eventroom;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import com.google.common.io.Files;

import tn.esprit.b2.esprit1718b2eventmanagement.entities.SpecialEvent;
import tn.esprit.b2.esprit1718b2eventmanagement.services.eventmanagementservice.specialeventservices.SpecialEventServicesLocal;

/**
 * Servlet implementation class UploadBannerServlet
 */
@WebServlet("/uploadServlet")
@MultipartConfig(maxFileSize = 16177215)
public class UploadBannerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	@EJB
	SpecialEventServicesLocal specialEventServicesLocal;
	private SpecialEvent specialEvent;
	private Part file;
	private byte img[];

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public UploadBannerServlet() {
		super();
		// TODO Auto-generated constructor stub
		specialEvent = new SpecialEvent();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// gets values of text fields
		// specialEvent.setName(request.getParameter("nameee"));
		String sid = request.getParameter("pid");
		file = request.getPart("photo");
		InputStream inputStream = null; // input stream of the upload file

		// obtains the upload file part in this multipart request

		if (file != null) {
			// prints out some information for debugging
			System.out.println(file.getName());
			System.out.println(file.getSize());
			System.out.println(file.getContentType());

			// obtains input stream of the uploafild file
			inputStream = file.getInputStream();
		}

		String message = null; // message will be sent back to client

		try {
			// connects to the database

			if (inputStream != null) {
				// fetches input stream of the uplo File f Zad file for the blob column
				byte[] buffer = new byte[inputStream.available()];
				inputStream.read(buffer);
				File targetFile = File.createTempFile("fdgfd", ".tmp");
				Files.write(buffer, targetFile);
				specialEvent = specialEventServicesLocal.find(Integer.parseInt(sid));
				specialEvent.setEventBanner(targetFile);

				specialEventServicesLocal.update(specialEvent);
				request.setAttribute("Message", message);
				inputStream.close();
				targetFile.deleteOnExit();
				// forwards to the message page
				getServletContext().getRequestDispatcher("/eventBack.jsf?id=" + specialEvent.getId()).forward(request,
						response);
			}

		} catch (Exception e) {
			// TODO: handle exception
		}
		// sets the message in request scope

	}

	public SpecialEvent getSpecialEvent() {
		return specialEvent;
	}

	public void setSpecialEvent(SpecialEvent specialEvent) {
		this.specialEvent = specialEvent;
	}

	public Part getFile() {
		return file;
	}

	public void setFile(Part file) {
		this.file = file;
	}

	public byte[] getImg() {
		return img;
	}

	public void setImg(byte[] img) {
		this.img = img;
	}

}
