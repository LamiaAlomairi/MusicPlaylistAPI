package com.MusicPlaylistAPI.MusicPlaylistAPI.Controllers;

import com.MusicPlaylistAPI.MusicPlaylistAPI.Models.Playlist;
import com.MusicPlaylistAPI.MusicPlaylistAPI.RequestObject.PlaylistRequest;
import com.MusicPlaylistAPI.MusicPlaylistAPI.ResponseObject.PlaylistResponse;
import com.MusicPlaylistAPI.MusicPlaylistAPI.Services.PlaylistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

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
    @GetMapping("/{id}")
    public PlaylistResponse getPlaylistById(@PathVariable Long id) {
        Playlist playlist = playlistService.getPlaylistById(id);
        return PlaylistResponse.convertToResponse(playlist);
    }

    /****** Playlist Update ******/
    @PutMapping("/{id}")
    public void updatePlaylist(@PathVariable Long id, @RequestBody PlaylistRequest playlistRequest) {
        playlistService.updatePlaylist(id, playlistRequest);
    }

    /****** Playlist Deletion ******/
    @DeleteMapping("/{id}")
    public void deletePlaylistAndSongs(@PathVariable Long id) {
        playlistService.deletePlaylistAndSongsById(id);
    }

    /****** Song Addition ******/
    @PostMapping("/{playlistId}/songs")
    public void addSongsToPlaylist(@PathVariable Long playlistId, @RequestBody List<Long> songIds) {
        playlistService.addSongsToPlaylistById(playlistId, songIds);
    }

    /****** Delete Songs by id ******/
    @DeleteMapping("/{playlistId}/songs/{songId}")
    public void deleteSongFromPlaylist(@PathVariable Long playlistId, @PathVariable Long songId) {
        playlistService.deleteSongFromPlaylist(playlistId, songId);
    }

    /****** Search for Playlist by keyword ******/
    @GetMapping("/search")
    public List<PlaylistResponse> searchPlaylistsByKeyword(@RequestParam String keyword) {
        List<Playlist> playlists = playlistService.searchPlaylistsByKeyword(keyword);
        return playlists.stream()
                .map(PlaylistResponse::convertToResponse)
                .collect(Collectors.toList());
    }
}
