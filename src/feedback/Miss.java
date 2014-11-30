package feedback;

import graphics.steps.Step;

/**
 * The Class Miss.
 */
public class Miss extends FeedBack
{

  /**
   * Instantiates a new miss.
   * 
   * @param s the Step this feedback corresponds to
   * @param vertShift the vertical shift
   */
  public Miss(Step s, int vertShift)
  {
    super("pic/Miss.png", s, vertShift);
  }

}
