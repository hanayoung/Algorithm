
import java.awt.Point;
import java.util.HashSet;

class Solution {
    public int solution(String dirs) {
        int answer = 0;

        String[] dirsArr = dirs.split("");

        HashSet<StartToEnd> set = new HashSet<>();
        int cx = 0;
        int cy = 0;
        int nx = 0;
        int ny = 0;
        for (String dir : dirsArr) {
            switch(dir) {
                case "L":
                    int tmp = ny;
                    ny = cy - 1;
                    if(ny < -5) {
                       ny = tmp; 
                    }else{
                        if(set.contains(new StartToEnd(new Point(cx, cy), new Point(nx, ny))) == false && set.contains(new StartToEnd(new Point(nx, ny), new Point(cx, cy))) == false) {
                            set.add(new StartToEnd(new Point(cx, cy), new Point(nx, ny)));
                        }
                        cy = ny;
                    }
                break;
                case "R":
                    tmp = ny;
                    ny = cy + 1;
                    if(ny > 5) {
                        ny = tmp; 
                    }else{
                        if(set.contains(new StartToEnd(new Point(cx, cy), new Point(nx, ny))) == false && set.contains(new StartToEnd(new Point(nx, ny), new Point(cx, cy))) == false) {
                            set.add(new StartToEnd(new Point(cx, cy), new Point(nx, ny)));
                        }
                        cy = ny;
                    }
                break;
                case "U":
                    tmp = nx;
                    nx = cx - 1;
                    if(nx < -5) {
                        nx = tmp; 
                    }else{
                        if(set.contains(new StartToEnd(new Point(cx, cy), new Point(nx, ny))) == false && set.contains(new StartToEnd(new Point(nx, ny), new Point(cx, cy))) == false) {
                            set.add(new StartToEnd(new Point(cx, cy), new Point(nx, ny)));
                        }
                        cx = nx;
                    }
                break;
                case "D":
                    tmp = nx;
                    nx = cx + 1;
                    if(nx > 5) {
                        nx = tmp; 
                    }else{
                        if(set.contains(new StartToEnd(new Point(cx, cy), new Point(nx, ny))) == false && set.contains(new StartToEnd(new Point(nx, ny), new Point(cx, cy))) == false) {
                            set.add(new StartToEnd(new Point(cx, cy), new Point(nx, ny)));
                        }
                        cx = nx;
                    }
                break;
            }
        }
        answer = set.size();
        return answer;
    }

    static class StartToEnd {
        Point start;
        Point end;
    
        public StartToEnd(Point start, Point end) {
            this.start = start;
            this.end = end;
        }
    
        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            StartToEnd that = (StartToEnd) o;
            return (start.equals(that.start) && end.equals(that.end)) ||
                   (start.equals(that.end) && end.equals(that.start));
        }
    
        @Override
        public int hashCode() {
            return start.hashCode() + end.hashCode();
        }
    } 
}