/* Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license */
/* Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template */

package com.mycompany.ejercico02;


public class PARTICION{
    String nombre;
    int tamano;

    public PARTICION(String nombre, int tamano){ //constructor para inicializar
        this.nombre= nombre;
        this.tamano= tamano;
    }

    int id;
    int tamanoProceso;
    boolean ocupada;
    String nombreProceso;

    public PARTICION(int id, int tamano){
        this.id = id;
        this.tamanoProceso = tamano;
        this.ocupada = false;
        this.nombreProceso = "";

    }
}

