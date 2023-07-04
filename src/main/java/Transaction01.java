import java.sql.*;
/*
Transaction: Databasedeki atomik(parçalanamaz) en küçük işlem
Birden fazla işlem için custom olarak transaction oluşturulabilir.
Bu işlemlerin tamamı başarılı bir şekilde gerçekleşince transaction commit edilir.
En az birinde problem olursa rollback ile tüm işlemler iptal edilir.
 */

public class Transaction01 {
    public static void main(String[] args) throws SQLException {
        Connection con = DriverManager.getConnection("jdbc:postgresql://localhost:5432/jdbc_db", "dev_user", "password");
        Statement st = con.createStatement();

        con.setAutoCommit(false);
        try {
            //hesap no:1234 ten hesap no:5678 e 1000 para transferi olsun.
            String sql = "UPDATE hesaplar SET bakiye=bakiye+? WHERE hesap_no=?";
            PreparedStatement prst = con.prepareStatement(sql);
            prst.setDouble(1, -1000);
            prst.setInt(2, 1234);
            prst.executeUpdate();
            //sistemde HATA oluştu.
            if (true) {
                throw new Exception();
            }
            prst.setDouble(1, 1000);
            prst.setInt(2, 5678);
            prst.executeUpdate();

            prst.close();
            con.commit();
            con.close();
        }catch (Exception e){
            con.rollback();
        }

    }
}



