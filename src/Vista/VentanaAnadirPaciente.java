package Vista;


import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.io.File;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import com.toedter.calendar.JDateChooser;
import com.toedter.calendar.JDayChooser;
import com.toedter.calendar.JMonthChooser;
import com.toedter.calendar.JYearChooser;

import Control.*;

public class VentanaAnadirPaciente extends JFrame{
	ControladorAnadirPaciente controlador;
	
	public JFrame frmAnadirPaciente;

	public JLabel lbl_AP_NHistorial;
	public JTextField txt_AP_NHistorial;
	public JLabel lbl_AP_Nombre;
	public JTextField txt_AP_Nombre;
	public JLabel lbl_AP_Apellido1;
	public JTextField txt_AP_Apellido1;
	public JLabel lbl_AP_Apellido2;
	public JTextField txt_AP_Apellido2;
	public JLabel lbl_AP_Dni;
	public JTextField txt_AP_Dni;
	public JLabel lbl_AP_Condicion;
	public JComboBox cb_AP_Condicion;
	public JLabel lbl_AP_Sexo;
	public JComboBox cb_AP_Sexo;
	public JLabel lbl_AP_Fianza;
	public JTextField txt_AP_Fianza;
	public JLabel lbl_AP_Direccion;
	public JTextField txt_AP_Direccion;
	public JLabel lbl_AP_Poblacion;
	public JTextField txt_AP_Poblacion;
	public JLabel lbl_AP_Telefono1;
	public JTextField txt_AP_Telefono1;	
	public JLabel lbl_AP_Telefono2;
	public JTextField txt_AP_Telefono2;	
	public JLabel lbl_AP_Telefono3;
	public JTextField txt_AP_Telefono3;	
	public JLabel lbl_N_Asegurado;
	public JTextField txt_N_Asegurado;	
	
	
	//public JLabel lbl_AP_Nacimiento;
	public JLabel lbl_AP_Inicio;
	
	///public JLabel lbl_AP_Altura;
	//public JComboBox cb_AP_Altura;
	//public JComboBox cb_AP_Cent;
	public JButton btn_AP_Aceptar;
	public JButton btn_AP_Cancelar;
	public JTextField txt_AP_BuscarImagen;
	public JButton btn_AP_BuscarImagen;
	public JDateChooser dateChooser_AP_Nacimiento;
	public JDateChooser dateChooser_AP_Inicio;
	
	
	public void addController(ControladorAnadirPaciente mc){
		controlador = mc;
	}
	
	
	
	//Funciï¿½n para crear la ventana y sus componentes
	public void crearVentana(){
		frmAnadirPaciente = new JFrame();
		frmAnadirPaciente.getContentPane().setFont(new Font("Tahoma", Font.PLAIN, fuenteRelativa(19)));
		frmAnadirPaciente.getContentPane().setBackground(Color.WHITE);
		frmAnadirPaciente.setTitle("Nuevo Paciente");
		frmAnadirPaciente.setBounds(AnchoRelativo(100), AltoRelativo(100), AnchoRelativo(1100), AltoRelativo(700));
	//	frmAnadirPaciente.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		frmAnadirPaciente.getContentPane().setLayout(null);
		frmAnadirPaciente.setResizable(false);
		frmAnadirPaciente.addWindowListener(controlador);
		//Primera Linea
		lbl_AP_NHistorial = new JLabel("Nº Historial:");
		lbl_AP_NHistorial.setFont(new Font("Tahoma", Font.PLAIN, fuenteRelativa(20)));
		lbl_AP_NHistorial.setBounds(AnchoRelativo(50), AltoRelativo(100), AnchoRelativo(110), AltoRelativo(22));
		frmAnadirPaciente.getContentPane().add(lbl_AP_NHistorial);
		
		txt_AP_NHistorial = new JTextField();
		txt_AP_NHistorial.setFont(new Font("Tahoma", Font.PLAIN, fuenteRelativa(19)));
		txt_AP_NHistorial.setBounds(AnchoRelativo(165), AltoRelativo(100), AnchoRelativo(100), AltoRelativo(26));
		frmAnadirPaciente.getContentPane().add(txt_AP_NHistorial);
		txt_AP_NHistorial.setColumns(10);
		
		lbl_AP_Nombre = new JLabel("Nombre:");
		lbl_AP_Nombre.setFont(new Font("Tahoma", Font.PLAIN, fuenteRelativa(20)));
		lbl_AP_Nombre.setBounds(AnchoRelativo(285), AltoRelativo(100), AnchoRelativo(85), AltoRelativo(22));
		frmAnadirPaciente.getContentPane().add(lbl_AP_Nombre);
		
		txt_AP_Nombre = new JTextField();
		txt_AP_Nombre.setFont(new Font("Tahoma", Font.PLAIN, fuenteRelativa(19)));
		txt_AP_Nombre.setBounds(AnchoRelativo(375), AltoRelativo(100), AnchoRelativo(150), AltoRelativo(26));
		frmAnadirPaciente.getContentPane().add(txt_AP_Nombre);
		txt_AP_Nombre.setColumns(10);
		
		lbl_AP_Apellido1 = new JLabel("Apellido1:");
		lbl_AP_Apellido1.setFont(new Font("Tahoma", Font.PLAIN, fuenteRelativa(20)));
		lbl_AP_Apellido1.setBounds(AnchoRelativo(535), AltoRelativo(100), AnchoRelativo(85), AltoRelativo(22));
		frmAnadirPaciente.getContentPane().add(lbl_AP_Apellido1);
		
		txt_AP_Apellido1 = new JTextField();
		txt_AP_Apellido1.setFont(new Font("Tahoma", Font.PLAIN, fuenteRelativa(19)));
		txt_AP_Apellido1.setColumns(10);
		txt_AP_Apellido1.setBounds(AnchoRelativo(625), AltoRelativo(100), AnchoRelativo(150), AltoRelativo(26));
		frmAnadirPaciente.getContentPane().add(txt_AP_Apellido1);
		

		lbl_AP_Apellido2 = new JLabel("Apellido2:");
		lbl_AP_Apellido2.setFont(new Font("Tahoma", Font.PLAIN, fuenteRelativa(20)));
		lbl_AP_Apellido2.setBounds(AnchoRelativo(785), AltoRelativo(100), AnchoRelativo(85), AltoRelativo(22));
		frmAnadirPaciente.getContentPane().add(lbl_AP_Apellido2);
		
		txt_AP_Apellido2 = new JTextField();
		txt_AP_Apellido2.setFont(new Font("Tahoma", Font.PLAIN, fuenteRelativa(19)));
		txt_AP_Apellido2.setColumns(10);
		txt_AP_Apellido2.setBounds(AnchoRelativo(875), AltoRelativo(100), AnchoRelativo(150), AltoRelativo(26));
		frmAnadirPaciente.getContentPane().add(txt_AP_Apellido2);
		//Segunda Linea
		lbl_AP_Dni = new JLabel("DIP:");
		lbl_AP_Dni.setFont(new Font("Tahoma", Font.PLAIN, fuenteRelativa(20)));
		lbl_AP_Dni.setBounds(AnchoRelativo(50), AltoRelativo(200), AnchoRelativo(50), AltoRelativo(22));
		frmAnadirPaciente.getContentPane().add(lbl_AP_Dni);
		
		txt_AP_Dni = new JTextField();
		txt_AP_Dni.setFont(new Font("Tahoma", Font.PLAIN, fuenteRelativa(19)));
		txt_AP_Dni.setColumns(10);
		txt_AP_Dni.setBounds(AnchoRelativo(105), AltoRelativo(200), AnchoRelativo(160), AltoRelativo(26));
		frmAnadirPaciente.getContentPane().add(txt_AP_Dni);
		
		lbl_AP_Condicion = new JLabel("Condicion:");
		lbl_AP_Condicion.setFont(new Font("Tahoma", Font.PLAIN, fuenteRelativa(20)));
		lbl_AP_Condicion.setBounds(AnchoRelativo(285), AltoRelativo(200), AnchoRelativo(100), AltoRelativo(22));
		frmAnadirPaciente.getContentPane().add(lbl_AP_Condicion);
		
		cb_AP_Condicion = new JComboBox();
		cb_AP_Condicion.setFont(new Font("Tahoma", Font.PLAIN, 12));
		cb_AP_Condicion.setModel(new DefaultComboBoxModel(new String[] {"Asegurado", "NO-Asegurado", "Jubilado", "Pensionista","Discapacitado"}));
		cb_AP_Condicion.setBounds(AnchoRelativo(390), AltoRelativo(200), AnchoRelativo(130), AltoRelativo(26));
		frmAnadirPaciente.getContentPane().add(cb_AP_Condicion);
		
		lbl_AP_Sexo = new JLabel("Sexo:");
		lbl_AP_Sexo.setFont(new Font("Tahoma", Font.PLAIN, fuenteRelativa(19)));
		lbl_AP_Sexo.setBounds(AnchoRelativo(550), AltoRelativo(200), AnchoRelativo(50), AltoRelativo(22));
		frmAnadirPaciente.getContentPane().add(lbl_AP_Sexo);
		
		cb_AP_Sexo = new JComboBox();
		cb_AP_Sexo.setFont(new Font("Tahoma", Font.PLAIN, 12));
		cb_AP_Sexo.setModel(new DefaultComboBoxModel(new String[] {"Mujer", "Hombre"}));
		cb_AP_Sexo.setBounds(AnchoRelativo(605), AltoRelativo(200), AnchoRelativo(100), AltoRelativo(26));
		frmAnadirPaciente.getContentPane().add(cb_AP_Sexo);
				
		lbl_N_Asegurado = new JLabel("N_Asegurado:");		
		lbl_N_Asegurado.setFont(new Font("Tahoma", Font.PLAIN, fuenteRelativa(19)));
		lbl_N_Asegurado.setBounds(AnchoRelativo(735), AltoRelativo(200), AnchoRelativo(120), AltoRelativo(22));
		frmAnadirPaciente.getContentPane().add(lbl_N_Asegurado);
		
		txt_N_Asegurado = new JTextField();
		txt_N_Asegurado.setFont(new Font("Tahoma", Font.PLAIN, fuenteRelativa(19)));
		txt_N_Asegurado.setColumns(10);
		txt_N_Asegurado.setBounds(AnchoRelativo(860), AltoRelativo(200), AnchoRelativo(150), AltoRelativo(26));
		frmAnadirPaciente.getContentPane().add(txt_N_Asegurado);
		//Tercera Linea
		lbl_AP_Direccion = new JLabel("Direccion:");
		lbl_AP_Direccion.setFont(new Font("Tahoma", Font.PLAIN, fuenteRelativa(19)));
		lbl_AP_Direccion.setBounds(AnchoRelativo(50), AltoRelativo(300), AnchoRelativo(85), AltoRelativo(22));
		frmAnadirPaciente.getContentPane().add(lbl_AP_Direccion);

		txt_AP_Direccion = new JTextField();
		txt_AP_Direccion.setFont(new Font("Tahoma", Font.PLAIN, fuenteRelativa(19)));
		txt_AP_Direccion.setColumns(10);
		txt_AP_Direccion.setBounds(AnchoRelativo(140), AltoRelativo(300), AnchoRelativo(300), AltoRelativo(26));
		frmAnadirPaciente.getContentPane().add(txt_AP_Direccion);

		lbl_AP_Poblacion = new JLabel("Poblacion:");
		lbl_AP_Poblacion.setFont(new Font("Tahoma", Font.PLAIN, fuenteRelativa(19)));
		lbl_AP_Poblacion.setBounds(AnchoRelativo(540), AltoRelativo(300), AnchoRelativo(100), AltoRelativo(22));
		frmAnadirPaciente.getContentPane().add(lbl_AP_Poblacion);
		
		txt_AP_Poblacion = new JTextField();
		txt_AP_Poblacion.setFont(new Font("Tahoma", Font.PLAIN, fuenteRelativa(19)));
		txt_AP_Poblacion.setColumns(10);
		txt_AP_Poblacion.setBounds(AnchoRelativo(645), AltoRelativo(300), AnchoRelativo(300), AltoRelativo(26));
		frmAnadirPaciente.getContentPane().add(txt_AP_Poblacion);
		//Cuearta Linea
		lbl_AP_Telefono1 = new JLabel("Telefono1:");
		lbl_AP_Telefono1.setFont(new Font("Tahoma", Font.PLAIN, fuenteRelativa(19)));
		lbl_AP_Telefono1.setBounds(AnchoRelativo(50), AltoRelativo(400), AnchoRelativo(100), AltoRelativo(22));
		frmAnadirPaciente.getContentPane().add(lbl_AP_Telefono1);
		
		txt_AP_Telefono1 = new JTextField();
		txt_AP_Telefono1.setFont(new Font("Tahoma", Font.PLAIN, fuenteRelativa(19)));
		txt_AP_Telefono1.setColumns(10);
		txt_AP_Telefono1.setBounds(AnchoRelativo(155), AltoRelativo(400), AnchoRelativo(200), AltoRelativo(26));
		frmAnadirPaciente.getContentPane().add(txt_AP_Telefono1);
		
		lbl_AP_Telefono2 = new JLabel("Telefono2:");
		lbl_AP_Telefono2.setFont(new Font("Tahoma", Font.PLAIN, fuenteRelativa(19)));
		lbl_AP_Telefono2.setBounds(AnchoRelativo(385), AltoRelativo(400), AnchoRelativo(100), AltoRelativo(22));
		frmAnadirPaciente.getContentPane().add(lbl_AP_Telefono2);
		
		txt_AP_Telefono2 = new JTextField();
		txt_AP_Telefono2.setFont(new Font("Tahoma", Font.PLAIN, fuenteRelativa(19)));
		txt_AP_Telefono2.setColumns(10);
		txt_AP_Telefono2.setBounds(AnchoRelativo(490), AltoRelativo(400), AnchoRelativo(200), AltoRelativo(26));
		frmAnadirPaciente.getContentPane().add(txt_AP_Telefono2);
		
		lbl_AP_Telefono3 = new JLabel("Telefono3:");		
		lbl_AP_Telefono3.setFont(new Font("Tahoma", Font.PLAIN, fuenteRelativa(19)));
		lbl_AP_Telefono3.setBounds(AnchoRelativo(720), AltoRelativo(400), AnchoRelativo(100), AltoRelativo(22));
		frmAnadirPaciente.getContentPane().add(lbl_AP_Telefono3);
		
		txt_AP_Telefono3 = new JTextField();
		txt_AP_Telefono3.setFont(new Font("Tahoma", Font.PLAIN, fuenteRelativa(19)));
		txt_AP_Telefono3.setColumns(10);
		txt_AP_Telefono3.setBounds(AnchoRelativo(825), AltoRelativo(400), AnchoRelativo(200), AltoRelativo(26));
		frmAnadirPaciente.getContentPane().add(txt_AP_Telefono3);
		
		/*lbl_AP_Fianza = new JLabel("Fianza:");
		lbl_AP_Fianza.setFont(new Font("Tahoma", Font.PLAIN, fuenteRelativa(19)));
		lbl_AP_Fianza.setBounds(AnchoRelativo(50), AltoRelativo(500), AnchoRelativo(60), AltoRelativo(22));
		frmAnadirPaciente.getContentPane().add(lbl_AP_Fianza);
		
		txt_AP_Fianza = new JTextField();
		txt_AP_Fianza.setFont(new Font("Tahoma", Font.PLAIN, fuenteRelativa(19)));
		txt_AP_Fianza.setColumns(10);
		txt_AP_Fianza.setBounds(AnchoRelativo(115), AltoRelativo(500), AnchoRelativo(100), AltoRelativo(26));
		frmAnadirPaciente.getContentPane().add(txt_AP_Fianza);
		*/
		
	
		
		/*lbl_AP_Nacimiento = new JLabel("Fecha de nacimiento:");
		lbl_AP_Nacimiento.setFont(new Font("Tahoma", Font.PLAIN, fuenteRelativa(19)));;
		lbl_AP_Nacimiento.setBounds(AnchoRelativo(43), AltoRelativo(330), AnchoRelativo(183), AltoRelativo(16));
		frmAnadirPaciente.getContentPane().add(lbl_AP_Nacimiento);

		
		dateChooser_AP_Inicio = new JDateChooser();
		dateChooser_AP_Inicio.setFont(new Font("Tahoma", Font.PLAIN, fuenteRelativa(19)));
		dateChooser_AP_Inicio.getCalendarButton().setFont(new Font("Tahoma", Font.PLAIN, 19));
		dateChooser_AP_Inicio.setBounds(AnchoRelativo(238), AltoRelativo(416), AnchoRelativo(168), AltoRelativo(22));
		//dateChooser_AP_Inicio.setMaxSelectableDate();
		frmAnadirPaciente.getContentPane().add(dateChooser_AP_Inicio);

		
		dateChooser_AP_Nacimiento = new JDateChooser();
		dateChooser_AP_Nacimiento.setFont(new Font("Tahoma", Font.PLAIN, fuenteRelativa(19)));
		dateChooser_AP_Nacimiento.getCalendarButton().setFont(new Font("Tahoma", Font.PLAIN, 19));
		dateChooser_AP_Nacimiento.setBounds(AnchoRelativo(238), AltoRelativo(331), AnchoRelativo(168), AltoRelativo(22));
		frmAnadirPaciente.getContentPane().add(dateChooser_AP_Nacimiento);
		
		
*/
		
			
		btn_AP_Aceptar = new JButton("Aceptar");
		btn_AP_Aceptar.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btn_AP_Aceptar.setFont(new Font("Tahoma", Font.BOLD, fuenteRelativa(21)));	//Establece el tamaï¿½o y el tipo de letra que tendrï¿½ el botï¿½n
		btn_AP_Aceptar.setBackground(new Color(0, 0, 96));	//Establece el color del botï¿½n
		btn_AP_Aceptar.setOpaque(true);
		btn_AP_Aceptar.setBorderPainted(false);
		btn_AP_Aceptar.setForeground(Color.WHITE);
		btn_AP_Aceptar.setBounds(AnchoRelativo(650), AltoRelativo(570), AnchoRelativo(180), AltoRelativo(33));
	    btn_AP_Aceptar.addActionListener(controlador);
	    frmAnadirPaciente.getContentPane().add(btn_AP_Aceptar);
	  
	   
	    
		btn_AP_Cancelar = new JButton("Cancelar");
		btn_AP_Cancelar.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btn_AP_Cancelar.setFont(new Font("Tahoma", Font.BOLD, fuenteRelativa(21)));	//Establece el tamaï¿½o y el tipo de letra que tendrï¿½ el botï¿½n
		btn_AP_Cancelar.setBackground(new Color(0, 0, 96));	//Establece el color del botï¿½n
		btn_AP_Cancelar.setOpaque(true);
		btn_AP_Cancelar.setBorderPainted(false);
		btn_AP_Cancelar.setForeground(Color.WHITE);		
		btn_AP_Cancelar.setBounds(AnchoRelativo(850), AltoRelativo(570), AnchoRelativo(180), AltoRelativo(32));
	
		btn_AP_Cancelar.addActionListener(controlador);
		 frmAnadirPaciente.getContentPane().add(btn_AP_Cancelar);
		 frmAnadirPaciente.add(btn_AP_Cancelar);
		
		frmAnadirPaciente.setIconImage(Toolkit.getDefaultToolkit().getImage(("."+File.separator+"img"+File.separator+"apus_logo.jpg"))); 
		frmAnadirPaciente.setVisible(true);
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
