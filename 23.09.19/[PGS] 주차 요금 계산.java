import java.util.*;

// PG L2. 삼각 달팽이
/*
 * 23:30 ~ 23:50
 * 
 * 배열이 채워지는 순서는 아래, 오른쪽, 왼쪽 대각선 순
 * 이 순서를 지키되 배열 범위 밖으로 나가면 안됨
 * 
 * 아래와 오른쪽이동에 배열 범위 밖으로 나가는지 확인해주고
 * 왼쪽 대각선 이동시 요소가 0일때 값을 넣는다
 * 
 * 위 조건으로 인덱스가 0미만으로 내려가는지 확인할 필요가 없어짐
 * => 시작은 (0, 0) 이므로 이미 0이 아닌값으로 바뀜
 */
class Solution {
    private class Cost {
        int defaultTime;
        int defaultCost;
        int segTime;
        int segCost;

        public Cost(int defaultTime, int defaultCost, int segTime, int segCost) {
            this.defaultTime = defaultTime;
            this.defaultCost = defaultCost;
            this.segTime = segTime;
            this.segCost = segCost;
        }

        public int calculate(int time) {
            int fee = defaultCost;
            time -= defaultTime;

            while (time > 0) {
                fee += segCost;
                time -= segTime;
            }

            return fee;
        }
    }

    private class Car {
        String number;
        Cost cost;
        int in = -1;
        int stay = 0;

        public Car(String number, Cost cost) {
            this.number = number;
            this.cost = cost;
        }

        public void out(int time) {
            if (this.in == -1)
                return;
            stay += time - this.in;
            this.in = -1;
        }

        public int money() {
            return cost.calculate(stay);
        }

        public String carName() {
            return this.number;
        }
    }

    public int[] solution(int[] fees, String[] records) {
        Cost cost = new Cost(fees[0], fees[1], fees[2], fees[3]);
        Map<String, Car> store = new HashMap<>();
        int[] answer = {};

        for (String record : records) {
            String[] sep = record.split(" ");

            int minute = time(sep[0]);
            String carTag = sep[1];
            boolean isIn = sep[2].equals("IN");

            if (!store.containsKey(carTag)) {
                store.put(carTag, new Car(carTag, cost));
            }

            Car car = store.get(carTag);
            if (isIn) {
                car.in = minute;
            } else {
                car.out(minute);
            }
        }

        int close = time("23:59");
        for (Car leave : store.values()) {
            leave.out(close);
        }
        return store.values().stream()
                .sorted(Comparator.comparing(Car::carName))
                .mapToInt(Car::money).toArray();
    }

    private int time(String t) {
        int h = Integer.parseInt(t.substring(0, 2));
        int m = Integer.parseInt(t.substring(3));
        return h * 60 + m;
    }
}