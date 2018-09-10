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

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import Modelo.BBDD;
import Modelo.Concepto;
import Modelo.Paciente;
import Vista.VentanaAdministrador;
import Vista.VentanaAdministradorAnadirConcepto;
import Vista.VentanaAdministradorPrecios;
import Vista.VentanaAnadirUsuario;
import Vista.VentanaAnadirTratamiento;

public class ControladorAdministradorPrecios implements ActionListener, MouseListener, ItemListener, WindowListener {
	
	VentanaAdministradorPrecios ventanaControlada;
	public JFrame frmDialogo = null;
	
	String tabla_bbdd;
	int tipoPrueba;
	int cant;
	boolean preciosAorD=true;//Precios activos = true precios inactivos = false;
	VentanaAdministrador vadmin;
	public ControladorAdministradorPrecios(VentanaAdministradorPrecios vbp, Paciente pac, VentanaAdministrador vadmin){
		ventanaControlada=vbp;
		
		this.vadmin=vadmin;
	}
	@Override
	public void actionPerformed(ActionEvent arg0) {
		if(arg0.getSource().equals(ventanaControlada.btn_addConcepto)){
			ventanaControlada.frmAnadirTratamiento.setEnabled(false);
			VentanaAdministradorAnadirConcepto vp=new VentanaAdministradorAnadirConcepto();
			ControladorAdministradorAnadirConcepto cp= new ControladorAdministradorAnadirConcepto(vp,ventanaControlada,tabla_bbdd,tipoPrueba);
			vp.addController(cp,tabla_bbdd,tipoPrueba);
			vp.crearVentana();
			
		}
			
		}
		
	
	
	
	public void mouseClicked(MouseEvent arg0) {
		
		if(arg0.getSource() instanceof JTable){
			if(arg0.getComponent().equals(ventanaControlada.tablaTipos)){
            JTable target = (JTable) arg0.getSource();
            int row = target.getSelectedRow();
           
            DefaultTableModel tableModel =(DefaultTableModel) target.getModel();
            Vector<Object> elementAt = (Vector<Object>) tableModel.getDataVector().elementAt(row);
             tabla_bbdd=(String) elementAt.get(1);
             tipoPrueba=(int) elementAt.get(0);
             ventanaControlada.btn_addConcepto.setVisible(true);
             ventanaControlada.chec_Activos.setVisible(true);
           
		            if(tipoPrueba<9){
		            	Vector<Concepto>v_conceptos=BBDD.obtener_ConceptosActivos(tabla_bbdd);
		            try {
						ventanaControlada.crearModeloTablaConcepto(v_conceptos);
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
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
             
		}else if(arg0.getComponent().equals(ventanaControlada.tablaConceptos)){
			JTable target = (JTable) arg0.getSource();
            int row = target.getSelectedRow();
            int[] column=target.getSelectedColumns();
            DefaultTableModel tableModel =(DefaultTableModel) target.getModel();
            Vector<Object> elementAt = (Vector<Object>) tableModel.getDataVector().elementAt(row);
            if(preciosAorD){
            	if(column[0]==2){
            		int n = JOptionPane.showConfirmDialog((Component) frmDialogo , "Quiere Modificar el precio de Descripcion: "+elementAt.get(1),"Confirmacion", JOptionPane.YES_NO_OPTION);
       		     if(n==JOptionPane.YES_OPTION)
       		      {
       		    	int precio = 0;
       		    	 boolean modificado=BBDD.modificarPreciosAntiguo(tipoPrueba, (int)elementAt.get(0));
       		    	 if(modificado){
       		    		String precioS=JOptionPane.showInputDialog((Component) frmDialogo, "Introduzca el nuevo precio ");
       		    		boolean aux=true;
       		    		try{
       		    		 precio=Integer.parseInt(precioS);
       		    		}catch (Exception e){
       		    			aux=false;
       		    		}
       		    		if(aux){
       		    		 boolean comp=BBDD.IngresarPrecio(tipoPrueba, (int)elementAt.get(0), precio);
       		    		JOptionPane.showMessageDialog(null, "Modificacion correcta");
       		    		}else JOptionPane.showMessageDialog(null, "El Precio introducido no es numerico");
       		       
       		    	 }else JOptionPane.showMessageDialog(null, "Modificacion Erronea");
       		      }
            		
            	
            	}else {
            	int a = JOptionPane.showConfirmDialog((Component) frmDialogo , "Quiere desactivar Descripcion: "+elementAt.get(1)+", Precio: "+elementAt.get(2),"Confirmacion", JOptionPane.YES_NO_OPTION);
		     if(a==JOptionPane.YES_OPTION)
		      {
		    	 boolean modificado=BBDD.modificarActividadPreciosBaja(tabla_bbdd, (int)elementAt.get(0));
		    	 if(modificado){
		       JOptionPane.showMessageDialog(null, "Modificacion correcta");
		    	 }else JOptionPane.showMessageDialog(null, "Modificacion erronea");
		      }
            	}
            }else {
            	int n = JOptionPane.showConfirmDialog((Component) frmDialogo , "Quiere Activar Descripcion: "+elementAt.get(1)+", Precio: "+elementAt.get(2),"Confirmacion", JOptionPane.YES_NO_OPTION);
   		     if(n==JOptionPane.YES_OPTION)
   		      {
   		    	 boolean modificado=BBDD.modificarActividadPreciosAlta(tabla_bbdd, (int)elementAt.get(0));
   		    	 if(modificado){
   		       JOptionPane.showMessageDialog(null, "Modificacion correcta");
   		    	 }else JOptionPane.showMessageDialog(null, "Modificacion correcta");
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

	public void itemStateChanged(ItemEvent e) {
		if(e.getSource().equals(ventanaControlada.chec_Activos)){
			if(ventanaControlada.chec_Activos.getState()==true){
				preciosAorD=true;
			}else preciosAorD=false;
			 if(preciosAorD==true){//Precios Activos
		            if(tipoPrueba<9){
		            	Vector<Concepto>v_conceptos=BBDD.obtener_ConceptosActivos(tabla_bbdd);
		            try {
						ventanaControlada.crearModeloTablaConcepto(v_conceptos);
					} catch (Exception a) {
						// TODO Auto-generated catch block
						a.printStackTrace();
					}
		            }else if(tipoPrueba==9){
		            	Vector<Concepto>v_conceptos=BBDD.obtener_MedicamentosActivos();
		            	try {
		    				ventanaControlada.crearModeloTablaMedicamentos(v_conceptos);
		    			} catch (Exception a) {
		    				// TODO Auto-generated catch block
		    				a.printStackTrace();
		    			}
		            }else if(tipoPrueba==10){
		            	Vector<Concepto>v_conceptos=BBDD.obtener_FungiblesActivos();
		            	try {
		    				ventanaControlada.crearModeloTablaFungibles(v_conceptos);
		    			} catch (Exception a) {
		    				// TODO Auto-generated catch block
		    				a.printStackTrace();
		    			}
		            }
          }else{//Precios Inactivos
         	 if(tipoPrueba<9){
		            	Vector<Concepto>v_conceptos=BBDD.obtener_ConceptosInactivos(tabla_bbdd);
		            try {
						ventanaControlada.crearModeloTablaConcepto(v_conceptos);
					} catch (Exception a) {
						// TODO Auto-generated catch block
						a.printStackTrace();
					}
		            }else if(tipoPrueba==9){
		            	Vector<Concepto>v_conceptos=BBDD.obtener_MedicamentosInactivos();
		            	try {
		    				ventanaControlada.crearModeloTablaMedicamentos(v_conceptos);
		    			} catch (Exception a) {
		    				// TODO Auto-generated catch block
		    				a.printStackTrace();
		    			}
		            }else if(tipoPrueba==10){
		            	Vector<Concepto>v_conceptos=BBDD.obtener_FungiblesInactivos();
		            	try {
		    				ventanaControlada.crearModeloTablaFungibles(v_conceptos);
		    			} catch (Exception a) {
		    				// TODO Auto-generated catch block
		    				a.printStackTrace();
		    			}
		            }
          }
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

}
