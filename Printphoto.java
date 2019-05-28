package weizhenyang;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Printphoto extends JFrame {
	static BufferedImage image=null;
	static File file =null;
	public static void main(String args[])throws IOException {	
		try {
			file=new File("C:\\Users\\75490\\Pictures\\20150709230000_XFcvE.jpg");
			image=ImageIO.read(file);
		} catch (IOException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
		new Printphoto("2001","C:\\Users\\75490\\Pictures\\20150709230000_XFcvE.jpg");
		
	}
	public Printphoto() {
		setSize(600,500);
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().setLayout(null);
		
		JButton btnNewButton_1 = new JButton("New button");
		btnNewButton_1.setBounds(200, 393, 113, 27);
		getContentPane().add(btnNewButton_1);	
	}	
	public Printphoto(String str,String path) {
		setSize(1200,800);
		setVisible(true);
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		try {
			file=new File(path);
			image=ImageIO.read(file);
		} catch (IOException e) {
			e.printStackTrace();
		}		
	}
	public void paint(Graphics g) {
		super.paint(g);
		g.drawImage(image, 0, 0, null);
	}
}