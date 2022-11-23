
package misClases;

import java.sql.Connection;
import java.sql.DriverManager;
import javax.swing.JOptionPane;

public class ConexionBD {
    String DRIVER="com.mysql.jdbc.Driver";
    String USUARIO="root";
    String PASSWORD="123";
    String URL="jdbc:mysql://localhost:3306/bbdd_cine";
    private Connection con;
    
    public Connection getConexion()
    {
        con=null;
        try 
        {
            Class.forName(DRIVER);
            con=(Connection) DriverManager.getConnection(URL,USUARIO,PASSWORD);
//            JOptionPane.showMessageDialog(null, "Conexion establecida");
        } 
        catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error de conexion "+e.getMessage());
        }
        return con;
    }
    
//    public Connection getConexion()
//    {
//        return con;
//    }
    
    public void desconectar()
    {
        con=null;
        if(con==null)
            JOptionPane.showMessageDialog(null, "Conexion terminada");
    }
}
