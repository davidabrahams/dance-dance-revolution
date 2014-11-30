package main;

/**
 * The Class DanceDanceRevolution.
 */
public class DanceDanceRevolution
{

  arduioutility.SerialUtility main;
  /** The frame. */
  MainFrame frame;

  /**
   * The main method.
   * 
   * @param args the arguments
   */
  public static void main(String[] args)
  {
    DanceDanceRevolution ddr = new DanceDanceRevolution();
  }

  /**
   * Instantiates a new dance dance revolution.
   */
  public DanceDanceRevolution()
  {
    System.out.println("Started");
    frame = new MainFrame(this);
    main = new arduioutility.SerialUtility();
    main.initialize(frame.getInGamePanel());
    frame.setVisible(true);
  }

  /**
   * Close.
   */
  public void close()
  {
    main.close();
  }

  /**
   * Sets the serial port.
   * 
   * @param port the new serial port
   */
  public void setSerialPort(String port)
  {
    main.setSerialPort(port);
    main.initialize(frame.getInGamePanel());
  }

}