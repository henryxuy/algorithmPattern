import java.util.*;

class MatrixNode{
    int row;
    int column;
    int value;
    MatrixNode(int row, int column, int value){
        this.row = row;
        this.column = column;
        this.value = value;
    }
}


class KthSmallestInSortedMatrix {

    public static int findKthSmallest(int[][] matrix, int k) {
        // the rows and columns of the matrix is sorted
        // treat them as sorted arrays
        PriorityQueue<MatrixNode> minHeap = new PriorityQueue<>((o1, o2) -> (o1.value - o2.value));
        int countRow = matrix.length;
        int result = 0;
        for(int i = 0; i < countRow && i < k; i++){
            MatrixNode tempNode = new MatrixNode(i, 0, matrix[i][0]);
            minHeap.offer(tempNode);
        }
        for(int i = 0; i < k; i++){
            MatrixNode current = minHeap.poll();
            result = current.value;
            current.column++;
            if(current.column < matrix[current.row].length){
                current.value = matrix[current.row][current.column];
                minHeap.offer(current);
            }
        }

        return result;
    }

    public static void main(String[] args) {
        int matrix[][] = { { 2, 6, 8 }, { 3, 7, 10 }, { 5, 8, 11 } };
        int result = KthSmallestInSortedMatrix.findKthSmallest(matrix, 5);
        System.out.print("Kth smallest number is: " + result);
    }
}