
package model;

import java.io.Serializable;

/**
 *
 * @author Mário Pardo
 */

public class Carro implements Serializable{
    
    private int idCarta;  
    private String marca;
    private String modelo; //Similar ao nome do carro
    //Atributos de Jogo
    private int velocidadeMaxima; //km/h
    private int peso; //kg
    private int cilindradasMotor; //cm3
    private double preco; //R$
    private double torque; //KgF
    private int potencia; //cv ou HP

    public Carro() {
        this.idCarta = 0;
        this.marca = "";
        this.modelo = "";
        this.velocidadeMaxima = 0;
        this.peso = 0;
        this.cilindradasMotor = 0;
        this.preco = 0.0;
        this.torque = 0.0;
        this.potencia = 0;
    }

    
    public Carro(int idCarta, String marca, String modelo, int velocidadeMaxima, int peso, int cilindradasMotor, double preco, double torque, int potencia) {
        this.idCarta = idCarta;
        this.marca = marca;
        this.modelo = modelo;
        this.velocidadeMaxima = velocidadeMaxima;
        this.peso = peso;
        this.cilindradasMotor = cilindradasMotor;
        this.preco = preco;
        this.torque = torque;
        this.potencia = potencia;
    }

    public int getIdCarta() {
        return idCarta;
    }

    public void setIdCarta(int idCarta) {
        this.idCarta = idCarta;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public int getVelocidadeMaxima() {
        return velocidadeMaxima;
    }

    public void setVelocidadeMaxima(int velocidadeMaxima) {
        this.velocidadeMaxima = velocidadeMaxima;
    }

    public int getPeso() {
        return peso;
    }

    public void setPeso(int peso) {
        this.peso = peso;
    }

    public int getCilindradasMotor() {
        return cilindradasMotor;
    }

    public void setCilindradasMotor(int cilindradasMotor) {
        this.cilindradasMotor = cilindradasMotor;
    }

    public double getPreco() {
        return preco;
    }

    public void setPreco(double preco) {
        this.preco = preco;
    }

    public double getTorque() {
        return torque;
    }

    public void setTorque(double torque) {
        this.torque = torque;
    }

    public int getPotencia() {
        return potencia;
    }

    public void setPotencia(int potencia) {
        this.potencia = potencia;
    }

    @Override
    public String toString() {
        return "Carro{" + "idCarta=" + idCarta + ", marca=" + marca + ", modelo=" + modelo + ", velocidadeMaxima=" + velocidadeMaxima + ", peso=" + peso + ", cilindradasMotor=" + cilindradasMotor + ", preco=" + preco + ", torque=" + torque + ", potencia=" + potencia + '}';
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 59 * hash + this.idCarta;
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
        final Carro other = (Carro) obj;
        if (this.idCarta != other.idCarta) {
            return false;
        }
        return true;
    }
  
}
