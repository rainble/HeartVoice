package server.service;

import org.springframework.stereotype.Service;
import server.domain.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Service
public class ServerServiceImpl implements ServerService{


    public TypeAndSound manuallyConvertSongsStyle(String songKind){

        System.out.println("Kind:" + songKind);
        TypeAndSound result = new TypeAndSound(0,"jichao-test");

        return result;

    }

    public ArrayList<MusicInfo> manuallyGenSongs(String songKind){

        System.out.println("Kind:" + songKind);

        //mock data
//        MusicInfo info1 = new MusicInfo("output_9.mp3", "output_9.mp3");
//        MusicInfo info2 = new MusicInfo("output_57.mp3", "output_57.mp3");
//        MusicInfo info3 = new MusicInfo("output_42.mp3", "output_42.mp3");
//        MusicInfo info4 = new MusicInfo("output_18.mp3", "output_18.mp3");
//        MusicInfo info5 = new MusicInfo("output_37.mp3", "output_37.mp3");

        ArrayList<MusicInfo> infos = getMus();
//        infos.add(info1);
//        infos.add(info2);
//        infos.add(info3);
//        infos.add(info4);
//        infos.add(info5);

        return infos;
    }


    public ArrayList<MusicInfo> getMusicGet(String type) {

        System.out.println("Type:" + type);
        type = type.toLowerCase();

        switch(type){
            case "rock" :
                //语句
                break; //可选
            case "metal":
                //语句
                break; //可选
            case "hiphop" :
                //语句
                break; //可选
            case "pop" :
                //语句
                break; //可选
            case "jazz" :
                //语句
                break; //可选
            case "classic" :
                //语句
                break; //可选
            default : //可选
                //语句
        }


//        //mock data
//        MusicInfo info1 = new MusicInfo("output_9.mp3", "output_9.mp3");
//        MusicInfo info2 = new MusicInfo("output_57.mp3", "output_57.mp3");
//        MusicInfo info3 = new MusicInfo("output_42.mp3", "output_42.mp3");
//        MusicInfo info4 = new MusicInfo("output_18.mp3", "output_18.mp3");
//        MusicInfo info5 = new MusicInfo("output_37.mp3", "output_37.mp3");

        ArrayList<MusicInfo> infos = getMus();
//
//        infos.add(info1);
//        infos.add(info2);
//        infos.add(info3);
//        infos.add(info4);
//        infos.add(info5);

        return infos;

    }

    public ArrayList<MusicInfo> getMusicPost(String path) {

        System.out.println("Path:" + path);

        //mock data
//        MusicInfo info1 = new MusicInfo("output_9.mp3", "output_9.mp3");
//        MusicInfo info2 = new MusicInfo("output_57.mp3", "output_57.mp3");
//        MusicInfo info3 = new MusicInfo("output_42.mp3", "output_42.mp3");
//        MusicInfo info4 = new MusicInfo("output_18.mp3", "output_18.mp3");
//        MusicInfo info5 = new MusicInfo("output_37.mp3", "output_37.mp3");

        ArrayList<MusicInfo> infos = getMus();
//        infos.add(info1);
//        infos.add(info2);
//        infos.add(info3);
//        infos.add(info4);
//        infos.add(info5);

        return infos;


    }


    private List<MusicInfo> ms = new ArrayList<>();


    public void init() {
        ms.add(new MusicInfo("output_11.mp3", "output_11.mp3"));
        ms.add(new MusicInfo("output_12.mp3", "output_12.mp3"));
        ms.add(new MusicInfo("output_13.mp3", "output_13.mp3"));
        ms.add(new MusicInfo("output_14.mp3", "output_14.mp3"));
        ms.add(new MusicInfo("output_15.mp3", "output_15.mp3"));
        ms.add(new MusicInfo("output_16.mp3", "output_16.mp3"));
        ms.add(new MusicInfo("output_17.mp3", "output_17.mp3"));
        ms.add(new MusicInfo("output_18.mp3", "output_18.mp3"));
        ms.add(new MusicInfo("output_19.mp3", "output_19.mp3"));
        ms.add(new MusicInfo("output_20.mp3", "output_20.mp3"));
        ms.add(new MusicInfo("output_21.mp3", "output_21.mp3"));
        ms.add(new MusicInfo("output_22.mp3", "output_22.mp3"));
        ms.add(new MusicInfo("output_23.mp3", "output_23.mp3"));
        ms.add(new MusicInfo("output_24.mp3", "output_24.mp3"));
        ms.add(new MusicInfo("output_25.mp3", "output_25.mp3"));
    }

    public ArrayList<MusicInfo> getMus() {
        ArrayList<MusicInfo> mus = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            int rand = new Random().nextInt(ms.size());
            mus.add(ms.get(rand));
            ms.remove(rand);
        }
        if (mus.isEmpty())
            init();
        return mus;
    }

    public MusicInfo getChangedMus(String musicName) {

        int rand = new Random().nextInt(4);

        String mn = musicName.split("[.]")[0];
        return new MusicInfo(mn + "_" + rand + ".mp3", "/hackdata/" + mn + "_" + rand + ".mp3");

    }

    public InteractResult interact(String path) {

        System.out.println("Path:" + path);

        InteractResult result = new InteractResult(0,path);

        return result;

    }

}
