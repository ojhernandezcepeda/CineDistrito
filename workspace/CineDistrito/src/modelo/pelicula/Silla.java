package modelo.pelicula;

public class Silla {

	private int identificador;
	private int columna;
	private String fila;
	private int puntos;
	private double precio;
	private int tipoSilla;
	private int disponible;
		//1 comprada
		//2 reservada
		//3 disponible
	
	public int getIdentificador() {
		return identificador;
	}
	public void setIdentificador(int identificador) {
		this.identificador = identificador;
	}
	public int getColumna() {
		return columna;
	}
	public void setColumna(int columna) {
		this.columna = columna;
	}
	public String getFila() {
		return fila;
	}
	public void setFila(String fila) {
		this.fila = fila;
	}
	public int getPuntos() {
		return puntos;
	}
	public void setPuntos(int puntos) {
		this.puntos = puntos;
	}
	public double getPrecio() {
		return precio;
	}
	public void setPrecio(double precio) {
		this.precio = precio;
	}
	public int getTipoSilla() {
		return tipoSilla;
	}
	public void setTipoSilla(int tipoSilla) {
		this.tipoSilla = tipoSilla;
	}
	public int getDisponible() {
		return disponible;
	}
	public void setDisponible(int disponible) {
		this.disponible = disponible;
	}
	
}
