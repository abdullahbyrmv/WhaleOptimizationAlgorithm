import java.util.Arrays;

/***
    Function manages all the needed formulas and measures the time spent for giving the output.
    There are 5 main functions:
        -   Ackley Function
        -   Rastrigin Function
        -   Rosenbrock Function
        -   Schwefel Function
        -   Optimizer Function (Our solution to optimization)
 ***/
public final class Function {
    public static double ACKLEY() {
        DB.left = -32.768;
        DB.right = 32.768;
        DB.c = DB.c * Math.PI;
        for(int i=0;i<DB.dimension;i++) {
            double val = (Math.random() * (DB.right - DB.left + 1)) + DB.left;
            DB.x[i]=val;
            DB.ratio[i]=((DB.x[i]-DB.left) / (DB.right-DB.left));
        }
        double ans = 0;
        double s1 = 0;
        double s2 = 0;
        for(int i=0;i<DB.dimension;i++) {
            s1 += Math.pow(DB.x[i],2);
            s2 += Math.cos(DB.c*DB.x[i]);
        }
        ans = -DB.a * Math.exp(-DB.b*Math.sqrt(s1*1.0/DB.dimension))-Math.exp(s2*1.0/DB.dimension)+DB.a+Math.exp(1.0);
        if(ans<DB.limit)
            ans=0;
        return ans;
    }
    public static double RASTRIGIN() {
        DB.left = -5.12;
        DB.right = 5.12;
        for(int i=0;i<DB.dimension;i++) {
            DB.x[i]=(DB.ratio[i]*(DB.right-DB.left)+DB.left);
        }
        double s1 = 0;
        double ans = 10*DB.dimension;
        for(int i=0;i<DB.dimension;i++) {
            s1 += Math.pow(DB.x[i],2) - 10*Math.cos(2*Math.PI*DB.x[i]);
        }
        ans+=s1;
        if(ans<DB.limit)
            ans=0;
        return ans;
    }
    public static double ROSENBROCK() {
        DB.left = -5;
        DB.right = 10;
        for(int i=0;i<DB.dimension;i++) {
            DB.x[i]=(DB.ratio[i]*(DB.right-DB.left)+DB.left);
        }
        double sum = 0;
        for(int i=0;i<DB.dimension-1;i++) {
            sum+=(50*Math.pow(DB.x[i+1]-Math.pow(DB.x[i],2),2)+Math.pow(DB.x[i]-1,2));
        }
        if(sum<DB.limit)
            sum=0;
        return sum;
    }
    public static double SCHWEFEL() {
        DB.left = -500;
        DB.right = 500;
        for(int i=0;i<DB.dimension;i++) {
            DB.x[i]=(DB.ratio[i]*(DB.right-DB.left)+DB.left);
        }
        double s1 = 0;
        double ans = 418.9829*DB.dimension;
        for(int i=0;i<DB.dimension;i++) {
            s1 += DB.x[i] * Math.sin(Math.sqrt(Math.abs(DB.x[i])));
        }
        ans-=s1;
        if(ans<DB.limit)
            ans=0;
        return ans;
    }
    public static void Optimizer() throws Exception {
        String bigSpace = "\t\t";
        System.out.println("Run #\t\tACKLEY\t\t\t\t\t\t\t\tRASTRIGIN\t\t\t\t\t\t    ROSENBROCK\t\t\t\t\t\t\tSCHWEFEL");
        DB.out.println("Run #\t\tACKLEY\t\t\t\t\t\t\t\tRASTRIGIN\t\t\t\t\t\t    ROSENBROCK\t\t\t\t\t\t\tSCHWEFEL");
        long before = System.currentTimeMillis();
        double min1=Double.MAX_VALUE,min2=min1,min3=min1,min4=min1;
        for(int run = 0; run < DB.countOfRuns; run++) {
            DB.a = (int)Math.abs(Math.random()%51)+10*Math.abs(Math.random());
            DB.b = (int)Math.abs(Math.random()%51)+10*Math.abs(Math.random());
            DB.c = (int)Math.abs(Math.random()%51)+10*Math.abs(Math.random());
            DB.dimension = (int) (Math.abs(10*Math.random()%51)+10*Math.abs(Math.random())) + 10;
            DB.x = new double[DB.dimension];
            DB.ratio = new double[DB.dimension];
            double s1 = Function.ACKLEY(),s2 = Function.RASTRIGIN(),s3 = Function.ROSENBROCK(),s4 = Function.SCHWEFEL();
            min1 = Math.min(min1,s1);
            min2 = Math.min(min2,s2);
            min3 = Math.min(min3,s3);
            min4 = Math.min(min4,s4);
            System.out.println(String.format("%07d.\t%030.16f\t\t%030.16f\t\t%030.16f\t\t%030.16f",run+1,s1,s2,s3,s4));
            DB.out.println(String.format("%07d.\t%030.16f\t\t%030.16f\t\t%030.16f\t\t%030.16f",run+1,s1,s2,s3,s4));
        }
        System.out.println();
        System.out.println("\t\t----------------------------------------------------------------Minimum Values--------------------------------------------------------");
        System.out.println("     \t\tACKLEY\t\t\t\t\t\t\t\tRASTRIGIN\t\t\t\t\t\t    ROSENBROCK\t\t\t\t\t\t\tSCHWEFEL");
        System.out.println(String.format("        \t%030.16f\t\t%030.16f\t\t%030.16f\t\t%030.16f",min1,min2,min3,min4));
        DB.out.println();
        DB.out.println("\t\t----------------------------------------------------------------Minimum Values--------------------------------------------------------");
        DB.out.println("     \t\tACKLEY\t\t\t\t\t\t\t\tRASTRIGIN\t\t\t\t\t\t    ROSENBROCK\t\t\t\t\t\t\tSCHWEFEL");
        DB.out.println(String.format("        \t%030.16f\t\t%030.16f\t\t%030.16f\t\t%030.16f",min1,min2,min3,min4));
        double[] arr = new double[4];
        String minimum = "Ackley";
        arr[0]=min1;
        arr[1]=min2;
        arr[2]=min3;
        arr[3]=min4;
        Arrays.sort(arr);
        if(arr[0]==min2) {
            minimum = "Rastrigin";
        }
        if(arr[0]==min3) {
            minimum = "Rosenbrock";
        }
        if(arr[0]==min4) {
            minimum = "Schwefel";
        }
        System.out.println();
        System.out.printf("\t\t\t\t\t\t\t\t\t\t\t\t\t\t\tThe smallest value: %030.16f\n\t\t\t\t\t\t\t\t\t\t\t\t\t\t\tFormula: %s\n",arr[0],minimum);
        DB.out.printf("\t\t\t\t\t\t\t\t\t\t\t\t\t\t\tThe smallest value: %030.16f\n\t\t\t\t\t\t\t\t\t\t\t\t\t\t\tFormula: %s\n",arr[0],minimum);
        double mean = (min1+min2+min3+min4)/4.0;
        double standardDeviation = Math.sqrt((Math.pow(min1-mean,2)+Math.pow(min2-mean,2)+Math.pow(min3-mean,2)+Math.pow(min4-mean,2))/4.0);
        System.out.println();
        System.out.println("\t\t-----------------------------------------------------------------Global Mean----------------------------------------------------------");
        System.out.printf("%90.16f\n",mean);
        System.out.println();
        System.out.println("\t\t--------------------------------------------------------------Standard Deviation------------------------------------------------------");
        System.out.printf("%90.16f\n",standardDeviation);
        DB.out.println();
        DB.out.println("\t\t-----------------------------------------------------------------Global Mean----------------------------------------------------------");
        DB.out.printf("%90.16f\n",mean);
        DB.out.println();
        DB.out.println("\t\t--------------------------------------------------------------Standard Deviation------------------------------------------------------");
        DB.out.printf("%90.16f\n",standardDeviation);
        long after = System.currentTimeMillis();
        long duration = after - before;
        System.out.println();
        System.out.println("\t\t--------------------------------------------------------------------Time--------------------------------------------------------------");
        System.out.printf("\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t   %07d milliseconds\n",duration);
        DB.out.println();
        DB.out.println("\t\t--------------------------------------------------------------------Time--------------------------------------------------------------");
        DB.out.printf("\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t    %07d milliseconds\n",duration);

        long memoryUsage = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();
        System.out.println("\t\t-------------------------------------------------------------------Memory-------------------------------------------------------------");
        System.out.printf("\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t      %09d bytes\n",memoryUsage);
        DB.out.println("\t\t-------------------------------------------------------------------Memory-------------------------------------------------------------");
        DB.out.printf("\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t      %09d bytes\n",memoryUsage);
        DB.dom.println("x[i]  \t\tACKLEY\t\t\t\t\t\t\t\tRASTRIGIN\t\t\t\t\t\t    ROSENBROCK\t\t\t\t\t\t\tSCHWEFEL");
        for(int i=0;i<DB.dimension;i++) {
            DB.dom.println(String.format("%07d.\t%030.16f\t\t%030.16f\t\t%030.16f\t\t%030.16f",i+1,DB.x[i],(DB.ratio[i]*(5.12-(-5.12))+(-5.12)),(DB.ratio[i]*(10-(-5))+(-5)),(DB.ratio[i]*(500-(-500))+(-500))));
        }
    }
}
