<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
	<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Profile</title>
</head>
<body>
	<div class="info">
		<c:if test="${hasTFG}"> // Es un alumno
			<p>Tiene TFG</p>
	        <table>
	            <tr><c:out value="${tfg.autor}" /></tr>
	            <tr><c:out value="${tfg.titulo}" /></tr>
	            <tr><c:out value="${tfg.resumen}" /></tr>
	            <tr><c:out value="${tfg.tutor}" /></tr>
            </table>
		</c:if>
		<c:if test="${!hasTFG}">
			<c:if test="${not empty tfgs}">
			
			</c:if>
			<c:choose>
				<c:when test="${not empty tfgs}">
					<p>Estos son los tfg que tiene a su cargo: </p>
					<c:forEach items="${tfgs}" var="tfg">
						<table><tr>
				            <td><c:out value="${tfg.autor}" /></td>
				            <td><c:out value="${tfg.titulo}" /></td>
				            <td><c:out value="${tfg.resumen}" /><td>
				            <td><c:out value="${tfg.tutor}" /></td>
						</tr></table>
					</c:forEach>
				</c:when>
				<c:when test="${empty tfgs}">
					<p>Aún no has seleccionado ningún TFG, ¿Empezamos?</p>
					<form action="/tfg" method="post" acceptcharset="utf-8">
			            <input type="text" name="titulo" id="titulo" maxLength="255"
			                size="20" required placeholder="Titulo" />
			            <input type="text"
			                name="resumen" id="resumen" maxLength="255" required size="20"
			                placeholder="resumen" />
			            <input type="text" name="tutor" id="tutor"
			                maxLength="255" required size="20" placeholder="tutor" />
			            <input type="submit" value="Solicitar" />
			        </form>
				</c:when>
				<c:otherwise></c:otherwise>
			</c:choose>
		</c:if>
	</div>
</body>
</html>