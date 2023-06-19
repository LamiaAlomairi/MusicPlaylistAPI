package com.MusicPlaylistAPI.MusicPlaylistAPI.Controllers;

import com.MusicPlaylistAPI.MusicPlaylistAPI.RequestObject.SongRequest;
import com.MusicPlaylistAPI.MusicPlaylistAPI.Services.SongService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "api/playlists/{id}/songs")
public class SongController {
    @Autowired
    SongService songService;

    /*******  Song Addition  ******/
//    @PostMapping
//    public void addSong(@RequestBody SongRequest songRequest) {
//        songService.addSong(songRequest);
//    }
}
