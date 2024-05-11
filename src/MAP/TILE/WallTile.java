package MAP.TILE;
import CHARACTER.Character;

public class WallTile extends Tile
{

//-----------------------------------------

    public WallTile()
    {   
        super(true);
    }

    @Override
    public void drawTile(String mark)
    {
        System.out.printf(" <%2s> ", "00");
    }

    @Override
    public void applyEffectTo(Character character) 
    {
        
    }
    
}
