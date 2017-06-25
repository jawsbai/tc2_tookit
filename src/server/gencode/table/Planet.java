
        package server.gencode.table;
        import java.sql.ResultSet;
        import toolkit.database.expr.EQ;
        import toolkit.database.fields.*;
        import toolkit.database.TableName;
        import toolkit.database.TableDefined;
        import java.util.Date;
        public class Planet {
            public static final INTEGER FD_PLANETID=new INTEGER("planetid");
public static final STRING FD_NAME=new STRING("name",50);
public static final TIME FD_CREATETIME=new TIME("createtime");
            
            private int planetId;
            public void setPlanetId(int value){
                planetId=value;
            }
            public int getPlanetId(){
                return planetId;
            }
private String name;
            public void setName(String value){
                name=value;
            }
            public String getName(){
                return name;
            }
private Date createTime;
            public void setCreateTime(Date value){
                createTime=value;
            }
            public Date getCreateTime(){
                return createTime;
            }
            
            public Planet(int planetId, String name, Date createTime){
                this.planetId=planetId;
this.name=name;
this.createTime=createTime;
            }
            
            public static Planet newFromRS(ResultSet rs){
                return new Planet(
                    FD_PLANETID.getValue(rs),
FD_NAME.getValue(rs),
FD_CREATETIME.getValue(rs)
                );
            }
            
            public static TableDefined newTableDefined(String tableName){
                return new TableDefined(
                    new TableName(tableName),
                    FD_PLANETID,
FD_NAME,
FD_CREATETIME
                );
            }
            
            public static TableDefined newTableDefined(){
                return newTableDefined("Planet");
            }
            
            public EQ[] toEQS(){
                return toEQS(this);
            }
            
            public static EQ[] toEQS(Planet table){
                EQ[] eqs=new EQ[3];
                eqs[0]=FD_PLANETID.eq(table.planetId);
eqs[1]=FD_NAME.eq(table.name);
eqs[2]=FD_CREATETIME.eq(table.createTime);
                return eqs;
            }
        }