import java.util.ArrayList;
import java.lang.Math;
public class Move
{
   public int movePower;
   public String moveName;
   public String moveType;
   public String aim;
   public Move(int pow, String name, String type)
   {
      movePower = pow;
      moveName = name;
      moveType = type;
      aim = "Single"; //Single - choose one target (default) //Self - only used on self, 
                      //Enemies - used on all foes, //Allies - used on team. Those last three are automatically done.
   }
   public String description(boolean willDescribe)
   {  //if you turned move descriptions on, it'll tell you what a specific move does!
      //this is good for people new to my program
      String text = "";
      if (willDescribe)
      {  //basic text
         text += ": a " + moveType + " Type move";
         //singe target
         if(aim.equals("Single") && !(moveName.equals("Helping Hand") || moveName.equals("Toxic") || moveName.equals("Tickle") || moveName.equals("Endeavor")) )
         {   
            text += " with a power of " + movePower;
            if(moveName.equals("Charge Beam"))
            {
               return text + ". There is a 70% chance that using this move will increase the User's Attack by 1 Stage.\n";
            }
            else if(moveName.equals("Double Edge") || moveName.equals("Flare Blitz"))
            {
               return text + " that will damage the user by up to 25% of the damage they dealt.\n";
            }
            else if(moveName.equals("Giga Drain"))
            {
               return text + " that will heal the user by up to 50% of the damage they dealt.\n";
            }
            else if(moveName.equals("Brine"))
            {
               return text + " that becomes more powerful if the target has less than half of their health remaining.\n";
            }
            else if(moveName.equals("Flail"))
            {
               return text + " that becomes more powerful based on how much hp this user is missing.\n";
            }
            return text + ".\n";
         }
         else if(moveName.equals("Toxic"))
         {
            return text + " that has a 90% chance to poison an enemy if they are not a Poison Type Pokemon.\n";
         }
         else if(moveName.equals("Helping Hand"))
         {
            return text + " that increases a target's Attack by 2 Stages.\n";
         }
         else if(moveName.equals("Tickle"))
         {
            return text + " that reduces a target's Attack and Defense by 1 Stage.\n";
         }
         else if(moveName.equals("Endeavor"))
         {
            return text + " that reduces a target's hp to this Pokemon's hp. If this Pokemon's hp is higher, the move fails.\n";
         }
      }
      return "\n";
   }
   
   //return the nae of the move
   public String getName()
   {
      return moveName;
   }
   
   //calculates the damage dealt
   public int damage(Pokemon user, Pokemon target)
   {
      int dam = movePower; //the move power of an attack, but this value can be changed, and
      //movePower should not be changed, ever.
      
      //moves that deal no damage or have unique effects
      if (moveName.equals("Endeavor"))
      {
         if(user.hp < target.hp)
         {  //if user has 1 hp and the enemy has 300, deal (300-1)= 299 damage so that the enemy has 1 hp too
            return target.hp - user.hp;
         }
         System.out.println(user.name+"'s hp is not less than the target"+ target.name+"'s hp! the move failed!");
         return 0; //if you can't damage the enemy, just return 0 damage
      }
      else if (moveName.equals("Tickle"))
      {  //reduce a single enemy's atk and def by 1 stage
         target.alterAtk(-1);
         target.alterDef(-1);
         return 0; //no actual damage is dealt
      }
      else if (moveName.equals("Helping Hand"))
      {  //increase any single ally's atk by 2 stages, including self
         target.alterAtk(2);
         return 0; //you shouldn't hurt an ally with this
      }
      else if (moveName.equals("Toxic"))
      {
         if(target.status.equals("Poison"))
         {
            System.out.println(target.name+" is already poisoned! the move failed!");
         }
         else if(target.name.equals("Bulbasaur"))
         {
            System.out.println("Toxic does not affect Bulbasaur! Bulbasaur is a Poison Type!");
         }
         //the only valid way, a 90% chance of poisoning
         else if(0.9 > Math.random())
         {
            System.out.println(user.name+" poisoned the target "+target.name+"!"); 
            target.status = "Poison";
         }
         else
         {
            System.out.println("The attack missed?!");
         }
         //Toxic deals no direct damage to the opponent
         return 0;
      }
      else if(moveName.equals("Flail"))
      {  //default 20, but damage increases when the user is hurt
         if(user.hp / (double)user.maxhp < 0.6875) //total 40
            dam += 20;
         if(user.hp / (double)user.maxhp < 0.3542) //total 70
            dam += 30;
         if(user.hp / (double)user.maxhp < 0.2083) //total 100
            dam += 30;
         if(user.hp / (double)user.maxhp < 0.1042) //total 150
            dam += 50;
         if(user.hp / (double)user.maxhp < 0.0417) //total 200 damage
            dam += 50;
      }
      //brine has its 'move power' doubled if the target is at 50% or less hp
      else if(target.hp < target.maxhp / 2 && moveName.equals("Brine"))
         dam *= 2;
      //the actual damage calculations
      dam = (int)(((42.0 * dam *((double)user.getAtk() / target.getDef()))/50.0 + 2) * (Math.random() * 0.15 + 0.85));
      //final damage is doubled if the target is weak to that attack
      if (target.contains(target.weakness, moveType))
      {
         System.out.print("It's super effective! ");
         dam *= 2;
      }
      //final damage is halved if the target is resistant to that attack
      else if(target.contains(target.resistance, moveType)) 
      {
         System.out.print("It's not very effective... ");
         dam /= 2;
      }
      //If the move the user uses is the same type as the user, damage is increased by 50%
      if(user.type.indexOf(moveType) >= 0)
         dam *= 1.5;
      return dam;
   }
   
}

/*
Subclasses; moves that have unique targeting. 
This affects their descriptions,but not their effects.
their effects are determined in the Pokemon subclasses (such as bulbaasaur's synthesis)
This also differentiates use() from attack()
*/

//extra stuff if the move is a selfMove (targets ONLY the user)
class selfMove extends Move
{
   public selfMove(int pow, String name, String type)
   {
      super(pow, name, type);
      aim = "Self"; //Single - choose one target (default) //Self - only used on self, 
                    //Enemies - used on all foes, //Allies - used on team. Those last three are automatically done.
   }
   public String description(boolean willDescribe)
   {
      if (willDescribe)
      {  //basic text
         if(moveName.equals("Synthesis"))
         {
            return ": a " + moveType + " Type move that heals the user by up to 50% of their maximum hp. The amount recovered reduces over time if used too many times.\n";
         }
         else if(moveName.equals("Belly Drum"))
         {
            return ": a " + moveType + " Type move that damages the user by 33% of the user's maximum hp in order to increase the user's Attack by 3 Stages.\n";
         }
      }
      return "\n"; 
   }
}

//extra stuff if the move is a selfMove (targets the user and their allies, if those allies are able to battle)
class teamMove extends Move
{
   public teamMove(int pow, String name, String type)
   {
      super(pow, name, type);
      aim = "Allies"; //Single - choose one target (default) //Self - only used on self, 
                    //Enemies - used on all foes, //Allies - used on team. Those last three are automatically done.
   }
   
   public String description(boolean willDescribe)
   {
      if (willDescribe)
      {  
         if(moveName.equals("Life Dew"))
         {   
            return ": a " + moveType + " Type move that heals the team by an amount equal to 25% of their maximum hp.\n";
         }  
         return ": a " + moveType + " Type move that increases the Team's Defense by 1 Stage.\n";
      }
      return "\n"; 
   }
}

//extra stuff if the move is a spreadMove (targets both foes at 75% the original damage)
class spreadMove extends Move
{
   public spreadMove(int pow, String name, String type)
   {
      super(pow, name, type);
      aim = "Enemies"; //Single - choose one target (default) //Self - only used on self, 
                    //Enemies - used on all foes, //Allies - used on team. Those last three are automatically done.
   }
   
   public String description(boolean willDescribe)
   {
      if (willDescribe)
      {  //basic text
         if(aim.equals("Enemies") && moveName.equals("Tail Whip"))
         {   
            return ": a " + moveType + " Type move that reduces the Defense of both enemies on the field by 1 Stage\n";
         }
         return ": a " + moveType + " Type move with a power of " + movePower + " that hits both enemies on the field. If both targets are able to fight, damage will be reduced by 25%.\n";
      }
      return "\n"; 
   }
}