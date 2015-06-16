package service;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import model.Carro;
import model.Conexao;
import model.Escopo;
import model.Pacote;

/**
 *  Este classe é o cliente do jogo utilizado para jogar.
 * @author augusto
 */
public class Cliente extends javax.swing.JFrame {
    
    //Modelo conexão representa uma conexão com o servidor
    protected Conexao server;
    //ArrayList de carros 
    protected ArrayList<Carro> carros;
    //Carro que é exibido na tela
    protected Carro carroNaTela;
    
    /**
     * Creates new form Cliente
     */
    public Cliente() {
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        tPorta = new javax.swing.JTextField();
        tNome = new javax.swing.JTextField();
        tIp = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        tArea = new javax.swing.JTextArea();
        tEnviar = new javax.swing.JTextField();
        bEnviar = new javax.swing.JButton();
        bConectar = new javax.swing.JButton();
        cOpcoes = new javax.swing.JComboBox();
        bJogar = new javax.swing.JButton();
        jLabel6 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        lMarcaModelo = new javax.swing.JLabel();
        lPreco = new javax.swing.JLabel();
        lCilindradas = new javax.swing.JLabel();
        lPotencia = new javax.swing.JLabel();
        lTorque = new javax.swing.JLabel();
        lVelocidade = new javax.swing.JLabel();
        lPeso = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        lNumCartas = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setText("Nome:");

        jLabel2.setText("Ip:");

        jLabel3.setText("Porta:");

        tPorta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tPortaActionPerformed(evt);
            }
        });

        tNome.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tNomeActionPerformed(evt);
            }
        });

        tIp.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tIpActionPerformed(evt);
            }
        });

        tArea.setEditable(false);
        tArea.setColumns(20);
        tArea.setRows(5);
        tArea.setRequestFocusEnabled(false);
        jScrollPane1.setViewportView(tArea);

        tEnviar.setEnabled(false);

        bEnviar.setText("Enviar");
        bEnviar.setEnabled(false);
        bEnviar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bEnviarActionPerformed(evt);
            }
        });

        bConectar.setText("Conectar");
        bConectar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bConectarActionPerformed(evt);
            }
        });

        cOpcoes.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Velocidade Max", "Peso", "Cilindrada", "Preço", "Torque", "Potencia" }));

        bJogar.setText("Jogar");
        bJogar.setEnabled(false);
        bJogar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bJogarActionPerformed(evt);
            }
        });

        jLabel6.setText("Vel Max");

        jLabel8.setText("Peso");

        jLabel9.setText("Preco");

        jLabel10.setText("Cilindradas");

        jLabel11.setText("Potencia");

        jLabel12.setText("Torque");

        lMarcaModelo.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lMarcaModelo.setText("Marca / Modelo");

        lPreco.setText("Preco");

        lCilindradas.setText("Cilindradas");

        lPotencia.setText("Potencia");

        lTorque.setText("Torque");

        lVelocidade.setText("Vel Max");

        lPeso.setText("Peso");

        jLabel4.setText("Numero de cartas:");

        lNumCartas.setText("Aguarde o inicio da partida");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel3)
                            .addComponent(jLabel2)
                            .addComponent(jLabel1))
                        .addGap(74, 74, 74)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(tNome, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(tIp, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(tPorta, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(bConectar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(cOpcoes, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(bJogar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(lMarcaModelo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel6)
                            .addComponent(jLabel8)
                            .addComponent(jLabel9)
                            .addComponent(jLabel10)
                            .addComponent(jLabel11)
                            .addComponent(jLabel12))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(lVelocidade)
                            .addComponent(lPeso)
                            .addComponent(lPreco)
                            .addComponent(lCilindradas)
                            .addComponent(lPotencia)
                            .addComponent(lTorque)))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(lNumCartas)))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(tEnviar, javax.swing.GroupLayout.PREFERRED_SIZE, 202, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(bEnviar, javax.swing.GroupLayout.DEFAULT_SIZE, 77, Short.MAX_VALUE))
                    .addComponent(jScrollPane1))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1)
                            .addComponent(tNome, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2)
                            .addComponent(tIp, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel3)
                            .addComponent(tPorta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(9, 9, 9)
                        .addComponent(bConectar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel4)
                            .addComponent(lNumCartas))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(cOpcoes, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(bJogar))
                        .addGap(18, 18, 18)
                        .addComponent(lMarcaModelo)
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel6)
                                .addGap(4, 4, 4)
                                .addComponent(jLabel8)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel10)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel9)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel12)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel11))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(lVelocidade)
                                .addGap(4, 4, 4)
                                .addComponent(lPeso)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(lCilindradas)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(lPreco)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(lTorque)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(lPotencia))))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 364, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(tEnviar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(bEnviar))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void tPortaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tPortaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tPortaActionPerformed

    private void tNomeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tNomeActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tNomeActionPerformed

    private void tIpActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tIpActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tIpActionPerformed
    /**
     * Botão responsavel por enviar msgs de texto ao servidor.
     * @param evt 
     */
    private void bEnviarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bEnviarActionPerformed
        
        //Pega a mensagem do usuario no campo
        String text = this.tEnviar.getText();
        
        //limpa o campo
        this.tEnviar.setText("");
        
        //adiciona um '\n' para que a menssagem pule uma linha ao ser exibida na interfacie dos demais
        String textC = text.concat("\n");
        
        //pega o nome do usuario
        String nome = this.tNome.getText();
        
        //adiciona o nome a mensagem
        String textPEnviar = nome + " diz : " + textC;
        
        //cria um pacote com escopo chat 
        Pacote pacote = new Pacote(Escopo.CHAT);
        //add a msg 
        pacote.addContainer(textPEnviar);
        
        try {
            
            //envia a msg ao servidor
            new ObjectOutputStream(this.server.getSocket().getOutputStream()).writeObject(pacote);
            
        } catch (IOException ex) {
            
            ex.printStackTrace();
        }
        
    }//GEN-LAST:event_bEnviarActionPerformed
    
    /**
     * Botão responsavel por conectar o cliente ao servidor.
     * @param evt 
     */
    private void bConectarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bConectarActionPerformed
        
        //Pega os ip porta e nome dos campos
        String ip = this.tIp.getText();
        String porta = this.tPorta.getText();
        String nome = this.tNome.getText();
        
        //bloqueia os campos e o bConectar
        this.tIp.setEnabled(false);
        this.tPorta.setEnabled(false);
        this.tNome.setEnabled(false);
        this.bConectar.setEnabled(false);
        
        //desbloqueia o chat
        this.bEnviar.setEnabled(true);
        this.tEnviar.setEnabled(true);
        
        Socket socket;
        
        try {
            
            //abre conexão socket com o server
            socket = new Socket(ip, Integer.parseInt(porta));
            this.server = new Conexao(socket);
            
            //cria um listener para o servidor
            Thread thread = new Thread(new ServerListener(server));
            thread.start();
            
            //envia um mensagem de chat avisando outros jogadores da conexão
            Pacote pacote = new Pacote(Escopo.CHAT);
            pacote.addContainer("[Servidor] Jogador " + nome + " acaba de se conectar!\n");
            new ObjectOutputStream(this.server.getSocket().getOutputStream()).writeObject(pacote);
            
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        
    }//GEN-LAST:event_bConectarActionPerformed

    private void bJogarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bJogarActionPerformed
        
        
        //cria um pacote com escopo jogada
        Pacote pacote = new Pacote(Escopo.JOGADA);
        
        //adiciona a opção selecionada do usuario
        pacote.addContainer((String) this.cOpcoes.getSelectedItem());
        //adiciona o carro do usuario
        pacote.addContainer(this.carroNaTela);
        
        //chama o método tratar pacote
        trataPacote(pacote);
    }//GEN-LAST:event_bJogarActionPerformed
       
    /**
     * Faz a renderização do carro atual na tela.
     */
    private void renderizarCarroTela() {
        
        //pega os valores do carro corrente e adiciona na tela
        this.lMarcaModelo.setText(carroNaTela.getMarca() + " " + carroNaTela.getModelo());
        this.lVelocidade.setText("" + carroNaTela.getVelocidadeMaxima());
        this.lPeso.setText("" + carroNaTela.getPeso());
        this.lCilindradas.setText("" + carroNaTela.getCilindradasMotor());
        this.lPreco.setText("" + carroNaTela.getPreco());
        this.lTorque.setText("" + carroNaTela.getTorque());
        this.lPotencia.setText("" + carroNaTela.getPotencia());
        this.lNumCartas.setText("" + this.carros.size());
    }
    
    /**
     * Classe ouvinte do servidor.
     */
    class ServerListener implements Runnable{
        
        Conexao cliente;
        
        /**
         * Contrutor recebe a conexão com o Server;
         * @param cliente 
         */
        public ServerListener(Conexao cliente) {
            this.cliente = cliente;
        }
                
        @Override
        public void run() {
            
            //loop da thread
            while(true){
                
                try {
                    
                    //aguarda um pacote do servidor
                    Pacote pacote = (Pacote) new ObjectInputStream(cliente.getSocket().getInputStream()).readObject();
                    //envia o pacote para o servidor
                    trataPacote(pacote);
                    
                    
                } catch (IOException ex) {
                    
                    //o servidor desconecte                    
                    throw  new RuntimeException("Jogador " + cliente.getId() + " desconectou");
                    
                } catch (ClassNotFoundException ex) {
                    
                    //o objeto enviado não seja um pacote
                    ex.printStackTrace();
                }
            }
        }
        
    }
    
    //Trata os pacotes
    public synchronized void trataPacote(Pacote pacote){
        
        //pega o escopo do pacote recebido
        Escopo escopo = pacote.getEscopo();
        
        //caso pacote de chat
        if(escopo == Escopo.CHAT){
            
            //printa o texto no textArea
            this.tArea.append((String)pacote.getContainer().get(0));
            
        //pacote de start - pacote que inicia o jogo
        }else if(escopo == Escopo.START){
            
            //pega o array de carros do container do pacote
            this.carros = (ArrayList<Carro>) pacote.getContainer().get(0);
            
            //pega o boolean do container do pacote, este boolean vai determinar se ele deve começar a partida
            Boolean isStart = (Boolean) pacote.getContainer().get(1);
            
            //caso ele seja o primeiro
            if(isStart){
                
                //habilita o botão jogar
                this.bJogar.setEnabled(true);
                
                //envia um pacote chat para iformar que ele ira começar
                Pacote pacoteChat = new Pacote(Escopo.CHAT);
                String msgFirst = "[Servidor] O jogador " + this.tNome.getText() + " deve começar!\n";
                pacoteChat.addContainer(msgFirst);
                try {
                    new ObjectOutputStream(server.getSocket().getOutputStream()).writeObject(pacoteChat);
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
            
            //pega o primeira carro ta lista
            carroNaTela = carros.get(0);
        
            //printa ele na tela
            renderizarCarroTela();
        
        //pacote de retorno da jogada
        }else if(escopo == Escopo.JOGADA_RETURN){
            
            //pega o boolean que diz se ele ganhou ou não
            Boolean isWin = (Boolean) pacote.getContainer().get(0);
            
            //se ganhou
            if(isWin){
                
                //habilita o botão jogar
                this.bJogar.setEnabled(true);
                
                //remove o primeiro carro
                this.carros.remove(0);
                
                //extrai os carros dos outros jogadores do pacote
                ArrayList<Carro> carros = (ArrayList<Carro>) pacote.getContainer().get(1);
                
                //adiciona na lista do jogador
                for (Carro carro : carros) {
                    this.carros.add(carro);
                }
            //Caso perdeu    
            }else{
                
                //desabilita o botão jogar
                this.bJogar.setEnabled(false);
                
                //remove o carro atual da lista de carros
                this.carros.remove(this.carroNaTela);
                
                //caso o numero de carros chegue a zero o jogador perde
                if(carros.isEmpty()){
                    
                    //avisa os demais jogadores da derrota
                    Pacote pacoteMsgDerrota = new Pacote(Escopo.CHAT);
                    pacoteMsgDerrota.addContainer("[Servidor] O jogador " + this.tNome.getText() + " perdeu!\n");
                    
                    try {
                        //envia ao servidor
                        new ObjectOutputStream(this.server.getSocket().getOutputStream()).writeObject(pacoteMsgDerrota);
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }
                    
                    //lança um jOptionPane na tela para informar a derrota ao usuario
                    JOptionPane.showMessageDialog(this, "Suas cartas acabaram, você esta fora do jogo.");
                    
                    //fecha o jogo
                    System.exit(0);
                }
                
            }
            
            //pega o primeiro carrp da lista
            this.carroNaTela = this.carros.get(0);
            
            //renderiza na tela
            renderizarCarroTela();
        
        //caso pacote jogada ele o envia ao servidor
        }else if(escopo == Escopo.JOGADA){
            
            try {
                
                // envia o pacote ao servidor
                new ObjectOutputStream(server.getSocket().getOutputStream()).writeObject(pacote);
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        
        //pacote que pede uma jogada 
        //(Para os jogadores que não podem fazer a jogada na rodada, o servidor vai requisitar a carta)
        }else if(escopo == Escopo.GET_JOGADA){
            
            //cria o pacote jogada
            Pacote pacoteJogada = new Pacote(Escopo.GET_JOGADA);
            
            //adiciona a carta ao container do pacote
            pacoteJogada.addContainer(this.carroNaTela);
            
            
            try {
                //envia o pacote para o servidor
                new ObjectOutputStream(server.getSocket().getOutputStream()).writeObject(pacoteJogada);
            } catch (IOException ex) {
                ex.printStackTrace();
            }
            
            
        }
    }
    
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Cliente.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Cliente.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Cliente.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Cliente.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Cliente().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton bConectar;
    private javax.swing.JButton bEnviar;
    private javax.swing.JButton bJogar;
    private javax.swing.JComboBox cOpcoes;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lCilindradas;
    private javax.swing.JLabel lMarcaModelo;
    private javax.swing.JLabel lNumCartas;
    private javax.swing.JLabel lPeso;
    private javax.swing.JLabel lPotencia;
    private javax.swing.JLabel lPreco;
    private javax.swing.JLabel lTorque;
    private javax.swing.JLabel lVelocidade;
    private javax.swing.JTextArea tArea;
    private javax.swing.JTextField tEnviar;
    private javax.swing.JTextField tIp;
    private javax.swing.JTextField tNome;
    private javax.swing.JTextField tPorta;
    // End of variables declaration//GEN-END:variables
}
