package worker;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Servidor implements Runnable {

    int Puerto;
    Boolean Activo = true;
    ServerSocket Server;
    Socket sock = null;

    Servidor(int Puerto) {
        this.Puerto = Puerto;
        try {
            Server = new ServerSocket(this.Puerto);
        } catch (IOException ex) {
            //Logger.getLogger(Servidor.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void run() {

        while (Activo) {

            System.out.println("Esperando Instruciones");
            try {
                // ServerSocket me da el Socket
                sock = Server.accept();
                // instancio un Thread
                //(new Cliente(s, this.view )).start();
            } catch (Exception ex) {
                //ex.printStackTrace();
            }
        }

    }

    public void close() throws IOException {
        Server.close();
        Activo = false;
        System.out.println("Servidor Apagado");
    }

}
