public class Character {

    protected String name;
    protected int strength;

    public Character(String name, int strength) {
        this.name = name;
        this.strength = strength;
    }

    public int performAttack(){
        return strength;

    }

    public String introduceSelf() {
        return this.name+" (Strength: "+this.strength+")";
    }

}
