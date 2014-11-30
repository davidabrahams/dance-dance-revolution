package feedback;

import graphics.steps.Step;

import java.awt.Graphics;
import java.awt.image.ImageObserver;

import misc.MovingImage;

/**
 * The Class FeedBack.
 */
public class FeedBack extends MovingImage
{

  /** How long this feedback has been visible. */
  int beenAlive;

  /**
   * Instantiates a new feedback.
   *
   * @param filename the filename
   * @param s the Step this feedback corresponds to
   * @param vertShift the vertical shift
   */
  public FeedBack(String filename, Step s, int vertShift)
  {
    this(filename, s, 118, 60, vertShift);
  }

  /**
   * Instantiates a new feedback.
   *
   * @param filename the filename
   * @param s the Step this feedback corresponds to
   * @param w the width
   * @param h the height
   * @param vertShift the vertical shift
   */
  public FeedBack(String filename, Step s, int w, int h, int vertShift)
  {
    super(filename, s.getCenter().x - w / 2, 30, w, h);
    this.applyWindowLimits(800, 549);
    beenAlive = 0;
  }

  /**
   * Increments the time.
   *
   * @param time the time the has passed
   */
  public void increment(int time)
  {
    beenAlive += time;

  }

  /**
   * Checks if is alive.
   *
   * @return true, if is alive
   */
  public boolean isAlive()
  {
    return beenAlive < 250;
  }

  /* (non-Javadoc)
   * @see misc.MovingImage#draw(java.awt.Graphics, java.awt.image.ImageObserver)
   */
  public void draw(Graphics g, ImageObserver io)
  {
    if (isAlive())
      super.draw(g, io);
  }

}
