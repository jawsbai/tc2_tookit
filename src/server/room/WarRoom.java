package server.room;

import server.service.RoomService;
import toolkit.lang.Return;

import java.util.Objects;

public class WarRoom extends Room<WarGuest> {
    public final int factionMaxGuests;

    public WarRoom(RoomService service, int maxTime, int factionMaxGuests) {
        super(service, maxTime);

        this.factionMaxGuests = factionMaxGuests;
    }

    @Override
    protected void onAdded() {
        super.onAdded();
    }

    @Override
    protected void onRemoved() {
        super.onRemoved();
    }

    private WarGuest findWarGuestByHeroId(String heroId) {
        for (WarGuest guest : guests) {
            if (Objects.equals(guest.hero.getHeroId(), heroId)) {
                return guest;
            }
        }
        return null;
    }

    private int getFactionWarGuests(int factionId) {
        int count = 0;
        for (WarGuest guest : guests) {
            if (guest.hero.getFactionId() == factionId) {
                count++;
            }
        }
        return count;
    }

    //guest add remove
    protected final Return<String> addWarGuest(WarGuest guest) {
        Return<String> rt = new Return<>();
        if (findWarGuestByHeroId(guest.hero.getHeroId()) != null) {
            rt.setCode(1);
        } else if (getFactionWarGuests(guest.hero.getFactionId()) >= factionMaxGuests) {
            rt.setCode(2);
        } else {
            addGuest(guest);
        }
        return rt;
    }

    @Override
    public void update() {
        super.update();
    }
}
