# PokemonDoubleBattle
A Java program for a turn-based battle game.

To start the game, run the PokemonRunner.java file. 
The game should then ask for a name (which is functionally unimportant), then ask the player to choose 2 out of 5 available Pokemon (duplicates are allowed)!
On a specific Pokemon's turn, the player may choose an action for it to use, if the Pokemon is theirs. Otherwise, the 'computer' will select an action for their Pokemon.
The game will continue until either both of the player's Pokemon have been defeated or both of the computer's Pokemon have been defeated.

After all standing Pokemon have acted ten times, the game will damage all remaining Pokemon on both sides as 'fatigue' damage in order to avoid a game lasting forever.
'Fatigue' will take effect every 2 times all standing Pokemon have acted, and will deal more damage each time. Additionally, it will reduce the remaining Pokemon's defense stats and increase their attack stats in order to increase damage output and help the game end sooner.

This game does not function exactly as a conventional "Pokémon" game; many features are modified, ignored from the original, or added (such as the 'Fatigue' mechanic).
