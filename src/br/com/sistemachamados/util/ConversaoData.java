package br.com.sistemachamados.util;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;




public class ConversaoData {		
	public String converteParaUSA(String s) {
	    String dia = s.substring(0, 2);
	    String mes = s.substring(3, 5);
	    String ano = s.substring(6, 10);
	    
	    return ano + "-" + mes + "-" + dia;
	}

	public String converte(String s) {	    
	    String ano = s.substring(0, 4);
	    String mes = s.substring(5, 7);
	    String dia = s.substring(8, 10);
	    
	    return dia + "/" + mes + "/" + ano;
	    
	}
	
	public static final String dataAtualInserir() {        
		Date dataAtual = new Date();
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String dataFormatada = dateFormat.format(dataAtual);		
        return dataFormatada;
	}
	
	public static final String horaAtualInserir() {        
		Date dataAtual = new Date();
        DateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
        String dataFormatada = dateFormat.format(dataAtual);		
        return dataFormatada;
	}
}
