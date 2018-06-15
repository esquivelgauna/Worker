package worker;

public class Main  {
    public static void main(String[] args) throws Exception {
        Panel MiPanel = new Panel();
        Control MiServer = new Control(MiPanel);
        MiPanel.conectaControlador(MiServer);
    }
}
