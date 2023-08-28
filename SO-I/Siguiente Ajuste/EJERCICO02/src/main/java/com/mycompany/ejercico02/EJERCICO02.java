/* Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license */
/* Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template */

package com.mycompany.ejercico02;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class EJERCICO02 {

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
        
        List<PARTICION> particiones = new ArrayList<>();

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

            particiones.add(new PARTICION("Particion" + i, tamanoParticion));
            espacioAsignado += tamanoParticion;
        }

        if (particionExcedeMemoria) {
            return;
        }
        
        System.out.print("Ingrese el espacio para Discord: ");
        int espacioDiscord = Integer.parseInt(scanner.nextLine());
        System.out.print("Ingrese el espacio para Spotify: ");
        int espacioSpotify = Integer.parseInt(scanner.nextLine());
        System.out.print("Ingrese el espacio para Roblox: ");
        int espacioRoblox = Integer.parseInt(scanner.nextLine());        
        
        // List<Particion> particiones = new ArrayList<>();
        List<PROCESO> procesos = new ArrayList<>();
        
        // procesos.add(new Proceso("SO", 100));
        procesos.add(new PROCESO("Discord", espacioDiscord));
        procesos.add(new PROCESO("Spotify", espacioSpotify));
        procesos.add(new PROCESO("Roblox", espacioRoblox));

        //boolean sobro = false;
        for (int i = 0; i < procesos.size(); i++) {
            PROCESO proceso = procesos.get(i);
            boolean asignado = false;

            for (int j = BanderaIn; j < particiones.size(); j++) {
                PARTICION particion = particiones.get(j);
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
            if (!asignado) {
                for (int j = 0; j < BanderaIn; j++) {
                    PARTICION particion = particiones.get(j);
                    if (!particion.ocupada && particion.tamano >= proceso.tamanoProceso) {
                        particion.ocupada = true;
                        particion.nombreProceso = proceso.nombreProceso;
                        asignado = true;
                        System.out.println(proceso.nombreProceso + " asignado a la particion  " + particion.nombre);
                        BanderaIn = j;
                        break;
                    }
                }
                if (!asignado) {
                    System.out.println(proceso.nombreProceso + " No fue asignado a la memoria");
                }
            }
        }
            for (PARTICION particion : particiones) {
                if (!particion.ocupada) {
                    espacioSobrante += particion.tamano;
                }
            }
                        
        System.out.println("Espacio sobrante: " + espacioSobrante + "mB");
    }
}
