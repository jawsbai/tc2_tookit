//package server.space;
//
//import server.hero.IHero;
//import toolkit.promise.Promise;
//
//public class WarSpaceObject extends SObject<WarSpace> {
//    private final IHero hero;
//
//    public WarSpaceObject(WarSpace space, int queueTime, IHero hero) {
//        super(space, queueTime);
//
//        this.hero = hero;
//    }
//
//    public String getHeroId() {
//        return hero.getHeroId();
//    }
//
//    public int getFactionId() {
//        return hero.getFactionId();
//    }
//
//    //add
////    public Promise<Return<String>> addToWarRoom() {
////        Deferred<Return<String>> defer = new Deferred<>();
////        room.service.invoke(() -> {
////            Return<String> rt = room.addWarGuest(this);
////            if (rt.isSuccess()) {
////                onAdded();
////            }
////            defer.resolve(rt);
////        });
////        return defer.promise();
////    }
//
//    public final Promise<Boolean> removeFromWarSpace() {
//        return removeFromSpace();
//    }
//
//}
