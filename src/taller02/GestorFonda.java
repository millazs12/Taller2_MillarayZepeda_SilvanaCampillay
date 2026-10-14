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

    public static boolean guardarConsumidos() {
        try {
            FileWriter fw = new FileWriter("Consumido.txt");
            BufferedWriter escritor = new BufferedWriter(fw);
            for (int i = 0; i < cantChoripanes; i++) {
                escritor.write(choripanes[i].aLineaArchivo());
                escritor.newLine();
            }
            for (int i = 0; i < cantTerremotos; i++) {
                escritor.write(terremotos[i].aLineaArchivo());
                escritor.newLine();
            }
            escritor.close();
            return true;
        } catch (IOException e) {
            System.out.println(">> Error al guardar en Consumido.txt");
            return false;
        }
    }

    public static int totalRegistros() {
        return cantChoripanes + cantTerremotos;
    }

    public static double redondear(double valor) {
        return (double) Math.round(valor * 100.0) / 100.0;
    }

    public static boolean validarFecha(String fecha) {
        if (fecha.length() != 10) {
            return false;
        }
        String[] partes = fecha.split("-");
        if (partes.length != 3) {
            return false;
        }
        try {
            int anio = Integer.parseInt(partes[0]);
            int mes = Integer.parseInt(partes[1]);
            int dia = Integer.parseInt(partes[2]);
            if (anio < 1 || mes < 1 || mes > 12 || dia < 1 || dia > 31) {
                return false;
            }
        } catch (NumberFormatException e) {
            return false;
        }
        return true;
    }

    public static void mostrarParticipantes() {
        for (int i = 0; i < cantParticipantes; i++) {
            System.out.println("  [" + (i + 1) + "] " + participantes[i].getNombre());
        }
    }

    public static void verChoripanes() {
        System.out.println();
        System.out.println("=============== CHORIPANES =================");
        System.out.println("  #   Consumidor    Largo    Ancho    Fecha          KCal");
        for (int i = 0; i < cantChoripanes; i++) {
            Choripan c = choripanes[i];
            System.out.println("  " + (i + 1) + "   " + c.getConsumidor() + "        " + c.getLargo() + " cm    " + c.getAncho() + " cm    " + c.getFecha() + "    " + redondear(c.calcularKcal()));
        }
        System.out.println("--------------------------------------------------");
        System.out.println("  Total: " + cantChoripanes + " choripanes registrados");
        System.out.println("==================================================");
    }

    public static void verTerremotos() {
        System.out.println();
        System.out.println("=============== TERREMOTOS =================");
        System.out.println("  #   Consumidor    Litros   Fecha          KCal");
        double totalLitros = 0;
        for (int i = 0; i < cantTerremotos; i++) {
            Terremoto t = terremotos[i];
            totalLitros = totalLitros + t.getLitros();
            System.out.println("  " + (i + 1) + "   " + t.getConsumidor() + "        " + t.getLitros() + " L    " + t.getFecha() + "    " + redondear(t.calcularKcal()));
        }
        System.out.println("--------------------------------------------------");
        System.out.println("  Total: " + cantTerremotos + " terremotos registrados (" + redondear(totalLitros) + " L)");
        System.out.println("==================================================");
    }

    public static void agregarRegistro(Scanner scanner) {
        boolean check;
        if (totalRegistros() >= 200) {
            System.out.println();
            System.out.println(">> Error: se alcanzo la capacidad maxima de registros (200).");
            return;
        }

        System.out.println();
        System.out.println("Tipo de registro:");
        System.out.println("  [1] Choripan");
        System.out.println("  [2] Terremoto");
        System.out.println("  [3] Cancelar");
        System.out.print("Seleccione una opcion: ");
        String opStr = scanner.nextLine();

        if (opStr.equals("")) {
            System.out.println(">> Error: no ingreso ninguna opcion.");
            return;
        }
        if (opStr.equals("3")) {
            return;
        }
        if (opStr.equals("1") == false && opStr.equals("2") == false) {
            System.out.println(">> Error: opcion invalida.");
            return;
        }

        if (cantParticipantes == 0) {
            System.out.println(">> Error: No hay participantes cargados.");
            return;
        }

        System.out.println();
        System.out.println("Consumidores disponibles:");
        mostrarParticipantes();
        System.out.print("Seleccione el consumidor: ");
        String cIndexStr = scanner.nextLine();
        if (cIndexStr.equals("")) {
            System.out.println(">> Error: no ingreso ninguna opcion.");
            return;
        }
        int cIndex = -1;
        try {
            cIndex = Integer.parseInt(cIndexStr) - 1;
        } catch (NumberFormatException e) {
            System.out.println(">> Error: debe ingresar un numero valido.");
            return;
        }

        if (cIndex < 0 || cIndex >= cantParticipantes) {
            System.out.println(">> Error: consumidor no valido.");
            return;
        }
        String consumidor = participantes[cIndex].getNombre();

        if (opStr.equals("1")) {
            System.out.print("Largo del choripan (cm): ");
            String largoStr = scanner.nextLine();
            if (largoStr.equals("")) {
                System.out.println(">> Error: no ingreso ninguna opcion.");
                return;
            }
            double largo = 0;
            try {
                largo = Double.parseDouble(largoStr);
            } catch (NumberFormatException e) {
                System.out.println(">> Error: debe ingresar un numero valido para el largo.");
                return;
            }
            if (largo <= 0) {
                System.out.println(">> Error: el largo debe ser mayor a 0.");
                return;
            }

            System.out.print("Ancho del choripan (cm): ");
            String anchoStr = scanner.nextLine();
            if (anchoStr.equals("")) {
                System.out.println(">> Error: no ingreso ninguna opcion.");
                return;
            }
            double ancho = 0;
            try {
                ancho = Double.parseDouble(anchoStr);
            } catch (NumberFormatException e) {
                System.out.println(">> Error: debe ingresar un numero valido para el ancho.");
                return;
            }
            if (ancho <= 0) {
                System.out.println(">> Error: el ancho debe ser mayor a 0.");
                return;
            }

            System.out.print("Fecha (YYYY-MM-DD): ");
            String fecha = scanner.nextLine();
            if (fecha.equals("")) {
                System.out.println(">> Error: no ingreso ninguna opcion.");
                return;
            }
            if (validarFecha(fecha) == false) {
                System.out.println(">> Error: formato de fecha invalido. Debe ser YYYY-MM-DD.");
                return;
            }

            choripanes[cantChoripanes] = new Choripan(consumidor, largo, ancho, fecha);
            cantChoripanes++;
            check = guardarConsumidos();
            if (check){
                System.out.println();
                System.out.println(">> Registro agregado correctamente y guardado en Consumido.txt");
            }
            
        } else {
            System.out.print("Litros del terremoto: ");
            String litrosStr = scanner.nextLine();
            if (litrosStr.equals("")) {
                System.out.println(">> Error: no ingreso ninguna opcion.");
                return;
            }
            double litros = 0;
            try {
                litros = Double.parseDouble(litrosStr);
            } catch (NumberFormatException e) {
                System.out.println(">> Error: debe ingresar un numero valido para los litros.");
                return;
            }
            if (litros <= 0) {
                System.out.println(">> Error: los litros deben ser mayores a 0.");
                return;
            }

            System.out.print("Fecha (YYYY-MM-DD): ");
            String fecha = scanner.nextLine();
            if (fecha.equals("")) {
                System.out.println(">> Error: no ingreso ninguna opcion.");
                return;
            }
            if (validarFecha(fecha) == false) {
                System.out.println(">> Error: formato de fecha invalido. Debe ser YYYY-MM-DD.");
                return;
            }

            terremotos[cantTerremotos] = new Terremoto(consumidor, litros, fecha);
            cantTerremotos++;
            check = guardarConsumidos();
            if (check){
                System.out.println();
                System.out.println(">> Registro agregado correctamente y guardado en Consumido.txt");
            }
        }
    }
       public static void editarRegistro(Scanner scanner) {
        boolean check;
        System.out.println();
        System.out.println("Tipo de registro a editar:");
        System.out.println("  [1] Choripan");
        System.out.println("  [2] Terremoto");
        System.out.println("  [3] Cancelar");
        System.out.print("Seleccione una opcion: ");
        String op = scanner.nextLine();
        if (op.equals("")) {
            System.out.println(">> Error: no ingreso ninguna opcion.");
            return;
        }
        if (op.equals("1")) {
            if (cantChoripanes == 0) {
                System.out.println(">> No hay choripanes registrados.");
                return;
            }
            verChoripanes();
            System.out.print("Seleccione el registro a editar (0 para cancelar): ");
            String idxStr = scanner.nextLine();
            if (idxStr.equals("")) {
                System.out.println(">> Error: no ingreso ninguna opcion.");
                return;
            }
            int idx = -1;
            try {
                idx = Integer.parseInt(idxStr);
            } catch (NumberFormatException e) {
                System.out.println(">> Error: debe ingresar un numero valido.");
                return;
            }
            if (idx <= 0) {
                return;
            }
            if (idx > cantChoripanes) {
                System.out.println(">> Error: no existe un registro con ese numero. Intente nuevamente.");
                return;
            }
            Choripan c = choripanes[idx - 1];
            System.out.println("Registro actual -> Choripan | " + c.getConsumidor() + " | " + c.getLargo() + " cm | " + c.getAncho() + " cm | " + c.getFecha());
            System.out.println("Deje el campo vacio para mantener el valor actual.");
            System.out.print("Consumidor [" + c.getConsumidor() + "]: ");
            String cons = scanner.nextLine();
            if (cons.equals("") == false) {
                c.setConsumidor(cons);
            }
            System.out.print("Largo [" + c.getLargo() + "]: ");
            String lStr = scanner.nextLine();
            if (lStr.equals("") == false) {
                c.setLargo(Double.parseDouble(lStr));
            }
            System.out.print("Ancho [" + c.getAncho() + "]: ");
            String aStr = scanner.nextLine();
            if (aStr.equals("") == false) {
                c.setAncho(Double.parseDouble(aStr));
            }
            System.out.print("Fecha [" + c.getFecha() + "]: ");
            String f = scanner.nextLine();
            if (f.equals("") == false) {
                if (validarFecha(f) == false) {
                    System.out.println(">> Error: formato de fecha invalido. No se actualizo la fecha.");
                } else {
                    c.setFecha(f);
                }
            }
            check = guardarConsumidos();
            if (check){
                System.out.println(">> Registro actualizado correctamente y guardado en Consumido.txt");
            }
        } else {
            if (op.equals("2")) {
                if (cantTerremotos == 0) {
                    System.out.println(">> No hay terremotos registrados.");
                    return;
                }
                verTerremotos();
                System.out.print("Seleccione el registro a editar (0 para cancelar): ");
                String idxStr = scanner.nextLine();
                if (idxStr.equals("")) {
                    System.out.println(">> Error: no ingreso ninguna opcion.");
                    return;
                }
                int idx = -1;
                try {
                    idx = Integer.parseInt(idxStr);
                } catch (NumberFormatException e) {
                    System.out.println(">> Error: debe ingresar un numero valido.");
                    return;
                }
                if (idx <= 0) {
                    return;
                }
                if (idx > cantTerremotos) {
                    System.out.println(">> Error: no existe un registro con ese numero. Intente nuevamente.");
                    return;
                }
                Terremoto t = terremotos[idx - 1];
                System.out.println("Registro actual -> Terremoto | " + t.getConsumidor() + " | " + t.getLitros() + " L | " + t.getFecha());
                System.out.println("Deje el campo vacio para mantener el valor actual.");
                System.out.print("Consumidor [" + t.getConsumidor() + "]: ");
                String cons = scanner.nextLine();
                if (cons.equals("") == false) {
                    t.setConsumidor(cons);
                }
                System.out.print("Litros [" + t.getLitros() + "]: ");
                String litStr = scanner.nextLine();
                if (litStr.equals("") == false) {
                    t.setLitros(Double.parseDouble(litStr));
                }
                System.out.print("Fecha [" + t.getFecha() + "]: ");
                String f = scanner.nextLine();
                if (f.equals("") == false) {
                    if (validarFecha(f) == false) {
                        System.out.println(">> Error: formato de fecha invalido. No se actualizo la fecha.");
                    } else {
                        t.setFecha(f);
                    }
                }
                check = guardarConsumidos();
                if(check){
                    System.out.println(">> Registro actualizado correctamente y guardado en Consumido.txt");
                }
            }
        }
    }
    public static void eliminarRegistro(Scanner scanner) {
        boolean check;
        System.out.println();
        System.out.println("Tipo de registro a eliminar:");
        System.out.println("  [1] Choripan");
        System.out.println("  [2] Terremoto");
        System.out.println("  [3] Cancelar");
        System.out.print("Seleccione una opcion: ");
        String op = scanner.nextLine();
        if (op.equals("")) {
            System.out.println(">> Error: no ingreso ninguna opcion.");
            return;
        }
        if (op.equals("1")) {
            if (cantChoripanes == 0) {
                System.out.println(">> No hay choripanes.");
                return;
            }
            verChoripanes();
            System.out.print("Seleccione el registro a eliminar (0 para cancelar): ");
            String idxStr = scanner.nextLine();
            if (idxStr.equals("")) {
                System.out.println(">> Error: no ingreso ninguna opcion.");
                return;
            }
            int idx = -1;
            try {
                idx = Integer.parseInt(idxStr);
            } catch (NumberFormatException e) {
                System.out.println(">> Error: debe ingresar un numero valido.");
                return;
            }
            if (idx <= 0) {
                return;
            }
            if (idx > cantChoripanes) {
                System.out.println(">> Error: no existe un registro con ese numero.");
                return;
            }
            Choripan c = choripanes[idx - 1];
            System.out.print("Confirma la eliminacion de \"Choripan | " + c.getConsumidor() + " | " + c.getLargo() + " cm x " + c.getAncho() + " cm | " + c.getFecha() + "\"? (s/n): ");
            String conf = scanner.nextLine();
            if (conf.equalsIgnoreCase("s")) {
                for (int i = idx - 1; i < cantChoripanes - 1; i++) {
                    choripanes[i] = choripanes[i + 1];
                }
                choripanes[cantChoripanes - 1] = null;
                cantChoripanes--;
                check = guardarConsumidos();
                if (check){
                    System.out.println(">> Registro eliminado correctamente. Cambios guardados en Consumido.txt");
                }
            }
        } else {
            if (op.equals("2")) {
                if (cantTerremotos == 0) {
                    System.out.println(">> No hay terremotos.");
                    return;
                }
                verTerremotos();
                System.out.print("Seleccione el registro a eliminar (0 para cancelar): ");
                String idxStr = scanner.nextLine();
                if (idxStr.equals("")) {
                    System.out.println(">> Error: no ingreso ninguna opcion.");
                    return;
                }
                int idx = -1;
                try {
                    idx = Integer.parseInt(idxStr);
                } catch (NumberFormatException e) {
                    System.out.println(">> Error: debe ingresar un numero valido.");
                    return;
                }
                if (idx <= 0) {
                    return;
                }
                if (idx > cantTerremotos) {
                    System.out.println(">> Error: no existe un registro con ese numero.");
                    return;
                }
                Terremoto t = terremotos[idx - 1];
                System.out.print("Confirma la eliminacion de \"Terremoto | " + t.getConsumidor() + " | " + t.getLitros() + " L | " + t.getFecha() + "\"? (s/n): ");
                String conf = scanner.nextLine();
                if (conf.equalsIgnoreCase("s")) {
                    for (int i = idx - 1; i < cantTerremotos - 1; i++) {
                        terremotos[i] = terremotos[i + 1];
                    }
                    terremotos[cantTerremotos - 1] = null;
                    cantTerremotos--;
                    check = guardarConsumidos();
                    if (check){
                        System.out.println(">> Registro eliminado correctamente. Cambios guardados en Consumido.txt");
                    }
                }
            }
        }
    }
}