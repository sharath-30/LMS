package Library;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.SwingConstants;

public class AddBoooks extends JFrame{

	private static final String Connection = null;
	private JFrame frame;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args)  {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AddBoooks window = new AddBoooks();
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
	public AddBoooks() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 607, 680);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Add Books");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 35));
		lblNewLabel.setBounds(195, 41, 251, 67);
		frame.getContentPane().add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Book ID            :");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 25));
		lblNewLabel_1.setBounds(53, 151, 200, 42);
		frame.getContentPane().add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Book Name       :");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 25));
		lblNewLabel_2.setBounds(53, 203, 200, 48);
		frame.getContentPane().add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("Book Author      :");
		lblNewLabel_3.setFont(new Font("Tahoma", Font.PLAIN, 25));
		lblNewLabel_3.setBounds(53, 261, 200, 42);
		frame.getContentPane().add(lblNewLabel_3);
		
		JLabel lblNewLabel_4 = new JLabel("Books Available :");
		lblNewLabel_4.setFont(new Font("Tahoma", Font.PLAIN, 25));
		lblNewLabel_4.setBounds(53, 313, 200, 42);
		frame.getContentPane().add(lblNewLabel_4);
		
		textField = new JTextField();
		textField.setBounds(273, 163, 173, 25);
		frame.getContentPane().add(textField);
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setBounds(273, 218, 173, 25);
		frame.getContentPane().add(textField_1);
		textField_1.setColumns(10);
		
		textField_2 = new JTextField();
		textField_2.setBounds(273, 273, 173, 25);
		frame.getContentPane().add(textField_2);
		textField_2.setColumns(10);
		
		textField_3 = new JTextField();
		textField_3.setBounds(273, 325, 173, 25);
		frame.getContentPane().add(textField_3);
		textField_3.setColumns(10);
		
		JButton btnNewButton = new JButton("Add Book");
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnNewButton.setBounds(195, 418, 173, 58);
		frame.getContentPane().add(btnNewButton);
		btnNewButton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				String bid=textField.getText();
				String bname=textField_1.getText();
				String bauth=textField_2.getText();
				String avble=textField_3.getText();
				int a=Integer.parseInt(avble);
				
				//String insrt="Insert into booksdetail(BookID,BookName,Author,Available) values(bid,bname,bauth,a);";					
				int stus=0;	
				Connection  con = null;
				String url="jdbc:mysql://localhost:3306/LibraryManagement1";
				String usrname="root";
				String pswrd="ASK@9948";
				try {
					Class.forName("com.mysql.jdbc.Driver");
					con=DriverManager.getConnection(url,usrname,pswrd);
					System.out.println("Connected");
					
					DatabaseMetaData dbm = con.getMetaData();
				      ResultSet tables = dbm.getTables(null, null, "booksdetail", null);
				      if (tables.next()) {
				        System.out.println("EXIXTS____________");
				      } else {
				        // Table does not exist, create it
				    	  String tb="create table booksdetail(BookID varchar(30),BookName varchar(30),Author varchar(30),Available int, primary key(BookID));";
				    	  PreparedStatement ps9999=con.prepareStatement(tb);
				  			     ps9999.executeUpdate(tb);
				  			 System.out.println("table created");
				      }
				      
					PreparedStatement ps=con.prepareStatement("Insert into booksdetail(BookID,BookName,Author,Available) values(?,?,?,?);");
					ps.setString(1, bid);
					ps.setString(2, bname);
					ps.setString(3, bauth);
					ps.setInt(4, a);
					stus=ps.executeUpdate();
				if(stus!=0) {
					JOptionPane.showMessageDialog(AddBoooks.this,"Books added successfully!");
					frame.dispose();
				}else {
					JOptionPane.showMessageDialog(AddBoooks.this,"Error UnSuccesful");
				}					
				}catch(Exception e1) {
					System.out.println(e1);
				}
				try {
					con.close();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		
		JButton btnNewButton_1 = new JButton("exit");
		btnNewButton_1.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnNewButton_1.setBounds(347, 500, 170, 42);
		frame.getContentPane().add(btnNewButton_1);
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
			}
			
		});
		
	}
}
