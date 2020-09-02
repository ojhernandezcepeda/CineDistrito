package controlador;

import java.io.IOException;
import java.util.ArrayList;
import javax.annotation.Resource;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import modelo.administracion.AdministracionBD;
import modelo.cinedistrito.Cliente;
import modelo.cinedistrito.Establecimiento;
import modelo.pelicula.FacturaBoleta;
import modelo.pelicula.Pelicula;
import modelo.pelicula.Silla;

/**
 * Servlet implementation class ControladorHome
 */
@WebServlet("/ControladorHome")
public class ControladorHome extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private AdministracionBD administracionBD;
	private int idEstablecimiento = 0;
	private String nombrePelicula = null;
	private int idPelicula = 0;
	FacturaBoleta objFacturaBoleta;
	private ArrayList<Silla> sillas = null;
	private Cliente objCliente = null;


	// Definir el data source
	@Resource(name = "jdbc/CineDistrito")
	private DataSource miPool;

	@Override
	public void init() throws ServletException {
		// TODO Auto-generated method stub
		super.init();
		try {
			administracionBD = new AdministracionBD(miPool);
		} catch (Exception e) {
			throw new ServletException(e);
		}
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {		
		String comando = request.getParameter("instruccion");
		if (comando == null)
			comando = "consultaEstablecimientos";		
		switch (comando) {
		case "consultaEstablecimientos":
			try {
				consultarEstablecimientos(request, response);
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			break;
		case "consultaPeliculas":
			try {				
				consultarPeliculas(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			} 
			break;
		case "consultaPeliculaEspecifica":
			try {				
				consultarFechas(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			} 
			break;
		case "consultaDetalle":
			try {				
				consultarPelicula(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
			break;
		case "inicioSesion":
			try {
				iniciarSesion(request, response);
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			break;
		case "NuevaConsulta":
			try {
				nuevaConsulta(request, response);
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			break;
		default:
			try {
				consultarEstablecimientos(request, response);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			break;
		}
	}
	
	private void nuevaConsulta(HttpServletRequest request, HttpServletResponse response) throws Exception{
		ArrayList<Establecimiento> establecimientos = administracionBD.consultarEstablecimientos();
		// agregar lista de establecimientos al request
		request.setAttribute("listaEstablecimientos", establecimientos);
		RequestDispatcher myDispatcher = request.getRequestDispatcher("/home.jsp");
		myDispatcher.forward(request, response);
	}

	private void iniciarSesion(HttpServletRequest request, HttpServletResponse response) throws Exception{		
		//recibir parametros
		String usuario = request.getParameter("usuario");
		String password = request.getParameter("pass");
		int rol = Integer.parseInt(request.getParameter("rol"));		
		//validar usuario y pass
		int idUsuario = administracionBD.validarUsuario(rol, usuario, password);
		if(idUsuario != 0) {
			// enviar request a pagina java
			RequestDispatcher myDispatcher = request.getRequestDispatcher("/ControladorCompraBoleta");
			myDispatcher.forward(request, response);	
		}else {
			consultarEstablecimientos(request, response);			
		}		
	}

	private void consultarEstablecimientos(HttpServletRequest request, HttpServletResponse response) throws Exception{
		// obtener lista de establecimientos desde el modelo
		ArrayList<Establecimiento> establecimientos;
		try {
			establecimientos = administracionBD.consultarEstablecimientos();
			// agregar lista de establecimientos al request
			request.setAttribute("listaEstablecimientos", establecimientos);
			// enviar request a pagina jsp
			RequestDispatcher myDispatcher = request.getRequestDispatcher("/home.jsp");
			myDispatcher.forward(request, response);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} 
	}
	
	private void consultarPeliculas(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// Leer el codigo del establecimiento
		String aux = request.getParameter("establecimiento");
		this.idEstablecimiento = Integer.parseInt(aux);
		// enviar el codigo al modelo y obtener lista de peliculas desde el modelo
		Establecimiento objEstablecimiento = administracionBD.consultarEstablecimiento(this.idEstablecimiento);
		ArrayList<Establecimiento> establecimientos = new ArrayList<Establecimiento>();
		establecimientos.add(objEstablecimiento);		 
		ArrayList<Pelicula> peliculas = administracionBD.consultarPeliculas(this.idEstablecimiento); 
		// agregar lista de establecimientos al request		
		request.setAttribute("listaEstablecimientos", establecimientos);
		request.setAttribute("listaPeliculas", peliculas);
		// agregar bandera de bloqueo
		request.setAttribute("banderaEstablecimiento", true);		
		// enviar request a pagina jsp
		RequestDispatcher myDispatcher = request.getRequestDispatcher("/home.jsp");
		myDispatcher.forward(request, response); 
	} 
	
	private void consultarFechas(HttpServletRequest request, HttpServletResponse response) throws Exception{
		// Leer identificadores		
		nombrePelicula = request.getParameter("pelicula");		
		// enviar el codigo al modelo y obtener lista de peliculas		
		ArrayList<Pelicula> peliculas = administracionBD.consultarPeliculasDia(this.idEstablecimiento, nombrePelicula);
		Establecimiento objEstablecimiento = administracionBD.consultarEstablecimiento(this.idEstablecimiento);
		ArrayList<Establecimiento> establecimientos = new ArrayList<Establecimiento>();
		establecimientos.add(objEstablecimiento);
		// agregar lista de establecimientos al request		
		request.setAttribute("listaEstablecimientos", establecimientos);
		// agregar lista de peliculas al request
		request.setAttribute("listaPeliculas", peliculas);
		// agregar banderas de bloqueo
		request.setAttribute("banderaEstablecimiento", true);
		request.setAttribute("banderaPelicula", true);
		// enviar request a pagina jsp
		RequestDispatcher myDispatcher = request.getRequestDispatcher("/home.jsp");
		myDispatcher.forward(request, response);		
	}
	
	private void consultarPelicula(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// Leer identificadores
		idPelicula = Integer.parseInt(request.getParameter("fecha"));
		// enviar el codigo al modelo y obtener pelicula especifica
		Pelicula objPelicula = administracionBD.consultarPelicula(idPelicula);
		// enviar el codigo al modelo y obtener sillas especificas
		this.sillas = administracionBD.consultarSillas(idPelicula);
		// llenar listas
		ArrayList<Pelicula> peliculas = administracionBD.consultarPeliculasDia(this.idEstablecimiento, this.nombrePelicula);
		Establecimiento objEstablecimiento = administracionBD.consultarEstablecimiento(this.idEstablecimiento);
		ArrayList<Establecimiento> establecimientos = new ArrayList<Establecimiento>();
		establecimientos.add(objEstablecimiento);
		// agregar lista de establecimientos al request		
		request.setAttribute("listaEstablecimientos", establecimientos);
		// agregar lista de peliculas al request
		request.setAttribute("listaPeliculas", peliculas);		
		// agregar pelicula al request
		request.setAttribute("peliculaDeseada", objPelicula); 
		// agregar lista de sillas al request
		request.setAttribute("sillasPelicula", sillas);
		// agregar banderas de bloqueo
		request.setAttribute("banderaEstablecimiento", true);
		request.setAttribute("banderaPelicula", true);
		request.setAttribute("banderaFecha", true);
		// enviar request a pagina jsp
		RequestDispatcher myDispatcher = request.getRequestDispatcher("/home.jsp");
		myDispatcher.forward(request, response);
	}
	
}
