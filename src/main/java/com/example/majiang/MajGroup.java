package com.example.majiang;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Objects;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MajGroup {
    /**
     * 雀头  AA
     */
    public static final int QT = 0;
    /**
     * ABC
     */
    public static final int SHUN_ZI = 1;
    public static final int AN_KE = 2;
    public static final int MING_KE = 3;
    public static final int AN_GANG = 4;
    public static final int MING_GANG = 5;


    private int type;
    private List<GroupMaj> majs;

    public boolean contain(Maj maj) {
        for (Maj m : majs) {
            if (m.equals(maj)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public String toString() {
        return majs.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        MajGroup majGroup = (MajGroup) o;
        return type == majGroup.type &&
                Objects.equals(majs, majGroup.majs);
    }

    @Override
    public int hashCode() {
        return Objects.hash(type, majs);
    }

    public boolean containYaoJiu() {
        for (Maj m : majs) {
            int type = m.getType();
            int content = m.getContent();
            if (type == Maj.ZI) {
                return true;
            } else if (content == 0 || content == 8) {
                return true;
            }
        }
        return false;
    }
}
