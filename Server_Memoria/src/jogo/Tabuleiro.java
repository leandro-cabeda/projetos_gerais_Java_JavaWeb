/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jogo;

import java.util.Random;

/**
 *
 * @author elder
 */
public class Tabuleiro {

    int teste;
    int dimx, dimy;
    int xAnterior, yAnterior;
    private int rev = 0;

    private Carta[][] tabuleiro;
    private static Tabuleiro instance;

    public synchronized static Tabuleiro getInstance() {
        if (instance == null) {
            instance = new Tabuleiro(4, 5);
        }
        return instance;
    }

    public void novojogo() {
        instance = new Tabuleiro(4, 5);

    }

    private Tabuleiro(int dimX, int dimY) {
        tabuleiro = new Carta[dimX][dimY];
        dimx = dimX;
        dimy = dimY;
        geraCartasBasico();
        desenhaTabuleiro();

    }

    private void geraCartasBasico() {
        int cont = 0;
        for (int i = 0; i < dimx; i++) {
            for (int j = 0; j < dimy; j++) {

                int num = Math.abs(cont++ % 10);

                tabuleiro[i][j] = new Carta(num);
            }
        }

        for (int i = 0; i < 9; i++) {
            Random rand = new Random();

            int x1 = rand.nextInt(3);
            int x2 = rand.nextInt(3);
            int y2 = rand.nextInt(4);
            int y1 = rand.nextInt(4);

            Carta temp = tabuleiro[x1][y1];
            tabuleiro[x1][y1] = tabuleiro[x2][y2];
            tabuleiro[x2][y2] = temp;
        }

    }

    public StatusJogada jogada(int x, int y, boolean primeira) {
        StatusJogada status;
        try {
            if (rev >= (dimx + dimy)) {
                status = StatusJogada.FIMJOGO;
            } else {
                if (primeira) {
                    if (tabuleiro[x][y].getRevelada()) {
                        //faltou testar se os indices estão dentro da matriz
                        status = StatusJogada.INVALIDA;
                    } else {
                        tabuleiro[x][y].setRevelada(true);
                        xAnterior = x;
                        yAnterior = y;

                        //mandar atualização do tabuleiro para todo mundo;
                        status = StatusJogada.PRIMEIRA;
                    }
                } else {//segunda jogada
                    if (tabuleiro[x][y].revelada) {
                        status = StatusJogada.INVALIDA;
                    } else {
                        tabuleiro[x][y].setRevelada(true);

                        if (tabuleiro[x][y].getValor() == tabuleiro[xAnterior][yAnterior].getValor()) {
                            //se a carta virada for igual a da 1a jogada -->vitoria
                            status = StatusJogada.VITORIA;
                            maisRev();
                        } else {
                            status = StatusJogada.DERROTA;
                            tabuleiro[x][y].setRevelada(false);
                            tabuleiro[xAnterior][yAnterior].setRevelada(false);
                            menosRev();
                        }
                    }
                }
            }
        } catch (Exception e) {
            status = StatusJogada.INVALIDA;
            if (!primeira) {
                tabuleiro[xAnterior][yAnterior].setRevelada(false);
            }
        }
        return status;

    }

    public String desenhaTabuleiro() {
        String s = " ";

        for (int j = 0; j < dimy; j++) {
            s += "  " + j;
        }
        s += '\n';

        for (int i = 0; i < dimx; i++) {
            s += i + " |";
            for (int j = 0; j < dimy; j++) {
                if (tabuleiro[i][j].getRevelada()) {
                    s += tabuleiro[i][j].getValor() + " |";
                } else {
                    s += "* |";
                }
            }
            s += '\n';
        }
        return s;
    }

    int incrementaTeste() {

        return ++teste;
    }

    public static void main(String[] args) {
        Tabuleiro t = new Tabuleiro(5, 4);
        System.out.println("" + t.desenhaTabuleiro());

        if (t.jogada(0, 0, true) != StatusJogada.INVALIDA) {

            System.out.println("" + t.desenhaTabuleiro());
            System.out.println("" + t.jogada(1, 1, false));
            System.out.println("" + t.desenhaTabuleiro());
        } else {
            System.out.println("Jogada Inválida");
        }

    }

    public void maisRev() {
        this.rev += 2;
    }

    public void menosRev() {
        this.rev -= 2;
    }

}
