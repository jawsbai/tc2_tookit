
        package server.gencode.table;
        import java.sql.ResultSet;
        import toolkit.database.expr.EQ;
        import toolkit.database.fields.*;
        import toolkit.database.TableName;
        import toolkit.database.TableDefined;
        import java.util.Date;
        public class OperationLog {
            public static final INTEGER_AUTO_INCREMENT FD_ID=new INTEGER_AUTO_INCREMENT("id");
public static final STRING FD_RELATEID=new STRING("relateid",50);
public static final STRING FD_TYPE=new STRING("type",50);
public static final STRING FD_DEPICT=new STRING("depict",200);
public static final TIME FD_TIME=new TIME("time");
            
            private int id;
            public void setId(int value){
                id=value;
            }
            public int getId(){
                return id;
            }
private String relateId;
            public void setRelateId(String value){
                relateId=value;
            }
            public String getRelateId(){
                return relateId;
            }
private String type;
            public void setType(String value){
                type=value;
            }
            public String getType(){
                return type;
            }
private String depict;
            public void setDepict(String value){
                depict=value;
            }
            public String getDepict(){
                return depict;
            }
private Date time;
            public void setTime(Date value){
                time=value;
            }
            public Date getTime(){
                return time;
            }
            
            public OperationLog(int id, String relateId, String type, String depict, Date time){
                this.id=id;
this.relateId=relateId;
this.type=type;
this.depict=depict;
this.time=time;
            }
            
            public static OperationLog newFromRS(ResultSet rs){
                return new OperationLog(
                    FD_ID.getValue(rs),
FD_RELATEID.getValue(rs),
FD_TYPE.getValue(rs),
FD_DEPICT.getValue(rs),
FD_TIME.getValue(rs)
                );
            }
            
            public static TableDefined newTableDefined(String tableName){
                return new TableDefined(
                    new TableName(tableName),
                    FD_ID,
FD_RELATEID,
FD_TYPE,
FD_DEPICT,
FD_TIME
                );
            }
            
            public static TableDefined newTableDefined(){
                return newTableDefined("OperationLog");
            }
            
            public EQ[] toEQS(){
                return toEQS(this);
            }
            
            public static EQ[] toEQS(OperationLog table){
                EQ[] eqs=new EQ[5];
                eqs[0]=FD_ID.eq(table.id);
eqs[1]=FD_RELATEID.eq(table.relateId);
eqs[2]=FD_TYPE.eq(table.type);
eqs[3]=FD_DEPICT.eq(table.depict);
eqs[4]=FD_TIME.eq(table.time);
                return eqs;
            }
        }