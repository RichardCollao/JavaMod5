package entities;

public class Administrativo extends Usuario {

    private String area; // obligatorio, mínimo 5 caracteres, máximo 20
    private String experienciaPrevia; // máximo 100 caracteres

    public Administrativo(String area, String experienciaPrevia) {
        this.area = area;
        this.experienciaPrevia = experienciaPrevia;
    }

    public Administrativo(String area, String experienciaPrevia, Integer idUsuario, String nombre, String fechaNacimiento, String run) {
        super(idUsuario, nombre, fechaNacimiento, run);
        this.area = area;
        this.experienciaPrevia = experienciaPrevia;
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
