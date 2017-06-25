
        package rpc2j;
        import java.util.Date;
        public class TypeReader extends rpc2j.ByteArrayReader {
            public TypeReader(byte[] bytes){
                super(bytes);
            }
            
            
            public boolean[] readBooleanArray(){
                int length=readInt();
                boolean[] array=new boolean[length];
                for(int i=0;i<length;i++){
                    array[i]=readBoolean();
                }
                return array;
            }

            public byte[] readByteArray(){
                int length=readInt();
                byte[] array=new byte[length];
                for(int i=0;i<length;i++){
                    array[i]=readByte();
                }
                return array;
            }

            public short[] readShortArray(){
                int length=readInt();
                short[] array=new short[length];
                for(int i=0;i<length;i++){
                    array[i]=readShort();
                }
                return array;
            }

            public int[] readIntArray(){
                int length=readInt();
                int[] array=new int[length];
                for(int i=0;i<length;i++){
                    array[i]=readInt();
                }
                return array;
            }

            public Date[] readDateArray(){
                int length=readInt();
                Date[] array=new Date[length];
                for(int i=0;i<length;i++){
                    array[i]=readDate();
                }
                return array;
            }

            public String[] readStringArray(){
                int length=readInt();
                String[] array=new String[length];
                for(int i=0;i<length;i++){
                    array[i]=readString();
                }
                return array;
            }
            
            
            public server.gencode.static_data.Map readMap(){
                return new server.gencode.static_data.Map(
                
                readString(),
                readInt(),
                readInt(),
                readMapLabelArray()
                );
            }
            public server.gencode.static_data.Map[] readMapArray(){
                int length=readInt();
                server.gencode.static_data.Map[] array=new server.gencode.static_data.Map[length];
                for(int i=0;i<length;i++){
                    array[i]=readMap();
                }
                return array;
            }

            public server.gencode.static_data.MapLabel readMapLabel(){
                return new server.gencode.static_data.MapLabel(
                
                readInt(),
                readInt(),
                readInt(),
                readString()
                );
            }
            public server.gencode.static_data.MapLabel[] readMapLabelArray(){
                int length=readInt();
                server.gencode.static_data.MapLabel[] array=new server.gencode.static_data.MapLabel[length];
                for(int i=0;i<length;i++){
                    array[i]=readMapLabel();
                }
                return array;
            }
        }