<%@ include file="includes/cabecera.jsp"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<h2>Tienda</h2>



	<span class="col">Nombre</span>
	<span class="col">Descripcion</span>
	<span class="col">Precio</span>
	<span class="col">Comprar</span>



	<c:forEach items="${requestScope.productos}" var="producto">
		<form method="post" action="tienda">
			<fieldset>

				<label class="col">${producto.nombre}</label> 
				<label class="col">${producto.descripcion}</label>
				<label class="col">${producto.precio}</label> 
				<label for="cantidad">Cantidad</label>
				<input type="number" id="cantidad" name="cantidad" value="1" /> 
				<input type="submit" value="Añadir al Carrito"> 
				<input type="hidden" name="op" value="sumar" /> 
				<input type="hidden" name="id" value="${producto.id}" />
			</fieldset>
		</form>
	</c:forEach>


<a href="/carrito">Carrito</a>


<%@ include file="includes/pie.jsp"%>