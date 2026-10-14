package taller02;

//Millaray Zepeda - 22.063.994-0 - ICCI
//Silvana Campillay- 21.862.853-2- ICI

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        boolean entrar = true;

        GestorFonda.cargarParticipantes();
        GestorFonda.cargarConsumidos();

        while (entrar) {
            System.out.println();
            System.out.println("============================================");
            System.out.println("            LA FONDA DEL PAPU");
            System.out.println("============================================");
            System.out.println("  Registros cargados: " + GestorFonda.totalRegistros());
            System.out.println("--------------------------------------------");
            System.out.println("  [1] Ver registros");
            System.out.println("  [2] Actualizar registros");
            System.out.println("  [3] Calcular metricas");
            System.out.println("  [4] Salir");
            System.out.println("============================================");
            System.out.print("Seleccione una opcion: ");
 
            String opcion = sc.nextLine();

            if (opcion.equals("")) {
                System.out.println(">> Error: no ingreso ninguna opcion. Intente nuevamente.");
            } else {
                switch (opcion) {
                    case "1":
                        String opVer = "";
                        while (opVer.equals("3") == false) {
                            System.out.println();
                            System.out.println("============ VER REGISTROS =================");
                            System.out.println("  [1] Choripanes");
                            System.out.println("  [2] Terremotos");
                            System.out.println("  [3] Volver al menu principal");
                            System.out.println("============================================");
                            System.out.print("Seleccione una opcion: ");
                            opVer = sc.nextLine();

                            if (opVer.equals("1")) {
                                GestorFonda.verChoripanes();
                            } else {
                                if (opVer.equals("2")) {
                                    GestorFonda.verTerremotos();
                                } else {
                                    if (opVer.equals("3")) {
                                        break;
                                    } else {
                                        System.out.println(">> Error: opcion fuera de rango.");
                                    }
                                }
                            }
                        }
                        break;

                    case "2":
                        String opAct = "";
                        while (opAct.equals("4") == false) {
                            System.out.println();
                            System.out.println("========== ACTUALIZAR REGISTROS ============");
                            System.out.println("  [1] Agregar");
                            System.out.println("  [2] Editar");
                            System.out.println("  [3] Eliminar");
                            System.out.println("  [4] Volver al menu principal");
                            System.out.println("============================================");
                            System.out.print("Seleccione una opcion: ");
                            opAct = sc.nextLine();

                            if (opAct.equals("1")) {
                                GestorFonda.agregarRegistro(sc);
                            } else {
                                if (opAct.equals("2")) {
                                    GestorFonda.editarRegistro(sc);
                                } else {
                                    if (opAct.equals("3")) {
                                        GestorFonda.eliminarRegistro(sc);
                                    } else {
                                        if (opAct.equals("4")) {
                                            break;
                                        } else {
                                            System.out.println(">> Error: opcion fuera de rango.");
                                        }
                                    }
                                }
                            }
                        }
                        break;

                    case "3":
                        String opMet = "";
                        while (opMet.equals("7") == false) {
                            System.out.println();
                            System.out.println("============ CALCULAR METRICAS =============");
                            System.out.println("  [1] Choripanes comidos en total");
                            System.out.println("  [2] Terremotos tomados en total");
                            System.out.println("  [3] Choripanes comidos por usuario");
                            System.out.println("  [4] Terremotos tomados por usuario");
                            System.out.println("  [5] KCal consumidas por usuario");
                            System.out.println("  [6] Dias ebrio por usuario");
                            System.out.println("  [7] Volver al menu principal");
                            System.out.println("============================================");
                            System.out.print("Seleccione una opcion: ");
                            opMet = sc.nextLine();

                            if (opMet.equals("1")) {
                                GestorFonda.calcularTotalChoripanes();
                            } else {
                                if (opMet.equals("2")) {
                                    GestorFonda.calcularTotalTerremotos();
                                } else {
                                    if (opMet.equals("3")) {
                                        GestorFonda.choripanesPorUsuario();
                                    } else {
                                        if (opMet.equals("4")) {
                                            GestorFonda.terremotosPorUsuario();
                                        } else {
                                            if (opMet.equals("5")) {
                                                GestorFonda.kcalPorUsuario();
                                            } else {
                                                if (opMet.equals("6")) {
                                                    GestorFonda.diasEbrioPorUsuario();
                                                } else {
                                                    if (opMet.equals("7")) {
                                                        break;
                                                    } else {
                                                        System.out.println(">> Error: opcion fuera de rango.");
                                                    }
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                        break;

                    case "4":
                        entrar = false;
                        System.out.println();
                        System.out.println(">> Hasta luego...");
                        break;

                    default:
                        System.out.println(">> Error: opcion fuera de rango. Intente nuevamente.");
                        break;
                }
            }
        }
        sc.close();
    }
}