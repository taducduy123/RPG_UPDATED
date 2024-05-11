package MAP.TILE;
import CHARACTER.Character;

public class LandTile extends Tile
{

//---------------------------------------------

    public LandTile()
    {   
        super(false);
    }

    @Override
    public void drawTile(String mark)
    {
        System.out.printf(" [%2s] ", mark);
    }

    @Override
    public void applyEffectTo(Character character) 
    {
        
    }

    
}
