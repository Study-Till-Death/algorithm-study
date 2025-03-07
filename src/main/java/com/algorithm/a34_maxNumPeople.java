package com.algorithm;

import java.util.*;

public class a34_maxNumPeople {
    public int solution(int n, int[][] trains, int[][] bookings) {
        // 1-n번역 사이를 운행
        // 기차별 각 운행하는 구역이 있음
        // 어린이들 예약에 승차역 하차역 ㅇ
        // 모든 기차는 승차와 하차 동시에 일어남

        // 풀다가 심란해서 포기했어요
        int answer = 0;
        List<Train> trainList = new ArrayList<>();
        List<Book> bookList = new ArrayList<>();

        for (int[] train : trains) {
            trainList.add(new Train(train[0], train[1], train[2]));
        }

        for (int[] book : bookings) {
            bookList.add(new Book(book[0], book[1], 0));
        }

        // 빨리 내리는 기준으로 소트
        bookList.sort(Comparator.comparingInt((Book o) -> o.end - o.start).thenComparingInt(o -> o.start));
        trainList.sort(Comparator.comparingInt((Train o) -> o.start).thenComparing(Comparator.comparingInt((Train o) -> o.end).reversed()));

        for (Book booking : bookList) {
            updateBooking(bookList,booking, trainList); // 예약을 돌면서 현재 승차할 역에서 하차시킬 어린이 찾음 혹은 이전에 하차했어야 할 어린이.
            if (checkTrains(bookList,trainList,booking)) {
                answer++;
            }
        }

        return answer;
    }

    private boolean checkTrains(List<Book> bookList, List<Train> trainList, Book booking) {
        int start = booking.start;
        int end = booking.end;
        for (int i = 0; i < trainList.size(); i++) {
            Train train = trainList.get(i);
            if (train.start<=start && train.people > 0 && train.end>=end) {
                train.people--;
                booking.trainIdx = i; // 해당 예약을 처리한 기차번호
                return true;
            }
        }

        List<Train> transfers = new ArrayList<>();
        for (int i = 0; i < trainList.size(); i++) {
            Train train = trainList.get(i);
            if (train.start<=start && train.people > 0 && transfers.size()==0) transfers.add(train); //일단 승차 가능하면 넣음
            else if (transfers.size()!=0 && transfers.getLast().end >= train.start && train.people > 0) { // 다음 승차 가능한 경우 넣고
                transfers.add(train);
                if (train.end>=end) { // 해당 승차로 예약이 완료처리 되면
                    for (Train transfer : transfers) {
                        transfer.people--; // 사용한 기차 모두 좌석수 --
                    }

                    break;
                }
            }
        }


        return false;
    }


    private void updateBooking(List<Book> bookList, Book booking, List<Train> trainList) {
        int start = booking.start;
        int end = booking.end;
        for (Book book : bookList) {
            if (book.start==start && book.end==end) continue;
            if (book.end <= start && book.trainIdx != 0 && book.trainIdx != -1) {
                trainList.get(book.trainIdx).people++; // 어린이 하차 시켜서 좌석수 증가
                book.trainIdx = -1; // 예약처리 완.
            }
        }
    }

    class Book {
        int start;
        int end;
        int trainIdx;

        public Book(int start, int end, int trainIdx) {
            this.start = start;
            this.end = end;
            this.trainIdx = trainIdx;
        }
    }

    class Train {
        int start;
        int end;
        int people;

        public Train(int start, int end, int people) {
            this.start = start;
            this.end = end;
            this.people = people;
        }
    }

    public static void main(String[] args) {
        a34_maxNumPeople T = new a34_maxNumPeople();
        System.out.println(T.solution(5, new int[][]{{1, 4, 2}, {2, 5, 1}}, new int[][]{{1, 2}, {1, 5}, {2, 3}, {2, 4}, {2, 5}, {2, 5}, {3, 5}, {3, 4}}));
        System.out.println(T.solution(5, new int[][]{{2, 3, 1}, {1, 5, 1}}, new int[][]{{2, 5}, {1, 5}, {1, 3}, {2, 4}, {2, 5}, {2, 3}}));
        System.out.println(T.solution(8, new int[][]{{1, 8, 3}, {3, 8, 1}}, new int[][]{{1, 3}, {5, 8}, {2, 7}, {3, 8}, {2, 7}, {2, 8}, {3, 8}, {6, 8}, {7, 8}, {5, 8}, {2, 5}, {2, 7}, {3, 7}, {3, 8}}));
        System.out.println(T.solution(9, new int[][]{{1, 8, 3}, {3, 9, 2}, {1, 5, 3}}, new int[][]{{1, 9}, {5, 8}, {2, 9}, {3, 8}, {2, 9}, {1, 9}, {8, 9}, {3, 9}, {1, 8}, {6, 8}, {7, 8}, {5, 8}, {3, 5}, {3, 7}, {4, 7}, {5, 8}}));
        System.out.println(T.solution(9, new int[][]{{2, 7, 2}, {3, 9, 2}, {1, 5, 3}}, new int[][]{{1, 9}, {4, 8}, {2, 9}, {5, 9}, {3, 8}, {2, 9}, {1, 9}, {8, 9}, {3, 9}, {1, 8}, {6, 8}, {3, 6}, {7, 8}, {5, 8}, {3, 5}, {2, 7}, {1, 7}, {2, 8}}));
    }
}
