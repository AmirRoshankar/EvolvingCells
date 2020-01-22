import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.Console;
import java.util.*;
import java.util.concurrent.TimeUnit;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

/*
 * next task:
 * fix the flee and continue system 
 * direction depends on recent food consumption
	how much 
	where

keep memory of last n turns regarding x directions (360 degrees/x for the cell)
 
 
 * radius too
 */
public class Presenter{
	private static double iX = 0;
	private static double iY = 0;
	public static double iN = 5;
	public static ArrayList<Cell2> cells = new ArrayList<Cell2>();
	public static Map2 map;
	public static InfoGUI infoGUI;
	public static JFrame frame = new JFrame();
	private static JFrame state = new JFrame();
	
	public static void startSim() throws InterruptedException
	{
		InfoGUI infoG = new InfoGUI();
		while (!infoG.getSetUp())
		{
			//wait until the infoGUI class has gathered the required details
		}

		System.gc();
		iX = map.getSize()/10;
		iY = map.getSize()/2;
		
		for (int i = 0; i < iN; i++)
		{
			cells.add(new Cell2(iX + i * (map.getSize()/iN), iY, Cell2.iHtoD, Cell2.iBirthH, 
					Cell2.iRepH, Cell2.iMutResist, map));
		}
		
				
		
		GUI2 gui = new GUI2(map, cells);
		frame.add(gui);
		frame.setSize(gui.getSize());
		frame.setTitle("The Map");
		state.setTitle("Current State");
		state.setSize(400,200);
		JLabel popLabel = new JLabel();
		popLabel.setText("Population Size: " + cells.size());
		JLabel hToDLabel = new JLabel();
		hToDLabel.setText("Health to Distance Average: N/A");
		state.setLayout(new GridLayout(2,1));
		state.add(popLabel);
		state.add(hToDLabel);
		
		frame.setVisible(true);
		state.setVisible(true);

		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		state.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.addWindowListener(new WListener());
		state.addWindowListener(new WListener());
		
		double sumhToRCoeff = 0;
		double sumContCoeff = 0;
		int deleted = 0;
		int c =0;
		while (cells.size()>0)
		{
			TimeUnit.MILLISECONDS.sleep(50);
			popLabel.setText("Population Size: " + cells.size());
			
			double hToDAve = 0;

			for (int i = cells.size()-1; i >= 0; i--)
			{
				hToDAve += cells.get(i).getHToD();
			}
			hToDAve /= cells.size();
			hToDLabel.setText("Health to Distance Average: " + Math.round(hToDAve*100000)/100000.0);

			
			for (int i = cells.size()-1; i >= 0; i--)
			{
			
				cells.get(i).moveRand();
								
				Cell2 temp = cells.get(i).tryReplicate();
				if (temp!=null)
				{
					cells.add(temp);
				}
				
				if (cells.get(i).getLife()==false)
				{
					deleted++;
					cells.remove(i);
				}
			}
			
			gui.repaint();
			map.update();
			state.revalidate();
			state.repaint();
		}
		System.exit(0);
	}
	
	public static void setMap(Map2 nMap)
	{
		map = nMap;
	}
	
	public static Map2 getInitials()
	{
		Scanner c = new Scanner(System.in);
		
		System.out.println("Number of Cells: ");
		iN = c.nextDouble();
		
		System.out.println("iHtoD: ");
		Cell2.iHtoD = c.nextDouble();
		
		System.out.println("dPerH: ");
		Cell2.dPerH = c.nextDouble();
		
		System.out.println("hPerFood: ");
		Cell2.hPerFood = c.nextDouble();
		
		System.out.println("maxH: ");
		Cell2.maxH = c.nextDouble();
		
		System.out.println("iBirthH: ");
		Cell2.iBirthH = c.nextDouble();
		
		System.out.println("iRad: ");
		Cell2.iRad= c.nextDouble();
		
		System.out.println("iRepH: ");
		Cell2.iRepH = c.nextDouble();
		
		System.out.println("repCost: ");
		Cell2.repCost = c.nextDouble();
		
		System.out.println("iMutResist: ");
		Cell2.iMutResist = c.nextDouble();
		
		System.out.println("Food R: ");
		double foodR = c.nextDouble();
		
		System.out.println("SIZE: ");
		double SIZE = c.nextDouble();
		
		System.out.println("F_ADD: ");
		int F_ADD = c.nextInt();
		
		System.out.println("START_F: ");
		int START_F = c.nextInt();
		
		return new Map2(foodR, SIZE, F_ADD, START_F);
	}

}

class WListener implements WindowListener
{
	public void windowClosing(WindowEvent event) 
	{
	    System.exit(0);
	}

	public void windowOpened(WindowEvent event) {}
	public void windowClosed(WindowEvent event) {}
	public void windowIconified(WindowEvent event) {}
	public void windowDeiconified(WindowEvent event) {}
	public void windowActivated(WindowEvent event) {}
	public void windowDeactivated(WindowEvent event) {}

}
