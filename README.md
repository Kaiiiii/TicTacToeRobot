# Exercise 1.5 from Machine Learning by Tom M. Mitchell 
* Language used: Java
* Main class: src/Game.java

## Unresolved Issues 
1. After the first game, the weights are adjusted to give a win condition, but subsequent weight adjustments consistently produce a draw condition
2. Ideal move at each step is always symmetrical (i.e. a positive feature has the same value as another negative feature) 
    * causes problems when same value is assigned to all weights for DumbPlayer, as error at each step will always be 0 
    * perhaps can consider other features for target expressions  

## References 
1. Machine Learning, Tom M. Mitchell 
2. Teaching a Computer to Play TicTacToe, Christopher J. MacLellan (http://www.christopia.net/media/redactor/TicTacToe.py)
