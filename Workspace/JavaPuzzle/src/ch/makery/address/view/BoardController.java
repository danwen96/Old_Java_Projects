package ch.makery.address.view;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import java.util.Collections;
import java.util.concurrent.TimeUnit;
import java.util.prefs.Preferences;

import javax.imageio.ImageIO;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import ch.makery.address.MainApp;
import ch.makery.address.model.Tile;
import ch.makery.address.model.TimeWrapper;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.ParallelTransition;
import javafx.animation.PathTransition;
import javafx.animation.Timeline;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.LineTo;
import javafx.scene.shape.MoveTo;
import javafx.scene.shape.Path;
import javafx.stage.FileChooser;
import javafx.util.Duration;
import java.lang.Math;

public class BoardController {
	@FXML
	private Canvas canvas1;
	@FXML
	private Canvas canvas2;
	@FXML
	private Canvas canvas3;
	@FXML
	private Canvas canvas4;
	@FXML
	private Canvas canvas5;
	@FXML
	private Canvas canvas6;
	@FXML
	private Canvas canvas7;
	@FXML
	private Canvas canvas8;
	@FXML
	private Canvas canvas9;
	@FXML
	private Label label;
	@FXML
	private Label record;
	
	
	private ObservableList<Canvas> canvases = FXCollections.observableArrayList();
	double height = 200;
	double width = 330;
	
	private ObservableList<Tile> tiles = FXCollections.observableArrayList();
	private int tmp1 = -1;
	private int tmp2 = -1;
	private int nPieces = 9;
	GraphicsContext gc = null;
	private long recordTime = 0;
	private boolean hasStarted = false;
	
	
	Timeline timeline = new Timeline(new KeyFrame(
			Duration.millis(100),
			new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent ae) {
			updateTime();				
			}
			}));
	
	private MainApp mainApp;
	
	public BoardController(){
	}
	
	@FXML
	private void initialize()
	{
		File file = getTimeFilePath();
		if(file != null)
		{
			loadTimeDataFromFile(file);	
		}
		canvases.add(canvas1);
		canvases.add(canvas2);
		canvases.add(canvas3);
		canvases.add(canvas4);
		canvases.add(canvas5);
		canvases.add(canvas6);
		canvases.add(canvas7);
		canvases.add(canvas8);
		canvases.add(canvas9);
		label.setText("00:00:00:000");
		
		
		try {
			BufferedImage img = ImageIO.read(new File("E:\\Workspace\\JavaPuzzle\\bin\\obraz.jpg"));
			BufferedImage part = null;
			img = resizeImage(img,(int)(3*width),(int)(3*height));
			
			for(int i =0;i<9;i++)
			{
			part = img.getSubimage((int)((i%3)*width),(int)((i/3)*height), (int)width,(int) height);
			tiles.add(new Tile(part,i));
			//tile.setFill(new ImagePattern(SwingFXUtils.toFXImage(tile.getPart(), null)));
			
			}
			drawImage();
			
		} catch (IOException e) {
			 //TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	
	
	@FXML
	public void handleStartBtnAction()
	{
		hasStarted = true;
		Collections.shuffle(tiles);
		drawImage();
		time = 0;
		timeline.setCycleCount(Animation.INDEFINITE); 
		timeline.play();
		
	
	}
	
	@FXML
	public void handleCanvasClick(final MouseEvent event)
	{
		Object obj = event.getSource();
		  if ( obj instanceof Canvas )
	        {
	            //System.out.println( ((Canvas) obj).getUserData() );
			  	if(tmp1 == -1)
			  	{
			  		tmp1 = canvases.indexOf(obj);
			  		gc = canvases.get(tmp1).getGraphicsContext2D();
			  		gc.setFill(Color.AZURE);
			  		gc.fillRect(0, 0, 5, height);
			  		gc.fillRect(width - 5, 0, 5, height);
			  		gc.fillRect(0, 0, width, 5);
			  		gc.fillRect(0, height-5, width, 5);
			  		
			  	}
			  	else if(tmp2 == -1)
			  	{
			  		int tmp = canvases.indexOf(obj);
			  		
			  		if(tmp1 != tmp){
			  		if(((tmp % 3 == tmp1%3)&&((Math.abs(tmp/3 - tmp1/3))<2)) || ((tmp/3 == tmp1/3)&&((Math.abs(tmp%3 - tmp1%3))<2))){
			  		
			  		tmp2 = canvases.indexOf(obj);
			  		
			  		double fx = canvases.get(tmp1).getLayoutX();
			  		double fy = canvases.get(tmp1).getLayoutY();
			  		double sx = canvases.get(tmp2).getLayoutX();
			  		double sy = canvases.get(tmp2).getLayoutY();
			  		  		
			  		PathTransition ptr = getPathTransition(canvases.get(tmp1),canvases.get(tmp2));
			  		PathTransition ptr2 = getPathTransition(canvases.get(tmp2),canvases.get(tmp1));
			  		ParallelTransition pt = new ParallelTransition(ptr, ptr2);
			  		pt.play();
			  			  		
			  		pt.setOnFinished(new EventHandler<ActionEvent>() {
			  			@Override
			  			public void handle(ActionEvent event) {
			  				canvases.get(tmp1).setTranslateX(0);
			  				canvases.get(tmp1).setTranslateY(0);
			  				canvases.get(tmp2).setTranslateX(0);
			  				canvases.get(tmp2).setTranslateY(0);
			  				
			  				canvases.get(tmp1).setLayoutX(sx);
			  				canvases.get(tmp1).setLayoutY(sy);
			  				canvases.get(tmp2).setLayoutX(fx);
			  				canvases.get(tmp2).setLayoutY(fy);
			  				
			  				Collections.swap(tiles, tmp1, tmp2);
					  		drawImage();
					  		tmp1 = -1;
					  		tmp2 = -1;
					  		
					  		if(isFinished())
					  		{
					  			if(!hasStarted){return;}
					  			 timeline.stop();
					  			 Alert alert = new Alert(AlertType.INFORMATION);
					             alert.setTitle("Wygrales!");
					             alert.setHeaderText("Gratulacje");
					             alert.setContentText("Twoj czas to " + label.getText());
					             alert.show();

					             
					             if(time < recordTime)
					             {
					            	 recordTime = time - 100;
					            	 record.setText(timeString(recordTime));
					            	 File timeFile = getTimeFilePath();
					            	 if (timeFile != null) {
					            		 saveTimeDataToFile(timeFile);
					            	 } else {
					            		 handleSaveAs();
					            	 }
					             }
					  		}
			  			}
			  			});
			  		}
			  		else
			  		{
			  			System.out.println("niesasiudujace pole");
			  		}
			  		}
			  		else
			  		{
			  			System.out.println("wybrales ten sam kawalek;");
			  		}
			  	}	
	        }
		  else
		  {
			  System.out.println("obiekt nie jest Canvas");
		  }
	}
	
	
	public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;
	}
	
	public void drawImage()
	{
		for(int i =0;i<9;i++)
		{
		gc = canvases.get(i).getGraphicsContext2D();
		gc.drawImage(SwingFXUtils.toFXImage(tiles.get(i).getPart(), null), 0,0 );
		}
	}
	
	public boolean isFinished()
	{
		boolean check = true;
		for(int i=0;i<nPieces;i++)
		{
			if(tiles.get(i).getNum() != i)
			{
				check = false;
			}
		}
		return check;
	}
	
	private PathTransition getPathTransition(Canvas first, Canvas second) {
		PathTransition ptr = new PathTransition();
		Path path = new Path();
		path.getElements().clear();
		path.getElements().add(new MoveToAbs(first));
		path.getElements().add(new LineToAbs(first, second.getLayoutX(),
		second.getLayoutY()));
		ptr.setPath(path);
		ptr.setNode(first);
		return ptr;
		}
	
	public static class MoveToAbs extends MoveTo {
		public MoveToAbs(Node node) {
		super(node.getLayoutBounds().getWidth() / 2,
		node.getLayoutBounds().getHeight() / 2);
		}
		}
		public static class LineToAbs extends LineTo {
		public LineToAbs(Node node, double x, double y) {
		super(x - node.getLayoutX() + node.getLayoutBounds().getWidth() / 2, y -
		node.getLayoutY() + node.getLayoutBounds().getHeight() / 2);
		}
		}
		
		private long time = 0;
		private void updateTime() 
		{ 
			
			long second = TimeUnit.MILLISECONDS.toSeconds(time);
			long minute = TimeUnit.MILLISECONDS.toMinutes(time); 
			long hour = TimeUnit.MILLISECONDS.toHours(time); 
			long millis = time - TimeUnit.SECONDS.toMillis(second); 
			String timeString = String.format("%02d:%02d:%02d:%d", hour, minute, second, millis); 
			label.setText(timeString);
			time += 100; 
		}
		
		private static BufferedImage resizeImage(BufferedImage originalImage, int targetWidth, int targetheight)
		{ 
			BufferedImage resizedImage = new BufferedImage(targetWidth, targetheight, originalImage.getType());
			Graphics2D g = resizedImage.createGraphics();
			g.drawImage(originalImage, 0, 0, targetWidth, targetheight, null);
			g.dispose(); 
			return resizedImage; 
		}
		
		/**
		 * Returns the person file preference, i.e. the file that was last opened.
		 * The preference is read from the OS specific registry. If no such
		 * preference can be found, null is returned.
		 * 
		 * @return
		 */
		public File getTimeFilePath() {
		    Preferences prefs = Preferences.userNodeForPackage(MainApp.class);
		    String filePath = prefs.get("filePath", null);
		    if (filePath != null) {
		        return new File(filePath);
		    } else {
		        return null;
		    }
		}

		/**
		 * Sets the file path of the currently loaded file. The path is persisted in
		 * the OS specific registry.
		 * 
		 * @param file the file or null to remove the path
		 */
		public void setTimeFilePath(File file) {
		    Preferences prefs = Preferences.userNodeForPackage(MainApp.class);
		    if (file != null) {
		        prefs.put("filePath", file.getPath());
	        
		    } else {
		        prefs.remove("filePath");
	     
		    }
		}
		
		
		
		/**
		 * Loads person data from the specified file. The current person data will
		 * be replaced.
		 * 
		 * @param file
		 */
		public void loadTimeDataFromFile(File file) {
		    try {
		        JAXBContext context = JAXBContext
		                .newInstance(TimeWrapper.class);
		        Unmarshaller um = context.createUnmarshaller();

		        // Reading XML from the file and unmarshalling.
		        TimeWrapper wrapper = (TimeWrapper) um.unmarshal(file);

		        
		        recordTime = wrapper.getMiliseconds();
		        record.setText(timeString(recordTime));

		        // Save the file path to the registry.
		        setTimeFilePath(file);

		    } catch (Exception e) { // catches ANY exception
		    	recordTime = 999999999;
		    	record.setText("Nie grano");
		        Alert alert = new Alert(AlertType.ERROR);
		        alert.setTitle("Error");
		        alert.setHeaderText("Could not load data");
		        alert.setContentText("Could not load data from file:\n" + file.getPath());

		        alert.showAndWait();
		    }
		}

		/**
		 * Saves the current person data to the specified file.
		 * 
		 * @param file
		 */
		public void saveTimeDataToFile(File file) {
		    try {
		        JAXBContext context = JAXBContext
		                .newInstance(TimeWrapper.class);
		        Marshaller m = context.createMarshaller();
		        m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

		        // Wrapping our person data.
		        TimeWrapper wrapper = new TimeWrapper();
		        wrapper.setMiliseconds(recordTime);

		        // Marshalling and saving XML to the file.
		        m.marshal(wrapper, file);

		        // Save the file path to the registry.
		        setTimeFilePath(file);
		    } catch (Exception e) { // catches ANY exception
		        Alert alert = new Alert(AlertType.ERROR);
		        alert.setTitle("Error");
		        alert.setHeaderText("Could not save data");
		        alert.setContentText("Could not save data to file:\n" + file.getPath());

		        alert.showAndWait();
		    }
		}
		
		
	    private void handleSaveAs() {
	        FileChooser fileChooser = new FileChooser();

	        // Set extension filter
	        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter(
	                "XML files (*.xml)", "*.xml");
	        fileChooser.getExtensionFilters().add(extFilter);

	        // Show save file dialog
	        File file = fileChooser.showSaveDialog(mainApp.getPrimaryStage());

	        if (file != null) {
	            // Make sure it has the correct extension
	            if (!file.getPath().endsWith(".xml")) {
	                file = new File(file.getPath() + ".xml");
	            }
	            saveTimeDataToFile(file);
	        }
	    }
	    
	    public String timeString(long time)
	    {
	    	long second = TimeUnit.MILLISECONDS.toSeconds(time);
			long minute = TimeUnit.MILLISECONDS.toMinutes(time); 
			long hour = TimeUnit.MILLISECONDS.toHours(time); 
			long millis = time - TimeUnit.SECONDS.toMillis(second); 
			String timeString = String.format("%02d:%02d:%02d:%d", hour, minute, second, millis); 
			return timeString;
	    }
}
