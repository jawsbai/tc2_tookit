package server.room;

import server.service.RoomService;

public class BattleRoom extends Room<BattleRObject> {
    public BattleRoom(RoomService service, int maxTime) {
        super(service, maxTime);
    }

    protected final boolean addBrObject(BattleRObject brObject) {
        return addObject(brObject);
    }

    protected final boolean removeBrObject(BattleRObject brObject) {
        return removeObject(brObject);
    }

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

//    //guest add remove
//    public final Return<String> addWarSpaceObject(WarSpaceObject warSpaceObject) {
//        Return<String> rt = new Return<>();
//        if (findWarGuestByHeroId(guest.getHeroId()) != null) {
//            rt.setCode(1);
//        } else if (getFactionWarGuests(guest.getFactionId()) >= factionMaxGuests) {
//            rt.setCode(2);
//        } else {
//            addObject(guest);
//        }
//        return rt;
//    }
}
