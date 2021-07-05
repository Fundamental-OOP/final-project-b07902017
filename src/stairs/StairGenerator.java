package stairs;

import java.util.List;
import java.util.Random;
import java.util.concurrent.CopyOnWriteArrayList;
import java.awt.*;

public class StairGenerator {
    private final List<Stair> candidates;
    private List<Integer> numbers;
    private Random r1 = new Random(System.currentTimeMillis());

    public StairGenerator(int[] pos) { // pos: the probability distribution of six kinds of stairs
        this.candidates = new CopyOnWriteArrayList<>();
        candidates.add(new NormalStair(null, 0));
        candidates.add(new Nails(null, 0));
        candidates.add(new Trampoline(null, 0));
        candidates.add(new Fake(null, 0));
        candidates.add(new Conveyor(null, 0, 1));
        candidates.add(new Conveyor(null, 0, -1));
        setpossibility(pos);
    }

    public void setpossibility(int a[]){
        this.numbers = new CopyOnWriteArrayList<>();
        assert(a.length == 6);
        for(int i=0; i<6; i++){
            for(int j=0; j<a[i]; j++) 
                numbers.add(i);
        }
    }

    public Stair getStair(Point point){
        int idx = numbers.get(r1.nextInt(numbers.size()));
        return candidates.get(idx).makeNew(point);
    }
}
