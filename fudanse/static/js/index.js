// 需要丢给播放列表的数据
// var racks=[{
//     "track": 1,
//     "name": "1",
//     "length": "8:31",
//     "file": "1"
// },{
//     "track": 2,
//     "name": "2",
//     "length": "8:31",
//     "file": "2"
// }];

var mediaPath = 'music/';

jQuery(function ($) {
    var supportsAudio = !!document.createElement('audio').canPlayType;
    if (supportsAudio) {
        var playing = false;

        var zz = function(data,flag) {
            $('#plList').html("");
            var index = 0;

            var extension = '';
            var buildPlaylist = $.each(data, function (key, value) {
                var trackNumber = value.track;
                var trackName = value.name
                var trackLength = value.length;
                if (trackNumber.toString().length === 1) {
                    trackNumber = '0' + trackNumber;
                } else {
                    trackNumber = '' + trackNumber;
                }
                $('#plList').append(
                    '<li><div class="plItem"><div class="plNum">' + trackNumber + '.</div>' +
                    '<div class="plTitle">' + trackName + '</div>' +
                    '<div class="plLength">' + trackLength + '</div></div></li>');
            });

            //这一堆定义的是播放器那一堆按钮的操作
            var audio = $('#audio1').bind('play', function () {

                    //点击播放按钮
                    playing = true;
                    $('#npAction').text('Now Playing...');

                }).bind('pause', function () {

                    //点击暂停按钮
                    playing = false;
                    $('#npAction').text('Paused...');

                }).bind('ended', function () {

                    //点击停止按钮（话说停止按钮在哪啊
                    $('#npAction').text('Paused...');
                    if ((index + 1) < data.length) {
                        //播放完一首之后，进行一下操作
                        index++;
                        loadTrack(index);
                        audio.play();
                    } else {
                        //整个list音乐播放结束之后，进行以下操作
                        audio.pause();
                        index = 0;
                        loadTrack(index);
                    }
            }).get(0);

            //点击前一首按钮
            var btnPrev = $('#btnPrev').click(function () {
                if ((index - 1) > -1) {
                    index--;
                    loadTrack(index);
                    if (playing) {
                        audio.play();
                    }
                } else {
                    //如果现在已经是列表第一首，还要向前一首歌，那么就播放列表的第一首
                    audio.pause();
                    index = 0;
                    loadTrack(index);
                }
            });

            //点击下一首按钮
            var btnNext = $('#btnNext').click(function () {
                if ((index + 1) < data.length) {
                    index++;
                    loadTrack(index);
                    if (playing) {
                        audio.play();
                    }
                } else {
                    //如果现在已经是列最后一首，用户又点了向后一首，那就播放第一首歌
                    audio.pause();
                    index = 0;
                    loadTrack(index);
                }
            });



            var li = $('#plList li').click(function () {
                var id = parseInt($(this).index());
                if (id !== index) {
                    playTrack(id);
                }
            });

            var loadTrack = function (id) {
                $('.plSel').removeClass('plSel');
                $('#plList li:eq(' + id + ')').addClass('plSel');
                $('#npTitle').text(data[id].name);
                index = id;
                audio.src = mediaPath + data[id].file + extension;//歌曲的路径，这里还有点细节不太清楚
            };

            var playTrack = function (id) {
                loadTrack(id);
                audio.play();
            };

            if (flag) {
                loadTrack(index);
                audio.play();
            } else{
                index = -1;
            }

        };
    }

    var lastsongflag;

    // //每2秒钟刷新一次页面
    // var timer = setInterval(function() {
    //     getEmotionMusicListFromServer();
    // }, 2000);

    //setInterval(getEmotionMusicListFromServer(), 5000);
    //getEmotionMusicListFromServer();
    getEmotionMusicListFromServer();

    //手动更新播放列表.不过这个按钮被我隐藏了暂时不准备用它
    $("#testPlay").click(function(){
        getEmotionMusicListFromServer();
        //playTheMusic(racks);
    });


    //这个是之前写的连接服务器获取信息的，可以留作参考
    function getEmotionMusicListFromServer(){
        $.ajax({
            type: "get",
            url: "http://10.141.212.24:14567/getSongs",
            contentType: "application/json",
            dataType: "json",
            xhrFields: {
                withCredentials: true
            },
            success: function (obj) {
                if(obj["status"] == true){

                    //获取服务器的播放列表
                    var songList = obj["songInfo"]["songNameList"];
                    //把获取到的播放列表转换成数组
                    var arrayObj = new Array(songList.length);
                    //然后将播放列表数组添加到播放列表里
                    for(var i = 0;i < songList.length;i++){
                        var tempTrack = new Object();
                        tempTrack.track = i;
                        tempTrack.name = songList[i];

                        //因为之前无法准确获取音乐时长所以这里随便生成了一个数字
                        var min = 1 + Math.floor(Math.random()*3);
                        var sec = Math.floor(Math.random()*30) + Math.floor(Math.random()*30) ;


                        tempTrack.length = min + ":" + sec;
                        tempTrack.file = songList[i];
                        arrayObj[i] = tempTrack;
                    }
                    //之前摄像头会采集表情分析情绪，这个是获取情绪，然后把页面上的情绪图标变掉
                    var songflag = obj["songInfo"]["emotionType"];
                    //songflag是这个分析到的心情
                    //lastsongflag是上次的分析心情，要是和这次一样就不更新了
                    if(!(songflag === lastsongflag)) {

                        //更新好了列表就调用这个方法让它去播放
                        playTheMusic(arrayObj);

                        //更换表情图片
                        replaceOnshowEmoji(obj["songInfo"]["emotionType"]);

                        lastsongflag = songflag;
                    }else{
                        console.log("未更换心情图标")
                    }
                }else{
                    //alert("报错信息：" + obj["message"])
                }
            },
            error: function(){
                // alert("服务器无法连接")
            }
        });
    }



    function playTheMusic(songInfoList){
        zz(songInfoList,true);
    }

});

//initialize plyr
plyr.setup($('#audio1'), {});