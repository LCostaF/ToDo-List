/*
 * Autor: Lucas Costa Fuganti
 * V360 - ToDo List
 */
package Model;

/**
 *
 * @author Lucas
 */
public class Item {
    // Atributos
    private int idItem;
    private String descItem;

    // Métodos Construtores
    public Item() {
        
    }
    
    // Métodos Getters
    public int getIdItem() {
        return idItem;
    }
    
    public String getDescItem() {
        return descItem;
    }
    
    // Métodos Setters
    public void setIdItem(int idItem) {
        this.idItem = idItem;
    }
    
    public void setDescItem(String descItem) {
        this.descItem = descItem;
    }
}
