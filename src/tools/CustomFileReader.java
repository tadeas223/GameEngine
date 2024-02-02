package tools;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class CustomFileReader {
    public static String[] readLines(String path){
        ArrayList<String> lines = new ArrayList<>();
        try{
            Scanner sc = new Scanner(new File(path));

            while(sc.hasNextLine()){
                lines.add(sc.nextLine());
            }
            sc.close();

        } catch (IOException e){
            throw new RuntimeException(e);
        }

        return lines.toArray(new String[0]);
    }

    public static String readLine(String path, int line){
        String[] lines = readLines(path);

        return lines[line];
    }

    public static int getValueLine(String path, String valueName){
        String[] lines = readLines(path);

        for(int i = 0; i < lines.length;i++){
            if(lines[i].contains(valueName)){
                return i;
            }
        }
        return -1;
    }

    public static int getValueLine(String path, String valueName, int startLine){
        String[] lines = readLines(path);

        for(int i = startLine; i < lines.length;i++){
            if(lines[i].contains(valueName)){
                return i;
            }
        }
        return -1;
    }

    public static int getValueLine(String path,String valueName,int startLine,int endLine){
        String[] lines = readLines(path);

        if(endLine > lines.length) endLine = lines.length;

        for(int i = startLine; i < endLine;i++){
            if(lines[i].contains(valueName)){
                return i;
            }
        }
        return -1;
    }

    public static int getValueLine(String path,String valueName,int startLine, boolean untilNextEnd){
        if(untilNextEnd){
            String[] lines = readLines(path);
            int i = startLine+1;

            while(!lines[i].equals("END")){
                if(lines[i].contains(valueName)){
                    return i;
                }
                i++;

            }
        } else{
            return getValueLine(path,valueName,startLine);
        }
        return -1;
    }
    public static String readSimpleValue(String path,String valueName){
        String[] lines = readLines(path);

        for(int i = 0; i < lines.length; i++){
            if(lines[i].contains(valueName)){
                String[] split = lines[i].split(" ");
                return split[1];
            }
        }

        return null;
    }

    public static String readSimpleValue(String path, String valueName, int startLine){
        String[] lines = readLines(path);

        for(int i = startLine; i < lines.length; i++){
            if(lines[i].contains(valueName)){
                String[] split = lines[i].split(" ");
                return split[1];
            }
        }

        return null;
    }

    public static String readSimpleValue(String path, String valueName, int startLine, int endLine){
        String[] lines = readLines(path);

        if(endLine > lines.length) endLine = lines.length;

        for(int i = startLine; i < endLine; i++){
            if(lines[i].contains(valueName)){
                String[] split = lines[i].split(" ");
                return split[1];
            }
        }

        return null;
    }

    public static String readSimpleValue(String path, String valueName, int startLine, boolean untilNextEnd){
        if(untilNextEnd){
            String[] lines = readLines(path);
            int i = startLine+1;

            while(!lines[i].equals("END")){
                if(lines[i].contains(valueName)){
                    String[] split = lines[i].split(" ");
                    return split[1];
                }
                i++;

            }

            return null;
        } else{
            return readSimpleValue(path,valueName,startLine);
        }
    }

    public static String[] readComplexValue(String path, String valueName){
        String[] lines = readLines(path);

        ArrayList<String> value = new ArrayList<>();

        for(int i = 0; i < lines.length; i++){
            if(lines[i].equals(valueName)){
                int j = i+1;

                while(!lines[j].equals("END")){
                    value.add(lines[j]);
                    j++;
                }
            }
        }

        return value.toArray(new String[0]);
    }

    public static String[] readComplexValue(String path, String valueName, int startline){
        String[] lines = readLines(path);

        ArrayList<String> value = new ArrayList<>();

        for(int i = startline; i < lines.length; i++){
            if(lines[i].equals(valueName)){
                int j = i+1;

                while(!lines[j].equals("END")){
                    value.add(lines[j]);
                    j++;
                }
            }
        }

        return value.toArray(new String[0]);
    }

    public static String[] readComplexValue(String path, String valueName, int startLine,int endLine){
        String[] lines = readLines(path);

        ArrayList<String> value = new ArrayList<>();

        if(endLine > lines.length) endLine = lines.length;

        for(int i = startLine; i < endLine; i++){
            if(lines[i].contains(valueName)){
                int j = i+1;

                while(!lines[j].equals("END")){
                    value.add(lines[j]);
                    j++;
                }
            }
        }

        return value.toArray(new String[0]);
    }

}
