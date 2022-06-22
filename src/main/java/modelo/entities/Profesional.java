package modelo.entities;

public class Profesional extends Usuario {

	private String titulo; // obligatorio, mínimo 10 caracteres, máximo 50
	private String fechaIngreso; // independiente si lo declara como una fecha o un String, debe ser desplegado con elformato DD/MM/AAAA

	public Profesional() {
		super();
	}

	public String getFechaIngreso() {
		return fechaIngreso;
	}

	public void setFechaIngreso(String fechaIngreso) {
		this.fechaIngreso = fechaIngreso;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(super.toString());
		sb.append("Profesional{");
		sb.append("titulo=").append(titulo);
		sb.append(", fechaIngreso=").append(fechaIngreso);
		sb.append('}');
		return sb.toString();
	}
}
