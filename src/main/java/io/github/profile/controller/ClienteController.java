package io.github.profile.controller;

import java.util.Comparator;
import java.util.Objects;
import java.util.stream.Collectors;

import javax.naming.NamingException;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import io.github.profile.dao.exeption.BusinessException;
import io.github.profile.fatory.ApiServiceFactory;
import io.github.profile.model.Cliente;
import io.github.profile.service.ClienteService;
import io.github.profile.vo.ClienteVO;

public class ClienteController {
	
	private static final Logger LOGGER = LogManager.getLogger(ClienteController.class);
	
	ClienteService clienteService;
	
	public ClienteController() {
		try {
			this.clienteService = ApiServiceFactory.getInstance().getService(ClienteService.JNDI_NAME);
		} catch (IllegalArgumentException | NamingException e) {
			LOGGER.error(e);
		}
	}

	public ClienteVO cadastrar(final Cliente cliente) {
		try {
			
			this.clienteService.salvar(cliente);
		} catch (Exception e) {	
			LOGGER.error(e);
			return new ClienteVO(false, e.getMessage());
		}
		return new ClienteVO(true, "Cliente inserido com sucesso: ".concat(cliente.getNome()));
	}

	public ClienteVO pesquisar() {
		ClienteVO clienteVO = new ClienteVO();
		try {
			clienteVO.setOk(Boolean.TRUE);
			clienteVO.setMensagem("Clientes");
			clienteVO.setColecao(this.clienteService.pesquisar().stream().sorted(Comparator.comparingLong(Cliente::getId).reversed()).collect(Collectors.toList()));
			return clienteVO;
		} catch (BusinessException e) {
			LOGGER.error(e);
			return new ClienteVO(false, e.getMessage());
		}
	}
	
	public ClienteVO buscarCliente(String cpf) {
		
		ClienteVO clienteVO = new ClienteVO();
		try {
			Cliente cliente = this.clienteService.buscarCliente(cpf);
			if (Objects.isNull(cliente)) {
				return new ClienteVO(true, "Busca não encontrada!");
			}
			clienteVO.setOk(Boolean.TRUE);
			clienteVO.setMensagem("Cliente encontrado no sistema");
			clienteVO.setCliente(cliente);
			return clienteVO;
		} catch (BusinessException e) {
			LOGGER.error(e);	
			return new ClienteVO(false, e.getMessage());
		}
	}

	public ClienteVO deletarCliente(Long id) {
		try {
			
			this.clienteService.delete(id);
			return new ClienteVO(true, "Cliente excluído com sucesso");
		} catch (Exception e) {	
			LOGGER.error(e);
			return new ClienteVO(false, e.getMessage());
		}
	}
	
}
