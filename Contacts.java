import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution {

    static class Trie {
        Node root = new Node();
        
        public void add(String s){
            // add a new contact to the list.
            Node currentNode = root;
            for (int i = 0; i < s.length(); ++i){
                char c = s.charAt(i);
                if(currentNode.hasNode(c)){
                    currentNode.getNode(c).increment();
                } else {
                    currentNode.addNode(c);
                }
                currentNode = currentNode.getNode(c);
            }
        }
                
        public int count(String c){
            // return the number of matching entries.
            return root.count(c, root);
        }
    }
    
    static class Node {
        private HashMap<Character, Node> chars;
        private int word_count = 0; // keep a count of all matching patterns.
        
        public Node(){
            ++word_count;
            chars = new HashMap<>();
        }
        
        public void increment(){
            // increment count of the number of words which match this pattern.
            ++word_count;
        }
        
        public void addNode(char c){
            chars.put(c, new Node());
        }
        
        public Node getNode(char c){
            return chars.get(c);
        }
        
        public boolean hasNode(char c){
            return chars.get(c) != null;
        }
        
        int count(String s, Node n){
            if (s.length() == 0){
                return n.word_count;
            } else {
                char ch = s.charAt(0);
                s = s.substring(1);
                if (n.hasNode(ch))
                    return this.count(s, n.getNode(ch));
                else
                    return 0; // string does not match.
            }
        }
    }
    
    
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        
        Trie contacts = new Trie();
        
        for(int a0 = 0; a0 < n; a0++){
            String op = in.next();
            String contact = in.next();
            
            if (op.equals("add")){
                contacts.add(contact);
            } else if (op.equals("find")){
                System.out.println(contacts.count(contact));
            }
        }
    }
}
