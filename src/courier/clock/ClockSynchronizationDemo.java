package courier.clock;

/** Educational Berkeley-style average-time simulation; it does not alter OS clocks. */
public class ClockSynchronizationDemo {
    public static void main(String[] args) {
        PhysicalClock[] clocks = { new PhysicalClock(1, 5), new PhysicalClock(2, 9), new PhysicalClock(3, 3) };
        System.out.println("=== PHYSICAL CLOCK SYNCHRONIZATION (SIMULATION) ===");
        for (PhysicalClock clock : clocks) System.out.println(clock.display());
        int average = (clocks[0].getSeconds() + clocks[1].getSeconds() + clocks[2].getSeconds()) / 3;
        System.out.println("Coordinator calculates average time = 10:00:" + String.format("%02d", average));
        for (PhysicalClock clock : clocks) {
            int offset = average - clock.getSeconds();
            System.out.println("Offset = " + (offset >= 0 ? "+" : "") + offset + " seconds");
            clock.adjustBy(offset);
        }
        System.out.println("After synchronization:");
        for (PhysicalClock clock : clocks) System.out.println(clock.display());
        System.out.println("This is an educational simulation; operating-system clocks are unchanged.");
    }
}
