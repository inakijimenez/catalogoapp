<%@ include file="includes/cabecera.jsp"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<h2>Checkout</h2>
<table>
<caption>
	SU FACTURA
</caption>
<thead> 

<tr>
	<th>Id Usuario</th>
	<th>Producto</th>
	<th>Cantidad</th>
	
</tr>
</thead>
<tbody>
		<c:forEach items="${requestScope.lineas}" var="lineas">
			<tr>

				<td>${carrito.idUsuario}</td> 
				<td>${lineas.producto.nombre}</td>
				<td>${lineas.cantidad}</td> 
			
			</tr>
	
		
		</c:forEach>
</tbody>
</table>



<%@ include file="includes/pie.jsp"%>