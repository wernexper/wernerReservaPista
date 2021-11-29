
package datos;

import dominio.*;
import java.sql.Date;
import java.sql.SQLException;
import java.util.List;
public interface ReservaDAO {
    
  
    List<UserDTO> seleccionarUser() throws SQLException;
    List<PistaDTO> seleccionarPista() throws SQLException;
    
    List<PistaDTO> verPistaLibre(Date fechaEscojida) throws SQLException;
    
    int escojerPista(UsuarioPistaDTO UsuarioPista)throws SQLException;
    
    int dejarPista(UsuarioPistaDTO UsuarioPista)throws SQLException;
    
    List<UsuarioPistaDTO>misPistas(String idUsuario)throws SQLException;
    
    //void impReserva()
    
    
//void seleccionarUserPista(String idUsuario,int idPista) throws SQLException;
    

    
    //int insertar(PersonaDTO persona) throws SQLException;
    
   // int actualizar(PersonaDTO persona) throws SQLException;
    
   // int borrar(PersonaDTO persona) throws SQLException;
    
}
