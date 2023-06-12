import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;

public class TextEditor implements ActionListener {
    public int x=100,y=20;
    // Declaring properties of TextEditor
    JFrame frame;

    JMenuBar menuBar;

    JMenu file,edit;

    JMenu colour;

    //File Menu items;
    JMenuItem newFile,openFile,saveFile;

    JMenuItem cut,copy,paste,selectAll,close;

    JMenuItem black,yellow,pink;

    //TextArea
    JTextArea textArea;
    TextEditor(){
        //Initialize a frame
        frame = new JFrame();


        //Initialize menuBar
        menuBar=new JMenuBar();
        //Initialize text area
        textArea=new JTextArea();

        //Adding Selection color
        textArea.setSelectionColor(Color.pink);

        //Initialize menu
        file = new JMenu("File");
        edit = new JMenu("Edit");
        colour = new JMenu("Colour");

        //Initialize file menu items
        newFile=new JMenuItem("New Window");
        openFile=new JMenuItem("Open File");
        saveFile=new JMenuItem("Save File");
        //Add action Listener to file menu items
        newFile.addActionListener(this);
        openFile.addActionListener(this);
        saveFile.addActionListener(this);
        //Add menu items to file menu
        file.add(newFile);
        file.add(openFile);
        file.add(saveFile);
        //Initialize edit menu items
        cut=new JMenuItem("Cut");
        copy=new JMenuItem("Copy");
        paste=new JMenuItem("Paste");
        selectAll=new JMenuItem("Select All");
        close=new JMenuItem("Close");
        //Add action Listener to edit menu items
        cut.addActionListener(this);
        copy.addActionListener(this);
        paste.addActionListener(this);
        selectAll.addActionListener(this);
        close.addActionListener(this);
        //Adding to edit menu
        edit.add(cut);
        edit.add(copy);
        edit.add(paste);
        edit.add(selectAll);
        edit.add(close);
        //Initialize colour menu items
        black=new JMenuItem("Black");
        yellow=new JMenuItem("Yellow");
        pink=new JMenuItem("Pink");
        //Add action Listener to colour menu items
        black.addActionListener(this);
        yellow.addActionListener(this);
        pink.addActionListener(this);
        //Adding to colour menu
        colour.add(black);
        colour.add(yellow);
        colour.add(pink);


        //Add menu to menu bar
        menuBar.add(file);
        menuBar.add(edit);
        menuBar.add(colour);

        //Set MenuBar to frame
        frame.setJMenuBar(menuBar);
        //Create Content Pane
        JPanel panel=new JPanel();
        panel.setBorder(new EmptyBorder(5,5,5,5));
        panel.setLayout(new BorderLayout(0,0));
        //Add the text area to the panel
        panel.add(textArea,BorderLayout.CENTER);
        //Create a scroll pane
        JScrollPane scrollPane=new JScrollPane(textArea,JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        //Add scroll pane to panel
        panel.add(scrollPane);
        //Add panel to frame
        frame.add(panel);
        //Set Dimension of frame
        frame.setBounds(x,y,860,550);
        frame.setTitle("Text Editor");
        frame.setVisible(true);
        frame.setLayout(null);
    }
    @Override
    public void actionPerformed(ActionEvent actionEvent){
        if(actionEvent.getSource()==cut){
            //Perform cut operation
            textArea.cut();
        }
        if(actionEvent.getSource()==copy){
            //Perform copy operation
            textArea.copy();
        }
        if(actionEvent.getSource()==paste){
            //Perform paste operation
            textArea.paste();
        }
        if(actionEvent.getSource()==selectAll){
            //Perform Select All operation
            textArea.selectAll();
        }
        if(actionEvent.getSource()==close){
            //Perform close operation
            System.exit(0);
        }
        if(actionEvent.getSource()==black){
            //Perform colour change operation
            textArea.setBackground(Color.BLACK);
            textArea.setForeground(Color.WHITE);
        }
        if(actionEvent.getSource()==yellow){
            //Perform colour change operation
            textArea.setBackground(Color.YELLOW);
            textArea.setForeground(Color.BLACK);
        }
        if(actionEvent.getSource()==pink){
            //Perform colour change operation
            textArea.setBackground(Color.PINK);
        }
        if(actionEvent.getSource()==openFile){
            //Open a file chooser
            JFileChooser fileChooser=new JFileChooser("C:");
            int chooseOption=fileChooser.showOpenDialog(null);
            //If we have clicked on open button
            if(chooseOption==JFileChooser.APPROVE_OPTION){
                //Getting the selected file
                File file=fileChooser.getSelectedFile();
                //Get the path of selected file
                String filePath= file.getPath();
                try {
                    //Initialize file reader
                    FileReader fileReader=new FileReader(filePath);
                    //Initialize the buffered reader
                    BufferedReader bufferedReader=new BufferedReader(fileReader);
                    String intermediate="",output="";
                    //Read Content of file line by line
                    while((intermediate=bufferedReader.readLine())!=null){
                        output+=intermediate+"\n";
                    }
                    //Set the output string to text area
                    textArea.setText(output);
                } catch (IOException fileNotFoundException){
                    fileNotFoundException.printStackTrace();
                }
            }
        }
        if(actionEvent.getSource()==saveFile){
            //Initialize file picker
            JFileChooser fileChooser=new JFileChooser("C:");
            //Get choose option from file chooser
            int chooseOption=fileChooser.showSaveDialog(null);
            //Check if we clicked on save option
            if(chooseOption==JFileChooser.APPROVE_OPTION){
            //Create a new file with chosen directory path and file name
                File file=new File(fileChooser.getSelectedFile().getAbsolutePath()+".txt");
                try {
                        //Initialize file writer
                        FileWriter fileWriter=new FileWriter(file);
                        //Initialize Buffered writer
                        BufferedWriter bufferedWriter=new BufferedWriter(fileWriter);
                        //Write content of text area to file
                        textArea.write(bufferedWriter);
                        bufferedWriter.close();
                }
                catch (IOException ioException){
                    ioException.printStackTrace();
                }
            }
        }
        if(actionEvent.getSource()==newFile){
            x+=50;
            y+=10;
            TextEditor textEditor=new TextEditor();
            frame.setBounds(x,y,860,550);
        }

    }
    public static void main(String[] args) {
        TextEditor textEditor=new TextEditor();
    }
}