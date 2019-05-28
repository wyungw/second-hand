package weizhenyang;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;


public class FMchange {

	private JFrame frame;
	private JTextField n;
	private JTextField s;
	private JTextField y;
	private JTextField p;
	private JTextField d;
	private JTextField m;
	private JLabel label_1;
	private JLabel label_2;
	private JLabel lblNewLabel;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FMchange window = new FMchange("2002");
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
	public FMchange(String str) {
		frame = new JFrame("修改个人信息");
		frame.setBounds(100, 100, 483, 366);
		frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		String sql="select * from 房东信息表 where fno='"+str+"'";
		String fname="",fsex="",fphone="";
		Date fbir=Date.valueOf("1000-1-1");
		System.out.println(fbir); 
		Connection conn;
		try {
			conn = DriverManager.getConnection("jdbc:sqlserver://127.0.0.1:1433;DatabaseName=sechh","sa","123456");
			Statement pstmt=conn.createStatement();
            ResultSet rs=pstmt.executeQuery(sql);
            while(rs.next()) {
            	fname=rs.getString(4);
            	fsex=rs.getString(5);
            	fphone=rs.getString(7);
            	if(rs.getDate(6)!=null)
            	    fbir=rs.getDate(6);
            }

    		JLabel name = new JLabel("\u59D3\u540D\uFF1A");
    		name.setFont(new Font("黑体", Font.PLAIN, 17));
    		name.setBounds(14, 17, 72, 18);
    		frame.getContentPane().add(name);
    		
    		JLabel sex = new JLabel("\u6027\u522B\uFF1A");
    		sex.setFont(new Font("黑体", Font.PLAIN, 17));
    		sex.setBounds(14, 71, 72, 18);
    		frame.getContentPane().add(sex);
    		
    		JLabel bir = new JLabel("\u51FA\u751F\u65E5\u671F\uFF1A");
    		bir.setFont(new Font("黑体", Font.PLAIN, 17));
    		bir.setBounds(14, 134, 117, 18);
    		frame.getContentPane().add(bir);
    		
    		JLabel phone = new JLabel("\u7535\u8BDD\uFF1A");
    		phone.setFont(new Font("黑体", Font.PLAIN, 17));
    		phone.setBounds(14, 214, 72, 18);
    		frame.getContentPane().add(phone);
    		
    		n = new JTextField(fname);
    		n.setFont(new Font("楷体", Font.PLAIN, 17));
    		n.setBounds(76, 11, 192, 33);
    		frame.getContentPane().add(n);
    		n.setColumns(10);
    		
    		s = new JTextField(fsex);
    		s.setFont(new Font("楷体", Font.PLAIN, 17));
    		s.setColumns(10);
    		s.setBounds(76, 65, 192, 33);
    		frame.getContentPane().add(s);
    		
    	    y = new JTextField(fbir.toString().split("-")[0]);
    		y.setFont(new Font("楷体", Font.PLAIN, 17));
    		y.setColumns(10);
    		y.setBounds(100, 122, 55, 33);
    		frame.getContentPane().add(y);
    		System.out.println(fbir);
    		
    		p = new JTextField(fphone);
    		p.setFont(new Font("楷体", Font.PLAIN, 17));
    		p.setColumns(10);
    		p.setBounds(76, 207, 192, 33);
    		frame.getContentPane().add(p);
    		
    		JButton btnNewButton = new JButton("\u786E\u8BA4\u4FEE\u6539");
    		btnNewButton.addActionListener(new ActionListener() {
    			public void actionPerformed(ActionEvent arg0) {
    				try {
						pstmt.executeUpdate("update 房东信息表 set fname='"+n.getText()+"'where fno='"+str+"'");
						pstmt.executeUpdate("update 房东信息表 set fsex='"+s.getText()+"'where fno='"+str+"'");
						pstmt.executeUpdate("update 房东信息表 set fphone='"+p.getText()+"'where fno='"+str+"'");
						if(y.getText().length()!=0) {
							  String newbir=y.getText()+"-"+m.getText()+"-"+d.getText();    
					    Date dbir=Date.valueOf(newbir);
					    pstmt.executeUpdate("update 房东信息表 set fbir='"+dbir+"'where fno='"+str+"'");   
						}
					   
					    JOptionPane.showMessageDialog(frame, "修改成功");
					    frame.setVisible(false);
					    //new Fmess(str);
    				} catch (SQLException e) {
						// TODO 自动生成的 catch 块
						e.printStackTrace();
					}
    			}
    		});
    		btnNewButton.setFont(new Font("黑体", Font.PLAIN, 17));
    		btnNewButton.setBounds(132, 265, 168, 33);
    		frame.getContentPane().add(btnNewButton);
    		
    		JLabel label = new JLabel("\u5E74");
    		label.setFont(new Font("黑体", Font.PLAIN, 17));
    		label.setBounds(169, 135, 72, 18);
    		frame.getContentPane().add(label);
    		
    	    d = new JTextField(fbir.toString().split("-")[2]);
    		d.setFont(new Font("楷体", Font.PLAIN, 17));
    		d.setColumns(10);
    		d.setBounds(276, 122, 55, 33);
    		frame.getContentPane().add(d);
    		
    		m = new JTextField(fbir.toString().split("-")[1]);
    		m.setFont(new Font("楷体", Font.PLAIN, 17));
    		m.setBounds(188, 122, 53, 33);
    		frame.getContentPane().add(m);
    		m.setColumns(10);
    		
    		label_1 = new JLabel("\u6708");
    		label_1.setFont(new Font("黑体", Font.PLAIN, 17));
    		label_1.setBounds(255, 135, 72, 18);
    		frame.getContentPane().add(label_1);
    		
    		label_2 = new JLabel("\u65E5");
    		label_2.setFont(new Font("黑体", Font.PLAIN, 17));
    		label_2.setBounds(341, 134, 72, 18);
    		frame.getContentPane().add(label_2);
    		
    		lblNewLabel = new JLabel("\u5F53\u5E74\u4EFD\u4E3A1000\u65F6\u4E3A\u7CFB\u7EDF\u9ED8\u8BA4\u5E74\u4EFD\uFF0C\u4E0D\u586B\u8BF7\u6E05\u7A7A");
    		lblNewLabel.setForeground(Color.RED);
    		lblNewLabel.setFont(new Font("新宋体", Font.PLAIN, 17));
    		lblNewLabel.setBounds(100, 165, 439, 20);
    		frame.getContentPane().add(lblNewLabel);
    		frame.setVisible(true);
		} catch (SQLException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
		
		
	}
}
