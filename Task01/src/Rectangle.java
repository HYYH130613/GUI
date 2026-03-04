import java.awt.*;

public class Rectangle {
    int x;
    int y;

    public Rectangle(int width, int height) {
        this.x = width;
        this.y = height;
    }

    public void draw(String file){
        String figure = file;
        try{

            if(x == y){
                figure = "Square";
            }
            else{
                figure = "Rectangle";
            }

            if(x < 0 || y < 0){
                throw new Exception("Invalid "+figure+"!");
            }

        }catch(Exception e){
            System.out.println(e.getMessage());
        }
        finally{
            System.out.println(
                    figure +" "+ x + "x" + y
            );
        }
    }

}
