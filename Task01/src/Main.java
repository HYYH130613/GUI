//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        Rectangle[] rect = {
                    new Rectangle(2, 3),
                    new Rectangle(0, 3),
                    new CharRectangle(4, 'a', 'e'),        // three-parameter constructor
                    new CharRectangle(5, 3, '*', '+'),    // four-parameter constructor
                    new CharRectangle(1, 2, 'a', 'a'),
                    new CharRectangle(3, 3, '+', 'x'),
                    new CharRectangle(1, 2, 'x', 'y'),
                    new CharRectangle(3, 4, '^', '$')
            };

            for (Rectangle r : rect)                        // for (int i = 0; i < rect.length; i++)
                try {
                    r.draw("file");                                        // rect[i].draw("file");
                } catch(RectangleException e) {
                System.out.println(e.getMessage());
                }
    }
}