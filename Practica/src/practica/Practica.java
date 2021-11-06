/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package practica;

import java.util.InputMismatchException;
import java.io.IOException;
//import java.util.Collections;
import java.util.Scanner;

/**
 *
 * @author fernando & cesar
 */
public class Practica {  
    static Memoria memoria = Memoria.getInstance();
    static int id = 1;
    static int posicionActual = 0;
    static int index = getIndex();
    static int ultimoProceso = getUltimoProceso();
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
            System.out.println("9. Array de memoria");
            System.out.println("10. Desfragmentacion");
            
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
                    case 8: // Desfragmenta los huecos de linked list
                        desfragmentacion();
                        break;
                    case 9:  // Muestra la LISTA LIGADA CUSTOM de memoria
                        memoria.listaMemoria.show(); 
                        //System.out.println(memoria.listaMemoria.size()); 
                        //System.out.println(memoria.listaMemoria.get(0).getNombre());
                        //System.out.println(getIndex());
                        //System.out.println(getUltimoProceso());
                        break;
                    case 10:  // Salir del programa                        
                        salir = true;
                        mostrarProcesos();
                        System.out.println("Fin del Programa...");
                        sn.close();
                        break;   
                    default: // Si el usuario es gracioso
                        System.out.println("Solo números entre 1 y 10");
                }
                //teclaParaContinuar(); // Para no desplegar el menu inmediatamente
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
        int localidades = 16;
        if (memoria.getLocalidades() >= espacio){
            Proceso proceso = new Proceso();
            Scanner sn = new Scanner(System.in);
            String nombre; // Guardamos nombre del proceso
            System.out.println("Escribe el nombre del proceso");
            nombre = sn.nextLine();
            proceso.crearProceso(nombre, id,espacio);
            proceso.setPaginas(espacio / localidades);
        
            id += 1;
            memoria.colaProcesos.add(proceso);
            
            for(int i = 0; i < proceso.getPaginas();i++){
                
                //eliminarProceso(index);
                //insertarProceso(index,nombre,posicionActual,localidades,index);
                //posicionActual += localidades;
                //index += 1;
                
                memoria.listaMemoria.get(index).setNombre(nombre);
                //for(int j = 0; j < memoria.listaMemoria.size();i++){
                //    if(memoria.listaMemoria.get(j).getNombre().contains("Hueco")){
                //        memoria.listaMemoria.get(j).setNombre(nombre);
                //        break;
                //    }       
                //}
                proceso.insertarTabla(index);
                index = getIndex();
                
                
            }
            
        }else{
            System.out.println("ERROR: MEMORIA INSUFICIENTE.");
            System.out.println("Es necesario ejecutar o matar otros procesos.");
        }
        
    }
    
    private static void estadoActual(){
        System.out.println("El número de procesos en la cola es " + 
                memoria.colaProcesos.size());
        if (!memoria.colaProcesos.isEmpty()) {     // LISTA DE PROCESOS EN COLA
            System.out.println("Los procesos en cola son ");
            System.out.println("PID     NOMBRE          INSTRUCCIONES     ");
            for (int i = 0; i < memoria.colaProcesos.size(); i++) {
                System.out.println(memoria.colaProcesos.get(i).getId() + "\t"
                        + memoria.colaProcesos.get(i).getNombre() + "\t\t"
                        + memoria.colaProcesos.get(i).getInstrucciones());
            }
        } else {
            System.out.println("LISTA DE PROCESOS EN COLA VACIA.");
        }
        
        
        if(!memoria.finalizados.isEmpty()){     // LISTA DE PROCESOS FINALIZADOS
            System.out.println("Los procesos finalizados exitosamente son ");
            System.out.println("PID     NOMBRE          INSTRUCCIONES     ");
            for (int i = 0; i < memoria.finalizados.size(); i++) {
                System.out.println(memoria.finalizados.get(i).getId() + "\t"
                                   + memoria.finalizados.get(i).getNombre() + "\t\t"
                                   + memoria.finalizados.get(i).getInstrucciones());
            }    
        }else{
            System.out.println("LISTA DE PROCESOS FINALIZADOS VACIA.");
        }

        if(!memoria.eliminados.isEmpty()){      // LISTA DE PROCESOS ELIMINADOS
            System.out.println("Los procesos eliminados son ");
            System.out.println("PID     NOMBRE          INSTRUCCIONES     ");
            for (int i = 0; i < memoria.eliminados.size(); i++) {
                System.out.println(memoria.eliminados.get(i).getId() + "\t"
                                   + memoria.eliminados.get(i).getNombre() + "\t\t"
                                   + memoria.eliminados.get(i).getInstrucciones());
            }
        }else{
            System.out.println("LISTA DE PROCESOS ELIMINADOS VACIA");
        }
        
        /*
        if (memoria.colaProcesos.isEmpty()) {    // LISTA DE LOCALIDADES DE MEM
            System.out.println("NO HAY PROCESOS EN MEMORIA.");
        } else {
            int aux;
            int sum = 0;
            System.out.println("LOCALIDAD \t PROCESO");
            for (int i = 0; i < memoria.colaProcesos.size(); i++){
                aux = memoria.colaProcesos.get(i).getEspacio();
                
                System.out.println("[" + (sum+1) + "-" + 
                        (sum + memoria.colaProcesos.get(i).getEspacio())+ "]  \t" +
                        memoria.colaProcesos.get(i).getNombre());
                                      
                sum += aux;
            }
        }
        */
        
        // Total de localidades faltantes
        System.out.println("ESPACIO DE MEMORIA DISPONIBLE: " + 
                memoria.getLocalidades() + " LOCALIDADES");
    }
 
    private static void mostrarProcesos(){
        if(!memoria.colaProcesos.isEmpty()){
            System.out.println("PID     NOMBRE          INSTRUCCIONES     ");
            for (int i = 0; i < memoria.colaProcesos.size(); i++) {
                System.out.println(memoria.colaProcesos.get(i).getId() + "\t"
                        + memoria.colaProcesos.get(i).getNombre() + "  \t\t"
                        + memoria.colaProcesos.get(i).getInstrucciones());
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
            System.out.println("Nombre \t| PID \t| Instrucciones Totales | "
                    + "Instrucciones Ejecutadas | DIR. De Memoria   ");
            System.out.println(memoria.colaProcesos.getFirst().getNombre() // Nombre Proceso
                    + "\t  " + memoria.colaProcesos.getFirst().getId()       // PID Proceso
                    + "\t\t " + memoria.colaProcesos.getFirst().getInstrucciones() // Instrucciones totales
                    + "\t\t\t " + memoria.colaProcesos.getFirst().getInstruccionesEjecutadas() // Instrucciones ejecutadas
                    + "\t\t\t\t " + memoria.colaProcesos.getFirst().getEspacio());     // DIR. de memoria ocupadas por proceso     
            
            //Forma de Fer
            //System.out.println("Nombre " + memoria.colaProcesos.getFirst().getNombre());
            //System.out.println("ID " + memoria.colaProcesos.getFirst().getId());
            //System.out.println("Intrucciones totales " + memoria.colaProcesos.getFirst().getInstrucciones());
            //System.out.println("Instrucciones ejecutadas " + memoria.colaProcesos.getFirst().getInstruccionesEjecutadas());
            //System.out.println("Direcciones de memoria " + memoria.colaProcesos.getFirst().getEspacio());
            System.out.println("\n-------TABLA DE PÁGINAS-------");
            System.out.println("Pagina\t|Frame\t|PID\t|Contenido");
            int i;
            for(i=0; i < memoria.colaProcesos.getFirst().tablaPaginas.size();i++){
                System.out.println( i+1 // Contador de paginas
                + "\t " +memoria.colaProcesos.getFirst().tablaPaginas.get(i) // Frame
                + "\t " + memoria.colaProcesos.getFirst().getId() // PID
                + "\t " + memoria.colaProcesos.getFirst().getNombre() // Contenido
                + "/"   + "Pagina" + (i+1));
                //+ memoria.colaProcesos.getFirst().getInstruccionesEjecutadas() 
                //+ memoria.colaProcesos.getFirst().getEspacio());
                
                //Forma de fernando
                //System.out.println(memoria.colaProcesos.getFirst().tablaPaginas.get(i));    
            }
            System.out.println("PAGINAS TOTALES: " + i);
        }
    }
    
    private static void ejecutarProceso(){
        if(memoria.colaProcesos.isEmpty()){
            System.out.println("COLA DE PROCESOS VACIA, REQUIERE AL MENOS UN (1) PROCESO CREADO.");
        }else{
            System.out.println("Proceso: " + memoria.colaProcesos.getFirst().getNombre());
            memoria.colaProcesos.getFirst().setInstrucciones(memoria.colaProcesos.getFirst().getInstrucciones() - 5);
            memoria.colaProcesos.getFirst().setInstruccionesEjecutadas(memoria.colaProcesos.getFirst().getInstruccionesEjecutadas() + 5);
            if (memoria.colaProcesos.getFirst().getInstrucciones() <= 0) {            // Si ya no tiene instrucciones
                memoria.finalizados.addFirst(memoria.colaProcesos.getFirst());  // Agrega el elemento a lista finalizados
                System.out.println("PROCESO: " + memoria.colaProcesos.getFirst().getNombre() + " FINALIZADO");
                System.out.println("LIBERADAS: " + memoria.colaProcesos.getFirst().getEspacio() + " LOCALIDADES");
                memoria.setLocalidades(memoria.getLocalidades() + memoria.colaProcesos.getFirst().getEspacio()); // Reintegramos el espacio
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
            System.out.println("SIGUIENTE: " + memoria.colaProcesos.getFirst().getNombre());
        }
    }
    
    private static void matarProceso(){
        if(memoria.colaProcesos.isEmpty()){
            System.out.println("COLA DE PROCESOS VACIA, REQUIERE AL MENOS UN (1) PROCESO CREADO.");
        }else{
            memoria.setLocalidades(memoria.getLocalidades() + memoria.colaProcesos.getFirst().getEspacio());
            memoria.eliminados.add(memoria.colaProcesos.getFirst());
            System.out.println("PROCESO: " + memoria.colaProcesos.getFirst().getNombre() + " SERA ELIMINADO");
            System.out.println("Las instrucciones pendientes son " + memoria.colaProcesos.getFirst().getInstrucciones());
            liberarMemoria(memoria.colaProcesos.getFirst().getNombre());
            memoria.colaProcesos.remove();
        }
    }
    
    private static void liberarMemoria(String nombre){
        //Collections.replaceAll(memoria.listaMemoria, nombre, "Vacio");
        for(int i = 1; i <= memoria.listaMemoria.size(); i++){
            if(memoria.listaMemoria.get(i).getNombre().contains(nombre)){
                memoria.listaMemoria.get(i).setNombre("Hueco");
            }
        }
    }

    private static void insertarProceso(int indice,String nombre, int empieza, int longitud, int index){
        memoria.listaMemoria.insertAt(indice,nombre,empieza,longitud, index);
    }
    
    private static void eliminarProceso(int indice){
        memoria.listaMemoria.deleteAt(indice);
    }
    
    private static int getIndex(){
        for(int i = 1; i <= memoria.listaMemoria.size();i++){
            if(memoria.listaMemoria.get(i).getNombre().contains("Hueco")){
                index = memoria.listaMemoria.get(i).getIndice();
                break;
            }
        }
        return index;
    }
    
    private static int getUltimoProceso(){
        for(int i = getIndex();i <= memoria.listaMemoria.size();i++){
            if(!memoria.listaMemoria.get(i).getNombre().contains("Hueco")){
                ultimoProceso = memoria.listaMemoria.get(i).getIndice();
                break;
            }
            ultimoProceso = -1;
        }
        return ultimoProceso;
    }
    
    private static void desfragmentacion(){
        int diff = getUltimoProceso() - getIndex();
        //System.out.println(diff);     // Para saber el valor de la diferencia
        if(getUltimoProceso() == -1){   // No hubo diferencia de desfragmentacion
            System.out.println("NO SE DETECTARON PROCESOS PARA DESFRAGMENTAR");
        }else{
            while(getUltimoProceso() != -1 ){
                intercambio(getIndex(), getUltimoProceso());
            }
        }
    }
    
    private static void intercambio(int hueco, int proceso){
        memoria.listaMemoria.get(hueco).setNombre(memoria.listaMemoria.get(proceso).getNombre());
        memoria.listaMemoria.get(proceso).setNombre("Hueco");
    } 
}

