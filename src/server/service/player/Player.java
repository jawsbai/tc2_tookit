package server.service.player;

public class Player {
    public final PlayerService service;

    public Player(PlayerService service) {
        this.service = service;
    }

    protected void update(long et) {

    }
}
