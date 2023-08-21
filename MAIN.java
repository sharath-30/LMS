package Library;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Scanner;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
public class MAIN extends JFrame implements ActionListener{
	static MAIN frame;
	JLabel j1,j2,l2;
	JButton b1,b2,b3,b4,b5;
	int i=0,j=0,q,w;
	Scanner a=new Scanner(System.in);
	MAIN(){
		this.setSize(1000, 750);
		this.setTitle("SAS");
		j1=new JLabel("LIBRARY FILES");
		j1.setFont(new Font("verdana",Font.BOLD,50));
		j1.setBounds(250, 20, 700, 100);
		j2=new JLabel("");
		j2.setBounds(10, 10, 100, 100);
		b1=new JButton("VIEW BOOKS");
		b2=new JButton("BOOK ISSUING");
		b3=new JButton("BOOK RETURN");
		b4=new JButton("ADD BOOKS");
		b5=new JButton("ISSUED BOOKS");
		b1.setBounds(200, 150, 500, 50);
		b2.setBounds(200, 250, 500, 50);
		b3.setBounds(200, 350, 500, 50);
		b4.setBounds(200, 450, 500, 50);
		b5.setBounds(200, 550, 500, 50);
		b1.addActionListener(this);
		b2.addActionListener(this);
		b3.addActionListener(this);
		b4.addActionListener(this);
		b5.addActionListener(this);
		this.add(b1);
		this.add(b2);
		this.add(b3);
		this.add(b4);
		this.add(b5);
		this.add(j1);
		this.add(j2);
		this.setVisible(true);
	}
	public static void main(String[] args) {
			new MAIN();
	}
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==b1) {
			ViewBooks.main(new String[]{});
		}
		if(e.getSource()==b2) {
			BooksIssuing.main(new String[] {});
		}
		if(e.getSource()==b3) {
			BooksReturn.main(new String[] {});
		}
		if(e.getSource()==b4) {
			AddBoooks.main(new String[] {});
		}
		if(e.getSource()==b5) {
			IssuedBooks.main(new String[] {});
		}
	}
}
