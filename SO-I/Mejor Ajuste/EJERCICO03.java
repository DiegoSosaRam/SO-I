/* Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license */
/* Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template */

    package com.mycompany.ejercico03;

    import java.util.ArrayList;
    import java.util.List;
    import java.util.Scanner;
    import java.util.Collections;
    import java.util.Comparator;
    


    public class EJERCICO03 {

        public static void main(String[] args) {
            Scanner scanner = new Scanner(System.in);

            int memoriaTotal = 2000;
            int memoriaSO = 100;
            int sobrantEspacio = 0;
            int espacioSobrante = 0;


            memoriaTotal = memoriaTotal - memoriaSO;
            System.out.println("Espacio total sobrante: " + memoriaTotal + "mB \n");        

            System.out.print("Ingrese la cantidad de particiones: ");
            int nParticiones = Integer.parseInt(scanner.nextLine());

            List<particion> particiones = new ArrayList<>();
            System.out.println("\n");

           int espacioAsignado = 0;
            boolean particionExcedeMemoria = false;

            int BanderaIn= 0;
            for (int i = 1; i <= nParticiones; i++) {
                System.out.print("Ingrese el tamaño para la partición " + i + ": ");
                int tamanoParticion = Integer.parseInt(scanner.nextLine());

                if (espacioAsignado + tamanoParticion > memoriaTotal) {
                    System.out.println("Error: El tamaño de las particiones excede la memoria total disponible.");
                    particionExcedeMemoria = true;
                    break;
                }
                particiones.add(new particion("Particion" + i, tamanoParticion));
                espacioAsignado += tamanoParticion;

            }

            if (particionExcedeMemoria) {
                return;
            }

            System.out.println("\n");
            //System.out.println("Ordenadas ascendentementes:");
            for (int i = 0; i < particiones.size() - 1; i++) {
                for (int j = 0; j < particiones.size() - i - 1; j++) {
                    if (particiones.get(j).tamanoProceso > particiones.get(j + 1).tamanoProceso) {
                        particion temp = particiones.get(j);         // Guarda una referencia al elemento en la posición j
                        particiones.set(j, particiones.get(j + 1)); // Reemplaza el elemento en la posición j con el siguiente
                        particiones.set(j + 1, temp);               // Reemplaza el siguiente elemento con el que se guardó en temp
                    }
                }
            }
            Collections.sort(particiones, Comparator.comparingInt(p -> p.tamano)); // Ordenar las particiones por tamaño ascendente

            System.out.println("\n");

            System.out.println("Ordenadas ascendentemente (mejor ajuste):");
            for (int j=0; j<particiones.size(); j++){
                particion particion = particiones.get(j);
                System.out.println(particion.nombre + ": " + particion.tamano + "mB"); //SKDJFBASIDFAÑSIJDHALDKFJ
            }

            System.out.println("\n");

            System.out.print("Ingrese el espacio para Discord: ");
            int espacioDiscord = Integer.parseInt(scanner.nextLine());
            System.out.print("Ingrese el espacio para Spotify: ");
            int espacioSpotify = Integer.parseInt(scanner.nextLine());
            System.out.print("Ingrese el espacio para Roblox: ");
            int espacioRoblox = Integer.parseInt(scanner.nextLine()); 

            System.out.println("\n");
            // List<Particion> particiones = new ArrayList<>();
            List<proceso> procesos = new ArrayList<>();

            // procesos.add(new Proceso("SO", 100));
            procesos.add(new proceso("Discord", espacioDiscord));
            procesos.add(new proceso("Spotify", espacioSpotify));
            procesos.add(new proceso("Roblox", espacioRoblox));
            System.out.println("\n");
            //boolean sobro = false;
            for (int i = 0; i < procesos.size(); i++) {
                proceso proceso = procesos.get(i);
                boolean asignado = false;
                for (int j = BanderaIn; j < particiones.size(); j++) {
                    particion particion = particiones.get(j);
                    boolean sobro = false;
                    if (!particion.ocupada && particion.tamano >= proceso.tamanoProceso) {
                        particion.ocupada = true;
                        particion.nombreProceso = proceso.nombreProceso;
                        asignado = true;
                        System.out.println(proceso.nombreProceso + " asignado a la particion  " + particion.nombre);
                        BanderaIn = j + 1;
                        if (particion.tamano > proceso.tamanoProceso) {
                                sobrantEspacio = particion.tamano - proceso.tamanoProceso;
                                sobro=true;          
                            if (sobro=true) {
                                espacioSobrante = espacioSobrante + sobrantEspacio;
                            }
                        }
                        break;
                    }
                }

                // Aquí vuelve a iniciar la búsqueda desde el principio si no se encontró más espacio al final
                boolean sobro = false;
                if (!asignado) {
                    for (int j = 0; j < BanderaIn; j++) {
                        particion particion = particiones.get(j);

                        if (!particion.ocupada && particion.tamano >= proceso.tamanoProceso) {
                            particion.ocupada = true;
                            particion.nombreProceso = proceso.nombreProceso;
                            asignado = true;
                            System.out.println(proceso.nombreProceso + " asignado a la particion  " + particion.nombre);
                            BanderaIn = j;
                            break;
                        }
                    }
                System.out.println("\n");
                    if (!asignado) {
                        System.out.println(proceso.nombreProceso + " No fue asignado a la memoria");
                    }
                }
            }
            System.out.println("\n");
                for (particion particion : particiones) {
                    if (!particion.ocupada) {
                        espacioSobrante += particion.tamano;
                    }
                }

            System.out.println("Espacio sobrante: " + espacioSobrante + "mB");
        }
    }
