package modelo.entities;

public class Revision {

    private int idRevision; //  obligatorio, número interno manejado por la compañía.
    private int idVisita; //  obligatorio, número de la vista a la que se asocia la revisión.
    private String nombreRevision; // obligatorio, mínimo 10 caracteres, máximo 50
    private String revision; // Detalle para revisar: máximo 100 caracteres
    private int estado; // 1 (sin problemas), 2 (con observaciones), 3 (no aprueba), solo se pueden ingresar los valores antes indicados.

    public Revision() {
    }

    public Revision(int idRevision, int idVisita, String nombreRevision, String revision, int estado) {
        this.idRevision = idRevision;
        this.idVisita = idVisita;
        this.nombreRevision = nombreRevision;
        this.revision = revision;
        this.estado = estado;
    }

    public int getEstado() {
        return estado;
    }

    public void setEstado(int estado) {
        this.estado = estado;
    }

    public int getIdRevision() {
        return idRevision;
    }

    public void setIdRevision(int idRevision) {
        this.idRevision = idRevision;
    }

    public int getIdVisita() {
        return idVisita;
    }

    public void setIdVisita(int idVisita) {
        this.idVisita = idVisita;
    }

    public String getNombreRevision() {
        return nombreRevision;
    }

    public void setNombreRevision(String nombreRevision) {
        this.nombreRevision = nombreRevision;
    }

    public String getRevision() {
        return revision;
    }

    public void setRevision(String revision) {
        this.revision = revision;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Revision{");
        sb.append("idRevision=").append(idRevision);
        sb.append(", idVisita=").append(idVisita);
        sb.append(", nombreRevision=").append(nombreRevision);
        sb.append(", revision=").append(revision);
        sb.append(", estado=").append(estado);
        sb.append('}');
        return sb.toString();
    }

}
