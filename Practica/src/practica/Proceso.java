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
    private int id;
    private int instrucciones;
    private int espacio;
    
    Memoria memoria = new Memoria();
    
    public void crearProceso(String nombre){
        System.out.println("Las localidades restantes son " + memoria.localidades);
        this.nombre = nombre;
        this.espacio = 64;
        memoria.colaProcesos.add(this);
        memoria.localidades = memoria.localidades - this.espacio;
    }
    
    public void mostrarProcesos(){
        System.out.println(memoria.colaProcesos.getFirst().nombre);
        System.out.println("Las localidades restantes son " + memoria.localidades);
    }
    
    
}
