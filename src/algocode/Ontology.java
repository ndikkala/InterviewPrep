package algocode;

import java.io.*;
import java.util.*;
//import java.util.Map.Entry;


public class Ontology {
	static long n = 0;
	static long m = 0;
	static long k = 0;
	static HashMap<String, String> mapTopicIndex = new HashMap<String, String>();
	static HashMap<String, PrefixTrie> questions = new HashMap<String, PrefixTrie>();
	static long []count;

	public static void main(String[] args) {
		try {
			process();
		} catch (Exception e) {
			e.printStackTrace();
			
		}
	}

	static void process() throws Exception {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(in.readLine());
		int concatIndex = 0;
		String index = "";
		String lastIndex = "";
		String[] secondLine = in.readLine().split(" ");
		for (String s : secondLine) {
			if (!s.equals("(") && !s.equals(")")) {
				concatIndex++;
				if(!index.contains(":")){
					index = String.valueOf(concatIndex);
				}
				else{
					index += concatIndex;
				}
				lastIndex = index;
				/*Two way mapping, as topic and index have one-to-one relation.*/
				mapTopicIndex.put(s, index);
				mapTopicIndex.put(index, s);
				if(index.contains(":")){
					index = index.substring(0, index.lastIndexOf(":")+1);
				}
			}
			if (s.equals("(")) {
				/*reset concat index, new level of children is starting*/
				concatIndex = 0; 
				/*Start of a new level of children*/
				index = lastIndex+ ":";
			}
			if (s.equals(")")) {
				if (index.contains(":")){
					// A level of children ended, go one level up to come to sibling level
					index = index.substring(0, index.lastIndexOf(":"));
					if (index.contains(":")){
						//Increment the sibling index
						concatIndex = Integer.parseInt(index.substring(index.lastIndexOf(":")+1, index.length())); 
					}
					else{
						concatIndex = Integer.parseInt(index);
					}
				}
				else{
					concatIndex = Integer.parseInt(index);
				}
				if (index.contains(":")) {
					//Go to parent level
					index = index.substring(0, index.lastIndexOf(":")); 
					index += ":";
				}
			}
		}
		
		/*for (Map.Entry<String, String> entry : mapTopicIndex.entrySet()) {
			 System.out.println(entry.getKey()+" and "+entry.getValue()); 
		}*/
		
		m = Integer.parseInt(in.readLine());
		String[] question = new String[2];
		String questionIndex = "";
		String parentIndex = "";
		String parentTopic = "";
		for (int i = 0; i < m; i++) {
			question = in.readLine().split(": ");
			// A question can be associated with only one topic, i.e., question
			// strings are unique. For each topic store the question strings as a trie
			if (!questions.containsKey(question[0])) {
				// Topic is not present in hashmap yet
				PrefixTrie qpt = new PrefixTrie();
				qpt.insert(question[1]);
				questions.put(question[0], qpt);
			}
			else{
				// Topic present in hashmap
				PrefixTrie qpt = questions.get(question[0]);
				qpt.insert(question[1]);
				questions.put(question[0], qpt);
			}
			//Add the question under parent topics too
			questionIndex = mapTopicIndex.get(question[0]); //Gets the index of the particular query topic
			parentIndex = questionIndex;
			while(parentIndex.contains(":")){
				parentIndex = parentIndex.substring(0, parentIndex.lastIndexOf(":"));
				parentTopic = mapTopicIndex.get(parentIndex);
				if (!questions.containsKey(parentTopic)) {
					// Topic is not present in hashmap yet
					PrefixTrie qpt = new PrefixTrie();
					qpt.insert(question[1]);
					questions.put(parentTopic, qpt);
				}
				else{
					// Topic present in hashmap
					PrefixTrie qpt = questions.get(parentTopic);
					qpt.insert(question[1]);
					questions.put(parentTopic, qpt);
				}
				
			}
			
		}
		
		
		 /*for (Entry<String, PrefixTrie> entry : questions.entrySet()) {
		  System.out.println(entry.getKey()+" Tree: ");
		  PrefixTrie val = entry.getValue();
		  TrieNode root =  val.root;
		  HashMap<Character, TrieNode> children = root.children;
		  for (Entry<Character, TrieNode> child : children.entrySet()) {
		  	System.out.print(child.getKey()+ " Node is "+ child.getValue().c);
		  }
		  System.out.println();
		}*/

		k = Integer.parseInt(in.readLine());
		count = new long[(int) k];
		String[] query = new String[2];
		String queryTopic = "";
		String queryPrefix = "";
		
		for (int i = 0; i < k; i++) {
			query = in.readLine().split(" ", 2);
			//loop through each query and compute count array
			queryTopic = query[0];
			queryPrefix = query[1];
			if(questions.containsKey(queryTopic)){
				PrefixTrie compare = questions.get(queryTopic);
				long result  = compare.startsWith(queryPrefix);
				count[i] += result;
			}
			
		}
			
	
		
		for(long out:count){
			System.out.println(out);
		}
		
		 

	}



	static class PrefixTrie {
		 private TrieNode root;
		
		 
		    public PrefixTrie() {
		        root = new TrieNode();
		    }
		    /*Inserts a word into the trie.*/
		    public void insert(String word) {
		    	TreeMap<Character, TrieNode> children = root.children;
		 
		        for(int i=0; i<word.length(); i++){
		            char c = word.charAt(i);
		 
		            TrieNode t;
		            if(children.containsKey(c)){
		                    t = children.get(c);
		                    t.countNoOfQues++;
		            }else{
		                t = new TrieNode(c);
		                children.put(c, t);
		            }
		 
		            children = t.children;
		 
		            //set leaf node
		            if(i==word.length()-1){
		                t.isLeaf = true; 
		            }
		        }
		    }
		 
		    // Returns if the word is in the trie.
		    public boolean search(String word) {
		        TrieNode t = searchNode(word);
		        if(t != null && t.isLeaf) 
		            return true;
		        else
		            return false;
		    }
		 
		    // Returns the count of words in the trie that start with the prefix
		    public int startsWith(String prefix) {
		    	TrieNode result = searchNode(prefix) ;
		   
		        if(result == null) 
		            {return 0;}
		        else
		            {return result.countNoOfQues ;}
		    }
		 
		    public TrieNode searchNode(String str){
		        TreeMap<Character, TrieNode> children = root.children; 
		        TrieNode t = null;
		        for(int i=0; i<str.length(); i++){
		            char c = str.charAt(i);
		            if(children.containsKey(c)){
		                t = children.get(c);
		                children = t.children;
		            }else{
		                return null;
		            }
		        }
		 
		        return t;
		    }
		    
		
		 
	}

	static class TrieNode {
		char c;
		int countNoOfQues; //No of questions that start with this sequence of chars from the root
		TreeMap<Character, TrieNode> children = new TreeMap<Character, TrieNode>();
	    boolean isLeaf;
	 
	    public TrieNode() {}
	 
	    public TrieNode(char c){
	        this.c = c;
	        this.countNoOfQues = 1;
	    }
	}
}
