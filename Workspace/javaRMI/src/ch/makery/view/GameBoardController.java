package ch.makery.view;



import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
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
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class GameBoardController extends UnicastRemoteObject implements RMIInterface, ClientCallback
{
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
	
	private static List<Integer> circles = new ArrayList();
	private static List<Integer> crosses = new ArrayList();
	
	private String serverName = "TestServer";
	
	private ObservableList<GraphicsContext> canvases = FXCollections.observableArrayList();
	
	private ClientCallbackListener clientCallbackListener;
	
	private static double canvasesWidth = 263;
	private static double canvasesHeight = 200;
	
	private MainAppRMI mainApp;

	private static int whoseTurn = 1;
	
	public void setMainApp(MainAppRMI mainApp) {
        this.mainApp = mainApp;
	}
	
	Registry registry;
	public boolean endClicked = false;
	//private IntegerProperty indexToDraw;
	public static int indexToDraw;
	
	/*public GameBoardController() throws RemoteException
	{
		super();
		//ServerOperation.main(null);
		//Thread.sleep(4000);
		
	}*/
	
	public GameBoardController(String data) throws RemoteException {
		this.serverName = data;
		System.out.println("server name ustawiono jako: " + serverName);
		
    }
	
	public GameBoardController() throws RemoteException {
		System.out.println("wywolano konstruktor");
    }
	
	public void setServerName(String data) {
	       this.serverName = data;
	       //handle data
	       System.out.println("server name ustawiono jako: " + data);
	       //System.out.println("ustawiono adres jako: " + data);
	}
	
	@Override
    public int helloTo(int index) throws RemoteException{

        System.err.println(index + " is trying to contact!");
        indexToDraw = index;
        drawCross(index);
        whoseTurn = 1;
        crosses.add(index);
        
        if(checkTheBoardCrossesCombination())
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
	
	@FXML
	private void handleEndClik()
	{
		try {
			registry = LocateRegistry.createRegistry(1099);
			registry.rebind(serverName, this);
			System.out.println("Server dziala!: " + serverName);
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public boolean isEndGameClicked() {
        return endClicked;
    }
	
	@FXML
	public void initialize() throws InterruptedException 
	{
		canvases.add(canvas1.getGraphicsContext2D());
		canvases.add(canvas2.getGraphicsContext2D());
		canvases.add(canvas3.getGraphicsContext2D());
		canvases.add(canvas4.getGraphicsContext2D());
		canvases.add(canvas5.getGraphicsContext2D());
		canvases.add(canvas6.getGraphicsContext2D());
		canvases.add(canvas7.getGraphicsContext2D());
		canvases.add(canvas8.getGraphicsContext2D());
		canvases.add(canvas9.getGraphicsContext2D());
		
		canvasesWidth = canvas1.getWidth();
		canvasesHeight = canvas1.getHeight();
	
		
		
        
	
	}
	
	@FXML
	public void handleCanvasClick(final MouseEvent event)
	{
		Object obj = event.getSource();
		  if ( obj instanceof Canvas )
		  {
			if(whoseTurn == 1)
			{
			int check = sendToClient(canvases.indexOf(((Canvas) obj).getGraphicsContext2D()));
			if(check != 1) System.out.println("something gone wrong with sending");
			else{
			drawCircle(canvases.indexOf(((Canvas) obj).getGraphicsContext2D()));
			circles.add(canvases.indexOf(((Canvas) obj).getGraphicsContext2D()));
			
			whoseTurn = 0;
			}
			if(checkTheBoardCirclesCombination())
	        {
				JOptionPane.showMessageDialog(null, "Wygra³eœ!");
	        }
			if((circles.size() + crosses.size()) >= 9) 
			{
				JOptionPane.showMessageDialog(null, "Remis!");
				return;
			}
			}
			else System.out.println("Poczekaj na swoja ture!");
		  }
	}
	
	private void drawCircle(int index)
	{
		canvases.get(index).fillOval(canvasesWidth/4, canvasesHeight/4, canvasesHeight/2, canvasesHeight/2);
	}
	
	private void drawCross(int index)
	{
		GraphicsContext gc = canvases.get(index);
		gc.setLineWidth(5);
		gc.strokeLine(10.0, 10.0, canvasesWidth - 10.0, canvasesHeight - 10.0);
		gc.strokeLine(10.0, canvasesHeight - 10.0,canvasesWidth - 10.0, 10.0);
	}
    
    @Override
    public void setPrintOnClientCallback(ClientCallbackListener clientCallbackListener) throws RemoteException {
        // To jest metoda uruchamiana z klienta, ktora pozwala na przeslanie metody klienta, ktora bedzie mogl uzyc serwer
        System.out.println ("Adding listener -" + clientCallbackListener);
        this.clientCallbackListener = clientCallbackListener;
    }
    
    public int sendToClient(int index){
        // Uruchomienie metody z klienta
        if(clientCallbackListener != null) {
            try {
                int check = clientCallbackListener.sendToClient(index);
                return 1;
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
        return 0;
    }

    @Override
    public ClientCallbackListener getPrintOnClientCallback() throws RemoteException {
        return clientCallbackListener;
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
