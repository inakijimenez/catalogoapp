<%@ include file="includes/cabecera.jsp"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<h2>Formulario de usuarios</h2>

<jsp:useBean id="usuario" scope="request"
	class="com.ipartek.formacion.ijimenez.tipos.HibernateUsuario" />

<form action="usuarioform" method="post">
	<fieldset>
		<label for="username">Nombre</label> <input id="username" name="username"
			required="required" minlength="4" value="${usuario.username}"
			<c:if test="${param.op == 'modificar' or param.op == 'borrar'}">
			  	readonly="readonly"
			  </c:if> />
	</fieldset>
	<fieldset>
		<label for="pass">Contrase�a</label> <input type="password" id="pass"
			name="pass" />
	</fieldset>
	<fieldset>
		<label for="pass2">Contrase�a otra vez</label> <input type="password"
			id="pass2" name="pass2" />
	</fieldset>
	<fieldset>
		<label for="nombre_completo">Nombre Completo</label> <input type="text"
			id="nombre_completo" name="nombre_completo" />
	</fieldset>
	<fieldset>
		<label for="nivel">Nivel de Usuario</label> <input type="radio"
			id="usuario" name="nivel" value="usuario" checked>Usuario <input
			type="radio" id="admin" name="nivel" value="admin">Administrador
	</fieldset>
	<fieldset>
		<input type="submit" value="${fn:toUpperCase(param.op)}" />
		<p class="errores">${usuario.errores}</p>

		<input type="hidden" name="opform" value="${param.op}" />
	</fieldset>
</form>

<c:if test="${param.op == 'borrar'}">
	<script>
		document.forms[0].onsubmit = confirmarBorrado;
	</script>
</c:if>

<%@ include file="includes/pie.jsp"%>