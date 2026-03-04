import java.util.Random;

public class Archer extends Character{

    private Random rand = new Random();

    public Archer(String name, int strength){
        super(name,strength);
    }

    @Override
    public int performAttack(){
        boolean random = rand.nextBoolean();
        int damage = strength;
        if(random){
            System.out.println(
                    "Archer " + name + " hitsthe eye! CRITICAL HIT! (" + damage + " dmg)"
            );
        }else{
            System.out.println(
                    "Archer " + name + " shoots an arrow dealing " + damage
                            + " damage."
            );
        }
        return damage;
    }

    @Override
    public String introduceSelf(){
        return "[Archer] "+super.introduceSelf();
    }
}
