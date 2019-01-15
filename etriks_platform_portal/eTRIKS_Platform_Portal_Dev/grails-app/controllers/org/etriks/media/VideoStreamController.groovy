package org.etriks.media

import org.etriks.utils.DirectoryManager

class VideoStreamController {
  //This method shows the list of video availabe in the portal
    def index() {
        DirectoryManager dm=new DirectoryManager("/web-app/images/videos");
        def List<String> videoList=new ArrayList<String>();
        List<String> fileList = dm.getFileNameList();
        for(String file:fileList){
            if(file.endsWith("mp4")){
                  videoList.add(file);
            }
        }
        render(view:"index",model:[videoList:videoList])
    }

    //This action plays a specific video based on the given ID.
    def showVideo(String videoId){

        render(view:"showVideo",model:[videoId:videoId])
    }
}
