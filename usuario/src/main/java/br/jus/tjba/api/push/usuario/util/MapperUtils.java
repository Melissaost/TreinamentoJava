package br.jus.tjba.api.push.usuario.util;

import java.util.Optional;
import java.util.function.Consumer;

public class MapperUtils {

    public static void setIfNotEmpty(Consumer<String> setter, String value) {
        Optional.ofNullable(value)
                .filter(v -> !v.trim().isEmpty())
                .ifPresent(setter);
    }
}
