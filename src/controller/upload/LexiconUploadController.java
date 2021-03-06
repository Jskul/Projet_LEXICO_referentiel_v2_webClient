package controller.upload;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Map;
import java.util.Scanner;

import javax.naming.InitialContext;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import parameter.Parameters;
import clientServer.exception.LexicoUploadException;
import clientServer.parameter.Errors;
import clientServer.parameter.ParametersI;
import clientServer.service.ServiceFacadeI;
import clientServer.utility.Utilities;

/**
 * A controller for lexical data upload.
 * 
 * Servlet implementation class DataUploadController
 * 
 * TODO GERER EXCEPTIONS
 */
@WebServlet("/lexicon/upload")
public class LexiconUploadController extends HttpServlet {
	/**
	 * TODO serialVersionUID
	 */
	private static final long serialVersionUID = 1L;

	private RequestDispatcher dispatcher;
	private ServiceFacadeI serviceFacadeStateless;

	/**
	 * Processes get requests.
	 * 
	 * @param	request		HttpServletRequest	A servlet request.
	 * @param	response	HttpServletResponse	A servlet response.
	 * 
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO
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
	 * @throws Exception 
	 */
	@SuppressWarnings("resource")
	private void doUpload(HttpServletRequest request, HttpServletResponse response) throws Exception {
		Utilities.trace(this.getClass().getName(), ".doUpload()", null, true, false);
		String lexiconType = request.getParameter("lexicon-type");		

		/*
		 *  Upload the file.
		 */
		try {			
			this.dispatcher = request.getRequestDispatcher("/UploadFileServlet");
			this.dispatcher.include(request, response);
		} catch (Exception e) {
			throw new Exception("TODO upload failed"); // TODO
		}

		/*
		 * Scan the uploaded file.
		 */
		setServiceEJB(request, response);

		String FILE_PATH = "";
		File file = null;
		Map<String, String> report = null;
		if (lexiconType.equals("lexique380")) {
			FILE_PATH = Parameters.LEXICON_SOURCE_FILE_PATH_LEXIQUE_380;
			file = new File(FILE_PATH);
			report = this.serviceFacadeStateless.persistLexique380File(file);
		}
		
        /*
         * Set feedback.
         */
		String feedbackMessage = "";
		if (report != null) {			
			feedbackMessage += report.get("deletedCount") + "/" + report.get("itemCountBefore") + " supprim�es.\\n";
			feedbackMessage += report.get("processed") + "/" + report.get("lines") + " trait�es, dont " + report.get("success") + " avec succ�s.";
		} else {
			feedbackMessage += "L'action s'est termin�e de mani�re inattendue.";
		}

		request.setAttribute("feedbackMessage", feedbackMessage);
		this.dispatcher = request.getRequestDispatcher(Parameters.JSP_PATH_UPLOADING);
		this.dispatcher.forward(request, response);
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
	private void setServiceEJB(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		Utilities.trace(this.getClass().getName(), ".setServiceEJB()", null, true, false);
		InitialContext initialContext;
		try {
			initialContext = new InitialContext();
			this.serviceFacadeStateless  = (ServiceFacadeI) initialContext.lookup( ParametersI.EJB_SERVICE_FACADE );
		} catch (Exception e) {
			doError(request, response, e);
		}
		Utilities.trace(this.getClass().getName(), ".setServiceEJB()", null, false, false);
	}
	
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
		this.dispatcher = request.getRequestDispatcher(Parameters.JSP_PATH_ERROR);
		this.dispatcher.forward(request, response);
		Utilities.trace(this.getClass().getName(), ".doError()", null, false, false);
	}
	
}
