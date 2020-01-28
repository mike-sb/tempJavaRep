package com.company.model;

public enum WeekType {
    odd,
    even,
    none;

    @Override
    public String toString() {
        switch (this)
        {
            case odd: return "odd";
            case even:return "even";
            case none:return "none";
        }
        return "nm";//not mentioned
    }
    public static WeekType toEnum(String str)
    {
        switch (str)
        {
            case "odd":  return WeekType.odd ;
            case "even": return WeekType.even;
            case "none": return WeekType.none;
        }
        return WeekType.none;
    }
}
