import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.io.*;

public class FileSelect {
    public static void main(String[] args) throws IOException {
        JFileChooser browser = new JFileChooser();
        FileNameExtensionFilter txtFilter = new FileNameExtensionFilter(".txt", "txt");
        browser.setFileFilter(txtFilter);

        JFrame textEditorFrame = new JFrame("Michael-soft Word"); textEditorFrame.setSize(600, 600);
        textEditorFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        JTextArea textEditArea = new JTextArea();

        JMenuBar textMenu = new JMenuBar();
        JMenu fileMenu = new JMenu("File");
        fileMenu.setMnemonic(KeyEvent.VK_F);
        textMenu.add(fileMenu);

        JMenuItem saveMenuButton = new JMenuItem("Save");
        fileMenu.add(saveMenuButton);
        textEditorFrame.setJMenuBar(textMenu);
        textEditorFrame.add(textEditArea);
        JMenuItem exitMenuButton = new JMenuItem("Exit");
        fileMenu.add(exitMenuButton);
        
        String filePath = null;
        File selectedFile = null;
        int choice = browser.showOpenDialog(browser);
        if(choice == browser.APPROVE_OPTION){
            selectedFile = browser.getSelectedFile();
            filePath = selectedFile.getPath();
            textEditorFrame.setVisible(true);
        }else if(choice == browser.CANCEL_OPTION){
            System.exit(0);
        }
            FileReader txtFileReader = new FileReader(filePath);
            textEditArea.read(txtFileReader, filePath);
            txtFileReader.close();
            
        String finalFilePath = filePath;
        File finalSelectedFile = selectedFile;
        saveMenuButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                try {
                    FileWriter textSave = new FileWriter(finalSelectedFile);
                    textEditArea.write(textSave);
                    textSave.close();
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
            }
            });
        exitMenuButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                    System.exit(0);
            }
        });
    }
}
