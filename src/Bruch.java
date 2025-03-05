public class Bruch {
    private int zaehler, nenner;

    public Bruch(int zaehler, int nenner) {
        if (nenner == 0) {
            throw new IllegalArgumentException("Nenner darf nicht 0 sein!");
        }
        this.zaehler = zaehler;
        this.nenner = nenner;
        kuerzen();
    }

    private void kuerzen() {
        int teiler = ggt(zaehler, nenner);
        zaehler /= teiler;
        nenner /= teiler;
    }

    public Bruch add(Bruch other) {
        int kgv = kgv(this.nenner, other.nenner);
        int neuZaehler = this.zaehler * (kgv / this.nenner) + other.zaehler * (kgv / other.nenner);
        return new Bruch(neuZaehler, kgv);
    }

    public Bruch sub(Bruch other) {
        int kgv = kgv(this.nenner, other.nenner);
        int neuZaehler = this.zaehler * (kgv / this.nenner) - other.zaehler * (kgv / other.nenner);
        return new Bruch(neuZaehler, kgv);
    }

    public Bruch mul(Bruch other) {
        return new Bruch(this.zaehler * other.zaehler, this.nenner * other.nenner);
    }

    public Bruch div(Bruch other) {
        if (other.zaehler == 0) {
            throw new IllegalArgumentException("Division durch 0 ist nicht erlaubt!");
        }
        return new Bruch(this.zaehler * other.nenner, this.nenner * other.zaehler);
    }

    private int ggt(int a, int b) {
        while (b != 0) {
            int temp = b;
            b = a % b;
            a = temp;
        }
        return a;
    }

    private int kgv(int a, int b) {
        return Math.abs(a * b) / ggt(a, b);
    }

    @Override
    public String toString() {
        return zaehler + "/" + nenner;
    }
}
