package com.MusicPlaylistAPI.MusicPlaylistAPI.Services;

import com.MusicPlaylistAPI.MusicPlaylistAPI.Models.Song;
import com.MusicPlaylistAPI.MusicPlaylistAPI.Repositories.SongRepository;
import com.MusicPlaylistAPI.MusicPlaylistAPI.RequestObject.SongRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SongService {
    @Autowired
    SongRepository songRepository;

    /*******  Song Addition  ******/
    public void addSong(SongRequest songRequest) {
        try {
            Song song = SongRequest.convert(songRequest);
            songRepository.save(song);
        } catch (Exception e) {
            System.out.println("Cannot add song " + e.getMessage());
        }
    }

    /*******  Get All Song  ******/
    public List<Song> getAllSongs() {
        try {
            return songRepository.getAllSongs();
        } catch (Exception e) {
            System.out.println("Cannot get all songs " + e.getMessage());
            return null;
        }
    }
}
