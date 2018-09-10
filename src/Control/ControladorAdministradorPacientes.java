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
import Modelo.Paciente;
import Vista.VentanaAdministrador;
import Vista.VentanaAdministradorModificarPaciente;
import Vista.VentanaAdministradorPacientes;
import Vista.VentanaAnadirPaciente;
import Vista.VentanaVerTratamientos;

public class ControladorAdministradorPacientes implements ActionListener, WindowListener, ItemListener, MouseListener {
	VentanaAdministradorPacientes ventanaControlada;
	VentanaAdministrador vadmin;
	boolean activos =true;
	
	public ControladorAdministradorPacientes(VentanaAdministradorPacientes vbp,VentanaAdministrador vadmin){
		ventanaControlada=vbp;
		this.vadmin=vadmin;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource().equals(ventanaControlada.btn_addPaciente)){
			ventanaControlada.frmVerPacientes.setEnabled(false);
			VentanaAnadirPaciente vp= new VentanaAnadirPaciente();
			ControladorAnadirPaciente cp= new ControladorAnadirPaciente(vp,ventanaControlada);
			vp.addController(cp);
			vp.crearVentana();
		}
		
	}

	@Override
	public void windowOpened(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowClosing(WindowEvent e) {
		vadmin.frame.setEnabled(true);
		
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
	public void itemStateChanged(ItemEvent e) {
		if(e.getSource().equals(ventanaControlada.chec_Activos)){
			if(ventanaControlada.chec_Activos.getState()==true){
				activos=true;
			}else activos=false;
			 if(activos==true){
				 try {
					ventanaControlada.crearModeloTablaPaciente(BBDD.obtenerPacientes(1));
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				 } else
				try {
					ventanaControlada.crearModeloTablaPaciente(BBDD.obtenerPacientes(0));
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			 }
		
	}

	@Override
	public void mouseClicked(MouseEvent arg0) {
		JTable target = (JTable) arg0.getSource();
		 
        int row = target.getSelectedRow();
        int[] column=target.getSelectedColumns();
        DefaultTableModel tableModel =(DefaultTableModel) target.getModel();
        Vector<Object> elementAt = (Vector<Object>) tableModel.getDataVector().elementAt(row);
        Paciente pac=BBDD.prueba((int) elementAt.get(3));
        	if(column[0]==5){
        		if(activos){
        		int n = JOptionPane.showConfirmDialog((Component) vadmin , "Quiere Desactivar el paciente "+elementAt.get(0)+" "+elementAt.get(1)+" "+elementAt.get(2),"Confirmacion", JOptionPane.YES_NO_OPTION);
   		     if(n==JOptionPane.YES_OPTION)
   		      {
   		    	 
   		    	boolean modificacion=BBDD.modificarPacienteInt("Actividad" ,  pac,  0);
   		    	if(modificacion){
   		    		JOptionPane.showMessageDialog(null, "Modificacion Correcta");
   		    	}else JOptionPane.showMessageDialog(null, "Modificacion Erronea");
   		    	 }
   		      }else {
   		    	  int n = JOptionPane.showConfirmDialog((Component) vadmin , "Quiere Activar el paciente "+elementAt.get(0)+" "+elementAt.get(1)+" "+elementAt.get(2),"Confirmacion", JOptionPane.YES_NO_OPTION);
    		     if(n==JOptionPane.YES_OPTION)
      		      {
    		    	 
    	   		    	boolean modificacion=BBDD.modificarPacienteInt("Actividad" ,  pac,  1);
    	   		    	if(modificacion){
    	   		    		JOptionPane.showMessageDialog(null, "Modificacion Correcta");
    	   		    	}else JOptionPane.showMessageDialog(null, "Modificacion Erronea");
      		    	 }
   		    	  
   		      }
        		
        		 
        	}else if(column[0]==0 || column[0]==1) {
        		ventanaControlada.frmVerPacientes.setEnabled(false);
        		int id_fecha_pac=BBDD.obtenerIdUltimaFechaPaciente(pac);
                int fianza=0;
                if(id_fecha_pac==-1){
                  
                }else fianza=BBDD.obtenerFianzatotal(id_fecha_pac);
               
               pac.setFianza(fianza);
              
               VentanaAdministradorModificarPaciente vpaciente=new VentanaAdministradorModificarPaciente();
               ControladorAdministradorModificarPacientes capac=new ControladorAdministradorModificarPacientes(vpaciente,pac,ventanaControlada);
               vpaciente.addController(capac);
                vpaciente.crearVentana(pac);
        	
        }else{
        	ventanaControlada.frmVerPacientes.setEnabled(false);
			VentanaVerTratamientos vp= new VentanaVerTratamientos();
			ControladorVerTratamiento cp= new ControladorVerTratamiento(vp, pac,ventanaControlada);
			vp.addController(cp);
			vp.crearVistaTratamiento(pac);
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
	
}
