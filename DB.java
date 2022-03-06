import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;
/***
    DB saves all the input and output data.
    Each time user runs a new program (new test), program will create a new folder in "History" folder which
    can be used for reaching x values and outputs anytime.
 ***/
public final class DB {
    public static final double limit = 1e-16;
    public static double left, right;
    public static double a,b,c;
    public static int dimension, countOfRuns;
    public static double[] x;
    public static double[] ratio;
    public static long POPULATION_ID;
    public static PrintWriter out;
    public static PrintWriter dom;
    public static void activate() throws IOException {


        File confile = new File("src/POPULATIONID.txt");
        Scanner con = new Scanner(confile);
        POPULATION_ID = con.nextLong() + 1;
        PrintWriter outx = new PrintWriter(confile);
        outx.println(POPULATION_ID);

        Path hist = Paths.get("History/Population "+POPULATION_ID);
        Files.createDirectories(hist);
        File outfile1 = new File(String.valueOf(hist.toAbsolutePath())+"/output "+POPULATION_ID+".txt");
        File outfile2 = new File(String.valueOf(hist.toAbsolutePath())+"/domain "+POPULATION_ID+" values.txt");
        if(!outfile1.exists())
            outfile1.createNewFile();
        if(!outfile2.exists())
            outfile2.createNewFile();

        out = new PrintWriter(outfile1);
        dom = new PrintWriter(outfile2);
        outx.close();
        con.close();
    }
    public static void save() throws Exception {
        out.close();
        dom.close();
    }
}
