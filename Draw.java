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
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Draw {

	private JFrame frame;
	private JTable table;
	private JTextField m;
    private int number=0;
    private String pass="";
    private JTextField p;
    private int times=0;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Draw window = new Draw("2009");
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
	public Draw(String str) {
		frame = new JFrame("房东"+str);
		frame.setBounds(100, 100, 408, 606);
		frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(14, 71, 344, 311);
		frame.getContentPane().add(scrollPane);
		
		int i=0,j=0;
	    Connection conn;
		try {
			conn = DriverManager.getConnection("jdbc:sqlserver://127.0.0.1:1433;DatabaseName=sechh","sa","123456");
		    Statement stmt=conn.createStatement();
		    ResultSet rset=stmt.executeQuery("select * from 收租总和 where 房东编号='"+str+"'");
		    ArrayList ar = new ArrayList();
		    
		    while(rset.next()) {
		    	ar.add(rset.getString(1));
		    	ar.add(rset.getInt(2));
		    	i++;
		    }
		    String[][] arr=new String[i][2];
		    String[] list= {"房屋编号","收租总和"};
		    ResultSet rs=stmt.executeQuery("select * from 收租总和 where 房东编号='"+str+"'");
		    while(rs.next()) {
		    	arr[j][0]=rs.getString(1);
		    	arr[j][1]=rs.getInt(2)+"元";
		    	j++;
		    }
		    table = new JTable(arr,list);
		    table.setFont(new Font("宋体", Font.PLAIN, 15));
		    table.setRowHeight(30);
			 scrollPane.setViewportView(table);
			 rset.close();
			 //int number=0;
			 ResultSet rset2=stmt.executeQuery("select * from 房东信息表 where fno='"+str+"'");
			 while(rset2.next()){
				 number=rset2.getInt(8);
				 pass=rset2.getString(3);
			 }
		} catch (SQLException e) {
			
			e.printStackTrace();
		}		 
		
		JLabel label = new JLabel("\u5404\u623F\u5C4B\u6536\u79DF\u60C5\u51B5\uFF1A");
		label.setFont(new Font("黑体", Font.PLAIN, 17));
		label.setBounds(14, 47, 169, 18);
		frame.getContentPane().add(label);
		
		JLabel label_1 = new JLabel("\u4E2A\u4EBA\u4F59\u989D\uFF1A"+number+"元");
		label_1.setFont(new Font("黑体", Font.PLAIN, 17));
		label_1.setBounds(14, 412, 159, 18);
		frame.getContentPane().add(label_1);
		
		m = new JTextField();
		m.setBounds(105, 458, 113, 33);
		frame.getContentPane().add(m);
		m.setColumns(10);
		
		JLabel label_2 = new JLabel("\u63D0\u73B0\u91D1\u989D\uFF1A");
		label_2.setFont(new Font("黑体", Font.PLAIN, 17));
		label_2.setBounds(14, 464, 95, 18);
		frame.getContentPane().add(label_2);
		
		p = new JTextField();
		p.setColumns(10);
		p.setBounds(105, 504, 113, 33);
		frame.getContentPane().add(p);
		
		JButton btnNewButton = new JButton("\u786E\u5B9A\u63D0\u73B0");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				times++;
				if((p.getText().equals(pass))==false) {
					JOptionPane.showMessageDialog(frame, "密码不对，请重新输入密码,您还有"+(3-times)+"次机会");
				}
				else {
					try {
						Connection conn;
						conn = DriverManager.getConnection("jdbc:sqlserver://127.0.0.1:1433;DatabaseName=sechh","sa","123456");
						Statement stmt=conn.createStatement();
					    stmt.executeUpdate("update 房东信息表 set fmoney='"+(number-Integer.valueOf(m.getText()))+"'where fno='"+str+"'");
					    JOptionPane.showMessageDialog(frame, "提现成功");
					    number=number-Integer.valueOf(m.getText());
					    label_1.setText("个人余额："+number+"元");
					} catch (SQLException e) {
						// TODO 自动生成的 catch 块
						e.printStackTrace();
					}
				   
				}
				if(times==3)
				{
					frame.setVisible(false);
				}
			}
		});
		btnNewButton.setBackground(new Color(230, 230, 250));
		btnNewButton.setFont(new Font("黑体", Font.PLAIN, 17));
		btnNewButton.setBounds(263, 508, 113, 27);
		frame.getContentPane().add(btnNewButton);
		
		JLabel lblNewLabel = new JLabel("\u63D0\u73B0\u5BC6\u7801\uFF1A");
		lblNewLabel.setFont(new Font("黑体", Font.PLAIN, 17));
		lblNewLabel.setBounds(14, 512, 95, 18);
		frame.getContentPane().add(lblNewLabel);
		

		frame.setVisible(true);
	}

}
