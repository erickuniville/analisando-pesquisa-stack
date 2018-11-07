package erick;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;

import java.io.FileReader;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import static erick.Util.jobSatisfactionToNumeric;

public class ScriptJobPriorities {
    public static void main(String[] args) throws Exception {
        String fileResource = ScriptJobPriorities.class.getClassLoader().getResource("survey_results_public.csv").getFile();

        HashMap<String, String> jobPriorities = new HashMap<>();
//        jobPriorities.put("1", "The diversity of the company or organization");
//        jobPriorities.put("2", "The office environment or company culture");
//        jobPriorities.put("3", "\"The languages, frameworks, and other technologies I’d be working with\"");
//        jobPriorities.put("4", "The industry that I’d be working in");
//        jobPriorities.put("5", "The specific department or team I’d be working on");
//        jobPriorities.put("6", "The financial performance or funding status of the company or organization");
//        jobPriorities.put("7", "How widely used or impactful the product or service I’d be working on is");
//        jobPriorities.put("8", "Opportunities for professional development");
//        jobPriorities.put("9", "The opportunity to work from home/remotely");
//        jobPriorities.put("10", "The compensation and benefits offered");
        jobPriorities.put("1", "diversity");
        jobPriorities.put("2", "culture");
        jobPriorities.put("3", "tecnologies");
        jobPriorities.put("4", "industry");
        jobPriorities.put("5", "departament");
        jobPriorities.put("6", "financialPerf");
        jobPriorities.put("7", "impactful");
        jobPriorities.put("8", "profDevelop");
        jobPriorities.put("9", "workRemot");
        jobPriorities.put("10", "compensations");

        try (PrintWriter writer = new PrintWriter("survey_tratado_teste.csv")) {
            Iterable<CSVRecord> records = CSVFormat.DEFAULT.withFirstRecordAsHeader().parse(new FileReader(fileResource));
            writer.println("jobPriority,jobSatisfaction");
            for (CSVRecord record : records) {
                String jobSatisfactionStr = record.get("JobSatisfaction");
                String jobPriority = "NA";

                for(int i=1; i<=10; i++) {
                    if("1".equals(record.get("AssessJob" + i))) {
                        jobPriority = String.valueOf(i);
                        break;
                    }
                }

                if(jobSatisfactionStr.equals("NA") || jobPriority.equals("NA")) {
                    continue;
                }

                int jobSatisfaction = jobSatisfactionToNumeric(jobSatisfactionStr);

                writer.println(jobPriorities.get(jobPriority) + "," + jobSatisfaction);
            }
        }
    }
}
