package com.demo.lab1;

public class WordCounter {

    public static int countWords(String input) {
        if (input == null || input.trim().isEmpty()) {
            return 0;
        }

        // Split the string into parts using whitespace
        String[] parts = input.trim().split("\\s+");

        int count = 0;
        for (String part : parts) {
            // Count only alphabetic words (ignoring numbers)
            if (part.matches("[a-zA-Z]+")) {
                count++;
            }
        }

        return count;
    }

    public static void main(String[] args) {
        String text = "Sum of 12 and 20 is 32";
        int wordCount = countWords(text);
        System.out.println("Number of words: " + wordCount);  // Output: 4
    }
}
