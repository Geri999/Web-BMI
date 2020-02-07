package pl.bci.g73.bmi1;

public class MeasurementRecord {

    private static int IDmain;

    private int ID;
    private String name;
    private String sex;
    private double age;
    private double weight;
    private double height;

    public MeasurementRecord(int ID, String name, String sex, double age, double weight, double height) {
        this.ID=ID;
        this.name = name;
        this.sex = sex;
        this.age = age;
        this.weight = weight;
        this.height = height;
    }

    public static int getIDmain() {
        return IDmain;
    }

    public static void setIDmain(int IDmain) {
        MeasurementRecord.IDmain = IDmain;
    }

    @Override
    public String toString() {
        return new StringBuilder()
                .append(ID + ",")
                .append(name + ",")
                .append(sex + ",")
                .append(age + ",")
                .append(weight + ",")
                .append(height)
                .toString();
    }
}
