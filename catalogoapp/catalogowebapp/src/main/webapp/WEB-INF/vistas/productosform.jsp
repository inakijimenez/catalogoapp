<%@ include file="includes/cabecera.jsp"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<h2>Formulario de usuarios</h2>
	
	<jsp:useBean id="producto" scope="request"
		class="com.ipartek.formacion.ijimenez.tipos.HibernateProducto" />

	<form action="productosform" method="post">
		<fieldset>
<!-- 	<label for="id">ID</label> 
			
			<input type="number" id="id" name="id"
			  required="required" value="${producto.id}"	
						  
			  <c:if test="${param.op == 'modificar' or param.op == 'borrar'}">
			  	readonly="readonly"
			  </c:if>   
		 	/>
		 	<span class="errores">${producto.idErrores}</span>
		 	
		 	 -->	
			<label for="nombre">Nombre</label> 
			
			<input id="nombre" name="nombre"
			  required="required" value="${producto.nombre}"/>
			  <span class="errores">${producto.nombreErrores}</span>
		
			<label for="descripcion">Descripcion</label> <input type="text" id="descripcion"
				name="descripcion" value="${producto.descripcion}"/>
				<span class="errores">${producto.descripcionErrores}</span>
		
			<label for="precio">Precio</label> <input type="number" id="precio"
				name="precio" value="${producto.precio}"/>
				<span class="errores">${producto.precioErrores}</span>
		</fieldset>
		<fieldset>
			<input type="submit" value="${fn:toUpperCase(param.op)}" />
			
			
			<input type="hidden" name="op" value="${param.op}" />
		</fieldset>
	</form>
	


<%@ include file="includes/pie.jsp" %>