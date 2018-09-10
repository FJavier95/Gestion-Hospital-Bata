package Vista;

import java.awt.Checkbox;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import Control.ControladorAnadirPaciente;
import Control.ControladorAnadirTratamiento;
import Control.ControladorAnadirUsuario;
import Modelo.BBDD;
import Modelo.Usuario;

public class VentanaAnadirUsuario {
	
	ControladorAnadirUsuario controlador;
	public JLabel lbl_AP_Precio;
	public JTextField txt_AP_Precio;
	public JFrame frmAnadirConcepto;
	public JTable tablaTipos;
	public JLabel lbl_AP_Farmaco;
	public JTextField txt_AP_Farmaco;
	public JLabel lbl_AP_Descripcion;
	public JTextField txt_AP_Descripcion;
	public VentanaAdministrador vadmin;
	public Checkbox chec_Activos;
	public JButton btn_mas_usuario;
	public void addController(ControladorAnadirUsuario mc, VentanaAdministrador vadmin){
		controlador = mc;
		this.vadmin=vadmin;
	}
	
	public void crearVentana(){
	frmAnadirConcepto = new JFrame();
	frmAnadirConcepto.getContentPane().setFont(new Font("Tahoma", Font.PLAIN, fuenteRelativa(19)));
	frmAnadirConcepto.getContentPane().setBackground(Color.WHITE);
	frmAnadirConcepto.setTitle("Usuarios Administrador");
	frmAnadirConcepto.setBounds(AnchoRelativo(130), AltoRelativo(70), AnchoRelativo(1700), AltoRelativo(1000));
//	frmAnadirConcepto.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
	frmAnadirConcepto.getContentPane().setLayout(null);
	frmAnadirConcepto.setResizable(false);
	frmAnadirConcepto.addWindowListener(controlador);
	
	tablaTipos = new JTable();
	JScrollPane scrollPane = new JScrollPane();
	scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
	scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
	scrollPane.setBounds(AnchoRelativo(20), AltoRelativo(450), AnchoRelativo(1650), AltoRelativo(500));
	tablaTipos.addMouseListener(controlador);
	
	try {
		crearModeloTablaPruebas(BBDD.UsuariosAdministracion(1));
	} catch (Exception e) {
		e.printStackTrace();
	}
	scrollPane.setViewportView(tablaTipos);
	tablaTipos.setVisible(true);
	frmAnadirConcepto.getContentPane().add(scrollPane);
	chec_Activos= new Checkbox("Activos",true);
	chec_Activos.setBounds(AnchoRelativo(1350), AltoRelativo(400), AnchoRelativo(400), AltoRelativo(40));
	chec_Activos.addItemListener(controlador);
	chec_Activos.setVisible(true);
	frmAnadirConcepto.getContentPane().add(chec_Activos);
	
	btn_mas_usuario = new JButton("+ Usuario");
	btn_mas_usuario.setFont(new Font("Tahoma", Font.BOLD, fuenteRelativa(21)));	//Establece el tama�o y el tipo de letra que tendr� el bot�n
	btn_mas_usuario.setBackground(new Color(0, 48, 110));	//Establece el color del bot�n
	btn_mas_usuario.setOpaque(true);
	btn_mas_usuario.setBorderPainted(false);
	btn_mas_usuario.setForeground(Color.WHITE);	//Establece el color de la fuente
	btn_mas_usuario.setVisible(true);
	btn_mas_usuario.addActionListener(controlador);
	btn_mas_usuario.setBounds(AnchoRelativo(1350), AltoRelativo(100), AnchoRelativo(250), AltoRelativo(53));	//Establece el tama�o del bot�n
	frmAnadirConcepto.getContentPane().add(btn_mas_usuario);
	frmAnadirConcepto.setVisible(true);
	
	
	}
	public void crearModeloTablaPruebas( Vector<Usuario> datos) throws Exception{
		
		  
		  String[] nombre_Columnas_Paciente= {"ID","Nombre Usuario","Tipo Usuario"};
		       
		  TableModel modelo_Tabla_Paciente = new DefaultTableModel(crearDatostablaPruebas(datos), nombre_Columnas_Paciente) {
		   public boolean isCellEditable(int row, int column) {return false;}};
		   tablaTipos.setModel(modelo_Tabla_Paciente);
		   tablaTipos.repaint();
		  
		  
		 }
		 
		public Object[][] crearDatostablaPruebas(Vector<Usuario> datos){
		  Object[][] md = new Object[datos.size()][3];
		       
		  for (int i = 0; i < datos.size(); i++){
			 
		         md[i][1] =  datos.get(i).getNombre();
		         md[i][0]=datos.get(i).getId_usuario();
		         md[i][2]=usuariotoString(datos.get(i).getTipousa());
		         }
		  
		  return md;
		 }
		public String usuariotoString(int tipous){
			switch(tipous){
			case 0:return "Usuario Generico";
			
			case 1:return "Administrador";
			
			}
			return"";
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

