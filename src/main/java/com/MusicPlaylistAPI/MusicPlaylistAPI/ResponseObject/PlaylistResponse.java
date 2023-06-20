package com.MusicPlaylistAPI.MusicPlaylistAPI.ResponseObject;

import com.MusicPlaylistAPI.MusicPlaylistAPI.Models.Playlist;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
@Data
@Builder
public class PlaylistResponse {
    private Long id;
    private String name;
    private List<String> songTitles;

    public static PlaylistResponse convertToResponse(Playlist playlist) {
        List<String> songTitles = playlist.getPlaylistSongs().stream()
                .map(ps -> ps.getSong().getTitle())
                .collect(Collectors.toList());

        return PlaylistResponse.builder()
                .id(playlist.getId())
                .name(playlist.getName())
                .songTitles(songTitles)
                .build();
    }

    public static List<PlaylistResponse> convertToResponseList(List<Playlist> response) {
        List<PlaylistResponse> playlistResponse = new ArrayList<>();
        if (!response.isEmpty()) {
            for (Playlist playlist : response) {
                playlistResponse.add(convertToResponse(playlist));
            }
        }
        return playlistResponse;
    }
}
