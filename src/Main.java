/**
 * Codigo Principal
 * @author Higor Campos e Lucas Cunha
 * @versao 1.0
 * @ano_pr 2023/1
*/

//importando arquivos necessários 
import BynaryTree.*;
import Aluno.*;

//importando bibliotecas necessárias
import java.util.Scanner;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        
        //Inicializando o scanner
        Scanner scanner = new Scanner(System.in);
        
        //Declarando o nome do arquivo
        String nomeArquivoGerado = "entradaOrdenada10000.txt";
        
        //Declarando variaveis utilizadas para receber valores do menu
        int opOrdena;
        int opMatricula;
        String opNome;
        String opArqNome;
        int opMatriculaNovo;
        String opNomeNovo;
        int opNotaNovo;

        //Declarando variaveis utilizadas nas estatisticas
        int raiz;
        int altura;
        Node<Aluno> menor;
        Node<Aluno> maior;
        
        //Inicializando arvores por nome e matricula
        BynaryTree<Aluno> arvoreNome = new BynaryTree<>(new ComparadorAlunoPorNome());
        BynaryTree<Aluno> arvoreMatricula = new BynaryTree<>(new ComparadorAlunoPorMatricula());
        
        // Carregando para classe que manipula o arquivo importado
        ManipulaArquivo.lerArquivo(nomeArquivoGerado, arvoreNome, arvoreMatricula);
        
        //Prints do menu de opções
        do{
            System.out.println("\n-------- Sistema de gerenciamento de alunos --------");
            System.out.println("Escolha uma opcao listada abaixo: ");
            System.out.println("[1] >> Efetuar busca por matricula");
            System.out.println("[2] >> Efetuar busca por nome");
            System.out.println("[5] >> Incluir aluno");
            System.out.println("[6] >> Exibir estatisticas por matricula");
            System.out.println("[7] >> Exibir estatisticas por nome");
            System.out.println("[0] >> Sair");
            System.out.print("Opcao escolhida: ");
            opOrdena = scanner.nextInt();
            
            //Trabalhando com as opções
            switch(opOrdena) {
                
                //Efetuar busca por matrícula
                case 1: 
                    System.out.print("\n>> Digite a matricula do aluno: ");
                    opMatricula = scanner.nextInt();
                    //Enviando matricula para método de busca
                    arvoreMatricula.buscarPorMatricula(opMatricula);
                    break;
                
                //Efetuar busca por nome
                case 2: 
                    //Consumir o caracter da quebra de linha
                    scanner.nextLine();
                    System.out.print("\n>> Digite o nome do aluno: ");
                    opNome = scanner.nextLine();
                    //Enviando nome para método de busca
                    arvoreNome.buscarPorNome(opNome);
                    break;
                    
                //Incluir aluno
                case 5:
                    System.out.print("\n>> Digite a matricula do aluno: ");
                    opMatriculaNovo = scanner.nextInt();
                    //Consumir o caracter da quebra de linha
                    scanner.nextLine();
                    System.out.print(">> Digite o nome do aluno: ");
                    opNomeNovo = scanner.nextLine();
                    System.out.print(">> Digite a nota do aluno: ");
                    opNotaNovo = scanner.nextInt();
                    //Novo aluno sendo criado com as informações consumidas pelo usuário
                    Aluno alunoNovo = new Aluno(opMatriculaNovo, opNomeNovo, opNotaNovo);
                    //Inserindo nas arvores
                    arvoreNome.insert(alunoNovo);
                    arvoreMatricula.insert(alunoNovo);
                    System.out.println("\n>> Aluno inserido com sucesso! <<");
                    break;
                
                //Exibir estatisticas por matricula
                case 6:
                    //Se eu fizer a altura por nome primeiro, não dá erro na hora de fazer por matricula
                    //Se comentar essa linha e escolher a opção 6 de primeira, vai dar stackoverflow
                    altura = arvoreNome.altura();
                    System.out.println("\n-------- Estatisticas da arvore [MATRICULA] --------");
                    //Requisitando metodos para impressão
                    raiz = arvoreMatricula.getQtdElem();
                    altura = arvoreMatricula.altura();
                    menor = arvoreMatricula.getMenor();
                    maior = arvoreMatricula.getMaior();
                    System.out.println("Quantidade total de elementos: " + raiz);
                    System.out.println("Altura da arvore: " + altura);
                    System.out.println("Menor matricula: " + menor.getValor().getMatricula());
                    System.out.println("Maior matricula: " + maior.getValor().getMatricula());
                    break;
                 
                //Exibir estatisticas por nome    
                case 7:
                    System.out.println("\n-------- Estatisticas da arvore [NOME] --------");
                    //Requisitando metodos para impressão
                    raiz = arvoreNome.getQtdElem();
                    altura = arvoreNome.altura();
                    menor = arvoreNome.getMenor();
                    maior = arvoreNome.getMaior();
                    System.out.println("Quantidade total de elementos: " + raiz);
                    System.out.println("Altura da arvore: " + altura);
                    System.out.println("Menor matricula: " + menor.getValor().getMatricula());
                    System.out.println("Maior matricula: " + maior.getValor().getMatricula());
                    break;
                
                //Sair
                case 0: 
                    //Consumir o caracter da quebra de linha
                    scanner.nextLine();
                    //Receber nome para gerar arquivo com arvore ordenada por matricula                  
                    System.out.print("Digite o nome do arquivo para receber a lista ordenada por matricula: ");
                    opArqNome = scanner.nextLine();
                    ManipulaArquivo.exportarArquivo(opArqNome, arvoreMatricula);
                    break;
                
                //Caso digite uma opcao não listada    
                default:
                    System.out.println("Opcao nao identificada. Tente novamente.\n");
                    break;
                } 
            
        }while (opOrdena != 0);
        
    }
}
