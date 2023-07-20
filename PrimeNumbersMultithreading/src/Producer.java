import java.util.concurrent.BlockingQueue;

public class Producer implements Runnable
{

	BlockingQueue<Integer> queue;
	int number,buffer,full=0,last,i=0,j=0,cnt=0,divisors;
	long start = System.currentTimeMillis();

    Producer(BlockingQueue<Integer> queue,int n, int b)
    {
        this.queue = queue;
        this.number=n;
        this.buffer=b;
    }
  
    @Override public void run()
    {
        for (i = 1; i <= number; i++) 
        {
            divisors = 0;
            for (j = i; j >= 1; j--) 
            {
                if (i % j == 0)
                {
                  divisors++;
                }
            }
           
            if (divisors == 2) 
            {
            		if(full<number)
            		{
            			try 
            			{
							queue.put(i);
							last = i;
							cnt++;
						} 
            			catch (InterruptedException e)
            			{
							e.printStackTrace();
						}
            		}
            		else
            		{
            			try {
							wait();
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
                    }
            	}
           }
        
       }
}
        
 


