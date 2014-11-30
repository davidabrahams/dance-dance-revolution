package gamepanels;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Collections;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFileChooser;
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
public class SongLibraryPanel extends JPanel implements ActionListener, ListSelectionListener
{

  /** The list model. */
  private DefaultListModel listModel;

  /** The frame. */
  private MainFrame frame;

  /** The btn new song. */
  private JButton btnNewSong;

  /** The btn delete song. */
  private JButton btnDeleteSong;

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

  /** The btn import song library. */
  private JButton btnImportSongLibrary;

  /** The btn export song library. */
  private JButton btnExportSongLibrary;

  /** The fc. */
  private JFileChooser fc;

  /**
   * Create the panel.
   * 
   * @param frame the frame
   * @param fc the filechooser
   */
  public SongLibraryPanel(MainFrame frame, JFileChooser fc)
  {
    this.frame = frame;
    this.fc = fc;

    setBackground(Color.DARK_GRAY);
    setLayout(new BorderLayout(0, 0));

    JPanel libraryPanel = new JPanel();
    libraryPanel.setBackground(Color.DARK_GRAY);
    add(libraryPanel, BorderLayout.EAST);
    GridBagLayout gbl_libraryPanel = new GridBagLayout();
    gbl_libraryPanel.rowWeights = new double[] {0.0, 1.0, 0.0};
    libraryPanel.setLayout(gbl_libraryPanel);

    JLabel lblSongLibrary = new JLabel("Song Library");
    lblSongLibrary.setForeground(Color.WHITE);
    GridBagConstraints gbc_lblSongLibrary = new GridBagConstraints();
    gbc_lblSongLibrary.gridwidth = 2;
    gbc_lblSongLibrary.gridx = 0;
    gbc_lblSongLibrary.gridy = 0;
    libraryPanel.add(lblSongLibrary, gbc_lblSongLibrary);

    listModel = new DefaultListModel();

    list = new JList(listModel);
    list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
    list.addListSelectionListener(this);
    list.setBackground(Color.GRAY);
    list.setForeground(Color.WHITE);
    GridBagConstraints gbc_list = new GridBagConstraints();
    gbc_list.gridwidth = 2;
    gbc_list.fill = GridBagConstraints.BOTH;
    gbc_list.gridx = 0;
    gbc_list.gridy = 1;
    libraryPanel.add(list, gbc_list);

    btnNewSong = new JButton("New Song");
    btnNewSong.addActionListener(this);
    GridBagConstraints gbc_btnNewSong = new GridBagConstraints();
    gbc_btnNewSong.gridx = 0;
    gbc_btnNewSong.gridy = 2;
    libraryPanel.add(btnNewSong, gbc_btnNewSong);

    btnDeleteSong = new JButton("Delete Song");
    btnDeleteSong.addActionListener(this);
    GridBagConstraints gbc_btnDeleteSong = new GridBagConstraints();
    gbc_btnDeleteSong.gridx = 1;
    gbc_btnDeleteSong.gridy = 2;
    libraryPanel.add(btnDeleteSong, gbc_btnDeleteSong);

    bottomPanel = new JPanel();
    bottomPanel.setBackground(Color.GRAY);
    add(bottomPanel, BorderLayout.SOUTH);
    GridBagLayout gbl_bottomPanel = new GridBagLayout();
    bottomPanel.setLayout(gbl_bottomPanel);

    btnGoBack = new JButton("Go Back");
    GridBagConstraints gbc_btnGoBack = new GridBagConstraints();
    gbc_btnGoBack.fill = GridBagConstraints.BOTH;
    gbc_btnGoBack.gridx = 0;
    gbc_btnGoBack.gridy = 0;
    bottomPanel.add(btnGoBack, gbc_btnGoBack);
    btnGoBack.addActionListener(this);

    btnImportSongLibrary = new JButton("Import Song Library");
    btnImportSongLibrary.addActionListener(this);
    GridBagConstraints gbc_btnImportSongLibrary = new GridBagConstraints();
    gbc_btnImportSongLibrary.fill = GridBagConstraints.BOTH;
    gbc_btnImportSongLibrary.gridx = 1;
    gbc_btnImportSongLibrary.gridy = 0;
    bottomPanel.add(btnImportSongLibrary, gbc_btnImportSongLibrary);

    btnExportSongLibrary = new JButton("Export Song Library");
    btnExportSongLibrary.addActionListener(this);
    GridBagConstraints gbc_btnExportSongLibrary = new GridBagConstraints();
    gbc_btnExportSongLibrary.gridx = 2;
    gbc_btnExportSongLibrary.gridy = 0;
    bottomPanel.add(btnExportSongLibrary, gbc_btnExportSongLibrary);

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

  /**
   * Gets the list model.
   * 
   * @return the list model
   */
  public DefaultListModel getListModel()
  {
    return listModel;
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
    else if (e.getSource() == btnNewSong)
    {
      int simul = JOptionPane.showConfirmDialog(this, "Would you like to input all song files simultaneously?", null, JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE);
      NewSongPanelInterface inputPanel = null;
      if (simul == JOptionPane.YES_OPTION)
      {
        fc.setFileSelectionMode(JFileChooser.FILES_ONLY);
        fc.setMultiSelectionEnabled(true);
        int returnVal = fc.showOpenDialog(this);
        if (returnVal == JFileChooser.APPROVE_OPTION)
        {
          File[] chosenFiles = fc.getSelectedFiles();
          if (chosenFiles.length == 5)
            inputPanel = new SimultaneousNewSongPanel(chosenFiles);
          else
            JOptionPane.showMessageDialog(this, "You must select exactly 5 files", null, JOptionPane.ERROR_MESSAGE);
        }
      }
      else if (simul == JOptionPane.NO_OPTION)
      {
        inputPanel = new NewSongPanel(fc);
      }
      if (inputPanel != null)
      {
        int userInput = JOptionPane.showOptionDialog(this, inputPanel, "New Song", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE, null, null, null);
        if (userInput == JOptionPane.OK_OPTION)
        {
          Song newSong = null;
          try
          {
            newSong = new Song(inputPanel.getMpFile(), inputPanel.getStepFile(), inputPanel.getBackgroundImage(), inputPanel.getBannerImage(), inputPanel.getCdTitleImage());
          }
          catch (NumberFormatException e1)
          {
            JOptionPane.showMessageDialog(this, "A number format exception occured when creating the song.", null, JOptionPane.ERROR_MESSAGE);
            e1.printStackTrace();
          }
          catch (IOException e1)
          {
            JOptionPane.showMessageDialog(this, "An IO exception occured when creating the song.", null, JOptionPane.ERROR_MESSAGE);
            e1.printStackTrace();
          }
          catch (IllegalArgumentException e1)
          {
            JOptionPane.showMessageDialog(this, "An illegal argument exception occured when creating the song.\nIt is possible either one of the files was left blank or its extension was incorrect", null, JOptionPane.ERROR_MESSAGE);
            e1.printStackTrace();
          }
          catch (NullPointerException e1)
          {
            JOptionPane.showMessageDialog(this, "A null point exception occured when creating the song.\nIt is possible either one of the files was left blank or duplicated.", null, JOptionPane.ERROR_MESSAGE);
            e1.printStackTrace();
          }
          if (newSong != null)
          {
            listModel.addElement(newSong);
            newSong.printData();
          }
        }
      }
    }
    else if (e.getSource() == btnDeleteSong)
    {
      int selected = list.getSelectedIndex();
      if (selected != -1)
      {
        int confirm = JOptionPane.showConfirmDialog(this, "Are you sure would want to delete the selected song?", null, JOptionPane.YES_NO_OPTION);
        if (confirm == JOptionPane.YES_OPTION)
          listModel.remove(selected);
      }
    }
    else if (e.getSource() == btnExportSongLibrary)
    {
      fc.setFileSelectionMode(JFileChooser.FILES_ONLY);
      ArrayList<Song> songs = (ArrayList<Song>) Collections.list(listModel.elements());
      if (fc.showSaveDialog(this) == JFileChooser.APPROVE_OPTION)
      {
        File fileToSave = fc.getSelectedFile();
        ObjectOutputStream save = null;
        try
        {
          FileOutputStream saveFile = new FileOutputStream(new File(fileToSave.getAbsolutePath()));
          save = new ObjectOutputStream(new BufferedOutputStream(saveFile));
          save.writeObject(songs);
        }
        catch (FileNotFoundException e1)
        {
          JOptionPane.showMessageDialog(this, "The file could not be found.", null, JOptionPane.ERROR_MESSAGE);
          e1.printStackTrace();
        }
        catch (IOException e1)
        {
          JOptionPane.showMessageDialog(this, "An IO exception occured.", null, JOptionPane.ERROR_MESSAGE);
          e1.printStackTrace();
        }
        finally
        {
          try
          {
            if (save != null)
              save.close();
          }
          catch (IOException e1)
          {
            JOptionPane.showMessageDialog(this, "An IO exception occured.", null, JOptionPane.ERROR_MESSAGE);
            e1.printStackTrace();
          }
        }
      }
    }
    else if (e.getSource() == btnImportSongLibrary)
    {
      fc.setAcceptAllFileFilterUsed(true);
      if (fc.showOpenDialog(this) == JFileChooser.APPROVE_OPTION)
      {
        File fileToOpen = fc.getSelectedFile();
        ObjectInputStream read = null;
        ArrayList<Song> imported = null;
        try
        {
          FileInputStream input = new FileInputStream(fileToOpen.getAbsolutePath());
          read = new ObjectInputStream(new BufferedInputStream(input));
          imported = (ArrayList<Song>) read.readObject();
        }
        catch (FileNotFoundException e1)
        {
          JOptionPane.showMessageDialog(this, "The file could not be found.", null, JOptionPane.ERROR_MESSAGE);
          e1.printStackTrace();
        }
        catch (IOException e1)
        {
          JOptionPane.showMessageDialog(this, "An IO exception occured.", null, JOptionPane.ERROR_MESSAGE);
          e1.printStackTrace();
        }
        catch (ClassNotFoundException e1)
        {
          JOptionPane.showMessageDialog(this, "A class not found exception occured.", null, JOptionPane.ERROR_MESSAGE);
          e1.printStackTrace();
        }
        finally
        {
          if (read != null)
            try
            {
              read.close();
            }
            catch (IOException e1)
            {
              JOptionPane.showMessageDialog(this, "An IO exception occured.", null, JOptionPane.ERROR_MESSAGE);
              e1.printStackTrace();
            }
        }
        if (imported != null)
        {
          listModel.clear();
          for (Song song : imported)
            listModel.addElement(song);
        }
      }
    }
  }

  /*
   * (non-Javadoc)
   * 
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
