package com.MusicPlaylistAPI.MusicPlaylistAPI.Services;

import com.MusicPlaylistAPI.MusicPlaylistAPI.Models.Playlist;
import com.MusicPlaylistAPI.MusicPlaylistAPI.Models.PlaylistSong;
import com.MusicPlaylistAPI.MusicPlaylistAPI.Models.Song;
import com.MusicPlaylistAPI.MusicPlaylistAPI.Repositories.PlaylistRepository;
import com.MusicPlaylistAPI.MusicPlaylistAPI.Repositories.SongRepository;
import com.MusicPlaylistAPI.MusicPlaylistAPI.RequestObject.PlaylistRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
@Service
@Transactional
public class PlaylistService {
    @Autowired
    PlaylistRepository playlistRepository;
    @Autowired
    SongRepository songRepository;

    /*******  Playlist Creation  ******/
    public Playlist createPlaylist(PlaylistRequest playlistRequest) {
        Playlist playlist = new Playlist();
        playlist.setName(playlistRequest.getName());
        List<PlaylistSong> playlistSongs = new ArrayList<>();
        for (Long songId : playlistRequest.getSongIds()) {
            Song song = new Song();
            song.setId(songId);
            PlaylistSong playlistSong = new PlaylistSong();
            playlistSong.setSong(song);
            playlistSong.setPlaylist(playlist);
            playlistSongs.add(playlistSong);
        }
        playlist.setPlaylistSongs(playlistSongs);
        return playlistRepository.save(playlist);
    }

    public List<Long> getSongIdsByPlaylist(Playlist playlist) {
        List<Long> songIds = new ArrayList<>();
        for (PlaylistSong playlistSong : playlist.getPlaylistSongs()) {
            songIds.add(playlistSong.getSong().getId());
        }
        return songIds;
    }

    /****** Playlist Retrieval ******/
    public Playlist getPlaylistById(Long id) {
        return playlistRepository.getPlaylistById(id);
    }

//    /****** Playlist Update ******/
//    public void updatePlaylist(Long id, PlaylistRequest playlistRequest) {
//        Playlist playlist = playlistRepository.getPlaylistById(id);
//        if (playlist != null) {
//            playlist.setName(playlistRequest.getName());
////            playlist.setSongs(playlistRequest.getSongs());
//            playlistRepository.save(playlist);
//        }
//    }
//
//    /****** Playlist Deletion ******/
//    public void deletePlaylistById(Long id) {
//        playlistRepository.deletePlaylistById(id);
//    }
//
//    /****** Song Addition ******/
////    public void addSongToPlaylist(Long id, List<Song> songs) {
////        Playlist playlist = playlistRepository.getPlaylistById(id);
////        if (playlist != null) {
////            List<Song> existingSongs = playlist.getSongs();
////            existingSongs.addAll(songs);
////            playlistRepository.save(playlist);
////        } else {
////            throw new IllegalArgumentException("No playlist found with ID: " + id);
////        }
////    }
//
//    /****** Search for Playlist by keyword ******/
//    public List<Playlist> searchPlaylistsByKeyword(String keyword) {
//        return playlistRepository.searchPlaylistsByKeyword(keyword);
//    }
//
//    /****** Delete Songs by id ******/
//    public void deleteSongFromPlaylist(Long playlistId, List<String> songIds) {
//        Playlist playlist = playlistRepository.getOne(playlistId);
//        if (playlist != null) {
//            List<String> songs = playlist.getSongs();
//            songs.removeAll(songIds);
//            playlist.setSongs(songs);
//            playlistRepository.save(playlist);
//        } else {
//            throw new IllegalArgumentException("No playlist found with ID: " + playlistId);
//        }
//    }
//    public void deleteSongFromPlaylist(Long playlistId, List<String> songIds) {
//        Playlist playlist = playlistRepository.getPlaylistById(playlistId);
//        if (playlist != null) {
//            List<String> songs = playlist.getSongs();
//            songs.removeAll(songIds);
//            playlist.setSongs(songs);
//            playlistRepository.save(playlist);
//        } else {
//            throw new IllegalArgumentException("No playlist found with ID: " + playlistId);
//        }
//    }
//    @Transactional
//    public void deleteSongFromPlaylist(Long playlistId, List<String> songIds) {
//        Playlist playlist = playlistRepository.getPlaylistById(playlistId);
//        if (playlist != null) {
//            List<Song> songs = playlist.getSongs();
//            songs.removeAll(songIds);
//            playlistRepository.save(playlist);
//        } else {
//            throw new IllegalArgumentException("No playlist found with ID: " + playlistId);
//        }
//    }

}
