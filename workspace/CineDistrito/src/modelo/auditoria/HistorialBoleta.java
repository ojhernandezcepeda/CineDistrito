package modelo.auditoria;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.sql.Date;

import javax.sql.DataSource;

public class HistorialBoleta extends Historial {

	public HistorialBoleta(DataSource origenDatos) {
		this.origenDatos= origenDatos;
	}
	
	@Override
	public ResultSet generarReporte() throws SQLException {
		objResultSet = null;
		try {
			//establecer conexión
			objConexion = origenDatos.getConnection();
			//crear sentencia SQL y statement
			String sentencia = "SELECT * FROM HistorialBoletas"; 
			objPreStatement = objConexion.prepareStatement(sentencia);
			//Ejecutar sentencia
			objResultSet = objPreStatement.executeQuery();			
		}catch(Exception e) {
			e.printStackTrace();
		}	finally {
			objConexion.close();
		}	
		return objResultSet;		
	}	
}