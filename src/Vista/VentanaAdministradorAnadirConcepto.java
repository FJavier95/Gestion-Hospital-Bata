package Vista;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

import Control.ControladorAdministradorAnadirConcepto;
import Control.ControladorAnadirPaciente;

public class VentanaAdministradorAnadirConcepto {
	ControladorAdministradorAnadirConcepto controlador;
	public String tipoPrueba;
	public JFrame frmAdminAnadirConcepto;
	public JButton btn_AP_Aceptar;
	public JButton btn_AP_Cancelar;
	JLabel lbl_AP_Descripcion;
	JLabel lbl_AP_Farmaco;
	JLabel lbl_AP_Precio;
	JLabel lbl_AP_Ref1;
	JLabel lbl_AP_Ref2;
	JLabel lbl_AP_Marca;
	public JTextField txt_AP_Descripcion;
	public JTextField txt_AP_Precio;
	public JTextField txt_AP_Farmaco;
	public JTextField txt_AP_Ref1;
	public JTextField txt_AP_Ref2;
	public JTextField txt_AP_Marca;
	public int id_tipotabla;
	public void addController(ControladorAdministradorAnadirConcepto mc, String tipoPrueba, int id_tipotabla){
		controlador = mc;
		this.tipoPrueba=tipoPrueba;
		this.id_tipotabla=id_tipotabla;
	}
	
	
	
	//Funci�n para crear la ventana y sus componentes
	public void crearVentana(){
		frmAdminAnadirConcepto = new JFrame();
		frmAdminAnadirConcepto.getContentPane().setFont(new Font("Tahoma", Font.PLAIN, fuenteRelativa(19)));
		frmAdminAnadirConcepto.getContentPane().setBackground(Color.WHITE);
		frmAdminAnadirConcepto.setTitle("Nuevo Concepto");
		frmAdminAnadirConcepto.setBounds(AnchoRelativo(100), AltoRelativo(100), AnchoRelativo(1100), AltoRelativo(700));
	
		frmAdminAnadirConcepto.getContentPane().setLayout(null);
		frmAdminAnadirConcepto.setResizable(false);
		frmAdminAnadirConcepto.addWindowListener(controlador);
		btn_AP_Aceptar = new JButton("Aceptar");
		btn_AP_Aceptar.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btn_AP_Aceptar.setFont(new Font("Tahoma", Font.BOLD, fuenteRelativa(21)));	//Establece el tama�o y el tipo de letra que tendr� el bot�n
		btn_AP_Aceptar.setBackground(new Color(0, 0, 96));	//Establece el color del bot�n
		btn_AP_Aceptar.setOpaque(true);
		btn_AP_Aceptar.setBorderPainted(false);
		btn_AP_Aceptar.setForeground(Color.WHITE);
		btn_AP_Aceptar.setBounds(AnchoRelativo(650), AltoRelativo(570), AnchoRelativo(180), AltoRelativo(33));
	    btn_AP_Aceptar.addActionListener(controlador);
	    frmAdminAnadirConcepto.getContentPane().add(btn_AP_Aceptar);
	  
	   
	    
		btn_AP_Cancelar = new JButton("Cancelar");
		btn_AP_Cancelar.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btn_AP_Cancelar.setFont(new Font("Tahoma", Font.BOLD, fuenteRelativa(21)));	//Establece el tama�o y el tipo de letra que tendr� el bot�n
		btn_AP_Cancelar.setBackground(new Color(0, 0, 96));	//Establece el color del bot�n
		btn_AP_Cancelar.setOpaque(true);
		btn_AP_Cancelar.setBorderPainted(false);
		btn_AP_Cancelar.setForeground(Color.WHITE);		
		btn_AP_Cancelar.setBounds(AnchoRelativo(850), AltoRelativo(570), AnchoRelativo(180), AltoRelativo(32));
	
		btn_AP_Cancelar.addActionListener(controlador);
		frmAdminAnadirConcepto.getContentPane().add(btn_AP_Cancelar);
		frmAdminAnadirConcepto.add(btn_AP_Cancelar);
		
		lbl_AP_Descripcion = new JLabel("Descripcion:");
		lbl_AP_Descripcion.setFont(new Font("Tahoma", Font.PLAIN, fuenteRelativa(20)));
		lbl_AP_Descripcion.setBounds(AnchoRelativo(50), AltoRelativo(50), AnchoRelativo(110), AltoRelativo(22));
		frmAdminAnadirConcepto.getContentPane().add(lbl_AP_Descripcion);
		
		txt_AP_Descripcion = new JTextField();
		txt_AP_Descripcion.setFont(new Font("Tahoma", Font.PLAIN, fuenteRelativa(19)));
		txt_AP_Descripcion.setBounds(AnchoRelativo(165), AltoRelativo(50), AnchoRelativo(400), AltoRelativo(26));
		frmAdminAnadirConcepto.getContentPane().add(txt_AP_Descripcion);
		txt_AP_Descripcion.setColumns(10);
		
		lbl_AP_Precio = new JLabel("Precio:");
		lbl_AP_Precio.setFont(new Font("Tahoma", Font.PLAIN, fuenteRelativa(20)));
		lbl_AP_Precio.setBounds(AnchoRelativo(50), AltoRelativo(120), AnchoRelativo(110), AltoRelativo(22));
		frmAdminAnadirConcepto.getContentPane().add(lbl_AP_Precio);
		
		txt_AP_Precio = new JTextField();
		txt_AP_Precio.setFont(new Font("Tahoma", Font.PLAIN, fuenteRelativa(19)));
		txt_AP_Precio.setBounds(AnchoRelativo(165), AltoRelativo(120), AnchoRelativo(80), AltoRelativo(26));
		frmAdminAnadirConcepto.getContentPane().add(txt_AP_Precio);
		txt_AP_Precio.setColumns(10);
		if(id_tipotabla==9){
		lbl_AP_Farmaco = new JLabel("Farmaco:");
		lbl_AP_Farmaco.setFont(new Font("Tahoma", Font.PLAIN, fuenteRelativa(20)));
		lbl_AP_Farmaco.setBounds(AnchoRelativo(50), AltoRelativo(180), AnchoRelativo(110), AltoRelativo(22));
		frmAdminAnadirConcepto.getContentPane().add(lbl_AP_Farmaco);
		
		txt_AP_Farmaco = new JTextField();
		txt_AP_Farmaco.setFont(new Font("Tahoma", Font.PLAIN, fuenteRelativa(19)));
		txt_AP_Farmaco.setBounds(AnchoRelativo(165), AltoRelativo(180), AnchoRelativo(110), AltoRelativo(26));
		frmAdminAnadirConcepto.getContentPane().add(txt_AP_Farmaco);
		txt_AP_Farmaco.setColumns(10);
		
		}/*else if(id_tipotabla==10){
			
			lbl_AP_Ref1 = new JLabel("Ref1:");
			lbl_AP_Ref1.setFont(new Font("Tahoma", Font.PLAIN, fuenteRelativa(20)));
			lbl_AP_Ref1.setBounds(AnchoRelativo(50), AltoRelativo(180), AnchoRelativo(110), AltoRelativo(22));
			frmAdminAnadirConcepto.getContentPane().add(lbl_AP_Ref1);
			
			txt_AP_Ref1 = new JTextField();
			txt_AP_Ref1.setFont(new Font("Tahoma", Font.PLAIN, fuenteRelativa(19)));
			txt_AP_Ref1.setBounds(AnchoRelativo(165), AltoRelativo(180), AnchoRelativo(110), AltoRelativo(26));
			frmAdminAnadirConcepto.getContentPane().add(txt_AP_Ref1);
			
			lbl_AP_Ref2 = new JLabel("Ref2:");
			lbl_AP_Ref2.setFont(new Font("Tahoma", Font.PLAIN, fuenteRelativa(20)));
			lbl_AP_Ref2.setBounds(AnchoRelativo(50), AltoRelativo(250), AnchoRelativo(110), AltoRelativo(22));
			frmAdminAnadirConcepto.getContentPane().add(lbl_AP_Ref2);
			
			txt_AP_Ref2 = new JTextField();
			txt_AP_Ref2.setFont(new Font("Tahoma", Font.PLAIN, fuenteRelativa(19)));
			txt_AP_Ref2.setBounds(AnchoRelativo(165), AltoRelativo(250), AnchoRelativo(110), AltoRelativo(26));
			frmAdminAnadirConcepto.getContentPane().add(txt_AP_Ref2);
			
			lbl_AP_Marca = new JLabel("Marca:");
			lbl_AP_Marca.setFont(new Font("Tahoma", Font.PLAIN, fuenteRelativa(20)));
			lbl_AP_Marca.setBounds(AnchoRelativo(50), AltoRelativo(320), AnchoRelativo(110), AltoRelativo(22));
			frmAdminAnadirConcepto.getContentPane().add(lbl_AP_Marca);
			
			txt_AP_Marca = new JTextField();
			txt_AP_Marca.setFont(new Font("Tahoma", Font.PLAIN, fuenteRelativa(19)));
			txt_AP_Marca.setBounds(AnchoRelativo(165), AltoRelativo(320), AnchoRelativo(110), AltoRelativo(26));
			frmAdminAnadirConcepto.getContentPane().add(txt_AP_Marca);
		}
		*/
		frmAdminAnadirConcepto.setVisible(true);
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
