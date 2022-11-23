
package misClases;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.JOptionPane;
import misClases.ConexionBD;

public class Metodos extends ConexionBD {
    
    
    public boolean InsertarUsuario(Registrar regis){
        
        Connection con= getConexion();
        
        try {
            String SQL="insert into Clientes (Id_Cliente,Nombre_Cliente,ApePaterno_Cliente,"
                    + "ApeMaterno_Cliente,Correo_Cliente,Contraseña_Cliente,Edad_Cliente,"
                    + "Celular_Cliente,Distrito_Cliente,Sexo_Cliente)values(?,?,?,?,?,?,?,?,?,?)";
            PreparedStatement pst=con.prepareStatement(SQL);
            pst.setInt(1, regis.getIdCliente());
            pst.setString(2, regis.getNombre());
            pst.setString(3, regis.getApePaterno());
            pst.setString(4, regis.getApeMaterno());
            pst.setString(5, regis.getCorreo());
            pst.setString(6, regis.getContraseña());
            pst.setInt(7, regis.getEdad());
            pst.setInt(8, regis.getCelular());
            pst.setString(9, regis.getDistrito());
            pst.setString(10, regis.getGenero());
            pst.execute();
            
            return true;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error "+e);
            return false;
        }
        
    }
    
    public int existeUsuario(int idCliente){
        
        Connection con= getConexion();
        ResultSet rs=null;
        try {
            String SQL="select count(Id_Cliente) from Clientes where Id_Cliente=?";
            PreparedStatement pst=con.prepareStatement(SQL);
            pst.setInt(1, idCliente);
            rs=pst.executeQuery();
            if (rs.next()) {
                return rs.getInt(1);
            } 
            
            return 1;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error "+e);
            return 1;
        }
        
    }
    
    public boolean esEmail(String correo){
        //patron para validar el email
        Pattern pattern = Pattern.compile("^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"+
                "[A-Za-z0-9-]+(\\.[A-za-z0-9]+)*(\\.[A-Za-z]{2,})$");
        Matcher mather=pattern.matcher(correo);
        return mather.find();
    }
    
     
    public boolean Autenticacion(Registrar user) {
        
        Connection con= getConexion();
        ResultSet rs=null;
        try {
            String SQL="select Id_Cliente,Nombre_Cliente,ApePaterno_Cliente,ApeMaterno_Cliente,"
                    + "Correo_Cliente,Contraseña_Cliente,Edad_Cliente,Celular_Cliente,"
                    + "Distrito_Cliente,Sexo_Cliente from Clientes where Id_Cliente=?";
            PreparedStatement pst=con.prepareStatement(SQL);
            pst.setInt(1, user.getIdCliente());
            rs=pst.executeQuery();
            
            if (rs.next()) {
                if (user.getContraseña().equals(rs.getString(6))) {
                    return true;
                } else {
                    return false;
                }
            } 
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error "+e);
        }
        return false;
    }
    
//    public boolean Autenticacion(String Puser,String Ppaw) {
//        
//        Connection con= getConexion();
//        ResultSet rs=null;
//        try {
//            String SQL="select Id_Cliente,Contraseña_Cliente from Clientes where Id_Cliente=? and Contraseña_Cliente=?";
//            PreparedStatement pst=con.prepareStatement(SQL);
//            pst.setString(1, Puser);
//            pst.setString(2, Ppaw);
//            rs=pst.executeQuery();
//            
//            while (rs.next()) { 
//                desconectar();
//                return true;
//            }
//        } catch (Exception e) {
//            JOptionPane.showMessageDialog(null, "Error "+e);
//        }
//        return false;
//    }
}
