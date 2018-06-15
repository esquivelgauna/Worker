package worker;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.HashMap;

public class EnviarW extends Thread {
    String IP;
    int Puerto;
    HashMap Datos;
    
    ObjectOutputStream Sender;
    Socket Socket = null;
    ObjectOutputStream Out;
    
    EnviarW(String IP, int Puerto , HashMap Map ) {
        //this.view = view;
        this.IP = IP;
        this.Puerto = Puerto;
        Datos = Map;
    }
    
    @Override
    public void run() {
        try {
            Socket = new Socket(IP, Puerto);
            Out = new ObjectOutputStream(Socket.getOutputStream());
            Out.writeObject(Datos);
            
            System.out.println("Se enviaron datos");
        } catch (IOException ex) {
            System.out.println("No se conecto");
        } finally {
            if (Socket != null) {
                try {
                    Socket.close();
                    
                } catch (IOException ex) {
                }
            }
            System.out.println("Envio cerrado");
        }
        this.interrupt();
    }

}