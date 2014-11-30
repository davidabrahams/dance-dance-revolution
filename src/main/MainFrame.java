package main;

import gamepanels.GamePanel;
import gamepanels.HelpPanel;
import gamepanels.InGamePanel;
import gamepanels.MainMenuPanel;
import gamepanels.SongLibraryPanel;
import gamepanels.SongSelectPanel;

import java.awt.CardLayout;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JPanel;

import songinfo.Song;

/**
 * The Class MainFrame.
 */
public class MainFrame extends JFrame
{

  /** The content pane. */
  private JPanel contentPane;
  
  /** The main menu panel. */
  private MainMenuPanel mainMenuPanel;
  
  /** The help panel. */
  private HelpPanel helpPanel;
  
  /** The song select panel. */
  private SongSelectPanel songSelectPanel;
  
  /** The game panel. */
  private GamePanel gamePanel;
  
  /** The song library panel. */
  private SongLibraryPanel songLibraryPanel;
  
  /** The ddr. */
  private DanceDanceRevolution ddr;

  /** The Constant MAIN_MENU_PANEL. */
  public final static String MAIN_MENU_PANEL = "Main Menu Panel";

  /** The Constant GAME_PANEL. */
  public final static String GAME_PANEL = "Game Panel";

  /** The Constant HELP_PANEL. */
  public final static String HELP_PANEL = "Help Panel";

  /** The Constant IMPORT_SONG_PANEL. */
  public final static String IMPORT_SONG_PANEL = "Import Song Panel";

  /** The Constant CHOSE_SONG_PANEL. */
  public final static String SONG_SELECT_PANEL = "Chose Song Panel";

  /** The fc. */
  private JFileChooser fc;

  /**
   * Create the frame.
   *
   * @param ddr the ddr
   */
  public MainFrame(final DanceDanceRevolution ddr)
  {
    this.ddr = ddr;
    addWindowListener(new WindowAdapter()
    {
      public void windowClosed(WindowEvent arg0)
      {
        ddr.close();
      }
    });
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setBounds(100, 100, 800, 600);

    fc = new JFileChooser();

    contentPane = new JPanel();
    contentPane.setLayout(new CardLayout());

    mainMenuPanel = new MainMenuPanel(this);
    helpPanel = new HelpPanel(this);
    songLibraryPanel = new SongLibraryPanel(this, fc);
    songSelectPanel = new SongSelectPanel(this, songLibraryPanel.getListModel());
    gamePanel = new GamePanel(this);

    contentPane.add(mainMenuPanel, MAIN_MENU_PANEL);
    contentPane.add(helpPanel, HELP_PANEL);
    contentPane.add(songSelectPanel, SONG_SELECT_PANEL);
    contentPane.add(gamePanel, GAME_PANEL);
    contentPane.add(songLibraryPanel, IMPORT_SONG_PANEL);

    setContentPane(contentPane);
  }

  /**
   * Switch card.
   * 
   * @param cardName the card name
   */
  public void switchCard(String cardName)
  {
    CardLayout contentPaneLayout = (CardLayout) contentPane.getLayout();
    contentPaneLayout.show(contentPane, cardName);
  }

  /**
   * Start game.
   *
   * @param song the song
   * @param numPlayers the num players
   * @param difficultyLevel the difficulty level
   */
  public void startGame(Song song, int numPlayers, int difficultyLevel)
  {
    switchCard(MainFrame.GAME_PANEL);
    gamePanel.setSong(song);
    gamePanel.startGame(numPlayers, difficultyLevel);
  }

  /**
   * Gets the in game panel.
   *
   * @return the in game panel
   */
  public InGamePanel getInGamePanel()
  {
    return this.gamePanel.getInGamePanel();
  }

  /**
   * Gets the main.
   *
   * @return the main
   */
  public DanceDanceRevolution getMain()
  {
    return ddr;
  }
  
}
