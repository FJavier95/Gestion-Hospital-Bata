package Vista;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ScrollPaneConstants;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import Control.ControladorAnadirPaciente;
import Control.ControladorVerTratamiento;
import Modelo.BBDD;
import Modelo.Fecha_Paciente;
import Modelo.Paciente;
import Modelo.Tratamiento;

public class VentanaVerTratamientos extends JFrame {
	ControladorVerTratamiento controlador;
	public JFrame frmVerTratamientos;
	public JTable tablaFechas;
	public JButton btn_Factura;
	
	public JTable tablaTratamientos;
	Paciente pac;
	public void addController(ControladorVerTratamiento mc){
		controlador = mc;
	}
	public void crearVistaTratamiento(Paciente pac){
			this.pac=pac;
			frmVerTratamientos = new JFrame();
			frmVerTratamientos.getContentPane().setFont(new Font("Tahoma", Font.PLAIN, fuenteRelativa(19)));
			frmVerTratamientos.getContentPane().setBackground(Color.WHITE);
			frmVerTratamientos.setTitle("Ver  Tratamientos "+pac.getNombre()+" "+pac.getApellido1()+" "+pac.getApellido2()+" "+pac.getDni());
			frmVerTratamientos.addWindowListener(controlador);
			frmVerTratamientos.setBounds(AnchoRelativo(130), AltoRelativo(70), AnchoRelativo(1700), AltoRelativo(1000));
		
			
			frmVerTratamientos.setResizable(false);
			
			tablaFechas = new JTable();
			JScrollPane scrollPane = new JScrollPane();
			scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
			scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
			scrollPane.setBounds(AnchoRelativo(20), AltoRelativo(20), AnchoRelativo(1200), AltoRelativo(400));
			tablaFechas.addMouseListener(controlador);
			scrollPane.add(tablaFechas);
			frmVerTratamientos.getContentPane().add(scrollPane);
			try {
				crearModeloTablaFecha(BBDD.obtener_fechasPaciente(pac.getId_Paciente()));
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			scrollPane.setViewportView(tablaFechas);

			tablaFechas.setVisible(true);
			btn_Factura = new JButton("Factura");
			btn_Factura.setFont(new Font("Tahoma", Font.BOLD, fuenteRelativa(21)));	//Establece el tama�o y el tipo de letra que tendr� el bot�n
			btn_Factura.setBackground(new Color(0, 48, 110));	//Establece el color del bot�n
			btn_Factura.setOpaque(true);
			btn_Factura.setBorderPainted(false);
			btn_Factura.setForeground(Color.WHITE);	//Establece el color de la fuente
			btn_Factura.setVisible(false);
			btn_Factura.addActionListener(controlador);
			btn_Factura.setBounds(AnchoRelativo(1350), AltoRelativo(100), AnchoRelativo(250), AltoRelativo(53));	//Establece el tama�o del bot�n
			frmVerTratamientos.getContentPane().add(btn_Factura);
			
			JPanel Tratamientos = new JPanel();
			Tratamientos.setBackground(Color.white);
			Tratamientos.setBounds(AnchoRelativo(20), AltoRelativo(450), AnchoRelativo(1400), AltoRelativo(500));
			Tratamientos.setLayout(null);
			frmVerTratamientos.getContentPane().add(Tratamientos);
			tablaTratamientos = new JTable();
			
			JScrollPane scrollPane1 = new JScrollPane();
			scrollPane1.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
			scrollPane1.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
			scrollPane1.setBounds(AnchoRelativo(20), AltoRelativo(450), AnchoRelativo(1400), AltoRelativo(500));
			
			scrollPane1.add(tablaTratamientos);
			Tratamientos.add(scrollPane1);
			
			scrollPane1.setViewportView(tablaTratamientos);
			tablaTratamientos.setVisible(true);	
			
			
			frmVerTratamientos.setVisible(true);
			
			
			
			
	}
	
	public void crearModeloTablaFecha( Vector<Fecha_Paciente> datos) throws Exception{
		
		  
		  String[] nombre_Columnas_Paciente= {"Ingreso","Fecha_Ingreso","Fecha_Alta"};
		       
		  TableModel modelo_Tabla_Paciente = new DefaultTableModel(crearDatostablaFecha(datos), nombre_Columnas_Paciente) {
		   public boolean isCellEditable(int row, int column) {return false;}};
		   tablaFechas.setModel(modelo_Tabla_Paciente);
		   tablaFechas.repaint();
		  
		  
		 }
		 
		public Object[][] crearDatostablaFecha(Vector<Fecha_Paciente> datos){
		  Object[][] md = new Object[datos.size()][5];
		  
		  
		  for (int i = 0; i < datos.size(); i++){
			 
		         md[i][0] =  i+1;
		         md[i][1] =  datos.get(i).getFecha_ingreso().toString();
		         if( !datos.get(i).getFecha_alta().toString().equals("2999-12-31 00:00:00.0")){
		        
		        	 
					md[i][2] =  datos.get(i).getFecha_alta().toString();
				
		         }else md[i][2]="Sin Fecha";
		         }
		  
		  return md;
		 }
		public void crearModeloTablaPruebas( Vector<Tratamiento> datos) throws Exception{
			
			  
			  String[] nombre_Columnas_Paciente= {"Fecha","Tipo de prueba","Nombre del concepto", "Cantidad","Precio-Unidad", "CosteTotal"};
			       
			  TableModel modelo_Tabla_Paciente = new DefaultTableModel(crearDatostablaPruebas(datos), nombre_Columnas_Paciente) {
			   public boolean isCellEditable(int row, int column) {return false;}};
			   tablaTratamientos.setModel(modelo_Tabla_Paciente);
			   tablaTratamientos.repaint();
			  
			  
			 }
			 
			public Object[][] crearDatostablaPruebas(Vector<Tratamiento> datos){
			  Object[][] md = new Object[datos.size()][6];
			  NumberFormat nf = NumberFormat.getInstance();
			  for (int i = 0; i < datos.size(); i++){
				 
			        
					md[i][0] =  datos.get(i).getFecha().toString();
					
			         md[i][1] =  Tratamiento.idTipoPruebaToString(datos.get(i).gettipo_Prueba());
			         md[i][2] =  datos.get(i).getId_Concepto();
			         md[i][3] =  datos.get(i).getCantidad();
			         md[i][4]=	nf.format(datos.get(i).getCoste());
			         md[i][5]=	nf.format(datos.get(i).getCoste() * datos.get(i).getCantidad());
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
