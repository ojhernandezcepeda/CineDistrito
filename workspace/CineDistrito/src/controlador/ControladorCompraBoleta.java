package controlador;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.annotation.Resource;
import javax.security.auth.message.callback.PrivateKeyCallback.Request;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;

import modelo.administracion.AdministracionBD;
import modelo.auditoria.Empleado;
import modelo.cinedistrito.Cliente;
import modelo.cinedistrito.Establecimiento;
import modelo.pelicula.FacturaBoleta;
import modelo.pelicula.Pelicula;
import modelo.pelicula.Reserva;
import modelo.pelicula.Silla;

/**
 * Servlet implementation class ControladorHome
 */
@WebServlet("/ControladorCompraBoleta")
public class ControladorCompraBoleta extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private AdministracionBD administracionBD;
	private int idEstablecimiento = 0; 
	private String nombrePelicula = null;
	Pelicula objPelicula = null;
	private int idPelicula = 0;
	private ArrayList<Silla> sillas = null;
	
	/*FacturaBoleta objFacturaBoleta; Reserva
	 * objReserva; private int idUsuario;
	 * private int rol = 0; private String nombreUsuario; private boolean resultado
	 * = false; private Cliente objCliente = null; private Empleado objEmpleado =
	 * null; private int bonosUsuario = 0; private ArrayList<Integer> listaBonos =
	 * null;
	 */
	// Definir el data source
	@Resource(name = "jdbc/CineDistrito")
	private DataSource miPool;
	private HttpSession sesion;

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
		if(sesion == null) {
			sesion = request.getSession(true);
			String usuario = request.getParameter("usuario");
			String password = request.getParameter("pass");
			String rol = request.getParameter("rol");
			System.out.println("-"+usuario+" "+password+" "+rol);
			sesion.setAttribute("usuario", usuario);
			sesion.setAttribute("pass", password);
			sesion.setAttribute("rol", rol);
			ArrayList<Integer> listaBonos = null;
			int idUsuario = 0;
			try {
				idUsuario = administracionBD.validarUsuario(Integer.parseInt(rol), usuario, password);
				sesion.setAttribute("idUsuario", idUsuario);
				if (rol.equals("1") && idUsuario != 0) {
					Empleado objEmpleado = administracionBD.consultarEmpleado(idUsuario);
					sesion.setAttribute("objEmpleado", objEmpleado);
					sesion.setAttribute("nombreActual", objEmpleado.getNombre());
					System.out.println("--"+objEmpleado.getNombre());
				}else if (rol.equals("2") && idUsuario != 0) {
					Cliente objCliente = administracionBD.consultarCliente(usuario);
					objCliente.setTotalBonus(administracionBD.obtenerTotalBonusUsuario(objCliente.getIdentificador()));
					listaBonos = new ArrayList<Integer>();
					for (int i = 0; i < objCliente.getTotalBonus(); i++) {
						listaBonos.add(i + 1);
					}
					sesion.setAttribute("objCliente", objCliente);
					sesion.setAttribute("nombreActual", objCliente.getNombre());
					sesion.setAttribute("listaBonos", listaBonos);
					System.out.println("--"+objCliente.getNombre()+" "+listaBonos);
				}				
			} catch (Exception e) {			
				e.printStackTrace();				
			}		
		}		
		
		
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
		case "accionSillas":
			try {
				accionBoletas(request, response);
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
			
		case "CerrarSesion":
			try {
				sesion = null;
				RequestDispatcher myDispatcher = request.getRequestDispatcher("/ControladorHome");
				myDispatcher.forward(request, response);
			} catch (Exception e1) {				
				e1.printStackTrace();
			}
			break;
			
		case "consultarReserva":
			try {

				consultarReserva(request, response);
				
			} catch (Exception e1) {				
				e1.printStackTrace();
			}
			break;
			
		case "transaccionReserva":
			try {

				transaccionReserva(request, response);
				
			} catch (Exception e1) {				
				e1.printStackTrace();
			}
			break;
		
		default:
			try {
				consultarEstablecimientos(request, response);
			} catch (Exception e) {				
				e.printStackTrace();
			}
			break;
		}
	}

	private void transaccionReserva(HttpServletRequest request, HttpServletResponse response) throws Exception {
		Reserva objReserva = (Reserva) sesion.getAttribute("objReserva");
		int idReserva = objReserva.getIdentificador();
		System.out.println("id reserva "+idReserva);
		//String idClienteReserva = request.getParameter("clienteReservaId");
		Pelicula objPelicula = null;
		String transaccion = request.getParameter("transaccionReservaBoton");
		
		objPelicula = administracionBD.consutarPeliculaReserva(idReserva);
		//Reserva objReserva = administracionBD.consultarReserva(Integer.parseInt(idReserva));
		
		FacturaBoleta objFacturaBoleta = new FacturaBoleta(miPool);
		
		if (transaccion.equals("Confirmar")){
			objFacturaBoleta.setClienteId(objReserva.getClienteId());
			objFacturaBoleta.setEmpleadoId((int) sesion.getAttribute("idUsuario"));
			long idFactura = objFacturaBoleta.confirmarReserva(objReserva, objPelicula.getNombre(), objPelicula.getGenero());
			if (idFactura != 0) {
				//consultaFactura(request, response, idFactura);
				FacturaBoleta objFactura = administracionBD.consultarFactura(idFactura);
				request.setAttribute("objFactura", objFactura);
				System.out.println("objeto fact: "+objFactura.getClienteId());
			}
			
			System.out.println("tra "+ transaccion);
			System.out.println("factura "+objFacturaBoleta.getClienteId() + " "+objFacturaBoleta.getEmpleadoId());
		} else {

		}
		
		
		ArrayList<Establecimiento> establecimientos = administracionBD.consultarEstablecimientos();
		// agregar lista de establecimientos al request
		request.setAttribute("listaEstablecimientos", establecimientos);			
		boolean resultado = false;
		try {
			resultado = (Boolean) sesion.getAttribute("resultado");
		}catch (Exception e){
			System.out.println("no se obtuvo el atributo resultado");
		}
		request.setAttribute("nombreUsuario", (String) sesion.getAttribute("nombreActual"));				
		RequestDispatcher myDispatcher = request.getRequestDispatcher("/moduloComprarBoletas.jsp");
		myDispatcher.forward(request, response);
	}
	
	private void consultaFactura(HttpServletRequest request, HttpServletResponse response, long idFactura) throws SQLException, ServletException, IOException {
		FacturaBoleta objFactura = administracionBD.consultarFactura(idFactura);
		request.setAttribute("objFactura", objFactura);
		
		ArrayList<Establecimiento> establecimientos = administracionBD.consultarEstablecimientos();
		// agregar lista de establecimientos al request
		request.setAttribute("listaEstablecimientos", establecimientos);			
		boolean resultado = false;
		try {
			resultado = (Boolean) sesion.getAttribute("resultado");
		}catch (Exception e){
			System.out.println("no se obtuvo el atributo resultado");
		}
		request.setAttribute("nombreUsuario", (String) sesion.getAttribute("nombreActual"));				
		RequestDispatcher myDispatcher = request.getRequestDispatcher("/moduloComprarBoletas.jsp");
		myDispatcher.forward(request, response);
	}

	private void consultarReserva(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String idReserva = request.getParameter("reservaId");
		Reserva objReserva = administracionBD.consultarReserva(Integer.parseInt(idReserva));
		
		if (objReserva != null) {			
			request.setAttribute("datosReserva", objReserva);
			sesion.setAttribute("objReserva", objReserva);
		}
		
		ArrayList<Establecimiento> establecimientos = administracionBD.consultarEstablecimientos();
		// agregar lista de establecimientos al request
		request.setAttribute("listaEstablecimientos", establecimientos);			
		boolean resultado = false;
		try {
			resultado = (Boolean) sesion.getAttribute("resultado");
		}catch (Exception e){
			System.out.println("no se obtuvo el atributo resultado");
		}
		request.setAttribute("nombreUsuario", (String) sesion.getAttribute("nombreActual"));				
		RequestDispatcher myDispatcher = request.getRequestDispatcher("/moduloComprarBoletas.jsp");
		myDispatcher.forward(request, response);
	}

	private void nuevaConsulta(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ArrayList<Establecimiento> establecimientos = administracionBD.consultarEstablecimientos();
		// agregar lista de establecimientos al request
		request.setAttribute("listaEstablecimientos", establecimientos);			
		boolean resultado = false;
		try {
			resultado = (Boolean) sesion.getAttribute("resultado");
		}catch (Exception e){
			System.out.println("no se obtuvo el atributo resultado");
		}
		request.setAttribute("nombreUsuario", (String) sesion.getAttribute("nombreActual"));				
		RequestDispatcher myDispatcher = request.getRequestDispatcher("/moduloComprarBoletas.jsp");
		myDispatcher.forward(request, response);
	}

	private void consultarEstablecimientos(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// obtener lista de establecimientos desde el modelo
		ArrayList<Establecimiento> establecimientos;
		try {				
			boolean resultado = false;
			try {
				resultado = (Boolean) sesion.getAttribute("resultado");
			}catch (Exception e){
				System.out.println("no se obtuvo el atributo resultado");
			}			
			establecimientos = administracionBD.consultarEstablecimientos();
			// agregar lista de establecimientos al request
			request.setAttribute("listaEstablecimientos", establecimientos);
			request.setAttribute("nombreUsuario", (String) sesion.getAttribute("nombreActual"));
			// agregar parametros de usuario			
			request.setAttribute("rol", (String) sesion.getAttribute("rol"));
			// enviar request a pagina jsp
			RequestDispatcher myDispatcher = request.getRequestDispatcher("/moduloComprarBoletas.jsp");
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
		System.out.println("ConsultarPeliculas(),IdEstablecimiento"+idEstablecimiento);
		// enviar el codigo al modelo y obtener lista de peliculas desde el modelo
		Establecimiento objEstablecimiento = administracionBD.consultarEstablecimiento(this.idEstablecimiento);
		ArrayList<Establecimiento> establecimientos = new ArrayList<Establecimiento>();
		establecimientos.add(objEstablecimiento);
		ArrayList<Pelicula> peliculas = administracionBD.consultarPeliculas(this.idEstablecimiento);
		// agregar lista de establecimientos al request
		request.setAttribute("listaEstablecimientos", establecimientos);
		request.setAttribute("listaPeliculas", peliculas);
		// agregar bandera de bloqueo
		request.setAttribute("nombreUsuario", (String) sesion.getAttribute("nombreActual"));
		request.setAttribute("rol", (String) sesion.getAttribute("rol"));
		request.setAttribute("banderaCompra", false);
		request.setAttribute("banderaEstablecimiento", true);
		request.setAttribute("banderaCompra", false);		
		// enviar request a pagina jsp
		RequestDispatcher myDispatcher = request.getRequestDispatcher("/moduloComprarBoletas.jsp");
		myDispatcher.forward(request, response);
	}

	private void consultarFechas(HttpServletRequest request, HttpServletResponse response) throws Exception {		
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
		request.setAttribute("banderaCompra", false);
		request.setAttribute("nombreUsuario", (String) sesion.getAttribute("nombreActual"));
		request.setAttribute("rol", (String) sesion.getAttribute("rol"));		
		// enviar request a pagina jsp
		RequestDispatcher myDispatcher = request.getRequestDispatcher("/moduloComprarBoletas.jsp");
		myDispatcher.forward(request, response);
	}

	private void consultarPelicula(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// Leer identificadores
		idPelicula = Integer.parseInt(request.getParameter("fecha"));
		// enviar el codigo al modelo y obtener pelicula especifica
		objPelicula = administracionBD.consultarPelicula(idPelicula);
		// enviar el codigo al modelo y obtener sillas especificas
		
		this.sillas = administracionBD.consultarSillas(idPelicula);
		// llenar listas
		ArrayList<Pelicula> peliculas = administracionBD.consultarPeliculasDia(this.idEstablecimiento,
				this.nombrePelicula);
		Establecimiento objEstablecimiento = administracionBD.consultarEstablecimiento(this.idEstablecimiento);
		ArrayList<Establecimiento> establecimientos = new ArrayList<Establecimiento>();
		establecimientos.add(objEstablecimiento);
		// agregar nombre usuario
		request.setAttribute("nombreUsuario", (String) sesion.getAttribute("nombreActual"));
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
		request.setAttribute("banderaCompra", false);
		request.setAttribute("rol", (String) sesion.getAttribute("rol"));				
		// enviar request a pagina jsp
		RequestDispatcher myDispatcher = request.getRequestDispatcher("/moduloComprarBoletas.jsp");
		myDispatcher.forward(request, response);
	}

	private void accionBoletas(HttpServletRequest request, HttpServletResponse response) throws Exception {
		int cantidadBonus = 0;
		boolean resultado = false;
		long idCompra = 0;
		// Integer.parseInt(request.getParameter("cantidadBonus"));
		String accion = request.getParameter("button5");
		String[] identificadores = request.getParameterValues("sillaSeleccion");
		Cliente objCliente = null;
		if (identificadores != null) {
			ArrayList<Silla> sillasSeleccionadas = new ArrayList<Silla>();
			for (String id : identificadores) {
				for (Silla silla : this.sillas) {
					if (silla.getIdentificador() == Integer.parseInt(id)) {
						sillasSeleccionadas.add(silla);
					}
				}
			}
			String rol = (String) sesion.getAttribute("rol");
			if (accion.equals("Comprar")) {
				FacturaBoleta objFacturaBoleta = new FacturaBoleta(miPool);
				objFacturaBoleta.setPoolSillas(sillasSeleccionadas);				
				if (rol.equals("1")) {
					// empleado
					objFacturaBoleta.setEmpleadoId((Integer) sesion.getAttribute("idUsuario"));
					String idUser = request.getParameter("clienteId");
					cantidadBonus = Integer.parseInt(request.getParameter("bonos"));
					objCliente = administracionBD.consultarCliente(Integer.parseInt(idUser));
					idCompra = objFacturaBoleta.comprarBoletas(objCliente, cantidadBonus, objPelicula.getNombre(), objPelicula.getGenero(), (Integer) sesion.getAttribute("idUsuario"));
					if (idCompra != 0) {
						//consultaFactura(request, response, idFactura);
						FacturaBoleta objFactura = administracionBD.consultarFactura(idCompra);
						request.setAttribute("objFactura", objFactura);
					}
					
				} else {
					// cliente
					objFacturaBoleta.setEmpleadoId(1);
					objCliente = (Cliente)sesion.getAttribute("objCliente");
					idCompra = objFacturaBoleta.comprarBoletas(objCliente, cantidadBonus, objPelicula.getNombre(), objPelicula.getGenero(), 1);
					if (idCompra != 0) {
						//consultaFactura(request, response, idFactura);
						FacturaBoleta objFactura = administracionBD.consultarFactura(idCompra);
						request.setAttribute("objFactura", objFactura);
					}
				}
			} else if (accion.equals("Reservar")) {
				Reserva objReserva = new Reserva(miPool);
				objReserva.setPoolSillas(sillasSeleccionadas);
				if (rol.equals("2")) {
					objCliente = (Cliente)sesion.getAttribute("objCliente");					
					objReserva.reservarBoletas(objCliente);
				}				
			}
		}
		this.nuevaConsulta(request, response);
	}

}
