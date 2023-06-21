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
import java.util.Date;
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
        try {
            List<PlaylistSong> playlistSongs = new ArrayList<>();
            for (Long songId : playlistRequest.getSongIds()) {
                Song song = new Song();
                song.setId(songId);

                PlaylistSong playlistSong = new PlaylistSong();
                playlistSong.setSong(song);
                playlistSong.setPlaylist(playlist);
                playlist.setIsActive(true);
                playlist.setCreatedDate(new Date());
                playlistSongs.add(playlistSong);
            }
            playlist.setPlaylistSongs(playlistSongs);
        } catch (Exception e) {
            System.out.println("Cannot create playlist");
        }
        return playlistRepository.save(playlist);
    }

    /****** Playlist Retrieval ******/
    public Playlist getPlaylistById(Long id) {
        try {
            return playlistRepository.getPlaylistById(id);
        } catch (Exception e) {
            System.err.println("Error retrieving playlist: " + e.getMessage());
            return null;
        }
    }

    /****** Playlist Update ******/
    @Transactional
    public Playlist updatePlaylist(Long id, PlaylistRequest playlistRequest) {
        Playlist playlist = playlistRepository.getPlaylistById(id);
        try {
            playlist.setName(playlistRequest.getName());

            List<Long> updatedSongIds = playlistRequest.getSongIds();
            if (updatedSongIds != null) {
                // Remove all existing songs from the playlist
                playlistRepository.deletePlaylistSongs(id);
                // Add new songs to the playlist
                List<PlaylistSong> newSongs = new ArrayList<>();
                for (Long songId : updatedSongIds) {
                    Song song = songRepository.getSongById(songId);
                    newSongs.add(new PlaylistSong(playlist, song));
                }
                playlist.setPlaylistSongs(newSongs);
            }
        } catch (Exception e) {
            System.out.println("Cannot update playlist: " + e.getMessage());
        }
        Playlist updatedPlaylist = playlistRepository.save(playlist);
        return updatedPlaylist;
    }

    /****** Playlist Deletion ******/
    @Transactional
    public void deletePlaylistAndSongsById(Long id) {
        try {
            playlistRepository.deletePlaylistAndSongsById(id);
            playlistRepository.deletePlaylistById(id);
        } catch (Exception e) {
            System.out.println("Cannot delete playlist: " + e.getMessage());
        }
    }

    /****** Song Addition ******/
    @Transactional
    public void addSongsToPlaylistById(Long playlistId, List<Long> songIds) {
        try {
            playlistRepository.addSongsToPlaylist(playlistId, songIds);
            Playlist playlist = playlistRepository.getPlaylistById(playlistId);
            playlist.setUpdatedDate(new Date());
        } catch (Exception e) {
            System.out.println("Songs cannot added successfully into playlist: " + e.getMessage());
        }
    }

    /****** Delete Songs by id ******/
    @Transactional
    public void deleteSongFromPlaylist(Long playlistId, Long songId) {
        try {
            playlistRepository.deleteSongFromPlaylist(playlistId, songId);
        } catch (Exception e) {
            System.out.println("Songs cannot delete: " + e.getMessage());
        }
    }


    /****** Search for Playlist by keyword ******/
    public List<Playlist> searchPlaylistsByKeyword(String keyword) {
        try {
            List<Playlist> playlists = playlistRepository.searchPlaylistsByKeyword(keyword);
            return playlists;
        } catch (Exception e) {
            System.out.println("Cannot search about playlist: " + e.getMessage());
        }
        return null;
    }
}
