
        package rpc2j;
        import java.util.Date;
        public class TypeWriter extends rpc2j.ByteArrayWriter {
            
            
            public void writeBooleanArray(boolean[] array){
                int length=array.length;
                writeInt(length);
                for(int i=0;i<length;i++){
                    writeBoolean(array[i]);
                }
            }

            public void writeByteArray(byte[] array){
                int length=array.length;
                writeInt(length);
                for(int i=0;i<length;i++){
                    writeByte(array[i]);
                }
            }

            public void writeShortArray(short[] array){
                int length=array.length;
                writeInt(length);
                for(int i=0;i<length;i++){
                    writeShort(array[i]);
                }
            }

            public void writeIntArray(int[] array){
                int length=array.length;
                writeInt(length);
                for(int i=0;i<length;i++){
                    writeInt(array[i]);
                }
            }

            public void writeDateArray(Date[] array){
                int length=array.length;
                writeInt(length);
                for(int i=0;i<length;i++){
                    writeDate(array[i]);
                }
            }

            public void writeStringArray(String[] array){
                int length=array.length;
                writeInt(length);
                for(int i=0;i<length;i++){
                    writeString(array[i]);
                }
            }
            
            
        }