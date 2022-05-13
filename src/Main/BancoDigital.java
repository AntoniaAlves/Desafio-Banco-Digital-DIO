package Main;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.TimeZone;



import Dominio.Conta;
import Negocio.Transacao;
import Negocio.Validacao;

public class BancoDigital {
	
	public static void main(String[] args) {
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
		simpleDateFormat.setTimeZone(TimeZone.getTimeZone("GMT"));
		
		Transacao transacaoHelper = new Transacao();
		
		List<Conta> contas = new ArrayList<Conta>();
		
		String opcao = "";
		
		boolean sair = false;
		
		Scanner teclado = new Scanner(System.in);
		
		System.out.println("*------------------------------------------------------------*");
		System.out.println("|Bem vindo ao MeuBanco!                                        |");
		System.out.println("|Aqui é possível: criar conta, depositar, sacar e transferir.|");
		System.out.println("*------------------------------------------------------------*");
		
		try {
			do {

				System.out.println("*------------------------------------------------------------*");
				System.out.println("|0 - Sair                                                    |");
				System.out.println("|1 - Criar Conta                                             |");
				System.out.println("|2 - Listar Todas as Contas                                  |");
				System.out.println("|3 - Exibir Dados Bancários pelo CPF                         |");
				System.out.println("|4 - Visualizar Saldo Pelo CPF                               |");
				System.out.println("|5 - Solicitar Cartão de Crédito                             |");
				System.out.println("|6 - Sacar                                                   |");
				System.out.println("|7 - Depositar                                               |");
				System.out.println("|8 - Transferir                                              |");
				System.out.println("*------------------------------------------------------------*");
				
				System.out.print("Informe a opção: ");
				opcao = teclado.nextLine();
				
				switch (opcao) {
				case "0":
					System.out.println("*------------------------------------------------------------*");
					System.out.println("|ENCERRADANDO APLICAÇÃO...                                   |");
					System.out.println("|OBRIGADO!                                                   |");
					System.out.println("*------------------------------------------------------------*");
					sair = true;
					
					break;
				case "1":
					System.out.println("-> CRIAR CONTA");
					
					Conta c1 = new Conta();
					System.out.print("Informe o nome do Cliente: ");
					c1.getCliente().setNome(teclado.nextLine());
					
					System.out.print("Informe o CPF do Cliente: ");
					c1.getCliente().setCpf(teclado.nextLine());
					
					System.out.print("Informe a data de nascimento do Cliente: ");
					String dataNascimento = teclado.nextLine();
					
					if(!dataNascimento.isEmpty()) {
						c1.getCliente().setDataNascimento(simpleDateFormat.parse(dataNascimento));
					}
					
					if(Validacao.isPossivelCadastrarConta(c1)) {
						contas.add(c1);
						
						System.out.println("Conta criada com sucesso!");
					}
					
					break;
				case "2":
					System.out.println("-> LISTAR CONTAS");
					
					if(!contas.isEmpty()) {
						for (Conta conta : contas) {
							System.out.println("Número:" + conta.getNumero() + "Agência: " + conta.getCodigo());
						}
					}
					
					break;
				case "3":
					System.out.println("-> DADOS BANCÁRIOS");
					
					System.out.print("Informe o CPF do cliente: ");
					String cpfDados = teclado.nextLine();
					
					if(Validacao.isContaExistente(contas, cpfDados)) {
						for (Conta conta : contas) {
							if(conta.getCliente().getCpf().equals(cpfDados)) {
								transacaoHelper.exibirDadosBancarios(conta);
							}
						}
					}
					
					break;
				case "4":
					System.out.println("-> SALDO");
					
					System.out.print("Informe o CPF do cliente: ");
					String cpfSaldo = teclado.nextLine();
					
					if(Validacao.isContaExistente(contas, cpfSaldo)) {
						for (Conta conta : contas) {
							if(conta.getCliente().getCpf().equals(cpfSaldo)) {
								System.out.println("O saldo é de: " + conta.getSaldo());
							}
						}	
					}
					break;
				case "5":
					System.out.println("-> SOLICITAR CARTÃO");
					
					System.out.print("Informe o CPF do cliente: ");
					String cpfCartao = teclado.nextLine();
					
					if(Validacao.isContaExistente(contas, cpfCartao)) {
						for (Conta conta : contas) {
							if(conta.getCliente().getCpf().equals(cpfCartao)) {
								transacaoHelper.solicitarCartao(conta);
							}
						}
					}	
						
					break;
				case "6":
					System.out.println("-> SAQUE");
					
					System.out.print("Informe o CPF do cliente: ");
					String cpfSacar = teclado.nextLine();
					
					if(Validacao.isContaExistente(contas, cpfSacar)) {
						String valor;
						
						System.out.print("Informe o valor do saque: ");
						valor = teclado.nextLine();
						
						for (Conta conta : contas) {
							if(conta.getCliente().getCpf().equals(cpfSacar)) {
								transacaoHelper.sacar(conta, Double.parseDouble(valor));
							}
						}
					}
					
					break;	
				case "7":
					System.out.println("-> DEPÓSITO");
					
					System.out.print("Informe o CPF do cliente: ");
					String cpfDeposito = teclado.nextLine();
					
					if(Validacao.isContaExistente(contas, cpfDeposito)) {
						String valor;
						
						System.out.print("Informe o valor do depósito: ");
						valor = teclado.nextLine();
						
						for (Conta conta : contas) {
							if(conta.getCliente().getCpf().equals(cpfDeposito)) {
								transacaoHelper.depositar(conta, Double.parseDouble(valor));
							}
						}
					}
					
					break;
				case "8":
					System.out.println("-> TRANSFERÊNCIA");
					
					System.out.print("Informe o CPF do depositante: ");
					String cpfDepositante = teclado.nextLine();
					
					System.out.print("Informe o CPF do recebedor: ");
					String cpfRecebedor = teclado.nextLine();
					
					if(Validacao.isContaExistente(contas, cpfDepositante) 
								&& Validacao.isContaExistente(contas, cpfRecebedor)) {
						Conta contaDepositante = null, contaRecebedor = null;
						String valor;
						
						System.out.print("Informe o valor do depósito: ");
						valor = teclado.nextLine();
						
						for (Conta conta : contas) {
							if(conta.getCliente().getCpf().equals(cpfDepositante)) {
								contaDepositante = conta;
							}else if (conta.getCliente().getCpf().equals(cpfRecebedor)){
								contaRecebedor = conta;
							}
						}
						
						transacaoHelper.transferir(contaDepositante, contaRecebedor, Double.parseDouble(valor));
					}
					
					break;	
				default:
					break;
				}
			}while(!sair);
			
		} catch (ParseException e) {
			e.printStackTrace();
		}finally {
			teclado.close();
		}
	}

}

			
