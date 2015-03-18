package controller;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import parameter.Parameters;
import clientServer.utility.Utilities;
import clientServer.parameter.Errors;


/**
 * Filters access.
 * 
 * @author JL
 */
@WebFilter (urlPatterns = {"/*"})
public class FrontController implements Filter {
	
	@Override
	public void init(FilterConfig config) throws ServletException {
	}

	/**
	 * Manages access.
	 */
	@Override
	public void doFilter(ServletRequest sRequest, ServletResponse sResponse, FilterChain chain) throws IOException, ServletException {
		HttpServletRequest hRequest = (HttpServletRequest) sRequest;
		
		System.out.println("#### " +  sRequest.getAttribute("upload-data-type") );
		System.out.println("#### " +  sRequest.getAttribute("lexique-file") );
		System.out.println("#### " +  hRequest.getAttribute("upload-data-type") );
		System.out.println("#### " +  hRequest.getAttribute("lexique-file") );
		
		HttpServletResponse hResponse = (HttpServletResponse) sResponse;
		RequestDispatcher dispatcher;
		
		String sPath = hRequest.getServletPath();

		Utilities.trace(this.getClass().getName(), ".doFilter()", "sPath = " + sPath, null, false);

		/*
		 * Redirect to the welcome page.
		 */
		if (sPath.equals("/") || sPath.equals("/common/welcome")) {
			Utilities.trace(this.getClass().getName(), ".doFilter()", "Redirect to welcome page", true, false);
			dispatcher = hRequest.getRequestDispatcher(Parameters.JSP_PATH_WELCOME);
			dispatcher.forward(hRequest, hResponse);
			Utilities.trace(this.getClass().getName(), ".doFilter()", "Redirect to welcome page", false, false);
		}
		
		/*
		 * Redirect cancelation.
		 */
		else if (sPath.startsWith("/common/cancelation")) {
			Utilities.trace(this.getClass().getName(), ".doFilter()", "Redirect to cancelation page", true, false);
			String origin = hRequest.getParameter("origin");
			String cancelationMessage = origin;
			hRequest.setAttribute("cancelationMessage", cancelationMessage);
			dispatcher = hRequest.getRequestDispatcher(Parameters.JSP_PATH_CANCELATION);
			dispatcher.forward(hRequest, hResponse);
			Utilities.trace(this.getClass().getName(), ".doFilter()", "Redirect to cancelation page", false, false);
		}
		
		/*
		 * Filter web contents common folders.
		 */
		else if (sPath.startsWith("/_common/")) {
			Utilities.trace(this.getClass().getName(), ".doFilter()", "/_common/*", true, false);
			chain.doFilter(hRequest, hResponse);
			Utilities.trace(this.getClass().getName(), ".doFilter()", "/_common/*", false, false);
		}
		
		/*
		 * Filter by use case.
		 */
		
		// Data management.
		else if (sPath.startsWith("/TODO")) {
			Utilities.trace(this.getClass().getName(), ".doFilter()", "Redirect to consultation/*", true, false);
			chain.doFilter(hRequest, hResponse);
			Utilities.trace(this.getClass().getName(), ".doFilter()", "Redirect to consultation/*", false, false);
		}
		// Data consultation.
		else if (sPath.startsWith("/TODO")) {
			Utilities.trace(this.getClass().getName(), ".doFilter()", "Redirect to gestion/*", true, false);
			chain.doFilter(hRequest, hResponse);
			Utilities.trace(this.getClass().getName(), ".doFilter()", "Redirect to gestion/*", false, false);
		}
		// Data uploading.
		else if (sPath.startsWith("/lexicon/upload")) {
			Utilities.trace(this.getClass().getName(), ".doFilter()", "Redirect to upload*", true, false);
			if (sPath.equals("/lexicon/uploading")) {
				dispatcher = hRequest.getRequestDispatcher(Parameters.JSP_PATH_UPLOADING);
				dispatcher.forward(hRequest, hResponse);
			} else {				
				chain.doFilter(hRequest, hResponse);
			}
			Utilities.trace(this.getClass().getName(), ".doFilter()", "Redirect to upload*", false, false);
		}

		/*
		 * Redirect to error page.
		 */
		else {
			Utilities.trace(this.getClass().getName(), ".doFilter()", "Redirect to error page", true, false);
			Exception exception = new Exception(Errors.FORBIDDEN_ACCESS.getMessage());
			hRequest.setAttribute("exception", exception);
			dispatcher = hRequest.getRequestDispatcher(Parameters.JSP_PATH_ERROR);
			dispatcher.forward(hRequest, hResponse);
			Utilities.trace(this.getClass().getName(), ".doFilter()", "Redirect to error page", false, false);
		}
	}

	@Override
	public void destroy() {
	}
}
