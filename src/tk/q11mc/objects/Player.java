package tk.q11mc.objects;


//import com.siinus.simpleGrafixShader.Light;
import com.siinus.simpleGrafixShader.ShaderImage;
import tk.q11mc.Main;
import tk.q11mc.core.Handler;

import java.awt.*;
import java.awt.event.KeyEvent;

public class Player extends GameObject {
    //private final Light light;
    public int dx = 4, dy = 4;
    //dx und dy müssen genauso groß sein wie speed
    static float speed,minusSpeed;
    public Player(Main program, ShaderImage sprite, int width, int height, Handler handler) {
        super(program, sprite, width, height,handler);
        //light = new Light(256,0xffffffff);
    }

    @Override
    public void update() {
        controls();
    }
    public void controls() {
        speed = 4f;
        minusSpeed = -1*speed;
        if (program.getInput().isKeyPressed(KeyEvent.VK_D) && !collisionright()) {
            x+=speed;
        }
        if (program.getInput().isKeyPressed(KeyEvent.VK_S) && !collisiondown()) {
            y+=speed;
        }
        if (program.getInput().isKeyPressed(KeyEvent.VK_A) && !collisionleft()) {
            x-=speed;
        }
        if (program.getInput().isKeyPressed(KeyEvent.VK_W) && !collisionup()) {
            y-=speed;
        }
    }
    public boolean collisionup() {
        for(GameObject other : Handler.objects ) {
            if(other instanceof Collideable && ((Collideable) other).intersects(new Rectangle(x,y-dx,width
                    ,height))) {
                return true;
            }
        }
        return false;
    }
    public boolean collisiondown() {
        for(GameObject other : Handler.objects ) {
            if(other instanceof Collideable && ((Collideable) other).intersects(new Rectangle(x,y+dy,width
                    ,height))) {
                return true;
            }
        }
        return false;
    }
    public boolean collisionleft() {
        for(GameObject other : Handler.objects ) {
            if(other instanceof Collideable && ((Collideable) other).intersects(new Rectangle(x-dx,y,width
            ,height))) {
                return true;
            }
        }
        return false;
    }
    public boolean collisionright() {
        for(GameObject other : Handler.objects ) {
            if(other instanceof Collideable && ((Collideable) other).intersects(new Rectangle(x+dx,y,width
                    ,height))) {
                return true;
            }
        }
        return false;
    }

    @Override
    public void render() {
        program.getRenderer().drawImage(sprite, x+offX(), y+offY());
        //program.getShader().drawLight(light, x+64, y+64);
    }

    @Override
    public Rectangle getBounds() {
        return new Rectangle(x,y,width,height);
    }

}