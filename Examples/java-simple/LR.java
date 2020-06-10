import java.util.Random;

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
	Random rand = new Random();
        int N = rand.nextInt(10);
        System.out.printf("N = %d\n", N); 

        double[] x = new double[N];
        double[] y = new double[N];
        for (int i = 0; i < N; i++) {
            x[i] = rand.nextInt(100) * 1.0;
            y[i] = rand.nextInt(100) * 1.0;
        }

        // compute best-fit line             
        LR lr = new LR(x, y);
        double a = lr.slope();
        double b = lr.intercept();
        System.out.printf("y = %.2f x + %.2f\n", a, b); 

        // print observation pairs and predicted values
        for (int i = 0; i < N; i++) {
            System.out.printf("%6.2f %6.2f %6.2f\n", x[i], y[i], lr.predict(x[i]));
        }
    }
}

