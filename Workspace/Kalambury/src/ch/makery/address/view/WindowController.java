package ch.makery.address.view;


import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.Socket;
import ch.makery.address.MainApp;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import ch.makery.address.model.*;



public class WindowController {
	@FXML
	private Canvas canvas;
	@FXML
	private TextField textfield;
	@FXML
	private Label label;
	
	private MainApp mainApp;
	
	private String text;
	private Line line = new Line();
	private GraphicsContext gc ;
	private Socket s;
	OutputStream os;
	ObjectOutputStream oos;
	//private DrawerTask task;
	
	public WindowController(){}
	
	@FXML
	private void initialize()
	{
		gc = canvas.getGraphicsContext2D();
		gc.setLineWidth(5);
		gc.setFill(Color.AQUA);
		gc.fillRect(0, 0,gc.getCanvas().getWidth() ,gc.getCanvas().getHeight()); 
		
		
		try{
			s = new Socket("localhost",2002);
			os = s.getOutputStream();
			oos = new ObjectOutputStream(os);
			DrawerTask task = new DrawerTask(gc,s,label);
			
			new Thread(task).start();
		
			}catch(Exception e){System.out.println(e);}
		
		
	}
	
	@FXML
	private void handleMouseClick(final MouseEvent event)
	{
		
		
		if(line.getX1() == -1)
		{
			line.setX1(event.getX());
			line.setY1(event.getY());
			System.out.println(line.getX1());
			System.out.println(line.getY1());
		}
		else if(line.getX2() == -1)
		{
			line.setX2(event.getX());
			line.setY2(event.getY());
			line.setNazwa(null);
			System.out.println(line.getX2());
			System.out.println(line.getY2());
			try {
				oos.reset();
				oos.writeObject(line);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
			
			gc.strokeLine(line.getX1(),line.getY1(),line.getX2(), line.getY2());
			System.out.println("narysowano");
			
			line.setX1(-1.0);
			line.setX2(-1.0);
			line.setY1(-1.0);
			line.setY2(-1.0);
		}
		
	}
	
	@FXML
	private void handleButtonClick()
	{
		line.setNazwa(textfield.getText());
		try {
			oos.reset();
			oos.writeObject(line);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		label.setText(line.getNazwa());
	}
	
	@FXML
	private void handleEndButtonClick()
	{
		line.setTheEnd(true);
		try {
			oos.reset();
			oos.writeObject(line);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		gc.setFill(Color.AQUA);
		gc.fillRect(0, 0,gc.getCanvas().getWidth() ,gc.getCanvas().getHeight());
		line = new Line();
	}
	
	
	
	public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;
	}
}
