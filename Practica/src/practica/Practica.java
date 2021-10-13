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
    static Proceso proceso = new Proceso();
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
                        break;
                    case 3:
                        System.out.println("Has seleccionado la opcion 3");
                        proceso.mostrarProcesos();
                        break;
                    case 4:
                        System.out.println("Has seleccionado la opcion 4");
                        break;
                    case 5:
                        System.out.println("Has seleccionado la opcion 5");
                        break;
                    case 6:
                        System.out.println("Has seleccionado la opcion 6");
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
        }
    }
    
    private static void crearProceso(){
        Scanner sn = new Scanner(System.in);
        String nombre; // Guardamos nombre del proceso
        
        System.out.println("Has seleccionado la opcion 1");
        System.out.println("Escribe el nombre del proceso");
        nombre = sn.nextLine();
        proceso.crearProceso(nombre);
    }
    
}
