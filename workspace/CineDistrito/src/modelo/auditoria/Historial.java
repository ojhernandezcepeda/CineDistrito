package modelo.auditoria;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.sql.DataSource;

public abstract class Historial {
	protected Connection objConexion;		
	protected PreparedStatement objPreStatement; 
	protected ResultSet objResultSet;
	protected DataSource origenDatos;
	
	public abstract ResultSet generarReporte() throws SQLException;
}
