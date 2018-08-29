package AdivinhadorClient;

import java.io.DataInputStream;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class InputHelper implements Runnable {

    private DataInputStream input;
    private Boolean ligado;

    public InputHelper(DataInputStream input) {
        this.input = input;
        ligado = true;
    }

    public void setLigado(Boolean ligado) {
        this.ligado = ligado;
    }

    public Boolean getLigado() {
        return this.ligado;
    }
    
    @Override
    public void run() {
        while (ligado) {
            try {

                System.out.println("--> ");
                String teste = input.readUTF();
                System.out.println(teste);
                if(teste.contains("SAIRREPLY#OK")){
                    ligado = false;
                }
                
                System.out.println("$ ");
            } catch (IOException ex) {
                Logger.getLogger(InputHelper.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

}
