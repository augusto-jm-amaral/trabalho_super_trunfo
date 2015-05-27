
package model;

import java.io.Serializable;
import java.net.Socket;
import java.util.Objects;

/**
 *  Este objeto representa a conexão entre um servidor e o jogador e vice-versa
 *  
 * @author augusto
 */
public class Conexao implements Serializable{
    
    private static int conexoes = 0;
    
    protected Socket socket;
    protected int id;
    protected String nome;
    
    public Conexao(Socket socket) {
        this.socket = socket;
        this.id = conexoes;
        this.nome = "";
        conexoes++;
    }

    public Socket getSocket() {
        return socket;
    }

    public int getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 67 * hash + Objects.hashCode(this.socket);
        hash = 67 * hash + this.id;
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
        final Conexao other = (Conexao) obj;
        if (!Objects.equals(this.socket, other.socket)) {
            return false;
        }
        if (this.id != other.id) {
            return false;
        }
        return true;
    }
    
    
}
