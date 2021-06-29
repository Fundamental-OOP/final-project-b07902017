package stairs;

import java.util.List;
import java.util.Random;
import java.util.concurrent.CopyOnWriteArrayList;
import java.awt.*;

public class StairGenerator {
    private final List<Stair> candidates;
    private List<Integer> numbers;
    private Random r1 = new Random(0);
    
    public StairGenerator(List<Stair> stairs){
        this.candidates = stairs;
        int[] pos = {5,1,1,1,1,1};
        this.numbers = setpossibility(pos);
    }

    public static List<Integer> setpossibility(int a[]){
        List<Integer> numbers = new CopyOnWriteArrayList<>();
        assert(a.length == 6);
        for(int i=0; i<6; i++){
            for(int j=0; j<a[i]; j++) 
                numbers.add(i);
        }
        return numbers;
    }

    public Stair getStair(Point point){
        int idx = numbers.get(r1.nextInt(numbers.size()));
        return candidates.get(idx).makeNew(point);
    }
}
