package make;

//A1 B1 C1
//A2 B2 C2
//A3 B3 C3

import com.alibaba.fastjson.JSON;
import server.gencode.static_data.Map;
import server.gencode.static_data.MapLabel;
import server.gencode.static_data.MapLabelType;
import toolkit.helper.FileHelper;
import toolkit.print.Console;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class MapGrid {
    class Label {
        final MapLabelType type;
        final int r;
        final int g;
        final int b;
        final String value;

        public Label(MapLabelType type, int r, int g, int b, String value) {
            this.type = type;
            this.r = r;
            this.g = g;
            this.b = b;
            this.value = value;
        }
    }

    class Block {
        final String key;
        final int bx;
        final int by;

        public Block(String key, int bx, int by) {
            this.key = key;
            this.bx = bx;
            this.by = by;
        }

        int getOffsetX() {
            return bx * SIZE;
        }

        int getOffsetY() {
            return by * SIZE;
        }

        int getCenterX() {
            return getOffsetX() + SIZE / 2;
        }

        int getCenterY() {
            return getOffsetY() + SIZE / 2;
        }
    }

    private static final int SIZE = 128;
    private static final int CELL_SIZE = 32;
    private final Block[] blocks = new Block[]{
            new Block("A1", 0, 0),
            new Block("A2", 0, 1),
            new Block("A3", 0, 2),
            new Block("B1", 1, 0),
            new Block("B2", 1, 1),
            new Block("B3", 1, 2),
            new Block("C1", 2, 0),
            new Block("C2", 2, 1),
            new Block("C3", 2, 2),

    };
    private final Label[] labels = new Label[]{
            new Label(MapLabelType.HEROBORNPOINT, 0, 0, 0xff, ""),
            new Label(MapLabelType.FACTORY, 0, 0x99, 0, ""),
            new Label(MapLabelType.LINK, 0xff, 0, 0xff, ""),
            new Label(MapLabelType.POC, 0xff, 0, 0, ""),
            new Label(MapLabelType.CAVE, 0xff, 0x66, 0, "1"),
            new Label(MapLabelType.CAVE, 0xff, 0x99, 0, "2"),
            new Label(MapLabelType.CAVE, 0xff, 0xcc, 0, "3"),
    };
    private final String configPath;

    public MapGrid(String configPath) throws IOException {
        this.configPath = configPath;

        BufferedImage image = ImageIO.read(new File(configPath + "/map.png"));

        int width = image.getWidth();
        int height = image.getHeight();
        Console.log(width, height);

        int bwc = width / SIZE;
        int bhc = height / SIZE;

        for (int bx = 0; bx < bwc; bx++) {
            for (int by = 0; by < bhc; by++) {
                Block block = findBlock(bx, by);
                makeMap(image, block);
            }
        }
    }

    Block findBlock(int bx, int by) {
        for (Block block : blocks) {
            if (block.bx == bx && block.by == by) {
                return block;
            }
        }
        return null;
    }

    Label findLabel(int r, int g, int b) {
        for (Label label : labels) {
            if (label.r == r && label.g == g && label.b == b) {
                return label;
            }
        }
        return null;
    }

    ArrayList<Block> findAroundBlocks(Block block) {
        ArrayList<Block> list = new ArrayList<>();
        Block top = findBlock(block.bx, block.by - 1);
        Block bottom = findBlock(block.bx, block.by + 1);
        Block left = findBlock(block.bx - 1, block.by);
        Block right = findBlock(block.bx + 1, block.by);
        if (top != null) {
            list.add(top);
        }
        if (bottom != null) {
            list.add(bottom);
        }
        if (left != null) {
            list.add(left);
        }
        if (right != null) {
            list.add(right);
        }
        return list;
    }

    int dis(int x, int y, int x2, int y2) {
        return (int) (Math.pow(x - x2, 2) + Math.pow(y - y2, 2));
    }

    Block findDisBlock(int x, int y, ArrayList<Block> aroundBlocks) {
        int lastDis = Integer.MAX_VALUE;
        Block block = null;
        for (Block item : aroundBlocks) {
            int dis = dis(x, y, item.getCenterX(), item.getCenterY());
            if (dis < lastDis) {
                lastDis = dis;
                block = item;
            }
        }
        return block;
    }

    void makeMap(BufferedImage image, Block block) {
        ArrayList<Block> aroundBlocks = findAroundBlocks(block);
        ArrayList<MapLabel> labels = new ArrayList<>();

        for (int x = 0; x < SIZE; x++) {
            for (int y = 0; y < SIZE; y++) {
                int xx = x + block.getOffsetX();
                int yy = y + block.getOffsetY();
                int rgb = image.getRGB(xx, yy);
                int r = (rgb >> 16) & 0xff;
                int g = (rgb >> 8) & 0xff;
                int b = (rgb) & 0xff;
//                if (r != 0 || g != 0 || b != 0) {
//                    Console.log(xx, yy, r, g, b);
//                }
                Label label = findLabel(r, g, b);
                if (label == null) {
                    continue;
                }

                String value = label.value;

                if (label.type == MapLabelType.LINK) {
                    Block disBlock = findDisBlock(xx, yy, aroundBlocks);
                    value = disBlock.key;
                }

                labels.add(new MapLabel(label.type.getInt(),
                        x * CELL_SIZE,
                        y * CELL_SIZE,
                        value));
            }
        }
        Map map = new Map(block.key, SIZE, SIZE, CELL_SIZE, MapLabel.toArray(labels));
        String json = JSON.toJSONString(map);
        Console.log(json);
        FileHelper.writeString(configPath + "/maps/" + block.key + ".json", json);
    }
}