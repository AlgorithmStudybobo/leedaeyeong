import java.util.*;
// PGS L2. 주차 요금 계산
/*
 * 문제의 요구사항대로 구현함
 * 1. fees배열을 객체로 변환하여 접근을 용이하게 하고 method를 통해 출차시 요금을 계산해줌
 * 2. 입, 출차 관리 -> 객체와 Map구조를 통해 관리함 차가 들어오면 시간을 저장하고 출차시 시간을 계산함
 * 3. 정산 -> 맵에서 모든 value를 가져와 요금 계산 method를 통해 최종 정산
 * 4. stream을 통해 요구사항에 맞게 출력값으로 바뀜
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