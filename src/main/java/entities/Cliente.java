package entities;
import java.util.ArrayList;

public class Cliente extends Usuario {

    private String nombres; // obligatorio, mínimo 5 caracteres, máximo 30
    private String apellidos; // obligatorio, mínimo 5 caracteres, máximo 30
    private String telefono; // obligatorio
    private String afp; // mínimo 4 caracteres, máximo 30
    private int sistemaSalud; // 1 (Fonasa) o 2 (Isapre), debe ser solo una de las dos opciones indicadas
    private String direccion; // máximo 70 caracteres
    private String comuna; // máximo 50 caracteres

    private ArrayList<Capacitacion> listaCapacitacion; // Un cliente puede tomar ninguna o muchas capacitaciones
    private ArrayList<Accidente> listaAccidente; // Un cliente puede registrar ninguno o muchos accidentes
    private ArrayList<VisitaTerreno> listaVisitaTerreno; // Un cliente debe tener una o muchas visitas en terreno

    public Cliente() {
    }

    public Cliente(String nombres, String apellidos, String telefono, String afp, int sistemaSalud, String direccion, String comuna, Integer idUsuario, String nombre, String fechaNacimiento, String run) {
        super(idUsuario, nombre, fechaNacimiento, run);
        this.nombres = nombres;
        this.apellidos = apellidos;
        this.telefono = telefono;
        this.afp = afp;
        this.sistemaSalud = sistemaSalud;
        this.direccion = direccion;
        this.comuna = comuna;
    }

    public int getEdad() {
        int edad = 0;
        // ToDoubleFunction: calcular segun fecha de nacimiento
        return edad;
    }

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getAfp() {
        return afp;
    }

    public void setAfp(String afp) {
        this.afp = afp;
    }

    public int getSistemaSalud() {
        return sistemaSalud;
    }

    public void setSistemaSalud(int sistemaSalud) {
        this.sistemaSalud = sistemaSalud;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getComuna() {
        return comuna;
    }

    public void setComuna(String comuna) {
        this.comuna = comuna;
    }

    public void addCapacitacio(Capacitacion capacitacion) {
        listaCapacitacion.add(capacitacion);
    }

    public ArrayList<Capacitacion> getListCapacitacion() {
        return listaCapacitacion;
    }

    public void addAccidente(Accidente accidente) {
        listaAccidente.add(accidente);
    }

    public ArrayList<Accidente> getListAccidente() {
        return listaAccidente;
    }

    public void addCapacitacio(VisitaTerreno visitaTerreno) {
        listaVisitaTerreno.add(visitaTerreno);
    }

    public ArrayList<VisitaTerreno> getListVisitaTerreno() {
        return listaVisitaTerreno;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(super.toString());
        sb.append("Cliente{");
        sb.append(", nombres=").append(nombres);
        sb.append(", apellidos=").append(apellidos);
        sb.append(", telefono=").append(telefono);
        sb.append(", afp=").append(afp);
        sb.append(", sistemaSalud=").append(sistemaSalud);
        sb.append(", direccion=").append(direccion);
        sb.append(", comuna=").append(comuna);
        sb.append('}');
        return sb.toString();
    }
}
