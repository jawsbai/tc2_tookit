//package server.space;
//
//import server.service.SpaceService;
//
//import java.util.Objects;
//
//public class WarSpace extends Space<WarSpaceObject> {
//
//    public WarSpace(SpaceService service, int maxTime) {
//        super(service, maxTime);
//    }
//
//    private WarSpaceObject findWarGuestByHeroId(String heroId) {
//        for (WarSpaceObject obj : objects) {
//            if (Objects.equals(obj.getHeroId(), heroId)) {
//                return obj;
//            }
//        }
//        return null;
//    }
//
//    private int getFactionWarGuests(int factionId) {
//        int count = 0;
//        for (WarSpaceObject obj : objects) {
//            if (obj.getFactionId() == factionId) {
//                count++;
//            }
//        }
//        return count;
//    }
//
////    //guest add remove
////    public final Return<String> addWarSpaceObject(WarSpaceObject warSpaceObject) {
////        Return<String> rt = new Return<>();
////        if (findWarGuestByHeroId(guest.getHeroId()) != null) {
////            rt.setCode(1);
////        } else if (getFactionWarGuests(guest.getFactionId()) >= factionMaxGuests) {
////            rt.setCode(2);
////        } else {
////            addGuest(guest);
////        }
////        return rt;
////    }
//}
