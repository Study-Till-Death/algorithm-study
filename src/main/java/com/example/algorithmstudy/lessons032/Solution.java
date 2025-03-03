package com.example.algorithmstudy.lessons032;

import java.util.*;
class Solution {
    public int solution(int[] plantTime, int[] growTime){
        int answer = 0;

        //우선순위 큐에 심는기간, 성장기간 넣기
        PriorityQueue<PlantInfo> pq = new PriorityQueue<>();
        for(int i = 0; i < plantTime.length; i++){
            pq.add(new PlantInfo(plantTime[i], growTime[i]));
        }

        int current = 0; //현재
        while(!pq.isEmpty()){
            PlantInfo plantInfo = pq.poll();
            
            current += plantInfo.plantTime;
            
            //방금 심은 작물이 다 자라는 날이 젤 나중인지 비교
            answer = Math.max(answer, current+plantInfo.growTime);
        }

        return answer;
    }

    class PlantInfo implements Comparable<PlantInfo>{
        int plantTime;
        int growTime;

        public PlantInfo(int plantTime, int growTime) {
            this.plantTime = plantTime;
            this.growTime = growTime;
        }

        //1순위. 성장기간이 긴 작물
        //2순위. 심는기간이 짦은 작물
        @Override
        public int compareTo(PlantInfo o) {
            int growTimeCompare = o.growTime - this.growTime;
            int plantTimeCompare = this.plantTime - o.plantTime;

            return growTimeCompare == 0 ? plantTimeCompare : growTimeCompare;
        }
    }

    public static void main(String[] args){
        Solution T = new Solution();
        System.out.println(T.solution(new int[]{1, 3, 2}, new int[]{2, 3, 2}));
        System.out.println(T.solution(new int[]{2, 1, 4, 3}, new int[]{2, 5, 3, 1}));
        System.out.println(T.solution(new int[]{1, 1, 1}, new int[]{7, 3, 2}));
        System.out.println(T.solution(new int[]{5, 7, 10, 15, 7, 3, 5}, new int[]{6, 7, 2, 10, 15, 6, 7}));
        System.out.println(T.solution(new int[]{1, 2, 3, 4, 5, 6, 7}, new int[]{7, 5, 4, 3, 2, 1, 6}));
    }
}