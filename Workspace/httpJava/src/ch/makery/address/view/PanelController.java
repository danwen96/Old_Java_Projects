package ch.makery.address.view;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Calendar;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.databind.ObjectMapper;

import ch.makery.address.model.DrawerTask;
import ch.makery.address.model.JSONDeserialization;
import ch.makery.address.model.ReturnValue;
import javafx.concurrent.WorkerStateEvent;
import javafx.event.EventHandler;
//import ch.makery.address.model.HttpURLConnectionExample;
import javafx.fxml.FXML;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;

public class PanelController {

	private final String USER_AGENT = "Mozilla/5.0";
	
	@FXML
	private DatePicker dataPocz;
	@FXML
	private DatePicker dataKonc;
	@FXML
	private NumberAxis yAxis;
	@FXML
	private LineChart<String,Double> lineChart;
	@FXML
	private Label dzienMinLabel;
	@FXML
	private Label dzienMaxLabel;
	
	
	public PanelController()
	{}
	
	@FXML
	private void initialize()
	{
		//HttpURLConnectionExample http = new HttpURLConnectionExample();

		//System.out.println("Testing 1 - Send Http GET request");
		//http.sendGet();
		///http.sendPost();
		//yAxis.
		yAxis.setAutoRanging(false);
		yAxis.setLowerBound(4);
		yAxis.setUpperBound(4.5);
		yAxis.setTickUnit(0.1);
		//lineChart.axis
		lineChart.setCreateSymbols(false);
	}
	
	@FXML
	private void handleButtonClick() throws IOException
	{
		lineChart.getData().clear();
		DrawerTask task = new DrawerTask(dataPocz,dataKonc,lineChart);
		task.setOnSucceeded(new EventHandler<WorkerStateEvent>(){
			@Override
			public void handle (WorkerStateEvent event){
				ReturnValue returnValue = new ReturnValue();
				returnValue = (ReturnValue) task.getValue();
				dzienMinLabel.setText(returnValue.getMinDate() + " - " + returnValue.getMinRate());
				dzienMaxLabel.setText(returnValue.getMaxDate() + " - " + returnValue.getMaxRate());
			}	
		});
		
		
		
		new Thread(task).start();
		
		/*Calendar calendar;
		LocalDate date = calendar.getTime(); 
		String formattedDate = date.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));*/
		
		
		//URL url = new URL("http://api.fixer.io/2000-01-03"); 
		//URLConnection urlCon = url.openConnection(); 
		/*LocalDate start = dataPocz.getValue();
		LocalDate end = dataKonc.getValue();
		
		LocalDate dzienMin = start;
		LocalDate dzienMax = start;
		*/
		//System.out.println(start.toString());
		
		/*URL url = new URL("http://api.fixer.io/"+start.toString()+"?symbols=PLN"); 
		URLConnection urlCon = url.openConnection(); 
		BufferedReader in = new BufferedReader(new InputStreamReader(urlCon.getInputStream()));
		System.out.println(in.readLine());*/
		
		
		
		
		/*Calendar startDate = Calendar.getInstance(); 
		startDate.set(2010, Calendar.JANUARY, 1); 
		Calendar endDate = Calendar.getInstance(); 
		endDate.set(2011, Calendar.JANUARY, 1);*/
		//LocalDate start = startDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate(); 
		//LocalDate end = endDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate(); 
	/*	for (LocalDate date = start; !date.isEqual(end.plusDays(1)); date = date.plusDays(1)) 
		{ // Twoj kod 
			System.out.println("kolejny dzien");
			URL url = new URL("http://api.fixer.io/"+start.toString()+"?symbols=PLN"); 
			URLConnection urlCon = url.openConnection();
			InputStreamReader in = new InputStreamReader(urlCon.getInputStream()); 
			ObjectMapper objMapper = new ObjectMapper().setSerializationInclusion(Include.NON_NULL);
			//System.out.println("przed deserializacja");
			JSONDeserialization obj = objMapper.readValue(in, JSONDeserialization.class);
			XYChart.Series<String, Double> series = new XYChart.Series<String, Double>();
			series.getData().add(new XYChart.Data<String, Double>(date.toString(),obj.getRates().get("PLN")));
			lineChart.getData().add(series);
		}*/
		

	}
	
	
}
