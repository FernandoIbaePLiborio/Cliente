package io.github.profile.dao.exeption;

import javax.ejb.ApplicationException;

import org.apache.log4j.Logger;

@ApplicationException(rollback = true)
public class BusinessException extends Exception {
	
  private static final long serialVersionUID = 179009611284247156L;
  private Integer codigo = Integer.valueOf(500);
 
  private String mensagem;
 
  private static Logger LOG = Logger.getLogger(BusinessException.class);
 
  public BusinessException(String menssage) {
    super(menssage);
    this.mensagem = menssage;
    LOG.error(String.format("\n Mensagem: %s", new Object[] { menssage }));
  }
 
  public BusinessException(Throwable cause) {
    super(cause);
    LOG.error(cause);
  }
 
  public BusinessException(Integer codigo, Throwable cause) {
    super(cause);
    this.codigo = codigo;
    LOG.error(String.format("\n Código: %s \n", new Object[] { codigo }), cause);
  }
 
  public BusinessException(String mensagem, Throwable cause) {
    super(mensagem, cause);
    this.mensagem = mensagem;
    LOG.error(String.format("\n Mensagem: %s \n", new Object[] { mensagem }), cause);
  }
 
  public BusinessException(Integer codigo, String mensagem) {
    super(String.format("\n Código: %s\n Mensagem: %s", new Object[] { codigo, mensagem }));
    this.codigo = codigo;
    this.mensagem = mensagem;
    LOG.error(String.format("\n Código: %s\n Mensagem: %s", new Object[] { codigo, mensagem }));
  }

  public BusinessException(String codigo, String mensagem) { this(Integer.valueOf(codigo), mensagem); }
 
  public BusinessException(Integer codigo, String mensagem, Throwable cause) {
    super(String.format("\n Código: %s\n Mensagem: %s", new Object[] { codigo, mensagem }), cause);
    this.codigo = codigo;
    this.mensagem = mensagem;
    LOG.error(String.format("\n Código: %s\n Mensagem: %s", new Object[] { codigo, mensagem }), cause);
  }
 
  public Integer getCodigo() { return this.codigo; }
 
  public String getMenssagem() { return getMessage(); }
 
  public String getMensagem() { return this.mensagem; }
}
