import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class Execute01 {
    public static void main(String[] args) throws ClassNotFoundException, SQLException {
       //1.Adim:driver i kaydet
        Class.forName("org.postgresql.Driver");//Java 7 ile birlkte gerek kalmadı

        //2.Adim:Database e baglanma
       Connection con= DriverManager.getConnection("jdbc:postgresql://localhost:5432/jdbc_db","dev_user","password");

       //3.Adim:Statement olustur.SQL komutlarini DB ye iletmek ve calistirmak icin
        Statement st=con.createStatement();

        //System.out.println("Connection success");

        //4.Adim:Query(sorgu) calistirma
        //---ÖRNEK1: "workers" adında bit tablo oluşturup "worker_id, worker_name,salary" sutunları ekleyiniz

        boolean sql1=st.execute("CREATE TABLE workers(worker_id INT,worker_name VARCHAR(50),salary REAL)");
        System.out.println("sql1 " +sql1);

        //execute()  DDL veya DQL icin kullanilir
        //DDL(tablo olusturma) icin kullanilirsa: geriye boolean deger olarak false dondurur
        //DQL(select) icin kullanilirsa: geriye ResultSet nesnesi dondurur

        //ÖRNEK 2:"workers" tablosuna VARCHAR(20) tipinde "city" sütununu ekleyiniz.

        String query2="ALTER TABLE workers ADD COLUMN city VARCHAR(20)";
        st.execute(query2);

        //ÖRNEK 3:"workers" tablosunu SCHEMAdan siliniz.

       String query3= "DROP TABLE workers";
       st.execute(query3);

       //5.Adim:Bağlantı ve statement i kapatma
        st.close();
        con.close();


    }
}
