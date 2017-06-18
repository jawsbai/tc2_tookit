
        package rpc2j_table;
        import java.sql.ResultSet;
        import toolkit.database.expr.EQ;
        import toolkit.database.fields.*;
        import toolkit.database.TableName;
        import toolkit.database.TableDefined;
        import java.util.Date;
        public class Country {
            public static final INTEGER FD_COUNTRYID=new INTEGER("countryid");
public static final STRING FD_NAME=new STRING("name",50);
public static final STRING FD_CAPITAL=new STRING("capital",50);
public static final TIME FD_CREATETIME=new TIME("createtime");
            
            private int countryId;
            public void setCountryId(int value){
                countryId=value;
            }
            public int getCountryId(){
                return countryId;
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
            
            public Country(int countryId, String name, String capital, Date createTime){
                this.countryId=countryId;
this.name=name;
this.capital=capital;
this.createTime=createTime;
            }
            
            public static Country newFromRS(ResultSet rs){
                return new Country(
                    FD_COUNTRYID.getValue(rs),
FD_NAME.getValue(rs),
FD_CAPITAL.getValue(rs),
FD_CREATETIME.getValue(rs)
                );
            }
            
            public static TableDefined newTableDefined(String tableName){
                return new TableDefined(
                    new TableName(tableName),
                    FD_COUNTRYID,
FD_NAME,
FD_CAPITAL,
FD_CREATETIME
                );
            }
            
            public static TableDefined newTableDefined(){
                return newTableDefined("Country");
            }
            
            public EQ[] toEQS(){
                return toEQS(this);
            }
            
            public static EQ[] toEQS(Country table){
                EQ[] eqs=new EQ[4];
                eqs[0]=FD_COUNTRYID.eq(table.countryId);
eqs[1]=FD_NAME.eq(table.name);
eqs[2]=FD_CAPITAL.eq(table.capital);
eqs[3]=FD_CREATETIME.eq(table.createTime);
                return eqs;
            }
        }