package worker;

import java.awt.Color;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.TimerTask;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.hyperic.sigar.SigarException;

public class ConexionW extends TimerTask {

    ObjectOutputStream DDatos;
    InfoW Datos;
    Socket s = null;
    String IP;
    int Puerto;
    int MiPuerto;
    protected PanelW view;
    protected Boolean conexion = true;
    Boolean Con = false;

    ConexionW(String IP, int Puerto, PanelW view, int mp) {
        this.view = view;
        this.IP = IP;
        this.Puerto = Puerto;
        MiPuerto = mp;
    }

    @Override
    public void run() {
        if (conexion) {
            Con = true;
            try {
                s = new Socket(IP, Puerto);
                DDatos = new ObjectOutputStream(s.getOutputStream());
                Datos = new InfoW(MiPuerto);
                DDatos.writeObject(Datos.PC());
                Texto("Status Activo", Color.green, Color.black);
                System.out.println("Se enviaron datos");
                Con = true;
            } catch (IOException | SigarException ex) {
                
                System.out.println("No se conecto");
                Con = false;
            } finally {
                if (s != null) {
                    try {
                        s.close();
                    } catch (IOException ex) {
                        Logger.getLogger(ControlW.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                if (Con) {
                    Texto("Esperando peticion", Color.orange, Color.black);
                }else{
                    Texto("No se pudo conectar al servidor ", Color.red, Color.black);
                }
                System.out.println("Conexion cerrada");
                System.out.println("Conectando....");
                
            }
        }

    }

    public void Texto(String Msj, Color Fore, Color Back) {
        this.view.SMSJ.setText(Msj);
        this.view.SMSJ.setForeground(Fore);
        this.view.SMSJ.setBackground(Back);
    }

    public void Pause() {
        conexion = false;
    }

    public void Play() {
        conexion = true;
    }
}
