package modelo.pelicula;

import modelo.cinedistrito.Establecimiento;

public class Sala {

	private int identificador;
	private int cantidadGeneral;
	private int cantidadPreferencial;
	private int numeroSala;
	private String estado;
	private int idEstablecimiento;
	
	
	public int getIdentificador() {
		return identificador;
	}
	public void setIdentificador(int identificador) {
		this.identificador = identificador;
	}
	public int getCantidadGeneral() {
		return cantidadGeneral;
	}
	public void setCantidadGeneral(int cantidadGeneral) {
		this.cantidadGeneral = cantidadGeneral;
	}
	public int getCantidadPreferencial() {
		return cantidadPreferencial;
	}
	public void setCantidadPreferencial(int cantidadPreferencial) {
		this.cantidadPreferencial = cantidadPreferencial;
	}
	public String getEstado() {
		return estado;
	}
	public void setEstado(String estado) {
		this.estado = estado;
	}
	
	public int getNumeroSala() {
		return numeroSala;
	}
	public void setNumeroSala(int numeroSala) {
		this.numeroSala = numeroSala;
	}
	public int getIdEstablecimiento() {
		return idEstablecimiento;
	}
	public void setIdEstablecimiento(int idEstablecimiento) {
		this.idEstablecimiento = idEstablecimiento;
	}
}
