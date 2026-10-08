package courier.unit4;

public class DistributedObjectDemo {
    private static class CourierService {
        String findCourier(String id) { return "Remote-style service result: " + id + " is IN_TRANSIT"; }
    }
    public static void main(String[] args) {
        System.out.println(new CourierService().findCourier("C101"));
        System.out.println("Simulation only: a real remote object would use network middleware such as RMI.");
    }
}
