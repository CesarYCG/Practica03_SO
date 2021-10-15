/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package practica;

/**
 *
 * @author fernando & cesar
 */
public class Proceso {
    private String nombre;
    private int id = 0;
    private int instrucciones;
    private int espacio;
    private int instruccionesEjecutadas;
    
    private Memoria memoria = Memoria.getInstance();
    
    public void crearProceso(String nombre, int id,int espacio){
        this.setEspacio(espacio);
        this.setNombre(nombre);
        this.setId(id);
        this.setInstrucciones((int)(Math.random()*(30-10)) + 10);
        this.setInstruccionesEjecutadas(0);
        getMemoria().setLocalidades(getMemoria().getLocalidades() - this.getEspacio());
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
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * @return the instrucciones
     */
    public int getInstrucciones() {
        return instrucciones;
    }

    /**
     * @param instrucciones the instrucciones to set
     */
    public void setInstrucciones(int instrucciones) {
        this.instrucciones = instrucciones;
    }

    /**
     * @return the espacio
     */
    public int getEspacio() {
        return espacio;
    }

    /**
     * @param espacio the espacio to set
     */
    public void setEspacio(int espacio) {
        this.espacio = espacio;
    }

    /**
     * @return the instruccionesEjecutadas
     */
    public int getInstruccionesEjecutadas() {
        return instruccionesEjecutadas;
    }

    /**
     * @param instruccionesEjecutadas the instruccionesEjecutadas to set
     */
    public void setInstruccionesEjecutadas(int instruccionesEjecutadas) {
        this.instruccionesEjecutadas = instruccionesEjecutadas;
    }

    /**
     * @return the memoria
     */
    public Memoria getMemoria() {
        return memoria;
    }

    /**
     * @param memoria the memoria to set
     */
    public void setMemoria(Memoria memoria) {
        this.memoria = memoria;
    }
}
