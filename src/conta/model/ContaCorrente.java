package conta.model;

public class ContaCorrente extends Conta { // extends -> herança/herda (AQUI A CONTA CORRENTE ESTÁ HERDANDO AS CARACTERÍSTICAS DA CLASSE CONTA)
	
	// Atributo / Variaveis 
	private float limite;

	// Método Especial - Método Construtor
	public ContaCorrente(int numero, int agencia, int tipo, String titular, float saldo, float limite) {
		super(numero, agencia, tipo, titular, saldo); //Invocando o método Construtor Conta
		
		this.limite = limite; // this -> nome da classe
		
	}
	
	public ContaCorrente(int numero, int agencia, int tipo, String titular, float saldo) {
		super(numero, agencia, tipo, titular, saldo);
		this.limite = 5000.0f;
	}
	
	//Métodos de Acesso
	public float getLimite() {
		return limite;
	}

	public void setLimite(float limite) {
		this.limite = limite;
	}
	
	public boolean sacar(float valor) {
		if (this.getSaldo() + this.getLimite() < valor) {
			System.out.println("\n Saldo Insuficiente!");
			return false;
		}
		this.setSaldo(this.getSaldo() - valor);
		return true;
	}
	public void visualizar() {
		super.visualizar();
		System.out.println("Limite de crédito: " + this.limite);
	}
	
	/*// Polimorfismo de Sobrescrtita -> Add mais lógica ai método existente
	public void visualizar() {
		super.visualizar(); // Método da Classe Conta
		System.out.println("Limite de crédito: " + this.limite);*/
	}
	
	

