/**
 * 
 */
package controlador;

import static org.junit.jupiter.api.Assertions.*;

import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.SQLFeatureNotSupportedException;
import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;

import javax.sql.DataSource;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;

import modelo.administracion.AdministracionBD;
import modelo.auditoria.Empleado;
import modelo.cinedistrito.Cliente;
import modelo.cinedistrito.Establecimiento;
import modelo.pelicula.*;
import modelo.snack.FacturaSnack;
import modelo.snack.Snack;

/**
 * @author BUGISOFT
 *
 */
class TestTransacciones {
	private static DataSource origenDatos;
	private static AdministracionBD objAdmin;
	private static Empleado objEmpleado;
	private static Cliente objCliente;
	private static Establecimiento objEstablecimiento;
	private static FacturaBoleta objFacturaBoleta;
	private static Reserva objReserva;
	private static Sala objSala;
	private static Pelicula objPelicula;
	private Silla objSilla;
	private FacturaSnack objFacuraSnack;
	private static Snack objSnack;
	
	
	@SuppressWarnings("deprecation")
	@BeforeAll
	static void initAll(){
		origenDatos = new DataSource() {

			@Override
			public <T> T unwrap(Class<T> arg0) throws SQLException {
				// TODO Auto-generated method stub
				return null;
			}
			
			@Override
			public boolean isWrapperFor(Class<?> arg0) throws SQLException {
				// TODO Auto-generated method stub
				return false;
			}
			
			@Override
			public void setLoginTimeout(int arg0) throws SQLException {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void setLogWriter(PrintWriter arg0) throws SQLException {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public Logger getParentLogger() throws SQLFeatureNotSupportedException {
				// TODO Auto-generated method stub
				return null;
			}
			
			@Override
			public int getLoginTimeout() throws SQLException {
				// TODO Auto-generated method stub
				return 0;
			}
			
			@Override
			public PrintWriter getLogWriter() throws SQLException {
				// TODO Auto-generated method stub
				return null;
			}
			
			@Override
			public Connection getConnection(String username, String password) throws SQLException {
				return null;
			}
			
			@Override
			public Connection getConnection() throws SQLException {
				// Registramos el driver de PostgresSQL
				try { 
				    Class.forName("org.postgresql.Driver");
				} catch (ClassNotFoundException ex) {
				    System.out.println("Error al registrar el driver de PostgreSQL: " + ex);
				}
				
				Connection connection = null;
				// Database connect
				// Conectamos con la base de datos
				connection = DriverManager.getConnection(
				        "jdbc:postgresql://localhost:5432/CineDistrito",
				        "postgres", "admin");
				return connection;								
			}
		};
		//Crear objeto de prueba
		objAdmin = new AdministracionBD(origenDatos);
		
		//Llenar objeto empleado de prueba
		objEmpleado = new Empleado();
		objEmpleado.setIdentificador(2);
		objEmpleado.setCodigo("ADMIN001");	
		objEmpleado.setNombre("Diego Guerra");
		objEmpleado.setCargo("ADMIN");
		objEmpleado.setDocumento("1020101020");
		objEmpleado.setTipoDocumento("CC");
		objEmpleado.setTelefono("3103145000");
		objEmpleado.setContrasena("2");
		objEmpleado.setInicioContrato(new java.sql.Date(2020-1900,8-1,23));
		objEmpleado.setSalario(2000000);
		objEmpleado.setEstado("ACTIVO");
		//llenar objEstablecimiento 
		objEstablecimiento = new Establecimiento();
		objEstablecimiento.setIdentificador(1);
		objEstablecimiento.setNombre("CC MAYOR");
		objEstablecimiento.setUbicacion("UBICACIÓN CC MAYOR");
		objEstablecimiento.setMaxEmpleados(15);
		objEstablecimiento.setTipoEstablecimiento(1);
		objEstablecimiento.setEstado("ACTIVO");		
		//llenar objEstablecimiento 
		objCliente = new Cliente();
		objCliente.setIdentificador(2);
		objCliente.setNombre("Natalia Angarita");
		objCliente.setDocumento("1231231234");
		objCliente.setTipoDocumento("CC");
		objCliente.setTelefono("1234567890");
		objCliente.setContrasena("2");
		objCliente.setMaxSillas(5);
		objCliente.setTotalBonus(0);
		objCliente.setTotalPuntos(30);
		objCliente.setUsuario("USER2");
		//Llenar factura boleta
		objFacturaBoleta = new FacturaBoleta(origenDatos);
		objFacturaBoleta.setIdentificador(1);
		objFacturaBoleta.setFechaCompra(new java.sql.Date(2020-1900,8-1,23));
		objFacturaBoleta.setCantidadBonosUsados(0);
		objFacturaBoleta.setEmpleadoId(1);
		objFacturaBoleta.setClienteId(2);
		//Llenar reserva
		objReserva = new Reserva();
		objReserva.setIdentificador(1);
		objReserva.setClienteId(1);
		objReserva.setFechaReserva(new java.sql.Date(2020-1900,8-1,23));
		objReserva.setEstado("ACTIVA");
		objReserva.setTotalCompra((double)26000);
		objReserva.setPuntosReserva(20);
		//Sala
		objSala = new Sala();
		objSala.setIdentificador(1);
		objSala.setCantidadGeneral(40);
		objSala.setCantidadPreferencial(20);
		objSala.setNumeroSala(1);
		objSala.setIdEstablecimiento(1);
		objSala.setEstado("FUNCIONAL");
		//SNACK LOCAL
		objSnack = new Snack();
		objSnack.setIdSnackLocal(1);
		objSnack.setIdEstablecimiento(1);
		objSnack.setStockLocal(20);
		objSnack.setIdentificador(1);
		objSnack.setNombre("PAPAS MARGARITA 500G");
		objSnack.setStockGeneral(100);
		objSnack.setValorUnidad(2000);
		objSnack.setPuntos(1);
		objSnack.setFechaCaducidad(new java.sql.Date(2021-1900,9-1,23));
		//Pelicula
		objPelicula = new Pelicula();
		objPelicula.setIdentificador(1);
		objPelicula.setNombre("AVENGERS");
		objPelicula.setDuracion(150);
		objPelicula.setGenero("CIENCIA FICCIÓN");
		objPelicula.setFechaInicio(new java.sql.Date(2020-1900,9-1,26));
		objPelicula.setHoraInicio(8);
		objPelicula.setIdSala(1);
		objPelicula.setEstado("PROGRAMADA");
	}
	
	@Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS)
	void testValidarUsuarioEmpleado() {					 							
		try {
			
			assertEquals(objEmpleado.getIdentificador(), objAdmin.validarUsuario(1, objEmpleado.getCodigo(), objEmpleado.getContrasena()));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
	}
	
	@Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS)
	void testValidarUsuarioCliente() {					 							
		try {
			
			assertEquals(objCliente.getIdentificador(), objAdmin.validarUsuario(2, objCliente.getUsuario(), objCliente.getContrasena()));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
	}
	
	@Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS)
	void testObtenerBonusUsuario() {					 							
		try {
			
			assertEquals(objCliente.getTotalBonus(), objAdmin.obtenerTotalBonusUsuario(objCliente.getIdentificador()));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
	}
	
	@Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS)
	void testInsertarEstablecimiento() {		
		try {			
			assertEquals(false, objAdmin.insertarEstablecimiento(null));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
	}
	
	@Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS)
	void testConsultarEstablecimiento() {					 							
		try {
			Establecimiento aux =objAdmin.consultarEstablecimiento(objEstablecimiento.getIdentificador()); 
			assertEquals(objEstablecimiento.getIdentificador(),aux.getIdentificador());
			assertEquals(objEstablecimiento.getEstado(),aux.getEstado());
			assertEquals(objEstablecimiento.getMaxEmpleados(),aux.getMaxEmpleados());
			assertEquals(objEstablecimiento.getNombre(),aux.getNombre());
			assertEquals(objEstablecimiento.getTipoEstablecimiento(),aux.getTipoEstablecimiento());
			assertEquals(objEstablecimiento.getUbicacion(),aux.getUbicacion());			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
	}
	
	@Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS)
	void testConsultarEstablecimientos() {					 							
		try {
			Establecimiento aux =objAdmin.consultarEstablecimientos().get(0); 
			assertEquals(objEstablecimiento.getIdentificador(),aux.getIdentificador());
			assertEquals(objEstablecimiento.getEstado(),aux.getEstado());
			assertEquals(objEstablecimiento.getMaxEmpleados(),aux.getMaxEmpleados());
			assertEquals(objEstablecimiento.getNombre(),aux.getNombre());
			assertEquals(objEstablecimiento.getTipoEstablecimiento(),aux.getTipoEstablecimiento());
			assertEquals(objEstablecimiento.getUbicacion(),aux.getUbicacion());			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
	}
	
	@Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS)
	void testEsliminarEstablecimiento() {					 							
		try { 
			assertEquals(false,objAdmin.eliminarEstablecimiento(null));						
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
	}
	
	@Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS)
	void testActualizarEstablecimiento() {					 							
		try { 
			assertEquals(true,objAdmin.actualizarEstablecimiento(objEstablecimiento));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
	}
	
	@Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS)
	void testInsertarEmpleado(){		
		try { 
			assertEquals(false,objAdmin.insertarEmpleado(null));
						
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
	}
	
	@Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS)
	void testConsultarEmpleadoCodigo(){		
		try { 
			assertEquals(objEmpleado.getIdentificador(),objAdmin.consultarEmpleado(objEmpleado.getCodigo()).getIdentificador());
						
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
	}
	
	@Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS)
	void testConsultarEmpleadoId(){		
		try { 
			assertEquals(objEmpleado.getCodigo(),objAdmin.consultarEmpleado(objEmpleado.getIdentificador()).getCodigo());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	} 


	@Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS)
	void testConsultarClienteId(){		
		try { 
			assertEquals(objCliente.getUsuario(),objAdmin.consultarCliente(objCliente.getIdentificador()).getUsuario());					
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
	}
	
	@Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS)
	void testConsultarClienteCodigo(){		
		try { 
			assertEquals(objCliente.getIdentificador(),objAdmin.consultarCliente(objCliente.getUsuario()).getIdentificador());
						
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
	}
	
	@Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS)
	void testActualizarEmpleado(){		
		try { 
			assertEquals(true,objAdmin.actualizarEmpleado(objEmpleado));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
	}
	
	@Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS)
	void testEliminarEmpleado(){		
		try { 
			assertEquals(false,objAdmin.eliminarEmpleado(null));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
	}
	
	@Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS)
	void testInsertarPelicula(){		
		try { 
			assertEquals(false,objAdmin.insertarPelicula(null));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
	}
	
	@Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS)
	void testConsultarPelicula(){		
		try { 
			assertEquals(objPelicula.getNombre(),objAdmin.consultarPelicula(objPelicula.getIdentificador()).getNombre());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Test
    @Timeout(value = 1, unit = TimeUnit.SECONDS)
	void testConsultarFactura(){		
		try { 
			assertEquals(2, objAdmin.consultarFactura(objFacturaBoleta.getIdentificador()).getClienteId());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}