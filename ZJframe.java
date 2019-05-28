package weizhenyang;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import java.awt.Font;
import java.awt.Color;
import java.awt.SystemColor;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;

public class ZJframe {

	private JFrame frame;
	private JTable table;
	private JTable table_1;
    private int numberr=0;
    private int numberr2=0;
    private String sql="select * from 可租房屋 order by cast(房屋编号 as int)";
    private String sql1="select * from 可租房屋,房屋信息表 where hno=房屋编号 order by cast(hno as int)";
    private String sql2="select * from 已租房屋2";
    
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ZJframe window = new ZJframe("1008");
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
	public ZJframe(String str) {
		frame = new JFrame(str);
		frame.getContentPane().setFont(new Font("黑体", Font.PLAIN, 17));
		frame.setTitle("亲爱的租客"+str+"，欢迎您使用系统");
		frame.setBounds(100, 100, 930, 718);
		frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
	    
	    JLabel label_4 = new JLabel("\u5DF2\u79DF");
	    label_4.setFont(new Font("黑体", Font.PLAIN, 17));
	    label_4.setBounds(575, 145, 72, 18);
	    frame.getContentPane().add(label_4);
	    
		 /*面板一开始的*/
	    JScrollPane scrollPane = new JScrollPane();
	    scrollPane.setBounds(0, 173, 715, 288);
	    frame.getContentPane().add(scrollPane);
	    int i=0,j=0;
	    Connection conn;
		try {		
			conn = DriverManager.getConnection("jdbc:sqlserver://127.0.0.1:1433;DatabaseName=sechh","sa","123456");
		    Statement stmt=conn.createStatement();
		    ResultSet rset=stmt.executeQuery(sql);
		    ArrayList ar = new ArrayList();
		    //String sqll="select * from 可租房屋";	
		    while(rset.next()) {
		    	ar.add(rset.getString(1));
		    	ar.add(rset.getString(2));
		    	ar.add(rset.getInt(3));
		    	ar.add(rset.getString(4));
		    	ar.add(rset.getString(5));
		    	ar.add(rset.getString(6));
		    	ar.add(rset.getInt(7));
		    	ar.add(rset.getInt(8));
		    	ar.add(rset.getString(9));
		    	i++;
		    	numberr++;
		    }

		    String[][] arr=new String[i][9];
		    String[] list= {"房屋编号","房东姓名","房东年龄","房东电话","房屋户型","地址","面积","租金","照片"};
		    ResultSet rs=stmt.executeQuery(sql);
		    while(rs.next()) {
		    	arr[j][0]=rs.getString(1);
		    	arr[j][1]=rs.getString(2);
		    	arr[j][2]=rs.getInt(3)+"";
		    	arr[j][3]=rs.getString(4);
		    	arr[j][4]=rs.getString(5);
		    	arr[j][5]=rs.getString(6);
		    	arr[j][6]=rs.getInt(7)+"平方米";
		    	arr[j][7]=rs.getInt(8)+"元";
		    	arr[j][8]=rs.getString(9);
		    	j++;
		    }
		    table = new JTable(arr,list);
		    table.setFont(new Font("宋体", Font.PLAIN, 15));
		    table.getColumnModel().getColumn(5).setPreferredWidth(100);
		    table.getColumnModel().getColumn(3).setPreferredWidth(100);
		    table.getColumnModel().getColumn(8).setPreferredWidth(50);
		    table.setRowHeight(30);
			scrollPane.setViewportView(table);
			
		} catch (SQLException e) {
			
			e.printStackTrace();
		}

	    JScrollPane scrollPane_1 = new JScrollPane();
	    scrollPane_1.setBounds(0, 512, 623, 146);
	    frame.getContentPane().add(scrollPane_1);
		JButton button = new JButton("\u4E2A\u4EBA\u4FE1\u606F\u7BA1\u7406");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				new Zmess(str);
			}
		});
		
		try {
		    Connection conn2 = DriverManager.getConnection("jdbc:sqlserver://127.0.0.1:1433;DatabaseName=sechh","sa","123456");
	        Statement stmt2;
			stmt2 = conn2.createStatement();
			ResultSet rset2=stmt2.executeQuery(sql2);
		    while(rset2.next()) {
	    	    numberr2++;
	        }
		    label_4.setText("已租"+numberr2+"间");
		} catch (SQLException e3) {
			// TODO 自动生成的 catch 块
			e3.printStackTrace();
		}
		button.setBackground(new Color(230, 230, 250));
		button.setFont(new Font("黑体", Font.PLAIN, 18));
		button.setBounds(743, 222, 168, 47);
		frame.getContentPane().add(button);
		/*租赁房屋按钮*/
		JButton button_1 = new JButton("\u79DF\u623F");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String hno="";
				int number=5;
				int row=table.getSelectedRow();
				if(row==-1) {
					JOptionPane.showMessageDialog(null,"请选择要租赁的房屋！");
				}
				else {
					/*由被选中的函数得到hno*/
					int x=0;
					Connection conn;
					try {
						 conn = DriverManager.getConnection("jdbc:sqlserver://127.0.0.1:1433;DatabaseName=sechh","sa","123456");
						 Statement stmt = conn.createStatement();						 
						 ResultSet rset=stmt.executeQuery(sql1);
						 while(rset.next()&&x<=row) {
							 hno=rset.getString(1);						 
							 x++;
						 }
						 //统计交易表行数
						 String sqll="select * from 交易表";	
						 ResultSet rset1=stmt.executeQuery(sqll);						 
						  while(rset1.next()) {
							  number++;
						  }		
						 System.out.println(hno);
						 System.out.println(number);
						 String sql="insert into 交易表 values(?,?,?,?,DEFAULT)";
						 PreparedStatement  pstmt=conn.prepareStatement(sql);
						 pstmt.setString(1, Integer.toString(number));
						 pstmt.setString(2, str);
						 pstmt.setString(3, hno);
						 pstmt.setString(4, "租出");
						 pstmt.executeUpdate();			  
						 pstmt.close();
					     JOptionPane.showMessageDialog(frame, "租房成功");
					} catch (SQLException e1) {						 
						e1.printStackTrace();
					}			   
				}
			}
		});
		button_1.setBackground(new Color(230, 230, 250));
		button_1.setFont(new Font("黑体", Font.PLAIN, 18));
		button_1.setBounds(743, 282, 168, 47);
		frame.getContentPane().add(button_1);
		/*房屋缴费按钮*/
		JButton button_3 = new JButton("\u7F34\u7EB3\u672C\u6708\u623F\u79DF");
		button_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String hno="";
				int number=10;
				int row=table_1.getSelectedRow();
				if(row==-1) {
					JOptionPane.showMessageDialog(null,"请选择要缴费的房屋！");
				}
				else {
					/*由被选中的函数得到hno*/
					int x=0;
					Connection conn;
					try {
						 conn = DriverManager.getConnection("jdbc:sqlserver://127.0.0.1:1433;DatabaseName=sechh","sa","123456");
						 Statement stmt = conn.createStatement();
						 ResultSet rset=stmt.executeQuery("select * from 已租房屋  where 租客='"+str+"'order by cast(房屋编号 as int)");
						 while(rset.next()&&x<=row) {
							 hno=rset.getString(1);						 
							 x++;
						 }
						 //统计交易表行数
						 String sqll="select * from 缴费流水表";	
						 ResultSet rset1=stmt.executeQuery(sqll);						 
						  while(rset1.next()) {
							  number++;
						  }		
						 System.out.println(hno);
						 System.out.println(number);
						 String sql="insert into 缴费流水表 values(?,?,?,?,DEFAULT)";
						 PreparedStatement  pstmt=conn.prepareStatement(sql);
						 pstmt.setString(1, Integer.toString(number));
						 pstmt.setString(2, hno);
						 pstmt.setString(3, str);
						 pstmt.setInt(4,500);
						 pstmt.executeUpdate();			  
						 pstmt.close();
					     JOptionPane.showMessageDialog(frame, "缴纳本月房租成功");
					} catch (SQLException e1) {
						
						e1.printStackTrace();
					}			   
				}
			}
		});
		button_3.setBackground(new Color(230, 230, 250));
		button_3.setFont(new Font("黑体", Font.PLAIN, 18));
		button_3.setBounds(725, 535, 186, 47);
		frame.getContentPane().add(button_3);
		
		JButton button_4 = new JButton("\u67E5\u8BE2\u7F34\u8D39\u6D41\u6C34");
		button_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new Flow2(str);
			}
		});
		button_4.setBackground(new Color(230, 230, 250));
		button_4.setFont(new Font("黑体", Font.PLAIN, 18));
		button_4.setBounds(725, 595, 186, 47);
		frame.getContentPane().add(button_4);
		ImageIcon ic=new ImageIcon("E:\\平时用文件\\大二\\数据库课程设计\\12.jpg");
	    JLabel l=new JLabel(ic);	  
	    l.setBounds(0,0,ic.getIconWidth(),ic.getIconHeight());	  	    
	    frame.getLayeredPane().add(l,new Integer(Integer.MIN_VALUE));	  
	    ((JComponent) frame.getContentPane()).setOpaque(false);//设置透明	
	    
	    JLabel label = new JLabel("\u4E8C\u624B\u623F\u5C4B\u7BA1\u7406\u7CFB\u7EDF");
	    label.setFont(new Font("楷体", Font.PLAIN, 50));
	    label.setBounds(240, 23, 444, 55);
	    frame.getContentPane().add(label);
	    /*退租按钮*/
	    JButton button_2 = new JButton("\u9000\u79DF");
	    button_2.addActionListener(new ActionListener() {
	    	public void actionPerformed(ActionEvent e) {
	    		String hno="";
				int number=5;
				int row=table_1.getSelectedRow();
				if(row==-1) {
					JOptionPane.showMessageDialog(null,"请选择要退租的房屋！");
				}
				else {
					/*由被选中的函数得到hno*/
					int x=0;
					Connection conn;
					try {
						 conn = DriverManager.getConnection("jdbc:sqlserver://127.0.0.1:1433;DatabaseName=sechh","sa","123456");
						 Statement stmt = conn.createStatement();
						 ResultSet rset=stmt.executeQuery("select * from 已租房屋  where 租客='"+str+"'order by cast(房屋编号 as int)");
						 while(rset.next()&&x<=row) {
							 hno=rset.getString(1);						 
							 x++;
						 }
						 //统计交易表行数
						 String sqll="select * from 交易表";	
						 ResultSet rset1=stmt.executeQuery(sqll);						 
						  while(rset1.next()) {
							  number++;
						  }		
						 System.out.println(hno);
						 System.out.println(number);
						 String sql="insert into 交易表 values(?,?,?,?,DEFAULT)";
						 PreparedStatement  pstmt=conn.prepareStatement(sql);
						 pstmt.setString(1, Integer.toString(number));
						 pstmt.setString(2, str);
						 pstmt.setString(3, hno);
						 pstmt.setString(4, "退租");
						 pstmt.executeUpdate();			  
						 pstmt.close();
					     JOptionPane.showMessageDialog(frame, "退租成功");
					} catch (SQLException e1) {
						
						e1.printStackTrace();
					}			   
				}
	    	}
	    });
	    button_2.setFont(new Font("黑体", Font.PLAIN, 18));
	    button_2.setBackground(new Color(230, 230, 250));
	    button_2.setBounds(725, 475, 186, 47);
	    frame.getContentPane().add(button_2);
	   
	    int m=0,n=0;	    
		try {
			conn = DriverManager.getConnection("jdbc:sqlserver://127.0.0.1:1433;DatabaseName=sechh","sa","123456");
		    Statement stmt=conn.createStatement();
		    ResultSet rset=stmt.executeQuery("select * from 已租房屋  where 租客='"+str+"'order by cast(房屋编号 as int)" );
		    ArrayList ar = new ArrayList();
		    
		    while(rset.next()) {
		    	ar.add(rset.getString(1));
		    	ar.add(rset.getString(2));
		    	ar.add(rset.getString(3));
		    	ar.add(rset.getString(4));
		    	ar.add(rset.getInt(5));
		    	ar.add(rset.getString(6));
		    	m++;
		    }
		    String[][] arr=new String[m][6];
		    String[] list= {"房屋编号","房东姓名","房东性别","房东电话","月租","是否欠费"};
		    ResultSet rs=stmt.executeQuery("select * from 已租房屋  where 租客='"+str+"'order by cast(房屋编号 as int)" );
		    while(rs.next()) {
		    	arr[n][0]=rs.getString(1);
		    	arr[n][1]=rs.getString(2);
		    	arr[n][2]=rs.getString(3);
		    	arr[n][3]=rs.getString(4);
		    	arr[n][4]=rs.getInt(5)+"";
		    	arr[n][5]=rs.getString(6);
		    	n++;
		    }
		    table_1 = new JTable(arr,list);
		    table_1.setFont(new Font("宋体", Font.PLAIN, 15));
		    table_1.setRowHeight(30);
			 scrollPane_1.setViewportView(table_1);
		} catch (SQLException e) {
			
			e.printStackTrace();
		}	    
	    JLabel label_2 = new JLabel("\u5DF2\u79DF\u623F\u5C4B\uFF1A");
	    label_2.setFont(new Font("黑体", Font.PLAIN, 17));
	    label_2.setBounds(0, 481, 88, 18);
	    frame.getContentPane().add(label_2);
	    /*查看房屋照片*/
	    JButton button_5 = new JButton("\u67E5\u770B\u623F\u5C4B\u56FE\u7247");
	    button_5.addActionListener(new ActionListener() {
	    	public void actionPerformed(ActionEvent e) {
				String hno="";
				String path="";
				int row=table.getSelectedRow();
				if(row==-1) {
					JOptionPane.showMessageDialog(null,"请选择要查看照片的房屋！");
				}
				else {
					int x=0;
					Connection conn;
					try {
						 conn = DriverManager.getConnection("jdbc:sqlserver://127.0.0.1:1433;DatabaseName=sechh","sa","123456");
						 Statement stmt = conn.createStatement();
						 ResultSet rset=stmt.executeQuery(sql1);
						 System.out.println(sql1);
						 while(rset.next()&&x<=row) {
							 hno=rset.getString(1);
							 if(rset.getString(17)!=null)
							     path=rset.getString(17);
							 x++;
						 }
						 System.out.println(hno);
						 System.out.println(path);
						 if(path.equals("")) {
							 JOptionPane.showMessageDialog(null,"该房屋没有照片");
						 }else {
							 new Printphoto(str,path);
						 }			
					} catch (SQLException e1) {
						
						e1.printStackTrace();
					}			   
				}
	    	}
	    });
	    button_5.setFont(new Font("黑体", Font.PLAIN, 18));
	    button_5.setBackground(new Color(230, 230, 250));
	    button_5.setBounds(743, 342, 168, 47);
	    frame.getContentPane().add(button_5);
	    
	    JLabel label_1 = new JLabel("可租房屋一览（"+numberr+"间)");
	    label_1.setFont(new Font("黑体", Font.PLAIN, 17));
	    label_1.setBounds(0, 144, 191, 18);
	    frame.getContentPane().add(label_1);
	    /*刷新所有信息*/
	    JButton button_6 = new JButton("\u5237\u65B0\u6240\u6709\u4FE1\u606F");
	    button_6.addActionListener(new ActionListener() {
	    	public void actionPerformed(ActionEvent e) {
	    		 int i=0,j=0;
	    		 numberr=0;
	    		    Connection conn;
	    			try {	    				
	    				conn = DriverManager.getConnection("jdbc:sqlserver://127.0.0.1:1433;DatabaseName=sechh","sa","123456");
	    			    Statement stmt=conn.createStatement();
	    			    ResultSet rset=stmt.executeQuery("select * from 可租房屋 order by cast(房屋编号 as int)");
	    			    ArrayList ar = new ArrayList();
	    			    String sqll="select * from 可租房屋";	
	    			    while(rset.next()) {
	    			    	ar.add(rset.getString(1));
	    			    	ar.add(rset.getString(2));
	    			    	ar.add(rset.getInt(3));
	    			    	ar.add(rset.getString(4));
	    			    	ar.add(rset.getString(5));
	    			    	ar.add(rset.getString(6));
	    			    	ar.add(rset.getInt(7));
	    			    	ar.add(rset.getInt(8));
	    			    	ar.add(rset.getString(9));
	    			    	i++;
	    			    	numberr++;
	    			    }
	    			    
	    			    String[][] arr=new String[i][9];
	    			    String[] list= {"房屋编号","房东姓名","房东年龄","房东电话","房屋户型","地址","面积","租金","照片"};
	    			    ResultSet rs=stmt.executeQuery("select * from 可租房屋 order by cast(房屋编号 as int)");
	    			    while(rs.next()) {
	    			    	arr[j][0]=rs.getString(1);
	    			    	arr[j][1]=rs.getString(2);
	    			    	arr[j][2]=rs.getInt(3)+"";
	    			    	arr[j][3]=rs.getString(4);
	    			    	arr[j][4]=rs.getString(5);
	    		
	    			    	arr[j][5]=rs.getString(6);
	    			    	arr[j][6]=rs.getInt(7)+"平方米";
	    			    	arr[j][7]=rs.getInt(8)+"元";
	    			    	arr[j][8]=rs.getString(9);
	    			    	j++;
	    			    	
	    			    }
	    			    table = new JTable(arr,list);
	    			    table.setFont(new Font("宋体", Font.PLAIN, 15));
	    			    table.getColumnModel().getColumn(5).setPreferredWidth(100);
	    			    table.getColumnModel().getColumn(3).setPreferredWidth(100);
	    			    table.getColumnModel().getColumn(8).setPreferredWidth(50);
	    			    table.setRowHeight(30);
	    				 scrollPane.setViewportView(table);
	    				// System.out.println(numberr);
	    				 label_1.setText("可租房屋一览（"+numberr+"间)");
	    			} catch (SQLException e1) { 				
	    				e1.printStackTrace();
	    			}
	    			  int m=0,n=0;
	    			    
	    				try {
	    					conn = DriverManager.getConnection("jdbc:sqlserver://127.0.0.1:1433;DatabaseName=sechh","sa","123456");
	    				    Statement stmt=conn.createStatement();
	    				    ResultSet rset=stmt.executeQuery("select * from 已租房屋  where 租客='"+str+"'order by cast(房屋编号 as int)" );
	    				    ArrayList ar = new ArrayList();
	    				    
	    				    while(rset.next()) {
	    				    	ar.add(rset.getString(1));
	    				    	ar.add(rset.getString(2));
	    				    	ar.add(rset.getString(3));
	    				    	ar.add(rset.getString(4));
	    				    	ar.add(rset.getInt(5));
	    				    	ar.add(rset.getString(6));
	    				    	m++;
	    				    }
	    				    String[][] arr=new String[m][6];
	    				    String[] list= {"房屋编号","房东姓名","房东性别","房东电话","月租","是否欠费"};
	    				    ResultSet rs=stmt.executeQuery("select * from 已租房屋  where 租客='"+str+"'order by cast(房屋编号 as int)" );
	    				    while(rs.next()) {
	    				    	arr[n][0]=rs.getString(1);
	    				    	arr[n][1]=rs.getString(2);
	    				    	arr[n][2]=rs.getString(3);
	    				    	arr[n][3]=rs.getString(4);
	    				    	arr[n][4]=rs.getInt(5)+"";
	    				    	arr[n][5]=rs.getString(6);
	    				    	n++;
	    				    }
	    				    table_1 = new JTable(arr,list);
	    				    table_1.setFont(new Font("宋体", Font.PLAIN, 15));
	    				    table_1.setRowHeight(30);
	    					 scrollPane_1.setViewportView(table_1);
	    				} catch (SQLException e2) {
	    					
	    					e2.printStackTrace();
	    				}	    
	    	}
	    });
	   
	    button_6.setBackground(new Color(221, 160, 221));
	    button_6.setFont(new Font("黑体", Font.PLAIN, 17));
	    button_6.setBounds(743, 162, 168, 47);
	    frame.getContentPane().add(button_6);
	    
	    JLabel label_3 = new JLabel("\u6309\u7C7B\u578B\u67E5\u770B");
	    label_3.setFont(new Font("黑体", Font.PLAIN, 17));
	    label_3.setBounds(16, 102, 96, 31);
	    frame.getContentPane().add(label_3);

	    JLabel lblNewLabel = new JLabel("\uFF08\u5E73\u65B9\u7C73\uFF09");
	    lblNewLabel.setFont(new Font("黑体", Font.PLAIN, 17));
	    lblNewLabel.setBounds(463, 109, 104, 18);
	    frame.getContentPane().add(lblNewLabel);
	    
	    JLabel label_5 = new JLabel("\u6309\u9762\u79EF\u67E5\u770B");
	    label_5.setFont(new Font("黑体", Font.PLAIN, 17));
	    label_5.setBounds(226, 108, 129, 18);
	    frame.getContentPane().add(label_5);
	    
	     
	    JLabel lblNewLabel_1 = new JLabel("\u6309\u4EF7\u683C\u67E5\u770B");
	    lblNewLabel_1.setFont(new Font("黑体", Font.PLAIN, 17));
	    lblNewLabel_1.setBounds(564, 108, 96, 18);
	    frame.getContentPane().add(lblNewLabel_1);
	    /*按类型筛选*/
	    JComboBox comboBox = new JComboBox();

	   /* comboBox.addFocusListener(new FocusAdapter() {
	    	@Override
	    	public void focusLost(FocusEvent arg0) {
	    		comboBox.setSelectedIndex(0);
	    	}
	    });*/
	    comboBox.setFont(new Font("黑体", Font.PLAIN, 17));
	    comboBox.setModel(new DefaultComboBoxModel(new String[] {"\u5168\u90E8", "\u4E00\u5C45", "\u4E24\u5C45", "\u4E09\u5C45"}));
	    comboBox.setBounds(112, 100, 88, 37);
	 
	    JComboBox comboBox_1 = new JComboBox();
	    /*comboBox_1.addFocusListener(new FocusAdapter() {
	    	@Override
	    	public void focusLost(FocusEvent e) {
	    		comboBox_1.setSelectedIndex(0);
	    	}
	    });*/
	    comboBox_1.setFont(new Font("黑体", Font.PLAIN, 17));
	    comboBox_1.setModel(new DefaultComboBoxModel(new String[] {"\u5168\u90E8", "50\u4EE5\u4E0B", "50-70", "70-90", "90-150", "150\u4EE5\u4E0A"}));
	    comboBox_1.setBounds(320, 100, 136, 37);


	    /*按类型筛选*/
	    JComboBox comboBox_2 = new JComboBox();
	   /* comboBox_2.addFocusListener(new FocusAdapter() {
	    	@Override
	    	public void focusLost(FocusEvent e) {
	    		comboBox_2.setSelectedIndex(0);
	    	}
	    });*/
	    comboBox_2.setModel(new DefaultComboBoxModel(new String[] {"\u5168\u90E8", "500\u4EE5\u4E0B", "500-800", "800-1000", "1000-1500", "1500\u4EE5\u4E0A"}));
	    comboBox_2.setFont(new Font("黑体", Font.PLAIN, 17));
	    comboBox_2.setBounds(657, 99, 136, 37);
	    
	    comboBox.addActionListener(new ActionListener() {
	    	public void actionPerformed(ActionEvent arg0) {
	    	    sql1="select * from 可租房屋,房屋信息表 where hno=房屋编号 order by cast(hno as int)";
	    		int i=0,j=0;
	    		numberr=0;
	    	    Connection conn;
	    	    String slqplus="";
	    	    if(comboBox.getSelectedIndex()==0) {
	    	    	slqplus="select * from 可租房屋 order by cast(房屋编号 as int)";
	    	    	sql1="select * from 可租房屋,房屋信息表 where hno=房屋编号 order by cast(hno as int)";
	    	        sql2="select * from 已租房屋2";
	    	    }
	    	    	
	    	         
	    	    else {
	    	    	slqplus="select * from 可租房屋 where 房屋户型='"+comboBox.getSelectedItem()+"'order by cast(房屋编号 as int)";
	    	    	sql1="select * from 可租房屋,房屋信息表 where hno=房屋编号 and 房屋户型='"+comboBox.getSelectedItem()+"' order by cast(hno as int)";
	    	    	sql2="select * from 已租房屋2 where 房屋类型='"+comboBox.getSelectedItem()+"'";
	    	    }
	    	    	
	    			try {	    				
	    				conn = DriverManager.getConnection("jdbc:sqlserver://127.0.0.1:1433;DatabaseName=sechh","sa","123456");
	    			    Statement stmt=conn.createStatement();
	    			    ResultSet rset=stmt.executeQuery(slqplus);
	    			    ArrayList ar = new ArrayList();
	    			    String sqll="select * from 可租房屋";	
	    			    while(rset.next()) {
	    			    	ar.add(rset.getString(1));
	    			    	ar.add(rset.getString(2));
	    			    	ar.add(rset.getInt(3));
	    			    	ar.add(rset.getString(4));
	    			    	ar.add(rset.getString(5));
	    			    	ar.add(rset.getString(6));
	    			    	ar.add(rset.getInt(7));
	    			    	ar.add(rset.getInt(8));
	    			    	ar.add(rset.getString(9));
	    			    	i++;
	    			    	numberr++;
	    			    }
	    			    
	    			    String[][] arr=new String[i][9];
	    			    String[] list= {"房屋编号","房东姓名","房东年龄","房东电话","房屋户型","地址","面积","租金","照片"};
	    			    ResultSet rs=stmt.executeQuery(slqplus);
	    			    while(rs.next()) {
	    			    	arr[j][0]=rs.getString(1);
	    			    	arr[j][1]=rs.getString(2);
	    			    	arr[j][2]=rs.getInt(3)+"";
	    			    	arr[j][3]=rs.getString(4);
	    			    	arr[j][4]=rs.getString(5);
	    			    	arr[j][5]=rs.getString(6);
	    			    	arr[j][6]=rs.getInt(7)+"平方米";
	    			    	arr[j][7]=rs.getInt(8)+"元";
	    			    	arr[j][8]=rs.getString(9);
	    			    	j++;
	    			    	
	    			    }
	    			    table = new JTable(arr,list);
	    			    table.setFont(new Font("宋体", Font.PLAIN, 15));
	    			    table.getColumnModel().getColumn(5).setPreferredWidth(100);
	    			    table.getColumnModel().getColumn(3).setPreferredWidth(100);
	    			    table.getColumnModel().getColumn(8).setPreferredWidth(50);
	    			    table.setRowHeight(30);
	    				 scrollPane.setViewportView(table);
	    				// System.out.println(numberr);
	    				 label_1.setText("可租房屋一览（"+numberr+"间)");
	    				 //label_4.setText("已租"+numberr2+"间");
	    				 numberr=0;
	    				 numberr2=0;
	    			} catch (SQLException e1) { 				
	    				e1.printStackTrace();
	    			}
	    			try {
	    			    Connection conn2 = DriverManager.getConnection("jdbc:sqlserver://127.0.0.1:1433;DatabaseName=sechh","sa","123456");
	    		        Statement stmt2;
	    				stmt2 = conn2.createStatement();
	    				ResultSet rset2=stmt2.executeQuery(sql2);
	    			    while(rset2.next()) {
	    		    	    numberr2++;
	    		        }
	    			    label_4.setText("已租"+numberr2+"间");
	    			    numberr2=0;
	    			} catch (SQLException e3) {
	    				// TODO 自动生成的 catch 块
	    				e3.printStackTrace();
	    			}
	    	}
	    });
	    
	    comboBox_1.addActionListener(new ActionListener() {
	    	public void actionPerformed(ActionEvent arg0) {
	    		int i=0,j=0;
	    		numberr=0;
	    	    Connection conn;
	    	    String slqplus="";
	    	    sql1="select * from 可租房屋,房屋信息表 where hno=房屋编号 order by cast(hno as int)";
	    	    sql2="select * from 已租房屋2";
	    	    if(comboBox_1.getSelectedIndex()==0)
	    	    	slqplus="select * from 可租房屋 order by cast(房屋编号 as int)";	    	    
	    	    if(comboBox_1.getSelectedIndex()==1) {
	    	    	slqplus="select * from 可租房屋 where 面积 <50 order by cast(房屋编号 as int)";
	    	    	sql1="select * from 可租房屋,房屋信息表 where hno=房屋编号 and 面积<50 order by cast(hno as int)";
	    	    	sql2="select * from 已租房屋2 where 面积<50";
	    	    }    
	    	    if(comboBox_1.getSelectedIndex()==2) {
	    	    	slqplus="select * from 可租房屋 where 面积 between 50 and 70 order by cast(房屋编号 as int)";	
	    	    	sql1="select * from 可租房屋,房屋信息表 where hno=房屋编号 and 面积 between 50 and 70 order by cast(hno as int)";
	    	    	sql2="select * from 已租房屋2 where 面积 between 50 and 70 ";
	    	    }    	    	
	    	    if(comboBox_1.getSelectedIndex()==3) {
	    	    	slqplus="select * from 可租房屋 where 面积 between 70 and 90 order by cast(房屋编号 as int)";
	    	    	sql1="select * from 可租房屋,房屋信息表 where hno=房屋编号 and 面积 between 70 and 90 order by cast(hno as int)";
	    	    	sql2="select * from 已租房屋2 where 面积 between 70 and 90 ";
	    	    }    	    	
	    	    if(comboBox_1.getSelectedIndex()==4) {
	    	    	slqplus="select * from 可租房屋 where 面积 between 90 and 150 order by cast(房屋编号 as int)";
	    	    	sql1="select * from 可租房屋,房屋信息表 where hno=房屋编号 and 面积 between 90 and 150 order by cast(hno as int)";
	    	    	sql2="select * from 已租房屋2 where 面积 between 90 and 150 ";
	    	    }
	    	    	
	    	    if(comboBox_1.getSelectedIndex()==5) {
	    	    	slqplus="select * from 可租房屋 where 面积 >150 order by cast(房屋编号 as int)";
	    	    	sql1="select * from 可租房屋,房屋信息表 where hno=房屋编号 and 面积 >150 order by cast(hno as int)";
	    	    	sql2="select * from 已租房屋2 where 面积 >150 ";
	    	    }
	    	    	
	    	    try {	    				
	    				conn = DriverManager.getConnection("jdbc:sqlserver://127.0.0.1:1433;DatabaseName=sechh","sa","123456");
	    			    Statement stmt=conn.createStatement();
	    			    ResultSet rset=stmt.executeQuery(slqplus);
	    			    ArrayList ar = new ArrayList();
	    			    String sqll="select * from 可租房屋";	
	    			    while(rset.next()) {
	    			    	ar.add(rset.getString(1));
	    			    	ar.add(rset.getString(2));
	    			    	ar.add(rset.getInt(3));
	    			    	ar.add(rset.getString(4));
	    			    	ar.add(rset.getString(5));
	    			    	ar.add(rset.getString(6));
	    			    	ar.add(rset.getInt(7));
	    			    	ar.add(rset.getInt(8));
	    			    	ar.add(rset.getString(9));
	    			    	i++;
	    			    	numberr++;
	    			    }
	    			    
	    			    String[][] arr=new String[i][9];
	    			    String[] list= {"房屋编号","房东姓名","房东年龄","房东电话","房屋户型","地址","面积","租金","照片"};
	    			    ResultSet rs=stmt.executeQuery(slqplus);
	    			    while(rs.next()) {
	    			    	arr[j][0]=rs.getString(1);
	    			    	arr[j][1]=rs.getString(2);
	    			    	arr[j][2]=rs.getInt(3)+"";
	    			    	arr[j][3]=rs.getString(4);
	    			    	arr[j][4]=rs.getString(5);
	    			    	arr[j][5]=rs.getString(6);
	    			    	arr[j][6]=rs.getInt(7)+"平方米";
	    			    	arr[j][7]=rs.getInt(8)+"元";
	    			    	arr[j][8]=rs.getString(9);
	    			    	j++;
	    			    	
	    			    }
	    			    table = new JTable(arr,list);
	    			    table.setFont(new Font("宋体", Font.PLAIN, 15));
	    			    table.getColumnModel().getColumn(5).setPreferredWidth(100);
	    			    table.getColumnModel().getColumn(3).setPreferredWidth(100);
	    			    table.getColumnModel().getColumn(8).setPreferredWidth(50);
	    			    table.setRowHeight(30);
	    				 scrollPane.setViewportView(table);
	    				// System.out.println(numberr);
	    				 label_1.setText("可租房屋一览（"+numberr+"间)");
	    				 numberr=0;
	    			} catch (SQLException e1) { 				
	    				e1.printStackTrace();
	    			}
	    		try {
    			    Connection conn2 = DriverManager.getConnection("jdbc:sqlserver://127.0.0.1:1433;DatabaseName=sechh","sa","123456");
    		        Statement stmt2;
    				stmt2 = conn2.createStatement();
    				ResultSet rset2=stmt2.executeQuery(sql2);
    			    while(rset2.next()) {
    		    	    numberr2++;
    		        }
    			    label_4.setText("已租"+numberr2+"间");
    			    numberr2=0;
    			} catch (SQLException e3) {
    				// TODO 自动生成的 catch 块
    				e3.printStackTrace();
    			}
	    	}
	    });
	    
	    comboBox_2.addActionListener(new ActionListener() {
	    	public void actionPerformed(ActionEvent e) {
	    		int i=0,j=0;
	    		numberr=0;
	    	   // comboBox_1.setSelectedIndex(0);
		       // comboBox.setSelectedIndex(0);
	    	    Connection conn;
	    	    String slqplus="";
	    	    sql1="select * from 可租房屋,房屋信息表 where hno=房屋编号 order by cast(hno as int)";
	    	    sql2="select * from 已租房屋2";
	    	    if(comboBox_2.getSelectedIndex()==0)
	    	    	slqplus="select * from 可租房屋 order by cast(房屋编号 as int)";
	    	    if(comboBox_2.getSelectedIndex()==1) {
	    	    		slqplus="select * from 可租房屋 where 租金 <500 order by cast(房屋编号 as int)";
	    	    		sql1="select * from 可租房屋,房屋信息表 where hno=房屋编号 and 租金<500 order by cast(hno as int)";
	    	    		sql2="select * from 已租房屋2 where 租金 <500";
	    	    }
	    	    
	    	    if(comboBox_2.getSelectedIndex()==2) {
	    	    	slqplus="select * from 可租房屋 where 租金 between 500 and 800 order by cast(房屋编号 as int)";	
	    	    	sql1="select * from 可租房屋,房屋信息表 where hno=房屋编号 and 租金 between 500 and 800 order by cast(hno as int)";
	    	    	sql2="select * from 已租房屋2 where 租金 between 500 and 800";
	    	    }
	    	    	
	    	    if(comboBox_2.getSelectedIndex()==3) {
	    	    	slqplus="select * from 可租房屋 where 租金 between 800 and 1000 order by cast(房屋编号 as int)";
	    	    	sql1="select * from 可租房屋,房屋信息表 where hno=房屋编号 and 租金 between 800 and 1000 order by cast(hno as int)";
	    	    	sql2="select * from 已租房屋2 where 租金 between 800 and 1000";
	    	    }
	    	       	    	
	    	    if(comboBox_2.getSelectedIndex()==4) {
	    	    	slqplus="select * from 可租房屋 where 租金 between 1000 and 1500 order by cast(房屋编号 as int)";
	    	    	sql1="select * from 可租房屋,房屋信息表 where hno=房屋编号 and 租金 between 1000 and 1500 order by cast(hno as int)";
	    	    	sql2="select * from 已租房屋2 where 租金 between 1000 and 1500";
	    	    }
	    	    if(comboBox_2.getSelectedIndex()==5) {
	    	    	slqplus="select * from 可租房屋 where 租金 >1500 order by cast(房屋编号 as int)";
	    	    	sql1="select * from 可租房屋,房屋信息表 where hno=房屋编号 and 租金 >1500 order by cast(hno as int)";
	    	    	sql2="select * from 已租房屋2 where 租金 >1500";
	    	    }
	    	    try {	    				
	    				conn = DriverManager.getConnection("jdbc:sqlserver://127.0.0.1:1433;DatabaseName=sechh","sa","123456");
	    			    Statement stmt=conn.createStatement();
	    			    ResultSet rset=stmt.executeQuery(slqplus);
	    			    ArrayList ar = new ArrayList();
	    			    String sqll="select * from 可租房屋";	
	    			    while(rset.next()) {
	    			    	ar.add(rset.getString(1));
	    			    	ar.add(rset.getString(2));
	    			    	ar.add(rset.getInt(3));
	    			    	ar.add(rset.getString(4));
	    			    	ar.add(rset.getString(5));
	    			    	ar.add(rset.getString(6));
	    			    	ar.add(rset.getInt(7));
	    			    	ar.add(rset.getInt(8));
	    			    	ar.add(rset.getString(9));
	    			    	i++;
	    			    	numberr++;
	    			    }
	    			    
	    			    String[][] arr=new String[i][9];
	    			    String[] list= {"房屋编号","房东姓名","房东年龄","房东电话","房屋户型","地址","面积","租金","照片"};
	    			    ResultSet rs=stmt.executeQuery(slqplus);
	    			    while(rs.next()) {
	    			    	arr[j][0]=rs.getString(1);
	    			    	arr[j][1]=rs.getString(2);
	    			    	arr[j][2]=rs.getInt(3)+"";
	    			    	arr[j][3]=rs.getString(4);
	    			    	arr[j][4]=rs.getString(5);
	    			    	arr[j][5]=rs.getString(6);
	    			    	arr[j][6]=rs.getInt(7)+"平方米";
	    			    	arr[j][7]=rs.getInt(8)+"元";
	    			    	arr[j][8]=rs.getString(9);
	    			    	j++;
	    			    	
	    			    }
	    			    table = new JTable(arr,list);
	    			    table.setFont(new Font("宋体", Font.PLAIN, 15));
	    			    table.getColumnModel().getColumn(5).setPreferredWidth(100);
	    			    table.getColumnModel().getColumn(3).setPreferredWidth(100);
	    			    table.getColumnModel().getColumn(8).setPreferredWidth(50);
	    			    table.setRowHeight(30);
	    				 scrollPane.setViewportView(table);
	    				// System.out.println(numberr);
	    				 label_1.setText("可租房屋一览（"+numberr+"间)");
	    				 numberr=0;
	    			} catch (SQLException e1) { 				
	    				e1.printStackTrace();
	    			}
	    		try {
    			    Connection conn2 = DriverManager.getConnection("jdbc:sqlserver://127.0.0.1:1433;DatabaseName=sechh","sa","123456");
    		        Statement stmt2;
    				stmt2 = conn2.createStatement();
    				ResultSet rset2=stmt2.executeQuery(sql2);
    			    while(rset2.next()) {
    		    	    numberr2++;
    		        }
    			    label_4.setText("已租"+numberr2+"间");
    			    numberr2=0;
    			} catch (SQLException e3) {
    				// TODO 自动生成的 catch 块
    				e3.printStackTrace();
    			}
	    	}
	    });
	    comboBox.addFocusListener(new FocusAdapter() {
	    	@Override
	    	public void focusGained(FocusEvent arg0) {
	    		comboBox_1.setSelectedIndex(0);
	    		comboBox_2.setSelectedIndex(0);
	    	}
	    });
	    comboBox_1.addFocusListener(new FocusAdapter() {
	    	@Override
	    	public void focusGained(FocusEvent arg0) {
	    		comboBox.setSelectedIndex(0);
	    		comboBox_2.setSelectedIndex(0);
	    	}
	    });
	    comboBox_2.addFocusListener(new FocusAdapter() {
	    	@Override
	    	public void focusGained(FocusEvent arg0) {
	    		comboBox_1.setSelectedIndex(0);
	    		comboBox.setSelectedIndex(0);
	    	}
	    });
	    frame.getContentPane().add(comboBox);
	    frame.getContentPane().add(comboBox_1);
	    frame.getContentPane().add(comboBox_2);
	    
	    JLabel label_6 = new JLabel("\uFF08\u5143\uFF09");
	    label_6.setFont(new Font("黑体", Font.PLAIN, 18));
	    label_6.setBounds(803, 109, 72, 18);
	    frame.getContentPane().add(label_6);
	    frame.setVisible(true);
		
	}
}
