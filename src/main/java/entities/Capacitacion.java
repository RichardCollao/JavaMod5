package entities;

public class Capacitacion {

    private Integer idCapacitacion; // obligatorio, número interno de la capacitación manejado por la empresa
    private String rutEmpresa; // obligatorio
    private String dia; // texto, día de la semana. Debe ser un valor permitido entre “Lunes” y “Domingo”
    private String hora; // debe ser una hora válida del día, en formato HH:MM (hora desde 0 a 23, minutos entre 0 y 59)
    private String lugar; // obligatorio, mínimo 10 caracteres, máximo 50
    private String duracion; //  máximo 70 caracteres
    private Integer cantidadAsistentes; // obligatorio, número entero menor que 1000
    
    public Capacitacion() {
	super();
    }

    public Capacitacion(Integer idCapacitacion, String rutEmpresa, String dia, String hora, String lugar,
	    String duracion, Integer cantidadAsistentes) {
	super();
	this.idCapacitacion = idCapacitacion;
	this.rutEmpresa = rutEmpresa;
	this.dia = dia;
	this.hora = hora;
	this.lugar = lugar;
	this.duracion = duracion;
	this.cantidadAsistentes = cantidadAsistentes;
    }

    public Integer getIdCapacitacion() {
        return idCapacitacion;
    }

    public void setIdCapacitacion(Integer idCapacitacion) {
        this.idCapacitacion = idCapacitacion;
    }

    public String getRutEmpresa() {
        return rutEmpresa;
    }

    public void setRutEmpresa(String rutEmpresa) {
        this.rutEmpresa = rutEmpresa;
    }

    public String getDia() {
        return dia;
    }

    public void setDia(String dia) {
        this.dia = dia;
    }

    public String getHora() {
        return hora;
    }

    public void setHora(String hora) {
        this.hora = hora;
    }

    public String getLugar() {
        return lugar;
    }

    public void setLugar(String lugar) {
        this.lugar = lugar;
    }

    public String getDuracion() {
        return duracion;
    }

    public void setDuracion(String duracion) {
        this.duracion = duracion;
    }

    public Integer getCantidadAsistentes() {
        return cantidadAsistentes;
    }

    public void setCantidadAsistentes(Integer cantidadAsistentes) {
        this.cantidadAsistentes = cantidadAsistentes;
    }

    @Override
    public String toString() {
	StringBuilder builder = new StringBuilder();
	builder.append("Capacitacion [idCapacitacion=");
	builder.append(idCapacitacion);
	builder.append(", rutEmpresa=");
	builder.append(rutEmpresa);
	builder.append(", dia=");
	builder.append(dia);
	builder.append(", hora=");
	builder.append(hora);
	builder.append(", lugar=");
	builder.append(lugar);
	builder.append(", duracion=");
	builder.append(duracion);
	builder.append(", cantidadAsistentes=");
	builder.append(cantidadAsistentes);
	builder.append("]");
	return builder.toString();
    }
}
