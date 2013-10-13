public class systemClock
{
    public static void main(String[] args)
        throws InterruptedException 
        {
            long timerStart = System.currentTimeMillis();
            Thread.sleep(4000);
            long timerCheck = System.currentTimeMillis() - timerStart;
            double elapsedSec = timerCheck / 1000;
            System.out.println(elapsedSec); 
    }
}
class SystemClock
{
	public void clock()
	{
		
	}
}
//comment