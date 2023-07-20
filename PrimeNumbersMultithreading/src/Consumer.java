import java.io.FileWriter;
import java.io.IOException;
import java.util.concurrent.BlockingQueue;

class Consumer implements Runnable 
{
    BlockingQueue<Integer> queue;
    int removed = -1,number,buffer;
    String fileName;
    long finish;
 
    public Consumer(BlockingQueue<Integer> queue, int n,int b, String str)
    {
        this.queue = queue;
        this.number = b;
        this.buffer = n;
        this.fileName = str;
    }
  
    @Override public void run()
    {
    	FileWriter fileWriter = null;
		try 
		{
			fileWriter = new FileWriter(fileName);
		} 
		catch (IOException e)
		{
			e.printStackTrace();
		}
        while (removed < buffer) 
        {
            try 
            {
                removed = queue.take();
      	      	fileWriter.append('"'+Integer.toString(removed)+'"'+" ");
      	      	fileWriter.flush();
      	        finish = System.currentTimeMillis();
            }
            catch (InterruptedException | IOException e) 
            {
                e.printStackTrace();
            }
            
        }
        
        notify();
        
     } 
    	
}

