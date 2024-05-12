package CHARACTER;

import MAP.*;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;

import ITEM.Item;

public class Boss extends Monster{

    public Boss(String name, int maxhp, int atk, int def, int range, int x, int y) {
        super(name, maxhp, atk, def, range, x, y);
        //TODO Auto-generated constructor stub
    }

    @Override
    public void setItemToDrop() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'setItemToDrop'");
    }

    @Override
    public Item lootItem() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'lootItem'");
    }

    @Override
    public String getMark() {
        return "Bo";
    }
    
    
    public boolean lowerHalfHp(){
        boolean status = false;
        if(this.getHP() <= this.getMaxHp()/2){
            status = true;
        }
        return status;
    }
    

    public void bossMove(Map map, Player player)
    {
        List<Pair> path = new LinkedList<>();
        int currentStep = 0;
        if(map.findPath_BFS_Between(this.getX(), this.getY(), player.getX(), player.getY(), path))
        {
            //Search the current position of monster, compared to the path
            for(int i = 0; i < path.size(); i++)
            {
                if(path.get(i).getX() == this.getX() && path.get(i).getY() == this.getY())
                {
                    currentStep = i;
                    break;
                }
            }
            //Navigate monster to follow the correct path
            if(currentStep < path.size() - 1)       //if the monster does not reach target
            {
                int dx = path.get(currentStep + 1).getX() - this.getX();
                int dy = path.get(currentStep + 1).getY() - this.getY();
                if(dx == 0 && dy == -1)
                {
                    this.moveUp(map);
                }
                else if(dx == 0 && dy == 1)
                {
                    this.moveDown(map);
                }
                else if(dx == -1 && dy == 0)
                {
                    this.moveLeft(map);
                }
                else if(dx == 1 && dy == 0)
                {
                    this.moveRight(map);
                }
            }
        }
        else        //random move if it doesn't find path
        {
            Random random = new Random();
            int ranNum = random.nextInt(100) + 1;
    
            if(ranNum <= 25)
            {
                this.moveUp(map);
            }
            else if (25 < ranNum && ranNum <= 50)
            {
                this.moveDown(map);
            }
            else if (50 < ranNum && ranNum <= 75) 
            {
                this.moveLeft(map);
            }
            else
            {
                this.moveRight(map);
            }
        }
    }
   
    public void bossPush(Map map, Player player)
    {
        
        int deltaX = player.getX() - map.mapCenter().getX();
        int deltaY = player.getY() - map.mapCenter().getY();

        if(Math.abs(deltaY) > Math.abs(deltaX)){
            if(player.getY() > map.mapCenter().getY()){
                player.setXY(player.getX(), map.getMaxTileRows() - 1, map);
            }
            else{
                player.setXY(player.getX(), 0, map);
            }
        }
        else if(Math.abs(deltaY) == Math.abs(deltaX)){
            if(deltaX > 0 && deltaY < 0)
                player.setXY(map.getMaxTileCols() - 1 , 0, map);
            else if(deltaX < 0 && deltaY < 0)
                player.setXY(0 , 0, map);
            else if(deltaX < 0 && deltaY > 0)
                player.setXY(0 , map.getMaxTileRows() - 1, map);
            else if(deltaX > 0 && deltaY > 0)
                player.setXY(map.getMaxTileRows() -1  , map.getMaxTileCols() - 1, map);
        }
        else{
            if(player.getX() > map.mapCenter().getX()){
                player.setXY(map.getMaxTileCols() - 1, player.getY(), map);
            }
            else{
                player.setXY(0, player.getY(), map);
            }
        }
    }

}
