import java.awt.*;

public class Rectangle {
    int x;
    int y;

    public Rectangle(int width, int height) {
        this.x = width;
        this.y = height;
    }

    public void draw(String file) throws RectangleException {
        String figure = "";

        if (x == y) {
            figure = "Square";
        } else {
            figure = "Rectangle";
        }

        System.out.println(
                figure + " " + x + " x " + y
        );

        if (x < 1 || y < 1) {
            throw new RectangleException("Invalid rectangle!");
        }
    }

}
