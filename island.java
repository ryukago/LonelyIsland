import java.util.Arrays;

public class island {
  public static int[][] MakeIsland(int island, int[][] jembatan) {
    //membentuk matriks baru untuk menyimpan pulau & jembatan
    //index island+1 karena index mulai dari 0
    int[][] MIsland = new int[island+1][island+1];
    //membuat jembatan : MIsland[asal][tujuan]
    for (int i=0; i<jembatan.length; i++) {
      MIsland[jembatan[i][0]][jembatan[i][1]] = 1;
    }
    return MIsland;
  }

  public static int[] LonelyIsland(int asal, int[][] misland, int[] temp, int idx_temp) {
    int[] mhasil = new int[misland.length]; //array himpunan hasil
    temp[idx_temp] = asal; //tambah pulau ke jalur

    if (IsLonely(asal,misland)) { //basis
      mhasil = TambahElemen(asal,mhasil); //masukkan ke himpunan solusi
      PrintJalur(temp); //print jalur
      HapusJalur(temp,idx_temp);
    }
    else {
      for (int tujuan=0; tujuan<misland.length; tujuan++) {
        if (misland[asal][tujuan] == 0) {} //basis : tidak ada jembatan
        else {
          if (!(IsNotElemen(tujuan,temp))) { //basis : siklik
            temp[idx_temp+1] = tujuan;
            HapusJalur(temp,idx_temp+1);
          }
          else if (misland[asal][tujuan] == 1) { //rekurens : bukan siklik dan ada jembatan
            HapusJalur(temp,idx_temp+1);
            mhasil = TambahElemen(LonelyIsland(tujuan,misland,temp,idx_temp+1),mhasil);
          }
        }
      }
    }
    return mhasil;
  }

  public static boolean IsLonely(int pulau, int[][] misland) {
    //apakah tidak ada jembatan penghubung dari pulau ke manapun
    int xjembatan = 0;
    for (int i=0; i<misland.length; i++) {
      if (misland[pulau][i] == 1) {
        xjembatan++;
      }
    }
    return (xjembatan == 0);
  }

  public static int[] TambahElemen(int elmt, int[] marray) {
    //marray = [1,2,3] ; elmt = 4 -> marray = [1,2,3,4]
    int i = 0;
    while (marray[i] != 0) {
      i++;
    }
    if (IsNotElemen(elmt,marray)) {
      marray[i] = elmt;
    }
    return marray;
  }

  public static int[] TambahElemen(int[] elmt, int[] marray) {
    //marray = [1,2,3] ; elmt = [4,5] -> marray = [1,2,3,4,5]
    int i = 0;
    while (marray[i] != 0) {
      i++;
    }
    int j = 0;
    while (elmt[j] != 0){
      if (IsNotElemen(elmt[j],marray)) {
        marray[i] = elmt[j];
      }
      i++;
      j++;
    }
    return marray;
  }

  public static boolean IsNotElemen(int elmt, int[] marray) {
    //biar ga ditulis solusi double
    boolean hasil = true;
    int i = 0;
    while ((hasil) && (i < marray.length)) {
      if (marray[i] == elmt) {
        hasil = false;
      }
      else
        i++;
    }
    return hasil;
  }

  public static void HapusJalur(int[] temp, int idx_temp) {
    //menghapus jalur yang sudah mentok di lonely island
    for (int i=idx_temp; i<temp.length; i++) {
      temp[i] = 0;
    }
  }

  public static void PrintJalur(int[] mhasil) {
    //print jalur dgn format a->b->c->... dst
    int i = 0;
    while ((mhasil[i] != 0) && (mhasil[i+1] != 0)) {
      System.out.print(mhasil[i]);
      System.out.print("->");
      i++;
    }
    System.out.println(mhasil[i]);
  }
}
