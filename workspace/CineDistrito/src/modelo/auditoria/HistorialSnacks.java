package modelo.auditoria;

import java.sql.ResultSet;

import javax.sql.DataSource;

public class HistorialSnacks extends Historial {

	public HistorialSnacks(DataSource origenDatos) {
		this.origenDatos= origenDatos;
	}
	
	@Override
	public ResultSet generarReporte() {
		objResultSet = null;
		try {
			//establecer conexión
			objConexion = origenDatos.getConnection();
			//crear sentencia SQL y statement
			String sentencia = "SELECT * FROM Snacks"; 
			objPreStatement = objConexion.prepareStatement(sentencia);
			//Ejecutar sentencia
			objResultSet = objPreStatement.executeQuery();			
		}catch(Exception e) {
			e.printStackTrace();
		}		
		return objResultSet;
	}

}
