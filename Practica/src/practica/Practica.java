/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package practica;

import java.util.InputMismatchException;
import java.util.Scanner;

/**
 *
 * @author fernando & cesar
 */
public class Practica {  
    static Memoria memoria = Memoria.getInstance();
    static int id = 1;
    static int[] arrLocalidades = new int[]{64,128,256,512};
    
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
 
                System.out.println("Ingresa el numero de la opcion requerida"
                        + "y pulsa enter");
                opcion = sn.nextInt();
                switch (opcion) {
                    case 1: // Crear Proceso
                        crearProceso();
                        break;
                    case 2: // Estado actual del sistema
                        estadoActual();
                        System.out.println("Espacio de memoria " + memoria.getLocalidades());
                        break;
                    case 3: // Imprimir cola de procesos
                        mostrarProcesos();
                        break;
                    case 4: // Ver proceso actual
                        procesoActual();
                        break;
                    case 5: // Ejecutar proceso actual
                        ejecutarProceso();
                        break;
                    case 6: // Pasar al proceso siguiente
                        siguienteProceso();
                        break;
                    case 7: // Matar proceso actual
                        matarProceso();
                        break;
                    case 8: // Salir del programa
                        salir = true;
                        mostrarProcesos();
                        System.out.println("Fin del Programa...");
                        sn.close();
                        break;
                    default:
                        System.out.println("Solo números entre 1 y 8");
                }
            } catch (InputMismatchException e) {
                System.out.println("Debes insertar un número");
                sn.next();
            }
            //teclaParaContinuar(); // Para no desplegar el menu inmediatamente
        }
    }
    // DECLARACION Y ESTRUCTURACION DE FUNCIONES
    private static void teclaParaContinuar(){ //func para dar intervalo entre menus
        System.out.println("Presiona ENTER para continuar...");
        try{
            System.in.read();
            
        }
        catch(Exception e){}
    }
    
    private static void crearProceso(){
        int random = (int)(Math.random()*4);
        int espacio = arrLocalidades[random];        
        if (memoria.getLocalidades() >= espacio){
            Proceso proceso = new Proceso();
            Scanner sn = new Scanner(System.in);
            String nombre; // Guardamos nombre del proceso
            System.out.println("Has seleccionado la opcion 1");
            System.out.println("Escribe el nombre del proceso");
            nombre = sn.nextLine();
            proceso.crearProceso(nombre, id,espacio);
            System.out.println("Espacio Ocupado: " + proceso.espacio);
            id += 1;
            memoria.colaProcesos.add(proceso);
        }else{
            System.out.println("ERROR: MEMORIA INSUFICIENTE.");
            System.out.println("Es necesario ejecutar o matar otros proyectos.");
        }
        
    }
    
    private static void estadoActual(){
        System.out.println("El número de procesos en la cola es " + 
                memoria.colaProcesos.size());
        if(memoria.finalizados.size() > 0){
            System.out.println("Los procesos finalizados exitosamente son ");
            System.out.println("PID     NOMBRE          INSTRUCCIONES     ");
            for (int i = 0; i < memoria.finalizados.size(); i++) {
                System.out.println(memoria.finalizados.get(i).id + "\t"
                                   + memoria.finalizados.get(i).nombre + "\t\t"
                                   + memoria.finalizados.get(i).instrucciones);
            }    
        }else{
            System.out.println("No hay procesos finalizados");
        }

        if(memoria.eliminados.size() > 0){
            System.out.println("Los procesos eliminados son ");
            System.out.println("PID     NOMBRE          INSTRUCCIONES     ");
            for (int i = 0; i < memoria.eliminados.size(); i++) {
                System.out.println(memoria.eliminados.get(i).id + "\t"
                                   + memoria.eliminados.get(i).nombre + "\t\t"
                                   + memoria.eliminados.get(i).instrucciones);
            }
        }else{
            System.out.println("No hay procesos eliminados");
        }

        if (memoria.colaProcesos.isEmpty()){    // LISTA PROCESOS
            System.out.println("LISTA DE PROCESOS EN COLA VACIA. ");
        }else{
            System.out.println("El número de procesos en la cola es "
                    + memoria.colaProcesos.size());
        }  
        
        if(memoria.finalizados.isEmpty()){      // LISTA FINALIZADOS
            System.out.println("LISTA DE PROCESOS FINALIZADOS VACIA. ");
        }else{
            System.out.println("Los procesos finalizados exitosamente son: "); 
            System.out.println("PID     NOMBRE   ");
            for (int i = 0; i < memoria.finalizados.size(); i++) {
                System.out.println(memoria.finalizados.get(i).id + "\t"
                        + memoria.finalizados.get(i).nombre);
            } 
        }
        
        if (memoria.eliminados.isEmpty()){      // LISTA ELIMINADOS
            System.out.println("LISTA DE PROCESOS ELIMINADOS VACIA. ");
        }else{
            System.out.println("Los procesos eliminados son: "); // Lista eliminados
            System.out.println("PID     NOMBRE   ");
            for (int i = 0; i < memoria.eliminados.size(); i++) {
                System.out.println(memoria.eliminados.get(i).id + "\t"
                        + memoria.eliminados.get(i).nombre);
            }
        }

        if (memoria.colaProcesos.isEmpty()) {    // LISTA PROCESOS
            System.out.println("LISTA DE PROCESOS ACTIVOS VACIA");
        } else {
            int aux;
            int sum = 0;
            System.out.println("LOCALIDAD \t PROCESO");
            for (int i = 0; i < memoria.colaProcesos.size(); i++){
                aux = memoria.colaProcesos.get(i).espacio;
                
                System.out.println("[" + (sum+1) + "-" + 
                        (sum + memoria.colaProcesos.get(i).espacio)+ "]\t" +
                        memoria.colaProcesos.get(i).nombre);
                                      
                sum += aux;
            }
        }
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
        System.out.println("Instrucciones ejecutadas " + memoria.colaProcesos.getFirst().instruccionesEjecutadas);
        System.out.println("Direcciones de memoria " + memoria.colaProcesos.getFirst().espacio);
    }
    
    private static void ejecutarProceso(){
        System.out.println("Proceso: " + memoria.colaProcesos.getFirst().nombre);
        memoria.colaProcesos.getFirst().instrucciones = memoria.colaProcesos.getFirst().instrucciones - 5;
        memoria.colaProcesos.getFirst().instruccionesEjecutadas += 5;
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
    
    private static void matarProceso(){
        memoria.setLocalidades(memoria.getLocalidades() + memoria.colaProcesos.getFirst().espacio);
        memoria.eliminados.add(memoria.colaProcesos.getFirst());
        System.out.println("La instrucciones pendientes son " + memoria.colaProcesos.getFirst().instrucciones);
        memoria.colaProcesos.remove();
    }
}
