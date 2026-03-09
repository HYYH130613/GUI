import static java.lang.Math.*;

public class CharRectangle extends Rectangle {

    char border;
    char interior;
    private static int rectId = 0, squareId = 0;

    public CharRectangle(int x, int y, char border, char interior) {
        super(x, y);
        this.border = border;
        this.interior = interior;
        this.rectId = ++rectId;
    }

    public CharRectangle(int x, char border, char interior) {
        this(x, x, border, interior);
        this.squareId = ++squareId;
    }

    static int numeric() {
        int number = 0;
        return ++number;
    }

    @Override
    public void draw(String file) throws RectangleException{
        int bordercount, interiorcount;

        System.out.print("Character ");
        if (x == y) {
            System.out.print("square ");
            bordercount = 4 * x - 4;
            interiorcount = (int) Math.pow((x - 2), 2);
            System.out.print("(" + squareId + ") " + x + " x " + x + ", ");
        } else {
            System.out.print("rectangle ");
            bordercount = 2 * (x + y) - 4;
            interiorcount = (x - 2) * (y - 2);
            System.out.print("(" + rectId + ") " + x + " x " + y + ", ");
        }

        if (x == 1 && y == 2 || x == 2 && y == 1) {
            if (border != interior) {
                System.out.println();
                throw new RectangleException("Invalid rectangle");
            }
        }

        System.out.println(border + "=" + bordercount + ", " + interior + "=" + interiorcount);

        for (int row = 0; row < y; row++) {
            for (int col = 0; col < x; col++) {
                if (row == 0 || row == y - 1 || col == 0 || col == x - 1) {
                    System.out.print(border);
                } else {
                    System.out.print(interior);
                }
            }
            System.out.println();
        }
    }
}
