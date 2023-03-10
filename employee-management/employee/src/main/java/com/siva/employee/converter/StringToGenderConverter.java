package com.siva.employee.converter;

import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;

import com.siva.employee.model.Enum.Gender;

public class StringToGenderConverter implements Converter<String, Gender> {

    @Override
    @Nullable
    public Gender convert(String gender) {
        if (gender.equals("Male"))
            return Gender.MALE;
        else if (gender.equals("Female"))
            return Gender.FEMALE;
        else if (gender.equals("Other"))
            return Gender.OTHER;
        else
            return null;
    }

}
