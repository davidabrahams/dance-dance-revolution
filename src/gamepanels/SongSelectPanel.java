package gamepanels;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import main.MainFrame;
import songinfo.Song;

/**
 * The Class ImportSongPanel.
 */
public class SongSelectPanel extends JPanel implements ActionListener, ListSelectionListener
{

  /** The list model. */
  private DefaultListModel listModel;

  /** The frame. */
  private MainFrame frame;

  /** The btn go back. */
  private JButton btnGoBack;

  /** The list. */
  private JList list;
  
  /** The bottom panel. */
  private JPanel bottomPanel;
  
  /** The display song panel. */
  private JPanel displaySongPanel;
  
  /** The lbl background. */
  private JLabel lblBackground;
  
  /** The lbl bg. */
  private JLabel lblBg;
  
  /** The lbl banner. */
  private JLabel lblBanner;
  
  /** The lbl bn. */
  private JLabel lblBn;
  
  /** The lbl cd title. */
  private JLabel lblCdTitle;
  
  /** The lbl cd. */
  private JLabel lblCd;
  
  /** The btn play now. */
  private JButton btnPlayNow;

  /**
   * Create the panel.
   *
   * @param frame the frame
   * @param listModel the list model
   */
  public SongSelectPanel(MainFrame frame, DefaultListModel listModel)
  {
    this.frame = frame;
    this.listModel = listModel;

    setBackground(Color.DARK_GRAY);
    setLayout(new BorderLayout(0, 0));

    JPanel libraryPanel = new JPanel();
    libraryPanel.setBackground(Color.DARK_GRAY);
    add(libraryPanel, BorderLayout.EAST);
    GridBagLayout gbl_libraryPanel = new GridBagLayout();
    gbl_libraryPanel.rowWeights = new double[] {0.0, 1.0};
    libraryPanel.setLayout(gbl_libraryPanel);

    JLabel lblSelectSong = new JLabel("Select Song:");
    lblSelectSong.setForeground(Color.WHITE);
    GridBagConstraints gbc_lblSelectSong = new GridBagConstraints();
    gbc_lblSelectSong.gridx = 0;
    gbc_lblSelectSong.gridy = 0;
    libraryPanel.add(lblSelectSong, gbc_lblSelectSong);

    list = new JList(this.listModel);
    list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
    list.addListSelectionListener(this);
    list.setBackground(Color.GRAY);
    list.setForeground(Color.WHITE);
    GridBagConstraints gbc_list = new GridBagConstraints();
    gbc_list.fill = GridBagConstraints.BOTH;
    gbc_list.gridx = 0;
    gbc_list.gridy = 1;
    libraryPanel.add(list, gbc_list);

    bottomPanel = new JPanel();
    bottomPanel.setBackground(Color.GRAY);
    add(bottomPanel, BorderLayout.SOUTH);
    GridBagLayout gbl_bottomPanel = new GridBagLayout();
    bottomPanel.setLayout(gbl_bottomPanel);

    btnGoBack = new JButton("Go Back");
    btnGoBack.addActionListener(this);
    GridBagConstraints gbc_btnGoBack = new GridBagConstraints();
    gbc_btnGoBack.fill = GridBagConstraints.BOTH;
    gbc_btnGoBack.gridx = 0;
    gbc_btnGoBack.gridy = 0;
    bottomPanel.add(btnGoBack, gbc_btnGoBack);

    btnPlayNow = new JButton("Play Now!");
    btnPlayNow.addActionListener(this);
    GridBagConstraints gbc_btnPlayNow = new GridBagConstraints();
    gbc_btnPlayNow.gridx = 1;
    gbc_btnPlayNow.gridy = 0;
    bottomPanel.add(btnPlayNow, gbc_btnPlayNow);

    displaySongPanel = new JPanel();
    displaySongPanel.setBackground(Color.DARK_GRAY);
    add(displaySongPanel, BorderLayout.CENTER);
    GridBagLayout gbl_displaySongPanel = new GridBagLayout();
    gbl_displaySongPanel.rowWeights = new double[] {1.0, 1.0, 1.0, 1.0};
    gbl_displaySongPanel.columnWeights = new double[] {1.0, 1.0};
    displaySongPanel.setLayout(gbl_displaySongPanel);

    lblBackground = new JLabel("Background:");
    lblBackground.setForeground(Color.WHITE);
    GridBagConstraints gbc_lblBackground = new GridBagConstraints();
    gbc_lblBackground.gridwidth = 2;
    gbc_lblBackground.gridx = 0;
    gbc_lblBackground.gridy = 0;
    displaySongPanel.add(lblBackground, gbc_lblBackground);

    lblBg = new JLabel();
    GridBagConstraints gbc_lblBg = new GridBagConstraints();
    gbc_lblBg.gridwidth = 2;
    gbc_lblBg.gridx = 0;
    gbc_lblBg.gridy = 1;
    displaySongPanel.add(lblBg, gbc_lblBg);

    lblBanner = new JLabel("Banner:");
    lblBanner.setForeground(Color.WHITE);
    GridBagConstraints gbc_lblBanner = new GridBagConstraints();
    gbc_lblBanner.gridx = 0;
    gbc_lblBanner.gridy = 2;
    displaySongPanel.add(lblBanner, gbc_lblBanner);

    lblCdTitle = new JLabel("CD Title:");
    lblCdTitle.setForeground(Color.WHITE);
    GridBagConstraints gbc_lblCdTitle = new GridBagConstraints();
    gbc_lblCdTitle.gridx = 1;
    gbc_lblCdTitle.gridy = 2;
    displaySongPanel.add(lblCdTitle, gbc_lblCdTitle);

    lblBn = new JLabel();
    GridBagConstraints gbc_lblBn = new GridBagConstraints();
    gbc_lblBn.gridx = 0;
    gbc_lblBn.gridy = 3;
    displaySongPanel.add(lblBn, gbc_lblBn);

    lblCd = new JLabel();
    GridBagConstraints gbc_lblCd = new GridBagConstraints();
    gbc_lblCd.gridx = 1;
    gbc_lblCd.gridy = 3;
    displaySongPanel.add(lblCd, gbc_lblCd);

  }

  /*
   * (non-Javadoc)
   * 
   * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
   */
  public void actionPerformed(ActionEvent e)
  {
    if (e.getSource() == btnGoBack)
      frame.switchCard(MainFrame.MAIN_MENU_PANEL);
    else if (e.getSource() == btnPlayNow)
    {
      int selected = list.getSelectedIndex();
      if (selected == -1)
        JOptionPane.showMessageDialog(this, "You must select a song to play", null, JOptionPane.ERROR_MESSAGE);
      else
      {
        int multiPlayer = JOptionPane.showConfirmDialog(this, "Would you like to play with two players?");
        int numPlayers = -1;
        if (multiPlayer == JOptionPane.YES_OPTION)
          numPlayers = 2;
        else if (multiPlayer == JOptionPane.NO_OPTION)
          numPlayers = 1;
        if (numPlayers != -1)
        {
          JComboBox box = new JComboBox();
          for (int i = 1; i <= 10; i++)
            box.addItem((Integer) i);
          int option = JOptionPane.showOptionDialog(this, box, "Select the difficulty level.", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE, null, null, null);
          if (option == JOptionPane.OK_OPTION)
            frame.startGame((Song) listModel.get(selected), numPlayers, (Integer) box.getSelectedItem());
        }
      }
    }
  }

  /* (non-Javadoc)
   * @see javax.swing.event.ListSelectionListener#valueChanged(javax.swing.event.ListSelectionEvent)
   */
  public void valueChanged(ListSelectionEvent e)
  {
    int selected = list.getSelectedIndex();
    if (selected != -1)
    {
      Song song = (Song) listModel.get(selected);
      lblBg.setIcon(song.getBackground());
      lblBn.setIcon(song.getBanner());
      lblCd.setIcon(song.getCdTitle());
    }
    else
    {
      lblBg.setIcon(null);
      lblBn.setIcon(null);
      lblCd.setIcon(null);
    }
  }

}
