package weizhenyang;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.awt.event.ActionEvent;

public class HMchange {

	private JFrame frame;
	private JTextField t;
	private JTextField a;
	private JTextField s;
	private JTextField p;
	protected String path="";

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					HMchange window = new HMchange("","105");
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
	public HMchange(String str,String hno) {
		frame = new JFrame("房屋编号："+hno);
		frame.setBounds(100, 100, 451, 486);
		frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		String sql="select * from 房屋信息表 where hno='"+hno+"'";
		String aa="",tt="";
		Connection conn;
		int pp=0,ss=0;
		try {
			conn = DriverManager.getConnection("jdbc:sqlserver://127.0.0.1:1433;DatabaseName=sechh","sa","123456");
			Statement pstmt=conn.createStatement();
	        ResultSet rs=pstmt.executeQuery(sql);
	        while(rs.next()) {
	        	aa=rs.getString(5);
	        	tt=rs.getString(4);
	        	ss=rs.getInt(6);
	        	pp=rs.getInt(7);
	        	path=rs.getString(8);
	        }
	        JButton button_1 = new JButton("\u4FDD\u5B58\u4FEE\u6539");
			button_1.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					try {
						pstmt.executeUpdate("update 房屋信息表 set varity='"+t.getText()+"'where hno='"+hno+"'");
						pstmt.executeUpdate("update 房屋信息表 set adress='"+a.getText()+"'where hno='"+hno+"'");
						pstmt.executeUpdate("update 房屋信息表 set hsquare='"+s.getText()+"'where hno='"+hno+"'");
						pstmt.executeUpdate("update 房屋信息表 set price='"+p.getText()+"'where hno='"+hno+"'");
						JOptionPane.showMessageDialog(frame, "修改成功");
					} catch (SQLException e1) {
						// TODO 自动生成的 catch 块
						e1.printStackTrace();
					}
					
				}
			});
			button_1.setBounds(86, 295, 234, 35);
			frame.getContentPane().add(button_1);
			
		} catch (SQLException e1) {
			// TODO 自动生成的 catch 块
			e1.printStackTrace();
		}

		JLabel htype = new JLabel("\u7C7B\u578B\uFF1A");
		htype.setFont(new Font("黑体", Font.PLAIN, 17));
		htype.setBounds(14, 29, 72, 18);
		frame.getContentPane().add(htype);
		
		JLabel address = new JLabel("\u5730\u5740\uFF1A");
		address.setFont(new Font("黑体", Font.PLAIN, 17));
		address.setBounds(14, 93, 72, 18);
		frame.getContentPane().add(address);
		
		JLabel s1 = new JLabel("\u9762\u79EF\uFF1A");
		s1.setFont(new Font("黑体", Font.PLAIN, 17));
		s1.setBounds(14, 159, 72, 18);
		frame.getContentPane().add(s1);
		
		JLabel label = new JLabel("\u623F\u79DF\uFF1A");
		label.setFont(new Font("黑体", Font.PLAIN, 17));
		label.setBounds(14, 221, 72, 18);
		frame.getContentPane().add(label);
		
		t = new JTextField(tt);
		t.setBounds(67, 23, 194, 35);
		frame.getContentPane().add(t);
		t.setColumns(10);
		
		a = new JTextField(aa);
		a.setColumns(10);
		a.setBounds(67, 91, 338, 35);
		frame.getContentPane().add(a);
		
		s = new JTextField(ss+"");
		s.setColumns(10);
		s.setBounds(67, 152, 194, 35);
		frame.getContentPane().add(s);
		
		p = new JTextField(pp+"");
		p.setColumns(10);
		p.setBounds(67, 214, 194, 35);
		frame.getContentPane().add(p);
		
		JButton button = new JButton("\u91CD\u65B0\u4E0A\u4F20\u7167\u7247");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				new FileOpen(str,hno);
				frame.setVisible(false);
				
			}
		});
		button.setBounds(86, 343, 234, 35);
		frame.getContentPane().add(button);
		
		JButton btnNewButton = new JButton("\u67E5\u770B\u623F\u5C4B\u7167\u7247");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				new Printphoto(str,path);
			}
		});
		btnNewButton.setBounds(86, 391, 234, 35);
		frame.getContentPane().add(btnNewButton);
		
		JLabel label_1 = new JLabel("\u5E73\u65B9\u7C73");
		label_1.setFont(new Font("黑体", Font.PLAIN, 17));
		label_1.setBounds(274, 160, 72, 18);
		frame.getContentPane().add(label_1);
		
		JLabel label_2 = new JLabel("\u5143");
		label_2.setFont(new Font("黑体", Font.PLAIN, 17));
		label_2.setBounds(274, 222, 72, 18);
		frame.getContentPane().add(label_2);
		frame.setVisible(true);
	}
	/*写这里，有可能printphoto也要重新写一个构造函数*/
	public HMchange(String str,String hno,String path) {
		frame = new JFrame("房屋编号："+hno);
		frame.setBounds(100, 100, 451, 486);
		frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		String sql="select * from 房屋信息表 where hno='"+hno+"'";
		String aa="",tt="";
		Connection conn;
		int pp=0,ss=0;
		try {
			conn = DriverManager.getConnection("jdbc:sqlserver://127.0.0.1:1433;DatabaseName=sechh","sa","123456");
			Statement pstmt=conn.createStatement();
	        ResultSet rs=pstmt.executeQuery(sql);
	        while(rs.next()) {
	        	aa=rs.getString(5);
	        	tt=rs.getString(4);
	        	ss=rs.getInt(6);
	        	pp=rs.getInt(7);
	        }
	        JButton button_1 = new JButton("\u4FDD\u5B58\u4FEE\u6539");
			button_1.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					try {
						pstmt.executeUpdate("update 房屋信息表 set varity='"+t.getText()+"'where hno='"+hno+"'");
						pstmt.executeUpdate("update 房屋信息表 set adress='"+a.getText()+"'where hno='"+hno+"'");
						pstmt.executeUpdate("update 房屋信息表 set hsquare='"+s.getText()+"'where hno='"+hno+"'");
						pstmt.executeUpdate("update 房屋信息表 set price='"+p.getText()+"'where hno='"+hno+"'");
						JOptionPane.showMessageDialog(frame, "修改成功");
						
					} catch (SQLException e1) {
						// TODO 自动生成的 catch 块
						e1.printStackTrace();
					}
					
				}
			});
			button_1.setBounds(86, 295, 234, 35);
			frame.getContentPane().add(button_1);
			pstmt.executeUpdate("update 房屋信息表 set photo ='"+path+"' where hno='"+hno+"'");
			JButton button = new JButton("\u91CD\u65B0\u4E0A\u4F20\u7167\u7247");
			button.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					new FileOpen(str,hno);
					frame.setVisible(false);
				}
			});
			button.setBounds(86, 343, 234, 35);
			frame.getContentPane().add(button);
		} catch (SQLException e1) {
			// TODO 自动生成的 catch 块
			e1.printStackTrace();
		}

		JLabel htype = new JLabel("\u7C7B\u578B\uFF1A");
		htype.setFont(new Font("黑体", Font.PLAIN, 17));
		htype.setBounds(14, 29, 72, 18);
		frame.getContentPane().add(htype);
		
		JLabel address = new JLabel("\u5730\u5740\uFF1A");
		address.setFont(new Font("黑体", Font.PLAIN, 17));
		address.setBounds(14, 93, 72, 18);
		frame.getContentPane().add(address);
		
		JLabel s1 = new JLabel("\u9762\u79EF\uFF1A");
		s1.setFont(new Font("黑体", Font.PLAIN, 17));
		s1.setBounds(14, 159, 72, 18);
		frame.getContentPane().add(s1);
		
		JLabel label = new JLabel("\u623F\u79DF\uFF1A");
		label.setFont(new Font("黑体", Font.PLAIN, 17));
		label.setBounds(14, 221, 72, 18);
		frame.getContentPane().add(label);
		
		t = new JTextField(tt);
		t.setBounds(67, 23, 194, 35);
		frame.getContentPane().add(t);
		t.setColumns(10);
		
		a = new JTextField(aa);
		a.setColumns(10);
		a.setBounds(67, 91, 338, 35);
		frame.getContentPane().add(a);
		
		s = new JTextField(ss+"");
		s.setColumns(10);
		s.setBounds(67, 152, 194, 35);
		frame.getContentPane().add(s);
		
		p = new JTextField(pp+"");
		p.setColumns(10);
		p.setBounds(67, 214, 194, 35);
		frame.getContentPane().add(p);
		
		JLabel label_1 = new JLabel("\u5E73\u65B9\u7C73");
		label_1.setFont(new Font("黑体", Font.PLAIN, 17));
		label_1.setBounds(274, 160, 72, 18);
		frame.getContentPane().add(label_1);
		
		JLabel label_2 = new JLabel("\u5143");
		label_2.setFont(new Font("黑体", Font.PLAIN, 17));
		label_2.setBounds(274, 222, 72, 18);
		frame.getContentPane().add(label_2);
		
		JButton btnNewButton = new JButton("\u67E5\u770B\u623F\u5C4B\u7167\u7247");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				new Printphoto(str,path);
			}
		});
		btnNewButton.setBounds(86, 391, 234, 35);
		frame.getContentPane().add(btnNewButton);
		
		frame.setVisible(true);
	}

}
