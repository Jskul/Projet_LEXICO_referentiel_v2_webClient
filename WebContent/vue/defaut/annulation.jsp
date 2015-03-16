<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%
	String includer				=	"/annulation";
	String root					=	request.getContextPath();
	String candelationMessage			=	(String) request.getAttribute("cancelationMessage");
	if (candelationMessage == null) {candelationMessage = "";}
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