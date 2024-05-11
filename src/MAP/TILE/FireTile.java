package MAP.TILE;
import CHARACTER.Character;

public class FireTile extends Tile
{
    private int lossHP = 10;

//---------------------------------------------------
    public FireTile()
    {   
        super(false);
    }

    @Override
    public void drawTile(String mark)
    {
        System.out.printf(" {%2s} ", mark);
    }

    @Override
    public void applyEffectTo(Character character) 
    {
        character.takeDamage(lossHP);
    }

}