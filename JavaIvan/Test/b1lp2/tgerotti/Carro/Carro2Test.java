package br.edu.ifsp.spo.b1lp2.tgerotti.aula1;

import static org.junit.Assert.*;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.junit.Test;

public class Carro2Test {

	@Test
	public void testHashCode() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetMarca() {
		fail("Not yet implemented");
	}

	@Test
	public void testSetMarca() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetCor() {
		fail("Not yet implemented");
	}

	@Test
	public void testSetCor() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetAno() {
		fail("Not yet implemented");
	}

	@Test
	public void testSetAno() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetModelo() {
		fail("Not yet implemented");
	}

	@Test
	public void testSetModelo() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetValor() {
		fail("Not yet implemented");
	}

	@Test
	public void testSetValor() {
		fail("Not yet implemented");
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
		Carro2 carro = new Carro2();
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
		Carro2 carro = new Carro2();
		carro.setDataCompra(data);									
		
		//recebe o get e converte para o formato especificado
		dataConvertida = formato.format(carro.getDataCompra());			
		//JOptionPane.showMessageDialog(null, dataConvertida);
		
		assertEquals(stringData, dataConvertida);

	}

	@Test
	public void testClone() throws CloneNotSupportedException {
		Carro2 c1 =  new Carro2();
		c1.setMarca("X");
		c1.setDataCompra(new Date());
		
		Carro2 c2 = (Carro2) c1.clone();
		assertEquals(c1, c2);
		assertNotSame(c1, c2);//e identico mas nao o mesmo
		
		assertNotSame(c1.getDataCompra(), c2.getDataCompra());
	}

	@Test
	public void testEqualsObject() {
		fail("Not yet implemented");
	}

}
