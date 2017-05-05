package nl.aalten.javabuddy.newdomain;

public abstract class Rekening {

    private String rekeningNummer;
    private int saldo;
    private int kredietLimiet;

    public Rekening(String rekeningNummer, int saldo, int kredietLimiet) {
        this.rekeningNummer = rekeningNummer;
        this.saldo = saldo;
        this.kredietLimiet = kredietLimiet;
    }

    public String getRekeningNummer() {
        return rekeningNummer;
    }

    public int getSaldo() {
        return saldo;
    }

    public int getKredietLimiet() {
        return kredietLimiet;
    }

    public void withdraw(int opTeNemenBedrag) {
        boolean kredietLimietOverschreden = saldo - opTeNemenBedrag < - kredietLimiet;
        if (kredietLimietOverschreden) {
//            throw new IllegalArgumentException("Onvoldoende saldo");
            System.out.println("Onvoldoende saldo");
        } else {
            saldo = saldo - opTeNemenBedrag;
        }
    }

    public void deposit(int overTeMakenBedrag) {
        if (overTeMakenBedrag > 0) {
            saldo = saldo + overTeMakenBedrag;
        }
    }
}
