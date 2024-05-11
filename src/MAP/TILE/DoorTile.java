package MAP.TILE;
import MAP.Map;
import CHARACTER.Character;

public class DoorTile extends Tile
{
    private boolean open;

//---------------------------------------------------
    public DoorTile()
    {   
        super(true);
        this.open = false;
    }

   
    @Override
    public void applyEffectTo(Character character) 
    {
        
    }


    @Override
    public void drawTile(String mark) 
    {
        if(!this.isOpen())
        {
            mark = "--";
        }
        System.out.printf(" |%2s| ", mark);
    }

    public void setDoorOpen(Map m)
    {
        if(m.numberOfMonsters() == 0)
        {
            this.solid = false;
            this.open = true;
        }
    }

    public boolean isOpen()
    {return this.open;}

}
