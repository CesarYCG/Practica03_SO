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

            id += 1;
            //this.id = id;
            this.instrucciones = (int)(Math.random()*(30-10)) + 10;
            this.espacio = 500;
            //memoria.colaProcesos.add(this);

            memoria.setLocalidades(memoria.getLocalidades() - this.espacio);
            
        }else{
            System.out.println("ERROR: MEMORIA INSUFICIENTE.");
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
        
        System.out.println("PID     NOMBRE               INSTRUCCIONES     ");
        for (int i = 0; i < memoria.colaProcesos.size(); i++) {
            System.out.println(memoria.colaProcesos.get(i).id + "       "
                    + memoria.colaProcesos.get(i).nombre + "              "
                    + memoria.colaProcesos.get(i).instrucciones);
        }
        System.out.println("El número total de procesos en ela cola es " + 
                memoria.colaProcesos.size());
    }    
}
