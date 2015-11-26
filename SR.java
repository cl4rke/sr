import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import javax.swing.*;
import javax.swing.text.Document;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

public class SR extends JFrame {
    JTextArea textArea, search, replace;
    JButton importfile, save, find, replacebutton;
    JScrollPane scroll;
    int pos = 0;


    public static void main(String arg[]) {

        SR ti = new SR();
    }


    public SR() {

        textArea = new JTextArea(900,300);
        search = new JTextArea("Search");
        importfile = new JButton("Import");
        save = new JButton("Save");
        find = new JButton("Search");
        replace = new JTextArea("replace");
        replacebutton = new JButton("Replace");
        textArea.setLineWrap(true);
        textArea.setVisible(true);


        scroll = new JScrollPane(textArea);
        scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scroll.setSize(100,100);
        this.add(search);
        this.add(importfile);
        this.add(save);
        this.add(scroll);
        this.add(find);
        this.add(replace);
        this.add(replacebutton);



        importfile.setBounds(10, 50, 100, 40);
        search.setBounds(150, 50, 200, 40);
        find.setBounds(370, 50, 100, 40);
        save.setBounds(800, 50, 100, 40);
        scroll.setBounds(20, 150, 900, 300);
        replacebutton.setBounds(500, 50, 100, 40);
        replace.setBounds(600, 50, 150, 40);


        this.setSize(1000, 600);
       this.setLayout(new BorderLayout());



        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);


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

            public void actionPerformed(ActionEvent e) {
                String txt = textArea.getText();
                String txt2 = search.getText();
                String txt3 = replace.getText();

                if (txt.contains(txt2)) {

                  textArea.setText(txt.replaceAll("(?i)" + txt2, txt3));

                }

            }
        });
        importfile.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                String excelFilePath = "Workbook 26 - 50.xlsx";
                FileInputStream inputStream = null;
                try {
                    inputStream = new FileInputStream(new File(excelFilePath));
                } catch (FileNotFoundException e1) {
                    e1.printStackTrace();
                }

                Workbook workbook = null;
                try {
                    workbook = new XSSFWorkbook(inputStream);
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
                Sheet firstSheet = workbook.getSheetAt(0);

                Iterator<Row> iterator = firstSheet.iterator();

                while (iterator.hasNext()) {
                    Row nextRow = iterator.next();

                    Iterator<Cell> cellIterator = nextRow.cellIterator();
                    Iterator<Cell> scellIterator = nextRow.cellIterator();

                    cellIterator.next();
                    scellIterator.next();
                    scellIterator.next();
                    Cell topicsCell = cellIterator.next();
                    Cell topicSentimentCell =scellIterator.next();

                    String cellContents = topicsCell.getStringCellValue();
                    String scellContents = topicSentimentCell.getStringCellValue();

                    String[] topics = cellContents.split(";");
                    String[] topicSentiment = scellContents.split(";");

                    ArrayList<String> tpc = new ArrayList<>();
                    ArrayList<String> topicsents = new ArrayList<>();
                    for(int i = 0; i < topics.length; i++) {
                        topics[i] = topics[i].trim();
                        tpc.add(topics[i]);

                        for (int indx = 0; indx < tpc.size(); indx++) {
                            textArea.append(tpc.get(indx)+"\n");
                        }

                    }

                    for(int si = 0; si < topicSentiment.length; si++) {
                        topicSentiment[si] = topicSentiment[si].trim();
                        topicsents.add(topicSentiment[si]);

                       for (int index = 0; index < topicsents.size(); index++) {
                          //  textArea.append(topicsents.get(index)+"\n");
                         System.out.print(topicsents+"\n");


                        }

                    }


/*
                    while (cellIterator.hasNext()) {
                        Cell cell = cellIterator.next();

                        switch (cell.getCellType()) {
                            case Cell.CELL_TYPE_STRING:
                                System.out.print(cell.getStringCellValue());
                                break;
                            case Cell.CELL_TYPE_BOOLEAN:
                                System.out.print(cell.getBooleanCellValue());
                                break;
                            case Cell.CELL_TYPE_NUMERIC:
                                System.out.print(cell.getNumericCellValue());
                                break;
                        }
                       System.out.print(" - ");
                    }
                    System.out.println();
*/

                }


                try {
                    inputStream.close();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }

            }


        });
    }
}


