package MAP.TILE;
import CHARACTER.Character;

public abstract class Tile 
{
    
    protected boolean solid;

//--------------------------------------------------

    //Constructor
    public Tile(boolean solid)
    {
        this.solid = solid;
    }

    //Getter Methods
    public boolean getSolid()
    {return this.solid;}

    //Abstract
    public abstract void drawTile(String mark);
    public abstract void applyEffectTo(Character character);

}
