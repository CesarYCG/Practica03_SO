/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package practica;

/**
 *
 * @author fernando
 */
public class Proceso {
    String nombre;
    int id = 0;
    int instrucciones;
    int espacio;
    int instruccionesEjecutadas;
    
    Memoria memoria = Memoria.getInstance();
    
    public void crearProceso(String nombre, int id,int espacio){
        this.espacio = espacio;
        this.nombre = nombre;
        this.id = id;
        this.instrucciones = (int)(Math.random()*(30-10)) + 10;
        this.instruccionesEjecutadas = 0;
        memoria.setLocalidades(memoria.getLocalidades() - this.espacio);
    }
}
