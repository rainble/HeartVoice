package server.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import server.domain.*;
import server.service.ServerService;

import java.io.*;
import java.util.ArrayList;

@RestController
public class ServerController {

    @Autowired
    private ServerService service;

    @CrossOrigin(origins = "*")
    @RequestMapping(value="/test",method= RequestMethod.GET)
    public String create(){
        return "Server Service";
    }

    @CrossOrigin(origins = "*")
    @RequestMapping(value="/musicservice/music/getmusic/{type}", method = RequestMethod.GET)
    public ArrayList<MusicInfo> doGetMusicGet(@PathVariable String type){

        return service.getMusicGet(type);

    }

    @CrossOrigin(origins = "*")
    @RequestMapping(value="/musicservice/music/getmusic", method = RequestMethod.POST)
    public ArrayList<MusicInfo> doGetMusicPost(@RequestBody GetMusicInfo info){

        return service.getMusicPost(info.getPath());

    }


    @CrossOrigin(origins = "*")
    @RequestMapping(value="/musicservice/music/interact", method = RequestMethod.POST)
    public InteractResult doInteractcPost(@RequestBody GetMusicInfo info){

        return service.interact(info.getPath());

    }

    //1、根据用户语音输入生成歌曲，前台输入数据为用户语音，发送后台数据为{path: " paht/to/voice/wav"}，
    // 接收数据为歌曲列表{[name: "song's name", addr: "path/to/song"],[name: "song's name", addr: "path/to/song"]}；
    @CrossOrigin(origins = "*")
    @RequestMapping(value = "/yufan/autoGenSongs", method = RequestMethod.POST)
    @ResponseBody
    public ArrayList<MusicInfo> autoGenSongs(@RequestParam("file") MultipartFile file){

        if (!file.isEmpty()) {
            try {
                File newFile = new File("/hackdata/" + file.getOriginalFilename());
                BufferedOutputStream out = new BufferedOutputStream(
                        new FileOutputStream(newFile));
                out.write(file.getBytes());
                out.flush();
                out.close();

                System.out.println(" === File Absolute Path:" + newFile.getAbsolutePath());
                //文件已写入 进行后续操作
            } catch (FileNotFoundException e) {
                e.printStackTrace();
                return null;
            } catch (IOException e) {
                e.printStackTrace();
                return null;
            }
            return null;
        } else {
            return null;
        }
    }


    //2、根据用户手动输入生成歌曲，前台输入数据为用户手动输入，发送后台数据为{kind: "song's kind"}，接收数据同1；1
    @CrossOrigin(origins = "*")
    @RequestMapping(value = "/yufan/manuallyGenSongs", method = RequestMethod.POST)
    public ArrayList<MusicInfo> manuallyGenSongs(@RequestBody GenSongs info){

        String songKind = info.getKind();
        return service.manuallyGenSongs(songKind);

    }

    //3.风格迁移，前台输入数据为用户语音，发送后台数据同1，接收数据为{code: 1/2, path: path/to/voice/or/song}，
    // 当返回为1时path为语音数据，返回为2是为音乐数据；
    @CrossOrigin(origins = "*")
    @RequestMapping(value = "/yufan/autoConvertSongsStyle", method = RequestMethod.POST)
    @ResponseBody
    public TypeAndSound convertSongsStyle(@RequestParam("file") MultipartFile file){



        if (!file.isEmpty()) {
            try {
                File newFile = new File("/hackdata/" + file.getOriginalFilename());
                BufferedOutputStream out = new BufferedOutputStream(
                        new FileOutputStream(newFile));
                out.write(file.getBytes());
                out.flush();
                out.close();

                System.out.println(" === File Absolute Path:" + newFile.getAbsolutePath());
                //文件已写入 进行后续操作
            } catch (FileNotFoundException e) {
                e.printStackTrace();
                return null;
            } catch (IOException e) {
                e.printStackTrace();
                return null;
            }
            return null;
        } else {
            return null;
        }

    }

    //4、风格迁移，前台输入数据为用户手动输入，发送后台数据同2，接收数据同3
    @CrossOrigin(origins = "*")
    @RequestMapping(value = "/yufan/manullyConvertSongsStyle", method = RequestMethod.POST)
    public TypeAndSound convertSongsStyle(@RequestBody GenSongs info){

        String songKind = info.getKind();
        return service.manuallyConvertSongsStyle(songKind);
    }

    @CrossOrigin(origins = "*")
    @RequestMapping(value = "/yufan/upload", method = RequestMethod.POST)
    @ResponseBody
    public String upload(@RequestParam("file") MultipartFile file, @RequestParam("filePath") String filePath) {

        System.out.println("===== Uploaded File Path = " + filePath + " =====");

        if (!file.isEmpty()) {
            try {

                System.out.println("File Upload - File Name:" + file.getName());
                System.out.println("File Upload - File Original Filename:" + file.getOriginalFilename());
                System.out.println("File Upload - File Size:" + file.getSize());

                File newFile = new File("/hackdata/" + file.getOriginalFilename());
                BufferedOutputStream out = new BufferedOutputStream(
                        new FileOutputStream(newFile));
                out.write(file.getBytes());
                out.flush();
                out.close();

                System.out.println(" === File Absolute Path:" + newFile.getAbsolutePath());
                //文件已写入 进行后续操作






            } catch (FileNotFoundException e) {
                e.printStackTrace();
                return "Upload fail," + e.getMessage();
            } catch (IOException e) {
                e.printStackTrace();
                return "Upload Fail," + e.getMessage();
            }
            return "Upload Success.";
        } else {
            return "Upload Fail. Because the file is empty.";
        }
    }


}
