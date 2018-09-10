package Vista;

import java.awt.Checkbox;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ScrollPaneConstants;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import Control.ControladorAdministradorPacientes;
import Control.ControladorAnadirPaciente;
import Control.ControladorVerTratamiento;
import Modelo.Fecha_Paciente;
import Modelo.Paciente;

	

public class VentanaAdministradorPacientes extends JFrame{
		public JFrame frmVerPacientes;
		ControladorAdministradorPacientes controlador;
		public JTable tablaPacientes;
		public Checkbox chec_Activos;
		public JButton btn_addPaciente;
		public void addController(ControladorAdministradorPacientes mc){
			controlador = mc;
			}
	public void crearVentana(Vector<Paciente> pacientes1){	
	
		frmVerPacientes = new JFrame();
		frmVerPacientes.getContentPane().setFont(new Font("Tahoma", Font.PLAIN, fuenteRelativa(19)));
		frmVerPacientes.getContentPane().setBackground(Color.WHITE);
		frmVerPacientes.setTitle("Ver Pacientes");
		frmVerPacientes.setBounds(AnchoRelativo(130), AltoRelativo(40), AnchoRelativo(1700), AltoRelativo(1000));
		frmVerPacientes.addWindowListener(controlador);
		frmVerPacientes.getContentPane().setLayout(null);
		
		tablaPacientes = new JTable();
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
		scrollPane.setBounds(AnchoRelativo(10), AltoRelativo(300), AnchoRelativo(1700), AltoRelativo(750));
		tablaPacientes.addMouseListener(controlador);
		
		scrollPane.add(tablaPacientes);
		frmVerPacientes.getContentPane().add(scrollPane);
		//pacientes.add(scrollPane);
		
		try {
			this.crearModeloTablaPaciente(pacientes1);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		scrollPane.setViewportView(tablaPacientes);
		chec_Activos= new Checkbox("Activos",true);
		chec_Activos.setBounds(AnchoRelativo(1350), AltoRelativo(250), AnchoRelativo(400), AltoRelativo(40));
		chec_Activos.addItemListener(controlador);
		chec_Activos.setVisible(true);
		frmVerPacientes.getContentPane().add(chec_Activos);
		btn_addPaciente = new JButton("+ Paciente");
		btn_addPaciente.setFont(new Font("Tahoma", Font.BOLD, fuenteRelativa(21)));	//Establece el tama�o y el tipo de letra que tendr� el bot�n
		btn_addPaciente.setBackground(new Color(0, 48, 110));	//Establece el color del bot�n
		btn_addPaciente.setOpaque(true);
		btn_addPaciente.setBorderPainted(false);
		btn_addPaciente.setForeground(Color.WHITE);	//Establece el color de la fuente
		btn_addPaciente.setVisible(true);
		btn_addPaciente.addActionListener(controlador);
		btn_addPaciente.setBounds(AnchoRelativo(1350), AltoRelativo(100), AnchoRelativo(250), AltoRelativo(53));	//Establece el tama�o del bot�n
		frmVerPacientes.getContentPane().add(btn_addPaciente);
		tablaPacientes.setVisible(true);
		frmVerPacientes.setVisible(true);
		
	
		
	}
	
	public void crearModeloTablaPaciente( Vector<Paciente> pacientes) throws Exception{
		
		  
		  String[] nombre_Columnas_Paciente= {"Nombre","Telefono","Numero Asegurado", "DIP", "Numero Historial","Actividad"};
		       
		  TableModel modelo_Tabla_Paciente = new DefaultTableModel(crearDatostablaPaciente(pacientes), nombre_Columnas_Paciente) {
		   public boolean isCellEditable(int row, int column) {return false;}};
		   tablaPacientes.setModel(modelo_Tabla_Paciente);
		   tablaPacientes.repaint();
		  
		  
		 }
		 
		public Object[][] crearDatostablaPaciente(Vector<Paciente> pacientes){
		  Object[][] md = new Object[pacientes.size()][6];
		       
		  for (int i = 0; i < pacientes.size(); i++){
			 
		         md[i][0] =  pacientes.get(i).getNombre();
		         md[i][4] =  pacientes.get(i).getN_historial();
		         md[i][2] =  pacientes.get(i).getN_Asegurado();
		         md[i][3] =  pacientes.get(i).getDni();
		         md[i][1] =  pacientes.get(i).getTelf1();
		         md[i][5] = pacientes.get(i).getActividad();
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