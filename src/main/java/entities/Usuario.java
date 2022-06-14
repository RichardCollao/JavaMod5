package entities;

public class Usuario {

    private Integer idUsuario;
    private String nombreUsuario;// obligatorio, mínimo 10 caracteres, máximo 50
    private String fechaNacimiento; // obligatorio, independiente si lo declara como una fecha o un String, formato DD/MM/AAAA
    private String run; // corresponde a un número menor a 99.999.999

    public Usuario() {
        
    }

    public Usuario(Integer idUsuario, String nombre, String fechaNacimiento, String run) {
        this.idUsuario = idUsuario;
        this.nombreUsuario = nombre;
        this.fechaNacimiento = fechaNacimiento;
        this.run = run;
    }

    public Integer getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Integer idUsuario) {
        this.idUsuario = idUsuario;
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

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Usuario{");
        sb.append("idUsuario=").append(idUsuario);
        sb.append(", nombre=").append(nombreUsuario);
        sb.append(", fechaNacimiento=").append(fechaNacimiento);
        sb.append(", run=").append(run);
        sb.append('}');
        return sb.toString();
    }

}
