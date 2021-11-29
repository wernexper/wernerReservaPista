
package dominio;

import java.sql.Date;


public class UsuarioPistaDTO {
    private String idUsuario;
    private int idPista;
    private Date fecha;

    public UsuarioPistaDTO() {
    }

    public UsuarioPistaDTO(String idUsuario, int idPista, Date fecha) {
        this.idUsuario = idUsuario;
        this.idPista = idPista;
        this.fecha = fecha;
    }
    public UsuarioPistaDTO( int idPista, Date fecha) {
        
        this.idPista = idPista;
        this.fecha = fecha;
    }
    
    public String getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(String idUsuario) {
        this.idUsuario = idUsuario;
    }

    public int getIdPista() {
        return idPista;
    }

    public void setIdPista(int idPista) {
        this.idPista = idPista;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    @Override
    public String toString() {
        return "usuarioPistaDTO{" + "idUsuario=" + idUsuario + ", idPista=" + idPista + ", fecha=" + fecha + '}';
    }
    
    
}
