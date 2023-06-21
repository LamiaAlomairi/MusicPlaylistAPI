package com.MusicPlaylistAPI.MusicPlaylistAPI.Controllers;

import com.MusicPlaylistAPI.MusicPlaylistAPI.Models.Song;
import com.MusicPlaylistAPI.MusicPlaylistAPI.RequestObject.SongRequest;
import com.MusicPlaylistAPI.MusicPlaylistAPI.ResponseObject.SongResponse;
import com.MusicPlaylistAPI.MusicPlaylistAPI.Services.SongService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "api/songs")
public class SongController {
    @Autowired
    SongService songService;

    /*******  Song Addition  ******/
    @PostMapping
    public void addSong(@RequestBody SongRequest songRequest) {
        try {
            songService.addSong(songRequest);
        } catch (Exception e) {
            System.err.println("Cannot create song " + e.getMessage());
        }
    }

    /*******  Get All Song  ******/
    @GetMapping
    public List<SongResponse> getAllSongs() {
        try {
            List<Song> songs = songService.getAllSongs();
            List<SongResponse> listOfConvertedSong = SongResponse.convertToResponseList(songs);
            return listOfConvertedSong;
        } catch (Exception e) {
            System.err.println("Cannot get all songs " + e.getMessage());
            return null;
        }
    }
}
