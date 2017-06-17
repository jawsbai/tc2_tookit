
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
            
            
        }