/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;
import model.Escopo;
import model.Pacote;

/**
 *
 * @author augusto
 */
public class ClienteTeste {
    
    public static void main(String [] args){
        new ClienteTeste();
    }

    public ClienteTeste() {
        
        try {
            Socket socket = new Socket("127.0.0.1", 7878);
            
            for (int i = 0; i < 1000; i++) {
                
                Pacote pacote = new Pacote(Escopo.CHAT);
                String msg = "" + 1000;
                pacote.addContainer(msg);
                new ObjectOutputStream(socket.getOutputStream()).writeObject(pacote);
            }
            
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
        
}
