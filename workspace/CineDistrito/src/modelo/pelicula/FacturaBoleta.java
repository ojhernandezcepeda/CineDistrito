package modelo.pelicula;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;

import javax.sql.DataSource;

import modelo.cinedistrito.Cliente;

public class FacturaBoleta {

	private int identificador;

	private int cantidadBonosUsados;
	private int clienteId;
	private int empleadoId;
	private int establecimientoId;
	private int puntosCompra;
	private Date fechaCompra;
	private double totalCompra;
	private Reserva objReserva;
	private String ubicacion;
	private int numeroSala;


	public String getUbicacion() {
		return ubicacion;
	}

	public void setUbicacion(String ubicacion) {
		this.ubicacion = ubicacion;
	}

	public int getNumeroSala() {
		return numeroSala;
	}

	public void setNumeroSala(int numeroSala) {
		this.numeroSala = numeroSala;
	}

	private ArrayList<Silla> poolSillas;

	private DataSource origenDatos;
	private Connection objConexion;
	private PreparedStatement objPreStatement;
	private ResultSet objResultSet = null;

	public FacturaBoleta() {
		
	}
	
	public FacturaBoleta(DataSource origenDatos) {
		this.origenDatos = origenDatos;
	}

	public int getIdentificador() {
		return identificador;
	}

	public void setIdentificador(int identificador) {
		this.identificador = identificador;
	}

	public int getCantidadBonosUsados() {
		return cantidadBonosUsados;
	}

	public void setCantidadBonosUsados(int cantidadBonosUsados) {
		this.cantidadBonosUsados = cantidadBonosUsados;
	}

	public int getClienteId() {
		return clienteId;
	}

	public void setClienteId(int clinteId) {
		this.clienteId = clinteId;
	}

	public int getEmpleadoId() {
		return empleadoId;
	}

	public void setEmpleadoId(int empleadoId) {
		this.empleadoId = empleadoId;
	}

	public int getEstablecimientoId() {
		return establecimientoId;
	}

	public void setEstablecimientoId(int establecimientoId) {
		this.establecimientoId = establecimientoId;
	}

	public int getPuntosCompra() {
		return puntosCompra;
	}

	public void setPuntosCompra(int puntosCompra) {
		this.puntosCompra = puntosCompra;
	}

	public Date getFechaCompra() {
		return fechaCompra;
	}

	public void setFechaCompra(Date fechaCompra) {
		this.fechaCompra = fechaCompra;
	}

	public double getTotalCompra() {
		return totalCompra;
	}

	public void setTotalCompra(double totalCompra) {
		this.totalCompra = totalCompra;
	}

	public ArrayList<Silla> getPoolSillas() {
		return poolSillas;
	}

	public void setPoolSillas(ArrayList<Silla> poolSillas) {
		this.poolSillas = poolSillas;
	}

	private boolean verificarCliente(int identificador) throws SQLException {
		boolean bandera = false;
		// Si consulta es verdadera cambiar a true la bandera
		try {
			// establecer conexión
			objConexion = origenDatos.getConnection();
			objConexion.setAutoCommit(false);
			// crear sentencia SQL y statement
			String sentencia = "SELECT * FROM Cliente WHERE id_cliente = ?;";
			objPreStatement = objConexion.prepareStatement(sentencia);
			objPreStatement.setInt(1, identificador);
			// Ejecutar sentencia
			objResultSet = objPreStatement.executeQuery();
			while (objResultSet.next()) {
				if (objResultSet.getInt("id_cliente") == identificador) {
					bandera = true;
				}
			}
			objConexion.commit();
			return bandera;
		} catch (Exception e) {
			e.printStackTrace();
			objConexion.rollback();
			return bandera;
		} finally {
			objConexion.close();
		}
	}

	public long comprarBoletas(Cliente objCliente, int bonus, String nombrePelicula, String generoPelicula, Integer empleadoID) throws SQLException {
		boolean bandera = false;
		
		// boolean banderaBonus= false;
		int bonusCliente = 0;
		totalCompra = 0;
		puntosCompra = 0;
		int puntosCliente = 0;
		long idInsertado = 0;
		int idTemporal = 0;
		int sillaGeneral = 0;
		int sillaPreferencial = 0;

		// ResultSet cliente = this.verificarCliente(objCliente.getIdentificador());
		if (objCliente == null) {
			objCliente = new Cliente();
			objCliente.setIdentificador(0);
		}

		boolean existeCliente = this.verificarCliente(objCliente.getIdentificador());

		// Recorrer las sillas
		for (Silla silla : poolSillas) {
			totalCompra = totalCompra + silla.getPrecio();
			puntosCompra = puntosCompra + silla.getPuntos();
			if (silla.getTipoSilla() == 1) {
				sillaGeneral++;
			} else {
				sillaPreferencial++;
			}
		}
		System.out.println("sillas " + totalCompra);
		System.out.println("sillas " + puntosCompra);

		// Crear e insertar reserva
		this.clienteId = objCliente.getIdentificador();

		
		if (bonus != 0 && existeCliente) {

			bonusCliente = cantidadBonusCliente(objCliente.getIdentificador());
			// banderaBonus = cliente.getInt("id_cliente")
		}
		
		
		System.out.println("bonus " + bonusCliente);
		if (bonusCliente >= bonus) {
			try {
				int restaBonus = bonus; // bonusCliente-bonus;
				objConexion = origenDatos.getConnection();
				// Quitar auto commit para la transaccion
				objConexion.setAutoCommit(false);
				// Insertar en compra boletas
				String sentencia = existeCliente ? "INSERT INTO compraboletas (fecha,cantidadbonus,empleado_id,cliente_id) VALUES (current_date,?,?,?);" : "INSERT INTO compraboletas (fecha,cantidadbonus,empleado_id) VALUES (current_date,?,?);";
				objPreStatement = objConexion.prepareStatement(sentencia, Statement.RETURN_GENERATED_KEYS);
				objPreStatement.setInt(1, restaBonus);
				objPreStatement.setInt(2, this.empleadoId);
				if (existeCliente) {					
					objPreStatement.setInt(3, this.clienteId);
				}
				objPreStatement.executeUpdate();
				objResultSet = objPreStatement.getGeneratedKeys();
				while (objResultSet.next()) {
					idInsertado = objResultSet.getLong(1);
				}
				// Cambiar estado de cada silla a (poner ID de compra en tablaRompimiento)
				for (Silla silla : poolSillas) {
					idTemporal = silla.getIdentificador();
					sentencia = "UPDATE pelicula_silla SET compra_id = ? WHERE silla_id = ?;";
					objPreStatement = objConexion.prepareStatement(sentencia);
					objPreStatement.setLong(1, idInsertado);
					objPreStatement.setInt(2, idTemporal);
					objPreStatement.executeUpdate();
				}

				if (existeCliente) {					
					// Traer puntos del cliente
					sentencia = "SELECT * FROM Cliente WHERE id_cliente = ?;";
					objPreStatement = objConexion.prepareStatement(sentencia);
					objPreStatement.setInt(1, this.clienteId);
					objResultSet = objPreStatement.executeQuery();
					while (objResultSet.next()) {
						puntosCliente = objResultSet.getInt("totalpuntos");
					}
					
					// Sumar puntos al cliente
					sentencia = "UPDATE cliente SET totalpuntos = ? WHERE id_cliente = ?;";
					objPreStatement = objConexion.prepareStatement(sentencia);
					objPreStatement.setInt(1, puntosCliente + puntosCompra);
					objPreStatement.setInt(2, this.clienteId);
					objPreStatement.executeUpdate();
					
					// Actualizar fecha bonusboletas del cliente
					sentencia = "SELECT * FROM boletabonus WHERE cliente_id = ? ORDER BY fechalimite ASC LIMIT ?;";
					objPreStatement = objConexion.prepareStatement(sentencia);
					objPreStatement.setInt(1, this.clienteId);
					objPreStatement.setInt(2, restaBonus);
					objResultSet = objPreStatement.executeQuery();
					
					while (objResultSet.next()) {
						int id_boletabonus = objResultSet.getInt("id_boletabonus");
						System.out.println("id boleta bonus " + id_boletabonus);
						sentencia = "UPDATE boletabonus SET fechalimite = current_date WHERE id_boletabonus = ?";
						objPreStatement = objConexion.prepareStatement(sentencia);
						objPreStatement.setInt(1, id_boletabonus);
						objPreStatement.executeUpdate();
					}
				}

				/*
				 * //Actualizar bonus de cliente sentencia =
				 * "UPDATE cliente SET totalpuntos = ? WHERE id_cliente = ?;"; objPreStatement =
				 * objConexion.prepareStatement(sentencia); objPreStatement.setInt(1,
				 * puntosCliente+puntosCompra); objPreStatement.setInt(2, this.clienteId);
				 * objPreStatement.executeUpdate();
				 * 
				 */
				
				  sentencia = 
				  "INSERT INTO historialboletas (compra_id, cantidadboletas, valortotal, cantidadpreferencial, cantidadgeneral, fechaventa, nombrepelicula, generopelicula, empleado_id) VALUES (?,?,?,?,?,current_date,?,?,?);"; 
				  objPreStatement = objConexion.prepareStatement(sentencia);
				  objPreStatement.setInt(1, (int)idInsertado); 
				  objPreStatement.setInt(2, poolSillas.size()); 
				  objPreStatement.setDouble(3, totalCompra);
				  objPreStatement.setInt(4, sillaPreferencial); 
				  objPreStatement.setInt(5, sillaGeneral);
				  objPreStatement.setString(6, nombrePelicula); 
				  objPreStatement.setString(7, generoPelicula); 
				  objPreStatement.setInt(8, empleadoID); 
				  objPreStatement.executeUpdate();
				 

				// Insertar en tabla de auditoria

				// Ejecutar Commit para transaccion
				objConexion.commit();
				bandera = true;
				
			} catch (Exception e) {
				e.printStackTrace();
				// Ejecutar Rollback
				objConexion.rollback();
				bandera = false;
			} finally {
				objConexion.close();
			}

		} else {
			System.out.println("Cantidad de bonus insuficiente");
			bandera = false;
		}

		return idInsertado;
	}

	public int cantidadBonusCliente(int clienteId) {
		int cantidadBonus = 0;
		ResultSet objResultSet;
		try {
			objConexion = origenDatos.getConnection();

			String sentencia = "SELECT * FROM boletabonus WHERE cliente_id = ? AND fechalimite < current_date;";
			objPreStatement = objConexion.prepareStatement(sentencia);
			objPreStatement.setInt(1, clienteId);
			objResultSet = objPreStatement.executeQuery();

			while (objResultSet.next()) {
				cantidadBonus++;
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return cantidadBonus;
	}

	public long confirmarReserva(Reserva objReserva, String nombrePelicula, String generoPelicula) throws SQLException {				
		long idCompra = 0;
		int idTemporal = 0;		
		int contador = 0;
		int puntosCliente = 0;
		int sillaGeneral = 0;
		int sillaPreferencial = 0;
		this.totalCompra = 0;
		this.puntosCompra = 0;
		this.poolSillas = new ArrayList<Silla>();		 
		//Evitar nullpointer en SQL
		if(objReserva == null) {
			objReserva = new Reserva();
			objReserva.setClienteId(0);
			objReserva.setIdentificador(0);
		}		
		try {
			//establecer conexión
			objConexion = origenDatos.getConnection();
			objConexion.setAutoCommit(false);
			//crear sentencia SQL y statement validando coincidencia de identificadores
			String sentencia = "SELECT r.estado estadoReserva, r.id_reserva, cl.id_cliente, cl.totalPuntos puntosCliente, ps.silla_id, s.fila, s.columna, s.tiposilla_id, ts.precio, ts.puntos FROM tiposilla ts, silla s, pelicula_silla ps , reservaspelicula r, cliente cl WHERE ts.id_tiposilla = s.tiposilla_id AND s.id_silla = ps.silla_id AND ps.reserva_id = r.id_reserva AND r.cliente_id = cl.id_cliente AND r.id_reserva = ? AND cl.id_cliente = ?;"; 
			objPreStatement = objConexion.prepareStatement(sentencia);
			objPreStatement.setInt(1, objReserva.getIdentificador());
			objPreStatement.setInt(2, objReserva.getClienteId());
			//Ejecutar sentencia
			objResultSet = objPreStatement.executeQuery();
			//Llenar variables
			while(objResultSet.next()) {			
				objReserva.setEstado(objResultSet.getString("estadoReserva"));
				objReserva.setIdentificador(objResultSet.getInt("id_reserva"));
				objReserva.setClienteId(objResultSet.getInt("id_cliente"));					
				this.clienteId = objResultSet.getInt("id_cliente");
				puntosCliente = objResultSet.getInt("puntosCliente");
				//Consultar todas las sillas asociadas con el idReserva
				Silla objSilla = new Silla();
				objSilla.setIdentificador(objResultSet.getInt("silla_id"));
				objSilla.setFila(objResultSet.getString("fila"));
				objSilla.setColumna(objResultSet.getInt("columna"));
				objSilla.setTipoSilla(objResultSet.getInt("tiposilla_id"));
				objSilla.setPrecio(objResultSet.getDouble("precio"));
				objSilla.setPuntos(objResultSet.getInt("puntos"));
				//Guardar las sillas en el arraylist
				this.poolSillas.add(objSilla);
			}				
			//Crear objReserva y setear sillas
			objReserva.setPoolSillas(this.poolSillas);
			
			//Crear e insertar factura
			sentencia = "INSERT INTO compraBoletas (fecha, cantidadBonus, empleado_id, cliente_id) VALUES (current_date,?,?,?);"; 
			objPreStatement = objConexion.prepareStatement(sentencia, Statement.RETURN_GENERATED_KEYS);
			objPreStatement.setInt(1, this.cantidadBonosUsados);
			objPreStatement.setInt(2, this.empleadoId);
			objPreStatement.setInt(3, this.clienteId);
			objPreStatement.executeUpdate();
			objResultSet = objPreStatement.getGeneratedKeys();
			//Obtener id de la factura
			if(objResultSet.next()) {
				idCompra = objResultSet.getLong(1);
			}
			//Cambiar estado de cada silla a Reservadp (poner ID de compra en tablaRompimiento)				
			for (Silla silla : poolSillas) {
				totalCompra = totalCompra + silla.getPrecio();
				puntosCompra = puntosCompra + silla.getPuntos();
				if (silla.getTipoSilla() == 1) {
					sillaGeneral++;
				} else {
					sillaPreferencial++;
				}
				
				idTemporal = silla.getIdentificador();		
				sentencia = "UPDATE pelicula_silla SET compra_id = ? WHERE silla_id = ? AND compra_id ISNULL;";
				objPreStatement = objConexion.prepareStatement(sentencia);
				objPreStatement.setLong(1, idCompra);	
				objPreStatement.setInt(2, idTemporal);
				contador = contador + objPreStatement.executeUpdate();	
			}
			//actualizar puntos de cliente
			sentencia = "UPDATE cliente SET totalpuntos=? WHERE id_cliente = ?;"; 
			objPreStatement = objConexion.prepareStatement(sentencia);
			objPreStatement.setInt(1, this.puntosCompra + puntosCliente);
			objPreStatement.setInt(2, this.clienteId);
			objPreStatement.executeUpdate();

			//actualizar estado de la reserva
			sentencia = "UPDATE reservaspelicula SET estado=? WHERE id_reserva = ?;"; 
			objPreStatement = objConexion.prepareStatement(sentencia);
			objPreStatement.setString(1, "CONFIRMADO");
			objPreStatement.setInt(2, objReserva.getIdentificador());
			objPreStatement.executeUpdate();
			
			//Insertar Tabla de Auditoria
			 sentencia = 
			 "INSERT INTO historialboletas (compra_id, cantidadboletas, valortotal, cantidadpreferencial, cantidadgeneral, fechaventa, nombrepelicula, generopelicula, empleado_id) VALUES (?,?,?,?,?,current_date,?,?,?);"; 
			 objPreStatement = objConexion.prepareStatement(sentencia);
			 objPreStatement.setLong(1, idCompra); 
			 objPreStatement.setInt(2, poolSillas.size()); 
			 objPreStatement.setDouble(3, totalCompra);
			 objPreStatement.setInt(4, sillaPreferencial); 
			 objPreStatement.setInt(5, sillaGeneral);
			 objPreStatement.setString(6, nombrePelicula); 
			 objPreStatement.setString(7, generoPelicula); 
			 objPreStatement.setInt(8, this.empleadoId); 
			 objPreStatement.executeUpdate();
			 
			
			//commit
			if(contador == poolSillas.size()) {				
				objConexion.commit();
			}else {					
				objConexion.rollback();
			}			
		} catch (Exception e) {
			e.printStackTrace();			
			objConexion.rollback();
		} finally {
			objConexion.close();
		}		
		return idCompra;
	}
}
