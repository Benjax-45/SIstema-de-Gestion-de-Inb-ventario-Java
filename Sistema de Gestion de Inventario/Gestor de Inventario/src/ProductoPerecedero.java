public class ProductoPerecedero extends Producto {
    private int interaccionCreacion;
    private int interaccionVence;
    private int interaccionEliminar;

    public ProductoPerecedero(int id, String nombre, double precioCompra,
                              int stockMinimo, int stockMaximo, int interaccionActual) {

        super(id, nombre, precioCompra, stockMinimo, stockMaximo);

        this.interaccionCreacion = interaccionActual;
        this.interaccionVence = interaccionActual + 7;
        this.interaccionEliminar = interaccionVence + 3;
    }

    public boolean estaVencido(int interaccion) {
        return interaccion >= interaccionVence;
    }

    public boolean debeEliminarse(int interaccion) {
        return interaccion >= interaccionEliminar;
    }

    @Override
    public void mostrarInfo() {
        super.mostrarInfo();
        System.out.println("Tipo: Perecedero");
        System.out.println("Vence en interacción: " + interaccionVence);
    }
}
