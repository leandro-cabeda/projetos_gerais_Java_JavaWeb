
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;


/**
 *
 * @author Leandro
 */
public class Conta {
    
    private int saldo;
    private static Conta instance;
    
    
    public synchronized static Conta getInstance()
    {
        if(instance == null)
        {
            instance=new Conta();
        }
      return instance;
    }

    private Conta() {
        saldo=0;
    }
    
    
    public synchronized  void deposita(int deposita)
    {
        saldo+=deposita;
        notifyAll();   // acordar todas threads que estão dormindo.
    }
    
    public synchronized  int saca(int saque){
        
        while(saldo <saque)
        {
            try {
                wait();                        // wait suspende o processo e libera o lock || processo dorme e acorda todo mundo
            } catch (InterruptedException ex) {
            }
        }
        saldo -= saque;
         return saque;
        
        /*if( saldo >= saque ){
            saldo -= saque;
            return saque;
        }
        else
        {
            return 0;
        }*/
    }

    public int getSaldo() {
        return saldo;
    }
    
    
    public static void main(String[] args) {
        Conta conta= new Conta();
        ArrayList<Thread> threads = new ArrayList<>();
        
        for(int i=0; i<100; i++)
        {
           threads.add(new Thread(()->{
                for (int j=0; j<1000;j++)
                {
                    conta.deposita(j);
                }
            }));
            
            threads.add(new Thread(()->{
                for (int j=0; j<1000;j++)
                {
                    conta.saca(j);
                }
            }));
        }
        
        for (Thread t:threads)
        {
            t.start();
        }
        
        
        for (Thread th:threads)
        {
            try {
                th.join();
            } catch (InterruptedException ex) {
                
            }
        }
        
        for (Thread tr:threads)
        {
            System.out.println("Nome thread: "+tr.getName()+ "\n"+"ID thread: "+tr.getId()+
                    "\nPrioridade: "+tr.getPriority()+"\n Estado: "+tr.getState()+
                    "\nGrupo: "+tr.getThreadGroup());
        }
        
        System.out.println("Saldo final: "+conta.getSaldo());
        
        
    }
    
    
}
