
import java.util.ArrayList;
import java.util.Scanner;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Leandro
 */
public class Main{

    ArrayList<Thread> threads;
    ArrayList<Funcao> funcao;
    Scanner sc = new Scanner(System.in);

    public Main() {
        threads = new ArrayList<>();
        funcao = new ArrayList<>();
    }

    public void inicia() throws InterruptedException {
        int jobs = 100000; // jobs começa padrao normal de 100000
        int works = 10; // works começa padrão normal de 10
        int chunk = jobs / works; // jobs=100000/works=10 == 10000
        int inicio = 1; // inicio começa valor padrao 1

        for (int i = 0; i < works; i++) {  // works vai ate fechar 10 vezes pelo valor que tem
            int f = (i + 1) * chunk;  // f que é o fim vai pegar a var i que inicia 0 vai somar +1
            // e multiplicar por chunk que a divisão do jobs com works que da 10000
            Funcao fu = new Funcao(i, inicio, f); // i recebe de 0 ate, inicio de 1 ate 10000 depois 10000
            // ate 20000 e assim por adiante
            // f recebe o contador do i +1 multiplicando os 10000 por ves
            funcao.add(fu); // vetor de arraya diciona
            Thread th = new Thread(fu); // cria a thread e passa a função da classe Função
            // por parametro para o disparo da thread
             threads.add(th); // adiciona todas threads dentro da lista
            th.start(); // start a thread
            inicio = f + 1; // inicio recebe o valor inicial + o valor do f multiplicado
            // pelo chunk e somado +1 para começa proxima sequencia de inicio e fim
            // a função vai disparar 10 threads juntas ao mesmo tempo como mais 10 main além do primeiro executando

        }
        //if (!th.isAlive()) { }                 // Verifica se a thread terminou de uma forma não bloqueante, 
                                             //se terminou seu processamento
                                             
               // barreira 
               System.out.println("Esperando threads....");
               for(Thread th2: threads){
                        th2.join();  // join espera finalizar cada thread ate seu fim para iniciar a proxima
               }
               System.out.println("Terminou as threads.");
                for (Funcao fun : funcao) {
                    for (Integer l : fun.getVetor()) {
                        System.out.println("Função " + fun.getId() + "  contou " + l);
                    }
                }
                System.out.println("terminou programa...");
  
            System.exit(0); // termina o programa todo

    }

    public static void main(String[] args) throws InterruptedException {
        Main m = new Main();
        //sc.nextLine();
        m.inicia();

    }

  
}
