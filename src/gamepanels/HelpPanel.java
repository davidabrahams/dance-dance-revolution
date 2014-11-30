package gamepanels;

import java.awt.BorderLayout;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;

import main.MainFrame;

/**
 * The Class HelpPanel.
 */
public class HelpPanel extends JPanel implements ActionListener
{

  /** The containing frame. */
  private MainFrame frame;

  /** The button go back. */
  private JButton btnGoBack;

  /** The image panel. */
  private JPanel imagePanel;

  /**
   * Create the panel.
   * 
   * @param frame the frame
   */
  public HelpPanel(MainFrame frame)
  {
    this.frame = frame;

    setLayout(new BorderLayout(0, 0));

    imagePanel = new ImagePanel();
    add(imagePanel, BorderLayout.CENTER);

    JPanel buttonPanel = new JPanel();
    add(buttonPanel, BorderLayout.SOUTH);

    btnGoBack = new JButton("Go Back");
    btnGoBack.addActionListener(this);
    buttonPanel.add(btnGoBack);

  }

  /*
   * (non-Javadoc)
   * 
   * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
   */
  public void actionPerformed(ActionEvent arg0)
  {
    if (arg0.getSource() == btnGoBack)
      frame.switchCard(MainFrame.MAIN_MENU_PANEL);
  }

}

class ImagePanel extends ResizingPanel
{

  public ImagePanel()
  {
    super(800, 539);
  }

  protected void paintComponent(Graphics g)
  {
    getScaledGraphics2D(g).drawImage(new ImageIcon("pic/HelpScreen.png").getImage(), 0, 0, 800, 539, this);
  }
}