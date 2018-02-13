package ch.makery.address.model;


import java.awt.Color;
import java.awt.image.BufferedImage;
import java.util.Random;

import javafx.concurrent.Task;
import javafx.embed.swing.SwingFXUtils;
import javafx.scene.canvas.GraphicsContext;

//import javafx.scene.control.ProgressBar;

public class DrawerTask extends Task { 
	
	GraphicsContext gc;
	BufferedImage bi;
	private double x;
	private double y;
	private int tmp1 ;
	private int tmp2 ;
	private Random random = new Random();
	private double valOfIntegral;
	private int repetition;
	
	public DrawerTask(GraphicsContext gc,int repetition) {
		tmp1 =0;
		tmp2 =0;
		this.gc = gc;
		this.repetition = repetition;
		//this.integral = integral;
		bi = new BufferedImage(800, 800,
				BufferedImage.TYPE_INT_RGB);
		/*for(int i=0;i<800;i++)
		{
			bi.setRGB(400,i,Color.WHITE.getRGB());
		}
		for(int i=0;i<800;i++)
		{
			bi.setRGB(i,400,Color.WHITE.getRGB());
		}*/
	}

	public DrawerTask() {
		// TODO Auto-generated constructor stub
	}

	@Override 
	protected Object call() throws Exception { 
	 
			for(int i=0;i<repetition;i++)
			{
				x = -8 + 16* random.nextDouble();
				y = -8 + 16* random.nextDouble();
				
				if(Equation.calc(x, y))
				{
					bi.setRGB((int)(400 - 400*x/8),(int)(400 - 400*y/8), Color.YELLOW.getRGB());
					tmp1++;
				}
				else
				{
					bi.setRGB((int)(400 - 400*x/8),(int)(400 - 400*y/8), Color.BLUE.getRGB());
					tmp2++;
				}
				
				if(i%10000==0){
				gc.drawImage(SwingFXUtils.toFXImage(bi, null), 0,0 );
				valOfIntegral = (double)tmp1/(tmp1+tmp2);
				updateProgress(i,repetition);
				}
				if(isCancelled()) break;
			}
			 
			
		return valOfIntegral*16.0*16.0; 
		}
	
	
	}
