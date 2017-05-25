<%@ include file="includes/cabecera.jsp" %>

	<h2>Alta de usuarios</h2>
	
	<jsp:useBean id="usuario" scope="request"
		class="com.ipartek.formacion.ijimenez.tipos.Usuario" />

	<form action="alta" method="post">
		<fieldset>
			<label for="nombre">Nombre</label> <input id="nombre" name="nombre"
			  required="required" minlength="4" value="${usuario.nombre}" />
		</fieldset>
		<fieldset>
			<label for="pass">Contraseña</label> <input type="password" id="pass"
				name="pass" />
		</fieldset>
		<fieldset>
			<label for="pass2">Contraseña otra vez</label> <input type="password" id="pass2"
				name="pass2" />
		</fieldset>
				<fieldset>
			<label for="nivel">Nivel de Usuario</label> <input type="radio" id="usuario" 
				name="nivel" value="usuario" checked>Usuario
				 <input type="radio" id="admin" 
				name="nivel" value="admin" >Administrador
		</fieldset>
		<fieldset>
			<input type="submit" value="Alta" />
			<p class="errores">${usuario.errores}</p>
		</fieldset>
	</form>
	
<%@ include file="includes/pie.jsp" %>