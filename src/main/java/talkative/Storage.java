package talkative;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Helps with the management of loading and saving of memory of history
 */
public class Storage {

    private final String filePath;

    public Storage(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Returns the saved task from user's previous action
     *
     * @return the formatted output.
     */
    public ArrayList<Task> load() {
        ArrayList<Task> tasks = new ArrayList<>();
        File file = new File(filePath);

        try {
            file.getParentFile().mkdirs();

            if (!file.exists()) {
                file.createNewFile();
                return tasks;
            }

            BufferedReader br = new BufferedReader(new FileReader(file));
            String line;

            while ((line = br.readLine()) != null) {

                try {
                    String[] parts = line.split(" \\| ");

                    String type = parts[0];
                    boolean isDone = parts[1].equals("1");
                    String desc = parts[2];

                    Task task;

                    switch (type) {
                    case "T":
                        task = new Todo(desc);
                        break;
                    case "D":
                        task = new Deadline(desc, parts[3]);
                        break;
                    case "E":
                        task = new Event(desc, parts[3], parts[4]);
                        break;
                    default:
                        continue; // corrupted line
                    }

                    if (isDone) {
                        task.markTaskAsDone();
                    }

                    tasks.add(task);

                } catch (Exception e) {
                    // stretch goal — skip corrupted lines
                    System.out.println("Skipping corrupted line: " + line);
                }
            }

            br.close();

        } catch (IOException e) {
            System.out.println("Error loading file.");
        }

        return tasks;
    }

    /**
     * Saves the user input into the file for memory of history
     */
    public void save(ArrayList<Task> tasks) {

        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter(filePath));

            for (Task t : tasks) {
                bw.write(t.toFileFormat());
                bw.newLine();
            }

            bw.close();

        } catch (IOException e) {
            System.out.println("Error saving file.");
        }
    }
}
