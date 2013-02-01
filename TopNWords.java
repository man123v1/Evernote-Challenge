import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class TopNWords {
	private HashMap<String, Integer> wordCount = null;
	private BufferedReader input = null;
	private BufferedWriter output = null;

	public static void main(String[] args) throws Exception {
		new TopNWords().findTopNWords();
	}
	
	public void findTopNWords() throws Exception
	{
		wordCount = new HashMap<String, Integer>();
		input = new BufferedReader(new InputStreamReader(System.in));
		output = new BufferedWriter(new OutputStreamWriter(System.out));

		int wordsInputSize = Integer.parseInt(input.readLine());

		while (wordsInputSize > 0) {
			String newWord = input.readLine();
			if (wordCount.get(newWord) == null) {
				wordCount.put(newWord, 1);
			} else {
				int existingCount = wordCount.get(newWord);
				existingCount++;
				wordCount.put(newWord, existingCount);
			}
			wordsInputSize--;
		}
		
		List<Map.Entry<String, Integer>> list = new ArrayList<Map.Entry<String, Integer>>(
				wordCount.entrySet());
		Collections.sort(list, new ValueThenKeyComparator<String, Integer>());
		//System.out.println(list);
		int topNSize = Integer.parseInt(input.readLine());
		
		Iterator<Map.Entry<String, Integer>> itr = list.iterator();
		while(topNSize>0 && itr.hasNext())
		{
			output.write(itr.next().getKey()+"\n");
			topNSize--;
		}
		
		input.close();
		output.close();
	}

	class ValueThenKeyComparator<K extends Comparable<? super K>, V extends Comparable<? super V>>
			implements Comparator<Map.Entry<K, V>> {

		public int compare(Map.Entry<K, V> b, Map.Entry<K, V> a) {
			int cmp1 = a.getValue().compareTo(b.getValue());
			if (cmp1 != 0) {
				return cmp1;
			} else {
				return b.getKey().compareTo(a.getKey());
			}
		}

	}
}
