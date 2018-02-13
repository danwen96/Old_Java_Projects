

import java.awt.image.BufferedImage;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

import ch.makery.MainAppRMI;
import ch.makery.address.rmiinterface.RMIInterface;
import ch.makery.address.rmiserver.ServerOperation;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class GameBoardController extends UnicastRemoteObject implements RMIInterface {
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
	
	private MainAppRMI mainApp;
	
	public void setMainApp(MainAppRMI mainApp) {
        this.mainApp = mainApp;
	}
	
	private GraphicsContext gc;
	private BufferedImage biCircle;
	private BufferedImage biCross;
	private Stage dialogStage;
	private boolean endClicked;
	
	public GameBoardController() throws RemoteException
	{
		 super();
	}
	
	@Override
    public String helloTo(String name) throws RemoteException{

        System.err.println(name + " is trying to contact!");
        return "Server says hello to " + name;

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
	
	public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
    }
	
	
	
	@FXML
	public void initialize() 
	{
		biCircle = new BufferedImage((int)canvas1.getWidth(), (int)canvas1.getHeight(),
				BufferedImage.TYPE_INT_RGB);
				
		
		biCircle = new BufferedImage((int)canvas1.getWidth(), (int)canvas1.getHeight(),
				BufferedImage.TYPE_INT_RGB);
		try {

            Naming.rebind("//localhost/MyServer", new GameBoardController());
            System.err.println("Server ready");

        } catch (Exception e) {

            System.err.println("Server exception: " + e.toString());
            e.printStackTrace();

        }
		
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
