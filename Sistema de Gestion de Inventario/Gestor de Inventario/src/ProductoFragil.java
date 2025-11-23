public class ProductoFragil extends Producto {
    private double coefFragilidad;

    public ProductoFragil(int id, String nombre, double precioCompra,
                          int stockMinimo, int stockMaximo, double coefFragilidad) {

        super(id, nombre, precioCompra, stockMinimo, stockMaximo);
        this.coefFragilidad = coefFragilidad;
    }

    @Override
    public void mostrarInfo() {
        super.mostrarInfo();
        System.out.println("Tipo: Frágil");
        System.out.println("Coeficiente de fragilidad: " + coefFragilidad);
    }
}
