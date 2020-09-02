package modelo.pelicula;

import java.util.ArrayList;
import javax.sql.DataSource;
import modelo.cinedistrito.Cliente;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Reserva {

	private int identificador;
	private int clienteId;
	private Date fechaReserva;
	private int puntosReserva; 
	private double totalCompra;	
	private String estado;
	private ArrayList<Silla> poolSillas;
	
	private DataSource origenDatos;
	private Connection objConexion = null;
	private PreparedStatement objPreStatement = null; 
	private ResultSet objResultSet = null;

	public Reserva(DataSource origenDatos) {
		this.origenDatos = origenDatos;
	}
	
	public Reserva() {
		
	}
	
	public int getIdentificador() {
		return identificador;
	}
	public void setIdentificador(int identificador) {
		this.identificador = identificador;
	}
	public int getClienteId() {
		return clienteId;
	}
	public void setClienteId(int clienteId) {
		this.clienteId = clienteId;
	}

	public Date getFechaReserva() {
		return fechaReserva;
	}
	public void setFechaReserva(Date fechaReserva) {
		this.fechaReserva = fechaReserva;
	}
	public Double getTotalCompra() {
		return totalCompra;
	}
	public void setTotalCompra(Double totalCompra) {
		this.totalCompra = totalCompra;
	}
	public ArrayList<Silla> getPoolSillas() {
		return poolSillas;
	}
	public void setPoolSillas(ArrayList<Silla> poolSillas) {
		this.poolSillas = poolSillas;
	}
	
	public int getPuntosReserva() {
		return puntosReserva;
	}
	public void setPuntosReserva(int puntosReserva) {
		this.puntosReserva = puntosReserva;
	}
	
	private boolean verificarCliente(int identificador) throws SQLException {
		boolean bandera = false;		
		//Si consulta es verdadera cambiar a true la bandera
		try {			
			//establecer conexión
			objConexion = origenDatos.getConnection();
			objConexion.setAutoCommit(false);
			//crear sentencia SQL y statement
			String sentencia = "SELECT * FROM Cliente WHERE id_cliente = ?;"; 
			objPreStatement = objConexion.prepareStatement(sentencia);
			objPreStatement.setInt(1, identificador);						
			//Ejecutar sentencia
			objResultSet = objPreStatement.executeQuery();
			while(objResultSet.next()) {				
				if(objResultSet.getInt("id_cliente") == identificador) {					
					bandera = true;
				}
			}
			objConexion.commit();
			return bandera;
		}catch(Exception e) {
			e.printStackTrace();
			objConexion.rollback();
			return bandera;
		}finally {
			objConexion.close();			
		}
	}
	
	public boolean reservarBoletas(Cliente objCliente) throws SQLException {
		boolean bandera = false;
		long idInsertado = 0;
		int idTemporal = 0;
		int contador = 0;
		int reservasMax = 0;
		int reservasActivas = 0;
		int sillasPosibles = 0;
		String sentencia = "";
		totalCompra = 0;
		puntosReserva = 0;
		ResultSet rs = null;
		try {
			//Crear e insertar reserva
			this.clienteId = objCliente.getIdentificador();				
			objConexion = origenDatos.getConnection();
			sentencia = "SELECT * FROM cliente WHERE id_cliente = ?;";
			objPreStatement = objConexion.prepareStatement(sentencia);
			objPreStatement.setInt(1, this.clienteId);
			rs = objPreStatement.executeQuery();			
			if(rs.next()) {
				reservasMax =rs.getInt("maxsillas");
				System.out.println("Max: "+ reservasMax);
			}			
			sentencia = "SELECT COUNT(*) contador FROM pelicula_silla ps, reservaspelicula r WHERE ps.reserva_id = r.id_reserva AND r.cliente_id = ? AND ps.compra_id ISNULL;";
			objPreStatement = objConexion.prepareStatement(sentencia);
			objPreStatement.setInt(1, this.clienteId);
			rs = objPreStatement.executeQuery();
			if(rs.next()) {
				reservasActivas = rs.getInt("contador");
				System.out.println("Activas: "+ reservasActivas);
			}
			
		}catch(Exception e) {
			System.out.println("Error obteniendo las reservas activas");
		}finally {
			objConexion.close();
		}
		sillasPosibles = reservasActivas + this.poolSillas.size();
		//usar Verificar Cliente para confirmar reserva asociada al cliente
		if(this.verificarCliente(objCliente.getIdentificador()) && reservasMax >= sillasPosibles ) {		
			//Recorrer las sillas
			for (Silla silla : poolSillas) {
				totalCompra = totalCompra + silla.getPrecio();
				puntosReserva = puntosReserva + silla.getPuntos();
			}			
			try {
				objConexion = origenDatos.getConnection();
				objConexion.setAutoCommit(false);
				sentencia = "INSERT INTO Reservaspelicula (cliente_id,fecha,estado,valortotal,puntostotal) VALUES (?,current_date,?,?,?);"; 
				objPreStatement = objConexion.prepareStatement(sentencia, Statement.RETURN_GENERATED_KEYS);
				objPreStatement.setInt(1, this.clienteId);
				objPreStatement.setString(2, "ACTIVA");
				objPreStatement.setDouble(3, totalCompra);
				objPreStatement.setInt(4, puntosReserva);
				objPreStatement.executeUpdate();
				objResultSet = objPreStatement.getGeneratedKeys();
				while(objResultSet.next()) {
					idInsertado = objResultSet.getLong(1);
				}				
				//Cambiar estado de cada silla a Reservadp (poner ID de reserva en tablaRompimiento)				
				for (Silla silla : poolSillas) {				
					idTemporal = silla.getIdentificador();				
					sentencia = "UPDATE pelicula_silla SET reserva_id = ? WHERE silla_id = ? AND reserva_id ISNULL;";
					objPreStatement = objConexion.prepareStatement(sentencia);
					objPreStatement.setLong(1, idInsertado);	
					objPreStatement.setInt(2, idTemporal);
					contador = contador + objPreStatement.executeUpdate();	
				}
				//commit
				if(contador == poolSillas.size()) {
					bandera = true;
					objConexion.commit();
				}else {					
					objConexion.rollback();
				}				
			}catch (Exception e) {
				e.printStackTrace();
				//Rollback
				objConexion.rollback();							
			}finally {
				objConexion.close();
			}		
		}
		return bandera;
	}
	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}
	
}
