/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license */
/* Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template */


package com.mycompany.ejercico01;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class EJERCICO01 {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int memoriaTotal = 2000;
        int memoriaSO = 100;

        memoriaTotal = memoriaTotal - memoriaSO;
        System.out.println("Espacio total sobrante: " + memoriaTotal + "mB \n");        
        
        System.out.print("Ingrese la cantidad de particiones: ");
        int nParticiones = Integer.parseInt(scanner.nextLine());
        
        List<Particion> particiones = new ArrayList<>();

       int espacioAsignado = 0;
        boolean particionExcedeMemoria = false;

        for (int i = 1; i <= nParticiones; i++) {
            System.out.print("Ingrese el tamaño para la partición " + i + ": ");
            int tamanoParticion = Integer.parseInt(scanner.nextLine());

            if (espacioAsignado + tamanoParticion > memoriaTotal) {
                System.out.println("Error: El tamaño de las particiones excede la memoria total disponible.");
                particionExcedeMemoria = true;
                break;
            }

            particiones.add(new Particion("Particion" + i, tamanoParticion));
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
        int espacioRoblox = Integer.parseInt(scanner.nextLine()+1);        
        
        // List<Particion> particiones = new ArrayList<>();
        List<Proceso> procesos = new ArrayList<>();

        /*
        for (int i = 1; i <= nParticiones; i++) {
            particiones.add(new Particion("Particion" + i, memoriaParticion));
        }
        */
        
        // 3procesos.add(new Proceso("SO", 100));
        procesos.add(new Proceso("Discord", espacioDiscord));
        procesos.add(new Proceso("Spotify", espacioSpotify));
        procesos.add(new Proceso("Roblox", espacioRoblox));

        for (int i = 0; i < procesos.size(); i++) {
            Proceso proceso = procesos.get(i);
            boolean asignado = false;

            for (int j = 0; j < particiones.size(); j++) {
                Particion particion = particiones.get(j);

                if (!particion.ocupada && particion.tamano >= proceso.tamanoProceso) {
                    particion.ocupada = true;
                    particion.nombreProceso = proceso.nombreProceso;
                    asignado = true;
                    System.out.println(proceso.nombreProceso + " asignado a la particion  " + particion.nombre);
                    break;
                }
            }
            if (!asignado) {
                System.out.println(proceso.nombreProceso + " No fue asignado a la memoria");
            }
        }
        int espacioSobrante = 0;
        for (Particion particion : particiones) {
            if (!particion.ocupada) {
            espacioSobrante += particion.tamano;
            }
        }
        System.out.println("Espacio sobrante: " + espacioSobrante + "mB");
    }
}