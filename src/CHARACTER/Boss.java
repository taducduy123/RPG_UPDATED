package CHARACTER;

import MAP.*;
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
    public void bossMove(Map map){
        
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
