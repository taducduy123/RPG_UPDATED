package CHARACTER;

import java.util.LinkedList;
import java.util.List;

import ITEM.Item;

public abstract class Monster extends Character
{
    protected List<Item> itemsToDrop;
    public int type;

//------------------------------------------

    //Constructor
    public Monster(String name, int maxhp, int atk, int def, int range, int x, int y) 
    {
        super(name, maxhp, atk, def, range, x, y);
        itemsToDrop = new LinkedList<>();
    }



//------------------------------ Getter Method ------------------------------------
    public int getMonsterType(){
        return this.type;
    }
    public boolean isDie(){
        boolean status = false;
        if(this.getHP() <= 0)
            status = true;
        return status;
    }
//------------------------------ Abstract Methods ---------------------------------

    public abstract void setItemToDrop();
    public abstract Item lootItem();
    
}