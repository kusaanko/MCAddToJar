package io.github.kusaanko;

import com.google.gson.InstanceCreator;

import java.lang.reflect.Type;
import java.nio.file.Path;
import java.nio.file.Paths;

public class PathInstanceCreator implements InstanceCreator<Path> {
    @Override
    public Path createInstance(Type type) {
        return Paths.get("");
    }
}
