package com.example.algorithmstudy.week12;

import java.util.*;

// 최대 인원 수 (예제 5번 왜 답이 7인지 설명 좀 해주실 분 구합니다. 왜 7인지 이해를 못해서 여기서 더 이상 수정을 못하겠어요.)
// 근데 그냥 설명해주지 마세요. 알고 싶지 않아요.
class Solution34 {
    public int solution34(int n, int[][] trans, int[][] bookings) {
        // 기차 정보 저장
        List<Train> trains = new ArrayList<>();
        for (int i = 0; i < trans.length; i++) {
            trains.add(new Train(trans[i][0], trans[i][1], trans[i][2]));
        }

        // 출발역 기준으로 예약 저장
        Map<Integer, PriorityQueue<Integer>> stationBookings = new HashMap<>();
        for (int i = 1; i <= n; i++) {
            stationBookings.put(i, new PriorityQueue<>()); // 목적지가 가까운 순으로 정렬
        }
        for (int[] booking : bookings) {
            stationBookings.get(booking[0]).offer(booking[1]); // 출발역 -> 도착역 저장
        }

        // 현재 기차에 탑승 중인 어린이 정보
        Map<Integer, Queue<Integer>> info = new HashMap<>();
        for (int i = 0; i < trains.size(); i++) {
            info.put(i, new LinkedList<>());
        }

        int maxBookedChildren = 0;

        // 역을 1번부터 n번까지 순회하며 어린이들을 태우고 내림
        for (int station = 1; station <= n; station++) {
            // 현재 역에서 내려야 할 어린이들을 내림
            for (int trainId = 0; trainId < trains.size(); trainId++) {
                Queue<Integer> inTrain = info.get(trainId);
                Queue<Integer> newQueue = new LinkedList<>();

                while (!inTrain.isEmpty()) {
                    int destination = inTrain.poll();
                    if (destination != station) {
                        newQueue.offer(destination);
                    } else {
                        trains.get(trainId).current--; // 좌석이 비워짐
                    }
                }
                info.put(trainId, newQueue);
            }

            // 현재 역에서 태울 수 있는 어린이들을 기차에 태움 (목적지가 가까운 애들부터)
            PriorityQueue<Integer> requests = stationBookings.get(station);
            while (!requests.isEmpty()) {
                int dest = requests.poll(); // 가장 가까운 목적지부터 꺼냄

                // 탈 수 있는 기차를 찾음
                for (int trainId = 0; trainId < trains.size(); trainId++) {
                    Train train = trains.get(trainId);
                    if (train.start <= station && train.end >= dest && train.current < train.maxPeople) {
                        train.current++; // 좌석 사용
                        info.get(trainId).offer(dest);
                        maxBookedChildren++;
                        break; // 한 어린이는 한 기차에만 탈 수 있음
                    }
                }
            }
        }

        return maxBookedChildren;
    }

    static class Train {
        int start, end, maxPeople, current;

        Train(int start, int end, int maxPeople) {
            this.start = start;
            this.end = end;
            this.maxPeople = maxPeople;
            this.current = 0; // 현재 기차에 탑승한 어린이 수
        }
    }

    public static void main(String[] args) {
        Solution34 T = new Solution34();
        System.out.println(T.solution34(5, new int[][]{{1, 4, 2}, {2, 5, 1}}, new int[][]{{1, 2}, {1, 5}, {2, 3}, {2, 4}, {2, 5}, {2, 5}, {3, 5}, {3, 4}}));
        System.out.println(T.solution34(5, new int[][]{{2, 3, 1}, {1, 5, 1}}, new int[][]{{2, 5}, {1, 5}, {1, 3}, {2, 4}, {2, 5}, {2, 3}}));
        System.out.println(T.solution34(8, new int[][]{{1, 8, 3}, {3, 8, 1}}, new int[][]{{1, 3}, {5, 8}, {2, 7}, {3, 8}, {2, 7}, {2, 8}, {3, 8}, {6, 8}, {7, 8}, {5, 8}, {2, 5}, {2, 7}, {3, 7}, {3, 8}}));
        System.out.println(T.solution34(9, new int[][]{{1, 8, 3}, {3, 9, 2}, {1, 5, 3}}, new int[][]{{1, 9}, {5, 8}, {2, 9}, {3, 8}, {2, 9}, {1, 9}, {8, 9}, {3, 9}, {1, 8}, {6, 8}, {7, 8}, {5, 8}, {3, 5}, {3, 7}, {4, 7}, {5, 8}}));
        System.out.println(T.solution34(9, new int[][]{{2, 7, 2}, {3, 9, 2}, {1, 5, 3}}, new int[][]{{1, 9}, {4, 8}, {2, 9}, {5, 9}, {3, 8}, {2, 9}, {1, 9}, {8, 9}, {3, 9}, {1, 8}, {6, 8}, {3, 6}, {7, 8}, {5, 8}, {3, 5}, {2, 7}, {1, 7}, {2, 8}}));
    }
}
