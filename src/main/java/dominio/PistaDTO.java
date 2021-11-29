
package dominio;


public class PistaDTO {
    
    private int idPista;
    private String tipoPista;
    private String nombrePista;
    private String direccionPista;   

    public PistaDTO() {
    }

    public PistaDTO(String tipoPista, String nombrePista, String direccionPista) {
        this.tipoPista = tipoPista;
        this.nombrePista = nombrePista;
        this.direccionPista = direccionPista;
    }

    public PistaDTO(int idPista, String tipoPista, String nombrePista, String direccionPista) {
        this.idPista = idPista;
        this.tipoPista = tipoPista;
        this.nombrePista = nombrePista;
        this.direccionPista = direccionPista;
    }
    //lo usamos para ver las pistas libres segun una fecha 
    public PistaDTO(String nombrePista, String direccionPista) {
        this.nombrePista = nombrePista;
        this.direccionPista = direccionPista;
    }

    public int getIdPista() {
        return idPista;
    }

    public void setIdPista(int idPista) {
        this.idPista = idPista;
    }

    public String getTipoPista() {
        return tipoPista;
    }

    public void setTipoPista(String tipoPista) {
        this.tipoPista = tipoPista;
    }

    public String getNombrePista() {
        return nombrePista;
    }

    public void setNombrePista(String nombrePista) {
        this.nombrePista = nombrePista;
    }

    public String getDireccionPista() {
        return direccionPista;
    }

    public void setDireccionPista(String direccionPista) {
        this.direccionPista = direccionPista;
    }

    @Override
    public String toString() {
        return "PistaDTO{" + "idPista=" + idPista + ", tipoPista=" + tipoPista + ", nombrePista=" + nombrePista + ", direccionPista=" + direccionPista + '}';
    }
    
    
}
