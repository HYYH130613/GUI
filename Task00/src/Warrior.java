public class Warrior extends Character{

    Warrior(String name, int strength){
        super(name, strength);
    }

    @Override
    public int performAttack(){
        int damage = (int)( 1.5 * strength);
        System.out.println(
                "Warrior " + name + " slashes with a sword dealing " + damage + " damage!"
        );
        return damage;
    }

    @Override
    public String introduceSelf(){
        return  "[Warrior] " + super.introduceSelf();
    }

}
