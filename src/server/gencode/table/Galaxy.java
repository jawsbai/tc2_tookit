
        package server.gencode.table;
        import java.sql.ResultSet;
        import toolkit.database.expr.EQ;
        import toolkit.database.fields.*;
        import toolkit.database.TableName;
        import toolkit.database.TableDefined;
        import java.util.Date;
        public class Galaxy {
            public static final INTEGER FD_GALAXYID=new INTEGER("galaxyid");
public static final TIME FD_CREATETIME=new TIME("createtime");
            
            private int galaxyId;
            public void setGalaxyId(int value){
                galaxyId=value;
            }
            public int getGalaxyId(){
                return galaxyId;
            }
private Date createTime;
            public void setCreateTime(Date value){
                createTime=value;
            }
            public Date getCreateTime(){
                return createTime;
            }
            
            public Galaxy(int galaxyId, Date createTime){
                this.galaxyId=galaxyId;
this.createTime=createTime;
            }
            
            public static Galaxy newFromRS(ResultSet rs){
                return new Galaxy(
                    FD_GALAXYID.getValue(rs),
FD_CREATETIME.getValue(rs)
                );
            }
            
            public static TableDefined newTableDefined(String tableName){
                return new TableDefined(
                    new TableName(tableName),
                    FD_GALAXYID,
FD_CREATETIME
                );
            }
            
            public static TableDefined newTableDefined(){
                return newTableDefined("Galaxy");
            }
            
            public EQ[] toEQS(){
                return toEQS(this);
            }
            
            public static EQ[] toEQS(Galaxy table){
                EQ[] eqs=new EQ[2];
                eqs[0]=FD_GALAXYID.eq(table.galaxyId);
eqs[1]=FD_CREATETIME.eq(table.createTime);
                return eqs;
            }
        }