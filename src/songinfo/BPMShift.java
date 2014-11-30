package songinfo;
import java.io.Serializable;

/**
 * The Class BPMShift.
 */
public class BPMShift implements Serializable
{

  /** The beat number. */
  private double beatNumber;

  /** The bpm. */
  private double bpm;

  /**
   * Instantiates a new bPM shift.
   * 
   * @param beatNumber the beat number
   * @param bpm the bpm
   */
  public BPMShift(double beatNumber, double bpm)
  {
    this.beatNumber = beatNumber;
    this.bpm = bpm;
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
   * Gets the bpm.
   *
   * @return the bpm
   */
  public double getBPM()
  {
    return bpm;
  }
  
  /* (non-Javadoc)
   * @see java.lang.Object#toString()
   */
  public String toString()
  {
    return beatNumber + "=" + bpm;
  }

}
