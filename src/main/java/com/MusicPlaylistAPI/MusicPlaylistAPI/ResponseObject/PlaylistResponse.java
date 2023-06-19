package com.MusicPlaylistAPI.MusicPlaylistAPI.ResponseObject;

import com.MusicPlaylistAPI.MusicPlaylistAPI.Models.Playlist;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Data
@Builder
public class PlaylistResponse {
    private Long id;
    private String name;

    public static PlaylistResponse convertToResponse(Playlist playlist) {
        return PlaylistResponse.builder()
                .id(playlist.getId())
                .name(playlist.getName())
                .build();
    }

    public static List<PlaylistResponse> convertToResponseList(List<Playlist> response) {
        List<PlaylistResponse> playlistResponse= new ArrayList<>();
        if(!response.isEmpty()){
            for (Playlist playlist : response) {
                playlistResponse.add(convertToResponse(playlist));
            }
        }
        return playlistResponse;
    }
}
