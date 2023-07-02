package sdmcet.cse.oop;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

class GradingSystem extends Exception implements ActionListener {

	JFrame f = new JFrame("Students’ Grading System");

	JTextField ia1Field = new JTextField(10);
	JTextField ia2Field = new JTextField(10);
	JTextField ia3Field = new JTextField(10);
	JTextField ctaField = new JTextField(10);
	JTextField seeField = new JTextField(10);

	JLabel title = new JLabel("Grade Calculator");
	JLabel ia1label = new JLabel("Enter IA-1 Marks:");
	JLabel ia2label = new JLabel("Enter IA-2 Marks:");
	JLabel ia3label = new JLabel("Enter IA-3 Marks:");
	JLabel ctalabel = new JLabel("Enter CTA Marks:");
	JLabel seelabel = new JLabel("Enter SEE Marks:");
	JLabel resultLabel = new JLabel();
	JLabel markslabel = new JLabel();
	JLabel lnxt = new JLabel();

	JPanel ptitle = new JPanel();
	JPanel pia1 = new JPanel();
	JPanel pia2 = new JPanel();
	JPanel pia3 = new JPanel();
	JPanel pcta = new JPanel();
	JPanel psee = new JPanel();
	JPanel pb1 = new JPanel();
	JPanel presult = new JPanel();
	JPanel pnxt = new JPanel();

	JButton b1 = new JButton("Calculate");

	public GradingSystem() {
		f.setSize(400, 300);
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setLayout(new GridLayout(10, 0));
		f.setBounds(200, 200, 500, 500);
		f.setVisible(true);
		f.setResizable(true);

		b1.addActionListener(this);
		presult.setLayout(new FlowLayout(FlowLayout.CENTER, 100, 0));

		lnxt.setForeground(Color.red);
		lnxt.setFont(new Font("", Font.BOLD, 20));
		title.setFont(new Font("", Font.BOLD, 18));
		resultLabel.setFont(new Font("", Font.BOLD, 15));
		markslabel.setFont(new Font("", Font.BOLD, 15));

		ptitle.add(title);

		pia1.add(ia1label);
		pia1.add(ia1Field);

		pia2.add(ia2label);
		pia2.add(ia2Field);

		pia3.add(ia3label);
		pia3.add(ia3Field);

		pcta.add(ctalabel);
		pcta.add(ctaField);

		psee.add(seelabel);
		psee.add(seeField);

		pb1.add(b1);

		presult.add(markslabel);
		presult.add(resultLabel);
		pnxt.add(lnxt);

		f.add(ptitle);
		f.add(pia1);
		f.add(pia2);
		f.add(pia3);
		f.add(pcta);
		f.add(psee);
		f.add(pb1);
		f.add(presult);
		f.add(pnxt);

	}

	public void actionPerformed(ActionEvent e) {
		try {
			int ia1 = Integer.parseInt(ia1Field.getText());
			int ia2 = Integer.parseInt(ia2Field.getText());
			int ia3 = Integer.parseInt(ia3Field.getText());
			int cta = Integer.parseInt(ctaField.getText());
			double see = Integer.parseInt(seeField.getText());

			// Validate input marks
			if (ia1 < 0 || ia1 > 20 || ia2 < 0 || ia2 > 20 || ia3 < 0 || ia3 > 20 || cta < 0 || cta > 10 || see < 0
					|| see > 100) {
				lnxt.setText("!! Invalid marks entered !!.");
				return;
			}

			// Calculate CIE
			int cie;
			int sum1, sum2, largest;

			largest = ia1 + ia2;
			sum1 = ia2 + ia3;
			sum2 = ia1 + ia3;

			if (sum1 > largest) {
				largest = sum1;
			}
			if (sum2 > largest) {
				largest = sum2;
			}

			cie = largest + cta;

			// Check if student is detained
			if (cie < 20) {
				lnxt.setText("!!Student was detained from taking SEE!!");
				return;
			}

			// Upgrade SEE marks to 40 if 38 or 39
			if (see == 38 || see == 39) {
				see = 40;
			}

			// Calculate total marks
			double totalMarks = (cie + Math.round(see / 2));
			totalMarks = Math.round(totalMarks);

			// Calculate grade
			String grade;
			if (totalMarks >= 90) {
				grade = "S";
			} else if (totalMarks >= 80) {
				grade = "A";
			} else if (totalMarks >= 70) {
				grade = "B";
			} else if (totalMarks >= 60) {
				grade = "C";
			} else if (totalMarks >= 50) {
				grade = "D";
			} else if (totalMarks >= 40) {
				grade = "E";
			} else {
				grade = "F";
			}

			markslabel.setText("Total Marks: " + totalMarks);
			resultLabel.setText("Grade: " + grade);

			// Failed due to Less marks in SEE
			if (see < 38) {
				resultLabel.setText("Grade: F");
				lnxt.setText("!!Failed due to Less marks in SEE!!");
			}

		} catch (NumberFormatException nfe) {
			lnxt.setText("!! Invalid Input Entered !!");
			System.out.println(nfe);
		}
	}

	public static void main(String[] args) {
		new GradingSystem();
	}

}
