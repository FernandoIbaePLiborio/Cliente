package io.github.profile.dao.exeption;

import javax.ejb.ApplicationException;

import org.apache.log4j.Logger;

@ApplicationException(rollback = true)
public class DAOException extends Exception {
	
  private static final long serialVersionUID = -5368901765487861805L;
  private String code;
  private static Logger LOG = Logger.getLogger(BusinessException.class);
 
  public DAOException(Throwable cause) {	
    super(cause);
    LOG.error(cause);
  }
 
  public DAOException(String message) {
    super(message);
    LOG.error(String.format("\n Mensagem: %s", new Object[] { this.code, message }));
    setCodigo(this.code);
  }
 
  public DAOException(String code, String message) {
    super(message);
    LOG.error(String.format("\nC�digo: %s \n Mensagem: %s", new Object[] { code, message }));
    setCodigo(code);
  }
 
  public DAOException(String code, Throwable cause) {
    super(cause);
    LOG.error(String.format("\nC�digo: %s \n", new Object[] { code }), cause);
    setCodigo(code);
  }
 
  public DAOException(String code, String message, Throwable cause) {
    super(message, cause);
    LOG.error(String.format("\nC�digo: %s \n Mensagem: %s", new Object[] { code, message }), cause);
    setCodigo(code);
  }

 
  public String getCodigo() { return this.code; }


 
  private void setCodigo(String code) { this.code = code; }
}