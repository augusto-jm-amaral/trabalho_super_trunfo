package service;

import model.Jogada;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Random;
import java.util.StringTokenizer;
import model.Carro;
import model.Conexao;
import model.Escopo;
import model.Pacote;

/**
 *  Esta classe representa o servidor, tem de haver um minimo de 4 conexões para iniciar uma partida.
 * 
 * @author augusto
 */
public class Servidor {
    
    protected final int NUMERO_DE_CARTAS_POR_JOGADOR = 4;
    protected final int NUMERO_DE_JOGADORES_POR_JOGO = 4;
    
    protected ServerSocket server;
    protected static final int PORTA = 7878;
    protected static ArrayList<Conexao> conexoes;
    protected ArrayList<Jogada> jogadas;
    protected String opcao;
    
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
            
            //gera a conexao
            Conexao conexao = new Conexao(socket);
            
            //cria um listener para a conexão
            Thread thread = new Thread(new ClientListener(conexao));
            thread.start();
            
            //add ao array de conexões
            this.conexoes.add(conexao);
            
            //Game Start
            if(conexoes.size()==4){
                
                System.out.println("[SERVIDOR] Start...");
                
                //msg aos jogadores
                Pacote pacote = new Pacote(Escopo.CHAT);
                String msgStart = "[Servidor] Game Start\n";
                pacote.addContainer(msgStart);
                trataPacote(pacote, null);
                
                //carrega carros
                ArrayList<Carro> carros = carregaCartas();
                
                Random rand = new Random();
                
                int first = rand.nextInt(conexoes.size());
                
                //para cada jogador
                System.out.println("[SERVIDOR] Enviado Cartas...");
                for (int i = 0 ; i < conexoes.size(); i++) {
                    
                    System.out.println("[SERVIDOR] " + conexoes.get(i).getNome());
                    
                    Pacote pacoteStart = new Pacote(Escopo.START);
                    
                    ArrayList<Carro> carrosStart = new ArrayList<>();
                    
                    for(int j = 0; j < NUMERO_DE_CARTAS_POR_JOGADOR ; j++){
                        
                        //pega um carro aleatorio / add pacote / remove do array
                        int randNum = rand.nextInt(carros.size());
                        Carro carro = carros.get(randNum);
                        carrosStart.add(carro);
                        carros.remove(carro);
                        
                    }
                    
                    //add arraylist de cartas
                    pacoteStart.addContainer(carrosStart);
                    
                    Boolean isFirst = null;
                    
                    //verifica qual vai ser o primeiro a jogar
                    if(first==i){
                        
                        System.out.println("[Servidor] O Jogador " + conexoes.get(i).getNome() + " deve começar");
                        isFirst = new Boolean(true);
                    
                    }else{
                    
                        isFirst = new Boolean(false);
                    
                    }
                    
                    //add o boolean de quem começa
                    pacoteStart.addContainer(isFirst);
                    
                    trataPacote(pacoteStart, conexoes.get(i));
                    
                    pause(500);
                }
                
                
                System.out.println("FINISH START");
                //apenas para teste
                
                //Thread principal aguarda [TEMPORARIO]
                while(true){
                    
                    try{
                        Thread.sleep(10000);
                    }catch(InterruptedException ex){
                        ex.printStackTrace();
                    }
                }
            }
            
            Pacote pacote = new Pacote(Escopo.CHAT);
            pacote.addContainer("[Servidor] Faltam " +(this.NUMERO_DE_JOGADORES_POR_JOGO-conexoes.size()) + " jogadores!\n");
            trataPacote(pacote,null);
        }
    }
    
    public synchronized void pause(int tempo){
        
        try {
            Thread.sleep(tempo);
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        }
    }
    
    public synchronized void trataPacote(Pacote pacote, Conexao conex){
        
        Escopo escopo = pacote.getEscopo();
        
        if(escopo == Escopo.CHAT){
            
            for (Conexao conexao : conexoes) {
                
                try {
                                                          
                    new ObjectOutputStream(conexao.getSocket().getOutputStream()).writeObject(pacote);
                    
                } catch (IOException ex) {
                    
                    ex.printStackTrace();
                }
            }
        }else if(escopo == Escopo.START){
            
            try {
                new ObjectOutputStream(conex.getSocket().getOutputStream()).writeObject(pacote);
            } catch (IOException ex) {
                ex.printStackTrace();
            }
            
        }else if(escopo == Escopo.GET_JOGADA){
            
            System.out.println("GET_JOGADA " + conex.getNome());
            
            //tranforma o pacote em uma jogada
            Jogada jogada = new Jogada(conex, (Carro) pacote.getContainer().get(0));
            jogadas.add(jogada);
            
            //espera todos jogarem
            if((jogadas.size()) == (conexoes.size())){
                
                //Class responsavel por determinar o ganhador
                JogadaService jogadaService = new JogadaService();
                //determina ganahdor
                Jogada jogadaWinner = jogadaService.getWinner(jogadas, opcao);
                
                //envia msg dizendo quem ganhou
                Pacote pacoteMsgWinner = new Pacote(Escopo.CHAT);
                pacoteMsgWinner.addContainer("[Servidor] O Jogardor " + jogadaWinner.getConexao().getNome() + " ganhou a rodada!\n");
                trataPacote(pacoteMsgWinner, null);
                
                //Array dos carros da jogada na qual o ganhador deve levar
                ArrayList<Carro> carrosNaJogada = new ArrayList<>();
                carrosNaJogada.add(jogadaWinner.getCarro());
                
                //remove jogador ganahdor do array de jogadas
                jogadas.remove(jogadaWinner);
                
                //sobra apena so jogadores perdedores
                for (Jogada jogadaPerdedor : jogadas) {
                    
                    //pega carro jogador perdedor
                    carrosNaJogada.add(jogadaPerdedor.getCarro());
                    
                    //boolean dizendo que ele perdeu
                    Boolean isWinner = new Boolean(false);
                    Pacote pacoteLoser = new Pacote(Escopo.JOGADA_RETURN);
                    pacoteLoser.addContainer(isWinner);
                    
                    try {
                        //envia pacote ao jogador perdedor
                        new ObjectOutputStream(jogadaPerdedor.getConexao().getSocket().getOutputStream()).writeObject(pacoteLoser);
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }
                }
               
                //pacote jogador ganhador
                Pacote pacoteWinner = new Pacote(Escopo.JOGADA_RETURN);
                pacoteWinner.addContainer(new Boolean(true));
                pacoteWinner.addContainer(carrosNaJogada);
                
                try {
                    //envia pacote ao jogador ganhador
                    new ObjectOutputStream(jogadaWinner.getConexao().getSocket().getOutputStream()).writeObject(pacoteWinner);
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
                
                
            }
            
        }else if(escopo == Escopo.JOGADA){
            
            System.out.println("JOGADA " + conex.getNome());
            
            //elimina jogadas
            jogadas = new ArrayList<>();
            
            //converte pacote em jogada
            Jogada jogada = new Jogada(conex,(Carro) pacote.getContainer().get(1));
            opcao = (String) pacote.getContainer().get(0);
            
            //add jogada no array
            jogadas.add(jogada);
            
            //avisa os jogadores qual opção escolhida
            Pacote pacoteMsgWinner = new Pacote(Escopo.CHAT);
            pacoteMsgWinner.addContainer("[Servidor] O Jogardor " + jogada.getConexao().getNome() + " escolheu a opção " + opcao + "\n");
            trataPacote(pacoteMsgWinner, null);
            
            
            //Para todas as conexões
            for (Conexao conexao : conexoes) {
                
                //menos a conexão que jogou
                if(!conex.equals(conexao)){
                    try {
                        
                        new ObjectOutputStream(conexao.getSocket().getOutputStream()).writeObject(new Pacote(Escopo.GET_JOGADA));
                        
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }
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
                    
                    //pega nick do jogador
                    if(pacote.getEscopo()==Escopo.CHAT && cliente.getNome().equals("")){
                        String msg = (String) pacote.getContainer().get(0);
                        String[] split = msg.split(" ");
                        cliente.setNome(split[2]);
                        
                    }
                    
                    trataPacote(pacote, this.cliente);
                    
                } catch (IOException ex) {
                    
                    //Caso o jogador tenha se desconectado
                    
                    String nome  = cliente.getNome();
                    conexoes.remove(this.cliente);
                    
                    String msgOut = "[Servidor] O jogador " + nome + " desconectou-se\n";
                    Pacote pacote = new Pacote(Escopo.CHAT);
                    pacote.addContainer(msgOut);
                    
                    trataPacote(pacote, this.cliente);
                    
                    throw  new RuntimeException("Jogador " + nome + " desconectou.");
                    
                } catch (ClassNotFoundException ex) {
                    ex.printStackTrace();
                }
            }
        }
        
    }
    
    public ArrayList<Carro> carregaCartas() {
        ArrayList<Carro> cartas = new ArrayList<Carro>();

        //Titulo de colunas: Modelo;Marca;Velocidade;Peso KG;Cilindrada cm³;Torque Kgfm;Potência  CV; Preço
        String strCarros = "Ecosport SE 2.0 PowerShift;Ford;180;1289;1999;19.7;147;75900.00;"
                + "F-1000 3.9 CS;Ford;123;2080;3922;25.3;86;23772.00;"
                + "Jeep 2.3;Ford;113;1176;2300;17;91;13300.00;"
                + "Fusion Hybrid 2.0;Ford;180;1717;1999;18;143;142000.00;"
                + "Ka SEL 1.5;Ford;181;1034;1499;14.9;110; 44990.00;"
                + "Versa Unique 1.6;Nissan;187;1088;1598;15.1;111;54990.00;"
                + "March SV 1.0;Nissan;167;964;999;10;77; 40990.00;"
                + "GT-R 3.8 V6 biturbo;Nissan;310;1740;3799;60;480;330000.00;"
                + "Pathfinder LE 2.5 Turbo;Nissan;174;2250;2488;41.1;174;73019.00;"
                + "300ZX 3.0 V6 biturbo;Nissan;225;1610;2960;63.2;300;55156.00;"
                + "Camaro SS;Chevrolet;250;1790;6162;56.7;406;181520.00;"
                + "Corvette Z06;Chevrolet;330;1598;6162;89.9;659;500000.00;"
                + "Omega Fittipaldi;Chevrolet;235;1758;3564;36.7;292;77407.00;"
                + "Cruze LT;Chevrolet;203;1387;1796;18.9;144;73500.00;"
                + "Malibu LTZ;Chevrolet;194;1582;2384;22.1;1713;49218.00;"
                + "ix35;Hyundai;180;1500;1998;21.8;178;98950.00;"
                + "Santa Fe;Hyundai;200;1800;3342;32.4;270;131100.00;"
                + "Veracruz GLS;Hyundai;215;1965;3778;36.2;270;69160.00;"
                + "Veloster;Hyundai;195;1170;1591;16.1;128;58965.00;"
                + "HB20 Spicy;Hyundai;188;1000;1591;16.5;128;49975.00;"
                + "Roadster 3.0;Jaguar;260;1597;2995;45.9;340;363100.00;"
                + "XF R 5.0;Jaguar;250;1987;5000;69.3;551;456783.00;"
                + "F-Type Coupé 3.0;Jaguar;260;1577;2995;46.9;381;426300.00;"
                + "XJ Supersport 5.0;Jaguar;250;1775;5000;63.7;510;555800.00;"
                + "XJ220 3.5;Jaguar;351;1375;3498;65.7;549;930000.00;"
                + "Veyron 8.0;Bugatti;406;1888;7993;127.1;1001;3430000.00;"
                + "Veyron Super Sport 8.0;Bugatti;415;1888;7993;153;1200;6500000.00;"
                + "EB 110 3.5;Bugatti;341;1550;3499;62.2;561;1200000.00;"
                + "Veyron Grand Sport 8.0;Bugatti;407;1968;7993;127.1;1001;4500000.00;"
                + "458 Speciale 4.5;Ferrari;325;1325;4499;55.1;605;2300000.00;";
        StringTokenizer leitor = new StringTokenizer(strCarros, ";");

        Carro aux = null;
        int id = 1;
        while (leitor.hasMoreTokens()) {
            aux = new Carro();
            aux.setIdCarta(id++);
            aux.setModelo((String) leitor.nextElement());
            aux.setMarca((String) leitor.nextElement());
            aux.setVelocidadeMaxima(Integer.parseInt(leitor.nextElement().toString()));
            aux.setPeso(Integer.parseInt(leitor.nextElement().toString()));
            aux.setCilindradasMotor(Integer.parseInt(leitor.nextElement().toString()));
            aux.setTorque(Double.parseDouble(leitor.nextElement().toString()));
            aux.setPotencia(Integer.parseInt(leitor.nextElement().toString()));
            aux.setPreco(Double.parseDouble(leitor.nextElement().toString()));
            cartas.add(aux);
//            System.out.println("carta=" + aux.toString());
        }
        return cartas;
    }
    
}
