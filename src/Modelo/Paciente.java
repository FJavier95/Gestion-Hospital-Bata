package Modelo;

public class Paciente {
	
	private int id_Paciente;
	private int n_historial;
	private String nombre;
	private String apellido1;
	private String apellido2;
	private int condicion;
	private int telf1;
	private int telf2;
	private int telf3;
	private String poblacion;
	private String direccion;
	private int sexo;
	private int fianza;
	private int actividad;
	private String letraDni;
	private String n_Asegurado;
	private int dni;
	
	public Paciente(int id_paciente ,int n_historial, String nombre, String apellido1, String apellido2, int condicion,
			int telf1, int telf2, int telf3, String poblacion, String direccion, int sexo, int dni, String n_asegurado) {
		
		this.id_Paciente = id_paciente;
		this.n_historial = n_historial;
		this.nombre = nombre;
		this.apellido1 = apellido1;
		this.apellido2 = apellido2;
		this.condicion = condicion;
		this.telf1 = telf1;
		this.telf2 = telf2;
		this.telf3 = telf3;;
		this.poblacion = poblacion;
		this.direccion = direccion;
		this.sexo = sexo;
		
		this.dni = dni;
		this.n_Asegurado = n_asegurado;
	}
	public String getLetraDni() {
		return letraDni;
	}
	public void setLetraDni(String letraDni) {
		this.letraDni = letraDni;
	}
	public int getFianza() {
		return fianza;
	}
	public void setFianza(int fianza) {
		this.fianza = fianza;
	}
	public String getN_Asegurado() {
		return n_Asegurado;
	}
	public void setN_Asegurado(String n_Asegurado) {
		this.n_Asegurado = n_Asegurado;
	}
	
	public Paciente(int n_historial, String nombre, String apellido1, String apellido2, int condicion,
			int telf1, int telf2, int telf3, String poblacion, String direccion, int sexo, int dni, String n_asegurado) {
		
		
		this.n_historial = n_historial;
		this.nombre = nombre;
		this.apellido1 = apellido1;
		this.apellido2 = apellido2;
		this.condicion = condicion;
		this.telf1 = telf1;
		this.telf2 = telf2;
		this.telf3 = telf3;
		this.poblacion = poblacion;
		this.direccion = direccion;
		this.sexo = sexo;
		
		this.dni = dni;
		this.n_Asegurado = n_asegurado;
	}

	public Paciente(int id_paciente, int n_historial, String nombre, String apellido1, int dni) {
		
		this.id_Paciente = id_paciente;
		this.n_historial = n_historial;
		this.nombre = nombre;
		this.apellido1 = apellido1;
		this.dni = dni;
	}


	public Paciente(String nombre2, int n_historial2, String n_asegurado2, int dip, int telefono) {
		this.nombre=nombre2;
		this.n_historial=n_historial2;
		this.n_Asegurado=n_asegurado2;
		this.dni=dip;
		this.telf1=telefono;
	}
	public int getDni() {
		return dni;
	}

	

	public void setDni(int dni) {
		this.dni = dni;
	}

	public int getId_Paciente() {
		return id_Paciente;
	}

	public void setId_Paciente(int id_Paciente) {
		this.id_Paciente = id_Paciente;
	}

	public int getN_historial() {
		return n_historial;
	}

	public void setN_historial(int n_historial) {
		this.n_historial = n_historial;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellido1() {
		return apellido1;
	}

	public void setApellido1(String apellido1) {
		this.apellido1 = apellido1;
	}

	public String getApellido2() {
		return apellido2;
	}

	public void setApellido2(String apellido2) {
		this.apellido2 = apellido2;
	}

	public int getCondicion() {
		return condicion;
	}

	public void setCondicion(int condicion) {
		this.condicion = condicion;
	}

	public int getTelf1() {
		return telf1;
	}

	public void setTelf1(int telf1) {
		this.telf1 = telf1;
	}

	public int getTelf2() {
		return telf2;
	}

	public void setTelf2(int telf2) {
		this.telf2 = telf2;
	}

	public int getTelf3() {
		return telf3;
	}

	public void setTelf3(int telf3) {
		this.telf3 = telf3;
	}

	public String getPoblacion() {
		return poblacion;
	}

	public void setPoblacion(String poblacion) {
		this.poblacion = poblacion;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public int getSexo() {
		return sexo;
	}

	public void setSexo(int sexo) {
		this.sexo = sexo;
	}

	public String sexoString(int i){
		if (i ==0)
			return "Femenino";
		else
		if (i ==1)
			return "Masculino";
		else return "";
	}
	
	public String getCondicionString() {
		String Condicion = null;
		switch(condicion){
		case 1: Condicion="Asegurado";
		break;
		case 2: Condicion="NO-Asegurado";
		break;
		case 3: Condicion="Jubilado";
		break;
		case 4: Condicion="Pensionista";
		break;
		case 5: Condicion="Discapacitado";
		break;
		}
		return Condicion;
	}
	
	public static int getCondiciontoInt(String Con) {
		int Condicion = -1;
	
		if(Con.equals("Asegurado")){
			Condicion=1;
		}else if(Con.equals("NO-Asegurado")){
			Condicion=2;
		}else if(Con.equals("Jubilado")){
				Condicion=3;
			}else if(Con.equals("Pensionista")){
				Condicion=4;
			}else if(Con.equals("Discapacitado")){
				Condicion=5;
			}
		
		return Condicion;
	}
	public int getActividad() {
		return actividad;
	}
	public void setActividad(int actividad) {
		this.actividad = actividad;
	}

}
