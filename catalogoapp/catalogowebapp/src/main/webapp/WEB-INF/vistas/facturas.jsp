<%@ include file="includes/cabecera.jsp"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<h2>Listado de Facturas por Usuario</h2>

<table border="1">
	<thead>
		<tr>
			<th>Operaciones</th>
			<th>Id Factura</th>
		
			<th>Usuario</th>
			<th>Fecha de Factura</th>
			
		</tr>
	</thead>
	<tbody>
		<c:forEach items="${requestScope.facturas}" var="facturas">
			<tr>
				<td>
					<a href="?op=modificar&id=${facturas.id}">Modificar</a>
				</td>
				<td>${facturas.id}</td>
		
				<td>${facturas.usuario.username}</td>
				<td>${facturas.fecha}</td>
				
			</tr>
		</c:forEach>
	</tbody>
</table>


<%@ include file="includes/pie.jsp"%>
