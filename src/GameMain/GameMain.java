package GameMain;
import MAP.Map;
import MAP.Map1;
import MAP.Map2;

import java.util.Scanner;
import CHARACTER.Player;
import GAMESTAGE.GameStage;
import ITEM.Inventory;

public class GameMain
{
    private Player player;
    private Map map;
    private Inventory inventory;
    private int stage;
    private GameStage stage1, stage2; 

    private final int inventorySize = 5;
    //private final int maxStage = 2;
    private final String path1 = "src\\InputFile\\map1.txt";
    private final String path2 = "src\\InputFile\\map2.txt";

    private String playerName;

    private static Scanner input = new Scanner(System.in);
//----------------------------------------------------------------------------

    //Constructor
    public GameMain()
    {
        System.out.print("Enter you name: ");
        this.playerName = input.nextLine();
        System.out.println("\n**************************************************");
        System.out.println("Hello " + this.playerName + "! Let's start the game.");
        System.out.println("Press any key to continue: ");
        input.nextLine();

        this.player = new Player(playerName);
        this.map = new Map1(path1);
        this.inventory = new Inventory(inventorySize);
        this.stage = 1;
    }

    public void start()
    {
        runMainLoop();
    }


    public void runMainLoop()
    {
        int choice;
        do
        {      
            System.out.println("\n******************************************************************");
            System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>> HOME MENU <<<<<<<<<<<<<<<<<<<<<<<<<<<");
            System.out.println("******************************************************************");
            System.out.println("1. New Game");
            System.out.println("2. Continue Game");
            System.out.println("3. Exit");
            System.out.print("Enter your choice: ");
            choice = input.nextInt();
            input.nextLine();                //Consume keyboard buffer
            switch (choice) 
            {
                case 1:
                        resetGame();
                        this.runStageLoop();
                        break;
                case 2:
                        this.runStageLoop();
                        break;
                case 3:
                        System.out.println("See you next time!");
                        break;
                default:
                        System.out.println("ERROR: Invalid Choice!");
                        break;

            }
        } while (choice != 3);
    }
    
    public void runStageLoop()
    {
        boolean status = true;
        while (status == true) 
        {
            switch (this.stage) 
            {
                case 1:
                        stage1 = new GameStage(player, map, inventory, stage);
                        stage1.StartGame();
                        status = updateNewState();
                        break;
            
                case 2:
                        stage2 = new GameStage(player, map, inventory, stage);
                        stage2.StartGame();
                        status = updateNewState();
                        break;
                default:
                    break;
            }
        }
    }


    public boolean updateNewState()
    {
        boolean status = true;
        switch (this.stage) 
        {
            case 1:
                    if(stage1.getWinFlag() == true)
                    {
                        this.stage = this.stage + 1;
                        this.player = new Player(this.playerName);
                        this.map = new Map2(path2);
                    }
                    else if (stage1.getLoseFlag() == true)
                    {
                        this.stage = 1;
                        this.player = new Player(this.playerName);
                        this.map = new Map1(path1);
                        this.inventory = new Inventory(inventorySize);
                    }
                    else
                    {
                        status = false;         //back to HomeMenu
                    }
                    break;
        
            case 2:
                    if(stage2.getWinFlag() == true)
                    {
                        System.out.println("YOU WIN ALL THE GAME !!!!!!!!!!!!!!!");
                        
                    }
                    else if (stage2.getLoseFlag() == true)
                    {
                        this.stage = 1;
                        this.player = new Player(this.playerName);
                        this.map = new Map1(path1);
                        this.inventory = new Inventory(inventorySize);
                    }
                    else
                    {
                        status = false;        //back to HomeMenu
                    }
                    break;
            default:
                    break;
        }

        return status;
    }
    

    public void resetGame()
    {
        this.player = new Player(playerName);
        this.map = new Map1(path1);
        this.inventory = new Inventory(inventorySize);
        this.stage = 1;
    }

    
    //Embedded Main
    public static void main(String[] args) 
    {
        GameMain game = new GameMain();
        game.start();
    }
}
