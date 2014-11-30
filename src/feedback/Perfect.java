package feedback;

import graphics.steps.Step;

/**
 * The Class Perfect.
 */
public class Perfect extends FeedBack
{

  /**
   * Instantiates a new perfect.
   * 
   * @param s the Step this feedback corresponds to
   * @param vertShift the vertical shift
   */
  public Perfect(Step s, int vertShift)
  {
    super("pic/Perfect.png", s, 155, 60, vertShift);
  }

}
