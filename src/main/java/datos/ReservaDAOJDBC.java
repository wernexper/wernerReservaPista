
package datos;
import static datos.Conexion.*;
import dominio.PistaDTO;
import dominio.UserDTO;
import dominio.UsuarioPistaDTO;
import java.sql.*;
import java.util.*;
import java.sql.Date;
import java.time.LocalDate;


public class ReservaDAOJDBC implements ReservaDAO{
   // Creamos los consultas que necesitemos 
    private static final String SQL_SELECT_USER = "SELECT * FROM usuario";
    private static final String SQL_SELECT_PISTA = "SELECT * FROM pista";
    
    
    private Connection conexionTransaccional; 

    public ReservaDAOJDBC() {
    }

    public ReservaDAOJDBC(Connection conexionTransaccional) {
        this.conexionTransaccional = conexionTransaccional;
    }

    @Override
    public List<UserDTO> seleccionarUser() throws SQLException {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        
        List<UserDTO> users = new ArrayList<>();
        
        try {
            conn = this.conexionTransaccional != null? this.conexionTransaccional : getConnection();
            stmt = conn.prepareStatement(SQL_SELECT_USER);
            rs = stmt.executeQuery();
         
            while(rs.next()){
                String idUsuario = rs.getString("idUsuario");
                String nombre = rs.getString("nombre");
                String tipo = rs.getString("tipo");
                users.add(new UserDTO(idUsuario, nombre, tipo));
            }
        } finally {
            close(rs);
            close(stmt);
            if (this.conexionTransaccional == null) {
                close(conn);
            }
        }
        return users;
    }

    @Override
    public List<PistaDTO> seleccionarPista() throws SQLException {
    
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        
        List<PistaDTO> pistas = new ArrayList<>();
        
        try {
            conn = this.conexionTransaccional != null? this.conexionTransaccional : getConnection();
            stmt = conn.prepareStatement(SQL_SELECT_PISTA);
            rs = stmt.executeQuery();
            

            while(rs.next()){
                int idPista = rs.getInt("idPista");
                String tipoPista = rs.getString("tipoPista");
                String nombrePista = rs.getString("nombrePista");
                String direccionPista = rs.getString("direccionPista");
                
                pistas.add(new PistaDTO(idPista, tipoPista, nombrePista,direccionPista));
            }
        } finally {
            close(rs);
            close(stmt);
            if (this.conexionTransaccional == null) {
                close(conn);
            }
        }
        return pistas;

        
        
    }

    @Override
    public List<PistaDTO> verPistaLibre(Date fechaEscojida) throws SQLException {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        String SQL_SELECT_PISTA_LIBRE ="SELECT DISTINCTROW nombrePista,direccionPista from pista JOIN usuariopista WHERE (usuariopista.idPista <> pista.idPista and usuariopista.fecha = '"+fechaEscojida+"' );";
                                      
        List<PistaDTO> pistaLibre = new ArrayList<>();
        
        try {
            conn = this.conexionTransaccional != null? this.conexionTransaccional : getConnection();
            stmt = conn.prepareStatement(SQL_SELECT_PISTA_LIBRE);
            rs = stmt.executeQuery();
            while(rs.next()){
               
               // String idUsuario = rs.getString("idUsuario");
                String nombrePista = rs.getString("nombrePista");
                String direccionPista = rs.getString("direccionPista");
//List<UsuarioPistaDTO> pistasLibres = reservaDao2.verPistaLibre(Date.valueOf("2021-11-09"));                                    
                pistaLibre.add(new PistaDTO( nombrePista, direccionPista));
            }
        } finally {
            close(rs);
            close(stmt);
            if (this.conexionTransaccional == null) {
                close(conn);
            }
        }
        return pistaLibre;
        
        
        
    }

    @Override
    public int escojerPista(UsuarioPistaDTO UsuarioPista)throws SQLException{
        
        
        Connection conn = null;
        PreparedStatement stmt = null;
        int registros = 0;        
        String SQL_ESCOJER = "INSERT INTO `usuariopista` (`idUsuario`, `idPista`, `fecha`)" 
                + "VALUES (?,?,?);";
                
        try {
            conn = this.conexionTransaccional != null? this.conexionTransaccional : getConnection();
            stmt = conn.prepareStatement(SQL_ESCOJER);
            
            stmt.setString(1, UsuarioPista.getIdUsuario());
            stmt.setInt(2, UsuarioPista.getIdPista());
            stmt.setDate(3, UsuarioPista.getFecha());
            
            System.out.println(stmt.toString()+"\n");
            
            registros = stmt.executeUpdate();
        } finally{
            close(stmt);
            if (this.conexionTransaccional == null) {
                close(conn);
            }
        }
        return registros;
        

     
     
    }

    @Override
    public int dejarPista(UsuarioPistaDTO UsuarioPista)throws SQLException {
        Connection conn = null;
        PreparedStatement stmt = null;
        String SQL_DELETE_USUARIOPISTA ="DELETE FROM `usuariopista` WHERE `usuariopista`.`idUsuario` = ? AND `usuariopista`.`idPista` = ? AND `usuariopista`.`fecha` = ?";
        
        //"DELETE FROM `usuariopista` WHERE `usuariopista`.`idUsuario` = \'2\' AND `usuariopista`.`idPista` = 10 AND `usuariopista`.`fecha` = \'2021-11-25\'
        
        int registros = 0;
        
        try {            
            conn = this.conexionTransaccional != null? this.conexionTransaccional : getConnection();
            stmt = conn.prepareStatement(SQL_DELETE_USUARIOPISTA);
            
            stmt.setString(1, UsuarioPista.getIdUsuario());
            stmt.setInt(2, UsuarioPista.getIdPista());
            stmt.setDate(3, UsuarioPista.getFecha());
            
            registros = stmt.executeUpdate();
            System.out.println("datos de la consulta:\n");
            System.out.println(stmt.toString());
            
        } finally {
            close(stmt);
            if (this.conexionTransaccional == null) {
                close(conn);
            }
        }
        return registros;
    }

    @Override
    public List<UsuarioPistaDTO> misPistas(String idUsuario) throws SQLException {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        String SQL_SELECT_PISTAUSUARIO = "SELECT idPista, fecha FROM `usuariopista` WHERE `usuariopista`.`idUsuario` = ?";
        
        
        List<UsuarioPistaDTO> pistaUsuario = new ArrayList<>();
        
        try {
            conn = this.conexionTransaccional != null? this.conexionTransaccional : getConnection();
            stmt = conn.prepareStatement(SQL_SELECT_PISTAUSUARIO);
            stmt.setString(1, idUsuario);
            rs = stmt.executeQuery();
            
            

            while(rs.next()){
              
                int idPista = rs.getInt("idPista");
                Date fecha = rs.getDate("fecha");
                
                
                pistaUsuario.add(new UsuarioPistaDTO(idPista, fecha));
                
            }
        } finally {
            close(rs);
            close(stmt);
            if (this.conexionTransaccional == null) {
                close(conn);
            }
        }
        return pistaUsuario;
    }

  

  

  

   
    
}
