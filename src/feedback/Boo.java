package feedback;

import graphics.steps.Step;

/**
 * The Class Boo.
 */
public class Boo extends FeedBack
{

  /**
   * Instantiates a new boo.
   *
   * @param s the Step this feedback corresponds to
   * @param vertShift the vertical shift
   */
  public Boo(Step s, int vertShift)
  {
    super("pic/Boo.png", s, vertShift);
  }

}
