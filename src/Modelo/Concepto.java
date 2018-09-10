package Modelo;

public class Concepto {
	int id;
	int id_tipoPrueba;
	String descripcion;
	int precio;
	String ref1;
	String ref2;
	String marca;
Concepto(int id, String descripcion, int tipoprueba){
	this.id=id;
	this.descripcion=descripcion;
	this.id_tipoPrueba=tipoprueba;
}
Concepto(int id, String descripcion, int tipoprueba,String ref1,String ref2, String marca){
	//Para fungibles
	this.id=id;
	this.descripcion=descripcion;
	this.id_tipoPrueba=tipoprueba;
	this.ref1=ref1;
	this.ref2=ref2;
	this.marca=marca;
}
Concepto(int id, String descripcion, int tipoprueba,String marca){
	//Para los medicamentos
	this.id=id;
	this.descripcion=descripcion;
	this.id_tipoPrueba=tipoprueba;
	this.marca=marca;
}
public String getRef1() {
	return ref1;
}
public void setRef1(String ref1) {
	this.ref1 = ref1;
}
public String getRef2() {
	return ref2;
}
public void setRef2(String ref2) {
	this.ref2 = ref2;
}
public String getMarca() {
	return marca;
}
public void setMarca(String marca) {
	this.marca = marca;
}
public int getId_tipoPrueba() {
	return id_tipoPrueba;
}
public void setId_tipoPrueba(int id_tipoPrueba) {
	this.id_tipoPrueba = id_tipoPrueba;
}
public int getId() {
	return id;
}
public void setId(int id) {
	this.id = id;
}
public String getDescripcion() {
	return descripcion;
}
public void setDescripcion(String descripcion) {
	this.descripcion = descripcion;
}
public int getPrecio() {
	return precio;
}
public void setPrecio(int precio) {
	this.precio = precio;
}
}
