package Library;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.sql.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

public class IssuedBooks extends JFrame {

	private JFrame frame;
	private JPanel contentPane;
	private JTable table;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					new IssuedBooks();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public IssuedBooks() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setBounds(100, 100, 826, 503);
		this.setTitle("Issued Books");
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		String data[][]=null;
		String column[]=null;
		
		Connection con=null;
		String url="jdbc:mysql://localhost:3306/LibraryManagement1";
		String usrname="root";
		String pswrd="Shiva@9948";
		try {
			 Class.forName("com.mysql.jdbc.Driver");
		      con = DriverManager.getConnection(url, usrname, pswrd);
		     // System.out.println("Connected!");
		     // String tb="create table booksdetail(BookID varchar(30),BookName varchar(30),Author varchar(30),Available int, primary key(BookID));";
		  //    System.out.println("Inserting record");
			// String s="Insert into booksdetail(BookID,BookName,Author,Available) values(521,'mech','shiva',6);";
			PreparedStatement ps=con.prepareStatement("select * from bookIssue",ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
		//	ps.executeUpdate(tb);
		//	ps.executeUpdate(s);
			//System.out.println("inserted");
			ResultSet rs=ps.executeQuery();
			ResultSetMetaData rsmd=rs.getMetaData();
			int cols=rsmd.getColumnCount();
			column=new String[cols];
			for(int i=1;i<=cols;i++){
				column[i-1]=rsmd.getColumnName(i);
			}
			rs.last();
			int rows=rs.getRow();
			rs.beforeFirst();
			data=new String[rows][cols];
			int count=0;
			while(rs.next()){
				for(int i=1;i<=cols;i++){
					data[count][i-1]=rs.getString(i);
				}
				count++;
			}
			con.close();
				this.setVisible(true);
			
		}catch(Exception e) {
			System.out.println(e);
		}
		table = new JTable(data,column);
		getContentPane().add(table);
		JScrollPane sp=new JScrollPane(table);
		contentPane.add(sp, BorderLayout.CENTER);
	}

}
