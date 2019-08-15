package Week1;

import java.util.*;
import java.io.*;

public class tree_height {
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

    public class TreeHeight {
        int n;
        int parent[];
        Treenode[] nodes;
        Treenode root = null;
        
        void read() throws IOException {
            FastScanner in = new FastScanner();
            n = in.nextInt();
            nodes = new Treenode[n];
            
            for (int i = 0; i < n; i++) {
                nodes[i] = new Treenode(in.nextInt());
            }
            for (int i = 0; i < n; i++)  {
                if(nodes[i].parent == -1)
                    root = nodes[i];
                else
                    nodes[nodes[i].parent].addChild(nodes[i]);
            }
            
        }
        int computeHeightBruteForce() {
            int maxHeight = 0;
            for (int vertex = 0; vertex < n; vertex++) {
                int height = 0;
                for (int i = vertex; i != -1; i = parent[i])
                    height++;
                maxHeight = Math.max(maxHeight, height);
            }
            return maxHeight;
        }
        int computeHeight() {
             return computeTreeheight(root);
        }
        
        
        int computeTreeheight(Treenode node)  {
            if (node.children.isEmpty())
                return 1;
            int maxheight = 0;
            for (int i = 0; i < node.children.size(); i++) {
                maxheight = Math.max(maxheight, computeTreeheight(node.children.get(i)));
            }
            return 1 + maxheight;
        }
    }

    static public void main(String[] args) throws IOException {
            new Thread(null, new Runnable() {
                    public void run() {
                        try {
                            new tree_height().run();
                        } catch (IOException e) {
                        }
                    }
                }, "1", 1 << 26).start();
    }
    public void run() throws IOException {
        TreeHeight tree = new TreeHeight();
        tree.read();
        System.out.println(tree.computeHeight());
    }
}
 
class Treenode {
     int parent;
     List<Treenode> children;
     
     Treenode(int parent) {
         this.parent = parent;
         children = new ArrayList<Treenode>();
     }
     
     void addChild(Treenode child)  {
         children.add(child);
     }
}
