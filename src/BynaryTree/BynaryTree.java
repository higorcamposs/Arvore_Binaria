/**
 * Codigo Principal
 * @author Higor Campos e Lucas Cunha
 * @versao 1.0
 * @ano_pr 2023/1
*/

package BynaryTree;

import Aluno.Aluno;

import java.util.ArrayList;
import java.util.Comparator;


public class BynaryTree<T>{
    private Node<T> raiz;
    private int qtdElem;
    private Comparator<T> comparador;

    public BynaryTree(Comparator<T> comp){
        this.raiz = null;
        this.qtdElem = 0;
        this.comparador = comp;
    }

    //Metodo para inserir dados na arvore
    public void insert(T elem){
        Node<T> novo = new Node<T>(elem);
        Node<T> noatual = this.getRaiz();
        boolean parar = false;

        if(noatual == null){
            this.raiz = novo;
            this.qtdElem += 1;
        }
        else{
            while(!parar){
                if(comparador.compare(novo.getValor(),noatual.getValor()) < 0){
                    //noatual = noatual.getEsq();
                    if(noatual.getEsq() == null){
                        noatual.setEsq(novo);
                        parar = true;
                        this.qtdElem += 1;
                    }else{
                        noatual = noatual.getEsq();
                    }
                } else if (comparador.compare(novo.getValor(),noatual.getValor()) > 0) {
                    //noatual = noatual.getEsq();
                    if(noatual.getDir() == null){
                        noatual.setDir(novo);
                        parar = true;
                        this.qtdElem += 1;
                    }else{
                        noatual = noatual.getDir();
                    }
                }
                else{
                    System.out.printf("Valor %s ja existe na arvore.", noatual.getValor().toString()
                    );
                }
            }
        }
    }

    public int getQtdElem(){
        return this.qtdElem;
    }

    //Terminar o walk
    public ArrayList<T> walkLevel () {
        ArrayList<T> lista = new ArrayList<T>();
        Node<T> actualnode = this.getRaiz();
        int lvl = this.altura();
        int actuallvl = 0;
        int actualelem = 0;

        lista.add(actualnode.getValor());

        while (actuallvl <= lvl) {
            if(actualnode.getEsq() != null){
                lista.add((T) actualnode.getEsq().getValor());
            }
            if(actualnode.getDir() != null) {
                lista.add((T) actualnode.getDir().getValor());
            }

            actuallvl++;
            actualelem++;

            actualnode = this.goTo(lista.get(actualelem));
        }

        return lista;
    }

    public Node<T> goTo(T elem){
        Node<T> ret = new Node<T>();
        boolean foundit = false;
        boolean parar = false;

        if (elem != null) {
            ret = this.getRaiz();
            while (!parar) {
                if (comparador.compare(elem,ret.getValor()) < 0) {
                    ret = ret.getEsq();
                    if (ret.getValor() == null) {
                        parar = true;
                    }
                } else if (comparador.compare(elem,ret.getValor()) > 0) {
                    ret = ret.getDir();
                    if (ret.getValor() == null) {
                        parar = true;
                    }
                } else {
                    foundit = true;
                    parar = true;
                }
            }
        }

        return ret;
    }

    public boolean search(T elem) {
        if(this.goTo(elem) != null)
            return true;
        else
            return false;
    }
    
    public void inOrder(Node<T> no) {
        if (no != null) {
            inOrder(no.getEsq());
            System.out.print(no.getValor() + " ");
            inOrder(no.getDir());
        }
    }

    //Metodo para encontrar altura da arvore
    private int altura(Node<T> current){
        if(current == null){
            return 0;
        }else if (current.getEsq() == null && current.getDir() == null) {
            return 0;
        }
        
        int left = altura(current.getEsq());
        int right = altura(current.getDir());
        
        return Math.max(left, right) + 1;
    }

    public int altura(){
        return altura(raiz);
    }
    
    //Metodo para encontrar a menor matricula da arvore
    private Node<T> getMenor(Node<T> current){
        while(current.getEsq() != null)
            current = current.getEsq();
        return current;
    }

    public Node<T> getMenor(){
        return BynaryTree.this.getMenor(raiz);
    }

    //Metodo para encontrar a maior matricula da arvore
    private Node<T> getMaior(Node<T> current){
        while(current.getDir() != null)
            current = current.getDir();
        return current;
    }

    public Node<T> getMaior(){
        return getMaior(raiz);
    }

    //Metodo construido para realizar busca por matricula
    public Node<Aluno> buscarPorMatricula(int matricula) {
        Node<Aluno> atual = getRaiz();
        int contador = 0;

        while (atual != null) {
            contador++;
            if (atual.getValor().getMatricula() == matricula) {
                System.out.print("\nMatricula encontrada: ");
                System.out.println(atual.getValor().toString());
                System.out.println("Quantidade de elementos percorridos: " + contador);
                return atual;
            } else if (atual.getValor().getMatricula() < matricula) {
                atual = atual.getDir();
            } else {
                atual = atual.getEsq();
            }
        }
        
        System.out.println("\n>> Matricula nao encontrada! <<");
        System.out.println("Quantidade de elementos percorridos: " + contador);
        return null;
    }
    
    //Metodo construido para realizar busca por nome
    public Node<Aluno> buscarPorNome(String nome) {
        Node<Aluno> atual = getRaiz();
        int contador = 0;

        while (atual != null) {
            contador++;
            if (atual.getValor().getNome().equals(nome)) {
                System.out.print("\nNome encontrado: ");
                System.out.println(atual.getValor().toString());
                System.out.println("\nQuantidade de elementos percorridos: " + contador);
                return atual;
            } else if (atual.getValor().getNome().compareTo(nome) < 0) {
                atual = atual.getDir();
            } else {
                atual = atual.getEsq();
            }
        }
        
        System.out.println("\n>> Nome nao encontrado! << ");
        System.out.println("Quantidade de elementos percorridos: " + contador);
        
        return null;
    }
    
    //Metodos gets e sets
    public void setComparador(Comparator<T> comparador) {
        this.comparador = comparador;
    }
    
    public Node getRaiz(){
        return this.raiz;
    }

    private void setRaiz(Node<T> elem){
        this.raiz = elem;
    }

    public Comparator<T> getComparator(){
        return this.comparador;
    }

    public void setQtdElem(int qtdElem) {
        this.qtdElem = qtdElem;
    }
    
    
}