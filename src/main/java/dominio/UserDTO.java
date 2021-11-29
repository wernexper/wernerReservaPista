
package dominio;


public class UserDTO {
    private String idUsuario;
    private String nombre;
    private String tipo;

    public UserDTO() {
    }

    public UserDTO(String nombre, String tipo) {
        this.nombre = nombre;
        this.tipo = tipo;
    }

    public UserDTO(String idUsuario, String nombre, String tipo) {
        this.idUsuario = idUsuario;
        this.nombre = nombre;
        this.tipo = tipo;
    }

   



    public String getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(String idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    @Override
    public String toString() {
        return "UserDTO{" + "idUsuario=" + idUsuario + ", nombre=" + nombre + ", tipo=" + tipo + '}';
    }
    
    
    
    
}
