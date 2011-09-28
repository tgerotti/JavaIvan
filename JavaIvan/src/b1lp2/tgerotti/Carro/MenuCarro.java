package b1lp2.tgerotti.Carro;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Scanner;
import java.util.TreeMap;

public class MenuCarro{
	static final int INCLUIR = 1;
	static final int EXCLUIR = 2;
	static final int PESQUISAR = 3;
	static final int LISTAR = 4;
	static final int ALTERAR = 5;
	static final int SAIR = 9;
	static final String ARQ = "arquivoDeEstado.dat";

	public static void main(String[] args) throws ClassCastException {
		Scanner entrada = new Scanner(System.in);
		DateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
		Date dataCompra = null;
		InterCarro carMetodos = new InterCarro();

		String marca, cor, modelo, placa, chassi;
		int opcao, ano;
		boolean controle = true;
		double valor;
		Map arqLido = carMetodos.leEstado(ARQ);
		//restaura estado se existir arquivo
		if(arqLido != null){
			carMetodos.add(arqLido);
		}
		//

		while(controle){

			System.out.println("Digite \n1-Incluir \n2-Ecluir \n3-Pesquisar \n4-Listar \n5-Alterar \n6-Carrega Dados \n9-Sair");
			opcao = entrada.nextInt();
			if (opcao != 1 && opcao != 2 && opcao != 3 && opcao !=4 && opcao !=5 && opcao !=6 && opcao !=9){
				System.out.println("Opcao invalida!!!");
				continue;
			}

			switch (opcao){
			case INCLUIR:

				System.out.println("Entre com a marca:");
				marca = entrada.next();
				System.out.println("Entre com a cor:");
				cor = entrada.next();
				System.out.println("Entre com o ano:");
				ano = entrada.nextInt();
				System.out.println("Entre com o modelo:");
				modelo = entrada.next();
				System.out.println("Entre com a placa:");
				placa = entrada.next();
				System.out.println("Entre com a chassi:");
				chassi = entrada.next();
				System.out.println("Entre com o valor:");
				valor = entrada.nextDouble();
				System.out.println("Entre com a data da compra");
				//formata data do usu�rio
				try {
					dataCompra = formato.parse(entrada.next());
				} catch (ParseException e) {

					e.printStackTrace();

				}
				Carro c = new Carro(marca, cor, ano, modelo, placa, chassi, valor, dataCompra);
				carMetodos.add(c);
				carMetodos.gravaEstado(carMetodos.getAll(), ARQ);
				break;

			case EXCLUIR:

				System.out.println("Entre com o placa ou chassi do Carro a ser excluido:");
				String excluir = entrada.next();
				try{
					carMetodos.remove(carMetodos.getByPlaca(excluir));
				}catch(NullPointerException e){
					try{
						carMetodos.remove(carMetodos.getByChassi(excluir));
						System.out.println("Carro Excluido!");
					}catch(NullPointerException n){
						System.out.println("Carros nao encontrado!\n"+n+"\n"+e);
					}

				}
				carMetodos.gravaEstado(carMetodos.getAll(), ARQ);
				break;

			case PESQUISAR:

				System.out.println("Entre com o placa ou chassi a ser pesquisada:");
				String pesquisa = entrada.next();

				if( carMetodos.getByPlaca(pesquisa)!= null){
					System.out.println(carMetodos.getByPlaca(pesquisa));
				}else{
					if(carMetodos.getByChassi(pesquisa) != null){
						System.out.println(carMetodos.getByChassi(pesquisa));
					}else{
						System.out.println("Carros nao encontrado! \n");
					}
				}

				break;

			case LISTAR:

				System.out.println("Listando todos os carros\n\n");
				Map allCarros = carMetodos.getAll();

				TreeMap<String, Carro> carrosP = (TreeMap<String, Carro>) allCarros;//placa

				//Lista por Placa
				for(Entry<String, Carro> e: carrosP.entrySet() ){

					System.out.println(e.getValue());
				}


				//				Iterator<Carro> i = carrosP.entrySet().iterator(); 
				//				 
				//				while(i.hasNext()) {
				//					Map.Entry me = (Map.Entry)i.next();
				//					System.out.println(me);
				//					
				//				}

				break;

			case ALTERAR:

				break;
			case SAIR:
				System.out.println("Gravando dados.....");
				carMetodos.gravaEstado(carMetodos.getAll(), ARQ);

				System.out.println("Sistema Finalizado!");
				controle = false;
				break;
			}
		}
	}

}
