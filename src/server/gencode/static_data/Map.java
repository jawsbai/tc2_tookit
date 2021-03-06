
        package server.gencode.static_data;
        import java.util.Date;
        import java.util.ArrayList;
        public class Map {
            
            public String key;

            public int width;

            public int height;

            public int cellSize;

            public server.gencode.static_data.MapLabel[] labels;

            public Map(){
                
                this.key="";

                this.width=0;

                this.height=0;

                this.cellSize=0;

                this.labels=new server.gencode.static_data.MapLabel[]{};
            }

            public Map(String key, int width, int height, int cellSize, server.gencode.static_data.MapLabel[] labels){
                
                this.key=key;

                this.width=width;

                this.height=height;

                this.cellSize=cellSize;

                this.labels=labels;
            }
            
            public static Map[] toArray(ArrayList<Map> list){
                int size=list.size();
                Map[] array=new Map[size];
                for (int i = 0; i < size; i++) {
                    array[i]=list.get(i);
                }
                return array;
            }
        }