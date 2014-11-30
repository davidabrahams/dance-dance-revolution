package gamepanels;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;

import main.MainFrame;
import songinfo.Song;

/**
 * The Class GamePanel.
 */
public class GamePanel extends JPanel implements ActionListener
{

  /** The frame. */
  private MainFrame frame;
  
  /** The center panel. */
  private InGamePanel centerPanel;
  
  /** The button go back. */
  private JButton btnGoBack;

  /**
   * Create the panel.
   *
   * @param frame the containing frame
   */
  public GamePanel(MainFrame frame)
  {
    setBackground(Color.BLACK);
    this.frame = frame;
    setLayout(new BorderLayout(0, 0));

    centerPanel = new InGamePanel();
    add(centerPanel, BorderLayout.CENTER);

    setFocusable(true);

    JPanel panel = new JPanel();
    add(panel, BorderLayout.SOUTH);

    btnGoBack = new JButton("Go Back");
    panel.add(btnGoBack);
    btnGoBack.addActionListener(this);

  }

  /**
   * Sets the song.
   *
   * @param song the new song
   */
  public void setSong(Song song)
  {
    centerPanel.setSong(song);
  }

  /**
   * Start the game.
   *
   * @param numPlayers the number players
   * @param difficultyLevel the difficulty level
   */
  public void startGame(int numPlayers, int difficultyLevel)
  {
    centerPanel.startGame(numPlayers, difficultyLevel);
  }

  /* (non-Javadoc)
   * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
   */
  public void actionPerformed(ActionEvent arg0)
  {
    if (arg0.getSource() == btnGoBack)
    {
      centerPanel.closeSong();
      frame.switchCard(MainFrame.MAIN_MENU_PANEL);
    }
  }

  /**
   * Gets the in-game panel.
   *
   * @return the in game panel
   */
  public InGamePanel getInGamePanel()
  {
    return this.centerPanel;
  }

}
