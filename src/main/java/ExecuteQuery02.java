import java.sql.*;

public class ExecuteQuery02 {
    public static void main(String[] args) throws SQLException {
        Connection con = DriverManager.getConnection("jdbc:postgresql://localhost:5432/jdbc_db", "dev_user", "password");
        Statement st = con.createStatement();

        //ÖRNEK1:bolumler tablosunda taban puanı en yüksek 2. bölümün ismini ve puanını yazdırınız.

        String query1 = "SELECT bolum,taban_puanı FROM bolumler ORDER BY taban_puanı DESC OFFSET 1 LIMIT 1";
        ResultSet rs1 = st.executeQuery(query1);

        while (rs1.next()) {
            System.out.println(rs1.getString("bolum") + " " + rs1.getInt("taban_puanı"));
        }
        //subquery
        String sql1 = "SELECT bolum,taban<-puanı FROM bolumler WHERE taban_puanı=" +
                "(SELECT max(taban_puanı) FROM bolumler WHERE taban_puanı<(SELECT max(taban_puanı) FROM bolumler ))";
        System.out.println("**************");
        //ÖRNEK2:Bölüm isimlerini, kampüslerini ve
        //her bölümde okuyan öğrencilerin en yüksek puanlarını listeleyiniz.

        String sql2="SELECT bolum,kampus,(SELECT max(puan) FROM ogrenciler o WHERE o.bolum=b.bolum) max_puan FROM bolumler b";
        ResultSet rs2= st.executeQuery(sql2);

        while(rs2.next()){
            System.out.println(rs2.getString("bolum")+" "+rs2.getString("kampus")+" "+rs2.getInt("max_puan"));
        }
        st.close();
        con.close();





    }
}
