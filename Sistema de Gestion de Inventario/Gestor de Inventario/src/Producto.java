public class Producto {
    protected int id;
    protected String nombre;
    protected double precioCompra;
    protected double precioVenta;
    protected int stockActual;
    protected int stockMinimo;
    protected int stockMaximo;

    public Producto(int id, String nombre, double precioCompra, int stockMinimo, int stockMaximo) {
        this.id = id;
        this.nombre = nombre;
        this.precioCompra = precioCompra;
        this.stockMinimo = stockMinimo;
        this.stockMaximo = stockMaximo;
        this.stockActual = 0;

        // Precio de venta = +30%
        this.precioVenta = precioCompra * 1.30;
    }

    public int getId() { return id; }
    public String getNombre() { return nombre; }
    public double getPrecioVenta() { return precioVenta; }
    public double getPrecioCompra() { return precioCompra; }
    public int getStockActual() { return stockActual; }

    public boolean agregarStock(int cantidad) {
        if (stockActual + cantidad <= stockMaximo) {
            stockActual += cantidad;
            return true;
        }
        return false;
    }

    public boolean retirarStock(int cantidad) {
        if (stockActual >= cantidad) {
            stockActual -= cantidad;
            return true;
        }
        return false;
    }

    public boolean esCritico() {
        return stockActual <= stockMinimo;
    }

    public void mostrarInfo() {
        System.out.println("ID: " + id);
        System.out.println("Nombre: " + nombre);
        System.out.println("Stock: " + stockActual + "/" + stockMaximo);
        System.out.println("Precio compra: " + precioCompra);
        System.out.println("Precio venta: " + precioVenta);
    }
}
