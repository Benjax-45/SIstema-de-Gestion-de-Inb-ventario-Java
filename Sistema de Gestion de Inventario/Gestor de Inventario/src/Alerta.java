public class Alerta {
    private String mensaje;

    public Alerta(String mensaje) {
        this.mensaje = mensaje;
    }

    public void mostrar() {
        System.out.println("[ALERTA] " + mensaje);
    }
}
