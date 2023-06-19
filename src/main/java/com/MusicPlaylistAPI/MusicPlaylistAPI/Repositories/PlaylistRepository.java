package com.MusicPlaylistAPI.MusicPlaylistAPI.Repositories;

import com.MusicPlaylistAPI.MusicPlaylistAPI.Models.Playlist;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PlaylistRepository extends JpaRepository<Playlist, Long> {
}
