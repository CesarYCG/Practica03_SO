/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package practica;

/**
 *
 * @author fernando
 */
public class Node {
    private String nombre; //Data in the current node
    private int empieza;
    private int longitud;
    private int indice;
    Node next; //Reference for the next node

    //Constructor which contains nombre, start val, length and index of process
    Node(String nombre, int empieza, int longitud, int indice) {
        this.nombre = nombre;
        this.empieza = empieza;
        this.longitud = longitud;
        this.indice = indice;
    }

    /**
     * @return the nombre
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * @param nombre the nombre to set
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * @return the empieza
     */
    public int getEmpieza() {
        return empieza;
    }

    /**
     * @param empieza the empieza to set
     */
    public void setEmpieza(int empieza) {
        this.empieza = empieza;
    }

    /**
     * @return the longitud
     */
    public int getLongitud() {
        return longitud;
    }

    /**
     * @param longitud the longitud to set
     */
    public void setLongitud(int longitud) {
        this.longitud = longitud;
    }
    
        /**
     * @return the indice
     */
    public int getIndice() {
        return indice;
    }

    /**
     * @param indice the indice to set
     */
    public void setIndice(int indice) {
        this.indice = indice;
    }
    
}