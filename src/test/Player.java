package test;

import Tools.Vector2;
import gameObject.GameObject;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class Player extends GameObject {
    public Player(){
        try {
            setTexture(ImageIO.read(new File("data/player.png")));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        addModule(new MovementModule(this,100));
        clampSizeToTexture();
        setPosition(20,20);
        setScale(new Vector2(1,1));
    }
}
