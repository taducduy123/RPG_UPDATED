package MAP.TILE;
import CHARACTER.Character;


public class WaterTile extends Tile
{
    private int hpToHeal = 5;

    //---------------------------------------------------
    public WaterTile()
    {   
        super(false);
    }

    @Override
    public void drawTile(String mark)
    {
        System.out.printf(" (%2s) ", mark);
    }

    @Override
    public void applyEffectTo(Character character)
    {
        character.heal(hpToHeal);
    }    

    
}
