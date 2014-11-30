package songinfo;
import java.io.Serializable;

/**
 * The Class Stop.
 */
public class Stop implements Serializable
{

  /** The beat number. */
  private double beatNumber;

  /** The stop length. */
  private double stopLength;

  /**
   * Instantiates a new stop.
   * 
   * @param beatNumber the beat number
   * @param stopLength the stop length
   */
  public Stop(double beatNumber, double stopLength)
  {
    this.beatNumber = beatNumber;
    this.stopLength = stopLength;
  }

  /**
   * Gets the beat number.
   *
   * @return the beat number
   */
  public double getBeatNumber()
  {
    return beatNumber;
  }



  /**
   * Gets the stop length.
   *
   * @return the stop length
   */
  public double getStopLength()
  {
    return stopLength;
  }



  /* (non-Javadoc)
   * @see java.lang.Object#toString()
   */
  public String toString()
  {
    return beatNumber + "=" + stopLength;
  }
}