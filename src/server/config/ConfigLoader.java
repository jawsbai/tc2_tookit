package server.config;

import toolkit.helper.FileHelper;

public class ConfigLoader {
    private final String folder;

    public ConfigLoader(String folder) {
        this.folder = folder;
    }

    public <T> T load(Class<T> t, String file) throws Exception {
        T root = FileHelper.readJson(t, folder + file);
        if (root == null) {
            throw new Exception("load " + t.getSimpleName() + " error");
        }
        return root;
    }
}
