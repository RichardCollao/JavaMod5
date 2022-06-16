package entities;

public class Administrativo extends Usuario {

	private String area; // obligatorio, mínimo 5 caracteres, máximo 20
	private String experienciaPrevia; // máximo 100 caracteres

	public Administrativo() {
		super();
	}

	public Administrativo(String area, String experienciaPrevia) {
		super();
		this.area = area;
		this.experienciaPrevia = experienciaPrevia;
	}

	public Administrativo(Integer idUsuario, String correo, String clave, String nombreUsuario, String fechaNacimiento, String run, String type) {
		super(idUsuario, correo, clave, nombreUsuario, fechaNacimiento, run, type);
	}

	public String getExperienciaPrevia() {
		return experienciaPrevia;
	}

	public void setExperienciaPrevia(String experienciaPrevia) {
		this.experienciaPrevia = experienciaPrevia;
	}

	public String getArea() {
		return area;
	}

	public void setArea(String area) {
		this.area = area;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(super.toString());
		sb.append("Administrativo{");
		sb.append("area=").append(area);
		sb.append(", experienciaPrevia=").append(experienciaPrevia);
		sb.append('}');
		return sb.toString();
	}
}
