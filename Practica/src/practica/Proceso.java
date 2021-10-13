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
    private int espacio;
    
    Memoria memoria = Memoria.getInstance();
    
    public void crearProceso(String nombre, int id){
        if(memoria.getLocalidades() > this.espacio){
            this.nombre = nombre;
            this.id = id;
            this.instrucciones = (int)(Math.random()*(30-10)) + 10;
            this.espacio = 500;
            memoria.setLocalidades(memoria.getLocalidades() - this.espacio);
        }else{
            System.out.println("No se puede crear otro proceso.");
            System.out.println("Es necesario ejecutar o matar otros proyectos.");
        }
    }
    
    public void estadoActual(){
        System.out.println("El número de procesos en la cola es " + 
                memoria.colaProcesos.size());
        System.out.println("Los procesos finalizados exitosamente son ");
        System.out.println("Los procesos eliminados son ");
        // falta poner el estado de la memoria
        // localidades ocupadas por procesos
    }
    
    public void mostrarProcesos(){
        /*System.out.println(memoria.colaProcesos.getFirst().nombre);
        System.out.println("Las localidades restantes son " + memoria.getLocalidades());
        System.out.println("Las instrucciones restantes son " + memoria.colaProcesos.getFirst().instrucciones);*/
        
        for(int i = 0; i < memoria.colaProcesos.size(); i++){
            System.out.println("ID " + memoria.colaProcesos.get(i).id);
            System.out.println("Nombre " + memoria.colaProcesos.get(i).nombre);
            System.out.println("Intrucciones " + memoria.colaProcesos.get(i).instrucciones);
        }
        
    }
    
    
    
    
}
