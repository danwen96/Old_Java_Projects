package ch.makery.address.model;

import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.databind.ObjectMapper;

import javafx.application.Platform;
import javafx.concurrent.Task;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.DatePicker;



public class DrawerTask extends Task { 
	
	DatePicker dataPocz;
	DatePicker dataKonc;
	LineChart<String,Double> linechart;
	XYChart.Series<String, Double> series;
	ReturnValue returnValue;
	//LocalDate date
	
	public DrawerTask(DatePicker dataPocz,DatePicker dataKonc,LineChart<String,Double> linechart) 
	{
		this.dataPocz = dataPocz;
		this.dataKonc = dataKonc;
		this.linechart = linechart;
		series = new XYChart.Series<String, Double>();
		returnValue = new ReturnValue();
	}
	public DrawerTask() {
		// TODO Auto-generated constructor stub
	}

	@Override 
	protected Object call() throws Exception { 
	 
		LocalDate start = dataPocz.getValue();
		LocalDate end = dataKonc.getValue();
		
		LocalDate dzienMin = start;
		LocalDate dzienMax = start;
		
		double minRate;
		double maxRate;
		
		URL url1 = new URL("http://api.fixer.io/"+start.toString()+"?symbols=PLN"); 
		//System.out.println("http://api.fixer.io/"+date.toString()+"?symbols=PLN");
		URLConnection urlCon1 = url1.openConnection();
		InputStreamReader in1 = new InputStreamReader(urlCon1.getInputStream()); 
		ObjectMapper objMapper1 = new ObjectMapper().setSerializationInclusion(Include.NON_NULL);
		//System.out.println("przed deserializacja");
		JSONDeserialization obj1 = objMapper1.readValue(in1, JSONDeserialization.class);
		//Date tmp = Date.from(date.atStartOfDay(ZoneId.systemDefault()).toInstant());
		//String formattedDate = date.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
		System.out.println(obj1.getRates().get("PLN"));
		
		minRate = obj1.getRates().get("PLN");
		maxRate = obj1.getRates().get("PLN");
		
		//linechart.setCreateSymbols(false);
		
		//System.out.println(start.toString());
		
		Platform.runLater(new Runnable() {
            @Override public void run() {
            	
             	linechart.getData().add(series);
             	series.getData().add(new XYChart.Data<String, Double>(start.toString(),obj1.getRates().get("PLN")));
             } 
         });
		
		for (LocalDate date = start.plusDays(1); !date.isEqual(end.plusDays(1)); date = date.plusDays(1)) 
		{ // Twoj kod 
			Thread.sleep(200);
			URL url = new URL("http://api.fixer.io/"+date.toString()+"?symbols=PLN");
			//System.out.println("http://api.fixer.io/"+date.toString()+"?symbols=PLN");
			URLConnection urlCon = url.openConnection();
			InputStreamReader in = new InputStreamReader(urlCon.getInputStream()); 
			ObjectMapper objMapper = new ObjectMapper().setSerializationInclusion(Include.NON_NULL);
			//System.out.println("przed deserializacja");
			JSONDeserialization obj = objMapper.readValue(in, JSONDeserialization.class);
			//Date tmp = Date.from(date.atStartOfDay(ZoneId.systemDefault()).toInstant());
			//String formattedDate = date.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
			//System.out.println(obj.getRates().get("PLN"));
			final LocalDate tmp = date;
			Platform.runLater(new Runnable() {
	            @Override public void run() {
	            	series.getData().add(new XYChart.Data<String, Double>(tmp.toString(),obj.getRates().get("PLN")));
	            }
	         });
			if(minRate>obj.getRates().get("PLN"))
			{
				minRate = obj.getRates().get("PLN");
				dzienMin = date;
			}
			if(maxRate<obj.getRates().get("PLN"))
			{
				maxRate = obj.getRates().get("PLN");
				dzienMax = date;
			}
			//series.getData().remove(0);
		}
	
		//dzienMinLabel.setText(dzienMin.toString() + " - " + minRate);
		//dzienMaxLabel.setText(dzienMax.toString() + " - " + maxRate);
		
		returnValue.setMinDate(dzienMin.toString());
		returnValue.setMaxDate(dzienMax.toString());
		returnValue.setMinRate(minRate);
		returnValue.setMaxRate(maxRate);
		
		return returnValue;
	}
	
	
	}
