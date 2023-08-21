package Library;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.awt.Color;
import javax.swing.JTextField;
import javax.swing.JButton;

public class BooksReturn {

	private JFrame frame;
	private JTextField textField;
	private JTextField textField_1;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					BooksReturn window = new BooksReturn();
					window.frame.setTitle("BOOKS RETURN");
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
	public BooksReturn() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 637, 534);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Books Return");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 30));
		lblNewLabel.setBounds(185, 41, 247, 51);
		frame.getContentPane().add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Student ID   :");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 21));
		lblNewLabel_1.setBounds(97, 115, 132, 51);
		frame.getContentPane().add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Book ID         :");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNewLabel_2.setBounds(97, 170, 144, 34);
		frame.getContentPane().add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("*Enter book ID properly");
		lblNewLabel_3.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel_3.setForeground(new Color(255, 0, 0));
		lblNewLabel_3.setBounds(216, 389, 277, 34);
		frame.getContentPane().add(lblNewLabel_3);
		
		JLabel lblNewLabel_4 = new JLabel("*Enter Student ID properly");
		lblNewLabel_4.setFont(new Font("Dialog", Font.PLAIN, 15));
		lblNewLabel_4.setForeground(new Color(255, 0, 0));
		lblNewLabel_4.setBounds(216, 433, 277, 13);
		frame.getContentPane().add(lblNewLabel_4);
		
		textField = new JTextField();
		textField.setBounds(260, 125, 144, 29);
		frame.getContentPane().add(textField);
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setBounds(260, 170, 144, 31);
		frame.getContentPane().add(textField_1);
		textField_1.setColumns(10);
		
		JButton btnNewButton = new JButton("Submit");
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnNewButton.setBounds(238, 253, 132, 51);
		frame.getContentPane().add(btnNewButton);
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String stid=textField.getText();
				String bid=textField_1.getText();
				
				Connection  con = null;
				String url="jdbc:mysql://localhost:3306/LibraryManagement1";
				String usrname="root";
				String pswrd="Shiva@9948";
				int stus=0;
				try {
					Class.forName("com.mysql.jdbc.Driver");
					con=DriverManager.getConnection(url,usrname,pswrd);
				//	System.out.println("Connected");
					
					PreparedStatement p1=con.prepareStatement("select *from booksdetail where BookID=?;");
					p1.setString(1,bid);
					ResultSet rs=p1.executeQuery();
					int k = 0;
					while(rs.next()) {
					 k=rs.getInt("Available");
					}
					k++;
					PreparedStatement p2=con.prepareStatement("update booksdetail set Available=? where BookID=? ;");
					p2.setInt(1, k);
					p2.setString(2,bid);
					stus=p2.executeUpdate();
					if(stus>0){
						PreparedStatement ps=con.prepareStatement("delete from bookIssue where BookID=? and StudentId=? ");
						ps.setString(1,bid);
						ps.setString(2,stid);
						stus=ps.executeUpdate();
						}
					if(stus!=0) {
						JOptionPane.showMessageDialog(frame,"Books returned successfully!");
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
		
		JButton btnNewButton_1 = new JButton("Exit");
		btnNewButton_1.setFont(new Font("Tahoma", Font.PLAIN, 19));
		btnNewButton_1.setBounds(421, 305, 105, 34);
		frame.getContentPane().add(btnNewButton_1);
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
			}
		});
	}
}
