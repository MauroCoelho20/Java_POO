package jogo;

import entidades.FrontEnd;
import entidades.Heroi;
import entidades.NPC;

import java.util.Scanner;
/**
 * Classe que gerencia os combates entre o herói e NPCs.
 * Controla o fluxo de turnos, ataques e uso de itens durante o combate.
 */
public class Combate {
    Jogo jogoCtx;
    Heroi heroi;
    NPC inimigo;
    boolean usouAtaqueEspecial;
    boolean inimigoAtacaPrimeiro;

    /**
     * Construtor da classe Combate
     *
     * @param heroi O herói que participará do combate
     * @param inimigo O NPC inimigo que enfrentará o herói
     */
    public Combate(Heroi heroi, NPC inimigo, Jogo jogoCtx) {
        this.heroi = heroi;
        this.inimigo = inimigo;
        this.usouAtaqueEspecial = false;
        this.jogoCtx = jogoCtx;

        this.inimigoAtacaPrimeiro = heroi instanceof FrontEnd;
    }

    /**
     * Trata de toda a lógica de Combate.
     *
     * @return True se o Jogador ganhou, False se o jogador perdeu
     */
    public boolean InciarCombate() {
        Scanner in = new Scanner(System.in);

        System.out.println("\n============= Combate =============");
        while (heroi.estaVivo() && inimigo.estaVivo()) {
            if (inimigoAtacaPrimeiro && inimigo.estaVivo()) {
                inimigo.atacar(heroi);

                if (!heroi.estaVivo())
                    break;
            }

            System.out.println("\n🎯 Escolha o tipo de ataque:");
            System.out.println("1. ⚔️ Ataque Normal");
            System.out.println("2. 💥 Ataque Especial (Só pode usar uma vez por Batalha, tenha cuidado!)");
            System.out.println("3. 🧪 Usar Consumível (Cuidado! Se usar perde a sua chance de Ataque!)");

            int escolha = in.nextInt();
            in.nextLine();

            switch (escolha) {
                case 1:
                    heroi.atacar(inimigo);
                    break;
                case 2:
                    if (!usouAtaqueEspecial) {
                        heroi.ataqueEspecial(inimigo);
                        usouAtaqueEspecial = true;
                    }
                    break;
                case 3:
                    heroi.usarItem();
                    break;
                default:
                    System.out.println("Escolha inválida!");
                    break;
            }

            if (!inimigoAtacaPrimeiro && inimigo.estaVivo()) {
                inimigo.atacar(heroi);
            }
            System.out.println("\nVida do: " +heroi.getNome()+ " " +heroi.getVidaAtual());
            System.out.println("Vida do: " +inimigo.getNome() + " " + inimigo.getVidaAtual());
            System.out.println("-----------------------------------------------------------");
        }

        if (!heroi.estaVivo()) {
            this.jogoCtx.GameOver();
            return false;
        }

        System.out.println("Tu venceste o combate!");
        heroi.subirNivel();
        heroi.ganharOuro(inimigo.getOuro());
        return true;
    }
}
