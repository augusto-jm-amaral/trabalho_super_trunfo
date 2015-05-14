/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author augusto
 */
public class Pacote implements Serializable{
    
    protected Escopo escopo;
    protected List<Object> container;
    
    public Pacote(Escopo escopo) {
        container = new ArrayList<>();
        this.escopo = escopo;
    }
    
    public void addContainer(Object object){
        container.add(object);
    }

    public List<Object> getContainer() {
        return container;
    }

    public Escopo getEscopo() {
        return escopo;
    }
    
}
