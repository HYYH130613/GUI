import java.awt.*;

public class CharRectangle extends Rectangle {

    char border;
    char interior;

    public CharRectangle(int x, char border, char interior) {
        super(x, x);
        this.border = border;
        this.interior = interior;
    }

    public CharRectangle(int x, int y, CharRectangle rectangle) {
        super(x,y);
        this.border = rectangle.border;
        this.interior = rectangle.interior;
    }

    static int numeric(){
        int number = 0;
        return ++number;
    }

    @Override
    public void draw(String file) {

        int border = 0;
        int interior = 0;

        System.out.print("Character ");
        super.draw(file);
        System.out.print(", ");

    }
}
