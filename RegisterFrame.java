package weizhenyang;

import java.awt.EventQueue;

import javax.swing.*;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.awt.event.ActionEvent;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;

public class RegisterFrame {

	private JFrame frame;
	private JTextField name;
	private JTextField year;
	private JTextField phone;
	private JTextField pass;
	private JTextField pass1;
	private JTextField pas1;
	private JTextField pas2;
	private JComboBox<Integer> month=new JComboBox<Integer>();
    private JComboBox<Integer> day=new JComboBox<Integer>();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					RegisterFrame window = new RegisterFrame();
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
	public RegisterFrame() {
		frame = new JFrame();
		frame.setTitle("\u623F\u4E1C\u6CE8\u518C");
		frame.setBounds(100, 100, 566, 505);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel label = new JLabel("\u59D3\u540D\uFF1A");
		label.setBounds(14, 46, 72, 18);
		frame.getContentPane().add(label);
		
		name = new JTextField();
		name.setBounds(100, 36, 82, 34);
		frame.getContentPane().add(name);
		name.setColumns(10);
		
		JLabel label_1 = new JLabel("\u6027\u522B\uFF1A");
		label_1.setBounds(14, 80, 72, 18);
		frame.getContentPane().add(label_1);
		
		JComboBox sex = new JComboBox();
		sex.setModel(new DefaultComboBoxModel(new String[] {"\u7537", "\u5973"}));
		sex.setBounds(100, 77, 67, 24);
		frame.getContentPane().add(sex);
		
		JLabel label_2 = new JLabel("\u51FA\u751F\u65E5\u671F\uFF1A");
		label_2.setBounds(14, 114, 95, 29);
		frame.getContentPane().add(label_2);
		
		year = new JTextField();
		year.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try
				{   
					int y = Integer.parseInt(year.getText());
					System.out.println("输入的年份为:"+y+"年");
					int m = month.getSelectedIndex()+1;
					switch(m) {
					case 1:				
					case 3:
					case 5:
					case 7:
					case 8:
					case 10:
					case 12:{	//1 3 5 7 8 10 12月
						if(day.getItemAt(28)!=null) {
							day.removeItemAt(28);
							day.insertItemAt(29, 28);	
						}	
						else
							day.addItem(29);	
						
						if(day.getItemAt(29)!=null) {
							day.removeItemAt(29);
							day.insertItemAt(30, 29);	
						}
						else
							day.addItem(30);
						
						if(day.getItemAt(30)!=null) {
							day.removeItemAt(30);
							day.insertItemAt(31, 30);
						}
						else
							day.addItem(31);
			
					    break;}
					case 2:{
						if(y%4==0&&y%100!=0||y%400==0) {//闰年2月
							System.out.println("该年为闰年");
							if(day.getItemAt(28)!=null) {
								day.removeItemAt(28);
								day.insertItemAt(29, 28);	
							}	
							else
								day.addItem(29);	
									
							while(day.getItemAt(29)!=null) {
								day.removeItemAt(29);
							}
												
								       	
						}
						else {//平年2月
						    while(day.getItemAt(28)!=null) {
								day.removeItemAt(28);	
							}						
						}
						break;	
					}
				
					case 4:
					case 6:
					case 9:
					case 11:{//4 6 9 11月
						if(day.getItemAt(28)!=null) {
							day.removeItemAt(28);
							day.insertItemAt(29, 28);	
						}	
						else
							day.addItem(29);	
						
						if(day.getItemAt(29)!=null) {
							day.removeItemAt(29);
							day.insertItemAt(30, 29);	
						}
						else
							day.addItem(30);
						
						while(day.getItemAt(30)!=null) {
							day.removeItemAt(30);
						}
					    break;	
					    }
					}	
				}
				catch(NumberFormatException n)
				{
					 System.out.println("输入应为数字");
				}
		        finally {}
			}
		});
		year.setBounds(98, 114, 61, 29);
		frame.getContentPane().add(year);
		year.setColumns(10);
		
		JLabel label_3 = new JLabel("\u5E74");
		label_3.setBounds(162, 119, 72, 18);
		frame.getContentPane().add(label_3);
		
		JLabel label_5 = new JLabel("\u65E5");
		label_5.setBounds(330, 119, 37, 18);
		frame.getContentPane().add(label_5);
		
		JLabel label_6 = new JLabel("\u7535\u8BDD\uFF1A");
		label_6.setBounds(14, 159, 72, 18);
		frame.getContentPane().add(label_6);
		
		phone = new JTextField();
		phone.setBounds(98, 156, 153, 34);
		frame.getContentPane().add(phone);
		phone.setColumns(10);
		
		JLabel label_7 = new JLabel("\u8BBE\u7F6E\u8D26\u6237\u5BC6\u7801\uFF1A");
		label_7.setBounds(14, 225, 134, 18);
		frame.getContentPane().add(label_7);
		
		pass = new JTextField();
		pass.setBounds(141, 217, 175, 34);
		frame.getContentPane().add(pass);
		pass.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("\u786E\u5B9A\u8D26\u6237\u5BC6\u7801\uFF1A");
		lblNewLabel.setBounds(14, 275, 115, 18);
		frame.getContentPane().add(lblNewLabel);
		
		pass1 = new JTextField();
		pass1.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent arg0) {
				if(false==(pass.getText().equals(pass1.getText()))) {
					JOptionPane.showMessageDialog(frame, "两次输入密码不同");
				}
			}
		});
		pass1.setColumns(10);
		pass1.setBounds(141, 264, 175, 34);
		frame.getContentPane().add(pass1);
		
		JLabel label_8 = new JLabel("\u8BBE\u7F6E4\u4F4D\u6570\u5B57\u63D0\u73B0\u5BC6\u7801\uFF1A");
		label_8.setBounds(14, 321, 187, 18);
		frame.getContentPane().add(label_8);
		
		JLabel label_9 = new JLabel("\u786E\u5B9A4\u4F4D\u6570\u5B57\u63D0\u73B0\u5BC6\u7801\uFF1A");
		label_9.setBounds(14, 366, 168, 18);
		frame.getContentPane().add(label_9);
		
		pas1 = new JTextField();
		pas1.addFocusListener(new FocusAdapter() {
			@Override
			
			public void focusLost(FocusEvent e) {
				if(pas1.getText().length()!=4)
					JOptionPane.showMessageDialog(frame, "提现密码必须为4位");
			}
		});
		pas1.setColumns(10);
		pas1.setBounds(171, 311, 145, 34);
		frame.getContentPane().add(pas1);
		
		JButton button = new JButton("\u4FDD\u5B58\u5E76\u63D0\u4EA4");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String sqll="select * from 房东信息表";		
				int number=0;
				try {				    
					Connection conn=DriverManager.getConnection("jdbc:sqlserver://127.0.0.1:1433;DatabaseName=sechh","sa","123456");
					Statement stmt=conn.createStatement();
					ResultSet rset=stmt.executeQuery(sqll);	
					String sql="insert into 房东信息表 values(?,?,?,?,?,?,?,?)";	
					PreparedStatement  pstmt=conn.prepareStatement(sql);
					while(rset.next()) {
						number++;
					}		
					number=number+2001;
					System.out.println(number);
					pstmt.setString(1, Integer.toString(number));
					pstmt.setString(2, pass.getText());//密码
				    pstmt.setString(3, pas1.getText());//提现密码
				    pstmt.setString(4, name.getText());//姓名
				    pstmt.setString(5, (String) sex.getSelectedItem());//性别
				    pstmt.setString(7, phone.getText());//电话
				    pstmt.setInt(8, 0);
					if(year.getText().length()==0) {
					    pstmt.setDate(6, null);
					}
					else if(year.getText().length()!=0){
						String bir=year.getText()+"-"+month.getSelectedItem()+"-"+day.getSelectedItem();
						Date dbir=Date.valueOf(bir);	
					    pstmt.setDate(6, dbir);
					}
				    pstmt.executeUpdate();			  
				    pstmt.close();
				    int n= JOptionPane.showConfirmDialog(null, "注册成功！用户编号为:"+number+".  要使用当前编号登录吗？","房东注册", JOptionPane.YES_NO_OPTION);
				    if(n==JOptionPane.YES_OPTION) {
				    	frame.setVisible(false);
				    	new Loginframe(Integer.toString(number));
				    }
				} catch (SQLException e) {
					// TODO 自动生成的 catch 块
					e.printStackTrace();
				}
				
			}
		});
		button.setBounds(171, 418, 113, 27);
		frame.getContentPane().add(button);
		
		JLabel label_10 = new JLabel("*");
		label_10.setForeground(Color.RED);
		label_10.setBounds(212, 46, 72, 18);
		frame.getContentPane().add(label_10);
		
		JLabel label_11 = new JLabel("*");
		label_11.setForeground(Color.RED);
		label_11.setBounds(195, 80, 72, 18);
		frame.getContentPane().add(label_11);
		
		month = new JComboBox();
		month.setModel(new DefaultComboBoxModel(new String[] {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12"}));
		month.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try
				{   
					int y = Integer.parseInt(year.getText());
					System.out.println("输入的年份为:"+y+"年");
					int m = month.getSelectedIndex()+1;
					switch(m) {
					case 1:				
					case 3:
					case 5:
					case 7:
					case 8:
					case 10:
					case 12:{	//1 3 5 7 8 10 12月
						if(day.getItemAt(28)!=null) {
							day.removeItemAt(28);
							day.insertItemAt(29, 28);	
						}	
						else
							day.addItem(29);	
						
						if(day.getItemAt(29)!=null) {
							day.removeItemAt(29);
							day.insertItemAt(30, 29);	
						}
						else
							day.addItem(30);
						
						if(day.getItemAt(30)!=null) {
							day.removeItemAt(30);
							day.insertItemAt(31, 30);
						}
						else
							day.addItem(31);
			
					    break;}
					case 2:{
						if(y%4==0&&y%100!=0||y%400==0) {//闰年2月
							System.out.println("该年为闰年");
							if(day.getItemAt(28)!=null) {
								day.removeItemAt(28);
								day.insertItemAt(29, 28);	
							}	
							else
								day.addItem(29);	
									
							while(day.getItemAt(29)!=null) {
								day.removeItemAt(29);
							}
												
								       	
						}
						else {//平年2月
						    while(day.getItemAt(28)!=null) {
								day.removeItemAt(28);	
							}						
						}
						break;	
					}
				
					case 4:
					case 6:
					case 9:
					case 11:{//4 6 9 11月
						if(day.getItemAt(28)!=null) {
							day.removeItemAt(28);
							day.insertItemAt(29, 28);	
						}	
						else
							day.addItem(29);	
						
						if(day.getItemAt(29)!=null) {
							day.removeItemAt(29);
							day.insertItemAt(30, 29);	
						}
						else
							day.addItem(30);
						
						while(day.getItemAt(30)!=null) {
							day.removeItemAt(30);
						}
					    break;	
					    }
					}	
				}
				catch(NumberFormatException n)
				{
					 System.out.println("输入应为数字");
				}
		        finally {}
			}
		});
		month.setBounds(183, 114, 51, 29);
		frame.getContentPane().add(month);
		
		JLabel label_4 = new JLabel("\u6708");
		label_4.setBounds(244, 119, 72, 18);
		frame.getContentPane().add(label_4);
		
		day = new JComboBox();
		day.setModel(new DefaultComboBoxModel(new String[] {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28"}));
		day.setBounds(266, 114, 51, 29);
		frame.getContentPane().add(day);
		
		JLabel label_12 = new JLabel("*");
		label_12.setForeground(Color.RED);
		label_12.setBounds(276, 159, 72, 18);
		frame.getContentPane().add(label_12);
		
		JLabel label_13 = new JLabel("*");
		label_13.setForeground(Color.RED);
		label_13.setBounds(322, 225, 72, 18);
		frame.getContentPane().add(label_13);
		
		JLabel label_14 = new JLabel("*");
		label_14.setForeground(Color.RED);
		label_14.setBounds(322, 321, 72, 18);
		frame.getContentPane().add(label_14);
		
		JLabel label_15 = new JLabel("\u786E\u4FDD\u4E24\u6B21\u8F93\u5165\u5BC6\u7801\u76F8\u540C");
		label_15.setForeground(Color.RED);
		label_15.setBounds(330, 275, 168, 18);
		frame.getContentPane().add(label_15);
		
		JLabel label_16 = new JLabel("\u786E\u4FDD\u4E24\u6B21\u8F93\u5165\u5BC6\u7801\u76F8\u540C");
		label_16.setForeground(Color.RED);
		label_16.setBounds(330, 371, 168, 18);
		frame.getContentPane().add(label_16);
		
		pas2 = new JTextField();
		pas2.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {
				if(false==(pas1.getText().equals(pas2.getText()))) {
					JOptionPane.showMessageDialog(frame, "两次输入密码不同");
				}
			}
		});
		pas2.setColumns(10);
		pas2.setBounds(171, 363, 145, 34);
		frame.getContentPane().add(pas2);
		frame.setVisible(true);
	}



}
