<%@ include file="includes/cabecera.jsp"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<h2>Carrito</h2>



	<span class="col">Id Usuario</span>
	<span class="col">Producto</span>
	<span class="col">Cantidad</span>
	<span class="col">Operaciones</span>


		<c:forEach items="${requestScope.lineas}" var="lineas">
			<form method="post" action="carrito">
			<fieldset>

				<label class="col">${carrito.idUsuario}</label> 
				<label class="col">${lineas.producto.nombre}</label>
				<label class="col">${lineas.cantidad}</label> 
				<input type="number" name="cantidad" value="1" /> 
				<input type="submit" name="opeliminar" value="Eliminar Cantidad"/> 
				<input type="submit" name="opeliminartodos" value="Eliminar Todos"/> 
				<input type="hidden" name="nombre" value="${lineas.producto.nombre}" />
			
			
			</fieldset>
		</form>
		
		</c:forEach>


<a href="/checkout">Checkout (Sacar Factura)</a>
<a href="/tienda">Tienda</a>


<%@ include file="includes/pie.jsp"%>