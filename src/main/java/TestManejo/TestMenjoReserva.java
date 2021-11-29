
package TestManejo;

import datos.*;
import dominio.*;
import java.sql.*;
import java.util.List;
import java.sql.Date;
import java.text.DateFormat;
import java.util.Scanner;
import java.io.*;
public class TestMenjoReserva {
        public static void main(String[] args) throws SQLException {
        Scanner scanner = new Scanner(System.in);
	int select = -1; 
        String idUsuario;
        int idPista;
        int dia;
        int mes;
        
        Connection conexion = null;
        
        try {
            conexion =  Conexion.getConnection();
            
            if (conexion.getAutoCommit()){
                conexion.setAutoCommit(false);
            }
            while(select != 0){
			
			try{
                            //escojemos del 1-6 y 0 para Salir
				System.out.println("Elige opción:"
                                        + "\n1: Usuarios"
                                        + "\n2 Pistas"
                                        + "\n3 Pista Libre"
                                        + "\n4 Escojer Pista"
                                        + "\n5 Cancelar Reserva"
                                        + "\n6 Ver Mis Pistas"
                                        + "\n0 Salir");                                                
				//Recoger una variable por consola
				select = Integer.parseInt(scanner.nextLine()); 
	
				//Ejemplo de switch case en Java
				switch(select){
				case 1: 
                                        //creamos la conexion con la bbdd
                                        ReservaDAOJDBC reservaDao = new ReservaDAOJDBC(conexion);
                                        List<UserDTO> usuarios = reservaDao.seleccionarUser();    
                                        usuarios.forEach(usuario -> {
                                           System.out.println("Persona = " + usuario);
                                       });
					//salimos del swch
                                        
					break;
				case 2: 
					ReservaDAOJDBC reservaDao2 = new ReservaDAOJDBC(conexion);
                                        List<PistaDTO> pistas = reservaDao2.seleccionarPista();    
                                        pistas.forEach(pista -> {
                                           System.out.println("Pista = " + pista);  
                                       });
					break;
				case 3: 
                                    ReservaDAOJDBC reservaDao3 = new ReservaDAOJDBC(conexion);
                                    //introducimos los 
                                    List<PistaDTO> pistasLibres = reservaDao3.verPistaLibre(Date.valueOf("2021-11-15"));  
                                    System.out.println("PISTAS LIBRES DEL DIA: 2021-11-15\n");
                                    pistasLibres.forEach(pistaLibre -> {
                                        System.out.println("Pista: " + pistaLibre.getNombrePista()+"\nDirección: "+pistaLibre.getDireccionPista()+"\n\n");  
                                    });
					
					break;
				case 4: 
                                    
                                    conexion =  Conexion.getConnection();
            
                                    if (conexion.getAutoCommit()){
                                        conexion.setAutoCommit(false);
                                    }
                                    
                                    ReservaDAOJDBC reservaDao4 = new ReservaDAOJDBC(conexion);
                                    idUsuario="";
                                    idPista=0;
                                    dia=0;
                                    mes=0;
                                    
                                    System.out.println("Usuario:\n");
                                    idUsuario= scanner.nextLine();
                                    System.out.println("pista:\n");
                                    idPista = scanner.nextInt();
                                    System.out.println("dia:\n");
                                    dia = scanner.nextInt();
                                    System.out.println("mes:\n");
                                    mes = scanner.nextInt();
                                    
                                    UsuarioPistaDTO escojerPista = new UsuarioPistaDTO(idUsuario, idPista, Date.valueOf("2021-"+mes+"-"+(dia+1)));
                                    System.out.println("Pista escojida:\n");
                                    System.out.println(escojerPista.toString());
                                    //ReservaDAOJDBC reservaDao4 = new ReservaDAOJDBC(conexion);

                                    reservaDao4.escojerPista(escojerPista);
                                    conexion.commit();

                                    //System.out.println(escojerPista.toString());
					
					break;
                                case 5: 
                                        //public int dejarPista(UsuarioPistaDTO UsuarioPista)throws SQLException {
                                    conexion =  Conexion.getConnection();
            
                                    if (conexion.getAutoCommit()){
                                        conexion.setAutoCommit(false);
                                    }
                                     ReservaDAOJDBC reservaDao5 = new ReservaDAOJDBC(conexion);
                                    idUsuario="";
                                    idPista=0;
                                    dia=0;
                                    mes=0;
                                    
                                    System.out.println("Usuario:\n");
                                    idUsuario= scanner.nextLine();
                                    System.out.println("pista:\n");
                                    idPista = scanner.nextInt();
                                    System.out.println("dia:\n");
                                    dia = scanner.nextInt();
                                    System.out.println("mes:\n");
                                    mes = scanner.nextInt();
                                    UsuarioPistaDTO dejarpista = new UsuarioPistaDTO(idUsuario, idPista,  Date.valueOf("2021-"+mes+"-"+(dia+1)));
                                    System.out.println("Reserva cancelada :\n");
                                    System.out.println(dejarpista.toString());
                                    reservaDao5.dejarPista(dejarpista);
                                    
                                    
                                    
                                    conexion.commit();
                                    
                                    
					break;
                                case 6: 
                                    //crearmos el archivo
                                    String fileName = "misPistas.txt";
                                    String encoding = "UTF-8";
                                    PrintWriter writer = new PrintWriter(fileName, encoding);
                                    conexion =  Conexion.getConnection();
            
                                    if (conexion.getAutoCommit()){
                                        conexion.setAutoCommit(false);
                                    }
                                     ReservaDAOJDBC reservaDao6 = new ReservaDAOJDBC(conexion);
                                     //public List<UsuarioPistaDTO> misPistas(String idUsuario) throws SQLException {
                                     
                                        idUsuario="";
                                        System.out.println("Usuario:\n");
                                        idUsuario= scanner.nextLine();
                                        //escribimos en el archivo
                                        List<UsuarioPistaDTO> mipista = reservaDao6.misPistas(idUsuario);
                                        mipista.forEach(usuario -> {
                                           writer.println("Pista = " + usuario);
                                       });
                                        writer.close();
					conexion.commit();
                                        //leemos los datos dentro del archivo
                                        
                                        try {
                                            System.out.println("Mis Pistas");
                                            Scanner input = new Scanner(new File("misPistas.txt"));
                                            while (input.hasNextLine()) {
                                                String line = input.nextLine();
                                                System.out.println(line);
                                            }
                                            input.close();
                                        } catch (Exception ex) {
                                            ex.printStackTrace();
                                        }
                                        
                                        
					break;
                                        
				case 0: 
					System.out.println("Adios!");
					break;
				default:
					System.out.println("Número no reconocido");break;
				}
				
				System.out.println("\n"); //Mostrar un salto de línea en Java
				
			}catch(Exception  e){
				System.out.println("");
			}
		}

            
            conexion.commit();
        } catch (SQLException ex){
            ex.printStackTrace(System.out);
            System.out.println("Entramos en el rollback");
            try {
                conexion.rollback();
            } catch (SQLException e) {
                e.printStackTrace(System.out);
            }
        }
        
    }
}
