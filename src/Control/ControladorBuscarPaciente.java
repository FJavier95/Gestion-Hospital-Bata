package Control;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.Timestamp;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Vector;

import javax.swing.JFrame;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import com.toedter.calendar.JDateChooser;

import Modelo.BBDD;
import Modelo.Fecha_Paciente;
import Modelo.Paciente;
import Vista.VentanaAnadirPaciente;
import Vista.VentanaAnadirTratamiento;
import Vista.VentanaBusquedaPaciente;
import Vista.VentanaEditarDatos;
import Vista.VentanaLogin;
import Vista.VentanaVerTratamientos;

public class ControladorBuscarPaciente implements ActionListener, MouseListener {

	VentanaBusquedaPaciente ventanaControlada;
	public JFrame frmDialogo = null;
	static Paciente pac;
	public static Paciente getPac() {
		return pac;
	}
	public void setPac(Paciente paci) {
		pac = paci;
	}
	boolean DatosPaciente=false;
	int id_usuario=0;
	 int id_fecha_pac=0;
	public ControladorBuscarPaciente(VentanaBusquedaPaciente vbp , int id_usuario ){
		ventanaControlada=vbp;
		this.id_usuario=id_usuario;
	}
	public void actionPerformed(ActionEvent e) {
		if(e.getSource().equals(ventanaControlada.Jbuttonbusqueda)){
			String busqueda=ventanaControlada.textFieldbus.getText();
			Vector<Paciente>V_pac=BBDD.busqueda(busqueda);
			if(V_pac.size()!=0){
			try {
				ventanaControlada.crearModeloTabla( V_pac);
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			}else JOptionPane.showMessageDialog((Component) frmDialogo, "Ningun usuario con los parametros de busqueda introducidos.  "+busqueda+" ", "Error", JOptionPane.ERROR_MESSAGE);
		}else if (e.getSource().equals(ventanaControlada.mntmNewMenuItem)){
			ventanaControlada.frame.setEnabled(false);
			VentanaAnadirPaciente vp= new VentanaAnadirPaciente();
			ControladorAnadirPaciente cp= new ControladorAnadirPaciente(vp,ventanaControlada);
			vp.addController(cp);
			vp.crearVentana();
			
		}else if(e.getSource().equals(ventanaControlada.mntmNewMenuItem_1)){
			ventanaControlada.frame.setVisible(false);
			  VentanaLogin mainframe=new VentanaLogin();
              
			  ControladorLogin mc=new ControladorLogin(mainframe);
			  mainframe.addController(mc);            
			  mainframe.crearVentana();
			
		}else if(e.getActionCommand().equals("+ Tratamientos")){

			boolean aux = BBDD.IngresoActivo(pac);
			if(aux==false){// No tiene fecha de alta 
				ventanaControlada.frame.setEnabled(false);
				VentanaAnadirTratamiento vp= new VentanaAnadirTratamiento();
				ControladorAnadirTratamiento cp= new ControladorAnadirTratamiento(vp,pac,ventanaControlada,id_usuario);
				vp.addController(cp);
				vp.crearTratamiento();
				
			}else {
				int n = JOptionPane.showConfirmDialog((Component) frmDialogo ,"El paciente "+pac.getNombre()+" "+pac.getApellido1()+" "+ pac.getApellido2()+" \n No esta ingresado en el Hospital \n �Quiere ingresarle? " ,"Confirmacion", JOptionPane.YES_NO_OPTION);
			     if(n==JOptionPane.YES_OPTION)
			      {
			    	
			    	 JDateChooser jd = new JDateChooser();
			    	 String message ="Fecha de Ingreso:\n";
			    	 Object[] params = {message,jd};
			    	 JOptionPane.showConfirmDialog(null,params,"Fecha ingreso", JOptionPane.PLAIN_MESSAGE);
			    	 if(((JDateChooser) params[1]).getDate()!=null){
			    	 String s="";
			    	 SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			    	 s=sdf.format(((JDateChooser)params[1]).getDate());
			    	Fecha_Paciente fp = new Fecha_Paciente(Timestamp.valueOf("2999-12-31 00:00:00"), Timestamp.valueOf(s) );
			    	int fianza = 0;
		    		String cantidad=JOptionPane.showInputDialog((Component) frmDialogo, "Introduzca la fianza para realizar el ingreso");
					boolean compfianza=true;
		    		try {
		    			fianza=Integer.parseInt(cantidad);
					} catch (NumberFormatException nfe){
						
						JOptionPane.showMessageDialog((Component) frmDialogo, "La cantidad introducidad debe ser numerica", "Error", JOptionPane.ERROR_MESSAGE);
					compfianza=false;
					}
		    		if(compfianza){
			    	boolean compIngreso = BBDD.IngresarPaciente(pac, fp,id_usuario);
			    	id_fecha_pac=BBDD.obtenerIdUltimaFechaPaciente(pac);
			    	Timestamp fech=new Timestamp(System.currentTimeMillis());
			    	boolean boolFianza=BBDD.IngresarFianza(id_fecha_pac,fianza,fech,id_usuario);
			    	if(compIngreso && boolFianza){
			    		
			       JOptionPane.showMessageDialog(null, "Paciente ingresado correctamente");
			    	}
			    	 }else JOptionPane.showMessageDialog(null, "Error al introducir la fianza");
			      }else JOptionPane.showMessageDialog(null, "Fecha erronea");
				}
			}
		}else if (e.getActionCommand().equals("Ver Tratamientos")){
			ventanaControlada.frame.setEnabled(false);
			VentanaVerTratamientos vp= new VentanaVerTratamientos();
			ControladorVerTratamiento cp= new ControladorVerTratamiento(vp, pac,ventanaControlada);
			vp.addController(cp);
			vp.crearVistaTratamiento(pac);
		}else if(e.getActionCommand().equals("Editar Datos")){
		      ventanaControlada.frame.setEnabled(false);
		      VentanaEditarDatos vp= new VentanaEditarDatos();
		      ControladorEditarDatos cp= new ControladorEditarDatos(vp, pac,ventanaControlada);
		      vp.addController(cp);
		      vp.crearVentana(pac);
		    }else if(e.getActionCommand().equals("Modificar Fianza")){
		    	id_fecha_pac=BBDD.obtenerIdUltimaFechaPaciente(pac);
		    	if(id_fecha_pac==-1){
		    		JOptionPane.showMessageDialog(null, "El Paciente no esta ingresado");
		           }else{
		        	   String cantidad=JOptionPane.showInputDialog((Component) frmDialogo, "Introduzca el nuevo pago: ");
		        	   boolean aux=false;
		        	   int cant = 0;
		   			try {
		   				cant=Integer.parseInt(cantidad);
		   			} catch (NumberFormatException nfe){
		   				
		   				JOptionPane.showMessageDialog((Component) frmDialogo, "La cantidad introducidad debe ser numerica", "Error", JOptionPane.ERROR_MESSAGE);
		   			aux =true;
		   			}
		   			if(cant>0){
		   			if (!aux){
		   				NumberFormat nf=NumberFormat.getInstance();
		   				int n = JOptionPane.showConfirmDialog((Component) frmDialogo , "Quiere introducir "+String.valueOf(nf.format(cant)),"Confirmacion", JOptionPane.YES_NO_OPTION);
					     if(n==JOptionPane.YES_OPTION)
					      {Timestamp fech=new Timestamp(System.currentTimeMillis());
					    	 boolean boolFianza=BBDD.IngresarFianza(id_fecha_pac,cant,fech,id_usuario);
						    	if( boolFianza){
						    		
						       JOptionPane.showMessageDialog(null, "Fianza ingresado correctamente");
						    	}
						    	 else JOptionPane.showMessageDialog(null, "Error al introducir la fianza");
					      }
		   			}
		           }else JOptionPane.showMessageDialog(null, "Error la fianza no puede ser negativa");
		    }
		    }
		
	}
	

	public void mouseClicked(MouseEvent e) {
			if(e.getSource() instanceof JTable){
				
				
            JTable target = (JTable) e.getSource();
            int row = target.getSelectedRow();
            DefaultTableModel tableModel =(DefaultTableModel) target.getModel();
            Vector<Object> elementAt = (Vector<Object>) tableModel.getDataVector().elementAt(row);
   
          int dni= (int) elementAt.get(0); 
           pac=BBDD.prueba(dni);
            id_fecha_pac=BBDD.obtenerIdUltimaFechaPaciente(pac);
           int fianza=0;
           if(id_fecha_pac==-1){
        	   
           }else fianza=BBDD.obtenerFianzatotal(id_fecha_pac);
          
          pac.setFianza(fianza);
          if(pac.getActividad()>0){
         ventanaControlada.panelBotonesIngresado();
         // }else 
        
         ventanaControlada.panelDatosPaciente(pac);
         if(DatosPaciente==true){
        	 ventanaControlada.remove(ventanaControlada.panel_datos_paciente);
				
			}DatosPaciente=true;
			}else JOptionPane.showMessageDialog(null, "Paciente no Activo en la aplicacion consulte con el administrador");
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
