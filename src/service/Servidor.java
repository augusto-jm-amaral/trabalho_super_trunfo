package service;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import model.Conexao;
import model.Escopo;
import model.Pacote;

/**
 *
 * @author augusto
 */
public class Servidor {
    
    protected ServerSocket server;
    protected static final int PORTA = 7878;
    protected List<Conexao> conexoes;
        
    public static void main(String [] args){
        try {
            new Servidor();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
    
    public void init(){
        conexoes = new ArrayList<>();
    }
    
    public Servidor() throws IOException {
        
        init();
        
        System.out.println("[SERVIDOR] Subindo Servidor...");
        
        server= new ServerSocket(PORTA);
        
        System.out.println("[SERVIDOR] Servidor On...");
        
        while(true){
            
            System.out.println("[SERVIDOR] Aguardando Cliente...");
            
            Socket socket = server.accept();
            
            System.out.println("[SERVIDOR] Cliente on...");
            
            Conexao conexao = new Conexao(socket);
            
            Thread thread = new Thread(new ClientListener(conexao));
            thread.start();
            
            this.conexoes.add(conexao);
            
        }
    }
    
    public synchronized void trataPacote(Pacote pacote){
        
        Escopo escopo = pacote.getEscopo();
        
        if(escopo == Escopo.CHAT){
            
            for (Conexao conexao : conexoes) {
                
                try {
                                                          
                    new ObjectOutputStream(conexao.getSocket().getOutputStream()).writeObject(pacote);
                    
                } catch (IOException ex) {
                    
                    ex.printStackTrace();
                }
            }
        }
    }
    
    class ClientListener implements Runnable{
        
        Conexao cliente;
        
        public ClientListener(Conexao cliente) {
            this.cliente = cliente;
        }
                
        @Override
        public void run() {
            
            while(true){
                
                try {
                    
                    Pacote pacote = (Pacote) new ObjectInputStream(cliente.getSocket().getInputStream()).readObject();
                    trataPacote(pacote);
                    
                } catch (IOException ex) {
                                        
                    for(int i = 0; i < conexoes.size(); i++){
                        
                        if(!conexoes.get(i).getSocket().isConnected()){
                            conexoes.remove(i);
                            break;
                        }
                    }
                    
                    throw  new RuntimeException("Jogador " + cliente.getId() + " desconectou");
                    
                } catch (ClassNotFoundException ex) {
                    ex.printStackTrace();
                }
            }
        }
        
    }
    
}
