
        package rpc2j_table;
        import java.sql.ResultSet;
        import toolkit.database.expr.EQ;
        import toolkit.database.fields.*;
        import toolkit.database.TableName;
        import toolkit.database.TableDefined;
        import java.util.Date;
        public class Unit {
            public static final STRING FD_UNITID=new STRING("unitid",50);
public static final STRING FD_HEROID=new STRING("heroid",50);
public static final STRING FD_NAME=new STRING("name",50);
public static final TIME FD_CREATETIME=new TIME("createtime");
public static final STRING FD_DATAID=new STRING("dataid",50);
public static final STRING FD_EQUIPS=new STRING("equips",200);
public static final INTEGER FD_LEVEL=new INTEGER("level");
public static final INTEGER FD_EXP=new INTEGER("exp");
public static final INTEGER FD_HEALTH=new INTEGER("health");
public static final INTEGER FD_DEATHS=new INTEGER("deaths");
            
            private String unitId;
            public void setUnitId(String value){
                unitId=value;
            }
            public String getUnitId(){
                return unitId;
            }
private String heroId;
            public void setHeroId(String value){
                heroId=value;
            }
            public String getHeroId(){
                return heroId;
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
private String dataId;
            public void setDataId(String value){
                dataId=value;
            }
            public String getDataId(){
                return dataId;
            }
private String equips;
            public void setEquips(String value){
                equips=value;
            }
            public String getEquips(){
                return equips;
            }
private int level;
            public void setLevel(int value){
                level=value;
            }
            public int getLevel(){
                return level;
            }
private int exp;
            public void setExp(int value){
                exp=value;
            }
            public int getExp(){
                return exp;
            }
private int health;
            public void setHealth(int value){
                health=value;
            }
            public int getHealth(){
                return health;
            }
private int deaths;
            public void setDeaths(int value){
                deaths=value;
            }
            public int getDeaths(){
                return deaths;
            }
            
            public Unit(String unitId, String heroId, String name, Date createTime, String dataId, String equips, int level, int exp, int health, int deaths){
                this.unitId=unitId;
this.heroId=heroId;
this.name=name;
this.createTime=createTime;
this.dataId=dataId;
this.equips=equips;
this.level=level;
this.exp=exp;
this.health=health;
this.deaths=deaths;
            }
            
            public static Unit newFromRS(ResultSet rs){
                return new Unit(
                    FD_UNITID.getValue(rs),
FD_HEROID.getValue(rs),
FD_NAME.getValue(rs),
FD_CREATETIME.getValue(rs),
FD_DATAID.getValue(rs),
FD_EQUIPS.getValue(rs),
FD_LEVEL.getValue(rs),
FD_EXP.getValue(rs),
FD_HEALTH.getValue(rs),
FD_DEATHS.getValue(rs)
                );
            }
            
            public static TableDefined newTableDefined(){
                return new TableDefined(
                    new TableName("Unit"),
                    FD_UNITID,
FD_HEROID,
FD_NAME,
FD_CREATETIME,
FD_DATAID,
FD_EQUIPS,
FD_LEVEL,
FD_EXP,
FD_HEALTH,
FD_DEATHS
                );
            }
            
            public EQ[] toEQS(){
                return toEQS(this);
            }
            
            public static EQ[] toEQS(Unit table){
                EQ[] eqs=new EQ[10];
                eqs[0]=FD_UNITID.eq(table.unitId);
eqs[1]=FD_HEROID.eq(table.heroId);
eqs[2]=FD_NAME.eq(table.name);
eqs[3]=FD_CREATETIME.eq(table.createTime);
eqs[4]=FD_DATAID.eq(table.dataId);
eqs[5]=FD_EQUIPS.eq(table.equips);
eqs[6]=FD_LEVEL.eq(table.level);
eqs[7]=FD_EXP.eq(table.exp);
eqs[8]=FD_HEALTH.eq(table.health);
eqs[9]=FD_DEATHS.eq(table.deaths);
                return eqs;
            }
        }