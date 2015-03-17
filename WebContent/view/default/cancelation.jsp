<%@ page language="java" contentType="text/html; charset=iso-8859-1" pageEncoding="iso-8859-1"%>

<%@ page import="clientServer.parameter.Cancelations" %>

<%
	String includer				=	"/common/cancelation";
	String root					=	request.getContextPath();
	String candelationMessage	=	request.getParameter("cancelationMessage");
	if (candelationMessage == null) {candelationMessage = Cancelations.MISC.getMessage();}
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
				<h1>Annulation</h1>
				<p><%= candelationMessage %></p>
			</article>
		
		</main>
		

	</body>
</html>