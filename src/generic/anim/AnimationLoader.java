package generic.anim;

import tools.CustomFileReader;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class AnimationLoader {
    public static Animation loadAnimation(String path) {
        ArrayList<BufferedImage> images = new ArrayList<>();
        double wait = 0;
        boolean loop;

        String imageIDs = CustomFileReader.readSimpleValue(path+"/data.anim","IMAGES");
        wait = Double.parseDouble(CustomFileReader.readSimpleValue(path+"/data.anim","WAIT"));
        loop = Boolean.parseBoolean(CustomFileReader.readSimpleValue(path+"/data.anim","Loop"));

        String[] split = imageIDs.split("-");
        int startId = Integer.parseInt(split[0]);
        int endId = Integer.parseInt(split[1]);

        for(int i = startId; i <= endId; i++){
            try{
                images.add(ImageIO.read(new File(path + i + ".png")));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

        return new Animation(images.toArray(new BufferedImage[0]),wait,loop);
    }

    public static AnimationController loadAnimationController(String path){
        AnimationController animationController = new AnimationController();

        String[] animNames = CustomFileReader.readComplexValue(path+"/data.animc","ANIMATIONS");

        int animEnd = CustomFileReader.getValueLine(path+"/data.animc","END");

        for(String s : animNames){
            int animLine = CustomFileReader.getValueLine(path+"/data.animc",s,animEnd+1);

            String animPath = CustomFileReader.readSimpleValue(path+"/data.animc","PATH",animLine,true);
            animPath = animPath.split("\"")[1];

            String flip = CustomFileReader.readSimpleValue(path+"/data.animc","FLIP",animLine,true);

            Animation animation = loadAnimation(path + "/" + animPath);

            if(flip != null){
                if(flip.equals("X")){
                    animation.flip(true);
                } else if (flip.equals("Y")){
                    animation.flip(false);
                }
            }


            animationController.addAnimation(s,animation);
        }

        return animationController;
    }
}
