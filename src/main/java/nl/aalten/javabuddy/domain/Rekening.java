package nl.aalten.javabuddy.domain;

public class Rekening {

    public String bsnNummer;
    private String rekeningNummer;
    private String rekening_type;
    private int saldo;
    private int kredietLimiet;

    public Rekening(String bsnNummer, String rekeningNummer, String rekening_type, int saldo, int kredietLimiet) {
        this.bsnNummer = bsnNummer;
        this.rekeningNummer = rekeningNummer;
        this.rekening_type = rekening_type;
        this.saldo = saldo;
        this.kredietLimiet = kredietLimiet;
    }

    public String getRekening_type() {
        return rekening_type;
    }

    public int getSaldo_type() {
        return saldo;
    }

    public String getRekeningNummer() {
        return rekeningNummer;
    }

    public void transfer(String rekeningFrom, String rekeingTo, int bedrag) {
        System.out.println(rekeningFrom + " " + rekeingTo + " " + bedrag);
    }

    public void stortGeld(int bedrag) {
        this.saldo = this.saldo + bedrag;
        System.out.println("Op rekening" + this.rekeningNummer + "is gestort: " + bedrag);
        System.out.println("Huidig saldo van deze rekening is: " + this.saldo);
    }

    public void geldOpnemen(int bedrag) {
        this.saldo = this.saldo - bedrag;
        System.out.println("Op rekening" + this.rekeningNummer + "is afgehaald: " + bedrag);
        System.out.println("Huidig saldo van deze rekening is: " + this.saldo);
    }

}
