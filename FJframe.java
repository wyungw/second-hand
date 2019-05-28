package weizhenyang;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;

import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import java.awt.Font;
import java.awt.Color;
import javax.swing.JTable;

public class FJframe {

	private JFrame frame;
	private JTable table;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FJframe window = new FJframe("2001");
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
	public FJframe(String str) {
		frame = new JFrame(str);
		frame.setTitle("亲爱的房东"+str+"，欢迎您使用系统");
		frame.setBounds(100, 100, 866, 642);
		frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		
		JButton button = new JButton("\u4E2A\u4EBA\u4FE1\u606F\u7BA1\u7406");
		button.setBounds(674, 96, 173, 68);
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				new Fmess(str);
			}
		});
		frame.getContentPane().setLayout(null);
		button.setBackground(new Color(230, 230, 250));
		button.setFont(new Font("黑体", Font.PLAIN, 18));
		frame.getContentPane().add(button);
		
		JButton button_1 = new JButton("\u623F\u5C4B\u4FE1\u606F\u7BA1\u7406");
		button_1.setBounds(674, 208, 173, 68);
		button_1.setBackground(new Color(230, 230, 250));
		button_1.setFont(new Font("黑体", Font.PLAIN, 18));
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				new Managehouse(str);
			}
		});
		frame.getContentPane().add(button_1);
		
		JButton button_2 = new JButton("\u8D26\u6237\u63D0\u73B0");
		button_2.setBounds(674, 315, 173, 68);
		button_2.setBackground(new Color(230, 230, 250));
		button_2.setFont(new Font("黑体", Font.PLAIN, 18));
		frame.getContentPane().add(button_2);
		
		JButton button_3 = new JButton("\u7F34\u8D39\u6D41\u6C34\u67E5\u8BE2");
		button_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				new Flow1(str);
			}
		});
		button_3.setBounds(674, 422, 173, 68);
		button_3.setBackground(new Color(230, 230, 250));
		button_3.setFont(new Font("黑体", Font.PLAIN, 18));
		frame.getContentPane().add(button_3);
		ImageIcon ic=new ImageIcon("E:\\平时用文件\\大二\\数据库课程设计\\6.jpg");
	    JLabel l=new JLabel(ic);	  
	    l.setBounds(0,0,ic.getIconWidth(),ic.getIconHeight());	  	    
	    frame.getLayeredPane().add(l,new Integer(Integer.MIN_VALUE));	  
	    ((JComponent) frame.getContentPane()).setOpaque(false);//设置透明	
	    
	    JLabel lblNewLabel = new JLabel("\u4E8C\u624B\u623F\u5C4B\u7BA1\u7406\u7CFB\u7EDF");
	    lblNewLabel.setBounds(90, 31, 424, 58);
	    lblNewLabel.setFont(new Font("楷体", Font.PLAIN, 50));
	    frame.getContentPane().add(lblNewLabel);
	   // JScrollPane jsp=new JScrollPane(table);
	 //   frame.add(jsp);
	    JLabel label = new JLabel("\u6240\u6709\u623F\u5C4B\uFF1A");
	    label.setBounds(14, 96, 127, 18);
	    label.setFont(new Font("黑体", Font.PLAIN, 17));
	    frame.getContentPane().add(label);
	    
	    JScrollPane scrollPane = new JScrollPane();
	    scrollPane.setBounds(24, 127, 605, 410);
	    frame.getContentPane().add(scrollPane);	    
	   
	    int i=0,j=0;
	    Connection conn;
		try {
			conn = DriverManager.getConnection("jdbc:sqlserver://127.0.0.1:1433;DatabaseName=sechh","sa","123456");
		    Statement stmt=conn.createStatement();
		    ResultSet rset=stmt.executeQuery("select * from 房屋信息表");
		    ArrayList ar = new ArrayList();
		    
		    while(rset.next()) {
		    	ar.add(rset.getString(1));
		    	ar.add(rset.getString(2));
		    	ar.add(rset.getString(3));
		    	ar.add(rset.getString(4));
		    	ar.add(rset.getString(5));
		    	ar.add(rset.getInt(6));
		    	ar.add(rset.getInt(7));
		    	i++;
		    }
		    String[][] arr=new String[i][7];
		    String[] list= {"房屋编号","房东编号","房屋状态","类型","地址","面积","价格"};
		    ResultSet rs=stmt.executeQuery("select * from 房屋信息表 order by hno");
		    while(rs.next()) {
		    	arr[j][0]=rs.getString(1);
		    	arr[j][1]=rs.getString(2);
		    	arr[j][2]=rs.getString(3);
		    	arr[j][3]=rs.getString(4);
		    	arr[j][4]=rs.getString(5);
		    	arr[j][5]=rs.getInt(6)+"平方米";
		    	arr[j][6]=rs.getInt(7)+"元";
		    	j++;
		    }
		    table = new JTable(arr,list);
		    table.setFont(new Font("宋体", Font.PLAIN, 15));
		    table.setRowHeight(30);
			 scrollPane.setViewportView(table);
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		 
	    frame.setVisible(true);
	}
}
