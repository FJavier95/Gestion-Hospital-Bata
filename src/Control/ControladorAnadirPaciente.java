 package Control;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.sql.Timestamp;
import java.util.Vector;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import Modelo.BBDD;
import Modelo.Paciente;
import Vista.VentanaAdministradorModificarPaciente;
import Vista.VentanaAdministradorPacientes;
import Vista.VentanaAnadirPaciente;
import Vista.VentanaBusquedaPaciente;


public class ControladorAnadirPaciente implements ActionListener, WindowListener {

	VentanaAnadirPaciente ventanaControlada;
	VentanaBusquedaPaciente ventanaPacientes;
	public JFrame frmDialogo = null;
	VentanaBusquedaPaciente cbp;
	boolean aux=false;
	VentanaAdministradorPacientes cba;
	public ControladorAnadirPaciente(VentanaAnadirPaciente vap, VentanaBusquedaPaciente cbp){
		ventanaControlada=vap;
		this.cbp=cbp;
		aux=false;
	}
	public ControladorAnadirPaciente(VentanaAnadirPaciente vap, VentanaAdministradorPacientes cbp){
		ventanaControlada=vap;
		this.cba=cbp;
		aux=true;
	}
	public void actionPerformed(ActionEvent e) {
	
		if(e.getSource().equals(ventanaControlada.btn_AP_Aceptar)){
				int tlf1=0, tlf2=0, tlf3=0;
				int Nh=0;
				float fianz=0.0f;
				int n_as = 0;
				Paciente paciente1;
				String nombre=ventanaControlada.txt_AP_Nombre.getText();
				
				String apellido1=ventanaControlada.txt_AP_Apellido1.getText();
				String apellido2=ventanaControlada.txt_AP_Apellido2.getText();
				String direccion=ventanaControlada.txt_AP_Direccion.getText();
				String DNI=ventanaControlada.txt_AP_Dni.getText();
				String poblacion=ventanaControlada.txt_AP_Poblacion.getText();
				String N_historial=ventanaControlada.txt_AP_NHistorial.getText();
				String sexo=ventanaControlada.cb_AP_Sexo.getSelectedItem().toString();
				String Condicion=ventanaControlada.cb_AP_Condicion.getSelectedItem().toString();
				String telefono1=ventanaControlada.txt_AP_Telefono1.getText();
				String telefono2=ventanaControlada.txt_AP_Telefono2.getText();
				String telefono3=ventanaControlada.txt_AP_Telefono3.getText();
				String n_asegurado=ventanaControlada.txt_N_Asegurado.getText();
				boolean aux = true;
				
				
				
					
					if(nombre.isEmpty() || apellido1.isEmpty() || DNI.isEmpty() || direccion.isEmpty() || N_historial.isEmpty() || poblacion.isEmpty() ||  telefono1.isEmpty() || 
							 sexo.isEmpty()){
						aux = false;
						JOptionPane.showMessageDialog((Component) frmDialogo, "Faltan campos por rellenar.", "Error", JOptionPane.ERROR_MESSAGE);
					
					}else if(telefono1.length()>9 || telefono1.length()<9){
						aux = false;
						JOptionPane.showMessageDialog((Component) frmDialogo, "Numero de telefono no valido.", "Error", JOptionPane.ERROR_MESSAGE);
					
					} 
					
					else{
					try{
						Nh=Integer.parseInt(N_historial);
						tlf1 = Integer.parseInt(telefono1);
						//tlf2 = Integer.parseInt(telefono2);
				    	
				    	
				    	
					} catch(Exception a){
						aux = false;
							JOptionPane.showMessageDialog((Component) frmDialogo, "El telefono y el Numero de historial deben contener numeros, no letras ni caracteres especiales.", "Error", JOptionPane.ERROR_MESSAGE);
					}
					if (aux){
						if(!BBDD.esAlfaNumerica(direccion) && !BBDD.esAlfaNumerica(poblacion) && !BBDD.esAlfaNumerica(nombre)
								&& !BBDD.esAlfaNumerica(apellido1) && !BBDD.esAlfaNumerica(apellido2) && !BBDD.esAlfaNumerica(Condicion))
					{
					int dialogo = JOptionPane.showConfirmDialog(frmDialogo, "�Guardar Cambios?", "Guardar nuevo paciente", JOptionPane.YES_NO_OPTION);
					if (dialogo == JOptionPane.YES_OPTION){
					
					
						try {
							
							if(telefono3==""){
							tlf3 = Integer.parseInt(telefono3);
							
							}
							if(telefono2==""){
								tlf2 = Integer.parseInt(telefono2);
							}
							String letra;
						       int dni;
						       String[] parts = DNI.split("[A-z]");
						       dni=Integer.parseInt(parts[0]);
						       parts=DNI.split("[0-9]");
						       int comp=parts.length-1;
						       String[] asegurado= n_asegurado.split(" ");
						       paciente1 = new Paciente(Nh,nombre,apellido1,apellido2,getCondiciontoInt(Condicion),tlf1,tlf2,tlf3,
						          poblacion, direccion,sexotoInt(sexo),dni,asegurado[0]);
						       if(comp<0){
						        paciente1.setLetraDni(" ");
						       }else {
						        letra=parts[comp];
						        paciente1.setLetraDni(letra);
						        
						       }
						       if(!BBDD.comprobarPaciente(paciente1)){
						       BBDD.anadirPaciente(paciente1);
						       cbp.frame.setEnabled(true);
						       ventanaControlada.frmAnadirPaciente.dispose();
						       }else JOptionPane.showMessageDialog((Component) frmDialogo, "El Paciente introducido ya existe en la base de dato (DIP o Numero Asegurado o Numero de Historial coinciden)", "Error", JOptionPane.ERROR_MESSAGE);
						       
						       } catch (Exception e1) {
						        // TODO Auto-generated catch block
						        e1.printStackTrace();
						       }
						
						
					}
					}else JOptionPane.showMessageDialog((Component) frmDialogo, "El nombre, los apellido, la direccion y la poblacion deben contener solo letras no numeros", "Error", JOptionPane.ERROR_MESSAGE);
					}	
					}
			}else if(e.getSource().equals(ventanaControlada.btn_AP_Cancelar)){
				if(aux){
					cba.frmVerPacientes.setEnabled(true);
				}else{
				cbp.frame.setEnabled(true);
				}
				ventanaControlada.frmAnadirPaciente.dispose();
			
				}
		}
  
	
	public int sexotoInt(String sex){// MUJER=0, HOMBRE=1
		int sexo=-1;
		if(sex.equals("Mujer")){
			sexo=0;
		}else if(sex.equals("Hombre")){
			sexo=1;
		}
		return sexo;
	}
	public int getCondiciontoInt(String Con) {//Asegurado=1, NO-Asegurado=2, Jubilado=3, Pensionista=4, Embarazada=5, Discapacitado=6
		int Condicion = -1;
	
		if(Con.equals("Asegurado")){
			Condicion=1;
		}else if(Con.equals("NO-Asegurado")){
			Condicion=2;
		}else if(Con.equals("Jubilado")){
				Condicion=3;
			}else if(Con.equals("Pensionista")){
				Condicion=4;
			}else if(Con.equals("Discapacitado")){
				Condicion=5;
			}/*else if(Con.equals("Discapacitado")){
				Condicion=6;
			}*/
		
		return Condicion;
	}
	@Override
	public void windowActivated(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void windowClosed(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void windowClosing(WindowEvent e) {
		if(aux){
			cba.frmVerPacientes.setEnabled(true);
		}else{
		cbp.frame.setEnabled(true);
		}
		
	}
	@Override
	public void windowDeactivated(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void windowDeiconified(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void windowIconified(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void windowOpened(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}
}
	
	
	


