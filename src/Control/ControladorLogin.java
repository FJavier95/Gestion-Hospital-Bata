package Control;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JOptionPane;

import Modelo.BBDD;
import Vista.VentanaAbout;
import Vista.VentanaAdministrador;
import Vista.VentanaBusquedaPaciente;
import Vista.VentanaLogin;

public class ControladorLogin implements KeyListener, ActionListener{
	
	private VentanaLogin ventanaControlada;
	
	public ControladorLogin(VentanaLogin ventana){
		this.ventanaControlada=ventana;
	
	}
	public static void main(String[]args){
		  VentanaLogin mainframe=new VentanaLogin();
		                
		  ControladorLogin mc=new ControladorLogin(mainframe);
		  mainframe.addController(mc);            
		  mainframe.crearVentana();
		 }
	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		if(e.getKeyCode() == KeyEvent.VK_ENTER) {
			String usuario = ventanaControlada.txt_usuario.getText();	//recoge el contenido del JTextField
			char caracteres[] = ventanaControlada.txt_password.getPassword();	//array de caracteres que coge los elementos que se encuentran en el JPasswordField
			
			String password = String.valueOf(caracteres);
			if(!usuario.equals("")){
				if(!password.equals("")){
					int comp[]=BBDD.comprobarLogin(usuario, password);
					switch(comp[0]){
					case 0:
					
					VentanaBusquedaPaciente mainframe=new VentanaBusquedaPaciente();
		            
					  ControladorBuscarPaciente mc=new ControladorBuscarPaciente(mainframe,comp[1]);
					  mainframe.addController(mc);            
					  mainframe.crearVentana();
					  ventanaControlada.frmIndex.setVisible(false);
					  break;
					case 1 :VentanaAdministrador ventaAdmin=new VentanaAdministrador();
				      ControladorAdministrador controAdmin = new ControladorAdministrador(ventaAdmin);
				      ventaAdmin.addController(controAdmin);
				      ventaAdmin.crearVentana();      
				      ventanaControlada.frmIndex.setVisible(false);
					
						break;
					case -1 :JOptionPane.showMessageDialog(null, "Usuario o contrsena no validos");
						break;
					}
				}else JOptionPane.showMessageDialog(null, "Faltan campos por reyenar");
			}else JOptionPane.showMessageDialog(null, "Faltan campos por reyenar");
			
		} 
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource().equals(ventanaControlada.btn_Aceptar)){
			
			String usuario = ventanaControlada.txt_usuario.getText();	//recoge el contenido del JTextField
			char caracteres[] = ventanaControlada.txt_password.getPassword();	//array de caracteres que coge los elementos que se encuentran en el JPasswordField
			
			String password = String.valueOf(caracteres);
			
			if(!usuario.equals("")){
				if(!password.equals("")){
			int comp[]=BBDD.comprobarLogin(usuario, password);
			switch(comp[0]){
			case 0:
			
			VentanaBusquedaPaciente mainframe=new VentanaBusquedaPaciente();
            
			  ControladorBuscarPaciente mc=new ControladorBuscarPaciente(mainframe,comp[1]);
			  mainframe.addController(mc);            
			  mainframe.crearVentana();
			  ventanaControlada.frmIndex.setVisible(false);
			  break;
			case 1 :VentanaAdministrador ventaAdmin=new VentanaAdministrador();
		      ControladorAdministrador controAdmin = new ControladorAdministrador(ventaAdmin);
		      ventaAdmin.addController(controAdmin);
		      ventaAdmin.crearVentana();      
		      ventanaControlada.frmIndex.setVisible(false);
			
				break;
			case -1 :JOptionPane.showMessageDialog(null, "Usuario o contrseña no validos");
				break;
			}
			 
		}else JOptionPane.showMessageDialog(null, "Faltan campos por reyenar");
			}else JOptionPane.showMessageDialog(null, "Faltan campos por reyenar");
		}else if(e.getSource().equals(ventanaControlada.btn_About)){
			ControladorAbout ca = null;
			VentanaAbout va=new VentanaAbout();
			va.addController(ca);
			va.crearVentana();
		}
		
	}

}
