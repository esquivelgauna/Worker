package worker;

import java.io.IOException;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.HashMap;
import org.hyperic.sigar.CpuInfo;
import org.hyperic.sigar.CpuPerc;
import org.hyperic.sigar.Mem;
import org.hyperic.sigar.OperatingSystem;
import org.hyperic.sigar.Sigar;
import org.hyperic.sigar.SigarException;

public class InfoW {
    
    int puerto;
    InfoW( int p ){
        puerto = p;
    }
    
    public HashMap<String,String>  PC() throws IOException, SigarException {
        HashMap<String,String> Datos = new HashMap<>();
        InetAddress ip;
        Sigar sigar = new Sigar();
        Mem memoria = sigar.getMem();
        CpuInfo[] infos = null;
        //CpuPerc[] cpus = null;
        String IPA = null;
        try {
            infos = sigar.getCpuInfoList();
            //cpus = sigar.getCpuPercList();
            ip = InetAddress.getLocalHost();
            IPA = ip.getHostAddress();
            //System.out.println("Current IP address : " + ip.getHostAddress());

        } catch (SigarException e) {
            //e.printStackTrace();
        }
        CpuInfo info = infos[0];
        OperatingSystem sys = OperatingSystem.getInstance();

        Datos.put("Desc",(String) sys.getDescription());
        Datos.put("Nombre",(String) sys.getName());
        Datos.put("CPU",info.getVendor());
        Datos.put("Model",info.getModel());
        Datos.put("MHZ",String.valueOf(info.getMhz()));
        Datos.put("Cores",String.valueOf(info.getTotalCores()));
        Datos.put("CoresU",String.valueOf(CpuPerc.format(sigar.getCpuPerc().getUser())));
        Datos.put("RAMT",String.valueOf((memoria.getTotal()) / 1024 / 1000));
        Datos.put("RAMF",String.valueOf((memoria.getFree()) / 1024 / 1000));
        Datos.put("RAMU",String.valueOf((memoria.getUsed()) / 1024 / 1000));
        Datos.put("IP",(IPA));
        Datos.put("MAC",Mac());
        Datos.put("Tipo","Worker");
        Datos.put("PUERTO", String.valueOf(puerto) );
        Datos.put("Estatus","Libre");
        //System.out.println(Datos);
        return Datos;
    }

    public static String Mac() throws SocketException {
        InetAddress ip;
        String MAC = null;
        try {

            ip = InetAddress.getLocalHost();
            //System.out.println("Current IP address : " + ip.getHostAddress());

            NetworkInterface network = NetworkInterface.getByInetAddress(ip);

            byte[] mac = network.getHardwareAddress();

            System.out.print("Mi MAC address : ");

            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < mac.length; i++) {
                sb.append(String.format("%02X%s", mac[i], (i < mac.length - 1) ? "-" : ""));
            }
            System.out.println(sb.toString());
            MAC = sb.toString();

        } catch (UnknownHostException e) {

            e.printStackTrace();

        }
        return MAC;
    }
}
