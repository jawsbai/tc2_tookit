
        package rpc2j;
        public abstract class EndRemote {
            public EndRemote() {
            }
        
            protected abstract int newMessageID();        
            protected abstract void sendMessage(TypeWriter writer, Message message);            
            protected abstract void handle(TypeReader reader, Message message);
        }