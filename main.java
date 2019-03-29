import java.util.Scanner;
import java.util.Arrays;
import java.io.File;
import java.io.FileNotFoundException;

public class main {
  public static void main(String[] args) {
    //program utama, cukup jalanin yang ini aja saat compile + run
    Scanner scan = new Scanner(System.in);

    System.out.print("Masukkan nama file input : ");
    String fileName = scan.nextLine();
    File input = new File(fileName);
    try {
      Scanner scanner = new Scanner(input);

      int numIsland = scanner.nextInt(); //jumlah pulau
      int numJembatan = scanner.nextInt(); //jumlah jembatan
      int firstIsland = scanner.nextInt(); //pulau pertama yang jadi patokan

      int[][] jembatan = new int[numJembatan][2];
      while (scanner.hasNextInt()) {
        for (int i=0; i<numJembatan; i++) {
          for (int j=0; j<2; j++) {
            jembatan[i][j] = scanner.nextInt();
          }
        }
      }

      //membuat pulau + jembatan (sekaligus print jalur jalan pulau)
      int[][] pulau = island.MakeIsland(numIsland+1,jembatan);
      System.out.println("Jalur pulau yang mungkin : ");
      int[] temp = new int[pulau.length+1]; //temporari untuk mengatasi "siklik"
      long start1 = System.nanoTime();
      long start2 = System.nanoTime();
      int[] lonely = island.LonelyIsland(firstIsland,pulau,temp,0);
      long stop = System.nanoTime();

      //Menampikan hasil lonely island ke layar
      SortHasil(lonely);
      PrintHasil(lonely);
      long time = stop - 2*start2 + start1;
      double seconds = (double)time / 1_000_000_000.0;
      System.out.println("Waktu eksekusi : " + seconds + " s");
    } catch(FileNotFoundException fnfe) {
      System.out.println(fnfe.getMessage());
    }
  }

  public static void PrintIsland(int[][] misland) {
    //Siapa tau mau print pulau + jembatannya
    System.out.println(Arrays.deepToString(misland));
  }

  public static void PrintHasil(int[] mhasil) {
    //print lonely island
    System.out.print("Lonely Island : ");
    for (int i=0; i<mhasil.length; i++) {
      if (mhasil[i] != 0) {
        System.out.print(mhasil[i]);
        System.out.print(" ");
      }
    }
    System.out.println("");
  }

  public static void SwapElemen(int[] array, int idx1, int idx2) {
    //menukar elemen array, dipakai saat mencari IdxMin dan saam Sorting
    int temp = array[idx1];
    array[idx1] = array[idx2];
    array[idx2] = temp;
  }

  public static int IdxMin(int[] array, int first_idx) {
    //index dari elemen minimum di sub-array[first_idx...length]
    int minim = first_idx;
    for (int i = first_idx; i<array.length; i++) {
      if (array[i] < array[minim]) {
        SwapElemen(array,i,minim);
      }
    }
    return minim;
  }

  public static void SortHasil(int[] mhasil) {
    //sort lonely island agar ditampilkan terurut membesar
    for (int i=0; i<mhasil.length; i++) {
      SwapElemen(mhasil, i, IdxMin(mhasil,i));
    }
  }
}
