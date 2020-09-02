package modelo.snack;

import java.sql.Date;

public class Snack {

	private int identificador;
	private int idSnackLocal;
	private String nombre;
	private int puntos;
	private int stockGeneral;
	private int stockLocal;
	private double valorUnidad;
	private Date fechaCaducidad;
	private int idEstablecimiento;
	
	
	
	public int getIdentificador() {
		return identificador;
	}
	public void setIdentificador(int identificador) {
		this.identificador = identificador;
	}
	public int getIdSnackLocal() {
		return idSnackLocal;
	}
	public void setIdSnackLocal(int idSnackLocal) {
		this.idSnackLocal = idSnackLocal;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public int getPuntos() {
		return puntos;
	}
	public void setPuntos(int puntos) {
		this.puntos = puntos;
	}
	public int getStockGeneral() {
		return stockGeneral;
	}
	public void setStockGeneral(int stockGeneral) {
		this.stockGeneral = stockGeneral;
	}
	public int getStockLocal() {
		return stockLocal;
	}
	public void setStockLocal(int stockLocal) {
		this.stockLocal = stockLocal;
	}
	public double getValorUnidad() {
		return valorUnidad;
	}
	public void setValorUnidad(double valorUnidad) {
		this.valorUnidad = valorUnidad;
	}
	public Date getFechaCaducidad() {
		return fechaCaducidad;
	}
	public void setFechaCaducidad(Date fechaCaducidad) {
		this.fechaCaducidad = fechaCaducidad;
	}
	public int getIdEstablecimiento() {
		return idEstablecimiento;
	}
	public void setIdEstablecimiento(int idEstablecimiento) {
		this.idEstablecimiento = idEstablecimiento;
	}
			
}
