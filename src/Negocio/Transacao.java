package Negocio;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.TimeZone;

import Dominio.CartaoCredito;
import Dominio.Conta;
import Interface.Operacoes;

public class Transacao implements Operacoes {

	@Override
	public void sacar(Conta conta, double valor) {
		if(Validacao.isPossivelRealizarOperacaoBancaria(conta, valor)) {
			double novoSaldo = conta.getSaldo() - valor;
			conta.setSaldo(novoSaldo);
			
			System.out.println("Você sacou: R$ " + valor);
		}
	}

	@Override
	public void depositar(Conta conta, double valor) {
		if(Validacao.isPossivelRealizarDeposito(valor)) {
			double novoSaldo = conta.getSaldo() + valor;
			conta.setSaldo(novoSaldo);
			
			System.out.println("Você depositou: R$ " + valor);
		}
	}

	@Override
	public void transferir(Conta suaConta, Conta contaDestino, double valor) {
		if(Validacao.isPossivelRealizarOperacaoBancaria(suaConta, valor)) {
			sacar(suaConta, valor);
			depositar(contaDestino, valor);
			
			System.out.println("Você transferiu para " + contaDestino.getCliente().getNome() + " o total de: R$ " + suaConta.getSaldo());
			System.out.println("Seu saldo atual da sua conta é: R$ " + suaConta.getSaldo());
		}
	}
	
	@Override
	public void solicitarCartao(Conta conta) throws ParseException {
		if(Validacao.isPossivelSolicitarCartao(conta)) {
			CartaoCredito cartaoCredito = new CartaoCredito();
			conta.setCartaoCredito(cartaoCredito);
			
			System.out.println("Cartão de Crédito solicitado com sucesso!");
		}
	}

	@Override
	public void exibirDadosBancarios(Conta conta) {
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
		simpleDateFormat.setTimeZone(TimeZone.getTimeZone("GMT"));
		
		System.out.println("Nome: " + conta.getCliente().getNome());
		System.out.println("CPF: " + conta.getCliente().getCpf());
		System.out.println("Data de Nascimento: " + simpleDateFormat.format(conta.getCliente().getDataNascimento()));
		System.out.println("Banco: " + conta.getNome());
		System.out.println("Agência: " + conta.getCodigo());
		System.out.println("Nº da Conta: " + conta.getNumero());
	}

}

