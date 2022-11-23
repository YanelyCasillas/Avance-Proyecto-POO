package Controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
import misClases.Hash;
import misClases.Metodos;
import misClases.Registrar;
import misFormularios.frmCompra;
import misFormularios.frmLogin;
import misFormularios.frmRegistro;

public class Controlador implements ActionListener {

    Metodos met = new Metodos();
    Registrar x = new Registrar();
    frmLogin log = new frmLogin();
    frmRegistro reg = new frmRegistro();
    frmCompra compra=new frmCompra();

    public Controlador(frmLogin log, frmRegistro reg, Registrar x, Metodos met) {
        this.log = log;
        this.reg = reg;
        this.x = x;
        this.met = met;

        //Registrar Cliente
        this.reg.btnRegistrar.addActionListener(this);

        //Registrar Cliente
        this.log.btnSesion.addActionListener(this);

        //Ir a iniciar sesion
        this.reg.btnAtras.addActionListener(this);
        //Ir a registrar
        this.log.btnRegistrar.addActionListener(this);
        
        //Ir a cartelera
        this.log.btnAtras.addActionListener(this);

    }

    public void Titulo() {
        reg.setTitle("CINE FUN");
        reg.setLocationRelativeTo(null);
        log.setTitle("CINE FUN");
        log.setLocationRelativeTo(null);
        compra.setTitle("CINE FUN");
        compra.setLocationRelativeTo(null);
    }

    public void Inscribir() {
        String pass = new String(reg.passContraseña.getPassword());
        String passcon = new String(reg.passConfirmar.getPassword());
        if (reg.txtDNI.getText().equals("") || pass.equals("") || passcon.equals("")
                || reg.txtNombre.getText().equals("") || reg.txtApePaterno.getText().equals("")
                || reg.txtApeMaterno.getText().equals("") || reg.txtCorreo.getText().equals("")
                || reg.txtEdad.getText().equals("") || reg.txtCelular.getText().equals("")
                || reg.cboxDistrito.getSelectedItem().equals("Elegir distrito")
                || reg.cboxGenero.getSelectedItem().equals("Elegir género")) {

            JOptionPane.showMessageDialog(null, "Hay campos vacíos");

        } else {
            if (pass.equals(passcon)) {

                if (met.existeUsuario(Integer.parseInt(reg.txtDNI.getText())) == 0) {

                    if (met.esEmail(reg.txtCorreo.getText())) {
                        String nuevoPass = Hash.sha1(pass);

                        x.setIdCliente(Integer.parseInt(reg.txtDNI.getText()));
                        x.setNombre(reg.txtNombre.getText());
                        x.setApePaterno(reg.txtApePaterno.getText());
                        x.setApeMaterno(reg.txtApeMaterno.getText());
                        x.setCorreo(reg.txtCorreo.getText());
                        x.setContraseña(nuevoPass);
                        x.setEdad(Integer.parseInt(reg.txtEdad.getText()));
                        x.setCelular(Integer.parseInt(reg.txtCelular.getText()));
                        x.setDistrito((String) reg.cboxDistrito.getSelectedItem());
                        x.setGenero((String) reg.cboxGenero.getSelectedItem());

                        if (met.InsertarUsuario(x)) {
                            JOptionPane.showMessageDialog(null, "Usuario registrado");
                            reg.limpiarCajas();
                            log.setVisible(true);
                            reg.setVisible(false);
                        } else {
                            JOptionPane.showMessageDialog(null, "Error al registrar");
                        }
                    } else {
                        JOptionPane.showMessageDialog(null, "El correo electrónico no es válido");
                    }

                } else {
                    JOptionPane.showMessageDialog(null, "El usuario ya existe");
                }

            } else {
                JOptionPane.showMessageDialog(null, "Las contraseñas no coinciden");
            }

        }

    }

    public void Ingreso() {

        String pass = new String(log.passContraseña.getPassword());

        if (!log.txtDNI.getText().equals("") && !pass.equals("")) {
            String nuevoPass = Hash.sha1(pass);

            x.setIdCliente(Integer.parseInt(log.txtDNI.getText()));
            x.setContraseña(nuevoPass);

            if (met.Autenticacion(x)) {
                JOptionPane.showMessageDialog(null, "Logeo correcto");
                compra.setVisible(true);
                log.setVisible(false);
            } else {
                JOptionPane.showMessageDialog(null, "Error de logeo");
            }

        } else {
            JOptionPane.showMessageDialog(null, "Debe ingresar sus datos");
        }

    }

    @Override
    public void actionPerformed(ActionEvent e) {

        try {
            if (e.getSource() == reg.btnRegistrar) {
                Inscribir();

            }
            if (e.getSource() == log.btnSesion) {
                Ingreso();

            }
            if (e.getSource() == log.btnRegistrar) {
                reg.setVisible(true);
                log.setVisible(false);

            }
            if (e.getSource() == reg.btnAtras) {
                log.setVisible(true);
                reg.setVisible(false);

            }

        } catch (Exception a) {
            JOptionPane.showMessageDialog(null, a);
        }

    }

}
