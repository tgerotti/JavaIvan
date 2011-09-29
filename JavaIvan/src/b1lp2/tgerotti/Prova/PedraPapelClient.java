package b1lp2.tgerotti.Prova;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

public class PedraPapelClient implements Runnable {
	Socket s; 
	PedraPapelServer server;
	String nome;
	PrintStream msg ;
	SimpleDateFormat dataHora;
	Date data;
	static final int PEDRA = 1;
	static final int PAPEL = 2;
	static final int TESOURA = 3;

	public PedraPapelClient(Socket s, PedraPapelServer server) throws IOException{
		this.s = s;
		this.server = server;
		msg = new PrintStream(getS().getOutputStream());
		this.dataHora = new SimpleDateFormat("dd/MM - HH:mm:ss");

	}

	@Override
	public void run() {

		boolean controle = true;
		boolean nomeIgualContinua = true;
		
		envia("Jogo iniciado!\n");
		envia("Quem chegar a 5 vitorias ganha o jogo!\n\n");
		envia("Digite seu nome: ");
						
		data = new Date();

		try {
			
			BufferedReader lido = new BufferedReader(new InputStreamReader(getS().getInputStream()));
			setNome(lido.readLine());
			
			nomeIgualContinua = server.verificaCliente(this.getNome());
			
			while(nomeIgualContinua){
				envia("Escolha outro nome, nome ja exitente!\n");
				envia("Digite seu nome: ");
				setNome(lido.readLine());
				nomeIgualContinua = server.verificaCliente(this.getNome());
				
			}
					
			envia("Ola "+getNome()+", seja bem vindo!\n");

			server.addCliente(this);//teminou as verifica��o cliente aceito
			
			server.enviaAll(getNome()+" entrou: " + dataHora.format(data));//data e hora
			int scoreCom = 0;
			int scoreHum = 0;
			Random nuRdn = new Random();
			int escolhaHum;

			while(controle){
				envia("\n\n");
				envia("Escolha:\n 1-Pedra 2-Papel 3-Tesoura\n\n");
				lido = new BufferedReader(new InputStreamReader(getS().getInputStream()));
				
				try{
					escolhaHum = Integer.parseInt(lido.readLine());

				} catch (NumberFormatException e) {
					envia("Ente com um numero valido!");
					continue;
				}
				
				if(escolhaHum == 0 || escolhaHum > 3){
					envia("Ente com um numero valido!");
					continue;
				}
				
				int escolhaCom = nuRdn.nextInt(4);
				
				while (escolhaCom == 0){
					escolhaCom = nuRdn.nextInt(4);
				}
				envia("O computador " + escolha(escolhaCom) + "\n");
				
				switch (escolhaHum){
				
				case PEDRA:
					if( escolhaCom == PAPEL){
						scoreCom++;
						envia("Um ponto para o Computador!");
											
					}else{
						if(escolhaCom == TESOURA){
							scoreHum++;
							envia("Um ponto para " + getNome());
							
						}else{
							envia("Empate !!!");
						}
					}
					break;
				
				case PAPEL:
					if( escolhaCom == PEDRA){
						scoreHum++;
						envia("Um ponto para " + getNome());
					}else{
						if(escolhaCom == TESOURA){
							scoreCom++;
							envia("Um ponto para o Computador!");
						}else{
							envia("Empate !!!");
						}
					}
					
					break;
				
				case TESOURA:
					if( escolhaCom == PAPEL){
						scoreHum++;
						envia("Um ponto para " + getNome());
					}else{
						if(escolhaCom == PEDRA){
							scoreCom++;
							envia("Um ponto para o Computador!");
						}else{
							envia("Empate !!!");
						}
					}
					
					break;
				}
				//verifica se alguem ganhou 5
				if(scoreCom == 5 || scoreHum == 5){
					envia("O Jogo acabou!\n");
					controle = false;
					
					if(scoreCom == 5){
						envia("O Computador venceu!\n "+ getNome()+ " "+scoreHum+" X 5 Computador" );
						fecha(lido);
					}else{
						envia("Voce venceu!\n "+ getNome()+ " 5 X " + scoreCom + " Computador" );
						fecha(lido);
					}
				}else{
					envia("Escore parcial:\n Computador "+scoreCom+" X "+scoreHum+ " " +getNome());
				}
				

			}
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println("Erro principal");
			server.delCliente(this.getNome());

		} catch (Throwable e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("Erro Stream");
			server.delCliente(this.getNome());
		}

	}

	private String escolha(int escolha) {
		String st;
		
		if (escolha == 1){
			st = "escolheu Pedra!";
		}else{
			if(escolha ==2){
				st = "escolheu Papel!";
			}else{
				st = "escolheu Tesoura!";
			}
		}
		return st;
	}

	private void fecha(BufferedReader lido) throws IOException {
		envia("Digite enter para fechar!");
		lido.readLine();
		server.delCliente(this.getNome());
		msg.close();
	}


	public Socket getS() {
		return s;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public void envia(String mensagem) {
		msg.println(mensagem);
		msg.flush();
	}
}
