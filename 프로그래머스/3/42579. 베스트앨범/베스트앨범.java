import java.util.Map;
import java.util.HashMap;
import java.util.ArrayList;
import java.util.List;
import java.util.Collections;

class Solution {
    public int[] solution(String[] genres, int[] plays) {
        List<Integer> answer = new ArrayList<>();
        // 속한 노래가 많이 재생된 장르 --> 장르별 재생횟수 알아야함
        // 장르 내에서 많이 재생된 노래 --> 장르 내 재생횟수 알아야함
        // 장르 내 같은 재생 횟수라면, 고유 번호가 낮은 노래 --> 노래 고유 번호 알아야함
        
        Map<String, Integer> genrePlays = new HashMap<>(); // <장르, 장르별 재생횟수> map 하나
        Map<String, List<Song>> genreMostPlays = new HashMap<>(); // <장르, 많이 재생된 노래고유번호 2개 담는 리스트> map 하나
        // Map<Integer, Integer> songPlays = new HashMap<>(); // 2번 map에 속해있는 <고유번호, 재생횟수> map 하나
        
        final int MAX = 2;
        
        for(int i = 0; i < genres.length; i++) {
            genrePlays.put(genres[i], genrePlays.getOrDefault(genres[i], 0) + plays[i]);
            List<Song> list = genreMostPlays.get(genres[i]) == null ? new ArrayList<Song>() : genreMostPlays.get(genres[i]);
            if(list.size() == MAX) {
                Collections.sort(list);
                for(int j = 0; j < MAX; j++) {
                    Song song = list.get(j);
                    if(song.plays < plays[i]) {
                        list.add(j, new Song(i, plays[i]));
                        list.remove(MAX);
                        break;
                    }
                }
            } else if(list.size() == 1) {
                Song song = list.get(0);
                if(song.plays < plays[i]) {
                    list.add(0, new Song(i, plays[i]));
                } else list.add(new Song(i, plays[i]));
            } else list.add(new Song(i, plays[i]));
                
            genreMostPlays.put(genres[i], list);
        }
 
        List<Genre> list = new ArrayList<>();
        for(Map.Entry<String, Integer> entry: genrePlays.entrySet()) {
            list.add(new Genre(entry.getKey(), entry.getValue()));
        }
        Collections.sort(list);
        
        for(Genre genre: list) {
            for(Song song: genreMostPlays.get(genre.name)) {
                answer.add(song.id);
            }
        }
    
        return answer.stream().mapToInt(i -> i).toArray();
        }

    public class Song implements Comparable<Song>{
        int id; // 고유번호
        int plays; // 재생횟수
        
        public Song(int id, int plays) {
            this.id = id;
            this.plays = plays;
        }
        
        @Override
        public int compareTo(Song o) {
            if(this.plays > o.plays) return -1;
            else if(this.plays == o.plays) {
                if(this.id < o.id) return -1;
            }
            return 1;
        }
    }
    public class Genre implements Comparable<Genre>{
        String name; // 장르이름
        int plays; // 재생횟수
        
        public Genre(String name, int plays) {
            this.name = name;
            this.plays = plays;
        }
        
        @Override
        public int compareTo(Genre o) {
            if(this.plays > o.plays) return -1;
            return 1;
        }
    }
}
                                             