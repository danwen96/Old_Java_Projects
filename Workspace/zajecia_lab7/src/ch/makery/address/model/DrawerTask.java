package ch.makery.address.model;



import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.net.Socket;
import javafx.application.Platform;
import javafx.concurrent.Task;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;
import javafx.scene.control.Alert.AlertType;



public class DrawerTask extends Task { 
	
	GraphicsContext gc;
	private Socket s;
	InputStream is;
	ObjectInputStream ois;
	Line line = new Line();
	String text;
	Label label;
	
	public DrawerTask(GraphicsContext gc,Socket s,Label label) {
		//tmp1 =0;
		//tmp2 =0;
		this.gc = gc;
		this.s = s;
		this.label = label;
		gc.setLineWidth(5);
		try {
			is = s.getInputStream();
			ois = new ObjectInputStream(is);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
		
	}

	public DrawerTask() {
		// TODO Auto-generated constructor stub
	}

	@Override 
	protected Object call() throws Exception { 
	 
			while(true)
			{	
				try {
					System.out.println("czekam na object");
                    Line to = (Line) ois.readObject();
                    if(to.isTheEnd()){
                    Platform.runLater(new Runnable() {
                        @Override public void run() {
                        	Alert alert = new Alert(AlertType.INFORMATION);
                            alert.setTitle("Koniec!");
                            alert.setHeaderText("Rysunek odgadniety");
                            alert.setContentText("Rysunek przedstawial " + label.getText());
                            alert.show();
                            
                            line = new Line();
                        }
                        
                    });
                    gc.setFill(Color.AQUA);
            		gc.fillRect(0, 0,gc.getCanvas().getWidth() ,gc.getCanvas().getHeight());     			 
                    }
                    else if(to.getNazwa() == null)
                    {
                    	gc.strokeLine(to.getX1(), to.getY1(), to.getX2(), to.getY2());
                    }
                    else
                    {
                    	text = to.getNazwa();
                    	System.out.println(to.getNazwa());
                    	Platform.runLater(new Runnable() {
                            @Override public void run() {
                               label.setText(text);
                            }
                        });
                    	
                    }
                   
                } catch (Exception e) {
                    System.out.println(e);
                    break;
                }
				if(isCancelled()) break;
				//System.out.println("jest w task");
			}
			  
			return 0;
		}
	
	
	}
