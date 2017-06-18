
        package rpc2j_table;
        import java.sql.ResultSet;
        import toolkit.database.expr.EQ;
        import toolkit.database.fields.*;
        import toolkit.database.TableName;
        import toolkit.database.TableDefined;
        import java.util.Date;
        public class Hero {
            public static final STRING FD_HEROID=new STRING("heroid",50);
public static final STRING FD_PLAYERID=new STRING("playerid",50);
public static final STRING FD_NICKNAME=new STRING("nickname",50);
public static final TIME FD_CREATETIME=new TIME("createtime");
public static final BOOL FD_PLAYING=new BOOL("playing");
public static final INTEGER FD_PLANETID=new INTEGER("planetid");
public static final INTEGER FD_COUNTRYID=new INTEGER("countryid");
public static final STRING FD_AT=new STRING("at",100);
public static final STRING FD_DEPLOYS=new STRING("deploys",200);
public static final INTEGER FD_MONEY=new INTEGER("money");
public static final INTEGER FD_ORE=new INTEGER("ore");
public static final INTEGER FD_URANIUM=new INTEGER("uranium");
public static final INTEGER FD_SULFUR=new INTEGER("sulfur");
public static final INTEGER FD_SILICON=new INTEGER("silicon");
            
            private String heroId;
            public void setHeroId(String value){
                heroId=value;
            }
            public String getHeroId(){
                return heroId;
            }
private String playerId;
            public void setPlayerId(String value){
                playerId=value;
            }
            public String getPlayerId(){
                return playerId;
            }
private String nickname;
            public void setNickname(String value){
                nickname=value;
            }
            public String getNickname(){
                return nickname;
            }
private Date createTime;
            public void setCreateTime(Date value){
                createTime=value;
            }
            public Date getCreateTime(){
                return createTime;
            }
private boolean playing;
            public void setPlaying(boolean value){
                playing=value;
            }
            public boolean getPlaying(){
                return playing;
            }
private int planetId;
            public void setPlanetId(int value){
                planetId=value;
            }
            public int getPlanetId(){
                return planetId;
            }
private int countryId;
            public void setCountryId(int value){
                countryId=value;
            }
            public int getCountryId(){
                return countryId;
            }
private String at;
            public void setAt(String value){
                at=value;
            }
            public String getAt(){
                return at;
            }
private String deploys;
            public void setDeploys(String value){
                deploys=value;
            }
            public String getDeploys(){
                return deploys;
            }
private int money;
            public void setMoney(int value){
                money=value;
            }
            public int getMoney(){
                return money;
            }
private int ore;
            public void setOre(int value){
                ore=value;
            }
            public int getOre(){
                return ore;
            }
private int uranium;
            public void setUranium(int value){
                uranium=value;
            }
            public int getUranium(){
                return uranium;
            }
private int sulfur;
            public void setSulfur(int value){
                sulfur=value;
            }
            public int getSulfur(){
                return sulfur;
            }
private int silicon;
            public void setSilicon(int value){
                silicon=value;
            }
            public int getSilicon(){
                return silicon;
            }
            
            public Hero(String heroId, String playerId, String nickname, Date createTime, boolean playing, int planetId, int countryId, String at, String deploys, int money, int ore, int uranium, int sulfur, int silicon){
                this.heroId=heroId;
this.playerId=playerId;
this.nickname=nickname;
this.createTime=createTime;
this.playing=playing;
this.planetId=planetId;
this.countryId=countryId;
this.at=at;
this.deploys=deploys;
this.money=money;
this.ore=ore;
this.uranium=uranium;
this.sulfur=sulfur;
this.silicon=silicon;
            }
            
            public static Hero newFromRS(ResultSet rs){
                return new Hero(
                    FD_HEROID.getValue(rs),
FD_PLAYERID.getValue(rs),
FD_NICKNAME.getValue(rs),
FD_CREATETIME.getValue(rs),
FD_PLAYING.getValue(rs),
FD_PLANETID.getValue(rs),
FD_COUNTRYID.getValue(rs),
FD_AT.getValue(rs),
FD_DEPLOYS.getValue(rs),
FD_MONEY.getValue(rs),
FD_ORE.getValue(rs),
FD_URANIUM.getValue(rs),
FD_SULFUR.getValue(rs),
FD_SILICON.getValue(rs)
                );
            }
            
            public static TableDefined newTableDefined(String tableName){
                return new TableDefined(
                    new TableName(tableName),
                    FD_HEROID,
FD_PLAYERID,
FD_NICKNAME,
FD_CREATETIME,
FD_PLAYING,
FD_PLANETID,
FD_COUNTRYID,
FD_AT,
FD_DEPLOYS,
FD_MONEY,
FD_ORE,
FD_URANIUM,
FD_SULFUR,
FD_SILICON
                );
            }
            
            public static TableDefined newTableDefined(){
                return newTableDefined("Hero");
            }
            
            public EQ[] toEQS(){
                return toEQS(this);
            }
            
            public static EQ[] toEQS(Hero table){
                EQ[] eqs=new EQ[14];
                eqs[0]=FD_HEROID.eq(table.heroId);
eqs[1]=FD_PLAYERID.eq(table.playerId);
eqs[2]=FD_NICKNAME.eq(table.nickname);
eqs[3]=FD_CREATETIME.eq(table.createTime);
eqs[4]=FD_PLAYING.eq(table.playing);
eqs[5]=FD_PLANETID.eq(table.planetId);
eqs[6]=FD_COUNTRYID.eq(table.countryId);
eqs[7]=FD_AT.eq(table.at);
eqs[8]=FD_DEPLOYS.eq(table.deploys);
eqs[9]=FD_MONEY.eq(table.money);
eqs[10]=FD_ORE.eq(table.ore);
eqs[11]=FD_URANIUM.eq(table.uranium);
eqs[12]=FD_SULFUR.eq(table.sulfur);
eqs[13]=FD_SILICON.eq(table.silicon);
                return eqs;
            }
        }