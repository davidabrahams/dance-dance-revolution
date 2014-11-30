package misc;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.image.ImageObserver;

import javax.swing.ImageIcon;

/*
 * Represents a moving, appearing/disappearing image.
 *
 * by: Shelby
 * on: 5/3/13
 */

/**
 * The Class MovingImage.
 */
public class MovingImage extends Rectangle
{

  // FIELDS
  /** The image. */
  private Image image;

  // CONSTRUCTORS
  /**
   * Instantiates a new moving image.
   *
   * @param filename the filename
   * @param x the x
   * @param y the y
   * @param w the w
   * @param h the h
   */
  public MovingImage(String filename, int x, int y, int w, int h)
  {
    this((new ImageIcon(filename)).getImage(), x, y, w, h);
  }

  /**
   * Instantiates a new moving image.
   *
   * @param img the img
   * @param x the x
   * @param y the y
   * @param w the w
   * @param h the h
   */
  public MovingImage(Image img, int x, int y, int w, int h)
  {
    super(x, y, w, h);
    image = img;
  }

  // METHODS
  /**
   * Move to location.
   *
   * @param x the x
   * @param y the y
   */
  public void moveToLocation(int x, int y)
  {
    super.x = x;
    super.y = y;
  }

  /**
   * Move by amount.
   *
   * @param x the x
   * @param y the y
   */
  public void moveByAmount(int x, int y)
  {
    super.x += x;
    super.y += y;
  }

  /**
   * Apply window limits.
   *
   * @param windowWidth the window width
   * @param windowHeight the window height
   */
  public void applyWindowLimits(int windowWidth, int windowHeight)
  {
    x = Math.min(x, windowWidth - width);
    y = Math.min(y, windowHeight - height);
    x = Math.max(0, x);
    y = Math.max(0, y);
  }

  /**
   * Draw.
   *
   * @param g the g
   * @param io the io
   */
  public void draw(Graphics g, ImageObserver io)
  {
    g.drawImage(image, x, y, width, height, io);
  }

}
