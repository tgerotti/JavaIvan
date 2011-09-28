package b1lp2.tgerotti.Carro;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.Map.Entry;


public class InterCarro implements Serializable{

	public InterCarro(Map<String, Carro> carrosPlaca,
		Map<String, Carro> carrosChassi) {
		super();
		this.carrosPlaca = carrosPlaca;
		this.carrosChassi = carrosChassi;
	}
	public InterCarro() {

	}

	Map <String, Carro> carrosPlaca = new TreeMap <String, Carro>();
	Map <String, Carro> carrosChassi = new TreeMap <String, Carro>();

	
	public void add(Map carros){
		//insere nos dois arrays para manter a igualdade
		carrosPlaca = carros;
		
		for(Entry<String, Carro> e: carrosPlaca.entrySet() ){
			carrosChassi.put(e.getValue().getChassi(), e.getValue());			
		}
	}

	public void add(Carro c){

		carrosPlaca.put(c.getPlaca(), c);
		carrosChassi.put(c.getChassi(), c);
		//insere nos dois arrays para manter a igualdade
	}

	public void remove(Carro c){

		carrosPlaca.remove(c.getPlaca());
		carrosChassi.remove(c.getChassi());
		//retira nos dois arrays para manter a igualdade
	}

	public Carro getByPlaca(String placa){

		return carrosPlaca.get(placa);
	}
	public Carro getByChassi(String chassi){

		return carrosChassi.get(chassi);
	}

	public Map getAll (){

		return carrosPlaca;
	}

	public void gravaEstado(Map carros, String arqEstado){

		File arq = new File(arqEstado);

		try {
			//FileOutputStream gravaCarros = );
			ObjectOutputStream objCarros = new ObjectOutputStream(new FileOutputStream(arq));
			objCarros.writeObject(carros);
			//gravaCarros.close();
			objCarros.close();

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}



	}

	public Map leEstado(String systemArq){
		File arq = new File(systemArq);

		try {
			FileInputStream leCarrros = new FileInputStream(arq); 
			ObjectInputStream objCarros = new ObjectInputStream(leCarrros);
			Map carrosLidos = (Map) objCarros.readObject();
			leCarrros.close();
			objCarros.close();
			return carrosLidos;

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			System.out.println("Nao existe arquivo de estado");
			return null;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}

	}

}
