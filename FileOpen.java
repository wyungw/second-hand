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
	JButton jb = new JButton("ѡ�񱾵�ͼƬ");
	public String s="45";
	public static void main(String[] args) {
		// TODO �Զ����ɵķ������
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
		jb.setBackground(Color.PINK);//���ð�ť��ɫ
		frame.getContentPane().add(jb);//��������ʹ�ñ߽粼��
		jb.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e){
			    if (e.getActionCommand().equals("open")){
				JFileChooser jf = new JFileChooser();
				jf.showOpenDialog(frame);//��ʾ�򿪵��ļ��Ի���
				File f =  jf.getSelectedFile();//ʹ���ļ����ȡѡ����ѡ����ļ�
				s = f.getAbsolutePath();//����·����
				//JOptionPane�����Ի����࣬��ʾ����·����
				JOptionPane.showMessageDialog(frame, s, "����",JOptionPane.WARNING_MESSAGE);  
				//System.out.println(s);
				new AddHouseF(str,s);
				frame.setVisible(false);
			}
			
		}
		});
		frame.setTitle("ѡ���ļ�...");
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
		jb.setBackground(Color.PINK);//���ð�ť��ɫ
		frame.getContentPane().add(jb);//��������ʹ�ñ߽粼��
		jb.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e){
			    if (e.getActionCommand().equals("open")){
				JFileChooser jf = new JFileChooser();
				jf.showOpenDialog(frame);//��ʾ�򿪵��ļ��Ի���
				File f =  jf.getSelectedFile();//ʹ���ļ����ȡѡ����ѡ����ļ�
				s = f.getAbsolutePath();//����·����
				//JOptionPane�����Ի����࣬��ʾ����·����
				JOptionPane.showMessageDialog(frame, s, "����",JOptionPane.WARNING_MESSAGE);  
				//System.out.println(s);
				new HMchange(str,hno,s);
				frame.setVisible(false);
			}
			
		}
		});
		frame.setTitle("ѡ���ļ�...");
		frame.setSize(333, 288);
		frame.setLocation(200,200);
		frame.setVisible(true);
}
}


