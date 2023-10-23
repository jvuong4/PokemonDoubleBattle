//Jonathan Vuong
import java.util.Scanner;
import java.util.ArrayList;
import java.lang.Math;
import java.util.Collections;
public class PokemonRunner
{
   public static ArrayList<Pokemon> playerRoster;
   public static ArrayList<Pokemon> computerRoster;
   public static boolean willDescMove;
   public static void main(String[] args)
   {
      Scanner scanner = new Scanner(System.in);
      System.out.println("Pokemon Double Battle Simulator\n\n<Press enter to start>");
      scanner.nextLine(); //basically this just waits for a response that it does nothing with. Artificial 'pacing'
      System.out.println("Hello! Please enter your name below.");
      String name = scanner.nextLine(); //Your name isn't really important but I think it's nice to have
      
      playerRoster = new ArrayList<Pokemon>();
      computerRoster = new ArrayList<Pokemon>();
      
      System.out.println("\nHello " + name + "! You will be able to choose 2 of these 5 pokemon: \nBulbasaur, Squirtle, Charmander, Pikachu, and Eevee. \nPlease enter the number beside the Pokemon to select it.\n1 Bulbasaur: A Grass and Poison type specialized in self-sustenance and poison\n2 Squirtle: A Water type that can protect itself and its teammates with its bulky shell\n3 Charmander: A Fire type that can deal tons of damage with its offensive moveset\n4 Pikachu: An Electric Type that can manipulate HP and stats\n5 Eevee: A Normal Type that can help increase damage output");
      String[] choices = {"1","2","3","4","5"};
      String input = validResponse(choices);
      if(input.equals("1"))
      {
         playerRoster.add(new Bulbasaur());
         System.out.println("Bulbasaur was added to your party.");
      }
      else if(input.equals("2"))
      {
         playerRoster.add(new Squirtle());
         System.out.println("Squirtle was added to your party.");
      }
      else if(input.equals("3"))
      {
         playerRoster.add(new Charmander());
         System.out.println("Charmander was added to your party.");
      }
      else if(input.equals("4"))
      {
         playerRoster.add(new Pikachu());
         System.out.println("Pikachu was added to your party.");
      }
      else
      {
         playerRoster.add(new Eevee());
         System.out.println("Eevee was added to your party.");
      }
      System.out.println("\nOk " + name + ", now choose your second Pokemon.\n1 Bulbasaur: A Grass and Poison type specialized in self-sustenance and poison\n2 Squirtle: A Water type that can protect itself and its teammates with its bulky shell\n3 Charmander: A Fire type that can deal tons of damage with its offensive moveset\n4 Pikachu: An Electric Type that can manipulate HP and stats\n5 Eevee: A Normal Type that can help increase damage output");
      input = validResponse(choices);
      if(input.equals("1"))
      {
         playerRoster.add(new Bulbasaur());
         System.out.println("Bulbasaur was added to your party.");
      }
      else if(input.equals("2"))
      {
         playerRoster.add(new Squirtle());
         System.out.println("Squirtle was added to your party.");
      }
      else if(input.equals("3"))
      {
         playerRoster.add(new Charmander());
         System.out.println("Charmander was added to your party.");
      }
      else if(input.equals("4"))
      {
         playerRoster.add(new Pikachu());
         System.out.println("Pikachu was added to your party.");
      }
      else
      {
         playerRoster.add(new Eevee());
         System.out.println("Eevee was added to your party.");
      }
      System.out.println("Press enter to continue.");
      scanner.nextLine();
      //add random pokemon to fight
      System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\nNow we will grab some Pokemon for you to battle...");
      scanner.nextLine();
      for(int i = 0; i < 2; i++)
      {
         int decision = (int) (Math.random() * 5);
         if(decision == 0)
         {
            computerRoster.add(new Bulbasaur());
            System.out.println("Bulbasaur was added to your foe's party.");
         }
         else if(decision == 1)
         {
            computerRoster.add(new Squirtle());
            System.out.println("Squirtle was added to your foe's party.");
         }
         else if(decision == 2)
         {
            computerRoster.add(new Charmander());
            System.out.println("Charmander was added to your foe's party.");
         }
         else if(decision == 3)
         {
            computerRoster.add(new Pikachu());
            System.out.println("Pikachu was added to your foe's party.");
         }
         else if(decision == 4)
         {
            computerRoster.add(new Eevee());
            System.out.println("Eevee was added to your foe's party.");
         }
      }
      scanner.nextLine();
      System.out.println("\n\n\n\n\n\nYou have been challenged to a pokemon fight!\nBefore we begin, would you like to turn on move descriptions? yes / no");
      String[] yesOrNo = {"yes", "no"};
      willDescMove = validResponse(yesOrNo).equals("yes");
      //begin battle
      ArrayList<Integer> turnOrder = new ArrayList<Integer>(); //1 = your first pkmn, 2 = your second, 3 = com first, 4 = com second
      turnOrder.add(0); 
      turnOrder.add(1); 
      turnOrder.add(2); 
      turnOrder.add(3);
      int turnIndex = 0;
      int turn = 1;
      //randomizes the order the the 4 pokemon will go
      Collections.shuffle(turnOrder);
      int cycles = 0;
      while((playerRoster.get(0).hp > 0 || playerRoster.get(1).hp > 0) && (computerRoster.get(0).hp > 0 || computerRoster.get(1).hp > 0))
      {  
         if(turnOrder.get(turnIndex) == 0 && playerRoster.get(0).battling)
         {
            display();
            System.out.println("Ally "+playerRoster.get(0).name+"'s turn!");
            scanner.nextLine();
            playerMove(0,playerRoster.get(0));
         }
         else if(turnOrder.get(turnIndex) == 1 && playerRoster.get(1).battling)
         { 
            display();
            System.out.println("Ally "+playerRoster.get(1).name+"'s turn!");
            scanner.nextLine();
            playerMove(1,playerRoster.get(1));
         }
         else if(turnOrder.get(turnIndex) == 2 && computerRoster.get(0).battling)
         {
            display();
            System.out.println("Enemy "+computerRoster.get(0).name+"'s turn!");
            scanner.nextLine();
            computerMove(computerRoster.get(0));
         }
         else if(turnOrder.get(turnIndex) == 3 && computerRoster.get(1).battling)
         {
            display();
            System.out.println("Enemy "+computerRoster.get(1).name+"'s turn!");
            scanner.nextLine();
            computerMove(computerRoster.get(1));
         }
         turnIndex++;
         if(turnIndex >= turnOrder.size())
         {
            turnIndex = 0;
            cycles++;
            playerRoster.get(0).poisonDamage();
            playerRoster.get(1).poisonDamage();
            computerRoster.get(0).poisonDamage();
            computerRoster.get(1).poisonDamage();
            
            if((cycles >=10 && cycles % 2 == 0) && (playerRoster.get(0).hp > 0 || playerRoster.get(1).hp > 0) && (computerRoster.get(0).hp > 0 || computerRoster.get(1).hp > 0)  )
            {  //after every pokemon has gone 10 times, this occurs every 2nd turn 
               //Attack increased, Defense reduced, Minor amounts of HP are lost
               System.out.println("The battle is getting rough!");
               playerRoster.get(0).fatigue(cycles);
               playerRoster.get(1).fatigue(cycles);
               computerRoster.get(0).fatigue(cycles);
               computerRoster.get(1).fatigue(cycles);
            }
            scanner.nextLine();
         }
      }
      //end of battle
      if(!(playerRoster.get(0).battling || playerRoster.get(1).battling) && !(computerRoster.get(0).battling || computerRoster.get(1).battling))
      {  //a rare situation where all 4 pokemon have fainted
      
      }
      else if(!(computerRoster.get(0).battling || computerRoster.get(1).battling))
      {  //if neither of the opposing pokemon are able to battle !(||)
         System.out.println("Congratulations " + name+"! You won!");
      }
      else
      {  //if at least of of the 4 pokemon are able to battle, and at least one of th efoe's pokemon are able to battle
         System.out.println("Don't worry, " + name+". You'll get them next time!");
      }
   }
   
//determine the move that a pokemon will use
   public static void playerMove(int id, Pokemon poke)
   {
      System.out.println("What will " + poke.name + " do? Enter the number associated with the move.");
      for(int i = 0; i < poke.moveset.size(); i++)
      {
         System.out.print((i+1)+ " " + poke.moveset.get(i).getName() + poke.moveset.get(i).description(willDescMove));
      }
      String[] options = {"1","2","3","4"};
      int action = Integer.parseInt(validResponse(options)) - 1;
      if(!poke.moveset.get(action).aim.equals("Single"))
      {  //basically, if the action you have affects all allies, all foes, or only yourself
         poke.use(action, playerRoster, computerRoster);
         return; //just stop everything happening in this method
      }
      String target = getTarget(id, poke, action);
      if(target.equals("1"))
      {
         poke.attack(computerRoster.get(0),action);
      }
      else if(target.equals("2"))
      {
         poke.attack(computerRoster.get(1),action);
      }
      else if(target.equals("3"))
      {
         poke.attack(playerRoster.get(0),action);
      }
      else if(target.equals("4"))
      {
         poke.attack(playerRoster.get(1),action);
      }
   }


//determine the move that a computer-controlled pokemon will use
   public static void computerMove(Pokemon poke)
   {
      if(poke.name.equals("Bulbasaur"))
      { //if hp is really low -> Synthesis; safety first
         if((double)poke.hp / poke.maxhp <= Math.random() * 0.25 + 0.25)
         { //higher chance at occuring at low health, >50% hp is always no, <= 25% is always yes
            poke.use(1,computerRoster,playerRoster);
         }
         //if enemy is not bulbasaur and not poisoned and fairly high hp -> toxic, lower their health
         else if(playerRoster.get(0).battling && playerRoster.get(0).status.equals("") && !(playerRoster.get(0).name.equals("Bulbasaur")) && ((double)playerRoster.get(0).hp) / playerRoster.get(0).maxhp >= Math.random()* 0.75 + 0.25)
            poke.attack(playerRoster.get(0),0);
         else if(playerRoster.get(1).battling && playerRoster.get(1).status.equals("") && !(playerRoster.get(1).name.equals("Bulbasaur")) && ((double)playerRoster.get(1).hp) / playerRoster.get(1).maxhp >= Math.random()* 0.75 + 0.25)
            poke.attack(playerRoster.get(1),0);
         //if enemy is weak to grass -> giga drain, deal extra damage
         else if(playerRoster.get(0).battling && playerRoster.get(0).contains(playerRoster.get(0).weakness, "Grass"))
            poke.attack(playerRoster.get(0), 3);
         else if(playerRoster.get(1).battling && playerRoster.get(1).contains(playerRoster.get(1).weakness, "Grass"))
            poke.attack(playerRoster.get(1), 3);
         //if hp is high -> Double Edge, 
         else if((double) poke.hp / poke.maxhp > Math.random() * 0.75 + 0.25)
         {  // use double edge on a random enemy at high health
            ArrayList<Integer> targets = new ArrayList<Integer>();
            if(playerRoster.get(0).battling)
               targets.add(0);
            if(playerRoster.get(1).battling)
               targets.add(1);
            int target = (int)(Math.random() * targets.size()); 
            poke.attack(playerRoster.get(targets.get(target)), 2);
         }
         //default - check if target does not resist Grass. If both resist grass, use giga drain on a random enemy
         else if(playerRoster.get(0).battling && playerRoster.get(0).contains(playerRoster.get(0).resistance, "Grass") == false)
            poke.attack(playerRoster.get(0), 3);
         else if(playerRoster.get(1).battling && playerRoster.get(1).contains(playerRoster.get(1).resistance, "Grass") == false)
            poke.attack(playerRoster.get(1), 3);
         else
         {
            ArrayList<Integer> targets = new ArrayList<Integer>();
            if(playerRoster.get(0).battling)
               targets.add(0);
            if(playerRoster.get(1).battling)
               targets.add(1);
            int target = (int)(Math.random() * targets.size()); 
            poke.attack(playerRoster.get(targets.get(target)), 3);
         }
      }
      else if(poke.name.equals("Squirtle"))
      {  //if both Pokemon are alive but at <75% hp
         if(computerRoster.get(0).battling && computerRoster.get(1).battling && (double)computerRoster.get(0).hp / computerRoster.get(0).maxhp < 0.75 && (double)computerRoster.get(1).hp / computerRoster.get(1).maxhp < 0.75)
            poke.use(1,computerRoster,playerRoster);
         //if this pokemon is at <75% hp, chance based. Guaranteed at 25% hp or lower
         else if((double)poke.hp / poke.maxhp <= Math.random() * 0.5 + 0.25)
            poke.use(1,computerRoster,playerRoster);
         //if defense is not at max, there's a 25% chance to use Iron Defense
         else if(Math.random() < 0.25 && (computerRoster.get(0).battling && computerRoster.get(0).defmod < 6) || (computerRoster.get(1).battling && computerRoster.get(1).defmod < 6) )
            poke.use(2,computerRoster,playerRoster);
         //if a target is at <50% hp and does not resist water
         else if(playerRoster.get(0).hp < playerRoster.get(0).maxhp / 2 && !(playerRoster.get(0).contains(playerRoster.get(0).resistance,"Water")))
         {
            poke.attack(playerRoster.get(0), 3);
         }
         else if(playerRoster.get(1).hp < playerRoster.get(1).maxhp / 2 && !(playerRoster.get(1).contains(playerRoster.get(1).resistance,"Water")))
         {
            poke.attack(playerRoster.get(1), 3);
         }
         //use a spread move against 2 targets
         else if(playerRoster.get(0).battling && playerRoster.get(1).battling)
         {
            poke.use(0,computerRoster,playerRoster);
         }
         //if a target is at <50% hp OR does not resist water
         else if(playerRoster.get(0).hp < playerRoster.get(0).maxhp / 2 && !(playerRoster.get(0).contains(playerRoster.get(0).resistance,"Water")))
         {
            poke.attack(playerRoster.get(0), 3);
         }
         else if(playerRoster.get(1).hp < playerRoster.get(1).maxhp / 2 && !(playerRoster.get(1).contains(playerRoster.get(1).resistance,"Water")))
         {
            poke.attack(playerRoster.get(1), 3);
         }
         //worst case scenario: fighting a bulbasaur at high hp... this ideally shouldn't happen since that means this pokemon will probably lose
         else
         {
            poke.use(0,computerRoster,playerRoster);
         }
      
      }
      else if(poke.name.equals("Charmander"))
      {
         //if hp is really low
         if((double)poke.hp / poke.maxhp < Math.random() * 0.0625 + 0.0417) 
         {  // use flail at really low health
            ArrayList<Integer> targets = new ArrayList<Integer>();
            if(playerRoster.get(0).battling)
               targets.add(0);
            if(playerRoster.get(1).battling)
               targets.add(1);
            int target = (int)(Math.random() * targets.size()); 
            poke.attack(playerRoster.get(targets.get(target)), 1);
         }
         //if hp is really high
         else if((double)poke.hp / poke.maxhp > Math.random() * 0.21 + 0.8 && poke.atkmod < 6)
         {  //if hp is high, there's a chance to spend 33% of it to increase attack. Does not occur at atkMod == 6
            poke.use(2, computerRoster, playerRoster);
         }
         //if hp is decently high and a target is weak to fire
         else if((double)poke.hp / poke.maxhp > Math.random() * 0.5 + 0.5 && playerRoster.get(0).battling && playerRoster.get(0).contains(playerRoster.get(0).weakness, "Fire"))
            poke.attack(playerRoster.get(0), 3);
         else if((double)poke.hp / poke.maxhp > Math.random() * 0.5 + 0.5 && playerRoster.get(1).battling && playerRoster.get(1).contains(playerRoster.get(1).weakness, "Fire"))
            poke.attack(playerRoster.get(1), 3);
         //if hp is decently high and a target does not resist fire
         else if((double)poke.hp / poke.maxhp > Math.random() * 0.5 + 0.5 && playerRoster.get(0).battling && playerRoster.get(0).contains(playerRoster.get(0).resistance, "Fire") == false)
            poke.attack(playerRoster.get(0), 3);
         else if((double)poke.hp / poke.maxhp > Math.random() * 0.5 + 0.5 && playerRoster.get(1).battling && playerRoster.get(1).contains(playerRoster.get(1).resistance, "Fire") == false)
            poke.attack(playerRoster.get(1), 3);
         //target does not resist Flying
         else if(playerRoster.get(0).battling && playerRoster.get(0).contains(playerRoster.get(0).resistance, "Flying") == false)
            poke.attack(playerRoster.get(0), 0);
         else if(playerRoster.get(1).battling && playerRoster.get(1).contains(playerRoster.get(1).resistance, "Flying") == false)
            poke.attack(playerRoster.get(1), 0);
         //Default, Flare Blitz
         else
         {
            ArrayList<Integer> targets = new ArrayList<Integer>();
            if(playerRoster.get(0).battling)
               targets.add(0);
            if(playerRoster.get(1).battling)
               targets.add(1);
            int target = (int)(Math.random() * targets.size()); 
            poke.attack(playerRoster.get(targets.get(target)), 3);
         }
      }
      else if(poke.name.equals("Pikachu"))
      {
         //Endeavor: If the enemy's hp is higher than the user, set the enemy's hp to the user's hp
         //targets the enemy with higher hp. Damage dealt should be greater than 50. The target should be at 50%hp or more. More likely to occur at higher hp percentages.
         if(playerRoster.get(0).hp >= playerRoster.get(1).hp && playerRoster.get(0).hp - poke.hp > 50 && (double)playerRoster.get(0).hp / playerRoster.get(0).maxhp >= Math.random() * 0.5 + 0.5)
            poke.attack(playerRoster.get(0), 1);
         else if(playerRoster.get(1).hp > playerRoster.get(0).hp && playerRoster.get(1).hp - poke.hp > 50 && (double)playerRoster.get(1).hp / playerRoster.get(1).maxhp >= Math.random() * 0.5 + 0.5)
            poke.attack(playerRoster.get(1), 1);
         //Tickle: reduce an enemy's defense and attack by 1 stage. both atk and def must be greater than a number from -5 to 2 (not including 2). The number used for eaach are not necessarily the same
         else if(playerRoster.get(0).battling && playerRoster.get(0).atkmod > Math.random() * 7 - 5 && playerRoster.get(0).defmod > Math.random() * 7 - 5)
            poke.attack(playerRoster.get(0), 2);
         else if(playerRoster.get(1).battling && playerRoster.get(1).atkmod > Math.random() * 7 - 5 && playerRoster.get(1).defmod > Math.random() * 7 - 5)
            poke.attack(playerRoster.get(1), 2);
         //choose someone weak to electric to use either Charge beam or thunderbolt
         else if(playerRoster.get(0).battling && playerRoster.get(0).contains(playerRoster.get(0).weakness, "Electric"))
         {
            //User's attack is low = chance to use charge beam (70% chance to increase attack by 1 stage, weaker than thunderbolt)
            if(poke.atkmod <= (int)Math.random() * 5 + 1) //atkmod < [1,5]
               poke.attack(playerRoster.get(0), 0);
            //Thunderbolt
            else
               poke.attack(playerRoster.get(0), 3);
         }
         else if(playerRoster.get(1).battling && playerRoster.get(1).contains(playerRoster.get(1).weakness, "Electric"))
         {
            if(poke.atkmod <= (int)Math.random() * 5 + 1) //atkmod < [1,5]
               poke.attack(playerRoster.get(1), 0);
            else
               poke.attack(playerRoster.get(1), 3);
         }
         //since there is no foes weak to electric, just find one that does not resist electric
         else if(playerRoster.get(0).battling && !playerRoster.get(0).contains(playerRoster.get(0).resistance, "Electric"))
         {
            if(poke.atkmod <= (int)Math.random() * 5 + 1) //atkmod < [1,5]
               poke.attack(playerRoster.get(0), 0);
            else
               poke.attack(playerRoster.get(0), 3);
         }
         else if(playerRoster.get(1).battling && !playerRoster.get(1).contains(playerRoster.get(1).resistance, "Electric"))
         {
            if(poke.atkmod <= (int)Math.random() * 5 + 1) //atkmod < [1,5]
               poke.attack(playerRoster.get(1), 0);
            else
               poke.attack(playerRoster.get(1), 3);
         }
         //last resort: an uneffective attack
         else
         {
            ArrayList<Integer> targets = new ArrayList<Integer>();
            if(playerRoster.get(0).battling)
               targets.add(0);
            if(playerRoster.get(1).battling)
               targets.add(1);
            int target = (int)(Math.random() * targets.size()); 
            if(poke.atkmod <= (int)Math.random() * 5 + 1) //atkmod < [1,5]
               poke.attack(playerRoster.get(targets.get(target)), 0);
            else
               poke.attack(playerRoster.get(targets.get(target)), 3);
         }
      }
      else if(poke.name.equals("Eevee"))
      {
         //if both foes have high defense and are battling : Tail whip to lower both their defenses by 1 stage
         if(playerRoster.get(0).battling && playerRoster.get(0).defmod > Math.random() * 6 - 5 && playerRoster.get(1).battling && playerRoster.get(1).defmod > Math.random() * 6 - 5)
            poke.use(0,computerRoster,playerRoster);
         //75% chance to increase the attack of the concious ally with the highest base attack with an atkmod <= 4
         else if(Math.random() < 0.75 && (computerRoster.get(0).baseatk > computerRoster.get(1).baseatk && computerRoster.get(0).battling && computerRoster.get(0).atkmod <= 4)||(!computerRoster.get(1).battling && computerRoster.get(0).atkmod <= 4))
            poke.attack(computerRoster.get(0), 1);
         else if(Math.random() < 0.75 && (computerRoster.get(1).baseatk > computerRoster.get(0).baseatk && computerRoster.get(1).battling && computerRoster.get(1).atkmod <= 4)||(!computerRoster.get(0).battling && computerRoster.get(1).atkmod <= 4))
            poke.attack(computerRoster.get(1), 1);
         //Both enemies are alive: guaranteed swift
         else if(playerRoster.get(0).battling && playerRoster.get(1).battling)
            poke.use(3,computerRoster,playerRoster);
         //user at high hp: double edge on the remaining target
         else if((double)poke.hp / poke.maxhp > Math.random() * 0.5 + 0.5 && playerRoster.get(0).battling)
            poke.attack(playerRoster.get(0), 2);
         else if((double)poke.hp / poke.maxhp > Math.random() * 0.5 + 0.5 && playerRoster.get(1).battling)
            poke.attack(playerRoster.get(1), 2);
         //last resort: swift on a single target
         else
            poke.use(3,computerRoster,playerRoster);
      }
   }

//display the hp and names of all of the pokemon.
   public static void display()
   {
      System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
      System.out.println("\t\t\t\t\t\t\t\t\t\t" + computerRoster.get(0).name + ": " + computerRoster.get(0).hp + "/"+computerRoster.get(0).maxhp+" hp");
      System.out.println("\t\t\t\t\t\t\t\t\t\t" + computerRoster.get(1).name + ": " + computerRoster.get(1).hp + "/"+computerRoster.get(1).maxhp+" hp");
      System.out.println("\t" + playerRoster.get(0).name + ": " + playerRoster.get(0).hp + "/"+playerRoster.get(0).maxhp+" hp");
      System.out.println("\t" + playerRoster.get(1).name + ": " + playerRoster.get(1).hp + "/"+playerRoster.get(1).maxhp+" hp");
      System.out.println();
   }
   
//help the user select a target.
   public static String getTarget(int id, Pokemon poke, int action)
   {
      System.out.println("Which pokemon will "+ poke.name + " use "+poke.moveset.get(action).getName()+ " on?");
      ArrayList<String> targets = new ArrayList<String>();
      if(computerRoster.get(0).battling && !poke.moveset.get(action).getName().equals("Helping Hand"))
      {
         System.out.println("1 : enemy " + computerRoster.get(0).name);
         targets.add("1");
      }
      if(computerRoster.get(1).battling && !poke.moveset.get(action).getName().equals("Helping Hand"))
      {
         System.out.println("2 : enemy " + computerRoster.get(1).name);
         targets.add("2");
      }
      if(playerRoster.get(0).battling && poke.moveset.get(action).getName().equals("Helping Hand"))
      {
         System.out.println("3 : ally " + playerRoster.get(0).name);
         targets.add("3");
      }
      if(playerRoster.get(1).battling && poke.moveset.get(action).getName().equals("Helping Hand"))
      {
         System.out.println("4 : ally " + playerRoster.get(1).name);
         targets.add("4");
      }
      //'convert' ArrayList<String> to String[]
      String[] options = new String[targets.size()];
      for(int i = 0; i < targets.size(); i++)
      {
         options[i] = targets.get(i);
      }
      //I probably could've just made a second validResponse method to accept ArrayList<String> but I was feeling kinda lazy, not gonna lie.
      return validResponse(options);
   }

//Basically scanner.nextLine but improved! :) super proud of myself for this
//an "infinite loop" that will ask for a response "forever" until you enter something valid
//super good for people that are new to this program or are typo prone. I make typos a lot!
   public static String validResponse(String[] options)
   {
      Scanner scanner = new Scanner(System.in);
      String response = "";
      while(true) // infinite loop that only 'ends' with return
      {
         response = scanner.nextLine();
         for(String option : options)
         {
            if(response.equals(option))
            {
               return response;
            }
         }
         System.out.print("Please enter a valid response: ");
         for(String option : options)
         {
            System.out.print(option + " ");
         }
         System.out.println();
      }
   }
}