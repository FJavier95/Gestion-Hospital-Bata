package Control;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.util.Vector;

import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import Modelo.BBDD;
import Modelo.Concepto;
import Modelo.Paciente;
import Modelo.Usuario;
import Vista.VentanaAnadirTratamiento;
import Vista.VentanaAnadirUsuario;
import Vista.VentanaBusquedaPaciente;

public class ControladorAnadirUsuario implements WindowListener, MouseListener, ItemListener,ActionListener {

	VentanaAnadirUsuario ventanaControlada;
	boolean usuariosAorD=true;//Precios activos = true precios inactivos = false;
	public ControladorAnadirUsuario(VentanaAnadirUsuario vbp){
		ventanaControlada=vbp;
		
	}
	@Override
	public void windowOpened(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowClosing(WindowEvent e) {
		ventanaControlada.vadmin.frame.setEnabled(true);
		
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

	@Override
	public void mouseClicked(MouseEvent arg0) {
		if(arg0.getComponent().equals(ventanaControlada.tablaTipos)){
			JTable target = (JTable) arg0.getSource();
            int row = target.getSelectedRow();
         
            DefaultTableModel tableModel =(DefaultTableModel) target.getModel();
            Vector<Object> elementAt = (Vector<Object>) tableModel.getDataVector().elementAt(row);
            if(usuariosAorD){
            	
            		int n = JOptionPane.showConfirmDialog((Component) ventanaControlada.frmAnadirConcepto , "Quiere Desactivar el usuario: "+elementAt.get(1),"Confirmacion", JOptionPane.YES_NO_OPTION);
       		     if(n==JOptionPane.YES_OPTION)
       		      {
       		    	boolean modificado=BBDD.modificarActividadUsario((int)elementAt.get(0), 0);
       		    	if(modificado){	
       		    	JOptionPane.showMessageDialog(null, "Modificacion correcta");
       		    		
       		    	}else JOptionPane.showMessageDialog(null, "Modificacion Erronea");
       		    	 }
       		      
       		      }else{
       		    	int n = JOptionPane.showConfirmDialog((Component) ventanaControlada.frmAnadirConcepto , "Quiere Activar el usuario: "+elementAt.get(1),"Confirmacion", JOptionPane.YES_NO_OPTION);
          		     if(n==JOptionPane.YES_OPTION)
          		      {
          		    	
          		    	boolean modificado=BBDD.modificarActividadUsario((int)elementAt.get(0), 1);
           		    	if(modificado){	
           		    	JOptionPane.showMessageDialog(null, "Modificacion correcta");
           		    		
           		    	}else JOptionPane.showMessageDialog(null, "Modificacion Erronea");
           		   }
          		      
       		      }
            }
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
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void itemStateChanged(ItemEvent e) {
		if(e.getSource().equals(ventanaControlada.chec_Activos)){
			if(ventanaControlada.chec_Activos.getState()==true){
				usuariosAorD=true;
			}else usuariosAorD=false;
			 if(usuariosAorD==true){//Precios Activos
		            
		            	Vector<Usuario>v_conceptos=BBDD.UsuariosAdministracion(1);
		            try {
						ventanaControlada.crearModeloTablaPruebas(v_conceptos);
					} catch (Exception a) {
						// TODO Auto-generated catch block
						a.printStackTrace();
					}
		            
			 }else {
					Vector<Usuario>v_conceptos=BBDD.UsuariosAdministracion(0);
		            try {
						ventanaControlada.crearModeloTablaPruebas(v_conceptos);
					} catch (Exception a) {
						// TODO Auto-generated catch block
						a.printStackTrace();
					}
			 }
		}
		
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getActionCommand().equals("+ Usuario")){
			
			String nombre_Usuario=JOptionPane.showInputDialog((Component) ventanaControlada.frmAnadirConcepto, "Introduzca el Nombre de Usuario ");
			String pasword_Usuario=JOptionPane.showInputDialog((Component) ventanaControlada.frmAnadirConcepto, "Introduzca el la Password ");
			boolean compusuario=BBDD.comprobarUsuarioActivo(nombre_Usuario);
			if(!compusuario){
				boolean modificado=BBDD.insertarUsuarioActivo(nombre_Usuario, pasword_Usuario);
				if(modificado){
					JOptionPane.showMessageDialog(null, "Modificacion correcta");
				}else JOptionPane.showMessageDialog(null, "Modificacion erronea");
			}else JOptionPane.showMessageDialog(null, "Usuario ya registrado");
		}
	}
	
}
