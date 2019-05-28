package weizhenyang;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import java.awt.Color;
import java.io.File;
@SuppressWarnings("serial")
public class FileOpen {
	private JFrame frame;
	JButton jb = new JButton("选择本地图片");
	public String s="45";
	public static void main(String[] args) {
		// TODO 自动生成的方法存根
		//System.out.println(s);
		new FileOpen("2007");
	}
	public FileOpen(String str){
		frame = new JFrame();
		frame.setBounds(100, 100, 737, 449);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		//getContentPane().setLayout(null);
		jb.setBounds(82, 85, 145, 74);
		jb.setActionCommand("open");
		jb.setBackground(Color.PINK);//设置按钮颜色
		frame.getContentPane().add(jb);//建立容器使用边界布局
		jb.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e){
			    if (e.getActionCommand().equals("open")){
				JFileChooser jf = new JFileChooser();
				jf.showOpenDialog(frame);//显示打开的文件对话框
				File f =  jf.getSelectedFile();//使用文件类获取选择器选择的文件
				s = f.getAbsolutePath();//返回路径名
				//JOptionPane弹出对话框类，显示绝对路径名
				JOptionPane.showMessageDialog(frame, s, "标题",JOptionPane.WARNING_MESSAGE);  
				//System.out.println(s);
				new AddHouseF(str,s);
				frame.setVisible(false);
			}
			
		}
		});
		frame.setTitle("选择文件...");
		frame.setSize(333, 288);
		frame.setLocation(200,200);
		frame.setVisible(true);
}
	public FileOpen(String str,String hno){
		frame = new JFrame();
		frame.setBounds(100, 100, 737, 449);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		//getContentPane().setLayout(null);
		jb.setBounds(82, 85, 145, 74);
		jb.setActionCommand("open");
		jb.setBackground(Color.PINK);//设置按钮颜色
		frame.getContentPane().add(jb);//建立容器使用边界布局
		jb.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e){
			    if (e.getActionCommand().equals("open")){
				JFileChooser jf = new JFileChooser();
				jf.showOpenDialog(frame);//显示打开的文件对话框
				File f =  jf.getSelectedFile();//使用文件类获取选择器选择的文件
				s = f.getAbsolutePath();//返回路径名
				//JOptionPane弹出对话框类，显示绝对路径名
				JOptionPane.showMessageDialog(frame, s, "标题",JOptionPane.WARNING_MESSAGE);  
				//System.out.println(s);
				new HMchange(str,hno,s);
				frame.setVisible(false);
			}
			
		}
		});
		frame.setTitle("选择文件...");
		frame.setSize(333, 288);
		frame.setLocation(200,200);
		frame.setVisible(true);
}
}


