package org.khwu.ch01_thread_management.recipe03_interruption;

public class PrimeGenerator extends Thread{
    @Override
    public void run() {
        long number = 1L;
        while (true) {
            if (isPrime(number)) {
                System.out.printf("Number %d is Prime%n", number);
                if (isInterrupted()) {
                    System.out.printf("The Prime Generator has been Interrupted");
                    return;
                }
            }
            number++;
        }
    }

    private boolean isPrime(long number) {
        if (number <= 2) return true;
        for (long i = 2; i < number; i++) {
            if (number % 2 == 0) return false;
        }
        return true;
    }

    public static void main(String[] args) {
        Thread task = new PrimeGenerator();
        task.start();
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        task.interrupt();
        System.out.printf("Main: Status of the Thread: %s\n", task.getState());
        System.out.printf("Main: isInterrupted: %s\n", task.isInterrupted());
        System.out.printf("Main: isAlive: %s\n", task.isAlive());
    }
}
