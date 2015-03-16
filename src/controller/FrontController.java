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
		HttpServletResponse hResponse = (HttpServletResponse) sResponse;
		
		String sPath = hRequest.getServletPath() + "/";
		
		/*
		 * Redirect to the welcome page.
		 */
		if (sPath.equals("//") || sPath.equals("/accueil")) {
			Utilities.trace(this.getClass().getName(), ".doFilter()", "Redirect to welcome page", true, false);
			RequestDispatcher dispatcher = hRequest.getRequestDispatcher("/vue/accueil.jsp");
			dispatcher.forward(hRequest, hResponse);
			Utilities.trace(this.getClass().getName(), ".doFilter()", "Redirect to welcome page", false, false);
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
		// Data exchange.
		else if (sPath.startsWith("/vue/referentiel/telechargement/")) {
			Utilities.trace(this.getClass().getName(), ".doFilter()", "Redirect to telechargement/*", true, false);
			chain.doFilter(hRequest, hResponse);
			Utilities.trace(this.getClass().getName(), ".doFilter()", "Redirect to telechargement/*", false, false);
		}
		else if (sPath.startsWith("/telecharger/")) {
			Utilities.trace(this.getClass().getName(), ".doFilter()", "To uploadController", true, false);
			RequestDispatcher dispatcher = hRequest.getRequestDispatcher("/DataUploadController");
			dispatcher.include(hRequest, hResponse);
			Utilities.trace(this.getClass().getName(), ".doFilter()", "To uploadController", false, false);
		}
		
		/*
		 * Redirect cancelation.
		 */
		
		else if (sPath.startsWith("/annuler")) {
			Utilities.trace(this.getClass().getName(), ".doFilter()", "Redirect to annuler", true, false);
			
			String origin = hRequest.getParameter("origine");
			String cancelationMessage = "";
			
			// TODO >>>
			switch (origin) {
				case "upload":
					cancelationMessage = "Upload abandonné.";
				break;
				
				default:
					cancelationMessage = "Abandon.";
				break;
			}
			// <<< TODO
			
			hRequest.setAttribute("cancelationMessage", cancelationMessage);
			RequestDispatcher dispatcher = hRequest.getRequestDispatcher("/vue/defaut/annulation.jsp");
			dispatcher.forward(hRequest, hResponse);

			Utilities.trace(this.getClass().getName(), ".doFilter()", "Redirect to annuler", false, false);
		}
		
		/*
		 * Filter web contents common folders.
		 */
		else if (sPath.startsWith("/_commun/")) {
			Utilities.trace(this.getClass().getName(), ".doFilter()", "_commun/*", true, false);
			chain.doFilter(hRequest, hResponse);
			Utilities.trace(this.getClass().getName(), ".doFilter()", "_commun/*", false, false);
		}
		
		/*
		 * Redirect to error page.
		 */
		else {
			Utilities.trace(this.getClass().getName(), ".doFilter()", "Redirect to error page", true, false);
			hRequest.setAttribute("errorMessage", Errors.FORBIDDEN_ACCESS.getMessage());
			RequestDispatcher dispatcher = hRequest.getRequestDispatcher("/vue/defaut/erreur.jsp");
			dispatcher.forward(hRequest, hResponse);
			Utilities.trace(this.getClass().getName(), ".doFilter()", "Redirect to error page", false, false);
		}
	}

	@Override
	public void destroy() {
	}
}
