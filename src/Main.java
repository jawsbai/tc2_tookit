import com.tc2.database.Database;
import com.tc2.database.TableName;
import com.tc2.database.expr.EQS;
import com.tc2.database.expr.INSERT;
import com.tc2.database.fields.INT;
import com.tc2.database.fields.VARCHAR;
import com.tc2.toolkit.net.ws.WebSocket;
import com.tc2.toolkit.print.Console;
import com.tc2.toolkit.promise.Promise;
import com.tc2.toolkit.thread.ActiveObject;
import com.tc2.galaxy.CONST;
import com.tc2.galaxy.tables.TableHero;

import java.io.IOException;
import java.sql.SQLException;

public class Main {
    public static void main(String[] args) {
        galaxyTableTest();
    }

    public static void galaxyTableTest() {
        Database db = new Database();
        try {
            db.connect(CONST.DB_CONN_STR);

            TableHero tableHero = new TableHero(db);
            tableHero.createTable();

            boolean inserted = tableHero.insert(
                    TableHero.HERO_ID.value(1),
                    TableHero.USER_ID.value("user1"),
                    TableHero.HERO_NAME.value("22222"),
                    TableHero.CREATE_TIME.value("11111"));
            Console.log(inserted);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void databaseTest() {

        TableName table1 = new TableName("table1");
        INT field1 = new INT("field1");
        VARCHAR field2 = new VARCHAR("field2", 10);

        Database db = new Database();
        Console.log(db.formatSQL("select * from @0 where @1 and @2",
                table1, field1.EQ(1), field2.EQ("00000000")));

        Console.log(db.formatSQL("set @0",
                new EQS(field1.EQ(1), field2.EQ("00000000"))));

        Console.log(db.formatSQL("@0",
                new INSERT(table1, field1.value(1), field2.value("00000000"))));
    }

    public static void socketTest() {
        try {
            new WebSocket(7788, 1, 1, client -> {
                Console.log(client);

                client.beginReceive();
            });

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void aoTest() {

        ActiveObject ao = new ActiveObject(1);
        ao.start();

//        ao.timeout(() -> {
//            Console.log("22222222222");
//        }, 1000);
//
//        ao.interval(() -> {
//            Console.log("ssssssssssssss");
//        }, 1000);

        ao.invoke(() -> {
            Promise[] promises = {
                    new Promise<>("ssssssss")
            };
            Promise.all(promises).then(() -> {
                Console.log("all");
            }).catch_(e -> {
                Console.log("all", e);
            }).finish(() -> {
                Console.log("all finish");
            });


            new Promise<>("1111111").then(result -> {
                Console.log("p1.then", result);

                byte[] bs = {};

//                Console.log(bs[100]);

            }).catch_(e -> {
                Console.log("catch_");
                Console.log(e);
            }).then(result -> {
                Console.log("ssssssssssssssss222222", result);
            }).finish(() -> {
                Console.log("finish");
            }).then(() -> {
                Console.log("ssssssssssss");
            });
        });
    }
}
