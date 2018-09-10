package Control;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.JOptionPane;

import Modelo.BBDD;
import Vista.VentanaAdministradorAnadirConcepto;
import Vista.VentanaAdministradorPrecios;

public class ControladorAdministradorAnadirConcepto implements WindowListener , ActionListener{
	VentanaAdministradorPrecios ventanaAdmin;
	VentanaAdministradorAnadirConcepto ventanaControlada;
	String tipoPrueba;
	int id_tipoPrueba;
	public ControladorAdministradorAnadirConcepto(VentanaAdministradorAnadirConcepto vp,VentanaAdministradorPrecios ventanaadmin, String tipoPrueba, int id_tipoPrueba) {
		ventanaControlada=vp;
		ventanaAdmin=ventanaadmin;
		this.tipoPrueba=tipoPrueba;
		this.id_tipoPrueba=id_tipoPrueba;
	}

	@Override
	public void windowOpened(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowClosing(WindowEvent e) {
		ventanaAdmin.frmAnadirTratamiento.setEnabled(true);
		
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
	public void actionPerformed(ActionEvent e) {
		if(e.getSource().equals(ventanaControlada.btn_AP_Cancelar)){
			ventanaAdmin.frmAnadirTratamiento.setEnabled(true);
			ventanaControlada.frmAdminAnadirConcepto.setVisible(false);
			
		}else if(e.getSource().equals(ventanaControlada.btn_AP_Aceptar)){
			if(ventanaControlada.id_tipotabla<9){
				String descripcion= ventanaControlada.txt_AP_Descripcion.getText();
				String precioS=ventanaControlada.txt_AP_Precio.getText();
				boolean aux=true;
				int precio=0;
				if(descripcion.equals("")){ JOptionPane.showMessageDialog((Component) ventanaControlada.frmAdminAnadirConcepto, "No ha introducido descripcion", "Error", JOptionPane.ERROR_MESSAGE);
					
					}else {
						try{
							 precio=Integer.parseInt(precioS);
							}catch(Exception a){
								aux=false;
								JOptionPane.showMessageDialog((Component) ventanaControlada.frmAdminAnadirConcepto, "El precio no debe contener caracteres", "Error", JOptionPane.ERROR_MESSAGE);
							}
							if(aux==true){
								int dialogo = JOptionPane.showConfirmDialog(ventanaControlada.frmAdminAnadirConcepto, "�Quiere anadir un nuevo concepto en "+ventanaControlada.tipoPrueba+" el concepto "+descripcion+" precio "+precio+" ?", "Guardar nuevo Concepto", JOptionPane.YES_NO_OPTION);
								if (dialogo == JOptionPane.YES_OPTION){
									int id=BBDD.IngresarConcepto(descripcion, tipoPrueba);
									boolean compprecio=BBDD.IngresarPrecio( id_tipoPrueba,id, precio);
									if(compprecio){
										JOptionPane.showMessageDialog((Component) ventanaControlada.frmAdminAnadirConcepto, "Concepto Creado correctamente", "Error", JOptionPane.INFORMATION_MESSAGE);
									}
									ventanaAdmin.frmAnadirTratamiento.setEnabled(true);
									ventanaControlada.frmAdminAnadirConcepto.setVisible(false);
								}
								}
					}
				}else if(ventanaControlada.id_tipotabla==9){
					String descripcion= ventanaControlada.txt_AP_Descripcion.getText();
					String precioS=ventanaControlada.txt_AP_Precio.getText();
					String farmaco=ventanaControlada.txt_AP_Farmaco.getText();
					boolean aux=true;
					int precio=0;
					if(descripcion.equals("")){JOptionPane.showMessageDialog((Component) ventanaControlada.frmAdminAnadirConcepto, "No ha introducido descripcion", "Error", JOptionPane.ERROR_MESSAGE);
						
						}else if(farmaco.equals("")){
							JOptionPane.showMessageDialog((Component) ventanaControlada.frmAdminAnadirConcepto, "No ha introducido el apartado del farmaco", "Error", JOptionPane.ERROR_MESSAGE);
						}else {
							try{
								 precio=Integer.parseInt(precioS);
								}catch(Exception a){
									aux=false;
									JOptionPane.showMessageDialog((Component) ventanaControlada.frmAdminAnadirConcepto, "El precio no debe contener caracteres", "Error", JOptionPane.ERROR_MESSAGE);
								}
								if(aux==true){
									int dialogo = JOptionPane.showConfirmDialog(ventanaControlada.frmAdminAnadirConcepto, "¿Quiere añadir un nuevo concepto en "+ventanaControlada.tipoPrueba+" el concepto "+descripcion+" precio "+precio+" ?", "Guardar nuevo Concepto", JOptionPane.YES_NO_OPTION);
									if (dialogo == JOptionPane.YES_OPTION){
										int id=BBDD.IngresarMedicamento(descripcion, tipoPrueba,farmaco);
										boolean compprecio=BBDD.IngresarPrecio( id_tipoPrueba,id, precio);
										if(compprecio){
											JOptionPane.showMessageDialog((Component) ventanaControlada.frmAdminAnadirConcepto, "Concepto Creado correctamente", "Error", JOptionPane.ERROR_MESSAGE);
										}
										ventanaAdmin.frmAnadirTratamiento.setEnabled(true);
										ventanaControlada.frmAdminAnadirConcepto.setVisible(false);
									}
									}
						}
				}else if(ventanaControlada.id_tipotabla==10){
					String descripcion= ventanaControlada.txt_AP_Descripcion.getText();
					String precioS=ventanaControlada.txt_AP_Precio.getText();
					boolean aux=true;
					int precio=0;
					if(descripcion.equals("")){ JOptionPane.showMessageDialog((Component) ventanaControlada.frmAdminAnadirConcepto, "No ha introducido descripcion", "Error", JOptionPane.ERROR_MESSAGE);
						
						}else {
							try{
								 precio=Integer.parseInt(precioS);
								}catch(Exception a){
									aux=false;
									JOptionPane.showMessageDialog((Component) ventanaControlada.frmAdminAnadirConcepto, "El precio no debe contener caracteres", "Error", JOptionPane.ERROR_MESSAGE);
								}
								if(aux==true){
									int dialogo = JOptionPane.showConfirmDialog(ventanaControlada.frmAdminAnadirConcepto, "�Quiere anadir un nuevo concepto en "+ventanaControlada.tipoPrueba+" el concepto "+descripcion+" precio "+precio+" ?", "Guardar nuevo Concepto", JOptionPane.YES_NO_OPTION);
									if (dialogo == JOptionPane.YES_OPTION){
										int id=BBDD.IngresarConcepto(descripcion, tipoPrueba);
										boolean compprecio=BBDD.IngresarPrecio( id_tipoPrueba,id, precio);
										if(compprecio){
											JOptionPane.showMessageDialog((Component) ventanaControlada.frmAdminAnadirConcepto, "Concepto Creado correctamente", "Error", JOptionPane.INFORMATION_MESSAGE);
										}
										ventanaAdmin.frmAnadirTratamiento.setEnabled(true);
										ventanaControlada.frmAdminAnadirConcepto.setVisible(false);
									}
									}
						}
			/*else if(ventanaControlada.id_tipotabla==10){
					
					String descripcion= ventanaControlada.txt_AP_Descripcion.getText();
					String precioS=ventanaControlada.txt_AP_Precio.getText();
					String ref1=ventanaControlada.txt_AP_Ref1.getText();
					String ref2=ventanaControlada.txt_AP_Ref2.getText();
					String marca=ventanaControlada.txt_AP_Marca.getText();
					boolean aux=true;
					int precio=0;
					if(descripcion.equals("")){JOptionPane.showMessageDialog((Component) ventanaControlada.frmAdminAnadirConcepto, "No ha introducido descripcion", "Error", JOptionPane.ERROR_MESSAGE);
						
						
						}else {
							try{
								 precio=Integer.parseInt(precioS);
								}catch(Exception a){
									aux=false;
									JOptionPane.showMessageDialog((Component) ventanaControlada.frmAdminAnadirConcepto, "El precio no debe contener caracteres", "Error", JOptionPane.ERROR_MESSAGE);
								}
								if(aux==true){
									int dialogo = JOptionPane.showConfirmDialog(ventanaControlada.frmAdminAnadirConcepto, "¿Quiere añadir un nuevo concepto en "+ventanaControlada.tipoPrueba+" el concepto "+descripcion+" precio "+precio+" ?", "Guardar nuevo Concepto", JOptionPane.YES_NO_OPTION);
									if (dialogo == JOptionPane.YES_OPTION){
										int id=BBDD.IngresarFungible(descripcion, tipoPrueba,ref1,ref2,marca);
										boolean compprecio=BBDD.IngresarPrecio( id_tipoPrueba,id, precio);
										if(compprecio){
											JOptionPane.showMessageDialog((Component) ventanaControlada.frmAdminAnadirConcepto, "Concepto Creado correctamente", "Error", JOptionPane.ERROR_MESSAGE);
										}
										ventanaAdmin.frmAnadirTratamiento.setEnabled(true);
										ventanaControlada.frmAdminAnadirConcepto.setVisible(false);
									}
									}
									
						}
			}
			*/
			
		}
	}}
}
	


