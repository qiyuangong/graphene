/*************************************************************************
 *  Name:         Kevin Wayne
 *  Login:        wayne
 *
 *  Compilation:  javac LR.java
 *  Execution:    java LR < input.txt
 *  
 *  Simpler linear regression data type.
 *  Reads a sequence of observation pairs from standard input,
 *  computes the best-fit line, and prints out the observation
 *  pairs and the predicted values.
 *
 *  % more lr4.txt
 *  4
 *   20.0  91.0
 *   40.0  83.0
 *   60.0  68.0
 *   80.0  50.0
 *
 *  % java LR < lr4.txt
 *  y = -0.69 x + 107.50
 *  20.00  91.00  93.70
 *  40.00  83.00  79.90
 *  60.00  68.00  66.10
 *  80.00  50.00  52.30
 *
 *************************************************************************/


public class LR {
    private final double xbar, ybar;   // mean of x- and y-values
    private double sxx, sxy;           // intermediate statistics
    private final int N;               // number of observation pairs

    // linear regression with observations pairs (x[i], y[i])
    public LR(double[] x, double[] y) {
        if (x.length != y.length) {
            throw new RuntimeException("dimensions don't agree");
        }
        N = x.length;

        // first pass: compute mean x- and y-values
        double sumx = 0.0, sumy = 0.0;
        for (int i = 0; i < N; i++) sumx += x[i];
        for (int i = 0; i < N; i++) sumy += y[i];
        xbar = sumx / N;
        ybar = sumy / N;

        // second pass: compute sxx and sxy
        for (int i = 0; i < N; i++) {
            sxx += (x[i] - xbar) * (x[i] - xbar);
            sxy += (x[i] - xbar) * (y[i] - ybar);
        }
    }

    // slope of best-fit line
    public double slope() {
        return sxy / sxx;
    }

    // y-intercept of best-fit line
    public double intercept() {
        return meany() - slope() * meanx();
    }

    // mean of x- and y-values
    public double meanx()     { return xbar; }
    public double meany()     { return ybar; }

    // estimated response variable, given predictor variable x0
    public double predict(double x0) {
        return slope() * x0 + intercept();
    }

    public static void main(String[] args) {

        // read in input
        int N = StdIn.readInt();
        double[] x = new double[N];
        double[] y = new double[N];
        for (int i = 0; i < N; i++) {
            x[i] = StdIn.readDouble();
            y[i] = StdIn.readDouble();
        }

        // compute best-fit line             
        LR lr = new LR(x, y);
        double a = lr.slope();
        double b = lr.intercept();
        StdOut.printf("y = %.2f x + %.2f\n", a, b); 

        // print observation pairs and predicted values
        for (int i = 0; i < N; i++) {
            StdOut.printf("%6.2f %6.2f %6.2f\n", x[i], y[i], lr.predict(x[i]));
        }
    }
}

