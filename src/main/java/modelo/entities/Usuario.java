package modelo.entities;

public class Usuario {

	protected Integer idUsuario;
	protected String correo;// obligatorio, mínimo 10 caracteres, máximo 50
	protected String clave;// obligatorio, mínimo 10 caracteres, máximo 50
	protected String nombreUsuario;// obligatorio, mínimo 10 caracteres, máximo 50
	protected String fechaNacimiento; // obligatorio, independiente si lo declara como una fecha o un String, formato DD/MM/AAAA
	protected String run; // corresponde a un número menor a 99.999.999
	protected String tipo;
	
	public Usuario() {

	}

	public Integer getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(Integer idUsuario) {
		this.idUsuario = idUsuario;
	}

	public String getCorreo() {
		return correo;
	}

	public void setCorreo(String correo) {
		this.correo = correo;
	}

	public String getClave() {
		return clave;
	}

	public void setClave(String clave) {
		this.clave = clave;
	}

	public String getNombreUsuario() {
		return nombreUsuario;
	}

	public void setNombreUsuario(String nombreUsuario) {
		this.nombreUsuario = nombreUsuario;
	}

	public String getFechaNacimiento() {
		return fechaNacimiento;
	}

	public void setFechaNacimiento(String fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}

	public String getRun() {
		return run;
	}

	public void setRun(String run) {
		this.run = run;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Usuario [idUsuario=");
		builder.append(idUsuario);
		builder.append(", correo=");
		builder.append(correo);
		builder.append(", clave=");
		builder.append(clave);
		builder.append(", nombreUsuario=");
		builder.append(nombreUsuario);
		builder.append(", fechaNacimiento=");
		builder.append(fechaNacimiento);
		builder.append(", run=");
		builder.append(run);
		builder.append(", tipo=");
		builder.append(tipo);
		builder.append("]");
		return builder.toString();
	}

}
