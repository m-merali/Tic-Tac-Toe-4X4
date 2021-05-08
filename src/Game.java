import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;

public class Game extends JFrame{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	Position position = new Position();
	JButton[] buttons = new JButton[Position.SIZE];
	
	public Game(){
		String Message1 = "You Are 'X' ";
		JOptionPane.showMessageDialog(null, Message1);
		setLayout(new GridLayout(Position.DIM,Position.DIM));
		for (int i = 0; i < Position.SIZE; i++) {
			final JButton button = createButtons();
			buttons[i] = button;
			final int idx = i;
			button.addMouseListener(new MouseAdapter() {
				public void mouseClicked(MouseEvent e){
					button.setText((Character.toString(position.turn)));
					position.move(idx);
					if(!position.isGameEnd()){
						int best = position.bestmove();
						buttons[best].setText(Character.toString(position.turn));
						position.move(best);
					}
					if (position.isGameEnd()){
						String Message = position.isWinFor('x') ? "You Won!" :
							position.isWinFor('o') ? "Computer Won!" : "Draw";
						JOptionPane.showMessageDialog(null, Message);
					}
				}
			});
		}
		pack();
		setVisible(true);
		
	}

	private JButton createButtons() {
		JButton button = new JButton();
		button.setPreferredSize(new Dimension(100,100));
		button.setBackground(Color.BLACK);
		button.setOpaque(false);
		button.setFont(new Font(null, Font.PLAIN, 50));
		add(button);
		return button;
		
	}

	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				new Game();
			}
		});
	
	}
	
}
	
