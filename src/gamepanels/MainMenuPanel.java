package gamepanels;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import main.MainFrame;
import misc.MovingImage;

/**
 * The Class MainMenuPanel.
 */
public class MainMenuPanel extends JPanel implements ActionListener
{

  /** The containing frame. */
  private MainFrame frame;

  /** The button play now. */
  private JButton btnPlayNow;

  /** The button manage music. */
  private JButton btnManageMusic;

  /** The button help. */
  private JButton btnHelp;

  /** The button manage serial. */
  private JButton btnManageSerial;

  /**
   * Create the panel.
   * 
   * @param frame the frame
   */
  public MainMenuPanel(MainFrame frame)
  {
    this.frame = frame;

    GridBagLayout gridBagLayout = new GridBagLayout();
    gridBagLayout.columnWeights = new double[] {1.0};
    setLayout(gridBagLayout);

    JPanel topRedPanel = new JPanel();
    topRedPanel.setBackground(Color.RED);
    GridBagConstraints gbc_topRedPanel = new GridBagConstraints();
    gbc_topRedPanel.weighty = 0.2;
    gbc_topRedPanel.fill = GridBagConstraints.BOTH;
    gbc_topRedPanel.gridx = 0;
    gbc_topRedPanel.gridy = 0;
    add(topRedPanel, gbc_topRedPanel);

    JPanel largeGreyPanel = new JPanel();
    GridBagConstraints gbc_largeGreyPanel = new GridBagConstraints();
    gbc_largeGreyPanel.weighty = 0.6;
    gbc_largeGreyPanel.fill = GridBagConstraints.BOTH;
    gbc_largeGreyPanel.gridx = 0;
    gbc_largeGreyPanel.gridy = 1;
    add(largeGreyPanel, gbc_largeGreyPanel);
    largeGreyPanel.setLayout(new BorderLayout(0, 0));

    JPanel buttonPanel = new JPanel();
    buttonPanel.setBackground(Color.GRAY);
    largeGreyPanel.add(buttonPanel, BorderLayout.SOUTH);
    GridBagLayout gbl_buttonPanel = new GridBagLayout();
    buttonPanel.setLayout(gbl_buttonPanel);

    btnPlayNow = new JButton("Play Now!");
    btnPlayNow.addActionListener(this);
    GridBagConstraints gbc_btnPlayNow = new GridBagConstraints();
    gbc_btnPlayNow.fill = GridBagConstraints.HORIZONTAL;
    gbc_btnPlayNow.gridx = 0;
    gbc_btnPlayNow.gridy = 0;
    buttonPanel.add(btnPlayNow, gbc_btnPlayNow);

    btnManageMusic = new JButton("Manage Music");
    btnManageMusic.addActionListener(this);
    GridBagConstraints gbc_btnManageMusic = new GridBagConstraints();
    gbc_btnManageMusic.fill = GridBagConstraints.HORIZONTAL;
    gbc_btnManageMusic.gridx = 0;
    gbc_btnManageMusic.gridy = 1;
    buttonPanel.add(btnManageMusic, gbc_btnManageMusic);

    btnManageSerial = new JButton("Manage Serial");
    btnManageSerial.addActionListener(this);
    GridBagConstraints gbc_btnManageSerial = new GridBagConstraints();
    gbc_btnManageSerial.fill = GridBagConstraints.HORIZONTAL;
    gbc_btnManageSerial.gridx = 0;
    gbc_btnManageSerial.gridy = 2;
    buttonPanel.add(btnManageSerial, gbc_btnManageSerial);

    btnHelp = new JButton("Help");
    btnHelp.addActionListener(this);
    GridBagConstraints gbc_btnHelp = new GridBagConstraints();
    gbc_btnHelp.fill = GridBagConstraints.HORIZONTAL;
    gbc_btnHelp.gridx = 0;
    gbc_btnHelp.gridy = 3;
    buttonPanel.add(btnHelp, gbc_btnHelp);

    GraphicsPanel graphicsPanel = new GraphicsPanel();
    graphicsPanel.setBackground(Color.GRAY);
    largeGreyPanel.add(graphicsPanel, BorderLayout.CENTER);

    JPanel bottomRedPanel = new JPanel();
    bottomRedPanel.setBackground(Color.RED);
    GridBagConstraints gbc_bottomRedPanel = new GridBagConstraints();
    gbc_bottomRedPanel.weighty = 0.2;
    gbc_bottomRedPanel.fill = GridBagConstraints.BOTH;
    gbc_bottomRedPanel.gridx = 0;
    gbc_bottomRedPanel.gridy = 2;
    add(bottomRedPanel, gbc_bottomRedPanel);

  }

  /*
   * (non-Javadoc)
   * 
   * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
   */
  public void actionPerformed(ActionEvent e)
  {
    if (e.getSource() == btnManageMusic)
      frame.switchCard((MainFrame.IMPORT_SONG_PANEL));
    else if (e.getSource() == btnPlayNow)
      frame.switchCard(MainFrame.SONG_SELECT_PANEL);
    else if (e.getSource() == btnManageSerial)
    {
      String input = JOptionPane.showInputDialog(this, "Enter the name of the serial port.");
      if (input != null)
        frame.getMain().setSerialPort(input);
    }
    else if (e.getSource() == btnHelp)
      frame.switchCard(MainFrame.HELP_PANEL);
  }

}

class GraphicsPanel extends ResizingPanel
{

  private MovingImage ddrLogo;

  GraphicsPanel()
  {
    super(800, 286);
    setBackground(Color.ORANGE);

    Point ddrLogoDrawingPoint = centerDrawingPointForImage(800, 280);
    ddrLogo = new MovingImage("pic/ddrlogo.png", ddrLogoDrawingPoint.x, ddrLogoDrawingPoint.y, 800, 280);
  }

  protected void paintComponent(Graphics g)
  {
    super.paintComponent(g);
    Graphics2D g2D = getScaledGraphics2D(g);
    ddrLogo.draw(g2D, this);
  }
}