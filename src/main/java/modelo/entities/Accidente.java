package modelo.entities;

public class Accidente {

    private int idAccidente; // obligatorio, número interno manejado por la compañía.
    private String rut; // obligatorio
    private String dia; // fecha del accidente, independiente si lo declara como una fecha o un String, debe ser desplegado con el formato DD/MM/AAAA
    private String hora; // fecha del accidente, independiente si lo declara como una fecha o un String, debe ser desplegado con el formato DD/MM/AAAA
    private String lugar; // obligatorio, mínimo 10 caracteres, máximo 50
    private String origen; // máximo 100 caracteres
    private String consecuencias; // máximo 100 caracteres

    public Accidente() {
    }

    public Accidente(int idAccidente, String rut, String dia, String hora, String lugar, String origen, String consecuencias) {
        this.idAccidente = idAccidente;
        this.rut = rut;
        this.dia = dia;
        this.hora = hora;
        this.lugar = lugar;
        this.origen = origen;
        this.consecuencias = consecuencias;
    }

    public String getConsecuencias() {
        return consecuencias;
    }

    public void setConsecuencias(String consecuencias) {
        this.consecuencias = consecuencias;
    }

    public int getIdAccidente() {
        return idAccidente;
    }

    public void setIdAccidente(int idAccidente) {
        this.idAccidente = idAccidente;
    }

    public String getRut() {
        return rut;
    }

    public void setRut(String rut) {
        this.rut = rut;
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

    public String getOrigen() {
        return origen;
    }

    public void setOrigen(String origen) {
        this.origen = origen;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Accidente{");
        sb.append("idAccidente=").append(idAccidente);
        sb.append(", rut=").append(rut);
        sb.append(", dia=").append(dia);
        sb.append(", hora=").append(hora);
        sb.append(", lugar=").append(lugar);
        sb.append(", origen=").append(origen);
        sb.append(", consecuencias=").append(consecuencias);
        sb.append('}');
        return sb.toString();
    }

}
