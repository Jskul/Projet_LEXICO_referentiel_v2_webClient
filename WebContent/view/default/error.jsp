<%@ page language="java" contentType="text/html; charset=iso-8859-1" pageEncoding="iso-8859-1"%>

<%@page import="controller.upload.LexiconUploadController"%>

<%
	String includer				=	"/common/error";
	String root					=	request.getContextPath();
	Exception exception			=	(Exception) request.getAttribute("exception");
	String errorMessage			=	"";
	if (exception != null) {errorMessage = exception.getMessage();}
%>

<!DOCTYPE html>
<html>
	<head>

		<jsp:include page="../../WEB-INF/include/head.inc.jsp">
            <jsp:param name="includer" value="<%=includer %>" />
        </jsp:include>

	</head>
	<body>

		<jsp:include page="../../WEB-INF/include/navigation.inc.jsp">
            <jsp:param name="includer" value="<%=includer %>" />
        </jsp:include>

		<main class="half-width">
		
			<article>
				<h1>Erreur</h1>
				<p><%= errorMessage %></p>
			</article>
		
		</main>
		

	</body>
</html>