package modelo.auditoria;

import java.sql.ResultSet;

import javax.sql.DataSource;

public class HistorialCargos extends Historial {
	
	public HistorialCargos(DataSource origenDatos) {
		this.origenDatos= origenDatos;
	}
	
	@Override
	public ResultSet generarReporte() {
		objResultSet = null;
		try {
			//establecer conexión
			objConexion = origenDatos.getConnection();
			//crear sentencia SQL y statement
			String sentencia = "SELECT * FROM HistorialCargos"; 
			objPreStatement = objConexion.prepareStatement(sentencia);
			//Ejecutar sentencia
			objResultSet = objPreStatement.executeQuery();			
		}catch(Exception e) {
			e.printStackTrace();
		}		
		return objResultSet;
	}

}
