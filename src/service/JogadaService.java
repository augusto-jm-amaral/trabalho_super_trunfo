/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import java.util.ArrayList;

/**
 *
 * @author augusto
 */
public class JogadaService {
    
    public Jogada getWinner(ArrayList<Jogada> jogadas, String opcao){
        
        double valor = 0;
        Jogada ganhador = null;
        
        for (Jogada jogada : jogadas) {
            
            double valorDoJogador = getValor(jogada, opcao);
            
            if(valor<valorDoJogador){
                
                ganhador = jogada;
                valor = valorDoJogador;
            }
            
        }
        
        return ganhador;
    }
    
    public double getValor(Jogada jogada, String opcao){
        
        double valor = 0;
        
        switch(opcao){
            
            case "Velocidade Max":
                    valor = jogada.getCarro().getVelocidadeMaxima();
                break;
            
            case "Peso":
                    valor = jogada.getCarro().getPeso();
                break;
                
            case "Cilindrada":
                    valor = jogada.getCarro().getCilindradasMotor();
                break;
                
            case "Preço":
                    valor = jogada.getCarro().getPreco();
                break;
                
            case "Torque":
                    valor = jogada.getCarro().getPreco();
                break;
                
            case "Potencia":
                    valor = jogada.getCarro().getPreco();
                break;
        }
        
        return valor;
    }
    
//Velocidade Max
//Peso
//Cilindrada
//Preço
//Torque
//Potencia
    
}
