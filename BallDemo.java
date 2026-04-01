import java.awt.Color;
import java.util.Random;
import java.util.ArrayList;

/**
 * Class BallDemo - a short demonstration showing animation with the 
 * Canvas class. 
 *
 * @author Michael Kölling and David J. Barnes
 * @author Gavin Armenti
 * 
 * @version 2026.03.31
 */

public class BallDemo   
{
    private Canvas myCanvas;
    private Box box;

    /**
     * Create a BallDemo object. Creates a fresh canvas and makes it visible.
     * 
     */
    public BallDemo()
    {
        myCanvas = new Canvas("Ball Demo", 600, 500);
        box=new Box (100,100,500,400, myCanvas);
        box.draw();
        
        Box box2 = new Box(myCanvas);
        box2.draw();
        
    }

    /**
     * boxBounce - simulate 5-50 balls bouncing within a box
     * 
     * @param numOfBalls number of balls to simulate bouncing, clamped between 5-50. 
     */
    public void boxBounce(int numOfBalls)
    {
        // you must implement this
        Random random = new Random();
        
        if (numOfBalls >= 5 && numOfBalls <=50)
        {
            ArrayList<BoxBall> boxballs = new ArrayList<BoxBall>();
            for (int i = 0; i < numOfBalls; i++)
            {
                int xpos = random.nextInt(368) + 116;
                int ypos = random.nextInt(268) + 116;
                
                int colorR = random.nextInt(256);
                int colorG = random.nextInt(256);
                int colorB = random.nextInt(256);
                
                if (colorR > 210 && colorG > 210 && colorB > 210)
                {
                    colorR = 150;
                    colorG = 150;
                    colorB = 150;
                }
                
                boxballs.add(new BoxBall(xpos, ypos, 16, new Color(colorR, colorG, colorB), this.box, myCanvas));
            }
            
            while (true)
            {
                for (int i = 0; i < boxballs.size();i++)
                {
                    boxballs.get(i).move();
                }
            }
        }
    }
    
    /**
     * Simulate two bouncing balls
     */
    public void bounce()
    {
        int ground = 400;   // position of the ground line

        myCanvas.setVisible(true);

        // draw the ground
        myCanvas.setForegroundColor(Color.BLACK);
        myCanvas.drawLine(50, ground, 550, ground);

        // create and show the balls
        BouncingBall ball = new BouncingBall(50, 50, 16, Color.BLUE, ground, myCanvas);
        ball.draw();
        BouncingBall ball2 = new BouncingBall(70, 80, 20, Color.RED, ground, myCanvas);
        ball2.draw();

        // make them bounce
        boolean finished =  false;
        while (!finished) {
            myCanvas.wait(50);           // small delay
            ball.move();
            ball2.move();
            // stop once ball has travelled a certain distance on x axis
            if(ball.getXPosition() >= 550 || ball2.getXPosition() >= 550) {
                finished = true;
            }
        }
    }
}
