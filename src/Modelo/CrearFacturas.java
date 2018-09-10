package Modelo;

import java.awt.Color;
import java.awt.Desktop;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Timestamp;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Vector;
import java.util.concurrent.TimeUnit;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.FontSelector;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

public class CrearFacturas {
	static int totaldias;
	public CrearFacturas(){
		
	}
	public void mostrarFactura(Paciente pac , Fecha_Paciente fepac, Vector<Integer>vtotal, int total, int fianza)  throws Exception{
   	 Image imagen1 = Image.getInstance("utilidades"+File.separator+"logoasisa.png");
        Image imagen2 = Image.getInstance("utilidades"+File.separator+"barra.png");
        Image imagen3 = Image.getInstance("utilidades"+File.separator+"barra.png");
        Paragraph paciente = new Paragraph("\n\n\nPACIENTE "
              +"              "+pac.getNombre());
        //Paragraph pacienter = new Paragraph(pac.getNombre());
        Paragraph N_Historial = new Paragraph("\nN� HISTORIA "
              +"              "+pac.getN_historial());
        //Paragraph N_Historialr = new Paragraph(pac.getN_historial());
        Paragraph Direccion = new Paragraph("\nDIRECCION "
                +"              "+pac.getDireccion());
        //Paragraph Direccionr = new Paragraph(pac.getDireccion());
        Paragraph telefono = new Paragraph("\nTELEFONO "
               + "              "+pac.getTelf1());
        //Paragraph telefonor = new Paragraph(pac.getTelf1()));
        Paragraph auxiliar = new Paragraph("\n\n\n");
        String S1;
        if(fepac.getFecha_alta().toString().equals("2999-12-31 00:00:00.0")){
        	Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        	S1 = new SimpleDateFormat("dd - MM - yyyy").format(timestamp);
        }else {
        	S1 = new SimpleDateFormat("dd - MM - yyyy").format(fepac.getFecha_alta());
        }
        String S = new SimpleDateFormat("dd - MM - yyyy").format(fepac.getFecha_ingreso());
       
       
        Paragraph fecha_ingreso_alta = new Paragraph("\n\n\n\n\n\nFECHA DE INGRESO"
            + "                                               " +"FECHA DE ALTA");
        Paragraph fecha_ingreso_altar = new Paragraph("\n"+ S
            +"                                                            "+S1);
        
        
        try{
          
          Document document = new Document();
            PdfWriter pdf =  PdfWriter.getInstance(document, new FileOutputStream("."+File.separator+"Factura"+File.separator+pac.getDni()+".pdf") );
           // imagen.setBorderWidth(5);
           // imagen.setBorderColor(BaseColor.BLACK);
            imagen1.scaleAbsolute(350f, 50f);//el tamaño la primera es el ancho la segunda el ato
            imagen1.setAbsolutePosition(70, 760);//donde quieres colocarla la segunda el alto empezando desde abajo
                              //la primera la colocacion horizontalempezando desde la izquiera
            imagen2.scaleAbsolute(570f, 100f);//el tamaño
            imagen2.setAbsolutePosition(0, 660);//donde quieres colocarla
            imagen3.scaleAbsolute(570f, 100f);//el tamaño
            imagen3.setAbsolutePosition(0, 550);//donde quieres colocarla
           //Colocacion de los parrafos desde la izquierda
            fecha_ingreso_alta.setIndentationLeft(50);
            fecha_ingreso_altar.setIndentationLeft(60);
            paciente.setIndentationLeft(50); 
            N_Historial.setIndentationLeft(50);
            Direccion.setIndentationLeft(50);
            telefono.setIndentationLeft(50);
          
              document.open();
              document.add(imagen2);
              document.add(imagen1);
              document.add(fecha_ingreso_alta);
              document.add(fecha_ingreso_altar);
              document.add(imagen3);
              document.add(paciente);
              document.add(N_Historial);
              document.add(Direccion);
              document.add(telefono);
              document.add(auxiliar);
              long diff;
              if(fepac.getFecha_alta().toString().equals("2999-12-31 00:00:00.0")){
            	  Timestamp timestamp = new Timestamp(System.currentTimeMillis());
            	   diff = timestamp.getTime()-fepac.getFecha_ingreso().getTime();
              }else{
               diff = fepac.getFecha_alta().getTime()-fepac.getFecha_ingreso().getTime();
              }
              int dias= (int) TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS);
              dias-=1;
	        document.add(createtable(dias,vtotal,fianza));
	        PdfPTable table = new PdfPTable(2);
	          PdfPCell cell = new PdfPCell(new Phrase("Total de Factura"));
	          table.setHorizontalAlignment(Element.ALIGN_RIGHT);
	          table.addCell(cell);
	          NumberFormat nf = NumberFormat.getInstance();
	          cell = new PdfPCell(new Phrase(String.valueOf(nf.format(total+totaldias))));
	          cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
	          table.addCell(cell);
	         
	          PdfPTable table1 = new PdfPTable(2);
	          PdfPCell cell1 = new PdfPCell(new Phrase("Total a pagar"));
	          table.setHorizontalAlignment(Element.ALIGN_RIGHT);
	          table.addCell(cell1);
	         
	          if(pac.getCondicion() == 2){
	        	  if(vtotal.get(0)>0){
	        		  PdfPCell cell2 = new PdfPCell(new Phrase(String.valueOf(nf.format((vtotal.get(0)-fianza)))));
	    	          cell2.setHorizontalAlignment(Element.ALIGN_RIGHT);
	    	          table.addCell(cell2);
	        	  }else 
	          cell1 = new PdfPCell(new Phrase(String.valueOf(nf.format((total+totaldias)-fianza))));
	          }else cell1 = new PdfPCell(new Phrase("0"));
	          cell1.setHorizontalAlignment(Element.ALIGN_RIGHT);
	          table.addCell(cell1);
	          
	        document.add(auxiliar);
	        document.add(table);
	        document.close();
	        try {
                  File path = new File ("."+File.separator+"Factura"+File.separator+pac.getDni()+".pdf");
                  Desktop.getDesktop().open(path);
             }catch (IOException ex) {
                  ex.printStackTrace();
             }
   	}catch (DocumentException e){
   		e.printStackTrace();
   	}
   	
   }
   public static PdfPTable createtable(int dias,Vector<Integer>vtotal, int fianza) {
   	// a table with three columns
       PdfPTable table = new PdfPTable(4);
       
       // the cell object
       PdfPCell cell;
       Paragraph phs;
       NumberFormat nf = NumberFormat.getInstance();
       // we add a cell with colspan 3
       cell = new PdfPCell(new Phrase("Cantidad"));
    cell.setBackgroundColor(new BaseColor(62,143,149));
       table.addCell(cell);
       cell = new PdfPCell(new Phrase("Descripcion"));
       cell.setBackgroundColor(new BaseColor(62,143,149));
       table.addCell(cell);
       cell = new PdfPCell(new Phrase("Precio Unitario"));
       cell.setBackgroundColor(new BaseColor(62,143,149));
       table.addCell(cell);
       cell = new PdfPCell(new Phrase("Total"));
       cell.setBackgroundColor(new BaseColor(62,143,149));
       table.addCell(cell);
       // now we add a cell with rowspan 2
       cell = new PdfPCell(new Phrase(String.valueOf(dias)));
       cell.setHorizontalAlignment(Element.ALIGN_CENTER);
       table.addCell(cell);
       
       cell = new PdfPCell(new Phrase("Dias de hospitalizacion"));
       table.addCell(cell);
       phs=new Paragraph("60.000 CFA");
      
       cell = new PdfPCell(phs);
       //phs.setAlignment(Element.ALIGN_RIGHT);
       cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
       table.addCell(cell);
       
       
          	
      totaldias=60000*dias;
       if(dias>0){
    	   phs=new Paragraph(String.valueOf(nf.format(totaldias)));
    	      
           cell = new PdfPCell(phs);
           cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
     table.addCell(cell);
       }else table.addCell(" ");
       table.addCell(" ");
     	table.addCell("Medicamentos");
     	table.addCell(" ");
     	
       if(vtotal.get(8)!=0){
    	   phs=new Paragraph(String.valueOf(nf.format(vtotal.get(8))));
    	   cell = new PdfPCell(phs);
    	   cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
          	table.addCell(cell);
          }else table.addCell(" ");
       table.addCell(" ");
     	table.addCell("Fungibles");
     	table.addCell(" ");
     	
       if(vtotal.get(9)!=0){
    	   phs=new Paragraph(String.valueOf(nf.format(vtotal.get(9))));
    	   cell = new PdfPCell(phs);
    	   cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
          	table.addCell(cell);
          	
          }else table.addCell(" ");
       table.addCell(" ");
     	table.addCell("Radiologia");
     	table.addCell(" ");
     	
       if(vtotal.get(7)!=0||vtotal.get(1)!=0||vtotal.get(3)!=0){
    	   phs=new Paragraph(String.valueOf(nf.format(vtotal.get(7)+vtotal.get(1)+vtotal.get(3))));
    	   cell = new PdfPCell(phs);
    	   cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
          	table.addCell(cell);
          	
          }else table.addCell(" ");
       table.addCell(" ");
     	table.addCell("Laboratorio");
     	table.addCell(" ");
     	
       if(vtotal.get(2)!=0){
    	   phs=new Paragraph(String.valueOf(nf.format(vtotal.get(2))));
    	   cell = new PdfPCell(phs);
    	   cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
          	table.addCell(cell);
          
          }else table.addCell(" ");
       table.addCell(" ");
     	table.addCell("Intervenciones");
     	table.addCell(" ");
      if(vtotal.get(6)!=0){
    	  phs=new Paragraph(String.valueOf(nf.format(vtotal.get(6))));
   	   cell = new PdfPCell(phs);
   	   cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
         	table.addCell(cell);
      	
      }else table.addCell(" ");
   
       if(vtotal.get(4)!=0){
       	table.addCell(" ");
       	table.addCell("Odontoestomatologia");
       	table.addCell(" ");
       	phs=new Paragraph(String.valueOf(nf.format(vtotal.get(4))));
    	   cell = new PdfPCell(phs);
    	   cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
    	   table.addCell(cell);
       }
       if(vtotal.get(5)!=0){
       	table.addCell(" ");
       	table.addCell("Otorrinolaringologia");
       	table.addCell(" ");
       	phs=new Paragraph(String.valueOf(nf.format(vtotal.get(5))));
    	   cell = new PdfPCell(phs);
    	   cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
    	   table.addCell(cell);
       }
       
      
       
       
       if(vtotal.get(0)!=0){
    	   table.addCell(" ");
         	
         
         	FontSelector selector = new FontSelector();
         	Font ft= new Font();
         	BaseColor col= new BaseColor (0, 48, 110);
         	ft.setColor(col);
         	selector.addFont(ft);
        	Phrase ph=selector.process("Embarazo");
     	   cell = new PdfPCell();
     	   cell.addElement(ph);
     	   table.addCell(cell);
         	table.addCell(" ");
         	 ph=selector.process(String.valueOf(nf.format(vtotal.get(0))));
        	   cell = new PdfPCell(ph);
        	   cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
        	   table.addCell(cell);
       }
       table.addCell(" ");
   		table.addCell("Fianza");
   		table.addCell(" ");
   		phs=new Paragraph(String.valueOf(nf.format(fianza)));
    	   cell = new PdfPCell(phs);
    	   cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
    	   table.addCell(cell);
    	   table.setWidthPercentage(100F);
      float [] medidaCelda={0.55f,2.25f,1.00f,1.00f};
      
      try {
		table.setWidths(medidaCelda);
	} catch (DocumentException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
       return table;
   }
}
