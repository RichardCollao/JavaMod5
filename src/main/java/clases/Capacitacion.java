package clases;

/**
 *
 * @author richard
 */
public class Capacitacion {
	private Integer id;
	private String titulo;
	private String Duracion;
	private Integer asistentes;

	public Capacitacion(Integer id, String titulo, String Duracion, Integer asistentes) {
		this.id = id;
		this.titulo = titulo;
		this.Duracion = Duracion;
		this.asistentes = asistentes;
	}

	public Integer getAsistentes() {
		return asistentes;
	}

	public void setAsistentes(Integer asistentes) {
		this.asistentes = asistentes;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getDuracion() {
		return Duracion;
	}

	public void setDuracion(String Duracion) {
		this.Duracion = Duracion;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("Capacitacion{");
		sb.append("id=").append(id);
		sb.append(", titulo=").append(titulo);
		sb.append(", Duracion=").append(Duracion);
		sb.append(", asistentes=").append(asistentes);
		sb.append('}');
		return sb.toString();
	}

}
