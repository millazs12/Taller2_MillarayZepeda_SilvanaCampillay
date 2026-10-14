package taller02;

import java.io.File;
import java.io.FileWriter;
import java.io.BufferedWriter;
import java.io.IOException;
import java.util.Scanner;

public class GestorFonda {
    static Participante[] participantes = new Participante[200];
    static int cantParticipantes = 0;

    static Choripan[] choripanes = new Choripan[200];
    static int cantChoripanes = 0;

    static Terremoto[] terremotos = new Terremoto[200];
    static int cantTerremotos = 0;

    public static void cargarParticipantes() {
        try {
            File archivo = new File("Participantes.txt");
            Scanner lector = new Scanner(archivo);
            while (lector.hasNextLine()) {
                String linea = lector.nextLine();
                if (linea.equals("") == false) {
                    if (cantParticipantes < 200) {
                        participantes[cantParticipantes] = new Participante(linea);
                        cantParticipantes++;
                    }
                }
            }
            lector.close();
        } catch (IOException e) {
            System.out.println(">> ADVERTENCIA: no se encontro el archivo \"Participantes.txt\".");
        }
    }

    public static void cargarConsumidos() {
        try {
            File archivo = new File("Consumido.txt");
            Scanner lector = new Scanner(archivo);
            while (lector.hasNextLine()) {
                String linea = lector.nextLine();
                if (linea.equals("") == false) {
                    String[] partes = linea.split(";");
                    if (partes.length >= 5 && partes[0].equalsIgnoreCase("Choripan")) {
                        if (cantChoripanes < 200) {
                            String consumidor = partes[1];
                            double largo = Double.parseDouble(partes[2]);
                            double ancho = Double.parseDouble(partes[3]);
                            String fecha = partes[4];
                            choripanes[cantChoripanes] = new Choripan(consumidor, largo, ancho, fecha);
                            cantChoripanes++;
                        }
                    } else {
                        if (partes.length >= 4 && partes[0].equalsIgnoreCase("Terremoto")) {
                            if (cantTerremotos < 200) {
                                String consumidor = partes[1];
                                double litros = Double.parseDouble(partes[2]);
                                String fecha = partes[3];
                                terremotos[cantTerremotos] = new Terremoto(consumidor, litros, fecha);
                                cantTerremotos++;
                            }
                        }
                    }
                }
            }
            lector.close();
        } catch (IOException e) {
            System.out.println(">> ADVERTENCIA: no se encontro el archivo \"Consumido.txt\".");
            System.out.println("   Se iniciara el programa sin registros previos.");
        }
    }
}