# Sudoku (Java CLI)

Projeto simples de **Sudoku** em **Java**, rodando no **terminal**.
Usei este projeto para treinar **POO**: separar responsabilidades, validar regras e trabalhar com objetos.

---

## Como jogar

- O jogo mostra o tabuleiro no terminal.
- Digite: `linha coluna valor` (1 a 9).
- Use `0` para **limpar** uma célula.
- Digite `q` para **sair**.
- Células **fixas** não podem ser alteradas.

Exemplo:
```
1 3 9
```

---

## Como rodar

**Pré-requisitos**
- Java 17+ (testado com 21)
- Maven

**IntelliJ IDEA**
1. Abra o projeto.
2. Run → **Edit Configurations…**
3. (Opcional) Em **Program arguments**, cole a lista de argumentos do tabuleiro (veja abaixo).
4. **Run**.

**Terminal**
```bash
mvn -q -DskipTests package
java -cp target/classes br.com.reginaldo.sudoku.Game
```

> Se **não** passar argumentos, um **puzzle padrão** é carregado automaticamente.

---

## Carregar um tabuleiro por argumentos (opcional)

O jogo aceita argumentos no formato **`r,c;v,fixed`** (0-based), separados por espaço:

- `r,c` → linha e coluna (0–8)
- `v` → valor (0–9; 0 = vazio)
- `fixed` → `true` se a célula for travada

Exemplo curto:
```
0,0;4,true 0,1;0,false 1,0;7,false
```

Você também pode colar a **lista completa** fornecida na descrição do projeto (um por espaço).

---

## Estrutura do projeto

```
src/main/java/br/com/reginaldo/sudoku
├─ Game                 -> ponto de entrada (CLI)
├─ domain/
│  ├─ Board             -> estado do tabuleiro + regras básicas (linha/coluna/bloco)
│  └─ Coordinates       -> objeto para (linha, coluna) com validação
└─ service/
   └─ GameService       -> regras de jogada (set/clear), validações e impressão
```

---

## O que trabalhei de POO (modo iniciante)

- **Classes e Objetos**  
  Separei em `Board`, `Coordinates`, `GameService` e `Game`. Cada classe com um papel claro.

- **Encapsulamento**  
  Não mexo direto nas matrizes. Uso métodos (`set`, `clear`, `isValidMove`) para controlar mudanças.

- **Composição**  
  `GameService` **tem** um `Board`. Juntei objetos ao invés de herdar.

- **Imutabilidade básica**  
  `Coordinates` tem campos `final` e valida o range no construtor. Depois de criado, não muda.

- **Responsabilidade Única (SRP)**
    - `Board`: guarda o estado e valida regras do Sudoku.
    - `GameService`: aplica jogadas e imprime o tabuleiro.
    - `Game`: conversa com o usuário (entrada/saída no terminal).

> **Herança e Polimorfismo**: não forcei aqui. Para este problema, **composição** já resolveu bem.

---

## Ideias de próximos passos

- **Undo/redo** das jogadas.
- **Dica** de jogada possível.
- Ler tabuleiros de um arquivo `.txt`.
- Interface gráfica (JavaFX ou Swing).

---

## Contato

- GitHub: https://github.com/reginaldojr-dev
- LinkedIn: https://www.linkedin.com/in/reginaldo-junior-175148188/

---

> Projeto feito para estudo de POO. Sugestões são bem-vindas! 😊
