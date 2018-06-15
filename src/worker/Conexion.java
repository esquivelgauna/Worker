package worker;

import java.awt.Color;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.TimerTask;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.hyperic.sigar.SigarException;

public class Conexion extends TimerTask {

    ObjectOutputStream DDatos;
    Info Datos;
    Socket s = null;
    String IP;
    int Puerto;
    protected Panel view;

    Conexion(String IP, int Puerto, Panel view) {
        this.view = view;
        this.IP = IP;
        this.Puerto = Puerto;
    }

    @Override
    public void run() {
        try {
            s = new Socket(IP, Puerto);
            DDatos = new ObjectOutputStream(s.getOutputStream());
            Datos = new Info();
            DDatos.writeObject(Datos.PC());
            Texto("Status Activo", Color.green, Color.black);
            System.out.println("Se enviaron datos");
            
        } catch (IOException | SigarException ex) {
            Texto("No se pudo conectar al servidor ", Color.red, Color.black);
            System.out.println("No se conecto");
            
        } finally {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException ex) {
                Logger.getLogger(Conexion.class.getName()).log(Level.SEVERE, null, ex);
            }
            if (s != null) {
                try {
                    s.close();
                } catch (IOException ex) {
                    Logger.getLogger(Control.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            System.out.println("Conexion cerrada");
            System.out.println("Conectando....");
            Texto("Esperando peticion", Color.orange, Color.black);
        }
    }

    public void Texto(String Msj, Color Fore, Color Back) {
        this.view.SMSJ.setText(Msj);
        this.view.SMSJ.setForeground(Fore);
        this.view.SMSJ.setBackground(Back);
    }

}
