package org.apache.jsp.WEB_002dINF.vistas;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;

public final class principal_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent {

  private static final JspFactory _jspxFactory = JspFactory.getDefaultFactory();

  private static java.util.List<String> _jspx_dependants;

  static {
    _jspx_dependants = new java.util.ArrayList<String>(2);
    _jspx_dependants.add("/WEB-INF/vistas/includes/cabecera.jsp");
    _jspx_dependants.add("/WEB-INF/vistas/includes/pie.jsp");
  }

  private org.glassfish.jsp.api.ResourceInjector _jspx_resourceInjector;

  public java.util.List<String> getDependants() {
    return _jspx_dependants;
  }

  public void _jspService(HttpServletRequest request, HttpServletResponse response)
        throws java.io.IOException, ServletException {

    PageContext pageContext = null;
    HttpSession session = null;
    ServletContext application = null;
    ServletConfig config = null;
    JspWriter out = null;
    Object page = this;
    JspWriter _jspx_out = null;
    PageContext _jspx_page_context = null;

    try {
      response.setContentType("text/html; charset=UTF-8");
      pageContext = _jspxFactory.getPageContext(this, request, response,
      			null, true, 8192, true);
      _jspx_page_context = pageContext;
      application = pageContext.getServletContext();
      config = pageContext.getServletConfig();
      session = pageContext.getSession();
      out = pageContext.getOut();
      _jspx_out = out;
      _jspx_resourceInjector = (org.glassfish.jsp.api.ResourceInjector) application.getAttribute("com.sun.appserv.jsp.resource.injector");

      out.write("\r\n");
      out.write("<!DOCTYPE html>\r\n");
      out.write("<html>\r\n");
      out.write("<head>\r\n");
      out.write("<meta charset=\"UTF-8\" />\r\n");
      out.write("\r\n");
      out.write("<link rel=\"stylesheet\" href=\"css/estilos.css\" />\r\n");
      out.write("<title>Supermercado</title>\r\n");
      out.write("</head>\r\n");
      out.write("<body>\r\n");
      out.write("\t<header>\r\n");
      out.write("\t\t<h1>Supermercado Superchupi</h1>\r\n");
      out.write("\t</header>\r\n");
      out.write("\t<nav>\r\n");
      out.write("\t\t<ul>\r\n");
      out.write("\t\t\t<li><a href=\"index\">Inicio</a></li>\r\n");
      out.write("\t\t\t<li><a href=\"login\">Login</a></li>\r\n");
      out.write("\t\t\t<li><a href=\"usuariocrud?op=alta\">Alta</a></li>\r\n");
      out.write("\t\t\t<li><a href=\"usuariocrud\">Gestion de Usuarios</a></li>\r\n");
      out.write("\t\t\t<li><a href=\"login?opcion=logout\">Salir</a></li>\r\n");
      out.write("\t\t</ul>\r\n");
      out.write("\t</nav>\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("\t<h2>Principal</h2>\r\n");
      out.write("\t");
      com.ipartek.formacion.ijimenez.tipos.UsuarioMySQL usuario = null;
      synchronized (session) {
        usuario = (com.ipartek.formacion.ijimenez.tipos.UsuarioMySQL) _jspx_page_context.getAttribute("usuario", PageContext.SESSION_SCOPE);
        if (usuario == null){
          usuario = new com.ipartek.formacion.ijimenez.tipos.UsuarioMySQL();
          _jspx_page_context.setAttribute("usuario", usuario, PageContext.SESSION_SCOPE);
        }
      }
      out.write("\r\n");
      out.write("\t<h2>Bienvenido ");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.evaluateExpression("${usuario.username}", java.lang.String.class, (PageContext)_jspx_page_context, null));
      out.write(" <a href=\"login?opcion=logout\">Logout</a></h2>\r\n");
      out.write("\t<h3>");
      out.print( new java.util.Date() );
      out.write("</h3>\r\n");
      out.write("\r\n");
      out.write("<footer>\r\n");
      out.write("\t\t<p>&copy;2017 Superchupi S.A.</p>\r\n");
      out.write("</footer>\r\n");
      out.write("\r\n");
      out.write("</body>\r\n");
      out.write("</html>");
    } catch (Throwable t) {
      if (!(t instanceof SkipPageException)){
        out = _jspx_out;
        if (out != null && out.getBufferSize() != 0)
          out.clearBuffer();
        if (_jspx_page_context != null) _jspx_page_context.handlePageException(t);
        else throw new ServletException(t);
      }
    } finally {
      _jspxFactory.releasePageContext(_jspx_page_context);
    }
  }
}
