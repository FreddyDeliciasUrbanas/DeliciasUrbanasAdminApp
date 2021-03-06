package cl.deliciasurbanas.www.admindeliciasurbanas.Models;

public class Mensaje {
    private int idMensaje;
    private String nombreMensaje;
    private String emailMensaje;
    private String telefonoMensaje;
    private String direccionMensaje;
    private String textoMensaje;

    public Mensaje(int idMensaje, String nombreMensaje, String emailMensaje, String telefonoMensaje, String direccionMensaje, String textoMensaje){
        this.idMensaje = idMensaje;
        this.nombreMensaje = nombreMensaje;
        this.emailMensaje = emailMensaje;
        this.telefonoMensaje = telefonoMensaje;
        this.direccionMensaje = direccionMensaje;
        this.textoMensaje = textoMensaje;
    }

    public Mensaje(){}

    public int getIdMensaje() {
        return idMensaje;
    }

    public void setIdMensaje(int idMensaje) {
        this.idMensaje = idMensaje;
    }

    public String getNombreMensaje() {
        return nombreMensaje;
    }

    public void setNombreMensaje(String nombreMensaje) {
        this.nombreMensaje = nombreMensaje;
    }

    public String getEmailMensaje() {
        return emailMensaje;
    }

    public void setEmailMensaje(String emailMensaje) {
        this.emailMensaje = emailMensaje;
    }

    public String getTelefonoMensaje() {
        return telefonoMensaje;
    }

    public void setTelefonoMensaje(String telefonoMensaje) {
        this.telefonoMensaje = telefonoMensaje;
    }

    public String getDireccionMensaje() {
        return direccionMensaje;
    }

    public void setDireccionMensaje(String direccionMensaje) {
        this.direccionMensaje = direccionMensaje;
    }

    public String getTextoMensaje() {
        return textoMensaje;
    }

    public void setTextoMensaje(String textoMensaje) {
        this.textoMensaje = textoMensaje;
    }
}
