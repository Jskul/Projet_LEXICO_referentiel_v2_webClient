package controller.upload;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import clientServer.exception.LexicoUploadException;
import clientServer.parameter.Errors;
import clientServer.utility.Utilities;

/**
 * A controller for lexical data upload.
 * 
 * Servlet implementation class DataUploadController
 */
@WebServlet("/lexicon/upload")
public class LexiconUploadController extends HttpServlet {
	/**
	 * TODO serialVersionUID
	 */
	private static final long serialVersionUID = 1L;

	private RequestDispatcher dispatcher;
//	private ServiceI serviceStateless;

	/**
	 * Processes get requests.
	 * 
	 * @param	request		HttpServletRequest	A servlet request.
	 * @param	response	HttpServletResponse	A servlet response.
	 * 
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO doGet
	}

	/**
	 * Processes post requests.
	 * 
	 * @param	request		HttpServletRequest	A servlet request.
	 * @param	response	HttpServletResponse	A servlet response.
	 * 
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Utilities.trace(this.getClass().getName(), ".doPost()", null, true, false);
		//String action  = request.getPathInfo().toLowerCase();
		String action = request.getServletPath();
		Utilities.trace(this.getClass().getName(), ".doPost()", "action = " + action, null, false);

		try {
			switch (action) {
				case "/lexicon/upload":
					doUpload(request, response);
				break;

				default :
					throw new LexicoUploadException(Errors.FORBIDDEN_ACCESS);
			}
			if (true) {return;}
		} catch (Exception e) {
			doError(request, response, e);
		}
		Utilities.trace(this.getClass().getName(), ".doPost()", null, false, false);
	}

	/**
	 * Performs lexicon upload.
	 * 
	 * @param	request		HttpServletRequest	A servlet request.
	 * @param	response	HttpServletResponse	A servlet response.
	 */
	private void doUpload(HttpServletRequest request, HttpServletResponse response) {
		Utilities.trace(this.getClass().getName(), ".doUpload()", null, true, false);
		
		Utilities.trace(this.getClass().getName(), ".doUpload()", "*** TODO ***", null, false);
		
		Utilities.trace(this.getClass().getName(), ".doUpload()", null, false, false);
	}






	
	/**
	 * Sets the service EJB.
	 * 
	 * @param	request		HttpServletRequest	A servlet request.
	 * @param	response	HttpServletResponse	A servlet response.
	 * 
	 * @throws IOException
	 * @throws ServletException
	 */
//	private void setServiceEJB(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
//		Utilities.trace(this.getClass().getName(), ".setServiceEJB()", "");
//		
//		InitialContext initialContext;
//
//		try {
//			initialContext = new InitialContext();
//			this.serviceStateless  = (ServiceI) initialContext.lookup( ParametersI.SERVICE_EJB );
//		} catch (Exception e) {
//			doError(request, response, e);
//		}
//		
//		Utilities.trace(this.getClass().getName(), ".setServiceEJB() ########################### FIN ###########################", "");
//	}
	
	/**
	 * Redirects to an error page.
	 * 
	 * @param	request		HttpServletRequest	A servlet request.
	 * @param	response	HttpServletResponse	A servlet response.
	 * @param	exception	Exception			An exception whose message is to be displayed on the error page (optional).
	 * 
	 * @throws IOException
	 * @throws ServletException
	 */
	private void doError(HttpServletRequest request, HttpServletResponse response, Exception exception) throws IOException, ServletException {
		Utilities.trace(this.getClass().getName(), ".doError()", null, true, false);
		request.setAttribute("exception", exception);
		this.dispatcher = request.getRequestDispatcher("/view/default/error.jsp");
		this.dispatcher.forward(request, response);
		Utilities.trace(this.getClass().getName(), ".doError()", null, false, false);
	}
	
}
