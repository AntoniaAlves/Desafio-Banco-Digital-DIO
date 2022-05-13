package Interface;

import java.text.ParseException;

import Dominio.Conta;

public interface Operacoes {
	
	public void sacar(Conta conta, double valor);
	
	public void depositar(Conta conta, double valor);
	
	public void transferir(Conta suaConta, Conta contaDestino, double valor);
	
	public void solicitarCartao(Conta conta) throws ParseException;
	
	public void exibirDadosBancarios(Conta conta);

}
