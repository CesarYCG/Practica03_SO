/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package practica;

import java.util.LinkedList;

/**
 *
 * @author fernando
 */
public class Memoria {
    private int localidades = 2048;
    LinkedList<Proceso> colaProcesos = new LinkedList<Proceso>();
    LinkedList<Proceso> finalizados = new LinkedList<Proceso>();
    LinkedList<Proceso> eliminados = new LinkedList<Proceso>();
    
    
    
    
    
    
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
    
}
