import java.util.List;

import Abstrato.Banheiro;
import Abstrato.Comodo;
import Abstrato.Cozinha;
import Abstrato.Garagem;
import Abstrato.Item;
import Abstrato.Lavanderia;

import java.util.ArrayList;
import java.util.Scanner;
import java.io.FileWriter;
import java.io.IOException;
import org.json.JSONArray;
import org.json.JSONObject;

public class Sistema {
    private List<Comodo> comodos;

    public Sistema() {
        comodos = new ArrayList<>();
        inicializarComodos();
    }

    private void inicializarComodos() {
        comodos.add(new Cozinha());
        comodos.add(new Lavanderia());
        comodos.add(new Garagem());
        comodos.add(new Banheiro());
    }

    public void menu() {
        int opcao = 0;
        do {
            System.out.println("\nMenu:");
            System.out.println("1. Adicionar Item");
            System.out.println("2. Remover Item");
            System.out.println("3. Listar Itens");
            System.out.println("4. Gerar Relatórios");
            System.out.println("5. Exportar Dados");
            System.out.println("6. Sair");
            opcao = Console.lerInt("Escolha uma opção:");

            switch (opcao) {
                case 1:
                    adicionarItem();
                    break;
                case 2:
                    removerItem();
                    break;
                case 3:
                    listarItens();
                    break;
                case 4:
                    gerarRelatorios();
                    break;
                case 5:
                    exportarDados();
                    break;
                case 6:
                    System.out.println("Saindo...");
                    break;
                default:
                    System.out.println("Opção inválida.");
            }
        } while (opcao != 6);
    }

    private void adicionarItem() {
        System.out.println("\nAdicionar Item:");
        Comodo comodo = selecionarComodo();
        String nome = Console.lerString("Nome do item:");
        String dataValidade = Console.lerString("Data de validade (dd/MM/yyyy):");
        int quantidade = Console.lerInt("Quantidade:");

        Item item = new Item(nome, dataValidade, quantidade);
        comodo.adicionarItem(item);
        System.out.println("Item adicionado com sucesso!");
    }

    private void removerItem() {
        System.out.println("\nRemover Item:");
        Comodo comodo = selecionarComodo();
        String nome = Console.lerString("Nome do item a remover:");

        Item itemRemover = null;
        for (Item item : comodo.getItens()) {
            if (item.getNome().equalsIgnoreCase(nome)) {
                itemRemover = item;
                break;
            }
        }

        if (itemRemover != null) {
            comodo.removerItem(itemRemover);
            System.out.println("Item removido com sucesso!");
        } else {
            System.out.println("Item não encontrado.");
        }
    }

    private void listarItens() {
        System.out.println("\nListar Itens:");
        for (Comodo comodo : comodos) {
            System.out.println(comodo);
        }
    }

    private void gerarRelatorios() {
        System.out.println("\nGerar Relatórios:");
        System.out.println("1. Relatório por Categoria");
        System.out.println("2. Itens Próximos da Validade");
        System.out.println("3. Itens Abaixo do Estoque Mínimo");

        int opcao = Console.lerInt("Escolha uma opção:");

        switch (opcao) {
            case 1:
                relatorioPorCategoria();
                break;
            case 2:
                itensProximosValidade();
                break;
            case 3:
                itensAbaixoEstoqueMinimo();
                break;
            default:
                System.out.println("Opção inválida.");
        }
    }

    private Comodo selecionarComodo() {
        System.out.println("Selecione um cômodo:");
        for (int i = 0; i < comodos.size(); i++) {
            System.out.println((i + 1) + ". " + comodos.get(i).getNome());
        }
        int escolha = Console.lerInt("Escolha uma opção:");

        return comodos.get(escolha - 1);
    }

    private void relatorioPorCategoria() {
        System.out.println("\nRelatório por Categoria:");
        for (Comodo comodo : comodos) {
            System.out.println(comodo.getNome() + ":");
            for (Item item : comodo.getItens()) {
                System.out.println(item);
            }
        }
    }

    private void itensProximosValidade() {
        System.out.println("\nItens Próximos da Validade:");
        for (Comodo comodo : comodos) {
            for (Item item : comodo.getItens()) {
                System.out.println(item);
            }
        }
    }

    private void itensAbaixoEstoqueMinimo() {
        System.out.println("\nItens Abaixo do Estoque Mínimo:");
        for (Comodo comodo : comodos) {
            for (Item item : comodo.getItens()) {
                if (item.getQuantidade() < 1) {
                    System.out.println(item);
                }
            }
        }
    }

    private void exportarDados() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Escolha o formato de exportação:");
        System.out.println("1. TXT");
        System.out.println("2. JSON");
        int opcao = scanner.nextInt();
        scanner.nextLine();

        switch (opcao) {
            case 1:
                exportarParaTxt();
                break;
            case 2:
                exportarParaJson();
                break;
            default:
                System.out.println("Opção inválida!");
        }
    }

    private void exportarParaTxt() {
        try (FileWriter writer = new FileWriter("dados.txt")) {
            for (Comodo comodo : comodos) {
                writer.write(comodo.getNome() + ":\n");
                for (Item item : comodo.getItens()) {
                    writer.write("  " + item.toString() + "\n");
                }
            }
            System.out.println("Dados exportados para dados.txt com sucesso!");
        } catch (IOException e) {
            System.out.println("Erro ao exportar dados para TXT: " + e.getMessage());
        }
    }

    private void exportarParaJson() {
        JSONArray jsonArray = new JSONArray();
        for (Comodo comodo : comodos) {
            JSONObject comodoJson = new JSONObject();
            comodoJson.put("nome", comodo.getNome());
            JSONArray itensArray = new JSONArray();
            for (Item item : comodo.getItens()) {
                JSONObject itemJson = new JSONObject();
                itemJson.put("nome", item.getNome());
                itemJson.put("dataValidade", item.getDataValidade());
                itemJson.put("quantidade", item.getQuantidade());
                itensArray.put(itemJson);
            }
            comodoJson.put("itens", itensArray);
            jsonArray.put(comodoJson);
        }

        try (FileWriter writer = new FileWriter("dados.json")) {
            writer.write(jsonArray.toString(4));
            System.out.println("Dados exportados para dados.json com sucesso!");
        } catch (IOException e) {
            System.out.println("Erro ao exportar dados para JSON: " + e.getMessage());
        }
    }
}