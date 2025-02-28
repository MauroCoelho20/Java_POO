package entidades;

import items.ArmaPrincipal;
import items.ConsumivelCombate;
import items.ItemHeroi;
import items.Pocao;

import java.util.*;
/**
 * Classe que representa um Vendedor no jogo.
 * Permite ao herói comprar itens como armas, poções e consumíveis.
 */
public class Vendedor {
    private ArrayList<ItemHeroi> loja;
    /**
     * Construtor da classe Vendedor.
     * Inicializa o estoque com diferentes tipos de itens.
     */
    public Vendedor() {
        this.loja = new ArrayList<>();
    }

    /**
     * Metodo que inclui no Vendedor as Pocoes, ConsumiveisCombate e armas para uso do Heroi
     * Iniciando a loja no jogo
     */
    private void iniciarloja() {

        // Poções
        Pocao pocaoCura = new Pocao("🧪 Poção de Cura", 2, 20, 0);
        pocaoCura.addHeroiPermitido("BackEnd");
        pocaoCura.addHeroiPermitido("FrontEnd");
        pocaoCura.addHeroiPermitido("FullStack");

        Pocao pocaoCuraMaior = new Pocao("🧪 Poção de Cura Maior", 6, 50, 0);
        pocaoCuraMaior.addHeroiPermitido("BackEnd");
        pocaoCuraMaior.addHeroiPermitido("FrontEnd");
        pocaoCuraMaior.addHeroiPermitido("FullStack");

        Pocao pocaoChoro = new Pocao("💧 Poção de Choro", 7, 0, 30);
        pocaoChoro.addHeroiPermitido("FrontEnd");

        Pocao pocaoVigor = new Pocao("💪 Poção de Vigor", 4, 35, 0);
        pocaoVigor.addHeroiPermitido("FrontEnd");

        Pocao elixirMistico = new Pocao("✨ Elixir Místico", 15, 25, 25);
        elixirMistico.addHeroiPermitido("FullStack");

        Pocao pocaoRegeneracao = new Pocao("❤️ Poção de Regeneração", 8, 15, 15);
        pocaoRegeneracao.addHeroiPermitido("BackEnd");

        Pocao frascoVida = new Pocao("🫀 Frasco de Vida", 3, 25, 0);
        frascoVida.addHeroiPermitido("BackEnd");
        frascoVida.addHeroiPermitido("FrontEnd");
        frascoVida.addHeroiPermitido("FullStack");

        Pocao elixirPoder = new Pocao("🔮 Elixir de Poder", 14, 0, 50);
        elixirPoder.addHeroiPermitido("FullStack");
        elixirPoder.addHeroiPermitido("FrontEnd");

        Pocao pocaoVitalidade = new Pocao("💖 Poção de Vitalidade", 5, 30, 0);
        pocaoVitalidade.addHeroiPermitido("BackEnd");
        pocaoVitalidade.addHeroiPermitido("FullStack");

        Pocao essenciaMagica = new Pocao("🌟 Essência Mágica", 9, 0, 40);
        essenciaMagica.addHeroiPermitido("FrontEnd");

        Pocao tonicoRestaurador = new Pocao("💉 Tônico Restaurador", 8, 20, 10);
        tonicoRestaurador.addHeroiPermitido("BackEnd");
        tonicoRestaurador.addHeroiPermitido("FrontEnd");

        // Consumíveis de Combate
        ConsumivelCombate olhoMagico = new ConsumivelCombate("🔮 Olho Mágico", 8, 14);
        olhoMagico.addHeroiPermitido("BackEnd");
        olhoMagico.addHeroiPermitido("FrontEnd");
        olhoMagico.addHeroiPermitido("FullStack");

        ConsumivelCombate bombaFumaca = new ConsumivelCombate("💨 Bomba de Fumaça", 4, 4);
        bombaFumaca.addHeroiPermitido("FullStack");
        bombaFumaca.addHeroiPermitido("BackEnd");

        ConsumivelCombate armaduraTemp = new ConsumivelCombate("🛡️ Armadura Temporária", 10, 20);
        armaduraTemp.addHeroiPermitido("BackEnd");

        ConsumivelCombate granadaLuz = new ConsumivelCombate("✨ Granada de Luz", 6, 9);
        granadaLuz.addHeroiPermitido("FullStack");

        ConsumivelCombate pedraSorte = new ConsumivelCombate("💎 Pedra de Sorte", 7, 12);
        pedraSorte.addHeroiPermitido("BackEnd");

        ConsumivelCombate amuletoDefesa = new ConsumivelCombate("🔮 Amuleto de Defesa", 9, 18);
        amuletoDefesa.addHeroiPermitido("FullStack");

        ConsumivelCombate pergaminhoAtaque = new ConsumivelCombate("📜 Pergaminho de Ataque", 5, 10);
        pergaminhoAtaque.addHeroiPermitido("BackEnd");
        pergaminhoAtaque.addHeroiPermitido("FrontEnd");

        ConsumivelCombate totemRaro = new ConsumivelCombate("🗿 Totem Raro", 8, 16);
        totemRaro.addHeroiPermitido("FrontEnd");
        totemRaro.addHeroiPermitido("FullStack");
        ConsumivelCombate cristalDuro = new ConsumivelCombate("💎 Cristal Duro", 6, 8);
        cristalDuro.addHeroiPermitido("BackEnd");

        ConsumivelCombate runaAtaque = new ConsumivelCombate("🔤 Runa de Ataque", 7, 14);
        runaAtaque.addHeroiPermitido("FullStack");

        // Armas Principais
        ArmaPrincipal chuchu = new ArmaPrincipal("🌂 Chuchu de Batalha", 15, 10, 15);
        chuchu.addHeroiPermitido("BackEnd");
        chuchu.addHeroiPermitido("FrontEnd");
        chuchu.addHeroiPermitido("FullStack");

        ArmaPrincipal laptop = new ArmaPrincipal("💻 Laptop Destruidor", 26, 50, 20);
        laptop.addHeroiPermitido("BackEnd");
        laptop.addHeroiPermitido("FrontEnd");
        laptop.addHeroiPermitido("FullStack");

        ArmaPrincipal teclado = new ArmaPrincipal("⌨️ Teclado Mortifero", 22, 20, 50);
        teclado.addHeroiPermitido("BackEnd");
        teclado.addHeroiPermitido("FrontEnd");
        teclado.addHeroiPermitido("FullStack");

        ArmaPrincipal ChatGPT = new ArmaPrincipal("\uD83E\uDD16\uD83E\uDDE0 ChatGPT֎", 100,100,150);
        ChatGPT.addHeroiPermitido("BackEnd");
        ChatGPT.addHeroiPermitido("FrontEnd");
        ChatGPT.addHeroiPermitido("FullStack");

        // Adicionar todos os itens à loja
        loja.addAll(Arrays.asList(pocaoCura, pocaoCuraMaior, pocaoChoro, pocaoVigor, elixirMistico,
                pocaoRegeneracao, frascoVida, elixirPoder, pocaoVitalidade, essenciaMagica,
                tonicoRestaurador, olhoMagico, bombaFumaca, armaduraTemp, granadaLuz,
                pedraSorte, amuletoDefesa, pergaminhoAtaque, totemRaro,
                cristalDuro, runaAtaque, chuchu, laptop, teclado, ChatGPT));
    }
    /**
     * Implementação para vender os itens do Heroi
     * @param heroi
     * @param
     */
    public void vender(Heroi heroi) {
        iniciarloja();
        Scanner in = new Scanner(System.in);
        Random rnd = new Random();
        String classeHeroi = heroi.getClass().getSimpleName();

        if (loja.isEmpty()) {
            System.out.println("A loja está vazia no momento.");
            return;
        }

        // Cria uma lista temporária com itens aleatórios
        List<ItemHeroi> itensExibidos = new ArrayList<>();
        List<Integer> indicesSorteados = new ArrayList<>();

        while (indicesSorteados.size() < Math.min(10, loja.size())) {
            int escolhaAleatoria = rnd.nextInt(loja.size());

            if (!indicesSorteados.contains(escolhaAleatoria)) {
                indicesSorteados.add(escolhaAleatoria);
                itensExibidos.add(loja.get(escolhaAleatoria));
            }
        }

        while (true) {
            // Pergunta se o jogador deseja comprar algo
            System.out.println("\nDesejas Comprar um Item? (S/N) ⚔️");
            String comprar = in.nextLine().toUpperCase();

            if (comprar.equals("N")) {
                System.out.println("😢 Que pena! Volta quando quiseres comprar algo! 👋");
                break;
            } else if (!comprar.equals("S")) {
                System.out.println("❌ Opção inválida! Por favor, escolhe S ou N.");
                continue; // Volta ao início do loop
            }

            // Exibe os itens da loja
            System.out.println("\n✧･ﾟ: *✧･ﾟ:* 【 LOJA MÁGICA 】 *:･ﾟ✧*:･ﾟ✧");
            System.out.println("💰 Suas moedas: " + heroi.getOuro());
            System.out.println("📝 Escolha um item para comprar (0 para sair):\n");

            // Exibe os itens disponíveis para compra
            for (int i = 0; i < itensExibidos.size(); i++) {
                System.out.printf("%d. ", (i + 1));
                itensExibidos.get(i).exibirDetalhes();
                System.out.println("-----------------------------------------------");
            }

            try {
                System.out.print(classeHeroi + "! Escolha os seus itens! 📝 (0 para sair): ");
                int escolha = in.nextInt();
                in.nextLine(); // Limpa o buffer do scanner

                if (escolha == 0) {
                    System.out.println("\n👋 Volte sempre à nossa loja!");
                    break;
                }

                if (escolha > 0 && escolha <= itensExibidos.size()) {
                    ItemHeroi itemComprado = itensExibidos.get(escolha - 1);

                    // Verifica se o herói pode usar este item
                    if (!itemComprado.podeUsar(classeHeroi)) {
                        System.out.println("\n❌ Este item não pode ser usado por um " + classeHeroi + "!");
                        continue;
                    }

                    // Verifica se o herói comprou o item
                    if (heroi.comprarItem(itemComprado)) {
                        loja.remove(itemComprado);
                        itensExibidos.remove(itemComprado);
                        System.out.println("✅ Item comprado com sucesso!");
                    } else {
                        System.out.println("❌ Não tens 💰 para comprar o item!");
                    }
                } else {
                    System.out.println("❌ Escolha inválida!");
                }
            } catch (InputMismatchException e) {
                System.out.println("\n❌ Entrada inválida. Por favor, digite um número.");
                in.nextLine(); // Limpa o buffer do scanner
            }

            // Se o herói não tiver mais dinheiro, ele sai automaticamente
            if (heroi.getOuro() <= 0) {
                System.out.println("\n💸 Você ficou sem moedas!");
                System.out.println("O Vendedor expulsou-o da Loja!! \uD83D\uDC4B");
                break;
            }
        }
    }
    }