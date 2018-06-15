package worker;

public class Main  {
    public static void main(String[] args) throws Exception {
        PanelW MiPanel = new PanelW();
        ControlW MiServer = new ControlW(MiPanel);
        MiPanel.conectaControlador(MiServer);
    }
}
