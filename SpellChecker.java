
public class SpellChecker {


	public static void main(String[] args) {
		String word = args[0];
		int threshold = Integer.parseInt(args[1]);
		String[] dictionary = readDictionary("dictionary.txt");
		String correction = spellChecker(word, threshold, dictionary);
		System.out.println(correction);

	}

	public static String tail(String str) {
		String tail = "";
		if (str.length() == 1){
			 return tail;
		}
		tail = str.substring(1);
		return tail;
	}

	public static int levenshtein(String word1, String word2) {
		int dist = 0;
		int min = 0; 
		if (word1.length() == 0){
			dist = word2.length();
		}
		else if (word2.length() == 0){
			dist = word1.length();
		}
		else if (word1.charAt(0) == word2.charAt(0)){
			dist = levenshtein(tail(word1), tail(word2));
		}
		
		else{	
			int a = levenshtein(tail(word1), word2);
			int b = levenshtein(word1, tail(word2));
			int c = levenshtein(tail(word1), tail(word2));
			min = Math.min(a, b);
			if (c < min){
				min = c;
			}
			dist = 1 + min;
		}
		return dist;
	}

	public static String[] readDictionary(String fileName) {
		String[] dictionary = new String[3000];

		In in = new In(fileName);

		for(int i = 0 ; i < dictionary.length ; i++){
			dictionary[i]= in.readLine();
		}

		return dictionary;
	}

	public static String spellChecker(String word, int threshold, String[] dictionary) {
		int min = levenshtein(word, dictionary[0]);
		int minIndex = 0;
		for(int i = 1 ; i < dictionary.length ; i++){
		if (levenshtein(word, dictionary[i]) < min){
			min = levenshtein(word, dictionary[i]);
			minIndex = i;
		}
		}
		if (min <= threshold){
			return dictionary[minIndex];
		}
		return word;
	}

}
