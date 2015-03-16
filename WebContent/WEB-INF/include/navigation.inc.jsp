<%-- Manages the main navigation menu as a function of the page that includes this jsp. --%>

<%

	/*
	 * Getting some parameters...
	 */
	 
	String includer					=			request.getParameter("includer");
	String root						=			request.getContextPath();

	/*
	 * Define the default navigation menu.
	 */
	
	String mainNavigation = ""
		+				"<aside>"
		+					"<nav>"
		+						"<ul id=\"main-navigation-menu\">"
		+							"<li>"
		+								"<a href=\"" + root + "/vue/accueil.jsp\">Accueil</a>"
		+							"</li>"
		+							"<li>"
		+								"<a href=\"javascript:;\" onclick=\"alert('Non impl�ment�, d�sol� !');\">Recherche</a>"
		+							"</li>"
		+							"<li>"
		+								"<a href=\"javascript:;\" onclick=\"alert('Non impl�ment�, d�sol� !');\">R&eacute;sultats</a>"
		+							"</li>"
		+							"<li>"
		+								"<a href=\"" + root + "/vue/referentiel/telechargement/upload.jsp\">Importer</a>"
		+							"</li>"
		+						"</ul>"
		+					"</nav>"
		+				"</aside>"  
		+				"<div class=\"clear\"></div>";

	/*
	 * Redefine the menu item matching the including page.
	 */
	 
	mainNavigation = mainNavigation.replaceFirst("<li><a href=\"[^>]+" + includer + ".jsp", "<li class=\"main-navigation-menu-current\"><a href=\"#");

%>

<%-- Write the navigation menu. --%>

<%=
	mainNavigation
%>

