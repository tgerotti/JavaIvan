package b1lp2.tgerotti.Prova;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Map;
import java.util.TreeMap;

public class PedraPapelServer{
	static final int PORTA = 1999;
	static final String SERVER = "localhost";
	Map <String, PedraPapelClient> clientes = new TreeMap<String, PedraPapelClient>();
	

	public static void main(String[] args){
		PedraPapelServer server = new PedraPapelServer();
		server.executa();

	}

	private void executa() {
		ServerSocket servidor;
		try {
			servidor = new ServerSocket(PORTA);

			while(true){

				Socket s = servidor.accept();
				PedraPapelClient cli = new PedraPapelClient(s, this);
				Thread t =  new Thread(cli);
				t.start();
				t.interrupt();
			}
		} catch (IOException e) {
			
			e.printStackTrace();
		}
	}


	public void addCliente(PedraPapelClient cli) {
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
		
		for (PedraPapelClient c : clientes.values()) {
			c.envia(mensagem);
		}
	}


}
