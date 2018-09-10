package Control;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import Modelo.BBDD;
import Modelo.Paciente;
import Vista.VentanaAdministradorModificarPaciente;
import Vista.VentanaAdministradorPacientes;
import Vista.VentanaBusquedaPaciente;
import Vista.VentanaEditarDatos;

public class ControladorAdministradorModificarPacientes implements MouseListener, ActionListener, WindowListener {

	VentanaAdministradorModificarPaciente ventanaControlada;
	VentanaAdministradorPacientes vbp;
	Paciente paciente ;
	public JFrame frmDialogo = null;
	
	public ControladorAdministradorModificarPacientes(VentanaAdministradorModificarPaciente ved , Paciente pac, VentanaAdministradorPacientes vbp){
		ventanaControlada=ved;
		paciente = pac;
		this.vbp = vbp;
		
	}
	
	public void actionPerformed(ActionEvent arg0) {
		Paciente paciente1;
		boolean aux= true;
		
		if(arg0.getSource().equals(ventanaControlada.btn_AP_Aceptar)){


			String nombre=ventanaControlada.LabelNombre.getText();
			
			String apellido1=ventanaControlada.LabelApellido1.getText();
			String apellido2=ventanaControlada.LabelApellido2.getText();
			String direccion=ventanaControlada.LabelDireccion.getText();
			String n_historial=ventanaControlada.laberN_historial.getText();
			String poblacion=ventanaControlada.lbl_AP_Poblacion.getText();
		
			//String condicion=ventanaControlada.cb_Condicion.getSelectedItem().toString();
			String telefono1=ventanaControlada.LabelTelefono.getText();
			String telefono2=ventanaControlada.LabelTelefono1.getText();
			String telefono3=ventanaControlada.LabelTelefono2.getText();
			
			//String n_asegurado=ventanaControlada.labelN_Asegurado.getText();
			
			
			int n = JOptionPane.showConfirmDialog((Component) frmDialogo , "Esta seguro que desea modificar los datos","Confirmacion", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
			
			if (n==JOptionPane.YES_OPTION){
				
			if(telefono1.length()>=9 && telefono1.length()<10 && telefono2.length()>=9 && telefono2.length()<10)
			{
				if(!BBDD.esAlfaNumerica(direccion) && !BBDD.esAlfaNumerica(poblacion) && !BBDD.esAlfaNumerica(nombre)
							&& !BBDD.esAlfaNumerica(apellido1) && !BBDD.esAlfaNumerica(apellido2) /*&& !BBDD.esAlfaNumerica(condicion)*/)
				{
					/*if(!n_historial.equals(paciente.getN_historial())){
						BBDD.modificarPacienteString(ventanaControlada.txtN_historial.getText(), paciente, n_historial);
					}else {BBDD.modificarPacienteString(ventanaControlada.txtN_historial.getText(), paciente, String.valueOf(paciente.getN_historial()));}	
					*/
					try{	
							
						/*if(!condicion.equals(paciente.getCondicionString())){
							BBDD.modificarPacienteInt(ventanaControlada.txtpnCondicion.getText(), paciente, Paciente.getCondiciontoInt(condicion));
						}else {BBDD.modificarPacienteInt(ventanaControlada.txtpnCondicion.getText(), paciente, paciente.getCondiciontoInt(paciente.getCondicionString()));}
						*/	
						if(!poblacion.equals(paciente.getPoblacion())){
							BBDD.modificarPacienteString(ventanaControlada.txt_AP_Poblacion.getText(), paciente, poblacion);
						}else {BBDD.modificarPacienteString(ventanaControlada.txt_AP_Poblacion.getText(), paciente, paciente.getPoblacion());}
						
						if (!nombre.equals(paciente.getNombre())){
							BBDD.modificarPacienteString(ventanaControlada.txtpnNombre.getText(), paciente, nombre);
						}else {BBDD.modificarPacienteString(ventanaControlada.txtpnNombre.getText(), paciente, paciente.getNombre());}
						
						if(!apellido1.equals(paciente.getApellido1())){
							BBDD.modificarPacienteString(ventanaControlada.txtpnApellido1.getText(), paciente, apellido1);
						}else {BBDD.modificarPacienteString(ventanaControlada.txtpnApellido1.getText(), paciente, paciente.getApellido1());	}
						
						if(!apellido2.equals(paciente.getApellido2())){
							BBDD.modificarPacienteString(ventanaControlada.txtpnApellido2.getText(), paciente, apellido2);
						}else {BBDD.modificarPacienteString(ventanaControlada.txtpnApellido2.getText(), paciente, paciente.getApellido2());}
						
						if(!(Integer.parseInt(telefono1) == paciente.getTelf1())){
							BBDD.modificarPacienteInt(ventanaControlada.txtpnTelefono.getText(), paciente, Integer.parseInt(telefono1));
						}else {BBDD.modificarPacienteInt(ventanaControlada.txtpnTelefono.getText(), paciente, paciente.getTelf1());}
						
						if(!(Integer.parseInt(telefono2) == paciente.getTelf2())){
							BBDD.modificarPacienteInt(ventanaControlada.txtpnTelefono1.getText(), paciente, Integer.parseInt(telefono2));
						}else {BBDD.modificarPacienteInt(ventanaControlada.txtpnTelefono1.getText(), paciente,paciente.getTelf2());}
						
						if(!(Integer.parseInt(telefono3) == paciente.getTelf3())){	
							BBDD.modificarPacienteInt(ventanaControlada.txtpnTelefono2.getText(), paciente, Integer.parseInt(telefono3));
						}else {BBDD.modificarPacienteInt(ventanaControlada.txtpnTelefono2.getText(), paciente, paciente.getTelf3());}
							
						if(!direccion.equals(paciente.getDireccion())){
							BBDD.modificarPacienteString(ventanaControlada.txtDireccion.getText(), paciente, direccion);
						}else {BBDD.modificarPacienteString(ventanaControlada.txtDireccion.getText(), paciente, paciente.getDireccion());}
						
						
				}catch (Exception e){
					aux=false;
					JOptionPane.showMessageDialog((Component) frmDialogo, "El telefono , es erroneo, debe contener numeros, no letras ni caracteres especiales.", "Error", JOptionPane.ERROR_MESSAGE);
					}
			}else {
				aux=false;
				JOptionPane.showMessageDialog((Component) frmDialogo, "El nombre, los apellido, la direccion y la poblacion deben contener solo letras no numeros", "Error", JOptionPane.ERROR_MESSAGE);
				 }
				}else {
					aux = false;
					JOptionPane.showMessageDialog((Component) frmDialogo, "El telefono es erroneo debe tener 9 digitos", "Error", JOptionPane.ERROR_MESSAGE);
					}
					
					if (aux== true)		
			JOptionPane.showMessageDialog((Component) frmDialogo, "Los datos se han modificado satisfactoriamente", "Error", JOptionPane.INFORMATION_MESSAGE);
						
		}
		}else if(arg0.getActionCommand().equals("Cancelar")){
					this.vbp.frmVerPacientes.setEnabled(true);
					ventanaControlada.frmModificarDatos.dispose();
				}
			
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowOpened(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowClosing(WindowEvent e) {
		vbp.frmVerPacientes.setEnabled(true);
		
	}

	@Override
	public void windowClosed(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowIconified(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowDeiconified(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowActivated(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowDeactivated(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}
}
