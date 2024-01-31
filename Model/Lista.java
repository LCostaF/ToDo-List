/*
 * Autor: Lucas Costa Fuganti
 * V360 - ToDo List
 */
package Model;

/**
 *
 * @author Lucas
 */
public class Lista {
    // Atributos
    private int idLista;
    private String nomeLista;
    
    // Métodos Construtores
    public Lista() {
        
    }
    
    public Lista(String nomeLista) {
        this.nomeLista = nomeLista;
    }
    
    public Lista(int idLista, String nomeLista) {
        this.idLista = idLista;
        this.nomeLista = nomeLista;
    }
    
    // Métodos Getters
    public int getIdLista() {
        return idLista;
    }
    
    public String getNomeLista() {
        return nomeLista;
    }
    
    // Métodos Setters
    public void setIdLista(int idLista) {
        this.idLista = idLista;
    }
    
    public void setNomeLista(String nomeLista) {
        this.nomeLista = nomeLista;
    }
}
