package gamepanels;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.io.File;

import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * The Class SimultaneousNewSongPanel.
 */
public class SimultaneousNewSongPanel extends JPanel implements NewSongPanelInterface
{

  /** The Constant OPTIONS. */
  private static final String[] OPTIONS = new String[] {"MP3 File", "Step File", "Background Image", "Banner Image", "CD Title Image"};
  
  /** The labels. */
  private JLabel[] labels;
  
  /** The boxes. */
  private JComboBox[] boxes;

  /**
   * Create the panel.
   *
   * @param files the files
   */
  public SimultaneousNewSongPanel(File[] files)
  {
    GridBagLayout gridBagLayout = new GridBagLayout();
    gridBagLayout.columnWeights = new double[] {0.0, 1.0};
    setLayout(gridBagLayout);

    labels = new JLabel[5];
    boxes = new JComboBox[5];

    labels[0] = new JLabel("MP3 File");
    GridBagConstraints gbc_lblMpFile = new GridBagConstraints();
    gbc_lblMpFile.gridx = 0;
    gbc_lblMpFile.gridy = 0;
    add(labels[0], gbc_lblMpFile);

    boxes[0] = new JComboBox();
    GridBagConstraints gbc_comboBox = new GridBagConstraints();
    gbc_comboBox.fill = GridBagConstraints.HORIZONTAL;
    gbc_comboBox.gridx = 1;
    gbc_comboBox.gridy = 0;
    add(boxes[0], gbc_comboBox);

    labels[1] = new JLabel("Step File");
    GridBagConstraints gbc_lblStepFile = new GridBagConstraints();
    gbc_lblStepFile.gridx = 0;
    gbc_lblStepFile.gridy = 1;
    add(labels[1], gbc_lblStepFile);

    boxes[1] = new JComboBox();
    GridBagConstraints gbc_comboBox_1 = new GridBagConstraints();
    gbc_comboBox_1.gridx = 1;
    gbc_comboBox_1.gridy = 1;
    add(boxes[1], gbc_comboBox_1);

    labels[2] = new JLabel("Background Image");
    GridBagConstraints gbc_lblBackgroundImage = new GridBagConstraints();
    gbc_lblBackgroundImage.gridx = 0;
    gbc_lblBackgroundImage.gridy = 2;
    add(labels[2], gbc_lblBackgroundImage);

    boxes[2] = new JComboBox();
    GridBagConstraints gbc_comboBox_2 = new GridBagConstraints();
    gbc_comboBox_2.gridx = 1;
    gbc_comboBox_2.gridy = 2;
    add(boxes[2], gbc_comboBox_2);

    labels[3] = new JLabel("Banner Image");
    GridBagConstraints gbc_lblBannerImage = new GridBagConstraints();
    gbc_lblBannerImage.gridx = 0;
    gbc_lblBannerImage.gridy = 3;
    add(labels[3], gbc_lblBannerImage);

    boxes[3] = new JComboBox();
    GridBagConstraints gbc_comboBox_3 = new GridBagConstraints();
    gbc_comboBox_3.gridx = 1;
    gbc_comboBox_3.gridy = 3;
    add(boxes[3], gbc_comboBox_3);

    labels[4] = new JLabel("CD Title Image");
    GridBagConstraints gbc_lblCdTitleImage = new GridBagConstraints();
    gbc_lblCdTitleImage.gridx = 0;
    gbc_lblCdTitleImage.gridy = 4;
    add(labels[4], gbc_lblCdTitleImage);

    boxes[4] = new JComboBox();
    GridBagConstraints gbc_comboBox_4 = new GridBagConstraints();
    gbc_comboBox_4.gridx = 1;
    gbc_comboBox_4.gridy = 4;
    add(boxes[4], gbc_comboBox_4);

    for (JComboBox box : boxes)
      for (String option : OPTIONS)
        box.addItem(option);

    for (int i = 0; i < files.length; i++)
      labels[i].setText(files[i].getPath());

    boxes[0].setSelectedIndex(1);
    boxes[1].setSelectedIndex(0);
    boxes[2].setSelectedIndex(3);
    boxes[3].setSelectedIndex(2);
    boxes[4].setSelectedIndex(4);

  }

  /* (non-Javadoc)
   * @see gamepanels.NewSongPanelInterface#getMpFile()
   */
  public String getMpFile()
  {
    return getLabelFromKey("MP3 File");
  }

  /* (non-Javadoc)
   * @see gamepanels.NewSongPanelInterface#getStepFile()
   */
  public String getStepFile()
  {
    return getLabelFromKey("Step File");
  }

  /* (non-Javadoc)
   * @see gamepanels.NewSongPanelInterface#getBackgroundImage()
   */
  public String getBackgroundImage()
  {
    return getLabelFromKey("Background Image");
  }

  /* (non-Javadoc)
   * @see gamepanels.NewSongPanelInterface#getBannerImage()
   */
  public String getBannerImage()
  {
    return getLabelFromKey("Banner Image");
  }

  /* (non-Javadoc)
   * @see gamepanels.NewSongPanelInterface#getCdTitleImage()
   */
  public String getCdTitleImage()
  {
    return getLabelFromKey("CD Title Image");
  }

  /**
   * Gets the label from key.
   *
   * @param key the key
   * @return the label from key
   */
  private String getLabelFromKey(String key)
  {
    for (int i = 0; i < boxes.length; i++)
      if (key.equals(boxes[i].getSelectedItem()))
        return labels[i].getText();
    return null;
  }

}
