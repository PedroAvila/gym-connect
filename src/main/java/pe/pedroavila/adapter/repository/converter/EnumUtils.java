package pe.pedroavila.adapter.repository.converter;

public class EnumUtils {

    public static <T extends Enum<T> & IntEnum> T fromInt(Class<T> enumType, int value) {
        for (T e : enumType.getEnumConstants()) {
            if (e.getValue() == value) {
                return e;
            }
        }
        throw new IllegalArgumentException("No enum constant for value: " + value);
    }

}
