import java.io.IOException;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

public class Counter {
    String statement = Utility.takeInput();
    Map <String,Integer> map = new HashMap<>();
    public Counter() throws IOException, IllegalAccessException {
    }
    public void NoOfWordsCount() {
        String stat = statement.toLowerCase(Locale.ROOT);
        int totalWords = 0;
        String [] stat1 = stat.split("\n");
        for (String s : stat1) {
            String[] arr = s.split("[^\\w']");
            for (String s1 : arr) {
                map.put(s1, map.getOrDefault(s1, 0) + 1);
            }
            totalWords += arr.length;
        }
        int UniqueWords = map.size();
        System.out.println("Total count of words are : " +  totalWords);
        System.out.println("Unique count of words are : " +  UniqueWords);
        System.out.println("Displaying frequency of each word :");
        for(Map.Entry<String,Integer> val : map.entrySet()){
            System.out.println(val.getKey() + " " + "-" + " "  + val.getValue());
        }
    }
}
