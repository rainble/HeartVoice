

//之前我们是根据心情的不同，会切换播放器左边的图片。这部分可以先留着
//之前更新数据的时候这个函数会在index里，在数据更新的时候被调用，你可以搜索一下
function replaceOnshowEmoji(number){
    switch(number) {
        case 0:
            document.getElementById('face_realtime').src='img/angry.png';
            break;
        case 1:
            document.getElementById('face_realtime').src='img/disgust.png';
            break;
        case 2:
            document.getElementById('face_realtime').src='img/fear.png';
            break;
        case 3:
            document.getElementById('face_realtime').src='img/happy.png';
            break;
        case 4:
            document.getElementById('face_realtime').src='img/sad.png';
            break;
        case 5:
            document.getElementById('face_realtime').src='img/surprise.png';
            break;
        case 6:
            document.getElementById('face_realtime').src='img/neutral.png';
            break;
        default:
            alert("输入表情序号不在范围内");
    }
}
