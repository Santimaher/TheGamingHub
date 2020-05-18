package org.tfgdp2.com.helper;

import org.tfgdp2.com.exception.DangerException;
import org.tfgdp2.com.exception.InfoException;

public class PRG {

	public static void info(String mensaje, String link) throws InfoException {
		throw new InfoException(mensaje+"@"+link);
	}

	public static void info(String mensaje) throws InfoException {
		throw new InfoException(mensaje+"@"+"/");
	}
	
	public static void error(String mensaje, String link) throws DangerException {
		throw new DangerException(mensaje+"@"+link);
	}

	public static void error(String mensaje) throws DangerException {
		throw new DangerException(mensaje+"@"+"/");
	}
	public static void info(String mensaje, String link,Long idForo) throws InfoException {
		throw new InfoException(mensaje+"@"+link+"@"+idForo);
	}
	public static void error(String mensaje, String link,Long idForo) throws DangerException {
		throw new DangerException(mensaje+"@"+link+"@"+idForo);
	}
	public static void info(String mensaje, String link,Long idForo,Long idEntrada) throws InfoException {
		throw new InfoException(mensaje+"@"+link+"@"+idForo+"@"+idEntrada);
	}
	public static void error(String mensaje, String link,Long idForo,Long idEntrada) throws DangerException {
		throw new DangerException(mensaje+"@"+link+"@"+idForo+"@"+idEntrada);
	}

}
