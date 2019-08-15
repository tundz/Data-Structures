package Week3;

import java.io.*;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class JobQueue {
    private int numWorkers;
    private int[] jobs;

    private int[] assignedWorker;
    private long[] startTime;

    private FastScanner in;
    private PrintWriter out;

    public static void main(String[] args) throws IOException {
        new JobQueue().solve();
    }

    private void readData() throws IOException {
        numWorkers = in.nextInt();
        int m = in.nextInt();
        jobs = new int[m];
        for (int i = 0; i < m; ++i) {
            jobs[i] = in.nextInt();
        }
    }

    private void writeResponse() {
        for (int i = 0; i < jobs.length; ++i) {
            out.println(assignedWorker[i] + " " + startTime[i]);
        }
    }

    private void assignJobs() {
        // TODO: replace this code with a faster algorithm.
        Comparator<Thread> comparator = new Thread(0);
        PriorityQueue<Thread> threadOrder = new PriorityQueue<Thread>(numWorkers, comparator);
        for (int i = 0; i < numWorkers; ++i) {
             threadOrder.add(new Thread(i));
        }
        assignedWorker = new int[jobs.length];
        startTime = new long[jobs.length];
        for (int i = 0; i < jobs.length; i++) {
            int duration = jobs[i];
            Thread assignedThread = threadOrder.remove();
            assignedWorker[i] = assignedThread.index;
            startTime[i] = assignedThread.timeToCompleteTasks;
            assignedThread.incrementTimeBy(duration);
            threadOrder.add(assignedThread);
        }
    }
    private class Thread implements Comparator<Thread>{
        int index;
        long timeToCompleteTasks;
        Thread(int Index)  {
           index = Index;
           timeToCompleteTasks = 0;
        }
        
        public void incrementTimeBy(long time) {
            timeToCompleteTasks += time;
        }
        public int compare(Thread one, Thread two) {
           if (one.timeToCompleteTasks - two.timeToCompleteTasks != 0)
                 return (int) (one.timeToCompleteTasks - two.timeToCompleteTasks);
           return one.index - two.index;
        }    
    }

    public void solve() throws IOException {
        in = new FastScanner();
        out = new PrintWriter(new BufferedOutputStream(System.out));
        readData();
        assignJobs();
        writeResponse();
        out.close();
    }

    static class FastScanner {
        private BufferedReader reader;
        private StringTokenizer tokenizer;

        public FastScanner() {
            reader = new BufferedReader(new InputStreamReader(System.in));
            tokenizer = null;
        }

        public String next() throws IOException {
            while (tokenizer == null || !tokenizer.hasMoreTokens()) {
                tokenizer = new StringTokenizer(reader.readLine());
            }
            return tokenizer.nextToken();
        }

        public int nextInt() throws IOException {
            return Integer.parseInt(next());
        }
    }
}

