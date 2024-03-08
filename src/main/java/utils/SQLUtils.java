package utils;

public class SQLUtils {
    public  static class PersonSQL{

        // all constant must in uppercase
        public static final String getAllPersonSql= """
            select * from persons;
            """;
        public static final String insertNewPerson= """
                INSERT INTO persons ("fullname","gender","email","address") VALUES(?,?,?,?);
                """;

        public  static final String deletePersonById = """
                delete from persons where id = ?
                """;

        public  static final String updatePerson= """
                update persons set  fullname=?,gender=?,email=?,address=?
                where id = ?
                """;
    }
    public static class UserSQL{
public static final String CREATENEWUSERS = """
        INSERT INTO users ("username","password") VALUES(?,?);
""";

public static final String LOGINUSER = """
        SELECT * FROM users WHERE username = ? AND password = ?""";
    }
}
