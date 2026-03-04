public class Rectangle {
    int width;
    int height;

    public Rectangle(int width, int height) {
        try{

        this.width = width;
        this.height = height;

        if(width == 0 || height == 0){
            throw new Exception("Invalid values!");
        }
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
        finally{
            System.out.println("Rectangle " + width + "x" + height);
        }
    }

}
