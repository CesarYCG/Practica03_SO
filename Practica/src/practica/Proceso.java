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
    private String nombre;
    private int id = 0;
    private int instrucciones;
    private int espacio;
    
    Memoria memoria = new Memoria();
    
    public void crearProceso(String nombre){
        if(memoria.getLocalidades() > this.espacio){
            this.nombre = nombre;
            id += 1;
            this.id = id;
            this.instrucciones = (int)(Math.random()*(30-10)) + 10;
            this.espacio = 500;
            memoria.colaProcesos.add(this);
            memoria.setLocalidades(memoria.getLocalidades() - this.espacio);
        }else{
            System.out.println("No se puede crear otro proceso.");
            System.out.println("Es necesario ejecutar o matar otros proyectos.");
        }
    }
    
    public void estadoActual(){
        System.out.println("El número de procesos en ela cola es " + 
                memoria.colaProcesos.);
    }
    
    public void mostrarProcesos(){
        System.out.println(memoria.colaProcesos.getFirst().nombre);
        System.out.println("Las localidades restantes son " + memoria.getLocalidades());
        System.out.println("Las instrucciones restantes son " + memoria.colaProcesos.getFirst().instrucciones);
    }
    
    
    
    
}
