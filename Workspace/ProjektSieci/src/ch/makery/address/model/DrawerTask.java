package ch.makery.address.model;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.net.Socket;

import javax.swing.JOptionPane;

import ch.makery.address.view.GameBoardController.LineToAbs;
import ch.makery.address.view.GameBoardController.MoveToAbs;
import javafx.animation.PathTransition;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.concurrent.Task;
import javafx.scene.Node;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.shape.LineTo;
import javafx.scene.shape.MoveTo;
import javafx.scene.shape.Path;
import javafx.scene.control.Alert.AlertType;



public class DrawerTask extends Task { 
	
	private Socket s;
	InputStream is;
	ObjectInputStream ois;
	Move move = new Move();
	String text;
	ImageView allyHero;
	ImageView enemyHero;
	Pack receivedPack;
	private ObservableList<CardRepresantation> lista = FXCollections.observableArrayList();
	Label ourHeroHpLabel;
	Label enemyHeroHpLabel;
	int id1;
	int id2;
	
	
	
	public DrawerTask(InputStream is,ObjectInputStream ois,ImageView allyHero,ImageView enemyHero,ObservableList<CardRepresantation> lista,Label ourHeroHpLabel,Label enemyHeroHpLabel) {
		//tmp1 =0;
		//tmp2 =0;
		this.is = is;
		this.ois = ois;
		/*try {
			is = s.getInputStream();
			ois = new ObjectInputStream(is);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
        this.allyHero = allyHero;
        this.enemyHero = enemyHero;
        this.lista = lista;
        this.ourHeroHpLabel = ourHeroHpLabel;
        this.enemyHeroHpLabel = enemyHeroHpLabel;
        /*try {
			this.ois.reset();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
	}
	public DrawerTask() {
		// TODO Auto-generated constructor stub
	}

	@Override 
	protected Object call() throws Exception { 
	 
			while(true)
			{
				
					System.out.println("czekam na object");
					
                    receivedPack = (Pack) ois.readObject();                                               
                    System.out.println("otrzymalem paczke: " + receivedPack);
                    
                    if(receivedPack.isEndTurnClicked()==true)
                    {
                    	//ois.reset();
                    	return true;
                    }
                    
                    if(receivedPack.getCard1() < 8 && (receivedPack.getCard2()>7 && receivedPack.getCard2() < 16))
                    {
                    	move.setCard1(lista.get(receivedPack.getCard1() + 8));
                    	move.setCard2(lista.get(receivedPack.getCard2() - 8));
                    	id1 = receivedPack.getCard1() + 8;
                    	id2 = receivedPack.getCard2() - 8; 
                    	Platform.runLater(new Runnable() {
                            @Override public void run() {
                            	//PathTransition ptr = getPathTransition(allyHero,enemyHero);		  
                				//ptr.play();	
                            	PathTransition ptr = getPathTransition(lista.get(id1).getImageview(),lista.get(id2).getImageview());		  
                				ptr.play();
                				trade(lista.get(id1),lista.get(id2));
                				id1 = -1;
                				id2 = -1;
                            }
                        });
                    }
                    
                    if(receivedPack.getCard1()<8 && (receivedPack.getCard2() == 100))
                    {
                    	id1 = receivedPack.getCard1() + 8;
                
                    	Platform.runLater(new Runnable() {
                            @Override public void run() {
                            	//PathTransition ptr = getPathTransition(allyHero,enemyHero);		  
                				//ptr.play();	
                            	PathTransition ptr = getPathTransition(lista.get(id1).getImageview(),allyHero);		  
                				ptr.play();
                				int ourHp = Integer.parseInt(ourHeroHpLabel.getText());
                				ourHp = ourHp - Integer.parseInt(lista.get(id1).getAttackLabel().getText());
                				if(ourHp < 1)
        				        {
        							JOptionPane.showMessageDialog(null, "Przegra³eœ!");
        				        }
                				ourHeroHpLabel.setText(Integer.toString(ourHp));
                				
                            }
                        });
                    }
                    if((receivedPack.getCard1() > 15) && (receivedPack.getCard2() < 8) )
                    {
                    	id1 = receivedPack.getCard1();
                    	id2 = receivedPack.getCard2() + 8;
                    	Platform.runLater(new Runnable() {
                            @Override public void run() {
                            	//PathTransition ptr = getPathTransition(allyHero,enemyHero);		  
                				//ptr.play();	
                            	lista.get(id2).setCard(receivedPack.getCardPlayed());
                            	lista.get(id2).getImageview().setImage(new Image(lista.get(id2).getCard().getPicName()));
                            	lista.get(id2).getAttackLabel().setText(Integer.toString(lista.get(id2).getCard().getAttack()));
                            	lista.get(id2).getHpLabel().setText(Integer.toString(lista.get(id2).getCard().getHp()));
                            	lista.get(id2).getRetalationLabel().setText(Integer.toString(lista.get(id2).getCard().getRetaliation()));
                            }
                        });
                    }                                
			}
			  
		}
	
	public void trade(CardRepresantation a,CardRepresantation b)
	{
		b.getHpLabel().setText(Integer.toString(Integer.parseInt(b.getHpLabel().getText()) - Integer.parseInt(a.getAttackLabel().getText())));
		if(Integer.parseInt(b.getHpLabel().getText()) > 0)
			a.getHpLabel().setText(Integer.toString(Integer.parseInt(a.getHpLabel().getText()) - Integer.parseInt(b.getRetalationLabel().getText())));
		else
		{
			b.setCard(new Card());
			Image image2 = new Image("transparent.png");
			b.getImageview().setImage(image2);
			b.getAttackLabel().setText("");
			b.getHpLabel().setText("");
			b.getRetalationLabel().setText("");
		}
		if(Integer.parseInt(a.getHpLabel().getText()) > 0)
		{}
		else
		{
			a.setCard(new Card());
			Image image2 = new Image("transparent.png");
			a.getImageview().setImage(image2);
			a.getAttackLabel().setText("");
			a.getHpLabel().setText("");
			a.getRetalationLabel().setText("");
		}
			
	}
	
	private PathTransition getPathTransition(ImageView first, ImageView second) {
		
		double layoutx  = first.localToScene(second.getBoundsInLocal()).getMinX();
		double layouty = first.localToScene(second.getBoundsInLocal()).getMinY();
		PathTransition ptr = new PathTransition();
		Path path = new Path();
		path.getElements().clear();
		path.getElements().add(new MoveToAbs(first));
		path.getElements().add(new LineToAbs(first, second.localToScene(second.getBoundsInLocal()).getMinX(),second.localToScene(second.getBoundsInLocal()).getMinY()));
		path.getElements().add(new LineToAbs(first, layoutx,layouty));
		ptr.setPath(path);
		ptr.setNode(first);
		return ptr;
		}
	private PathTransition getPathTransitionFromHand(ImageView first,ImageView second)
	{
		PathTransition ptr = new PathTransition();
		Path path = new Path();
		path.getElements().clear();
		path.getElements().add(new MoveToAbs(first));
		path.getElements().add(new LineToAbs(first, second.localToScene(second.getBoundsInLocal()).getMinX(),second.localToScene(second.getBoundsInLocal()).getMinY()));
		ptr.setPath(path);
		ptr.setNode(first);
		return ptr;
		
	}
	
	public static class MoveToAbs extends MoveTo {
		public MoveToAbs(Node node) {
		super(node.getLayoutBounds().getWidth() / 2,node.getLayoutBounds().getHeight() / 2);
		}
		}
		public static class LineToAbs extends LineTo {
		public LineToAbs(Node node, double x, double y) {
		super(x - node.localToScene(node.getBoundsInLocal()).getMinX() + node.getLayoutBounds().getWidth() / 2, y - node.localToScene(node.getBoundsInLocal()).getMinY() + node.getLayoutBounds().getHeight() / 2);
		}
	}
	
	}
