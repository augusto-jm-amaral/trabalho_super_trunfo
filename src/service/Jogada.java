/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import java.util.Objects;
import model.Carro;
import model.Conexao;

/**
 *
 * @author augusto
 */
class Jogada {
    
    private Conexao conexao;
    private Carro carro;

    public Jogada(Conexao conexao, Carro carro) {
        this.conexao = conexao;
        this.carro = carro;
    }
        
    public Conexao getConexao() {
        return conexao;
    }

    public void setConexao(Conexao conexao) {
        this.conexao = conexao;
    }

    public Carro getCarro() {
        return carro;
    }

    public void setCarro(Carro carro) {
        this.carro = carro;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 23 * hash + Objects.hashCode(this.conexao);
        hash = 23 * hash + Objects.hashCode(this.carro);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Jogada other = (Jogada) obj;
        if (!Objects.equals(this.conexao, other.conexao)) {
            return false;
        }
        if (!Objects.equals(this.carro, other.carro)) {
            return false;
        }
        return true;
    }
    
    
    
}
