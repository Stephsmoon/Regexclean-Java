import java.io.*;
import java.util.*;
import java.lang.*; 

public class bookdata {
    //******************************************************************************************//
    // Attributes                                                                               //
    //******************************************************************************************//

	private String name;
	private String[] words; 
	private HashMap<String,Integer> stats;

    //******************************************************************************************//
    // Constructors 
    //******************************************************************************************//

	public bookdata() {}
	public bookdata(String name, String[] words){
		this.name = name;
		this.words = words;
	}

    //******************************************************************************************//
    // Modifiers 
    //******************************************************************************************//
	
	public void setname(String name) {
		this.name = name;
	}
	public void setwords(String[] words) {
		this.words = words;
	}

    //******************************************************************************************//
    // Accessors                                                                                //
    //******************************************************************************************//

	public String getname() {
		return name;
	}
	public String[] getwords() {
		return words;
	}

    //******************************************************************************************//
    // Other Methods                                                                            //
    //******************************************************************************************//	
    public HashMap<String,Integer> numWords(String[] words) {
        HashMap<String,Integer> stats = new HashMap<String,Integer>();
        // Traverse each word in book 
        for(int i = 0; i < words.length; i++){ 
            // Checks whether contraction
            //if(words[i].toLowerCase().contains("\'") && words[i].charAt(0) != '\''){     
            // Check if word exist in Hashmap
            if(!stats.containsKey(words[i].toLowerCase())){
                stats.put(words[i].toLowerCase(),1);
            // When word does not exist in the Hashmap
            } else {
                int increase = stats.get(words[i].toLowerCase()) + 1;
                stats.put(words[i].toLowerCase(),increase);
            } 
        }    
        return stats;
    }
    public List<HashMap<String,Integer>> bookAnalysis(List<String[]> books) {
        List<HashMap<String,Integer>> wordstats = new ArrayList<HashMap<String,Integer>>();
        for(int i = 0; i < books.size(); i++){
            wordstats.add(numWords(books.get(i)));
        }
        return wordstats;
    }
}