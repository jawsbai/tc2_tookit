
        package rpc2j;
        public class Receiver {
            private final EndRemote remote;
            private final EndLocal local;
            public Receiver(EndRemote remote, EndLocal local) {
                this.remote=remote;
                this.local=local;
            }
            
            public void receive(byte[] bytes){
                TypeReader reader=new TypeReader(bytes);
                Message message=Message.read(reader);
                if(message.type==1 || message.type==3){
                    this.remote.handle(reader, message);
                }else if(message.type==2){
                    this.local.handle(reader, message);
                }
            }
        }