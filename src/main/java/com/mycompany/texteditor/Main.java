/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.texteditor;

import java.util.Stack;

class TextEditor {
    private StringBuilder currentText; // Untuk menyimpan teks saat ini
    private Stack<String> undoStack;   // Untuk menyimpan teks sebelum ada perubahan (undo)
    private Stack<String> redoStack;   // Untuk menyimpan teks yang di-undo (redo)

    public TextEditor() {
        currentText = new StringBuilder();
        undoStack = new Stack<>();
        redoStack = new Stack<>();
    }

    // Fungsi untuk menampilkan teks yang sedang ada di text editor
    public void show() {
        if (currentText.length() == 0) {
            System.out.println("(kosong)");
        } else {
            System.out.println(currentText.toString());
        }
    }

    // Fungsi untuk menambahkan teks ke dalam text editor
    public void write(String newText) {
        undoStack.push(currentText.toString()); // Simpan keadaan sebelum perubahan
        currentText.append(newText);
        redoStack.clear(); // Kosongkan redo stack setelah ada perubahan baru
    }

    // Fungsi untuk melakukan undo
    public void undo() {
        if (!undoStack.isEmpty()) {
            redoStack.push(currentText.toString()); // Simpan keadaan untuk redo
            currentText = new StringBuilder(undoStack.pop()); // Kembalikan keadaan sebelumnya
        } else {
            System.out.println("Tidak ada tindakan yang bisa di-undo.");
        }
    }

    // Fungsi untuk melakukan redo
    public void redo() {
        if (!redoStack.isEmpty()) {
            undoStack.push(currentText.toString()); // Simpan keadaan untuk undo lagi
            currentText = new StringBuilder(redoStack.pop()); // Pulihkan ke keadaan yang di-undo
        } else {
            System.out.println("Tidak ada tindakan yang bisa di-redo.");
        }
    }
}

public class Main {
    public static void main(String[] args) {
        TextEditor editor = new TextEditor();
        
        editor.write("Hello, ");
        editor.show();  // Output: Hello, 
        
        editor.write("world!");
        editor.show();  // Output: Hello, world!
        
        editor.undo();
        editor.show();  // Output: Hello, 
        
        editor.redo();
        editor.show();  // Output: Hello, world!
        
        editor.undo();
        editor.undo();
        editor.show();  // Output: (kosong)
    }
}

