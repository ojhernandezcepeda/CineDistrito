package modelo.administracion;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import javax.sql.DataSource;

import modelo.auditoria.Empleado;
import modelo.cinedistrito.Cliente;
import modelo.cinedistrito.Establecimiento;
import modelo.pelicula.FacturaBoleta;
import modelo.pelicula.Pelicula;
import modelo.pelicula.Reserva;
import modelo.pelicula.Sala;
import modelo.pelicula.Silla;
import modelo.snack.Snack;

public class AdministracionBD {

	private Connection objConexion = null;
	private PreparedStatement objPreStatement = null;
	private DataSource origenDatos = null;

	public AdministracionBD(DataSource origenDatos) {
		this.origenDatos = origenDatos;
	}

	public int validarUsuario(int rol, String usuario, String password) throws SQLException {
		int bandera = 0;
		try {
			// establecer conexión
			objConexion = origenDatos.getConnection();
			if (rol == 1) {
				// crear sentencia SQL y statement
				String sentencia = "SELECT * FROM Empleado WHERE codigo = ? and contraseña = ?";
				objPreStatement = objConexion.prepareStatement(sentencia);
				objPreStatement.setString(1, usuario);
				objPreStatement.setString(2, password);
				// Ejecutar sentencia
				ResultSet rs = objPreStatement.executeQuery();
				// Validar si hay coincidencia
				if (rs.next()) {
					bandera = rs.getInt("id_empleado");
				}
			} else if (rol == 2) {
				// crear sentencia SQL y statement
				String sentencia = "SELECT * FROM Cliente WHERE usuario = ? and contraseña = ?";
				objPreStatement = objConexion.prepareStatement(sentencia);
				objPreStatement.setString(1, usuario);
				objPreStatement.setString(2, password);
				// Ejecutar sentencia
				ResultSet rs = objPreStatement.executeQuery();
				// Validar si hay coincidencia
				if (rs.next()) {
					bandera = rs.getInt("id_cliente");
				}
			}
			return bandera;
		} catch (Exception e) {
			e.printStackTrace();
			return bandera;
		} finally {
			objConexion.close();
		}
	}

	public int obtenerTotalBonusUsuario(int idUsuario) throws SQLException {
		int bandera = 0;
		try {
			// establecer conexión
			objConexion = origenDatos.getConnection();
			// crear sentencia SQL y statement
			String sentencia = "SELECT COUNT (*) FROM boletabonus WHERE cliente_id = ?;";
			objPreStatement = objConexion.prepareStatement(sentencia);
			objPreStatement.setInt(1, idUsuario);
			// Ejecutar sentencia
			ResultSet rs = objPreStatement.executeQuery();
			// Validar si hay coincidencia
			if (rs.next()) {
				bandera = rs.getInt(1);
			}

			return bandera;
		} catch (Exception e) {
			e.printStackTrace();
			return bandera;
		} finally {
			objConexion.close();
		}
	}

	public boolean insertarEstablecimiento(Establecimiento objEstablecimiento) throws SQLException {
		boolean bandera = false;
		if(objEstablecimiento != null)
		try {
			// establecer conexión
			objConexion = origenDatos.getConnection();
			// crear sentencia SQL y statement
			String sentencia = "INSERT INTO Establecimiento (nombre,ubicacion,max_empleados,tipoestablecimiento_id,estado) VALUES (?,?,?,?,?);";
			objPreStatement = objConexion.prepareStatement(sentencia);
			objPreStatement.setString(1, objEstablecimiento.getNombre());
			objPreStatement.setString(2, objEstablecimiento.getUbicacion());
			objPreStatement.setInt(3, objEstablecimiento.getMaxEmpleados());
			objPreStatement.setInt(4, objEstablecimiento.getTipoEstablecimiento());
			objPreStatement.setString(5, objEstablecimiento.getEstado());
			// Ejecutar sentencia
			
			objPreStatement.executeUpdate();
			bandera = true;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			objConexion.close();
		}
		return bandera;
	}

	public Establecimiento consultarEstablecimiento(int idjEstablecimiento) throws SQLException {
		Establecimiento objEstablecimiento = null;
		try {
			// establecer conexión
			objConexion = origenDatos.getConnection();
			// crear sentencia SQL y statement
			String sentencia = "SELECT * FROM Establecimiento WHERE id_establecimiento = ? ;";
			objPreStatement = objConexion.prepareStatement(sentencia);
			objPreStatement.setInt(1, idjEstablecimiento);
			// Ejecutar sentencia
			ResultSet rs = objPreStatement.executeQuery();
			if (rs.next()) {
				objEstablecimiento = new Establecimiento();
				objEstablecimiento.setIdentificador(rs.getInt("id_establecimiento"));
				objEstablecimiento.setNombre(rs.getString("nombre"));
				objEstablecimiento.setUbicacion(rs.getString("ubicacion"));
				objEstablecimiento.setMaxEmpleados(rs.getInt("max_empleados"));
				objEstablecimiento.setTipoEstablecimiento(rs.getInt("tipoestablecimiento_id"));
				objEstablecimiento.setEstado(rs.getString("estado"));
			}
			return objEstablecimiento;

		} catch (Exception e) {
			e.printStackTrace();
			return null;
		} finally {
			objConexion.close();
		}
	}

	public ArrayList<Establecimiento> consultarEstablecimientos() throws SQLException {
		try {
			ResultSet rs;
			ArrayList<Establecimiento> arrayEstablecimientos = new ArrayList<Establecimiento>();
			// establecer conexión
			objConexion = origenDatos.getConnection();
			// crear sentencia SQL y statement
			String sentencia = "SELECT * FROM Establecimiento ORDER BY id_establecimiento;";
			objPreStatement = objConexion.prepareStatement(sentencia);
			// Ejecutar sentencia
			rs = objPreStatement.executeQuery();
			while (rs.next()) {
				Establecimiento objEstablecimiento = new Establecimiento();
				objEstablecimiento.setNombre(rs.getString("nombre"));
				objEstablecimiento.setUbicacion(rs.getString("ubicacion"));
				objEstablecimiento.setMaxEmpleados(rs.getInt("max_empleados"));
				objEstablecimiento.setTipoEstablecimiento(rs.getInt("tipoestablecimiento_id"));
				objEstablecimiento.setEstado(rs.getString("estado"));
				objEstablecimiento.setIdentificador(rs.getInt("id_establecimiento"));
				arrayEstablecimientos.add(objEstablecimiento);
			}
			return arrayEstablecimientos;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		} finally {
			objConexion.close();
		}
	}

	public boolean eliminarEstablecimiento(Establecimiento objEstablecimiento) throws SQLException {
		boolean bandera = false;	
		try {
			int idEstablecimiento = objEstablecimiento.getIdentificador();
			// establecer conexión
			objConexion = origenDatos.getConnection();
			// crear sentencia SQL y statement
			String sentencia = "DELETE FROM Establecimiento WHERE id_establecimiento = ?;";
			objPreStatement = objConexion.prepareStatement(sentencia);
			objPreStatement.setInt(1, idEstablecimiento);

			// Ejecutar sentencia
			objPreStatement.executeUpdate();
			bandera = true;
		} catch (Exception e) {
			bandera = false;
			System.out.println("idEstablecimiento es nulo, no se elimina el Establecimiento");
		} finally {
			objConexion.close();
		}
		return bandera;
	}

	public boolean actualizarEstablecimiento(Establecimiento objEstablecimiento) throws SQLException {
		boolean bandera = false;
		try {
			// establecer conexión
			objConexion = origenDatos.getConnection();
			// crear sentencia SQL y statement
			String sentencia = "UPDATE Establecimiento set nombre = ? , ubicacion = ? , max_empleados = ? , tipoestablecimiento_id = ? , estado = ?  "
					+ "WHERE id_establecimiento = ?;";
			objPreStatement = objConexion.prepareStatement(sentencia);
			objPreStatement.setString(1, objEstablecimiento.getNombre());
			objPreStatement.setString(2, objEstablecimiento.getUbicacion());
			objPreStatement.setInt(3, objEstablecimiento.getMaxEmpleados());
			objPreStatement.setInt(4, objEstablecimiento.getTipoEstablecimiento());
			objPreStatement.setString(5, objEstablecimiento.getEstado());
			objPreStatement.setInt(6, objEstablecimiento.getIdentificador());

			// Ejecutar sentencia
			objPreStatement.executeUpdate();
			bandera = true;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			objConexion.close();
		}
		return bandera;
	}

	public boolean insertarEmpleado(Empleado objEmpleado) throws SQLException {
		boolean bandera = false;
		try {
			if(objEmpleado != null) {
				// establecer conexión
				objConexion = origenDatos.getConnection();
				// crear sentencia SQL y statement
				String sentencia = "INSERT INTO Empleado (codigo,nombre,cargo,documento,tipodocumento,telefono, contraseña, establecimiento_id, fechainiciocontrato,salario,estado) VALUES (?,?,?,?,?,?,?,?,?,?,?);";
				objPreStatement = objConexion.prepareStatement(sentencia);
				objPreStatement.setString(1, objEmpleado.getCodigo());
				objPreStatement.setString(2, objEmpleado.getNombre());
				objPreStatement.setString(3, objEmpleado.getCargo());
				objPreStatement.setString(4, objEmpleado.getDocumento());
				objPreStatement.setString(5, objEmpleado.getTipoDocumento());
				objPreStatement.setString(6, objEmpleado.getTelefono());
				objPreStatement.setString(7, objEmpleado.getContrasena());
				objPreStatement.setInt(8, objEmpleado.getIdEstablecimiento());
				objPreStatement.setDate(9, (Date) objEmpleado.getInicioContrato());
				objPreStatement.setDouble(10, objEmpleado.getSalario());
				objPreStatement.setString(11, objEmpleado.getEstado());
				// Ejecutar sentencia			
				objPreStatement.executeUpdate();
				bandera = true;
			}
			return bandera;
		} catch (Exception e) {
			e.printStackTrace();
			return bandera;
		} finally {
			objConexion.close();
		}		
	}

	public Empleado consultarEmpleado(String codEmpleado) throws SQLException {
		Empleado objEmpleado = null;
		try {
			// establecer conexión
			objConexion = origenDatos.getConnection();
			// crear sentencia SQL y statement
			String sentencia = "SELECT * FROM Empleado WHERE codigo = ?;";
			objPreStatement = objConexion.prepareStatement(sentencia);
			objPreStatement.setString(1, codEmpleado);
			// Ejecutar sentencia
			ResultSet rs = objPreStatement.executeQuery();
			if (rs.next()) {
				objEmpleado = new Empleado();
				objEmpleado.setIdentificador(rs.getInt("id_empleado"));
				objEmpleado.setCodigo(rs.getString("codigo"));
				objEmpleado.setNombre(rs.getString("nombre"));
				objEmpleado.setCargo(rs.getString("cargo"));
				objEmpleado.setDocumento(rs.getString("documento"));
				objEmpleado.setTipoDocumento(rs.getString("tipodocumento"));
				objEmpleado.setTelefono(rs.getString("telefono"));
				objEmpleado.setContrasena(rs.getString("contraseña"));
				objEmpleado.setIdEstablecimiento(rs.getInt("establecimiento_id"));
				objEmpleado.setInicioContrato(rs.getDate("fechainiciocontrato"));
				objEmpleado.setSalario(rs.getDouble("salario"));
				objEmpleado.setEstado(rs.getString("estado"));
			}
			return objEmpleado;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		} finally {
			objConexion.close();
		}
	}

	public Empleado consultarEmpleado(int idEmpleado) throws SQLException {
		Empleado objEmpleado = null;
		try {
			// establecer conexión
			objConexion = origenDatos.getConnection();
			// crear sentencia SQL y statement
			String sentencia = "SELECT * FROM Empleado WHERE id_empleado = ?;";
			objPreStatement = objConexion.prepareStatement(sentencia);
			objPreStatement.setInt(1, idEmpleado);
			// Ejecutar sentencia
			ResultSet rs = objPreStatement.executeQuery();
			if (rs.next()) {
				objEmpleado = new Empleado();
				objEmpleado.setIdentificador(rs.getInt("id_empleado"));
				objEmpleado.setCodigo(rs.getString("codigo"));
				objEmpleado.setNombre(rs.getString("nombre"));
				objEmpleado.setCargo(rs.getString("cargo"));
				objEmpleado.setDocumento(rs.getString("documento"));
				objEmpleado.setTipoDocumento(rs.getString("tipodocumento"));
				objEmpleado.setTelefono(rs.getString("telefono"));
				objEmpleado.setContrasena(rs.getString("contraseña"));
				objEmpleado.setIdEstablecimiento(rs.getInt("establecimiento_id"));
				objEmpleado.setInicioContrato(rs.getDate("fechainiciocontrato"));
				objEmpleado.setSalario(rs.getDouble("salario"));
				objEmpleado.setEstado(rs.getString("estado"));
			}
			return objEmpleado;
		} catch (Exception e) {
			e.printStackTrace();
			return objEmpleado;
		} finally {
			objConexion.close();
		}
	}

	public Cliente consultarCliente(String usuario) throws SQLException {
		Cliente objCliente = null;
		try {
			// establecer conexión
			objConexion = origenDatos.getConnection();
			// crear sentencia SQL y statement
			String sentencia = "SELECT * FROM Cliente WHERE usuario = ?;";
			objPreStatement = objConexion.prepareStatement(sentencia);
			objPreStatement.setString(1, usuario);
			// Ejecutar sentencia
			ResultSet rs = objPreStatement.executeQuery();
			if (rs.next()) {
				objCliente = new Cliente();
				objCliente.setIdentificador(rs.getInt("id_cliente"));
				objCliente.setNombre(rs.getString("nombre"));
				objCliente.setDocumento(rs.getString("documento"));
				objCliente.setTipoDocumento(rs.getString("tipodocumento"));
				objCliente.setTelefono(rs.getString("telefono"));
				objCliente.setContrasena(rs.getString("contraseña"));
				objCliente.setMaxSillas(rs.getInt("maxsillas"));
				objCliente.setTotalPuntos(rs.getInt("totalpuntos"));
				objCliente.setUsuario(rs.getString("usuario"));
			}
			return objCliente;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		} finally {
			objConexion.close();
		}
	}

	public Cliente consultarCliente(int usuario) throws SQLException {
		Cliente objCliente = null;
		try {
			// establecer conexión
			objConexion = origenDatos.getConnection();
			// crear sentencia SQL y statement
			String sentencia = "SELECT * FROM Cliente WHERE id_cliente = ?;";
			objPreStatement = objConexion.prepareStatement(sentencia);
			objPreStatement.setInt(1, usuario);
			// Ejecutar sentencia
			ResultSet rs = objPreStatement.executeQuery();
			if (rs.next()) {
				objCliente = new Cliente();
				objCliente.setIdentificador(rs.getInt("id_cliente"));
				objCliente.setNombre(rs.getString("nombre"));
				objCliente.setDocumento(rs.getString("documento"));
				objCliente.setTipoDocumento(rs.getString("tipodocumento"));
				objCliente.setTelefono(rs.getString("telefono"));
				objCliente.setContrasena(rs.getString("contraseña"));
				objCliente.setMaxSillas(rs.getInt("maxsillas"));
				objCliente.setTotalPuntos(rs.getInt("totalpuntos"));
				objCliente.setUsuario(rs.getString("usuario"));
			}
			return objCliente;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		} finally {
			objConexion.close();
		}
	}

	public boolean actualizarEmpleado(Empleado objEmpleado) throws SQLException {
		boolean bandera = false;
		try {

			// establecer conexión
			objConexion = origenDatos.getConnection();
			// crear sentencia SQL y statement
			String sentencia = "UPDATE Empleado SET (nombre, cargo, documento, tipodocumento, telefono, contraseña, establecimiento_id, fechainiciocontrato, salario, estado) = (?, ?, ?, ?, ?, ?, ?, ?, ?, ?) WHERE codigo = ? AND id_empleado = ? ;";
			objPreStatement = objConexion.prepareStatement(sentencia);
			objPreStatement.setString(1, objEmpleado.getNombre());
			objPreStatement.setString(2, objEmpleado.getCargo());
			objPreStatement.setString(3, objEmpleado.getDocumento());
			objPreStatement.setString(4, objEmpleado.getTipoDocumento());
			objPreStatement.setString(5, objEmpleado.getTelefono());
			objPreStatement.setString(6, objEmpleado.getContrasena());
			if(objEmpleado.getIdEstablecimiento() != 0) {
				objPreStatement.setInt(7, objEmpleado.getIdEstablecimiento());	
			}else {
				objPreStatement.setNull(7, java.sql.Types.NULL);
			}			
			objPreStatement.setDate(8, objEmpleado.getInicioContrato());
			objPreStatement.setDouble(9, objEmpleado.getSalario());
			objPreStatement.setString(10, objEmpleado.getEstado());
			objPreStatement.setString(11, objEmpleado.getCodigo());
			objPreStatement.setInt(12, objEmpleado.getIdentificador());
			// Ejecutar sentencia
			objPreStatement.executeUpdate();
			bandera = true;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			objConexion.close();
		}
		return bandera;
	}

	public boolean eliminarEmpleado(Empleado objEmpleado) throws SQLException {
		boolean bandera = false;
		try {
			// establecer conexión
			objConexion = origenDatos.getConnection();
			// crear sentencia SQL y statement
			String sentencia = "DELETE FROM Empleado WHERE codigo = ? AND id_empleado ? ;";
			objPreStatement = objConexion.prepareStatement(sentencia);
			objPreStatement.setString(1, objEmpleado.getCodigo());
			objPreStatement.setInt(2, objEmpleado.getIdentificador());
			// Ejecutar sentencia
			objPreStatement.executeUpdate();
			bandera = true;
		} catch (Exception e) {
			bandera = false;
			System.out.println("idEmpleado o codigo es nulo, no se elimina el Empleado");
		} finally {
			objConexion.close();
		}
		return bandera;
	}

	public boolean insertarPelicula(Pelicula objPelicula) throws SQLException {
		boolean bandera = false;
		try {
			ResultSet rs = null;
			long keyPelicula = 0;
			// establecer conexión
			objConexion = origenDatos.getConnection();
			objConexion.setAutoCommit(false);
			// crear sentencia SQL y statement
			String sentencia = "INSERT INTO Pelicula (nombre,duracion,genero,fechaInicio,horainicio, sala_id, estado) VALUES (?,?,?,?,?,?,?);";
			objPreStatement = objConexion.prepareStatement(sentencia, Statement.RETURN_GENERATED_KEYS);
			objPreStatement.setString(1, objPelicula.getNombre());
			objPreStatement.setInt(2, objPelicula.getDuracion());
			objPreStatement.setString(3, objPelicula.getGenero());
			objPreStatement.setDate(4, (Date) objPelicula.getFechaInicio());
			objPreStatement.setInt(5, objPelicula.getHoraInicio());
			objPreStatement.setInt(6, objPelicula.getIdSala());
			objPreStatement.setString(7, objPelicula.getEstado());
			// Ejecutar sentencia
			objPreStatement.executeUpdate();
			rs = objPreStatement.getGeneratedKeys();

			if (rs.next()) {
				keyPelicula = rs.getLong(1);
				long keySilla = 0;
				for (int i = 0; i < 10; i++) {
					objPreStatement = null;
					rs = null;
					sentencia = "INSERT INTO silla (fila, columna, tiposilla_id) VALUES (?,?,?);";
					objPreStatement = objConexion.prepareStatement(sentencia, Statement.RETURN_GENERATED_KEYS);
					objPreStatement.setString(1, "A");
					objPreStatement.setInt(2, i + 1);
					objPreStatement.setInt(3, 1);
					objPreStatement.executeUpdate();
					rs = objPreStatement.getGeneratedKeys();
					if (rs.next()) {
						keySilla = rs.getLong(1);
						sentencia = "INSERT INTO pelicula_silla (pelicula_id, silla_id) VALUES (?,?);";
						objPreStatement = objConexion.prepareStatement(sentencia);
						objPreStatement.setLong(1, keyPelicula);
						objPreStatement.setLong(2, keySilla);
						objPreStatement.executeUpdate();
					}

				}

				for (int i = 0; i < 10; i++) {

					sentencia = "INSERT INTO silla (fila, columna, tiposilla_id) VALUES (?,?,?)";
					objPreStatement = objConexion.prepareStatement(sentencia, Statement.RETURN_GENERATED_KEYS);
					objPreStatement.setString(1, "B");
					objPreStatement.setInt(2, i + 1);
					objPreStatement.setInt(3, 1);
					objPreStatement.executeUpdate();
					rs = objPreStatement.getGeneratedKeys();
					if (rs.next()) {
						keySilla = rs.getLong(1);
						sentencia = "INSERT INTO pelicula_silla (pelicula_id, silla_id) VALUES (?,?)";
						objPreStatement = objConexion.prepareStatement(sentencia);
						objPreStatement.setLong(1, keyPelicula);
						objPreStatement.setLong(2, keySilla);
						objPreStatement.executeUpdate();
					}
				}
				for (int i = 0; i < 10; i++) {

					sentencia = "INSERT INTO silla (fila, columna, tiposilla_id) VALUES (?,?,?)";
					objPreStatement = objConexion.prepareStatement(sentencia, Statement.RETURN_GENERATED_KEYS);
					objPreStatement.setString(1, "C");
					objPreStatement.setInt(2, i + 1);
					objPreStatement.setInt(3, 1);
					objPreStatement.executeUpdate();
					rs = objPreStatement.getGeneratedKeys();
					if (rs.next()) {
						keySilla = rs.getLong(1);
						sentencia = "INSERT INTO pelicula_silla (pelicula_id, silla_id) VALUES (?,?)";
						objPreStatement = objConexion.prepareStatement(sentencia);
						objPreStatement.setLong(1, keyPelicula);
						objPreStatement.setLong(2, keySilla);
						objPreStatement.executeUpdate();
					}
				}
				for (int i = 0; i < 10; i++) {

					sentencia = "INSERT INTO silla (fila, columna, tiposilla_id) VALUES (?,?,?)";
					objPreStatement = objConexion.prepareStatement(sentencia, Statement.RETURN_GENERATED_KEYS);
					objPreStatement.setString(1, "D");
					objPreStatement.setInt(2, i + 1);
					objPreStatement.setInt(3, 1);
					objPreStatement.executeUpdate();
					rs = objPreStatement.getGeneratedKeys();
					if (rs.next()) {
						keySilla = rs.getLong(1);
						sentencia = "INSERT INTO pelicula_silla (pelicula_id, silla_id) VALUES (?,?)";
						objPreStatement = objConexion.prepareStatement(sentencia);
						objPreStatement.setLong(1, keyPelicula);
						objPreStatement.setLong(2, keySilla);
						objPreStatement.executeUpdate();
					}
				}
				for (int i = 0; i < 10; i++) {

					sentencia = "INSERT INTO silla (fila, columna, tiposilla_id) VALUES (?,?,?)";
					objPreStatement = objConexion.prepareStatement(sentencia, Statement.RETURN_GENERATED_KEYS);
					objPreStatement.setString(1, "E");
					objPreStatement.setInt(2, i + 1);
					objPreStatement.setInt(3, 2);
					objPreStatement.executeUpdate();
					rs = objPreStatement.getGeneratedKeys();
					if (rs.next()) {
						keySilla = rs.getLong(1);
						sentencia = "INSERT INTO pelicula_silla (pelicula_id, silla_id) VALUES (?,?)";
						objPreStatement = objConexion.prepareStatement(sentencia);
						objPreStatement.setLong(1, keyPelicula);
						objPreStatement.setLong(2, keySilla);
						objPreStatement.executeUpdate();
					}
				}
				for (int i = 0; i < 10; i++) {

					sentencia = "INSERT INTO silla (fila, columna, tiposilla_id) VALUES (?,?,?)";
					objPreStatement = objConexion.prepareStatement(sentencia, Statement.RETURN_GENERATED_KEYS);
					objPreStatement.setString(1, "F");
					objPreStatement.setInt(2, i + 1);
					objPreStatement.setInt(3, 2);
					objPreStatement.executeUpdate();
					rs = objPreStatement.getGeneratedKeys();
					if (rs.next()) {
						keySilla = rs.getLong(1);
						sentencia = "INSERT INTO pelicula_silla (pelicula_id, silla_id) VALUES (?,?)";
						objPreStatement = objConexion.prepareStatement(sentencia);
						objPreStatement.setLong(1, keyPelicula);
						objPreStatement.setLong(2, keySilla);
						objPreStatement.executeUpdate();
					}
				}
			} else {
				objConexion.rollback();
			}
			objConexion.commit();
			bandera = true;
		} catch (Exception e) {
			objConexion.rollback();			
			System.out.println("excepción insertarPelicula():\n\t" +e.getStackTrace()[0] +"\n\t"+e.getStackTrace()[1]);			 
		} finally {
			objConexion.close();
		}
		return bandera;
	}

	public Pelicula consultarPelicula(int idPelicula) throws SQLException {
		Pelicula objPelicula = null;
		try {
			// establecer conexión
			objConexion = origenDatos.getConnection();
			// crear sentencia SQL y statement
			String sentencia = "SELECT * FROM Pelicula WHERE id_pelicula = ?;";
			objPreStatement = objConexion.prepareStatement(sentencia);
			objPreStatement.setInt(1, idPelicula);
			// Ejecutar sentencia
			ResultSet rs = objPreStatement.executeQuery();
			if (rs.next()) {
				objPelicula = new Pelicula();
				objPelicula.setIdentificador(rs.getInt("id_pelicula"));
				objPelicula.setNombre(rs.getString("nombre"));
				objPelicula.setDuracion(rs.getInt("duracion"));
				objPelicula.setGenero(rs.getString("genero"));
				objPelicula.setFechaInicio(rs.getDate("fechainicio"));
				objPelicula.setIdSala(rs.getInt("sala_id"));
				objPelicula.setHoraInicio(rs.getInt("horainicio"));
				objPelicula.setEstado(rs.getString("estado"));
			}
			return objPelicula;
		} catch (Exception e) {
			e.printStackTrace();
			return objPelicula;
		} finally {
			objConexion.close();
		}
	}

	public ArrayList<Pelicula> consultarPeliculas(int idEstablecimiento) throws SQLException {
		try {
			ResultSet rs;
			ArrayList<Pelicula> arrayPeliculas = new ArrayList<Pelicula>();
			// establecer conexión
			objConexion = origenDatos.getConnection();
			// crear sentencia SQL y statement
			String sentencia = "SELECT DISTINCT p.nombre FROM pelicula p, sala s, establecimiento e WHERE p.sala_id = s.id_sala AND s.establecimiento_id = e.id_establecimiento AND e.id_establecimiento = ?;";
			objPreStatement = objConexion.prepareStatement(sentencia);
			objPreStatement.setInt(1, idEstablecimiento);
			// Ejecutar sentencia
			rs = objPreStatement.executeQuery();
			while (rs.next()) {
				Pelicula objPelicula = new Pelicula();
				// objPelicula.setIdentificador(rs.getInt("id_pelicula"));
				objPelicula.setNombre(rs.getString("nombre"));
				// objPelicula.setDuracion(rs.getInt("duracion"));
				// objPelicula.setGenero(rs.getString("genero"));
				// objPelicula.setFechaInicio(rs.getDate("fechainicio"));
				// objPelicula.setIdSala(rs.getInt("sala_id"));
				// objPelicula.setHoraInicio(rs.getInt("horainicio"));
				// objPelicula.setEstado(rs.getString("estado"));
				arrayPeliculas.add(objPelicula);
			}
			return arrayPeliculas;

		} catch (Exception e) {
			e.printStackTrace();
			return null;
		} finally {
			objConexion.close();
		}
	}

	public ArrayList<Pelicula> consultarPeliculasDia(int idEstablecimiento, String nombrePelicula) throws SQLException {
		try {
			ResultSet rs;
			ArrayList<Pelicula> arrayPeliculas = new ArrayList<Pelicula>();
			// establecer conexión
			objConexion = origenDatos.getConnection();
			// crear sentencia SQL y statement
			String sentencia = "SELECT * FROM pelicula p, sala s, establecimiento e WHERE p.sala_id = s.id_sala AND s.establecimiento_id = e.id_establecimiento AND e.id_establecimiento = ? AND p.nombre = ?;";
			objPreStatement = objConexion.prepareStatement(sentencia);
			objPreStatement.setInt(1, idEstablecimiento);
			objPreStatement.setString(2, nombrePelicula);
			// Ejecutar sentencia
			rs = objPreStatement.executeQuery();
			while (rs.next()) {
				Pelicula objPelicula = new Pelicula();
				objPelicula.setIdentificador(rs.getInt("id_pelicula"));
				objPelicula.setNombre(rs.getString("nombre"));
				objPelicula.setDuracion(rs.getInt("duracion"));
				objPelicula.setGenero(rs.getString("genero"));
				objPelicula.setFechaInicio(rs.getDate("fechainicio"));
				objPelicula.setIdSala(rs.getInt("sala_id"));
				objPelicula.setHoraInicio(rs.getInt("horainicio"));
				objPelicula.setEstado(rs.getString("estado"));
				arrayPeliculas.add(objPelicula);
			}
			return arrayPeliculas;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		} finally {
			objConexion.close();
		}
	}

	public boolean actualizarPelicula(Pelicula objPelicula) throws SQLException {
		boolean bandera = false;
		try {
			// establecer conexión
			objConexion = origenDatos.getConnection();
			// crear sentencia SQL y statement
			String sentencia = "UPDATE Pelicula SET (nombre,duracion,genero,fechaInicio,horainicio,sala_id, estado) VALUES (?,?,?,?,?,?,?) WHERE id_pelicula = ?;";
			objPreStatement = objConexion.prepareStatement(sentencia);
			objPreStatement.setString(1, objPelicula.getNombre());
			objPreStatement.setInt(2, objPelicula.getDuracion());
			objPreStatement.setString(3, objPelicula.getGenero());
			objPreStatement.setDate(4, (Date) objPelicula.getFechaInicio());
			objPreStatement.setInt(5, objPelicula.getHoraInicio());
			objPreStatement.setInt(6, objPelicula.getIdSala());
			objPreStatement.setString(7, objPelicula.getEstado());
			objPreStatement.setInt(8, objPelicula.getIdentificador());
			// Ejecutar sentencia
			objPreStatement.executeUpdate();
			bandera = true;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			objConexion.close();
		}
		return bandera;
	}

	public boolean eliminarPelicula(Pelicula objPelicula) throws SQLException {
		boolean bandera = false;
		try {
			// establecer conexión
			objConexion = origenDatos.getConnection();
			// crear sentencia SQL y statement
			String sentencia = "DELETE FROM Pelicula WHERE id_pelicula = ?;";
			objPreStatement = objConexion.prepareStatement(sentencia);
			objPreStatement.setInt(1, objPelicula.getIdentificador());
			// Ejecutar sentencia
			objPreStatement.executeUpdate();
			bandera = true;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			objConexion.close();
		}
		return bandera;
	}

	// CRUD RESERVA

	public Pelicula consutarPeliculaReserva(int idReserva) throws SQLException {

		Pelicula objPelicula = null;
		try {
			objConexion = origenDatos.getConnection();
			String sentencia = "SELECT p.id_pelicula, p.nombre, p.duracion, p.genero FROM pelicula p, reservaspelicula r, pelicula_silla ps WHERE r.id_reserva = ps.reserva_id AND ps.pelicula_id = p.id_pelicula AND r.id_reserva = ? LIMIT 1;";

			objPreStatement = objConexion.prepareStatement(sentencia);
			objPreStatement.setInt(1, idReserva);
			// Ejecutar sentencia
			ResultSet rs = objPreStatement.executeQuery();
			if (rs.next()) {
				objPelicula = new Pelicula();
				objPelicula.setIdentificador(rs.getInt("id_pelicula"));
				objPelicula.setNombre(rs.getString("nombre"));
				objPelicula.setDuracion(rs.getInt("duracion"));
				objPelicula.setGenero(rs.getString("genero"));
				//objPelicula.setFechaInicio(rs.getDate("fechainicio"));
				//objPelicula.setIdSala(rs.getInt("sala_id"));
				//objPelicula.setHoraInicio(rs.getInt("horainicio"));
				//objPelicula.setEstado(rs.getString("estado"));
			}
		} catch (Exception e) {
			e.printStackTrace();
			return objPelicula;
		} finally {
			objConexion.close();
		}

		return objPelicula;

	}

	public Reserva consultarReserva(int idReserva) throws SQLException {
		Reserva objReserva = null;
		try {
			// establecer conexión
			objConexion = origenDatos.getConnection();
			String sentencia = "SELECT * FROM reservaspelicula where id_reserva=? AND estado='ACTIVA';";
			objPreStatement = objConexion.prepareStatement(sentencia);
			objPreStatement.setInt(1, idReserva);
			ResultSet rs = objPreStatement.executeQuery();
			if (rs.next()) {
				objReserva = new Reserva();
				objReserva.setIdentificador(rs.getInt("id_reserva"));
				objReserva.setClienteId(rs.getInt("cliente_id"));
				objReserva.setFechaReserva(rs.getDate("fecha"));
				objReserva.setEstado(rs.getString("estado"));
				objReserva.setTotalCompra(rs.getDouble("valortotal"));
				objReserva.setPuntosReserva(rs.getInt("puntostotal"));
			}
			return objReserva;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return objReserva;
		} finally {
			objConexion.close();
		}

	}

	// CRUD SALA
	public boolean agregarSala(Sala objSala) throws SQLException {
		boolean bandera = false;
		// long lastID=0;
		try {
			// establecer conexión
			objConexion = origenDatos.getConnection();

			// crear sentencia SQL y statement
			String sentencia = "INSERT INTO Sala (cantidadgeneral,cantidadpreferencial,numerosala,establecimiento_id, estado) VALUES (?,?,?,?,?);";

			objPreStatement = objConexion.prepareStatement(sentencia, Statement.RETURN_GENERATED_KEYS);
			objPreStatement.setInt(1, objSala.getCantidadGeneral());
			objPreStatement.setInt(2, objSala.getCantidadPreferencial());
			objPreStatement.setInt(3, objSala.getNumeroSala());
			objPreStatement.setInt(4, objSala.getIdEstablecimiento());
			objPreStatement.setString(5, objSala.getEstado());
			// Ejecutar sentencia
			objPreStatement.executeUpdate();

			/*
			 * ResultSet rs = objPreStatement.getGeneratedKeys(); while(rs.next()) {
			 * lastID=rs.getLong(1); }
			 */

			bandera = true;

			// return lastID;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			objConexion.close();
		}
		return bandera;
		// return lastID;
	}

	public boolean actualizarSala(Sala objSala) throws SQLException {
		boolean bandera = false;
		try {
			// establecer conexión
			objConexion = origenDatos.getConnection();
			// crear sentencia SQL y statement
			String sentencia = "UPDATE Sala SET (cantidadgeneral,cantidadpreferencial,numerosala,establecimiento_id, estado) VALUES (?,?,?,?,?)WHERE id_sala = ?;";
			objPreStatement = objConexion.prepareStatement(sentencia);
			objPreStatement.setInt(1, objSala.getCantidadGeneral());
			objPreStatement.setInt(2, objSala.getCantidadPreferencial());
			objPreStatement.setInt(3, objSala.getNumeroSala());
			objPreStatement.setInt(4, objSala.getIdEstablecimiento());
			objPreStatement.setString(5, objSala.getEstado());
			objPreStatement.setInt(6, objSala.getIdentificador());
			// Ejecutar sentencia
			objPreStatement.executeUpdate();
			bandera = true;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			objConexion.close();
		}
		return bandera;
	}

	public boolean eliminarSala(Sala objSala) throws SQLException {
		boolean bandera = false;
		try {
			// establecer conexión
			objConexion = origenDatos.getConnection();
			// crear sentencia SQL y statement
			String sentencia = "DELETE FROM Sala WHERE id_sala = ?;";
			objPreStatement = objConexion.prepareStatement(sentencia);
			objPreStatement.setInt(1, objSala.getIdentificador());
			// Ejecutar sentencia
			objPreStatement.executeUpdate();
			bandera = true;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			objConexion.close();
		}
		return bandera;
	}

	public Sala consultarSala(int idSala) throws SQLException {
		Sala objSala = null;

		try {
			// establecer conexión
			objConexion = origenDatos.getConnection();
			// crear sentencia SQL y statement
			String sentencia = "SELECT * FROM Sala WHERE id_sala = ?;";
			objPreStatement = objConexion.prepareStatement(sentencia);
			objPreStatement.setInt(1, idSala);
			ResultSet rs;
			// Ejecutar sentencia
			rs = objPreStatement.executeQuery();
			while (rs.next()) {
				objSala = new Sala();
				objSala.setIdentificador(rs.getInt("id_sala"));
				objSala.setCantidadGeneral(rs.getInt("cantidadgeneral"));
				objSala.setCantidadPreferencial(rs.getInt("cantidadpreferencial"));
				objSala.setNumeroSala(rs.getInt("numerosala"));
				objSala.setIdEstablecimiento(rs.getInt("establecimiento_id"));
				objSala.setEstado(rs.getString("estado"));
			}
			return objSala;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			objConexion.close();
		}
		return objSala;
	}

	// CRUD INVENTARIOGENERAL
	public boolean agregarInventarioGeneral(Snack objSnack) throws SQLException {
		boolean bandera = false;
		try {
			// establecer conexión
			objConexion = origenDatos.getConnection();
			// crear sentencia SQL y statement
			String sentencia = "INSERT INTO Inventariogeneral (nombre,stock,valorunidad,puntos,fechavencimiento) VALUES (?,?,?,?,?);";
			objPreStatement = objConexion.prepareStatement(sentencia);
			objPreStatement.setString(1, objSnack.getNombre());
			objPreStatement.setInt(2, objSnack.getStockGeneral());
			objPreStatement.setDouble(3, objSnack.getValorUnidad());
			objPreStatement.setInt(4, objSnack.getPuntos());
			objPreStatement.setDate(5, (Date) objSnack.getFechaCaducidad());

			// Ejecutar sentencia
			objPreStatement.executeUpdate();

			bandera = true;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			objConexion.close();
		}
		return bandera;
	}

	public boolean actualizarInventarioGeneral(Snack objSnack) throws SQLException {
		boolean bandera = false;
		try {
			// establecer conexión
			objConexion = origenDatos.getConnection();
			// crear sentencia SQL y statement
			String sentencia = "UPDATE Inventariogeneral SET (nombre,stock,valorunidad,puntos,fechavencimiento) VALUES (?,?,?,?,?)WHERE id_snack = ?;";
			objPreStatement = objConexion.prepareStatement(sentencia);
			objPreStatement.setString(1, objSnack.getNombre());
			objPreStatement.setInt(2, objSnack.getStockGeneral());
			objPreStatement.setDouble(3, objSnack.getValorUnidad());
			objPreStatement.setInt(4, objSnack.getPuntos());
			objPreStatement.setDate(5, (Date) objSnack.getFechaCaducidad());
			objPreStatement.setInt(6, objSnack.getIdentificador());

			// Ejecutar sentencia
			objPreStatement.executeUpdate();

			bandera = true;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			objConexion.close();
		}
		return bandera;
	}

	public boolean eliminarInventarioGeneral(Snack objSnack) throws SQLException {
		boolean bandera = false;
		try {
			// establecer conexión
			objConexion = origenDatos.getConnection();
			// crear sentencia SQL y statement
			String sentencia = "DELETE FROM Inventariogeneral WHERE id_snack = ?;";
			objPreStatement = objConexion.prepareStatement(sentencia);
			objPreStatement.setInt(1, objSnack.getIdentificador());

			// Ejecutar sentencia
			objPreStatement.executeUpdate();

			bandera = true;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			objConexion.close();
		}
		return bandera;
	}

	public Snack consultarInventarioGeneral(int idSnack) throws SQLException {
		Snack objSnack = null;
		try {
			// establecer conexión
			objConexion = origenDatos.getConnection();
			String sentencia = "SELECT * FROM Inventariogeneral where id_snack=?;";
			objPreStatement = objConexion.prepareStatement(sentencia);
			objPreStatement.setInt(1, idSnack);
			ResultSet rs = objPreStatement.executeQuery();
			if (rs.next()) {
				objSnack = new Snack();
				objSnack.setIdentificador(rs.getInt("id_snack"));
				objSnack.setNombre(rs.getString("nombre"));
				objSnack.setStockGeneral(rs.getInt("stock"));
				objSnack.setValorUnidad(rs.getDouble("valorunidad"));
				objSnack.setPuntos(rs.getInt("puntos"));
				objSnack.setFechaCaducidad(rs.getDate("fechavencimiento"));
			}
			return objSnack;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return objSnack;
		} finally {
			objConexion.close();
		}

	}
	// crudsnacks

	public boolean agregarSnack(Snack objSnack) throws SQLException {
		boolean bandera = false;
		try {
			// establecer conexión
			objConexion = origenDatos.getConnection();
			// crear sentencia SQL y statement
			String sentencia = "INSERT INTO Snack (establecimiento_id,stockestablecimiento,inventariogeneral_id) VALUES (?,?,?);";
			objPreStatement = objConexion.prepareStatement(sentencia);
			objPreStatement.setInt(1, objSnack.getIdEstablecimiento());
			objPreStatement.setInt(2, objSnack.getStockLocal());
			objPreStatement.setInt(3, objSnack.getIdentificador());

			// Ejecutar sentencia
			objPreStatement.executeUpdate();

			bandera = true;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			objConexion.close();
		}
		return bandera;
	}

	public boolean actualizarSnack(Snack objSnack) throws SQLException {
		boolean bandera = false;
		try {
			// establecer conexión
			objConexion = origenDatos.getConnection();
			// crear sentencia SQL y statement
			String sentencia = "UPDATE Snack SET (establecimiento_id,stockestablecimiento,inventariogeneral_id) VALUES (?,?,?) WHERE id_snacks = ?;";
			objPreStatement = objConexion.prepareStatement(sentencia);
			objPreStatement.setInt(1, objSnack.getIdEstablecimiento());
			objPreStatement.setInt(2, objSnack.getStockLocal());
			objPreStatement.setInt(3, objSnack.getIdentificador());
			objPreStatement.setInt(4, objSnack.getIdSnackLocal());

			// Ejecutar sentencia
			objPreStatement.executeUpdate();

			bandera = true;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			objConexion.close();
		}
		return bandera;
	}

	public boolean eliminarSnack(Snack objSnack) throws SQLException {
		boolean bandera = false;
		try {
			// establecer conexión
			objConexion = origenDatos.getConnection();
			// crear sentencia SQL y statement
			String sentencia = "DELETE FROM Snack WHERE id_snacks = ?;";
			objPreStatement = objConexion.prepareStatement(sentencia);
			objPreStatement.setInt(1, objSnack.getIdSnackLocal());

			// Ejecutar sentencia
			objPreStatement.executeUpdate();

			bandera = true;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			objConexion.close();
		}
		return bandera;
	}

	public Snack consultarSnack(int idSnack) throws SQLException {
		Snack objSnack = null;
		try {
			// establecer conexión
			objConexion = origenDatos.getConnection();
			String sentencia = "SELECT * FROM Inventariogeneral ig,Snack s WHERE ig.id_snack =s.inventariogeneral_id AND s.id_snacks=?;";
			objPreStatement = objConexion.prepareStatement(sentencia);
			objPreStatement.setInt(1, idSnack);
			ResultSet rs = objPreStatement.executeQuery();
			if (rs.next()) {
				objSnack = new Snack();
				objSnack.setIdentificador(rs.getInt("inventariogeneral_id"));
				objSnack.setIdSnackLocal(rs.getInt("id_snacks"));
				objSnack.setNombre(rs.getString("nombre"));
				objSnack.setStockGeneral(rs.getInt("stock"));
				objSnack.setStockLocal(rs.getInt("stockestablecimiento"));
				objSnack.setIdEstablecimiento(rs.getInt("establecimiento_id"));
				objSnack.setValorUnidad(rs.getDouble("valorunidad"));
				objSnack.setPuntos(rs.getInt("puntos"));
				objSnack.setFechaCaducidad(rs.getDate("fechavencimiento"));
			}
			return objSnack;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return objSnack;
		} finally {
			objConexion.close();
		}

	}

	public ArrayList<Silla> consultarSillas(int idPelicula) throws SQLException {
		try {
			ResultSet rs;
			ArrayList<Silla> objSillas = new ArrayList<Silla>();
			// establecer conexión
			objConexion = origenDatos.getConnection();
			// crear sentencia SQL y statement
			String sentencia = "SELECT * FROM silla s, pelicula_silla ps, tiposilla ts WHERE s.id_silla = ps.silla_id and ps.pelicula_id = ? and s.tiposilla_id = ts.id_tiposilla ORDER BY id_silla;";
			objPreStatement = objConexion.prepareStatement(sentencia);
			objPreStatement.setInt(1, idPelicula);
			// Ejecutar sentencia
			rs = objPreStatement.executeQuery();
			while (rs.next()) {
				Silla objSilla = new Silla();
				objSilla.setIdentificador(rs.getInt("id_Silla"));
				objSilla.setFila(rs.getString("fila"));
				objSilla.setColumna(rs.getInt("columna"));
				objSilla.setPrecio(rs.getDouble("precio"));
				objSilla.setPuntos(rs.getInt("puntos"));
				objSilla.setTipoSilla(rs.getInt("id_tiposilla"));

				if (rs.getInt("compra_id") != 0) {
					objSilla.setDisponible(1);
				} else if (rs.getInt("reserva_id") != 0 && rs.getInt("compra_id") == 0) {
					objSilla.setDisponible(2);
				} else if (rs.getInt("reserva_id") == 0 && rs.getInt("compra_id") == 0) {
					objSilla.setDisponible(3);
				}
				objSillas.add(objSilla);
			}
			return objSillas;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		} finally {
			objConexion.close();
		}
	}
	
	public FacturaBoleta consultarFactura(long idFactura) throws SQLException {
		FacturaBoleta objFactura = null;
		ArrayList<Silla> objSillas = new ArrayList<Silla>();
		double totalCompra = 0;
		int puntos = 0;
		ResultSet rs;
		try {
			
			objConexion = origenDatos.getConnection();
			String sentencia = "SELECT cb.fecha, cb.id_compraboletas idFactura, cb.cantidadbonus bonusUsados, cb.cliente_id idCliente, cb.empleado_id idEmpleado, e.id_establecimiento idEstablecimiento, e.ubicacion ubicacionEstablecimiento, s.numerosala, si.id_silla, si.fila, si.columna, ts.precio, ts.puntos, ts.id_tiposilla FROM establecimiento e, sala s, pelicula p, pelicula_silla ps, silla si, tiposilla ts, compraboletas cb WHERE e.id_establecimiento = s.establecimiento_id AND s.id_sala = p.sala_id " + 
					"AND p.id_pelicula = ps.pelicula_id " + 
					"AND ps.silla_id = si.id_silla " + 
					"AND si.tiposilla_id = ts.id_tiposilla " + 
					"AND cb.id_compraboletas = ps.compra_id " + 
					"AND cb.id_compraboletas = ?;";
			objPreStatement = objConexion.prepareStatement(sentencia);
			objPreStatement.setLong(1, idFactura);
			rs = objPreStatement.executeQuery();
			objFactura = new FacturaBoleta(); 
			
			while (rs.next()) {
				
				objFactura.setIdentificador(rs.getInt("idFactura"));
				objFactura.setCantidadBonosUsados(rs.getInt("bonusUsados"));
				objFactura.setClienteId(rs.getInt("idCliente"));
				objFactura.setEmpleadoId(rs.getInt("idEmpleado"));
				objFactura.setEstablecimientoId(rs.getInt("idEstablecimiento"));
				
				objFactura.setFechaCompra(rs.getDate("fecha"));
				objFactura.setUbicacion(rs.getString("ubicacionEstablecimiento"));
				objFactura.setNumeroSala(rs.getInt("numeroSala"));
				Silla objSilla = new Silla();
				objSilla.setIdentificador(rs.getInt("id_silla"));
				objSilla.setFila(rs.getString("fila"));
				objSilla.setColumna(rs.getInt("columna"));
				objSilla.setPrecio(rs.getDouble("precio"));
				totalCompra = totalCompra+objSilla.getPrecio();
				objSilla.setPuntos(rs.getInt("puntos"));
				puntos = puntos + objSilla.getPuntos();
				objSilla.setTipoSilla(rs.getInt("id_tiposilla"));
				objSillas.add(objSilla);
				
			}
			objFactura.setTotalCompra(totalCompra);
			objFactura.setPuntosCompra(puntos);
			objFactura.setPoolSillas(objSillas);
			
		} catch (Exception e) {
			return null;
			// TODO: handle exception
		}finally {
			objConexion.close();
		}
		return objFactura;
		
	}

}