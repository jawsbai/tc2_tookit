import com.tc2.server.init.Server;
import com.tc2.toolkit.net.ws.WebSocket;
import com.tc2.toolkit.print.Console;
import com.tc2.toolkit.promise.Promise;
import com.tc2.toolkit.thread.ActiveObject;

import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        galaxyStart();
    }

    public static void galaxyStart() {
        try {
            String dataPath = System.getProperty("user.dir") + "/data";
            Server server = new Server(dataPath);
            server.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void galaxyTableTest() {
//        Database db = new Database();
//        try {
////            db.connect(CONST.DB_CONN_STR);
//
//            TableHero tableHero = new TableHero(db);
//            tableHero.createTable();
//
//            for (int i = 1; i < 6; i++) {
//                tableHero.insert(
//                        TableHero.FD_HERO_ID.eq(i),
//                        TableHero.FD_HERO_ID.eq("user" + i),
//                        TableHero.FD_HERO_NAME.eq("name" + i),
//                        TableHero.FD_CREATE_TIME.eq("11111"));
//            }
//
//
//            Console.log(tableHero.delete(new WHERE(TableHero.FD_HERO_ID.eq(3))));
//            Console.log(tableHero.delete(new WHERE(TableHero.FD_HERO_ID.eq(2))));
//
//            Console.log(tableHero.exists(new WHERE(TableHero.FD_HERO_ID.eq(2))));
//            Console.log(tableHero.exists(new WHERE(TableHero.FD_HERO_ID.eq(1))));
//
//            Console.log(tableHero.count(new WHERE(TableHero.FD_HERO_ID.eq(1))
//                    .and(TableHero.FD_HERO_ID.eq("user1"))));
//
//            tableHero.update(
//                    new EQ[]{TableHero.FD_HERO_NAME.eq("update")},
//                    new WHERE(TableHero.FD_HERO_ID.eq(1)));
//
//
//            tableHero.select(new WHERE(), new ORDER_BY(TableHero.FD_HERO_ID, "desc"), 100, (ResultSet rs) -> {
//                Console.log(TableHero.FD_HERO_ID.getValue(rs));
//            });
//
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
    }

    public static void databaseTest() {

//        TableName table1 = new TableName("table1");
//        INT field1 = new INT("field1");
//        VARCHAR field2 = new VARCHAR("field2", 10);
//
//        Database db = new Database();
//        Console.log(db.formatSQL("select * from @0 where @1 and @2",
//                table1, field1.eq(1), field2.eq("00000000")));
//
//        Console.log(db.formatSQL("set @0",
//                new EQS(field1.eq(1), field2.eq("00000000"))));
//
//        Console.log(db.formatSQL("@0",
//                new INSERT(table1, field1.eq(1), field2.eq("00000000"))));
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
