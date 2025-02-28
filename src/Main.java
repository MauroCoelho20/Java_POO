import entidades.*;
import items.ArmaPrincipal;
import jogo.Jogo;
import java.util.ArrayList;
import java.util.Scanner;
/**
 * Classe principal que contém o ponto de entrada da aplicação.
 * Responsável por iniciar o jogo e chamar os métodos necessários para sua execução.
 */
public class Main {
    /**
     * Método principal que inicia a execução do programa.
     * Cria uma nova instância do jogo e chama o método de introdução.
     *
     */
    public static void main(String[] args) {

        // Inicia o jogo com o herói escolhido

        Jogo jogo = new Jogo();

        jogo.introducao();
    }
}
