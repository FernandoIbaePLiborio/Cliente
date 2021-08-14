package io.github.profile.vo;

import java.util.Collection;
import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

import io.github.profile.model.Cliente;

@XmlRootElement(name = "ClienteVO")
public class ClienteVO extends AbstractVO<Cliente> {

	/* Construtores*/
    public ClienteVO() {
        super();
    }

    public ClienteVO(boolean ok, String mensagem) {
        super(ok, mensagem);
    }
    
    public ClienteVO(boolean ok, Throwable throwable, String mensagem) {
    	super(ok, throwable, mensagem);
    }

    public ClienteVO(boolean ok, String mensagem, Cliente cliente) {
        super(ok, mensagem, cliente);
    }

    public ClienteVO(boolean ok, String mensagem, List<Cliente> lista) {
        super(ok, mensagem, lista);
    }

    public ClienteVO(boolean ok, String mensagem, Collection<Cliente> lista) {
    	super(ok, mensagem, lista);
	}

	/* M�todos de acesso */
    public Cliente getCliente() {
        return getObjeto();
    }

    public void setCliente(Cliente cliente) {
        setObjeto(cliente);
    }
}
