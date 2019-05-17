package br.com.bv.juliocesar.utils;

import java.util.HashMap;
import java.util.Map;

public class ABC {
	private static Map<Character, Integer> valuesByLetters;
	private static Map<Integer, Character> lettersByValues;
	
	public ABC() {
		String abc = "abcdefghijklmnopqrstuvwxyz";
		initValuesByLetters(abc);
		initLettersByValues(abc);
	}

	private void initValuesByLetters(String abc) {		
		valuesByLetters = new HashMap<>();
		for (int i=0; i<abc.length(); i++) {
			valuesByLetters.put(abc.charAt(i), (i+1));
		}		
	}

	private void initLettersByValues(String abc) {
		lettersByValues = new HashMap<>();
		for (int i=0; i<abc.length(); i++) {
			lettersByValues.put((i+1), abc.charAt(i));
		}
	}

	public Integer getPositionByLetter(Character letter) {
		return valuesByLetters.get(letter);
	}

	public Character getLetterByPosition(Integer position) {
		return lettersByValues.get(position);
	}
	
	public int getMaxPosition() {
		return lettersByValues.size();
	}
	
	public boolean hasLetter(Character letter) {
		return valuesByLetters.containsKey(letter);
	}

}
