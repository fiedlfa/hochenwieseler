package github.hochenwieseler;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
/**
 * Ein Kurzer Berechner für integer Minvalue Werte ;)
 * 
 * @author Fabian Fiedler
 * @version 22.03.2013
 */
public class Integer_MinValue
{
    private CyclicBarrier barrier;
    private int _laenge;
    /**
     * Ein Kurzer Berechner für integer Minvalue Werte ;). Gibt die Werte auf der Konsole aus     
     * Analog zum intStarter.
     * Sinvoll ist eine länge von 7. 
     * dann kommen zwar nur wenige strings raus aber 8 dauert noch ewig ;)
     * @param laenge die Ziellänge des Strings
     */
    public static void main(String[] args)
    {
        new Integer_MinValue().berechneMinValue(8);
    }
    /**
     * Ein Kurzer Berechner für integer Minvalue Werte ;). Gibt die Werte auf der Konsole aus
     * Analog zur Main Methode
     * @param laenge die Ziellänge des Strings
     */
    public static void intStarter(int i)
    {
        new Integer_MinValue().berechneMinValue(i);
    }

    /**
     *  der eigentliche Starter ;)
     *  @param laenge die Ziellänge des strings
     *
     */
    public void berechneMinValue(int laenge)
    {
        long startzeit = System.nanoTime();
        System.out.println("Start ;)");
        
        int anzahl_Treads = 2* 24 + 1; // alle kleinen Buchstaben alle große und der "master" selber
        barrier = new CyclicBarrier(anzahl_Treads+1);
        _laenge = laenge;
        for(char i = 'a'; i <= 'z'; i++)
        {
            Thread t = new Thread(new hashBerechnerRunnable(i,barrier,_laenge));
            t.start();
        }
        for(char i = 'A'; i <= 'Z'; i++)
        {
            Thread t = new Thread(new hashBerechnerRunnable(i,barrier,_laenge));
            t.start();
        }

        try {
            barrier.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (BrokenBarrierException e) {
            e.printStackTrace();
        }
        System.out.println((System.nanoTime() - startzeit)/1000000 + "ms");
    }


}
