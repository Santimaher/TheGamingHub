package org.tfgdp2.com.exception;

@SuppressWarnings("serial")
public class DangerException extends Exception {
	public DangerException() {
		super();
	}

	public DangerException(String mensaje) {
		super(mensaje);
	}

}
