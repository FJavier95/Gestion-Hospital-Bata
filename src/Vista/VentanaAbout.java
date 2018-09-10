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

import Control.ControladorAbout;

public class VentanaAbout extends JFrame{
	public JFrame frmAbout;
	ControladorAbout controlador;
	public JLabel lbl_About_Logo;
	public JLabel lbl_About;
	public JButton btn_About_Aceptar;
	public JButton btn_About_mailto;
	public JLabel lbl_Licencia;
	
	public void addController(ControladorAbout mc){
		controlador=mc;
	}
	
	//Funci�n para crear la ventana y sus componentes
	public void crearVentana(){
		
		frmAbout = new JFrame();
		frmAbout.setIconImage(Toolkit.getDefaultToolkit().getImage("." + File.separator + "img" + File.separator + "apus_logo.jpg"));
		frmAbout.getContentPane().setBackground(Color.WHITE);	//Se establece como color de la ventana el blanco
		frmAbout.setTitle("About");	//Titulo de la ventana
		frmAbout.setBounds(1300, 100, 300, 300);	//Se establece el tama�o
		frmAbout.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);	//Se establece su funcion por defecto al cerrar con la "X"
		frmAbout.getContentPane().setLayout(null);	//Obtiene el del contenido del JFrame y no establece ning�n tipo de Dise�o(Layout)
		
		frmAbout.setFocusable(true);
		
		//Crea la etiqueta
		lbl_About = new JLabel("<html>Programa realizado por:<br><br>Adrian Gallego Sanchez<br><br>Javier Garcia Alvarez<br><br>Version 0.3<br>Realizado en el 2017</html>");
		//Se introduce el texto que va a salir por pantalla
		lbl_About.setBounds(100, 10, 300, 200);	//Se establece el tama�o
		lbl_About.setFont(new Font("Tahoma", Font.PLAIN, fuenteRelativa(19)));	//Establece el tama�o y el tipo de letra que tendr� el bot�n
			//Establece el tama�o y t  ipo de letra
		frmAbout.getContentPane().add(lbl_About);	//Se a�ade el elemento al JFrame
		
		//Crea la etiqueta para la imagen
		
		
		
		frmAbout.setVisible(true);	//Se hace visible la ventana
	}
	
	public int AltoRelativo(int altura) {
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

