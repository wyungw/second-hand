package weizhenyang;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JLabel;
import java.awt.Font;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Calendar;

import javax.swing.JTextField;
import javax.swing.JTable;
import javax.swing.JComboBox;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.DefaultComboBoxModel;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.Color;

public class Flow2 {

	private JFrame frame;
	private JTextField y1;
	private JTable table;
	private JTextField y2;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Flow2 window = new Flow2("1008");
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
	public Flow2(String str) {
		frame = new JFrame("租客"+str+"的缴费流水");
		frame.setBounds(100, 100, 750, 483);
		frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(14, 80, 617, 343);
		frame.getContentPane().add(scrollPane);
		Calendar now=Calendar.getInstance();
		
		JLabel label = new JLabel("\u6240\u6709\u7F34\u8D39\u8BB0\u5F55\uFF1A");
		label.setFont(new Font("黑体", Font.PLAIN, 17));
		label.setBounds(14, 53, 137, 18);
		frame.getContentPane().add(label);
		 int i=0,j=0;
		    Connection conn;
			try {		
				conn = DriverManager.getConnection("jdbc:sqlserver://127.0.0.1:1433;DatabaseName=sechh","sa","123456");
			    Statement stmt=conn.createStatement();
			    ResultSet rset=stmt.executeQuery("select * from 缴费流水2 where 租客='"+str+"' order by cast(房屋编号 as int)");
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
			    String[] list= {"订单编号","房屋编号","房东姓名","缴费价格","缴费日期","是否在租"};
			    ResultSet rs=stmt.executeQuery("select * from 缴费流水2 where 租客='"+str+"' order by cast(房屋编号 as int)");
			    while(rs.next()) {
			    	arr[j][0]=rs.getString(1);
			    	arr[j][1]=rs.getString(2);
			    	arr[j][3]=rs.getInt(4)+"元";
			    	arr[j][2]=rs.getString(3);
			    	arr[j][4]=rs.getDate(5)+"";
			    	arr[j][5]=rs.getString(6);
			    	j++;
			    }
			    table = new JTable(arr,list);
			    table.setFont(new Font("宋体", Font.PLAIN, 15));
			    table.setRowHeight(30);
			    scrollPane.setViewportView(table);
			    rs.close();
			} catch (SQLException e) {
				
				e.printStackTrace();
			}
			
			
			JComboBox d1 = new JComboBox();
			d1.setModel(new DefaultComboBoxModel(new String[] {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28"}));
			d1.setBounds(321, 41, 51, 30);
			frame.getContentPane().add(d1);
			
			JComboBox m1 = new JComboBox();
			m1.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					try
					{   
						int y = Integer.parseInt(y1.getText());
						System.out.println("输入的年份为:"+y+"年");
						int m = m1.getSelectedIndex()+1;
						switch(m) {
						case 1:				
						case 3:
						case 5:
						case 7:
						case 8:
						case 10:
						case 12:{	//1 3 5 7 8 10 12月
							if(d1.getItemAt(28)!=null) {
								d1.removeItemAt(28);
								d1.insertItemAt(29, 28);	
							}	
							else
								d1.addItem(29);	
							
							if(d1.getItemAt(29)!=null) {
								d1.removeItemAt(29);
								d1.insertItemAt(30, 29);	
							}
							else
								d1.addItem(30);
							
							if(d1.getItemAt(30)!=null) {
								d1.removeItemAt(30);
								d1.insertItemAt(31, 30);
							}
							else
								d1.addItem(31);
				
						    break;}
						case 2:{
							if(y%4==0&&y%100!=0||y%400==0) {//闰年2月
								System.out.println("该年为闰年");
								if(d1.getItemAt(28)!=null) {
									d1.removeItemAt(28);
									d1.insertItemAt(29, 28);	
								}	
								else
									d1.addItem(29);	
										
								while(d1.getItemAt(29)!=null) {
									d1.removeItemAt(29);
								}
													
									       	
							}
							else {//平年2月
							    while(d1.getItemAt(28)!=null) {
									d1.removeItemAt(28);	
								}						
							}
							break;	
						}
					
						case 4:
						case 6:
						case 9:
						case 11:{//4 6 9 11月
							if(d1.getItemAt(28)!=null) {
								d1.removeItemAt(28);
								d1.insertItemAt(29, 28);	
							}	
							else
								d1.addItem(29);	
							
							if(d1.getItemAt(29)!=null) {
								d1.removeItemAt(29);
								d1.insertItemAt(30, 29);	
							}
							else
								d1.addItem(30);
							
							while(d1.getItemAt(30)!=null) {
								d1.removeItemAt(30);
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
			m1.setModel(new DefaultComboBoxModel(new String[] {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12"}));
			m1.setBounds(246, 41, 51, 30);
			frame.getContentPane().add(m1);
			
			JLabel label_2 = new JLabel("\u6708");
			label_2.setBounds(300, 47, 72, 18);
			frame.getContentPane().add(label_2);

		y1 = new JTextField();
		y1.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent arg0) {
				try
				{   
					int y = Integer.parseInt(y1.getText());
					System.out.println("输入的年份为:"+y+"年");
					int m = m1.getSelectedIndex()+1;
					switch(m) {
					case 1:				
					case 3:
					case 5:
					case 7:
					case 8:
					case 10:
					case 12:{	//1 3 5 7 8 10 12月
						if(d1.getItemAt(28)!=null) {
							d1.removeItemAt(28);
							d1.insertItemAt(29, 28);	
						}	
						else
							d1.addItem(29);	
						
						if(d1.getItemAt(29)!=null) {
							d1.removeItemAt(29);
							d1.insertItemAt(30, 29);	
						}
						else
							d1.addItem(30);
						
						if(d1.getItemAt(30)!=null) {
							d1.removeItemAt(30);
							d1.insertItemAt(31, 30);
						}
						else
							d1.addItem(31);
			
					    break;}
					case 2:{
						if(y%4==0&&y%100!=0||y%400==0) {//闰年2月
							System.out.println("该年为闰年");
							if(d1.getItemAt(28)!=null) {
								d1.removeItemAt(28);
								d1.insertItemAt(29, 28);	
							}	
							else
								d1.addItem(29);	
									
							while(d1.getItemAt(29)!=null) {
								d1.removeItemAt(29);
							}
												
								       	
						}
						else {//平年2月
						    while(d1.getItemAt(28)!=null) {
								d1.removeItemAt(28);	
							}						
						}
						break;	
					}
				
					case 4:
					case 6:
					case 9:
					case 11:{//4 6 9 11月
						if(d1.getItemAt(28)!=null) {
							d1.removeItemAt(28);
							d1.insertItemAt(29, 28);	
						}	
						else
							d1.addItem(29);	
						
						if(d1.getItemAt(29)!=null) {
							d1.removeItemAt(29);
							d1.insertItemAt(30, 29);	
						}
						else
							d1.addItem(30);
						
						while(d1.getItemAt(30)!=null) {
							d1.removeItemAt(30);
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
		y1.setBounds(165, 41, 57, 30);
		frame.getContentPane().add(y1);
		y1.setColumns(10);
		
		JLabel label_1 = new JLabel("\u5E74");
		label_1.setBounds(225, 47, 72, 18);
		frame.getContentPane().add(label_1);
		
		JLabel label_3 = new JLabel(" \u65E5\u2014\u2014");
		label_3.setBounds(373, 47, 72, 18);
		frame.getContentPane().add(label_3);
		
		JComboBox d2 = new JComboBox();
		d2.setModel(new DefaultComboBoxModel(new String[] {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28"}));
		d2.setBounds(580, 41, 51, 30);
		frame.getContentPane().add(d2);
		
		d2.setSelectedItem(now.get(Calendar.DAY_OF_MONTH)+"");
		JComboBox m2 = new JComboBox();
		//System.out.println(now.get(Calendar.MONTH)+1);
		m2.setModel(new DefaultComboBoxModel(new String[] {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12"}));
		m2.setSelectedItem((now.get(Calendar.MONTH)+1)+"");
		m2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try
				{   
					int y = Integer.parseInt(y2.getText());
					System.out.println("输入的年份为:"+y+"年");
					int m = m2.getSelectedIndex()+1;
					switch(m) {
					case 1:				
					case 3:
					case 5:
					case 7:
					case 8:
					case 10:
					case 12:{	//1 3 5 7 8 10 12月
						if(d2.getItemAt(28)!=null) {
							d2.removeItemAt(28);
							d2.insertItemAt(29, 28);	
						}	
						else
							d2.addItem(29);	
						
						if(d2.getItemAt(29)!=null) {
							d2.removeItemAt(29);
							d2.insertItemAt(30, 29);	
						}
						else
							d2.addItem(30);
						
						if(d2.getItemAt(30)!=null) {
							d2.removeItemAt(30);
							d2.insertItemAt(31, 30);
						}
						else
							d2.addItem(31);
			
					    break;}
					case 2:{
						if(y%4==0&&y%100!=0||y%400==0) {//闰年2月
							System.out.println("该年为闰年");
							if(d2.getItemAt(28)!=null) {
								d2.removeItemAt(28);
								d2.insertItemAt(29, 28);	
							}	
							else
								d2.addItem(29);	
									
							while(d2.getItemAt(29)!=null) {
								d2.removeItemAt(29);
							}
												
								       	
						}
						else {//平年2月
						    while(d2.getItemAt(28)!=null) {
								d2.removeItemAt(28);	
							}						
						}
						break;	
					}
				
					case 4:
					case 6:
					case 9:
					case 11:{//4 6 9 11月
						if(d2.getItemAt(28)!=null) {
							d2.removeItemAt(28);
							d2.insertItemAt(29, 28);	
						}	
						else
							d2.addItem(29);	
						
						if(d2.getItemAt(29)!=null) {
							d2.removeItemAt(29);
							d2.insertItemAt(30, 29);	
						}
						else
							d2.addItem(30);
						
						while(d2.getItemAt(30)!=null) {
							d2.removeItemAt(30);
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

		m2.setBounds(507, 41, 51, 30);
		
		frame.getContentPane().add(m2);
		
		
		
		
		y2 = new JTextField(now.get(Calendar.YEAR)+"");
		y2.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent arg0) {
				try
				{   
					int y = Integer.parseInt(y2.getText());
					System.out.println("输入的年份为:"+y+"年");
					int m = m2.getSelectedIndex()+1;
					switch(m) {
					case 1:				
					case 3:
					case 5:
					case 7:
					case 8:
					case 10:
					case 12:{	//1 3 5 7 8 10 12月
						if(d2.getItemAt(28)!=null) {
							d2.removeItemAt(28);
							d2.insertItemAt(29, 28);	
						}	
						else
							d2.addItem(29);	
						
						if(d2.getItemAt(29)!=null) {
							d2.removeItemAt(29);
							d2.insertItemAt(30, 29);	
						}
						else
							d2.addItem(30);
						
						if(d2.getItemAt(30)!=null) {
							d2.removeItemAt(30);
							d2.insertItemAt(31, 30);
						}
						else
							d2.addItem(31);
			
					    break;}
					case 2:{
						if(y%4==0&&y%100!=0||y%400==0) {//闰年2月
							System.out.println("该年为闰年");
							if(d2.getItemAt(28)!=null) {
								d2.removeItemAt(28);
								d2.insertItemAt(29, 28);	
							}	
							else
								d2.addItem(29);	
									
							while(d2.getItemAt(29)!=null) {
								d2.removeItemAt(29);
							}
												
								       	
						}
						else {//平年2月
						    while(d2.getItemAt(28)!=null) {
								d2.removeItemAt(28);	
							}						
						}
						break;	
					}
				
					case 4:
					case 6:
					case 9:
					case 11:{//4 6 9 11月
						if(d2.getItemAt(28)!=null) {
							d2.removeItemAt(28);
							d2.insertItemAt(29, 28);	
						}	
						else
							d2.addItem(29);	
						
						if(d2.getItemAt(29)!=null) {
							d2.removeItemAt(29);
							d2.insertItemAt(30, 29);	
						}
						else
							d2.addItem(30);
						
						while(d2.getItemAt(30)!=null) {
							d2.removeItemAt(30);
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
		y2.setColumns(10);
		y2.setBounds(426, 41, 57, 30);
		frame.getContentPane().add(y2);
		
		JLabel label_4 = new JLabel("\u5E74");
		label_4.setBounds(486, 47, 72, 18);
		frame.getContentPane().add(label_4);
		
		JLabel label_5 = new JLabel("\u6708");
		label_5.setBounds(559, 47, 72, 18);
		frame.getContentPane().add(label_5);
		

		JLabel label_6 = new JLabel("\u65E5");
		label_6.setBounds(634, 47, 72, 18);
		frame.getContentPane().add(label_6);
		
		JButton btnNewButton = new JButton("\u67E5\u8BE2");
		btnNewButton.setBackground(new Color(230, 230, 250));
		btnNewButton.setFont(new Font("黑体", Font.PLAIN, 17));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String date1="";
				String date2="";
                date1=y1.getText()+"-"+m1.getSelectedItem()+"-"+d1.getSelectedItem();
                date2=y2.getText()+"-"+m2.getSelectedItem()+"-"+d2.getSelectedItem();
                Date d1=Date.valueOf(date1);
                Date d2=Date.valueOf(date2);
                int m=0,n=0;
    		    Connection conn;
    			try {		
    				conn = DriverManager.getConnection("jdbc:sqlserver://127.0.0.1:1433;DatabaseName=sechh","sa","123456");
    			    Statement stmt=conn.createStatement();
    			    ResultSet rset=stmt.executeQuery("select * from 缴费流水2 where 租客='"+str+"' and 缴费日期 between '"+date1+"'and '"+date2+"'order by cast(房屋编号 as int)");
    			    ArrayList ar = new ArrayList();	
    			    while(rset.next()) {
    			    	ar.add(rset.getString(1));
    			    	ar.add(rset.getString(2));
    			    	ar.add(rset.getString(3));
    			    	ar.add(rset.getInt(4));
    			    	ar.add(rset.getDate(5));
    			    	ar.add(rset.getString(6));
    			    	m++;
    			    }
    			    String[][] arr=new String[m][6];
    			    String[] list= {"订单编号","房屋编号","房东姓名","缴费价格","缴费日期","是否在租"};
    			    ResultSet rs=stmt.executeQuery("select * from 缴费流水2 where 租客='"+str+"' and 缴费日期 between '"+date1+"'and '"+date2+"'order by cast(房屋编号 as int)");
    			    while(rs.next()) {
    			    	arr[n][0]=rs.getString(1);
    			    	arr[n][1]=rs.getString(2);
    			    	arr[n][3]=rs.getInt(4)+"元";
    			    	arr[n][2]=rs.getString(3);
    			    	arr[n][4]=rs.getDate(5)+"";
    			    	arr[n][5]=rs.getString(6);
    			    	n++;
    			    }
    			    table = new JTable(arr,list);
    			    table.setFont(new Font("宋体", Font.PLAIN, 15));
    			    table.setRowHeight(30);
    			    scrollPane.setViewportView(table);
    			    rs.close();
    			} catch (SQLException e) {			
    				e.printStackTrace();
    			}
			}
		});
		btnNewButton.setBounds(653, 29, 79, 44);
		frame.getContentPane().add(btnNewButton);
		frame.setVisible(true);
	}
	}

