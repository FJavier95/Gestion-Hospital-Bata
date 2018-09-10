package Control;

import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.math.BigInteger;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Vector;

import javax.swing.JOptionPane;

import org.jfree.chart.ChartPanel;

import Modelo.BBDD;
import Modelo.Fecha_Paciente;
import Modelo.ProgressBarAdministrador;
import Modelo.Tratamiento;
import Vista.VentanaAdministradorGastosTotales;

public class ControladorAdministradorGastosTotales implements ActionListener, WindowListener, MouseListener{
		public VentanaAdministradorGastosTotales ventanaControlada;
	
	public ControladorAdministradorGastosTotales (VentanaAdministradorGastosTotales vgt){
	 this.ventanaControlada = vgt;
	}
	
	public void actionPerformed(ActionEvent e) {
		if(e.getSource().equals(ventanaControlada.btnAceptar)){
			java.util.Date fecha1=ventanaControlada.calendario.getDate();
			java.util.Date fecha2= ventanaControlada.calendario1.getDate();
			if(fecha1!=null){
				if(fecha2!=null){
					int aux=fecha1.compareTo(fecha2);
					if(aux<0){
					Timestamp fe1=new Timestamp(fecha1.getTime());
					Timestamp fe2=new Timestamp(fecha2.getTime());
				Fecha_Paciente fp=new Fecha_Paciente(fe1,fe2);
				Cursor cur = new Cursor(Cursor.WAIT_CURSOR);
		        
				ventanaControlada.frmGastosTotales.setCursor(cur);
				/*ArrayList<Tratamiento>vtembarazada=BBDD.obtenerTratamientosAdministrador(fp,1);
				ArrayList<Tratamiento>vtecografia=BBDD.obtenerTratamientosAdministrador(fp,2);
				ArrayList<Tratamiento>vtlaboratorio=BBDD.obtenerTratamientosAdministrador(fp,3);
				ArrayList<Tratamiento>vtmamografia=BBDD.obtenerTratamientosAdministrador(fp,4);
				ArrayList<Tratamiento>vtodontoestomatologia=BBDD.obtenerTratamientosAdministrador(fp,5);
				ArrayList<Tratamiento>vtotorrinolaringologia=BBDD.obtenerTratamientosAdministrador(fp,6);
				ArrayList<Tratamiento>vtprocesosquirurjicos=BBDD.obtenerTratamientosAdministrador(fp,7);
				ArrayList<Tratamiento>vtradiologiasimple=BBDD.obtenerTratamientosAdministrador(fp,8);
				ArrayList<Tratamiento>vtmedicamentos=BBDD.obtenerTratamientosAdministrador(fp,9);
				ArrayList<Tratamiento>vtfungibles=BBDD.obtenerTratamientosAdministrador(fp,10);
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
				}*/
				
				int totalembarazada=BBDD.ObtenerGastototalporTipoPrueba(1, String.valueOf(fe1),String.valueOf(fe2));
				int totalecografia=BBDD.ObtenerGastototalporTipoPrueba(2, String.valueOf(fe1),String.valueOf(fe2));
				int totallaboratorio=BBDD.ObtenerGastototalporTipoPrueba(3, String.valueOf(fe1),String.valueOf(fe2));;;
				int totalmamografia=BBDD.ObtenerGastototalporTipoPrueba(4, String.valueOf(fe1),String.valueOf(fe2));;;
				int totalodonto=BBDD.ObtenerGastototalporTipoPrueba(5, String.valueOf(fe1),String.valueOf(fe2));;;
				int totalotorrino=BBDD.ObtenerGastototalporTipoPrueba(6, String.valueOf(fe1),String.valueOf(fe2));;;
				int totalprocesos=BBDD.ObtenerGastototalporTipoPrueba(7, String.valueOf(fe1),String.valueOf(fe2));;;
				int totalradiologia=BBDD.ObtenerGastototalporTipoPrueba(8, String.valueOf(fe1),String.valueOf(fe2));;;
				int totalmedicamentos=BBDD.ObtenerGastototalporTipoPrueba(9, String.valueOf(fe1),String.valueOf(fe2));;;
				int totalfungibles=BBDD.ObtenerGastototalporTipoPrueba(10, String.valueOf(fe1),String.valueOf(fe2));;;
				ProgressBarAdministrador pbc=new ProgressBarAdministrador(totalembarazada,totalecografia,totallaboratorio,totalmamografia,totalodonto,totalotorrino,totalprocesos,totalradiologia,totalmedicamentos,totalfungibles);
			    ChartPanel chartPanel = new ChartPanel(pbc.progresBar());
			    chartPanel.setPreferredSize(new Dimension( AnchoRelativo(900), AltoRelativo(318)));
			    chartPanel.setFillZoomRectangle(true);
			             chartPanel.setMouseWheelEnabled(true);
			    ventanaControlada.panel_progbar.add(chartPanel);
			    ventanaControlada.panel_progbar.validate();
			    try {
					ventanaControlada.crearModeloTablaGastos(totalembarazada,totalecografia,totallaboratorio,totalmamografia,totalodonto,totalotorrino,totalprocesos,totalradiologia,totalmedicamentos,totalfungibles);
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			    Cursor curd = new Cursor(Cursor.DEFAULT_CURSOR);
		        
				ventanaControlada.frmGastosTotales.setCursor(curd);
					}
					}else JOptionPane.showMessageDialog(null, "Fecha 2 Erronea");
			}else JOptionPane.showMessageDialog(null, "Fecha 1 Erronea");
		}else if(e.getSource().equals(ventanaControlada.btn_gastos_condicion)){
			java.util.Date fecha1=ventanaControlada.calendario.getDate();
			java.util.Date fecha2= ventanaControlada.calendario1.getDate();
			if(fecha1!=null){
				if(fecha2!=null){
					int aux=fecha1.compareTo(fecha2);
					if(aux<0){
					Timestamp fe1=new Timestamp(fecha1.getTime());
					Timestamp fe2=new Timestamp(fecha2.getTime());
				Fecha_Paciente fp=new Fecha_Paciente(fe1,fe2);
				try {
					ventanaControlada.crearModeloTablaGastosPorCondicion(String.valueOf(fe1), String.valueOf(fe2));
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
					}
				}
			}else  JOptionPane.showMessageDialog(null, "Fecha 1 Erronea");
		}else JOptionPane.showMessageDialog(null, "Fecha 2 Erronea");
		
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		
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
	public void windowOpened(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowClosing(WindowEvent e) {
		ventanaControlada.va.frame.setEnabled(true);
		
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
	
	public int AltoRelativo (int altura) {
	      Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
	      int AltoRelat = (screen.height*altura)/1080;
	      return AltoRelat;
	    }
	
	public int AnchoRelativo(int ancho) {
	      Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
	      int AnchoRelat = (screen.width*ancho)/1920;
	      
	      return AnchoRelat;
	    }
	 
	public int fuenteRelativa(int fuenteActual){
	      Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
	      int fuenteBuena = (screen.width*fuenteActual)/1920;
	      return fuenteBuena;
	    }
	
}
