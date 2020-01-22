import java.awt.*;
import java.awt.event.*;
import java.awt.geom.*;
import java.util.ArrayList;

import javax.swing.*;

public class InfoGUI extends JFrame implements ActionListener
{
	private volatile boolean setUp = false;
	private JButton submit;
	private ArrayList<JTextField> textFields = new ArrayList<JTextField>();
	private ArrayList<JLabel> prompts = new ArrayList<JLabel>();
	
	public InfoGUI()
	{
		setUp = false;
		this.setSize(500, 600);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		submit = new JButton("Start");
		submit.addActionListener(this);
		this.setLayout(new GridLayout(16, 2, 10, 5));
		for (int i = 0; i < 15; i ++)
		{
			textFields.add(new JTextField("10"));
			
			switch (i)
			{
				case 0:prompts.add(new JLabel("Number of Cells: "));
					break;
				case 1:prompts.add(new JLabel("Initial Health To Distance: "));
					break;
				case 2:prompts.add(new JLabel("Distance Per Health: "));
					break;
				case 3:prompts.add(new JLabel("Health Per Food: "));
					break;
				case 4:prompts.add(new JLabel("Maximum Health: "));
					break;
				case 5:prompts.add(new JLabel("Initial Birth Health: "));
					break;
				case 6:prompts.add(new JLabel("Initial Radius: "));
					break;
				case 7:prompts.add(new JLabel("Initial Replication Health: "));
					break;
				case 8:prompts.add(new JLabel("Replication Cost: "));
					break;
				case 9:prompts.add(new JLabel("Initial Resistance to Mutation: "));
				break;
				case 10:prompts.add(new JLabel("Cost to Live: "));
				break;
				case 11:prompts.add(new JLabel("Food Radius: "));
					break;
				case 12:prompts.add(new JLabel("Size of map: "));
					break;
				case 13:prompts.add(new JLabel("Number of Food Pieces to Add: "));
					break;
				case 14:prompts.add(new JLabel("Initial Number of Food Pieces: "));
				break;
			}
			this.add(prompts.get(i));
			this.add(textFields.get(i));
		}
		this.add(new JPanel());
		this.add(submit);
		this.setVisible(true);
	}
	public boolean getSetUp()
	{
		return setUp;
	}
	@Override
	public void actionPerformed (ActionEvent event)
	{

		if (event.getSource().equals(submit))
		{
			double foodR = 0;
			int startF = 0;
			int fAdd = 0;
			double size = 0;
			for (int i = 0; i < textFields.size(); i++)
			{
				switch (i)
				{
					case 0: Presenter.iN = Integer.parseInt(textFields.get(i).getText());
						break;
					case 1: Cell2.iHtoD = Double.parseDouble(textFields.get(i).getText());
						break;
					case 2: Cell2.dPerH = Double.parseDouble(textFields.get(i).getText());
						break;
					case 3:Cell2.hPerFood = Double.parseDouble(textFields.get(i).getText());
						break;
					case 4:Cell2.maxH = Double.parseDouble(textFields.get(i).getText());
						break;
					case 5:Cell2.iBirthH = Double.parseDouble(textFields.get(i).getText());
						break;
					case 6:Cell2.iRad = Double.parseDouble(textFields.get(i).getText());
						break;
					case 7:Cell2.iRepH = Double.parseDouble(textFields.get(i).getText());
						break;
					case 8:Cell2.repCost = Double.parseDouble(textFields.get(i).getText());
						break;
					case 9:Cell2.iMutResist = Double.parseDouble(textFields.get(i).getText());
					break;
					case 10:Cell2.liveCost = Double.parseDouble(textFields.get(i).getText());
					break;
					case 11: foodR = Double.parseDouble(textFields.get(i).getText());
						break;
					case 12: size = Double.parseDouble(textFields.get(i).getText());
						break;
					case 13: fAdd = Integer.parseInt(textFields.get(i).getText());
						break;
					case 14: startF = Integer.parseInt(textFields.get(i).getText());
						break;
					
				}				
			}
			Presenter.setMap(new Map2(foodR, size, fAdd, startF));
			setUp = true;
			submit.removeActionListener(this);
			removeAll();
			this.dispose();
			
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
