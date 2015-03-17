package parameter;

/**
 * Some general client-server parameters.
 * 
 * @author JL
 */
public class Parameters {
	
	/*
	 * Project names.
	 */
	public final static String SERVER_PROJECT_NAME = "Projet_LEXICO_referentiel_v2";
	public final static String WEB_CLIENT_PROJECT_NAME = "Projet_LEXICO_referentiel_v2_webClient";
	
	/*
	 * EJB paths.
	 */
	public final static String EJB_SERVICE_FACADE = "ejb:/" + SERVER_PROJECT_NAME + "/ServiceFacadeBean!clientServer.service.ServiceFacadeI";
	
	/*
	 * JSP paths.
	 */
	public final static String JSP_PATH_WELCOME			=	"/view/welcome.jsp";
	public final static String JSP_PATH_ERROR			=	"/view/default/error.jsp";
	public final static String JSP_PATH_CANCELATION		=	"/view/default/cancelation.jsp";
	public final static String JSP_PATH_UPLOADING		=	"/view/lexicon/loading/uploading.jsp";
	
	
	
}
