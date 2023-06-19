package com.MusicPlaylistAPI.MusicPlaylistAPI.RequestObject;

import com.MusicPlaylistAPI.MusicPlaylistAPI.Models.Song;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Data
public class SongRequest {
    private String title;

    public static Song convert(SongRequest request) {
        Song song = new Song();

        song.setTitle(request.getTitle());
        return song;
    }

    public static List<Song> convert(List<SongRequest> requestList) {
        List<Song> songs = new ArrayList<>();
        if(!requestList.isEmpty()){
            for (SongRequest songRequest : requestList) {
                songs.add(convert(songRequest));
            }
        }
        return songs;
    }
}
