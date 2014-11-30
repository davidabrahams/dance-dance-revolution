package graphics.steps;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;
import java.awt.image.ImageObserver;

import javax.swing.ImageIcon;

/**
 * The Class Step.
 */
public abstract class Step
{

  /** The y. */
  private double x, y;

  /** The Constant H. */
  public static final int W = 80, H = 80;

  /** The image. */
  private Image image;

  /** The been pressed. */
  private boolean beenPressed;

  /**
   * Instantiates a new step.
   * 
   * @param filename the filename
   * @param x the x
   * @param y the y
   */
  public Step(String filename, double x, double y)
  {
    image = new ImageIcon(filename).getImage();
    this.x = x;
    this.y = y;
    beenPressed = false;
  }

  /**
   * Draw.
   * 
   * @param g the g
   * @param io the io
   * @param vertShift the vert shift
   */
  public void draw(Graphics g, ImageObserver io, double vertShift)
  {
    g.drawImage(image, (int) x, (int) (y + vertShift), W, H, io);
  }

  /**
   * Gets the pressed.
   * 
   * @return the pressed
   */
  public boolean getPressed()
  {
    return beenPressed;
  }

  /**
   * Sets the pressed.
   * 
   * @param b the new pressed
   */
  public void setPressed(boolean b)
  {
    beenPressed = b;
  }

  /**
   * Gets the center.
   * 
   * @return the center
   */
  public Point getCenter()
  {
    return new Point((int) (this.x + W / 2 + 0.5), (int) (this.y + H / 2 + 0.5));
  }

  /**
   * Gets the bottom.
   * 
   * @return the bottom
   */
  public double getBottom()
  {
    return this.y + H;
  }
}
