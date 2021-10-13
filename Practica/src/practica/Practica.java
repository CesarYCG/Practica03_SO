/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package practica;

import java.util.InputMismatchException;
import java.util.Scanner;

/**
 *
 * @author fernando
 */
public class Practica {  
    static Memoria memoria = Memoria.getInstance();
    static int id = 0;
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        menu();
    }
    
    private static void menu(){
        Scanner sn = new Scanner(System.in);
        boolean salir = false;
        int opcion; //Guardaremos la opcion del usuario
        while (!salir) {
 
            System.out.println("1. Crear Proceso nuevo");
            System.out.println("2. Ver estado actual del sistema");
            System.out.println("3. Imprimir cola de procesos");
            System.out.println("4. Ver proceso actual");
            System.out.println("5. Ejecutar proceso actual");
            System.out.println("6. Pasar al proceso siguiente");
            System.out.println("7. Matar proceso actual");
            System.out.println("8. Salir del programa");
 
            try {
 
                System.out.println("Escribe una de las opciones");
                opcion = sn.nextInt();
 
                switch (opcion) {
                    case 1:
                        crearProceso();
                        break;
                    case 2:
                        System.out.println("Has seleccionado la opcion 2");
                        estadoActual();
                        break;
                    case 3:
                        System.out.println("Has seleccionado la opcion 3");
                        mostrarProcesos();
                        break;
                    case 4:
                        System.out.println("Has seleccionado la opcion 4");
                        procesoActual();
                        break;
                    case 5:
                        System.out.println("Has seleccionado la opcion 5");
                        ejecutarProceso();
                        break;
                    case 6:
                        System.out.println("Has seleccionado la opcion 6");
                        siguienteProceso();
                        break;
                    case 7:
                        System.out.println("Has seleccionado la opcion 7");
                        break;
                    case 8:
                        salir = true;
                        sn.close();
                        break;
                    default:
                        System.out.println("Solo números entre 1 y 4");
                }
            } catch (InputMismatchException e) {
                System.out.println("Debes insertar un número");
                sn.next();
            }
            teclaParaContinuar(); // Para no desplegar el menu inmediatamente
        }
    }
    
    private static void teclaParaContinuar(){ //func para dar intervalo entre menus
        System.out.println("Presiona ENTER para continuar...");
        try{
            System.in.read();
            
        }
        catch(Exception e){}
    }
    
    private static void crearProceso(){
        Proceso proceso = new Proceso();
        Scanner sn = new Scanner(System.in);
        String nombre; // Guardamos nombre del proceso
        id += 1;
        System.out.println("Has seleccionado la opcion 1");
        System.out.println("Escribe el nombre del proceso");
        nombre = sn.nextLine();
        proceso.crearProceso(nombre, id);
        memoria.colaProcesos.add(proceso);
    }
    
    private static void estadoActual(){
        System.out.println("El número de procesos en la cola es " + 
                memoria.colaProcesos.size());
        System.out.println("Los procesos finalizados exitosamente son ");
        System.out.println("Los procesos eliminados son ");
        // falta poner el estado de la memoria
        // localidades ocupadas por procesos
    }  
    
    private static void mostrarProcesos(){
        System.out.println("PID     NOMBRE          INSTRUCCIONES     ");
        for (int i = 0; i < memoria.colaProcesos.size(); i++) {
            System.out.println(memoria.colaProcesos.get(i).id + "\t"
                               + memoria.colaProcesos.get(i).nombre + "\t\t"
                               + memoria.colaProcesos.get(i).instrucciones);
        }
        System.out.println("PROCESOS TOTALES: "
                 + memoria.colaProcesos.size());
    }
    
    private static void procesoActual(){
        System.out.println("Nombre " + memoria.colaProcesos.getFirst().nombre);
        System.out.println("ID " + memoria.colaProcesos.getFirst().id);
        System.out.println("Intrucciones totales " + memoria.colaProcesos.getFirst().instrucciones);
        //Falta instrucciones ejecutadas
        System.out.println("Direcciones de memoria " + memoria.colaProcesos.getFirst().espacio);
    }
    
    private static void ejecutarProceso(){
        System.out.println("Proceso: " + memoria.colaProcesos.getFirst().nombre);
        memoria.colaProcesos.getFirst().instrucciones = memoria.colaProcesos.getFirst().instrucciones - 5;
        if (memoria.colaProcesos.getFirst().instrucciones <= 0){            // Si ya no tiene instrucciones
            memoria.finalizados.addFirst(memoria.colaProcesos.getFirst());  // Agrega el elemento a lista finalizados
            System.out.println("PROCESO: " + memoria.colaProcesos.getFirst().nombre + " FINALIZADO");
            System.out.println("LIBERADAS: " + memoria.colaProcesos.getFirst().espacio + " LOCALIDADES");
            memoria.colaProcesos.remove();                                  // Quita el primer elemento   
        }else{
            memoria.colaProcesos.add(memoria.colaProcesos.getFirst());      // Copia el 1st al final de la LinkedList
            memoria.colaProcesos.removeFirst();                             // Saca el primer elemento de la LinkedList
        }
    
    }
    
    private static void siguienteProceso(){
        memoria.colaProcesos.add(memoria.colaProcesos.getFirst());
        memoria.colaProcesos.removeFirst();
    }
    
}
