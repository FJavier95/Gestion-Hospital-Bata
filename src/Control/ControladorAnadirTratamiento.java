package Control;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.math.BigInteger;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Vector;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;


import com.toedter.calendar.JDateChooser;

import Modelo.BBDD;
import Modelo.Concepto;
import Modelo.Paciente;
import Modelo.Tratamiento;
import Vista.VentanaAnadirTratamiento;
import Vista.VentanaBusquedaPaciente;






public class ControladorAnadirTratamiento implements ActionListener , MouseListener, WindowListener{
	
	VentanaAnadirTratamiento ventanaControlada;
	public JFrame frmDialogo = null;
	Paciente pac;
	String tabla_bbdd;
	int tipoPrueba;
	int cant;
	VentanaBusquedaPaciente cbp;
	int id_usuario;
	public ControladorAnadirTratamiento(VentanaAnadirTratamiento vbp, Paciente pac,VentanaBusquedaPaciente cbp,int id_usuario ){
		ventanaControlada=vbp;
		this.pac=pac;
		this.cbp=cbp;
		this.id_usuario=id_usuario;
	}
	@Override
	public void actionPerformed(ActionEvent arg0) {
		if(arg0.getSource().equals(ventanaControlada.btn_Dar_Alta)){
			
			int n = JOptionPane.showConfirmDialog((Component) frmDialogo , "Quiere dar de alta al paciente"+pac.getNombre(),"Confirmacion", JOptionPane.YES_NO_OPTION);
		     if(n==JOptionPane.YES_OPTION)
		      {
		    	 JDateChooser jd = new JDateChooser();
		    	 String message ="Fecha de Alta:\n";
		    	 Object[] params = {message,jd};
		    	 JOptionPane.showConfirmDialog(null,params,"Fecha alta", JOptionPane.PLAIN_MESSAGE);
		    	 if(((JDateChooser) params[1]).getDate()!=null){
		    	 String s="";
		    	 SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		    	 s=sdf.format(((JDateChooser)params[1]).getDate()); 
		    	 int idFechaPaciente = BBDD.obtenerIdUltimaFechaPaciente(pac);
		    	 if (idFechaPaciente > 0){
		    		 boolean aux1 = BBDD.modificarFecha_AltaPaciente(idFechaPaciente, Timestamp.valueOf(s), id_usuario);
		    		 JOptionPane.showMessageDialog((Component) frmDialogo, "El Paciente ha sido dado de alta correctamente", "Error", JOptionPane.ERROR_MESSAGE);
		    		 cbp.frame.setEnabled(true);

		    		 ventanaControlada.frmAnadirTratamiento.dispose();
		    		
		    		
		    	 }else JOptionPane.showMessageDialog((Component) frmDialogo, "Error consultar al administrador por dicho paciente", "Error", JOptionPane.ERROR_MESSAGE);
		      }
		      }
		}
		
	}
	@Override
	public void mouseClicked(MouseEvent arg0) {
		
		if(arg0.getSource() instanceof JTable){
			if(arg0.getComponent().equals(ventanaControlada.tablaTipos)){
            JTable target = (JTable) arg0.getSource();
            int row = target.getSelectedRow();
            DefaultTableModel tableModel =(DefaultTableModel) target.getModel();
            Vector<Object> elementAt = (Vector<Object>) tableModel.getDataVector().elementAt(row);
             tabla_bbdd=(String) elementAt.get(1);
             tipoPrueba=(int) elementAt.get(0);
             
            if(tipoPrueba<9){
            	if(tipoPrueba==1){
            		if(pac.getSexo()==1){
            			JOptionPane.showMessageDialog((Component) frmDialogo, "El paciente es un hombre y no puede recibir tratamientos de Embarazada", "Error", JOptionPane.ERROR_MESSAGE);
            		}else {
            			Vector<Concepto>v_conceptos=BBDD.obtener_ConceptosActivos(tabla_bbdd);
                        try {
            				ventanaControlada.crearModeloTablaConcepto(v_conceptos);
            			} catch (Exception e) {
            				// TODO Auto-generated catch block
            				e.printStackTrace();
            			}
                        	}
            		
            	}else {
            	Vector<Concepto>v_conceptos=BBDD.obtener_ConceptosActivos(tabla_bbdd);
            try {
				ventanaControlada.crearModeloTablaConcepto(v_conceptos);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
            	}
            }else if(tipoPrueba==9){
            	Vector<Concepto>v_conceptos=BBDD.obtener_MedicamentosActivos();
            	try {
    				ventanaControlada.crearModeloTablaMedicamentos(v_conceptos);
    			} catch (Exception e) {
    				// TODO Auto-generated catch block
    				e.printStackTrace();
    			}
            }else if(tipoPrueba==10){
            	Vector<Concepto>v_conceptos=BBDD.obtener_ConceptosActivos(tabla_bbdd);
                try {
    				ventanaControlada.crearModeloTablaConcepto(v_conceptos);
    			} catch (Exception e) {
    				// TODO Auto-generated catch block
    				e.printStackTrace();
    			}
            }
            /*}else if(tipoPrueba==10){
            	Vector<Concepto>v_conceptos=BBDD.obtener_FungiblesActivos();
            	try {
    				ventanaControlada.crearModeloTablaFungibles(v_conceptos);
    			} catch (Exception e) {
    				// TODO Auto-generated catch block
    				e.printStackTrace();
    			}
            }*/
		}else if(arg0.getComponent().equals(ventanaControlada.tablaConceptos)){
			JTable target = (JTable) arg0.getSource();
            int row = target.getSelectedRow();
            String stNumF ;
            DefaultTableModel tableModel =(DefaultTableModel) target.getModel();
            Vector<Object> elementAt = (Vector<Object>) tableModel.getDataVector().elementAt(row);
            String cantidad=JOptionPane.showInputDialog((Component) frmDialogo, "Descripcion: "+elementAt.get(1)+", Precio: "+elementAt.get(2)+"\n Cantidad: ");
			boolean aux=false;
			try {
				cant=Integer.parseInt(cantidad);
			} catch (NumberFormatException nfe){
				
				JOptionPane.showMessageDialog((Component) frmDialogo, "La cantidad introducidad debe ser numerica", "Error", JOptionPane.ERROR_MESSAGE);
			aux =true;
			}
			if (!aux){
				
				stNumF = elementAt.get(2).toString();
				String palabras = stNumF.replace(".", "");
			
				int n = JOptionPane.showConfirmDialog((Component) frmDialogo , "Descripcion: "+elementAt.get(1)+", Precio: "+elementAt.get(2)+"\n Cantidad: "+cant+ " Precio Total: "+((Integer.parseInt(palabras))*cant),"Confirmacion", JOptionPane.YES_NO_OPTION);
				     if(n==JOptionPane.YES_OPTION)
				      {
				    	 JDateChooser jd = new JDateChooser();
				    	 String message ="Fecha del Tratamiento:\n";
				    	 Object[] params = {message,jd};
				    	 JOptionPane.showConfirmDialog(null,params,"Fecha Tratamiento", JOptionPane.PLAIN_MESSAGE);
				    	 if(((JDateChooser) params[1]).getDate()!=null){
				    	 String s="";
				    	 SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				    	 s=sdf.format(((JDateChooser)params[1]).getDate());
				    	 int t = JOptionPane.showConfirmDialog((Component) frmDialogo , "�Anadir tratamiento?","Confirmacion", JOptionPane.YES_NO_OPTION);
					     if(t==JOptionPane.YES_OPTION)
					      {
					    	 int id_precio=BBDD.ObtenerIDPrecio(tipoPrueba, (int)elementAt.get(0), s);
					    	 if(id_precio>=0){
					    	 
				    	 boolean compAddTratamiento = BBDD.AnadirTratamiento(pac, cant, tipoPrueba, Timestamp.valueOf(s),  (int)elementAt.get(0),id_usuario,id_precio);
				    	 if(compAddTratamiento){

				       JOptionPane.showMessageDialog(null, "Tratamiento Creado");
				    	 }else JOptionPane.showMessageDialog(null, "Error al crear el tratamiento ");
				       }else JOptionPane.showMessageDialog(null, "Precio No encontrado");
 
				    	 }
				      }else JOptionPane.showMessageDialog(null, "Fecha no selecionada");
				      }
				   
			}
		}
		}
	}
	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void windowActivated(WindowEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void windowClosed(WindowEvent arg0) {
		
		
	}
	@Override
	public void windowClosing(WindowEvent arg0) {
		cbp.frame.setEnabled(true);
		cbp.panelDatosPaciente(pac);
		
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