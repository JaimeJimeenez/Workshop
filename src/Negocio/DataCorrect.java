package Negocio;

public class DataCorrect {
	public static boolean stringCorrecto(String cadena) {
		if (cadena.length() == 0)
			return false;

		for (int i = 0; i < cadena.length(); i++) {
			if (!Character.isLetter(cadena.charAt(i)))
				return false;
		}

		return true;
	}

	public static boolean stringSoloDigitosCorrecto(String cadena) {
		if (cadena.length() == 0)
			return false;

		for (int i = 0; i < cadena.length(); i++) {
			if (!Character.isDigit(cadena.charAt(i)))
				return false;
		}
		return true;
	}
	
	public static boolean stringSoloDigitosCorrecto(String cadena, int longitud) {
		if (cadena.length() != longitud)
			return false;

		for (int i = 0; i < cadena.length(); i++) {
			if (!Character.isDigit(cadena.charAt(i)))
				return false;
		}
		return true;
	}

	public static boolean dniCorrecto(String dni) {
		if (dni.length() != 9)
			return false;

		if (Character.isLetter(dni.charAt(0)) || !Character.isLetter(dni.charAt(8)))
			return false;

		int digitos;
		try {
			digitos = Integer.parseInt(dni.substring(0, 8));
		} catch (NumberFormatException e) {
			return false;
		}

		final String dniChars = "TRWAGMYFPDXBNJZSQVHLCKE";

		char letra = dniChars.charAt(digitos % 23);

		if (Character.toUpperCase(dni.charAt(8)) != letra)
			return false;

		return true;
	}

	public static boolean numeroMayorCero(int numero) {
		return numero > 0;
	}

	public static boolean numeroMayorCero(float numero) {
		return numero > 0;
	}

	public static boolean numeroMayorCero(double numero) {
		return numero > 0;
	}
}
