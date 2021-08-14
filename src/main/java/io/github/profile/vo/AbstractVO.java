package io.github.profile.vo;

import java.util.Collection;
import java.util.List;

public abstract class AbstractVO<T> {
	
	private boolean ok;
	private String mensagem;
	private T objeto;
	private List<T> lista;
	private Collection<T> colecao;
	private Throwable throwable;
	
	public AbstractVO() {
		super();
	}
	
	public AbstractVO(boolean ok, String mensagem) {
		super();
        this.ok = ok;
        this.mensagem = mensagem;
	}
	
	public AbstractVO(boolean ok, Throwable throwable, String mensagem) {
		super();
        this.ok = ok;
        this.throwable = throwable;
        this.mensagem = mensagem;
	}
	
	public AbstractVO(boolean ok, String mensagem, T objeto) {
		super();
        this.ok = ok;
        this.mensagem = mensagem;
        this.objeto = objeto;
	}
	
	public AbstractVO(boolean ok, String mensagem, List<T> lista) {
		super();
		this.ok = ok;
		this.mensagem = mensagem;
		this.lista = lista;
	}
	
	public AbstractVO(boolean ok, String mensagem, Collection<T> colecao) {
		super();
		this.ok = ok;
		this.mensagem = mensagem;
		this.colecao = colecao;
	}
	
	public boolean isOk() {
        return ok;
    }

    public void setOk(boolean pOk) {
        ok = pOk;
    }

    public String getMensagem() {
        return mensagem;
    }

    public void setMensagem(String pMensagem) {
        mensagem = pMensagem;
    }

    protected T getObjeto() {
        return objeto;
    }

    protected void setObjeto(T pT) {
        objeto = pT;
    }

    public List<T> getLista() {
        return lista;
    }

    public void setLista(List<T> pLista) {
        lista = pLista;
    }

	public Collection<T> getColecao() {
		return colecao;
	}

	public void setColecao(Collection<T> colecao) {
		this.colecao = colecao;
	}

	public Throwable getThrowable() {
		return throwable;
	}

	public void setThrowable(Throwable throwable) {
		this.throwable = throwable;
	}
	
}
