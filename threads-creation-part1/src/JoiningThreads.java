import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class JoiningThreads {

    public static void main(String[] args) {
        List<Long> factors = Arrays.asList(0L, 324L, 12L, 12134L, 99L, 334L);
        List<FactorialThread> threads = new ArrayList<>();

        for (long inputNumber : factors) {
            threads.add(new FactorialThread(inputNumber));
        }

        for (Thread thread : threads) {
            thread.start();
        }

//        for (Thread thread : threads) {
//            try {
//                thread.join(); // Wait for each thread to finish
//            } catch (InterruptedException e) {
//                System.out.println("Thread interrupted: " + e.getMessage());
//            }
//        }

        for (int i = 0; i < factors.size() ; i++) {
            FactorialThread factorialThread = threads.get(i);
            if (factorialThread.isFinished()) {
                System.out.println("Thread "+ factorialThread.getName()+" Factorial of " + factors.get(i) + " is " + factorialThread.getResult());
            } else {
                System.out.println("Thread "+ factorialThread.getName()+ " The calculation for " + factors.get(i) + " is still in progress");
            }
        }
    }

    private static class FactorialThread extends Thread {
        private final Long factor;
        private boolean isFinished;
        private BigInteger result;

        public FactorialThread(Long factor) {
            this.factor = factor;
            this.isFinished = false;
        }

        @Override
        public void run() {
            this.result = this.factorial(this.factor);
            this.isFinished = true; // Update the flag after computation
        }

        public BigInteger factorial(long n) {
            BigInteger result = BigInteger.ONE;
            for (long i = n; i > 0; i--) {
                result = result.multiply(BigInteger.valueOf(i));
            }
            return result;
        }

        public boolean isFinished() {
            return isFinished;
        }

        public BigInteger getResult() {
            return result;
        }
    }
}
