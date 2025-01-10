package com.algorithm;

import java.util.ArrayList;

public class a11_createDifferentFrequencies {
    public int solution(String s){
        int answer = 0;
        String tempString = s;
        ArrayList<ArrayList<String>> frequencyArray = getFrequencyArray(tempString); // [문자, 빈도] 2차원 배열 구하기
        int same; // 동일한 빈도의 개수(동일 빈도 여러 개일 경우 same만큼 제거)
        for (int i = 0; i < frequencyArray.size(); i++) {
            same=0;
            for (int j = 0; j < frequencyArray.size(); j++) {
                // 동일 인덱스 x, 동일 문자가 0이 x, 두 문자가 동일한 경우
                if(i!=j && !frequencyArray.get(i).get(1).equals("0") && frequencyArray.get(i).get(1).equals(frequencyArray.get(j).get(1))) {
                    same++; // 동일한 빈도 개수 증가
                    if (Integer.parseInt(frequencyArray.get(j).get(1))-same<0) {
                        answer++;
                        frequencyArray.get(j).set(1, "0");
                    }
                    else {
                        frequencyArray.get(j).set(1, String.valueOf(Integer.parseInt(frequencyArray.get(j).get(1)) - same));
                        answer+=same;
                    }
                }
            }
        }

        return answer;
    }

    public ArrayList<ArrayList<String>> getFrequencyArray(String tempString) {
        ArrayList<ArrayList<String>> frequencyArray = new ArrayList<>();
        while (tempString.length()!=0) {
            ArrayList<String> frequency = new ArrayList<>();
            frequency.add(String.valueOf(tempString.charAt(0))); // 문자열 첫 번째 숫자 리스트에 추가
            // 문자열 첫 번째 숫자로 split -> 사이즈 구해서 리스트에 추가
            if (tempString.split(String.valueOf(tempString.charAt(0))).length!=0) frequency.add(String.valueOf(tempString.split(String.valueOf(tempString.charAt(0))).length-1));
            else frequency.add(String.valueOf(tempString.length()));
            // [문자열, 개수] 세트로 리스트에 add
            frequencyArray.add(frequency);
            // 저장한 문자 전부 제거
            tempString = tempString.replaceAll(frequencyArray.get(frequencyArray.size()-1).get(0),"");
        }
        return frequencyArray;
    }

    public static void main(String[] args){
        a11_createDifferentFrequencies T = new a11_createDifferentFrequencies();
        System.out.println(T.solution("aaabbbcc"));
        System.out.println(T.solution("aaabbc"));
        System.out.println(T.solution("aebbbbc"));
        System.out.println(T.solution("aaabbbcccde"));
        System.out.println(T.solution("aaabbbcccdddeeeeeff"));
    }
}
