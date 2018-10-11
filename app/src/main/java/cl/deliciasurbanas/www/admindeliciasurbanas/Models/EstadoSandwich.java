package cl.deliciasurbanas.www.admindeliciasurbanas.Models;

public class EstadoSandwich {
    private int idSandwich;
    private int estadoSandwich;

    public EstadoSandwich(int idSandwich, int estadoSandwich){
        this.estadoSandwich = estadoSandwich;
        this.idSandwich = idSandwich;
    }


    public int getIdSandwich() {
        return idSandwich;
    }

    public void setIdSandwich(int idSandwich) {
        this.idSandwich = idSandwich;
    }

    public int getEstadoSandwich() {
        return estadoSandwich;
    }

    public void setEstadoSandwich(int estadoSandwich) {
        this.estadoSandwich = estadoSandwich;
    }
}
