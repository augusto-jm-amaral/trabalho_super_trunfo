package service;

import model.Jogada;
import java.util.ArrayList;

/**
 *  Esta classe é responsavel pelas regras de negócio de uma jogada, determina um ganhador.
 * 
 * @author augusto
 */
public class JogadaService {
    
    public Jogada getWinner(ArrayList<Jogada> jogadas, String opcao){
        
        //valor do atributo
        double valor = 0;
        //jogador vencedor
        Jogada ganhador = null;
        
        //para toda jogada
        for (Jogada jogada : jogadas) {
            
            //pega o valor do atributo
            double valorDoJogador = getValor(jogada, opcao);
            
            //valor do atributo for menor que o do jogador 
            if(valor<valorDoJogador){
                
                //jogada vencedora é igual a respequitiva jogada.
                ganhador = jogada;
                //valor passa a ser o valorDoJogador vencedor até então
                valor = valorDoJogador;
            }
            
        }
        
        //retorna a jogada do jogador vencedor
        return ganhador;
    }
    
    //getValor verifica o valor da jogada de determinado atributo.
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
    
}
