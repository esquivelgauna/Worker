package worker;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.Socket;
import java.util.Timer;
import javax.swing.JOptionPane;

public class Control implements ActionListener {

    String IP;
    Boolean Conectado = false;
    Boolean Servidor = false;
    int Puetro;
    Info Datos;
    Timer timer;
    Conexion MiConexion;
    Servidor MiServidor;
    Thread MiHilo;
    protected Panel view;

    Control(Panel view) throws IOException {
        this.view = view;
    }

    public Boolean Test(String IP, int Puerto) throws IOException {
        Socket s = null;
        s = new Socket(IP, Puerto);
        return true;
    }

    @Override
    public void actionPerformed(ActionEvent arg0) {
        //COMANDO EJECTUADO
        String comando = arg0.getActionCommand();

        switch (comando) {
            case "Conectar":
                if (Conectado == false) {
                    Texto("Conectando..", Color.green, Color.black);

                    String IP = this.view.IP.getText();
                    int Puerto = Integer.valueOf(this.view.Puerto.getText());
                    System.out.println("Conectar a la IP:" + IP + " Puerto:" + Puerto);
                    Boolean Con;
                    try {
                        Con = Test(IP, Puerto);
                    } catch (IOException ex) {
                        Con = false;
                    }
                    if (Con) {
                        Texto("Conexion exitosa..", Color.green, Color.black);
                        MiConexion = new Conexion(IP, Puerto, this.view);
                        timer = new Timer();
                        timer.scheduleAtFixedRate(MiConexion, 0, 1000);
                        Conectado = true;
                    } else {
                        Texto("Error al conectar..", Color.red, Color.black);
                    }
                } else {
                    System.out.println("Duplicado evitado");
                    JOptionPane.showMessageDialog(null, "Ya estas conectado");
                }
                break;

            case "Desconectar":
                timer.purge();
                timer.cancel();
                System.out.println("Desconectar");
                Texto("Desconectado", Color.blue, Color.gray);
                Conectado = false;
                break;

            case "Encender":
                //System.out.println("Comando Encender");
                if (Servidor == false) {
                    int SPuerto = Integer.valueOf(this.view.STPuerto.getText());

                    MiServidor = new Servidor(SPuerto);
                    MiHilo = new Thread(MiServidor);
                    MiHilo.start();
                    Servidor = true;
                    ST("Servidor Iniciado", Color.green, Color.black);
                } else {
                    JOptionPane.showMessageDialog(null, "Ya esta inicializado el servidor");
                }

                break;

            case "Apagar":
                //System.out.println("Comando Apagar");

                try {
                    MiServidor.close();
                } catch (IOException ex) {
                    //Logger.getLogger(Control.class.getName()).log(Level.SEVERE, null, ex);
                }
                ST("Servidor Apagado", Color.red, Color.black);
                Servidor = false;
                break;

            default:
                System.err.println("Comando no definido");
                break;
        }
    }

    public void Texto(String Msj, Color Fore, Color Back) {
        this.view.MSJ.setText(Msj);
        this.view.MSJ.setForeground(Fore);
        this.view.MSJ.setBackground(Back);
    }

    public void ST(String Msj, Color Fore, Color Back) {
        this.view.SMSJ.setText(Msj);
        this.view.SMSJ.setForeground(Fore);
        this.view.SMSJ.setBackground(Back);
    }
}
