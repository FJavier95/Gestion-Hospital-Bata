package Control;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Timestamp;
import java.util.Vector;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import Modelo.BBDD;
import Modelo.Fecha_Paciente;
import Modelo.Paciente;
import Modelo.Tratamiento;
import Vista.VentanaAdministrador;
import Vista.VentanaAdministradorGastosTotales;
import Vista.VentanaAdministradorPacientes;
import Vista.VentanaAdministradorPrecios;
import Vista.VentanaAnadirUsuario;
import Vista.VentanaLogin;
//import Vista.VentanaBusquedaPaciente;
import Vista.VentanaVerTratamientos;

public class ControladorAdministrador implements ActionListener {

	VentanaAdministrador ventanaControlada;
	//VentanaBusquedaPaciente ventanaPacientes;
	Vector<Paciente> pacientes = new Vector();
	public JFrame frmDialogo = null;
	Paciente pac;
	
	public ControladorAdministrador(VentanaAdministrador vap){
		ventanaControlada=vap;
		
	}
	
	public void actionPerformed(ActionEvent e) {
			
		if (e.getActionCommand().equals("Pacientes")){
			pacientes = BBDD.obtenerPacientes(1);
			VentanaAdministradorPacientes vp= new VentanaAdministradorPacientes();
			ventanaControlada.frame.setEnabled(false);
			ControladorAdministradorPacientes cp= new ControladorAdministradorPacientes(vp,ventanaControlada);
			vp.addController(cp);
			vp.crearVentana(pacientes);
		}else if (e.getActionCommand().equals("Precios")){
			ventanaControlada.frame.setEnabled(false);
				VentanaAdministradorPrecios vp= new VentanaAdministradorPrecios();
				ControladorAdministradorPrecios cp= new ControladorAdministradorPrecios(vp,pac,ventanaControlada);
				vp.addController(cp);
				vp.crearTratamiento();
			}else if (e.getActionCommand().equals("Gastos Tratamientos")){
				ventanaControlada.frame.setEnabled(false);
				//ventanaControlada.frame.setEnabled(false);
				VentanaAdministradorGastosTotales vp= new VentanaAdministradorGastosTotales();
				ControladorAdministradorGastosTotales cp= new ControladorAdministradorGastosTotales(vp);
				vp.addController(cp,ventanaControlada);
				vp.crearVentana();
				
			
			}else if (e.getActionCommand().equals("Usuarios")){
				ventanaControlada.frame.setEnabled(false);
				VentanaAnadirUsuario va=new VentanaAnadirUsuario();
				ControladorAnadirUsuario cau=new ControladorAnadirUsuario(va);
				va.addController(cau,ventanaControlada);
				
				va.crearVentana();
			}else if (e.getActionCommand().equals("CambiarUsuario")){
				ventanaControlada.frame.setVisible(false);
				  VentanaLogin mainframe=new VentanaLogin();
	              
				  ControladorLogin mc=new ControladorLogin(mainframe);
				  mainframe.addController(mc);            
				  mainframe.crearVentana();	
			}
		
				
		
			
	
		
	}
}

