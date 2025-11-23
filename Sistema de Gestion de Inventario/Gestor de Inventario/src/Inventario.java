import java.util.ArrayList;

public class Inventario {

    private ArrayList<ProductoPerecedero> perecederos = new ArrayList<>();
    private ArrayList<ProductoNoPerecedero> noPerecederos = new ArrayList<>();
    private ArrayList<ProductoFragil> fragiles = new ArrayList<>();

    private Billetera billetera;
    private SistemaAlertas alertas;

    private int contadorIds = 1;
    public int interaccionMenu = 0;

    public Inventario(Billetera billetera, SistemaAlertas alertas) {
        this.billetera = billetera;
        this.alertas = alertas;
    }

    public Inventario() {

    }

    public int nuevoId() {
        return contadorIds++;
    }

    public void registrarPerecedero(String nombre, double precio, int min, int max) {
        if (sumaStocks(perecederos) >= 100) {
            alertas.registrar("Stock general perecedero lleno (100)");
            return;
        }

        ProductoPerecedero p = new ProductoPerecedero(
                nuevoId(), nombre, precio, min, max, interaccionMenu
        );

        perecederos.add(p);
        alertas.registrar("Producto perecedero creado: " + nombre);
    }

    public void registrarNoPerecedero(String nombre, double precio, int min, int max) {
        if (sumaStocks(noPerecederos) >= 100) {
            alertas.registrar("Stock general no perecedero lleno (100)");
            return;
        }

        ProductoNoPerecedero p = new ProductoNoPerecedero(
                nuevoId(), nombre, precio, min, max
        );

        noPerecederos.add(p);
        alertas.registrar("Producto no perecedero creado: " + nombre);
    }

    public void registrarFragil(String nombre, double precio, int min, int max, double frag) {
        if (sumaStocks(fragiles) >= 100) {
            alertas.registrar("Stock general de frágiles lleno (100)");
            return;
        }

        ProductoFragil p = new ProductoFragil(
                nuevoId(), nombre, precio, min, max, frag
        );

        fragiles.add(p);
        alertas.registrar("Producto frágil creado: " + nombre);
    }

    private <T extends Producto> int sumaStocks(ArrayList<T> lista) {
        int total = 0;
        for (T p : lista) total += p.stockActual;
        return total;
    }

    public Producto buscarPorId(int id) {
        for (Producto p : perecederos) if (p.getId() == id) return p;
        for (Producto p : noPerecederos) if (p.getId() == id) return p;
        for (Producto p : fragiles)     if (p.getId() == id) return p;
        return null;
    }

    public void vender(int id, int cantidad) {
        Producto p = buscarPorId(id);

        if (p == null) {
            alertas.registrar("No existe producto con ID " + id);
            return;
        }

        if (p instanceof ProductoPerecedero) {
            ProductoPerecedero per = (ProductoPerecedero) p;
            if (per.estaVencido(interaccionMenu)) {
                alertas.registrar("NO se puede vender " + p.getNombre() + ": producto vencido.");
                return;
            }
        }

        if (!p.retirarStock(cantidad)) {
            alertas.registrar("Stock insuficiente para vender " + p.getNombre());
            return;
        }

        double ingreso = p.getPrecioVenta() * cantidad;
        billetera.cargar(ingreso);

        alertas.registrar("Venta completada: " + p.getNombre() + " x" + cantidad);
    }

    public void procesarEliminacionVencidos() {
        ArrayList<ProductoPerecedero> eliminar = new ArrayList<>();

        for (ProductoPerecedero p : perecederos) {
            if (p.debeEliminarse(interaccionMenu)) {
                double costo = p.getPrecioVenta() * p.getStockActual();
                billetera.descontarHastaDisponible(costo);
                eliminar.add(p);

                alertas.registrar(
                        "Producto vencido eliminado: " + p.getNombre() +
                                " (ID " + p.getId() + ") Stock: " + p.getStockActual()
                );
            }
        }

        perecederos.removeAll(eliminar);
    }

    public void mostrarTodo() {
        for (Producto p : perecederos) p.mostrarInfo();
        for (Producto p : noPerecederos) p.mostrarInfo();
        for (Producto p : fragiles) p.mostrarInfo();
    }
}
