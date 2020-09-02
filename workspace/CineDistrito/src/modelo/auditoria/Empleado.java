package modelo.auditoria;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Date;

import javax.sql.DataSource;

public class Empleado {
	
	private DataSource origenDatos;
	
	private Historial objHistorial;
	
	private int identificador;
	private String codigo;
	private String cargo;
	private String contrasena;
	private String tipoDocumento;
	private String documento;
	private String estado;
	private Date inicioContrato;
	private String nombre;
	private double salario;
	private String telefono;
	private int idEstablecimiento;
	
	
	public Empleado(DataSource origenDatos) {
		this.origenDatos = origenDatos;		
	}
	
	public Empleado() {
		
	}
	
	public ResultSet generarReporteCargos() throws SQLException {
		if(this.cargo == "Administrador") {
			objHistorial = new HistorialCargos(origenDatos);
			return objHistorial.generarReporte();
		}else {
			return null;
		}
	}
	
	public ResultSet generarReporteBoletas() throws SQLException {
		if(this.cargo == "Administrador") {
			objHistorial = new HistorialBoleta(origenDatos);
			return objHistorial.generarReporte();
		}else {
			return null;
		}		
	}
	
	public ResultSet generarReporteSnacks() throws SQLException {
		if(this.cargo == "Administrador") {
			objHistorial = new HistorialSnacks(origenDatos);
			return objHistorial.generarReporte();
		}else {
			return null;
		}		
	}

	public int getIdentificador() {
		return identificador;
	}

	public void setIdentificador(int identificador) {
		this.identificador = identificador;
	}

	public String getCargo() {
		return cargo;
	}

	public void setCargo(String cargo) {
		this.cargo = cargo;
	}

	public String getContrasena() {
		return contrasena;
	}

	public void setContrasena(String contrasena) {
		this.contrasena = contrasena;
	}

	public String getTipoDocumento() {
		return tipoDocumento;
	}

	public void setTipoDocumento(String tipoDocumento) {
		this.tipoDocumento = tipoDocumento;
	}

	public String getDocumento() {
		return documento;
	}

	public void setDocumento(String documento) {
		this.documento = documento;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public Date getInicioContrato() {
		return inicioContrato;
	}

	public void setInicioContrato(Date inicioContrato) {
		this.inicioContrato = inicioContrato;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public double getSalario() {
		return salario;
	}

	public void setSalario(double salario) {
		this.salario = salario;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public int getIdEstablecimiento() {
		return idEstablecimiento;
	}

	public void setIdEstablecimiento(int idEstablecimiento) {
		this.idEstablecimiento = idEstablecimiento;
	}

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo() {		
		String anio = String.valueOf(this.getInicioContrato().getYear());
		String mes = String.valueOf(this.getInicioContrato().getMonth());
		String dia = String.valueOf(this.getInicioContrato().getDay());
		this.codigo = anio+mes+dia+this.getDocumento();
	}
	public void setCodigo(String codigo) {				
		this.codigo = codigo;
	}
}