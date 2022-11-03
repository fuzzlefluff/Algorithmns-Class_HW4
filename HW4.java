// Matthew Prindle Homework 4
// CSC 4520 Fall 2022

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

class Main {
  public static void main(String[] args) {
    
    //graph of Q3
    int[][] m1 = {{0,0,0,0,0},
                  {1,0,1,0,1},
                  {1,0,0,0,0},
                  {1,0,0,0,0},
                  {0,0,0,1,0}};
    
    //graph of example
    int[][] m2 = {{0,0,0},
                  {0,0,1},
                  {0,1,0}};

    //wide invalid graph
    int[][] m3 = {{0,0,0},
                  {0,0,0}};
    
    //tall invalid graph
    int[][] m4 = {{0,0},
                  {0,0},
                  {0,0}};
    
    //an array to hold DFS function output
    int[] output = null;

    //run our DFS search for starting nodes 0-4 on the Q3 graph
    output = DFS(m1,0);
    printList(output, "matrix1 ex1");
    output = DFS(m1,1);
    printList(output, "matrix1 ex2");
    output = DFS(m1,2);
    printList(output, "matrix1 ex3");
    output = DFS(m1,3);
    printList(output, "matrix1 ex4");
    output = DFS(m1,4);
    printList(output, "matrix1 ex5");
    
    //ask for a search with node 5, which does not exist
    output = DFS(m1,5);
    printList(output, "matrix1 ex6");
    
    //run our DFS search for starting nodes 0-2 on the example graph
    output = DFS(m2,0);
    printList(output, "matrix2 ex1");
    output = DFS(m2,1);
    printList(output, "matrix2 ex2");
    output = DFS(m2,2);
    printList(output, "matrix2 ex3");
    
    //ask for a search with node 3, which does not exist
    output = DFS(m2,3);
    printList(output, "matrix2 ex4");
    
    //ask for a search with the wide invalid graph
    output = DFS(m3,0);
    printList(output, "matrix3 ex1");
  
    //ask for a search with the tall invalid graph
    output = DFS(m4,0);
    printList(output,"matrix4 ex1");
    
}
  // a method that simple prints a list with a given title
  static public void printList(int[] list, String title){
    System.out.print(title +":[ ");
    for(int i =0; i<list.length; i++){
      System.out.print(list[i]+" ");
    }
    System.out.print("]\n");
  }

  //returns an array with a depth first search of a given graph from its adjacency matrix
  static public int[] DFS(int[][] graphMatrix, int startingNode){
    //check for valid input
    int nodeNum = graphMatrix.length;
    if(startingNode > nodeNum-1){
      System.out.print("invalid input! bad starting node! ("+nodeNum+")\t");
      int[] r = {-1};
      return r;
    }
    if(nodeNum != graphMatrix[0].length){
      System.out.print("invalid input! bad matrix!\t");
      int[] r = {-1};
      return r;
    }
    //set up a list for our return order, and a queue of things to traverse, and an array of what we have visited or not
    List<Integer> rOrder = new ArrayList<Integer>();
    Queue<Integer> queue = new LinkedList<Integer>();
    boolean[] visited = new boolean[nodeNum];

    //add out initial starting node to the front of the queue and start iterating through the array, 
    //adding more nodes to our queue to visit where appropriate, ignoring previously visited nodes
    queue.add(startingNode);
    visited[startingNode] = true;
    while(!queue.isEmpty()){
      int i = queue.remove();
      rOrder.add(i);
      for(int j =0; j < graphMatrix[i].length; j++){
        if(!visited[j] && graphMatrix[i][j] >= 1){
          queue.add(j);
          visited[j] = true;
        }
      }
    }
    //convert our list to to an array and return it
    int[] rArr = rOrder.stream().mapToInt(Integer::intValue).toArray();
    return rArr;
  }
}