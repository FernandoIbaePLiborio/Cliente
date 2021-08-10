package io.github.profile.service.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Objects;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import org.apache.commons.collections.CollectionUtils;

import io.github.profile.dao.ClienteDao;
import io.github.profile.dao.exeption.BusinessException;
import io.github.profile.dao.exeption.CampoExcedeLimiteException;
import io.github.profile.dao.exeption.CampoNaoInformadoException;
import io.github.profile.dao.exeption.CamposObrigatoriosException;
import io.github.profile.dao.exeption.DAOException;
import io.github.profile.model.Cliente;
import io.github.profile.service.ClienteService;

@Stateless(name = ClienteService.JNDI_NAME, mappedName = ClienteService.JNDI_NAME)
public class ClienteServiceImpl implements ClienteService {
	
	@EJB
	private ClienteDao dao;
	
	public void salvar(final Cliente cliente) throws BusinessException {
		
		try {
			this.validarCliente(cliente);
			
			if(Objects.isNull(cliente.getId())) {
				this.dao.create(cliente);
			} else {
				this.dao.update(cliente);
			}					
		} catch (final DAOException e) {
			throw new BusinessException(e) ;
		}
	}
		
	public Collection<Cliente> pesquisar() throws BusinessException {
		
		try {
			return this.dao.all();
		} catch (final DAOException e) {
			throw new BusinessException(e);
		}
	}
	
	public Collection<Cliente> buscarCliente(final String cpf) throws BusinessException {
		
		try {
			Collection<Cliente> clientes = new ArrayList<>();
			clientes = this.dao.buscarCliente(cpf);
			return clientes;
		} catch (DAOException e) {
			throw new BusinessException(e);
		}
	}
	
	public void delete(final Long id) throws BusinessException {
		
		try {
				this.dao.delete(id);
		} catch (final DAOException e) {
			throw new BusinessException(e) ;
		}
	}
	
	private void validarCliente(final Cliente cliente) throws CampoNaoInformadoException, CampoExcedeLimiteException, CamposObrigatoriosException {
		
		if(Objects.isNull(cliente)) {
			
			throw new CampoNaoInformadoException();
		}
		if(!Objects.isNull(cliente.getCpf()) && (cliente.getCpf().length() > 11 || cliente.getTelefone().length() > 11)) {
			
			throw new CampoExcedeLimiteException();
		}
		if (Objects.isNull(cliente.getCpf()) || Objects.isNull(cliente.getNome()) || Objects.isNull(cliente.getTelefone())) {
			
			throw new CamposObrigatoriosException();
		}
	}

}
