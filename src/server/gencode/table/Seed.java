
        package server.gencode.table;
        import java.sql.ResultSet;
        import toolkit.database.expr.EQ;
        import toolkit.database.fields.*;
        import toolkit.database.TableName;
        import toolkit.database.TableDefined;
        import java.util.Date;
        public class Seed {
            public static final STRING FD_KEY=new STRING("key",50);
public static final INTEGER FD_INDEX=new INTEGER("index");
public static final TIME FD_CREATETIME=new TIME("createtime");
public static final TIME FD_UPDATETIME=new TIME("updatetime");
            
            private String key;
            public void setKey(String value){
                key=value;
            }
            public String getKey(){
                return key;
            }
private int index;
            public void setIndex(int value){
                index=value;
            }
            public int getIndex(){
                return index;
            }
private Date createTime;
            public void setCreateTime(Date value){
                createTime=value;
            }
            public Date getCreateTime(){
                return createTime;
            }
private Date updateTime;
            public void setUpdateTime(Date value){
                updateTime=value;
            }
            public Date getUpdateTime(){
                return updateTime;
            }
            
            public Seed(String key, int index, Date createTime, Date updateTime){
                this.key=key;
this.index=index;
this.createTime=createTime;
this.updateTime=updateTime;
            }
            
            public static Seed newFromRS(ResultSet rs){
                return new Seed(
                    FD_KEY.getValue(rs),
FD_INDEX.getValue(rs),
FD_CREATETIME.getValue(rs),
FD_UPDATETIME.getValue(rs)
                );
            }
            
            public static TableDefined newTableDefined(String tableName){
                return new TableDefined(
                    new TableName(tableName),
                    FD_KEY,
FD_INDEX,
FD_CREATETIME,
FD_UPDATETIME
                );
            }
            
            public static TableDefined newTableDefined(){
                return newTableDefined("Seed");
            }
            
            public EQ[] toEQS(){
                return toEQS(this);
            }
            
            public static EQ[] toEQS(Seed table){
                EQ[] eqs=new EQ[4];
                eqs[0]=FD_KEY.eq(table.key);
eqs[1]=FD_INDEX.eq(table.index);
eqs[2]=FD_CREATETIME.eq(table.createTime);
eqs[3]=FD_UPDATETIME.eq(table.updateTime);
                return eqs;
            }
        }