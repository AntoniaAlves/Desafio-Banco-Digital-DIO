package Negocio;

import java.util.List;

import Dominio.Conta;

public class Validacao {
	
	public static boolean isPossivelCadastrarConta(Conta conta) {
		if(!verificarDadosPessoa(conta)) {
			System.out.println("N�o foi poss�vel cadastrar a conta!");
			return false;
		}
		
		return true;
	}
	
	public static boolean verificarDadosPessoa(Conta conta) {
		if(conta == null) {
			System.out.println("Conta n�o foi preenchida.");
			
			return false;
		}
		
		if(conta.getCliente().getNome().isEmpty()) {
			System.out.println("Nome n�o foi preenchido.");
			
			return false;
		}
		
		if(conta.getCliente().getCpf().isEmpty()) {
			System.out.println("CPF n�o foi preenchido.");
			
			return false;
		
		}
		
		if(conta.getCliente().getDataNascimento() == null) {
			System.out.println("Data de Nascimento n�o foi preenchido.");
			
			return false;
		}
		
		if(conta.getNumero().isEmpty()) {
			System.out.println("N� da Conta n�o foi preenchido.");
			
			return false;
		}
		
		return true;
	}
	
	public static boolean isPossivelRealizarOperacaoBancaria(Conta conta, double valor) {
		if(valor <= conta.getSaldo()) {
			return true;
		}else{
			System.out.println("N�o foi poss�vel realizar a opera��o, verifique o valor informado!");
			
			return false;
		}
	}
	
	public static boolean isPossivelRealizarDeposito(double valor) {
		if(valor > 0) {
			return true;
		} else {
			System.out.println("N�o foi poss�vel realizar o dep�sito, verifique o valor informado!");
			
			return false;
		}
	}
	
	public static boolean isPossivelSolicitarCartao(Conta conta) {
		if(conta.getSaldo() > 50) {
			return true;
		}else {
			System.out.println("N�o � poss�vel solicitar um cart�o! Verifique o seu tempo de conta.");
			
			return false;
		}
	}
	
	public static boolean isContaExistente(List<Conta> contas, String cpf) {
		if(!contas.isEmpty()) {
			for (Conta conta : contas) {
				if(conta.getCliente().getCpf().equals(cpf)) {
					return true;
				}
			}
		}
		
		System.out.println("O CPF informado n�o pertence a nenhuma conta!");
		
		return false;
	}
}