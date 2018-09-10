package Modelo;
public class Usuario {
	public String Nombre;
	public String passw;
	public int tipousa;
	public int id_usuario;
	public int getTipousa() {
		return tipousa;
	}

	public void setTipousa(int tipousa) {
		this.tipousa = tipousa;
	}

	public Usuario(String nom,String con, int tipousa){
		this.Nombre = nom;
		this.passw = con;
		this.tipousa=tipousa;
		
	}
	public Usuario(String nom, int tipousa,int id_usuario){
		this.Nombre = nom;
		this.id_usuario=id_usuario;
		this.tipousa=tipousa;
		
	}
	public int getId_usuario() {
		return id_usuario;
	}

	public void setId_usuario(int id_usuario) {
		this.id_usuario = id_usuario;
	}

	public String getNombre() {
		return Nombre;
	}

	public void setNombre(String nombre) {
		Nombre = nombre;
	}

	public String getPassw() {
		return passw;
	}

	public void setPassw(String contrasena) {
		passw = contrasena;
	}
	
}