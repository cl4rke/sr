import java.awt.*;
import javax.swing.*;
import javax.swing.text.Document;
import java.util.*;
import java.awt.event.*;

public class SR extends JFrame {
    JTextArea textArea, search, replace;
    JButton importfile, save, find, replacebutton;
    JScrollPane scroll;
    int pos = 0;


    public static void main(String arg[]) {

        SR ti = new SR();
    }


    public SR() {
        textArea = new JTextArea();
        search = new JTextArea("Search");
        importfile = new JButton("Import");
        save = new JButton("Save");
        find = new JButton("Search");
        replace = new JTextArea("replace");
        replacebutton = new JButton("Replace");
        // scroll = new JScrollPane(textArea);
        //  scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        add(new JScrollPane(textArea));
        this.add(textArea);
        this.add(search);
        this.add(importfile);
        this.add(save);
        // this.add(scroll);
        this.add(find);
        this.add(replace);
        this.add(replacebutton);

        importfile.setBounds(10, 50, 100, 40);
        search.setBounds(150, 50, 200, 40);
        find.setBounds(370, 50, 100, 40);
        save.setBounds(800, 50, 100, 40);
        textArea.setBounds(20, 150, 900, 300);
        replacebutton.setBounds(500, 50, 100, 40);
        replace.setBounds(600, 50, 150, 40);


        this.setSize(1000, 600);
        this.setLayout(new BorderLayout());


        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);


        find.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String find = search.getText().toLowerCase();
                textArea.requestFocusInWindow();
                if (find != null && find.length() > 0) {
                    Document document = textArea.getDocument();
                    int findLength = find.length();
                    try {
                        boolean found = false;
                        if (pos + findLength > document.getLength()) {
                            pos = 0;
                        }
                        while (pos + findLength <= document.getLength()) {
                            String match = document.getText(pos, findLength).toLowerCase();
                            if (match.equals(find)) {
                                found = true;
                                break;
                            }
                            pos++;
                        }

                        if (found) {
                            Rectangle viewRect = textArea.modelToView(pos);
                            textArea.scrollRectToVisible(viewRect);
                            textArea.setCaretPosition(pos + findLength);
                            textArea.moveCaretPosition(pos);
                            pos += findLength;
                        }

                    } catch (Exception exp) {
                        exp.printStackTrace();
                    }

                }
            }
        });
        replacebutton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String txt = textArea.getText().toLowerCase();
                String txt2 = search.getText().toLowerCase();
                String txt3 = replace.getText();
                if (txt.contains(txt)) {
                    textArea.setText(txt.replaceAll(txt2, txt3));
                }

            }
        });
    }
}


