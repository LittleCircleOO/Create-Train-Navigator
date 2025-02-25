package de.mrjulsen.crn.client.lang;

import java.util.Arrays;
import de.mrjulsen.mcdragonlib.util.TextUtils;
import dev.architectury.platform.Platform;
import net.fabricmc.api.EnvType;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.util.StringRepresentable;

public enum ELanguage implements StringRepresentable {
    DEFAULT("defaut", "def"),
    ENGLISH("english", "en_us"),
    GERMAN("german", "de_de"),
    DUTCH("dutch", "nl_nl"),
    POLISH("polish", "pl_pl");

    private String name;
    private String code;


    private ELanguage(String name, String code) {
        this.name = name;
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public String getCode() {
        return code;
    }

    public static ELanguage getByCode(String code) {
        return Arrays.stream(values()).filter(x -> x.getCode().equals(code)).findFirst().orElse(DEFAULT);
    }

    public static MutableComponent translate(String key) {
        if (Platform.getEnv() == EnvType.CLIENT) {
            return new ModTranslatableComponent(key);
        } else {
            return TextUtils.translate(key);
        }
    }

    public static MutableComponent translate(String key, Object... args) {
        if (Platform.getEnv() == EnvType.CLIENT) {
            return new ModTranslatableComponent(key, args);
        } else {
            return TextUtils.translate(key, args);
        }
    }
    
    @Override
    public String getSerializedName() {
        return code;
    }
    
}
