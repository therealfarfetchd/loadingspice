package therealfarfetchd.loadingspice.api;

import net.fabricmc.loader.api.FabricLoader;

import java.util.Collections;
import java.util.List;

import therealfarfetchd.loadingspice.LoadingProgressImpl;
import therealfarfetchd.loadingspice.LoadingSpice;

public class APIInternals {

    static LoadingProgress getLoadingProgress() {
        if (FabricLoader.getInstance().isModLoaded(LoadingSpice.MODID)) {
            return LoadingProgressImpl.INSTANCE;
        } else {
            return DummyLoadingProgress.INSTANCE;
        }
    }

    private static class DummyLoadingProgress implements LoadingProgress {

        @Override
        public TaskInfo.Mutable pushTask() {
            return DummyTaskInfo.INSTANCE;
        }

        @Override
        public void popTask(TaskInfo ti) {}

        @Override
        public void popTask() {}

        private static class DummyTaskInfo implements TaskInfo.Mutable {

            public static final DummyTaskInfo INSTANCE = new DummyTaskInfo();

            @Override
            public TaskInfo.Mutable withTaskName(String name) {
                return this;
            }

            @Override
            public TaskInfo getParent() {
                return null;
            }

            @Override
            public List<TaskInfo> getChildren() {
                return Collections.emptyList();
            }

            @Override
            public String getText() {
                return "";
            }

        }

    }

}
