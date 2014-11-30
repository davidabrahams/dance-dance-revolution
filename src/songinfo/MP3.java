package songinfo;
import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.Serializable;

import javazoom.jl.player.Player;

/**
 * The Class MP3.
 */
public class MP3 implements Serializable
{
  
  /** The is playing. */
  private boolean isPlaying;
  /** The filename. */
  private String filename;

  /** The player. */
  transient private Player player;

  // constructor that takes the name of an MP3 file
  /**
   * Instantiates a new mp3.
   * 
   * @param filename the filename
   */
  public MP3(String filename)
  {
    this.filename = filename;
    isPlaying = false;
  }

  /**
   * Gets the file name.
   *
   * @return the file name
   */
  public String getFileName()
  {
    return filename;
  }
  
  /**
   * Checks if is playing.
   *
   * @return true, if is playing
   */
  public boolean isPlaying()
  {
    return isPlaying;
  }

  /**
   * Close.
   */
  public void close()
  {
    if (player != null)
      player.close();
    isPlaying = false;
  }

  // play the MP3 file to the sound card
  /**
   * Play.
   */
  public void play()
  {
    isPlaying = true;
    try
    {
      FileInputStream fis = new FileInputStream(filename);
      BufferedInputStream bis = new BufferedInputStream(fis);
      player = new Player(bis);
    }
    catch (Exception e)
    {
      System.out.println("Problem playing file " + filename);
      System.out.println(e);
    }

    // run in new thread to play in background
    new Thread()
    {
      public void run()
      {
        try
        {
          player.play();
        }
        catch (Exception e)
        {
          System.out.println(e);
        }
      }
    }.start();

  }
}