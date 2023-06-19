package com.MusicPlaylistAPI.MusicPlaylistAPI.ResponseObject;

import com.MusicPlaylistAPI.MusicPlaylistAPI.Models.Song;
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
public class SongResponse {
    private Long id;
    private String title;

    public static SongResponse convertToResponse(Song song) {
        return SongResponse.builder()
                .id(song.getId())
                .title(song.getTitle())
                .build();
    }

    public static List<SongResponse> convertToResponseList(List<Song> response) {
        List<SongResponse> songResponses= new ArrayList<>();
        if(!response.isEmpty()){
            for (Song song : response) {
                songResponses.add(convertToResponse(song));
            }
        }
        return songResponses;
    }
}
