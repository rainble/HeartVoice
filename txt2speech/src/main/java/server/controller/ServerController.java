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

    @CrossOrigin(origins = "*")
    @RequestMapping(value="/musicservice/music/gettext", method = RequestMethod.POST)
    public Txt2SpeechResult doGetMusicGet(@RequestBody Txt2SpeechInfo info){

        return new Txt2SpeechResult("jichao-test-data");

    }

}
