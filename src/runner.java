import java.util.List;

public class runner {
    public static void main(String[] args) {
        LZW a = new LZW();
        List<Integer> compressed =  LZW.compress("abracadabra");
        String decompressed = LZW.decompress(compressed);
        System.out.println(decompressed);
    }
}