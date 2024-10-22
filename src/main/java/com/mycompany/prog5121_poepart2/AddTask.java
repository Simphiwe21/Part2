/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.prog5121_poepart2;

import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
public class AddTask {
    final List<Task> tasks;
    int totalHours;
    
    public AddTask(){
        this.tasks = new ArrayList<>();
    this.totalHours = 0;
    }
    
    void manageTasks() {
    OUTER:
    while (true) {
        String[] options = {"Add tasks", "Show report (Coming Soon)", "Quit"};
        int option = JOptionPane.showOptionDialog(null, "Select an option:", "Task Management",
                JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, options, options[0]);

        if (option == JOptionPane.CLOSED_OPTION) return;

        switch (option) {
            case 2 -> {
                // Quit
                return; // Exit the program
            }
            case 0 -> {
                // Add tasks
                String numTasksString = JOptionPane.showInputDialog("How many tasks do you wish to enter?");
                if (numTasksString == null) continue; // Return to menu if cancel is pressed

                int numTasks;
                try {
                    numTasks = Integer.parseInt(numTasksString);
                } catch (NumberFormatException e) {
                    JOptionPane.showMessageDialog(null, "Invalid number. Please try again.");
                    continue;
                }

                for (int i = 0; i < numTasks; i++) {
                    Task task = createTask(i);
                    if (task == null) break; // Exit if cancel is pressed
                    tasks.add(task);
                    totalHours += task.getDuration();
                    JOptionPane.showMessageDialog(null, task.printTaskDetails());
                }
                JOptionPane.showMessageDialog(null, "Total hours across all tasks: " + returnTotalHours());
            }
            default -> JOptionPane.showMessageDialog(null, "Invalid option. Please try again.");
        }
    }
}


private Task createTask(int taskNumber) {
    String taskName = JOptionPane.showInputDialog("Enter Task Name:");
    if (taskName == null) return null; // Return null if cancel is pressed

    String taskDescription;
    do {
        taskDescription = JOptionPane.showInputDialog("Enter Task Description (max 50 characters):");
        if (taskDescription == null) return null; // Return null if cancel is pressed
    } while (!checkTaskDescription(taskDescription));

    String developerDetails = JOptionPane.showInputDialog("Enter Developer Details (Name and Surname):");
    if (developerDetails == null) return null; // Return null if cancel is pressed

    String durationString = JOptionPane.showInputDialog("Enter Task Duration (in hours):");
    if (durationString == null) return null; // Return null if cancel is pressed

    int duration = Integer.parseInt(durationString);

    String status = (String) JOptionPane.showInputDialog(null, "Select Task Status:",
            "Task Status", JOptionPane.QUESTION_MESSAGE, null, new String[]{"To Do", "Doing", "Done"}, "To Do");

    return new Task(taskName, taskNumber, taskDescription, developerDetails, duration, status);
}


    public boolean checkTaskDescription(String description) {
        return description.length() <= 50;
    }

   // public int returnTotalHours() {
   //     return totalHours;

    private int returnTotalHours() {
    return totalHours;
    }

   

class Task {
    private final String name;
    private final int taskNumber;
    private final String description;
    private final String developerDetails;
    private final int duration;
    private final String status;

    public Task(String name, int taskNumber, String description, String developerDetails, int duration, String status) {
        this.name = name;
        this.taskNumber = taskNumber;
        this.description = description;
        this.developerDetails = developerDetails;
        this.duration = duration;
        this.status = status;
    }

    public String printTaskDetails() {
        String taskID = createTaskID();
        return "Task Status: " + status + "\nDeveloper Details: " + developerDetails + "\nTask Number: " + taskNumber +
                "\nTask Name: " + name + "\nTask Description: " + description + "\nTask ID: " + taskID + "\nDuration: " + duration + " hours";
    }

    private String createTaskID() {
        return (name.substring(0, 2).toUpperCase() + ":" + taskNumber + ":" + developerDetails.substring(developerDetails.length() - 3).toUpperCase());
    }

    public String getName() {
        return name;
    }

    public int getTaskNumber() {
        return taskNumber;
    }

    public String getDeveloperDetails() {
        return developerDetails;
    }
    
    public int getDuration(){
    return duration;
    }

   
}

