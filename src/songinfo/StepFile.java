package songinfo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * The Class StepFile.
 */
public class StepFile implements Serializable
{

  /** The note data. */
  private ArrayList<Note> noteData;
  
  /** The notes type. */
  private String notesType;
  
  /** The description. */
  private String description;
  
  /** The difficult class. */
  private String difficultClass;
  
  /** The difficult meter. */
  private int difficultMeter;
  
  /** The radar values. */
  private double[] radarValues;

  /**
   * Instantiates a new step file.
   */
  public StepFile()
  {
    radarValues = new double[5];
    noteData = new ArrayList<Note>();
  }

  /**
   * Gets the note data.
   *
   * @return the note data
   */
  public ArrayList<Note> getNoteData()
  {
    return noteData;
  }

  /**
   * Sets the note data.
   *
   * @param noteData the new note data
   */
  public void setNoteData(ArrayList<Note> noteData)
  {
    this.noteData = noteData;
  }
  
  /**
   * Adds the note.
   *
   * @param note the note
   */
  public void addNote(Note note)
  {
    noteData.add(note);
  }

  /**
   * Gets the notes type.
   *
   * @return the notes type
   */
  public String getNotesType()
  {
    return notesType;
  }

  /**
   * Sets the notes type.
   *
   * @param notesType the new notes type
   */
  public void setNotesType(String notesType)
  {
    this.notesType = notesType;
  }

  /**
   * Gets the description.
   *
   * @return the description
   */
  public String getDescription()
  {
    return description;
  }

  /**
   * Sets the description.
   *
   * @param description the new description
   */
  public void setDescription(String description)
  {
    this.description = description;
  }

  /**
   * Gets the difficult class.
   *
   * @return the difficult class
   */
  public String getDifficultClass()
  {
    return difficultClass;
  }

  /**
   * Sets the difficult class.
   *
   * @param difficultClass the new difficult class
   */
  public void setDifficultClass(String difficultClass)
  {
    this.difficultClass = difficultClass;
  }

  /**
   * Gets the difficult meter.
   *
   * @return the difficult meter
   */
  public int getDifficultMeter()
  {
    return difficultMeter;
  }

  /**
   * Sets the difficult meter.
   *
   * @param difficultMeter the new difficult meter
   */
  public void setDifficultMeter(int difficultMeter)
  {
    this.difficultMeter = difficultMeter;
  }

  /**
   * Gets the radar values.
   *
   * @return the radar values
   */
  public double[] getRadarValues()
  {
    return radarValues;
  }

  /**
   * Sets the radar values.
   *
   * @param radarValues the new radar values
   */
  public void setRadarValues(double[] radarValues)
  {
    this.radarValues = radarValues;
  }
  
  /**
   * Prints the data.
   */
  public void printData()
  {
    System.out.println("Notes type: " + notesType);
    System.out.println("Description: " + description);
    System.out.println("Difficult class: " + difficultClass);
    System.out.println("Difficult meter: " + difficultMeter);
    System.out.println("Radar values: " + Arrays.toString(radarValues));
    System.out.println();
    for (Note note: noteData)
      note.printData();
  }

}
