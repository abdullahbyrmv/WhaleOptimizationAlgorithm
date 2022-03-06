import java.util.Scanner;
/***
     Control Panel manages:
     -   Input & Output
     -   Exception & Error Handling
     It also gives users the opportunity to manage input values by themselves or
     leaving the value choice to the program.
 ***/
public final class ControlPanel {
    public static void start() throws Exception {
        System.out.println("\t\t---------------------------------------------------------------------Population ID: "+DB.POPULATION_ID+"-------------------------------------------------------------");
        System.out.print("1. Calculate based on user input\n2. Calculate based on random input\nChoose the number: ");
        DB.out.println("\t\t---------------------------------------------------------------------Population ID: "+DB.POPULATION_ID+"-------------------------------------------------------------");
        Scanner con = new Scanner(System.in);
        byte num = con.nextByte();
        switch (num) {
            case 1:
                Option1();
                break;
            case 2:
                Option2();
                break;
            default:
                System.out.println("There is no such option");
                DB.out.println("There is no such option");
                DB.save();
                System.exit(0);
        }
        System.out.print("Count of runs: ");
        DB.countOfRuns = con.nextInt();
        DB.out.println("Count of runs: "+DB.countOfRuns);
        if(DB.countOfRuns <= 0) {
            System.out.println("Count of runs must be positive.");
            DB.out.println("Count of runs must be positive.");
            DB.save();
            System.exit(0);
        }
        DB.x = new double[DB.dimension];
        DB.ratio = new double[DB.dimension];
        Function.Optimizer();
    }
    public static void Option1() throws Exception {
        Scanner con = new Scanner(System.in);
        System.out.print("a = ");
        DB.a = con.nextDouble();
        System.out.print("b = ");
        DB.b = con.nextDouble();
        System.out.print("c = ");
        DB.c = con.nextDouble();
        System.out.print("dimension (d) = ");
        DB.dimension = con.nextInt();
        if(DB.dimension <= 0) {
            System.out.println("Dimension must be positive integer.");
            DB.out.println("Dimension must be positive integer.");
            DB.save();
            System.exit(0);
        }
        DB.out.println("a = "+DB.a);
        DB.out.println("b = "+DB.b);
        DB.out.println("c = "+DB.c);
        DB.out.println("dimension (d) = "+DB.dimension);
    }
    public static void Option2() {
        DB.a = (int)Math.abs(Math.random()%51)+10*Math.abs(Math.random());
        DB.b = (int)Math.abs(Math.random()%51)+10*Math.abs(Math.random());
        DB.c = (int)Math.abs(Math.random()%51)+10*Math.abs(Math.random());
        DB.dimension = (int) (Math.abs(10*Math.random()%51)+10*Math.abs(Math.random())) + 10;
        System.out.println("a = "+DB.a);
        System.out.println("b = "+DB.b);
        System.out.println("c = "+DB.c);
        System.out.println("dimension (d) = "+DB.dimension);
    }
}
