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
import java.util.stream.Collectors;

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

    /****** Playlist Update ******/
    @Transactional
    public Playlist updatePlaylist(Long id, PlaylistRequest playlistRequestDTO) {
        Playlist existingPlaylist = playlistRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Playlist not found with id: " + id));

        existingPlaylist.setName(playlistRequestDTO.getName());
        List<Long> updatedSongIds = playlistRequestDTO.getSongIds();
        if (updatedSongIds != null) {
            // Remove all existing songs from the playlist
            playlistRepository.deletePlaylistSongs(id);
            // Add new songs to the playlist
            List<PlaylistSong> newSongs = updatedSongIds.stream()
                    .map(songId -> {
                        Song song = songRepository.findById(songId)
                                .orElseThrow(() -> new IllegalArgumentException("Song not found with id: " + songId));
                        return new PlaylistSong(existingPlaylist, song);
                    })
                    .collect(Collectors.toList());
            existingPlaylist.setPlaylistSongs(newSongs);
        }
        Playlist updatedPlaylist = playlistRepository.save(existingPlaylist);
        return updatedPlaylist;
    }

    /****** Playlist Deletion ******/
    @Transactional
    public void deletePlaylistAndSongsById(Long id) {
        playlistRepository.deletePlaylistAndSongsById(id);
        playlistRepository.deletePlaylistById(id);
    }

    /****** Song Addition ******/
    @Transactional
    public void addSongsToPlaylistById(Long playlistId, List<Long> songIds) {
        playlistRepository.addSongsToPlaylist(playlistId, songIds);
        Playlist playlist = playlistRepository.getPlaylistById(playlistId);
        playlist.setUpdatedDate(new Date());
    }

    /****** Delete Songs by id ******/
    @Transactional
    public void deleteSongFromPlaylist(Long playlistId, Long songId) {
        playlistRepository.deleteSongFromPlaylist(playlistId, songId);
    }


    /****** Search for Playlist by keyword ******/
    public List<Playlist> searchPlaylistsByKeyword(String keyword) {
        List<Playlist> playlists = playlistRepository.searchPlaylistsByKeyword(keyword);
        return playlists;
    }
}
