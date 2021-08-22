import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;

public class LZW
{
    private int dictionarySize = 256;

    public List<Integer> compress(String text) {
        if(text == null){
            return null;
        }
        List<Integer> result = new ArrayList<Integer>();
        Map<String, Integer> dictionary = new HashMap<String, Integer>();
        for(int i = 0; i < dictionarySize; i++){
            dictionary.put("" + (char) i, i);
        }

        String prev = "";
        for(char c : text.toCharArray()) {
            String combined = prev + c;
            if(dictionary.containsKey(combined)){
                prev = combined;
            } else{
                result.add(dictionary.get(prev));
                dictionary.put(combined, dictionarySize++);
                prev = "" + c;
            }
        }

        if(!prev.equals("")){
            result.add(dictionary.get(prev));
        }

        return result;
    }
}
