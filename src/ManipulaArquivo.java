/**
 * Codigo Principal
 * @author Higor Campos e Lucas Cunha
 * @versao 1.0
 * @ano_pr 2023/1
*/

import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import BynaryTree.*;
import Aluno.*;

public class ManipulaArquivo {

    public static void lerArquivo(String nomeArquivoGerado, BynaryTree<Aluno> arvoreNome, BynaryTree<Aluno> arvoreMatricula) {
        
        try {
            File arquivoGerado = new File(nomeArquivoGerado);
            Scanner scanner = new Scanner(arquivoGerado);

            int numRegistros = scanner.nextInt();
            //System.out.println("\nNumero de registros: " + numRegistros + "\n");

            while (scanner.hasNextLine()) {
            String linha = scanner.nextLine();
            if (!linha.isEmpty()) { 
                String[] dados = linha.split(";");
                int matricula = Integer.parseInt(dados[0]);
                String nome = dados[1];
                int nota = Integer.parseInt(dados[2]);
                //System.out.println(matricula + ";" + nome + ";" + nota);
                Aluno aluno = new Aluno(matricula, nome ,nota);
                arvoreNome.insert(aluno);
                arvoreMatricula.insert(aluno);
            }
        }
        scanner.close();
        }
        catch (FileNotFoundException error) {
            error.printStackTrace();
        }
    }    
        
    public static void exportarArquivo(String nomeArquivo, BynaryTree<Aluno> arvoreMatricula) {
        
        try (FileWriter writer = new FileWriter(nomeArquivo + ".txt")) {
            StringBuilder sb = new StringBuilder();
            escreverEmOrdem(arvoreMatricula.getRaiz(), sb);
            writer.write(sb.toString());
            System.out.println("Arquivo gerado com sucesso!");
        } catch (IOException e) {
            System.out.println("Erro ao gerar arquivo: " + e.getMessage());
        }
    }
    
    private static void escreverEmOrdem(Node<Aluno> node, StringBuilder sb) {
        if (node == null) {
            return;
        }
        escreverEmOrdem(node.getEsq(), sb);
        sb.append(node.getValor().getMatricula()).append(";").append(node.getValor().getNome()).append(";").append(node.getValor().getNota()).append("\n");
        escreverEmOrdem(node.getDir(), sb);
    }
 
}
    
    


