# Projeto de Sistema de Gestão de Itens

## Descrição do Projeto

Este projeto é um sistema de gerenciamento de itens para diferentes cômodos de uma casa. Ele permite adicionar, remover, listar itens, gerar relatórios e exportar dados em formatos TXT e JSON.

## Estrutura do Projeto

O projeto é composto pelos seguintes arquivos Java:

- `Sistema.java`: Classe principal que gerencia o sistema.
- `Console.java`: Classe auxiliar para leitura de dados do console.
- `Main.java`: Classe que contém o método principal (main).
- `Item.java`: Classe que representa um item.
- `Comodo.java`: Classe abstrata que representa um cômodo.
- `Banheiro.java`: Classe que representa um banheiro (extende Comodo).
- `Garagem.java`: Classe que representa uma garagem (extende Comodo).
- `Lavanderia.java`: Classe que representa uma lavanderia (extende Comodo).
- `Cozinha.java`: Classe que representa uma cozinha (extende Comodo).
- `json-20230227.jar`: Biblioteca JSON necessária para manipulação de dados JSON.

## Configuração do Ambiente

Para configurar o ambiente de desenvolvimento, siga os passos abaixo:

1. Baixar e adicionar a biblioteca JSON ao seu projeto:
   - Baixe o arquivo `json.jar` do repositório Maven ou use o arquivo `json-20230227.jar` fornecido neste projeto.
2. Adicionar o arquivo JAR ao seu projeto:
   - **Eclipse:**
     - Clique com o botão direito no seu projeto e vá em `Properties`.
     - Selecione `Java Build Path`.
     - Vá até a aba `Libraries` e clique em `Add External JARs`.
     - Selecione o arquivo `json-20230227.jar`.
     - Clique em `Apply and Close`.
   - **IntelliJ IDEA:**
     - Clique com o botão direito no seu projeto e vá em `Open Module Settings`.
     - Vá até a aba `Libraries` e clique no símbolo de `+` para adicionar uma nova biblioteca.
     - Selecione o arquivo `json-20230227.jar`.
     - Clique em `OK`.

## Como Executar o Projeto

Para executar o projeto:

1. Importe todos os arquivos Java e o arquivo JAR para o seu ambiente de desenvolvimento.
2. Certifique-se de que todas as classes estão no pacote correto.
3. Execute a classe `Main.java` para iniciar o sistema.

## Uso do Sistema

O sistema apresenta um menu com as seguintes opções:

1. **Adicionar Item**: Adiciona um novo item a um cômodo selecionado.
2. **Remover Item**: Remove um item existente de um cômodo selecionado.
3. **Listar Itens**: Lista todos os itens em todos os cômodos.
4. **Gerar Relatórios**: Opção para gerar relatórios.
5. **Exportar Dados**: Opção para exportar dados.
6. **Sair**: Encerra a execução do sistema.
