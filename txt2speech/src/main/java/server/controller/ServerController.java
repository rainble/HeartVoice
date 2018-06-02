package server.controller;

import org.springframework.web.bind.annotation.*;
import server.domain.*;

@RestController
public class ServerController {

    @CrossOrigin(origins = "*")
    @RequestMapping(value="/test",method= RequestMethod.GET)
    public String create(){
        return "Txt2Speech Service";
    }


    //奚耀国
    //4.通过语音生成文本文件
    @CrossOrigin(origins = "*")
    @RequestMapping(value="/musicservice/music/gettext", method = RequestMethod.POST)
    public Txt2SpeechResult doGetMusicGet(@RequestBody Txt2SpeechInfo info){

        return new Txt2SpeechResult("jichao-test-data");

    }

}
