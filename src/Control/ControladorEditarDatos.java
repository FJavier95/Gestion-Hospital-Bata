package Control;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import Modelo.BBDD;
import Modelo.Paciente;
import Vista.VentanaAnadirTratamiento;
import Vista.VentanaBusquedaPaciente;
import Vista.VentanaEditarDatos;

public class ControladorEditarDatos implements ActionListener,WindowListener {
	VentanaEditarDatos ventanaControlada;
	VentanaBusquedaPaciente vbp;
	Paciente paciente ;
	public JFrame frmDialogo = null;
	
	public ControladorEditarDatos(VentanaEditarDatos ved , Paciente pac, VentanaBusquedaPaciente vbp){
		ventanaControlada=ved;
		paciente = pac;
		this.vbp = vbp;
		
	}
	
	public void actionPerformed(ActionEvent arg0) {
		Paciente paciente1;
		boolean aux= true;
		
		if(arg0.getSource().equals(ventanaControlada.btn_AP_Aceptar)){


			String nombre=ventanaControlada.LabelNombre.getText();
			//String fianza=ventanaControlada.lbl_AP_Fianza.getText();
			String apellido1=ventanaControlada.LabelApellido1.getText();
			String apellido2=ventanaControlada.LabelApellido2.getText();
			String direccion=ventanaControlada.LabelDireccion.getText();
			//String DNI=ventanaControlada.labelDNI.getText();
			String poblacion=ventanaControlada.lbl_AP_Poblacion.getText();
			//String N_historial=ventanaControlada.LabelN_Histoial.getText();
			//String sexo=ventanaControlada.cb_AP_Sexo.getSelectedItem().toString();
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
					JOptionPane.showMessageDialog((Component) frmDialogo, "El telefono es erroneo, debe contener numeros, no letras ni caracteres especiales.", "Error", JOptionPane.ERROR_MESSAGE);
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
					this.vbp.frame.setEnabled(true);
					vbp.controlador.setPac(paciente);
					vbp.panelDatosPaciente(paciente);
					ventanaControlada.frmEditarDatos.dispose();
		}
				}else if(arg0.getSource().equals(ventanaControlada.btn_AP_Cancelar)){
					this.vbp.frame.setEnabled(true);
					vbp.controlador.setPac(paciente);
					vbp.panelDatosPaciente(paciente);;
					ventanaControlada.frmEditarDatos.dispose();
				}
	
	}
			
	
	@Override
	public void windowActivated(WindowEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void windowClosed(WindowEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void windowClosing(WindowEvent arg0) {
		this.vbp.frame.setEnabled(true);
		vbp.controlador.setPac(paciente);
		vbp.panelDatosPaciente(paciente);
		
		
	}
	@Override
	public void windowDeactivated(WindowEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void windowDeiconified(WindowEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void windowIconified(WindowEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void windowOpened(WindowEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	
}
