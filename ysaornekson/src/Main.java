import java.lang.Math;

public class Main {

    public static void main(String[] args) {
        //girdi ve cikti matrisinin olusturulmasi
        final double lamda = 0.5; //ogrenme katsayisisabit deger
        final double alpha = 0.8; // momentum katsayisi sabit deger
        final int theta = 1; // esik degeri katsayisi sabit deger
        double net1 = 0;
        double net2 = 0;
        double net = 0;
        double cikti1 = 0;
        double cikti2 = 0;
        double cikti = 0;
        double hatadegeri = 0;
        double deltaHata = 0; //agirlik degesim miktari
        double beklenencikti=1;
        double yenib00=0;
        double yenib10=0;
        double yenib01=0;
        double yenib11=0;
        double yenic00=0;
        double yenic01=0;
        double yenid00=0;
        double yenid01=0;
        double yenie00=0;
        double deltad00=0;
        double deltad01=0;
        double deltae00=0;
        double girdib00=0;
        double girdib10=0;
        double girdib01=0;
        double girdib11=0;
        double deltaEsikilkc00=0;
        double deltaEsikilkc01=0;
        double deltaAgirlikIlkd01=0;
        double deltaAgirlikIlkd00=0;// bir onceki delta agirlik degeri delta A(t-1)
        double deltaEsikIlke00=0;
        double girdideltab00=0;
        double girdideltab10=0;
        double girdideltab01=0;
        double girdideltab11=0;
        double deltac00=0;
        double deltac01 =0;
        int[][] a = new int[][]{{0, 1}}; // GIRDI MATRISI OLUSTURULMASI
        double[][] b = new double[][]{{0.129952, 0.570345}, {-0.923123, 0.328932}}; //girdi katman agirlik matrisi
        double[][] c = new double[][]{{0.341332, -0.115223}}; //girdi katman esik matrisi (beta i)
        double[][] d = new double[][]{{0.164732, 0.752621}};//ara katman agirlik degeri
        double[][] e = new double[][]{{-0.993423}}; //ara katman esik degeri (beta a)
        int i, j, k, t,iterasyonsayisi=1;
        while (true) {
            if(iterasyonsayisi >1)
            { b[0][0] = yenib00;
              b[1][0] = yenib10;
              b[0][1] = yenib01;
              b[1][1] = yenib11;
              c[0][0] = yenic00;
              c[0][1] = yenic01;
              d[0][0] = yenid00;
              d[0][1] = yenid01;
              e[0][0] = yenie00;
              deltaAgirlikIlkd00=deltad00;
              deltaAgirlikIlkd01= deltad01;
              deltaEsikIlke00= deltae00;
              girdideltab00= girdib00;
              girdideltab10= girdib10;
              girdideltab01= girdib01;
              girdideltab11 = girdib11;
              deltaEsikilkc00=deltac00;
              deltaEsikilkc01=deltac01;

            }
            /*a ve b matrislerinin yazdırılması*/
            System.out.println("A matrisi");
            for (i = 0; i < 1; i++) {
                for (j = 0; j < 2; j++) {
                    System.out.print(a[i][j] + "\t");
                }
                System.out.println();
            }
            System.out.println("B matrisi");
            for (i = 0; i <= 1; i++) {
                for (j = 0; j <= 1; j++) {
                    System.out.print(b[i][j] + "\t");
                }
                System.out.println();
            }
            System.out.println("c matrisi");
            for (i = 0; i < 1; i++) {
                for (j = 0; j <= 1; j++) {
                    System.out.print(c[i][j] + "\t");
                }
                System.out.println();
            }
            System.out.println("d matrisi");
            for (i = 0; i < 1; i++) {
                for (j = 0; j <= 1; j++) {
                    System.out.print(d[i][j] + "\t");
                }
                System.out.println();
            }

            //girdi katmani ileri hesaplama
            net1 = a[0][0] * b[0][0] + a[0][1] * b[1][0] + theta * c[0][0]; //
            System.out.println("net1 degeri:" + net1);
            net2 = a[0][0] * b[0][1] + a[0][1] * b[1][1] + theta * c[0][1];
            System.out.println("net2 degeri:" + net2);
            var arahesap = Math.exp(-net1); //
            var arahesap2 = arahesap + 1;
            cikti1 = 1 / arahesap2; //cikti 1 hesabi
            System.out.println("cikti1 degeri:" + cikti1); //cikti 1 degeri
            var arahesap3 = Math.exp(-net2);
            var arahesap4 = arahesap3 + 1;
            cikti2 = 1 / arahesap4;//cikti 2 hesabi
            System.out.println("cikti2 degeri:" + cikti2);//cikti2  degeri

            //ara katman ileri hesaplama
            net = cikti1 * d[0][0] + cikti2 * d[0][1] + e[0][0] * theta;//cikis katmani net degeri
            System.out.println("net degeri:" + net);
            var ara5 = Math.exp(-net);
            var ara6 = ara5 + 1;
            cikti = 1 / ara6;
            System.out.println("cikti degeri:" + cikti); //cikis katmani cikti degeri

            //hata degeri hesaplamad
            hatadegeri = beklenencikti - cikti;
            System.out.println("hata degeri:" + hatadegeri);
            var hataOrani1 = (beklenencikti * 0.02) + beklenencikti;
            var hataOrani = (-beklenencikti * 0.02) + beklenencikti;

            if (hatadegeri > -0.02 && hatadegeri < 0.02) {
                System.out.println("ogrendi:" +iterasyonsayisi);
                break;
            } else if (hatadegeri > hataOrani && hatadegeri < hataOrani1) {
                System.out.println("ogrendi:"+iterasyonsayisi);
                break;
            } else {
                System.out.println("ogrenmedi");
            }


            // ara katman geriye hesaplama
            deltaHata = cikti * (1 - cikti) * hatadegeri; //sigmond aktivasyonu icin hatanin turevi
            System.out.println("delta hata degeri:" + deltaHata);
            deltad00 = lamda * deltaHata * cikti1 + alpha * deltaAgirlikIlkd00; // d matrisinin d[0][0] ara katmandaki agirlik degisim miktari
            System.out.println("delta d[0][0] agirlik degeri:" + deltad00);
            deltad01 = lamda * deltaHata * cikti2 + alpha * deltaAgirlikIlkd01;// d matrisinin d[0][1] ara katmandaki agirlik degisim miktari
            System.out.println("delta d[0][1] agirlik degeri:" + deltad01);
            deltae00 = lamda * deltaHata + (alpha * deltaEsikIlke00); // ara katman esik degerindeki e[0][0] esik degerindeki fark
            System.out.println("delta esik e[0][0] degeri:" + deltae00);
            yenid00 = d[0][0] + deltad00;
            System.out.println("yeni agirlik d[0][0] degeri:" + yenid00);//ara katman  d[0][0] yeni agirlik degeri
            yenid01 = d[0][1] + deltad01;
            System.out.println("yeni agirlik d[0][1] degeri:" + yenid01);//ara katman  d[0][1] yeni agirlik degeri
            yenie00 = e[0][0] + deltae00;
            System.out.println("yeni esik e[0][0] degeri:" + yenie00); //ara katman yeni esik degeri

            //girdi katman geriye hesaplama
            var girdideltahata1 = cikti1 * (1 - cikti1) * deltaHata * d[0][0]; // girdi ve ara katman arasindaki hata degeri 1
            System.out.println("girdi hata delta 1 degeri:" + girdideltahata1);
            var girdideltahata2 = cikti2 * (1 - cikti2) * deltaHata * d[0][1]; // girdi ve ara katman arasindaki hata degeri 1
            System.out.println("girdi  hata delta 2 degeri:" + girdideltahata2);
            girdib00 = lamda * girdideltahata1 * beklenencikti + alpha * girdideltab00;//0 degerini delta alicak sor
            System.out.println("yeni agirlik deltasi b[0][0] degeri:" + girdib00);
            girdib10 = lamda * girdideltahata2 * beklenencikti + alpha * girdideltab10;//0 degerini delta alicak sor
            System.out.println("girdi  yeni agirlik deltasi b[1][0] degeri:" + girdib10);
            girdib01 = lamda * girdideltahata1 * beklenencikti + alpha * girdideltab01;//0 degerini delta alicak sor
            System.out.println("girdi  yeni agirlik deltasi b[0][1] degeri:" + girdib01);
            girdib11 = lamda * girdideltahata2 * beklenencikti + alpha * girdideltab11;//0 degerini delta alicak sor
            System.out.println("girdi  yeni agirlik deltasi b[1][1] degeri:" + girdib11);
            yenib00 = b[0][0] + girdib00;
            System.out.println("yeni agirlik b[0][0] degeri:" + yenib00);//girdi katman  b[0][0] yeni agirlik degeri
            yenib10 = b[0][0] + girdib10;
            System.out.println("yeni agirlik b[1][0] degeri:" + yenib10);//girdi katman  b[1][0] yeni agirlik degeri
            yenib01 = b[0][1] + girdib01;
            System.out.println("yeni agirlik b[0][1] degeri:" + yenib01);//girdi katman  b[0][1] yeni agirlik degeri
            yenib11 = b[0][1] + girdib11;
            System.out.println("yeni agirlik b[1][1] degeri:" + yenib11);//girdi katman  b[1][1] yeni agirlik degeri
            deltac00 = (lamda * girdideltahata1)+(alpha*deltaEsikilkc00); //girdi esik deger delatasi
            System.out.println("yeni esik delta c[0][0] degeri:" + deltac00);//girdi ve ara katman esik delta degeri 1
            deltac01 = (lamda * girdideltahata2)+(alpha*deltaEsikilkc01);
            System.out.println("yeni esik delta c[0][1] degeri:" + deltac01);//girdi ve ara katman esik delta  degeri 2
            yenic00 = c[0][0] - deltac00;
            System.out.println("yeni esik  c[0][0] degeri:" + yenic00); // girdi ve ara katman yeni esik degeri c[0][0]
            yenic01 = c[0][1] - deltac01;
            System.out.println("yeni esik  c[0][1] degeri:" + yenic01);  //girdi ve ara katman yeni esik degeri c[0][1]
            iterasyonsayisi++;
        }

    }
}






