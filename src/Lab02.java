public class Lab02 {
    static void main() {
        AirPurifier p1 = new AirPurifier(
                false, 1, "Xiaomi 4 Pro", 6990.0, 35, "XM-001", "27x27x68 cm",
                "Xiaomi", Mode.AUTO, "White", "6.8 kg", "China", 220
        );
        AirPurifier p2 = new AirPurifier(
                true, 2, "Dyson Pure Cool", 18900.0, 85, "DY-002", "22x22x105 cm",
                "Dyson", Mode.MANUAL, "Silver", "4.9 kg", "Malaysia", 220
        );
        AirPurifier p3 = new AirPurifier(
                true, 4, "Sharp Plasmacluster", 8500.0, 180, "SH-003", "39x23x58 cm",
                "Sharp", Mode.POWER, "Black", "5.5 kg", "Thailand", 220
        );
        AirPurifier p4 = new AirPurifier(
                true, 1, "Xiaomi 4 Pro", 6990.0, 40, "XM-002", "27x27x68 cm",
                "Xiaomi", Mode.SLEEP, "White", "6.8 kg", "China", 220
        );

        p1.turnOn();
        p1.setMode(Mode.MANUAL);
        p1.showPm25();
        p1.setFanSpeed();
        p1.showPm25();
        p1.setFanSpeed();
        p1.setFanSpeed();
        p1.setFanSpeed();
        p1.showPm25();
        p1.turnOff();
        p1.showPm25();
        p1.turnOn();
        p1.setMode(Mode.POWER);
        p1.showPm25();
        p1.setFanSpeed();
        p1.showPm25();
        p1.setMode(Mode.SLEEP);
        p1.showPm25();
        p1.setFanSpeed();
        p1.showPm25();
        p1.setMode(Mode.AUTO);
        p1.showPm25();
        p1.changePm25(200);
        p1.showPm25();

        IO.println("Most popular model: "+AirPurifier.mostPopularModel());
        IO.println("Total number of Airpurifier: "+AirPurifier.totalNumber);

    }
}
