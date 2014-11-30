package graphics.receptors;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;
import java.awt.image.ImageObserver;

import javax.swing.ImageIcon;

/**
 * The Class Receptor.
 */
public abstract class Receptor
{

  /** The is pressed. */
  private boolean isPressed;

  /** The y. */
  private int x, y;

  /** The Constant H. */
  public static final int W = 80, H = 80;

  /** The waiting image. */
  private Image pressedImage, waitingImage;

  /**
   * Instantiates a new receptor.
   * 
   * @param filenamePressed the filename pressed
   * @param filenameWaiting the filename waiting
   * @param x the x
   * @param y the y
   */
  public Receptor(String filenamePressed, String filenameWaiting, int x, int y)
  {
    pressedImage = new ImageIcon(filenamePressed).getImage();
    waitingImage = new ImageIcon(filenameWaiting).getImage();
    this.x = x;
    this.y = y;
    isPressed = false;
  }

  /**
   * Sets the pressed.
   * 
   * @param b the new pressed
   */
  public void setPressed(boolean b)
  {
    this.isPressed = b;
  }

  public boolean isPressed()
  {
    return this.isPressed;
  }

  /**
   * Draw.
   * 
   * @param g the g
   * @param io the io
   */
  public void draw(Graphics g, ImageObserver io)
  {
    if (isPressed)
      g.drawImage(pressedImage, x, y, W, H, io);
    else
      g.drawImage(waitingImage, x, y, W, H, io);
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

}
