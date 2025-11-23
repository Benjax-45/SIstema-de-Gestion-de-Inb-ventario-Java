public class ProductoNoPerecedero extends Producto {

    public ProductoNoPerecedero(int id, String nombre, double precioCompra,
                                int stockMinimo, int stockMaximo) {

        super(id, nombre, precioCompra, stockMinimo, stockMaximo);
    }

    @Override
    public void mostrarInfo() {
        super.mostrarInfo();
        System.out.println("Tipo: No Perecedero");
    }
}
