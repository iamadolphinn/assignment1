public class Main {
    public static void main (String[] args){
        Rectangle r1 = new Rectangle();
        Rectangle r2 = new Rectangle(5.0, 3.0);

        System.out.println(r1);
        System.out.println(r2);

        System.out.println("Area of r2:" + r2.area());
        System.out.println("Perimeter of r2:" + r2.perimeter());
    }
}
