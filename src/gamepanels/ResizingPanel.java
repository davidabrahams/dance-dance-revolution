package gamepanels;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;

import javax.swing.JPanel;

/**
 * The Class ResizingPanel.
 */
public abstract class ResizingPanel extends JPanel
{

  /** The assumed height. */
  private int assumedWidth, assumedHeight;

  /**
   * Instantiates a new resizing panel.
   * 
   * @param assumedWidth the assumed width
   * @param assumedHeight the assumed height
   */
  public ResizingPanel(int assumedWidth, int assumedHeight)
  {
    this.assumedWidth = assumedWidth;
    this.assumedHeight = assumedHeight;
  }

  /**
   * Gets the assumed width.
   * 
   * @return the assumed width
   */
  public int getAssumedWidth()
  {
    return assumedWidth;
  }

  /**
   * Gets the assumed height.
   * 
   * @return the assumed height
   */
  public int getAssumedHeight()
  {
    return assumedHeight;
  }

  /**
   * Gets the scaled graphics2d.
   * 
   * @param g the graphics object
   * @return the scaled graphics2d
   */
  public Graphics2D getScaledGraphics2D(Graphics g)
  {
    Graphics2D g2D = (Graphics2D) g;
    g2D.scale((double) getWidth() / assumedWidth, (double) getHeight() / assumedHeight);
    return g2D;
  }

  /**
   * Center drawing point for image.
   * 
   * @param imageWidth the image width
   * @param imageHeight the image height
   * @return the point
   */
  public Point centerDrawingPointForImage(int imageWidth, int imageHeight)
  {
    int x = (assumedWidth - imageWidth) / 2;
    int y = (assumedHeight - imageHeight) / 2;
    return new Point(x, y);
  }

}
