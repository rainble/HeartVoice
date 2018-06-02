package server.service;

import server.domain.*;

import java.util.ArrayList;

public interface ServerService {

    ArrayList<MusicInfo> getMusicGet(String type);

    ArrayList<MusicInfo> getMusicPost(String path);

    InteractResult interact(String path);

    ArrayList<MusicInfo> manuallyGenSongs(String songKind);

    TypeAndSound manuallyConvertSongsStyle(String songKind);

}
