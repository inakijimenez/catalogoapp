<%@ include file="includes/cabecera.jsp" %>

	<h2>Login</h2>
	
	<jsp:useBean id="usuario" scope="request"
		class="com.ipartek.formacion.ijimenez.tipos.HibernateUsuario" />

	<form action="loginHibernate" method="post">
		<fieldset>
			<label for="nombre">Nombre</label> <input id="nombre" name="nombre"
			  required="required" minlength="4" value="${usuario.username}" />
		</fieldset>
		<fieldset>
			<label for="pass">Contraseņa</label> <input type="password" id="pass"
				name="pass" />
		</fieldset>
		<fieldset>
			<input type="submit" value="Login" />
			<p class="errores">${usuario.errores}</p>
		</fieldset>
	</form>
<%@ include file="includes/pie.jsp" %>