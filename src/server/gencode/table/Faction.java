
        package server.gencode.table;
        import java.sql.ResultSet;
        import toolkit.database.expr.EQ;
        import toolkit.database.fields.*;
        import toolkit.database.TableName;
        import toolkit.database.TableDefined;
        import java.util.Date;
        public class Faction {
            public static final INTEGER FD_FACTIONID=new INTEGER("factionid");
public static final STRING FD_NAME=new STRING("name",50);
public static final STRING FD_CAPITAL=new STRING("capital",50);
public static final TIME FD_CREATETIME=new TIME("createtime");
            
            private int factionId;
            public void setFactionId(int value){
                factionId=value;
            }
            public int getFactionId(){
                return factionId;
            }
private String name;
            public void setName(String value){
                name=value;
            }
            public String getName(){
                return name;
            }
private String capital;
            public void setCapital(String value){
                capital=value;
            }
            public String getCapital(){
                return capital;
            }
private Date createTime;
            public void setCreateTime(Date value){
                createTime=value;
            }
            public Date getCreateTime(){
                return createTime;
            }
            
            public Faction(int factionId, String name, String capital, Date createTime){
                this.factionId=factionId;
this.name=name;
this.capital=capital;
this.createTime=createTime;
            }
            
            public static Faction newFromRS(ResultSet rs){
                return new Faction(
                    FD_FACTIONID.getValue(rs),
FD_NAME.getValue(rs),
FD_CAPITAL.getValue(rs),
FD_CREATETIME.getValue(rs)
                );
            }
            
            public static TableDefined newTableDefined(String tableName){
                return new TableDefined(
                    new TableName(tableName),
                    FD_FACTIONID,
FD_NAME,
FD_CAPITAL,
FD_CREATETIME
                );
            }
            
            public static TableDefined newTableDefined(){
                return newTableDefined("Faction");
            }
            
            public EQ[] toEQS(){
                return toEQS(this);
            }
            
            public static EQ[] toEQS(Faction table){
                EQ[] eqs=new EQ[4];
                eqs[0]=FD_FACTIONID.eq(table.factionId);
eqs[1]=FD_NAME.eq(table.name);
eqs[2]=FD_CAPITAL.eq(table.capital);
eqs[3]=FD_CREATETIME.eq(table.createTime);
                return eqs;
            }
        }