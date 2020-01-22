import java.util.*;
import java.lang.Math.*;
public class Map2 
{
	//stores food and threats
	private ArrayList<Double[]> t;
	private ArrayList<Double[]> f;
	
	//size of food and threats
	private double foodR = 1;
	
	//Size of map, num of food 
	//and threats added each round and initial amount
	private double SIZE = 500;
	private int F_ADD = 400;
	private int START_F = 5000;
	
	//makes a map with some food and threats, each in their own array
	Map2 (double foodR, double SIZE, int F_ADD, int START_F)
	{
		this.foodR = foodR;
		this.SIZE = SIZE;
		this.F_ADD = F_ADD;
		this.START_F = START_F;
		
		t = new ArrayList<Double[]>();
		f = new ArrayList<Double[]>();
		
		addF(START_F);
	}
	
	//a bunch of accessors
	public double getSize()
	{
		return SIZE;
	}
	
	public double getFR()
	{
		return foodR;
	}
	
	public ArrayList<Double[]> getF()
	{
		return f;
	}
	
	public ArrayList<Double[]> getT()
	{
		return t;
	}
	
	//adds Food elements to the map randomely-ish (in lines)
	private void addFLines (int num)
	{
		for (int i = 0; i < num; i++)
		{
			if (f.size()==0)
			{
				Double[] tempFoodLoc = {Math.random()*SIZE, Math.random()*SIZE};
				f.add(tempFoodLoc);
			}
			int place = (int) (Math.random()*(f.size()));
			double newX =0;
			double newY;
			if (place == 0)
			{
				double beforeX = 0;
				double afterX = (f.get(0)[0]);
				double diffX = afterX - beforeX;
				double addX = Math.random()*diffX;
				newX = beforeX + addX;
				
				newY = Math.random()*SIZE;
				Double[] tempFoodLoc = {newX, newY};
				f.add(place, tempFoodLoc);
			}
			else if (place == f.size()-1)
			{
				double beforeX = f.get(place)[0];
				double afterX=SIZE;
				double diffX = afterX - beforeX;
				double addX = Math.random()*diffX;
				newX = beforeX + addX;
				
				newY = Math.random()*SIZE;
				Double[] tempFoodLoc = {newX, newY};
				f.add(tempFoodLoc);
			}
			else
			{
				double beforeX = f.get(place-1)[0];
				double afterX= f.get(place)[0];
				double diffX = afterX - beforeX;
				double addX = Math.random()*diffX;
				newX = beforeX + addX;
				
				newY = Math.random()*SIZE;
				Double[] tempFoodLoc = {newX, newY};
				f.add(place, tempFoodLoc);
				// trying to sort the y too seems useless but try if needed
				
				
				
								
			}
		}
		
	}
	
	/*
	 * Add food TRUELY randomly to the map
	 * Sort sort the foods based on the first bit (the x coordinate)
	 * Note that there may be an error with concatting the 
	 * 		double since the precisions may not always be the same
	 */
	
	public void addF(int numF)
	{
		for (int i = 0 ; i < numF; i ++)
		{
			double tempX = Math.random()*SIZE;
			double tempY = Math.random()*SIZE;
			Double[] tempFoodLoc = {tempX, tempY};
			
			f.add(tempFoodLoc);
		}
	}
	
	
	
	/*
	 * takes a cell and finds food for it
	 * takes out the foods it found from the map
	 * returns an arraylist of all the foods that were found
	 */
	public ArrayList<Double[]> findFood(Cell2 cell)
	{
		if (f.size()==0)
		{
			return null;
		}
		ArrayList<Double[]> foundF = new ArrayList<Double[]>();
		
		double cellR = cell.getR();
		
		double x = cell.getX();
		double y = cell.getY();
		
		
		double lowX = x - cellR - foodR;
		double highX = x + cellR + foodR;
		double lowY = y - cellR - foodR;
		double highY = y + cellR + foodR;
		
		Utilities.sortFoodX(f);
		
		int lowXIndex = Utilities.findLowBound(f,0, f.size()-1,lowX, 0);
		
		if (lowXIndex==-1)
		{
			return null;
		}	
		
		int highXIndex = Utilities.findLowBound(f,0, f.size()-1,highX, 0);
		
		if (highXIndex==-1)
		{
			return null;
		}		
		
		Utilities.qSortY(f, lowXIndex, highXIndex);
		int lowYIndex = Utilities.findLowBound(f,lowXIndex, highXIndex,lowY, 1);
		if (lowYIndex==-1)
		{
			return null;
		}	
		int highYIndex = Utilities.findLowBound(f,lowXIndex, highXIndex,highY, 1);
		if (highYIndex==-1)
		{
			return null;
		}
		
		ArrayList<Integer> toRemove = new ArrayList<Integer>();
		Double[] curr;
		//System.out.println("in " + cellR);
		for (int i = lowYIndex; i <= highYIndex; i++)
		{
			curr = f.get(i);
			
			double tempX = curr[0];
			double tempY = curr[1];
			
			if(Math.sqrt((tempY-y)*(tempY-y) + (tempX-x)*(tempX-x))<cellR+foodR)
			{
				toRemove.add(i);
				foundF.add(curr);
			}
		}
		
		Collections.sort(toRemove);
		//System.out.println("to remove: "+ toRemove.size());
		while (toRemove.size()>0)
		{
			f.remove((int) toRemove.get(toRemove.size()-1));
			toRemove.remove((int)toRemove.size()-1);
		}
		//System.out.println("found food: " + foundF.size());
		return foundF;		
	} 
	
	
	 
	public void update()
	{
		addF(F_ADD);
	}
	public void removeF(int i) 
	{
		f.remove(i);
	}
}

