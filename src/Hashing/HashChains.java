package Hashing;

import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.StringTokenizer;

public class HashChains {

    private FastScanner in;
    private PrintWriter out;
    // store all strings in one list
    private List<String>[] hashTable;
    // for hash function
    private int bucketCount;
    private int prime = 1000000007;
    private int multiplier = 263;

    public static void main(String[] args) throws IOException {
        new HashChains().processQueries();
    }
    
    private int hashFunc(String s) {
        long hash = 0;
        for (int i = s.length() - 1; i >= 0; --i)
            hash = (hash * multiplier + s.charAt(i)) % prime;
        return (int)hash % bucketCount;
    }

    private Query readQuery() throws IOException {
        String type = in.next();
        if (!type.equals("check")) {
            String s = in.next();
            return new Query(type, s);
        } else {
            int ind = in.nextInt();
            return new Query(type, ind);
        }
    }

    private void writeSearchResult(boolean wasFound) {
        out.println(wasFound ? "yes" : "no");
        // Uncomment the following if you want to play with the program interactively.
        // out.flush();
    }

    private void processQuery(Query query) {
        
        switch (query.type) {
            case "add":
                String elem = query.s;
                int elementHashCode = hashFunc(elem);
                if (hashTable[elementHashCode] == null)  {
                      hashTable[elementHashCode] = new ArrayList<String>();
                      hashTable[elementHashCode].add(0,elem);
                }
                else {
                       if(!hashTable[elementHashCode].contains(elem))
                            hashTable[elementHashCode].add(0,elem);
                }
                break;
            case "del":
                String Elem = query.s;
                int elementHashcode = hashFunc(Elem);
                if(hashTable[elementHashcode] != null) {
                    hashTable[elementHashcode].remove(Elem);
                }    
                break;
            case "find":
                String ele = query.s;
                int elementhashCode = hashFunc(ele);
                if(hashTable[elementhashCode] != null)
                     writeSearchResult(hashTable[elementhashCode].contains(ele));
                else
                    writeSearchResult(false);
                break;
            case "check":
                int index = query.ind;
                    if(hashTable[index] != null)  {
                          for (String element : hashTable[index])
                              out.print(element + " ");
                    }
                out.println();
                // Uncomment the following if you want to play with the program interactively.
                // out.flush();
                break;
            default:
                throw new RuntimeException("Unknown query: " + query.type);
        }
    }

    public void processQueries() throws IOException {
        in = new FastScanner();
        out = new PrintWriter(new BufferedOutputStream(System.out));
        bucketCount = in.nextInt();
        hashTable = new List[bucketCount];
        int queryCount = in.nextInt();
        for (int i = 0; i < queryCount; ++i) {
            processQuery(readQuery());
        }
        out.close();
    }

    static class Query {
        String type;
        String s;
        int ind;

        public Query(String type, String s) {
            this.type = type;
            this.s = s;
        }

        public Query(String type, int ind) {
            this.type = type;
            this.ind = ind;
        }
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

