import java.awt.*;
import java.awt.event.*;
import java.awt.geom.*;
import java.util.ArrayList;

import javax.swing.*;

public class GUI2 extends JComponent
{
	//public static JFrame frame = new JFrame();
	private Map2 map;
	private ArrayList<Cell2> cell;
	private ArrayList<Double[]> f;
	
	
	
	
	//Responsible for drawing the cells and food on the map
	public GUI2(Map2 map, ArrayList<Cell2> cell)
	{
		this.map = map;
		this.cell = cell;
		
		this.setSize((int) map.getSize()+100, (int) map.getSize()+100);
		this.setVisible(true);
	}
	public void paint(Graphics g)
	{
		f = map.getF();
					
		Graphics2D g2 = (Graphics2D)g;
		
		g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, 
				RenderingHints.VALUE_ANTIALIAS_ON);

		g2.setPaint(Color.RED);
		
		//drawing the cell
		Shape tempC;
		for (int i = 0; i < cell.size(); i++)
		{
			double cellX = cell.get(i).getX();
			double cellY = cell.get(i).getY();
			double cellR = cell.get(i).getR();
			tempC = new Ellipse2D.Double(cellX-cellR, cellY-cellR, 2*cellR, 2*cellR);
			g2.draw(tempC);
		}
			
		g2.setPaint(Color.DARK_GRAY);
		
		//drawing the food  
		double fR = map.getFR();
		Shape tempF;
		for (int i = 0 ; i < f.size(); i ++)
		{
			Double[] food = f.get(i);
			double fX = food[0];
			double fY = food[1];
			tempF = new Ellipse2D.Double(fX, fY, 2*fR, 2*fR);
			g2.draw(tempF);
		}
	}

}


























/*import java.awt.*;
import java.awt.event.*;
import java.awt.geom.*;
import java.util.ArrayList;

import javax.swing.*;

public class GUI extends Frame
{
	private static ArrayList<Shape> cell;
	private static Frame f;

	public void paint (Graphics graphics)
	{
		Graphics2D graphics2D = (Graphics2D)graphics;
		
		for (int i = 0; i < cell.size(); i++)
		{
			graphics2D.draw(cell.get(i));
			graphics2D.setPaint(Color.blue);
			graphics2D.fill(cell.get(i));
		}
	}
	
	public static void drawCirc (double x, double y, double r, Map map)
	{
		//g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		//g.setRenderingHint(RenderingHints.KEY_STROKE_CONTROL, RenderingHints.VALUE_STROKE_PURE);
		//g.draw(new Ellipse2D.Double(x, y, r, r));
		Shape temp = new Ellipse2D.Double(x,y,r,r);
		cell.add(temp);
		
		for (int i = 0; i < map.getF().size(); i++)
		{
			temp = new Ellipse2D.Double(x,y,r,r);
			cell.add(temp);
		}
		
		f = new GUI();
		f.addWindowListener(new WindowAdapter()
		{
			public void windowClosing(WindowEvent we)
			{
				System.exit(0);
		    }
		});

		f.setSize(500, 500);
		f.setVisible(true);
	}
	
	public static void repaint(double x, double y, double r, Map map)
	{
		cell.clear();
		cell.add(new Ellipse2D.Double(x,y,r,r));
		
		for (int i = 0; i < map.getF().size(); i++)
		{
			cell.add(new Ellipse2D.Double(x,y,r,r));
		}		
	}
	
	public static void main (String args[])
	{
		//drawCirc(50, 50, 5, null);
	}
}
*/
