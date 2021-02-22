package hw4;
/**
   <h1>COM S 227<br>String 2020<br>Assignment 4<br>300 points</h1>

   <p>Due: Friday, May 1, 11:59 pm (midnight)<br>+5&#37; for submissions at
   least 24 hours early (Thursday, April 30, 11:59 pm)<br><b>NO LATE
   SUBMISSIONS WILL BE ACCEPTED!!!</b><br><b>NO LATE
   SUBMISSIONS WILL BE ACCEPTED!!!</b><br><b>NO LATE
   SUBMISSIONS WILL BE ACCEPTED!!!</b><br><h1>NO LATE
   SUBMISSIONS WILL BE ACCEPTED!!!</h1>

   <h1>General Information</h1>

   <p>This assignment is to be done on your own. See the Academic Dishonesty
      policy in the syllabus, <a
      href="http://www.cs.iastate.edu/~cs227/syllabus.html">http://www.cs.iastate.edu/~cs227/syllabus.html</a>, for details.

   <p>You will not be able to submit your work unless you have completed the
Academic Dishonesty policy acknowledgement on the Homework page on
Blackboard. Please do this right away.

<p>If you need help, see your instructor or one of the TAs. Lots of help is
also available through the Piazza discussions.

<p>Please start the assignment as soon as possible and get your questions
answered right away!  There are likely to be more errors and ambiguities in
the spec than in previous assignments, and part of your job is to seek
clarification about them.

<p>Check Piazza regularly for updates to this document.

<h1>Introduction</h1>

<p> The purpose of this assignment is to give you some experience using
inheritance.

<p> You will be implementing a robot apocalypse game.  The robot hordes are
attacking the earth.  Armed only with your wits and a ray gun with three
charges, you will defend the earth from the robots.  Most of the robots will
simply take the shortest route to you, paying no heed to other robots or
obstructions, but collisions will destroy them.  You can use this to your
advantage by tricking them into running into each other or obstructions.
Some robots can explode, so be careful about letting them get close!  As a
last resort, you can fire a ray, buy you have a limited number of charges;
fortunately, you an get more with each new wave of the invasion.  Your ray
gun is very powerful!  It will destroy everything except rocks in the
direction that it is fired.  Sadly, you are destined to fail.  While you do
provide earths final resistance, the robot hordes are endless and they will
eventually overwhelm you.  The best you can do is inflict as much damage on
them as you can before that happens.

<h1>The user interface</h1>

<p>The user interface is text-based.  Java doesn't provide a mechanism for unbuffered I/O, so it is necessary to hit enter after each key of input.  The movement keys may seem odd, but them come from the popular editor <i>vi</i> and have been used in many, many UNIX games over the past few decades (most notably <i>nethack</i>), and will be very familiar to anybody coming from that tradition.

<p>There is an undocumented (in the UI) quit command bound to 'q'.

<p>You are welcome to add additional case statements to PlayerCharacter.moveTo and PlayerCharacter.doZap in order to alias the movement keys to something you are more familiar with; however, you may not remove the default keybindings!

<h1> What do you need to do?</h1>

<p>Most of the code is provided for you.  You are responsible to implementing or completing the following classes and methods:

<p>* class PlayerCharacter: one or more instance variables and several methods
<p>* class Robot: collideWith, moveTo, and getHit
<p>* class SmartRobot: moveTo
<p>* class Tableau: startWait
 */
public class Robots {
  /**
   * The main method
   *
   * @param args Optionally, a non-default set of dimensions for the tableau
   */
  public static void main(String [] args)
  {
    Tableau t;

    if (args.length == 2) {
      int x = Integer.parseInt(args[0]);
      int y = Integer.parseInt(args[1]);

      t = new Tableau(x, y);
    } else {
      t = new Tableau();
    }

    do {
      t.generateLevel();

      do {
        t.takeTurns();
      } while (!t.levelCleared() && !t.gameOver());
    } while(!t.gameOver());
    System.out.println(t + "; You died.  Final score: " + 
                       t.getScore() + " points");
  }
}
