package erick;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;

import java.io.FileReader;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.List;

import static erick.Util.jobSatisfactionToNumeric;

public class ScriptSplitLanguagesToMultiRecord {
    static final List<String> consideredLangs = Arrays.asList(
            "java",
            "javascript",
            "python",
            "c#",
            "php",
            "ruby",
            "kotlin"
    );

    public static void main(String[] args) throws Exception {
        String fileResource = ScriptSplitLanguagesToMultiRecord.class.getClassLoader().getResource("survey_results_public.csv").getFile();

        int sum = 0;
        int counter = 0;

        try (PrintWriter writer = new PrintWriter("survey_tratado_teste.csv")) {
            Iterable<CSVRecord> records = CSVFormat.DEFAULT.withFirstRecordAsHeader().parse(new FileReader(fileResource));
            writer.println("languageWorkedWith,jobSatisfaction");
            for (CSVRecord record : records) {
                String jobSatisfactionStr = record.get("JobSatisfaction");
                String languagesWorkedWithStr = record.get("LanguageWorkedWith");

                if(jobSatisfactionStr.equals("NA") || languagesWorkedWithStr.equals("NA")) {
                    continue;
                }

                String[] languagesWorkedWith = languagesWorkedWithStr.split(";");

                sum += languagesWorkedWith.length;
                counter++;

                int jobSatisfaction = jobSatisfactionToNumeric(jobSatisfactionStr);

                for(String language : languagesWorkedWith) {
                    if(consideredLangs.contains(language.toLowerCase())) {
                        writer.println(language + "," + jobSatisfaction);
                    }
                }
            }
        }

        System.out.println("Persons: " + counter); // 61092
        System.out.println("Average languages per person: " + ((double) sum / counter)); // 6.25
    }
}
