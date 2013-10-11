import javax.swing.*;

import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.Scanner;

public class Work extends JFrame{
	private JTextArea textArea;
	private JScrollPane scrollbar;
	private JButton[] buttons;
	private JPanel panel;
	
	
	public Work(){
		super("Notepad v1.1");
		setLayout(new FlowLayout());
		textArea = new JTextArea(17,30);
		textArea.setLineWrap(true);
		textArea.setFont(new Font("Times",Font.ITALIC+Font.BOLD,22));
		scrollbar = new JScrollPane(textArea);
		scrollbar.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		add(scrollbar);
		panel = new JPanel();
		buttons = new JButton[3];
		buttons[0]=new JButton("Open");
		buttons[1]=new JButton("Save");
		buttons[2]=new JButton("Exit");
		Buttonhandler handler = new Buttonhandler();
		for(int i=0;i<=2;i++){
			buttons[i].addActionListener(handler);
			panel.add(buttons[i]);
		}
		add(panel,BorderLayout.SOUTH);
		
	}
	
	public class Buttonhandler implements ActionListener{
		public void actionPerformed(ActionEvent event){
			if(event.getSource()==buttons[0]){
				openFile();
			}
			else if(event.getSource()==buttons[1]){
				opensaveFile();
			}
			else if(event.getSource()==buttons[2]){
				System.exit(1);
			}
		}
		
	}
	
	public void opensaveFile(){
		Scanner jText = new Scanner(textArea.getText());
		String filename = JOptionPane.showInputDialog("Enter a name for the file to be saved else press Cancel for nothing");
		if(filename!=null){
			File name = new File(filename);
			PrintWriter outputStream=null;
			try{
				outputStream=new PrintWriter(new FileOutputStream(name,true));
			}
			catch(IOException e){
				System.out.println("Not found");
			}
			while(jText.hasNextLine()){
				outputStream.println(jText.nextLine());
			}
			
			outputStream.close();
		}
		
	}
	
	public void openFile(){
		textArea.setText("");
		File open=null;
		JFileChooser filechoose = new JFileChooser();
		int result = filechoose.showOpenDialog(null);
			if(result==JFileChooser.APPROVE_OPTION){
				open=filechoose.getSelectedFile();
			
			
			Scanner inputStream=null;
			try{
				inputStream = new Scanner(new FileInputStream(open));
			}
			catch(IOException e){
				System.out.println("Some exception");
			}
			while(inputStream.hasNextLine()){
				textArea.append(inputStream.nextLine()+"\n");
			}
			
			inputStream.close();
			
			}
			
			
	}
	public static void main(String[] args){

		Work test = new Work();
		test.setSize(620,585);
		test.setResizable(true);
		
		test.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		test.setVisible(true);
		
	}
	
}
