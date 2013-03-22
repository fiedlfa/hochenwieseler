package github.hochenwieseler;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier; 
/**
 * Eine Runnable um alle Kerne der CPU via Threads mÃ¶glich zu machen
 * 
 */
public class hashBerechnerRunnable implements Runnable
{
    private int _anfangsbuchstabe;
    CyclicBarrier _barrier;
    int _laenge;
    public hashBerechnerRunnable(char anfangsbuchstabe,CyclicBarrier barrier,int laenge)
    {
        _anfangsbuchstabe = anfangsbuchstabe;     
        _barrier = barrier;
        _laenge = laenge;
    }

    public void run()
    {
        rechner(_laenge -1, _anfangsbuchstabe * pow(31,_laenge-1),"" + (char)_anfangsbuchstabe);
        try {
            _barrier.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (BrokenBarrierException e) {
            e.printStackTrace();
        }
    }

    /**
     * der eigentliche Rechner.
     *  laenge ist die restliche laenge des SpÃ¤teren Strings (beginnend mit 1)
     *  hashValue ist der bishergerechnete Wert fÃ¼r den hashcode
     *  value ist der aktuelle string der in berechnung ist.
     */
    private void rechner(int laenge, int hashValue,String value)
    {
        if(laenge > 0)
        {
        	if(laenge < 5)
        	{
        		if(hashValue < 0 || (hashValue > 0 && hashValue + pow (31,laenge) > 0)) // 31 hoch laenge ist größer als die Summe von 31^laenge -1 + 31^laenge-2 ... 
        				{
        			return;
        				}
        	}
        	
            for (int i = 'a'; i < 'z'; i++)
            {
                rechner(laenge -1, hashValue + (i * pow(31,laenge-1)) , value + (char)i );
            }
            rechner(laenge -1, hashValue + (' ' * pow(31,laenge-1)) , value + ' ' );
        }
        else if (hashValue == Integer.MIN_VALUE)
        {
            System.out.println(value);
        }

    }

    /**
     * Berechnet x hoch y
     */    
    private int pow(int x, int y)
    {
        int a = 1;
        for(int i = 0;i < y;i++)
        {
            a *= x;
        }
        return a;
    }
}