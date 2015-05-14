/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.net.Socket;

/**
 *
 * @author augusto
 */
public class Conexao {
    
    private static int conexoes = 0;
    
    protected Socket socket;
    protected int id;
    
    public Conexao(Socket socket) {
        this.socket = socket;
        this.id = conexoes;
        conexoes++;
    }

    public Socket getSocket() {
        return socket;
    }

    public int getId() {
        return id;
    }    
    
}
