
        package server.gencode.static_data;
        import java.util.Objects;
        public class MapLabelType {
            public final String key;
            public final Object value;
        
            public MapLabelType(String key, Object value){
                this.key=key;
                this.value=value;
            }
            
            public int getInt(){
                if(value instanceof Integer){
                    return (int)value;
                }
                return 0;
            }
            
            public String getString(){
                if(value instanceof String){
                    return (String)value;
                }
                return "";
            }
            
            public Boolean getBoolean(){
                if(value instanceof Boolean){
                    return (Boolean)value;
                }
                return false;
            }
            
            public static final MapLabelType FACTORY=new MapLabelType("factory", 100);
public static final MapLabelType CAVE1=new MapLabelType("cave1", 401);
public static final MapLabelType CAVE2=new MapLabelType("cave2", 402);
public static final MapLabelType CAVE3=new MapLabelType("cave3", 403);
public static final MapLabelType HEROBORNPOINT=new MapLabelType("heroBornPoint", 1);
public static final MapLabelType LINK=new MapLabelType("link", 2);
public static final MapLabelType POC=new MapLabelType("poc", 3);
            
            private static final MapLabelType[] items=new MapLabelType[]{
                FACTORY,CAVE1,CAVE2,CAVE3,HEROBORNPOINT,LINK,POC
            };
            
            public static MapLabelType[] getItems(){
                int len=items.length;
                MapLabelType[] newItems=new MapLabelType[len];
                System.arraycopy(items,0,newItems,0,len);
                return newItems;
            }
            
            public static MapLabelType getByKey(String key) {
                for (MapLabelType item : items) {
                    if (Objects.equals(item.key, key)) {
                        return item;
                    }
                }
                return null;
            }
            
            public static MapLabelType getByValue(Object value) {
                for (MapLabelType item : items) {
                    if (Objects.equals(item.value, value)) {
                        return item;
                    }
                }
                return null;
            }
        }