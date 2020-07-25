package com.xdclass.online_xdclass.controller;

import com.xdclass.online_xdclass.model.entity.Video;
import com.xdclass.online_xdclass.model.entity.VideoBanner;
import com.xdclass.online_xdclass.service.VideoService;
import com.xdclass.online_xdclass.utils.JsonData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/v1/pub/video")
public class VideoController {

    @Autowired
    private VideoService videoService;

    /**
     * 轮播图列表
     * @return
     */
    @RequestMapping("list_banner")
    public JsonData indexBanner(){
        //int i = 1/0;
        List<VideoBanner> bannerList = videoService.listBanner();
        return JsonData.buildSuccess(bannerList);
    }

    /**
     * 视频列表
     * @return
     */
    //localhost:8080/api/v1/pub/video/list
    @RequestMapping("list")
    public JsonData listVideo(){
        List<Video> videoList = videoService.listVideo();
        return JsonData.buildSuccess(videoList);
    }

    /**
     * 查询视频详情 包含章、集信息
     * @param videoId
     * @return
     */
    @GetMapping("find_detail_by_id")
    public JsonData findDetailById(@RequestParam(value = "video_id",required = true)int videoId){
        Video video = videoService.findDetailById(videoId);
        return JsonData.buildSuccess(video);
    }

}
