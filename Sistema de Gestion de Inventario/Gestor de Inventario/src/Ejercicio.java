import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Ejercicio {
    private static Scanner scanner = new Scanner(System.in);
    private static Inventario inventario = new Inventario();
    private static SistemaAlertas sistemaAlertas = new SistemaAlertas();

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        Billetera billetera = new Billetera();
        SistemaAlertas alertas = new SistemaAlertas();
        Inventario inv = new Inventario(billetera, alertas);

        int opcion = 0;

        while (opcion != 6) {

            inv.interaccionMenu++;
            inv.procesarEliminacionVencidos();

            alertas.mostrarAlertas();

            System.out.println("\n===============================");
            System.out.println(" SISTEMA DE INVENTARIO");
            System.out.println("===============================");
            System.out.println("Billetera: $" + billetera.getSaldo());
            System.out.println("Interacción actual: " + inv.interaccionMenu);
            System.out.println("1. Registrar producto");
            System.out.println("2. Registrar venta");
            System.out.println("3. Eliminar producto por ID");
            System.out.println("4. Mostrar inventario");
            System.out.println("5. Reabastecer (si aplica)");
            System.out.println("6. Salir");
            System.out.print(">>> ");

            opcion = sc.nextInt();

            switch (opcion) {

                case 1 -> {
                    System.out.println("1. Perecedero");
                    System.out.println("2. No perecedero");
                    System.out.println("3. Frágil");
                    System.out.print(">>> ");
                    int t = sc.nextInt();
                    sc.nextLine();

                    System.out.print("Nombre: ");
                    String nombre = sc.nextLine();

                    System.out.print("Precio de compra al proveedor: ");
                    double compra = sc.nextDouble();

                    System.out.print("Stock mínimo: ");
                    int min = sc.nextInt();

                    System.out.print("Stock máximo: ");
                    int max = sc.nextInt();

                    switch (t) {
                        case 1 -> inv.registrarPerecedero(nombre, compra, min, max);
                        case 2 -> inv.registrarNoPerecedero(nombre, compra, min, max);
                        case 3 -> {
                            System.out.print("Coef. fragilidad: ");
                            double f = sc.nextDouble();
                            inv.registrarFragil(nombre, compra, min, max, f);
                        }
                    }
                }

                case 2 -> {
                    System.out.println("Ingrese ID del producto:");
                    int id = sc.nextInt();

                    System.out.println("Cantidad a vender:");
                    int c = sc.nextInt();

                    inv.vender(id, c);
                }

                case 3 -> {
                    System.out.print("ID a eliminar: ");
                    int id = sc.nextInt();

                    Producto p = inv.buscarPorId(id);
                    if (p != null) {
                        p.retirarStock(p.getStockActual());
                        alertas.registrar("Producto eliminado: " + p.getNombre());
                    }
                }

                case 4 -> inv.mostrarTodo();

                case 5 -> {
                    if (inv.interaccionMenu < 5) {
                        alertas.registrar("Reabastecimiento no disponible aún.");
                    } else {
                        // Aquí iría tu lógica de reabastecimiento
                        alertas.registrar("Reabastecimiento realizado.");
                    }
                }

                case 6 -> System.out.println("Saliendo...");
            }
        }

        sc.close();
    }
}