package b1lp2.tgerotti.Chat;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ChatCliente implements Runnable {
	Socket s; 
	ChatServer server;
	String nome;
	PrintStream msg ;
	SimpleDateFormat dataHora;
	Date data;
	

	public ChatCliente(Socket s, ChatServer server) throws IOException{
		this.s = s;
		this.server = server;
		msg = new PrintStream(getS().getOutputStream());
		this.dataHora = new SimpleDateFormat("dd/MM - HH:mm:ss");

	}

	@Override
	public void run() {

		boolean controle = true;
		boolean nomeIgualContinua = true;
		
		envia("Chat iniciado!\n");
		envia("Para finalizar digite 'fim'\n\n");
		envia("Digite seu nome: ");
						
		data = new Date();

		try {
			
			BufferedReader lido = new BufferedReader(new InputStreamReader(getS().getInputStream()));
			setNome(lido.readLine());
			
			nomeIgualContinua = server.verificaCliente(this.getNome());
			
			while(nomeIgualContinua){
				envia("Escolha outro nome, nome ja exite no chat!\n");
				envia("Digite seu nome: ");
				setNome(lido.readLine());
				nomeIgualContinua = server.verificaCliente(this.getNome());
				
			}
					
			envia("Ola "+getNome()+", seja bem vindo!\n");

			server.addCliente(this);//teminou as verificação cliente aceito
			
			server.enviaAll(getNome()+" entrou: " + dataHora.format(data));//data e hora

			while(controle){
				lido = new BufferedReader(new InputStreamReader(getS().getInputStream()));
				String msgString = lido.readLine();

				if (msgString.equals("fim")){
					controle = false;
					envia("Chat finalizado!\n\n");
					server.delCliente(this.getNome());
					server.enviaAll(this.getNome()+" saiu!" + dataHora.format(data));
					msg.close();
				}

				server.enviaAll(getNome()+" diz: "+msgString);

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
			server.enviaAll(this.getNome()+" saiu!" + dataHora.format(data));
		}

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
