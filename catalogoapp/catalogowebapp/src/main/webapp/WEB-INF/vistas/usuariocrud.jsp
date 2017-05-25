<%@ include file="includes/cabecera.jsp"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<h2>Mantenimiento de usuarios</h2>

<table border="1">
	<thead>
		<tr>
			<th>Operaciones</th>
			<th>Usuario</th>
			<th>Contraseña</th>
			<th>Nivel de Acceso</th>
		</tr>
	</thead>
	<tbody>
		<c:forEach items="${requestScope.usuarios}" var="usuario">
			<tr>
				<td>
					<a href="usuariocrud?op=modificar&id=${usuario.nombre}">Modificar</a>
					<a href="usuariocrud?op=borrar&id=${usuario.nombre}">Borrar</a>
				</td>
				<td>${usuario.nombre}</td>
				<td>${usuario.pass}</td>
				<td>${usuario.nivel}</td>
			</tr>
		</c:forEach>
	</tbody>
</table>

<a href="usuariocrud?op=alta">Alta</a>

<%@ include file="includes/pie.jsp"%>
