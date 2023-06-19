package com.MusicPlaylistAPI.MusicPlaylistAPI.Services;

import com.MusicPlaylistAPI.MusicPlaylistAPI.Models.Playlist;
import com.MusicPlaylistAPI.MusicPlaylistAPI.Models.Song;
import com.MusicPlaylistAPI.MusicPlaylistAPI.Repositories.PlaylistRepository;
import com.MusicPlaylistAPI.MusicPlaylistAPI.RequestObject.PlaylistRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PlaylistService {
    @Autowired
    PlaylistRepository playlistRepository;

    /*******  Playlist Creation  ******/
    public void createPlaylist(PlaylistRequest playlistRequest) {
        Playlist playlist = PlaylistRequest.convert(playlistRequest);
        playlistRepository.save(playlist);
    }

//    public void addSongToPlaylist(Long id, Song song) {
//        Optional<Playlist> optionalPlaylist = playlistRepository.findById(id);
//        optionalPlaylist.ifPresent(playlist -> {
//            playlist.getSongs().add(song);
//            playlistRepository.save(playlist);
//        });
//    }
}
