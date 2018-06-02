package server.controller;

import com.ibm.watson.developer_cloud.text_to_speech.v1.TextToSpeech;
import com.ibm.watson.developer_cloud.text_to_speech.v1.model.SynthesizeOptions;
import com.ibm.watson.developer_cloud.text_to_speech.v1.util.WaveUtils;
import org.springframework.web.bind.annotation.*;
import server.domain.*;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

@RestController
public class ServerController {

    @CrossOrigin(origins = "*")
    @RequestMapping(value="/test",method= RequestMethod.GET)
    public String create(){
        return "Speech2Txt Service";
    }


    public static TextToSpeech service =
            new TextToSpeech("0281454d-66ac-46a5-952c-29d09b6e156e", "SVincMELqxwx");

    //奚耀国
    //4.通过文本生成语音文件
    @CrossOrigin(origins = "*")
    @RequestMapping(value="/musicservice/music/saveaudio", method = RequestMethod.POST)
    public Boolean doGetMusicGet(@RequestBody SpeechInfo info){

        String text = info.getText();

        String path = info.getPath();

        System.out.println("[==/musicservice/music/saveaudio==] Text:" + text + " " + "Path:"+ path);

        try {
            SynthesizeOptions synthesizeOptions = new SynthesizeOptions.Builder()
                    .text(text)
                    .accept("audio/wav")
                    .voice("en-US_AllisonVoice")
                    .build();
            InputStream stream = service.synthesize(synthesizeOptions).execute();
            InputStream in = WaveUtils.reWriteWaveHeader(stream);

            OutputStream out = new FileOutputStream(path);
            byte[] buffer = new byte[1024];
            int length;
            while ((length = in.read(buffer)) > 0) {
                out.write(buffer, 0, length);
            }
            out.close();
            in.close();
            stream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return true;

    }

}
