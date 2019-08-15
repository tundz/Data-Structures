package Trees;

import java.util.*;
import java.io.*;

public class tree_orders {
    class FastScanner {
        StringTokenizer tok = new StringTokenizer("");
        BufferedReader in;

        FastScanner() {
            in = new BufferedReader(new InputStreamReader(System.in));
        }

        String next() throws IOException {
            while (!tok.hasMoreElements())
                tok = new StringTokenizer(in.readLine());
            return tok.nextToken();
        }
    
        int nextInt() throws IOException {
            return Integer.parseInt(next());
        }
    }

    public class TreeOrders {
        int n;
        int[] key, left, right;
        
        void read() throws IOException {
            FastScanner in = new FastScanner();
            n = in.nextInt();
            key = new int[n];
            left = new int[n];
            right = new int[n];
            for (int i = 0; i < n; i++) { 
                key[i] = in.nextInt();
                left[i] = in.nextInt();
                right[i] = in.nextInt();
            }
        }

        List<Integer> inOrder() {
            ArrayList<Integer> result = new ArrayList<Integer>();         
            return buildInOrder(0, result);
        }
        private List<Integer>  buildInOrder(int i, List<Integer> result)  {
             if (left[i] != -1)
                 buildInOrder(left[i], result);
             result.add(key[i]);
             if (right[i] != -1)
                 buildInOrder(right[i], result);
             return result;
        }

        List<Integer> preOrder() {
            ArrayList<Integer> result = new ArrayList<Integer>();
           return  buildPreOrder(0, result);
        }
        private List<Integer>  buildPreOrder(int i, List<Integer> result)  {
            result.add(key[i]);
            if (left[i] != -1)
                buildPreOrder(left[i], result);
            if (right[i] != -1)
                buildPreOrder(right[i], result);
            return result;
       }

        List<Integer> postOrder() {
            ArrayList<Integer> result = new ArrayList<Integer>();
            return buildPostOrder(0, result);
        }
        
        private List<Integer>  buildPostOrder(int i, List<Integer> result)  {
            if (left[i] != -1)
                buildPostOrder(left[i], result);
            if (right[i] != -1)
                buildPostOrder(right[i], result);
            result.add(key[i]);
            return result;
       }
    }

    static public void main(String[] args) throws IOException {
            new Thread(null, new Runnable() {
                    public void run() {
                        try {
                            new tree_orders().run();
                        } catch (IOException e) {
                        }
                    }
                }, "1", 1 << 26).start();
    }

    public void print(List<Integer> x) {
        for (Integer a : x) {
            System.out.print(a + " ");
        }
        System.out.println();
    }

    public void run() throws IOException {
        TreeOrders tree = new TreeOrders();
        tree.read();
        print(tree.inOrder());
        print(tree.preOrder());
        print(tree.postOrder());
    }
}
