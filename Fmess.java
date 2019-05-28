package weizhenyang;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.Font;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class Fmess {

	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Fmess window = new Fmess("2001");
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public Fmess(String str) {
		frame = new JFrame(str);
	
		frame.setTitle("房东"+str+"个人资料");
		frame.setBounds(100, 100, 452, 372);
		frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		String sql="select * from 房东信息表 where fno='"+str+"'";
		String fname="",fsex="",fphone="";
		Date bir=new Date(2000-2-2);
		Connection conn;
		try {
			conn = DriverManager.getConnection("jdbc:sqlserver://127.0.0.1:1433;DatabaseName=sechh","sa","123456");
			Statement pstmt=conn.createStatement();
            ResultSet rs=pstmt.executeQuery(sql);
            while(rs.next()) {
            	fname=rs.getString(4);
            	fsex=rs.getString(5);
            	fphone=rs.getString(7);
            	bir=rs.getDate(6);
            }
            JLabel number = new JLabel("用户编号："+str);
    		number.setFont(new Font("黑体", Font.PLAIN, 17));
    		number.setBounds(31, 31, 195, 18);
    		frame.getContentPane().add(number);
    		
    		JLabel name = new JLabel("姓名："+fname);
    		name.setFont(new Font("黑体", Font.PLAIN, 17));
    		name.setBounds(31, 83, 225, 18);
    		frame.getContentPane().add(name);
    		
    		JLabel sex = new JLabel("\u6027\u522B\uFF1A"+fsex);
    		sex.setFont(new Font("黑体", Font.PLAIN, 17));
    		sex.setBounds(31, 128, 72, 18);
    		frame.getContentPane().add(sex);
    		
    		JLabel phone = new JLabel("\u7535\u8BDD\u53F7\u7801\uFF1A"+fphone);
    		phone.setFont(new Font("黑体", Font.PLAIN, 17));
    		phone.setBounds(31, 176, 225, 18);
    		frame.getContentPane().add(phone);
    		
    		JLabel fbir = new JLabel("\u51FA\u751F\u65E5\u671F\uFF1A"+bir);
    		fbir.setFont(new Font("黑体", Font.PLAIN, 17));
    		fbir.setBounds(31, 222, 253, 18);
    		frame.getContentPane().add(fbir);
    		
    		JButton btnNewButton = new JButton("\u7F16\u8F91\u4E2A\u4EBA\u4FE1\u606F");
    		btnNewButton.addActionListener(new ActionListener() {
    			public void actionPerformed(ActionEvent arg0) {
    				new FMchange(str);
    			}
    		});
    		btnNewButton.setFont(new Font("黑体", Font.PLAIN, 17));
    		btnNewButton.setBounds(280, 27, 152, 60);
    		frame.getContentPane().add(btnNewButton);
    		
    		JButton button = new JButton("\u4FEE\u6539\u5BC6\u7801");
    		button.addActionListener(new ActionListener() {
    			public void actionPerformed(ActionEvent arg0) {
    				new Fcpass(str);
    			}
    		});
    		button.setFont(new Font("黑体", Font.PLAIN, 17));
    		button.setBounds(280, 124, 152, 60);
    		frame.getContentPane().add(button);
    		
    		JButton button_1 = new JButton("\u8FD4\u56DE");
    		button_1.addActionListener(new ActionListener() {
    			public void actionPerformed(ActionEvent arg0) {
    				frame.setVisible(false);
    			}
    		});
    		button_1.setFont(new Font("黑体", Font.PLAIN, 17));
    		button_1.setBounds(155, 285, 113, 27);
    		frame.getContentPane().add(button_1);
		} catch (SQLException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
        
		frame.setVisible(true);
	}
}
