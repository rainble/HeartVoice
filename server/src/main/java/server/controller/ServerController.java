package server.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;
import server.domain.*;
import server.service.ServerService;
import java.io.*;
import java.util.ArrayList;

@RestController
public class ServerController {

    @Autowired
    private ServerService service;

    @Autowired
    private RestTemplate restTemplate;

    @CrossOrigin(origins = "*")
    @RequestMapping(value="/test",method= RequestMethod.GET)
    public String create(){
        return "Server Service";
    }

    @RequestMapping(value = "/download/{filename}", method = RequestMethod.GET)
    public Resource downloadTxt2(@PathVariable String filename) {

        System.out.println("FileName === " + filename + ".mp3");

        return new FileSystemResource("/hackdata/music/" + filename  + ".mp3");
    }

    //奚耀国
    //1.根据type获取music
    //ArrayList<MusicInfo>是返回给客户端的json数据
    @CrossOrigin(origins = "*")
    @RequestMapping(value="/musicservice/music/getmusic/{type}", method = RequestMethod.GET)
    public ArrayList<MusicInfo> doGetMusicGet(@PathVariable String type){

        return service.getMusicGet(type);

    }

    //奚耀国
    //2.根据语音交互获取music
    //info是从body中获取到的json对象
    @CrossOrigin(origins = "*")
    @RequestMapping(value="/musicservice/music/getmusic", method = RequestMethod.POST)
    public ArrayList<MusicInfo> doGetMusicPost(@RequestBody GetMusicInfo info){

        return service.getMusicPost(info.getPath());

    }

    //奚耀国
    //3.与用户交互进行风格迁移
    @CrossOrigin(origins = "*")
    @RequestMapping(value="/musicservice/music/interact", method = RequestMethod.POST)
    public InteractResult doInteractcPost(@RequestBody GetMusicInfo info){

        return service.interact(info.getPath());

    }

    //孙若宇
    //1、根据用户语音输入生成歌曲，前台输入数据为用户语音，发送后台数据为{path: " paht/to/voice/wav"}，
    // 接收数据为歌曲列表{[name: "song's name", addr: "path/to/song"],[name: "song's name", addr: "path/to/song"]}；
    @CrossOrigin(origins = "*")
    @RequestMapping(value = "/yufan/autoGenSongs", method = RequestMethod.POST)
    @ResponseBody
    public ArrayList<MusicInfo> autoGenSongs(@RequestParam("file") MultipartFile file){

        if (!file.isEmpty()) {
            try {
                File newFile = new File("/hackdata/" + file.getOriginalFilename() + ".wav");
                BufferedOutputStream out = new BufferedOutputStream(
                        new FileOutputStream(newFile));
                out.write(file.getBytes());
                out.flush();
                out.close();


                //上传后文件的绝对路径（在docker文件系统中的绝对路径）
                String newFileAbsolutePath = newFile.getAbsolutePath();
                System.out.println(" === File Absolute Path:" + newFileAbsolutePath);


                //打包数据调用---奚耀国的语音文件生成文本接口
                Txt2SpeechInfo info = new Txt2SpeechInfo(newFileAbsolutePath);
                Txt2SpeechResult result = restTemplate.postForObject("http://txt2speech:14569/musicservice/music/gettext",
                        info,Txt2SpeechResult.class);
                //result是语音文件生成文本接口的返回数据
                System.out.println("[调用语音文件生成文本接口]" + result.getText());




            } catch (FileNotFoundException e) {
                e.printStackTrace();
                return null;
            } catch (IOException e) {
                e.printStackTrace();
                return null;
            }



            //mock data
            MusicInfo info1 = new MusicInfo("output_9.mp3", "output_9.mp3");
            MusicInfo info2 = new MusicInfo("output_57.mp3", "output_57.mp3");
            MusicInfo info3 = new MusicInfo("output_42.mp3", "output_42.mp3");
            MusicInfo info4 = new MusicInfo("output_18.mp3", "output_18.mp3");
            MusicInfo info5 = new MusicInfo("output_37.mp3", "output_37.mp3");

            ArrayList<MusicInfo> infos = new ArrayList<>();
            infos.add(info1);
            infos.add(info2);
            infos.add(info3);
            infos.add(info4);
            infos.add(info5);


            return infos;
        } else {
            return null;
        }
    }

    //孙若宇
    //2、根据用户手动输入生成歌曲，前台输入数据为用户手动输入，发送后台数据为{kind: "song's kind"}，接收数据同1；1
    @CrossOrigin(origins = "*")
    @RequestMapping(value = "/yufan/manuallyGenSongs", method = RequestMethod.POST)
    public ArrayList<MusicInfo> manuallyGenSongs(@RequestBody GenSongs info){

        String songKind = info.getKind();
        return service.manuallyGenSongs(songKind);

    }

    //孙若宇
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



                //上传后文件的绝对路径（在docker文件系统中的绝对路径）
                String newFileAbsolutePath = newFile.getAbsolutePath();
                System.out.println(" === File Absolute Path:" + newFileAbsolutePath);



                //文件已写入 进行后续操作
            } catch (FileNotFoundException e) {
                e.printStackTrace();
                return null;
            } catch (IOException e) {
                e.printStackTrace();
                return null;
            }


            //mock data
            TypeAndSound result = new TypeAndSound(0,"asdasdsa");
            return result;

        } else {
            return null;
        }

    }

    //孙若宇
    //4、风格迁移，前台输入数据为用户手动输入，发送后台数据同2，接收数据同3
    @CrossOrigin(origins = "*")
    @RequestMapping(value = "/yufan/manullyConvertSongsStyle", method = RequestMethod.POST)
    public TypeAndSound convertSongsStyle(@RequestBody GenSongs info){

        String songKind = info.getKind();
        return service.manuallyConvertSongsStyle(songKind);
    }



    //测试用的
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


                //上传后文件的绝对路径（在docker文件系统中的绝对路径）
                System.out.println(" === File Absolute Path:" + newFile.getAbsolutePath());







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
