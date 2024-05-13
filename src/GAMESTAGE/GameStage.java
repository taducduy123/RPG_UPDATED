package GAMESTAGE;

import java.util.ArrayList;
import java.util.Scanner;
import javax.swing.JOptionPane;
import CHARACTER.*;
import CHARACTER.Character;
import ITEM.*;
import MAP.*;

public class GameStage 
{
    protected Player player;                    //current player
    protected Map map;                        //current map
    protected Inventory inventory;              //current inventory 

    private int stage;                        //current stage
    private boolean winFlag;
    private boolean loseFlag;

    private static Scanner input = new Scanner(System.in);

//---------------------------------------------------------------------------

    //Constructor
    public GameStage(Player player, Map map, Inventory inventory, int stage)
    {
        this.player = player;
        this.map = map;
        this.inventory = inventory;
        this.stage = stage;
        this.winFlag = false;
        this.loseFlag = false;

    }


    //--------------------------------- Start Game -------------------------------------
    public void StartGame(){
        
        System.out.println("\n***********************************************************");
        System.out.println(">>>>>>>>>>>>>>>>>>> Welcome to Stage #" + this.stage + " <<<<<<<<<<<<<<<<<<<");
        System.out.println("***********************************************************\n");
        System.out.println("Press any key to continue................");
        input.nextLine();

        //Print the initial state of game
        System.out.println("\n*****************************************************\n");
        this.player.showState();
        this.map.drawMap(player);

        //Game Loop   
        MainMenu(this.map, this.player, this.inventory);
                
    }

//---------------------------------- Main Menu ---------------------------------------  
    public void MainMenu(Map m, Player obj, Inventory i){
        int choice;
        do
        {
            System.out.println("\n------------------------------------------------------\n");
            System.out.println("1. Move");
            System.out.println("2. Show Inventory");
            System.out.println("3. Attack");
            System.out.println("4. Back To Home");
            System.out.print("Enter your choice: ");
            choice = input.nextInt();
            input.nextLine();                               //Consume keyboard buffer

            switch (choice) {
                case 1:
                    moveControl(m, obj, i);
                    break;
                case 2:
                    inventoryControl(m, obj, i);
                    break;
                case 3:
                    attackMenu(m, obj, i);         
                    break;
                case 4:                    
                    //System.out.println("Thanks for playing");
                    break;               
                default:
                    System.out.println("Invalid choice");
                    break;
            }           
        } while(choice != 4 && winFlag == false && loseFlag == false);
    }

    
//---------------------------------- Menu for Move ---------------------------------------

    public void moveControl(Map m, Player p, Inventory i) {
        int choice;
        boolean status = true;
        do {
            System.out.println("\n------------------------------------------------------\n");
            System.out.println("1. Move Up");
            System.out.println("2. Move Down");
            System.out.println("3. Move Left");
            System.out.println("4. Move Right");
            System.out.println("5. No Move");
            System.out.println("6. Back To Menu (Attack & Inventory)");
            System.out.print("Enter your choice: ");
            choice = input.nextInt();
            input.nextLine();                           //Consume keyboard buffer

            switch (choice) {
                case 1:
                    p.moveUp(m);
                    updateGame(m, p, i); 
                    if(this.winFlag == true || this.loseFlag == true)
                    {break;}                   
                    m.drawMap(p);
                    p.showState();
                    messageToShow(m, p);
                    break;

                case 2:
                    p.moveDown(m);
                    updateGame(m, p, i);
                    if(this.winFlag == true || this.loseFlag == true)
                    {break;}   
                    m.drawMap(p);
                    p.showState();
                    messageToShow(m, p);
                    break;

                case 3:
                    p.moveLeft(m);
                    updateGame(m, p, i);
                    if(this.winFlag == true || this.loseFlag == true)
                    {break;}   
                    m.drawMap(p);
                    p.showState();
                    messageToShow(m, p);
                    break;

                case 4:
                    p.moveRight(m);
                    updateGame(m, p, i);
                    if(this.winFlag == true || this.loseFlag == true)
                    {break;}   
                    m.drawMap(p);
                    p.showState();
                    messageToShow(m, p);
                    break;

                case 5:
                    updateGame(m, p, i);
                    if(this.winFlag == true || this.loseFlag == true)
                    {break;}   
                    m.drawMap(p);
                    p.showState();
                    messageToShow(m, p);
                    break;

                case 6: 
                    
                    status = false;
                    break;

                default:
                    System.out.println("Invalid choice!");
                    break;
            }
        } while (status == true  && winFlag == false && loseFlag == false);
    }
    

//---------------------------------- Menu for Inventory ---------------------------------------   
    public void inventoryControl(Map m, Player p, Inventory i){
        System.out.println("\n------------------------------------------------------\n");
        if(i.isEmpty())         
            System.out.println("Empty inventory");
        else{
            i.displayInventory();
            System.out.println("Attack weapon: " + p.getCurrentWeapon());
            System.out.println("Defense weapon: " + p.getCurrentArmor());
            int choice, choice1;
            boolean status = true;
            do{              
                System.out.print("Enter a number to show item (Exit: 0 | Range: 1 - " + i.size() + "): ");
                choice = input.nextInt();
                if(choice == 0) 
                    status = false;
                else if(0 < choice && choice <= i.size()){
                    boolean status1 = true;
                    System.out.println("\n------------------------------------------------------\n");  
                    if(i.getItem(choice - 1) instanceof Weapon)
                        System.out.println((i.getItem(choice - 1)).toString());
                    else if(i.getItem(choice - 1) instanceof Armor)
                        System.out.println((i.getItem(choice - 1)).toString());
                    else if(i.getItem(choice - 1) instanceof Potion)
                        System.out.println(i.getItem(choice - 1).toString());
                    do{               
                        System.out.println("1. Use item");
                        System.out.println("2. Remove item");
                        System.out.println("3. Back");
                        System.out.print("Enter your choice: ");
                        choice1 = input.nextInt();
                        if(choice1 == 1){
                            if(i.getItem(choice - 1) instanceof Weapon)
                                p.equipWeapon(i.getItem(choice - 1));
                            else if (i.getItem(choice - 1) instanceof Armor)
                                p.equipArmor(i.getItem(choice - 1));
                            else if (i.getItem(choice -1) instanceof Potion){
                                p.equipPotion(i.getItem(choice -1));
                                i.removeItem(choice - 1);
                            }
                            System.out.println("Equip sucessfully");
                            System.out.println("\n------------------------------------------------------\n");
                            i.displayInventory();
                            System.out.println("Current weapon: " + p.getCurrentWeapon());
                            System.out.println("Current armor: " + p.getCurrentArmor());
                            status1 = false;
                        }       
                        else if(choice1 == 2){
                            if(i.getItem(choice - 1) instanceof Weapon && i.getItem(choice - 1).getInUse() == true)
                                p.unequipWeapon();
                            else if(i.getItem(choice - 1) instanceof Armor && i.getItem(choice - 1).getInUse() == true)
                                p.unequipArmor();
                            i.removeItem(choice - 1);
                            System.out.println("Remove sucessfully");
                            System.out.println("\n------------------------------------------------------\n");
                            i.displayInventory();
                            System.out.println("Current weapon: " + p.getCurrentWeapon());
                            System.out.println("Current armor: " + p.getCurrentArmor());
                            status1 = false;
                        }
                        else if(choice1 == 3){            
                            System.out.println("\n------------------------------------------------------\n");
                            i.displayInventory();
                            System.out.println("Current weapon: " + p.getCurrentWeapon());
                            System.out.println("Current armor: " + p.getCurrentArmor());
                            status1 = false;
                        }
                        else System.out.println("Invalid choice");
                    } while (status1 == true);          
                }
                else System.out.println("Invalid choice");
            }while(status == true);
            System.out.println("\n------------------------------------------------------\n");
            m.drawMap(p);
            p.showState();
        }
    }


//---------------------------------- Menu for Attack ---------------------------------------  
    public void attackMenu(Map m, Player p, Inventory j){
        //Find all monsters in range of player
        ArrayList<Monster> targets = new ArrayList<Monster>();
        for(int i = 0; i < m.numberOfMonsters(); i++){
            if(p.collideMonster(m.getMonsterAtIndex(i)))
                targets.add(m.getMonsterAtIndex(i));
        }

        //Print all monsters in range so that player can pick one to attack
        if(targets.size() == 0)
        {
            System.out.println(">> No monster in range to attack!");
        }
        else
        {
            System.out.printf("|%10s | %20s | %10s |\n", "No.",
                                                        "Name",
                                                        "HP");
            for(int i = 0; i < targets.size(); i++){
                System.out.printf("|%10s | %20s | %10s |\n", i + 1, 
                        targets.get(i).getName() + "(" + targets.get(i).getMark() + ")",
                        targets.get(i).getHP() + "/" + targets.get(i).getMaxHp());
            }
            int choice;
            System.out.print("Choose a number (0: Exit || 1 - " + targets.size() + ") to attack monster: ");
            choice = input.nextInt();
            if(choice > 0){
                targets.get(choice - 1).takeDamage(p.getAttack());
                updateGame(m, p, j);
                m.drawMap(p);
                p.showState();
                targets.clear();
            }
            else if(choice < 0)
                System.out.println("Invalid choice");
            else{           
                System.out.println("\n------------------------------------------------------\n");
                m.drawMap(p);
            }
        }
    }

    /*
    public boolean isInRange(Character obj1, Character obj2){
        boolean status = false;
        int max_X = obj1.getX() + obj1.getRange();
        int min_X = obj1.getX() - obj1.getRange();
        int max_Y = obj1.getY() + obj1.getRange();
        int min_Y = obj1.getY() - obj1.getRange();
        if(min_X <= obj2.getX() && obj2.getX() <= max_X && min_Y <= obj2.getY() && obj2.getY() <= max_Y)
            status = true;
        return status;
    }
    */

    


//---------------------------------- Update Object ------------------------------------------------

    //Update player + items + monsters + door +  game state
    public void updateGame(Map m, Player p, Inventory i){
        updatePlayer(m, p);
        updateItems(m, p, i);
        updateMonsters(m, p);
        updateDoor(m);
        updateGameState();
    }

    //Update player
    public void updatePlayer(Map m, Player p)
    {
        m.getTile(m.getTileManager_RowCol(p.getY(), p.getX())).applyEffectTo(p);
    }

    //Update items
    public void updateItems(Map m, Player p, Inventory i){
        if(m.containItemAt(p.getX(), p.getY())){ 
            if(!i.isFull()){
                i.addItem(m.correspondingItemAt(p.getX(), p.getY()));
                m.removeItemHavingPosition(p.getX(), p.getY());
            }
            else JOptionPane.showMessageDialog(null, "Inventory is full!!!");
        } 
    }

    //Update monsters
    public void updateMonsters(Map m, Player p){
        for(int i = 0; i < m.numberOfMonsters(); i++){
            m.getMonsterAtIndex(i).doWork(p, m);
        }
    }
    
    //Update door
    public void updateDoor(Map m)
    {
        if(m.numberOfMonsters() == 0)
        {
            m.openDoor();
        }
    }
   
    public void messageToShow(Map m, Player p){
        
        if(playerCollideAnyMonster(m, p))
        {
            JOptionPane.showMessageDialog(null, "WARNING: Detecting the monster(s) in range!!!");
        }          
    }

    public boolean playerCollideAnyMonster(Map m, Player p){
        boolean status = false;
        for(int i = 0; i < m.numberOfMonsters(); i++){
            if(p.collideMonster(m.getMonsterAtIndex(i))){
                status = true;
                break;
            }
        }
        return status;
    }



//---------------------------------- Update Game State ------------------------------------------------
     
    public void updateGameState()
    {
        if(this.player.getHP() <= 0)        //if player died
        {
            this.loseFlag = true;                   //loseFlag
            System.out.println("\n*******************************************************\n");
            System.out.println("WARNING: You died! Let's start at the beginning");
            System.out.println("\n*******************************************************\n");
            System.out.print("Press any key to continue:");
            input.nextLine();
        }
        else
        {
            if(this.map.checkDoorOpen() == true)        //if door is open
            {
                System.out.println("\n>> NOW DOOR IS OPEN!!!!");
                if(this.map.containDoorAt(this.player.getX(), this.player.getY())) //if player moves into the door
                {
                    this.winFlag = true;                                //winFlag
                    System.out.println("\n*******************************************************\n");
                    System.out.println("Congratulation! You passed stage #" + this.stage + "!!!!");
                    System.out.println("\n*******************************************************\n");
                    System.out.print("Press any key to continue:");
                    input.nextLine();
                }
            }
        }
    }
    
//-------------------------------------------------- Getter Methods -------------------------------------------------
    public boolean getWinFlag()
    {return this.winFlag;}
    public boolean getLoseFlag()
    {return this.loseFlag;}


    //Embedded Main
    public static void main(String[] args) 
    {
        final String path = "src\\InputFile\\map3.txt";
        final int inventorySize = 10;
        final int stage = 3;

        Player player = new Player(null);
        Map map = new Map(path);
        Inventory inventory = new Inventory(inventorySize);

        GameStage stage1 = new GameStage(player, map, inventory, stage);
        stage1.StartGame();
    }



}
