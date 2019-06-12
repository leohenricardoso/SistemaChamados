package br.com.sistemachamados.util;

import br.com.sistemachamados.domain.Status;

public class StatusColor {
	public String createLabel(){
		Status status = new Status();
		
		int i = Integer.valueOf(status.getIdStatus().toString());
		
	    switch (i){

	    case 1:
	        return "concluido";

	    case 2:
	        return "em-andamento";

	    case 3:
	        return "cancelado";

	    default: 
	        return "default";   
	    }
	}
}
