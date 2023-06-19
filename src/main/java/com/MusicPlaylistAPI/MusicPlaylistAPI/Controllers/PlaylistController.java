package com.MusicPlaylistAPI.MusicPlaylistAPI.Controllers;

import com.MusicPlaylistAPI.MusicPlaylistAPI.Models.Playlist;
import com.MusicPlaylistAPI.MusicPlaylistAPI.RequestObject.PlaylistRequest;
import com.MusicPlaylistAPI.MusicPlaylistAPI.ResponseObject.PlaylistResponse;
import com.MusicPlaylistAPI.MusicPlaylistAPI.Services.PlaylistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    /****** Playlist Retrieval ******/
    @GetMapping(value = "/{id}")
    public PlaylistResponse getPlaylistById(@PathVariable Long id) {
        Playlist playlist = playlistService.getPlaylistById(id);
        PlaylistResponse convert = PlaylistResponse.convertToResponse(playlist);
        return convert;
    }

    /****** Playlist Update ******/
    @PutMapping("/{id}")
    public void updatePlaylist(@PathVariable long id, @RequestBody PlaylistRequest playlistRequest) {
        playlistService.updatePlaylist(id, playlistRequest);
    }

    /****** Playlist Deletion ******/
    @DeleteMapping("/{id}")
    public String deletePlaylistById(@PathVariable long id){
        playlistService.deletePlaylistById(id);
        return "Playlist deleted successfully.";
    }

    /****** Song Addition ******/
    @PostMapping("/{id}/songs")
    public PlaylistResponse addSongToPlaylist(@PathVariable Long id, @RequestBody List<String> songs) {
        playlistService.addSongToPlaylist(id, songs);
        Playlist playlist = playlistService.getPlaylistById(id);
        return PlaylistResponse.convertToResponse(playlist);
    }

//    @PostMapping("/{id}/songs")
//    public void addSongsToPlaylist(@PathVariable Long id, @RequestBody List<String> songs) {
//        Playlist playlist = playlistService.getPlaylistById(id);
//        if (playlist != null) {
//            List<String> existingSongs = playlist.getSongs();
//            existingSongs.addAll(songs);
//            playlistService.updatePlaylist(id, playlist);
//        }
//    }
}
