
package Trees;
import java.util.*;
import java.io.*;

public class is_bst {
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

    public class IsBST {
        class Node {
            int key;
            int left;
            int right;

            Node(int key, int left, int right) {
                this.left = left;
                this.right = right;
                this.key = key;
            }
        }

        int nodes;
        Node[] tree;

        void read() throws IOException {
            FastScanner in = new FastScanner();
            nodes = in.nextInt();
            tree = new Node[nodes];
            for (int i = 0; i < nodes; i++) {
                tree[i] = new Node(in.nextInt(), in.nextInt(), in.nextInt());
            }
        }
        
        boolean isBinarySearchTree() {
            if(tree.length <= 1)
                return true;
            return isBinarySearchTree(0, Integer.MIN_VALUE, Integer.MAX_VALUE);
        }
        
        private boolean isBinarySearchTree(int root, int low, int high) {
             if(root == -1) {
                  return true;
             }
             if(tree[root].key <= low || tree[root].key >= high) {
                  return false;
             }
             return isBinarySearchTree(tree[root].left, low, tree[root].key) &&
                  isBinarySearchTree(tree[root].right, tree[root].key, high);
        }
           
       /** boolean isBinarySearchTree() {
            List<Integer> inOrderTraversal = inOrder();
            for (int i = 0; i < inOrderTraversal.size() -1; i++) {
                if (inOrderTraversal.get(i) >= inOrderTraversal.get(i + 1))
                    return false;
            }
            return true;
        }
        
        List<Integer> inOrder() {
            ArrayList<Integer> result = new ArrayList<Integer>();
            if (tree.length > 0)
                buildInOrder(0, result);
            return result;
        }
        private void  buildInOrder(int i, List<Integer> result)  {
             if (tree[i].left != -1)
                 buildInOrder(tree[i].left, result);
             result.add(tree[i].key);
             if (tree[i].right != -1)
                 buildInOrder(tree[i].right, result);
        }
        */
    }

    static public void main(String[] args) throws IOException {
        new Thread(null, new Runnable() {
            public void run() {
                try {
                    new is_bst().run();
                } catch (IOException e) {
                }
            }
        }, "1", 1 << 26).start();
    }
    public void run() throws IOException {
        IsBST tree = new IsBST();
        tree.read();
        if (tree.isBinarySearchTree()) {
            System.out.println("CORRECT");
        } else {
            System.out.println("INCORRECT");
        }
    }
    
}
