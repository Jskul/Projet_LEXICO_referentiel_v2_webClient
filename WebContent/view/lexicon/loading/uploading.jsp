<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ page import="clientServer.parameter.CancelationOrigins" %>

<%
	String includer				=	"/lexicon/uploading";
	String root					=	request.getContextPath();
	
	// TODO
	
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
					
					<form id="lexique-upload-form" enctype="multipart/form-data" method="post" action="<%= root %>/lexicon/upload" >

						<input type="hidden" name="upload-data-type" value="lexique380" />

						<div class="label-control-container">
							<label id="lexique-file-label" for="lexique-file">Fichier</label> <input type="file" id="lexique-file" name="lexique-file">
							<div class="clear"></div>
						</div>

						<div id="buttons-bar">
							<input id="lexique-file-submit" type="submit" value="Ouvrir" title="Ouvrir">
							<button id="lexique-file-cancel" type="submit" formaction="<%= root %>/common/cancelation?origin=<%= CancelationOrigins.LEXICON_UPLOADING.getURLEncodedMessage() %>" title="Annuler">Annuler</button>
						</div>

					</form>
					
				</fieldset>
			</article>
		
		</main>
		

	</body>
</html>