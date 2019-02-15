# Passeio do Cavalo (Java)

Trabalho de Complexidade de Algoritmos para otimizar o algoritmo de força bruta para resolver o Knight's Tour Problem

## Abordagem

Para otimização do problema, foi adicionado um método de validação **semSaida** aonde ele valida se o caminho possui continuações válidas antes de chamar a recursividade.\n
Outra otimização utilizada foi a de deixar uma margem externa com o valor -1 e os campos válidos com 0. Com isso, ao invez de realizar validação dos 8 possíveis movimentos, somente é realizado a validação se o valor é 0, caso não for, o movimento não é válido.
