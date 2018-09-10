package Modelo;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.Vector;

import javax.imageio.ImageIO;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.data.category.DefaultCategoryDataset;



public class ProgressBarAdministrador {

		
		
		 JFreeChart chart;
		
		public float porcentajeEmba=0.0f;
		public float porcentajeEco=0.0f;
		public float porcentajeLab=0.0f;;
		public float porcentajeMamo=0.0f;;
		public float porcentajeOdonto=0.0f;;
		public float porcentajeOtorrino=0.0f;;
		public float porcentajePquiro=0.0f;;
		public float porcentajeRadio=0.0f;;
		public float porcentajeMed=0.0f;;
		public float porcentajeFung=0.0f;;
		int totemba=0;
		int totaleco=0;
		int totallab=0; 
		int totalmamo=0;
		int totalodon=0;
		int totaloto=0;
		int totalproc=0;
		int totalradio=0;
		int totalmed=0;
		int totalfung=0;
		int total=0;
		
		public ProgressBarAdministrador(int totemba,int totaleco, int totallab, int totalmamo,int totalodon,int totaloto,int totalproc,int totalradio,int totalmed,int totalfung){
			this.totemba=totemba;
			this.totaleco=totaleco;
			this.totallab=totallab; 
			this.totalmamo=totalmamo;
			this.totalodon=totalodon;
			this.totaloto=totaloto;
			this.totalproc=totalproc;
			this.totalradio=totalradio;
			this.totalmed=totalmed;
			this.totalfung=totalfung;
			
		
		rellenarPorcenajes();
		datosChart();
		
		}
		
	
			
		public void rellenarPorcenajes(){
			total=totemba+totaleco+totallab+totalmamo+totalodon+totaloto+totalproc+totalradio+totalmed+totalfung;
			if(total!=0){
			porcentajeEmba=(totemba*100)/total;
			 porcentajeEco=(totaleco*100)/total;;
			 porcentajeLab=(totallab*100)/total;;
			 porcentajeMamo=(totalmamo*100)/total;;
			 porcentajeOdonto=(totalodon*100)/total;;
			 porcentajeOtorrino=(totaloto*100)/total;;
			 porcentajePquiro=(totalproc*100)/total;;
			 porcentajeRadio=(totalradio*100)/total;;
			 porcentajeMed=(totalmed*100)/total;;
		 porcentajeFung=(totalfung*100)/total;;
			}
		}
		public void datosChart(){
			DefaultCategoryDataset dataset = new DefaultCategoryDataset();
	        
	        dataset.setValue(porcentajeEmba, "Embarazos", "Embarazos");
	        dataset.setValue(porcentajeEco, "Ecografias", "Ecografias");
	        dataset.setValue(porcentajeLab, "Laboratorio", "Laboratorio");
	        dataset.setValue(porcentajeMamo, "Mamografia", "Mamografia");
	        dataset.setValue(porcentajeOdonto, "Odontoestomatologia", "Odontoestomatologia");
	        dataset.setValue(porcentajeOtorrino, "Otorrinolaringologia", "Otorrinolaringologia");
	        dataset.setValue(porcentajePquiro, "Procesos Quirurjicos", "Procesos Quirurjicos");
	        dataset.setValue(porcentajeRadio, "Radiologia", "Radiologia");
	        dataset.setValue(porcentajeMed, "Medicamentos", "Medicamentos");
	        dataset.setValue(porcentajeFung, "Fungibles", "Fungibles");
	         chart = ChartFactory.createBarChart(
	                "Porcentaje de Gasto por tipo Prueba",
	                "Total", 
	                "Porcentaje(%)", 
	                dataset, 
	                PlotOrientation.HORIZONTAL,
	                true, 
	                false, 
	                false
	        );
	         
		        
		        CategoryPlot plot = chart.getCategoryPlot();
		        NumberAxis rangeAxis = (NumberAxis) plot.getRangeAxis();
		        rangeAxis.setRange(0, 100);
		       
		        plot.setBackgroundPaint(Color.white);
		        plot.setRangeGridlinesVisible(true);
				plot.setRangeGridlinePaint(Color.BLACK);
				
		        chart.removeLegend();
	        //customizeChart(chart);

		}
		 private void customizeChart(JFreeChart grafica) {
		  		XYPlot plot = grafica.getXYPlot();
		  		XYLineAndShapeRenderer renderer = new XYLineAndShapeRenderer();
		  		
		  		// Seleccionamos el render de las líneas
		  		plot.setRenderer(renderer);
		  		
		  		// fondo de la grafica
		  		plot.setBackgroundPaint(Color.WHITE);
		  		
		  		
		       
		  		
		  		// Color líneas discontinuas del fondo
		  		//HORIZONTALES
		  		plot.setRangeGridlinesVisible(true);
		  		plot.setRangeGridlinePaint(Color.BLACK);
		  		//VERTICALES
		  		plot.setDomainGridlinesVisible(false);
		  		plot.setDomainGridlinePaint(Color.DARK_GRAY);
		  	}
		public JFreeChart progresBar(){
			return chart;
		}
		
	}

