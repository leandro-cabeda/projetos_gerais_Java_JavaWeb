
import java.util.ArrayList;



/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Leandro
 */
class Funcao implements Runnable{

    private Integer id;
    private Integer inic;
    private Integer fim;
    private ArrayList<Integer> vetor;
      
    @Override
    public void run() {
        //ex1();
        ex2();
    }
    
    public Funcao(int i, int inicio, int f)
    {
        id=i;
        inic=inicio;
        fim=f;
        vetor=new ArrayList<>();
    }
    
    public void ex1()
    {
        for(int i=inic;i<=fim;i++)
        {
            System.out.println("Função "+(id+1)+"  contendo "+i);
        }
    }
    
    public void ex2()
    {
        for(int i=inic;i<=fim;i++)
        {
            int cont=0;
            for(int j=1;j<=i;j++)
            {
                if(i%j==0)
                {
                    cont++;
                }
            }
            if(cont==2)
            {
                vetor.add(i);
            }
            //vetor.add(i);
        }
    }
    
    

    public ArrayList<Integer> getVetor() {
        return vetor;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getInic() {
        return inic;
    }

    public void setInic(Integer inic) {
        this.inic = inic;
    }

    public Integer getFim() {
        return fim;
    }

    public void setFim(Integer fim) {
        this.fim = fim;
    }

    public void setVetor(ArrayList<Integer> vetor) {
        this.vetor = vetor;
    }

}
