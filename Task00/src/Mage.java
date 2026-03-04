import java.util.Random;

public class Mage extends Character{

    private Random rand = new Random();

    public Mage(String name, int strength){
        super(name, strength);
    }

    @Override
    public int performAttack(){
        int magic_damage = 20;
        int bonus = rand.nextInt(15);

        magic_damage = magic_damage + bonus + (strength/2);

        System.out.println(
                "Mage " + name + " casts a fireball dealing " + magic_damage + " damage!"
        );
        return magic_damage;
    }

    @Override
    public String introduceSelf(){
        return "[Mage] " + super.introduceSelf();
    }

}
