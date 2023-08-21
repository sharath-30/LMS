package Library;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;
import java.awt.Color;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.sql.*;
public class BooksIssuing {

	private JFrame frame;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JLabel lblNewLabel_5;
	private JButton btnNewButton;
	private JButton btnNewButton_1;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					BooksIssuing window = new BooksIssuing();
					window.frame.setTitle("BOOKS ISSUING");
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	
	}

	/**
	 * Create the application.
	 */
	public BooksIssuing() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 607, 676);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Books Issuing");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 35));
		lblNewLabel.setBounds(151, 55, 267, 55);
		frame.getContentPane().add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Student ID          :");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNewLabel_1.setBounds(75, 139, 175, 46);
		frame.getContentPane().add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Book ID              :");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNewLabel_2.setBounds(75, 195, 175, 29);
		frame.getContentPane().add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("Student Name     :");
		lblNewLabel_3.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNewLabel_3.setBounds(75, 234, 175, 40);
		frame.getContentPane().add(lblNewLabel_3);
		
		JLabel lblNewLabel_4 = new JLabel("Student Contact  :");
		lblNewLabel_4.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNewLabel_4.setBounds(75, 284, 175, 29);
		frame.getContentPane().add(lblNewLabel_4);
		
		textField = new JTextField();
		textField.setBounds(260, 147, 181, 29);
		frame.getContentPane().add(textField);
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setBounds(260, 195, 181, 28);
		frame.getContentPane().add(textField_1);
		textField_1.setColumns(10);
		
		textField_2 = new JTextField();
		textField_2.setBounds(260, 240, 181, 28);
		frame.getContentPane().add(textField_2);
		textField_2.setColumns(10);
		
		textField_3 = new JTextField();
		textField_3.setBounds(260, 284, 181, 28);
		frame.getContentPane().add(textField_3);
		textField_3.setColumns(10);
		
		lblNewLabel_5 = new JLabel("*Please check Student id Properly");
		lblNewLabel_5.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel_5.setForeground(new Color(255, 0, 0));
		lblNewLabel_5.setBounds(151, 571, 260, 19);
		frame.getContentPane().add(lblNewLabel_5);
		
		btnNewButton = new JButton("Issue Book");
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnNewButton.setBounds(196, 380, 186, 46);
		frame.getContentPane().add(btnNewButton);
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String sid=textField.getText();
				String bid=textField_1.getText();
				String sname=textField_2.getText();  
				String cont=textField_3.getText();
				Connection  con = null;
				String url="jdbc:mysql://localhost:3306/LibraryManagement1";
				String usrname="root";
				String pswrd="Shiva@9948";
				int stus=0;
				try {
					Class.forName("com.mysql.jdbc.Driver");
					con=DriverManager.getConnection(url,usrname,pswrd);
					//System.out.println("Connected");
					//Statement st=con.createStatement();
					//String tb="create table bookIssue(IssuingNumber int auto_increment,BookID varchar(30),StudentId varchar(30),StudentName varchar(30),StudentContact varchar(30),IssuingDate Date default(CURRENT_DATE), primary key(IssuingNumber));";
					//String d="delete from bookIssue;";
					//String d="drop table bookIssue;";
					//st.executeUpdate(tb);
					//System.out.println("created");
					/*
					 String d="drop table bookIssue;";
					 */
					DatabaseMetaData dbm = con.getMetaData();
				      ResultSet tables = dbm.getTables(null, null, "bookIssue", null);
				      if (tables.next()) {
				       System.out.println("EXIXTS____________");
				      } else {
				        // Table does not exist, create it
				    	  String tb="create table bookIssue(IssuingNumber int auto_increment,BookID varchar(30),StudentId varchar(30),StudentName varchar(30),StudentContact varchar(30),IssuingDate Date default(CURRENT_DATE), primary key(IssuingNumber));";
				    	  PreparedStatement ps9999=con.prepareStatement(tb);
				  			     ps9999.executeUpdate(tb);
				  			 System.out.println("table created");
				      }
					PreparedStatement ps=con.prepareStatement("Insert into bookIssue(BookID,StudentId,StudentName,StudentContact) values(?,?,?,?);");
					ps.setString(1, bid);
					ps.setString(2, sid);
					ps.setString(3, sname);
					ps.setString(4, cont);
					stus=ps.executeUpdate();
					/*String s="Insert into bookIssue(BookID,StudentId,StudentName,StudentContact) values(521,'me4ch122','shiva',63020688);";
					st.executeUpdate(s);
					System.out.println("INSERTED");*/
					PreparedStatement p1=con.prepareStatement("select *from booksdetail where BookID=?;");
					p1.setString(1,bid);
					ResultSet rs=p1.executeQuery();
					int k = 0;
					while(rs.next()) {
					 k=rs.getInt("Available");
					}
					if(k>0) {
					k--;
				//	System.out.println("K VALUE IS "+k);
					PreparedStatement p2=con.prepareStatement("update booksdetail set Available=? where BookID=? ;");
					p2.setInt(1, k);
					p2.setString(2,bid);
					p2.executeUpdate();
					}else {
						JOptionPane.showMessageDialog(frame,"Book is out f stock");
						frame.dispose();
					}
					if(stus!=0) {
						JOptionPane.showMessageDialog(frame,"Books Issued successfully!");
						frame.dispose();
					}else {
						JOptionPane.showMessageDialog(frame,"Error UnSuccesful");
						frame.dispose();
					}
				}catch(Exception e1) {
					System.out.println(e1);
				}
			}
		});
		
		btnNewButton_1 = new JButton("Exit");
		btnNewButton_1.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnNewButton_1.setBounds(418, 471, 103, 40);
		frame.getContentPane().add(btnNewButton_1);
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
			}
			});
		
		
		JLabel lblNewLabel_6 = new JLabel("*Please enter book id properly");
		lblNewLabel_6.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel_6.setBackground(new Color(240, 240, 240));
		lblNewLabel_6.setForeground(new Color(255, 0, 0));
		lblNewLabel_6.setBounds(155, 536, 222, 19);
		frame.getContentPane().add(lblNewLabel_6);
		
		
	}
}
