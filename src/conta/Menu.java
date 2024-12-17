package conta;

import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Scanner;

import conta.controller.ContaController;
import conta.model.Conta;
import conta.model.ContaCorrente;
import conta.util.Cores;
import conta.model.ContaPoupanca;

public class Menu {

	public static Scanner leia = new Scanner(System.in);

	public static void main(String[] args) {

		ContaController contas = new ContaController();

		Scanner leia = new Scanner(System.in);

		int opcao, numero, agencia, tipo, aniversario, numeroDestino;
		String titular;
		float saldo, limite, valor;
		
		System.out.println("\nCriar Contas");
		
		ContaCorrente cc1 = new ContaCorrente(contas.gerarNumero(), 123, 1, "Leafeon", 1000f, 100.0f);
		contas.cadastrar(cc1);
		ContaCorrente cc2 = new ContaCorrente(contas.gerarNumero(), 456, 1, "Sylveon", 5000f, 100.0f);
		contas.cadastrar(cc2);
		ContaCorrente cp1 = new ContaCorrente(contas.gerarNumero(), 789, 2, "Flareon", 4000, 12);
		contas.cadastrar(cp1);
		ContaCorrente cp2 = new ContaCorrente(contas.gerarNumero(), 951, 2, "Jolteon", 8000, 15);
		contas.cadastrar(cp2);
		

		while (true) {

			System.out.println(Cores.TEXT_YELLOW_BOLD_BRIGHT + Cores.ANSI_BLACK_BACKGROUND
					+ "*****************************************************");
			System.out.println("                                                     ");
			System.out.println("                BANCO DO ESTUDANTE                   ");
			System.out.println("                                                     ");
			System.out.println("*****************************************************");
			System.out.println("                                                     ");
			System.out.println("            1 - Criar Conta                          ");
			System.out.println("            2 - Listar todas as Contas               ");
			System.out.println("            3 - Buscar Conta por Numero              ");
			System.out.println("            4 - Atualizar Dados da Conta             ");
			System.out.println("            5 - Apagar Conta                         ");
			System.out.println("            6 - Sacar                                ");
			System.out.println("            7 - Depositar                            ");
			System.out.println("            8 - Transferir valores entre Contas      ");
			System.out.println("            9 - Sair                                 ");
			System.out.println("                                                     ");
			System.out.println("*****************************************************");
			System.out.println("Entre com a opção desejada:                          ");
			System.out.println("                                                     " + Cores.TEXT_RESET);

			try {
				opcao = leia.nextInt();
			} catch (InputMismatchException e) {
				System.out.println("\nDigite valores inteiros!");
				leia.nextLine();
				opcao = 0;
			}

			if (opcao == 9) {
				System.out.println(
						Cores.TEXT_WHITE_BOLD + "\nBanco do Estudante - O seu futuro começa onde você definir!");
				sobre();
				leia.close();
				System.exit(0);
			}
			switch (opcao) {
			case 1:
				System.out.println(Cores.TEXT_WHITE_BOLD + "Criar conta\n\n");

				System.out.println("Digite o número da Agência: ");
				agencia = leia.nextInt();
				System.out.println("Digite o nome do Titular: ");
				leia.skip("\\R?");
				titular = leia.nextLine();

				do {
					System.out.println("Digite o tipo da Conta (1-CC ou 2-CP): ");
					tipo = leia.nextInt();
				} while (tipo < 1 && tipo > 2);

				System.out.println("Digite o Saldo da Conta (R$): ");
				saldo = leia.nextFloat();

				switch (tipo) {
				case 1 -> {
					System.out.println("Digite o Limite de Crédito (R$): ");
					limite = leia.nextFloat();
					contas.cadastrar(new ContaCorrente(contas.gerarNumero(), agencia, tipo, titular, saldo, limite));
				}
				case 2 -> {
					System.out.println("Digite o Dia do Aniversário da Conta: ");
					aniversario = leia.nextInt();
					contas.cadastrar(
							new ContaPoupanca(contas.gerarNumero(), agencia, tipo, titular, saldo, aniversario));
				}
				}

				keyPress();
				break;
			case 2:
				System.out.println(Cores.TEXT_WHITE_BOLD + "Listar todas as Contas\n\n");
				contas.listarTodas();
				keyPress();
				break;
			case 3:
				System.out.println(Cores.TEXT_WHITE_BOLD + "Consultar dados da Conta - por número\n\n");
				System.out.println("Digite o número da Conta: ");
				numero = leia.nextInt();
				
				contas.procurarPorNumero(numero);
				
				keyPress();
				break;
			case 4:
				System.out.println(Cores.TEXT_WHITE_BOLD + "Atualizar dados da Conta\n\n");
				
				System.out.println("Digite o número da Conta: ");
				numero = leia.nextInt();
				
				var buscaConta = contas.buscarNaCollection(numero);
				
				if (buscaConta != null) {
					
					tipo = buscaConta.getTipo();
					
					System.out.println("Digite o número da Agência: ");
					agencia = leia.nextInt();
					System.out.println("Digite o nome do Titular: ");
					leia.skip("\\R?");
					titular = leia.nextLine();
					
					System.out.println("Digite o saldo da Conta (R$): ");
					saldo = leia.nextFloat();
					
					switch(tipo) {
					case 1 -> {
						System.out.println("Digite o limite de crédito (R$): ");
						limite = leia.nextFloat();
						
						contas.atualizar(new ContaCorrente(numero, agencia, tipo, titular, saldo, limite));
						
					}
					case 2 -> {
						System.out.println("Digite o dia do Aniversário da Conta: ");
						aniversario = leia.nextInt();
						
						contas.atualizar(new ContaPoupanca(numero, agencia, tipo, titular, saldo, aniversario));
					}
					default -> {
						System.out.println("Tipo de Conta inválido!");
					}
				}
					
				}else {
					System.out.println("A conta não foi encontrada!");
				}
				keyPress();
				break;
			case 5:
				System.out.println(Cores.TEXT_WHITE_BOLD + "Apagar a Conta\n\n");
				
				System.out.println("Digite o número da Conta: ");
				numero = leia.nextInt();
				
				contas.deletar(numero);
				
				
				keyPress();
				break;
			case 6:
				System.out.println(Cores.TEXT_WHITE_BOLD + "Saque\n\n");
				
				System.out.println("Digite o número da Conta: ");
				numero = leia.nextInt();
				
				do {
					System.out.println("Digite o valor do Saque (R$): ");
					valor = leia.nextFloat();
				}while(valor<= 0);
				
				contas.sacar(numero, valor);
				
				keyPress();
				break;
			case 7:
				System.out.println(Cores.TEXT_WHITE_BOLD + "Depósito\n\n");
				
				System.out.println("Digite o número da Conta: ");
				numero = leia.nextInt();
				
				do {
					System.out.println("Digite o valor do Depósito (R$): ");
					valor = leia.nextFloat();
				}while(valor<= 0);
				
				contas.depositar(numero, valor);
				
				keyPress();
				break;
			case 8:
				System.out.println(Cores.TEXT_WHITE_BOLD + "Transferência entre Contas\n\n");
				
				System.out.println("Digite o número da Conta de Origem: ");
				numero = leia.nextInt();
				System.out.println("Digite o número da Conta de Destino: ");
				numeroDestino = leia.nextInt();
				
				do {
					System.out.println("Digite o valor da Transferência (R$): ");
					valor = leia.nextFloat();
				}while(valor<= 0);
				
				contas.transferir(numero, numeroDestino, valor);
				
				keyPress();
				break;
			default:
				System.out.println(Cores.TEXT_RED_BOLD + "\nOpção Inválida\n");
				keyPress();
				break;
			}
		}
	}

	public static void keyPress() {

		try {
			System.out.println(Cores.TEXT_RESET + "\n\n Pressione Enter para Continuar...");
			System.in.read();
		} catch (IOException e) {
			System.out.println("Você pressionou uma tecla diferente de Enter!");
		}
	}

	public static void sobre() {
		System.out.println("\n*****************************************************");
		System.out.println("\nPojeto Desenvolvido por: Danilo Almeida");
		System.out.println("\nGeneration Brasil - daniloa@genstudents.org");
		System.out.println("github.com/hixdann");
		System.out.println("\n*****************************************************");
	}
}
