import java.io.*;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;

public class LZW
{
//    public static List<Integer> compress(String text) {
//        if(text == null)
//            return null;
//        int dictionarySize = 256;
//        List<Integer> result = new ArrayList<Integer>();
//        Map<String, Integer> dictionary = new HashMap<String, Integer>();
//        for(int i = 0; i < dictionarySize; i++){
//            dictionary.put("" + (char) i, i);
//        }
//
//        String prev = "";
//        for(char c : text.toCharArray()) {
////            if(!dictionary.containsKey(c))
////                dictionary.put(""+c, dictionarySize++);
//            String combined = prev + c;
//            if(dictionary.containsKey(combined)){
//                prev = combined;
//            } else{
//                result.add(dictionary.get(prev));
//                dictionary.put(combined, dictionarySize++);
//                prev = "" + c;
//            }
//        }
//
//        if(!prev.equals("")){
//            result.add(dictionary.get(prev));
//        }
//
//        return result;
//    }

    public static void compress(FileReader text, FileOutputStream fos) throws IOException {
//        if(text == null)
//            return null;
        //FileReader fr = new FileReader(text);
        int dictionarySize = 256;
        List<Integer> result = new ArrayList<Integer>();
        Map<String, Integer> dictionary = new HashMap<String, Integer>();
        for(int i = 0; i < dictionarySize; i++){
            dictionary.put("" + (char) i, i);
        }

        String prev = "";
        int c;
        while ((c = text.read()) != -1) {
//            if(!dictionary.containsKey(c))
//                dictionary.put(""+c, dictionarySize++);
            String combined = prev + (char)c;
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

        ObjectOutputStream oos = new ObjectOutputStream(fos);
        oos.writeObject(result);
        oos.close();

        //return result;
    }

//    public static String decompress(List<Integer> compressed){
//        if(compressed == null)
//            return null;
//        int dictionarySize = 256;
//        Map<Integer, String> dictionary = new HashMap<Integer, String>();
//        for (int i=0; i<dictionarySize; i++) {
//            dictionary.put(i, "" + (char) i);
//        }
//        String prev = "" + (char) (int) compressed.remove(0);
//        StringBuilder result = new StringBuilder(prev);
//        for(int j : compressed) {
//            String combined;
//            if(dictionary.containsKey(j)) {
//                combined  = dictionary.get(j);
//            }
//            else if(j == dictionarySize) {
//                combined = prev + prev.charAt(0);
//            }
//            else {
//                return null;
//            }
//            result.append(combined);
//            dictionary.put(dictionarySize++, prev + combined.charAt(0));
//            prev = combined;
//        }
//        return result.toString();
//    }

    public static String decompress(FileInputStream fis, FileWriter outputFile) throws IOException, ClassNotFoundException {
//        if(compressed == null)
//            return null;
        int dictionarySize = 256;
        Map<Integer, String> dictionary = new HashMap<Integer, String>();
        for (int i=0; i<dictionarySize; i++) {
            dictionary.put(i, "" + (char) i);
        }

        ObjectInputStream ois = new ObjectInputStream(fis);
        List<Integer> compressed = (List<Integer>) ois.readObject();
        ois.close();

        String prev = "" + (char) (int) compressed.remove(0);
        StringBuilder result = new StringBuilder(prev);
        //for(int j : compressed) {
        for(int j = 0; j<=compressed.size(); j++) {
            String combined;
            if(dictionary.containsKey(j)) {
                combined  = dictionary.get(j);
            }
            else if(j == dictionarySize) {
                combined = prev + prev.charAt(0);
            }
            else {
                return null;
            }
            result.append(combined);
            System.out.print(combined);
            outputFile.write(combined);

            dictionary.put(dictionarySize++, prev + combined.charAt(0));
            prev = combined;
        }

        //System.out.println(result.toString());
        return result.toString();
    }
}
