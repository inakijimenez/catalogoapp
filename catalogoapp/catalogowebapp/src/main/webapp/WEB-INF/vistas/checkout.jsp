<%@ include file="includes/cabecera.jsp"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<h2>Checkout</h2>
<table>
<caption>
	SU FACTURA
</caption>
<thead> 

<tr>
	<th>Id Usuario ${carrito.idUsuario}</th>
	<th>Id Factura ${factura.id}</th>
</tr>
<tr>

	<th>Producto</th>
	<th>Cantidad</th>
	<th>Precio por Unidad</th>
	
</tr>
</thead>
<tbody>
		<c:forEach items="${requestScope.lineas}" var="lineas">
			<tr>

				
				<td>${lineas.producto.nombre}</td>
				<td>${lineas.cantidad}</td> 
				<td>${lineas.producto.precio}</td>
			
			</tr>
	
		
		</c:forEach>
</tbody>
</table>



<%@ include file="includes/pie.jsp"%>