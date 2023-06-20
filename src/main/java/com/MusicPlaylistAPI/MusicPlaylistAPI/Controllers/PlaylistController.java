package com.MusicPlaylistAPI.MusicPlaylistAPI.Controllers;

import com.MusicPlaylistAPI.MusicPlaylistAPI.Models.Playlist;
import com.MusicPlaylistAPI.MusicPlaylistAPI.Models.Song;
import com.MusicPlaylistAPI.MusicPlaylistAPI.RequestObject.PlaylistRequest;
import com.MusicPlaylistAPI.MusicPlaylistAPI.ResponseObject.PlaylistResponse;
import com.MusicPlaylistAPI.MusicPlaylistAPI.Services.PlaylistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "api/playlists")
public class PlaylistController {
    @Autowired
    PlaylistService playlistService;

    /*******  Playlist Creation  ******/
    @PostMapping
    public PlaylistResponse createPlaylist(@RequestBody PlaylistRequest playlistRequest) {
        Playlist playlist = playlistService.createPlaylist(playlistRequest);
        return PlaylistResponse.builder()
                .id(playlist.getId())
                .name(playlist.getName())
                .songIds(playlistService.getSongIdsByPlaylist(playlist))
                .build();
    }

    /****** Playlist Retrieval ******/
    @GetMapping("/{id}")
    public PlaylistResponse getPlaylistById(@PathVariable Long id) {
        Playlist playlist = playlistService.getPlaylistById(id);
        return PlaylistResponse.builder()
                .id(playlist.getId())
                .name(playlist.getName())
                .songIds(playlistService.getSongIdsByPlaylist(playlist))
                .build();
    }

    /****** Playlist Update ******/
    @PutMapping("/{id}")
    public void updatePlaylist(@PathVariable Long id, @RequestBody PlaylistRequest playlistRequest) {
        playlistService.updatePlaylist(id, playlistRequest);
    }
//    @PutMapping("/{id}")
//    public void updatePlaylist(@PathVariable long id, @RequestBody PlaylistRequest playlistRequest) {
//        playlistService.updatePlaylist(id, playlistRequest);
//    }
//
//    /****** Playlist Deletion ******/
//    @DeleteMapping("/{id}")
//    public String deletePlaylistById(@PathVariable long id){
//        playlistService.deletePlaylistById(id);
//        return "Playlist deleted successfully.";
//    }
//
//    /****** Song Addition ******/
//    @PostMapping("/{id}/songs")
////    public PlaylistResponse addSongToPlaylist(@PathVariable Long id, @RequestBody List<Song> songs) {
////        playlistService.addSongToPlaylist(id, songs);
////        Playlist playlist = playlistService.getPlaylistById(id);
////        return PlaylistResponse.convertToResponse(playlist);
////    }
//
//    /****** Search for Playlist by keyword ******/
//    @GetMapping("/search")
//    public List<Playlist> searchPlaylistsByKeyword(@RequestParam("keyword") String keyword) {
//        return playlistService.searchPlaylistsByKeyword(keyword);
//    }
//
//    /****** Delete Songs by id ******/
////    @DeleteMapping("/{playlistId}/songs/{songId}")
////    public void deleteSongFromPlaylist(@PathVariable("playlistId") Long playlistId, @PathVariable("songId") String songId) {
////        List<String> songIds = Collections.singletonList(songId);
////        playlistService.deleteSongFromPlaylist(playlistId, songIds);
////    }
}
