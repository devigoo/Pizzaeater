package main;
 import javax.swing.JFrame;
import javax.swing.SwingUtilities;

public class Main {
	public static void main(String[] args) {

		SwingUtilities.invokeLater(new Runnable() {

			public void run() {
				
				JFrame frm = new JFrame();
				frm.setSize(500, 500);
				frm.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				frm.setTitle("Pizzaeater");
				frm.add(new App());
				frm.setVisible(true);
				
			}

		});
	}
}