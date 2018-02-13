package ch.makery.view;

import java.awt.image.BufferedImage;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import ch.makery.MainAppRMI;
import ch.makery.address.rmiinterface.ClientCallback;
import ch.makery.address.rmiinterface.ClientCallbackListener;
import ch.makery.address.rmiinterface.RMIInterface;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class GameBoardClientController extends UnicastRemoteObject implements ClientCallbackListener {
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
	
	
	private ObservableList<Canvas> canvases = FXCollections.observableArrayList();
	
	private static List<Integer> circles = new ArrayList();
	private static List<Integer> crosses = new ArrayList();

	private Stage dialogStage;
	private Boolean endClicked = false;
	private static int whoseTurn = 0;

	Remote remoteService = null;
	private String serverAddr = "rmi://localhost:1099/TestServer";
	
	public GameBoardClientController() throws RemoteException {
        super();
    }
	
	public GameBoardClientController(String data) throws RemoteException {
        super();
        this.serverAddr = data;
        System.out.println("adres klienta ustawiony jako: " + serverAddr);
    }
	
	private MainAppRMI mainApp;
	
	public void setMainApp(MainAppRMI mainApp) {
        this.mainApp = mainApp;
	}
	
	public void setServerName(String data) {
	       this.serverAddr = data;
	       //handle data
	       System.out.println("ustawiono adres do polaczenia jako: " + data);
	}
	
	@FXML
	private void initialize() throws MalformedURLException, RemoteException, NotBoundException
	{
		canvases.add(canvas1);
		canvases.add(canvas2);
		canvases.add(canvas3);
		canvases.add(canvas4);
		canvases.add(canvas5);
		canvases.add(canvas6);
		canvases.add(canvas7);
		canvases.add(canvas8);
		canvases.add(canvas9);
		
		
		
		//ClientOperation.main(null);
		
		
		//look_up = (RMIInterface) Naming.lookup("//localhost/MyServer");
		//String txt = JOptionPane.showInputDialog("What is your name?");

		//int liczba = 5;
		//JOptionPane.showMessageDialog(null, response);
	}
	
	public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
    }
	
	@FXML
	public void handleCanvasClick(final MouseEvent event) throws RemoteException
	{
		Object obj = event.getSource();
		  if ( obj instanceof Canvas )
		  {
			
			 try {
				 if(whoseTurn == 1)
				 {
		            RMIInterface server = (RMIInterface)remoteService;
		            //server.sendText(text);
		            int tempor = server.helloTo(canvases.indexOf(obj));
		            if(tempor != 1) System.out.println("Something gone wrong with sending!");
		            else{
		            whoseTurn = 0;
		            drawCross((Canvas)obj);
		            crosses.add(canvases.indexOf(obj));
		            }
		            
		            if(checkTheBoardCrossesCombination())
		            {
		            	JOptionPane.showMessageDialog(null, "Wygra³eœ!");
		            }
		            if((circles.size() + crosses.size()) >= 9) 
					{
						JOptionPane.showMessageDialog(null, "Remis!");
						return;
					}
				 }
				 else System.out.println("Wait for your turn!");
		        } catch (RemoteException e) {
		            e.printStackTrace();
		        }
			//int response = look_up.helloTo(canvases.indexOf(obj));
			//System.out.println(canvases.indexOf(obj));
			//System.out.println(response);
			//look_up.printVal();
		  }
	}
	
	@FXML
	private void handleEndClik() throws RemoteException
	{
		try {
			System.out.println("Lacze sie z adresem: " + serverAddr);
            remoteService = Naming.lookup ( serverAddr );

            // Klient przesyla do serwera wlasna metode (callback)
            // aby ten mogl jej uzyc
            ClientCallback callback = (ClientCallback)remoteService;
            callback.setPrintOnClientCallback(this);
        } catch (NotBoundException | MalformedURLException e) {
            e.printStackTrace();
        }
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

	@Override
	public int sendToClient(int index) throws RemoteException {
	    System.out.println("server wywolal metode w kliencie");
	    drawCircle(canvases.get(index));
	    whoseTurn = 1;
	    circles.add(index);   
        if(checkTheBoardCirclesCombination())
        {
        	JOptionPane.showMessageDialog(null, "Przegra³eœ!");
        }
        if((circles.size() + crosses.size()) >= 9) 
		{
			JOptionPane.showMessageDialog(null, "Remis!");
			return 1;
		}
	    
	    return 1;
	}
	
	private boolean checkTheBoardCrossesCombination()
    {	
    		if(crosses.contains(0))
    		{
    			if(crosses.contains(1) && crosses.contains(2))
    				return true;
    			if(crosses.contains(4) && crosses.contains(8))
    				return true;
    			if(crosses.contains(3)&& crosses.contains(6))
    				return true;
    		}
    		
    		if(crosses.contains(1))
    		{
    			if(crosses.contains(4)&& crosses.contains(7))
    				return true;	
    		}
    		if(crosses.contains(2))
    		{
    			if(crosses.contains(5)&& crosses.contains(8))
    				return true;
    			if(crosses.contains(4) && crosses.contains(6))
    				return true;
    		}
    		if(crosses.contains(3))
    		{
    			if(crosses.contains(4) && crosses.contains(5))
    				return true;
    			
    		}
    		if(crosses.contains(6))
    		{
    			if(crosses.contains(7)&&crosses.contains(8))
    				return true;
    		}
    	
    	return false;
    	
    }

    private boolean checkTheBoardCirclesCombination()
    {	
    		if(circles.contains(0))
    		{
    			if(circles.contains(1) && circles.contains(2))
    				return true;
    			if(circles.contains(4) && circles.contains(8))
    				return true;
    			if(circles.contains(3)&& circles.contains(6))
    				return true;		
    		}
    		
    		if(circles.contains(1))
    		{
    			if(circles.contains(4)&& circles.contains(7))
    				return true;	
    		}
    		if(circles.contains(2))
    		{
    			if(circles.contains(5)&& circles.contains(8))
    				return true;
    			if(circles.contains(4) && circles.contains(6))
    				return true;
    		}
    		if(circles.contains(3))
    		{
    			if(circles.contains(4) && circles.contains(5))
    				return true;
    			
    		}
    		if(circles.contains(6))
    		{
    			if(circles.contains(7)&&circles.contains(8))
    				return true;
    		}
    	
    	return false;
    	
    }
}
