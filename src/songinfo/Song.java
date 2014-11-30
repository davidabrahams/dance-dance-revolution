package songinfo;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;

import javax.swing.ImageIcon;

import org.apache.commons.io.FilenameUtils;

/**
 * The Class Song.
 */
public class Song implements Serializable
{

  /** The music. */
  private MP3 music;

  /** The artist. */
  private String title, subtitle, artist;

  /** The cd title. */
  private ImageIcon banner, background, cdTitle;

  /** The offset. */
  private double offset;

  /** The shifts. */
  private BPMShift[] shifts;

  /** The stops. */
  private Stop[] stops;

  /** The step files. */
  private ArrayList<StepFile> stepFiles;

  /**
   * Instantiates a new song.
   *
   * @param mpFile the mp file
   * @param stepFile the step file
   * @param backgroundImage the background image
   * @param bannerImage the banner image
   * @param cdTitleImage the cd title image
   * @throws NumberFormatException the number format exception
   * @throws IOException Signals that an I/O exception has occurred.
   * @throws IllegalArgumentException the illegal argument exception
   * @throws NullPointerException the null pointer exception
   */
  public Song(String mpFile, String stepFile, String backgroundImage, String bannerImage, String cdTitleImage) throws NumberFormatException, IOException, IllegalArgumentException, NullPointerException
  {
    if (mpFile == null || stepFile == null || backgroundImage == null || bannerImage == null || cdTitleImage == null)
      throw new NullPointerException("One of the filenames was null");
    if (!(verifySong(mpFile) && verifyText(stepFile) && verifyImage(backgroundImage) && verifyImage(bannerImage) && verifyImage(cdTitleImage)))
      throw new IllegalArgumentException("One of the file extensions was incorrect.");
    music = new MP3(mpFile);
    background = new ImageIcon(backgroundImage);
    banner = new ImageIcon(bannerImage);
    cdTitle = new ImageIcon(cdTitleImage);
    stepFiles = new ArrayList<StepFile>();
    inputDataFromStepFile(stepFile);
  }

  /**
   * Input data from step file.
   * 
   * @param stepFileName the step file
   * @throws IOException Signals that an I/O exception has occurred.
   * @throws NumberFormatException the number format exception
   */
  private void inputDataFromStepFile(String stepFileName) throws IOException, NumberFormatException
  {
    BufferedReader reader;
    reader = new BufferedReader(new FileReader(stepFileName));
    String line;
    while ((line = reader.readLine()) != null)
    {
      if (line.startsWith("#TITLE:"))
        title = line.substring("#TITLE:".length(), line.lastIndexOf(';'));
      else if (line.startsWith("#SUBTITLE:"))
        subtitle = line.substring("#SUBTITLE:".length(), line.lastIndexOf(';'));
      else if (line.startsWith("#ARTIST:"))
        artist = line.substring("#ARTIST:".length(), line.lastIndexOf(';'));
      else if (line.startsWith("#OFFSET:"))
        offset = Double.parseDouble(line.substring("#OFFSET:".length(), line.lastIndexOf(';')));
      else if (line.startsWith("#BPMS:"))
      {
        while (line.indexOf(';') == -1)
          line += reader.readLine();
        String substring = line.substring("#BPMS:".length(), line.lastIndexOf(';'));
        String[] parsed = substring.split(",");
        shifts = new BPMShift[parsed.length];
        for (int i = 0; i < shifts.length; i++)
        {
          String current = parsed[i];
          int equalsIndex = current.indexOf('=');
          double beatNum = Double.parseDouble(current.substring(0, equalsIndex));
          double bpm = Double.parseDouble(current.substring(equalsIndex + 1));
          shifts[i] = new BPMShift(beatNum, bpm);
        }
      }
      else if (line.startsWith("#STOPS:"))
      {
        while (line.indexOf(';') == -1)
          line += reader.readLine();
        String substring = line.substring("#STOPS:".length(), line.lastIndexOf(';'));
        if (substring.length() > 0)
        {
          String[] parsed = substring.split(",");
          stops = new Stop[parsed.length];
          for (int i = 0; i < stops.length; i++)
          {
            String current = parsed[i];
            int equalsIndex = current.indexOf('=');
            double beatNum = Double.parseDouble(current.substring(0, equalsIndex));
            double time = Double.parseDouble(current.substring(equalsIndex + 1));
            stops[i] = new Stop(beatNum, time);
          }
        }
      }
      else if (line.startsWith("#NOTES:"))
      {
        StepFile stepFile = new StepFile();
        readNoteData(reader, stepFile);
        stepFiles.add(stepFile);
      }
    }
    reader.close();
  }

  /**
   * Read note data.
   *
   * @param reader the reader
   * @param stepFile the step file
   * @throws IOException Signals that an I/O exception has occurred.
   * @throws NumberFormatException the number format exception
   */
  private void readNoteData(BufferedReader reader, StepFile stepFile) throws IOException, NumberFormatException
  {
    String notesType = processNoteInfo(reader.readLine());
    String description = processNoteInfo(reader.readLine());
    String difficultClass = processNoteInfo(reader.readLine());
    int difficultMeter = Integer.parseInt(processNoteInfo(reader.readLine()));
    String[] radarValueString = processNoteInfo(reader.readLine()).split(",");
    double[] radarValues = new double[radarValueString.length];
    for (int i = 0; i < radarValues.length; i++)
      radarValues[i] = Double.parseDouble(radarValueString[i]);
    stepFile.setNotesType(notesType);
    stepFile.setDescription(description);
    stepFile.setDifficultClass(difficultClass);
    stepFile.setDifficultMeter(difficultMeter);
    stepFile.setRadarValues(radarValues);
    String line;
    ArrayList<char[]> measure = new ArrayList<char[]>();
    int currentBeatNumber = 0;
    while ((line = reader.readLine().trim()).length() == 0 || line.charAt(0) != ';')
    {
      if (line.indexOf(',') != -1)
      {
        for (int i = 0; i < measure.size(); i++)
          stepFile.addNote(new Note(4.0 * i / measure.size() + currentBeatNumber, measure.get(i)));
        currentBeatNumber += 4;
        measure = new ArrayList<char[]>();
      }
      else if (line.length() != 0 && line.indexOf("//") == -1)
        measure.add(line.toCharArray());
    }
    for (int i = 0; i < measure.size(); i++)
      stepFile.addNote(new Note(4.0 * i / measure.size() + currentBeatNumber, measure.get(i)));
  }

  /**
   * Gets the banner.
   *
   * @return the banner
   */
  public ImageIcon getBanner()
  {
    return banner;
  }

  /**
   * Gets the background.
   *
   * @return the background
   */
  public ImageIcon getBackground()
  {
    return background;
  }

  /**
   * Gets the cd title.
   *
   * @return the cd title
   */
  public ImageIcon getCdTitle()
  {
    return cdTitle;
  }
  
  /**
   * Gets the music.
   *
   * @return the music
   */
  public MP3 getMusic()
  {
    return music;
  }

  /**
   * Verify song.
   *
   * @param filename the filename
   * @return true, if successful
   */
  private boolean verifySong(String filename)
  {
    return FilenameUtils.getExtension(filename).equalsIgnoreCase("mp3");
  }

  /**
   * Verify image.
   *
   * @param filename the filename
   * @return true, if successful
   */
  private boolean verifyImage(String filename)
  {
    return FilenameUtils.getExtension(filename).equalsIgnoreCase("png") || FilenameUtils.getExtension(filename).equalsIgnoreCase("jpg");
  }

  /**
   * Verify text.
   *
   * @param filename the filename
   * @return true, if successful
   */
  private boolean verifyText(String filename)
  {
    return FilenameUtils.getExtension(filename).equalsIgnoreCase("sm") || FilenameUtils.getExtension(filename).equalsIgnoreCase("txt");
  }

  /**
   * Process note info.
   *
   * @param s the s
   * @return the string
   */
  private String processNoteInfo(String s)
  {
    return s.trim().substring(0, s.trim().indexOf(':'));
  }

  /*
   * (non-Javadoc)
   * 
   * @see java.lang.Object#toString()
   */
  public String toString()
  {
    return title;
  }
  
  /**
   * Gets the step files.
   *
   * @return the step files
   */
  public ArrayList<StepFile> getStepFiles()
  {
    return stepFiles;
  }
  
  /**
   * Gets the bpm.
   *
   * @return the bpm
   */
  public BPMShift[] getBPM()
  {
    return shifts;
  }
  
  /**
   * Gets the offset.
   *
   * @return the offset
   */
  public double getOffset()
  {
    return offset;
  }
  
  /**
   * Gets the stops.
   *
   * @return the stops
   */
  public Stop[] getStops()
  {
    return stops;
  }

  /**
   * Prints the data.
   */
  public void printData()
  {
    System.out.println("Music file: " + music.getFileName());
    System.out.println("Title: " + title);
    System.out.println("Subtitle: " + subtitle);
    System.out.println("Artist: " + artist);
    System.out.println("Offset: " + offset);
    System.out.println("Shifts: " + Arrays.toString(shifts));
    System.out.println("Stops: " + Arrays.toString(stops));
    System.out.println();
    for (StepFile stepFile : stepFiles)
      stepFile.printData();
  }

}
