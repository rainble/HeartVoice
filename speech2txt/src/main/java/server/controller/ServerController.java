package server.controller;

import org.springframework.web.bind.annotation.*;
import server.domain.*;

@RestController
public class ServerController {

    @CrossOrigin(origins = "*")
    @RequestMapping(value="/test",method= RequestMethod.GET)
    public String create(){
        return "Speech2Txt Service";
    }


    //奚耀国
    //4.通过文本生成语音文件
    @CrossOrigin(origins = "*")
    @RequestMapping(value="/musicservice/music/saveaudio", method = RequestMethod.POST)
    public Boolean doGetMusicGet(@RequestBody SpeechInfo info){

        return new Boolean(false);

    }

}
