package br.com.bv.juliocesar.utils;

import br.com.bv.juliocesar.entity.Answer;

public class AnswerUtil {

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
	
	public static boolean isValid(Answer answer) {

		if (answer!=null 
		&& answer.getNumero_casas()!=null
		&& answer.getToken()!=null
		&& answer.getCifrado()!=null 
		&& answer.getDecifrado()!=null
		&& isInteger(answer.getNumero_casas(), 10)) {
			return true;
			
		}else
			return false;
	}
	
	public static boolean isInteger(String number, int radix) {
		
	    /*try { 
	        Integer.parseInt(s); 
	    } catch(NumberFormatException e) { 
	        return false; 
	    } catch(NullPointerException e) {
	        return false;
	    }*/
		if (number!=null) {
			
			if (number.isEmpty())
				return false;

		    for (int i = 0; i < number.length(); i++) {
		    	
		        if (i==0 && number.charAt(i)=='-') {
		        	
		            if(number.length() == 1)
		            	return false;
		            else 
		            	continue;
		        }
		        
		        if(Character.digit(number.charAt(i),radix) < 0)
		        	return false;
		    }
		    return true;
		
		}
		
		
		return true;
	}
	
	
}