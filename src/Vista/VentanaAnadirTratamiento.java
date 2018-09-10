package Vista;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.MouseListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.Timestamp;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Vector;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;
import javax.swing.table.TableModel;

import Control.ControladorAnadirPaciente;
import Control.ControladorAnadirTratamiento;
import Control.ControladorBuscarPaciente;
import Modelo.BBDD;
import Modelo.Concepto;
import Modelo.Conexion;
import Modelo.Paciente;



public class VentanaAnadirTratamiento extends JFrame {
	ControladorAnadirTratamiento controlador;
	
	
	public JFrame frmAnadirTratamiento;
	public JLabel lbl_AP_TipoPrueba;
	public JComboBox cb_AP_TipoPrueba;
	public JLabel lbl_AP_Concepto;
	public JComboBox cb_AP_Concepto;
	public JLabel lbl_AP_Cantidad;
	public JTextField txt_AP_Cantidad;
	public JButton btn_Dar_Alta;
	public JButton btn_AP_Aceptar;
	public JButton btn_AP_Cancelar;
	public JTable tablaTipos;
	public JTable tablaConceptos;
	
	
	
	
			
	public void addController(ControladorAnadirTratamiento cbp){
		  controlador=cbp;
		 }

	public void crearTratamiento(){
		
		
		frmAnadirTratamiento = new JFrame();
		frmAnadirTratamiento.getContentPane().setFont(new Font("Tahoma", Font.PLAIN, fuenteRelativa(19)));
		frmAnadirTratamiento.getContentPane().setBackground(Color.WHITE);
		frmAnadirTratamiento.setTitle("Nuevo Tratamiento");
		frmAnadirTratamiento.setBounds(AnchoRelativo(130), AltoRelativo(70), AnchoRelativo(1700), AltoRelativo(1000));
		//frmAnadirTratamiento.setAlwaysOnTop(true);
		//frmAnadirTratamiento.setFocusable(true);
		//frmAnadirTratamiento.setDefaultCloseOperation(defaultCloseOperation);
		frmAnadirTratamiento.addWindowListener(controlador);
		frmAnadirTratamiento.setResizable(false);
	
		
		
		tablaTipos = new JTable();
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
		scrollPane.setBounds(AnchoRelativo(20), AltoRelativo(10), AnchoRelativo(1000), AltoRelativo(400));
		tablaTipos.addMouseListener(controlador);
		
		
		
		try {
			crearModeloTablaPruebas(BBDD.tipotratamiento());
		} catch (Exception e) {
			e.printStackTrace();
		}
		scrollPane.setViewportView(tablaTipos);
		tablaTipos.setVisible(true);
		frmAnadirTratamiento.getContentPane().add(scrollPane);
		
		btn_Dar_Alta = new JButton("Dar el Alta");
		btn_Dar_Alta.setFont(new Font("Tahoma", Font.BOLD, fuenteRelativa(21)));	//Establece el tama�o y el tipo de letra que tendr� el bot�n
		btn_Dar_Alta.setBackground(new Color(0, 48, 110));	//Establece el color del bot�n
		btn_Dar_Alta.setOpaque(true);
		btn_Dar_Alta.setBorderPainted(false);
		btn_Dar_Alta.setForeground(Color.WHITE);	//Establece el color de la fuente
		
		btn_Dar_Alta.addActionListener(controlador);
		btn_Dar_Alta.setBounds(AnchoRelativo(1221), AltoRelativo(50), AnchoRelativo(250), AltoRelativo(53));	//Establece el tama�o del bot�n
		frmAnadirTratamiento.getContentPane().add(btn_Dar_Alta);	//Se a�ade el elemento al JFrame
		
		
		JPanel Panel_conceptos = new JPanel();
		Panel_conceptos.setBackground(Color.white);
		Panel_conceptos.setBounds(AnchoRelativo(20), AltoRelativo(450), AnchoRelativo(1650), AltoRelativo(500));
		Panel_conceptos.setLayout(null);
		frmAnadirTratamiento.getContentPane().add(Panel_conceptos);
		tablaConceptos = new JTable();
		JScrollPane scrollPane1 = new JScrollPane();
		scrollPane1.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane1.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
		scrollPane1.setBounds(AnchoRelativo(20), AltoRelativo(450), AnchoRelativo(1650), AltoRelativo(500));
		scrollPane1.setViewportView(tablaConceptos);
		tablaConceptos.addMouseListener(controlador);
		Panel_conceptos.add(scrollPane1);
		
		
		
		
		
		
		frmAnadirTratamiento.setVisible(true);
		
	}
	
	public void crearModeloTablaConcepto( Vector<Concepto> datos) throws Exception{
		
	  
	  String[] nombre_Columnas_Paciente= {"ID","Descripcion","Precio","Moneda"};
	       
	  TableModel modelo_Tabla_Paciente = new DefaultTableModel(crearDatostablaConcepto(datos), nombre_Columnas_Paciente) {
	   public boolean isCellEditable(int row, int column) {return false;}};
	   tablaConceptos.setModel(modelo_Tabla_Paciente);
	   tablaConceptos.setAutoResizeMode(JTable.AUTO_RESIZE_NEXT_COLUMN);
	   TableColumnModel colModel=tablaConceptos.getColumnModel();
	   colModel.getColumn(0).setPreferredWidth(20);    
	   colModel.getColumn(1).setPreferredWidth(400);
	   colModel.getColumn(2).setPreferredWidth(40);
	   colModel.getColumn(3).setPreferredWidth(40);
	   tablaConceptos.setAutoResizeMode(3);
	   tablaConceptos.repaint();
	   tablaConceptos.setVisible(true);
	   
	  
	  
	 }
	 
	public Object[][] crearDatostablaConcepto(Vector<Concepto> datos){
	  Object[][] md = new Object[datos.size()][4];
	     
	       NumberFormat nf = NumberFormat.getInstance();
			
	  for (int i = 0; i < datos.size(); i++){
	         md[i][0] =  datos.get(i).getId();
	         md[i][1] = datos.get(i).getDescripcion();
	         md[i][2] = nf.format(datos.get(i).getPrecio());
	         md[i][3]="CFA";
		  }	         
	         
	  
	  return md;
	 }

	public void crearModeloTablaMedicamentos( Vector<Concepto> datos) throws Exception{
		
	  
	  String[] nombre_Columnas_Paciente= {"ID","Nombre Comercial","Precio","Moneda","Farmaco"};
	       
	  TableModel modelo_Tabla_Paciente = new DefaultTableModel(crearDatostablaMedicamentos(datos), nombre_Columnas_Paciente) {
	   public boolean isCellEditable(int row, int column) {return false;}};
	   tablaConceptos.setModel(modelo_Tabla_Paciente);
	   tablaConceptos.setAutoResizeMode(JTable.AUTO_RESIZE_NEXT_COLUMN);
	   TableColumnModel colModel=tablaConceptos.getColumnModel();
	   colModel.getColumn(0).setPreferredWidth(20);    
	   colModel.getColumn(1).setPreferredWidth(460);
	   colModel.getColumn(2).setPreferredWidth(15);
	   colModel.getColumn(3).setPreferredWidth(15);
	   colModel.getColumn(4).setPreferredWidth(400);
	   tablaConceptos.setAutoResizeMode(3);
	   tablaConceptos.repaint();
	   tablaConceptos.setVisible(true);
	  
	  
	 }
	 
	public Object[][] crearDatostablaMedicamentos(Vector<Concepto> datos){
	  Object[][] md = new Object[datos.size()][5];
	     
	       NumberFormat nf = NumberFormat.getInstance();
	   
			
	  for (int i = 0; i < datos.size(); i++){
	         md[i][0] =  datos.get(i).getId();
	         md[i][1] = datos.get(i).getDescripcion();
	         md[i][2] = nf.format(datos.get(i).getPrecio());
	         md[i][3]="CFA";
	         md[i][4]=datos.get(i).getMarca();
		  }	         
	         
	  
	  return md;
	 }
	public void crearModeloTablaFungibles( Vector<Concepto> datos) throws Exception{
		
		  
		  String[] nombre_Columnas_Paciente= {"ID","Descripcion","Precio","Moneda","Marca","Ref1","Ref2"};
		       
		  TableModel modelo_Tabla_Paciente = new DefaultTableModel(crearDatostablaFungibles(datos), nombre_Columnas_Paciente) {
		   public boolean isCellEditable(int row, int column) {return false;}};
		   tablaConceptos.setModel(modelo_Tabla_Paciente);
		   tablaConceptos.setAutoResizeMode(JTable.AUTO_RESIZE_NEXT_COLUMN);
		   TableColumnModel colModel=tablaConceptos.getColumnModel();
		   colModel.getColumn(0).setPreferredWidth(20);    
		   colModel.getColumn(1).setPreferredWidth(515);
		   colModel.getColumn(2).setPreferredWidth(10);
		   colModel.getColumn(3).setPreferredWidth(20);
		   colModel.getColumn(4).setPreferredWidth(115);
		   colModel.getColumn(5).setPreferredWidth(70);
		   colModel.getColumn(6).setPreferredWidth(70);
		   tablaConceptos.setAutoResizeMode(3);
		   tablaConceptos.repaint();
		   tablaConceptos.setVisible(true);
		  
		  
		 }
		 
		public Object[][] crearDatostablaFungibles(Vector<Concepto> datos){
		  Object[][] md = new Object[datos.size()][7];
		     
		       NumberFormat nf = NumberFormat.getInstance();
				
		  for (int i = 0; i < datos.size(); i++){
		         md[i][0] =  datos.get(i).getId();
		         md[i][1] = datos.get(i).getDescripcion();
		         md[i][2] = nf.format(datos.get(i).getPrecio());
		         md[i][3]="CFA";
		         md[i][4]=datos.get(i).getMarca();
		         md[i][5]=datos.get(i).getRef1();
		         md[i][6]=datos.get(i).getRef2();
			  }	         
		         
		  
		  return md;
		 }
	public void crearModeloTablaPruebas( Vector<String> datos) throws Exception{
		
	  
	  String[] nombre_Columnas_Paciente= {"ID","Nombre Prueba"};
	       
	  TableModel modelo_Tabla_Paciente = new DefaultTableModel(crearDatostablaPruebas(datos), nombre_Columnas_Paciente) {
	   public boolean isCellEditable(int row, int column) {return false;}};
	   tablaTipos.setModel(modelo_Tabla_Paciente);
	   tablaTipos.repaint();
	  
	  
	 }
	 
	public Object[][] crearDatostablaPruebas(Vector<String> datos){
	  Object[][] md = new Object[datos.size()][2];
	       
	  for (int i = 0; i < datos.size(); i++){
		 
	         md[i][1] =  datos.get(i);
	         md[i][0]=i+1;
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
