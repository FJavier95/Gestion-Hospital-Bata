package Vista;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import Control.ControladorAdministrador;
import Control.ControladorBuscarPaciente;

public class VentanaAdministrador extends JFrame {

		public JFrame frame;
		public ControladorAdministrador controlador;
		public JButton btnPacientes;
		public JButton btnUsuarios;
		public JButton btnPrecios;
		public JButton btnCambiaUsuario;
		public JButton btnTratamientos;
		
		public void addController(ControladorAdministrador cbp){
			  controlador=cbp;
			 }
		public void crearVentana() {
			frame = new JFrame();
			Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
			frame.setBounds(0, 0, screen.width, screen.height);
			frame.setFocusable(true);
			frame.setResizable(false);
			frame.setBackground(Color.GRAY);
			frame.getContentPane().setBackground(Color.WHITE);
			frame.setExtendedState(JFrame.MAXIMIZED_BOTH);//Maximizar la ventana
			frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			frame.getContentPane().setLayout(null);
			
			/*JPanel principal = new JPanel();
			principal.setBackground(Color.green);
			principal.setBounds(AnchoRelativo(1300), AltoRelativo(27), AnchoRelativo(650), AltoRelativo(1000));
			frame.getContentPane().add(principal);
			principal.setLayout(null);*/
			
				btnPacientes = new JButton("Pacientes");				
				btnPacientes.setFont(new Font("Tahoma", Font.BOLD, fuenteRelativa(21)));	//Establece el tama�o y el tipo de letra que tendr� el bot�n
				btnPacientes.setBackground(new Color(0, 48, 110));	//Establece el color del bot�n
				btnPacientes.setOpaque(true);
				btnPacientes.setBorderPainted(false);
				btnPacientes.setForeground(Color.WHITE);	//Establece el color de la fuente
				btnPacientes.setVisible(true);
				btnPacientes.addActionListener(controlador);
				btnPacientes.setBounds(AnchoRelativo(300), AltoRelativo(100), AnchoRelativo(250), AltoRelativo(53));	//Establece el tama�o del bot�n
				frame.add(btnPacientes);
				
				btnCambiaUsuario = new JButton("CambiarUsuario");				
				btnCambiaUsuario.setFont(new Font("Tahoma", Font.BOLD, fuenteRelativa(21)));	//Establece el tama�o y el tipo de letra que tendr� el bot�n
				btnCambiaUsuario.setBackground(new Color(0, 48, 110));	//Establece el color del bot�n
				btnCambiaUsuario.setOpaque(true);
				btnCambiaUsuario.setBorderPainted(false);
				btnCambiaUsuario.setForeground(Color.WHITE);	//Establece el color de la fuente
				btnCambiaUsuario.setVisible(true);
				btnCambiaUsuario.addActionListener(controlador);
				btnCambiaUsuario.setBounds(AnchoRelativo(300), AltoRelativo(200), AnchoRelativo(250), AltoRelativo(53));	//Establece el tama�o del bot�n
				frame.add(btnCambiaUsuario);
				
				btnUsuarios = new JButton("Usuarios");				
				btnUsuarios.setFont(new Font("Tahoma", Font.BOLD, fuenteRelativa(21)));	//Establece el tama�o y el tipo de letra que tendr� el bot�n
				btnUsuarios.setBackground(new Color(0, 48, 110));	//Establece el color del bot�n
				btnUsuarios.setOpaque(true);
				btnUsuarios.setBorderPainted(false);
				btnUsuarios.setForeground(Color.WHITE);	//Establece el color de la fuente
				btnUsuarios.setVisible(true);
				btnUsuarios.addActionListener(controlador);
				btnUsuarios.setBounds(AnchoRelativo(650), AltoRelativo(100), AnchoRelativo(250), AltoRelativo(53));	//Establece el tama�o del bot�n
				frame.add(btnUsuarios);
				
				btnPrecios = new JButton("Precios");				
				btnPrecios.setFont(new Font("Tahoma", Font.BOLD, fuenteRelativa(21)));	//Establece el tama�o y el tipo de letra que tendr� el bot�n
				btnPrecios.setBackground(new Color(0, 48, 110));	//Establece el color del bot�n
				btnPrecios.setOpaque(true);
				btnPrecios.setBorderPainted(false);
				btnPrecios.setForeground(Color.WHITE);	//Establece el color de la fuente
				btnPrecios.setVisible(true);
				btnPrecios.addActionListener(controlador);
				btnPrecios.setBounds(AnchoRelativo(1000), AltoRelativo(100), AnchoRelativo(250), AltoRelativo(53));	//Establece el tama�o del bot�n
				frame.add(btnPrecios);
				
				btnTratamientos = new JButton("Gastos Tratamientos");				
				btnTratamientos.setFont(new Font("Tahoma", Font.BOLD, fuenteRelativa(21)));	//Establece el tama�o y el tipo de letra que tendr� el bot�n
				btnTratamientos.setBackground(new Color(0, 48, 110));	//Establece el color del bot�n
				btnTratamientos.setOpaque(true);
				btnTratamientos.setBorderPainted(false);
				btnTratamientos.setForeground(Color.WHITE);	//Establece el color de la fuente
				btnTratamientos.setVisible(true);
				btnTratamientos.addActionListener(controlador);
				btnTratamientos.setBounds(AnchoRelativo(1350), AltoRelativo(100), AnchoRelativo(300), AltoRelativo(53));	//Establece el tama�o del bot�n
				frame.add(btnTratamientos);
				frame.setVisible(true);
			
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
