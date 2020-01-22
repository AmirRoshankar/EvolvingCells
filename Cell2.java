import java.awt.Graphics2D;
import java.util.*;
import java.util.concurrent.TimeUnit;
public class Cell2 
{
	private double x;
	private double y;
	private double r;
	
	private double health;
	
	private boolean alive;
	
	// for D_PER_HEALTH = .3, hToD is 1.858651
	//recent hToD: 0.04
	public static double iHtoD = 5;//1.58651;
	public static double liveCost = 0;
	public static double dPerH = 5;
	public static double hPerFood = 1;
	public final static double CRIT_HEALTH = 0;
	public static double maxH = 100;
	public static double iBirthH = 50;
	public static double iRad = 5;
	public static  double iRepH = 50;
	public static double repCost = 50;
	public static final double DETAIL = 100;
	public static double iMutResist = 5;

	
	private double hToD;
	private double birthH;
	private double replicateH;
	private double mutationResist;

	private Map2 map;

	public Cell2(double x, double y, double hToD, double birthH, 
			double replicateH, double mutationResist, 
			Map2 map)
	{
		this.x = x;
		this.y = y;
		health = birthH;
		this.r = iRad;
		alive = true;
		this.hToD = hToD;
		this.birthH = birthH;
		this.replicateH = replicateH;
		this.map = map;
		this.mutationResist = mutationResist;
	}
	
	/*
	 *  Returns a random int from -1 to 1, with a higher tendancy to give 0
	 *	The higher the degree, the higher the tendancy to give 0
	 */
	
	public double randomMut(double degree)
	{
		double mut = Math.atan(Math.random()*2*degree-degree)/(Math.PI/2);
		if (mut>=0) return 1-mut;
		return -1 - mut;
	}
	
	public double getX()
	{
		return x;
	}
	
	public double getY()
	{
		return y;
	}
	
	public double getR()
	{
		return r;
	}
	
	public String toString()
	{
		return "x: " + x + " y: " + y + " health: " + health+ "hToD: " + hToD +
				" birthH: " + birthH + " replicateH: " + replicateH;
	}
	
	public Cell2 tryReplicateNoMut()
	{
		if (health > replicateH)
		{
			health -= repCost;
			double d = this.getR();
			double a = Math.random()*Math.PI*2;
			return new Cell2(getX() + Math.cos(a)*d, getY() + Math.sin(a)*d, hToD, birthH, replicateH, 
					mutationResist, map);
		}
		return null;
	}
	
	public Cell2 tryReplicate()
	{
		if (health > replicateH)
		{
			health -= repCost;
			double d = this.getR();
			double a = Math.random()*Math.PI*2;
			double NhToD = hToD + randomMut(mutationResist)*hToD;
			if (NhToD<0)
			{
				NhToD = 0;
			}
			
			return new Cell2(x + Math.cos(a)*d, y + Math.sin(a)*d, NhToD, birthH, replicateH, 
					mutationResist, map);
		}
		return null;
	}
	
	void moveRand()
	{
		//move
		double a = Math.random()*2*Math.PI;
		moveBasic(a, hToD*dPerH);
		health-=liveCost;
		Cell2 baby = tryReplicate();
		if (baby!=null) Presenter.cells.add(baby);
	}
	
	
	private void moveBasic(double a, double d)
	{
		
		double fx = x+d* Math.cos(a);
		double fy = y+d* Math.sin(a);
		
		double dx = (fx-x)/DETAIL;
		double dy = (fy-y)/DETAIL;
		double dh = d/(DETAIL*dPerH);
		
		for (int i = 0; i < DETAIL; i ++)
		{
			eat2();
			x+=dx;
			y+= dy;
			health -=dh;
			
			//check if dead
			if (health<=0) 
			{
				alive = false;
				return;
			}
		
		}
	}
	
	
	/*
	 * i remade eat because i dont know y it was not working
	 */
	private void eat2()
	{
		
		ArrayList<Double[]> foundFood = map.findFood(this);
		if (foundFood==null) {
			return;
		}
		health += hPerFood * foundFood.size();
		
		
	}
	
	
	
	public boolean getLife() 
	{
		return alive;
	}

	public double getHToD() 
	{
		return hToD;
	}

	
	public double getHealth() 
	{
		return health;
	}
	
	public double[] getFMemWeight()
	{
		return null;
		//return fMemWeight;
	}

}
