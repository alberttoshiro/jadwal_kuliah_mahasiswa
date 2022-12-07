package com.albert.jadwalkuliah;

public class App {

  public static void main(String[] args) {
    String url, username, password;
    if (args.length < 3) {
      url = "jdbc:postgresql://localhost/jadwal_mahasiswa";
      username = "postgres";
      password = "postgres";
      System.out.println("Input database url, username, and password");
      System.out.println("Using default database url, username, and password");
    } else {
      url = args[0];
      username = args[1];
      password = args[2];
    }
    FolderListener folderListener = new FolderListener(url, username, password);
    Thread t = new Thread(folderListener);
    t.start();
  }
}
