package br.com.bv.juliocesar.utils;

import br.com.bv.juliocesar.entity.Answer;

public class AnswerUtil {
	
	public static void setDefaultAttributesValues(Answer msg) {
		msg.setNumero_casas("8");
		msg.setToken("473ea6e3edbecd2f4ba2b9ffdf1fe2522eeb920a");
		msg.setCifrado("rclom i uiv jg pqa ycmabqwva zibpmz bpiv jg pqa ivaemza. dwtbiqzm");
		msg.setDecifrado("");
		msg.setResumo_criptografico("");
	}

	public static String decode(String cifrado, int numeroCasas) {
		
		if (cifrado==null || cifrado.equals("") || numeroCasas<=0)
			return cifrado;
				
		StringBuffer decifrado = new StringBuffer();
		ABC abc = new ABC();
		
		for (int i=0; i<cifrado.length(); i++) {
			char letter = cifrado.charAt(i);
				
			if ( !abc.hasLetter(letter) )
				decifrado.append(letter);
			
			else {
				int position = abc.getPositionByLetter(letter);
				int nextPosition = position - numeroCasas;

				if ( nextPosition <= 0 )
					nextPosition = abc.getMaxPosition() + nextPosition;

				char nextLetter = abc.getLetterByPosition(nextPosition);
				decifrado.append(nextLetter);
			}
		}
		
		return decifrado.toString();
	}
	
	
}