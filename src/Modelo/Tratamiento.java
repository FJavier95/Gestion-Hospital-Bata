package Modelo;
import java.sql.*;
import java.sql.Date;
import java.util.*;

public class Tratamiento {
	
//	public int id_Tratamiento;
	//public int id_Paciente;


	public Timestamp fecha;
	public int cantidad;
	public int tipo_Prueba;
	public String Concepto;
	public int id_concepto;
	public int coste;
	
	
	public Tratamiento(Timestamp fecha, int cantidad, int tipo_Prueba, String Concepto, int cost) {
		
		//this.id_Tratamiento = id_Tratamiento;
		this.fecha = fecha;
		this.cantidad = cantidad;
		this.tipo_Prueba = tipo_Prueba;
		this.Concepto = Concepto;
		this.coste = cost;
	}
	public Tratamiento(Timestamp fecha, int cantidad, int tipo_Prueba, int id_concepto) {
		this.fecha = fecha;
		this.cantidad = cantidad;
		this.tipo_Prueba = tipo_Prueba;
		this.id_concepto = id_concepto;
		Concepto con = null;
		if(tipo_Prueba<9){
		 con=BBDD.ObtenerDescripcionPrecio(idTipoPruebaToString(tipo_Prueba), id_concepto,fecha.toString()) ;
		
		}else if(tipo_Prueba==9){
			con=BBDD.ObtenerDescripcionPrecioMedicamento(idTipoPruebaToString(tipo_Prueba), id_concepto,fecha.toString()) ;
		}else if(tipo_Prueba==10){
			con=BBDD.ObtenerDescripcionPrecioFungible(idTipoPruebaToString(tipo_Prueba), id_concepto,fecha.toString()) ;
		}
			this.Concepto=con.getDescripcion();
		this.coste=con.getPrecio();
		
	}
	public  static String idTipoPruebaToString(int id_TipoPrueba){
		switch (id_TipoPrueba){
		case 1: return "EMBARAZADA";
		case 2: return "ECOGRAFIA";
		case 3: return "LABORATORIO";
		case 4: return "MAMOGRAFIA";
		case 5: return "ODONTOESTOMATOLOGIA";
		case 6: return "OTORRINOLARINGOLOGIA";
		case 7: return "PROCESOSQUIRURGICOS";
		case 8: return "RADIOLOGIASIMPLE";
		case 9:return "MEDICAMENTOS";
		case 10:return "FUNGIBLES";
		
		}
		return null;
		
	}
	/*public int getId_Paciente() {
		return id_Paciente;
	}

	public void setId_Paciente(int id_Paciente) {
		this.id_Paciente = id_Paciente;
	}*/
	
	/*public int getId_Tratamiento() {
		return id_Tratamiento;
	}
	public void setId_Tratamiento(int id_Tratamiento) {
		this.id_Tratamiento = id_Tratamiento;
	}*/
	public Timestamp getFecha() {
		return fecha;
	}
	public void setFecha(Timestamp fecha) {
		this.fecha = fecha;
	}
	public int getCantidad() {
		return cantidad;
	}
	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}
	public int gettipo_Prueba() {
		return tipo_Prueba;
	}
	public void settipo_Prueba(int tipo_Prueba) {
		this.tipo_Prueba = tipo_Prueba;
	}
	public String getId_Concepto() {
		return Concepto;
	}
	public void setId_Concepto(String id_Concepto) {
		this.Concepto = id_Concepto;
	}

	public float getCoste() {
		return coste;
	}

	public void setCoste(int coste) {
		this.coste = coste;
	}
	
	
	

}
