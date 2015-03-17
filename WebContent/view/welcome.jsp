<%@ page language="java" contentType="text/html; charset=iso-8859-1" pageEncoding="iso-8859-1"%>

<%
	String includer				=	"/common/welcome";
	String root					=	request.getContextPath();

%>

<!DOCTYPE html>
<html>
	<head>

		<jsp:include page="../WEB-INF/include/head.inc.jsp">
            <jsp:param name="includer" value="<%=includer %>" />
        </jsp:include>

	</head>
	<body>

		<jsp:include page="../WEB-INF/include/navigation.inc.jsp">
            <jsp:param name="includer" value="<%=includer %>" />
        </jsp:include>
	
		<main class="half-width">
		
			<article>
				<h1>Accueil</h1>
				<p>Bienvenus sur Lexico, choisissez une action dans le menu principal...</p>
			</article>
		
		</main>

	</body>
</html>