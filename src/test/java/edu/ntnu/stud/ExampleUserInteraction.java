package edu.ntnu.stud;

/**
 * The ExampleUserInteraction class is a subclass of UserInteraction.
 * It overrides the methods of UserInteraction to return predefined values,
 * which is useful for testing the Logic class without actual user input.
 */
public class ExampleUserInteraction extends UserInteraction {

  /**
   * Overrides the promptForTrainId method to return a predefined train ID.
   * @param shouldExist Whether the train should exist
   * @return The predefined train ID
   */
  @Override
  public int promptForTrainId(boolean shouldExist) {
    return 1;
  }

  /**
   * Overrides the promptForLine method to return a predefined line.
   * @return The predefined line
   */
  @Override
  public String promptForLine() {
    return "Line1";
  }

  /**
   * Overrides the promptForTime method to return a predefined time.
   * @return The predefined time
   */
  @Override
  public String promptForTime() {
    return "10:00";
  }

  /**
   * Overrides the promptForDestination method to return a predefined destination.
   * @return The predefined destination
   */
  @Override
  public String promptForDestination() {
    return "Destination1";
  }

  /**
   * Overrides the promptForTrackNumber method to return a predefined track number.
   * @return The predefined track number
   */
  @Override
  public int promptForTrackNumber() {
    return 1;
  }

  /**
   * Overrides the waitForEnter method to do nothing.
   * This is because there's no need to wait for the user to press Enter in a test environment.
   */
  @Override
  public void waitForEnter() {
    // Do nothing
  }
}