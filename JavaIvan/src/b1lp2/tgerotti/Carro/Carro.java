package b1lp2.tgerotti.Carro;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class Carro implements Cloneable, Serializable{

	DateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
	private String marca, cor, modelo, placa, chassi;
	private int ano;
	private double valor;
	private Date dataCompra;

	public Carro(String marca, String cor, int ano, String modelo, String placa, String chassi,
			double valor, Date dataCompra) {
		super();
		this.marca = marca;
		this.cor = cor;
		this.ano = ano;
		this.modelo = modelo;
		this.placa = placa;
		this.chassi = chassi;
		this.valor = valor;
		this.dataCompra = dataCompra;
	}
	public Carro() {
		
	}

	public String getPlaca() {
		return placa;
	}
	public void setPlaca(String placa) {
		this.placa = placa;
	}

	public String getMarca() {
		return marca;
	}
	public void setMarca(String marca) {
		this.marca = marca;
	}
	public String getCor() {
		return cor;
	}
	public void setCor(String cor) {
		this.cor = cor;
	}
	public int getAno() {
		return ano;
	}
	public void setAno(int ano) {
		this.ano = ano;
	}
	public String getModelo() {
		return modelo;
	}
	public void setModelo(String modelo) {
		this.modelo = modelo;
	}
	public double getValor() {
		return valor;
	}
	public void setValor(double valor) {
		this.valor = valor;
	}
	public Date getDataCompra() {
		return dataCompra;
	}
	public void setDataCompra(Date dataCompra) {
		this.dataCompra = dataCompra;
	}

	public String getChassi() {
		return chassi;
	}

	public void setChassi(String chassi) {
		this.chassi = chassi;
	}

	@Override
	public Object clone() throws CloneNotSupportedException{

		return super.clone();
	} 

	
	@Override
	public String toString() {
	
		return "Carro: " + getMarca() + "\n" +
				"Modelo: " + getModelo() + "\tCor: " + getCor() + 
				"\tPlaca: " + getPlaca()+ "\tChassi: "+ getChassi()+ 
				"\tAno de Fabricação: " + getAno()+"\tValor de compra: R$ " + 
				getValor() + "\tData da Compra: " + formato.format(getDataCompra())+"\n";
	}
	
	//inicio
	//Metodos Inserir, Pesquisar, listar, deletar, alterar
	Map <String, Carro> carrosPlaca = new TreeMap <String, Carro>();
	Map <String, Carro> carrosChassi = new TreeMap <String, Carro>();
	
	public void add(List carros){

		carrosPlaca = (Map<String, Carro>) carros.get(0);
		carrosChassi= (Map<String, Carro>) carros.get(1);
		
		//insere nos dois arrays para manter a igualdade
	}
	
	public void add(Carro c){

		carrosPlaca.put(c.getPlaca(), c);
		carrosChassi.put(c.getChassi(), c);
		//insere nos dois arrays para manter a igualdade
	}

	public void remove(Carro c){

		carrosPlaca.remove(c);
		carrosChassi.remove(c);
		//retira nos dois arrays para manter a igualdade
	}

	public Carro getByPlaca(String placa){

		return carrosPlaca.get(placa);
	}
	public Carro getByChassi(String chassi){

		return carrosChassi.get(chassi);
	}

	public List getAll (){
		
		List carros = new ArrayList();

		 carros.add(carrosPlaca);
		 carros.add(carrosChassi);
		 		 
		 return carros;
	}
	
	public void gravaEstado(List carros, String arqEstado){
		
		File arq = new File(arqEstado);
		
		try {
			FileOutputStream gravaCarros = new FileOutputStream(arq);
			ObjectOutputStream objCarros = new ObjectOutputStream(gravaCarros);
			objCarros.writeObject(carros);
			gravaCarros.close();
			objCarros.close();
			
						
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
	}
	
	public List leEstado(String systemArq){
		File arq = new File(systemArq);
		
						
		try {
			FileInputStream leCarrros = new FileInputStream(arq); 
			ObjectInputStream objCarros = new ObjectInputStream(leCarrros);
			List carrosLidos = (List) objCarros.readObject();
			leCarrros.close();
			objCarros.close();
			return carrosLidos;
						
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				System.out.println("Não existe arquivo de estado");
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
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ano;
		result = prime * result + ((chassi == null) ? 0 : chassi.hashCode());
		result = prime * result + ((cor == null) ? 0 : cor.hashCode());
		result = prime * result
				+ ((dataCompra == null) ? 0 : dataCompra.hashCode());
		result = prime * result + ((formato == null) ? 0 : formato.hashCode());
		result = prime * result + ((marca == null) ? 0 : marca.hashCode());
		result = prime * result + ((modelo == null) ? 0 : modelo.hashCode());
		result = prime * result + ((placa == null) ? 0 : placa.hashCode());
		long temp;
		temp = Double.doubleToLongBits(valor);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Carro other = (Carro) obj;
		if (ano != other.ano)
			return false;
		if (chassi == null) {
			if (other.chassi != null)
				return false;
		} else if (!chassi.equals(other.chassi))
			return false;
		if (cor == null) {
			if (other.cor != null)
				return false;
		} else if (!cor.equals(other.cor))
			return false;
		if (dataCompra == null) {
			if (other.dataCompra != null)
				return false;
		} else if (!dataCompra.equals(other.dataCompra))
			return false;
		if (formato == null) {
			if (other.formato != null)
				return false;
		} else if (!formato.equals(other.formato))
			return false;
		if (marca == null) {
			if (other.marca != null)
				return false;
		} else if (!marca.equals(other.marca))
			return false;
		if (modelo == null) {
			if (other.modelo != null)
				return false;
		} else if (!modelo.equals(other.modelo))
			return false;
		if (placa == null) {
			if (other.placa != null)
				return false;
		} else if (!placa.equals(other.placa))
			return false;
		if (Double.doubleToLongBits(valor) != Double
				.doubleToLongBits(other.valor))
			return false;
		return true;
	}
}