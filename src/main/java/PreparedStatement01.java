import java.sql.*;
/*
PreparedStatement, Statement interface ini extend eder.
Statement ile olusturduğumuz sorgu için database de bellekte bu sorgunun bir örneği oluşturulur, sorgu her çalıştırıldığnda
          yeni bir örneği oluşturulur
PreparedStatement birden fazla kez çalıştırılabilen parametrelendirilmiş sql sorgularını temsil eder, sorgu oluşturduğumuzda
          bu sorgunun örneği database de bellekte bir kere tutulur,aynı sorgu her çalıştırıldığında aynı önceki örneği kullanılır
 */


public class PreparedStatement01 {
    public static void main(String[] args) throws SQLException {


        Connection con = DriverManager.getConnection("jdbc:postgresql://localhost:5432/jdbc_db", "dev_user", "password");
        Statement st = con.createStatement();

        //ÖRNEK1:(Prepared Statement kullanarak) bolumler tablosunda Matematik bölümünün taban_puanı'nı 475 olarak güncelleyiniz.
        //String sql="UPDATE bolumler SET taban_puanı=475 WHERE bolum ILIKE 'matematik'";

        //Prepared Statement için parametreli query yaz
        String sql="UPDATE bolumler SET taban_puanı=? WHERE bolum ILIKE ?";
        //Prepared Statement oluştur
        PreparedStatement prst= con.prepareStatement(sql);
        //parametrenin değerlerini gir
        prst.setInt(1,475);
        prst.setString(2,"matematik");
        //Prepared Statement ile query i çalıştır
        int updated=prst.executeUpdate();
        System.out.println("updated: "+updated);

        ResultSet rs= st.executeQuery("SELECT * FROM bolumler");
        while (rs.next()){
            System.out.println(rs.getInt("bolum_id")+" "+rs.getString("bolum")+" "+rs.getInt("taban_puanı"));
        }

        System.out.println("**************");

       // ÖRNEK2:Prepared Statement kullanarak bolumler tablosunda Edebiyat bölümünün taban_puanı'nı 455 olarak güncelleyiniz.
        prst.setInt(1,455);
        prst.setString(2,"edebiyat");
        int updated2=prst.executeUpdate();
        System.out.println("updated: "+updated2);



        prst.close();
        st.close();
        con.close();
















    }
}
