package modelo.entities;
import java.util.ArrayList;

public class VisitaTerreno {

    private int idVisita; // obligatorio, número interno manejado por la compañía.
    private String rut; // obligatorio
    private String fechaAccidente; // fecha del accidente, independiente si lo declara como una fecha o un String, debe ser desplegado con el formato DD/MM/AAAA
    private String hora; // debe ser una hora válida del día, en formato HH:MM (hora desde 0 a 23, minutos entre 0 y 59)
    private String luga; // obligatorio, mínimo 10 caracteres, máximo 50
    private String comentarios; // máximo 100 caracteres
    private ArrayList<Revision> listaRevision; // Una visita en terreno debe tener una o más revisiones por cada ocasión

    public VisitaTerreno() {
    }

    public VisitaTerreno(int idVisita, String rut, String fechaAccidente, String hora, String luga, String comentarios) {
        this.idVisita = idVisita;
        this.rut = rut;
        this.fechaAccidente = fechaAccidente;
        this.hora = hora;
        this.luga = luga;
        this.comentarios = comentarios;
    }

    public String getComentarios() {
        return comentarios;
    }

    public void setComentarios(String comentarios) {
        this.comentarios = comentarios;
    }

    public int getIdVisita() {
        return idVisita;
    }

    public void setIdVisita(int idVisita) {
        this.idVisita = idVisita;
    }

    public String getRut() {
        return rut;
    }

    public void setRut(String rut) {
        this.rut = rut;
    }

    public String getFechaAccidente() {
        return fechaAccidente;
    }

    public void setFechaAccidente(String fechaAccidente) {
        this.fechaAccidente = fechaAccidente;
    }

    public String getHora() {
        return hora;
    }

    public void setHora(String hora) {
        this.hora = hora;
    }

    public String getLuga() {
        return luga;
    }

    public void setLuga(String luga) {
        this.luga = luga;
    }

    public void addRevision(Revision revision) {
        listaRevision.add(revision);
    }

    public ArrayList<Revision> getListRevision() {
        return listaRevision;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("VisitaTerreno{");
        sb.append("idVisita=").append(idVisita);
        sb.append(", rut=").append(rut);
        sb.append(", fechaAccidente=").append(fechaAccidente);
        sb.append(", hora=").append(hora);
        sb.append(", luga=").append(luga);
        sb.append(", comentarios=").append(comentarios);
        sb.append('}');
        return sb.toString();
    }

}
