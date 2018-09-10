package Modelo;

import java.sql.Date;
import java.sql.Timestamp;

public class Fecha_Paciente {
	Timestamp fecha_alta;
	Timestamp fecha_ingreso;
	public Fecha_Paciente(Timestamp fecha_alta,Timestamp fecha_ingreso){
		this.fecha_alta=fecha_alta;
		this.fecha_ingreso=fecha_ingreso;
	}
	public Timestamp getFecha_alta() {
		return fecha_alta;
	}
	public void setFecha_alta(Timestamp fecha_alta) {
		this.fecha_alta = fecha_alta;
	}
	public Timestamp getFecha_ingreso() {
		return fecha_ingreso;
	}
	public void setFecha_ingreso(Timestamp fecha_ingreso) {
		this.fecha_ingreso = fecha_ingreso;
	}
	

}
