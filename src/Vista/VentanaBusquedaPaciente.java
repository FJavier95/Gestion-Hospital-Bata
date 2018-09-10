package Vista;
import Modelo.*;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;

import java.awt.FlowLayout;
import java.awt.BorderLayout;
import javax.swing.JPanel;
import javax.swing.JSplitPane;
import javax.swing.JToolBar;
import javax.swing.ScrollPaneConstants;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import Control.ControladorBuscarPaciente;

import javax.swing.JScrollPane;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;

import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import java.awt.GridLayout;
import java.awt.Image;

import javax.swing.JButton;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.BorderFactory;
import javax.swing.Box;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionListener;
import java.awt.image.ImageProducer;
import java.io.File;
import java.util.Vector;
import java.awt.event.ActionEvent;
import javax.swing.JTextPane;
import javax.swing.JComboBox;
import java.awt.Label;
import java.awt.Panel;
import javax.swing.JTable;
import java.awt.ScrollPane;
import java.awt.Toolkit;

import javax.swing.JTextField;

public class VentanaBusquedaPaciente extends JFrame {

	public JFrame frame;
	private JTable table;
	public JTextField textFieldbus;
	public ControladorBuscarPaciente controlador;
	public JButton Jbuttonbusqueda;
	public JMenuItem mntmNewMenuItem;
	public JMenuItem mntmNewMenuItem_1 ;
	public JButton btnTratamiento;
	public JButton verTratamientos;
	public JButton Editar_Datos;
	public JPanel panel_2;
	public JPanel panel_datos_paciente;
	

	
			public void addController(ControladorBuscarPaciente cbp){
			  controlador=cbp;
			 }
	public void crearVentana() {
		frame = new JFrame();
		frame.getContentPane().setBackground(Color.WHITE);
		Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
		frame.setBounds(0, 0, screen.width, screen.height);
		frame.setFocusable(true);
		frame.setResizable(false);
		
		frame.setExtendedState(JFrame.MAXIMIZED_BOTH);//Maximizar la ventana
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JMenuBar menuBar = new JMenuBar();
		menuBar.setBounds(0, 0, screen.width, 21);
		menuBar.setForeground(Color.WHITE);
		menuBar.setFont(new Font("Tahoma", Font.BOLD, fuenteRelativa(24)));
		frame.getContentPane().add(menuBar);
		
		//MENU BARR
		 mntmNewMenuItem = new JMenuItem("Nuevo paciente");
		 mntmNewMenuItem.setForeground(Color.WHITE);
		 mntmNewMenuItem.setOpaque(true);
		mntmNewMenuItem.setBackground(new Color(0, 48, 110));
		
		mntmNewMenuItem.addActionListener(controlador);
		menuBar.add(mntmNewMenuItem);
		
		 mntmNewMenuItem_1 = new JMenuItem("Cambiar Usuario");
		 mntmNewMenuItem_1.setForeground(Color.WHITE);
		mntmNewMenuItem_1.setBackground(new Color(0, 48, 110));
		
		mntmNewMenuItem_1.addActionListener(controlador);
		menuBar.add(mntmNewMenuItem_1);
		
		
		//Panel tabla busqueda
		JPanel busqueda = new JPanel();
		busqueda.setBounds(AnchoRelativo(1300), AltoRelativo(27), AnchoRelativo(650), AltoRelativo(1000));
		Image imagen = new ImageIcon("."+File.separator+"utilidades"+File.separator+"pacientesbusqueda.png").getImage();	
		imagen = imagen.getScaledInstance(busqueda.getWidth(), busqueda.getHeight(), Image.SCALE_SMOOTH);
		JLabel lbl_logo = new JLabel(new ImageIcon(imagen));	
		lbl_logo.setBounds(AnchoRelativo(1300), AltoRelativo(27), AnchoRelativo(650), AltoRelativo(1000));	
		busqueda.setOpaque(false);
		frame.getContentPane().add(busqueda);
		frame.getContentPane().add(lbl_logo);
		
		//frame.getContentPane().add(busqueda);
		busqueda.setLayout(null);
		
		table = new JTable();
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
		scrollPane.setBounds(AnchoRelativo(20), AltoRelativo(100), AnchoRelativo(590), AltoRelativo(700));
		scrollPane.setViewportView(table);
		table.addMouseListener(controlador);
		
		
		busqueda.add(scrollPane);
		
		
		textFieldbus = new JTextField();
		textFieldbus.setBounds(AnchoRelativo(20), AltoRelativo(40), AnchoRelativo(330), AltoRelativo(40));
		textFieldbus.setColumns(15);
		busqueda.add(textFieldbus);
		
		Jbuttonbusqueda = new JButton("Tonanm");
		Jbuttonbusqueda.setBounds(AnchoRelativo(350),AltoRelativo(40), AnchoRelativo(60), AltoRelativo(40));
		Jbuttonbusqueda.addActionListener(controlador);
		busqueda.add(Jbuttonbusqueda);
		
		frame.setVisible(true);
	}
	public void panelBotonesIngresado(){
		//Panel botones
				panel_2 = new JPanel();
				panel_2.setBackground(Color.white);
				panel_2.setBounds(AnchoRelativo(900), AltoRelativo(190), AnchoRelativo(400), AltoRelativo(600));
				
				
				
				panel_2.setLayout(null);
				
				 btnTratamiento = new JButton("+ Tratamientos");
				
				 btnTratamiento.setFont(new Font("Tahoma", Font.BOLD, fuenteRelativa(21)));	//Establece el tama�o y el tipo de letra que tendr� el bot�n
				 btnTratamiento.setBackground(new Color(0, 0, 96));	//Establece el color del bot�n
				 btnTratamiento.setOpaque(true);
				 btnTratamiento.setBorderPainted(false);
				 btnTratamiento.setForeground(Color.WHITE);
				btnTratamiento.setBounds(AnchoRelativo(130), AltoRelativo(80), AnchoRelativo(250), AltoRelativo(60));
				btnTratamiento.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
				btnTratamiento.addActionListener(controlador);
				panel_2.add(btnTratamiento);
				
				
				JButton verTratamientos = new JButton("Ver Tratamientos");
				
				verTratamientos.setBounds(AnchoRelativo(130), AltoRelativo(200),AnchoRelativo(250), AltoRelativo(60));
				verTratamientos.setFont(new Font("Tahoma", Font.BOLD, fuenteRelativa(21)));	//Establece el tama�o y el tipo de letra que tendr� el bot�n
				verTratamientos.setBackground(new Color(0, 48, 110));	//Establece el color del bot�n
				verTratamientos.setOpaque(true);
				verTratamientos.setBorderPainted(false);
				verTratamientos.setForeground(Color.WHITE);
				verTratamientos.addActionListener(controlador);
				panel_2.add(verTratamientos);
				
				JButton Factura = new JButton("Modificar Fianza");
				
				Factura.setBounds(AnchoRelativo(130), AltoRelativo(320), AnchoRelativo(250), AltoRelativo(60));
				Factura.setFont(new Font("Tahoma", Font.BOLD, fuenteRelativa(21)));	//Establece el tama�o y el tipo de letra que tendr� el bot�n
				Factura.setBackground(new Color(0, 48, 110));	//Establece el color del bot�n
				Factura.setOpaque(true);
				Factura.setBorderPainted(false);
				Factura.setForeground(Color.WHITE);
				Factura.addActionListener(controlador);
				panel_2.add(Factura);
				
				Editar_Datos = new JButton("Editar Datos");
				
				Editar_Datos.setFont(new Font("Tahoma", Font.BOLD, fuenteRelativa(21)));	//Establece el tama�o y el tipo de letra que tendr� el bot�n
				Editar_Datos.setBackground(new Color(0, 48, 110));	//Establece el color del bot�n
				Editar_Datos.setOpaque(true);
				Editar_Datos.setBorderPainted(false);
				Editar_Datos.setForeground(Color.WHITE);
				Editar_Datos.setBounds(AnchoRelativo(130), AltoRelativo(440), AnchoRelativo(250), AltoRelativo(60));
				Editar_Datos.addActionListener(controlador);
				panel_2.add(Editar_Datos);
				frame.getContentPane().add(panel_2);
				frame.repaint();
			
	}
	
	public void panelDatosPaciente(Paciente pac){
		
		 panel_datos_paciente = new JPanel();
		panel_datos_paciente.setBackground(Color.WHITE);
		panel_datos_paciente.setBounds(AnchoRelativo(10), AltoRelativo(23), AnchoRelativo(1300), AltoRelativo(1000));
		
		
		panel_datos_paciente.setLayout(null);
	
		
		//Panel datos paciente apellido, nombre...
		
		JTextPane txtpnN_Asegurado = new JTextPane();
		txtpnN_Asegurado.setText("N_Asegurado:");
		txtpnN_Asegurado.setFont(new Font("Tahoma", Font.BOLD, fuenteRelativa(21)));
		txtpnN_Asegurado.setEditable(false);
		txtpnN_Asegurado.setBounds(AnchoRelativo(710), AltoRelativo(350), AnchoRelativo(150), AltoRelativo(40));
		panel_datos_paciente.add(txtpnN_Asegurado);

		JTextPane labelN_Asegurado = new JTextPane();
		labelN_Asegurado.setBackground(Color.WHITE);
		labelN_Asegurado.setText(String.valueOf(pac.getN_Asegurado()));
		labelN_Asegurado.setFont(new Font("Tahoma", Font.PLAIN, fuenteRelativa(21)));
		labelN_Asegurado.setEditable(false);
		labelN_Asegurado.setBounds(AnchoRelativo(870), AltoRelativo(350), AnchoRelativo(100), AltoRelativo(40));
		panel_datos_paciente.add(labelN_Asegurado);
		
		JTextPane txtpnTe = new JTextPane();
		txtpnTe.setText("N_Historial:");
		txtpnTe.setFont(new Font("Tahoma", Font.BOLD, fuenteRelativa(21)));
		txtpnTe.setEditable(false);
		txtpnTe.setBounds(AnchoRelativo(100), AltoRelativo(350), AnchoRelativo(120), AltoRelativo(40));
		panel_datos_paciente.add(txtpnTe);
	
		JTextPane Direccion = new JTextPane();
		Direccion.setBackground(Color.WHITE);
		Direccion.setEditable(false);
		Direccion.setText(String.valueOf(pac.getN_historial()));
		Direccion.setFont(new Font("Tahoma", Font.PLAIN, fuenteRelativa(21)));
		Direccion.setBounds(AnchoRelativo(230), AltoRelativo(350), AnchoRelativo(100), AltoRelativo(40));
		panel_datos_paciente.add(Direccion);

		JTextPane txtpnDNI = new JTextPane();
		txtpnDNI.setText("DIP:");
		txtpnDNI.setFont(new Font("Tahoma", Font.BOLD, fuenteRelativa(21)));
		txtpnDNI.setEditable(false);
		txtpnDNI.setBounds(AnchoRelativo(430), AltoRelativo(350), AnchoRelativo(50), AltoRelativo(40));
		panel_datos_paciente.add(txtpnDNI);

		JTextPane labelDNI = new JTextPane();
		labelDNI.setBackground(Color.WHITE);
		labelDNI.setText(String.valueOf(pac.getDni()+" - "+pac.getLetraDni()));
		labelDNI.setFont(new Font("Tahoma", Font.PLAIN, fuenteRelativa(21)));
		labelDNI.setEditable(false);
		labelDNI.setBounds(AnchoRelativo(490), AltoRelativo(350), AnchoRelativo(150), AltoRelativo(40));
		panel_datos_paciente.add(labelDNI);
//-------------------------------------------------------------------------------------------------
		JTextPane txtpnNombre = new JTextPane();
		txtpnNombre.setText("Nombre:");
		txtpnNombre.setFont(new Font("Tahoma", Font.BOLD, fuenteRelativa(21)));
		txtpnNombre.setEditable(false);
		txtpnNombre.setBounds(AnchoRelativo(100), AltoRelativo(200), AnchoRelativo(100), AltoRelativo(40));
		panel_datos_paciente.add(txtpnNombre);

		JTextPane LabelNombre = new JTextPane();
		LabelNombre.setText(pac.getNombre());
		LabelNombre.setFont(new Font("Tahoma", Font.PLAIN, fuenteRelativa(21)));
		LabelNombre.setEditable(false);
		LabelNombre.setBackground(Color.WHITE);
		LabelNombre.setBounds(AnchoRelativo(210), AltoRelativo(200), AnchoRelativo(150), AltoRelativo(40));
		panel_datos_paciente.add(LabelNombre);

		JTextPane txtpnApellido = new JTextPane();
		txtpnApellido.setEditable(false);
		txtpnApellido.setText("Apellido1:");
		txtpnApellido.setFont(new Font("Tahoma", Font.BOLD, fuenteRelativa(21)));
		txtpnApellido.setBounds(AnchoRelativo(430), AltoRelativo(200), AnchoRelativo(100), AltoRelativo(40));
		panel_datos_paciente.add(txtpnApellido);

		JTextPane LabelApellido = new JTextPane();
		LabelApellido.setBackground(Color.WHITE);
		LabelApellido.setEditable(false);
		LabelApellido.setText(pac.getApellido1());
		LabelApellido.setFont(new Font("Tahoma", Font.PLAIN, fuenteRelativa(21)));
		LabelApellido.setBounds(AnchoRelativo(540), AltoRelativo(200), AnchoRelativo(150), AltoRelativo(40));
		panel_datos_paciente.add(LabelApellido);

		JTextPane txtpnApellido_1 = new JTextPane();
		txtpnApellido_1.setText("Apellido2:");
		txtpnApellido_1.setFont(new Font("Tahoma", Font.BOLD, fuenteRelativa(21)));
		txtpnApellido_1.setEditable(false);
		txtpnApellido_1.setBounds(AnchoRelativo(710), AltoRelativo(200), AnchoRelativo(100), AltoRelativo(40));
		panel_datos_paciente.add(txtpnApellido_1);

		JTextPane LabelApellido1 = new JTextPane();
		LabelApellido1.setBackground(Color.WHITE);
		LabelApellido1.setEditable(false);
		LabelApellido1.setText(pac.getApellido2());
		LabelApellido1.setFont(new Font("Tahoma", Font.PLAIN, fuenteRelativa(21)));
	LabelApellido1.setBounds(AnchoRelativo(820), AltoRelativo(200), AnchoRelativo(150), AltoRelativo(40));
		panel_datos_paciente.add(LabelApellido1);

	//--------------------------------------------------------------------------------------------------
		JTextPane txtpnTelefono = new JTextPane();
		txtpnTelefono.setText("Telefono:");
		txtpnTelefono.setFont(new Font("Tahoma", Font.BOLD, fuenteRelativa(21)));
		txtpnTelefono.setEditable(false);
		txtpnTelefono.setBounds(AnchoRelativo(100), AltoRelativo(800), AnchoRelativo(120), AltoRelativo(40));
		panel_datos_paciente.add(txtpnTelefono);

		JTextPane LabelTelefono = new JTextPane();
		LabelTelefono.setBackground(Color.WHITE);
		LabelTelefono.setEditable(false);
		LabelTelefono.setText(String.valueOf(pac.getTelf1()));
		LabelTelefono.setFont(new Font("Tahoma", Font.PLAIN, fuenteRelativa(21)));
		LabelTelefono.setBounds(AnchoRelativo(230), AltoRelativo(800), AnchoRelativo(150), AltoRelativo(40));
		panel_datos_paciente.add(LabelTelefono);

		JTextPane txtpnTelefono1 = new JTextPane();
		txtpnTelefono1.setEditable(false);
		txtpnTelefono1.setText("Telefono1:");
		txtpnTelefono1.setFont(new Font("Tahoma", Font.BOLD, fuenteRelativa(21)));
		txtpnTelefono1.setBounds(AnchoRelativo(430), AltoRelativo(800), AnchoRelativo(120), AltoRelativo(40));
		panel_datos_paciente.add(txtpnTelefono1);

		JTextPane LabelTelefono1 = new JTextPane();
		LabelTelefono1.setEditable(false);
		LabelTelefono1.setBackground(Color.WHITE);
		LabelTelefono1.setText(String.valueOf(pac.getTelf2()));
		LabelTelefono1.setFont(new Font("Tahoma", Font.PLAIN, fuenteRelativa(21)));
		LabelTelefono1.setBounds(AnchoRelativo(560), AltoRelativo(800), AnchoRelativo(150), AltoRelativo(40));
		panel_datos_paciente.add(LabelTelefono1);

		JTextPane txtpnTelefono2 = new JTextPane();
		txtpnTelefono2.setText("Telefono2:");
		txtpnTelefono2.setFont(new Font("Tahoma", Font.BOLD, fuenteRelativa(21)));
		txtpnTelefono2.setEditable(false);
		txtpnTelefono2.setBounds(AnchoRelativo(760), AltoRelativo(800), AnchoRelativo(120), AltoRelativo(40));
		panel_datos_paciente.add(txtpnTelefono2);

		JTextPane LabelTelefono2 = new JTextPane();
		LabelTelefono2.setBackground(Color.WHITE);
		LabelTelefono2.setEditable(false);
		LabelTelefono2.setText(String.valueOf(pac.getTelf3()));
		LabelTelefono2.setFont(new Font("Tahoma", Font.PLAIN, fuenteRelativa(21)));
		LabelTelefono2.setBounds(AnchoRelativo(890), AltoRelativo(800), AnchoRelativo(150), AltoRelativo(40));
		panel_datos_paciente.add(LabelTelefono2);
//-----------------------------------------------------------------------------------------------------------
		JTextPane txtpnNhistorial = new JTextPane();

		txtpnNhistorial.setText("Direccion:");
		txtpnNhistorial.setFont(new Font("Tahoma", Font.BOLD, fuenteRelativa(21)));
		txtpnNhistorial.setEditable(false);
		txtpnNhistorial.setBounds(AnchoRelativo(100), AltoRelativo(500), AnchoRelativo(120), AltoRelativo(40));
		panel_datos_paciente.add(txtpnNhistorial);
		

		JTextPane LabelN_Histoial = new JTextPane();
		LabelN_Histoial.setBackground(Color.WHITE);
		LabelN_Histoial.setEditable(false);
		LabelN_Histoial.setText(pac.getDireccion());
		LabelN_Histoial.setFont(new Font("Tahoma", Font.PLAIN, fuenteRelativa(21)));
		LabelN_Histoial.setBounds(AnchoRelativo(230), AltoRelativo(500), AnchoRelativo(200), AltoRelativo(40));
		panel_datos_paciente.add(LabelN_Histoial);
		
		JTextPane txtpnFianza = new JTextPane();
		txtpnFianza.setText("Poblacion:");
		txtpnFianza.setFont(new Font("Tahoma", Font.BOLD, fuenteRelativa(21)));
		txtpnFianza.setEditable(false);
		txtpnFianza.setBounds(AnchoRelativo(430), AltoRelativo(500), AnchoRelativo(120), AltoRelativo(40));
		panel_datos_paciente.add(txtpnFianza);

		JTextPane labelFianza = new JTextPane();
		labelFianza.setBackground(Color.WHITE);
		labelFianza.setText(String.valueOf(pac.getPoblacion()));
		labelFianza.setFont(new Font("Tahoma", Font.PLAIN, fuenteRelativa(21)));
		labelFianza.setEditable(false);
		labelFianza.setBounds(AnchoRelativo(560), AltoRelativo(500), AnchoRelativo(170), AltoRelativo(40));
		panel_datos_paciente.add(labelFianza);
//-----------------------------------------------------------------------------------------------
		JTextPane txtpnCondicion = new JTextPane();
		txtpnCondicion.setText("Condicion:");
		txtpnCondicion.setFont(new Font("Tahoma", Font.BOLD, fuenteRelativa(21)));
		txtpnCondicion.setEditable(false);
		txtpnCondicion.setBounds(AnchoRelativo(100), AltoRelativo(650), AnchoRelativo(120), AltoRelativo(40));
		panel_datos_paciente.add(txtpnCondicion);

		JTextPane labelCondicion = new JTextPane();
		labelCondicion.setBackground(Color.WHITE);
		labelCondicion.setText(pac.getCondicionString());
		labelCondicion.setFont(new Font("Tahoma", Font.PLAIN, fuenteRelativa(21)));
		labelCondicion.setEditable(false);
		labelCondicion.setBounds(AnchoRelativo(230), AltoRelativo(650), AnchoRelativo(150), AltoRelativo(40));
		panel_datos_paciente.add(labelCondicion);

		JTextPane txtSexo = new JTextPane();
		txtSexo.setText("Sexo:");
		txtSexo.setFont(new Font("Tahoma", Font.BOLD, fuenteRelativa(21)));
		txtSexo.setEditable(false);
		txtSexo.setBounds(AnchoRelativo(430), AltoRelativo(650), AnchoRelativo(70), AltoRelativo(40));
		panel_datos_paciente.add(txtSexo);

		JTextPane lablSexo = new JTextPane();
		lablSexo.setBackground(Color.WHITE);
		lablSexo.setText(pac.sexoString(pac.getSexo()));
		lablSexo.setFont(new Font("Tahoma", Font.PLAIN, fuenteRelativa(21)));
		//---------------------------------------------------------------------------------------------
		lablSexo.setEditable(false);
		lablSexo.setBounds(AnchoRelativo(510), AltoRelativo(650), AnchoRelativo(100), AltoRelativo(40));
		panel_datos_paciente.add(lablSexo);

		frame.getContentPane().add(panel_datos_paciente);

		panel_datos_paciente.add(labelDNI);

		JTextPane txtpnfian = new JTextPane();
		txtpnfian.setText("Fianza:");
		txtpnfian.setFont(new Font("Tahoma", Font.BOLD, fuenteRelativa(21)));
		txtpnfian.setEditable(false);
		txtpnfian.setBounds(AnchoRelativo(710), AltoRelativo(650), AnchoRelativo(80), AltoRelativo(40));
		panel_datos_paciente.add(txtpnfian);

		JTextPane labelfian = new JTextPane();
		labelfian.setBackground(Color.WHITE);
		labelfian.setText(String.valueOf(pac.getFianza()));
		labelfian.setFont(new Font("Tahoma", Font.PLAIN, fuenteRelativa(21)));
		labelfian.setEditable(false);
		labelfian.setBounds(AnchoRelativo(800), AltoRelativo(650), AnchoRelativo(150), AltoRelativo(40));
		panel_datos_paciente.add(labelfian);
		panel_datos_paciente.repaint();
		frame.getContentPane().add(panel_datos_paciente);
				  
				  
				 
				
		
		
	}
	

	public void crearModeloTabla( Vector<Paciente> datos) throws Exception{
				table.removeAll();
			  
			  String[] nombre_Columnas_Paciente= {"DNI","Nombre"};
			       
			  TableModel modelo_Tabla_Paciente = new DefaultTableModel(crearDatostabla(datos), nombre_Columnas_Paciente) {
			   public boolean isCellEditable(int row, int column) {return false;}};
			   table.setModel(modelo_Tabla_Paciente);
			   table.repaint();
			  
			  
			 }
			 
	public Object[][] crearDatostabla(Vector<Paciente> datos){
			  Object[][] md = new Object[datos.size()][2];
			       
			  for (int i = 0; i < datos.size(); i++){
			         md[i][0] =  datos.get(i).getDni();
			         md[i][1] = datos.get(i).getNombre();
			         
			         
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
