

import java.awt.image.BufferedImage;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

import javax.swing.JOptionPane;

import ch.makery.MainAppRMI;
import ch.makery.address.rmiinterface.RMIInterface;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class GameBoardClientController {
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
	
	private GraphicsContext gc;
	private BufferedImage biCircle;
	private BufferedImage biCross;
	private Stage dialogStage;
	private Boolean endClicked = false;
	
	private static RMIInterface look_up;
	
	public GameBoardClientController()
	{}
	
	private MainAppRMI mainApp;
	
	public void setMainApp(MainAppRMI mainApp) {
        this.mainApp = mainApp;
	}
	
	@FXML
	private void initialize() throws MalformedURLException, RemoteException, NotBoundException
	{
		biCircle = new BufferedImage((int)canvas1.getWidth(), (int)canvas1.getHeight(),
				BufferedImage.TYPE_INT_RGB);
				
		
		biCircle = new BufferedImage((int)canvas1.getWidth(), (int)canvas1.getHeight(),
				BufferedImage.TYPE_INT_RGB);
		
	 

			look_up = (RMIInterface) Naming.lookup("//localhost/MyServer");
			String txt = JOptionPane.showInputDialog("What is your name?");

			String response = look_up.helloTo(txt);
			JOptionPane.showMessageDialog(null, response);
		
		
	}
	
	public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
    }
	
	@FXML
	public void handleCanvasClick(final MouseEvent event)
	{
		Object obj = event.getSource();
		  if ( obj instanceof Canvas )
		  {
			drawCircle((Canvas)obj);  
		  }
	}
	
	@FXML
	private void handleEndClik()
	{
		endClicked = true;
		dialogStage.close();
	}
	
	public boolean isEndGameClicked() {
        return endClicked;
    }
	
	private void drawCircle(Canvas canvas)
	{
		GraphicsContext gc = canvas.getGraphicsContext2D();
		gc.fillOval(canvas.getWidth()/4, canvas.getHeight()/4, canvas.getHeight()/2, canvas.getHeight()/2);
	}
	
	private void drawCross(Canvas canvas)
	{
		GraphicsContext gc = canvas.getGraphicsContext2D();
		gc.setLineWidth(5);
		gc.strokeLine(10.0, 10.0, canvas.getWidth() - 10.0, canvas.getHeight() - 10.0);
		gc.strokeLine(10.0, canvas.getHeight() - 10.0,canvas.getWidth() - 10.0, 10.0);
	}
}
