import java.util.*;

import java.util.*;

public class MaximizeCapital {

    private static class Project{
        int projectCapital;
        int projectProfit;

        Project(int projectCapital, int projectProfit){
            this.projectCapital = projectCapital;
            this.projectProfit = projectProfit;
        }
    }

    public static int findMaximumCapital(int[] capital, int[] profits, int numberOfProjects, int initialCapital){
        // use greedy algoritm + two-Heap
        int currentCapital = initialCapital;
        int N = capital.length;
        // minHeap: cost in increasing order. Then we can pick top-k available projects easily
        // maxHeap: profit in decreasing order. Then we can add top-k available projects into it, 
        // and pick the most profitable project 
        PriorityQueue<Project> minHeap = new PriorityQueue<>((o1, o2) -> Integer.compare(o1.projectCapital, o2.projectCapital));
        PriorityQueue<Project> maxHeap = new PriorityQueue<>((o1, o2) -> Integer.compare(o2.projectProfit, o1.projectProfit));

        for(int i = 0; i < N; i++){
            minHeap.offer(new Project(capital[i], profits[i]));
        }

        for(int i = 0; i < numberOfProjects; i++){
            while(!minHeap.isEmpty() && minHeap.peek().projectCapital <= currentCapital){
                maxHeap.offer(minHeap.poll());
            }
            // terminate if we are not able to find any project that can be completed within the available capital
            if(maxHeap.isEmpty()){
                break;
            }
            Project projectToDo = maxHeap.poll();
            currentCapital += projectToDo.projectProfit;
        }

        return currentCapital;
    }



    public static int findMaximumCapitalGreedy(int[] capital, int[] profits, int numberOfProjects, int initialCapital) {
        // greedy algorithm: choose the project that can maximize the profit each time
        int currentCapital = initialCapital;
        int N = capital.length;
        List<Project> projectList = new ArrayList<>();
        for(int i = 0; i < N; i++){
            projectList.add(new Project(capital[i], profits[i]));
        }

        for(int i = 0; i < numberOfProjects; i++){
            // sort by the profit in the reverse order
            Collections.sort(projectList, (o1, o2) -> Integer.compare(o2.projectProfit, o1.projectProfit));
            for(int j = 0; j < projectList.size(); j++){
                Project project = projectList.get(j);
                if(project.projectCapital <= currentCapital){
                    // do this project and remove it
                    currentCapital += project.projectProfit;
                    projectList.remove(j);
                    break;
                }
            }
        }


        return currentCapital;
    }
    
    public static void main(String[] args) {
        int result = MaximizeCapital.findMaximumCapital(new int[] { 0, 1, 2 }, new int[] { 1, 2, 3 }, 2, 1);
        System.out.println("Maximum capital: " + result);
        result = MaximizeCapital.findMaximumCapital(new int[] { 0, 1, 2, 3 }, new int[] { 1, 2, 3, 5 }, 3, 0);
        System.out.println("Maximum capital: " + result);
    }
}
