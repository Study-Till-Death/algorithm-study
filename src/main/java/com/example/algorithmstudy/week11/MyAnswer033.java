package com.example.algorithmstudy.week11;
import java.util.*;

class Student {
    int index; //학생 순번
    String team; //팀
    int power; //공격력

    public Student(int index, String team, int power) {
        this.index = index;
        this.team = team;
        this.power = power;
    }
}

public class MyAnswer033 {
    public int[] solution(String[] students){
        int n = students.length;
        int[] answer = new int[n];

        List<Student> studentList = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            String[] parts = students[i].split(" ");
            studentList.add(new Student(i, parts[0], Integer.parseInt(parts[1])));
        }

        studentList.sort(Comparator.comparingInt(s -> s.power));

        //이거 느린거구나 라는 사실을 깨달음
//        for (Student student : studentList) {
//            String team = student.team;
//            int power = student.power;
//            int score = 0;
//            for(Student enemy : studentList) {
//                if(!team.equals(enemy.team)){
//                    if(power > enemy.power){
//                        score += enemy.power;
//                    }else{
//                        break;
//                    }
//                }
//            }
//            answer[student.index] = score;
//        }

        Map<String, Integer> teamScoreMap = new HashMap<>();
        int totalScore = 0;
        int j = 0;

        for (int i = 0; i < n; i++) {
            Student currentStudent = studentList.get(i);
            String team = currentStudent.team;
            int power = currentStudent.power;

            while (j < n && studentList.get(j).power < power) {
                Student weaker = studentList.get(j);
                totalScore += weaker.power;
                teamScoreMap.put(weaker.team, teamScoreMap.getOrDefault(weaker.team, 0) + weaker.power);
                j++;
            }

            answer[currentStudent.index] = totalScore - teamScoreMap.getOrDefault(team, 0);
        }


        return answer;
    }

    public static void main(String[] args){
        MyAnswer033 T = new MyAnswer033();
        System.out.println(Arrays.toString(T.solution(new String[]{"a 20", "b 12", "a 10", "c 11", "e 12"})));
        System.out.println(Arrays.toString(T.solution(new String[]{"a 17", "b 12", "a 10", "c 11", "b 24", "a 25", "b 12"})));
        System.out.println(Arrays.toString(T.solution(new String[]{"b 20", "c 15", "a 200", "b 11", "b 24", "a 25", "b 12"})));
        System.out.println(Arrays.toString(T.solution(new String[]{"a 30", "a 25", "a 25", "b 20", "b 25", "a 25", "b 30"})));
    }
}
