/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package practica;

import java.util.ArrayList;
import java.util.LinkedList;

/**
 *
 * @author fernando & cesar
 */
public class Memoria {
    
    private static Memoria memoria;
    //2048
    private int localidades = 1024; 
    LinkedList<Proceso> colaProcesos = new LinkedList<Proceso>();
    LinkedList<Proceso> finalizados = new LinkedList<Proceso>();
    LinkedList<Proceso> eliminados = new LinkedList<Proceso>();
    CustomLinkedList listaMemoria = new CustomLinkedList();
    
    private Memoria(){
        inciaMemoria();
    }
    
    public static Memoria getInstance(){
        if(memoria == null){
            memoria = new Memoria();
        }
        return memoria;
    }
    

    
    
    /**
     * @return the localidades
     */
    public int getLocalidades() {
        return localidades;
    }

    /**
     * @param localidades the localidades to set
     */
    public void setLocalidades(int localidades) {
        this.localidades = localidades;
    }
    
    private void inciaMemoria(){
        int posicionActual = 0;
        int localidadesPagina = 16;
        for(int i = 1; i < 65; i++){
            listaMemoria.insert("Hueco", posicionActual, localidadesPagina,i);
            posicionActual += localidadesPagina;
        }
    }
    
}
