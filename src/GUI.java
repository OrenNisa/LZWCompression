import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.List;

public class GUI extends JFrame {
    private JButton btnChooseFile;
    private JButton btnCompress;
    private JButton btnDecompress;
    private JPanel mainPanel;
    private JLabel laFileName;
    private JLabel laIsCompleted;

    private String filename;

    private String folderPath;
    private FileReader reader;

    FileOutputStream fos;
    FileInputStream fis;

    FileWriter decompressedFile;

    public GUI() throws IOException {
        setContentPane(mainPanel);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("LZW ASCII Compression");
        pack();
        setVisible(true);
        btnChooseFile.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser chooser = new JFileChooser();
                chooser.showOpenDialog(null);
                File f = chooser.getSelectedFile();
                filename = f.getAbsolutePath();
                folderPath = f.getAbsolutePath().replaceAll(f.getName(), "");
                laFileName.setText(filename);
            }
        });
        btnCompress.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
//                try{
//                    //BufferedReader br = new BufferedReader(reader);
//
//                }
//                catch (Exception ex){
//                    JOptionPane.showMessageDialog(null, ex);
//                }
                try {
                    reader = new FileReader(filename);
                    fos = new FileOutputStream(folderPath + "\\compressed file");
                    LZW.compress(reader, fos);
                    laIsCompleted.setText("Compression Completed! look for file named \"compressed file\"");
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        });
        btnDecompress.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    fis = new FileInputStream(filename);
                    decompressedFile = new FileWriter(folderPath + "\\decompressed file.txt", true);
                    LZW.decompress(fis, decompressedFile);
                    laIsCompleted.setText("Decompression Completed! look for file named \"decompressed file\"");
                } catch (IOException | ClassNotFoundException ex) {
                    ex.printStackTrace();
                }
            }
        });
    }

    public static void main(String[] args) throws IOException {
        GUI gui = new GUI();
        //LZW a = new LZW();
        //List<Integer> compressed =  LZW.compress("Dai nimas li");
        //String decompressed = LZW.decompress(compressed);
        //System.out.println(decompressed);
    }
}
