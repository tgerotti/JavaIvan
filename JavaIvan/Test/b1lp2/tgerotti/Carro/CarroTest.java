package br.edu.ifsp.spo.b1lp2.tgerotti.aula1;

import static org.junit.Assert.*;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.junit.Before;
import org.junit.Test;

public class CarroTest {

	@Before
	public void setUp() throws Exception {
	}


	@Test
	public void testGetMarca() {
		Carro carro = new Carro();
		carro.setMarca("VW");		
		assertEquals("VW", carro.getMarca());
	}

	@Test
	public void testSetMarca() {
		Carro carro = new Carro();
		carro.setMarca("VW");		
		assertEquals("VW", carro.getMarca());
	}

	@Test
	public void testGetCor() {
		Carro carro = new Carro();
		carro.setCor("Azul");
		assertEquals("Azul", carro.getCor());
	}

	@Test
	public void testSetCor() {
		Carro carro = new Carro();
		carro.setCor("Azul");
		assertEquals("Azul", carro.getCor());
	}

	@Test
	public void testGetAno() {
		Carro carro = new Carro();
		carro.setAno(2011);
		assertEquals(2011, carro.getAno());
	}

	@Test
	public void testSetAno() {
		Carro carro = new Carro();
		carro.setAno(2011);
		assertEquals(2011, carro.getAno());
	}

	@Test
	public void testGetModelo() {
		Carro carro = new Carro();
		carro.setModelo("Gol");
		assertEquals("Gol",	carro.getModelo());
	}

	@Test
	public void testSetModelo() {
		Carro carro = new Carro();
		carro.setModelo("Gol");
		assertEquals("Gol",	carro.getModelo());
	}

	
	@Test
	public void testGetValor() {
		Carro carro = new Carro();
		carro.setValor(21000.00);
		assertEquals(21000.00, carro.getValor(),0);
	}

	@Test
	public void testSetValor() {
		Carro carro = new Carro();
		carro.setValor(21000.00);
		assertEquals(21000.00, carro.getValor(),0);
	}

	@Test
	public void testGetDataCompra() {
		//recebe a string de data convertida para setar no objeto 
		Date data = null;
		//string da data que sera convertida
		String stringData = "01/01/2011";	
		//string convertida do getCarro
		String dataConvertida = "";
		
		//Converte o formato da data
		DateFormat formato = new SimpleDateFormat("dd/MM/yyyy");			
		try {
			data = formato.parse(stringData);
		} catch (ParseException ex) {			
			ex.printStackTrace();
		}	
				
		//instancia e seta o objeto carro 
		Carro carro = new Carro();
		carro.setDataCompra(data);									
		
		//recebe o get e converte para o formato especificado
		dataConvertida = formato.format(carro.getDataCompra());			
		//JOptionPane.showMessageDialog(null, dataConvertida);
		
		assertEquals(stringData, dataConvertida);
	}

	@Test
	public void testSetDataCompra() {
		//recebe a string de data convertida para setar no objeto 
		Date data = null;
		//string da data que sera convertida
		String stringData = "01/01/2011";	
		//string convertida do getCarro
		String dataConvertida = "";
		
		//Converte o formato da data
		DateFormat formato = new SimpleDateFormat("dd/MM/yyyy");			
		try {
			data = formato.parse(stringData);
		} catch (ParseException ex) {			
			ex.printStackTrace();
		}	
				
		//instancia e seta o objeto carro 
		Carro carro = new Carro();
		carro.setDataCompra(data);									
		
		//recebe o get e converte para o formato especificado
		dataConvertida = formato.format(carro.getDataCompra());			
		//JOptionPane.showMessageDialog(null, dataConvertida);
		
		assertEquals(stringData, dataConvertida);
	}

	@Test
	public void testEqualsObject() {
		Date data = new Date();
		DateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
		try{
			data = formato.parse("07/08/2011");
		}catch(ParseException ex){
			ex.printStackTrace();
		}
				
		Carro carro1 = new Carro();
		carro1.setAno(2011);
		carro1.setCor("preto");
		carro1.setDataCompra(data);
		carro1.setMarca("GM");
		carro1.setModelo("corsa");	
		carro1.setValor(27000.00);
		
		Carro carro2 = new Carro();
		carro2.setAno(2011);
		carro2.setCor("preto");
		carro2.setDataCompra(data);
		carro2.setMarca("GM");
		carro2.setModelo("corsa");
		carro2.setValor(27000.00);
		// realizado teste retirando a comparacao de valor da classe carro
		//carro2.setValor(27000.01);
		
		assertEquals(carro1, carro2);
	}
	
	//clone
	@Test
	public void testeClone() throws CloneNotSupportedException{
		Carro c1 =  new Carro();
		c1.setMarca("X");
		c1.setDataCompra(new Date());
		
		Carro c2 = (Carro) c1.clone();
		assertEquals(c1, c2);
		assertNotSame(c1, c2);//e identico mas nao o mesmo
		
		assertEquals(c1.getDataCompra(), c2.getDataCompra());
		assertSame(c1.getDataCompra(), c2.getDataCompra());
		
		
		
	}
	

}
