package modelo.snack;

import java.util.ArrayList;
import java.sql.Connection;
import java.sql.Date;

import javax.sql.DataSource;

import modelo.cinedistrito.Cliente;

public class FacturaSnack {
	
	private int identificador;
	private int clienteId;
	private int empleadoId;
	private int establecimientoId;
	private Date fecha;
	private int puntosCompra;
	private double totalCompra;
	private ArrayList<Snack> listaSnack;
	private DataSource origenDatos;
	private Connection objConexion;
	
	public FacturaSnack(DataSource origenDatos) {
		this.origenDatos = origenDatos;
	}

	public int getIdentificador() {
		return identificador;
	}
	public void setIdentificador(int identificador) {
		this.identificador = identificador;
	}
	public int getClienteId() {
		return clienteId;
	}
	public void setClienteId(int clienteId) {
		this.clienteId = clienteId;
	}
	public int getEmpleadoId() {
		return empleadoId;
	}
	public void setEmpleadoId(int empleadoId) {
		this.empleadoId = empleadoId;
	}
	public int getEstablecimientoId() {
		return establecimientoId;
	}
	public void setEstablecimientoId(int establecimientoId) {
		this.establecimientoId = establecimientoId;
	}
	public Date getFecha() {
		return fecha;
	}
	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}
	public int getPuntosCompra() {
		return puntosCompra;
	}
	public void setPuntosCompra(int puntosCompra) {
		this.puntosCompra = puntosCompra;
	}
	public double getTotalCompra() {
		return totalCompra;
	}
	public void setTotalCompra(double totalCompra) {
		this.totalCompra = totalCompra;
	}
	public ArrayList<Snack> getListaSnack() {
		return listaSnack;
	}
	public void setListaSnack(ArrayList<Snack> listaSnack) {
		this.listaSnack = listaSnack;
	}
	
	public boolean verificarCliente(int idCliente) {
		boolean bandera = false;
		
		//Si consulta es verdadera cambiar a true la bandera
		
		return bandera;
	}
	
	public boolean comprarSnacks(Cliente objCliente) {
		boolean bandera = false;
		
		//Si sentencias son en su totalidad verdaderas cambiar a true la bandera, 
		//si no, verificar que no se hagan inserciones
		
		return bandera;
	}
	
}
