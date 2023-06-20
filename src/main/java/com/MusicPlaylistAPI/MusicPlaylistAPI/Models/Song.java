package com.MusicPlaylistAPI.MusicPlaylistAPI.Models;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@Data
@Entity
@Table(name = "songs")
public class Song extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;

    @OneToMany(mappedBy = "song", cascade = CascadeType.ALL)
    private List<PlaylistSong> playlistSongs;
}
