<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
	<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
	
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>Sistema de gestión de TFG</h1>
	<c:if test="${not empty user.username}">
		<p>
			Bienvenido <c:out value="${user.username}"/>
		</p>
    
		<form action="/profile" method="GET">
			<input type="text" name="name" value="<c:out value="${user.username}"/>" style="display: none;"/>
			<input type="submit" value="Go to profile"/>
		</form>
	</c:if>
	
	<p>Puedes pulsar el siguiente enlace para salir <a href="<c:url value="${url}"/>"><c:out value="${urlLinktext}"/></a></p>
</body>
</html>