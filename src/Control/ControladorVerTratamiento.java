package Control;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Vector;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import Modelo.BBDD;
import Modelo.Concepto;
import Modelo.CrearFacturas;
import Modelo.Fecha_Paciente;
import Modelo.Paciente;
import Modelo.Tratamiento;
import Vista.VentanaAdministradorPacientes;
import Vista.VentanaBusquedaPaciente;
import Vista.VentanaVerTratamientos;

public class ControladorVerTratamiento implements MouseListener, WindowListener, ActionListener {
	VentanaVerTratamientos ventanaControlada;
	public JFrame frmDialogo = null;
	Paciente pac = null;
	VentanaBusquedaPaciente cbp;
	 Fecha_Paciente fech_pac;
	 Vector<Tratamiento> v_trt;
	 VentanaAdministradorPacientes cba;
	 boolean aux=false;
	public ControladorVerTratamiento(VentanaVerTratamientos vbp, Paciente pac, VentanaBusquedaPaciente cbp ){
		ventanaControlada=vbp;
		this.pac = pac;
		this.cbp=cbp;
		aux=false;
	}
	public ControladorVerTratamiento(VentanaVerTratamientos vbp, Paciente pac, VentanaAdministradorPacientes cbp ){
		ventanaControlada=vbp;
		this.pac = pac;
		this.cba=cbp;
		aux=true;
	}
	@Override
	public void mouseClicked(MouseEvent e) {
		

		if(e.getSource() instanceof JTable){
			if(e.getComponent().equals(ventanaControlada.tablaFechas)){
			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
			Date parsedTimeStamp = null;
            JTable target = (JTable) e.getSource();
            int row = target.getSelectedRow();
            DefaultTableModel tableModel =(DefaultTableModel) target.getModel();
            Vector<Object> elementAt = (Vector<Object>) tableModel.getDataVector().elementAt(row);
            try {
				parsedTimeStamp = dateFormat.parse((String) elementAt.get(1));
			} catch (ParseException e2) {
				// TODO Auto-generated catch block
				e2.printStackTrace();
			}
            Timestamp fecha_ingreso =  new Timestamp (parsedTimeStamp.getTime());
            if(!elementAt.get(2).equals("Sin Fecha")){
            try {
            	
				parsedTimeStamp = dateFormat.parse((String) elementAt.get(2));
            	
			} catch (ParseException e2) {
				// TODO Auto-generated catch block
				e2.printStackTrace();
			}
            
            }try {
				parsedTimeStamp = dateFormat.parse("2999-12-31 00:00:00");
			} catch (ParseException e2) {
				// TODO Auto-generated catch block
				e2.printStackTrace();
			}
            Timestamp fecha_Alta =  new Timestamp (parsedTimeStamp.getTime());
             fech_pac = new Fecha_Paciente (fecha_Alta, fecha_ingreso);
			 v_trt = BBDD.obtenerTratamientosPorFecha(fech_pac, pac);
			try {
				ventanaControlada.btn_Factura.setVisible(true);
				ventanaControlada.crearModeloTablaPruebas(v_trt);
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}        
           
			}else if(e.getComponent().equals(ventanaControlada.tablaTratamientos)){
				 JTable target = (JTable) e.getSource();
		            int row = target.getSelectedRow();
		            DefaultTableModel tableModel =(DefaultTableModel) target.getModel();
		            Vector<Object> elementAt = (Vector<Object>) tableModel.getDataVector().elementAt(row);
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
		cbp.panelDatosPaciente(pac);
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
	@Override
	public void actionPerformed(ActionEvent arg0) {
		if(arg0.getSource().equals(ventanaControlada.btn_Factura)){
			int id=BBDD.obtenerIdUltimaFechaPaciente(pac);
			int fianza=BBDD.obtenerFianzatotal(id);
			ArrayList<Tratamiento>vtembarazada=BBDD.obtenerTratamientosFactura(fech_pac,1,pac);
			ArrayList<Tratamiento>vtecografia=BBDD.obtenerTratamientosFactura(fech_pac,2,pac);
			ArrayList<Tratamiento>vtlaboratorio=BBDD.obtenerTratamientosFactura(fech_pac,3,pac);
			ArrayList<Tratamiento>vtmamografia=BBDD.obtenerTratamientosFactura(fech_pac,4,pac);
			ArrayList<Tratamiento>vtodontoestomatologia=BBDD.obtenerTratamientosFactura(fech_pac,5,pac);
			ArrayList<Tratamiento>vtotorrinolaringologia=BBDD.obtenerTratamientosFactura(fech_pac,6,pac);
			ArrayList<Tratamiento>vtprocesosquirurjicos=BBDD.obtenerTratamientosFactura(fech_pac,7,pac);
			ArrayList<Tratamiento>vtradiologiasimple=BBDD.obtenerTratamientosFactura(fech_pac,8,pac);
			ArrayList<Tratamiento>vtmedicamentos=BBDD.obtenerTratamientosFactura(fech_pac,9,pac);
			ArrayList<Tratamiento>vtfungibles=BBDD.obtenerTratamientosFactura(fech_pac,10,pac);
			int totalembarazada=0;
			for (int i=0;i<vtembarazada.size();i++){
				totalembarazada+=(vtembarazada.get(i).cantidad*vtembarazada.get(i).coste);
			}
			int totalecografia=0;
			for (int i=0;i<vtecografia.size();i++){
				totalecografia+=(vtecografia.get(i).cantidad*vtecografia.get(i).coste);
			}
			int totallaboratorio=0;
			for (int i=0;i<vtlaboratorio.size();i++){
				totallaboratorio+=(vtlaboratorio.get(i).cantidad*vtlaboratorio.get(i).coste);
			}
			int totalmamografia=0;
			for (int i=0;i<vtmamografia.size();i++){
				totalmamografia+=(vtmamografia.get(i).cantidad*vtmamografia.get(i).coste);
			}
			int totalodonto=0;
			for (int i=0;i<vtodontoestomatologia.size();i++){
				totalodonto+=(vtodontoestomatologia.get(i).cantidad*vtodontoestomatologia.get(i).coste);
			}
			int totalotorrino=0;
			for (int i=0;i<vtotorrinolaringologia.size();i++){
				totalotorrino+=(vtotorrinolaringologia.get(i).cantidad*vtotorrinolaringologia.get(i).coste);
			}
			int totalprocesos=0;
			for (int i=0;i<vtprocesosquirurjicos.size();i++){
				totalprocesos+=(vtprocesosquirurjicos.get(i).cantidad*vtprocesosquirurjicos.get(i).coste);
			}
			int totalradiologia=0;
			for (int i=0;i<vtradiologiasimple.size();i++){
				totalradiologia+=(vtradiologiasimple.get(i).cantidad*vtradiologiasimple.get(i).coste);
			}
			int totalmedicamentos=0;
			for (int i=0;i<vtmedicamentos.size();i++){
				totalmedicamentos+=(vtmedicamentos.get(i).cantidad*vtmedicamentos.get(i).coste);
			}
			int totalfungibles=0;
			for (int i=0;i<vtfungibles.size();i++){
				totalfungibles+=(vtfungibles.get(i).cantidad*vtfungibles.get(i).coste);
			}
			int total=totalembarazada+totalecografia+totallaboratorio+totalmamografia+totalodonto+totalotorrino+totalprocesos+totalradiologia+totalmedicamentos+totalfungibles;
			Vector<Integer>totales =new Vector();
			totales.add(totalembarazada);
			totales.add(totalecografia);
			totales.add(totallaboratorio);
			totales.add(totalmamografia);
			totales.add(totalodonto);
			totales.add(totalotorrino);
			totales.add(totalprocesos);
			totales.add(totalradiologia);
			totales.add(totalmedicamentos);
			totales.add(totalfungibles);
			CrearFacturas cf=new CrearFacturas();
			try {
				cf.mostrarFactura(pac, fech_pac, totales,total,fianza);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
	}

}
