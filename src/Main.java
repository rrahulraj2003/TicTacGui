//ALPHA VERSION 2.0

//Past Errors in this program: 
//1. ai() would loop, stopped by using tracker and if(tracker) return;
//2. last button would not register and freeze program (cause: ai loop)
//3. Compatibility issues with TicTacToe methods (methods were reworked to work on both classes)
//4. isOccupied was out of array bounds (quick change in code, not too big)
//5. ai needed to be changed so that rng wouldn't choose an occupied space

/* For Reference Purposes:
 try {
		UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
	} catch (Exception f) {
		f.printStackTrace();
	}
 */

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Toolkit;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.UIManager;

public class Main { // TicTacToe but with GUI

	public static int difficulty = -1; // 0 to 4, 0 = Easy, 4 = Semi-Impossible

	public static char[][] gb = { { ' ', '|', ' ', '|', ' ' }, // game board
			{ '-', '+', '-', '+', '-' }, { ' ', '|', ' ', '|', ' ' }, { '-', '+', '-', '+', '-' },
			{ ' ', '|', ' ', '|', ' ' } };

	public static char[][] sgb = { { '1', '|', '2', '|', '3' }, // sample game board
			{ '-', '+', '-', '+', '-' }, { '4', '|', '5', '|', '6' }, { '-', '+', '-', '+', '-' },
			{ '7', '|', '8', '|', '9' } };

	public static void printGB() { // prints the actual game board

		for (char[] row : gb) {
			for (char c : row) {
				System.out.print(c);
			}
			System.out.println();
		}
		System.out.println();

	}

	public static void printSGB() { // prints a sample game board that shows numbers

		for (char[] row : sgb) {
			for (char c : row) {
				System.out.print(c);
			}
			System.out.println();
		}
		System.out.println();

	}

	public static int rng() { // return a random number from 1-9
		return (int) ((Math.random() * 9) + 1);
	}

	public static void play(String who, int move) { // This method is used as a move.

		char p = ' ';

		if (who.equals("User"))
			p = 'X';
		if (who.equals("CPU"))
			p = 'O';

		switch (move) {
		case 1:
			gb[0][0] = p;
			break;
		case 2:
			gb[0][2] = p;
			break;
		case 3:
			gb[0][4] = p;
			break;
		case 4:
			gb[2][0] = p;
			break;
		case 5:
			gb[2][2] = p;
			break;
		case 6:
			gb[2][4] = p;
			break;
		case 7:
			gb[4][0] = p;
			break;
		case 8:
			gb[4][2] = p;
			break;
		case 9:
			gb[4][4] = p;
			break;
		}

	}

	public static char check(int move) { // This method is used as a check on the gb.

		char p = ' ';

		switch (move) {
		case 1:
			p = gb[0][0];
			break;
		case 2:
			p = gb[0][2];
			break;
		case 3:
			p = gb[0][4];
			break;
		case 4:
			p = gb[2][0];
			break;
		case 5:
			p = gb[2][2];
			break;
		case 6:
			p = gb[2][4];
			break;
		case 7:
			p = gb[4][0];
			break;
		case 8:
			p = gb[4][2];
			break;
		case 9:
			p = gb[4][4];
			break;
		}
		return p;
	}

	public static int aiSmarts() { // CPU blocks the User's chances of winning

		if (check(1) == 'X' && check(2) == 'X' && check(3) == ' ') { //
			return 3;
		} else if (check(2) == 'X' && check(3) == 'X' && check(1) == ' ') { //
			return 1;
		} else if (check(1) == 'X' && check(3) == 'X' && check(2) == ' ') { // 123 - first row
			return 2;
		} else if (check(4) == 'X' && check(5) == 'X' && check(6) == ' ') { //
			return 6;
		} else if (check(5) == 'X' && check(6) == 'X' && check(4) == ' ') { //
			return 4;
		} else if (check(4) == 'X' && check(6) == 'X' && check(5) == ' ') { // 456 - second row
			return 5;
		} else if (check(7) == 'X' && check(8) == 'X' && check(9) == ' ') { //
			return 9;
		} else if (check(8) == 'X' && check(9) == 'X' && check(7) == ' ') { //
			return 7;
		} else if (check(7) == 'X' && check(9) == 'X' && check(8) == ' ') { // 789 - third row
			return 8;
		} else if (check(1) == 'X' && check(4) == 'X' && check(7) == ' ') { //
			return 7;
		} else if (check(4) == 'X' && check(7) == 'X' && check(1) == ' ') { //
			return 1;
		} else if (check(1) == 'X' && check(7) == 'X' && check(4) == ' ') { // 147 - first column
			return 4;
		} else if (check(2) == 'X' && check(5) == 'X' && check(8) == ' ') { //
			return 8;
		} else if (check(5) == 'X' && check(8) == 'X' && check(2) == ' ') { //
			return 2;
		} else if (check(2) == 'X' && check(8) == 'X' && check(5) == ' ') { // 258 - second column
			return 5;
		} else if (check(3) == 'X' && check(6) == 'X' && check(9) == ' ') { //
			return 9;
		} else if (check(6) == 'X' && check(9) == 'X' && check(3) == ' ') { //
			return 3;
		} else if (check(3) == 'X' && check(9) == 'X' && check(6) == ' ') { // 369 - third column
			return 6;
		} else if (check(1) == 'X' && check(5) == 'X' && check(9) == ' ') { //
			return 9;
		} else if (check(5) == 'X' && check(9) == 'X' && check(1) == ' ') { //
			return 1;
		} else if (check(1) == 'X' && check(9) == 'X' && check(5) == ' ') { // 159 - first diagonal
			return 5;
		} else if (check(3) == 'X' && check(5) == 'X' && check(7) == ' ') { //
			return 7;
		} else if (check(5) == 'X' && check(7) == 'X' && check(3) == ' ') { //
			return 3;
		} else if (check(3) == 'X' && check(7) == 'X' && check(5) == ' ') { // 357 - second diagonal
			return 5;
		}
		int returnValue = 0;

		returnValue = rng();
		while (check(returnValue) != ' ') {
			returnValue = rng();
		}
		return returnValue;
	}

	public static int ai() { // ai stands for Artificially Intelligence, does the difficulty and chance part

		int difficultyChance = (int) (Math.random() * 4) + 1; // Random Number from 1-4, stands for difficulty number

		int returnValue = aiSmarts();

		if (difficulty == 1) {

			if (difficultyChance == 1) { // Easy difficulty
				while (check(returnValue) != ' ') {
					returnValue = aiSmarts();
				}
				return returnValue;
			}
			returnValue = rng();
			while (check(returnValue) != ' ') {
				returnValue = rng();
			}
			return returnValue;

		} else if (difficulty == 2) {

			if (difficultyChance == 1 || difficultyChance == 2) { // Medium difficulty
				while (check(returnValue) != ' ') {
					returnValue = aiSmarts();
				}
				return returnValue;
			}
			returnValue = rng();
			while (check(returnValue) != ' ') {
				returnValue = rng();
			}
			return returnValue;

		} else if (difficulty == 3) {

			if (difficultyChance == 1 || difficultyChance == 2 || difficultyChance == 3) { // Hard difficulty
				while (check(returnValue) != ' ') {
					returnValue = aiSmarts();
				}
				return returnValue;
			}
			returnValue = rng();
			while (check(returnValue) != ' ') {
				returnValue = rng();
			}
			return returnValue;

		} else if (difficulty == 4) { // Impossible difficulty
			while (check(returnValue) != ' ') {
				returnValue = aiSmarts();
			}
			return returnValue;

		}

		returnValue = rng();
		while (check(returnValue) != ' ') {
			returnValue = rng();
		}
		return returnValue;
	}

	public static boolean gameOver(int different) { // go = Game Over

		// if different is 0, that means it asks for if the game is over
		// if different is 1, true means user won
		// if different is 2, true means CPU won

		if (gb[0][0] == gb[0][2] && gb[0][2] == gb[0][4] && (gb[0][0] == 'X' || gb[0][0] == 'O')) {
			if (different == 0)
				return true;
			if (different == 1)
				return (gb[0][0] == 'X');
			if (different == 2)
				return (gb[0][0] == 'O');

		} else if (gb[2][0] == gb[2][2] && gb[2][2] == gb[2][4] && (gb[2][0] == 'X' || gb[2][0] == 'O')) {
			if (different == 0)
				return true;
			if (different == 1)
				return (gb[2][0] == 'X');
			if (different == 2)
				return (gb[2][0] == 'O');

		} else if (gb[4][0] == gb[4][2] && gb[4][2] == gb[4][4] && (gb[4][0] == 'X' || gb[4][0] == 'O')) {
			if (different == 0)
				return true;
			if (different == 1)
				return (gb[4][0] == 'X');
			if (different == 2)
				return (gb[4][0] == 'O');

		} else if (gb[0][0] == gb[2][0] && gb[2][0] == gb[4][0] && (gb[0][0] == 'X' || gb[0][0] == 'O')) {
			if (different == 0)
				return true;
			if (different == 1)
				return (gb[0][0] == 'X');
			if (different == 2)
				return (gb[0][0] == 'O');

		} else if (gb[0][2] == gb[2][2] && gb[2][2] == gb[4][2] && (gb[0][2] == 'X' || gb[0][2] == 'O')) {
			if (different == 0)
				return true;
			if (different == 1)
				return (gb[0][2] == 'X');
			if (different == 2)
				return (gb[0][2] == 'O');

		} else if (gb[0][4] == gb[2][4] && gb[2][4] == gb[4][4] && (gb[0][4] == 'X' || gb[0][4] == 'O')) {
			if (different == 0)
				return true;
			if (different == 1)
				return (gb[0][4] == 'X');
			if (different == 2)
				return (gb[0][4] == 'O');

		} else if (gb[0][0] == gb[2][2] && gb[2][2] == gb[4][4] && (gb[0][0] == 'X' || gb[0][0] == 'O')) {
			if (different == 0)
				return true;
			if (different == 1)
				return (gb[0][0] == 'X');
			if (different == 2)
				return (gb[0][0] == 'O');

		} else if (gb[4][0] == gb[2][2] && gb[2][2] == gb[0][4] && (gb[4][0] == 'X' || gb[4][0] == 'O')) {
			if (different == 0)
				return true;
			if (different == 1)
				return (gb[4][0] == 'X');
			if (different == 2)
				return (gb[4][0] == 'O');

		}
		return false;
	}

	private static JFrame mainFrame = new JFrame("TicTacToe By: Rahulraj B)");
	private static JPanel gamePanel = new JPanel();
	private static JLabel title = new JLabel("Welcome to TicTacToe!");
	private static JLabel border1 = new JLabel("_____________________________");
	private static JLabel border2 = new JLabel("_____________________________");
	private static JLabel difficultyInstructions = new JLabel("Choose a Difficulty. Then CONFIRM.");
	private static JButton difficultyButton = new JButton("CONFIRM");
	private static JLabel instructions = new JLabel("Click a button below to start.");
	private static int[] called = new int[9]; // helps keep track which buttons are occupied

	private static JButton beginner = new JButton("Beginner (For Starters)");
	private static JButton easy = new JButton("Easy");
	private static JButton medium = new JButton("Medium");
	private static JButton hard = new JButton("Hard");
	private static JButton impossible = new JButton("IMPOSSIBLE");

	private static JButton whiteButton = new JButton();
	private static JButton blackButton = new JButton();
	private static JButton redButton = new JButton();
	private static JButton orangeButton = new JButton();
	private static JButton yellowButton = new JButton();
	private static JButton greenButton = new JButton();
	private static JButton cyanButton = new JButton();
	private static JButton blueButton = new JButton();
	private static JButton pinkButton = new JButton();

	private static Color backgroundColor = new Color(0, 0, 0);
	private static Color changeableColor = new Color(255, 255, 255);

	private static int aiSpace = 0;
	private static boolean tracker = false; // used to fix ai() loop when last button is clicked

	private static JButton button00 = new JButton(""); // row 0 col 0 button
	private static JButton button01 = new JButton(""); // row 0 col 1 button
	private static JButton button02 = new JButton(""); // row 0 col 2 button
	private static JButton button10 = new JButton(""); // row 1 col 0 button
	private static JButton button11 = new JButton(""); // row 1 col 1 button
	private static JButton button12 = new JButton(""); // row 1 col 2 button
	private static JButton button20 = new JButton(""); // row 2 col 0 button
	private static JButton button21 = new JButton(""); // row 2 col 1 button
	private static JButton button22 = new JButton(""); // row 2 col 2 button

	public static void cpuButtonChange(int location) {

		switch (location) {

		case 1:
			button00.setText("O");
			button00.setFont(new Font("Arial", Font.BOLD, 75));
			break;
		case 2:
			button01.setText("O");
			button01.setFont(new Font("Arial", Font.BOLD, 75));
			break;
		case 3:
			button02.setText("O");
			button02.setFont(new Font("Arial", Font.BOLD, 75));
			break;
		case 4:
			button10.setText("O");
			button10.setFont(new Font("Arial", Font.BOLD, 75));
			break;
		case 5:
			button11.setText("O");
			button11.setFont(new Font("Arial", Font.BOLD, 75));
			break;
		case 6:
			button12.setText("O");
			button12.setFont(new Font("Arial", Font.BOLD, 75));
			break;
		case 7:
			button20.setText("O");
			button20.setFont(new Font("Arial", Font.BOLD, 75));
			break;
		case 8:
			button21.setText("O");
			button21.setFont(new Font("Arial", Font.BOLD, 75));
			break;
		case 9:
			button22.setText("O");
			button22.setFont(new Font("Arial", Font.BOLD, 75));
			break;
		}

	}

	public static boolean isOccupied(int location) {

		if (called[location] == 1) {
			return true;
		}

		return false;

	}

	public static void gameOver() {

		if (gameOver(0)) {

			if (gameOver(1)) {
				printGB();
				called[0] = 1;
				called[1] = 1;
				called[2] = 1;
				called[3] = 1;
				called[4] = 1;
				called[5] = 1;
				called[6] = 1;
				called[7] = 1;
				called[8] = 1;
				instructions.setText("You won!");
				instructions.setBounds(100, 35, 300, 50);
				instructions.setFont(new Font("SansSerif", Font.BOLD, 22));
				tracker = true;
				return;
			} else if (gameOver(2)) {
				printGB();
				called[0] = 1;
				called[1] = 1;
				called[2] = 1;
				called[3] = 1;
				called[4] = 1;
				called[5] = 1;
				called[6] = 1;
				called[7] = 1;
				called[8] = 1;
				instructions.setText("You lost...");
				instructions.setBounds(97, 35, 300, 50);
				instructions.setFont(new Font("SansSerif", Font.BOLD, 22));
				tracker = true;
				return;
			}
		} else if (isOccupied(0) && isOccupied(1) && isOccupied(2) && isOccupied(3) && isOccupied(4) && isOccupied(5)
				&& isOccupied(6) && isOccupied(7) && isOccupied(8)) {
			printGB();
			called[0] = 1;
			called[1] = 1;
			called[2] = 1;
			called[3] = 1;
			called[4] = 1;
			called[5] = 1;
			called[6] = 1;
			called[7] = 1;
			called[8] = 1;
			instructions.setText("TIE GAME");
			instructions.setBounds(96, 35, 300, 50);
			instructions.setFont(new Font("SansSerif", Font.BOLD, 22));
			tracker = true;
			return;
		}

		printGB();

	}

	public static void colorChange(Color color) { // 0 means Foreground, 1, Background

		Color colorChanger = Color.RED;

		if (color == Color.RED)
			colorChanger = Color.RED;
		if (color == Color.ORANGE)
			colorChanger = Color.ORANGE;
		if (color == Color.YELLOW)
			colorChanger = Color.YELLOW;
		if (color == Color.GREEN)
			colorChanger = Color.GREEN;
		if (color == Color.CYAN)
			colorChanger = Color.CYAN;
		if (color == Color.BLUE)
			colorChanger = Color.BLUE;
		if (color == Color.MAGENTA)
			colorChanger = Color.MAGENTA;

		changeableColor = colorChanger;
		title.setForeground(colorChanger);
		border1.setForeground(colorChanger);
		border2.setForeground(colorChanger);
		instructions.setForeground(colorChanger);

		beginner.setForeground(colorChanger);
		easy.setForeground(colorChanger);
		medium.setForeground(colorChanger);
		hard.setForeground(colorChanger);
		impossible.setForeground(colorChanger);

		difficultyInstructions.setForeground(colorChanger);
		difficultyButton.setForeground(colorChanger);

		button00.setForeground(colorChanger);
		button01.setForeground(colorChanger);
		button02.setForeground(colorChanger);
		button10.setForeground(colorChanger);
		button11.setForeground(colorChanger);
		button12.setForeground(colorChanger);
		button20.setForeground(colorChanger);
		button21.setForeground(colorChanger);
		button22.setForeground(colorChanger);
		gamePanel.setVisible(true);
	}

	public Main() {

		mainFrame.setSize(316, 420);
		Dimension size = Toolkit. getDefaultToolkit(). getScreenSize();
		mainFrame.setLocation((int)size.getWidth()/2 - 158, (int)size.getHeight()/2 - 210);
		mainFrame.setResizable(false);
		gamePanel.setBackground(Color.BLACK);
		mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		mainFrame.add(gamePanel);
		gamePanel.setLayout(null);

		title.setBounds(12, -6, 400, 50);
		title.setFont(new Font("SansSerif", Font.ITALIC, 26));
		title.setForeground(Color.WHITE);
		gamePanel.add(title);

		border1.setBounds(0, -36, 400, 50);
		border1.setForeground(Color.WHITE);
		border1.setFont(new Font("SansSerif", Font.PLAIN, 22));
		gamePanel.add(border1);

		border2.setBounds(0, 2, 400, 50);
		border2.setForeground(Color.WHITE);
		border2.setFont(new Font("SansSerif", Font.PLAIN, 22));
		gamePanel.add(border2);

		difficultyInstructions.setBounds(13, 40, 300, 50);
		difficultyInstructions.setForeground(Color.WHITE);
		difficultyInstructions.setFont(new Font("SansSerif", Font.BOLD, 16));
		gamePanel.add(difficultyInstructions);

		try {
			UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
		} catch (Exception f) {
			f.printStackTrace();
		}

		beginner.setBounds(32, 90, 235, 35);
		beginner.setFont(new Font("SansSerif", Font.ITALIC, 18));
		beginner.setBackground(Color.BLACK);
		beginner.setForeground(Color.WHITE);
		beginner.setOpaque(false);
		gamePanel.add(beginner);

		easy.setBounds(32, 135, 235, 35);
		easy.setFont(new Font("SansSerif", Font.ITALIC, 18));
		easy.setBackground(Color.BLACK);
		easy.setForeground(Color.WHITE);
		easy.setOpaque(false);
		gamePanel.add(easy);

		medium.setBounds(32, 180, 235, 35);
		medium.setFont(new Font("SansSerif", Font.ITALIC, 18));
		medium.setBackground(Color.BLACK);
		medium.setForeground(Color.WHITE);
		medium.setOpaque(false);
		gamePanel.add(medium);

		hard.setBounds(32, 225, 235, 35);
		hard.setFont(new Font("SansSerif", Font.ITALIC, 18));
		hard.setBackground(Color.BLACK);
		hard.setForeground(Color.WHITE);
		hard.setOpaque(false);
		gamePanel.add(hard);

		impossible.setBounds(32, 270, 235, 35);
		impossible.setFont(new Font("SansSerif", Font.ITALIC, 18));
		impossible.setBackground(Color.BLACK);
		impossible.setForeground(Color.WHITE);
		impossible.setOpaque(false);
		gamePanel.add(impossible);

		difficultyButton.setBounds(32, 325, 235, 40);
		difficultyButton.setFont(new Font("SansSerif", Font.BOLD, 28));
		difficultyButton.setBackground(Color.BLACK);
		difficultyButton.setForeground(Color.WHITE);
		difficultyButton.setOpaque(false);
		gamePanel.add(difficultyButton);

		redButton.setBounds(0, 90, 20, 30);
		redButton.setFont(new Font("SansSerif", Font.PLAIN, 8));
		redButton.setBackground(Color.RED);
		redButton.setOpaque(true);
		gamePanel.add(redButton);

		orangeButton.setBounds(0, 120, 20, 30);
		orangeButton.setFont(new Font("SansSerif", Font.PLAIN, 8));
		orangeButton.setBackground(Color.ORANGE);
		orangeButton.setOpaque(true);
		gamePanel.add(orangeButton);

		yellowButton.setBounds(0, 150, 20, 30);
		yellowButton.setFont(new Font("SansSerif", Font.PLAIN, 8));
		yellowButton.setBackground(Color.YELLOW);
		yellowButton.setOpaque(true);
		gamePanel.add(yellowButton);

		greenButton.setBounds(0, 180, 20, 30);
		greenButton.setFont(new Font("SansSerif", Font.PLAIN, 8));
		greenButton.setBackground(Color.GREEN);
		greenButton.setOpaque(true);
		gamePanel.add(greenButton);

		cyanButton.setBounds(0, 210, 20, 30);
		cyanButton.setFont(new Font("SansSerif", Font.PLAIN, 8));
		cyanButton.setBackground(Color.CYAN);
		cyanButton.setOpaque(true);
		gamePanel.add(cyanButton);

		blueButton.setBounds(0, 240, 20, 30);
		blueButton.setFont(new Font("SansSerif", Font.PLAIN, 8));
		blueButton.setBackground(Color.BLUE);
		blueButton.setOpaque(true);
		gamePanel.add(blueButton);

		pinkButton.setBounds(0, 270, 20, 30);
		pinkButton.setFont(new Font("SansSerif", Font.PLAIN, 8));
		pinkButton.setBackground(Color.MAGENTA);
		pinkButton.setOpaque(true);
		gamePanel.add(pinkButton);

		whiteButton.setBounds(280, 90, 20, 30);
		whiteButton.setFont(new Font("SansSerif", Font.PLAIN, 8));
		whiteButton.setBackground(Color.WHITE);
		whiteButton.setOpaque(true);
		gamePanel.add(whiteButton);

		blackButton.setBounds(280, 120, 20, 30);
		blackButton.setFont(new Font("SansSerif", Font.PLAIN, 8));
		blackButton.setBackground(Color.BLACK);
		blackButton.setOpaque(true);
		gamePanel.add(blackButton);

		beginner.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				difficulty = 0;

			}

		});

		easy.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				difficulty = 1;

			}

		});

		medium.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				difficulty = 2;

			}

		});

		hard.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				difficulty = 3;

			}

		});

		impossible.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				difficulty = 4;

			}

		});

		redButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				colorChange(Color.RED);

			}

		});

		orangeButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				colorChange(Color.ORANGE);

			}

		});

		yellowButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				colorChange(Color.YELLOW);

			}

		});

		greenButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				colorChange(Color.GREEN);

			}

		});

		cyanButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				colorChange(Color.CYAN);

			}

		});

		blueButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				colorChange(Color.BLUE);

			}

		});

		pinkButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				colorChange(Color.MAGENTA);

			}

		});

		whiteButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				gamePanel.setBackground(Color.WHITE);
				backgroundColor = Color.WHITE;

			}

		});

		blackButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				gamePanel.setBackground(Color.BLACK);
				backgroundColor = Color.BLACK;

			}

		});

		mainFrame.setVisible(true);
		difficultyButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) { // this method is where the game will actually START

				if (difficulty > -1) { // ********************** THIS is where the game starts
										// **********************

					beginner.setVisible(false);
					easy.setVisible(false);
					medium.setVisible(false);
					hard.setVisible(false);
					impossible.setVisible(false);
					difficultyInstructions.setVisible(false);
					difficultyButton.setVisible(false);
					whiteButton.setVisible(false);
					blackButton.setVisible(false);
					redButton.setVisible(false);
					orangeButton.setVisible(false);
					yellowButton.setVisible(false);
					greenButton.setVisible(false);
					cyanButton.setVisible(false);
					blueButton.setVisible(false);
					pinkButton.setVisible(false);

					mainFrame.add(instructions);
					instructions.setBounds(25, 35, 300, 50);
					instructions.setForeground(changeableColor);
					instructions.setFont(new Font("SansSerif", Font.BOLD, 18));
					gamePanel.add(instructions);
					instructions.setVisible(true);

					try {
						UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
					} catch (Exception f) {
						f.printStackTrace();
					}

					mainFrame.add(button00);
					button00.setBounds(0, 81, 100, 100);
					button00.setBackground(backgroundColor);
					button00.setForeground(changeableColor);
					button00.setOpaque(false);
					gamePanel.add(button00);
					button00.setVisible(true);
					button00.addActionListener(new ActionListener() {

						@Override
						public void actionPerformed(ActionEvent arg0) {

							if (called[0] == 1)
								return; // Returns nothing if button is occupied

							called[0] = 1; // Changes value accordingly to occupy this button
							System.out.println("User: 1"); // Confirmation on Console only once

							button00.setText("X"); // Regular Changing of text on button
							button00.setFont(new Font("Arial", Font.BOLD, 75));

							play("User", 1); // TicTactoe is used to use its own methods here, kinda

							gameOver(); // "stops" the program when all buttons are pressed
							if (tracker)
								return;

							aiSpace = ai();

							play("CPU", aiSpace); // finds a value for the CPU to use, depending on the
													// difficulty

							cpuButtonChange(aiSpace);
							System.out.println("CPU: " + aiSpace);
							called[aiSpace - 1] = 1;

							gameOver();

							return;

						}

					});

					mainFrame.add(button01);
					button01.setBounds(100, 81, 100, 100);
					button01.setBackground(backgroundColor);
					button01.setForeground(changeableColor);
					button01.setOpaque(false);
					gamePanel.add(button01);
					button01.setVisible(true);
					button01.addActionListener(new ActionListener() {

						@Override
						public void actionPerformed(ActionEvent arg0) {
							if (called[1] == 1)
								return; // Returns nothing if button is occupied

							called[1] = 1; // Changes value accordingly to occupy this button
							System.out.println("User: 2"); // Confirmation on Console only once

							button01.setText("X"); // Regular Changing of text on button
							button01.setFont(new Font("Arial", Font.BOLD, 75));

							play("User", 2); // TicTactoe is used to use its own methods here, kinda

							gameOver();
							if (tracker)
								return;

							aiSpace = ai();

							play("CPU", aiSpace); // finds a value for the CPU to use, depending on the
													// difficulty

							cpuButtonChange(aiSpace);
							System.out.println("CPU: " + aiSpace);
							called[aiSpace - 1] = 1;

							gameOver();

							return;

						}

					});

					mainFrame.add(button02);
					button02.setBounds(200, 81, 100, 100);
					button02.setBackground(backgroundColor);
					button02.setForeground(changeableColor);
					button02.setOpaque(false);
					gamePanel.add(button02);
					button02.setVisible(true);
					button02.addActionListener(new ActionListener() {

						@Override
						public void actionPerformed(ActionEvent arg0) {
							if (called[2] == 1)
								return; // Returns nothing if button is occupied

							called[2] = 1; // Changes value accordingly to occupy this button
							System.out.println("User: 3"); // Confirmation on Console only once

							button02.setText("X"); // Regular Changing of text on button
							button02.setFont(new Font("Arial", Font.BOLD, 75));

							play("User", 3); // TicTactoe is used to use its own methods here, kinda

							gameOver();
							if (tracker)
								return;

							aiSpace = ai();

							play("CPU", aiSpace); // finds a value for the CPU to use, depending on the
													// difficulty

							cpuButtonChange(aiSpace);
							System.out.println("CPU: " + aiSpace);
							called[aiSpace - 1] = 1;

							gameOver();

							return;

						}

					});

					mainFrame.add(button10);
					button10.setBounds(0, 181, 100, 100);
					button10.setBackground(backgroundColor);
					button10.setForeground(changeableColor);
					button10.setOpaque(false);
					gamePanel.add(button10);
					button10.setVisible(true);
					button10.addActionListener(new ActionListener() {

						@Override
						public void actionPerformed(ActionEvent arg0) {
							if (called[3] == 1)
								return; // Returns nothing if button is occupied

							called[3] = 1; // Changes value accordingly to occupy this button
							System.out.println("User: 4"); // Confirmation on Console only once

							button10.setText("X"); // Regular Changing of text on button
							button10.setFont(new Font("Arial", Font.BOLD, 75));

							play("User", 4); // TicTactoe is used to use its own methods here, kinda

							gameOver();
							if (tracker)
								return;

							aiSpace = ai();

							play("CPU", aiSpace); // finds a value for the CPU to use, depending on the
													// difficulty

							cpuButtonChange(aiSpace);
							System.out.println("CPU: " + aiSpace);
							called[aiSpace - 1] = 1;

							gameOver();

							return;

						}

					});

					mainFrame.add(button11);
					button11.setBounds(100, 181, 100, 100);
					button11.setBackground(backgroundColor);
					button11.setForeground(changeableColor);
					button11.setOpaque(false);
					gamePanel.add(button11);
					button11.setVisible(true);
					button11.addActionListener(new ActionListener() {

						@Override
						public void actionPerformed(ActionEvent arg0) {
							if (called[4] == 1)
								return; // Returns nothing if button is occupied

							called[4] = 1; // Changes value accordingly to occupy this button
							System.out.println("User: 5"); // Confirmation on Console only once

							button11.setText("X"); // Regular Changing of text on button
							button11.setFont(new Font("Arial", Font.BOLD, 75));

							play("User", 5); // TicTactoe is used to use its own methods here, kinda

							gameOver();
							if (tracker)
								return;

							aiSpace = ai();

							play("CPU", aiSpace); // finds a value for the CPU to use, depending on the
													// difficulty

							cpuButtonChange(aiSpace);
							System.out.println("CPU: " + aiSpace);
							called[aiSpace - 1] = 1;

							gameOver();

							return;

						}

					});

					mainFrame.add(button12);
					button12.setBounds(200, 181, 100, 100);
					button12.setBackground(backgroundColor);
					button12.setForeground(changeableColor);
					button12.setOpaque(false);
					gamePanel.add(button12);
					button12.setVisible(true);
					button12.addActionListener(new ActionListener() {

						@Override
						public void actionPerformed(ActionEvent arg0) {
							if (called[5] == 1)
								return; // Returns nothing if button is occupied

							called[5] = 1; // Changes value accordingly to occupy this button
							System.out.println("User: 6"); // Confirmation on Console only once

							button12.setText("X"); // Regular Changing of text on button
							button12.setFont(new Font("Arial", Font.BOLD, 75));

							play("User", 6); // TicTactoe is used to use its own methods here, kinda

							gameOver();
							if (tracker)
								return;

							aiSpace = ai();

							play("CPU", aiSpace); // finds a value for the CPU to use, depending on the
													// difficulty

							cpuButtonChange(aiSpace);
							System.out.println("CPU: " + aiSpace);
							called[aiSpace - 1] = 1;

							gameOver();

							return;

						}

					});

					mainFrame.add(button20);
					button20.setBounds(0, 281, 100, 100);
					button20.setBackground(backgroundColor);
					button20.setForeground(changeableColor);
					button20.setOpaque(false);
					gamePanel.add(button20);
					button20.setVisible(true);
					button20.addActionListener(new ActionListener() {

						@Override
						public void actionPerformed(ActionEvent arg0) {
							if (called[6] == 1)
								return; // Returns nothing if button is occupied

							called[6] = 1; // Changes value accordingly to occupy this button
							System.out.println("User: 7"); // Confirmation on Console only once

							button20.setText("X"); // Regular Changing of text on button
							button20.setFont(new Font("Arial", Font.BOLD, 75));

							play("User", 7); // TicTactoe is used to use its own methods here, kinda

							gameOver();
							if (tracker)
								return;

							aiSpace = ai();

							play("CPU", aiSpace); // finds a value for the CPU to use, depending on the
													// difficulty

							cpuButtonChange(aiSpace);
							System.out.println("CPU: " + aiSpace);
							called[aiSpace - 1] = 1;

							gameOver();

							return;

						}

					});

					mainFrame.add(button21);
					button21.setBounds(100, 281, 100, 100);
					button21.setBackground(backgroundColor);
					button21.setForeground(changeableColor);
					button21.setOpaque(false);
					gamePanel.add(button21);
					button21.setVisible(true);
					button21.addActionListener(new ActionListener() {

						@Override
						public void actionPerformed(ActionEvent arg0) {
							if (called[7] == 1)
								return; // Returns nothing if button is occupied

							called[7] = 1; // Changes value accordingly to occupy this button
							System.out.println("User: 8"); // Confirmation on Console only once

							button21.setText("X"); // Regular Changing of text on button
							button21.setFont(new Font("Arial", Font.BOLD, 75));

							play("User", 8); // TicTactoe is used to use its own methods here, kinda

							gameOver();
							if (tracker)
								return;

							aiSpace = ai();

							play("CPU", aiSpace); // finds a value for the CPU to use, depending on the
													// difficulty

							cpuButtonChange(aiSpace);
							System.out.println("CPU: " + aiSpace);
							called[aiSpace - 1] = 1;

							gameOver();

							return;

						}

					});

					mainFrame.add(button22);
					button22.setBounds(200, 281, 100, 100);
					button22.setBackground(backgroundColor);
					button22.setForeground(changeableColor);
					button22.setOpaque(false);
					gamePanel.add(button22);
					button22.setVisible(true);
					button22.addActionListener(new ActionListener() {

						@Override
						public void actionPerformed(ActionEvent arg0) {
							if (called[8] == 1)
								return; // Returns nothing if button is occupied

							called[8] = 1; // Changes value accordingly to occupy this button
							System.out.println("User: 9"); // Confirmation on Console only once

							button22.setText("X"); // Regular Changing of text on button
							button22.setFont(new Font("Arial", Font.BOLD, 75));

							play("User", 9); // TicTactoe is used to use its own methods here, kinda

							gameOver();
							if (tracker)
								return;

							aiSpace = ai();

							play("CPU", aiSpace); // finds a value for the CPU to use, depending on the
													// difficulty

							cpuButtonChange(aiSpace);
							System.out.println("CPU: " + aiSpace);
							called[aiSpace - 1] = 1;

							gameOver();

							return;

						}

					});

				}

			}

		});

	}

	public static void main(String[] args) {

		new Main();

	}

}
