package model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 *  Esse objeto e utilizado para transações servidor cliente, cliente servidor.
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
