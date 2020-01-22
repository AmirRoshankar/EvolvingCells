
public class Main {

	public static void main(String[] args) {
		try
		{
			Presenter.startSim();
		}
		catch (Exception e)
		{
			System.out.println("Something Went Wrong. Exception in Main: " + e);
		}

	}

}
