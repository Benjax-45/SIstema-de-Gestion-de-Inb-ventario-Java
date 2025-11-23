import java.util.ArrayList;

public class SistemaAlertas {
    private ArrayList<Alerta> alertas = new ArrayList<>();

    public void registrar(String msg) {
        alertas.add(new Alerta(msg));
    }

    public void mostrarAlertas() {
        if (alertas.isEmpty()) return;

        System.out.println("\n--- ALERTAS GENERADAS ---");
        for (Alerta a : alertas) a.mostrar();

        alertas.clear();
    }
}
