package ch.makery.address.view;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.prefs.Preferences;

import javax.swing.JOptionPane;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import ch.makery.address.MainApp;
import ch.makery.address.model.Card;
import ch.makery.address.model.DecksWrapper;
import ch.makery.address.model.DrawerTask;
import ch.makery.address.model.Move;
import ch.makery.address.model.Pack;
import ch.makery.address.model.CardRepresantation;
//import ch.makery.address.view.BoardController.LineToAbs;
//import ch.makery.address.view.BoardController.MoveToAbs;
import javafx.scene.Node;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.animation.ParallelTransition;
import javafx.animation.PathTransition;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.concurrent.WorkerStateEvent;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.LineTo;
import javafx.scene.shape.MoveTo;
import javafx.scene.shape.Path;

public class GameBoardController {
	
	@FXML
	private ImageView bohater1;
	@FXML
	private ImageView bohaterPrzeciw;
	@FXML
	private ImageView poleOurs11;
	@FXML
	private ImageView poleOurs12;
	@FXML
	private ImageView poleOurs13;
	@FXML
	private ImageView poleOurs14;
	@FXML
	private ImageView poleOurs21;
	@FXML
	private ImageView poleOurs22;
	@FXML
	private ImageView poleOurs23;
	@FXML
	private ImageView poleOurs24;
	@FXML
	private ImageView poleEnemy11;
	@FXML
	private ImageView poleEnemy12;
	@FXML
	private ImageView poleEnemy13;
	@FXML
	private ImageView poleEnemy14;
	@FXML
	private ImageView poleEnemy21;
	@FXML
	private ImageView poleEnemy22;
	@FXML
	private ImageView poleEnemy23;
	@FXML
	private ImageView poleEnemy24;
	@FXML
	private ImageView reka1;
	@FXML
	private ImageView reka2;
	@FXML
	private ImageView reka3;
	@FXML
	private ImageView reka4;
	@FXML
	private ImageView reka5;
	@FXML
	private GridPane oursgridpane;
	@FXML
	private GridPane enemygridpane;
	@FXML
	private Label label001;
	@FXML
	private Label label002;
	@FXML
	private Label label003;
	@FXML
	private Label label011;
	@FXML
	private Label label012;
	@FXML
	private Label label013;
	@FXML
	private Label label021;
	@FXML
	private Label label022;
	@FXML
	private Label label023;
	@FXML
	private Label label031;
	@FXML
	private Label label032;
	@FXML
	private Label label033;
	@FXML
	private Label label101;
	@FXML
	private Label label102;
	@FXML
	private Label label103;
	@FXML
	private Label label111;
	@FXML
	private Label label112;
	@FXML
	private Label label113;
	@FXML
	private Label label121;
	@FXML
	private Label label122;
	@FXML
	private Label label123;
	@FXML
	private Label label131;
	@FXML
	private Label label132;
	@FXML
	private Label label133;
	@FXML
	private Label label201;
	@FXML
	private Label label202;
	@FXML
	private Label label203;
	@FXML
	private Label label211;
	@FXML
	private Label label212;
	@FXML
	private Label label213;
	@FXML
	private Label label221;
	@FXML
	private Label label222;
	@FXML
	private Label label223;
	@FXML
	private Label label231;
	@FXML
	private Label label232;
	@FXML
	private Label label233;
	@FXML
	private Label label301;
	@FXML
	private Label label302;
	@FXML
	private Label label303;
	@FXML
	private Label label311;
	@FXML
	private Label label312;
	@FXML
	private Label label313;
	@FXML
	private Label label321;
	@FXML
	private Label label322;
	@FXML
	private Label label323;
	@FXML
	private Label label331;
	@FXML
	private Label label332;
	@FXML
	private Label label333;
	@FXML
	private Label ourHeroHpLabel;
	@FXML
	private Label enemyHeroHpLabel;
	@FXML
	private Label turnLabel;
	@FXML
	private TextField serverAddress;
	@FXML
	private TextField portAddress;
	@FXML
	private TextField deckAddress;
	
	
	//Canvas canvas;
	
	private ObservableList<CardRepresantation> lista = FXCollections.observableArrayList();
	private ObservableList<ImageView> imageviews = FXCollections.observableArrayList();
	private ObservableList<Integer> actions = FXCollections.observableArrayList();
	//private List<Boolean> actions=new ArrayList<Boolean>();
	private Move move = new Move();
	int id1 = -1;
	int id2 = -1;
	private boolean isItOurTurn = false;
	private boolean wasCardPlayed = false;
	
	private ObservableList<Card> deck = FXCollections.observableArrayList();
	
	private Socket s;
	OutputStream os;
	ObjectOutputStream oos;
	InputStream is;
	ObjectInputStream ois;
	private Pack pack = new Pack();
	
	private MainApp mainApp;
	
	private int HeroHP = 30;
	private int enemyHeroHp = 30;
	
	
	public GameBoardController(){}
	
	@FXML
    private void initialize() {
		
		for(int i=0;i<21;i++)
		{
			CardRepresantation  tmp= new CardRepresantation();
			lista.add(tmp);
		}
		
		lista.get(0).setImageview(poleOurs11);
		lista.get(1).setImageview(poleOurs12);
		lista.get(2).setImageview(poleOurs13);
		lista.get(3).setImageview(poleOurs14);
		lista.get(4).setImageview(poleOurs21);
		lista.get(5).setImageview(poleOurs22);
		lista.get(6).setImageview(poleOurs23);
		lista.get(7).setImageview(poleOurs24);
		lista.get(8).setImageview(poleEnemy11);
		lista.get(9).setImageview(poleEnemy12);
		lista.get(10).setImageview(poleEnemy13);
		lista.get(11).setImageview(poleEnemy14);
		lista.get(12).setImageview(poleEnemy21);
		lista.get(13).setImageview(poleEnemy22);
		lista.get(14).setImageview(poleEnemy23);
		lista.get(15).setImageview(poleEnemy24);
		lista.get(16).setImageview(reka1);
		lista.get(17).setImageview(reka2);
		lista.get(18).setImageview(reka3);
		lista.get(19).setImageview(reka4);
		lista.get(20).setImageview(reka5);
		lista.get(0).setAttackLabel(label001);
		lista.get(0).setRetalationLabel(label002);
		lista.get(0).setHpLabel(label003);
		lista.get(1).setAttackLabel(label011);
		lista.get(1).setRetalationLabel(label012);
		lista.get(1).setHpLabel(label013);
		lista.get(2).setAttackLabel(label021);
		lista.get(2).setRetalationLabel(label022);
		lista.get(2).setHpLabel(label023);
		lista.get(3).setAttackLabel(label031);
		lista.get(3).setRetalationLabel(label032);
		lista.get(3).setHpLabel(label033);
		lista.get(4).setHpLabel(label103);
		lista.get(5).setHpLabel(label113);
		lista.get(6).setHpLabel(label123);
		lista.get(7).setHpLabel(label133);
		lista.get(8).setHpLabel(label203);
		lista.get(9).setHpLabel(label213);
		lista.get(10).setHpLabel(label223);
		lista.get(11).setHpLabel(label233);
		lista.get(12).setHpLabel(label303);
		lista.get(13).setHpLabel(label313);
		lista.get(14).setHpLabel(label323);
		lista.get(15).setHpLabel(label333);
		lista.get(4).setRetalationLabel(label102);
		lista.get(5).setRetalationLabel(label112);
		lista.get(6).setRetalationLabel(label122);
		lista.get(7).setRetalationLabel(label132);
		lista.get(8).setRetalationLabel(label202);
		lista.get(9).setRetalationLabel(label212);
		lista.get(10).setRetalationLabel(label222);
		lista.get(11).setRetalationLabel(label232);
		lista.get(12).setRetalationLabel(label302);
		lista.get(13).setRetalationLabel(label312);
		lista.get(14).setRetalationLabel(label322);
		lista.get(15).setRetalationLabel(label332);
		lista.get(4).setAttackLabel(label101);
		lista.get(5).setAttackLabel(label111);
		lista.get(6).setAttackLabel(label121);
		lista.get(7).setAttackLabel(label131);
		lista.get(8).setAttackLabel(label201);
		lista.get(9).setAttackLabel(label211);
		lista.get(10).setAttackLabel(label221);
		lista.get(11).setAttackLabel(label231);
		lista.get(12).setAttackLabel(label301);
		lista.get(13).setAttackLabel(label311);
		lista.get(14).setAttackLabel(label321);
		lista.get(15).setAttackLabel(label331);
		
		
		
		for(int i=0;i<8;i++)
			actions.add(0);
		
		///Image image2 = new Image("obraz.jpg");
		newTurn();
		
		Image image2 = new Image("transparent.png");
		//poleOurs11.setImage(image);
		for(int i=0;i<16;i++)
		{
			lista.get(i).setImage(image2);
			lista.get(i).getImageview().setImage(image2);
		}
		
		//Image image = new Image("obraz.jpg");
		for(int i=16;i<21;i++)
		{
			lista.get(i).getCard().setPicName("transparent.png");
			lista.get(i).getImageview().setImage(new Image(lista.get(i).getCard().getPicName()));
		}
		
		/*image = new Image("obraz.jpg");
		Image image3 = new Image("hero1.jpg");
		bohater1.setImage(image3);
		image3 = new Image("hero4.jpg");
		bohaterPrzeciw.setImage(image3);*/
		/*
		//image = new Image("creature1.jpg");
		Card tempToAdd = new Card(2,1,5,"creature1.jpg");
		//deck.add(tempToAdd);
		deck.add(tempToAdd);
		//image = new Image("creature2.jpg");
		tempToAdd = new Card(4,0,10,"creature2.jpg");
		//deck.add(tempToAdd);
		deck.add(tempToAdd);
		//image = new Image("creature3.jpg");
		tempToAdd = new Card(2,2,3,"creature3.jpg");
		deck.add(tempToAdd);
		//image = new Image("creature4.jpg");
		tempToAdd = new Card(4,5,10,"creature4.jpg");
		deck.add(tempToAdd);
		//image = new Image("creature5.jpg");
		tempToAdd = new Card(2,1,15,"creature5.jpg");
		deck.add(tempToAdd);
		*/
		//File tmpFile = getDeckFilePath();
		//File tmpFile2 = 
		//saveDeckToFile(tmpFile);
		
		for(int i=0;i<16;i++)
		{
			lista.get(i).getAttackLabel().setTextFill(Color.web("#ea0b0b"));
			lista.get(i).getRetalationLabel().setTextFill(Color.web("#798c0c"));
			lista.get(i).getHpLabel().setTextFill(Color.web("#28bb0e"));
		}
		
		
		//Card temp = new Card
		serverAddress.setText("localhost");
		portAddress.setText("2002");
		deckAddress.setText("E:/Workspace/ProjektSieci/bin/deck1.xml");
		/* File personFile = getDeckFilePath();
	     if (personFile != null) {
	         saveDeckToFile(personFile);
	     } else{
	     	System.out.println("Nie udalo sie stworzyc File!");
	     }*/
		
		oursgridpane.setStyle("-fx-grid-lines-visible: true");
		enemygridpane.setStyle("-fx-grid-lines-visible: true");
		
	    
	}
	
	@FXML
	public void handleConnectButton()
	{
		String tmpSrvAdr = serverAddress.getText();
		try{
			int tmpPrtNumber = Integer.parseInt(portAddress.getText());
			s = new Socket(tmpSrvAdr,tmpPrtNumber);
			
			os = s.getOutputStream();
			oos = new ObjectOutputStream(os);
			
			is = s.getInputStream();
			ois = new ObjectInputStream(is);
			
			turnLabel.setText("Polaczono! Czekam na drugiego gracza...");
			Pack tmp = (Pack)ois.readObject();
			if((tmp.isEndTurnClicked() == true) && (tmp.getCard2() == 200))
			{
				ourHeroHpLabel.setText("30");
				enemyHeroHpLabel.setText("30");
				isItOurTurn = true;
				turnLabel.setText("Ty zaczynasz!");
				Image image3 = new Image("hero1.jpg");
				bohater1.setImage(image3);
				image3 = new Image("hero5.jpg");
				bohaterPrzeciw.setImage(image3);
				
				loadDeckFromFile(new File(deckAddress.getText()));
				
				for(int i=16;i<21;i++)
				{
					//lista.get(i).getCard().setPicName("obraz.jpg");
					lista.get(i).setCard(deck.get(i - 16));
					lista.get(i).getImageview().setImage(new Image(lista.get(i).getCard().getPicName()));
				}
			}
			else if((tmp.isEndTurnClicked() == false) && (tmp.getCard2() == 200))
			{
				ourHeroHpLabel.setText("30");
				enemyHeroHpLabel.setText("30");
				isItOurTurn = false;
				turnLabel.setText("Przeciwnik zaczyna!");
				Image image3 = new Image("hero5.jpg");
				bohater1.setImage(image3);
				image3 = new Image("hero1.jpg");
				bohaterPrzeciw.setImage(image3);
				
				isItOurTurn = false;
				newTurn();
				loadDeckFromFile(new File(deckAddress.getText()));
				
				for(int i=16;i<21;i++)
				{
					//lista.get(i).getCard().setPicName("obraz.jpg");
					lista.get(i).setCard(deck.get(i - 16));
					lista.get(i).getImageview().setImage(new Image(lista.get(i).getCard().getPicName()));
				}
				DrawerTask task = new DrawerTask(is,ois,bohater1,bohaterPrzeciw,lista,ourHeroHpLabel,enemyHeroHpLabel);
				task.setOnSucceeded(new EventHandler<WorkerStateEvent>(){
					@Override
					public void handle (WorkerStateEvent event){
						 isItOurTurn = (boolean) task.getValue();
						 System.out.println("W¹tek zwróci³ wartoœæ");
						 turnLabel.setText("Twoja tura!");
					}
				});
				
				new Thread(task).start();
			}
			else
			{
				turnLabel.setText("Nie otrzymano prawid³owej paczki!");
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		catch(Exception e)
		{
			turnLabel.setText("Podaj liczbe jako adres portu");
			return;
		}
	}
	
	@FXML
	public void handleEndTurnClick() throws IOException, ClassNotFoundException
	{
		if(isItOurTurn == false) return;
		//String tmp = new String("Hidden Messege");
		System.out.println("zanotowano kilkniecie");
		pack.setEndTurnClicked(true);
		oos.reset();
		oos.writeObject(pack);
		isItOurTurn = false;
		newTurn();
		turnLabel.setText("Tura przeciwnika!");
		//ois.reset();
		DrawerTask task = new DrawerTask(is,ois,bohater1,bohaterPrzeciw,lista,ourHeroHpLabel,enemyHeroHpLabel);
		task.setOnSucceeded(new EventHandler<WorkerStateEvent>(){
			@Override
			public void handle (WorkerStateEvent event){
				 isItOurTurn = (boolean) task.getValue();
				 System.out.println("W¹tek zwróci³ wartoœæ");
				 turnLabel.setText("Twoja tura!");
			}
		});
		
		new Thread(task).start();
	}
	
	
	@FXML
	public void handleImageviewClick(final MouseEvent event)
	{	
		System.out.println("zanotowano klikniecie\nid1 = " + id1 +"\nid2 = " + id2);
		if(isItOurTurn == false) return;
		if(move.getCard1() == null)
		{
		Object obj = event.getSource();
		  if ( obj instanceof ImageView )
		  {
			  //System.out.println(bohaterPrzeciw);
			  for(int i=0;i<21;i++)
			  {
				  if(lista.get(i).getImageview() == obj)
				  {
					  if(i>7 && i<16) return;
					  id1 = i;
					  move.setCard1(lista.get(id1));
					  lista.get(id1).getImageview().setStyle("-fx-effect: dropshadow(three-pass-box, red, 20, 0, 0, 0);");
					  break;
				  }
			  }	  
		  }
		  
		}
		else if(move.getCard2() == null )
		{
			Object obj = event.getSource();
			int id;
			  if ( obj instanceof ImageView )
			  {
				  //System.out.println(obj);
				  if(obj == bohaterPrzeciw)
				  {
					  if(id1>15)
					  {
						  lista.get(id1).getImageview().setStyle("-fx-effect: dropshadow(three-pass-box, white, 0, 0, 0, 0);");
						  id1 = -1;
						  id2 = -1;
						  move.setCard1(null);
						  move.setCard2(null);
						  return;
					  }
					  if(id1 >0 && id1 < 4)
					  {
					  if((lista.get(id1 + 8 ).getCard().getPicName() != "transparent.png") || (lista.get(id1 + 12).getCard().getPicName() != "transparent.png"))
						  {
						  //lista.get(id1).getImageview().setStyle("-fx-effect: dropshadow(three-pass-box, white, 0, 0, 0, 0);");
						  //id1 = -1;
						  id2 = -1;
						  //move.setCard1(null);
						  move.setCard2(null);
						  return;
						  }
					  }
					  else if(id1 >3 && id1<8)
						  if((lista.get(id1 + 4 ).getCard().getPicName() != "transparent.png") || (lista.get(id1 + 8).getCard().getPicName() != "transparent.png"))
						  {
							  //lista.get(id1).getImageview().setStyle("-fx-effect: dropshadow(three-pass-box, white, 0, 0, 0, 0);");
							  //id1 = -1;
							  id2 = -1;
							  //move.setCard1(null);
							  move.setCard2(null);
							  return;
						  }
					  if(id1<8)
					  if(actions.get(id1) == 0) 
					  {
						System.out.println("brak punktow ruchu! ");
						lista.get(id1).getImageview().setStyle("-fx-effect: dropshadow(three-pass-box, white, 0, 0, 0, 0);");
						id1 = -1;
						id2 = -1;
						move.setCard1(null);
			  			move.setCard2(null);
						return;
					  }
					  PathTransition ptr = getPathTransition(lista.get(id1).getImageview(),bohaterPrzeciw);		  
						ptr.play();
						actions.set(id1, 0);
						try {
							pack.setCard1(id1);
							pack.setCard2(100);
							pack.setEndTurnClicked(false);
							pack.setCardPlayed(null);
							oos.reset();
							oos.writeObject(pack);
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						
				  		int enemyHp = Integer.parseInt(enemyHeroHpLabel.getText());
				  		enemyHp = enemyHp - Integer.parseInt(lista.get(id1).getAttackLabel().getText());
				  		enemyHeroHpLabel.setText(Integer.toString(enemyHp));
				  		if(enemyHp < 1)
				        {
							JOptionPane.showMessageDialog(null, "Wygra³eœ!");
				        }
						
				  		ptr.setOnFinished(new EventHandler<ActionEvent>() {
				  			@Override
				  			public void handle(ActionEvent event) {
				  				//trade(move.getCard1(),move.getCard2());
				  				lista.get(id1).getImageview().setTranslateX(0);
				  				lista.get(id1).getImageview().setTranslateY(0);
				  				//lista.get(id2).getImageview().setTranslateX(0);
				  				//lista.get(id2).getImageview().setTranslateY(0);
				  				
				  				lista.get(id1).getImageview().setStyle("-fx-effect: dropshadow(three-pass-box, white, 0, 0, 0, 0);");
				  				move.setCard1(null);
				  				move.setCard2(null);
				  				id1 = -1;
				  				id2 = -1;
				  			}});
					  //System.out.println("trafione");
					  return;
				  }
				  if(obj == bohater1)
				  {
					  lista.get(id1).getImageview().setStyle("-fx-effect: dropshadow(three-pass-box, white, 0, 0, 0, 0);");
					  id1 = -1;
					  id2 = -1;
					  move.setCard1(null);
					  move.setCard2(null);
					  return;
				  }
				  for(int i=0;i<21;i++)
				  {
					  if(lista.get(i).getImageview() == obj)
					  {
						  if(id1 == i)
						  {
							  lista.get(id1).getImageview().setStyle("-fx-effect: dropshadow(three-pass-box, white, 0, 0, 0, 0);");
							  id1 = -1;
							  id2 = -1;
							  move.setCard1(null);
							  move.setCard2(null);
							  return;
						  }
						  if(i>15)
						  {
							  lista.get(id1).getImageview().setStyle("-fx-effect: dropshadow(three-pass-box, white, 0, 0, 0, 0);");
							  id1 = -1;
							  id2 = -1;
							  move.setCard1(null);
							  move.setCard2(null);
							  return;
						  }
						  if(id1 <16 && i<8)
						  {
							  lista.get(id1).getImageview().setStyle("-fx-effect: dropshadow(three-pass-box, white, 0, 0, 0, 0);");
							  id1 = -1;
							  id2 = -1;
							  move.setCard1(null);
							  move.setCard2(null);
							  return;
						  }
						  if((lista.get(i).getCard().getPicName() == "transparent.png") && (i >7))
						  {
							  lista.get(id1).getImageview().setStyle("-fx-effect: dropshadow(three-pass-box, white, 0, 0, 0, 0);");
							  id1 = -1;
							  id2 = -1;
							  move.setCard1(null);
							  move.setCard2(null);
							  return;
						  }
						  id2 = i;
						  move.setCard2(lista.get(id2));
						  break;
					  }
				  }
			  }
			  if(id1 < 8)
			  {
				  if(actions.get(id1) == 0) 
				  {
					  System.out.println("brak punktow ruchu! ");
					  lista.get(id1).getImageview().setStyle("-fx-effect: dropshadow(three-pass-box, white, 0, 0, 0, 0);");
					id1 = -1;
					id2 = -1;
					move.setCard1(null);
		  			move.setCard2(null);
					return;
				  }  
				  
				if((id2 - id1 != 8) && (id2 - id1 != 4) && (id2 - id1 != 12))
				{
					
					id2 = -1;
					move.setCard2(null);				
					return;
				}
			  	PathTransition ptr = getPathTransition(lista.get(id1).getImageview(),lista.get(id2).getImageview());		  
				ptr.play();
				try {
					pack.setCard1(id1);
					pack.setCard2(id2);
					pack.setEndTurnClicked(false);
					pack.setCardPlayed(null);
					oos.reset();
					oos.writeObject(pack);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		  		
		  		ptr.setOnFinished(new EventHandler<ActionEvent>() {
		  			@Override
		  			public void handle(ActionEvent event) {
		  				//trade(move.getCard1(),move.getCard2());
		  				lista.get(id1).getImageview().setTranslateX(0);
		  				lista.get(id1).getImageview().setTranslateY(0);
		  				lista.get(id2).getImageview().setTranslateX(0);
		  				lista.get(id2).getImageview().setTranslateY(0);
		  				
		  				trade(lista.get(id1),lista.get(id2));	
		  				actions.set(id1, 0);
		  				
		  				lista.get(id1).getImageview().setStyle("-fx-effect: dropshadow(three-pass-box, white, 0, 0, 0, 0);");
		  				move.setCard1(null);
		  				move.setCard2(null);
		  				id1 = -1;
		  				id2 = -1;
		  			}});
			  }
			  else if(id1 > 15 && id2 <8){
				  	if(wasCardPlayed == true)
				  	{
				  		System.out.println("Karta zostala juz zagrana w tej turze");
				  		lista.get(id1).getImageview().setStyle("-fx-effect: dropshadow(three-pass-box, white, 0, 0, 0, 0);");
							id1 = -1;
							id2 = -1;
							move.setCard1(null);
				  			move.setCard2(null);
							return;	  
				  	}
				  
				  	double fx = lista.get(id1).getImageview().getLayoutX();
			  		double fy = lista.get(id1).getImageview().getLayoutY();
			  		double sx = lista.get(id2).getImageview().getLayoutX();
			  		double sy = lista.get(id2).getImageview().getLayoutY();
				  
			  PathTransition ptr2 = getPathTransitionFromHand(lista.get(id1).getImageview(),lista.get(id2).getImageview());
			  ptr2.play();
			  try {
					pack.setCard1(id1);
					pack.setCard2(id2);
					pack.setEndTurnClicked(false);
					pack.setCardPlayed(lista.get(id1).getCard());
					oos.reset();
					oos.writeObject(pack);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			  ptr2.setOnFinished(new EventHandler<ActionEvent>() {
		  			@Override
		  			public void handle(ActionEvent event) {
		  				//trade(move.getCard1(),move.getCard2());
		  				lista.get(id1).getImageview().setTranslateX(0);
		  				lista.get(id1).getImageview().setTranslateY(0);
		  				lista.get(id2).getImageview().setTranslateX(0);
		  				lista.get(id2).getImageview().setTranslateY(0);
		  				
		  				//Collections.swap(lista, id1, id2);
		  				Card tmp = lista.get(id1).getCard();
		  				Card tmp2 = lista.get(id2).getCard();
		  				lista.get(id1).setCard(tmp2);
		  				lista.get(id2).setCard(tmp);
		  				
		  				lista.get(id1).getImageview().setImage(new Image(lista.get(id1).getCard().getPicName()));
		  				lista.get(id2).getImageview().setImage(new Image(lista.get(id2).getCard().getPicName()));
		  				lista.get(id2).getAttackLabel().setText(Integer.toString(lista.get(id2).getCard().getAttack()));
		  				lista.get(id2).getHpLabel().setText(Integer.toString(lista.get(id2).getCard().getHp()));
		  				lista.get(id2).getRetalationLabel().setText(Integer.toString(lista.get(id2).getCard().getRetaliation()));
		  				
		  				actions.set(id2, 0);
		  				wasCardPlayed = true;
		  				/*lista.get(id1).getImageview().setLayoutX(fx);
		  				lista.get(id1).getImageview().setLayoutY(fy);
		  				lista.get(id2).getImageview().setLayoutX(sx);
		  				lista.get(id2).getImageview().setLayoutY(sy);*/
		  				//lista.get(id1).getImageview().
		  				//Collections.swap(lista, id1, id2);
		  				//lista.get(id1).getImageview()
		  				
		  				lista.get(id1).getImageview().setStyle("-fx-effect: dropshadow(three-pass-box, white, 0, 0, 0, 0);");
		  				move.setCard1(null);
		  				move.setCard2(null);
		  				id1 = -1;
		  				id2 = -1;
		  			}});
			  }
			
		}
	}
	
	
	public void setMainApp(MainApp mainApp) {
	        this.mainApp = mainApp;
	}
	
	
	public void attackHero(Card a)
	{
		enemyHeroHp = enemyHeroHp - a.getAttack();
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
	
    private void newTurn()
    {
    	System.out.println("metoda nowej tury zostala wywolana!");
    	wasCardPlayed = false;
    	for(int i=0; i<actions.size();i++)
    	{
    		
    		actions.set(i, 1);
    		System.out.println("Ustawiam wartosc na "+ actions.get(i) + " dla id: " + i);
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
		
		public void setPersonFilePath(File file) {
	        Preferences prefs = Preferences.userNodeForPackage(MainApp.class);
	        if (file != null) {
	            prefs.put("filePath", file.getPath());

	            // Update the stage title.
	           // primaryStage.setTitle("AddressApp - " + file.getName());
	        } else {
	            prefs.remove("filePath");

	            // Update the stage title.
	           // primaryStage.setTitle("AddressApp");
	        }
	    }
		
		 public void loadDeckFromFile(File file) {
		        try {
		            JAXBContext context = JAXBContext
		                    .newInstance(DecksWrapper.class);
		            Unmarshaller um = context.createUnmarshaller();

		            // Reading XML from the file and unmarshalling.
		            DecksWrapper wrapper = (DecksWrapper) um.unmarshal(file);

		            deck.clear();
		            deck.addAll(wrapper.getDeck());
		            
		            // Save the file path to the registry.
		            setPersonFilePath(file);

		        } catch (Exception e) { // catches ANY exception
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
		    public void saveDeckToFile(File file) {
		        try {
		            JAXBContext context = JAXBContext
		                    .newInstance(DecksWrapper.class);
		            Marshaller m = context.createMarshaller();
		            m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

		            // Wrapping our person data.
		            DecksWrapper wrapper = new DecksWrapper();
		            wrapper.setDeck(deck);
		            
		            //File file2 = new File("E:/Workspace/");
		            // Marshalling and saving XML to the file.
		            m.marshal(wrapper, file);

		            // Save the file path to the registry.
		            setPersonFilePath(file);
		        } catch (Exception e) { // catches ANY exception
		            Alert alert = new Alert(AlertType.ERROR);
		            alert.setTitle("Error");
		            alert.setHeaderText("Could not save data");
		            alert.setContentText("Could not save data to file:\n" + file.getPath());

		            alert.showAndWait();
		        }
		    }
		
		    public File getDeckFilePath() {
		        Preferences prefs = Preferences.userNodeForPackage(MainApp.class);
		        String filePath = prefs.get("filePath", null);
		        if (filePath != null) {
		            return new File(filePath);
		        } else {
		            return null;
		        }
		    }

		    
}
