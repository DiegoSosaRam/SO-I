/* Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license */
/* Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template */

package com.mycompany.ejercico01;


public class Particion{
    String nombre;
    int tamano;

    public Particion(String nombre, int tamano){ //constructor para inicializar
        this.nombre= nombre;
        this.tamano= tamano;
    }

    int id;
    int tamanoProceso;
    boolean ocupada;
    String nombreProceso;

    public Particion(int id, int tamano){
        this.id = id;
        this.tamanoProceso = tamano;
        this.ocupada = false;
        this.nombreProceso = "";

    }
}