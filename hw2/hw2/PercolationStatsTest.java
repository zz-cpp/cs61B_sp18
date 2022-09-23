package hw2;

import org.junit.Test;

public class PercolationStatsTest {

    PercolationStats test;

    @Test
    public void MeanTest() {
        test = new PercolationStats(20,10,new PercolationFactory());

        double stuMean = test.mean();
        System.out.println("test finish");
    }

    @Test
    public void TestStddev () {
        test = new PercolationStats(20,10,new PercolationFactory());

        double stustddev = test.stddev();
        System.out.println("test finish");

    }

}
