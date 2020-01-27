package com.company.model;

public enum WeekType {
    odd,
    even;

    @Override
    public String toString() {
        switch (this)
        {
            case odd: return "odd";
            case even:return "even";
        }
        return "nm";//not mentioned
    }
    public WeekType toEnum(String str)
    {
        switch (str)
        {
            case "odd":  return this.odd ;
            case "even": return this.even;
        }
        return this.odd;
    }
}
