package worker;

import java.awt.Color;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.math.BigInteger;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;

public class WorkerW implements Runnable {

    int MiPuerto;
    PanelW MiPanel;
    Boolean Activo = true;
    ServerSocket Server;
    Socket Socket = null;
    ConexionW MiCon;
    ObjectInputStream Datos;

    WorkerW(int Puerto, ConexionW Con, PanelW Panel) {
        MiPuerto = Puerto;
        MiCon = Con;
        MiPanel = Panel;
        try {
            Server = new ServerSocket(MiPuerto);
        } catch (IOException ex) {
            //Logger.getLogger(Servidor.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void run() {

        while (Activo) {

            System.out.println("Esperando Instruciones");
            try {
                // ServerSocket me da el Socket
                Socket = Server.accept();
                MiCon.Pause();
                Datos = new ObjectInputStream(Socket.getInputStream());
                HashMap Lista = (HashMap) Datos.readObject();
                System.out.println(Lista);
                //int numeros = (int) Integer.valueOf((String) Lista.get("Fiboo"));
                
                ST("Trabajando", Color.orange, Color.black);
                
                String cip = (String) Lista.get("IP");
                int cp = Integer.valueOf((String) Lista.get("PUERTO"));
                int n = (int) Integer.valueOf((String) Lista.get("Fiboo"));
                Lista.put("Res", Calcular(n));
                Lista.put("Desde", "Trabajador");
                
                //Enviar los datos
                new EnviarW(cip, cp, Lista).start();

                MiCon.Play();
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

    public BigInteger Calcular(int numeros) {
        BigInteger ini = new BigInteger("0");
        for (int i = 0; i < numeros; i++) {
            //ini.add( new BigInteger( String.valueOf(i) ));
            ini = ini.add(BigInteger.valueOf(i));
            //System.out.println("numero " + ini);
        }
        System.out.println("Algoritmo terminado");
        return ini;
    }

    public void ST(String Msj, Color Fore, Color Back) {
        MiPanel.SMSJ.setText(Msj);
        MiPanel.SMSJ.setForeground(Fore);
        MiPanel.SMSJ.setBackground(Back);
    }

}
