/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package practica;

import java.util.InputMismatchException;
import java.io.IOException;
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
                        + " y pulsa ENTER:");
                opcion = sn.nextInt();
                switch (opcion) {
                    case 1: // Crear Proceso
                        crearProceso();
                        break;
                    case 2: // Estado actual del sistema
                        estadoActual();
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
                teclaParaContinuar(); // Para no desplegar el menu inmediatamente
            } catch (InputMismatchException e) {
                System.out.println("Debes insertar un número");
                sn.next();
            }
        }
    }
    // DECLARACION Y ESTRUCTURACION DE FUNCIONES
    private static void teclaParaContinuar(){ //func para dar intervalo entre menus
        System.out.println("Presiona ENTER para continuar...");
        try{
            System.in.read();
            
        }
        catch(IOException e){}
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
        if (memoria.colaProcesos.size() > 0) {     // LISTA DE PROCESOS EN COLA
            System.out.println("Los procesos en cola son ");
            System.out.println("PID     NOMBRE          INSTRUCCIONES     ");
            for (int i = 0; i < memoria.colaProcesos.size(); i++) {
                System.out.println(memoria.colaProcesos.get(i).id + "\t"
                        + memoria.colaProcesos.get(i).nombre + "\t\t"
                        + memoria.colaProcesos.get(i).instrucciones);
            }
        } else {
            System.out.println("LISTA DE PROCESOS EN COLA VACIA.");
        }
        
        
        if(memoria.finalizados.size() > 0){     // LISTA DE PROCESOS FINALIZADOS
            System.out.println("Los procesos finalizados exitosamente son ");
            System.out.println("PID     NOMBRE          INSTRUCCIONES     ");
            for (int i = 0; i < memoria.finalizados.size(); i++) {
                System.out.println(memoria.finalizados.get(i).id + "\t"
                                   + memoria.finalizados.get(i).nombre + "\t\t"
                                   + memoria.finalizados.get(i).instrucciones);
            }    
        }else{
            System.out.println("LISTA DE PROCESOS FINALIZADOS VACIA.");
        }

        if(memoria.eliminados.size() > 0){      // LISTA DE PROCESOS ELIMINADOS
            System.out.println("Los procesos eliminados son ");
            System.out.println("PID     NOMBRE          INSTRUCCIONES     ");
            for (int i = 0; i < memoria.eliminados.size(); i++) {
                System.out.println(memoria.eliminados.get(i).id + "\t"
                                   + memoria.eliminados.get(i).nombre + "\t\t"
                                   + memoria.eliminados.get(i).instrucciones);
            }
        }else{
            System.out.println("LISTA DE PROCESOS ELIMINADOS VACIA");
        }
        
        if (memoria.colaProcesos.isEmpty()) {    // LISTA DE LOCALIDADES DE MEM
            System.out.println("NO HAY PROCESOS EN MEMORIA.");
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
        // Total de localidades faltantes
        System.out.println("ESPACIO DE MEMORIA DISPONIBLE: " + 
                memoria.getLocalidades() + " LOCALIDADES");
    }
 
    private static void mostrarProcesos(){
        if(memoria.colaProcesos.size() > 0){
            System.out.println("PID     NOMBRE          INSTRUCCIONES     ");
            for (int i = 0; i < memoria.colaProcesos.size(); i++) {
                System.out.println(memoria.colaProcesos.get(i).id + "\t"
                        + memoria.colaProcesos.get(i).nombre + "\t\t"
                        + memoria.colaProcesos.get(i).instrucciones);
            }
            System.out.println("PROCESOS TOTALES: "
                    + memoria.colaProcesos.size());
        }else{
            System.out.println("NO HAY PROCESOS EN COLA ACTUALMENTE.");
        }

    }
    
    private static void procesoActual(){
        if(memoria.colaProcesos.isEmpty()){
            System.out.println("NO HAY PROCESOS EN COLA, CREE UNO PRIMERO.");
        }else{
            System.out.println("Nombre " + memoria.colaProcesos.getFirst().nombre);
            System.out.println("ID " + memoria.colaProcesos.getFirst().id);
            System.out.println("Intrucciones totales " + memoria.colaProcesos.getFirst().instrucciones);
            System.out.println("Instrucciones ejecutadas " + memoria.colaProcesos.getFirst().instruccionesEjecutadas);
            System.out.println("Direcciones de memoria " + memoria.colaProcesos.getFirst().espacio);
        }
    }
    
    private static void ejecutarProceso(){
        if(memoria.colaProcesos.isEmpty()){
            System.out.println("COLA DE PROCESOS VACIA, REQUIERE AL MENOS UN (1) PROCESO CREADO.");
        }else{
            System.out.println("Proceso: " + memoria.colaProcesos.getFirst().nombre);
            memoria.colaProcesos.getFirst().instrucciones = memoria.colaProcesos.getFirst().instrucciones - 5;
            memoria.colaProcesos.getFirst().instruccionesEjecutadas += 5;
            if (memoria.colaProcesos.getFirst().instrucciones <= 0) {            // Si ya no tiene instrucciones
                memoria.finalizados.addFirst(memoria.colaProcesos.getFirst());  // Agrega el elemento a lista finalizados
                System.out.println("PROCESO: " + memoria.colaProcesos.getFirst().nombre + " FINALIZADO");
                System.out.println("LIBERADAS: " + memoria.colaProcesos.getFirst().espacio + " LOCALIDADES");
                memoria.colaProcesos.remove();                                  // Quita el primer elemento   
            } else {
                memoria.colaProcesos.add(memoria.colaProcesos.getFirst());      // Copia el 1st al final de la LinkedList
                memoria.colaProcesos.removeFirst();                             // Saca el primer elemento de la LinkedList
            }
        }
    }
    
    private static void siguienteProceso(){
        if(memoria.colaProcesos.isEmpty()){
            System.out.println("COLA DE PROCESOS VACIA, REQUIERE AL MENOS UN (1) PROCESO CREADO.");
        }else{
            memoria.colaProcesos.add(memoria.colaProcesos.getFirst());
            memoria.colaProcesos.removeFirst();
        }
    }
    
    private static void matarProceso(){
        if(memoria.colaProcesos.isEmpty()){
            System.out.println("COLA DE PROCESOS VACIA, REQUIERE AL MENOS UN (1) PROCESO CREADO.");
        }else{
            memoria.setLocalidades(memoria.getLocalidades() + memoria.colaProcesos.getFirst().espacio);
            memoria.eliminados.add(memoria.colaProcesos.getFirst());
            System.out.println("La instrucciones pendientes son " + memoria.colaProcesos.getFirst().instrucciones);
            memoria.colaProcesos.remove();
        }
    }
}
