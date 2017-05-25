package com.ipartek.formacion.ijimenez.dal;

public class CampoNoValidoDALException extends DALException {

	private static final long serialVersionUID = 5371466154809794327L;

	public CampoNoValidoDALException() {
		super();
	}

	public CampoNoValidoDALException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public CampoNoValidoDALException(String message, Throwable cause) {
		super(message, cause);
	}

	public CampoNoValidoDALException(String message) {
		super(message);
	}

	public CampoNoValidoDALException(Throwable cause) {
		super(cause);
	}

}
