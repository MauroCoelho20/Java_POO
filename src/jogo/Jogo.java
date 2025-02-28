package jogo;

import audio.Audio;
import resources.LerFicheiro;
import entidades.*;
import items.ArmaPrincipal;

import java.util.Random;
import java.util.Scanner;
/**
 * Classe principal que gerencia o jogo de aventura.
 * O jogo simula a jornada de um herói através de uma série de desafios e batalhas,
 * onde o objetivo final é completar o curso de Software Development no CESAE.
 * O jogador pode escolher diferentes especialidades para o herói, distribuir pontos de atributos,
 * e enfrentar diversos inimigos e desafios ao longo da jornada.
 */
public class Jogo {
    private Heroi heroi;
    private Heroi heroiBase;
    Scanner in = new Scanner(System.in);
    Random rnd = new Random();
    String nomeJogador;

    /**
     * Construtor padrão da classe Jogo.
     */
    public Jogo() {
    }

    /**
     * Método que inicia a introdução do jogo, apresentando o menu inicial e permitindo ao jogador
     * começar a aventura ou sair do jogo.
     */
    public void introducao(){

        Scanner in = new Scanner(System.in);

        System.out.println("\n" +
                "██████╗ ███████╗██████╗ ██╗   ██╗ ██████╗  ██████╗ ██╗███╗   ██╗ ██████╗ \n" +
                "██╔══██╗██╔════╝██╔══██╗██║   ██║██╔════╝ ██╔════╝ ██║████╗  ██║██╔════╝ \n" +
                "██║  ██║█████╗  ██████╔╝██║   ██║██║  ███╗██║  ███╗██║██╔██╗ ██║██║  ███╗\n" +
                "██║  ██║██╔══╝  ██╔══██╗██║   ██║██║   ██║██║   ██║██║██║╚██╗██║██║   ██║\n" +
                "██████╔╝███████╗██████╔╝╚██████╔╝╚██████╔╝╚██████╔╝██║██║ ╚████║╚██████╔╝\n" +
                "╚═════╝ ╚══════╝╚═════╝  ╚═════╝  ╚═════╝  ╚═════╝ ╚═╝╚═╝  ╚═══╝ ╚═════╝ \n" +
                "                                                                         \n" +
                "        ███╗   ███╗██╗   ██╗    ██╗     ██╗███████╗███████╗              \n" +
                "        ████╗ ████║╚██╗ ██╔╝    ██║     ██║██╔════╝██╔════╝              \n" +
                "        ██╔████╔██║ ╚████╔╝     ██║     ██║█████╗  █████╗                \n" +
                "        ██║╚██╔╝██║  ╚██╔╝      ██║     ██║██╔══╝  ██╔══╝                \n" +
                "        ██║ ╚═╝ ██║   ██║       ███████╗██║██║     ███████╗              \n" +
                "        ╚═╝     ╚═╝   ╚═╝       ╚══════╝╚═╝╚═╝     ╚══════╝              \n" +
                "                                                                         \n\n");
        System.out.println("🎮💻 Bem-vindo à Jornada do Software Development! 💻🎮\n");

        Audio.playMusic("AudioFiles/musica.wav", true);
        while (true) {
            System.out.println("⚔️ Desejas começar a tua Jornada? ⚔️ (S/N)");
            String jogar = in.nextLine().toUpperCase();

            if (jogar.equals("N")) {
                System.out.println("😢 Que pena! Volta quando quiseres começar a tua jornada! 👋");
                System.exit(0);
            } else if (jogar.equals("S")) {
                break;
            } else {
                System.out.println("❌ Opção inválida! Por favor, escolhe S ou N.");
            }
        }


        criarPersonagem();
    }

    /**
     * Método que permite ao jogador criar o personagem, escolhendo o nome, especialidade,
     * dificuldade e distribuindo pontos de atributos.
     */
    public void criarPersonagem() {
        Scanner in = new Scanner(System.in);

        // Pedir nome do jogador

        System.out.println("===== ✨ Criação do Herói ✨ =====");
        System.out.println("Digite o nome do seu herói: 📝");
        nomeJogador = in.nextLine();

        while (nomeJogador.trim().isEmpty()) {
            System.out.println("❌ O nome não pode estar vazio!");
            System.out.println("Digite o nome do seu herói: 📝");
            nomeJogador = in.nextLine();
        }

        // Inicia a ArmaPrimaria e principal do Heroi

        ArmaPrincipal soco = new ArmaPrincipal("Soco", 0, 1, 2);

        // Menu de escolha do herói

        System.out.println("===== 🎮 Escolha a especialidade do Seu Herói " + nomeJogador +  " 🎮 =====");
        System.out.println("1. 🖥️ FrontEnd - Desenvolve o interface e experiência do usuário de um site ou aplicativo.");
        System.out.println("2. ⚙️ BackEnd - Gerencia os servidores, bases de dados e lógica de funcionamento de um sistema.");
        System.out.println("3. 🌐 FullStack - Desenvolvedor que faz um pouco de tudo.");

        int escolha = in.nextInt();

        // Escolha da dificuldade

        System.out.println("\nEscolha a dificuldade:");
        System.out.println("1. Fácil \uD83E\uDD71");
        System.out.println("2. Difícil \uD83E\uDD2C");

        int escolhaDificuldade = in.nextInt();
        int pontosDisponiveis = escolhaDificuldade == 1 ? 300 : 200;
        int ouroInicial = escolhaDificuldade == 1 ? 15 : 10;

        // Distribuição de pontos

        int vidaBase = 100;
        int forcaBase = 10;
        int pontosVida = 0;
        int pontosForca = 0;

        System.out.println("\n=== 📊 Distribuição de Pontos 📊 ===");
        System.out.println("💡 Você tem " + pontosDisponiveis + " pontos para distribuir");
        System.out.println("❤️ Cada ponto de vida custa 1 ponto");
        System.out.println("💪 Cada ponto de força custa 5 pontos");
        System.out.println("❤️ Vida base: " + vidaBase);
        System.out.println("💪 Força base: " + forcaBase);

        while (pontosDisponiveis > 0) {
            System.out.println("\nPontos restantes: " + pontosDisponiveis);
            System.out.println("1. Adicionar pontos de ❤\uFE0F vida (custo: 1 ponto cada)");
            System.out.println("2. Adicionar pontos de \uD83D\uDCAA força (custo: 5 pontos cada)");
            System.out.println("Escolha uma opção:");

            int opcao = in.nextInt();

            if (opcao == 1) {
                System.out.println("Quantos pontos de ❤\uFE0F vida deseja adicionar? (Disponível: " + pontosDisponiveis + ")");
                int pontos = in.nextInt();

                if (pontos <= pontosDisponiveis && pontos >= 0) {
                    pontosVida += pontos;
                    pontosDisponiveis -= pontos;
                } else {
                    System.out.println("Quantidade inválida de pontos!");
                }
            }
            else if (opcao == 2) {
                System.out.println("Quantos pontos de \uD83D\uDCAA força deseja adicionar? (Disponível: " + pontosDisponiveis + ", Custo: 5 cada)");
                int pontos = in.nextInt();

                if (pontos * 5 <= pontosDisponiveis && pontos >= 0) {
                    pontosForca += pontos;
                    pontosDisponiveis -= (pontos * 5);
                } else {
                    System.out.println("Quantidade inválida de pontos!");
                }
            }

            System.out.println("\n📊 Status atual:");
            System.out.println("❤️ Vida total: " + (vidaBase + pontosVida));
            System.out.println("💪 Força total: " + (forcaBase + pontosForca));
        }

        // Criação do herói com os atributos escolhidos
        switch (escolha) {
            case 1:
                this.heroi = new FrontEnd("FrontEnd", vidaBase + pontosVida, forcaBase + pontosForca, 0, ouroInicial, soco);
                break;
            case 2:
                this.heroi = new BackEnd("BackEnd", vidaBase + pontosVida, forcaBase + pontosForca, 0, ouroInicial, soco);
                break;
            case 3:
                this.heroi = new FullStack("FullStack", vidaBase + pontosVida, forcaBase + pontosForca, 0, ouroInicial, soco);
                break;
            default:
                System.out.println("Opção inválida. Escolhendo FrontEnd por padrão.");
                this.heroi = new FrontEnd("FrontEnd", vidaBase + pontosVida, forcaBase + pontosForca, 0, ouroInicial, soco);
                break;
        }

        this.heroiBase = this.heroi.Clone();

        System.out.println("\n🏆 ===== Herói " + heroi.getNome() + " " + nomeJogador + " Criado ===== 🏆\n");
        this.heroi.exibirDetalhes();
        iniciarJornada();
    }

    /**
     * Método que inicia a jornada do herói, apresentando a narrativa inicial e os primeiros desafios.
     */
    public void iniciarJornada() {

        System.out.println("Bem-vindo, " + heroi.getNome() + " " +nomeJogador+ " 💻");
        System.out.println("Você está equipado com: \uD83D\uDC4A " +heroi.getArmaPrincipal().getNome()) ;
        System.out.println("Tem de Comprar ao Vendedor \uD83E\uDDD9 itens que o ajudem na sua Jornada");
        System.out.println("Força Héroi: " + heroi.getForca() +"\uD83D\uDCAA" + " | Vida: " + heroi.getVidaAtual() + "❤\uFE0F" + " | Ouro: " + heroi.getOuro()+"\uD83D\uDCB0" );

        Vendedor vendedor = new Vendedor();
        vendedor.vender(heroi);

        System.out.println("\n" +
                "██╗███╗   ██╗██╗ ██████╗██╗ ██████╗     ██████╗  █████╗          ██╗ ██████╗ ██████╗ ███╗   ██╗ █████╗ ██████╗  █████╗ \n" +
                "██║████╗  ██║██║██╔════╝██║██╔═══██╗    ██╔══██╗██╔══██╗         ██║██╔═══██╗██╔══██╗████╗  ██║██╔══██╗██╔══██╗██╔══██╗\n" +
                "██║██╔██╗ ██║██║██║     ██║██║   ██║    ██║  ██║███████║         ██║██║   ██║██████╔╝██╔██╗ ██║███████║██║  ██║███████║\n" +
                "██║██║╚██╗██║██║██║     ██║██║   ██║    ██║  ██║██╔══██║    ██   ██║██║   ██║██╔══██╗██║╚██╗██║██╔══██║██║  ██║██╔══██║\n" +
                "██║██║ ╚████║██║╚██████╗██║╚██████╔╝    ██████╔╝██║  ██║    ╚█████╔╝╚██████╔╝██║  ██║██║ ╚████║██║  ██║██████╔╝██║  ██║\n" +
                "╚═╝╚═╝  ╚═══╝╚═╝ ╚═════╝╚═╝ ╚═════╝     ╚═════╝ ╚═╝  ╚═╝     ╚════╝  ╚═════╝ ╚═╝  ╚═╝╚═╝  ╚═══╝╚═╝  ╚═╝╚═════╝ ╚═╝  ╚═╝\n" +
                "                                                                                                                       \n");
        System.out.println("\n=============💻 A Jornada do Jovem "+ heroi.getClass().getSimpleName() + " " + nomeJogador +  " 💻=============\n");
        System.out.println("Era uma manhã tranquila em Amarante \uD83C\uDFF0, uma pequena vila rodeada por montanhas \uD83D\uDDFB e rios \uD83C\uDF0A.");
        System.out.println("Tu \uD83D\uDC68\u200D\uD83D\uDCBB, um jovem Developer em treino \uD83C\uDF93, tens uma missão crucial: Vencer o curso de Software Development da CESAE! \uD83D\uDCAA");

        // Adiciona a opção para continuar, pressionando qualquer botao
        System.out.println("\n🔄 Pressione um botao para continuar para o próximo capítulo... 🔄");
        in.nextLine();
        capitulo1();
    }

    /**
     * Capítulo 1 da jornada, onde o jogador toma a primeira decisão importante.
     */
    private void capitulo1() {
        System.out.println("\n\uD83C\uDF1F============= Capítulo 1 =============\uD83C\uDF1F");
        System.out.println("\n" +
                " █████╗     ██████╗ ██████╗ ██╗███╗   ███╗███████╗██╗██████╗  █████╗     ██████╗ ███████╗ ██████╗██╗███████╗ █████╗  ██████╗ \n" +
                "██╔══██╗    ██╔══██╗██╔══██╗██║████╗ ████║██╔════╝██║██╔══██╗██╔══██╗    ██╔══██╗██╔════╝██╔════╝██║██╔════╝██╔══██╗██╔═══██╗\n" +
                "███████║    ██████╔╝██████╔╝██║██╔████╔██║█████╗  ██║██████╔╝███████║    ██║  ██║█████╗  ██║     ██║███████╗███████║██║   ██║\n" +
                "██╔══██║    ██╔═══╝ ██╔══██╗██║██║╚██╔╝██║██╔══╝  ██║██╔══██╗██╔══██║    ██║  ██║██╔══╝  ██║     ██║╚════██║██╔══██║██║   ██║\n" +
                "██║  ██║    ██║     ██║  ██║██║██║ ╚═╝ ██║███████╗██║██║  ██║██║  ██║    ██████╔╝███████╗╚██████╗██║███████║██║  ██║╚██████╔╝\n" +
                "╚═╝  ╚═╝    ╚═╝     ╚═╝  ╚═╝╚═╝╚═╝     ╚═╝╚══════╝╚═╝╚═╝  ╚═╝╚═╝  ╚═╝    ╚═════╝ ╚══════╝ ╚═════╝╚═╝╚══════╝╚═╝  ╚═╝ ╚═════╝ \n" +
                "                                                                                                                             \n");
        System.out.println("Toca o despertador ⏰ as 06:45h de uma manhã tranquila mas fria ❄\uFE0F em Amarante!!");
        System.out.println("O jovem Developer \uD83D\uDC68\u200D\uD83D\uDCBB acorda e tem de imediato uma decisão muito importante a fazer!!! \uD83E\uDD14");

        System.out.println("\nEscolha:");
        System.out.println("1. Sair da Cama Quentinha. \uD83D\uDECF\uFE0F \uD83D\uDEB6\uD83C\uDFFD\u200D➡\uFE0F");
        System.out.println("2. Ficar na Cama e continuar a dormir. \uD83D\uDE34");

        int escolha = in.nextInt();
        in.nextLine();

        if (escolha == 1) {
            System.out.println("\nFizeste a Escolha Correta Heroi!! \uD83D\uDC68\u200D\uD83D\uDCBB\n");
            System.out.println("Com esta Escolha recebes 5 💰!!\n");
            heroi.ganharOuro(5);
            heroi.exibirDetalhes();
        }

        if (escolha == 2) {
            System.out.println("Tiveste azar!! \uD83D\uDE12");
            System.out.println("Nao tens essa Escolha, tens de ir a qualquer custo!! \uD83D\uDEB6\u200D➡\uFE0F");
            System.out.println("Ficas deprimido e perdes 10 ❤\uFE0F \n");
            heroi.receberDano(10);
            heroi.exibirDetalhes();
        }
        heroi.usarItem();

        // Adiciona a opção para continuar, pressionando qualquer botao
        System.out.println("\n🔄 Pressione um botao para continuar para o próximo capítulo... 🔄");
        in.nextLine();
        capitulo2();
    }

    /**
     * Capitulo 2 da Jornada
     * A caminhada
     */
    private void capitulo2() {
        System.out.println("\n============= Capítulo 2 \uD83D\uDEB6 =============");
        System.out.println("\n" +
                " █████╗      ██████╗ █████╗ ███╗   ███╗██╗███╗   ██╗██╗  ██╗ █████╗ ██████╗  █████╗ \n" +
                "██╔══██╗    ██╔════╝██╔══██╗████╗ ████║██║████╗  ██║██║  ██║██╔══██╗██╔══██╗██╔══██╗\n" +
                "███████║    ██║     ███████║██╔████╔██║██║██╔██╗ ██║███████║███████║██║  ██║███████║\n" +
                "██╔══██║    ██║     ██╔══██║██║╚██╔╝██║██║██║╚██╗██║██╔══██║██╔══██║██║  ██║██╔══██║\n" +
                "██║  ██║    ╚██████╗██║  ██║██║ ╚═╝ ██║██║██║ ╚████║██║  ██║██║  ██║██████╔╝██║  ██║\n" +
                "╚═╝  ╚═╝     ╚═════╝╚═╝  ╚═╝╚═╝     ╚═╝╚═╝╚═╝  ╚═══╝╚═╝  ╚═╝╚═╝  ╚═╝╚═════╝ ╚═╝  ╚═╝\n" +
                "                                                                                    \n");
        System.out.println("Após horas de caminhada pelas terras frias de Amarante \uD83C\uDFF0, sentes uma leve brisa gélida 🌬️ de cortar o ar ❄️.");
        System.out.println("As sombras 👤👥 à tua volta parecem ganhar vida, sussurrando segredos 🗣️ que não consegues compreender.");
        System.out.println("Ao longe, avistas a ponte de S. Gonçalo 🌉, uma ponte antiga e decrépita que liga dois penhascos profundos ⛰️.");
        System.out.println("O chão range sob os teus pés enquanto atravessas, e um uivo 🐺 ecoa pela escuridão 🔊...");
        System.out.println("Ao pisar a última tábua da ponte, sentes um tremor 📳 no chão. Algo grande está por perto 😨.");
        System.out.println("O nevoeiro 🌫️ dissipa-se lentamente, e uma silhueta monstruosa 👹 surge diante de ti...\n");

        System.out.println("Aparece Cérbero \uD83D\uDC3A\uD83D\uDC3A\uD83D\uDC3A (Um cão selvagem com 3 cabeças) que tenta bloquear o teu caminho! \uD83D\uDED1 \n");
        LerFicheiro.LerFicheiroTexto("2.txt");
        System.out.println("\nEscolha:");
        System.out.println("1. Confiar na tua astúcia ✨ e fugir 🏃 ao Cérbero! (50% de Sucesso) ");
        System.out.println("2. Enfretá-lo com toda a tua força! \uD83D\uDCAA");

        int escolha = in.nextInt();
        in.nextLine();

        if (escolha == 2) {
            NPC cao = new NPC("Cérbero", 150, 15, 5);
            cao.exibirDetalhes();
            System.out.println("Decidiste enfrentar Cérbero \uD83D\uDC3A\uD83D\uDC3A\uD83D\uDC3A! ");
            Combate combateCerebros = new Combate(heroi, cao, this);
            combateCerebros.InciarCombate();
        } else {
            Random rnd = new Random();
            int chance = rnd.nextInt(100); // Gera um número aleatório entre 0 e 99

            if (chance < 50) { // 50% de hipotese de perder metade da vida
                int vidaPerdida = heroi.getVidaAtual() / 2;
                heroi.setVidaAtual(heroi.getVidaAtual() - vidaPerdida);
                System.out.println("Cérbero \uD83D\uDC3A\uD83D\uDC3A\uD83D\uDC3A não se deixou enganar pelo teu charme!");
                System.out.println("Perdeste metade da tua vida! ❤️‍🩹 (-" + vidaPerdida + " de vida)");
            } else {
                System.out.println("Usaste a tua astúcia e passaste sem confronto. \uD83D\uDE0E \n");

            }
        }
        heroi.exibirDetalhes();
        Vendedor vendedor = new Vendedor();
        vendedor.vender(heroi);
        heroi.usarItem();

        // Adiciona a opção para continuar, pressionando qualquer botao
        System.out.println("\n🔄 Pressione um botao para continuar para o próximo capítulo... 🔄");
        in.nextLine();

        capitulo3();
    }

    /**
     * Capitulo 3
     * O Autocarro Infernal
     */
    private void capitulo3() {
        System.out.println("\n=============🚌 Capítulo 3 🚌 =============");
        System.out.println("\n" +
                " █████╗ ██╗   ██╗████████╗ ██████╗  ██████╗ █████╗ ██████╗ ██████╗  ██████╗     ██╗███╗   ██╗███████╗███████╗██████╗ ███╗   ██╗ █████╗ ██╗     \n" +
                "██╔══██╗██║   ██║╚══██╔══╝██╔═══██╗██╔════╝██╔══██╗██╔══██╗██╔══██╗██╔═══██╗    ██║████╗  ██║██╔════╝██╔════╝██╔══██╗████╗  ██║██╔══██╗██║     \n" +
                "███████║██║   ██║   ██║   ██║   ██║██║     ███████║██████╔╝██████╔╝██║   ██║    ██║██╔██╗ ██║█████╗  █████╗  ██████╔╝██╔██╗ ██║███████║██║     \n" +
                "██╔══██║██║   ██║   ██║   ██║   ██║██║     ██╔══██║██╔══██╗██╔══██╗██║   ██║    ██║██║╚██╗██║██╔══╝  ██╔══╝  ██╔══██╗██║╚██╗██║██╔══██║██║     \n" +
                "██║  ██║╚██████╔╝   ██║   ╚██████╔╝╚██████╗██║  ██║██║  ██║██║  ██║╚██████╔╝    ██║██║ ╚████║██║     ███████╗██║  ██║██║ ╚████║██║  ██║███████╗\n" +
                "╚═╝  ╚═╝ ╚═════╝    ╚═╝    ╚═════╝  ╚═════╝╚═╝  ╚═╝╚═╝  ╚═╝╚═╝  ╚═╝ ╚═════╝     ╚═╝╚═╝  ╚═══╝╚═╝     ╚══════╝╚═╝  ╚═╝╚═╝  ╚═══╝╚═╝  ╚═╝╚══════╝\n" +
                "                                                                                                                                               \n");
        System.out.println("Ao chegar ao terminal, apanhas o Autocarro Infernal 🚌\uD83D\uDD25");
        System.out.println("Dentro do autocarro, encontras um Viajante Misterioso que te oferece um presente misterio 🎁");

        System.out.println("\nEscolha:");
        System.out.println("1. Aceitar e rezar que seja bom 🙏");
        System.out.println("2. Recusar e confiar na tua intuição 🤔");

        int escolha = in.nextInt();
        in.nextLine();

        if (escolha == 1) {
            System.out.println("Ganhaste 10 💰! Ainda bem que Confias-te!!");
            heroi.ganharOuro(10);
        }

        if (escolha == 2) {
            System.out.println("Recusas-te o presente e o Viajante ficou Furioso!! 😠");
            System.out.println("Tens de lutar contra ele!! ⚔️");
            NPC VendedorFurioso = new NPC("Vendedor Furioso", 200, 18, 10);
            VendedorFurioso.exibirDetalhes();

            Combate combateVendedor = new Combate(heroi, VendedorFurioso, this);
            combateVendedor.InciarCombate();
            Vendedor vendedor = new Vendedor();
            if (heroi.estaVivo()) {
                heroi.exibirDetalhes();
                vendedor.vender(heroi);
            }else {
                System.out.println("Perdes-te Por causa da tua escolha! Escolhe bem para a proxima!! ☠️");
            }
        }
        heroi.exibirDetalhes();
        heroi.usarItem();
        Vendedor vender = new Vendedor();
        vender.vender(heroi);

        // Adiciona a opção para continuar, pressionando qualquer botao
        System.out.println("\n🔄 Pressione um botao para continuar para o próximo capítulo... 🔄");
        in.nextLine();
        capitulo4();
    }

    /**
     * Capitulo 4
     * O Metro das Profundezas
     */
    private void capitulo4() {
        System.out.println("\n=============🚇 Capítulo 4 🚇 =============");
        System.out.println("\n" +
                "███╗   ███╗███████╗████████╗██████╗  ██████╗     ██████╗  █████╗ ███████╗    ██████╗ ██████╗  ██████╗ ███████╗██╗   ██╗███╗   ██╗██████╗ ███████╗███████╗ █████╗ ███████╗\n" +
                "████╗ ████║██╔════╝╚══██╔══╝██╔══██╗██╔═══██╗    ██╔══██╗██╔══██╗██╔════╝    ██╔══██╗██╔══██╗██╔═══██╗██╔════╝██║   ██║████╗  ██║██╔══██╗██╔════╝╚══███╔╝██╔══██╗██╔════╝\n" +
                "██╔████╔██║█████╗     ██║   ██████╔╝██║   ██║    ██║  ██║███████║███████╗    ██████╔╝██████╔╝██║   ██║█████╗  ██║   ██║██╔██╗ ██║██║  ██║█████╗    ███╔╝ ███████║███████╗\n" +
                "██║╚██╔╝██║██╔══╝     ██║   ██╔══██╗██║   ██║    ██║  ██║██╔══██║╚════██║    ██╔═══╝ ██╔══██╗██║   ██║██╔══╝  ██║   ██║██║╚██╗██║██║  ██║██╔══╝   ███╔╝  ██╔══██║╚════██║\n" +
                "██║ ╚═╝ ██║███████╗   ██║   ██║  ██║╚██████╔╝    ██████╔╝██║  ██║███████║    ██║     ██║  ██║╚██████╔╝██║     ╚██████╔╝██║ ╚████║██████╔╝███████╗███████╗██║  ██║███████║\n" +
                "╚═╝     ╚═╝╚══════╝   ╚═╝   ╚═╝  ╚═╝ ╚═════╝     ╚═════╝ ╚═╝  ╚═╝╚══════╝    ╚═╝     ╚═╝  ╚═╝ ╚═════╝ ╚═╝      ╚═════╝ ╚═╝  ╚═══╝╚═════╝ ╚══════╝╚══════╝╚═╝  ╚═╝╚══════╝\n" +
                "                                                                                                                                                                         \n");
        System.out.println("Ao chegar ao Porto, entras no Metro das Profundezas 🚇🌫️");
        System.out.println("Enfrentas uma Névoa da Confusão que tenta desorientar-te 😵‍💫");
        System.out.println("Tu sentes que devias sair agora do metro, mas não tens a certeza 🤷");

        System.out.println("\nEscolha:");
        System.out.println("1. Sair nesta paragem 🚶");
        System.out.println("2. Ficar no metro mais umas paragens 🚊");

        int escolha = in.nextInt();
        in.nextLine();

        if (escolha == 1) {
            System.out.println("Boa! Confiaste na tua intuição e ela não te falhou! Saiste na paragem certa! ✨");
            System.out.println("Mas com a Confusão perdes-te 10 de 💰");
            heroi.rouboOuro(10);
            heroi.exibirDetalhes();
            Vendedor vender = new Vendedor();
            vender.vender(heroi);
            capitulo5();
        } else {
            System.out.println("A Névoa da Confusão teve o seu efeito em ti! 😵");
            System.out.println("Acabaste por sair quatro paragens depois da que devias... 😞");
            System.out.println("Ficas irritado e perdes 5 ❤️ \n");
            heroi.receberDano(5);
            heroi.exibirDetalhes();
            Vendedor vendedor = new Vendedor();
            vendedor.vender(heroi);
            heroi.usarItem();

            // Adiciona a opção para continuar, pressionando qualquer botao
            System.out.println("\n🔄 Pressione um botao para continuar para o próximo capítulo... 🔄");
            in.nextLine();
            capitulo4Alt();
        }
    }

    /**
     * Capitulo 4.1
     * A Rota Dos Perdidos
     */
    private void capitulo4Alt() {
        System.out.println("\n============= Capítulo 4.1 \uD83C\uDF2B\uFE0F =============");
        System.out.println("\n" +
                "██████╗  ██████╗ ████████╗ █████╗     ██████╗  ██████╗ ███████╗    ██████╗ ███████╗██████╗ ██████╗ ██╗██████╗  ██████╗ ███████╗\n" +
                "██╔══██╗██╔═══██╗╚══██╔══╝██╔══██╗    ██╔══██╗██╔═══██╗██╔════╝    ██╔══██╗██╔════╝██╔══██╗██╔══██╗██║██╔══██╗██╔═══██╗██╔════╝\n" +
                "██████╔╝██║   ██║   ██║   ███████║    ██║  ██║██║   ██║███████╗    ██████╔╝█████╗  ██████╔╝██║  ██║██║██║  ██║██║   ██║███████╗\n" +
                "██╔══██╗██║   ██║   ██║   ██╔══██║    ██║  ██║██║   ██║╚════██║    ██╔═══╝ ██╔══╝  ██╔══██╗██║  ██║██║██║  ██║██║   ██║╚════██║\n" +
                "██║  ██║╚██████╔╝   ██║   ██║  ██║    ██████╔╝╚██████╔╝███████║    ██║     ███████╗██║  ██║██████╔╝██║██████╔╝╚██████╔╝███████║\n" +
                "╚═╝  ╚═╝ ╚═════╝    ╚═╝   ╚═╝  ╚═╝    ╚═════╝  ╚═════╝ ╚══════╝    ╚═╝     ╚══════╝╚═╝  ╚═╝╚═════╝ ╚═╝╚═════╝  ╚═════╝ ╚══════╝\n" +
                "                                                                                                                               \n");
        System.out.println("Enquanto estás a tentar decidir qual o melhor caminho a tomar, uma pessoa vai contra ti 💥");

        System.out.println("\nEscolha:");
        System.out.println("1. Ignorar 🙈");
        System.out.println("2. Chamar à atenção 🗣️");
        System.out.println("3. Não estás no teu melhor dia... Enfretá-lo com toda a tua força 💪");

        int escolha = in.nextInt();
        int rng = rnd.nextInt(100);
        NPC carteirista = new NPC("Carteirista", 60, 20, 15);
        carteirista.exibirDetalhes();
        in.nextLine();

        switch (escolha) {
            case 1:
                System.out.println("Decidiste ignorar o que aconteceu... 😶");

                int ouroRoubado = heroi.rouboOuro(5);

                if (ouroRoubado > 0) {
                    System.out.println("Mas depois percebes que os teus bolsos estão mais leves 😱");
                    System.out.println("Perdeste " + ouroRoubado + " 💰!!\n");
                }

                break;
            case 2:
                System.out.println("Quando o chamaste à atenção ele começou a correr... 🏃");
                System.out.println("Ao mesmo tempo, percebeste que ele te roubou algum 💰!!\n");

                System.out.println("\nEscolha:");
                System.out.println("1. Correr atrás dele 🏃‍♂️");
                System.out.println("2. Não vale a pena, tens é de chegar a tempo ao teu destino (CESAE)! ⏰");

                escolha = in.nextInt();

                if (escolha == 1) {
                    if (rng <= 40) {
                        System.out.println("Quando finalmente o apanhaste, reparas que ele tinha amigos e agora vais ter de os enfrentar! 👥");
                        System.out.println("Reparas que o primeiro Carteirista parece ter bastante força mas não parece muito resistente! 💪\n");

                        NPC carteiristaForte = new NPC("Carteirista Forte", 120, 20, 5);
                        carteiristaForte.exibirDetalhes();

                        Combate combatecarteiristaForte = new Combate(heroi, carteiristaForte, this);
                        combatecarteiristaForte.InciarCombate();

                        System.out.println("Depois de derrotares o primeiro Carteirista um segundo começa a vir na tua direção 🆕");
                        System.out.println("Este parece ser o oposto do primeiro! 🛡️\n");
                        NPC carteiristaResistente = new NPC("Carteirista Resistente", 165, 10, 5);
                        carteiristaResistente.exibirDetalhes();
                        Combate combateresistente = new Combate(heroi, carteiristaResistente, this);
                        combateresistente.InciarCombate();

                        System.out.println("Ao ver os seus parceiros derrotados, o Carteirista que foi contra ti decide enfrentar-te para os vingar! ⚔️\n");
                    }
                    Combate combatecarteirista = new Combate(heroi, carteirista, this);
                    combatecarteirista.InciarCombate();
                } else {
                    int ouroPerdido = heroi.rouboOuro(5);

                    if (ouroPerdido > 0) {
                        System.out.println("Perdeste " + ouroPerdido + " 💰!!\n");
                    }
                }

                break;
            case 3:
                System.out.println("Decidiste agarra-lo antes dele conseguir afastar-se! 🤜");
                System.out.println("Está na hora de lhe mostrar que ele foi contra ti no dia errado! ⚔️\n");

                Combate combatecarteirista = new Combate(heroi, carteirista, this);
                combatecarteirista.InciarCombate();
                break;
        }

        System.out.println("Reparaste que o autocarro que costumas apanhar tem uma paragem aqui perto! 🚌\n");

        heroi.exibirDetalhes();
        Vendedor vendedor = new Vendedor();
        vendedor.vender(heroi);
        heroi.usarItem();

        // Adiciona a opção para continuar, pressionando qualquer botao
        System.out.println("\n🔄 Pressione um botao para continuar para o próximo capítulo... 🔄");
        in.nextLine();
        capitulo5();
    }

    /**
     * Capitulo 5
     * A Chegada a Cesae
     */
    private void capitulo5() {
        System.out.println("\n=============🏢 Capítulo 5 🏢 =============");
        System.out.println("\n" +
                " ██████╗██╗  ██╗███████╗ ██████╗  █████╗ ██████╗  █████╗      █████╗      ██████╗███████╗███████╗ █████╗ ███████╗\n" +
                "██╔════╝██║  ██║██╔════╝██╔════╝ ██╔══██╗██╔══██╗██╔══██╗    ██╔══██╗    ██╔════╝██╔════╝██╔════╝██╔══██╗██╔════╝\n" +
                "██║     ███████║█████╗  ██║  ███╗███████║██║  ██║███████║    ███████║    ██║     █████╗  ███████╗███████║█████╗  \n" +
                "██║     ██╔══██║██╔══╝  ██║   ██║██╔══██║██║  ██║██╔══██║    ██╔══██║    ██║     ██╔══╝  ╚════██║██╔══██║██╔══╝  \n" +
                "╚██████╗██║  ██║███████╗╚██████╔╝██║  ██║██████╔╝██║  ██║    ██║  ██║    ╚██████╗███████╗███████║██║  ██║███████╗\n" +
                " ╚═════╝╚═╝  ╚═╝╚══════╝ ╚═════╝ ╚═╝  ╚═╝╚═════╝ ╚═╝  ╚═╝    ╚═╝  ╚═╝     ╚═════╝╚══════╝╚══════╝╚═╝  ╚═╝╚══════╝\n" +
                "                                                                                                                 \n");
        System.out.println("Finalmente chegas a CESAE 🏢, mas a entrada está bloqueada por uma Nuvem de Bugs! 🌫️🐛");
        System.out.println("Um Professor Guardião 🧙‍♂️ aproxima-se e diz que só te deixa entrar se provares o teu valor.");

        System.out.println("\nEscolha:");
        System.out.println("1. Tentar hackear o sistema de segurança 💻");
        System.out.println("2. Enfrentar o desafio do Professor 📚");
        System.out.println("3. Procurar uma entrada alternativa 🔍");

        int escolha = in.nextInt();
        in.nextLine();

        switch (escolha) {
            case 1:
                System.out.println("Ao tentar hackear, despoletas um sistema de segurança! 🚨");
                System.out.println("Um Bug Corrupto aparece para te enfrentar! ⚔️\n");

                NPC bugCorrupto = new NPC("Bug Corrupto", 150, 20, 10);
                bugCorrupto.exibirDetalhes();
                Combate lutabug = new Combate(heroi, bugCorrupto, this);
                lutabug.InciarCombate();

                if (heroi.estaVivo()) {
                    System.out.println("Conseguiste derrotar o Bug, mas perdeste tempo valioso! ⏰");
                    heroi.receberDano(10);
                    System.out.println("O atraso custou-te 10 ❤️\n");
                }
                break;

            case 2:
                System.out.println("A Professor Saravel prepara-se para te testar! 🎓");

                int rng = rnd.nextInt(100);
                if (rng <= 50) {
                    System.out.println("É um desafio de programação! Aparece um Bug Minor 🐞");
                    NPC bugMinor = new NPC("Bug Minor", 75, 20, 5);
                    bugMinor.exibirDetalhes();
                    Combate lutabugMinor = new Combate(heroi, bugMinor, this);
                    lutabugMinor.InciarCombate();

                    if (heroi.estaVivo()) {
                        System.out.println("Impressionado com a tua performance, a Professora Saravel oferece-te uma Poção de Debugging! 🧪");
                        heroi.curar(20);
                        System.out.println("Recuperaste 20 ❤️!");
                    }
                } else {
                    System.out.println("É um desafio de lógica! Um Bug Major aparece! 🪲");
                    NPC bugMajor = new NPC("Bug Major", 100, 20, 10);
                    bugMajor.exibirDetalhes();
                    Combate lutabugMajor = new Combate(heroi, bugMajor, this);
                    lutabugMajor.InciarCombate();

                    if (heroi.estaVivo()) {
                        System.out.println("Victoria! A Professora Saravel recompensa-te com Código Mágico! ✨");
                        heroi.ganharOuro(15);
                        System.out.println("Ganhaste 15 💰!");
                    }
                }
                break;

            case 3:
                System.out.println("Encontras uma janela partida, mas está guardada por um Estagiário Vigilante! 👀");
                System.out.println("\nEscolha:");
                System.out.println("1. Tentar suborná-lo 💰");
                System.out.println("2. Desafiá-lo para um duelo 🤺");

                int escolhaAlt = in.nextInt();
                in.nextLine();

                if (escolhaAlt == 1) {
                    if (heroi.getOuro() >= 20) {
                        System.out.println("O Estagiário aceita o suborno! Perdeste 20 💰");
                        heroi.rouboOuro(20);
                    } else {
                        System.out.println("Não tens ouro suficiente! O Estagiário fica ofendido! 😠");
                        System.out.println("Prepara-te para lutar! ⚔️");
                        NPC estagiarioFurioso = new NPC("Estagiário Furioso", 120, 19, 8);
                        estagiarioFurioso.exibirDetalhes();
                        Combate lutaestagiarioFurioso = new Combate(heroi, estagiarioFurioso, this);
                        lutaestagiarioFurioso.InciarCombate();
                    }
                } else {
                    System.out.println("O Estagiário aceita o teu desafio! ⚔️");
                    NPC estagiario = new NPC("Estagiário Vigilante", 90, 15, 6);
                    estagiario.exibirDetalhes();
                    Combate lutaestagiario = new Combate(heroi, estagiario, this);
                    lutaestagiario.InciarCombate();
                }
                break;
        }

        if (heroi.estaVivo()) {
            System.out.println("\nFinalmente consegues entrar no CESAE! 🎉");
            System.out.println("Mas as tuas aventuras ainda não acabaram...");
            System.out.println("Um Professor de Algoritmos aproxima-se com um sorriso misterioso... 👨‍🏫\n");
            heroi.exibirDetalhes();
        } else {
            System.out.println("Game Over! ☠️ Tenta novamente com mais sabedoria!");
        }
        Vendedor vendedor = new Vendedor();
        vendedor.vender(heroi);
        heroi.usarItem();

        // Adiciona a opção para continuar, pressionando qualquer botao
        System.out.println("\n🔄 Pressione um botao para continuar para o próximo capítulo... 🔄");
        in.nextLine();
        capitulo6();
    }

    /**
     * Capitulo 6
     * O Desafio de Javitor
     */
    private void capitulo6() {
        System.out.println("\n=============☕  Capítulo 6 ☕ =============");
        System.out.println("\n" +
                "██████╗ ███████╗███████╗ █████╗ ███████╗██╗ ██████╗   \n" +
                "██╔══██╗██╔════╝██╔════╝██╔══██╗██╔════╝██║██╔═══██╗  \n" +
                "██║  ██║█████╗  ███████╗███████║█████╗  ██║██║   ██║  \n" +
                "██║  ██║██╔══╝  ╚════██║██╔══██║██╔══╝  ██║██║   ██║  \n" +
                "██████╔╝███████╗███████║██║  ██║██║     ██║╚██████╔╝  \n" +
                "╚═════╝ ╚══════╝╚══════╝╚═╝  ╚═╝╚═╝     ╚═╝ ╚═════╝   \n" +
                "                                                      \n" +
                "     ██╗ █████╗ ██╗   ██╗██╗████████╗ ██████╗ ██████╗ \n" +
                "     ██║██╔══██╗██║   ██║██║╚══██╔══╝██╔═══██╗██╔══██╗\n" +
                "     ██║███████║██║   ██║██║   ██║   ██║   ██║██████╔╝\n" +
                "██   ██║██╔══██║╚██╗ ██╔╝██║   ██║   ██║   ██║██╔══██╗\n" +
                "╚█████╔╝██║  ██║ ╚████╔╝ ██║   ██║   ╚██████╔╝██║  ██║\n" +
                " ╚════╝ ╚═╝  ╚═╝  ╚═══╝  ╚═╝   ╚═╝    ╚═════╝ ╚═╝  ╚═╝\n" +
                "                                                      \n");
        System.out.println("O Professor Vitor aproxima-se com um sorriso desafiador 👨‍🏫");
        System.out.println("\"Para passar na minha aula, terás de provar o teu valor em Java!\" 📚");
        System.out.println("Uma aura de código Java começa a manifestar-se à sua volta... ✨");

        System.out.println("\nEscolha:");
        System.out.println("1. Aceitar o desafio de frente 💪");
        System.out.println("2. Tentar negociar um trabalho extra 📝");
        System.out.println("3. Pedir ajuda aos colegas de turma 👥");

        int escolha = in.nextInt();
        in.nextLine();

        switch (escolha) {
            case 1:
                System.out.println("\"Muito bem!\" - exclama o Professor Vitor - \"Mostra-me o que sabes!\" 🎯");
                System.out.println("O Professor invoca três Exceptions para te testar! ⚡");

                NPC nullPointerException = new NPC("NullPointerException", 80, 15, 8);
                nullPointerException.exibirDetalhes();
                System.out.println("\nPrimeiro desafio: NullPointerException ataca! 💥");
                Combate lutanull = new Combate(heroi, nullPointerException, this);
                lutanull.InciarCombate();

                if (heroi.estaVivo()) {
                    NPC arrayIndexException = new NPC("ArrayIndexOutOfBoundsException", 100, 19, 10);
                    arrayIndexException.exibirDetalhes();
                    System.out.println("\nSegundo desafio: ArrayIndexOutOfBoundsException surge! 📊");
                    Combate lutaarray = new Combate(heroi, arrayIndexException, this);
                    lutaarray.InciarCombate();

                    if (heroi.estaVivo()) {
                        System.out.println("\nChegou a hora do desafio final! Professor Vitor prepara-se! ⚔️");
                        NPC professorVitor = new NPC("Professor Vitor - Mestre de Java", 250, 35, 20);
                        professorVitor.exibirDetalhes();
                        Combate lutaVitor = new Combate(heroi, professorVitor, this);
                        lutaVitor.InciarCombate();

                        if (heroi.estaVivo()) {
                            System.out.println("\n\"Impressionante!\" - diz o Professor - \"Mereces isto!\" 🏆");
                            heroi.ganharOuro(50);
                            heroi.curar(30);
                            System.out.println("Recebeste 50 💰 e recuperaste 30 ❤️!");
                        }
                    }
                }
                break;

            case 2:
                System.out.println("O Professor considera a tua proposta... 🤔");
                int rng = rnd.nextInt(100);

                if (rng <= 30) {
                    System.out.println("\"Está bem, mas vai ser um desafio e tanto!\" 📚");
                    System.out.println("Recebes um Projeto Extra de Java para desenvolver 💻");

                    if (heroi.getOuro() >= 25) {
                        System.out.println("Gastas tempo e recursos no projeto ⏳");
                        heroi.rouboOuro(25);
                        System.out.println("Perdeste 25 💰 mas evitaste a batalha!");
                    } else {
                        System.out.println("Não tens recursos suficientes para o projeto! 😱");
                        System.out.println("O Professor Vitor decide testar-te em combate! ⚔️");
                        NPC professorVitorIrritado = new NPC("Professor Vitor Irritado", 250, 40, 25);
                        professorVitorIrritado.exibirDetalhes();
                        Combate lutaVitorIrritado = new Combate(heroi, professorVitorIrritado, this);
                        lutaVitorIrritado.InciarCombate();
                    }
                } else {
                    System.out.println("\"Não há atalhos no caminho da programação!\" 🚫");
                    System.out.println("O Professor Vitor prepara-se para o combate! ⚔️");
                    NPC professorVitor = new NPC("Professor Vitor", 200, 40, 20);
                    professorVitor.exibirDetalhes();
                    Combate lutaVitor = new Combate(heroi, professorVitor, this);
                    lutaVitor.InciarCombate();
                }
                break;

            case 3:
                System.out.println("Os teus colegas oferecem-se para ajudar! 🤝");
                System.out.println("Mas o Professor Vitor nota a tentativa... 👀");
                System.out.println("\"Trabalho em equipa? Interessante...\" 🤔");

                System.out.println("\nEscolha:");
                System.out.println("1. Fazer pair programming com um colega 👥");
                System.out.println("2. Tentar resolver sozinho mesmo assim 💪");

                int escolhaAlt = in.nextInt();
                in.nextLine();

                if (escolhaAlt == 1) {
                    System.out.println("O Professor aceita o pair programming! 👍");
                    System.out.println("A batalha será mais fácil, mas dividirás as recompensas 📊");
                    NPC professorVitorPair = new NPC("Professor Vitor - Modo Pair", 150, 20, 15);
                    professorVitorPair.exibirDetalhes();
                    Combate lutaVitorPair = new Combate(heroi, professorVitorPair, this);
                    lutaVitorPair.InciarCombate();

                    if (heroi.estaVivo()) {
                        System.out.println("Vitória em equipa! Mas a recompensa é menor 🏆");
                        heroi.ganharOuro(20);
                        System.out.println("Recebeste apenas 20 💰");
                    }
                } else {
                    System.out.println("\"Decisão corajosa!\" - exclama o Professor 👏");
                    NPC professorVitor = new NPC("Professor Vitor - Impressionado", 180, 25, 18);
                    professorVitor.exibirDetalhes();
                    Combate lutaVitor = new Combate(heroi, professorVitor, this);
                    lutaVitor.InciarCombate();
                }
                break;
        }

        if (heroi.estaVivo()) {
            System.out.println("\n\"Parabéns! Provaste o teu valor em Java!\" 🎊");
            System.out.println("O Professor Vitor entrega-te um Certificado de Java ✅");
            System.out.println("Mas ainda há mais desafios pela frente...");
            System.out.println("Ouves rumores sobre o tempido Professor de Base de Dados... 📀\n");
            heroi.exibirDetalhes();
        } else {
            System.out.println("Game Over! ☠️ Estudar Java é fundamental!");
        }
        Vendedor vendedor = new Vendedor();
        vendedor.vender(heroi);
        heroi.usarItem();

        // Adiciona a opção para continuar, pressionando qualquer botao
        System.out.println("\n🔄 Pressione um botao para continuar para o próximo capítulo... 🔄");
        in.nextLine();
        capitulo7();
    }

    /**
     * Capitulo 7
     * O Boss Final - O Curso
     */
    private void capitulo7() {
        System.out.println("\n============= ☠️ Capítulo 7 ☠️ =============");
        System.out.println("\n" +
                " ██████╗██╗   ██╗██████╗ ███████╗ ██████╗     ███████╗ ██████╗ ███████╗████████╗██████╗ ███████╗██╗   ██╗\n" +
                "██╔════╝██║   ██║██╔══██╗██╔════╝██╔═══██╗    ██╔════╝██╔═══██╗██╔════╝╚══██╔══╝██╔══██╗██╔════╝██║   ██║\n" +
                "██║     ██║   ██║██████╔╝███████╗██║   ██║    ███████╗██║   ██║█████╗     ██║   ██║  ██║█████╗  ██║   ██║\n" +
                "██║     ██║   ██║██╔══██╗╚════██║██║   ██║    ╚════██║██║   ██║██╔══╝     ██║   ██║  ██║██╔══╝  ╚██╗ ██╔╝\n" +
                "╚██████╗╚██████╔╝██║  ██║███████║╚██████╔╝    ███████║╚██████╔╝██║        ██║   ██████╔╝███████╗ ╚████╔╝ \n" +
                " ╚═════╝ ╚═════╝ ╚═╝  ╚═╝╚══════╝ ╚═════╝     ╚══════╝ ╚═════╝ ╚═╝        ╚═╝   ╚═════╝ ╚══════╝  ╚═══╝  \n" +
                "                                                                                                         \n" +
                "         ██████╗     ██████╗  ██████╗ ███████╗███████╗    ███████╗██╗███╗   ██╗ █████╗ ██╗               \n" +
                "        ██╔═══██╗    ██╔══██╗██╔═══██╗██╔════╝██╔════╝    ██╔════╝██║████╗  ██║██╔══██╗██║               \n" +
                "        ██║   ██║    ██████╔╝██║   ██║███████╗███████╗    █████╗  ██║██╔██╗ ██║███████║██║               \n" +
                "        ██║   ██║    ██╔══██╗██║   ██║╚════██║╚════██║    ██╔══╝  ██║██║╚██╗██║██╔══██║██║               \n" +
                "        ╚██████╔╝    ██████╔╝╚██████╔╝███████║███████║    ██║     ██║██║ ╚████║██║  ██║███████╗          \n" +
                "         ╚═════╝     ╚═════╝  ╚═════╝ ╚══════╝╚══════╝    ╚═╝     ╚═╝╚═╝  ╚═══╝╚═╝  ╚═╝╚══════╝          \n" +
                "                                                                                                         \n");
        System.out.println("A sala de aula transforma-se numa arena de desafios finais! ⚔️");
        System.out.println("O Curso materializa-se à tua frente como uma entidade poderosa! 🌟");
        System.out.println("\"Prepare-se para o desafio definitivo!\" 📚");

        System.out.println("\nFase 1: Teoria 📖");
        System.out.println("O conhecimento teórico manifesta-se como um oponente!");

        NPC teoriaDesafio = new NPC("Desafio Teórico", 150, 20, 15);
        teoriaDesafio.exibirDetalhes();
        System.out.println("Escolha como quer enfrentar a teoria:");
        System.out.println("1. Estudar intensamente 📚");
        System.out.println("2. Consultar apontamentos antigos 📝");
        System.out.println("3. Pedir ajuda aos veteranos 🎓");

        int escolha = in.nextInt();
        in.nextLine();
        boolean resultadoCombate;

        switch (escolha) {
            case 1:
                System.out.println("Escolheste enfrentar a teoria de frente! 💪");
                Combate teoria = new Combate(heroi, teoriaDesafio, this);
                teoria.InciarCombate();

                if (heroi.estaVivo()) {
                    System.out.println("A teoria foi dominada! Ganhas Conhecimento Teórico! 🧠");
                    heroi.curar(30);
                }

                break;
            case 2:
                System.out.println("Os apontamentos antigos revelam segredos! 📖");
                teoriaDesafio.setForca(15); // reduz a dificuldade
                Combate teoriaNova = new Combate(heroi, teoriaDesafio, this);

                teoriaNova.InciarCombate();
                break;
            case 3:
                System.out.println("Os veteranos compartilham suas experiências! 🤝");
                teoriaDesafio.setVida(100); // Reduz a vida do desafio
                Combate teoriaRecente = new Combate(heroi, teoriaDesafio, this);
                teoriaRecente.InciarCombate();
                break;
        }
        heroi.usarItem();

        if (heroi.estaVivo()) {
            System.out.println("\nFase 2: Prática 💻");
            System.out.println("Códigos e algoritmos ganham vida!");

            NPC praticaDesafio = new NPC("Desafio Prático", 200, 25, 20);
            praticaDesafio.exibirDetalhes();
            System.out.println("\nO compilador exige perfeição! Escolha sua abordagem:");
            System.out.println("1. Debugging intensivo 🔍");
            System.out.println("2. Refatoração de código ⚡");
            System.out.println("3. Stack Overflow pesquisa 🌐");

            escolha = in.nextInt();
            in.nextLine();

            switch (escolha) {
                case 1:
                    System.out.println("Bug por bug, vais resolvendo! 🐛");
                    Combate desporto = new Combate(heroi, praticaDesafio, this);
                    desporto.InciarCombate();
                    break;
                case 2:
                    System.out.println("O código flui como água! 🌊");
                    if (heroi.getOuro() >= 30) {
                        System.out.println("Usas recursos para otimizar o código!");
                        heroi.rouboOuro(30);
                        praticaDesafio.setForca(20);
                    }
                    Combate desporto1 = new Combate(heroi, praticaDesafio, this);
                    desporto1.InciarCombate();
                    break;
                case 3:
                    System.out.println("A sabedoria da comunidade te ajuda! 👥");
                    praticaDesafio.setVida(150);
                    Combate desporto2 = new Combate(heroi, praticaDesafio, this);
                    desporto2.InciarCombate();
                    break;
            }
        }
        Vendedor vendedor = new Vendedor();
        vendedor.vender(heroi);
        heroi.usarItem();
        if (heroi.estaVivo()) {
            System.out.println("\nFase Final: Projeto Final 🏆");
            System.out.println("O verdadeiro desafio se revela!");

            NPC projetoFinal = new NPC("Projeto Final", 400, 45, 0);
            System.out.println("O Curso de Software Development assume sua forma final! ✨");
            System.out.println("\nEscolha sua estratégia final:");
            System.out.println("1. Mergulho profundo no código 🤿");
            System.out.println("2. Maratona de programação ⏱️");
            System.out.println("3. Trabalho em equipe 👥");

            escolha = in.nextInt();
            in.nextLine();

            switch (escolha) {
                case 1:
                    System.out.println("Cada linha de código é uma batalha! ⚔️");
                    if (heroi.getVidaAtual() > 50) {
                        System.out.println("Tua experiência te fortalece! 💪");
                        projetoFinal.setForca(30);
                    }
                    break;
                case 2:
                    System.out.println("O tempo é teu aliado! ⏰");
                    heroi.curar(50);
                    System.out.println("Recuperaste energia! Ganhas 50 ❤️");
                    break;
                case 3:
                    System.out.println("Unidos somos mais fortes! 🤝");
                    projetoFinal.setVida(250);
                    heroi.curar(30);
                    System.out.println("O apoio da equipe te fortalece! Ganhas 30 ❤️");
                    break;
            }

            System.out.println("\nA batalha final começa! ⚔️");
            Combate lutaFinal = new Combate(heroi, projetoFinal, this);
            lutaFinal.InciarCombate();
        }

        if (heroi.estaVivo()) {
            System.out.println("\n" +
                    "██╗   ██╗ ██████╗ ██╗   ██╗    ██╗    ██╗██╗███╗   ██╗██╗\n" +
                    "╚██╗ ██╔╝██╔═══██╗██║   ██║    ██║    ██║██║████╗  ██║██║\n" +
                    " ╚████╔╝ ██║   ██║██║   ██║    ██║ █╗ ██║██║██╔██╗ ██║██║\n" +
                    "  ╚██╔╝  ██║   ██║██║   ██║    ██║███╗██║██║██║╚██╗██║╚═╝\n" +
                    "   ██║   ╚██████╔╝╚██████╔╝    ╚███╔███╔╝██║██║ ╚████║██╗\n" +
                    "   ╚═╝    ╚═════╝  ╚═════╝      ╚══╝╚══╝ ╚═╝╚═╝  ╚═══╝╚═╝\n" +
                    "                                                         \n");
            System.out.println("\n🎉 PARABÉNS! 🎉");
            System.out.println("Conquistaste o Curso de Software Development! 🏆");
            System.out.println("Teus conhecimentos foram provados! 📚");
            System.out.println("És agora um verdadeiro Desenvolvedor de Software! 💻");
            heroi.ganharOuro(100);
            System.out.println("Recebeste 100 💰 como prêmio final!");
            heroi.getInventario();
            finalJornada();
        }
    }

    public void GameOver() {
        System.out.println("\n" +
                " ██████╗  █████╗ ███╗   ███╗███████╗     ██████╗ ██╗   ██╗███████╗██████╗ ██╗\n" +
                "██╔════╝ ██╔══██╗████╗ ████║██╔════╝    ██╔═══██╗██║   ██║██╔════╝██╔══██╗██║\n" +
                "██║  ███╗███████║██╔████╔██║█████╗      ██║   ██║██║   ██║█████╗  ██████╔╝██║\n" +
                "██║   ██║██╔══██║██║╚██╔╝██║██╔══╝      ██║   ██║╚██╗ ██╔╝██╔══╝  ██╔══██╗╚═╝\n" +
                "╚██████╔╝██║  ██║██║ ╚═╝ ██║███████╗    ╚██████╔╝ ╚████╔╝ ███████╗██║  ██║██╗\n" +
                " ╚═════╝ ╚═╝  ╚═╝╚═╝     ╚═╝╚══════╝     ╚═════╝   ╚═══╝  ╚══════╝╚═╝  ╚═╝╚═╝\n" +
                "                                                                             \n");
        System.out.println("☠️ O Curso provou ser um desafio maior do que esperavas. ☠️");
        System.out.println("Mas lembra-te: cada tentativa te torna mais forte! 💪");

        fimDoJogo();
    }

    /**
     * Final da Jornada do Herói
     * Final: A Lenda do Programador
     */
    private void finalJornada() {
        System.out.println("\n============= Final:⭐ A Lenda do Programador ⭐ =============");
        System.out.println("O Curso de Software Development dissolve-se numa explosão de luz código! ✨");
        System.out.println("Uma aura de sabedoria envolve-te enquanto os professores se reúnem... 🌟");

        System.out.println("\nO Professor Vítor avança com um sorriso orgulhoso 👨‍🏫");
        System.out.println("\"Desde o primeiro dia de Java, viu potencial em ti!\"");

        System.out.println("\nUma luz dourada materializa-se nas tuas mãos... 🌈");
        System.out.println("=== Conquistas Desbloqueadas! ===");
        System.out.println("🏆 Diploma de Mestre do Código");
        System.out.println("⚔️ Veterano das Exceptions");
        System.out.println("💻 Dominador de Algoritmos");
        System.out.println("📚 Sábio das Base de Dados");
        System.out.println("🎓 Lenda do CESAE");

        System.out.println("\nOs teus companheiros de jornada aproximam-se para celebrar! 🤝");
        System.out.println("As histórias das tuas batalhas contra Bugs e Exceptions");
        System.out.println("serão contadas por gerações de programadores! 📖");

        System.out.println("\nDe Amarante para o Mundo! 🌍");
        System.out.println("A tua jornada como Herói do Código está apenas a começar... ⭐");

        System.out.println("\n\uD83C\uDFC6 ==== Estatísticas Finais ==== \uD83C\uDFC6");
        heroi.exibirDetalhes();

        System.out.println("\n\"O código é forte neste!\" - sussurram os professores 👥");
        System.out.println("E assim, uma nova lenda nasceu no CESAE... ⚡");
        System.out.println("\n🎉 THE END 🎉");

        fimDoJogo();
    }

    /**
     * Final do jogo
     * Pergunta ao jogador se quer voltar a jogar ou não. Se o jogador quiser continuar a jogar
     * pergunta se pretende continuar com o mesmo heroi ou criar um novo.
     */
    private void fimDoJogo() {
        System.out.println("\n ==== Jogar outra vez? ==== ");

        while (true) {
            System.out.println("⚔️ Desejas voltar a jogar? ⚔️ (S/N)");
            String jogar = in.nextLine().toUpperCase();

            if (jogar.equals("N")) {
                System.out.println("Obrigado por jogares! Que o código esteja contigo! 💫");
                System.exit(0);
            } else if (jogar.equals("S")) {
                break;
            } else {
                System.out.println("❌ Opção inválida! Por favor, escolhe S ou N.");
            }
        }

        while (true) {
            System.out.println("⚔️ Queres criar um heroi novo? ⚔️ (S/N)");
            String jogar = in.nextLine().toUpperCase();

            if (jogar.equals("N")) {
                this.heroi = this.heroiBase.Clone();
                System.out.println("\n🏆 ===== Herói " + heroi.getNome() + " " + nomeJogador + " ===== 🏆\n");
                this.heroi.exibirDetalhes();
                break;
            } else if (jogar.equals("S")) {
                criarPersonagem();
                break;
            } else {
                System.out.println("❌ Opção inválida! Por favor, escolhe S ou N.");
            }
        }

        iniciarJornada();
    }
}