package com.example.algorithmstudy.lessons039;

import java.util.*;
class Solution {
    String string;
    ArrayList<String> list;
    ArrayList<String> answer;

    public String[] solution(String s){
        list = new ArrayList<>();
        answer = new ArrayList<>();
        string = s;
        
        dfs(0);

        return answer.toArray(new String[0]);
    }

    private void dfs(int index) {
        //list의 길이가 4이고, 순회를 마친 경우면 정답에 포함
        if(list.size() == 4 && index == string.length()) {
            String temp = "";
            for(int i = 0; i < list.size(); i++) {
                temp+=list.get(i);
                if(i!=3) {
                    temp+=".";
                }
            }
            answer.add(temp);
        } else {
            //index부터 순회
            for (int i = index; i < string.length(); i++) {
                //숫자 자르기
                String number = string.substring(index, i + 1);

                //0으로 시작하는데 길이가 1보다 크면 종료
                char[] chars = number.toCharArray();
                if(chars[0] == '0' && number.length() > 1) {
                    return;
                }

                //255보다 크면 종료
                if(Integer.parseInt(number) > 255) {
                    return;
                }

                //자른 숫자 뒤에 점찍은 경우
                list.add(number);
                dfs(i + 1);
                //안찍은 경우
                list.removeLast();
            }
        }
    }

    public static void main(String[] args){
        Solution T = new Solution();
        System.out.println(Arrays.toString(T.solution("2025505")));
        System.out.println(Arrays.toString(T.solution("0000")));
        System.out.println(Arrays.toString(T.solution("255003")));
        System.out.println(Arrays.toString(T.solution("155032012")));
        System.out.println(Arrays.toString(T.solution("02325123")));
        System.out.println(Arrays.toString(T.solution("121431211")));
    }
}