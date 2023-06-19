package com.MusicPlaylistAPI.MusicPlaylistAPI.RequestObject;

import com.MusicPlaylistAPI.MusicPlaylistAPI.Models.Playlist;
import com.MusicPlaylistAPI.MusicPlaylistAPI.Models.Song;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Data
public class PlaylistRequest {
    private String name;
    private List<String> songs;

    public static Playlist convert(PlaylistRequest request) {
        Playlist playlist = new Playlist();

        playlist.setName(request.getName());
        playlist.setSongs(request.getSongs());
        return playlist;
    }

    public static List<Playlist> convert(List<PlaylistRequest> requestList) {
        List<Playlist> playlists = new ArrayList<>();
        if(!requestList.isEmpty()){
            for (PlaylistRequest playlistRequest : requestList) {
                playlists.add(convert(playlistRequest));
            }
        }
        return playlists;
    }
}
