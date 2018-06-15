package worker;

import javax.swing.*;
import java.awt.*;

public class Panel extends JFrame {
    protected JLabel Conexion;
    protected JLabel CLIP;
    protected JLabel CLPuerto;
    
    protected JButton BtnDesconectar;
    protected JButton BtnConectar;
    protected JTextField IP;
    protected JTextField Puerto;
    
    protected JLabel MSJ;
    
    protected JLabel Servidor;
    protected JLabel SLIP;
    protected JLabel SLPuerto;
    protected JButton SBtnApagar;
    protected JButton SBtnEncender;
    
    protected JTextField STIP;
    protected JTextField STPuerto;
    
    protected JLabel SMSJ;
    
    GridBagConstraints cons = new GridBagConstraints();

    public Panel() {
        setBounds(100, 100, 400, 400);//Definir las dimensiones de la ventana
        setTitle("Conexion");    //Barra de título
        setDefaultCloseOperation(EXIT_ON_CLOSE);    //Acción al pulsar salir
        getContentPane().setLayout(new GridBagLayout());
        setForeground(Color.orange);
        Conexion = new JLabel("Conexion:");
        Conexion.setForeground(Color.green);
        Conexion.setBackground(Color.black);
        Conexion.setOpaque(true);
        Conexion.setHorizontalAlignment(SwingConstants.LEFT);
        Conexion.setVerticalAlignment(SwingConstants.CENTER);
        cons.gridx = 0;
        cons.gridy = 0;
        cons.gridwidth = 2;
        cons.gridheight = 1;
        cons.insets = new Insets(5,10,5, 10);
        cons.anchor = GridBagConstraints.LAST_LINE_END;
        cons.fill = GridBagConstraints.BOTH ;
        cons.weighty = 1.0;
        cons.weightx = 1.0;
        getContentPane().add(Conexion, cons);
        
        
        CLIP = new JLabel("IP");
        cons.gridx = 0;
        cons.gridy = 1;
        cons.gridwidth = 1;
        cons.gridheight = 1;
        getContentPane().add(CLIP, cons);
        
        IP = new JTextField("127.0.0.1");
        cons.gridx = 1;
        cons.gridy = 1;
        cons.gridwidth = 1;
        cons.gridheight = 1;
        getContentPane().add(IP, cons);
        
        CLPuerto = new JLabel("Puerto");
        cons.gridx = 0;
        cons.gridy = 2;
        cons.gridwidth = 1;
        cons.gridheight = 1;
        getContentPane().add(CLPuerto, cons);
        
        Puerto = new JTextField("5432");
        cons.gridx = 1;
        cons.gridy = 2;
        cons.gridwidth = 1;
        cons.gridheight = 1;
        getContentPane().add(Puerto, cons);
        
        
        
        BtnConectar = new JButton("Conectar");
        cons.gridx = 0;
        cons.gridy = 4;
        cons.gridwidth = 1;
        cons.gridheight = 1;
        getContentPane().add(BtnConectar, cons);
        
        
        BtnDesconectar = new JButton("Desconectar");
        cons.gridx = 1;
        cons.gridy = 4;
        cons.gridwidth = 1;
        cons.gridheight = 1;
        getContentPane().add(BtnDesconectar, cons);
        
        MSJ = new JLabel("En espera...");
        MSJ.setForeground(Color.black);
        //MSJ.setPreferredSize(new Dimension( 50,50 ));
        MSJ.setOpaque(true);
        MSJ.setBackground(Color.gray);
        MSJ.setHorizontalAlignment(SwingConstants.CENTER);
        MSJ.setVerticalAlignment(SwingConstants.CENTER);
        cons.gridx = 0;
        cons.gridy = 5;
        cons.gridwidth = 2;
        cons.gridheight = 1;
        getContentPane().add(MSJ, cons);
        
        
        Servidor = new JLabel("Servidor:");
        Servidor.setForeground(Color.blue);
        Servidor.setBackground(Color.white);
        Servidor.setOpaque(true);
        Servidor.setHorizontalAlignment(SwingConstants.LEFT);
        Servidor.setVerticalAlignment(SwingConstants.CENTER);
        cons.gridx = 0;
        cons.gridy = 6;
        cons.gridwidth = 2;
        cons.gridheight = 1;
        getContentPane().add(Servidor, cons);
        
        /*SLIP = new JLabel("IP:");
        cons.gridx = 0;
        cons.gridy = 7;
        cons.gridwidth = 1;
        cons.gridheight = 1;
        getContentPane().add(SLIP, cons);*/
        
        SLPuerto = new JLabel("Puerto:");
        cons.gridx = 0;
        cons.gridy = 8;
        cons.gridwidth = 1;
        cons.gridheight = 1;
        getContentPane().add(SLPuerto, cons);
        
        /*STIP = new JTextField("127.0.0.1");
        cons.gridx = 1;
        cons.gridy = 7;
        cons.gridwidth = 1;
        cons.gridheight = 1;
        getContentPane().add(STIP, cons);*/
        
        STPuerto = new JTextField("5430");
        cons.gridx = 1;
        cons.gridy = 8;
        cons.gridwidth = 1;
        cons.gridheight = 1;
        getContentPane().add(STPuerto, cons);
        
        SBtnEncender = new JButton("Encender");
        cons.gridx = 0;
        cons.gridy = 10;
        cons.gridwidth = 1;
        cons.gridheight = 1;
        getContentPane().add(SBtnEncender, cons);
        
        SBtnApagar = new JButton("Apagar");
        cons.gridx = 1;
        cons.gridy = 10;
        cons.gridwidth = 1;
        cons.gridheight = 1;
        getContentPane().add(SBtnApagar, cons);
        
        SMSJ = new JLabel("En espera....");
        SMSJ.setForeground(Color.black);
        SMSJ.setBackground(Color.white);
        SMSJ.setOpaque(true);
        SMSJ.setHorizontalAlignment(SwingConstants.CENTER);
        SMSJ.setVerticalAlignment(SwingConstants.CENTER);
        cons.gridx = 0;
        cons.gridy = 11;
        cons.gridwidth = 2;
        cons.gridheight = 1;
        getContentPane().add(SMSJ, cons);
        
        setVisible(true);
    }
    
    public void conectaControlador(  Control c  ){
 
        BtnDesconectar.addActionListener(c);
        BtnDesconectar.setActionCommand("Desconectar");
 
        BtnConectar.addActionListener(c);
        BtnConectar.setActionCommand("Conectar");
        
        SBtnEncender.addActionListener(c);
        SBtnEncender.setActionCommand("Encender");
        
        SBtnApagar.addActionListener(c);
        SBtnApagar.setActionCommand("Apagar");
    }
}
