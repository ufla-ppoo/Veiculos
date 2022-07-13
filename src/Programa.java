import java.util.Scanner;

/**
 * Classe principal que faz a interface com usuario do simulador.
 * Permite cadastrar carros, caminhoes e onibus e lista-los.
 * 
 * @author Julio Cesar Alves
 */
public class Programa {

    // Objeto do simulador (regra de negócio)
    private Simulador simulador;

    // usado para obter dados do usuário
    private Scanner entrada;

    /*
     * Construtor (cria os atributos da classe)
     */
    public Programa() {
        entrada = new Scanner(System.in);
        simulador = new Simulador();
    }
    
    /**
     * Metodo principal que inicia a execucao do programa
     */
    public void executar() {        
        int opcaoMenu;
        do {
            opcaoMenu = exibirMenu();
            
            switch (opcaoMenu) {
                case 1:
                    adicionarVeiculo();
                    break;
                case 2:
                    alterarVelocidadeVeiculo();
                    break;
                case 3:
                    exibirVeiculos();
                    break;
                case 4: // nao faz nada, apenas vai sair do programa
                    break;
                default:
                    System.out.println("Opcao invalida!");
            }

            if (opcaoMenu != 4) {
                // espera usuario digitar uma tecla qualquer
                esperarTecla();
            }

        } while (opcaoMenu != 4);
    }
    
    /**
     * Exibe o menu para o usuario e retorna a opcao escolhida por ele
     * 
     * @return Opcao de menu escolhida pelo usuario
     */
    private int exibirMenu() {

        System.out.println("\n\n1) Adicionar Veiculo");
        System.out.println("2) Alterar velocidade");
        System.out.println("3) Listar Veiculos");
        System.out.println("4) Sair");
        System.out.print("\tDigite sua opcao: ");
        
        return Integer.parseInt(entrada.nextLine());
    }
    
    /**
     * Permite ao usuario adicionar um veiculo ao simulador.
     * O usuario passa os dados de acordo com o tipo de veiculo.
     */
    private void adicionarVeiculo() {        
        String modelo, marca, placa;
        int tipo;
        boolean adicionado = false;
        
        System.out.print("Digite o modelo: ");
        modelo = entrada.nextLine();
        System.out.print("Digite a marca: ");
        marca = entrada.nextLine();
        System.out.print("Digite a placa: ");
        placa = entrada.nextLine();
        
        System.out.print("Qual o tipo de veiculo (1-carro, 2-caminhao, 3-onibus)? ");
        tipo = Integer.parseInt(entrada.nextLine());
        
        switch(tipo) {
            case 1: // carro
                System.out.print("Digite se eh flex (1-sim, 2-nao): ");
                int flex = Integer.parseInt(entrada.nextLine());
                boolean ehFlex = (flex == 1);				
                simulador.adicionarCarro(modelo, marca, placa, ehFlex);
                adicionado = true;
                break;
            case 2: // caminhao
                System.out.print("Digite a capacidade de carga (ton): ");
                double capacidadeCarga = Double.parseDouble(entrada.nextLine());
                simulador.adicionarCaminhao(modelo, marca, placa, capacidadeCarga);
                adicionado = true;
                break;
            case 3: // onibus
                System.out.print("Digite a capacidade de passageiros: ");
                int capacidadePas = Integer.parseInt(entrada.nextLine());
                simulador.adicionarOnibus(modelo, marca, placa, capacidadePas);
                adicionado = true;
                break;
            default:
                System.out.println("Tipo de veiculo invalido!");
        }
        
        if (adicionado) {
            System.out.print("Veiculo adicionado!");
        }
    }
    
    /**
     * Permite ao usuario alterar a velocidade de um veiculo do simulador.
     * O usuario informa o tipo de veiculo e o modelo.
     */
    private void alterarVelocidadeVeiculo() {
        int tipo, velocidade;
        String modelo;
        boolean alterou = false;
       
        System.out.print("Qual o tipo de veiculo (1-carro, 2-caminhao, 3-onibus)? ");        
        tipo = Integer.parseInt(entrada.nextLine());
        System.out.print("Digite o modelo: ");
        modelo = entrada.nextLine();
        System.out.print("Digite a velocidade: ");
        velocidade = Integer.parseInt(entrada.nextLine());
        
        switch(tipo) {
            case 1: // carro
                alterou = simulador.alterarVelocidadeCarro(modelo, velocidade);
                break;
            case 2: // caminhao
                alterou = simulador.alterarVelocidadeCaminhao(modelo, velocidade);            
                break;
            case 3: // onibus
                alterou = simulador.alterarVelocidadeOnibus(modelo, velocidade);            
                break;
            default:
                System.out.println("Tipo de veiculo invalido!");
        }
        
        if (alterou) {
            System.out.println("Velocidade alterada!");
        }
        else {
            System.out.println("Veiculo nao encontrado ou velocidade invalida!");
        }
    }
    
    /**
     * Exibe na tela os veiculos retornados pelo simulador
     */
    private void exibirVeiculos() {
        System.out.println(simulador.getDescricaoFrota());
    }
	
	/**
	 * Exibe na tela o texto "ENTER para continuar" e aguarda um ENTER
	 */
	private void esperarTecla() {
		try {
			System.out.print("\n\nENTER para continuar...");
			entrada.nextLine();
		} catch(Exception e) {}
	}
}
