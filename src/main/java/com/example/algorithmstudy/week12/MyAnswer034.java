package com.example.algorithmstudy.week12;
import java.util.*;

//각 열차별 조건만족하는 인원만 남기고 거기서 최대 인원수 기준 최대명수를 찾은다음 답에 더해나가면 정답 나옴
//n은 역의 개수이고 trans는 순서대로 0번기차의 출발역,도착역,최대탑승인원이고 bookings 는 0번손님부터 승차역, 하차역이다
public class MyAnswer034 {
    //풀다가 던짐 위에 풀이보다 생각해야할게 더 있었음
    public int solutionFail(int n, int[][] trans, int[][] bookings){
        int totalPassengers = 0;
        Set<Integer> boardedPassengers = new HashSet<>(); // 이미 탄 승객을 저장

        for (int[] train : trans) {
            int start = train[0], end = train[1], capacity = train[2];
            int passengersTaken = 0, maxPassengers = 0;

            List<int[]> validBookings = new ArrayList<>();
            for (int i = 0; i < bookings.length; i++) {
                int board = bookings[i][0], alight = bookings[i][1];
                if (start <= board && alight <= end && !boardedPassengers.contains(i)) {
                    validBookings.add(new int[]{board, alight, i}); // i: 승객 ID 저장
                }
            }

            validBookings.sort(Comparator.comparingInt(a -> a[1]));

            PriorityQueue<Integer> dropOffQueue = new PriorityQueue<>();

            for (int[] booking : validBookings) {
                int board = booking[0], alight = booking[1], passengerId = booking[2];

                while (!dropOffQueue.isEmpty() && dropOffQueue.peek() <= board) {
                    dropOffQueue.poll();
                    capacity++;
                }

                if (capacity > 0) {
                    passengersTaken++;
                    capacity--;
                    dropOffQueue.add(alight);
                    boardedPassengers.add(passengerId);
                }

                maxPassengers = Math.max(maxPassengers, passengersTaken);
            }

//            System.out.println(Arrays.deepToString(validBookings.toArray()));
//            System.out.println(passengersTaken);
//            System.out.println("기차(" + start + " → " + end + "), 최대 탑승 가능 인원: " + maxPassengers);

            totalPassengers += maxPassengers;
        }

        return totalPassengers;
    }

    //정답코드
    public int solution(int n, int[][] trans, int[][] bookings){
        int answer=0;
        int[] sum = new int[n+1];
        for(int[] x : trans){
            sum[x[0]] += x[2];
            sum[x[1]] -= x[2];
        }
        for(int i = 1; i <= n; i++){
            sum[i] += sum[i-1];
        }
        int bN = bookings.length;
        Arrays.sort(bookings, (a, b) -> a[0] - b[0]);
        LinkedList<Integer> nums = new LinkedList<>();
        int ix = 0;
        for(int i = 1; i <= n; i++){
            while(!nums.isEmpty() && nums.peek() == i){
                answer++;
                nums.pollFirst();
            }
            while(ix < bN && bookings[ix][0] == i){
                nums.add(bookings[ix][1]);
                ix++;
            }
            Collections.sort(nums);
            while(nums.size() > sum[i]){
                nums.pollLast();
            }
        }
        return answer;
    }

    public static void main(String[] args){
        MyAnswer034 T = new MyAnswer034();
        System.out.println(T.solution(5, new int[][]{{1, 4, 2}, {2, 5, 1}}, new int[][]{{1, 2}, {1, 5}, {2, 3}, {2, 4}, {2, 5}, {2, 5}, {3, 5}, {3, 4}}));
        System.out.println(T.solution(5, new int[][]{{2, 3, 1}, {1, 5, 1}}, new int[][]{{2, 5}, {1, 5}, {1, 3}, {2, 4}, {2, 5}, {2, 3}}));
        System.out.println(T.solution(8, new int[][]{{1, 8, 3}, {3, 8, 1}}, new int[][]{{1, 3}, {5, 8}, {2, 7}, {3, 8}, {2, 7}, {2, 8}, {3, 8}, {6, 8}, {7, 8}, {5, 8}, {2, 5}, {2, 7}, {3, 7}, {3, 8}}));
        System.out.println(T.solution(9, new int[][]{{1, 8, 3}, {3, 9, 2}, {1, 5, 3}}, new int[][]{{1, 9}, {5, 8}, {2, 9}, {3, 8}, {2, 9}, {1, 9}, {8, 9}, {3, 9}, {1, 8}, {6, 8}, {7, 8}, {5, 8}, {3, 5}, {3, 7}, {4, 7}, {5, 8}}));
        System.out.println(T.solution(9, new int[][]{{2, 7, 2}, {3, 9, 2}, {1, 5, 3}}, new int[][]{{1, 9}, {4, 8}, {2, 9}, {5, 9}, {3, 8}, {2, 9}, {1, 9}, {8, 9}, {3, 9}, {1, 8}, {6, 8}, {3, 6}, {7, 8}, {5, 8}, {3, 5}, {2, 7}, {1, 7}, {2, 8}}));
    }
}
