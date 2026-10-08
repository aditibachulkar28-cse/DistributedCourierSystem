package courier.unit4;

public class DistributedFileSystemDemo {
    public static void main(String[] args) {
        System.out.println("Node 1 stores File A");
        System.out.println("Node 2 stores File B");
        System.out.println("Node 3 stores replica of File A");
        System.out.println("Simulation only: this is not HDFS or a real distributed file system.");
    }
}
