import java.util.Scanner;
/***
    "Heart" of our code. Activates the DB (Database) and then starts the process.
     At the end of the process, DB saves all the data into the corresponding files.
 ***/
public final class Main {
    public static void main(String[] args) throws Exception {
        System.out.print("Please write the count of populations: ");
        Scanner con = new Scanner(System.in);
        int countOfPopulation = con.nextInt();
        if(countOfPopulation <= 0) {
            System.out.println("Count of populations must be positive integer.");
            System.exit(0);
        }
        for(int i=1;i<=countOfPopulation;i++) {
            DB.activate();
            ControlPanel.start();
            DB.save();
            System.out.println();
            DB.out.println();
        }
    }
}
