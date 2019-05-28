package weizhenyang;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JButton;
import java.awt.Font;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Managehouse {

	private JFrame frame;
	private JTable table;
	private JTable table_1;

	/**
	 * Launch the application.
	 把表格生成的写在这里
	 */
	public void display() {
		
	}
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Managehouse window = new Managehouse("2007");
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
	public Managehouse(String str) {
		frame = new JFrame();
		frame.setBounds(100, 100, 737, 449);
		frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(0, 61, 712, 143);
		frame.getContentPane().add(scrollPane);
		
		Connection conn;
		int i=0,j=0;
		try {
			conn = DriverManager.getConnection("jdbc:sqlserver://127.0.0.1:1433;DatabaseName=sechh","sa","123456");
			Statement stmt=conn.createStatement();
		    ResultSet rset=stmt.executeQuery("select * from 房屋信息表 where fno='"+str+"'");
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
		    String[][] arr=new String[i][6];
		    String[] list= {"房屋编号","房屋状态","类型","地址","面积","价格"};
		    ResultSet rs=stmt.executeQuery("select * from 房屋信息表 where fno='"+str+"'");
		    while(rs.next()) {
		    	arr[j][0]=rs.getString(1);
		    	arr[j][1]=rs.getString(3);
		    	arr[j][2]=rs.getString(4);
		    	arr[j][3]=rs.getString(5);
		    	arr[j][4]=rs.getInt(6)+"平方米";
		    	arr[j][5]=rs.getInt(7)+"元";
		    	j++;
		    }
			table = new JTable(arr,list);
			table.setFont(table.getFont().deriveFont(table.getFont().getSize() + 2f));
			table.setRowHeight(30);
			scrollPane.setViewportView(table);
		} catch (SQLException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
		JButton button = new JButton("\u6DFB\u52A0\u623F\u5C4B");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				new AddHouseF(str);
			}
		});
		button.setFont(new Font("黑体", Font.PLAIN, 17));
		button.setBounds(0, 21, 113, 27);
		frame.getContentPane().add(button);
		
		JButton button_1 = new JButton("\u5220\u9664\u623F\u5C4B");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String hno="";
				String state="";
				int row=table.getSelectedRow();
				if(row==-1) {
					JOptionPane.showMessageDialog(null,"请选择要删除的房屋！");
				}
				else {
					int x=0;
					Connection conn;
					try {
						 conn = DriverManager.getConnection("jdbc:sqlserver://127.0.0.1:1433;DatabaseName=sechh","sa","123456");
						 Statement stmt = conn.createStatement();
						 ResultSet rset=stmt.executeQuery("select * from 房屋信息表 where fno='"+str+"'");
						 while(rset.next()&&x<=row) {
							 hno=rset.getString(1);
							 state=rset.getString(3);
							 x++;
						 }
						 System.out.println(hno);
						 System.out.println(state);
						 if(state.equals("已租出")) {
							 JOptionPane.showMessageDialog(null,"还有未退租的租客，不允许删除房屋");
						 }else {
							 stmt.executeUpdate("delete from 交易表 where hno='"+hno+"'");
							 stmt.executeUpdate("delete from 缴费流水表 where hno='"+hno+"'");
							 stmt.executeUpdate("delete from 房屋信息表 where hno='"+hno+"'");
							 JOptionPane.showMessageDialog(null,"删除成功");
						 }			
					} catch (SQLException e1) {
						
						e1.printStackTrace();
					}			   
				}
			}
		});
		button_1.setFont(new Font("黑体", Font.PLAIN, 17));
		button_1.setBounds(135, 21, 113, 27);
		frame.getContentPane().add(button_1);
		
		JButton button_2 = new JButton("\u4FEE\u6539\u4FE1\u606F");
		button_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String hno1="";
				int row1=table.getSelectedRow();
				if(row1==-1) {
					JOptionPane.showMessageDialog(null,"请选择要修改的房屋！");
				}else {
					int x=0;
					Connection conn;
					try {
						 conn = DriverManager.getConnection("jdbc:sqlserver://127.0.0.1:1433;DatabaseName=sechh","sa","123456");
						 Statement stmt = conn.createStatement();
						 ResultSet rset=stmt.executeQuery("select * from 房屋信息表 where fno='"+str+"'");
						 while(rset.next()&&x<=row1) {
							 hno1=rset.getString(1);
							 //state=rset.getString(3);
							 x++;
						 }
						 new HMchange(str,hno1);
						 System.out.println(hno1);
						//System.out.println(state);
						 	
					} catch (SQLException e1) {
						
						e1.printStackTrace();
					}			   
				}
			}
		});
		button_2.setFont(new Font("黑体", Font.PLAIN, 17));
		button_2.setBounds(272, 21, 113, 27);
		frame.getContentPane().add(button_2);
		
		JButton button_3 = new JButton("\u5237\u65B0");
		button_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Connection conn;
				int i=0,j=0;
				try {
					conn = DriverManager.getConnection("jdbc:sqlserver://127.0.0.1:1433;DatabaseName=sechh","sa","123456");
					Statement stmt=conn.createStatement();
				    ResultSet rset=stmt.executeQuery("select * from 房屋信息表 where fno='"+str+"'");
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
				    String[][] arr=new String[i][6];
				    String[] list= {"房屋编号","房屋状态","类型","地址","面积","价格"};
				    ResultSet rs=stmt.executeQuery("select * from 房屋信息表 where fno='"+str+"'");
				    while(rs.next()) {
				    	arr[j][0]=rs.getString(1);
				    	arr[j][1]=rs.getString(3);
				    	arr[j][2]=rs.getString(4);
				    	arr[j][3]=rs.getString(5);
				    	arr[j][4]=rs.getInt(6)+"平方米";
				    	arr[j][5]=rs.getInt(7)+"元";
				    	j++;
				    }
					table = new JTable(arr,list);
					table.setFont(table.getFont().deriveFont(table.getFont().getSize() + 2f));
					table.setRowHeight(30);
					scrollPane.setViewportView(table);
				} catch (SQLException e1) {
					// TODO 自动生成的 catch 块
					e1.printStackTrace();
				}
			}
		});
		button_3.setFont(new Font("黑体", Font.PLAIN, 17));
		button_3.setBounds(399, 21, 113, 27);
		frame.getContentPane().add(button_3);
		
		JButton button_4 = new JButton("\u8FD4\u56DE");
		button_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				frame.setVisible(false);
				new FJframe(str);
			}
		});
		button_4.setFont(new Font("黑体", Font.PLAIN, 17));
		button_4.setBounds(537, 22, 113, 27);
		frame.getContentPane().add(button_4);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(0, 233, 712, 156);
		frame.getContentPane().add(scrollPane_1);
		frame.setVisible(true);
		int m=0,n=0;
		try {
			conn = DriverManager.getConnection("jdbc:sqlserver://127.0.0.1:1433;DatabaseName=sechh","sa","123456");
			Statement stmt=conn.createStatement();
		    ResultSet rset=stmt.executeQuery("select *from 房屋租客,房屋信息表  where 房屋编号= 房屋信息表.hno and fno='"+str+"'");
		    ArrayList ar = new ArrayList();
		    while(rset.next()) {
		    	ar.add(rset.getString(1));
		    	ar.add(rset.getString(2));
		    	ar.add(rset.getString(3));
		    	ar.add(rset.getInt(4));
		    	ar.add(rset.getString(5));
		    	ar.add(rset.getString(6));
		    	m++;
		    }
		    String[][] arr=new String[i][7];
		    String[] list= {"房屋编号","租客姓名","租客性别","租客年龄","租客电话","是否欠费"};
		    ResultSet rs=stmt.executeQuery("select *from 房屋租客,房屋信息表  where 房屋编号= 房屋信息表.hno and fno='"+str+"'");
		    while(rs.next()) {
		    	arr[n][0]=rs.getString(1);
		    	arr[n][1]=rs.getString(2);
		    	arr[n][2]=rs.getString(3);
		    	arr[n][3]=rs.getInt(4)+"岁";
		    	arr[n][4]=rs.getString(5);
		    	arr[n][5]=rs.getString(6);
		    	n++;
		    }
			table_1 = new JTable(arr,list);
			table_1.setRowHeight(30);
			table_1.setFont(table_1.getFont().deriveFont(table_1.getFont().getSize() + 2f));
			scrollPane_1.setViewportView(table_1);
			
			JLabel label = new JLabel("\u5DF2\u79DF\u51FA\u623F\u5C4B\u79DF\u5BA2\u4E00\u89C8\uFF1A");
			label.setBounds(10, 212, 150, 18);
			frame.getContentPane().add(label);
		} catch (SQLException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
	}
	
}
