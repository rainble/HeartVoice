package server.service;

import org.springframework.stereotype.Service;
import server.domain.*;
import java.util.ArrayList;

@Service
public class ServerServiceImpl implements ServerService{


    public TypeAndSound manuallyConvertSongsStyle(String songKind){

        System.out.println("Kind:" + songKind);
        TypeAndSound result = new TypeAndSound(0,"jichao-test");

        return result;

    }

    public ArrayList<MusicInfo> manuallyGenSongs(String songKind){

        System.out.println("Kind:" + songKind);

        MusicInfo info1 = new MusicInfo("rock", "jichao-test-info-one");
        MusicInfo info2 = new MusicInfo("jazz", "jichao-test-info-two");

        ArrayList<MusicInfo> infos = new ArrayList<>();
        infos.add(info1);
        infos.add(info2);

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


        //mock data
        MusicInfo info1 = new MusicInfo("rock", "jichao-test-info-one");
        MusicInfo info2 = new MusicInfo("jazz", "jichao-test-info-two");

        ArrayList<MusicInfo> infos = new ArrayList<>();
        infos.add(info1);
        infos.add(info2);

        return infos;

    }

    public ArrayList<MusicInfo> getMusicPost(String path) {

        System.out.println("Path:" + path);


        MusicInfo info1 = new MusicInfo("rock", "jichao-test-info-one");
        MusicInfo info2 = new MusicInfo("jazz", "jichao-test-info-two");

        ArrayList<MusicInfo> infos = new ArrayList<>();
        infos.add(info1);
        infos.add(info2);

        return infos;


    }

    public InteractResult interact(String path) {

        System.out.println("Path:" + path);

        InteractResult result = new InteractResult(0,path);

        return result;

    }

}
