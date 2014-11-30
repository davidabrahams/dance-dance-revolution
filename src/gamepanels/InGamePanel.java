package gamepanels;

import feedback.Boo;
import feedback.FeedBack;
import feedback.Great;
import feedback.Marvellous;
import feedback.Miss;
import feedback.Perfect;
import graphics.receptors.DownReceptor;
import graphics.receptors.LeftReceptor;
import graphics.receptors.Receptor;
import graphics.receptors.RightReceptor;
import graphics.receptors.UpReceptor;
import graphics.steps.DownStep;
import graphics.steps.LeftStep;
import graphics.steps.RightStep;
import graphics.steps.Step;
import graphics.steps.UpStep;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;

import songinfo.Note;
import songinfo.Song;
import songinfo.StepFile;

/**
 * The Class InGamePanel.
 */
public class InGamePanel extends ResizingPanel implements KeyListener
{

  /** The receptors. */
  private Receptor[] receptors;

  /** The Constant RECEPTOR_START_X. */
  private static final int RECEPTOR_START_X = 10;

  /** The Constant RECEPTOR_SEPARATOR. */
  private static final int RECEPTOR_SEPARATOR = 10;

  /** The Constant PIXELS_PER_BEAT. */
  private static final int PIXELS_PER_BEAT = 120;

  /** The Constant Key Numbers */
  public static final int UP1 = 0, LEFT1 = 1, DOWN1 = 2, RIGHT1 = 3, UP2 = 4, LEFT2 = 5, DOWN2 = 6, RIGHT2 = 7;

  /** The Constant perfectScore. */
  private static final int booScore = -10, missScore = -3, greatScore = 3, marvellousScore = 7, perfectScore = 10;

  /** The Constant SLEEP_TIME. */
  private static final int SLEEP_TIME = 20;

  /** The number players. */
  private int numPlayers;

  /** If the game is being played. */
  private boolean gameBeingPlayed;

  /** The time elapsed. */
  private int timeElapsed;

  /** The current bpm. */
  private double currBPM;

  /** The current beat. */
  private double currBeat;

  /** The bpm index. */
  private int bpmIndex;

  /** The stop index. */
  private int stopIndex;

  /** The vert shift. */
  private double vertShift;

  /** The song. */
  private Song song;

  /** The steps. */
  private ArrayList<Step> steps;

  /** The feed backs. */
  private FeedBack[] feedBacks;

  /** The feed back index. */
  private int feedBackIndex;

  /** If this panel has been initialized */
  private boolean initialized;

  /** Player 1's score */
  private int score1;

  /** Player 2's score */
  private int score2;

  /**
   * Instantiates a new in game panel.
   */
  public InGamePanel()
  {
    super(800, 549);
    initialized = false;
    addKeyListener(this);
  }

  /**
   * Sets the song.
   * 
   * @param song the new song
   */
  public void setSong(Song song)
  {
    this.song = song;
  }

  /**
   * Starts the game.
   * 
   * @param numPlayers the num players
   * @param difficultyLevel the difficulty level
   */
  public void startGame(int numPlayers, int difficultyLevel)
  {
    this.numPlayers = numPlayers;
    receptors = new Receptor[8];
    vertShift = 1500;
    score1 = 0;
    score2 = 0;
    for (int i = 0; i < 8; i++)
    {
      int modded = i % 4;
      int difference = 0;
      if (i >= 4)
        difference = 70;
      if (modded == 0)
        receptors[i] = new LeftReceptor(RECEPTOR_START_X + i * (RECEPTOR_SEPARATOR + Receptor.W) + difference, 10);
      else if (modded == 1)
        receptors[i] = new DownReceptor(RECEPTOR_START_X + i * (RECEPTOR_SEPARATOR + Receptor.W) + difference, 10);
      else if (modded == 2)
        receptors[i] = new UpReceptor(RECEPTOR_START_X + i * (RECEPTOR_SEPARATOR + Receptor.W) + difference, 10);
      else if (modded == 3)
        receptors[i] = new RightReceptor(RECEPTOR_START_X + i * (RECEPTOR_SEPARATOR + Receptor.W) + difference, 10);
    }
    gameBeingPlayed = true;
    feedBacks = new FeedBack[3 * numPlayers];
    feedBackIndex = 0;
    initializeSteps(numPlayers, difficultyLevel);
    timeElapsed = (int) ((-vertShift / PIXELS_PER_BEAT) / currBPM * 60000.0);
    initialized = true;
    requestFocus();
    run();
  }

  /**
   * If this panel is initialized.
   * 
   * @return true, if initialized
   */
  public boolean initialized()
  {
    return initialized;
  }

  /**
   * Initialize steps.
   * 
   * @param numPlayers the number players
   * @param difficultyLevel the difficulty level
   */
  private void initializeSteps(int numPlayers, int difficultyLevel)
  {
    steps = new ArrayList<Step>();
    StepFile file = song.getStepFiles().get(0);
    currBPM = song.getBPM()[0].getBPM();
    currBeat = 0;
    stopIndex = 0;
    bpmIndex = 1;
    ArrayList<Note> notes = file.getNoteData();
    int filter = -1;
    int oldFilter = -1;
    for (Note note : notes)
    {
      char[] characters = note.getNotes();
      for (int j = 0; j < numPlayers; j++)
      {
        filter = oldFilter;
        for (int i = j * 4; i < 4 + j * 4; i++)
        {
          int modded = i % 4;
          if (characters[modded] == '1' && (filter = filter + 1) % (11 - difficultyLevel) == 0)
          {
            int difference = 0;
            if (i >= 4)
              difference = 70;
            if (modded == 0)
              steps.add(new LeftStep(RECEPTOR_START_X + i * (RECEPTOR_SEPARATOR + Receptor.W) + difference, (int) (10 + note.getBeat() * PIXELS_PER_BEAT)));
            else if (modded == 1)
              steps.add(new DownStep(RECEPTOR_START_X + i * (RECEPTOR_SEPARATOR + Receptor.W) + difference, (int) (10 + note.getBeat() * PIXELS_PER_BEAT)));
            else if (modded == 2)
              steps.add(new UpStep(RECEPTOR_START_X + i * (RECEPTOR_SEPARATOR + Receptor.W) + difference, (int) (10 + note.getBeat() * PIXELS_PER_BEAT)));
            else if (modded == 3)
              steps.add(new RightStep(RECEPTOR_START_X + i * (RECEPTOR_SEPARATOR + Receptor.W) + difference, (int) (10 + note.getBeat() * PIXELS_PER_BEAT)));
          }
        }
      }
      oldFilter = filter;
    }
  }

  /**
   * Close the song.
   */
  public void closeSong()
  {
    gameBeingPlayed = false;
    song.getMusic().close();
  }

  /*
   * (non-Javadoc)
   * 
   * @see javax.swing.JComponent#paintComponent(java.awt.Graphics)
   */
  protected void paintComponent(Graphics g)
  {
    super.paintComponent(g);
    Graphics2D g2D = getScaledGraphics2D(g);
    g2D.drawImage(song.getBackground().getImage(), 0, 0, 800, 549, this);
    for (Receptor r : receptors)
      r.draw(g2D, this);
    for (Step s : steps)
      if (s != null)
        s.draw(g2D, this, vertShift);
    for (FeedBack f : feedBacks)
      if (f != null && f.isAlive())
        f.draw(g2D, this);
    g2D.setColor(Color.RED);
    g2D.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 50));
    if (numPlayers == 1)
      g2D.drawString("Score : " + score1, 450, 540);
    else if (numPlayers == 2)
    {
      g2D.drawString("Score 1: " + score1, 420, 480);
      g2D.drawString("Score 2: " + score2, 420, 540);
    }

  }

  /**
   * Runs the actual game.
   */
  private void run()
  {
    new Thread()
    {
      public void run()
      {
        while (gameBeingPlayed)
        {
          long start = System.currentTimeMillis();
          if ((timeElapsed / 1000.0) > -song.getOffset() && gameBeingPlayed && !song.getMusic().isPlaying())
          {
            new Thread()
            {
              public void run()
              {
                song.getMusic().play();
              }
            }.start();
          }
          vertShift -= PIXELS_PER_BEAT * currBPM * (SLEEP_TIME / 60000.0);
          timeElapsed += SLEEP_TIME;
          if (timeElapsed >= 0)
            currBeat += (SLEEP_TIME / 60000.0) * currBPM;
          boolean indexLessThan = true;
          while (indexLessThan)
          {

            if (song.getStops() != null && stopIndex < song.getStops().length && currBeat >= song.getStops()[stopIndex].getBeatNumber())
            {
              try
              {
                Thread.sleep((int) (1000 * song.getStops()[stopIndex].getStopLength()));
                start += (int) (1000 * song.getStops()[stopIndex].getStopLength());
              }
              catch (InterruptedException e)
              {
                e.printStackTrace();
              }
              stopIndex++;

            }
            else
              indexLessThan = false;
          }
          indexLessThan = true;
          while (indexLessThan)
          {
            if (song.getBPM() != null && bpmIndex < song.getBPM().length && currBeat >= song.getBPM()[bpmIndex].getBeatNumber())
            {
              currBPM = song.getBPM()[bpmIndex].getBPM();
              bpmIndex++;
            }
            else
              indexLessThan = false;
          }
          for (int i = 0; i < steps.size(); i++)
          {
            if (steps.get(i) != null && steps.get(i).getBottom() + vertShift < -300)
            {
              if (!steps.get(i).getPressed())
              {
                if (steps.get(i).getCenter().x < 400)
                  score1 += booScore;
                else
                  score2 += booScore;
                steps.set(i, null);
                // feedBackIndex++;
              }
              else
                steps.set(i, null);
            }
          }
          for (int i = 0; i < steps.size(); i++)
          {
            if (steps.get(i) != null && steps.get(i).getPressed())
              steps.set(i, null);
          }
          for (FeedBack f : feedBacks)
          {
            if (f != null)
              f.increment(SLEEP_TIME);
          }
          repaint();
          if (System.currentTimeMillis() - start < SLEEP_TIME)
          {
            try
            {
              sleep(SLEEP_TIME - System.currentTimeMillis() + start);
            }
            catch (InterruptedException e)
            {
              e.printStackTrace();
            }
          }
          else
          {
            System.out.println("game too slow");
          }
        }
      }
    }.start();
  }

  /**
   * Gets the closest step.
   * 
   * @param i the i
   * @return the closest step
   */
  public Step getClosestStep(int i)
  {
    int x = (int) receptors[i].getCenter().x;
    Step closest = null;
    for (Step s : steps)
      if (s != null && s.getCenter().x == x)
        if (closest == null || Math.abs((closest.getCenter().y + vertShift) - receptors[i].getCenter().y) > Math.abs((s.getCenter().y + vertShift) - receptors[i].getCenter().y))
          closest = s;
    return closest;
  }

  /**
   * Presses a key.
   * 
   * @param key the key
   */
  public void pressKey(int key)
  {
    Step closest = null;
    if (key == LEFT1 && !receptors[0].isPressed())
    {
      receptors[0].setPressed(true);
      closest = getClosestStep(0);
    }
    else if (key == DOWN1 && !receptors[1].isPressed())
    {
      receptors[1].setPressed(true);
      closest = getClosestStep(1);
    }
    else if (key == UP1 && !receptors[2].isPressed())
    {
      receptors[2].setPressed(true);
      closest = getClosestStep(2);
    }
    else if (key == RIGHT1 && !receptors[3].isPressed())
    {
      receptors[3].setPressed(true);
      closest = getClosestStep(3);
    }
    else if (key == LEFT2 && !receptors[4].isPressed())
    {
      receptors[4].setPressed(true);
      closest = getClosestStep(4);
    }
    else if (key == DOWN2 && !receptors[5].isPressed())
    {
      receptors[5].setPressed(true);
      closest = getClosestStep(5);
    }
    else if (key == UP2 && !receptors[6].isPressed())
    {
      receptors[6].setPressed(true);
      closest = getClosestStep(6);
    }
    else if (key == RIGHT2 && !receptors[7].isPressed())
    {
      receptors[7].setPressed(true);
      closest = getClosestStep(7);
    }

    if (closest != null)
    {
      if (feedBackIndex >= feedBacks.length)
        feedBackIndex = 0;
      int difference = (int) Math.abs(receptors[0].getCenter().y - (closest.getCenter().y + vertShift));
      if (difference < 300)
      {
        if (difference > 200)
        {
          feedBacks[feedBackIndex] = new Boo(closest, (int) vertShift);
          closest.setPressed(true);
          if (closest.getCenter().x < 400)
            score1 += booScore;
          else
            score2 += booScore;
        }
        else if (difference > 120)
        {
          feedBacks[feedBackIndex] = new Miss(closest, (int) vertShift);
          closest.setPressed(true);
          if (closest.getCenter().x < 400)
            score1 += missScore;
          else
            score2 += missScore;
        }
        else if (difference > 60)
        {
          feedBacks[feedBackIndex] = new Great(closest, (int) vertShift);
          closest.setPressed(true);
          if (closest.getCenter().x < 400)
            score1 += greatScore;
          else
            score2 += greatScore;
        }
        else if (difference > 30)
        {
          feedBacks[feedBackIndex] = new Marvellous(closest, (int) vertShift);
          closest.setPressed(true);
          if (closest.getCenter().x < 400)
            score1 += marvellousScore;
          else
            score2 += marvellousScore;
        }
        else
        {
          feedBacks[feedBackIndex] = new Perfect(closest, (int) vertShift);
          closest.setPressed(true);
          if (closest.getCenter().x < 400)
            score1 += perfectScore;
          else
            score2 += perfectScore;
        }
        feedBackIndex++;
      }
    }
  }

  /**
   * Releases a key.
   * 
   * @param key the key
   */
  public void release(int key)
  {
    if (key == LEFT1)
      receptors[0].setPressed(false);
    else if (key == DOWN1)
      receptors[1].setPressed(false);
    else if (key == UP1)
      receptors[2].setPressed(false);
    else if (key == RIGHT1)
      receptors[3].setPressed(false);
    else if (key == LEFT2)
      receptors[4].setPressed(false);
    else if (key == DOWN2)
      receptors[5].setPressed(false);
    else if (key == UP2)
      receptors[6].setPressed(false);
    else if (key == RIGHT2)
      receptors[7].setPressed(false);

  }

  /*
   * (non-Javadoc)
   * 
   * @see java.awt.event.KeyListener#keyPressed(java.awt.event.KeyEvent)
   */
  public void keyPressed(KeyEvent arg0)
  {

    int keyCode = arg0.getKeyCode();
    if (keyCode == KeyEvent.VK_W)
      this.pressKey(InGamePanel.UP1);
    else if (keyCode == KeyEvent.VK_S)
      this.pressKey(InGamePanel.DOWN1);
    else if (keyCode == KeyEvent.VK_A)
      this.pressKey(InGamePanel.LEFT1);
    else if (keyCode == KeyEvent.VK_D)
      this.pressKey(InGamePanel.RIGHT1);
    if (numPlayers > 1)
    {
      if (keyCode == KeyEvent.VK_UP)
        this.pressKey(InGamePanel.UP2);
      else if (keyCode == KeyEvent.VK_DOWN)
        this.pressKey(InGamePanel.DOWN2);
      else if (keyCode == KeyEvent.VK_LEFT)
        this.pressKey(InGamePanel.LEFT2);
      else if (keyCode == KeyEvent.VK_RIGHT)
        this.pressKey(InGamePanel.RIGHT2);
    }
  }

  /*
   * (non-Javadoc)
   * 
   * @see java.awt.event.KeyListener#keyReleased(java.awt.event.KeyEvent)
   */
  public void keyReleased(KeyEvent arg0)
  {
    int keyCode = arg0.getKeyCode();
    if (keyCode == KeyEvent.VK_W)
      this.release(InGamePanel.UP1);
    else if (keyCode == KeyEvent.VK_S)
      this.release(InGamePanel.DOWN1);
    else if (keyCode == KeyEvent.VK_A)
      this.release(InGamePanel.LEFT1);
    else if (keyCode == KeyEvent.VK_D)
      this.release(InGamePanel.RIGHT1);
    if (numPlayers > 1)
    {
      if (keyCode == KeyEvent.VK_UP)
        this.release(InGamePanel.UP2);
      else if (keyCode == KeyEvent.VK_DOWN)
        this.release(InGamePanel.DOWN2);
      else if (keyCode == KeyEvent.VK_LEFT)
        this.release(InGamePanel.LEFT2);
      else if (keyCode == KeyEvent.VK_RIGHT)
        this.release(InGamePanel.RIGHT2);
    }
  }

  /*
   * (non-Javadoc)
   * 
   * @see java.awt.event.KeyListener#keyTyped(java.awt.event.KeyEvent)
   */
  public void keyTyped(KeyEvent arg0)
  {
  }
}