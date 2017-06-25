
        package server.gencode.static_data;
        import java.util.Date;
        import java.util.ArrayList;
        public class MapLabel {
            
            public final int type;

            public final int x;

            public final int y;

            public final String value;

            public MapLabel(){
                
                this.type=0;

                this.x=0;

                this.y=0;

                this.value="";
            }

            public MapLabel(int type, int x, int y, String value){
                
                this.type=type;

                this.x=x;

                this.y=y;

                this.value=value;
            }
            
            public static MapLabel[] toArray(ArrayList<MapLabel> list){
                int size=list.size();
                MapLabel[] array=new MapLabel[size];
                for (int i = 0; i < size; i++) {
                    array[i]=list.get(i);
                }
                return array;
            }
        }