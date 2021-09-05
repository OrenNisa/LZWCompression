import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;

public class LZW
{
    public static List<Integer> compress(String text) {
        if(text == null)
            return null;
        int dictionarySize = 0;
        List<Integer> result = new ArrayList<Integer>();
        Map<String, Integer> dictionary = new HashMap<String, Integer>();
//        for(int i = 0; i < dictionarySize; i++){
//            dictionary.put("" + (char) i, i);
//        }


        String prev = "";
        for(char c : text.toCharArray()) {
            String combined = prev + c;
            if(dictionary.containsKey(combined)) {
                prev = combined; }
//             else {
//                if(prev != "") {//so it doesn't put null into dictionary
//                    result.add(dictionary.get(prev));
//                    prev = "" + c; }
//                dictionary.put(combined, dictionarySize++);
//            }
            else {
                dictionary.put(combined, dictionarySize++);
                if(prev == "") {//so it doesn't put null into dictionary
                    result.add(dictionary.get(combined));
                    prev = "";
                }
                else {
                    result.add(dictionary.get(prev));
                    prev = (dictionary.containsKey(c)) ? "" + c : "";
                    //prev = "" + c;
                }

            }
        }

        if(!prev.equals(""))
            result.add(dictionary.get(prev));

        return result;
    }

    public static String decompress(List<Integer> compressed){
        if(compressed == null)
            return null;
        int dictionarySize = 0;
        StringBuilder result = new StringBuilder();
        Map<Integer, String> dictionary = new HashMap<Integer, String>();
//        for (int i=0; i<dictionarySize; i++) {
//            dictionary.put(i, "" + (char) i);
//        }
        String prev = "" + (char) (int) compressed.remove(0);
        for(int j : compressed) {
            String combined;
            if(dictionary.containsKey(j)) {
                combined  = dictionary.get(j);
            }
            else if(j == dictionarySize) {
                combined = prev + prev.charAt(0);
            }
            else {
                return "-1";
            }
            result.append(combined);
            dictionary.put(dictionarySize++, prev + combined.charAt(0));
            prev = combined;
        }
        return result.toString();
    }
}
