package ch.makery.address.view;



//import java.awt.Color;
import java.awt.image.BufferedImage;

import ch.makery.address.MainApp;
import javafx.embed.swing.SwingFXUtils;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class DrawingFunctionController {

	@FXML
	private Canvas canvas;
	@FXML
	private void handleRunBtnAction(){
	GraphicsContext gc = canvas.getGraphicsContext2D();
	//drawShapes(gc);
	gc.setFill(Color.AQUA);
	gc.fillOval(0,0,50,50);
	
	/*for(int i=0;i<1000;i++)
	{
	BufferedImage bi = new BufferedImage(600, 400, BufferedImage.TYPE_INT_RGB);
	bi.setRGB(i, i, Color.YELLOW.getRGB());
	gc.drawImage(SwingFXUtils.toFXImage(bi, null), 0,0 );
	}*/
	
	}
	
	private MainApp mainApp;
	public DrawingFunctionController(){}
	
	private void drawShapes(GraphicsContext gc) {
	gc.setFill(Color.BLUEVIOLET);
	gc.fillRect(gc.getCanvas().getLayoutX(),
	gc.getCanvas().getLayoutY(),
	gc.getCanvas().getWidth(),
	gc.getCanvas().getHeight());
	}
	
	@FXML
	private void initialize(){
		
		
	}
	
	public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;
    }
	
}
