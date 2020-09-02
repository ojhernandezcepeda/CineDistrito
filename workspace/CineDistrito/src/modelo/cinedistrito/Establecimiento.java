package modelo.cinedistrito;

public class Establecimiento {

	private int identificador;
	private String estado;	
	private int maxEmpleados;
	private String nombre;
	private String ubicacion;
	private int tipoEstablecimiento;
	
	public int getIdentificador() {
		return identificador;
	}
	public void setIdentificador(int identificador) {
		this.identificador = identificador;
	}
	public String getEstado() {
		return estado;
	}
	public void setEstado(String estado) {
		this.estado = estado;
	}
	public int getMaxEmpleados() {
		return maxEmpleados;
	}
	public void setMaxEmpleados(int maxEmpleados) {
		this.maxEmpleados = maxEmpleados;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getUbicacion() {
		return ubicacion;
	}
	public void setUbicacion(String ubicacion) {
		this.ubicacion = ubicacion;
	}
	public int getTipoEstablecimiento() {
		return tipoEstablecimiento;
	}
	public void setTipoEstablecimiento(int tipoEstablecimiento) {
		this.tipoEstablecimiento = tipoEstablecimiento;
	}
	
}
