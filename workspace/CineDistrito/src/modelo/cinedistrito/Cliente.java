package modelo.cinedistrito;

public class Cliente {
	
	private int identificador;
	private String contrasena;
	private int maxSillas;
	private String nombre;
	private String telefono;
	private String tipoDocumento;
	private String documento;
	private int totalPuntos;
	private String usuario;
	private int totalBonus;
	
	
	public int getTotalBonus() {
		return totalBonus;
	}
	public void setTotalBonus(int totalBonus) {
		this.totalBonus = totalBonus;
	}
	public String getUsuario() {
		return usuario;
	}
	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}
	public int getIdentificador() {
		return identificador;
	}
	public void setIdentificador(int identificador) {
		this.identificador = identificador;
	}
	public String getContrasena() {
		return contrasena;
	}
	public void setContrasena(String contrasena) {
		this.contrasena = contrasena;
	}
	public int getMaxSillas() {
		return maxSillas;
	}
	public void setMaxSillas(int maxSillas) {
		this.maxSillas = maxSillas;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getTelefono() {
		return telefono;
	}
	public void setTelefono(String telefono) {
		this.telefono = telefono;
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
	public int getTotalPuntos() {
		return totalPuntos;
	}
	public void setTotalPuntos(int totalPuntos) {
		this.totalPuntos = totalPuntos;
	}

}
