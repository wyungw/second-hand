package weizhenyang;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Flow1 {

	private JFrame frame;
	private JTable table;
	private JTextField textField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Flow1 window = new Flow1("2049");
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public Flow1(String str) {
		frame = new JFrame();
		frame.setBounds(100, 100, 562, 526);
		frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 60, 503, 352);
		frame.getContentPane().add(scrollPane);
		
		 int i=0,j=0;
		    Connection conn;
			try {
				conn = DriverManager.getConnection("jdbc:sqlserver://127.0.0.1:1433;DatabaseName=sechh","sa","123456");
			    Statement stmt=conn.createStatement();
			    ResultSet rset=stmt.executeQuery("select * from 缴费流水 where 房东编号='"+str+"'");
			    ArrayList ar = new ArrayList();
			    
			    while(rset.next()) {
			    	ar.add(rset.getString(1));
			    	ar.add(rset.getString(2));
			    	ar.add(rset.getString(3));
			    	ar.add(rset.getInt(4));
			    	ar.add(rset.getDate(5));
			    	ar.add(rset.getString(6));
			    	i++;
			    }
			    String[][] arr=new String[i][6];
			    String[] list= {"订单编号","租客姓名","房屋编号","缴费价格","缴费日期","是否在租"};
			    ResultSet rs=stmt.executeQuery("select * from 缴费流水 where 房东编号='"+str+"'");
			    while(rs.next()) {
			    	arr[j][0]=rs.getString(1);
			    	arr[j][1]=rs.getString(2);
			    	arr[j][2]=rs.getString(3);
			    	arr[j][3]=rs.getInt(4)+"元";
			    	arr[j][4]=rs.getDate(5)+"";
			    	arr[j][5]=rs.getString(6);
			    	j++;
			    }
			    table = new JTable(arr,list);
			    table.setFont(new Font("宋体", Font.PLAIN, 15));
			    table.setRowHeight(30);
				 scrollPane.setViewportView(table);
			} catch (SQLException e) {
				
				e.printStackTrace();
			}		
		JLabel lblNewLabel = new JLabel("\u6240\u6709\u7F34\u8D39\u8BB0\u5F55\uFF1A");
		lblNewLabel.setFont(new Font("黑体", Font.PLAIN, 17));
		lblNewLabel.setBounds(0, 29, 142, 18);
		frame.getContentPane().add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("\u67E5\u8BE2\u623F\u5BA2\uFF1A");
		lblNewLabel_1.setFont(new Font("黑体", Font.PLAIN, 17));
		lblNewLabel_1.setBounds(10, 435, 95, 31);
		frame.getContentPane().add(lblNewLabel_1);
		
		textField = new JTextField();
		textField.setBounds(100, 425, 122, 41);
		frame.getContentPane().add(textField);
		textField.setColumns(10);
		
		JButton button = new JButton("\u67E5\u8BE2");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int m=0,n=0;
			    Connection conn1;
			    if(textField.getText().length()!=0) {
			    	try {
						conn1 = DriverManager.getConnection("jdbc:sqlserver://127.0.0.1:1433;DatabaseName=sechh","sa","123456");
					    Statement stmt1=conn1.createStatement();
					    ResultSet rset1=stmt1.executeQuery("select * from 缴费流水 where 房东编号='"+str+"'");
					    ArrayList ar = new ArrayList();
					    
					    while(rset1.next()) {
					    	ar.add(rset1.getString(1));
					    	ar.add(rset1.getString(2));
					    	ar.add(rset1.getString(3));
					    	ar.add(rset1.getInt(4));
					    	ar.add(rset1.getDate(5));
					    	ar.add(rset1.getString(6));
					    	m++;
					    }
					    String[][] arr=new String[m][6];
					    String[] list= {"订单编号","租客姓名","房屋编号","缴费价格","缴费日期","是否在租"};
					    ResultSet rs=stmt1.executeQuery("select * from 缴费流水 where 房东编号='"+str+"'and 租客姓名='"+textField.getText()+"'");
					    while(rs.next()) {
					    	arr[n][0]=rs.getString(1);
					    	arr[n][1]=rs.getString(2);
					    	arr[n][2]=rs.getString(3);
					    	arr[n][3]=rs.getInt(4)+"元";
					    	arr[n][4]=rs.getDate(5)+"";
					    	arr[n][5]=rs.getString(6);
					    	n++;
					    }
					    table = new JTable(arr,list);
					    table.setFont(new Font("宋体", Font.PLAIN, 15));
					    table.setRowHeight(30);
						 scrollPane.setViewportView(table);
					} catch (SQLException e) {
						
						e.printStackTrace();
					}		
			    }else {
			    	JOptionPane.showMessageDialog(frame, "请输入要查询的名字");
			    }
				
			}
		});
		button.setFont(new Font("黑体", Font.PLAIN, 17));
		button.setBounds(261, 425, 122, 41);
		frame.getContentPane().add(button);
		frame.setVisible(true);
	}
}
