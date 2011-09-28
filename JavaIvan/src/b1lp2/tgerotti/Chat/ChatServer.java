package b1lp2.tgerotti.Chat;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Map;
import java.util.TreeMap;

public class ChatServer{
	static final int PORTA = 1999;
	static final String SERVER = "localhost";
	Map <String, ChatCliente> clientes = new TreeMap<String, ChatCliente>();
	

	public static void main(String[] args){
		ChatServer server = new ChatServer();
		server.executa();

	}

	private void executa() {
		ServerSocket servidor;
		try {
			servidor = new ServerSocket(PORTA);

			while(true){

				Socket s = servidor.accept();
				ChatCliente cli = new ChatCliente(s, this);
				Thread t =  new Thread(cli);
				t.start();
				t.interrupt();
			}
		} catch (IOException e) {
			
			e.printStackTrace();
		}
	}


	public void addCliente(ChatCliente cli) {
		clientes.put(cli.getNome(), cli);

	}
	public void delCliente(String cliente) {
	
		clientes.remove(cliente);
	}
	public boolean verificaCliente(String nome){
		boolean controle = false;
		
		if(clientes.size() >= 1){
			try {
				if (clientes.get(nome)!= null){
					controle = true;
				}
				
			} catch (Exception e) {
				controle = true;
				e.printStackTrace();
			}
		}
		return controle;
	}
	
	public void enviaAll(String mensagem) {
		
		for (ChatCliente c : clientes.values()) {
			c.envia(mensagem);
		}
	}


}
