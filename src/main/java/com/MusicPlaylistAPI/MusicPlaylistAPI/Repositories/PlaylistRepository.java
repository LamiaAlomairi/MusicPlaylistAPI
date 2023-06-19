package com.MusicPlaylistAPI.MusicPlaylistAPI.Repositories;

import com.MusicPlaylistAPI.MusicPlaylistAPI.Models.Playlist;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PlaylistRepository extends JpaRepository<Playlist, Long> {
//    @Query(value = "UPDATE Playlist pl SET pl.songs = :songs WHERE pl.id = :id")
//    void addSong();
}
