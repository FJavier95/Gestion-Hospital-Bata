package Vista;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JTextPane;

import Control.ControladorBuscarPaciente;
import Control.ControladorEditarDatos;
import Modelo.Paciente;

public class VentanaEditarDatos {
	
	public JFrame frmEditarDatos;
	ControladorEditarDatos controlador;
	public JPanel panel_datos_paciente;
	public JButton btn_AP_Aceptar;
	public JButton btn_AP_Cancelar;
	
	public JTextPane txtpnN_Asegurado;
	public JTextPane labelN_Asegurado;
	
	public JTextPane txt_AP_Poblacion;
	public JTextPane lbl_AP_Poblacion;
	
	public JTextPane txtpnDNI;
	public JTextPane labelDNI;
	
	public JTextPane txtpnNombre;
	public JTextPane LabelNombre;
	
	public JTextPane txtpnApellido1;
	public JTextPane LabelApellido1;
	
	public JTextPane txtpnApellido2;
	public JTextPane LabelApellido2;
	
	public JTextPane txtDireccion;
	public JTextPane LabelDireccion;
	
	public JTextPane txtpnTelefono;
	public JTextPane LabelTelefono;
	
	public JTextPane txtpnTelefono1;
	public JTextPane LabelTelefono1;
	
	public JTextPane txtpnTelefono2;
	public JTextPane LabelTelefono2;
	
	public JTextPane LabelN_Histoial;
	public JTextPane txtpnNhistorial;

	public JTextPane txtpnCondicion;
	public JComboBox cb_Condicion;
	 
	
	
	public void addController(ControladorEditarDatos ced) {
		
		  controlador=ced;
	}
	
	public void crearVentana(Paciente pac){
	
		frmEditarDatos = new JFrame();
		frmEditarDatos.getContentPane().setFont(new Font("Tahoma", Font.PLAIN, fuenteRelativa(19)));
		frmEditarDatos.getContentPane().setBackground(Color.WHITE);
		frmEditarDatos.setTitle("Editor De Datos");
		frmEditarDatos.setBounds(AnchoRelativo(130), AltoRelativo(70), AnchoRelativo(1700), AltoRelativo(1000));
		//frmEditarDatos.setAlwaysOnTop(true);
		//frmEditarDatos.setFocusable(true);
		//frmEditarDatos.setDefaultCloseOperation(defaultCloseOperation);
		frmEditarDatos.addWindowListener(controlador);
		frmEditarDatos.setResizable(false);
		
		btn_AP_Aceptar = new JButton("Aceptar");
		btn_AP_Aceptar.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btn_AP_Aceptar.setFont(new Font("Tahoma", Font.BOLD, fuenteRelativa(21)));	//Establece el tama�o y el tipo de letra que tendr� el bot�n
		btn_AP_Aceptar.setBackground(new Color(0, 0, 96));	//Establece el color del bot�n
		btn_AP_Aceptar.setOpaque(true);
		btn_AP_Aceptar.setBorderPainted(false);
		btn_AP_Aceptar.setForeground(Color.WHITE);
		btn_AP_Aceptar.setBounds(AnchoRelativo(1150), AltoRelativo(300), AnchoRelativo(200), AltoRelativo(50));
	    btn_AP_Aceptar.addActionListener(controlador);
	  frmEditarDatos.getContentPane().add(btn_AP_Aceptar);
	    
	 
		btn_AP_Cancelar = new JButton("Cancelar");
		btn_AP_Cancelar.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btn_AP_Cancelar.setFont(new Font("Tahoma", Font.BOLD, fuenteRelativa(21)));	//Establece el tama�o y el tipo de letra que tendr� el bot�n
		btn_AP_Cancelar.setBackground(new Color(0, 0, 96));	//Establece el color del bot�n
		btn_AP_Cancelar.setOpaque(true);
		btn_AP_Cancelar.setBorderPainted(false);
		btn_AP_Cancelar.setForeground(Color.WHITE);		
		btn_AP_Cancelar.setBounds(AnchoRelativo(1150), AltoRelativo(400), AnchoRelativo(200), AltoRelativo(50));
		btn_AP_Cancelar.addActionListener(controlador);
		frmEditarDatos.getContentPane().add(btn_AP_Cancelar);
		
		this.panelDatosPaciente(pac);
		
		
		
		frmEditarDatos.setVisible(true);
	}
	public void panelDatosPaciente(Paciente pac){
			
		panel_datos_paciente = new JPanel();
		panel_datos_paciente.setBackground(Color.WHITE);
		panel_datos_paciente.setBounds(AnchoRelativo(500), AltoRelativo(23), AnchoRelativo(1100), AltoRelativo(1000));

		panel_datos_paciente.setLayout(null);

		// Panel datos paciente apellido, nombre...

		
		
		
		txt_AP_Poblacion = new JTextPane();
		txt_AP_Poblacion.setText("Poblacion");
		txt_AP_Poblacion.setFont(new Font("Tahoma", Font.BOLD, fuenteRelativa(21)));
		txt_AP_Poblacion.setEditable(false);
		txt_AP_Poblacion.setBounds(AnchoRelativo(250), AltoRelativo(700), AnchoRelativo(100), AltoRelativo(26));
		panel_datos_paciente.add(txt_AP_Poblacion);
		
		lbl_AP_Poblacion = new JTextPane();
		lbl_AP_Poblacion.setBackground(Color.WHITE);
		lbl_AP_Poblacion.setText(pac.getPoblacion());
		lbl_AP_Poblacion.setEditable(true);
		lbl_AP_Poblacion.setBounds(AnchoRelativo(370), AltoRelativo(700), AnchoRelativo(200), AltoRelativo(26));
		panel_datos_paciente.add(lbl_AP_Poblacion);
		
		/*txtpnCondicion = new JTextPane();
		txtpnCondicion.setText("Condicion");
		txtpnCondicion.setFont(new Font("Tahoma", Font.BOLD, fuenteRelativa(20)));
		txtpnCondicion.setBounds(AnchoRelativo(250), AltoRelativo(765), AnchoRelativo(100), AltoRelativo(25));
		frmEditarDatos.getContentPane().add(txtpnCondicion);
		
		cb_Condicion = new JComboBox();
		cb_Condicion.setFont(new Font("Tahoma", Font.PLAIN, fuenteRelativa(20)));
		cb_Condicion.setModel(new DefaultComboBoxModel(new String[] {"Asegurado", "NO-Asegurado", "Jubilado", "Pensionista","Discapacitado"}));
		cb_Condicion.setBounds(AnchoRelativo(360), AltoRelativo(770), AnchoRelativo(130), AltoRelativo(26));
		frmEditarDatos.getContentPane().add(cb_Condicion);*/
	
		txtpnNombre = new JTextPane();
		txtpnNombre.setText("Nombre");
		txtpnNombre.setFont(new Font("Tahoma", Font.BOLD, fuenteRelativa(21)));
		txtpnNombre.setEditable(false);
		txtpnNombre.setBounds(AnchoRelativo(250), AltoRelativo(210), AnchoRelativo(120), AltoRelativo(40));
		panel_datos_paciente.add(txtpnNombre);

		LabelNombre = new JTextPane();
		LabelNombre.setText(pac.getNombre());
		LabelNombre.setEditable(true);
		LabelNombre.setBackground(Color.WHITE);
		LabelNombre.setBounds(AnchoRelativo(380), AltoRelativo(210), AnchoRelativo(200), AltoRelativo(40));
		panel_datos_paciente.add(LabelNombre);

		txtpnApellido1 = new JTextPane();
		txtpnApellido1.setEditable(false);
		txtpnApellido1.setText("Apellido1");
		txtpnApellido1.setFont(new Font("Tahoma", Font.BOLD, fuenteRelativa(21)));
		txtpnApellido1.setBounds(AnchoRelativo(250), AltoRelativo(280), AnchoRelativo(120), AltoRelativo(40));
		panel_datos_paciente.add(txtpnApellido1);

		LabelApellido1 = new JTextPane();
		LabelApellido1.setBackground(Color.WHITE);
		LabelApellido1.setEditable(true);
		LabelApellido1.setText(pac.getApellido1());
		LabelApellido1.setBounds(AnchoRelativo(380), AltoRelativo(280), AnchoRelativo(200), AltoRelativo(40));
		panel_datos_paciente.add(LabelApellido1);

		txtpnApellido2 = new JTextPane();
		txtpnApellido2.setText("Apellido2");
		txtpnApellido2.setFont(new Font("Tahoma", Font.BOLD, fuenteRelativa(21)));
		txtpnApellido2.setEditable(false);
		txtpnApellido2.setBounds(AnchoRelativo(250), AltoRelativo(350), AnchoRelativo(120), AltoRelativo(40));
		panel_datos_paciente.add(txtpnApellido2);

		LabelApellido2 = new JTextPane();
		LabelApellido2.setBackground(Color.WHITE);
		LabelApellido2.setEditable(true);
		LabelApellido2.setText(pac.getApellido2());
		LabelApellido2.setBounds(AnchoRelativo(380), AltoRelativo(350), AnchoRelativo(200), AltoRelativo(40));
		panel_datos_paciente.add(LabelApellido2);

		txtDireccion = new JTextPane();
		txtDireccion.setText("Direccion");
		txtDireccion.setFont(new Font("Tahoma", Font.BOLD, fuenteRelativa(21)));
		txtDireccion.setEditable(false);
		txtDireccion.setBounds(AnchoRelativo(250), AltoRelativo(420), AnchoRelativo(120), AltoRelativo(40));
		panel_datos_paciente.add(txtDireccion);

		LabelDireccion = new JTextPane();
		LabelDireccion.setBackground(Color.WHITE);
		LabelDireccion.setEditable(true);
		LabelDireccion.setText(pac.getDireccion());
		LabelDireccion.setBounds(AnchoRelativo(380), AltoRelativo(420), AnchoRelativo(200), AltoRelativo(40));
		panel_datos_paciente.add(LabelDireccion);

		txtpnTelefono = new JTextPane();
		txtpnTelefono.setText("Telefono1");
		txtpnTelefono.setFont(new Font("Tahoma", Font.BOLD, fuenteRelativa(21)));
		txtpnTelefono.setEditable(false);
		txtpnTelefono.setBounds(AnchoRelativo(250), AltoRelativo(490), AnchoRelativo(120), AltoRelativo(40));
		panel_datos_paciente.add(txtpnTelefono);

		LabelTelefono = new JTextPane();
		LabelTelefono.setBackground(Color.WHITE);
		LabelTelefono.setEditable(true);
		LabelTelefono.setText(String.valueOf(pac.getTelf1()));
		LabelTelefono.setBounds(AnchoRelativo(380), AltoRelativo(490), AnchoRelativo(200), AltoRelativo(40));
		panel_datos_paciente.add(LabelTelefono);

		txtpnTelefono1 = new JTextPane();
		txtpnTelefono1.setEditable(false);
		txtpnTelefono1.setText("Telefono2");
		txtpnTelefono1.setFont(new Font("Tahoma", Font.BOLD, fuenteRelativa(21)));
		txtpnTelefono1.setBounds(AnchoRelativo(250), AltoRelativo(560), AnchoRelativo(120), AltoRelativo(40));
		panel_datos_paciente.add(txtpnTelefono1);

		LabelTelefono1 = new JTextPane();
		LabelTelefono1.setEditable(true);
		LabelTelefono1.setBackground(Color.WHITE);
		LabelTelefono1.setText(String.valueOf(pac.getTelf2()));
		LabelTelefono1.setBounds(AnchoRelativo(380), AltoRelativo(560), AnchoRelativo(200), AltoRelativo(40));
		panel_datos_paciente.add(LabelTelefono1);

		txtpnTelefono2 = new JTextPane();
		txtpnTelefono2.setText("Telefono3");
		txtpnTelefono2.setFont(new Font("Tahoma", Font.BOLD, fuenteRelativa(21)));
		txtpnTelefono2.setEditable(false);
		txtpnTelefono2.setBounds(AnchoRelativo(250), AltoRelativo(630), AnchoRelativo(130), AltoRelativo(40));
		panel_datos_paciente.add(txtpnTelefono2);

		LabelTelefono2 = new JTextPane();
		LabelTelefono2.setBackground(Color.WHITE);
		LabelTelefono2.setEditable(true);
		LabelTelefono2.setText(String.valueOf(pac.getTelf3()));
		LabelTelefono2.setBounds(AnchoRelativo(390), AltoRelativo(630), AnchoRelativo(200), AltoRelativo(40));
		panel_datos_paciente.add(LabelTelefono2);

		
	  
		/*JTextPane txtpnCondicion = new JTextPane();
		txtpnCondicion.setText("Condicion:");
		txtpnCondicion.setFont(new Font("Tahoma", Font.BOLD, fuenteRelativa(21)));
		txtpnCondicion.setEditable(false);
		txtpnCondicion.setBounds(AnchoRelativo(140), AltoRelativo(770), AnchoRelativo(120), AltoRelativo(40));
		panel_datos_paciente.add(txtpnCondicion);

		JTextPane labelCondicion = new JTextPane();
		labelCondicion.setBackground(Color.WHITE);
		labelCondicion.setText(pac.getCondicionString());
		labelCondicion.setEditable(true);
		labelCondicion.setBounds(AnchoRelativo(300), AltoRelativo(770), AnchoRelativo(200), AltoRelativo(40));
		panel_datos_paciente.add(labelCondicion);*/
		
		panel_datos_paciente.repaint();
		frmEditarDatos.getContentPane().add(panel_datos_paciente);
		// frame.repaint();
	
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
