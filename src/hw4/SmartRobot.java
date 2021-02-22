package hw4;
/**
 * Class to represent smart robots.  Smart robots will not collide with
 * rubble or other robots (however, other robots can collide with smart
 * robots.
 * 
 * @author Kyle
 */
public class SmartRobot extends Robot {

  /**
   * Stringifies a smart robot
   *
   * @return The stringified robot
   */
  @Override
  public String toString()
  {
    return "S";
  }

  /**
   * Constructs a new smart robot at position (x, y)
   *
   * @param x The x position of the robot
   * @param y The y position of the robot
   */
  public SmartRobot(int x, int y)
  {
    super(x, y);
  }

  /**
   * Smart robots move toward the PC is at least one dimension, both if
   * possible, but only if there exists a move which doesn't result in a
   * collision.  A smart robot will never collide with obstrutions or other
   * robots.  It can get stuck behind an obstruction (or, optionally, you can
   * implement some more intelligent pathfinding around obsructions, but that
   * is harder to code, and make an already very hard game harder, as well).
   *
   * @param t The tableau
   * @return The new position
   */
  public Pair moveTo(Tableau t) 
  {
	  int xSpot;
	  int ySpot;
	  int pcX = t.getPC().getX();
	  int pcY = t.getPC().getY();
	  
	  if (t.getPC().getX() == xPos) {
		  xSpot = 0;
	  }
	  else {
		  if (pcX - xPos > 0) {
			  xSpot = 1;
		  }
		  else {
			  xSpot = -1;
		  }
	  }
	  
	  if (t.getPC().getY() == yPos) {
		  ySpot = 0;
	  }
	  else {
		  if (pcY - yPos > 0) {
			  ySpot = 1;
		  }
		  else {
			  ySpot = -1;
		  }
	  }
	  
	  if ((xSpot + xPos) == pcX && (ySpot + yPos) == pcY) {
		  return new Pair((xSpot + xPos), (ySpot + yPos));
	  }
	  else if (t.getCell(xSpot + xPos, yPos) == null) {
		  xPos += xSpot;
	  }
	  else if (t.getCell(xPos,  ySpot + yPos) == null) {
		  yPos += ySpot;
	  }
	  else if (t.getCell(xSpot + xPos, ySpot + yPos) == null){
		  xPos += xSpot;
		  yPos += ySpot;
	  }
	  
	  return new Pair(xPos, yPos);
  }
}
