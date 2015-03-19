<%@ page language="java" contentType="text/html; charset=iso-8859-1" pageEncoding="iso-8859-1"%>

<%@ page import="clientServer.parameter.Cancelations" %>

<%
	String includer				=	"/lexicon/uploading";
	String root					=	request.getContextPath();
	String feedbackMessage		=	(String) request.getAttribute("feedbackMessage");
	if (feedbackMessage == null) {feedbackMessage = ""; }
	
%>

<!DOCTYPE html>
<html>
	<head>

		<jsp:include page="../../../WEB-INF/include/head.inc.jsp">
            <jsp:param name="includer" value="<%=includer %>" />
        </jsp:include>

	</head>
	<body>

		<jsp:include page="../../../WEB-INF/include/navigation.inc.jsp">
            <jsp:param name="includer" value="<%=includer %>" />
        </jsp:include>

		<main class="third-width">
		
			<article>
				<h1>Téléchargement</h1>

				<fieldset>
					<legend>Charger un fichier Lexique 3.8</legend>
					
					<!-- @see http://stackoverflow.com/questions/2827778/does-form-with-enctype-multipart-form-data-cause-problems-accessing-a-hidden-f -->
					<form id="lexique380-upload-form" enctype="multipart/form-data" method="post" action="<%= root %>/lexicon/upload?lexicon-type=lexique380" >

						<!-- <input type="text" name="lexicon-type" value="lexique380" /> -->
 
						<div class="label-control-container">
							<label id="lexicon-file-label" for="lexicon-file">Fichier</label> <input type="file" id="lexicon-file" name="lexicon-file">
							<div class="clear"></div>
						</div>

						<div id="buttons-bar">
							<input id="lexique380-file-submit" type="submit" value="Ouvrir" title="Ouvrir">
							<button id="lexique380-file-cancel" type="submit" formaction="<%= root %>/common/cancelation?cancelationMessage=<%= Cancelations.LEXICON_UPLOADING.getURLEncodedMessage() %>" title="Annuler">Annuler</button>
						</div>

					</form>
					
				</fieldset>
			</article>
		
		</main>
		
		<%
			if (feedbackMessage.isEmpty() == false) {
				out.println("<script>alert(\"" + feedbackMessage + "\");</script>");
			}
		%>

	</body>
</html>