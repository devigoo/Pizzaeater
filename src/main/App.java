package main;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenuBar;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class App extends JPanel implements ActionListener {

	private static final long serialVersionUID = 2196343327222633538L;
	private JMenuBar bar;
	private JButton bVs2s;
	private JButton bigVsSmall;
	BufferedImage img;
	private String img1 = "/images/pizza.jpg";
	private double priceLarge, d1, d2, priceSmall;
	private boolean corrInput;

	private void loadImage(String filePath) {
		try {
			img = ImageIO.read(App.class.getResource(filePath));
		} catch (IOException e) {

			e.printStackTrace();
		}
	}

	protected void paintComponent(Graphics g) {
		super.paintComponents(g);
		g.drawImage(img, 0, 0, 500, 500, this);
	}

	public App() {

		bar = new JMenuBar();
		bigVsSmall = new JButton("Big vs small");
		bVs2s = new JButton("Big vs 2 small");
		setLayout(new FlowLayout());
		setSize(500, 500);
		bigVsSmall.setBackground(Color.YELLOW);
		bVs2s.setBackground(Color.YELLOW);
		add(bar);
		bar.add(bVs2s);
		bar.add(bigVsSmall);
		bVs2s.addActionListener(this);
		bigVsSmall.addActionListener(this);
		loadImage(img1);
	}

	@Override
	public void actionPerformed(ActionEvent e) {

		// First Option
		if (e.getSource() == bigVsSmall) {

			d1 = Integer.parseInt(JOptionPane.showInputDialog("Enter the diameter of the large pizza"));
			priceLarge = Integer.parseInt(JOptionPane.showInputDialog("Enter the price of large pizza"));
			d2 = Integer.parseInt(JOptionPane.showInputDialog("Enter the diameter of the small pizza"));
			priceSmall = Integer.parseInt(JOptionPane.showInputDialog("Enter the price of the small pizza"));

			// Checking if user inputs big and small diameters correctly
			corrInput = d1 > d2;
			if (corrInput == true) {

				// Method that counts which pizza pays off more and displays it on the screen
				bigVsSmall(d1, d2, priceLarge, priceSmall);
			} else {
				JOptionPane.showMessageDialog(null, "Diameter of big pizza must be greater than this of the small one");
			}
		}

		// Second option
		else if (e.getSource() == bVs2s) {

			d1 = Integer.parseInt(JOptionPane.showInputDialog("Give diameter of large pizza"));
			priceLarge = Integer.parseInt(JOptionPane.showInputDialog("Give price of large pizza"));
			d2 = Integer.parseInt(JOptionPane.showInputDialog("Give diameter of small pizza"));
			priceSmall = Integer.parseInt(JOptionPane.showInputDialog("Give price of two small pizza"));

			corrInput = d1 > d2;
			if (corrInput == true) {
				bigVs2Small(d1, d2, priceLarge, priceSmall);
			} else {
				JOptionPane.showMessageDialog(null, "Diameter of big pizza must be greater than this of the small one");
			}

		}
	}

	private void bigVsSmall(double d1, double d2, double priceLarge, double priceSmall) {

		// Counting areas of small and big pizza
		double areaLarge = pizzaArea(d1);
		double areaSmall = pizzaArea(d2);

		// Calculating ratio price/area of both pizza
		double ratio2 = priceSmall / areaSmall;
		double ratio1 = priceLarge / areaLarge;

		// Calculating difference in areas in percents
		double differencePercent = (((areaLarge - areaSmall) / areaSmall) * 100);
		differencePercent = Math.round(differencePercent);

		// Showing output based on ratio
		if (ratio2 > ratio1) {
			double percentPrice = (((ratio2 - ratio1) / ratio1) * 100);
			percentPrice = Math.round(percentPrice);
			JOptionPane.showMessageDialog(null,
					"Big pizza pays off more!\n  Smaller one is " + percentPrice
							+ " percents more expensive!\nLarge pizza is  " + differencePercent
							+ " percents bigger than the small one");
		}

		else if (ratio1 > ratio2) {
			double percentPrice = Math.round((((ratio1 - ratio2) / ratio2) * 100));
			JOptionPane.showMessageDialog(null,
					"Small pizza pays off more!\n Large pizza is " + percentPrice
							+ " percents more expensive!\n Large pizza is " + differencePercent
							+ " percents bigger than the small one");
		}

		else {
			JOptionPane.showMessageDialog(null, "Both pizzas pay off the same\n Large pizza is " + differencePercent
					+ " percents bigger than the small one\n");
		}

	}

	private void bigVs2Small(double d1, double d2, double priceLarge, double priceSmall1) {

		// Counting areas of small and big pizza
		double areaLarge = pizzaArea(d1);
		double areaSmall = 2 * pizzaArea(d2);
		double ratio1 = priceLarge / areaLarge;
		double ratio2 = priceSmall1 / areaSmall;

		// Output based on results
		if (areaLarge > areaSmall) {
			double differencePercent = ((areaLarge - areaSmall) / areaSmall) * 100;
			double differencePercent1 = Math.round(differencePercent);
			JOptionPane.showMessageDialog(null,
					"Big pizza is " + differencePercent1 + " percents bigger than 2 small ones\n");
		}

		if (areaSmall > areaLarge) {
			double differencePercent = ((areaSmall - areaLarge) / areaLarge) * 100;
			double differencePercent1 = (Math.round(differencePercent));
			JOptionPane.showMessageDialog(null, " 2 small are " + differencePercent1 + " percents bigger\n");
		}

		if (ratio1 > ratio2) {
			double differencePercent = countRatio(ratio1, ratio2);
			double differencePercent1 = Math.round(differencePercent);
			JOptionPane.showMessageDialog(null,
					"2 small pizzas pay off more!\nLarge pizza is " + differencePercent1 + " percent more expensive");
		}

		if (ratio2 > ratio1) {
			double differencPercent = countRatio(ratio2, ratio1);
			double differencPercent1 = (int) Math.round(differencPercent);
			JOptionPane.showMessageDialog(null, "Big pizza pays off more!!\n2 small pizzas are " + differencPercent1
					+ " percents more expensive\n");
		}

		if (ratio1 == ratio2) {
			JOptionPane.showMessageDialog(null, "Both pizzas pay off equally\n");

		}

	}

	private double pizzaArea(double d) {

		double area = (Math.PI * (d / 2) * (d / 2));
		return area;
	}

	private double countRatio(double biggerRatio, double smallerRatio) {

		double percentPrice = (((biggerRatio - smallerRatio) / smallerRatio) * 100);
		double percentPrice1 = (int) Math.round(percentPrice);
		return percentPrice1;
	}

}
