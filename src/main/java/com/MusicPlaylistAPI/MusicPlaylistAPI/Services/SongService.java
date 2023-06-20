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
        Song song = SongRequest.convert(songRequest);
        songRepository.save(song);
    }

    /*******  Get All Song  ******/
    public List<Song> getAllSongs() {
        return songRepository.getAllSongs();
    }
}
