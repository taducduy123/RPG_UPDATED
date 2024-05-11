package MAP;
import CHARACTER.RegularMonster;
import CHARACTER.TargetMonster;
import ITEM.*;

public class Map1 extends Map
{
    //------------------------------------------------------------

    public Map1(String mapFIlePath)
    {
        super(mapFIlePath);
        //this.addPlayer(player);
        this.addItem(new Weapon("Axe", 1, 1, 2, 0));
        this.addItem(new Armor("Armor", 10, 5, 3, 0));
        this.addItem(new Armor("Goden Armor", 10, 5, 4, 0));
        this.addItem(new Weapon("Goden A", 10, 5, 6, 0));
        this.addItem(new Potion("Apple", 1, 0, 1));
        this.addItem(new Potion("Apple1", 2, 1, 1));
        this.addMonster(new RegularMonster("Orc", 50, 5, 0, 10, 10));
        this.addMonster(new TargetMonster("Orc1", 50, 7, 0, 11, 10));
    }

    

}