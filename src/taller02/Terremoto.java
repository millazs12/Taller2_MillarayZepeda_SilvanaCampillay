package taller02;

public class Terremoto {
    private String consumidor;
    private double litros;
    private String fecha;

    public Terremoto(String consumidor, double litros, String fecha) {
        this.consumidor = consumidor;
        this.litros = litros;
        this.fecha = fecha;
    }

    public String getConsumidor() {
        return consumidor;
    }

    public void setConsumidor(String consumidor) {
        this.consumidor = consumidor;
    }

    public double getLitros() {
        return litros;
    }

    public void setLitros(double litros) {
        this.litros = litros;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public double calcularKcal() {
        double kcal = (litros * 10.0) / 3.0;
        return kcal;
    }

    public String aLineaArchivo() {
        return "Terremoto;" + consumidor + ";" + litros + ";" + fecha;
    }
}