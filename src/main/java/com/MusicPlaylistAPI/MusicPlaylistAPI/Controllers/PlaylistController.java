package com.MusicPlaylistAPI.MusicPlaylistAPI.Controllers;

import com.MusicPlaylistAPI.MusicPlaylistAPI.Models.Playlist;
import com.MusicPlaylistAPI.MusicPlaylistAPI.RequestObject.PlaylistRequest;
import com.MusicPlaylistAPI.MusicPlaylistAPI.ResponseObject.PlaylistResponse;
import com.MusicPlaylistAPI.MusicPlaylistAPI.Services.PlaylistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "api/playlists")
public class PlaylistController {
    @Autowired
    PlaylistService playlistService;

    /*******  Playlist Creation  ******/
    @PostMapping
    public void createPlaylist(@RequestBody PlaylistRequest playlistRequest) {
        try {
            playlistService.createPlaylist(playlistRequest);
        } catch (Exception e) {
            System.err.println("Cannot create playlist: " + e.getMessage());
        }
    }

    /****** Playlist Retrieval ******/
    @GetMapping("/{id}")
    public PlaylistResponse getPlaylistById(@PathVariable Long id) {
        try {
            Playlist playlist = playlistService.getPlaylistById(id);
            if (playlist == null) {
                // Handle the case when playlist is not found
                System.err.println("playlist is not found ");
                return null;
            }
            return PlaylistResponse.convertToResponse(playlist);
        } catch (Exception e) {
            System.err.println("Internal Server Error: " + e.getMessage());
            return null; // or return an appropriate error response
        }
    }


    /****** Playlist Update ******/
    @PutMapping("/{id}")
    public void updatePlaylist(@PathVariable Long id, @RequestBody PlaylistRequest playlistRequest) {
        try {
            playlistService.updatePlaylist(id, playlistRequest);
        } catch (Exception e) {
            System.err.println("Cannot update playlist: " + e.getMessage());
        }
    }

    /****** Playlist Deletion ******/
    @DeleteMapping("/{id}")
    public void deletePlaylistAndSongs(@PathVariable Long id) {
        try {
            playlistService.deletePlaylistAndSongsById(id);
        } catch (Exception e) {
            System.err.println("Cannot delete playlist: " + e.getMessage());
        }
    }

    /****** Song Addition ******/
    @PostMapping("/{playlistId}/songs")
    public void addSongsToPlaylist(@PathVariable Long playlistId, @RequestBody List<Long> songIds) {
        try {
            playlistService.addSongsToPlaylistById(playlistId, songIds);
        } catch (Exception e) {
            System.err.println("Cannot added songs to playlist: " + e.getMessage());
        }
    }

    /****** Delete Songs by id ******/
    @DeleteMapping("/{playlistId}/songs/{songId}")
    public void deleteSongFromPlaylist(@PathVariable Long playlistId, @PathVariable Long songId) {
        try {
            playlistService.deleteSongFromPlaylist(playlistId, songId);
        } catch (Exception e) {
            System.err.println("Cannot delete songs from playlist: " + e.getMessage());
        }
    }

    /****** Search for Playlist by keyword ******/
    @GetMapping("/search")
    public List<PlaylistResponse> searchPlaylistsByKeyword(@RequestParam String keyword) {
        List<Playlist> playlists = playlistService.searchPlaylistsByKeyword(keyword);
        List<PlaylistResponse> playlistResponses = new ArrayList<>();
        try {
            for (Playlist playlist : playlists) {
                playlistResponses.add(PlaylistResponse.convertToResponse(playlist));
            }

        } catch (Exception e) {
            System.err.println("Cannot search about playlist: " + e.getMessage());
        }
        return playlistResponses;
    }
}
