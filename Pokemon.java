import java.util.Scanner;
import java.util.ArrayList;
import java.lang.Math;
public class Pokemon
{
   public static Scanner scanner = new Scanner(System.in);
   public ArrayList<Move> moveset = new ArrayList<Move>(); 
   //moveset definitely should have been a String[] but I have already done too much with it as an ArrayList<String>
   public int maxhp;// the maximum amount of hp a pokemon may have.
   public int hp; //the current amount of health points a pokemon has. When it reaches 0, it faints.
   public int baseatk;//amount of damage a pokemon will deal when they attack (Think of a sharp sword vs a dull one)
   public int atkmod; //number from -6 to +6 that is combined with baseatk to find the net attack
   public int basedef;//amount of damage a pokemon will reduce when attacked (Think of a iron shield vs a wooden one)
   public int defmod; //number from -6 to +6 that is combined with basedef to find the net defense
   public String status;//technically could be a boolean since there is only one status in this program: poison.
   //If I wanted to add more status effects, like sleep, String wuld be better
   public String name;//the name of the pokemon
   public String type;//the type of the pokemon
   public ArrayList<String> weakness = new ArrayList<String>();
   //a list of weaknesses a pokemon has. For example, A fire type takes only half damage from grass attacks
   public ArrayList<String> resistance = new ArrayList<String>();
   //a list of resistances a pokemon has. For example, A fire type takes double the damage from water attacks
   public boolean battling; //is the pokemon able to fight?
   //When battling is false, this pokemon cannot move. They also cannot be targeted by attacks.
   public Pokemon()
   {  
      maxhp = 10;
      hp = maxhp;
      battling = true;
      atkmod = 0;
      defmod = 0;
      status = "";
   }
   public void alterAtk(int amount)
   {
      if(battling)
      {
         atkmod += amount;
         //this is just text to be displayed
         if(atkmod < -6)
         {
            System.out.println(name + "'s Attack has capped at -6 Stages!");
            atkmod = -6;
         }
         else if(atkmod > 6)
         {
            System.out.println(name + "'s Attack has capped at +6 Stages!");
            atkmod = 6;
         }
         else if(amount < 0)
         {
            System.out.println(name + "'s Attack Fell by "+ amount * -1 +" Stage(s)!");
         }
         else
         {
            System.out.println(name + "'s Attack Rose by "+ amount +" Stage(s)!");
         }
         
      }
   }
   public void alterDef(int amount)
   {
      if(battling)
      {
         defmod += amount;
         if(defmod < -6)
         {
            System.out.println(name + "'s Defense has capped at -6 Stages!");
            defmod = -6;
         }
         else if(defmod > 6)
         {
            System.out.println(name + "'s Defense has capped at +6 Stages!");
            defmod = 6;
         }
         else if(amount < 0)
         {
            System.out.println(name + "'s Defense Fell by "+ amount * -1 +" Stage(s)!");
         }
         else
         {
            System.out.println(name + "'s Defense Rose by "+ amount +" Stage(s)!");
         }
         
      }
   }
   public int getAtk()
   {  //basially, if attack is at +6 stages, (2+6)/2 = 8/2 = 4x the normal amount of attack
      //if attack is at -3 stages, 2/(2 - (-3)) = 2/5 = 40% the original amount of attack
      double mult = 1.0;
      if(atkmod < 0)
         mult= 2.0 / (2.0 - atkmod);
      else
         mult = (2.0 + atkmod) / 2.0;
      return (int)(baseatk * mult);
   }
   public int getDef()
   {
      double mult = 1.0;
      if(defmod < 0)
         mult = 2.0 / (2 - defmod);
      else
         mult = (2 + defmod) / 2;
      return (int)(basedef * mult);
   }
   public void use(int option, ArrayList<Pokemon> userteam, ArrayList<Pokemon> foeteam)
   { //will be overwritten in subclasses...
   }
   public void attack(Pokemon target,int option)
   {
      System.out.println(name +" used " + moveset.get(option).getName() + " on " + target.name + "!");
      scanner.nextLine();
      int damage = moveset.get(option).damage(this, target);
      
      if(damage > 0)
         System.out.println(name +" dealt " + damage + " damage to " + target.name + "!");
      else if(damage == 0)
         System.out.println("No damage was dealt to " + target.name + ".");
      else
         System.out.println(target.name + " recovered "+ damage * -1 + " hp!");
      
      int netDamage = damage;
      if(netDamage > target.hp)
      {
         netDamage = target.hp;
      }
      
      target.takeDamage( damage );
      if(moveset.get(option).getName().equals("Double Edge") || moveset.get(option).getName().equals("Flare Blitz"))
      {  //Double Edge/Flare Blitz harm the user based on 25% of damage dealt
         System.out.println(name +" took " + netDamage/4 + " recoil damage from using "+ moveset.get(option).getName() +"!");
         takeDamage( netDamage / 4);
         scanner.nextLine();
      }
      else if(moveset.get(option).getName().equals("Giga Drain"))
      {  //Giga Drain heals the user by 50% of damage dealt
         System.out.println(target.name +"'s health was drained with Giga Drain! " + name+" recovered " + netDamage / 2 + " hp!");
         takeDamage( netDamage / -2);
         scanner.nextLine();
      }
      else if(moveset.get(option).getName().equals("Charge Beam") && 0.7 > Math.random())
      {  //Charge Beam has a 70% chance to increase attack by 1 stage
         alterAtk(1);
      }
   }
   public void takeDamage(int hurt)
   {
      hp -= hurt;
      if(hp <= 0)
      {
         hp = 0;
         battling = false;
         System.out.println(name +" is now unable to battle! They fainted!");
      }
      if(hp > maxhp)
      {
         hp = maxhp;
      }
   }
   public void poisonDamage()
   {
      if(status.equals("Poison") && battling)
      {
         int damage = maxhp / 16;
         System.out.println(name+" took "+ damage +" damage from poison!");
         takeDamage(damage);
      }
   }
   public void fatigue(int potency)
   {
      if(battling)
      {  //damage = 10 cycles = (1/16)(5-4) = 1/16 of max hp. 12 cycles = (1/16)(6-4)= 1/8, 14 = 1/4, 16 =   
         alterAtk(1);
         alterDef(-1);
         int damage = (maxhp / 16) * (potency / 2 - 4);
         System.out.println(name+" was so exhausted that it took "+ damage +" damage!");
         takeDamage(damage);
      }
   }
   public boolean contains(ArrayList<String> reactions, String type)
   { //checks if the pokemon is weak to a specific type. For example, is Bulbasaur weak to Fire?
     //Bulbasaur's weakness arraylist will be checked for "Fire" and then once it's found it will return true.
      for(String reaction : reactions)
      {
         if(reaction.equals(type))
         {
            return true;
         }
      }
      return false;
   }
   public static String validResponse(String[] options)
   {
      //the exact method that is found in PokemonRunner. I decided it would be better to just have a second one here.
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

class Bulbasaur extends Pokemon
{
   double potency = 0.5;
   public Bulbasaur()
   {
      super();
      moveset.add(new Move(0,"Toxic","Poison")); //index 0
      moveset.add(new selfMove(0,"Synthesis","Grass")); //index 1
      moveset.add(new Move(120,"Double Edge","Normal")); //index 2
      moveset.add(new Move(75,"Giga Drain","Grass"));
      baseatk = 114;
      basedef = 114;
      maxhp = 450;
      hp = maxhp;
      weakness.add("Fire");
      weakness.add("Flying");
      resistance.add("Water");
      resistance.add("Grass");
      resistance.add("Electric");
      name = "Bulbasaur";
      type = "Grass Poison";
   }
   public void use(int option, ArrayList<Pokemon> userteam, ArrayList<Pokemon> foeteam)
   {  
      if(moveset.get(option).moveName.equals("Synthesis"))
      {  //incredibly potent healing (50% max hp). Can only be used on self. Weakens by 5% of max hp each use.
         System.out.println(name +" used Synthesis!");
         takeDamage((int)(maxhp * potency * -1));
         System.out.println(name + " recovered "+ (int)(maxhp * potency) + " hp!");
         if(potency > 0.1)
         {
            potency -= 0.05;
         }
      }
   }
   public void poisonDamage()
   { //just a precaution... Bulbasaur should not be able to take poison damage
   }
}

class Squirtle extends Pokemon
{
   public Squirtle()
   {
      super();
      moveset.add(new spreadMove(30,"Mud Slap","Ground")); //index 0
      moveset.add(new teamMove(0,"Life Dew","Water")); //index 1
      moveset.add(new teamMove(0,"Iron Defense","Steel")); //index 2
      moveset.add(new Move(65,"Brine","Water"));
      weakness.add("Grass");
      weakness.add("Electric");
      resistance.add("Water");
      resistance.add("Fire");
      name = "Squirtle";
      type = "Water";
      baseatk = 114;
      basedef = 114;
      maxhp = 440;
      hp = maxhp;
   }
   public void use(int option, ArrayList<Pokemon> userteam, ArrayList<Pokemon> foeteam)
   {
      if(moveset.get(option).aim.equals("Allies"))
      { //Life Dew or Iron Defense
         if(moveset.get(option).moveName.equals("Life Dew"))
         {  //the most consistent healing; heals the whole team by 25% of their max hp. Does not become less potent as time goes on.
            System.out.println(name + " used Life Dew to recover the party's health!");
            if(userteam.get(0).battling)
            {
               userteam.get(0).takeDamage(userteam.get(0).maxhp / -4);
               System.out.println(userteam.get(0).name + " recovered " + userteam.get(0).maxhp / 4 + " hp!");
            }
            if(userteam.get(1).battling)
            {
               userteam.get(1).takeDamage(userteam.get(1).maxhp / -4);
               System.out.println(userteam.get(1).name + " recovered " + userteam.get(1).maxhp / 4 + " hp!");
            }
         }
         else if(moveset.get(option).moveName.equals("Iron Defense"))
         {
            System.out.println(name + " used Iron Defense!");
            userteam.get(0).alterDef(1);
            userteam.get(1).alterDef(1);
         }
      }
      else
      {  //
         int numTargets = 0;
         for(Pokemon foe : foeteam)
         {
            if(foe.battling)
               numTargets++;
         }
         if(numTargets == 2)
         {
            System.out.println(name + " used " + moveset.get(option).getName() + " on the opposing Pokemon!");
            int dam = (int)(0.75 * moveset.get(option).damage(this, foeteam.get(0)));
            foeteam.get(0).takeDamage(dam);
            System.out.println(foeteam.get(0).name + " took "+ dam +" damage!");
            dam = (int)(0.75 * moveset.get(option).damage(this, foeteam.get(1)));
            foeteam.get(1).takeDamage(dam);
            System.out.println(foeteam.get(1).name + " took "+ dam +" damage!");
         }
         //otherwise it is treated as normal
         else if (foeteam.get(0).battling)
         {
            attack(foeteam.get(0), option);
         }
         else
         {
            attack(foeteam.get(1), option);
         }
      }
   }
}

class Charmander extends Pokemon
{
   public Charmander()
   {
      super();
      moveset.add(new Move(60,"Wing Attack","Flying")); //index 0
      moveset.add(new Move(20,"Flail","Normal")); //index 1
      moveset.add(new selfMove(0,"Belly Drum","Normal")); //index 2
      moveset.add(new Move(120,"Flare Blitz","Fire"));
      weakness.add("Water");
      weakness.add("Ground");
      weakness.add("Rock");
      resistance.add("Grass");
      resistance.add("Fire");
      name = "Charmander";
      type = "Fire";
      baseatk = 114;
      basedef = 114;
      maxhp = 390;
      hp = maxhp;
   }
   
   public void use(int option, ArrayList<Pokemon> userteam, ArrayList<Pokemon> foeteam)
   {
      if(moveset.get(option).moveName.equals("Belly Drum"))
      {  //spend 33% of max hp to increase Atk tremendously (100% atk -> 250% atk). High risk, high reward!
         System.out.println(name +" used Belly Drum!\n" + name + " took " + this.maxhp / 3 + " damage to increase its attack!");
         this.takeDamage(this.maxhp / 3);
         this.alterAtk(3);
      }
   }
}

class Pikachu extends Pokemon
{
   public Pikachu()
   {
      super();
      moveset.add(new Move(50,"Charge Beam","Electric")); //index 0
      moveset.add(new Move(0,"Endeavor","Normal")); //index 1
      moveset.add(new Move(0,"Tickle","Normal")); //index 2
      moveset.add(new Move(90,"Thunderbolt","Electric"));
      weakness.add("Ground");
      resistance.add("Flying");
      resistance.add("Electric");
      name = "Pikachu";
      type = "Electric";
      baseatk = 105;
      basedef = 90;
      maxhp = 350;
      hp = maxhp;
   }
}

class Eevee extends Pokemon
{
   public Eevee()
   {
      super();
      moveset.add(new spreadMove(0,"Tail Whip","Normal")); //index 0
      moveset.add(new Move(0,"Helping Hand","Normal")); //index 1
      moveset.add(new Move(120,"Double Edge","Normal")); //index 2
      moveset.add(new spreadMove(60,"Swift","Normal"));
      weakness.add("Fighting");
      name = "Eevee";
      type = "Normal";
      baseatk = 100;
      basedef = 115;
      maxhp = 550;
      hp = maxhp;
   }
   public void use(int option, ArrayList<Pokemon> userteam, ArrayList<Pokemon> foeteam)
   {
      int numTargets = 0;
      for(Pokemon foe : foeteam)
      {
         if(foe.battling)
            numTargets++;
      }
         //Tail whip reduces defense instead of doing damage
      if(moveset.get(option).moveName.equals("Tail Whip"))
      {
         System.out.println(name + " used Tail Whip on the opposing Pokemon!");
         foeteam.get(0).alterDef(-1);
         foeteam.get(1).alterDef(-1);
      }
         //if the spread move hits 2 targets, damage is reduced by 25%
         //spread moves mud slap
      else if(numTargets == 2)
      {
         System.out.println(name + " used " + moveset.get(option).getName() + " on the opposing Pokemon!");
         int dam = (int)(0.75 * moveset.get(option).damage(this, foeteam.get(0)));
         foeteam.get(0).takeDamage(dam);
         System.out.println(foeteam.get(0).name + " took "+ dam +" damage!");
         dam = (int)(0.75 * moveset.get(option).damage(this, foeteam.get(1)));
         foeteam.get(1).takeDamage(dam);
         System.out.println(foeteam.get(1).name + " took "+ dam +" damage!");
      }
         //otherwise it is treated as normal
      else if (foeteam.get(0).battling)
      {
         attack(foeteam.get(0), option);
      }
      else
      {
         attack(foeteam.get(1), option);
      }
   }
}