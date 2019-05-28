package weizhenyang;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Zcpass {

	private JFrame frame;
	private JTextField old;
	private JTextField newp1;
	private JTextField newp2;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Zcpass window = new Zcpass("1001");
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
	public Zcpass(String str) {
		frame = new JFrame();
		frame.setTitle("\u4FEE\u6539\u5BC6\u7801");
		frame.setBounds(100, 100, 437, 358);
		frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel label = new JLabel("\u8F93\u5165\u65E7\u5BC6\u7801\uFF1A");
		label.setFont(new Font("黑体", Font.PLAIN, 17));
		label.setBounds(14, 58, 140, 18);
		frame.getContentPane().add(label);
		String sql="select * from 租客信息表 where zno='"+str+"'";
		
		old = new JTextField();
		old.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {
				Connection conn;
				String pass="";
				try {
					conn = DriverManager.getConnection("jdbc:sqlserver://127.0.0.1:1433;DatabaseName=sechh","sa","123456");
					Statement pstmt=conn.createStatement();
	                ResultSet rs=pstmt.executeQuery(sql);
	                while(rs.next()) {	        
	                	pass=rs.getString(2);
	                }
	                if(false==(pass.equals(old.getText())))	{
	                	JOptionPane.showMessageDialog(frame, "密码错误，请重新输入");
	                	old.setText("");
	                }	                	
				} catch (SQLException e1) {
					// TODO 自动生成的 catch 块
					e1.printStackTrace();
				}
				
			}
		});
		old.setBounds(122, 49, 214, 38);
		frame.getContentPane().add(old);
		old.setColumns(10);
		
		JLabel label_1 = new JLabel("\u8F93\u5165\u65B0\u5BC6\u7801\uFF1A");
		label_1.setFont(new Font("黑体", Font.PLAIN, 17));
		label_1.setBounds(14, 131, 107, 18);
		frame.getContentPane().add(label_1);
		
		newp1 = new JTextField();
		newp1.setColumns(10);
		newp1.setBounds(122, 122, 214, 38);
		frame.getContentPane().add(newp1);
		
		JLabel label_2 = new JLabel("\u786E\u8BA4\u65B0\u5BC6\u7801\uFF1A");
		label_2.setFont(new Font("黑体", Font.PLAIN, 17));
		label_2.setBounds(14, 201, 107, 18);
		frame.getContentPane().add(label_2);
		
		newp2 = new JTextField();
		newp2.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent arg0) {
				if(false==(newp1.getText().equals(newp2.getText()))) {
					JOptionPane.showMessageDialog(frame, "两次输入密码不同");
				}
			}
		});
		newp2.setColumns(10);
		newp2.setBounds(122, 192, 214, 38);
		frame.getContentPane().add(newp2);
		
		JButton button = new JButton("\u786E\u8BA4\u4FEE\u6539");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Connection conn;
				try {
					conn = DriverManager.getConnection("jdbc:sqlserver://127.0.0.1:1433;DatabaseName=sechh","sa","123456");
					Statement pstmt=conn.createStatement();
                   // ResultSet rs=pstmt.executeQuery(sql);
                    pstmt.executeUpdate("update 租客信息表 set zpassword='"+newp1.getText()+"'where zno='"+str+"'");
                    JOptionPane.showMessageDialog(frame, "修改成功");
                    frame.setVisible(false);
				} catch (SQLException e) {
					// TODO 自动生成的 catch 块
					e.printStackTrace();
				}
				
			}
		});
		button.setFont(new Font("黑体", Font.PLAIN, 17));
		button.setBounds(148, 257, 127, 38);
		frame.getContentPane().add(button);
		frame.setVisible(true);
	}

}
