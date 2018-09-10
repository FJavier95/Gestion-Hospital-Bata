package Vista;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.text.NumberFormat;
import java.text.ParseException;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import com.toedter.calendar.JDateChooser;

import Control.ControladorAdministradorGastosTotales;
import Control.ControladorAnadirPaciente;
import Modelo.BBDD;
import Modelo.Tratamiento;


public class VentanaAdministradorGastosTotales {
	ControladorAdministradorGastosTotales controlador;
	public JDateChooser calendario;
	public JDateChooser calendario1;
	public JFrame frmGastosTotales;
	public JButton btnAceptar;
	public JButton btnCancelar;
	public JTable tabla_gastos;
	public JPanel panel_progbar;
	public JPanel panel_gastos;
	JScrollPane scrollPane;
	public JButton btn_gastos_condicion;
	 public VentanaAdministrador va;
	public void addController(ControladorAdministradorGastosTotales mc,VentanaAdministrador va){
		controlador = mc;
		this.va=va;
	}
	
	
	public void crearVentana(){
		
		frmGastosTotales = new JFrame();
		frmGastosTotales.getContentPane().setFont(new Font("Tahoma", Font.PLAIN, fuenteRelativa(19)));
		frmGastosTotales.getContentPane().setBackground(Color.WHITE);
		frmGastosTotales.setTitle("GastosTotales");
		frmGastosTotales.setBounds(AnchoRelativo(130), AltoRelativo(70), AnchoRelativo(1700), AltoRelativo(1000));
	//	frmGastosTotales.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		frmGastosTotales.getContentPane().setLayout(null);
		frmGastosTotales.setResizable(false);
		frmGastosTotales.addWindowListener(controlador);
		
		
		 //calendario.addActionListener(controlador);
		JTextPane txtpnDNI = new JTextPane();
		  txtpnDNI.setText("Introduzca dos fechas");
		  txtpnDNI.setFont(new Font("Tahoma", Font.BOLD, fuenteRelativa(21)));	
		  txtpnDNI.setEditable(false);
		  txtpnDNI.setBounds(AnchoRelativo(150), AltoRelativo(100), AnchoRelativo(120), AltoRelativo(80));
		  frmGastosTotales.add(txtpnDNI);
		calendario = new JDateChooser();
		
		calendario.setFont(new Font("Tahoma", Font.PLAIN, fuenteRelativa(19)));
		calendario.getCalendarButton().setFont(new Font("Tahoma", Font.PLAIN, 19));
		calendario.setBounds(AnchoRelativo(150), AltoRelativo(240), AnchoRelativo(300), AltoRelativo(22));
		//calendario.setMaxSelectableDate();
		//frmGastosTotales.getContentPane().add(calendario)
		frmGastosTotales.add(calendario);
		
		calendario1 = new JDateChooser();
		
		calendario1.setFont(new Font("Tahoma", Font.PLAIN, fuenteRelativa(19)));
		calendario1.getCalendarButton().setFont(new Font("Tahoma", Font.PLAIN, 19));
		calendario1.setBounds(AnchoRelativo(150), AltoRelativo(300), AnchoRelativo(300), AltoRelativo(22));
		//calendario.setMaxSelectableDate();
		//frmGastosTotales.getContentPane().add(calendario)
		frmGastosTotales.add(calendario1);
		
		btnAceptar = new JButton("Gastos Totales");
		btnAceptar.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnAceptar.setFont(new Font("Tahoma", Font.BOLD, fuenteRelativa(21)));	//Establece el tama�o y el tipo de letra que tendr� el bot�n
		btnAceptar.setBackground(new Color(0, 0, 96));	//Establece el color del bot�n
		btnAceptar.setOpaque(true);
		btnAceptar.setBorderPainted(false);
		btnAceptar.setForeground(Color.WHITE);
		btnAceptar.setBounds(AnchoRelativo(138), AltoRelativo(400), AnchoRelativo(300), AltoRelativo(33));
	    btnAceptar.addActionListener(controlador);
	    frmGastosTotales.getContentPane().add(btnAceptar);
	    
	    btn_gastos_condicion = new JButton("Gastos Totales Condicion");
	    btn_gastos_condicion.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
	    btn_gastos_condicion.setFont(new Font("Tahoma", Font.BOLD, fuenteRelativa(21)));	//Establece el tama�o y el tipo de letra que tendr� el bot�n
	    btn_gastos_condicion.setBackground(new Color(0, 0, 96));	//Establece el color del bot�n
	    btn_gastos_condicion.setOpaque(true);
	    btn_gastos_condicion.setBorderPainted(false);
	    btn_gastos_condicion.setForeground(Color.WHITE);
	    btn_gastos_condicion.setBounds(AnchoRelativo(138), AltoRelativo(450), AnchoRelativo(300), AltoRelativo(33));
	    btn_gastos_condicion.addActionListener(controlador);
	    frmGastosTotales.getContentPane().add(btn_gastos_condicion);
	    
	    panel_progbar = new JPanel();
  		panel_progbar.setBackground(Color.WHITE);
  		//panel_progbar.setBorder(new LineBorder(new Color(139, 0, 0), 2));
  		panel_progbar.setBounds(AnchoRelativo(500), AltoRelativo(20), AnchoRelativo(1000), AltoRelativo(518));
  		frmGastosTotales.getContentPane().add(panel_progbar);
  		panel_progbar.setLayout(new BorderLayout(0, 0));
	   
  		

  		tabla_gastos = new JTable();
		 scrollPane = new JScrollPane();
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
		scrollPane.setBounds(AnchoRelativo(500), AltoRelativo(600), AnchoRelativo(900), AltoRelativo(318));
		tabla_gastos.addMouseListener(controlador);
		scrollPane.add(tabla_gastos);
		frmGastosTotales.getContentPane().add(scrollPane);
		
		scrollPane.setViewportView(tabla_gastos);
		scrollPane.setVisible(false);
		
		frmGastosTotales.setVisible(true);
	}
	public void crearModeloTablaGastos(int totemba,int totaleco, int totallab, int totalmamo,int totalodon,int totaloto,int totalproc,int totalradio,int totalmed,int totalfung ) throws Exception{
		
		scrollPane.setVisible(true);
		  String[] nombre_Columnas_Paciente= {"TipoPrueba","Gasto Total"};
		       
		  TableModel modelo_Tabla_Paciente = new DefaultTableModel(crearDatostablaGastos(totemba,totaleco,totallab,totalmamo,totalodon,totaloto,totalproc,totalradio,totalmed,totalfung), nombre_Columnas_Paciente) {
		   public boolean isCellEditable(int row, int column) {return false;}};
		   tabla_gastos.setModel(modelo_Tabla_Paciente);
		   tabla_gastos.repaint();
		  
		  
		 }
		 
		public Object[][] crearDatostablaGastos(int totemba,int totaleco, int totallab, int totalmamo,int totalodon,int totaloto,int totalproc,int totalradio,int totalmed,int totalfung ) throws ParseException{
		  Object[][] md = new Object[10][2];
		  NumberFormat nf = NumberFormat.getInstance();
				md[0][0] =  "EMBARAZADA";
				if(totemba==0){
					md[0][1] =totemba;
				}else{
		         md[0][1] =  nf.format(totemba);
				}
		         md[1][0] =  "ECOGRAFIA";
		         if(totaleco==0){
		        	 md[1][1] =0;
		         }else {
		         md[1][1] =  nf.format(totaleco);
		         }
		         
		         md[2][0] =  "LABORATORIO";
		         if(totallab==0){
		        	 md[2][1] =0;
		         }else {
		         md[2][1] =  nf.format(totallab);
		         }
		         md[3][0] =  "MAMOGRAFIA";
		         if(totalmamo==0){
		        	 md[3][1] = 0;
		         }else{
		         md[3][1] =  nf.format(totalmamo);
		         }
		         md[4][0] =  "ODONTOESTOMATOLOGIA";
		         if(totalodon==0){
		         md[4][1] =  0;
		         }else md[4][1] =  nf.format(totalodon);
		         md[5][0] =  "OTORRINOLARINGOLOGIA";
		         if(totaloto==0){
		        	 md[5][1]= 0;
		         }else {
		         md[5][1] =  nf.format(totaloto);
		         }
		         md[6][0] =  "PROCESOSQUIRURGICOS";
		         if(totalproc==0){
		        	 md[6][1]=0;
		         }else {
		         md[6][1] =  nf.format(totalproc);
		         }
		         md[7][0] =  "RADIOLOGIASIMPLE";
		         if (totalradio==0){
		        	 md[7][1]=0;
		         }else {
		         md[7][1] =  nf.format(totalradio);
		         }
		         md[8][0] =  "MEDICAMENTOS";
		         if(totalmed==0){
		        	 md[8][1]=0;
		         }else{
		         md[8][1] =  nf.format(totalmed);
		         }
		         md[9][0] =  "FUNGIBLES";
		         if (totalfung==0){
		        	 md[9][1]=0;
		         }else{
		         md[9][1] =  nf.format(totalfung);
		         }
		         
		        
		  return md;
		 }
		public void crearModeloTablaGastosPorCondicion(String fech1, String fech2) throws Exception{
		      
		      scrollPane.setVisible(true);
		        String[] nombre_Columnas_Paciente= {"TipoPrueba","Asegurado","No-Asegurado","Jubilado","Pensionista","Discapacitado"};
		             
		        TableModel modelo_Tabla_Paciente = new DefaultTableModel(crearDatostablaGastosPorCondicion( fech1, fech2), nombre_Columnas_Paciente) {
		         public boolean isCellEditable(int row, int column) {return false;}};
		         tabla_gastos.setModel(modelo_Tabla_Paciente);
		         tabla_gastos.repaint();
		        
		        
		       }
		       
		      public Object[][] crearDatostablaGastosPorCondicion(String fech1, String fech2) throws ParseException{
		        Object[][] md = new Object[11][6];
		        NumberFormat nf = NumberFormat.getInstance();
		          md[0][0] =  "EMBARAZADA";
		          md[1][0] = "ECOGRAFIA";
		          md[2][0] = "LABORATORIO";
		          md[3][0] = "MAMOGRAFIA";
		          md[4][0] = "ODONTOESTOMATOLOGIA";
		          md[5][0] =  "OTORRINOLARINGOLOGIA";
		          md[6][0] = "PROCESOS QUIRURGICOS";
		          md[7][0] = "RADIOLOGIASIMPLE";
		          md[8][0] = "MEDICAMENTOS";
		          md[9][0] = "FUNGIBLES";
		          md[10][0] ="TOTAL";
		          for (int i = 1; i <= 10; i++){
		            for(int e = 1 ; e < 6; e++){
		              md[i-1][e] = BBDD.obtenerGastoTotalPorCondicion(e, i, fech1, fech2);
		              
		            }
		          }
		         for(int e =1;e<6;e++){
		        	 int total=0;
		        	 for(int i=1;i<10;i++){
		        	 total=total+(int)md[i][e];
		        	 }
		        	 md[10][e]=nf.format(total);
		          }
		         for (int i = 1; i <= 10; i++){
			            for(int e = 1 ; e < 6; e++){
			              md[i-1][e] = nf.format(md[i-1][e]);
			              
			            }
			          }
		        return md;
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
