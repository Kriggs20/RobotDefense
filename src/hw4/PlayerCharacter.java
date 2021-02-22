package hw4;

import java.util.Scanner;

/**
 * The class implements the player character (PC).
 * 
 * @author Kyle
 */
public class PlayerCharacter extends Character {
	
	/**
	 * This instance variable keeps track if the player character is alive or 
	 * dead after each round of the game.
	 */
	private boolean alive = true;
  /**
   * Constructs a new PC at position (x, y) in the tableau
   *
   * @param x The PCs initial x position.
   * @param y The PCs initial y position.
   */
  public PlayerCharacter(int x, int y)
  {
	  super(x, y);
  }

  /**
   * Returns true if and only if the PC is still alive.
   *
   * @return Whether or not the PC lives
   */
  public boolean isAlive()
  {
	  if (alive == true) {
		  return alive;
	  }
	  else {
		  return false;
	  }
  }

  /**
   * Marks the PC as no longer being alive
   */
  public void die()
  {
	  alive = false;
  }

  /**
   * Stringifies the PC
   *
   * @return The stringified PC
   */
  @Override
  public String toString()
  {
    return "@";
  }

  /**
   * This method should never be called.
   *
   * @param c Never called
   * @param t Never called
   * @return Never called
   */
  public Cell collideWith(Cell c, Tableau t)
  {
    return this;
  }

  /**
   * Handles player I/O.  Returns the new (possibly unchanged) position of
   * the PC.
   *
   * @param t The tableau
   * @return The new position of the PC
   */
  public Pair moveTo(Tableau t) {
    Scanner sc = new Scanner(System.in);
    boolean goodMove = false;
    int toX, toY;
    do {
      toX = xPos;
      toY = yPos;

      System.out.print("; your move: ");

      switch(sc.next().charAt(0)) {
      case 'h':
        toX--;
        break;
      case 'j':
        toY++;
        break;
      case 'k':
        toY--;
        break;
      case 'l':
        toX++;
        break;
      case 'y':
        toX--;
        toY--;
        break;
      case 'u':
        toX++;
        toY--;
        break;
      case 'b':
        toX--;
        toY++;
        break;
      case 'n':
        toX++;
        toY++;
        break;
      case '.':
        goodMove = true;
        break;
      case 'z':
        if (t.hasZap()) {
          doZap(t);
          goodMove = true;
        }
        break;
      case 'w':
        goodMove = t.startWait();
        break;
      case 'q':
        System.exit(0);
        break;
      default:
      }

      if (toX >= 0 && toX < t.getX() && toY >= 0 && toY < t.getY() &&
          t.getCell(toX, toY) == null) {
        goodMove = true;
      }

      if (!goodMove) {
        System.out.print(t + " Invalid move");
      }
    } while (!goodMove);
    
    sc.close();
    return new Pair(toX, toY);
  }

  /**
   * Gets a valid direction from the player and zaps a ray in that
   * direction.  Rays destroy all robots and rubble (but not rock) in a
   * straight line in a single direction from the PC to the edge of the
   * tableau
   *
   * @param The tableau
   */
  void doZap(Tableau t)
  {
	  Scanner sc = new Scanner(System.in);
	  boolean zap;
	  int toX = 0;
	  int toY = 0;
	  t.useZap();
	  
	  do {
		  zap = false;
		  System.out.println("Which direction would you like to FIRE UR LAZOR??!!");
		  
		  switch (sc.next().charAt(0)) {
		  case 'h' :
			  toX = -1;
			  toY = 0;
			  break;
		  case 'j' :
			  toX = 0;
			  toY = 1;
			  break;
		  case 'k' :
			  toX = 0;
			  toY = -1;
			  break;
		  case 'l' :
			  toX = 1;
			  toY = 0;
			  break;
		  case 'y' :
			  toX = -1;
			  toY = -1;
			  break;
		  case 'u' :
			  toX = 1;
			  toY = -1;
			  break;
		  case 'b' :
			  toX = -1;
			  toY = 1;
			  break;
		  case 'n' :
			  toX = 1;
			  toY = 1;
			  break;
		  default :
			  zap = true;
			  break;  
		  }
		  
	  } while (zap);
	  
	  int xSpot = toX + xPos;
	  int ySpot = toY + yPos;
	  
	  for (int i = ySpot; xSpot < t.getX() && ySpot < t.getY(); i++) {
		  Cell newSpot = t.getCell(xSpot, i);
		  
		  if (newSpot.getZapped() == true) {
			  t.nullifyCell(xSpot, i);
		  }
	  }
	  sc.close();
  }


  /**
   * Handles the situation where a Cell is zapped (by a ray or an
   * exploding robot).  Zapping vaporizes (no rubble) everything except
   * PermanentRock (which isn't effected.  Returns true if and only if the
   * value of the cell should be changed to null.  The PC is killed by a zap.
   *
   * @return Whether or not the cell should be nullified
   */
  @Override
  public boolean getZapped()
  {
	  alive = false;
	  
	  return true;
  }

  /**
   * Handles the situation where a Cell is hit by a (non-exploding) robot.
   * Getting hit will leave rock or rubble if cell was rock or rubble, will
   * leave rubble if cell was a robot.  Will cause an explosion if cell is
   * exploding robot.  Returns the value that should be placed in cell after
   * hit, and marks the PC as dead.
   *
   * @param t The tableau
   * @param by The thing doing the hitting
   * @return New value for cell
   */
  @Override
  public Cell getHit(Tableau t, Robot by)
  {
    alive = false;

    return by;
  }

  /**
   * Signals whether or not a cell can be removed (from Tableau's robot
   * list).  Robots return true; everything else false.  The PC should be
   * marked dead.
   *
   * @return false
   */
  @Override
  public boolean removable()
  {
    alive = false;

    return false;
  }
}
