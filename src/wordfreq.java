import org.apache.commons.io.FilenameUtils;
import java.io.*;
import java.util.*;
import java.lang.*; 

public class wordfreq {
    //******************************************************************************************//
	public String readFile(String filename) throws FileNotFoundException, IOException {
    	String book = "";
    	String line;
        BufferedReader textReader = new BufferedReader(new FileReader(filename));
        while (textReader.ready()) {
            line = textReader.readLine();
            book += line + " "; //Wouldn't Split Lines which kept Mistakes.
        }
        textReader.close();
        return book;
    }
    //******************************************************************************************//
    // PART 2 - TASK 1: given a 2D array A of ints, return a 1D array B of as many elements as  //
    // A has columns, and where each element at index i of B is the sum of all elements of A    //
    // in column at index i                                                                     //
    //******************************************************************************************//
    // PART 2 - TASK 2: given a 2D array A of ints and a row number r                           //
    // return the index of the smallest element of this row at index r                          //
    // return -1 if row r is not a valid row number                                             //
    //******************************************************************************************//
    // PART 2 - TASK 3: given a 2D array A of ints and a row number r                           //
    // return the index of the largest element of this row at index r                           //
    // return -1 if row r is not a valid row number                                             //
    //******************************************************************************************//
    // PART 2 - TASK 4: given a 2D array A of ints, a 1D array S of strings, and a row number r //
    // return a new array that is S sorted in increasing order of the elements of row r of A    //
    // So for instance, if A[r] is row: {10, 3, 5} and S is {"I", "she", "we"}                  //
    // you will return {"she", "we", "I"}                                                       //
    //******************************************************************************************//
    public int[] addColumnwise(int[][] array) {
        int[] result = new int[array[0].length];
        for(int i = 0; i < result.length; i++){
            for(int j = 0; j < array.length; j++){
                result[i] += array[j][i];
            }
        }
        return result;
    }
    public int minIndexAtRow(int[][] array, int row) {
        if(row > -1 && array.length > row){
            int index = array[row][0];
            for(int i = 0; i < array[row].length; i++){
                if(index > array[row][i]){
                    index = array[row][i];
                }
            }
            return index;
        }
        return -1;
    }
    public int maxIndexAtRow(int[][] array, int row) {
        if(row > -1 && array.length > row){
            int index = array[row][0];
            for(int i = 0; i < array[row].length; i++){
                if(index < array[row][i]){
                    index = array[row][i];
                }
            }
            return index;
        }
        return -1;
    }
    public String[] orderWordsOfInterest(int[][] array, int row, String[] wordsOfInterest) {
        if(row > -1 && array.length > row){
            String[][] result = {Arrays.copyOf(wordsOfInterest,wordsOfInterest.length),Arrays.copyOf(Arrays.toString(array[row]).replaceAll("[\\[\\]]", "").split(", "),array[row].length)};
            for(int i = 0; i < result[1].length; i++){
                for(int j = i + 1; j < result[1].length; j++){
                    if (Integer.parseInt(result[1][i]) > Integer.parseInt(result[1][j])){
                        result[0] = swapValues(result[0],i,j);
                        result[1] = swapValues(result[1],i,j);
                    }
                }
            }    
            return result[0];
        }
        return new String[0];
    }
    //******************************************************************************************//
    public static String[] convertBook(String book) {
    //Split by Multiple Whitespace Characters OR Split by Multiple Non-Alphanumeric Characters OR Underscores
        String[] words = book.replaceAll("\\[.*?\\]|[IVX]--|[\";,.!?()*_]", "").replaceAll("-", " ").split("\\s+");
        return words;
    }
    public static String[] swapValues(String[] array, int i, int j) {
    //Split by Multiple Whitespace Characters OR Split by Multiple Non-Alphanumeric Characters OR Underscores
        String[] values = Arrays.copyOf(array,array.length);
        String value = values[i];
        values[i] = values[j];
        values[j] = value;
        return values;
    }

    //******************************************************************************************//
    // The main method should be able to run without errors once your methods are completed.    //
    // You may change the main method as you see fit. It will not be graded.                    //
    //                                                                                          //
    // However, I do encourage you interpret the results you get from running it with methods.  //
    //******************************************************************************************//
	public static void main(String[] args) throws FileNotFoundException, IOException {
        // Create Instance
        wordfreq instance = new wordfreq(); 
        // Create List of Objects
        List<bookdata> books = new ArrayList<bookdata>();
        // Grab Files
        File folder = new File("src/books/");
        File[] listOfFiles = folder.listFiles();
        // Create Objects 
        for (int i = 0; i < listOfFiles.length; i++) {
            if (listOfFiles[i].isFile()) {
                if ("txt".equals(FilenameUtils.getExtension(listOfFiles[i].getName()))){
                    String file_name = "src/books/" + listOfFiles[i].getName();
                    books.add(new bookdata(listOfFiles[i].getName(),convertBook(instance.readFile(file_name))));
                }
            }
        }

        List<HashMap<String,Integer>> wordstats = instance.bookAnalysis(books);  

    }
}