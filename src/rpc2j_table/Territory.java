
        package rpc2j_table;
        import java.sql.ResultSet;
        import toolkit.database.expr.EQ;
        import toolkit.database.fields.*;
        import toolkit.database.TableName;
        import toolkit.database.TableDefined;
        import java.util.Date;
        public class Territory {
            public static final INTEGER FD_TERRID=new INTEGER("terrid");
public static final STRING FD_NAME=new STRING("name",50);
public static final INTEGER FD_FACTIONID=new INTEGER("factionid");
public static final TIME FD_CREATETIME=new TIME("createtime");
            
            private int terrId;
            public void setTerrId(int value){
                terrId=value;
            }
            public int getTerrId(){
                return terrId;
            }
private String name;
            public void setName(String value){
                name=value;
            }
            public String getName(){
                return name;
            }
private int factionId;
            public void setFactionId(int value){
                factionId=value;
            }
            public int getFactionId(){
                return factionId;
            }
private Date createTime;
            public void setCreateTime(Date value){
                createTime=value;
            }
            public Date getCreateTime(){
                return createTime;
            }
            
            public Territory(int terrId, String name, int factionId, Date createTime){
                this.terrId=terrId;
this.name=name;
this.factionId=factionId;
this.createTime=createTime;
            }
            
            public static Territory newFromRS(ResultSet rs){
                return new Territory(
                    FD_TERRID.getValue(rs),
FD_NAME.getValue(rs),
FD_FACTIONID.getValue(rs),
FD_CREATETIME.getValue(rs)
                );
            }
            
            public static TableDefined newTableDefined(String tableName){
                return new TableDefined(
                    new TableName(tableName),
                    FD_TERRID,
FD_NAME,
FD_FACTIONID,
FD_CREATETIME
                );
            }
            
            public static TableDefined newTableDefined(){
                return newTableDefined("Territory");
            }
            
            public EQ[] toEQS(){
                return toEQS(this);
            }
            
            public static EQ[] toEQS(Territory table){
                EQ[] eqs=new EQ[4];
                eqs[0]=FD_TERRID.eq(table.terrId);
eqs[1]=FD_NAME.eq(table.name);
eqs[2]=FD_FACTIONID.eq(table.factionId);
eqs[3]=FD_CREATETIME.eq(table.createTime);
                return eqs;
            }
        }