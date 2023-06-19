package com.MusicPlaylistAPI.MusicPlaylistAPI.RequestObject;

import com.MusicPlaylistAPI.MusicPlaylistAPI.Models.Playlist;
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

    public static Playlist convert(PlaylistRequest request) {
        Playlist playlist = new Playlist();

        playlist.setName(request.getName());
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
