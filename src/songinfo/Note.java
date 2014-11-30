package songinfo;
import java.io.Serializable;
import java.util.Arrays;

/**
 * The Class Note.
 */
public class Note implements Serializable
{

  /** The beat. */
  private double beat;
  
  /** The notes. */
  private char[] notes;

  /**
   * Instantiates a new note.
   *
   * @param beat the beat
   * @param notes the notes
   */
  public Note(double beat, char[] notes)
  {
    this.beat = beat;
    this.notes = notes;
  }

  /**
   * Gets the beat.
   *
   * @return the beat
   */
  public double getBeat()
  {
    return beat;
  }

  /**
   * Gets the notes.
   *
   * @return the notes
   */
  public char[] getNotes()
  {
    return notes;
  }

  /**
   * Prints the data.
   */
  public void printData()
  {
    System.out.println(beat + "; " + Arrays.toString(notes));
  }

}
