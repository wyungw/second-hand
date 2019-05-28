package weizhenyang;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.awt.event.ActionEvent;
import java.awt.Font;

public class AddHouseF {

	private JFrame frame;
	private JTextField a;
	private JTextField s;
	private JTextField m;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AddHouseF window = new AddHouseF("2001");
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
	public AddHouseF(String str) {
		frame = new JFrame();
		frame.setTitle("房东"+str);
		frame.setBounds(100, 100, 482, 545);
		frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("\u623F\u5C4B\u6237\u578B\uFF1A");
		lblNewLabel.setFont(new Font("黑体", Font.PLAIN, 17));
		lblNewLabel.setBounds(14, 13, 98, 18);
		frame.getContentPane().add(lblNewLabel);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setFont(new Font("黑体", Font.PLAIN, 17));
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"\u4E00\u5C45", "\u4E24\u5C45", "\u4E09\u5C45", "\u5176\u4ED6"}));
		comboBox.setBounds(100, 10, 80, 33);
		frame.getContentPane().add(comboBox);
		
		JLabel label = new JLabel("\u5730\u5740:");
		label.setFont(new Font("黑体", Font.PLAIN, 17));
		label.setBounds(14, 59, 72, 18);
		frame.getContentPane().add(label);
		
		a = new JTextField();
		a.setBounds(73, 52, 360, 33);
		frame.getContentPane().add(a);
		a.setColumns(10);
		
		JLabel label_1 = new JLabel("\u9762\u79EF\uFF1A");
		label_1.setFont(new Font("黑体", Font.PLAIN, 17));
		label_1.setBounds(14, 98, 72, 18);
		frame.getContentPane().add(label_1);
		
		s = new JTextField();
		s.setBounds(73, 95, 66, 33);
		frame.getContentPane().add(s);
		s.setColumns(10);
		
		JLabel lblM = new JLabel("\u5E73\u65B9\u7C73");
		lblM.setFont(new Font("黑体", Font.PLAIN, 17));
		lblM.setBounds(145, 98, 72, 18);
		frame.getContentPane().add(lblM);
		
		JLabel label_2 = new JLabel("\u79DF\u91D1\uFF1A");
		label_2.setFont(new Font("黑体", Font.PLAIN, 17));
		label_2.setBounds(14, 137, 72, 18);
		frame.getContentPane().add(label_2);
		
		m = new JTextField();
		m.setColumns(10);
		m.setBounds(73, 134, 66, 33);
		frame.getContentPane().add(m);
		
		JLabel label_3 = new JLabel("\u5143");
		label_3.setFont(new Font("黑体", Font.PLAIN, 17));
		label_3.setBounds(145, 137, 72, 18);
		frame.getContentPane().add(label_3);
		
		JButton button = new JButton("\u4E0A\u4F20\u623F\u5C4B\u56FE\u7247");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				new FileOpen(str);
				frame.setVisible(false);
				
			}
		});
		button.setFont(new Font("黑体", Font.PLAIN, 17));
		button.setBounds(85, 276, 262, 51);
		frame.getContentPane().add(button);
		
		JButton button_1 = new JButton("\u67E5\u770B\u5DF2\u4E0A\u4F20\u56FE\u7247");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		button_1.setFont(new Font("黑体", Font.PLAIN, 17));
		button_1.setBounds(85, 357, 256, 51);
		
		frame.getContentPane().add(button_1);
		
		JButton button_2 = new JButton("\u4FDD\u5B58\u5E76\u63D0\u4EA4");
		button_2.setFont(new Font("黑体", Font.PLAIN, 17));
		button_2.addActionListener(new ActionListener() {
			String sqll="select * from 房屋信息表";		
			int number=1;
			public void actionPerformed(ActionEvent arg0) {
				Connection conn;
				try {
					conn = DriverManager.getConnection("jdbc:sqlserver://127.0.0.1:1433;DatabaseName=sechh","sa","123456");
				    Statement stmt=conn.createStatement();
				    ResultSet rset=stmt.executeQuery(sqll);	
				    String sql="insert into 房屋信息表 values(?,?,?,?,?,?,?,null)";	
				    PreparedStatement  pstmt=conn.prepareStatement(sql);
				    while(rset.next()) {
					    number++;
				    }		
				    pstmt.setString(2, str);
				    pstmt.setString(1, Integer.toString(number));
				    pstmt.setString(3, "待租");
				    pstmt.setString(4, comboBox.getSelectedItem().toString());
				    pstmt.setString(5, a.getText());
				    pstmt.setInt(6, Integer.parseInt(s.getText()));
				    pstmt.setInt(7, Integer.parseInt(m.getText()));
				    pstmt.executeUpdate();			  
				    pstmt.close();
				    JOptionPane.showMessageDialog(frame, "添加成功");
				} catch (SQLException e) {
					// TODO 自动生成的 catch 块
					e.printStackTrace();
				}
				
			}
		});
		button_2.setBackground(new Color(100, 149, 237));
		button_2.setBounds(83, 196, 264, 51);
		frame.getContentPane().add(button_2);
		
		JButton button_3 = new JButton("\u8FD4\u56DE");
		button_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.setVisible(false);
				new Managehouse(str);
			}
		});
		button_3.setFont(new Font("黑体", Font.PLAIN, 17));
		button_3.setBounds(85, 434, 256, 51);
		frame.getContentPane().add(button_3);
		frame.setVisible(true);
	}
	/*************参考这里*/
	public AddHouseF(String str,String path) {
		String sqll="select top 1 * from 房屋信息表 where fno='"+str+"' order by cast(hno as int) desc";
		Connection conn;
		String varity="";
		String adress="";
		int hsquare=0,price=0;			
		try {
			conn = DriverManager.getConnection("jdbc:sqlserver://127.0.0.1:1433;DatabaseName=sechh","sa","123456");
		    Statement stmt=conn.createStatement();
	        ResultSet rset=stmt.executeQuery(sqll);	
	        while(rset.next()) {
            	varity=rset.getString(4);
            	adress=rset.getString(5);
            	hsquare=rset.getInt(6);
            	price=rset.getInt(7);		    
            }
	        System.out.println(path);
	        String a ="(select top 1 hno from 房屋信息表 where fno='"+str+"' order by cast( hno as int )desc)";
	        stmt.executeUpdate("update 房屋信息表 set photo='"+path+"'where hno="+a);
	    } catch (SQLException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
		frame = new JFrame();
		frame.setTitle("房东"+str);
		frame.setBounds(100, 100, 482, 545);
		frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("\u623F\u5C4B\u6237\u578B\uFF1A");
		lblNewLabel.setFont(new Font("黑体", Font.PLAIN, 17));
		lblNewLabel.setBounds(14, 13, 98, 18);
		frame.getContentPane().add(lblNewLabel);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setFont(new Font("黑体", Font.PLAIN, 17));
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"\u4E00\u5C45", "\u4E24\u5C45", "\u4E09\u5C45", "\u5176\u4ED6"}));
		comboBox.setSelectedItem(varity);
		comboBox.setBounds(100, 10, 80, 33);
		frame.getContentPane().add(comboBox);
		
		JLabel label = new JLabel("\u5730\u5740:");
		label.setFont(new Font("黑体", Font.PLAIN, 17));
		label.setBounds(14, 59, 72, 18);
		frame.getContentPane().add(label);
		
		a = new JTextField(adress);
		a.setBounds(73, 52, 360, 33);
		frame.getContentPane().add(a);
		a.setColumns(10);
		
		JLabel label_1 = new JLabel("\u9762\u79EF\uFF1A");
		label_1.setFont(new Font("黑体", Font.PLAIN, 17));
		label_1.setBounds(14, 98, 72, 18);
		frame.getContentPane().add(label_1);
		
		s = new JTextField(hsquare+"");
		s.setBounds(73, 95, 66, 33);
		frame.getContentPane().add(s);
		s.setColumns(10);
		
		JLabel lblM = new JLabel("\u5E73\u65B9\u7C73");
		lblM.setFont(new Font("黑体", Font.PLAIN, 17));
		lblM.setBounds(145, 98, 72, 18);
		frame.getContentPane().add(lblM);
		
		JLabel label_2 = new JLabel("\u79DF\u91D1\uFF1A");
		label_2.setFont(new Font("黑体", Font.PLAIN, 17));
		label_2.setBounds(14, 137, 72, 18);
		frame.getContentPane().add(label_2);
		
		m = new JTextField(price+"");
		m.setColumns(10);
		m.setBounds(73, 134, 66, 33);
		frame.getContentPane().add(m);
		
		JLabel label_3 = new JLabel("\u5143");
		label_3.setFont(new Font("黑体", Font.PLAIN, 17));
		label_3.setBounds(145, 137, 72, 18);
		frame.getContentPane().add(label_3);
		
		JButton button = new JButton("\u4E0A\u4F20\u623F\u5C4B\u56FE\u7247");
		button.setFont(new Font("黑体", Font.PLAIN, 17));
		button.setBounds(85, 276, 262, 51);
		frame.getContentPane().add(button);
		
		JButton button_1 = new JButton("\u67E5\u770B\u5DF2\u4E0A\u4F20\u56FE\u7247");
		button_1.setFont(new Font("黑体", Font.PLAIN, 17));
		button_1.setBounds(85, 357, 256, 51);
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				new Printphoto(str,path);
			}
		});
		frame.getContentPane().add(button_1);
		
		JButton button_2 = new JButton("\u4FDD\u5B58\u5E76\u63D0\u4EA4");
		button_2.setFont(new Font("黑体", Font.PLAIN, 17));
		
		button_2.setBackground(new Color(100, 149, 237));
		button_2.setBounds(83, 196, 264, 51);
		frame.getContentPane().add(button_2);
		
		JButton button_3 = new JButton("\u8FD4\u56DE");
		button_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.setVisible(false);
				new Managehouse(str);
			}
		});
		
		button_3.setFont(new Font("黑体", Font.PLAIN, 17));
		button_3.setBounds(85, 434, 256, 51);
		button_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.setVisible(false);
				new Managehouse(str);
			}
		});
		frame.getContentPane().add(button_3);
		frame.setVisible(true);
	}

}
