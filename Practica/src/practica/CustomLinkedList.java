package practica;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author fernando
 */
public class CustomLinkedList {
    Node head;
    

    //function to add an element to the list at the end if there are element already and at the start if no elements are there
    public void insert(String nombre, int empieza, int longitud, int indice) {
        Node newNode = new Node(nombre, empieza, longitud, indice);
        if(head == null) {
            head = newNode;
        }else {
            Node n = head;
            while(n.next != null) {
                n = n.next;
            }
            n.next = newNode;
        }
    }

    //function to add an element at the start of the list
    public void insertAtStart(String nombre, int empieza, int longitud, int indice) {
        Node newNode = new Node(nombre, empieza, longitud, indice);

        newNode.next = head;
        head = newNode;
    }

    //function to add an element at a given index
    public void insertAt(int index, String nombre, int empieza, int longitud, int indice) {
        if(index == 0){
            insertAtStart(nombre, empieza, longitud, indice); //We already have a function to do this.
        }else{
            Node newNode = new Node(nombre, empieza, longitud,indice);

            Node currentNode = head;
            for(int i = 0; i < index - 1; i++) {
                currentNode = currentNode.next;
            }
            newNode.next = currentNode.next;
            currentNode.next = newNode;
        }
    }

    //function to delete an element at the given index
    public void deleteAt(int index) {
        if(index == 0) {
            head = head.next;
        }else{
            Node currentNode = head;
            for(int i = 0; i < index - 1; i++) {
                currentNode = currentNode.next;
            }
            currentNode.next = currentNode.next.next;
        }
    }
    
    public int size(){
        int i = 0;
        Node currentNode = head;
        if(currentNode == null){
            System.out.println("Linked list is empty");
        }
        else {
            while(currentNode != null) {
                currentNode = currentNode.next;
                i += 1;
            }
        }
        return i;
    }
    //function to show nodes in Linked List
    public Node get(int index){
        Node currentNode = head;
        if(currentNode == null){ // No elements at LinkedList
            System.out.println("Linked list is empty");
            return null;
        }else{
            for(int i = 0; i < index - 1; i++) {
                currentNode = currentNode.next;
            }
            return currentNode;
        }
    }

    //function to show the list
    public void show() {
        Node currentNode = head;
        if(currentNode == null){
            System.out.println("Linked list is empty");
        }
        else {
            while(currentNode != null) {
                System.out.println("[" + (currentNode.getIndice()) + "]" + " " 
                        + currentNode.getNombre() + " " 
                        + currentNode.getEmpieza() + " " 
                        + currentNode.getLongitud() + " ");
                System.out.println("\t|");
                System.out.println("\t|");
                System.out.println("\t|");
                System.out.println("\tV");
                currentNode = currentNode.next;
            }
        }
    }        
}
