public class Billetera {
    private double saldo;

    public Billetera() {
        this.saldo = 100; // saldo inicial
    }

    public double getSaldo() {
        return saldo;
    }

    public void cargar(double monto) {
        saldo += monto;
    }

    public double descontarHastaDisponible(double monto) {
        double descontado = Math.min(monto, saldo);
        saldo -= descontado;
        return descontado;
    }
}
