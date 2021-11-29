import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class TEMP {}

//public class GUI implements ActionListener {
//    public GUI() {
//        JFrame frame = new JFrame();
//
//        JButton selectButton = new JButton("Select file");
//        selectButton.addActionListener(this);
//        JButton compressButton = new JButton("Start Compression");
//        JLabel label = new JLabel("Please select file to compress");
//
//        JPanel panel = new JPanel();
//        panel.setBorder(BorderFactory.createEmptyBorder(100,250,100,250));
//        panel.setLayout(new GridLayout(0,1));
//        panel.add(label);
//        panel.add(selectButton);
//
//        frame.add(panel, BorderLayout.CENTER);
//        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        frame.setTitle("Huffman ASCII Compression");
//        frame.pack();
//        frame.setVisible(true);
//    }
//    public static void main(String[] args) {
//        new GUI();
//        LZW a = new LZW();
//        List<Integer> compressed =  LZW.compress("Dai nimas li");
//        String decompressed = LZW.decompress(compressed);
//        System.out.println(decompressed);
//    }
//
//
//    @Override
//    public void actionPerformed(ActionEvent e) {
//
//    }
//}