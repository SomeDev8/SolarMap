package com.rorysoft.solarmap;

public class Constants {

    // Constants for database

    public static final String DB_NAME = "SolarDB.db";
    public static final String DB_TABLE = "solarOBJS";
    public static final String _ID = "_id";
    public static final String COLUMN_NAME = "name";
    public static final String COLUMN_DIAMETER = "diameter";
    public static final String COLUMN_MASS = "mass";
    public static final String COLUMN_DESC = "description";
    public static final String COLUMN_DIST_FROM_SUN = "distance_from_sun";
    public static final String PREFS_NAME = "sharedPreferences";
    public static final String IS_CREATED = "isCreated";
    public static final String IS_MUSIC_ON = "isMusicOn";

    public static final int ANIMATION_DURATION = 600;
    public static final int ANIMATION_CLOSE_DURATION = 300;


    // Constants for fragment values

    public static final String SCROLL_POS = "SCROLL_POSITION";
    public static final String NAME_KEY = "NAME";
    public static final String DIAMETER_KEY = "DIAMETER";
    public static final String MASS_KEY = "MASS";
    public static final String DISTANCE_KEY = "DISTANCE";
    public static final String DESCRIPTION_KEY = "DESCRIPTION";
    public static final String IMAGE_KEY = "IMAGE_KEY";
    public static final String WEB_LINK = "WEB_LINK";


    // Constants for URLs

    public static final String SUN_URL = "<html><a href=\"http://www.nasa.gov/sun\">Visit NASA</a> for up-to-date info</html>";
    public static final String MERCURY_URL = "<html><a href=\"http://www.nasa.gov/planetmercury\">Visit NASA</a> for up-to-date info</html>";
    public static final String VENUS_URL = "<html><a href=\"https://www.nasa.gov/venus\">Visit NASA</a> for up-to-date info</html>";
    public static final String EARTH_URL = "<html><a href=\"http://earthobservatory.nasa.gov/\">Visit NASA</a> for up-to-date info</html>";
    public static final String MARS_URL = "<html><a href=\"http://mars.nasa.gov/\">Visit NASA</a> for up-to-date info</html>";
    public static final String JUPITER_URL = "<html><a href=\"https://www.nasa.gov/jupiter\">Visit NASA</a> for up-to-date info</html>";
    public static final String SATURN_URL = "<html><a href=\"http://www.nasa.gov/saturn\">Visit NASA</a> for up-to-date info</html>";
    public static final String URANUS_URL = "<html><a href=\"http://www.nasa.gov/uranus\">Visit NASA</a> for up-to-date info</html>";
    public static final String NEPTUNE_URL = "<html><a href=\"https://www.nasa.gov/subject/3157/neptune/\">Visit NASA</a> for up-to-date info</html>";
    public static final String PLUTO_URL = "<html><a href=\"http://www.nasa.gov/audience/forstudents/k-4/stories/nasa-knows/what-is-pluto-k4.html\">Visit NASA</a> for up-to-date info</html>";
    public static final String MOON_URL = "<html><a href=\"http://solarsystem.nasa.gov/moon/home.cfm\">Visit NASA</a> for up-to-date info</html>";

    // Constants for Image Path

    public static final int SUN_IMGPATH = R.drawable.sun_detail;
    public static final int MERCURY_IMGPATH = R.drawable.mercury_detail;
    public static final int VENUS_IMGPATH = R.drawable.venus_detail;
    public static final int EARTH_IMGPATH = R.drawable.earth_detail;
    public static final int MARS_IMGPATH = R.drawable.mars_detail;
    public static final int JUPITER_IMGPATH = R.drawable.jupiter_detail;
    public static final int SATURN_IMGPATH = R.drawable.saturn_detail;
    public static final int URANUS_IMGPATH = R.drawable.uranus_detail;
    public static final int NEPTUNE_IMGPATH = R.drawable.neptune_detail;
    public static final int PLUTO_IMGPATH = R.drawable.pluto_detail;
    public static final int MOON_IMGPATH = R.drawable.moon_detail;
}

