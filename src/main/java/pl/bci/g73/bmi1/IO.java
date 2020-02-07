package pl.bci.g73.bmi1;

import org.springframework.stereotype.Component;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

@Component
public class IO {

    private static String bazaPath = (new File("").getAbsolutePath() + "\\src\\main\\java\\pl\\bci\\g73\\bmi1\\baza.txt");
    private static String lastIDPath = (new File("").getAbsolutePath() + "\\src\\main\\java\\pl\\bci\\g73\\bmi1\\LastID" +
            ".txt");

    //--------------------------------------------------------------------------------------
    public void save(MeasurementRecord measurementRecord) {
        try (BufferedWriter bufferedWriterDB = new BufferedWriter(new FileWriter(bazaPath, true));
        BufferedWriter bufferedWriterID = new BufferedWriter(new FileWriter(lastIDPath))) {

            bufferedWriterDB.write(measurementRecord.toString());
            bufferedWriterDB.newLine();
            bufferedWriterID.write(MeasurementRecord.getIDmain()+"");
            System.out.println(MeasurementRecord.getIDmain());


        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //--------------------------------------------------------------------------------------
    public MeasurementRecord findRecordOnHDDByID(int intToFind) {
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(bazaPath))) {

            String line;
            while ((line = bufferedReader.readLine()) != null) {
                String[] record = line.split(",");
                if (Integer.parseInt(record[0]) == intToFind) {
                    return new MeasurementRecord(Integer.parseInt(record[0]), record[1], record[2],
                            Double.parseDouble(record[3]), Double.parseDouble(record[4]),
                            Double.parseDouble(record[5]));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        throw new NoSuchElementException();
    }

    //--------------------------------------------------------------------------------------
    public List<MeasurementRecord> loadWholeDBfromHDD() {
        List<MeasurementRecord> measurementRecordList = new ArrayList<>();

        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(bazaPath))) {
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                String[] record = line.split(",");
                measurementRecordList.add(new MeasurementRecord(Integer.parseInt(record[0]), record[1], record[2],
                        Double.parseDouble(record[3]), Double.parseDouble(record[4]),
                        Double.parseDouble(record[5])));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return measurementRecordList;
    }

    //--------------------------------------------------------------------------------------
    public void loadAndSetLastMainIDfromHDD() {
        int recordMax = 0;
        String line;

        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(lastIDPath))) {
            while ((line = bufferedReader.readLine()) != null) {
                String[] record = line.split(",");
                if (Integer.parseInt(record[0]) > recordMax) recordMax = Integer.parseInt(record[0]);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
            MeasurementRecord.setIDmain(recordMax);
    }
}