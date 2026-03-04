//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {

        Character character = new Character("Adelina", 100);
        String inf = character.introduceSelf();

        Warrior warrior = new Warrior("Joe", 100);
        String inf2 = warrior.introduceSelf();
        int damage = warrior.performAttack();

        System.out.println(inf);

        System.out.println(damage);
        System.out.println(inf2);

    }
}