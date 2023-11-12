import java.util.LinkedList;
import java.util.Queue;
import java.util.Random;

class NoArvore {
    int valor;
    NoArvore esquerda;
    NoArvore direita;

    public NoArvore(int valor) {
        this.valor = valor;
        this.esquerda = null;
        this.direita = null;
    }
}

public class ArvoreBinaria {
    NoArvore raiz;

    // Função para inserir um valor na árvore
    public NoArvore inserir(NoArvore raiz, int valor) {
        if (raiz == null) {
            return new NoArvore(valor);
        }

        if (valor < raiz.valor) {
            raiz.esquerda = inserir(raiz.esquerda, valor);
        } else {
            raiz.direita = inserir(raiz.direita, valor);
        }

        return raiz;
    }

    // Função para realizar a impressão em pré-ordem
    public void preOrdem(NoArvore no) {
        if (no != null) {
            System.out.print(no.valor + " ");
            preOrdem(no.esquerda);
            preOrdem(no.direita);
        }
    }

    // Função para realizar a impressão em ordem
    public void inOrdem(NoArvore no) {
        if (no != null) {
            inOrdem(no.esquerda);
            System.out.print(no.valor + " ");
            inOrdem(no.direita);
        }
    }

    // Função para realizar a impressão em pós-ordem
    public void posOrdem(NoArvore no) {
        if (no != null) {
            posOrdem(no.esquerda);
            posOrdem(no.direita);
            System.out.print(no.valor + " ");
        }
    }

    // Função para realizar a impressão em nível
    public void impressao(NoArvore raiz) {
        if (raiz == null) {
            return;
        }

        Queue<NoArvore> fila = new LinkedList<>();
        fila.add(raiz);

        while (!fila.isEmpty()) {
            NoArvore noAtual = fila.poll();
            System.out.print(noAtual.valor + " ");

            if (noAtual.esquerda != null) {
                fila.add(noAtual.esquerda);
            }

            if (noAtual.direita != null) {
                fila.add(noAtual.direita);
            }
        }
    }

    // Função para remover 5 elementos da árvore
    public void removerCincoElementos() {
        for (int i = 0; i < 5; i++) {
            int valorMinimo = encontrarMinimo(raiz);
            raiz = remover(raiz, valorMinimo);
        }
    }

    private int encontrarMinimo(NoArvore raiz) {
        if (raiz.esquerda == null) {
            return raiz.valor;
        }
        return encontrarMinimo(raiz.esquerda);
    }

    private NoArvore remover(NoArvore raiz, int valor) {
        if (raiz == null) {
            return raiz;
        }

        if (valor < raiz.valor) {
            raiz.esquerda = remover(raiz.esquerda, valor);
        } else if (valor > raiz.valor) {
            raiz.direita = remover(raiz.direita, valor);
        } else {
            if (raiz.esquerda == null) {
                return raiz.direita;
            } else if (raiz.direita == null) {
                return raiz.esquerda;
            }
            raiz.valor = encontrarMinimo(raiz.direita);
            raiz.direita = remover(raiz.direita, raiz.valor);
        }

        return raiz;
    }

    public static void main(String[] args) {
        ArvoreBinaria arvore = new ArvoreBinaria();
        Random random = new Random();

        // Sortear 20 números aleatórios de 0 a 100
        int[] randomNumbers = new int[20];
        for (int i = 0; i < 20; i++) {
            randomNumbers[i] = random.nextInt(101);
            // Inserir os números sorteados na árvore
            arvore.raiz = arvore.inserir(arvore.raiz, randomNumbers[i]);
        }

        // Imprimir os números na árvore antes de retirar 5 elementos
        System.out.println("Impressão em Pré-Ordem:");
        arvore.preOrdem(arvore.raiz);
        System.out.println("\nImpressão em Ordem:");
        arvore.inOrdem(arvore.raiz);
        System.out.println("\nImpressão em Pós-Ordem:");
        arvore.posOrdem(arvore.raiz);
        System.out.println("\nImpressão em Nível:");
        arvore.impressao(arvore.raiz);

        // Retirar 5 elementos da árvore
        arvore.removerCincoElementos();

        // Imprimir os números na árvore após remover 5 elementos
        System.out.println("\nApós remover 5 elementos:");
        System.out.println("Impressão em Pré-Ordem:");
        arvore.preOrdem(arvore.raiz);
        System.out.println("\nImpressão em Ordem:");
        arvore.inOrdem(arvore.raiz);
        System.out.println("\nImpressão em Pós-Ordem:");
        arvore.posOrdem(arvore.raiz);
        System.out.println("\nImpressão em Nível:");
        arvore.impressao(arvore.raiz);
    }
}
