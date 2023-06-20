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
    //********* Get Playlist By Id    *********
    @Query("SELECT p FROM Playlist p JOIN FETCH p.playlistSongs ps JOIN FETCH ps.song WHERE p.id = :id")
    Playlist getPlaylistById(@Param("id") Long id);


    //********* User To update Playlist *********
    @Modifying
    @Query("UPDATE Playlist p SET p.name = :name WHERE p.id = :id")
    void updatePlaylistName(@Param("id") Long id, @Param("name") String name);

    @Modifying
    @Query("DELETE FROM PlaylistSong ps WHERE ps.playlist.id = :playlistId")
    void deletePlaylistSongs(Long playlistId);

    @Modifying
    @Query("INSERT INTO PlaylistSong (playlist, song) SELECT p, s FROM Playlist p, Song s WHERE p.id = :playlistId AND s.id = :songId")
    void addSongToPlaylist(@Param("playlistId") Long playlistId, @Param("songId") Long songId);


    //******** Delete Playlist ********
    @Modifying
    @Query("DELETE FROM PlaylistSong ps WHERE ps.playlist.id = :id")
    void deletePlaylistAndSongsById(@Param("id") Long id);

    @Modifying
    @Query("DELETE FROM Playlist p WHERE p.id = :id")
    void deletePlaylistById(@Param("id") Long id);


    //******** Add new song into playlist ********
    @Modifying
    @Query("INSERT INTO PlaylistSong (playlist, song) SELECT p, s FROM Playlist p, Song s WHERE p.id = :playlistId AND s.id IN :songIds")
    void addSongsToPlaylist(@Param("playlistId") Long playlistId, @Param("songIds") List<Long> songIds);


    //******** Delete Songs by id ********
    @Modifying
    @Query("DELETE FROM PlaylistSong ps WHERE ps.playlist.id = :playlistId AND ps.song.id = :songId")
    void deleteSongFromPlaylist(@Param("playlistId") Long playlistId, @Param("songId") Long songId);


    //******** Search About Playlist by keyword ********
    @Query("SELECT DISTINCT p FROM Playlist p LEFT JOIN FETCH p.playlistSongs ps LEFT JOIN FETCH ps.song s WHERE p.name LIKE %:keyword% OR s.title LIKE %:keyword%")
    List<Playlist> searchPlaylistsByKeyword(@Param("keyword") String keyword);
}
