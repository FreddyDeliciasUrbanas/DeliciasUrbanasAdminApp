package cl.deliciasurbanas.www.admindeliciasurbanas.Models;

public class Sandwich {
    private String id;
    private String nombre;
    private String descripcion;
    private String urlImg;
    private String urlMin;
    private String tipo;
    private String kcal;
    private String precio;


    public Sandwich (String id, String nombre, String descripcion, String urlImg, String urlMin, String tipo, String kcal, String precio){
        this.id = id;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.urlImg = urlImg;
        this.urlMin = urlMin;
        this.tipo = tipo;
        this.kcal = kcal;
        this.precio = precio;
    }

    public Sandwich(){}

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getUrlImg() {
        return urlImg;
    }

    public void setUrlImg(String urlImg) {
        this.urlImg = urlImg;
    }

    public String getUrlMin() {
        return urlMin;
    }

    public void setUrlMin(String urlMin) {
        this.urlMin = urlMin;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getKcal() {
        return kcal;
    }

    public void setKcal(String kcal) {
        this.kcal = kcal;
    }

    public String getPrecio() {
        return precio;
    }

    public void setPrecio(String precio) {
        this.precio = precio;
    }
}
