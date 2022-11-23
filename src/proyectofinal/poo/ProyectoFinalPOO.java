
package proyectofinal.poo;

import Controlador.Controlador;
import misClases.Metodos;
import misClases.Registrar;
import misFormularios.Cartelera;
import misFormularios.frmLogin;
import misFormularios.frmRegistro;

public class ProyectoFinalPOO {

    public static void main(String[] args) {
        Registrar x=new Registrar();
        frmLogin log=new frmLogin();
        frmRegistro reg=new frmRegistro();
        //Cartelera car=new Cartelera();
        Metodos met=new Metodos();
        Controlador con=new Controlador(log,reg,x,met);
        con.Titulo();
//        car.setVisible(true);
        reg.setVisible(true);
    }
    
}
