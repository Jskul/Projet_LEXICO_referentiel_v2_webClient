package controller.upload;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
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
@WebServlet("/XXXXlexicon/upload")
public class LexiconUploadController_BAK extends HttpServlet {
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
//		Utilities.trace(this.getClass().getName(), ".doUpload()", null, true, false);
//		String lexiconType = request.getParameter("lexicon-type");		
//
//		/*
//		 *  Upload the file.
//		 */
//		try {			
//			this.dispatcher = request.getRequestDispatcher("/UploadFileServlet");
//			this.dispatcher.include(request, response);
//		} catch (Exception e) {
//			throw new Exception("TODO upload failed"); // TODO
//		}
//
//		/*
//		 * Scan the uploaded file.
//		 */
//		setServiceEJB(request, response);
//
//		String FILE_PATH = "";
//		int SKIP_HEADER_LINES = 0;
//		boolean deletionStatus = false;
//		if (lexiconType.equals("lexique380")) {
//			FILE_PATH = Parameters.LEXICON_SOURCE_FILE_PATH_LEXIQUE_380;
//			SKIP_HEADER_LINES = Parameters.LEXICON_SOURCE_FILE_SKIP_HEADER_LINES_LEXIQUE_380;
//			deletionStatus = this.serviceFacadeStateless.emptyLexique380();
//			Utilities.trace(this.getClass().getName(), ".doUpload()", "deletionStatus = " + deletionStatus, null, false);
//		}
//		
//        Scanner scanner = null;
//		try {
//			scanner = new Scanner( new File(FILE_PATH) ); // TODO
//		} catch (FileNotFoundException e1) {
//			throw new Exception("TODO file not found"); // TODO
//		}
//
//        int index = 1;
//        boolean persistStatus = false;
//        int success = 0;
//        int processed = 0;
//
//        while (scanner.hasNextLine()) {
//        	String line = scanner.nextLine();
//        	if(index > SKIP_HEADER_LINES) {
//        		processed++;
//	            try {
//	            	if (lexiconType.equals("lexique380")) {	            		
//	            		persistStatus = this.serviceFacadeStateless.persistLexique380Line(line);
//	            	}
//	            	if (persistStatus) {
//	            		success++;
//	            	}
//				} catch (Exception e) {
//					throw new Exception("TODO parsing failed"); // TODO
//				}
//        	}
//            index++;
//        }
//        scanner.close();
//        
//        /*
//         * Set feedback.
//         */
//		String feedbackMessage = "";
//		if (processed > 0) {
//			if (processed == success) {
//				feedbackMessage = "Fichier importé avec succès (" + success + " entrées enregistrées).";
//			} else {
//				feedbackMessage = "Fichier importé (" + success + " entrées enregistrées, traitement en échec pour " + (processed - success) + " entrées).";
//			}
//		} else {
//			feedbackMessage = "Aucune ligne traitée.";
//		}
//
//		request.setAttribute("feedbackMessage", feedbackMessage);
//		this.dispatcher = request.getRequestDispatcher(Parameters.JSP_PATH_UPLOADING);
//		this.dispatcher.forward(request, response);
//		Utilities.trace(this.getClass().getName(), ".doUpload()", null, false, false);
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
