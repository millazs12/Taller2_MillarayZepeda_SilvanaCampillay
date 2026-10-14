package taller02;

public class Choripan {
    private String consumidor;
    private double largo;
    private double ancho;
    private String fecha;

    public Choripan(String consumidor, double largo, double ancho, String fecha) {
        this.consumidor = consumidor;
        this.largo = largo;
        this.ancho = ancho;
        this.fecha = fecha;
    }

    public String getConsumidor() {
        return consumidor;
    }

    public void setConsumidor(String consumidor) {
        this.consumidor = consumidor;
    }

    public double getLargo() {
        return largo;
    }

    public void setLargo(double largo) {
        this.largo = largo;
    }

    public double getAncho() {
        return ancho;
    }

    public void setAncho(double ancho) {
        this.ancho = ancho;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public double calcularKcal() {
        double kcal = (largo * ancho) / 3.0;
        return kcal;
    }

    public String aLineaArchivo() {
        return "Choripan;" + consumidor + ";" + largo + ";" + ancho + ";" + fecha;
    }
}