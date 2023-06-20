package com.MusicPlaylistAPI.MusicPlaylistAPI.Repositories;

import com.MusicPlaylistAPI.MusicPlaylistAPI.Models.Playlist;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PlaylistRepository extends JpaRepository<Playlist, Long> {
    @Query("SELECT p FROM Playlist p JOIN FETCH p.playlistSongs ps JOIN FETCH ps.song WHERE p.id = :id")
    Playlist getPlaylistById(@Param("id") Long id);
//    @Modifying
//    @Query(value = "DELETE FROM Playlist pl WHERE pl.id = :id")
//    void deletePlaylistById(@Param("id") Long id);
//
//    @Query("SELECT pl FROM Playlist pl JOIN pl.songs song WHERE song LIKE %:keyword% OR pl.name LIKE %:keyword%")
//    List<Playlist> searchPlaylistsByKeyword(@Param("keyword") String keyword);
//
//    @Modifying
//    @Query("UPDATE Playlist pl SET pl.songs = :songs WHERE pl.id = :playlistId")
//    void deleteSongFromPlaylist(@Param("playlistId") Long playlistId, @Param("songs") List<String> songs);
}
