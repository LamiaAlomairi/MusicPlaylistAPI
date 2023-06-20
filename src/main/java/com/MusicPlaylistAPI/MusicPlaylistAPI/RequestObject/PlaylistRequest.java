package com.MusicPlaylistAPI.MusicPlaylistAPI.RequestObject;

import com.MusicPlaylistAPI.MusicPlaylistAPI.Models.Playlist;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@Data
public class PlaylistRequest {
    private String name;
    private List<Long> songIds;

    public static Playlist convert(PlaylistRequest request) {
        Date nowDate = new Date();
        Playlist playlist = new Playlist();

        playlist.setName(request.getName());
        playlist.setIsActive(true);
        playlist.setCreatedDate(nowDate);
        playlist.setUpdatedDate(nowDate);
        return playlist;
    }

    public static List<Playlist> convert(List<PlaylistRequest> requestList) {
        List<Playlist> playlists = new ArrayList<>();
        if (!requestList.isEmpty()) {
            for (PlaylistRequest playlistRequest : requestList) {
                playlists.add(convert(playlistRequest));
            }
        }
        return playlists;
    }
}
