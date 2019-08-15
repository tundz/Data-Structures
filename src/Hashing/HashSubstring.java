package Hashing;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class HashSubstring {

    private static FastScanner in;
    private static PrintWriter out;
    private static final long prime = 1000000007;
    private static final long multiplier = 263;

    public static void main(String[] args) throws IOException {
        in = new FastScanner();
        out = new PrintWriter(new BufferedOutputStream(System.out));
        printOccurrences(getOccurrences(readInput()));
        out.close();
    }

    private static Data readInput() throws IOException {
        String pattern = in.next();
        String text = in.next();
        return new Data(pattern, text);
    }

    private static void printOccurrences(List<Integer> ans) throws IOException {
        for (Integer cur : ans) {
            out.print(cur);
            out.print(" ");
        }
    }
    
    private static int hashFunc (String S)  {
         int hash = 0;
         for (int i = S.length() - 1; i >= 0; --i)  {
              hash =  (int) (((hash * multiplier) + S.charAt(i)) % prime);
         }
         return hash; 
    }

    private static long[] preComputeHash(String pattern, String text) {
          int n = pattern.length(), m = text.length();
          long[] hashresult = new long[m - n + 1];
          hashresult[m - n] = hashFunc(text.substring(m - n));
          long  multiplyby = 1;
          for (int i = 0; i < n; i++)
               multiplyby = (multiplyby * multiplier) % prime;
          for (int i = m - n - 1; i >= 0; --i)  {
              hashresult[i] = (int) ((((multiplier * hashresult[i + 1] + text.charAt(i) - text.charAt(i + n)* multiplyby) % prime) + prime) % prime);
          }
          return hashresult;
    }
    private static List<Integer> getOccurrences(Data input) {
        String s = input.pattern, t = input.text;
        int m = s.length(), n = t.length();
        int patternhash = hashFunc(s);
        long[] texthashes = preComputeHash (s, t);
        List<Integer> occurrences = new ArrayList<Integer>();
        for (int i = 0; i < texthashes.length; ++i) {
        if (patternhash == texthashes[i]) {
                  if(s.equals(t.substring(i, i + m)))
                     occurrences.add(i);
            }
    }
        return occurrences;
    }

    static class Data {
        String pattern;
        String text;
        public Data(String pattern, String text) {
            this.pattern = pattern;
            this.text = text;
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

