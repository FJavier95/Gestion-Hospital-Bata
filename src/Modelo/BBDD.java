package Modelo;

import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Vector;

import javax.swing.JOptionPane;

public class BBDD {
	
	private static Conexion conexion = new Conexion();
	
	//public void a�adirPaciente()
	
	public static Vector<Paciente> busqueda (String parametro){
		Connection c = conexion.conectarse();
      //  PreparedStatement preparedStatement = null;
        Vector<Paciente> v_pacientes = new Vector();
        Paciente paciente1 = null;
        PreparedStatement pstmt = null;
        try{
        	ResultSet select;
        	pstmt = c.prepareStatement("Select ID_Paciente, N_Historial, Nombre, Apellido1, DNI"
        			+ " from Guinea.Paciente"
        			+ " Where Paciente.Nombre like ? OR Paciente.DNI  like ? "
        			+ " OR Paciente.N_Historial  like ? ;");
        	
        pstmt.setString(1, "%"+parametro+"%");
        pstmt.setString(2, parametro);
        pstmt.setString(3, parametro);
        
        select = pstmt.executeQuery();
        	
        	while(select.next()){
        		int id_Paciente = select.getInt("ID_Paciente");
        		int n_Historial = select.getInt("N_Historial");
        		String nombre = select.getString("Nombre");
        		String apellido1 = select.getString("Apellido1");
        		int dni = select.getInt("DNI");
        		
        		paciente1 =  new Paciente(id_Paciente, n_Historial, nombre,apellido1,dni);
        		v_pacientes.add(paciente1);
      
        	}
        }
        catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
		
		return v_pacientes;
		
		
	}
	public static boolean compFechaAlta(Timestamp fech){
		SimpleDateFormat aux=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String tiempo = aux.format(fech);
		Connection c = conexion.conectarse();
        PreparedStatement preparedStatement = null;
        Paciente paciente1 = null;
        Statement stmt = null;
        Timestamp fecalt = null;
        try{
        	stmt = c.createStatement();
        	ResultSet select = stmt.executeQuery("SELECT Fecha_Alta FROM Guinea.Fecha_Paciente"
	+" where Fecha_Paciente.Fecha_Ingreso like  '"+tiempo+"'  ;");
        	
        	
        	while(select.next()){
        		fecalt=select.getTimestamp("Fecha_Alta");
        	}
        }catch (Exception e) {
                System.err.println(e.getClass().getName() + ": " + e.getMessage());
                System.exit(0);
            }
        if(fecalt==null){
        	return false;
        }else
		return true;
	}
	
	//Obtienes el listado de todos lostratamientos de un paciente para luego 
	// en otra funcion poder diferenciar los tipos de prueba (mamografia, laboratorio, fungibles....
	/*public Vector<Tratamiento> ObtenerTratamientos (int Id_Paciente){
		
		Connection c = conexion.conectarse();
	      //  PreparedStatement preparedStatement = null;
	        Vector<Tratamiento> v_tratamientos = new Vector();
	        Tratamiento tratamiento = null;
	        Timestamp fechaTratamiento = null;
	        PreparedStatement pstmt = null;
	        try{
	        	ResultSet select;
	        	pstmt = c.prepareStatement("Select * "
	        			+ " from Guinea.Tratamiento"
	        			+ "where  Tratamiento.ID_Pacinete = " + Id_Paciente + ";");
	        		
	             
	        select = pstmt.executeQuery();
	        	
	        	while(select.next()){
	        		int id_Paciente = select.getInt("ID_Paciente");
	        		fechaTratamiento = select.getTimestamp(2);
	        		int cantidad = select.getInt("Cantidad");
	        		int TipoTratamiento= select.getInt("TipoTratamiento");
	        		int Concepto= select.getInt("ID_Concepto");
	        		
	        		tratamiento =  new Tratamiento (id_Paciente,fechaTratamiento, cantidad,TipoTratamiento,Concepto, 0);
	        		v_tratamientos.add(tratamiento);
	      
	        	}
	        	
		
	}
	*/
		
	/*public Vector<Tratamiento> ObtenerTratamientosPaciente (Vector<Tratamiento> trats){
		
		Connection c = conexion.conectarse();
	      //  PreparedStatement preparedStatement = null;
	        Vector<Tratamiento> v_pacientes = new Vector();
	       // Paciente paciente1 = null;
	        PreparedStatement pstmt = null;
	        try{
	        	ResultSet select;
	        	pstmt = c.prepareStatement("Select Paciente.Nombre, LABORATORIO.Descripcion, Tratamiento.Fecha,Tratamiento.Cantidad,(LABORATORIO.Precio*Tratamiento.Cantidad) as Coste"
	        			+ " from Guinea.Tratamiento, Guinea.Paciente, Guinea.LABORATORIO"
	        			+ "where Paciente.ID_Paciente = Tratamiento.ID_Pacinete and"
	        			+ "LABORATORIO.ID_LABORATORIO = Tratamiento.ID_Concepto;");
	        	
	        pstmt.setString(1, "%"+parametro+"%");
	        pstmt.setString(2, parametro);
	        pstmt.setString(3, parametro);
	        
	        select = pstmt.executeQuery();
	        	
	        	while(select.next()){
	        		int id_Paciente = select.getInt("ID_Paciente");
	        		fechaTratamiento = select.getInt("N_Historial");
	        		String nombre = select.getString("Nombre");
	        		String apellido1 = select.getString("Apellido1");
	        		int dni = select.getInt("DNI");
	        		
	        		paciente1 =  new Paciente(id_Paciente, n_Historial, nombre,apellido1,dni);
	        		v_pacientes.add(paciente1);
	      
	        	}
		
		
		
		
		return null;
	
		
	}*/
	public static int obtenerGastoTotalPorCondicion(int condicion, int tipoPrueba, String fecha1, String fecha2){
	    
	    Connection c = conexion.conectarse();
	        //  PreparedStatement preparedStatement = null;
	        int gastoTotalCondicion = 0;
	          Fecha_Paciente fecha = null;
	          Statement stmt = null;
	          try{
	            stmt = c.createStatement();
	            ResultSet select = stmt.executeQuery("SELECT sum(Precio*Cantidad) as Total from Guinea.Tratamiento"
	                + " inner join Guinea.Paciente on Tratamiento.ID_Paciente = Paciente.ID_Paciente"
	                +" inner join Guinea.Precios on Tratamiento.id_Precios = Precios.id_Precios"
	                +" Where Paciente.Condicion like "+condicion
	                +" and Tratamiento.TipoPrueba like " + tipoPrueba
	                +" and Tratamiento.Fecha between '"+fecha1+"' and '"+fecha2+"';");
	            
	            while(select.next()){
	               gastoTotalCondicion = select.getInt("Total");
	        
	            }
	          }
	          catch (Exception e) {
	              System.err.println(e.getClass().getName() + ": " + e.getMessage());
	              System.exit(0);
	          }
	      
	      return gastoTotalCondicion;
	      
	    
	    
	  }
	public static boolean anadirPaciente (Paciente pac){
			  
			  Connection c = conexion.conectarse();
			        PreparedStatement preparedStatement = null;
			     
			        
			     try{
			         
			      
			         String insertPaciente = "Insert Into Paciente(N_Historial,Nombre,Apellido1,Apellido2,Condicion,"
			           + "Telefono1,Telefono2,Telefono3,Poblacion,Direccion,Sexo,DNI,N_Asegurado,Actividad,LetraDni)"
			           +"values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?);";
			         
			         preparedStatement = c.prepareStatement(insertPaciente);
			         preparedStatement.setInt(1, pac.getN_historial());
			         preparedStatement.setString(2, pac.getNombre());
			         preparedStatement.setString(3, pac.getApellido1());
			         preparedStatement.setString(4, pac.getApellido2());
			         preparedStatement.setInt(5, pac.getCondicion());
			         preparedStatement.setInt(6, pac.getTelf1());
			         preparedStatement.setInt(7, pac.getTelf2());
			         preparedStatement.setInt(8, pac.getTelf3());
			         preparedStatement.setString(9, pac.getPoblacion());
			         preparedStatement.setString(10, pac.getDireccion());
			         preparedStatement.setInt(11,pac.getSexo());
			      
			         preparedStatement.setInt(12, pac.getDni());
			         preparedStatement.setString(13, pac.getN_Asegurado());
			         preparedStatement.setInt(14, 1);
			         preparedStatement.setString(15, pac.getLetraDni());
			         preparedStatement.execute();
			         
			         return true;
			  
			  
			 }catch (Exception e) {
			        System.err.println(e.getClass().getName() + ": " + e.getMessage());
			        System.exit(0);
			    }
			     return false;
			 }
	public static boolean IngresoActivo(Paciente pac){
		Connection c = conexion.conectarse();
        PreparedStatement preparedStatement = null;
        Paciente paciente1 = null;
        Statement stmt = null;
        Timestamp fechaIngreso = null;
        Timestamp fechaAlta=null;
        try{
        	stmt = c.createStatement();
        	ResultSet select = stmt.executeQuery(" SELECT Fecha_Alta, Fecha_Ingreso FROM Guinea.Fecha_Paciente"
        			+" where Fecha_Paciente.ID_Paciente like "+ pac.getId_Paciente() 
        			
        			+" order by Fecha_Alta desc;");
        	
        	
        	if(select.next()){
        		fechaIngreso=select.getTimestamp("Fecha_Ingreso");
        		fechaAlta=select.getTimestamp("Fecha_Alta");
        	}
        }catch (Exception e) {
                System.err.println(e.getClass().getName() + ": " + e.getMessage());
                System.exit(0);
            }
        if(fechaIngreso!=null){
        if(fechaAlta.toString().equals("2999-12-31 00:00:00.0")){
            
        	return false;// No tiene fecha de alta 
        }
        
		return true;//Tiene fecha de alta
        }return true;
	}
	public static int obtenerIdUltimaFechaPaciente(Paciente pac){
		
			Connection c = conexion.conectarse();
	      //  PreparedStatement preparedStatement = null;
			int id = -1;
	        Statement stmt = null;
	        try{
	        	stmt = c.createStatement();
	        	
	        	ResultSet select = stmt.executeQuery("SELECT Fecha_Paciente.ID_Fecha_Paciente"
	        										+" from Guinea.Fecha_Paciente"
	        										+" where Fecha_Paciente.ID_Paciente like " + pac.getId_Paciente()
	        										+"	and Fecha_Paciente.Fecha_Alta like '2999-12-31 00:00:00';");
	        	
	        
	      
	        	
	        	if(select.next()){
	        		 id = select.getInt("ID_Fecha_Paciente"); 	      
	        	}
	        }
	        catch (Exception e) {
	            System.err.println(e.getClass().getName() + ": " + e.getMessage());
	            System.exit(0);
	        }
			
			return id;
		
	}
	public static Vector<Concepto>obtener_ConceptosActivos(String arg){
		
		Connection c = conexion.conectarse();
	      //  PreparedStatement preparedStatement = null;
	        Vector<Concepto> v_conceptos = new Vector();
	        Concepto concepto = null;
	        Statement stmt = null;
	        try{
	        	stmt = c.createStatement();
	        	
	        	ResultSet select = stmt.executeQuery("SELECT ID_"+arg+", Descripcion, Precio FROM Guinea.Precios" 
						+" join  Guinea."+arg +" on "+arg+".ID_"+arg+"=Precios.ID_Concepto"
						+" where ID_TiposPrueba like "+idTipoPruebaToInt(arg)
						+" and "+ arg+".Actividad like 1"
						+ " and Precios.Fecha_Hasta like '2999-12-31 00:00:00'"
						+" order by Descripcion ;");
	        
	      
	        	
	        	while(select.next()){
	        		int id = select.getInt("ID_"+arg);
	        		String descripcion = select.getString("Descripcion");
	        		int precio=select.getInt("Precio");
	        		
	        		
	        		concepto =  new Concepto(id,descripcion,idTipoPruebaToInt(arg));
	        		concepto.setPrecio(precio);
	        		v_conceptos.add(concepto);
	      
	        	}
	        }
	        catch (Exception e) {
	            System.err.println(e.getClass().getName() + ": " + e.getMessage());
	            System.exit(0);
	        }
			
			return v_conceptos;
			
			
		}
	public static Vector<Concepto>obtener_ConceptosInactivos(String arg){
		
		Connection c = conexion.conectarse();
	      //  PreparedStatement preparedStatement = null;
	        Vector<Concepto> v_conceptos = new Vector();
	        Concepto concepto = null;
	        Statement stmt = null;
	        try{
	        	stmt = c.createStatement();
	        	
	        	ResultSet select = stmt.executeQuery("SELECT ID_"+arg+", Descripcion, Precio FROM Guinea.Precios" 
						+" join  Guinea."+arg +" on "+arg+".ID_"+arg+"=Precios.ID_Concepto"
						+" where ID_TiposPrueba like "+idTipoPruebaToInt(arg)
						+" and "+ arg+".Actividad like 0"
						+ " and Precios.Fecha_Hasta like '2999-12-31 00:00:00'"
						+" order by Descripcion ;");
	        	
	        
	      
	        	
	        	while(select.next()){
	        		int id = select.getInt("ID_"+arg);
	        		String descripcion = select.getString("Descripcion");
	        		int precio=select.getInt("Precio");
	        		
	        		
	        		concepto =  new Concepto(id,descripcion,idTipoPruebaToInt(arg));
	        		concepto.setPrecio(precio);
	        		v_conceptos.add(concepto);
	      
	        	}
	        }
	        catch (Exception e) {
	            System.err.println(e.getClass().getName() + ": " + e.getMessage());
	            System.exit(0);
	        }
			
			return v_conceptos;
			
			
		}
	public static Vector<Fecha_Paciente>obtener_fechasPaciente(int id_Paciente){

		Connection c = conexion.conectarse();
	      //  PreparedStatement preparedStatement = null;
	        Vector<Fecha_Paciente> v_fecha = new Vector();
	        Fecha_Paciente fecha = null;
	        Statement stmt = null;
	        try{
	        	stmt = c.createStatement();
	        	ResultSet select = stmt.executeQuery("Select Fecha_Ingreso, Fecha_Alta"
	        			+ " from  Guinea.Fecha_Paciente "+
	        			"Where ID_Paciente like "+id_Paciente
	        			+" order by Fecha_Ingreso desc ;");
	        	
	        	while(select.next()){
	        		Timestamp fecha_alta = select.getTimestamp("Fecha_Alta");
	        		Timestamp fecha_ingreso= select.getTimestamp("Fecha_Ingreso");
	        		fecha =  new Fecha_Paciente(fecha_alta,fecha_ingreso);
	        		v_fecha.add(fecha);
	      
	        	}
	        }
	        catch (Exception e) {
	            System.err.println(e.getClass().getName() + ": " + e.getMessage());
	            System.exit(0);
	        }
			
			return v_fecha;
			
			
		}
	public static Vector<Tratamiento> obtenerTratamientosPorFecha (Fecha_Paciente fech, Paciente pac){
		Connection c = conexion.conectarse();
	      //  PreparedStatement preparedStatement = null;
	        Vector<Tratamiento> v_tratamiento = new Vector();
	        Tratamiento trt = null;
	        Statement stmt = null;
	        String sele=null;
	        String fechaS=fech.getFecha_alta().toString();
	        
	
			if (fechaS.equals("2999-12-31 00:00:00.0")){
				sele="Select *"
						+ " from  Guinea.Tratamiento "+
						"Where Fecha between '"+fech.getFecha_ingreso() + "' AND adddate(now(),interval 1 hour) "
						+" AND ID_Paciente like '" + pac.getId_Paciente() + "' AND Actividad like '1';";
				
			       
			        
			}else {sele="Select *"
					+ " from  Guinea.Tratamiento "+
					"Where Fecha between '"+fech.getFecha_ingreso() + "' AND '"+ fech.getFecha_alta()+ "' "
					+" AND ID_Paciente like '" + pac.getId_Paciente() +"' AND Actividad like '1';";
			}
		
			try{
	        	stmt = c.createStatement();
	        	ResultSet select = stmt.executeQuery(sele);
	        
				while(select.next()){
		        		
					//int id_paciente = select.getInt("ID_Paciente");
					int cantidad = select.getInt("Cantidad");
					int tipo_prueba = select.getInt("TipoPrueba");
					Timestamp fecha = select.getTimestamp("Fecha");
					int id_concepto = select.getInt("ID_Concepto");
					
			    		trt =  new Tratamiento(fecha,cantidad,tipo_prueba,id_concepto);
		        		
					v_tratamiento.add(trt);
		      
		        	}
	        } 
	        catch (Exception e) {
	            System.err.println(e.getClass().getName() + ": " + e.getMessage());
	            System.exit(0);
		       
			
			
		}
		
			return v_tratamiento;
	
}
	public static ArrayList<Tratamiento> obtenerTratamientosAdministrador (Fecha_Paciente fech, int tipoPrueba){
		Connection c = conexion.conectarse();
	      //  PreparedStatement preparedStatement = null;
		ArrayList<Tratamiento> v_tratamiento = new ArrayList();
	        Tratamiento trt = null;
	        Statement stmt = null;
	        String sele=null;
	        String fechaS=fech.getFecha_alta().toString();
	        
	
			
			       
			        
			sele="Select *"
					+ " from  Guinea.Tratamiento "+
					"Where Fecha between '"+ fech.getFecha_alta()+ "' AND '"+ fech.getFecha_ingreso()+ "' "
					+"and Tratamiento.TipoPrueba like "+tipoPrueba+" AND Tratamiento.Actividad like '1';";
			
		
			try{
	        	stmt = c.createStatement();
	        	ResultSet select = stmt.executeQuery(sele);
	        
				while(select.next()){
		        		
					//int id_paciente = select.getInt("ID_Paciente");
					int cantidad = select.getInt("Cantidad");
					int tipo_prueba = select.getInt("TipoPrueba");
					Timestamp fecha = select.getTimestamp("Fecha");
					int id_concepto = select.getInt("ID_Concepto");
					
			    		trt =  new Tratamiento(fecha,cantidad,tipo_prueba,id_concepto);
		        		
					v_tratamiento.add(trt);
		      
		        	}
	        } 
	        catch (Exception e) {
	            System.err.println(e.getClass().getName() + ": " + e.getMessage());
	            System.exit(0);
		       
			
			
		}
		
			return v_tratamiento;
	
}
	public static ArrayList<Tratamiento> obtenerTratamientosFactura (Fecha_Paciente fech, int tipoPrueba, Paciente pac){
		Connection c = conexion.conectarse();
	      //  PreparedStatement preparedStatement = null;
		ArrayList<Tratamiento> v_tratamiento = new ArrayList();
	        Tratamiento trt = null;
	        Statement stmt = null;
	        String sele=null;
	        String fechaS=fech.getFecha_alta().toString();
	        
	
			
			       if(fechaS.equals("2999-12-31 00:00:00.0")){
			    	   Timestamp timestamp = new Timestamp(System.currentTimeMillis());
			    	   sele="Select *"
								+ " from  Guinea.Tratamiento "+
								"Where Fecha between '"+ fech.getFecha_ingreso()+ "' AND '"+ timestamp.toString()+ "' "
								+"and Tratamiento.TipoPrueba like "+tipoPrueba+" AND Tratamiento.Actividad like '1'"
										+ " and Tratamiento.ID_Paciente like "+pac.getId_Paciente()+" ;";
			       }else{
			        
			sele="Select *"
					+ " from  Guinea.Tratamiento "+
					"Where Fecha between '"+ fech.getFecha_ingreso()+ "' AND '"+ fech.getFecha_alta()+ "' "
					+"and Tratamiento.TipoPrueba like "+tipoPrueba+" AND Tratamiento.Actividad like '1'"
							+ " and Tratamiento.ID_Paciente like "+pac.getId_Paciente()+" ;";
			
			       }
			try{
	        	stmt = c.createStatement();
	        	ResultSet select = stmt.executeQuery(sele);
	        
				while(select.next()){
		        		
					//int id_paciente = select.getInt("ID_Paciente");
					int cantidad = select.getInt("Cantidad");
					int tipo_prueba = select.getInt("TipoPrueba");
					Timestamp fecha = select.getTimestamp("Fecha");
					int id_concepto = select.getInt("ID_Concepto");
					
			    		trt =  new Tratamiento(fecha,cantidad,tipo_prueba,id_concepto);
		        		
					v_tratamiento.add(trt);
		      
		        	}
	        } 
	        catch (Exception e) {
	            System.err.println(e.getClass().getName() + ": " + e.getMessage());
	            System.exit(0);
		       
			
			
		}
		
			return v_tratamiento;
	
}
	public static Concepto ObtenerDescripcionPrecio (String tipoPrueba, int id_concepto, String fechaS){
	Connection c = conexion.conectarse();
    //  PreparedStatement preparedStatement = null;
      Concepto concepto = null;
      Statement stmt = null;
      try{
      	stmt = c.createStatement();
      	String aux="SELECT Descripcion, Precio FROM Guinea.Precios" 
				+" join  Guinea."+tipoPrueba +" on "+tipoPrueba+".ID_"+tipoPrueba+"=Precios.ID_Concepto"
				+" where ID_TiposPrueba like "+idTipoPruebaToInt(tipoPrueba)
				+" and "+ tipoPrueba+".Actividad like 1 and ID_Concepto like "+id_concepto
				+ " and  '"+fechaS+"' between Precios.Fecha_Desde and Precios.Fecha_Hasta";
      	ResultSet select = stmt.executeQuery(aux);
      	
      
    
      	
      	while(select.next()){

      		String descripcion = select.getString("Descripcion");
      		int precio = select.getInt("Precio");
      		
      		
      		concepto =  new Concepto(id_concepto,descripcion,idTipoPruebaToInt(tipoPrueba));
      		concepto.setPrecio(precio);
      	}
      }
      catch (Exception e) {
          System.err.println(e.getClass().getName() + ": " + e.getMessage());
          System.exit(0);
      }
	
	
	
	return concepto;
	
}
	public static Concepto ObtenerDescripcionPrecioMedicamento (String tipoPrueba, int id_concepto, String fechaS){
		Connection c = conexion.conectarse();
	    //  PreparedStatement preparedStatement = null;
	      Concepto concepto = null;
	      Statement stmt = null;
	      try{
	      	stmt = c.createStatement();
	      	String aux="SELECT Nombre_Comercial, Precio FROM Guinea.Precios" 
					+" join  Guinea."+tipoPrueba +" on "+tipoPrueba+".ID_"+tipoPrueba+"=Precios.ID_Concepto"
					+" where ID_TiposPrueba like "+idTipoPruebaToInt(tipoPrueba)
					+" and "+ tipoPrueba+".Actividad like 1 and ID_Concepto like "+id_concepto
					+ " and  '"+fechaS+"' between Precios.Fecha_Desde and Precios.Fecha_Hasta";
	      	ResultSet select = stmt.executeQuery(aux);
	      	
	      
	    
	      	
	      	while(select.next()){

	      		String descripcion = select.getString("Nombre_Comercial");
	      		int precio = select.getInt("Precio");
	      		
	      		
	      		concepto =  new Concepto(id_concepto,descripcion,idTipoPruebaToInt(tipoPrueba));
	      		concepto.setPrecio(precio);
	      	}
	      }
	      catch (Exception e) {
	          System.err.println(e.getClass().getName() + ": " + e.getMessage());
	          System.exit(0);
	      }
		
		
		
		return concepto;
		
	}
	public static Concepto ObtenerDescripcionPrecioFungible (String tipoPrueba, int id_concepto, String fechaS){
		Connection c = conexion.conectarse();
	    //  PreparedStatement preparedStatement = null;
	      Concepto concepto = null;
	      Statement stmt = null;
	      try{
	      	stmt = c.createStatement();
	      	String aux="SELECT Nombre_Fungible, Precio FROM Guinea.Precios" 
					+" join  Guinea."+tipoPrueba +" on "+tipoPrueba+".ID_"+tipoPrueba+"=Precios.ID_Concepto"
					+" where ID_TiposPrueba like "+idTipoPruebaToInt(tipoPrueba)
					+" and "+ tipoPrueba+".Actividad like 1 and ID_Concepto like "+id_concepto
					+ " and  '"+fechaS+"' between Precios.Fecha_Desde and Precios.Fecha_Hasta";
	      	ResultSet select = stmt.executeQuery(aux);
	      	
	      
	    
	      	
	      	while(select.next()){

	      		String descripcion = select.getString("Nombre_Fungible");
	      		int precio = select.getInt("Precio");
	      		
	      		
	      		concepto =  new Concepto(id_concepto,descripcion,idTipoPruebaToInt(tipoPrueba));
	      		concepto.setPrecio(precio);
	      	}
	      }
	      catch (Exception e) {
	          System.err.println(e.getClass().getName() + ": " + e.getMessage());
	          System.exit(0);
	      }
		
		
		
		return concepto;
		
	}
	public static Paciente prueba(int DNI){
			  
			  Connection c = conexion.conectarse();
			        PreparedStatement preparedStatement = null;
			        Paciente paciente1 = null;
			        Statement stmt = null;
			        try{
			         stmt = c.createStatement();
			         ResultSet select = stmt.executeQuery("Select ID_Paciente, N_Historial, Nombre, Apellido1, Apellido2, Condicion, Telefono1, Telefono2,"
			           + " Telefono3, Poblacion, Direccion, Sexo, LetraDni, DNI, N_Asegurado, Actividad"
			           + " from Guinea.Paciente"
			           + " Where Paciente.DNI like '"+DNI+ "' ;");
			         
			         
			         while(select.next()){
			          int id_Paciente = select.getInt("ID_Paciente");
			          int n_Historial = select.getInt("N_Historial");
			          String nombre = select.getString("Nombre");
			          String apellido1 = select.getString("Apellido1");
			          String apellido2 = select.getString("Apellido2");
			          int condicion = select.getInt("Condicion");
			          int telefono1 = select.getInt("Telefono1");
			          int telefono2 = select.getInt("Telefono2");
			          int telefono3 = select.getInt("Telefono3");
			          String poblacion = select.getString("Poblacion");
			          String direccion = select.getString("Direccion");
			          int sexo = select.getInt("Sexo");
			          
			          String letraDni = select.getString("LetraDni");
			          int dni = select.getInt("DNI");
			          String n_Asegurado=select.getString("N_Asegurado");
			          int actividad=select.getInt("Actividad");
			          paciente1 =  new Paciente(id_Paciente, n_Historial, nombre,apellido1,apellido2,condicion,telefono1,telefono2,telefono3,
			            poblacion,direccion,sexo,dni,n_Asegurado);
			      paciente1.setActividad(actividad);
			      paciente1.setLetraDni(letraDni);
			         }
			        }
			        catch (Exception e) {
			            System.err.println(e.getClass().getName() + ": " + e.getMessage());
			            System.exit(0);
			        }
			  
			  return paciente1;
			  
			 }
	public static Boolean comprobarPaciente (Paciente pac){
	    Connection c = conexion.conectarse();
	      //  PreparedStatement preparedStatement = null;
	        Concepto concepto = null;
	        Statement stmt = null;
	        try{
	          stmt = c.createStatement();
	          String aux ="Select N_Historial, DNI, N_Asegurado"
		              + " from Guinea.Paciente"
		              +" WHERE EXISTS (Select N_Historial, DNI, N_Asegurado from Guinea.Paciente "
		              + " where N_Historial like '"+ pac.getN_historial() + "'"
		              +" or   DNI like '"+ pac.getDni() + "'"
		              +" or   N_Asegurado like '"+ pac.getN_Asegurado()+ "')"
		              		+ " and Actividad like 1 ;";
	          ResultSet select = stmt.executeQuery(aux);
	          
	        
	      
	          
	          if(select.next()){

	            return true; //Hay un paciente igual
	   
	          }
	        }
	        catch (Exception e) {
	            System.err.println(e.getClass().getName() + ": " + e.getMessage());
	            System.exit(0);
	        }
	    return false;
	  
	   
	}
	public static int[] comprobarLogin (String nom, String pas){
	    
	    Connection c = conexion.conectarse();
	        //  PreparedStatement preparedStatement = null;
	    // 0-Generico 1-Admin 2-Error
	          Vector<Fecha_Paciente> v_fecha = new Vector();
	          Fecha_Paciente fecha = null;
	          Statement stmt = null;
	          int[] idtipo = new int[2];
	          PreparedStatement pstmt = null;
	         
	            try{
	            	ResultSet select;
	            	pstmt = c.prepareStatement("Select TipoUsuario, ID_Usuario"
	                + " from  Guinea.Usuario "+
	                "Where Nombre like ?"
	                +" and  Password like ? "
	                + "and Actividad like 1;");
	            	
	            pstmt.setString(1, nom);
	            pstmt.setString(2, pas);
	            
	            
	            select = pstmt.executeQuery();
	            if(!select.next()){
	              idtipo[0]=-1;
	              return idtipo;
	            }
	              else { int tipo=select.getInt("TipoUsuario");
	              	int id=select.getInt("ID_Usuario");
	              	idtipo[0]=tipo;
	              	idtipo[1]=id;
	              	return idtipo;
	            }
	          }
	          catch (Exception e) {
	              System.err.println(e.getClass().getName() + ": " + e.getMessage());
	              System.exit(0);
	          }
	      return idtipo;
	      
	      
	  
	}
	public static int ultimoPrecioTratamiento(int tipoTratamiento, int id_concepto){
		Connection c = conexion.conectarse();
	      //  PreparedStatement preparedStatement = null;
	        int precio = 0;
	        
	        Statement stmt = null;
	        try{
	        	stmt = c.createStatement();
	        	ResultSet select = stmt.executeQuery("Select Precios.Precio"
	        			+ " from Guinea.Precios Where Precios.ID_TiposPrueba like "+tipoTratamiento +" and Precios.ID_Concepto "
	        					+ "like "+id_concepto+" and Precios.Fecha_Hasta like '2999-12-31 00:00:00' ;");
	        	
	        
	      
	        	
	        	if(select.next()){
	        		precio = select.getInt("Precio");

	      
	        	}
	        }
	        catch (Exception e) {
	            System.err.println(e.getClass().getName() + ": " + e.getMessage());
	            System.exit(0);
	        }
			
			return precio;
			
			
		}
	public static boolean modificarFecha_AltaPaciente( int idFecha, Timestamp fech,int id_usuario){

				Connection c = conexion.conectarse();
		        PreparedStatement preparedStatement = null;
		     
		        
		     try{
		         
		      
		         String insertPaciente = "Update Guinea.Fecha_Paciente SET Fecha_Paciente.Fecha_Alta = '"+fech+"' , Fecha_Paciente.ID_Usuario_Alta ='"+id_usuario
		        		 					+"' where Fecha_Paciente.ID_Fecha_Paciente like '" + idFecha+ "' ;";
		         
		         preparedStatement = c.prepareStatement(insertPaciente);
		    
		         preparedStatement.execute();
		         
		         return true;
		  
		  
		 }catch (Exception e) {
		        System.err.println(e.getClass().getName() + ": " + e.getMessage());
		        System.exit(0);
		    }
		     return false;
		
		
	}
	public static boolean IngresarPaciente(Paciente pac, Fecha_Paciente fp,int id_usuario){
		 Connection c = conexion.conectarse();
      PreparedStatement preparedStatement = null;
   
    
		      		try{
		       	
		      String ingresarPaciente = "Insert Into Guinea.Fecha_Paciente(ID_Paciente, Fecha_Ingreso, Fecha_Alta, ID_Usuario_Ingreso)"
		      		+" values(?,?,?,?);";
		       
		       preparedStatement = c.prepareStatement(ingresarPaciente);
		       preparedStatement.setInt(1, pac.getId_Paciente());
		       preparedStatement.setString(2, fp.getFecha_ingreso().toString());
		       preparedStatement.setString(3, fp.getFecha_alta().toString());
		       preparedStatement.setInt(4, id_usuario);
		       preparedStatement.execute();
		       
		       return true;
		
		
		}catch (Exception e) {
		      System.err.println(e.getClass().getName() + ": " + e.getMessage());
		      System.exit(0);
		  }
		   return false;
		}
	public static boolean IngresarFianza( int id_fecha_pac, int fianza, Timestamp fecha, int id_usuario){
		 Connection c = conexion.conectarse();
     PreparedStatement preparedStatement = null;
  
   
		      		try{
		       	
		      String ingresarPaciente = "Insert Into Guinea.Fianza(ID_Fecha_Paciente, Fianza, Fecha, ID_Usuario)"
		      		+" values(?,?,?,?);";
		       
		       preparedStatement = c.prepareStatement(ingresarPaciente);
		       preparedStatement.setInt(1, id_fecha_pac);
		       preparedStatement.setInt(2, fianza);
		       preparedStatement.setTimestamp(3, fecha);
		       preparedStatement.setInt(4, id_usuario);
		       preparedStatement.execute();
		       
		       return true;
		
		
		}catch (Exception e) {
		      System.err.println(e.getClass().getName() + ": " + e.getMessage());
		      System.exit(0);
		  }
		   return false;
		}
	public static int IngresarConcepto( String descripcion, String tipoPrueba){
		 Connection c = conexion.conectarse();
    PreparedStatement preparedStatement = null;
 
  
		      		try{//Insertamos la descripcion
		       	
		      String ingresarPaciente = "Insert Into Guinea."+tipoPrueba+"(Descripcion,Actividad)"
		      		+" values(?,?);";
		       
		       preparedStatement = c.prepareStatement(ingresarPaciente);
		       preparedStatement.setString(1, descripcion);
		       preparedStatement.setInt(2, 1);
		      
		       preparedStatement.execute();
		       
		      
		
		
		}catch (Exception e) {
		      System.err.println(e.getClass().getName() + ": " + e.getMessage());
		      System.exit(0);
		  }
		      		Statement stmt = null;
		      		int id = -1;
			        try{
			        	stmt = c.createStatement();
			        	
			        	ResultSet select = stmt.executeQuery("SELECT ID_"+tipoPrueba
			        										+" from Guinea."+tipoPrueba
			        										+" order by ID_"+tipoPrueba+" desc;");
			        	
			        
			      
			        	
			        	if(select.next()){
			        		 id = select.getInt("ID_"+tipoPrueba); 	      
			        	}
			        }
			        catch (Exception e) {
			            System.err.println(e.getClass().getName() + ": " + e.getMessage());
			            System.exit(0);
			        }
					
					return id;
		}
	public static int IngresarMedicamento( String descripcion, String tipoPrueba,String farmaco){
		 Connection c = conexion.conectarse();
		 PreparedStatement preparedStatement = null;

 
		      		try{//Insertamos la descripcion
		       	
		      String ingresarPaciente = "Insert Into Guinea.MEDICAMENTOS(Farmaco,Actividad,Nombre_Comercial)"
		      		+" values(?,?,?);";
		       
		       preparedStatement = c.prepareStatement(ingresarPaciente);
		       preparedStatement.setString(3, descripcion);
		       preparedStatement.setInt(2, 1);
		       preparedStatement.setString(1, farmaco);
		       preparedStatement.execute();
		       
		      
		
		
		}catch (Exception e) {
		      System.err.println(e.getClass().getName() + ": " + e.getMessage());
		      System.exit(0);
		  }
		      		Statement stmt = null;
		      		int id = -1;
			        try{
			        	stmt = c.createStatement();
			        	
			        	ResultSet select = stmt.executeQuery("SELECT ID_MEDICAMENTOS"
			        										+" from Guinea.MEDICAMENTOS"
			        										+" order by ID_MEDICAMENTOS desc;");
			        	
			        
			      
			        	
			        	if(select.next()){
			        		 id = select.getInt("ID_MEDICAMENTOS"); 	      
			        	}
			        }
			        catch (Exception e) {
			            System.err.println(e.getClass().getName() + ": " + e.getMessage());
			            System.exit(0);
			        }
					
					return id;
		}
	public static int IngresarFungible( String descripcion, String tipoPrueba,String ref1,String ref2,String marca){
		 Connection c = conexion.conectarse();
		 PreparedStatement preparedStatement = null;


		      		try{//Insertamos la descripcion
		       	
		      String ingresarPaciente = "Insert Into Guinea.FUNGIBLES(Referencia1,Referencia2,Actividad,Marca,Nombre_Fungible)"
		      		+" values(?,?,?,?,?);";
		       
		       preparedStatement = c.prepareStatement(ingresarPaciente);
		       preparedStatement.setString(1, ref1);
		       preparedStatement.setString(2, ref2);
		       preparedStatement.setInt(3, 1);
		       preparedStatement.setString(4,marca);
		       preparedStatement.setString(5,descripcion);
		       preparedStatement.execute();
		       
		      
		
		
		}catch (Exception e) {
		      System.err.println(e.getClass().getName() + ": " + e.getMessage());
		      System.exit(0);
		  }
		      		Statement stmt = null;
		      		int id = -1;
			        try{
			        	stmt = c.createStatement();
			        	
			        	ResultSet select = stmt.executeQuery("SELECT ID_FUNGIBLES"
			        										+" from Guinea.FUNGIBLES"
			        										+" order by ID_FUNGIBLES desc;");
			        	
			        
			      
			        	
			        	if(select.next()){
			        		 id = select.getInt("ID_MEDICAMENTOS"); 	      
			        	}
			        }
			        catch (Exception e) {
			            System.err.println(e.getClass().getName() + ": " + e.getMessage());
			            System.exit(0);
			        }
					
					return id;
		}
	public static boolean IngresarPrecio( int id_tipoPrueba, int  id_concepto,int precio){
		 Connection c = conexion.conectarse();
   PreparedStatement preparedStatement = null;

 
		   	try{//Insertamos la descripcion
		     String ingresarPaciente = "Insert Into Guinea.Precios(ID_TiposPrueba,ID_Concepto,Fecha_Desde, Fecha_Hasta, Precio)"
		    	+" values(?,?,?,?,?);";
		    Timestamp timestamp = new Timestamp(System.currentTimeMillis());
		    preparedStatement = c.prepareStatement(ingresarPaciente);
		    preparedStatement.setInt(1, id_tipoPrueba);
		    preparedStatement.setInt(2, id_concepto);
		    preparedStatement.setTimestamp(3,timestamp);
		    preparedStatement.setTimestamp(4,Timestamp.valueOf("2999-12-31 00:00:00"));
		    preparedStatement.setInt(5, precio);
		       preparedStatement.execute();
		       
		      return true;
		
		
		}catch (Exception e) {
		      System.err.println(e.getClass().getName() + ": " + e.getMessage());
		      System.exit(0);
		  }
		   	return false;
	}
	public static boolean modificarActividadPreciosAlta( String tipoPrueba, int id_concepto){

		Connection c = conexion.conectarse();
        PreparedStatement preparedStatement = null;
     
        
     try{
         
      
         String insertPaciente = "Update Guinea."+tipoPrueba+" SET Actividad = ?  "
        		 					+" where ID_"+tipoPrueba+" like " + id_concepto;
         
         preparedStatement = c.prepareStatement(insertPaciente);
         preparedStatement.setInt(1, 1);
         
         preparedStatement.execute();
         
         return true;
  
  
 }catch (Exception e) {
        System.err.println(e.getClass().getName() + ": " + e.getMessage());
        System.exit(0);
    }
     return false;


}
	public static boolean modificarActividadPreciosBaja( String tipoPrueba, int id_concepto){

		Connection c = conexion.conectarse();
        PreparedStatement preparedStatement = null;
     
        
     try{
         
      
         String insertPaciente = "Update Guinea."+tipoPrueba+" SET Actividad = ?  "
        		 					+" where ID_"+tipoPrueba+" like " + id_concepto;
         
         preparedStatement = c.prepareStatement(insertPaciente);
         preparedStatement.setInt(1, 0);
         
         preparedStatement.execute();
         
         return true;
  
  
 }catch (Exception e) {
        System.err.println(e.getClass().getName() + ": " + e.getMessage());
        System.exit(0);
    }
     return false;


}
	public static boolean modificarPreciosAntiguo( int  tipoPrueba, int id_concepto){

		Connection c = conexion.conectarse();
        PreparedStatement preparedStatement = null;
     
        
     try{
         
      
         String insertPaciente = "Update Guinea.Precios SET Fecha_Hasta = ?  "
        		 					+" where ID_TiposPrueba like " + tipoPrueba +" and  ID_Concepto like "+id_concepto+" ;";
         Timestamp timestamp = new Timestamp(System.currentTimeMillis());
         preparedStatement = c.prepareStatement(insertPaciente);
         preparedStatement.setTimestamp(1, timestamp);
         
         preparedStatement.execute();
         
         return true;
  
  
 }catch (Exception e) {
        System.err.println(e.getClass().getName() + ": " + e.getMessage());
        System.exit(0);
    }
     return false;


}	
	public static Vector<String> tipotratamiento(){

		Connection c = Conexion.conectarse();
	      //  PreparedStatement preparedStatement = null;
	
	        PreparedStatement pstmt = null;
	        Vector<String> arString = new Vector();
	        String aux;
	        PreparedStatement preparedStatement = null;
	           //Paciente paciente1 = null;
	           Statement stmt = null;
	            
	            try{
	             stmt = c.createStatement();
	             ResultSet select = stmt.executeQuery("SELECT NombreTipo FROM Guinea.TiposPrueba;");
	        			        	
	  	       
	        	
	        	while(select.next()){
	        		
	        		aux = select.getString("NombreTipo");
	        		arString.add(aux);
	      
	        	}
	        }
	        catch (Exception e) {
	            System.err.println(e.getClass().getName() + ": " + e.getMessage());
	            System.exit(0);
	        }
			
		
		return arString;
	}
	public static int idTipoPruebaToInt(String arg){
	if(arg.equals("EMBARAZADA")){return 1;
	}else if(arg.equals("ECOGRAFIA")){return 2;}
	else if(arg.equals("LABORATORIO")) {return 3;}
	else if(arg.equals("MAMOGRAFIA")){return 4;}
	else if(arg.equals("ODONTOESTOMATOLOGIA")){return 5;}
	else if(arg.equals("OTORRINOLARINGOLOGIA")){return 6;}
	else if(arg.equals("PROCESOSQUIRURGICOS")){return 7;}
	else if(arg.equals("RADIOLOGIASIMPLE")){return 8;}
	else if(arg.equals("MEDICAMENTOS")){return 9;}
	else if(arg.equals("FUNGIBLES")){return 10;}
	
	return -1;
	
}
	public static Vector<Concepto>obtener_MedicamentosActivos(){
		
		Connection c = conexion.conectarse();
	      //  PreparedStatement preparedStatement = null;
	        Vector<Concepto> v_conceptos = new Vector();
	        Concepto concepto = null;
	        Statement stmt = null;
	        try{
	        	stmt = c.createStatement();
	        	ResultSet select = stmt.executeQuery("SELECT ID_MEDICAMENTOS, Nombre_Comercial, Farmaco, Precio  FROM Guinea.Precios " 
	        										+" join  Guinea.MEDICAMENTOS on MEDICAMENTOS.ID_MEDICAMENTOS =Precios.ID_Concepto"
	        										+" where ID_TiposPrueba like 9"
	        										+" and MEDICAMENTOS.Actividad like 1 and Precios.Fecha_Hasta like '2999-12-31 00:00:00'"
	        										+" order by Farmaco ;");
	        	
	        
	      
	        	
	        	while(select.next()){
	        		int id = select.getInt("ID_MEDICAMENTOS");
	        		String descripcion = select.getString("Nombre_Comercial");
	        		String marca=select.getString("Farmaco");
	        		int precio=select.getInt("Precio");
	        		concepto =  new Concepto(id,descripcion,9,marca);
	        		
	        		concepto.setPrecio(precio);
	        		v_conceptos.add(concepto);
	      
	        	}
	        }
	        catch (Exception e) {
	            System.err.println(e.getClass().getName() + ": " + e.getMessage());
	            System.exit(0);
	        }
			
			return v_conceptos;
			
			
		}
	public static Vector<Concepto>obtener_MedicamentosInactivos(){
		
		Connection c = conexion.conectarse();
	      //  PreparedStatement preparedStatement = null;
	        Vector<Concepto> v_conceptos = new Vector();
	        Concepto concepto = null;
	        Statement stmt = null;
	        try{
	        	stmt = c.createStatement();
	        	ResultSet select = stmt.executeQuery("SELECT ID_MEDICAMENTOS, Nombre_Comercial, Farmaco, Precio  FROM Guinea.Precios " 
	        										+" join  Guinea.MEDICAMENTOS on MEDICAMENTOS.ID_MEDICAMENTOS =Precios.ID_Concepto"
	        										+" where ID_TiposPrueba like 9"
	        										+" and MEDICAMENTOS.Actividad like 0 and Precios.Fecha_Hasta like '2999-12-31 00:00:00'"
	        										+" order by Farmaco ;");
	        
	      
	        	
	        	while(select.next()){
	        		int id = select.getInt("ID_MEDICAMENTOS");
	        		String descripcion = select.getString("Nombre_Comercial");
	        		String marca=select.getString("Farmaco");
	        		int precio=select.getInt("Precio");
	        		concepto =  new Concepto(id,descripcion,9,marca);
	        		
	        		concepto.setPrecio(precio);
	        		v_conceptos.add(concepto);
	      
	        	}
	        }
	        catch (Exception e) {
	            System.err.println(e.getClass().getName() + ": " + e.getMessage());
	            System.exit(0);
	        }
			
			return v_conceptos;
			
			
		}
	public static Vector<Concepto>obtener_FungiblesActivos(){
	
	Connection c = conexion.conectarse();
      //  PreparedStatement preparedStatement = null;
        Vector<Concepto> v_conceptos = new Vector();
        Concepto concepto = null;
        Statement stmt = null;
        try{
        	stmt = c.createStatement();
        	ResultSet select = stmt.executeQuery("SELECT ID_FUNGIBLES, Nombre_Fungible, Marca, Precio, Referencia1, Referencia2  FROM Guinea.Precios " 
	        										+" join  Guinea.FUNGIBLES on FUNGIBLES.ID_Fungibles =Precios.ID_Concepto"
	        										+" where ID_TiposPrueba like 10"
	        										+" and FUNGIBLES.Actividad like 1  and Precios.Fecha_Hasta like '2999-12-31 00:00:00'"
        											+" order by Nombre_Fungible ;");
        
      
        	
        	while(select.next()){
        		int id = select.getInt("ID_FUNGIBLES");
        		String descripcion = select.getString("Nombre_Fungible");
        		String marca=select.getString("Marca");
        		String ref1=select.getString("Referencia1");
        		String ref2=select.getString("Referencia2");
        		int precio=select.getInt("Precio");
        		
        		
        		
        		concepto =  new Concepto(id,descripcion,10,ref1,ref2,marca);
        		concepto.setPrecio(precio);
        		v_conceptos.add(concepto);
      
        	}
        }
        catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
		
		return v_conceptos;
		
		
	}
	public static Vector<Concepto>obtener_FungiblesInactivos(){
		
		Connection c = conexion.conectarse();
	      //  PreparedStatement preparedStatement = null;
	        Vector<Concepto> v_conceptos = new Vector();
	        Concepto concepto = null;
	        Statement stmt = null;
	        try{
	        	stmt = c.createStatement();
	        	ResultSet select = stmt.executeQuery("SELECT ID_FUNGIBLES, Nombre_Fungible, Marca, Precio, Referencia1, Referencia2  FROM Guinea.Precios " 
		        										+" join  Guinea.FUNGIBLES on FUNGIBLES.ID_Fungibles =Precios.ID_Concepto"
		        										+" where ID_TiposPrueba like 10"
		        										+" and FUNGIBLES.Actividad like 0  and Precios.Fecha_Hasta like '2999-12-31 00:00:00'"
	        											+" order by Nombre_Fungible ;");
	        
	      
	        	
	        	while(select.next()){
	        		int id = select.getInt("ID_FUNGIBLES");
	        		String descripcion = select.getString("Nombre_Fungible");
	        		String marca=select.getString("Marca");
	        		String ref1=select.getString("Referencia1");
	        		String ref2=select.getString("Referencia2");
	        		int precio=select.getInt("Precio");
	        		
	        		
	        		
	        		concepto =  new Concepto(id,descripcion,10,ref1,ref2,marca);
	        		concepto.setPrecio(precio);
	        		v_conceptos.add(concepto);
	      
	        	}
	        }
	        catch (Exception e) {
	            System.err.println(e.getClass().getName() + ": " + e.getMessage());
	            System.exit(0);
	        }
			
			return v_conceptos;
			
			
		}
	public static Vector<Paciente> obtenerPacientes(int actividad){
	    Connection c = conexion.conectarse();
	    Paciente paciente;
	    Vector<Paciente> v_pacientes = new Vector();
	    Statement stmt = null;
	        try{
	          stmt = c.createStatement();
	          ResultSet select = stmt.executeQuery("Select *"
	              + " from Guinea.Paciente"
	              + " where Actividad like "+actividad+";");      
	        
	          while(select.next()){
	            String nombre = select.getString("Nombre");
	            int n_historial= select.getInt("N_Historial");
	            String n_asegurado = select.getString("N_Asegurado");
	            int dip = select.getInt("DNI");
	            int telefono =  select.getInt("Telefono1"); 
	             
	            paciente =  new Paciente(nombre,n_historial,n_asegurado,dip,telefono);
	            paciente.setActividad(actividad);
	            v_pacientes.add(paciente);
	            
	   
	          }
	        }
	        catch (Exception e) {
	            System.err.println(e.getClass().getName() + ": " + e.getMessage());
	            System.exit(0);
	        }
	    
	    return v_pacientes;
	  }
	public static int obtenerFianzatotal(int id_fecha){
		Connection c = conexion.conectarse();
	      //  PreparedStatement preparedStatement = null;
	       int fianza=0;
	        Concepto concepto = null;
	        Statement stmt = null;
	        try{
	        	stmt = c.createStatement();
	        	ResultSet select = stmt.executeQuery("SELECT sum(Fianza) as 'Total'  FROM Guinea.Fianza"
	        			+" where ID_Fecha_Paciente like "+id_fecha);
	        
	      
	        	
	        	if(select.next()){
	        		fianza=select.getInt("Total");
	      
	        	}
	        }
	        catch (Exception e) {
	            System.err.println(e.getClass().getName() + ": " + e.getMessage());
	            System.exit(0);
	        }
			
			return fianza;
			
		
	}
	public static boolean modificarActividadUsario(int id_usu, int actividad){
	    Connection c = conexion.conectarse();
	        PreparedStatement preparedStatement = null;

	        try{
	 
	         String insertPaciente = "Update Guinea.Usuario SET Actividad = ?  "
	                       +" where ID_Usuario like " + id_usu;
	         
	         preparedStatement = c.prepareStatement(insertPaciente);
	         
	         preparedStatement.setInt(1, actividad);
	         
	         preparedStatement.execute();
	         
	         return true;
	  
	  
	 }catch (Exception e) {
	        System.err.println(e.getClass().getName() + ": " + e.getMessage());
	        System.exit(0);
	    }
	     return false;


	  }
	public static boolean comprobarUsuarioActivo(String usuario){
	    Connection c = conexion.conectarse();
	        //  PreparedStatement preparedStatement = null;
	    // 0-Generico 1-Admin 2-Error
	         
	          
	          Statement stmt = null;
	          
	          PreparedStatement pstmt = null;
	         
	            try{
	             ResultSet select;
	             pstmt = c.prepareStatement("Select TipoUsuario, ID_Usuario"
	                + " from  Guinea.Usuario "+
	                "Where Nombre like ?"
	               
	                + " and Actividad like 1;");
	             
	            pstmt.setString(1, usuario);
	          
	            
	            
	            select = pstmt.executeQuery();
	            if(select.next()){
	            return true;
	            }
	              else {  return false;          }
	          }catch (Exception e) {
	              System.err.println(e.getClass().getName() + ": " + e.getMessage());
	              System.exit(0);
	          }
	      return false;
	  }
	public static boolean insertarUsuarioActivo(String us, String con){
	     Connection c = conexion.conectarse();
	          PreparedStatement preparedStatement = null;
	       
	          
	       try{
	           
	        
	           String insertPaciente = "Insert Into Guinea.Usuario(Nombre,Password,TipoUsuario,Actividad) "
	             +"values(?,?,?,?);";
	           
	           preparedStatement = c.prepareStatement(insertPaciente);
	           preparedStatement.setString(1, us);
	           preparedStatement.setString(2, con);
	           preparedStatement.setInt(3, 0);
	           preparedStatement.setInt(4, 1);
	           preparedStatement.execute();
	               return true;
	    
	    
	   }catch (Exception e) {
	          System.err.println(e.getClass().getName() + ": " + e.getMessage());
	          System.exit(0);
	      }
	       return false;
	  }
	public static boolean modificarPacienteInt(String modificar , Paciente pac, int g){
	    
	    Connection c = conexion.conectarse();
	        PreparedStatement preparedStatement = null;
	     
	        
	     try{
	         
	      
	         String insertPaciente = "Update Guinea.Paciente SET "+ modificar +" = ? "
	                       +" where Paciente.ID_Paciente like " + pac.getId_Paciente();
	         
	         preparedStatement = c.prepareStatement(insertPaciente);
	         preparedStatement.setInt(1, g);
	         preparedStatement.execute();
	         
	         return true;
	  
	  
	 }catch (Exception e) {
	        System.err.println(e.getClass().getName() + ": " + e.getMessage());
	        System.exit(0);
	    }
	     return false;
	    
	  }
	public static boolean modificarPacienteString(String modificar, Paciente pac, String g){
	    
	    Connection c = conexion.conectarse();
	        PreparedStatement preparedStatement = null;
	        
	     try{
	         
	      
	         String insertPaciente = "Update Guinea.Paciente SET "+ modificar +" = ? "
	                       +" where Paciente.ID_Paciente like " + pac.getId_Paciente();
	         
	         preparedStatement = c.prepareStatement(insertPaciente);
	         preparedStatement.setString(1, g);
	         preparedStatement.execute();
	         
	         return true;
	  
	  
	 }catch (Exception e) {
	        System.err.println(e.getClass().getName() + ": " + e.getMessage());
	        System.exit(0);
	    }
	     return false;
	    
	  }
	  public static boolean esAlfaNumerica( String cadena) {
	    int cont = 0;
	      for(int i = 0; i < cadena.length(); ++i) {
	          char caracter = cadena.charAt(i);
	   
	          if(!Character.isDigit(caracter)) 
	             cont ++; 
	          }
	      if (cont == cadena.length()){return false;}
	      return true;
	  }
	  public static boolean AnadirTratamiento(Paciente pac,int cantidad,int tipoprueba, Timestamp fecha, int concepto,int id_usuario,int id_precio){
				Connection c = conexion.conectarse();
			    PreparedStatement preparedStatement = null;
			    
			    
			 try{
			     
			  
			     String insertPaciente = "Insert Into Guinea.Tratamiento(ID_Paciente, Cantidad,TipoPrueba,Fecha,ID_Concepto, Actividad, ID_Usuario,id_Precios)"
			       +"values(?,?,?,?,?,?,?,?);";
			     
			     preparedStatement = c.prepareStatement(insertPaciente);
			     preparedStatement.setInt(1, pac.getId_Paciente());
			     preparedStatement.setInt(2, cantidad);
			     preparedStatement.setInt(3, tipoprueba);
			     preparedStatement.setTimestamp(4, fecha);
			     preparedStatement.setInt(5, concepto);
			     preparedStatement.setInt(6, 1);
			     preparedStatement.setInt(7, id_usuario);
			     preparedStatement.setInt(8, id_precio);
			     preparedStatement.execute();
			     
			     return true;
			
			
			}catch (Exception e) {
			    System.err.println(e.getClass().getName() + ": " + e.getMessage());
			    System.exit(0);
			}
			 return false;
				}
	  public static int ObtenerIDPrecio (int tipoPrueba, int id_concepto, String fechaS){
			Connection c = conexion.conectarse();
		    //  PreparedStatement preparedStatement = null;
		      int id=-1 ;
		      Statement stmt = null;
		      try{
		      	stmt = c.createStatement();
		      	String aux="SELECT  id_Precios FROM Guinea.Precios" 
						
						+" where ID_TiposPrueba like "+tipoPrueba
						+" and ID_Concepto like "+id_concepto
						+ " and  '"+fechaS+"' between Precios.Fecha_Desde and Precios.Fecha_Hasta ;";
		      	ResultSet select = stmt.executeQuery(aux);
		      	
		      
		    
		      	
		      	if(select.next()){

		      		id=select.getInt("id_Precios");
		      		
		      		
		      		
		      	}
		      }
		      catch (Exception e) {
		          System.err.println(e.getClass().getName() + ": " + e.getMessage());
		          System.exit(0);
		      }
			
			
			
			return id;
			
		}
	  public static int ObtenerGastototalporTipoPrueba(int tipoPrueba, String fechadesde, String fechahasta){
			Connection c = conexion.conectarse();
		    //  PreparedStatement preparedStatement = null;
		      int total=-1 ;
		      Statement stmt = null;
		      try{
		      	stmt = c.createStatement();
		      	String aux="SELECT sum(Precio*Cantidad) as Total from Guinea.Tratamiento "
		      			+"inner join Guinea.Paciente on Tratamiento.ID_Paciente = Paciente.ID_Paciente " 
		      			+"inner join Guinea.Precios on Tratamiento.id_Precios = Precios.id_Precios "
		      			+"Where Tratamiento.TipoPrueba like "+tipoPrueba+ " and Tratamiento.Fecha between '"+fechadesde+"' and '"+fechahasta+"' ;";


		      	ResultSet select = stmt.executeQuery(aux);
		      	
		      
		    
		      	
		      	if(select.next()){

		      		total=select.getInt("Total");
		      		
		      		
		      		
		      	}
		      }
		      catch (Exception e) {
		          System.err.println(e.getClass().getName() + ": " + e.getMessage());
		          System.exit(0);
		      }
			
			
			
			return total;
			
		}
	  public static Vector<Usuario>UsuariosAdministracion(int actividad){
		  Connection c = conexion.conectarse();
	        //  PreparedStatement preparedStatement = null;
	    Vector<Usuario>v_us=new Vector();
	          Statement stmt = null;
	          Usuario us;
	          try{
	  	      	stmt = c.createStatement();
	  	      	String aux="Select TipoUsuario, Nombre, ID_Usuario from Guinea.Usuario "
	  	      			+ "where Usuario.Actividad like'"+actividad+"';";
	  	      	ResultSet select = stmt.executeQuery(aux);
	  	      	
	  	      
	  	    
	  	      	
	  	      	while(select.next()){

	  	      		String nombre = select.getString("Nombre");
	  	      		int tipous = select.getInt("TipoUsuario");
	  	      		int id=select.getInt("ID_Usuario");
	  	      		us=new Usuario(nombre,tipous,id);
	  	      		v_us.add(us);
	  	      		
	  	      	}
	  	      }
	          catch (Exception e) {
	              System.err.println(e.getClass().getName() + ": " + e.getMessage());
	              System.exit(0);
	          }
	      return v_us;
	      
	  }
}