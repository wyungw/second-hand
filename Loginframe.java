package weizhenyang;

import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.FlowLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.*;
import java.awt.event.ActionEvent;
import javax.swing.JPasswordField;
import java.awt.Color;
import java.awt.Font;
import java.awt.SystemColor;
import javax.swing.JPanel;

public class Loginframe {

	private JFrame frame;
	private JTextField textField;
    private JComboBox comboBox;
    private String sql;
    private static String userno;
    private JPasswordField textField_1;
    
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Loginframe window = new Loginframe();
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
	public Loginframe() {
		initialize();	
	}
	public Loginframe(String s) {
		initialize();
		textField.setText(s);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	@SuppressWarnings("unchecked")
	private void initialize() {
		frame = new JFrame();
		frame.getContentPane().setFont(new Font("Arial Narrow", Font.PLAIN, 17));
		frame.setTitle("\u6B22\u8FCE\u4F7F\u7528\u623F\u5C4B\u4E2D\u4ECB\u7CFB\u7EDF");
		frame.setBounds(100, 100, 534, 465);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		textField = new JTextField();
		textField.setBounds(122, 219, 224, 41);
		frame.getContentPane().add(textField);
		textField.setColumns(10);
		userno=textField.getText().toString();
	    textField.addActionListener(new ActionListener() {
	    	public void actionPerformed(ActionEvent e) {
	    		userno=textField.getText().toString();
	    	
	    	}
	    });
		
		System.out.println(Loginframe.userno);
		JLabel label = new JLabel("\u7528\u6237\u7F16\u53F7\uFF1A");
		label.setFont(new Font("黑体", Font.PLAIN, 17));
		label.setForeground(Color.BLACK);
		label.setBounds(24, 229, 97, 18);
		frame.getContentPane().add(label);
		
		JLabel label_1 = new JLabel("\u7528\u6237\u5BC6\u7801\uFF1A");
		label_1.setFont(new Font("黑体", Font.PLAIN, 17));
		label_1.setBounds(24, 302, 102, 18);
		frame.getContentPane().add(label_1);
		
		JLabel label_2 = new JLabel("\u7528\u6237\u7C7B\u578B\uFF1A");
		label_2.setBackground(new Color(176, 196, 222));
		label_2.setFont(new Font("黑体", Font.PLAIN, 17));
		label_2.setBounds(24, 162, 97, 18);
		frame.getContentPane().add(label_2);
		
		comboBox = new JComboBox();
		comboBox.setFont(new Font("黑体", Font.PLAIN, 17));
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"\u623F\u4E1C", "\u79DF\u5BA2"}));
		comboBox.setBounds(122, 154, 224, 41);
		frame.getContentPane().add(comboBox);
		
		JButton button = new JButton("\u767B\u5F55");
		button.setFont(new Font("幼圆", Font.PLAIN, 17));
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				 System.out.println(textField.getText()+"\t"+textField_1.getText());
				    if(validate(textField.getText(),textField_1.getText())) {
				    	JOptionPane.showMessageDialog(frame,"登录成功");
				    	frame.setVisible(false);
						if(comboBox.getSelectedIndex()==0) 					
							new FJframe(textField.getText());		
						else if(comboBox.getSelectedIndex()==1) 
							new ZJframe(textField.getText());
				    }
				    else {
				    	JOptionPane.showMessageDialog(frame,"登录失败，请确认用户编号和密码是否正确");
				    	
				    }
			}
		});
		button.setBounds(186, 332, 117, 49);
		frame.getContentPane().add(button);
		
		JButton button_1 = new JButton("\u6CE8\u518C\u4E3A\u623F\u4E1C");
		button_1.setFont(new Font("幼圆", Font.PLAIN, 17));
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
					new RegisterFrame();
				
			}
		});
		button_1.setBounds(378, 212, 124, 48);
		frame.getContentPane().add(button_1);
		
		textField_1 = new JPasswordField();
		textField_1.setBounds(122, 279, 224, 41);
		frame.getContentPane().add(textField_1);
		
		JButton button_2 = new JButton("\u6CE8\u518C\u4E3A\u79DF\u5BA2");
		button_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				new RegisterFrame1();
			}
		});
		button_2.setFont(new Font("幼圆", Font.PLAIN, 17));
		button_2.setBounds(378, 285, 124, 49);
	    ImageIcon ic=new ImageIcon("E:\\平时用文件\\大二\\数据库课程设计\\11.jpg");	 
	    JLabel l=new JLabel(ic);	  
	    l.setBounds(0,0,ic.getIconWidth(),ic.getIconHeight());	  	    
	    frame.getLayeredPane().add(l,new Integer(Integer.MIN_VALUE));	  
	    ((JComponent) frame.getContentPane()).setOpaque(false);//设置透明	
		frame.getContentPane().add(button_2);
		
		JLabel label_3 = new JLabel("\u6B22\u8FCE\u4F7F\u7528\u4E8C\u624B\u623F\u4E2D\u4ECB\u7CFB\u7EDF");
		label_3.setFont(new Font("华文楷体", Font.PLAIN, 39));
		label_3.setBounds(34, 34, 455, 57);
		frame.getContentPane().add(label_3);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(240, 255, 255));
		panel.setBounds(14, 114, 488, 274);
		frame.getContentPane().add(panel);
		frame.setVisible(true);
	}
	private boolean validate(String userno,String userpass) {
		if(comboBox.getSelectedIndex()==0) {
			sql="select *from 房东信息表 where 房东信息表.fno='"+userno+"'and 房东信息表.fpassword='"+userpass+"'";
		}
		
		else if(comboBox.getSelectedIndex()==1) {
			sql="select *from 租客信息表 where 租客信息表.zno='"+userno+"'and 租客信息表.zpassword='"+userpass+"'";
		}
			
		try (
			Connection conn=DriverManager.getConnection("jdbc:sqlserver://127.0.0.1:1433;DatabaseName=sechh","sa","123456");
	        Statement pstmt=conn.createStatement();
	        ResultSet rs=pstmt.executeQuery(sql);
	    ){
	    	if(rs.next())
	    		return true;
	    } catch (SQLException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}return false;
	}
}
