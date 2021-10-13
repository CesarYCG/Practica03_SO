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
    int[] arrLocalidades = new int[]{64,128,256,512};
    int random;
    
    Memoria memoria = Memoria.getInstance();
    
    public void crearProceso(String nombre, int id){
        if(memoria.getLocalidades() > this.espacio){
            this.nombre = nombre;
            this.id = id;
            this.instrucciones = (int)(Math.random()*(30-10)) + 10;
            random = (int)(Math.random()*(4-0) + 0);
            this.espacio = arrLocalidades[random];
            memoria.setLocalidades(memoria.getLocalidades() - this.espacio);
        }else{
             System.out.println("ERROR: MEMORIA INSUFICIENTE.");
             System.out.println("Es necesario ejecutar o matar otros proyectos.");
        }
    }
    
    
}
