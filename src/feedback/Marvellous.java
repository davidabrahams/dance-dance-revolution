package feedback;

import graphics.steps.Step;

/**
 * The Class Marvellous.
 */
public class Marvellous extends FeedBack
{

  /**
   * Instantiates a new marvellous.
   *
   * @param s the Step this feedback corresponds to
   * @param vertShift the vertical shift
   */
  public Marvellous(Step s, int vertShift)
  {
    super("pic/Marvellous.png", s, 200, 60, vertShift);
  }

}
