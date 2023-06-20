package com.MusicPlaylistAPI.MusicPlaylistAPI.Repositories;

import com.MusicPlaylistAPI.MusicPlaylistAPI.Models.Song;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import java.util.List;

public interface SongRepository extends JpaRepository<Song, Long> {
    @Query(value = "select s from Song s")
    List<Song> getAllSongs();
}
