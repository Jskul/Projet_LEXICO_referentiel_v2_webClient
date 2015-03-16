<%-- Manages <head>...</head> contents as a function of the page that includes this jsp. --%>

<%

	/*
	 * Getting some parameters...
	 */
	 
	String includer						=			request.getParameter("includer");
	String root							=			request.getContextPath();

	/*
	 * Define default tags of the <head>...</head>.
	 */
	
	String tagMetaHttpEquiv				=			"<meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\">";
	String tagMetaCharSet				=			"<meta charset=\"UTF-8\">";
	String tagTitle						=			"<title>LEXICO R�f�rentiel</title>";
	String tagLinkRelStyleSheet			=			"<link rel=\"stylesheet\" href=\"" + root + "/_common/css/lexico.css\" media=\"screen\">";
	String tagScript					=			"";

	/*
	 * Redefine tags as a function of the including page.
	 */

	if (includer.equals("/common/welcome")) {
		tagTitle					=			"<title>LEXICO R�f�rentiel - Accueil</title>";
	} else if (includer.equals("/common/error")) {
		tagTitle					=			"<title>LEXICO R�f�rentiel - Erreur</title>";
	} else if (includer.equals("/common/cancelation")) {
		tagTitle					=			"<title>LEXICO R�f�rentiel - Annulation</title>";
	} else if (includer.equals("/lexicon/uploading")) {
		tagTitle					=			"<title>LEXICO R�f�rentiel - Upload</title>";
		
 		tagLinkRelStyleSheet		+=			"<link rel=\"stylesheet\" href=\"" + root + "/_common/css/form.css\" media=\"screen\">";
	} /*else if (includer.equals("/do/formeslister")) {
		tagTitle					=			"<title>LEXICO R�f�rentiel - R�sultats</title>";
		
 		tagLinkRelStyleSheet		+=			"<link rel=\"stylesheet\" href=\"" + root + "/_common/css/form.css\" media=\"screen\">";
 		tagLinkRelStyleSheet		+=			"<link rel=\"stylesheet\" href=\"" + root + "/_common/css/formesLister.css\" media=\"screen\">";

	} else if (includer.equals("lemmeCreer.jsp")) {
		tagTitle					=			"<title>LEXICO R�f�rentiel - Cr�ation d'un lemme</title>";
		
 		tagLinkRelStyleSheet		+=			"<link rel=\"stylesheet\" href=\"" + root + "/_common/css/form.css\" media=\"screen\">";
 		tagLinkRelStyleSheet		+=			"<link rel=\"stylesheet\" href=\"" + root + "/_common/css/itemInterface.css\" media=\"screen\">";
 		tagLinkRelStyleSheet		+=			"<link rel=\"stylesheet\" href=\"" + root + "/_common/js/jquery-ui-1.11.3/jquery-ui.min.css\">";
 		
 		tagScript					+=			"<script src=\"" + root + "/_common/js/jquery-ui-1.11.3/external/jquery/jquery.js\"></script>";
 		tagScript					+=			"<script src=\"" + root + "/_common/js/jquery-ui-1.11.3/jquery-ui.min.js\"></script>";
 		tagScript					+=			"<script src=\"" + root + "/_common/js/lexico.js\"></script>";
	} else if (includer.equals("lemmeModifier.jsp")) {
		tagTitle					=			"<title>LEXICO R�f�rentiel - Modification d'un lemme</title>";
		
 		tagLinkRelStyleSheet		+=			"<link rel=\"stylesheet\" href=\"" + root + "/_common/css/form.css\" media=\"screen\">";
 		tagLinkRelStyleSheet		+=			"<link rel=\"stylesheet\" href=\"" + root + "/_common/css/itemInterface.css\" media=\"screen\">";
 		tagLinkRelStyleSheet		+=			"<link rel=\"stylesheet\" href=\"" + root + "/_common/js/jquery-ui-1.11.3/jquery-ui.min.css\">";
 		
 		tagScript					+=			"<script src=\"" + root + "/_common/js/jquery-ui-1.11.3/external/jquery/jquery.js\"></script>";
 		tagScript					+=			"<script src=\"" + root + "/_common/js/jquery-ui-1.11.3/jquery-ui.min.js\"></script>";
 		tagScript					+=			"<script src=\"" + root + "/_common/js/lexico.js\"></script>";
	}*/

%>

<%-- Write <head>...</head> contents. --%>

<%=
		tagMetaHttpEquiv
	+	tagMetaCharSet
	+	tagTitle
	+	tagLinkRelStyleSheet
	+	tagScript
%>

