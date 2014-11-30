package gamepanels;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * The Class NewSongPanel.
 */
public class NewSongPanel extends JPanel implements NewSongPanelInterface, ActionListener
{

  /** The button select mp file. */
  private JButton btnSelectMpFile;

  /** The button select step file. */
  private JButton btnSelectStepFile;

  /** The button select background image. */
  private JButton btnSelectBackgroundImage;

  /** The button select banner image. */
  private JButton btnSelectBannerImage;

  /** The button select cd title. */
  private JButton btnSelectCdTitle;

  /** The label mp file. */
  private JLabel lblMpFile;

  /** The label step file. */
  private JLabel lblStepFile;

  /** The label background image. */
  private JLabel lblBackgroundImage;

  /** The label banner image. */
  private JLabel lblBannerImage;

  /** The label cd title image. */
  private JLabel lblCdTitleImage;

  /** The file chooser. */
  private JFileChooser fc;

  /**
   * Create the panel.
   * 
   * @param fc the file chooser
   */
  public NewSongPanel(JFileChooser fc)
  {
    GridBagLayout gridBagLayout = new GridBagLayout();
    gridBagLayout.columnWeights = new double[] {0.0, 1.0};
    setLayout(gridBagLayout);

    this.fc = fc;

    btnSelectMpFile = new JButton("Select MP3 File");
    btnSelectMpFile.addActionListener(this);
    GridBagConstraints gbc_btnSelectMpFile = new GridBagConstraints();
    gbc_btnSelectMpFile.fill = GridBagConstraints.BOTH;
    gbc_btnSelectMpFile.gridx = 0;
    gbc_btnSelectMpFile.gridy = 0;
    add(btnSelectMpFile, gbc_btnSelectMpFile);

    lblMpFile = new JLabel();
    GridBagConstraints gbc_lblMpFile = new GridBagConstraints();
    gbc_lblMpFile.fill = GridBagConstraints.BOTH;
    gbc_lblMpFile.gridx = 1;
    gbc_lblMpFile.gridy = 0;
    gbc_lblMpFile.ipadx = 500;
    add(lblMpFile, gbc_lblMpFile);

    btnSelectStepFile = new JButton("Select Step File");
    btnSelectStepFile.addActionListener(this);
    GridBagConstraints gbc_btnSelectStepFile = new GridBagConstraints();
    gbc_btnSelectStepFile.fill = GridBagConstraints.BOTH;
    gbc_btnSelectStepFile.gridx = 0;
    gbc_btnSelectStepFile.gridy = 1;
    add(btnSelectStepFile, gbc_btnSelectStepFile);

    lblStepFile = new JLabel();
    GridBagConstraints gbc_lblStepFile = new GridBagConstraints();
    gbc_lblStepFile.fill = GridBagConstraints.BOTH;
    gbc_lblStepFile.gridx = 1;
    gbc_lblStepFile.gridy = 1;
    add(lblStepFile, gbc_lblStepFile);

    btnSelectBackgroundImage = new JButton("Select Background Image");
    btnSelectBackgroundImage.addActionListener(this);
    GridBagConstraints gbc_btnSelectBackgroundImage = new GridBagConstraints();
    gbc_btnSelectBackgroundImage.fill = GridBagConstraints.BOTH;
    gbc_btnSelectBackgroundImage.gridx = 0;
    gbc_btnSelectBackgroundImage.gridy = 2;
    add(btnSelectBackgroundImage, gbc_btnSelectBackgroundImage);

    lblBackgroundImage = new JLabel();
    GridBagConstraints gbc_lblBackgroundImage = new GridBagConstraints();
    gbc_lblBackgroundImage.fill = GridBagConstraints.BOTH;
    gbc_lblBackgroundImage.gridx = 1;
    gbc_lblBackgroundImage.gridy = 2;
    add(lblBackgroundImage, gbc_lblBackgroundImage);

    btnSelectBannerImage = new JButton("Select Banner Image");
    btnSelectBannerImage.addActionListener(this);
    GridBagConstraints gbc_btnSelectBannerImage = new GridBagConstraints();
    gbc_btnSelectBannerImage.fill = GridBagConstraints.BOTH;
    gbc_btnSelectBannerImage.gridx = 0;
    gbc_btnSelectBannerImage.gridy = 3;
    add(btnSelectBannerImage, gbc_btnSelectBannerImage);

    lblBannerImage = new JLabel();
    GridBagConstraints gbc_lblBannerImage = new GridBagConstraints();
    gbc_lblBannerImage.fill = GridBagConstraints.BOTH;
    gbc_lblBannerImage.gridx = 1;
    gbc_lblBannerImage.gridy = 3;
    add(lblBannerImage, gbc_lblBannerImage);

    btnSelectCdTitle = new JButton("Select CD Title Image");
    btnSelectCdTitle.addActionListener(this);
    GridBagConstraints gbc_btnSelectCdTitle = new GridBagConstraints();
    gbc_btnSelectCdTitle.fill = GridBagConstraints.BOTH;
    gbc_btnSelectCdTitle.gridx = 0;
    gbc_btnSelectCdTitle.gridy = 4;
    add(btnSelectCdTitle, gbc_btnSelectCdTitle);

    lblCdTitleImage = new JLabel();
    GridBagConstraints gbc_lblCdTitleImage = new GridBagConstraints();
    gbc_lblCdTitleImage.fill = GridBagConstraints.BOTH;
    gbc_lblCdTitleImage.gridx = 1;
    gbc_lblCdTitleImage.gridy = 4;
    add(lblCdTitleImage, gbc_lblCdTitleImage);

  }

  /*
   * (non-Javadoc)
   * 
   * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
   */
  public void actionPerformed(ActionEvent e)
  {
    Object source = e.getSource();
    fc.setFileSelectionMode(JFileChooser.FILES_ONLY);
    fc.setAcceptAllFileFilterUsed(false);
    if (source == btnSelectMpFile)
    {
      int returnVal = fc.showOpenDialog(this);
      if (returnVal == JFileChooser.APPROVE_OPTION)
        lblMpFile.setText(fc.getSelectedFile().getPath());
    }
    else if (source == btnSelectStepFile)
    {
      int returnVal = fc.showOpenDialog(this);
      if (returnVal == JFileChooser.APPROVE_OPTION)
        lblStepFile.setText(fc.getSelectedFile().getPath());
    }
    else if (source == btnSelectBackgroundImage)
    {
      int returnVal = fc.showOpenDialog(this);
      if (returnVal == JFileChooser.APPROVE_OPTION)
        lblBackgroundImage.setText(fc.getSelectedFile().getPath());
    }
    else if (source == btnSelectBannerImage)
    {
      int returnVal = fc.showOpenDialog(this);
      if (returnVal == JFileChooser.APPROVE_OPTION)
        lblBannerImage.setText(fc.getSelectedFile().getPath());
    }
    else if (source == btnSelectCdTitle)
    {
      int returnVal = fc.showOpenDialog(this);
      if (returnVal == JFileChooser.APPROVE_OPTION)
        lblCdTitleImage.setText(fc.getSelectedFile().getPath());
    }
  }

  /**
   * Gets the mp3 file.
   * 
   * @return the mp file
   */
  public String getMpFile()
  {
    return lblMpFile.getText();
  }

  /*
   * (non-Javadoc)
   * 
   * @see NewSongPanelInterface#getStepFile()
   */
  public String getStepFile()
  {
    return lblStepFile.getText();
  }

  /*
   * (non-Javadoc)
   * 
   * @see NewSongPanelInterface#getBackgroundImage()
   */
  public String getBackgroundImage()
  {
    return lblBackgroundImage.getText();
  }

  /*
   * (non-Javadoc)
   * 
   * @see NewSongPanelInterface#getBannerImage()
   */
  public String getBannerImage()
  {
    return lblBannerImage.getText();
  }

  /*
   * (non-Javadoc)
   * 
   * @see NewSongPanelInterface#getCdTitleImage()
   */
  public String getCdTitleImage()
  {
    return lblCdTitleImage.getText();
  }

}
