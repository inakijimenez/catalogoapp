<%@ include file="includes/cabecera.jsp"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<h2>Mantenimiento de usuarios</h2>

<table border="1">
	<thead>
		<tr>
			<th>Operaciones</th>
			<th>Id</th>
			<th>Usuario</th>
			<th>Contraseña</th>
			<th>Id Roles</th>
			<th>Nombre Completo</th>
		</tr>
	</thead>
	<tbody>
		<c:forEach items="${requestScope.usuarios}" var="usuario">
			<tr>
				<td>
					<a href="?op=modificar&id=${usuario.username}">Modificar</a>
					<a href="?op=borrar&id=${usuario.username}">Borrar</a>
				</td>
				<td>${usuario.id}</td>
				<td>${usuario.username}</td>
				<td>${usuario.password}</td>
				<td>${usuario.rol.idRol}</td>
				<td>${usuario.nombre_completo}</td>
			</tr>
		</c:forEach>
	</tbody>
</table>

<a href="usuariocrud?op=alta">Alta</a>

<%@ include file="includes/pie.jsp"%>
