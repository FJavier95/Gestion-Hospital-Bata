package Vista;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.awt.Toolkit;
import java.io.File;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;

import Control.*;



public class VentanaLogin {
	
	ControladorLogin controlador;
	public JFrame frmIndex;
	public JTextField txt_usuario;
	public JPasswordField txt_password;
	public JButton btn_Aceptar;
	public JButton btn_About;
	public JButton btn_Recup;

	
	public void addController(ControladorLogin mc){
		controlador=mc;
	}
	
	//Funci�n para crear la ventana y sus componentes
	public void crearVentana(){
		//crea la ventana
		frmIndex = new JFrame();
		frmIndex.setIconImage(Toolkit.getDefaultToolkit().getImage("."+File.separator+"img"+File.separator+"apus_logo.jpg"));
		frmIndex.getContentPane().setBackground(Color.WHITE);	//Se establece como color de la ventana el blanco
		frmIndex.setTitle("Hospital Guinea");	//Titulo de la ventana
		frmIndex.setResizable(false);	//Se impide que se pueda redimensionar la ventana
		Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();	//Busca la resoluci�n de la pantalla
		frmIndex.setBounds(0, 0,screen.width,screen.height);	//Establece las dimensiones de la ventana
		frmIndex.setExtendedState(JFrame.MAXIMIZED_BOTH);	//Maximiza por defecto la ventana
		frmIndex.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);	//Estabalece la operaci�n de cierre por defecto
		frmIndex.getContentPane().setLayout(null);	//Obtiene el del contenido del JFrame y no establece ning�n tipo de Dise�o(Layout)
		frmIndex.setFocusable(true);
		frmIndex.addKeyListener(controlador);
		
		JLabel lbl_logo = new JLabel("");	
		lbl_logo.setBounds(AnchoRelativo(668), AltoRelativo(291), AnchoRelativo(480), AltoRelativo(148));	//Establece el tama�o de la etiqueta
		Image logo = new ImageIcon("."+File.separator+"utilidades"+File.separator+"logoasisa.png").getImage();	//Crea el objeto imagen y lo vincula a la imagen logo.png guardada en la carpeta img del proyecto
		logo = logo.getScaledInstance(lbl_logo.getWidth(), lbl_logo.getHeight(), Image.SCALE_SMOOTH);
		lbl_logo.setIcon(new ImageIcon(logo));	//Coloca la imagen en la etiqueta
		frmIndex.getContentPane().add(lbl_logo);	//Se a�ad
		//Crea el campo de texto
		txt_usuario = new JTextField();
		txt_usuario.setFont(new Font("Tahoma", Font.PLAIN, fuenteRelativa(21)));	//Establece el tama�o y tipo de letra con el que se escribir�
		txt_usuario.setBorder(new LineBorder(new Color(0, 48, 110), 5, true));	//Establece el tipo de linea y el color
		txt_usuario.setBounds(AnchoRelativo(693), AltoRelativo(514), AnchoRelativo(455), AltoRelativo(53));	//Establece el tama�o
		frmIndex.getContentPane().add(txt_usuario);	//Se a�ade el elemento al JFrame
		txt_usuario.setColumns(10);
		txt_usuario.addKeyListener(controlador);
		
		//Crea el campo para la contrase�a
		txt_password = new JPasswordField();
		txt_password.setFont(new Font("Tahoma", Font.PLAIN, fuenteRelativa(21)));	//Establece el tama�o y tipo de letra con el que se escribir�
		txt_password.setBorder(new LineBorder(new Color(0, 48, 110), 5, true));	//Establece el tipo de linea y el color
		txt_password.setBounds(AnchoRelativo(693), AltoRelativo(597), AnchoRelativo(455), AltoRelativo(53));	//Establece el tama�o
		frmIndex.getContentPane().add(txt_password);	//Se a�ade el elemento al JFrame
		txt_password.addKeyListener(controlador);	
		
		//Crea el bot�n
		btn_Aceptar = new JButton("Aceptar");	//Indica qu� est� escrito
		btn_Aceptar.setFont(new Font("Tahoma", Font.BOLD, fuenteRelativa(21)));	//Establece el tama�o y el tipo de letra que tendr� el bot�n
		btn_Aceptar.setBackground(new Color(0, 48, 110));	//Establece el color del bot�n
		btn_Aceptar.setOpaque(true);
		btn_Aceptar.setBorderPainted(false);
		btn_Aceptar.setForeground(Color.WHITE);	//Establece el color de la fuente
		btn_Aceptar.setBounds(AnchoRelativo(693), AltoRelativo(684), AnchoRelativo(455), AltoRelativo(53));	//Establece el tama�o del bot�n
		frmIndex.getContentPane().add(btn_Aceptar);	//Se a�ade el elemento al JFrame
		
		btn_Aceptar.addActionListener(controlador);	//A�ade el bot�n al ActionListener para despu�s asignarle su funci�n
		btn_About = new JButton("About");	//Indica qu� est� escrito
		btn_About.setFont(new Font("Tahoma", Font.BOLD, fuenteRelativa(21)));	//Establece el tama�o y el tipo de letra que tendr� el bot�n
		
		
		btn_About.setBorderPainted(false);
		btn_About.setForeground(new Color(0, 48, 110));	//Establece el color de la fuente
		btn_About.setBounds(AnchoRelativo(1500), AltoRelativo(54), AnchoRelativo(455), AltoRelativo(53));	//Establece el tama�o del bot�n
		frmIndex.getContentPane().add(btn_About);	//Se a�ade el elemento al JFrame
		
		btn_About.addActionListener(controlador);
		  
		frmIndex.setVisible(true);	//Se hace visible la ventana
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

