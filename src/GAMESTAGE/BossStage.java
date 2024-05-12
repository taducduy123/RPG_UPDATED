package GAMESTAGE;

import javax.swing.JOptionPane;

import CHARACTER.Boss;
import CHARACTER.Player;
import ITEM.Inventory;
import MAP.Map;

public class BossStage extends GameStage
{
    Boss boss;

//---------------------------------------------------------------------

    public BossStage(Player player, Map map, Inventory inventory, int stage, Boss boss) 
    {
        super(player, map, inventory, stage);
        this.boss = boss;
        this.map.addMonster(boss);
    }

    public boolean updateBossPhase1(Map m, Player p){
        boolean switchMap = false;
        
        if(this.boss.getHP() == 0){
            m.addItem(this.boss.lootItem());
            m.removeMonsterHavingPosition(this.boss.getX(), this.boss.getY());
        }
        else
        {
            if(this.boss.lowerHalfHp()){
                switchMap = true;
                return switchMap;
            }
            if(isInRange(this.boss, p)){
                JOptionPane.showMessageDialog(null, "WARNING: " 
                                                    + this.boss.getName() 
                                                    + " attacked you. You lost " 
                                                    + p.takeDamage(this.boss.getAttack()) 
                                                    + " HP!!!");
            }
            else{
                this.boss.bossMove(m, p);
            }     
        }
        return switchMap;
    }
    

    public void updateBossPhase2(Map m, Player p){ 
        if(this.boss.getHP() == 0){
            m.addItem(this.boss.lootItem());
            m.removeMonsterHavingPosition(this.boss.getX(), this.boss.getY());
        }
        else
        {
            if(isInRange(this.boss, p)){
                JOptionPane.showMessageDialog(null, "WARNING: " 
                                                    + this.boss.getName() 
                                                    + " attacked you. You lost " 
                                                    + p.takeDamage(this.boss.getAttack()) 
                                                    + " HP!!!");
            }
            else{
                this.boss.bossMove(m, p);
            }     
        }    
    }
}
