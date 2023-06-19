package com.MusicPlaylistAPI.MusicPlaylistAPI.Controllers;

import com.MusicPlaylistAPI.MusicPlaylistAPI.Models.Playlist;
import com.MusicPlaylistAPI.MusicPlaylistAPI.Models.Song;
import com.MusicPlaylistAPI.MusicPlaylistAPI.RequestObject.PlaylistRequest;
import com.MusicPlaylistAPI.MusicPlaylistAPI.Services.PlaylistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "api/playlists")
public class PlaylistController {
    @Autowired
    PlaylistService playlistService;

    /*******  Playlist Creation  ******/
    @PostMapping
    public void createPlaylist(@RequestBody PlaylistRequest playlistRequest) {
        playlistService.createPlaylist(playlistRequest);
    }

//    @PostMapping("{id}/songs")
//    public ResponseEntity<Playlist> addSongToPlaylist(@PathVariable Long id, @RequestBody Song song) {
//        playlistService.addSongToPlaylist(id, song);
//        return new ResponseEntity<>(HttpStatus.OK);
//    }
}
