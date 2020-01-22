import java.util.ArrayList;

public class Utilities {
	
	static boolean foodSortedX = false;
	static boolean foodSortedY = false;
	
	/*
	 * since food is usually already sorted, sort with insertion. If it's the first time then sort with 
	 * qsort
	 */
	public static void sortFoodX(ArrayList<Double[]> f)
	{
		if (!foodSortedX)
		{
			foodSortedX = true;
			qSortX(f, 0, f.size()-1);
		}
		else
		{
			insertSortX(f);
		}
		foodSortedY = false;
	}
	
	
	public static void sortFoodY(ArrayList<Double[]> f)
	{
		if (!foodSortedY)
		{
			foodSortedY = true;
			qSortY(f, 0, f.size()-1);
		}
		else
		{
			insertSortY(f);
		}
		foodSortedX = false;

	}
	
	
	//Swaps two objects in an arraylist
	public static void swap(ArrayList<Double[]> arr, int a, int b)
	{
		Double[] temp = {arr.get(a)[0], arr.get(a)[1]};
		arr.set(a, arr.get(b));
		arr.set(b, temp);
	}
	
	public static void insertSortX(ArrayList<Double[]> f)
	{

		int n = f.size();
		if (n<=1) return;
		int place = 1;
		while (place < n)
		{
			if (f.get(place-1)[0] > f.get(place)[0])
			{
				int interationPlace = place;
				while (interationPlace > 0 && f.get(interationPlace-1)[0] > f.get(interationPlace)[0])
				{
					Double[] temp = f.get(interationPlace);
					f.set(interationPlace, f.get(interationPlace-1));
					f.set(interationPlace-1, temp);
					interationPlace--;
				}
			}
			else
			{
				place++;
			}
		}
	}
	
	public static void insertSortY(ArrayList<Double[]> f)
	{

		int n = f.size();
		if (n<=1) return;
		int place = 1;
		while (place < n)
		{
			if (f.get(place-1)[1] > f.get(place)[1])
			{
				int interationPlace = place;
				while (interationPlace > 0 && f.get(interationPlace-1)[1] > f.get(interationPlace)[1])
				{
					Double[] temp = f.get(interationPlace);
					f.set(interationPlace, f.get(interationPlace-1));
					f.set(interationPlace-1, temp);
					interationPlace--;
				}
			}
			else
			{
				place++;
			}
		}
	}
	
	public static void qSortX(ArrayList<Double[]> f, int s, int n)
	{
		int size = n - s+1;
		if (size<=1)
		{
			return;
		}
		int m = s;
		double pvx = f.get(s)[0];
		for (int i = s; i <= n; i ++)
		{
			if (f.get(i)[0] < pvx)
			{
				m++;
				swap(f, m , i);
			}
		}
		swap(f, m , s);
		qSortX(f, s, m-1);
		qSortX(f, m+1, n);
	}
	
	
	public static void qSortY(ArrayList<Double[]> f, int s, int n)
	{
		int size = n - s+1;
		if (size<=1)
		{
			return;
		}
		int m = s;
		double pvy = f.get(s)[1];
		for (int i = s; i <= n; i ++)
		{
			if (f.get(i)[1] < pvy)
			{
				m++;
				swap(f, m , i);
			}
		}
		swap(f, m , s);
		qSortY(f, s, m-1);
		qSortY(f, m+1, n);
	}
	
	/*Finds highest index with double value less than the "low" parameter
	* 0 is for x and 1 is for y
	* returns -1 if there is no such bound
	*/
	public static int findLowBound(ArrayList<Double[]> f, int s, int n, double low, int xy)
	{
		int size = n - s+1;
		if (size==1)
		{
			return s;
		}
		if (size==0)
		{
			return -1;
		}
		int mid = (s+n)/2;
		if (f.get(mid)[xy]>low)
		{
			return findLowBound(f, s, mid, low, xy);
		}
		if (f.get(mid)[xy]<low)
		{
			if (mid ==f.size()-1)
			{
				return -1;
			}
			return findLowBound(f, mid+1, n, low, xy);
		}
		if (f.get(mid)[xy]==low)
		{
			if (mid ==0)
			{
				return 0;
			}
			return findLowBound(f, n, mid, low, xy);
		}
		return -1;
	}
	
	
	
	public static void mainTest (String[] args)
	{
		ArrayList<Double[]> list = new ArrayList<Double[]>();
		
		for (int i = 0; i <100; i ++)
		{
			Double[] temp = {0.0, 0.0};
			temp[0]=  Math.random()*100;
			temp[1] = Math.random()*100;
			list.add(temp);
		}
		//sortFoodX(list);
		/*sortFoodX(list);
		for (int i = 0; i < list.size(); i++)
		{
			System.out.println(list.get(i)[0]);
		}*/
		
		System.out.print("\n\n");
		
		sortFoodY(list);
		for (int i = 0; i < list.size(); i++)
		{
			System.out.println(list.get(i)[1]);
		}
	}

}
