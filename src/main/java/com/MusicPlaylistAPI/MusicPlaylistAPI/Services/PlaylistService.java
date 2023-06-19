package com.MusicPlaylistAPI.MusicPlaylistAPI.Services;

import com.MusicPlaylistAPI.MusicPlaylistAPI.Models.Playlist;
import com.MusicPlaylistAPI.MusicPlaylistAPI.Repositories.PlaylistRepository;
import com.MusicPlaylistAPI.MusicPlaylistAPI.RequestObject.PlaylistRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import javax.transaction.Transactional;
import java.util.List;


@Service
@Transactional
public class PlaylistService {
    @Autowired
    PlaylistRepository playlistRepository;

    /*******  Playlist Creation  ******/
    public void createPlaylist(PlaylistRequest playlistRequest) {
        Playlist playlist = PlaylistRequest.convert(playlistRequest);
        playlistRepository.save(playlist);
    }

    /****** Playlist Retrieval ******/
    public Playlist getPlaylistById(Long id) {
        return playlistRepository.getPlaylistById(id);
    }

    /****** Playlist Update ******/
    public void updatePlaylist(Long id, PlaylistRequest playlistRequest) {
        Playlist playlist = playlistRepository.getPlaylistById(id);
        if (playlist != null) {
            playlist.setName(playlistRequest.getName());
            playlist.setSongs(playlistRequest.getSongs());
            playlistRepository.save(playlist);
        }
    }

    /****** Playlist Deletion ******/
    public void deletePlaylistById(Long id) {
        playlistRepository.deletePlaylistById(id);
    }

    /****** Song Addition ******/
    public void addSongToPlaylist(Long id, List<String> songs) {
        Playlist playlist = playlistRepository.getPlaylistById(id);
        if (playlist != null) {
            List<String> existingSongs = playlist.getSongs();
            existingSongs.addAll(songs);
            playlistRepository.save(playlist);
        } else {
            throw new IllegalArgumentException("No playlist found with ID: " + id);
        }
    }
//    public Playlist getPlaylistById(Long id) {
//        Optional<Playlist> playlist = playlistRepository.findById(id);
//        return playlist.orElse(null);
//    }
//
//    public void updatePlaylist(Long id, Playlist playlist) {
//        playlistRepository.save(playlist);
//    }
}
