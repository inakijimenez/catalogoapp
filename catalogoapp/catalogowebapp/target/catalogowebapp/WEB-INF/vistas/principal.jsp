<%@ include file="includes/cabecera.jsp" %>

	<h2>Principal</h2>
	<jsp:useBean id="usuario" scope="session" 
		class="com.ipartek.formacion.ijimenez.tipos.UsuarioMySQL" />
	<h2>Bienvenido ${usuario.username} <a href="login?opcion=logout">Logout</a></h2>
	<h3><%= new java.util.Date() %></h3>

<%@ include file="includes/pie.jsp" %>