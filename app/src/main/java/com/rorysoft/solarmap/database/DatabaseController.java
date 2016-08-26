package com.rorysoft.solarmap.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import com.rorysoft.solarmap.Constants;

public class DatabaseController {

    private ObjectDatabase objectDatabase;
    private Context mContext;
    private SQLiteDatabase mDatabase;

    public DatabaseController(Context mContext) {
        this.mContext = mContext;
    }

    // Create the database for the first time

    public DatabaseController open() throws SQLException {
        objectDatabase = ObjectDatabase.getInstance(mContext);
        mDatabase = objectDatabase.getWritableDatabase();
        return this;
    }

    // Data is inserted into each column

    public void insert(String name, String diameter, String mass, String distanceFromSun, String description) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(Constants.COLUMN_NAME, name);
        contentValues.put(Constants.COLUMN_DIAMETER, diameter);
        contentValues.put(Constants.COLUMN_MASS, mass);
        contentValues.put(Constants.COLUMN_DIST_FROM_SUN, distanceFromSun);
        contentValues.put(Constants.COLUMN_DESC, description);
        mDatabase.insert(Constants.DB_TABLE, null, contentValues);
    }

    // Data is retrieved for the corresponding id parameter

    public Cursor getData(int _id) {

        String [] columns = new String[]{
                Constants.COLUMN_NAME, Constants.COLUMN_DIAMETER,
                Constants.COLUMN_MASS, Constants.COLUMN_DIST_FROM_SUN, Constants.COLUMN_DESC};

        Cursor cursor = mDatabase.query(Constants.DB_TABLE, columns, Constants._ID + " = " + _id, null, null, null, null );

        if(cursor != null) {
            cursor.moveToFirst();
        }
        return cursor;
    }

    // All data is created for each entry

    public void createData() {

        insert("Sun", "1390000" , "1.989 x 10^30", null, "The Sun is a yellow dwarf star located at the center of the solar system " +
                "containing a stellar 99.8% of its mass. The star is also referred to as “Sol” by the Romans or “Helios” by the Greeks. " +
                "The star is composed of 70% hydrogen, 28% helium and 2% carbon, neon, oxygen, nitrogen, magnesium, sulfur, iron and " +
                "silicon. Approximately 4.6 billion years ago, nebula, which is a giant cloud of hydrogen, helium and dust particles, " +
                "collapsed into itself forming a spinning disk. After a sufficient portion of material coalesced at the center, protons " +
                "at the nucleus of the hydrogen atoms began to fuse due to strong nuclear force, which is fundamental force. The result " +
                "is a helium atom, which releases high amounts of gamma radiation, which loses energy on its journey to the surface. The " +
                "reduction in energy results in lower frequency electromagnetic radiation including visible, ultraviolet, radio, microwave," +
                " X-ray and infrared light emitted at the surface of the Sun. This process is known as nuclear fusion and it occurs in all " +
                "stars in the universe. The rest of the matter around the Sun formed into planets, dwarf planets, and asteroids. All celestial " +
                "bodies in the solar system orbit the Sun due to its massive gravitational field. "
            +   "\n \nNuclear fusion, as discussed above, occurs at the core of the Sun where temperatures can reach 15 million degrees " +
                "(Celsius). The outward pressure from the nuclear reactions is balanced with the inward pressure of gravity, which prevents " +
                "the star from collapsing. The radiative zone is beyond the core where temperatures are slightly cooler and gas molecules " +
                "absorb energy emitted from the core. When the molecule is heated sufficiently, energy is re-emitted to another molecule. " +
                "The process can take millions of years before energy can reach the edge of the zone. Eventually the energy reaches the convective " +
                "zone where the chemical composition is the same but molecules are significantly cooler. Convective currents occur because of " +
                "the temperature gradient attributed to the temperature difference between the radiative zone and the atmospheric layers " +
                "which surround the convective zone. Energy is emitted quicker between molecules and it reaches the Sun’s atmosphere. An " +
                "example of convection could be boiling a pot of water, where hot less dense molecules rise and cooler dense molecules sink " +
                "due to a temperature difference at the base and top of the pot. "
            +   "\n \nThe Suns Atmosphere consists of the photosphere, chromosphere, and corona. The photosphere is the lowest layer and " +
                "radiates energy from the convective zone. The gaseous nature of the sun allows observers to see through to higher and lower " +
                "temperature regions revealing sunspots, faculae, and granules. These are all features that can be observed with a decent telescope" +
                " and solar filter. The chromosphere is located above the photosphere. According to NASA, temperatures can increase from 6000 " +
                "to 20,000 degrees (Celsius). Solar flares, eruptions and the flow of material can be viewed through filters and spectrographs. At " +
                "the Corona, gases can reach 1,000,000 degrees (Celsius) where electrons are “stripped” from some elements leaving only the nuclei " +
                "of atoms. The corona is visible during solar eclipses where streams of energy can be observed. Solar winds are derived from the " +
                "corona where coronal gases are too hot for gravity to retain. Winds stream in all directions at 400 km/s towards all bodies in the " +
                "solar system. The Earth’s magnetic field protects the planet by redirecting charged particles around it. During auroras, charged particles " +
                "enter at the Earth’s poles and interact with atoms and molecules in the atmosphere. Electrons are elevated to high-energy states and " +
                "back down to low energy states, which releases energy as photons."
            +   "\n \nIn approximately 5.4 billion years, the Sun’s hydrogen supply will deplete causing the core to contract and the outer layers to " +
                "expand. In this stage, the sun will become a red giant and swallow Mercury, Venus, and possibly the Earth. After approximately 100 " +
                "million years, the Sun will begin burning helium atoms into carbon due to its increased density. After the helium depletes, the Sun will " +
                "become a red giant, which extends to Jupiter. The additional mass will release and leave behind the scorching core classified as a white " +
                "dwarf. The layers will drift away around the remnant core as nebula… likely destined to give birth to new stars in the distant future."
        );

        insert("Mercury", "4880" , "3.30 x 10^23", "57,910000", "Mercury, named after the Roman messenger god, is the smallest planet in the solar system " +
                "and the closest to the sun. Mercury shares a similar appearance to the Earth’s moon due to it’s grayish color and cratered surface caused " +
                "by impacts from meteorites. Mercury’s surface temperature varies between 450 degrees (Celsius) when faced towards the sun and -173 degrees " +
                "(Celsius) on the night side. Mercury’s orbit is highly elliptical with a period of approximately 88 Earth days traveling at a speed of " +
                "112,000 mph (quicker than any other planet). It’s axis tilt is even more unusual at just 2.11 degrees resulting in no seasonal changes. On " +
                "the planet’s surface, the Sun appears at the horizon and never completely “sets” or “rises” as a result of its small tilt. Mercury’s atmosphere" +
                " is thin and composed of 42% oxygen, 29% sodium, 22% hydrogen, 6% helium, 0.5% potassium, and traces of other elements."
            +    "\n \nMercury has a massive iron core that accounts for an estimated 75% of the planets radius. The core is likely larger than" +
                " the Earth’s core and makes the planet more dense than the moon. Scientists previously thought Mercury’s core cooled to a completely " +
                "solid form due to the planet's size. Messenger, a spacecraft that orbited Mercury from 2011-2015, provided data on Mercury’s " +
                "gravitational field and magnetic field. The data combined with observations from Mariner 10 and Earth based radars strongly suggests " +
                "the core may still be molten and inducing magnetism. Mercury’s magnetic field is nearly 100 times weaker than the Earth’s despite the " +
                "size of the core. Researchers believe this is caused by the solar winds from the sun that deflects charged particles in Mercury’s " +
                "magnetosphere. The mantle, which surrounds the core, is composed of silicates that are 500-700 km thick. The curst is thinner with " +
                "measurements of 100-300 km. Unlike the Earth, there are no plate tectonics to recycle the planetary crust, which is why Mercury’s surface" +
                " is so heavily cratered. The Caloris Basin is one of most notable features on Mercury’s surface and is 1,300 km in radius. Like all basins" +
                " on Mercury, Caloris was probably formed after a large impact."
            +   "\n \nIn 2011, Messenger used a spectrometer, reading at the X-ray spectrum, to identify strange materials on the planets surface. " +
                "Research conducted at the Massachusetts Institute of Technology suggested that volcanic eruptions occurred on the planet 4 billion " +
                "years ago. Later, impact craters were observed with reflective materials identified as pyroclastic debris. The discovery was indicative" +
                " of ancient volcanic activity. Messenger also found that the planet has decreased in global radius by 7 km due to contractions. When a " +
                "planet's core cools, crust is forced over itself decreasing the overall thickness of the crust and forming ridges in the surface."
        );

        insert("Venus", "12,103.6" , "4.869 x 10^24", "108,200,000", "Venus, named after the Roman goddess of love and beauty, is the second planet " +
                "from the Sun and possesses the highest temperature out of any planet in the solar system. Physically, it is similar to the Earth in " +
                "regards to diameter, mass, density and chemical composition. The scorching hot planet is also the second brightest object in the Earth's" +
                " sky at night next to the Moon. There was likely water on Venus due to comets bombarding the surface and delivering water molecules. Exposure" +
                " to powerful solar winds from the Sun had caused all of the water to evaporate into space, leaving Venus a dry hellish planet."
            +   "\n \nVenus’s atmosphere is thick, dense, and fierce. Atmospheric pressure is approximately 900 Newtons per square centimeter or 92 times " +
                "more powerful than pressure in Earth’s atmosphere. Temperatures at the surface reach a brutal 480 degrees (Celsius), which could melt solid" +
                " lead to liquid form. Carbon dioxide dominates the atmosphere taking up 96.5% of its volume. The rest of the atmosphere is approximately" +
                " 3.5% nitrogen with small amounts of water vapor. There is no longer plate tectonic activity on Venus, which causes carbon to be released" +
                " into the atmosphere instead of being recycled into the surface. When the sun radiates energy towards the planet’s surface, the surface " +
                "tries to reflect it back into space but carbon dioxide and water vapor (green house gases) effectively trap thermal energy and raise the " +
                "planet’s temperature. Venus is home to more volcanoes than any other planet in the solar system and some are still active. The planet’s " +
                "surface indicates that 90% of it was covered in lava flow. There are no significant crater impacts in the surface due to lava reshaping the plains."
            +   "\n \nIt takes 243 Earth days for Venus to complete on periodic rotation on it’s axis. Currently, there is no conclusive answer to the state " +
                "of matter at Venus’s core. The uncertainty is due to probes being destroyed by extreme pressure and temperature before core data could be " +
                "extrapolated. The planet's slow rotation suggests that geodynamo (click on Earth on the map for more information) cannot occur to produce a " +
                "circulating electric field, which will produce a magnetic field. The absence of plate tectonics for the last 300-500 million years also " +
                "prevents heat from escaping the crust. As a result, there may not be a great enough difference between the temperature of the mantle and " +
                "inner core, which surround the outer core. This prevents convection and producing an electric field is not permissible. "
        );

        insert("Earth", "12,756.3" , "5.972 x 10^24", "149,600,000", "Earth is the third planet from the Sun and harbors the only life forms in the known " +
                "universe to modern Homo sapiens. The small blue planet’s name has English and German origins unlike all the other planets in the solar system." +
                " The Earth is covered in 30% land and 70% water with a multilayer atmosphere above it. The average temperature on the surface is approximately" +
                " 14 degrees (Celsius), but the temperature varies with the time of year, atmospheric and weather conditions. These factors assist in enabling " +
                "millions of species to evolve and thrive on the planet. The Earth is the densest planet in the solar system due to its iron-nickel alloy core " +
                "and rocky mantle. The Earth’s mass and volume is 5.9722 x 10^24 kilograms and 1 x 10^12 public kilometers. The Earth is tilted 23.5 degrees on " +
                "its axis likely due to a collision with a smaller planet early in its formation. The Earth’s tilt enables seasons on the planet."
            +   "\n \nThe Earth is located in the “goldilocks zone”, which is a region of space where a planet is located a comfortable distance from its star. " +
                "In this habitable zone, it is neither too hot nor too cold for liquid water to sustain without freezing or evaporating. When scientists attempt " +
                "to find planets that could harbor life, they tend to look for planets in the goldilocks zone in other solar systems. Location is not the only " +
                "reason life is permissible on the Earth. Mars and Venus are both located within the goldilocks zone, but they do not generate a magnetic field " +
                "and have atmospheres not suitable for life (click Venus and Mars on the map for more detail). Earth’s magnetic field serves as a barrier against " +
                "solar winds and cosmic radiation, which pose a lethal threat to all living creatures on the planet. The magnetic field is likely generated at the " +
                "center of the Earth in the outer core. The inner core is composed of iron as hot as the surface of the Sun at 5,700 degrees (Celsius). The " +
                "pressure at the core is so high that the melting point of iron is increased causing the core to remain solid. The 2,000km thick outer core is " +
                "cooler and has liquid iron and nickel. Convection occurs due to the difference in temperature and pressure between the inner and mantle, which " +
                "surround the outer core. As the Earth rotates, liquid in the outer turns causing a “dynamo effect” or “geodynamo”. Electric current is produced " +
                "from the flow of metal, which further produces a magnetic field."
            +   "\n \nThe Earth has a multilayer atmosphere composed of 78% nitrogen, 21% oxygen, and 1% argon with traces of other elements including water " +
                "vapor. The troposphere is the lowest layer between 8 and 14 km containing dense air and clouds. Approximately 99% of water vapor in the atmosphere" +
                " is concentrated in troposphere where it absorbs solar energy and thermal radiation allowing the regulation of air temperature. The stratosphere" +
                " is above the troposphere and contains the ozone layer in 35 km of air. The ozone layer absorbs ultraviolet radiation from the Sun, which is lethal" +
                " to life on the planet. The middle layer contains the 35 km thick Mesosphere which is the coldest layer at approximately -90 degrees (Celsius). Gases" +
                " are mixed up and cause friction and create heat so meteors burn up when they enter the mesosphere. The thermosphere is 513 km thick and has " +
                "maximum temperature of 2482 degrees (Celsius). Despite the high temperature, gas molecules are too sparse to transfer heat. The International Space" +
                " Station and low earth satellites occupy the thermosphere and atmospheric drag occurs. Occasionally, rockets must be sent to boost the structures to" +
                " prevent them from declining further into the Earth’s atmosphere. The Northern and Southern Lights (auroras) also occur in the thermosphere where charged" +
                " particles from solar winds collide with molecules in the air and cause energetic emissions. The ionosphere shrinks and expands in the mesosphere " +
                "and thermosphere. Solar radiation ionizes atoms and molecules creating a layer of electrons. This layer is important in radio communication for low " +
                "and high frequencies. The exosphere is the final layer of the atmosphere, which extends 10,000km from the thermosphere into space."
        );

        insert("Mars", "4880" , "3.30 x 10^23", "57,910000", "Mars is named after the Roman god of war. The planet is often referenced in fiction because of early " +
                "speculation that claimed the planet harbored intelligent life. Christian Huygens (1629-1695) was a Dutch astronomer that drew sketches of Mars " +
                "in 1659 after observing it with a self-made telescope. Giovanni Schiaparelli (1835-1910) was an Italian Astronomer who had identified “canali” or " +
                "“channels” in English, after observing the planet in 1877. Mistranslation of the word “canali” to “canals” caused many to believe that artificial " +
                "structures were engineered on Mars like the Suez Canal on Earth. Famed Astronomer and businessman, Percival Lowell (1855-1916), further fueled speculation " +
                "by falsely mapping out hundreds of canals he proposed were on Mars. The reality is that Mars has no artificial waterways or intelligent life, but " +
                "there are polar ice caps and hydrated slopes, which may possess simple life. Mars is significantly smaller than the Earth and it has a thin atmosphere " +
                "with a fraction of the Earth’s pressure. The red color of Mars is attributed to oxidation of iron minerals that are prominent on the planet. The " +
                "oxidized dust is released into the atmosphere giving the planet a reddish color when observed."
            +   "\n \nThe average temperature of Mars is -63 degrees (Celsius) although the temperature can drop to -123 degrees (Celsius). The planet is even farther " +
                "from the Sun than the Earth, which contributes to frigid temperatures. The planet’s atmosphere is only 1% of the Earth’s and is composed of 95.32% carbon " +
                "dioxide, 2.7% nitrogen, 1.6% argon, 0.13% oxygen, 0.13% carbon monoxide, and small traces of other elements. Mars cannot retain thermal energy in it’s " +
                "atmosphere without sufficient amounts of carbon dioxide or water vapor. It’s possible that violent solar winds from the sun stripped most gases from the " +
                "atmosphere. NASA’s Mars Atmosphere and Volatile Evolution (MAVEN) data determined that solar winds strip gas away at an estimated rate of 100 grams per " +
                "second. The loss of gases increases when the Sun is highly active during solar storms and the Sun was more active billions of years ago. The surface of " +
                "Mars contains physical features such as dry riverbeds and canyons with imbedded minerals that only develop in the presence of liquid water. Much of the " +
                "data collected since the 1970’s dictates that Mars may have been warm enough with a dense atmosphere to support oceans. Scientists from Berkley and " +
                "Johns Hopkins University believe the planet’s magnetic field ceased to function after being bombarded with at least five asteroids. The impacts prevent " +
                "convection from occurring and heat is trapped in the mantle above the outer core making an appropriate temperature gradient difficult. A temperature " +
                "difference between the mantle and inner core is intrinsic to electric currents, which produce magnetic fields in the outer core. Without a significantly strong " +
                "magnetic field the planet is overwhelmed with solar winds, which vaporize water on a planet’s surface. For more information on how planetary magnetic fields " +
                "are created, click on the Earth on the map."
            +   "\n \nIn Fall 2015, NASA’s Mars Reconnaissance Orbiter (MRO) used a imaging spectrometer to detect the presence of hydrated minerals on downhill slopes of the " +
                "planet. The slopes or “recurring slope linae” appear as dark and narrow 100-meter long streaks that appear during warmer climate and disappear during cooler" +
                " climate. Lujendra Ijha of Georgia Tech is a lead author of a report on the discovery. He and his co-authors believe the signatures found by MRO are a result" +
                " of hydrated minerals known as perchlorates which prevent liquids from freezing at up to -70 degrees (Celsius). According to NASA, the consensus among scientists" +
                " is that the darkened slopes have a shallow subsurface flow” containing trickles of water. When scientists search for life in the Cosmos, they look to" +
                " bodies with environments (with factors like it’s distance from stars) suitable for water to be in a liquid form. Humans are only aware of life on one " +
                "cosmic body, the Earth. The most plausible theory to the origin of life begins in the Earth’s water. Self-assembly of organic compounds is only permissible in" +
                " water, which leads to more molecules, membrane structures, proteins and nucleic acids. Cell membranes surrounded genetic material, which eventually lead to " +
                "early microbial life."
            +   "\n \nThe primary elements in biological molecules are in abundance throughout the universe and there are an estimated 40 billion planets orbiting stars in habitable" +
                " zones in the Milky Way galaxy (based on Kepler space observatory data). There are also at least one hundred billion galaxies in the known observable universe." +
                " The possibility of life elsewhere in the Cosmos is highly promising."
        );

        insert("Jupiter", "142,984" , "1.900 x 10^27", "778,330,000", "Jupiter was appropriately named after the Roman king of gods and it is the largest planet in " +
                "the solar system. Italian astronomer Galileo Galilei (1564-1642) was the first person to extensively observe the planet and record it in detail in 1610. " +
                "When the Sun first formed (click on the Sun on the map for more information), solar wind sent lighter elements farther into space than heavier rocky elements. " +
                "This is why the inner solar system has smaller rocky bodies and the outer portion consists of gas giants. The core accretion model asserts that a rocky core " +
                "formed through the merging of planetesimals will accrete a gas envelope of hydrogen and helium left over from the Sun. Although the core accretion model is " +
                "highly favored by scientists for the inner rocky planets, there are issues that leave some skeptical in formation of gas giants like Jupiter, Saturn, Uranus" +
                " and Neptune. Data from the spacecraft Galileo suggests Jupiter’s core lacks the mass to attract gases. Secondly, gases left over from the Sun’s formation do " +
                "not typically last several million years, which is how long it takes gas giants to form with core accretion. Dr. Alan P. Boss of the Carnegie Institution for" +
                " Science developed the disk instability model, which claims dust and gas coalesce into gas giants in a couple thousand years. Still, there is skepticism " +
                "towards Boss's proposal because of the slow cooling of gases, which may not produce the instability required. The debate of how gas giants formed continues, " +
                "but core accretion remains the most accepted model. Jupiter’s core is likely to be 10-15 times the Earth’s mass, but it significantly less dense (1.326 g/cm^3) " +
                "due to its gaseous composition beyond the core. The planet has a differential rotational system due to its gas composition, but there is little speed " +
                "difference between regions of the planet. Jupiter completes one rotation on its 3.13-degree axis in 9.9 hours as its equatorial clouds move at approximately " +
                "43,000 km/hr, which is quicker than any other planet in the solar system. Jupiter’s orbital period is over five times greater than the Earth’s, it takes 11.86 " +
                "years for a single orbit. Although Jupiter is the most massive planet in the solar system, it lacks the mass for nuclear fusion to begin at it’s core like the" +
                " Sun. It’s estimated that Jupiter would need 70 times more mass to begin the hydrogen to helium process that fuels the Sun (click on the Sun on the map for" +
                " more information). Jupiter has faint rings that were first observed by Voyager 1 in 1980 and they are likely composed of rocky material. "
            +   "\n \nJupiter’s appearance is stunning with captivating shades of white, red, orange, brown, and yellow. Jupiter’s atmosphere is 90% hydrogen and 10% " +
                "helium with small amounts of ammonia, sulfur, methane and water vapor. Convection driven from the center of the planet, creates immensely powerful storms " +
                "that push various elements to higher layers of the atmosphere. Sun light reflects off of these different elements giving white, brown and red colors when the " +
                "storms are cool, warm and hot. One of the most distinctive features of the planet is the Great Red Spot (observable in the photo above). The 400+ year old " +
                "storm once measured an estimated 40,000 km across, although it currently measures to half that diameter. Scientists believe that the Great Red Spot multiplied" +
                " in size when smaller spots merged. Unlike rocky planets that rely on liquid iron to generate magnetic fields, Jupiter and Saturn’s magnetic field is likely " +
                "caused by hydrogen gas compressed by atmospheric pressure into liquid metallic hydrogen. There are exponential amounts of pressure on Jupiter, especially when " +
                "venturing deeper towards the core. NASA estimated pressure on Jupiter to be equivalent to 650 million pounds or 130,000 standard size cars. Jupiter’s magnetosphere" +
                " (area where the magnetic field occupies) is colossal with a 30 million km diameter. Electrons from the hydrogen flow from atom to atom and Jupiter’s rapid " +
                "rotation produces a dynamo effect. Convective currents, due to a temperature difference between the core and hydrogen gas, causes hot less dense fluid to rise " +
                "and cool dense fluid to fall. The continuity of these chemical reactions and interactions creates an electric field which creates a magnetic field. Jupiter also " +
                "has a faint 4-part ring system composed of rocky debris and dust. The rings include the halo, main, Amalthea gossamer and Thebe gossamer rings."
            +   "\n \nJupiter has approximately 67 natural satellites including Io, Europa, Ganymede, and Callisto, which were discovered by Galileo. This discovery was " +
                "direct evidence for the Copernican heliocentric theory (all planets orbit the Sun). Ganymede is Jupiter’s largest moon and also the largest in the solar " +
                "system with a diameter of 5,268 km. The moon possesses an icy surface and may have a salt-water ocean below, which may harbor life (Click Uranus on the Map " +
                "for information on the possibility of life in oceans). Callisto, the second largest moon with a diameter of 4,800 km, has the most cratered surface in the solar" +
                " system and may also have an ocean of water beneath it. The lack of tidal heating means it may not harbor life, but the moon is a safe distance from Jupiter’s " +
                "radiation. The safety on Callisto as oppose to Europa makes the moon a better option for humans during manned space exploration in the future. Io is Jupiter's " +
                "third largest moon with a diameter of 3,636 km. Io’s surface is stretched and contorted (tidal flexing/heating) due the Jupiter’s gravitational field which heats" +
                " the crust. Europa is the fourth largest moon with a diameter of 3,138 km. Also, it is Jupiter’s most famous moon because the surface is covered by ice, which " +
                "may have an ocean of salt water beneath it. The presence of water could also mean that Europa harbors microbial life (Click Mars on the map for more information " +
                "of the importance of water to life). It is possible that microbes like halophiles, which thrive in salt water, could be located deep in the sea. All of the Galilean" +
                " moons are tidally locked with the Gas giant like the Moon is with the Earth (Click the Moon on the map for more information on how tidal locking occurs)."
        );

        insert("Saturn", "120,536" , "5.68 x 10^26", "1,429,400,000", "Saturn, the sixth planet from the Sun, is the second largest planet in the solar system and named after" +
                " the Roman god of agriculture. Italian astronomer Galileo Galilei (1564-1642) was the first known person to telescopically observe the planet in 1610, but he " +
                "was unable to explain the ring system due to his low power telescope. Dutch Astronomer, Christiaan Huygens (1629-1695), correctly identified the ring system in " +
                "1659 and Saturn’s moon, Titan. Saturn is classified as a gas giant composed of 75% hydrogen and 25% helium with traces of water methane, ammonia, and rock. The " +
                "planet was debatably formed when planetesimals coalesced into a core, which attracted hydrogen, helium and other gases. These gases were originally swiped away " +
                "by the Sun’s solar winds and later became envelopes surrounding the cores of gas giants. The issues with this model are discussed in Jupiter’s section of this " +
                "application. Saturn is the second largest planet in the solar system with a equatorial diameter of 120,536 km. Despite it’s massive size, Saturn has a " +
                "density of 0.687 g/cm^3, which is less than the density of water. Saturn has an elliptical period of 29.7 years and an 26.7 degree tilt on its axis. The planet " +
                "has differential rotation due to its gas composition and ring system. The regions around the equator has a rotational period is 10.20 hours, regions above and " +
                "below the equatorial belt have a period of 10.65 hours, and the planet’s magnetic field also has a rotational period of 10.65 hours. Saturn has an average " +
                "temperature of 178 degrees (Celsius)."
            +   "\n \nSaturn’s ring system is the most well defined in the solar system. There are several rings, labeled A through G (alphabetically based on there discovery)," +
                " that are made up of thousands of smaller rings. The rings are primarily composed of ice with rock, but their origin remains unclear. It’s possible that asteroids" +
                " and meteoroids impacted the moons of Saturn and caused pieces to break off and orbit the planet. Some of the ice particles likely came from passing comets. Some" +
                " of the rings are confined and close to each other and some are farther apart. The separation between the A and B rings is known as the “Cassini Division” (named " +
                "after the Italian astronomer, Giovanni Domenico Cassini (1625-1712), who discovered it). The A and B rings are the brightest and thickest and the C ring is " +
                "transparent, while the D ring is barely visible. The three are composed of ice water particles. The D, E, F, and G rings consist of even smaller microscopic ice " +
                "particles and dust. The F ring is minuscule and held together by the gravity of Saturn's moons, Pandora and Prometheus. The G ring is also very thin and is kept " +
                "stable by the gravity of Saturn's moon Mimas. The E ring, the farthest from the planet, is likely made up of ice particles from geysers on Saturn's moon, Enceladus. " +
                "The rings orbit the planet and move thousands of kilometers per hour. Johannes Kepler's Law of Areas asserted than a satellite’s speed is contingent on the distance " +
                "from the body it is orbiting. So, the outer rings (A, B, F, and G) orbit slower than the inner rings (C and D)."
            +   "\n \nSaturn has 62 known moons including those mentioned above. Titan is the largest moon with a diameter of 5,149 km (50% larger than the Earth’s moon) and takes " +
                "up 96% of the mass orbiting the planet. Titan’s dense atmosphere is composed of 95% nitrogen and 5% methane and its mass is mostly ice water and rock. Some of Titan’s " +
                "most distinctive features are it’s methane lakes and oceans. Nitrogen bubbles form on top of the liquid’s surface and resemble floating islands. Scientists refer to " +
                "the anomaly as “Magic Islands”. The presence of organic compounds in Titan’s atmosphere suggest that the moon may harbor life (Click Mars on the map for more information" +
                " on organic compounds and life). Rhea is Saturn's second largest moon with a diameter of 1,528 km and it does not have a core. The moon is almost completely composed " +
                "of ice with traces of rock. Rhea has a thin oxygen atmosphere possibly caused by irradiation from Saturn magnetosphere. Iapetus is the third largest moon with a diameter" +
                " of 1,471 km. The moon is also a strangely shaped (not as peculiar as Hyperion mentioned below) and composed mainly of water with moderate amounts of rock. Iapetus has " +
                "a bulge at the equator that possibly formed early when the planet spun faster. Dione is the fourth largest moon orbiting Saturn with a diameter of 1,123 km and it is composed " +
                "mostly of ice. The moon’s feeble atmosphere is thin and composed of oxygen. Tethys is Saturn's fifth largest moon with a diameter of 1,066 km and a highly reflective ice " +
                "surface. The moon orbits closer to Saturn than others and may partially melt at the surface due to the close proximity. Enceladus is the sixth largest moon with a diameter of " +
                "505 km and it has at least 101 geysers at it’s southern pole. Tidal forces from Saturn’s gravity distorts and heats the moon’s surface causing ice to melt. Like many " +
                "satellites orbiting the gas giants, Enceladus has oceans of water beneath its surface, which may contain life. Mimas is the smallest major moon with a diameter of 396 km " +
                "and it also has an icy surface. The moon has a massive crater in its surface and shockwaves opposite the crater. An ocean beneath Mimas is less than likely due to heat " +
                "escaping the ice surface. Hyperion is one of Saturn’s smaller moons with a diameter of 270 km. It has an unusual and elliptical shape. Scientists think the moon is composed" +
                " of ice, carbon dioxide, and methane after interpreting visual and infrared mapping data. All of these moons are tidally locked with Saturn (click on the Moon on the map " +
                "for more information on tidal locking)."
        );
        insert("Uranus", "51,118" , "8.683 x 10^25", "2,870,990,000", "Uranus, the seventh planet from the Sun, is the third largest planet in the solar system and named after the ancient" +
                " Greek god of the sky. Before the British Astronomer William Herschel (1838-1822) discovered the planet is 1781, astronomers thought it was another distant star. Uranus's" +
                " atmosphere is approximately 83% hydrogen, 15% helium, 2% methane, but below the atmosphere is only 15% hydrogen with traces of helium. The planet is referred to as a gaseous" +
                " ice giant due to its massive core composition consisting mainly of methane, water and ammonia ice and rock. There are two main theories on the formation of gas giants, which" +
                " includes the “core accretion model” and the “disk instability model”. Core accretion is more accepted by scientists despite its issues (Click on Jupiter on the map for more " +
                "information). The model suggests that rocky materials merge until enough mass can attract light gases left over from the Sun’s formation. Currently, scientists are conducting " +
                "research to better understand how gas giants formed in our solar system. Uranus’s light blue color is the result of methane gas in it’s atmosphere absorbing red light from the " +
                "Sun and reflecting visible blue light back. The average temperature on the ice giant is -216 degrees (Celsius). There is minimal temperature fluctuation because of Uranus's great" +
                " distance from the Sun."
            +   "\n \nUranus has the most unusual tilt out of all the cosmic bodies in the solar system. The ice giants odd axial tilt is 98 degrees which renders the planet on its side with " +
                "its poles at each end. Studies suggest that at least two collisions with other cosmic bodies knocked Uranus on its side. Like the Earth, Uranus experiences four seasons, but" +
                " they are dramatically different. During each solstice of the 84 year orbital period, a small portion of the plant experiences a day/night transition. When the northern pole" +
                " is facing the Sun, the northern hemisphere experiences 21 years of continuous sunlight and the southern hemisphere experiences darkness. After, the equinox occurs and planet" +
                " experiences a day and night cycle every 17 hours for another 21 years (occurs twice during the 84 years cycle). When the southern pole faces the Sun, the south experiences" +
                " continuous day and the north is shrouded in darkness for 21 years. In 2007, the planet progressed to its equinox cycle and powerful storms began in the Northern hemisphere." +
                " Storms on the ice giant are typically triggered by direct sunlight, but during the equinox, the northern and southern hemispheres do not encounter as much light. This peculiar" +
                " event has scientists bewildered. Uranus has 13 faint ring composed of ice, dust, and moon fragments. Scientists think the lack of definition in the rings indicate that they " +
                "did not form with Uranus, but after. Uranus and Neptune have unusual magnetic fields which are not aligned with their axises. Both planets may have liquid ammonia which is is" +
                " electrically conductive in solutions due to free ions with a net charge. Free ions can create a electrically conductive layer within the mantle which can generate a magnetic" +
                " field. Magnetic fields protect planets from violent solar winds from the Sun."
            +   "\n \nUranus has 27 known moons in its orbit and their names are attributed to the literary work of William Shakespeare (1564-1616) and Alexander Pope (1688-1744). " +
                "Titania is the largest of the five major moons with a diameter of 1,578 km. The moon is composed of carbonaceous chondrites (meteorites), organic compounds, and ice " +
                "water. It’s possible for Titania to have a rock core and ice mantle, which may have an ocean below it. Scientists believe life could be possible if hydrothermal vents " +
                "are imbedded in the ocean. Hydrothermal vents exist in the deep oceans of the Earth and provide hydrogen sulfide and carbon dioxide to bacteria. The bacteria use energy " +
                "from oxidizing sulfur to produce organic molecules. This process is known as chemosynthesis. Life can develop and thrive independent of sunlight used during photosynthesis. " +
                "Oberon is the second largest Uranian moon with a diameter of 1,523 km and it orbits farther than the other five moons. Like Titania, Oberon may contain hydrothermal vents " +
                "below its icy surface if liquid is present. Umbriel is the third largest moon with a diameter of 1,170 km. The surface of the planet is dark and possibly caused by decomposing" +
                " methane deposits. Spectrographic data suggest that ice water contains most of the moon mass. Ariel is the fourth largest moon with a diameter of 1,158 km composed of " +
                "approximately 50% rock and 50% ice water. The moon has distinctive features like valleys with smooth surfaces and ridges. Miranda is the smallest of the five moons " +
                "with a diameter of 472 km and a heavily featured surface. Its composition is similar to Ariel’s but it contains faults, deep canyons and cliffs stretched throughout " +
                "the moon's terrain."
        );

        insert("Neptune", "49,532" , "1.0247 x 10^26", "4,504,000,000", "Neptune, the eighth planet from the Sun, is named after the Roman god of the sea. German astronomers, Johann " +
                "Gottfried Galle (1812-1910) and Heinrich Louis d’Arrest (1822-1875) discovered the planet in 1846. The French astronomer Alexis Boulevard (1767-1843) noticed that Uranus " +
                "was not orbiting in accordance with Newton’s laws of gravitation. He imagined that another massive object must have been creating the anomaly. Galle calculated where Neptune" +
                " should have been in the sky before he identified it. Like Uranus, Neptune's atmosphere is likely a mix of hydrogen, helium, and methane. Light from the Sun that reflects " +
                "off of the methane gas in the atmosphere gives the planet its observable deep blue color. Beneath the atmosphere, Neptune is composed of methane, water and ammonia ice and" +
                " rock with 15% hydrogen and traces of helium. The gas giant planets may have developed from either “core accretion” or “disk instability” which is discussed in more detail" +
                " in the Jupiter section of this application. Neptune is the coldest planet in the solar system with an average temperature of approximately -218 degrees (Celsius) at the " +
                "equator. It also has a density of 1.638 g/cm^3."
            +   "\n \nNeptune’s erratic weather produces powerful winds of 2,100 km/hour, which is the most powerful detected in the solar system. Data from the Voyager 2 spacecraft and ground" +
                " telescopes assisted in understanding how Neptune’s gravitational field regulates its atmosphere. Winds circulate in thin atmospheric layers of approximately 1,000 km" +
                " and begin in outer layers where moisture condenses and evaporates. Despite Neptune being over 4 billion kilometers from the Sun and receiving a fraction of its light," +
                " the planet has internal heat. The internal heating is also a significant contributor to the violent weather on the ice giant. The source of the heat remains unclear, " +
                "but it could be caused by radioactive decay close the planet’s core. In 1989 Voyager 2 found the “Great Dark Spot” on Neptune whose diameter was greater than the " +
                "Earth's. The storm, which moved at 1,200 km/hr, must have eventually dissipated since images from the Hubble Space Telescope of Neptune did not have the spot in it. " +
                "Like all gas giants, Neptune has a ring system consisting of Galle, Le Verrier, Lassell, Arago, and Adams (named after astronomers who made discoveries related to Neptune)." +
                " The rings probably originated from meteorites colliding with Neptune’s moons and they are composed of 70% dust with traces of rock."
            +   "\n \nThere are 14 known moons orbiting Neptune. Some of the moons were discovered by ground base telescopes and others by the Voyager 2 spacecraft. Triton, Neptune’s largest" +
                " moon, has a diameter of 2,700 km and a smooth surface where Voyager 2 discovered geysers. Scientists think the moon’s crust is composed of frozen nitrogen with a " +
                "metal core and ice mantle. Proteus is the second largest moon with a diameter of 418 km and an irregular shape. According to NASA, if the moon were any larger then " +
                "its gravity would form it into a spherical shape. Nereid is the third largest moon with a diameter of a 340 km. The moon’s shape in unknown but spectral data suggests" +
                " its composed of ice water. According to NASA, the satellite may be an asteroid or a Kuiper belt object captured by Neptune’s gravity. Larissa is the fourth largest " +
                "moon with a diameter of 193 km and it may have also been an asteroid. Galatea is the fifth largest moon with a diameter of 158 km. The rest of Neptune’s moons are " +
                "smaller and have no defining features other than their odd shapes. Some of these satellites will be destroyed by Neptune’s gravity in the future."
        );

        insert("Pluto", "2372" , "1.303 x 10^22", "5,913,520,999", "Pluto is named after the Roman god of the underworld and it is one of the furthest cosmic bodies from the Sun " +
                "in the solar system. Astronomer and businessman, Percival Lowell (1855-1916), noticed irregularities in the orbits of Neptune and Uranus in 1905. Lowell had determined" +
                " the location of the object, but an American Astronomer named Clyde Tombaugh (1906-1997) discovered the planet in 1930. Pluto was considered the ninth planet in the " +
                "solar system for around 75 years until the International Astronomical Union classified it as a “dwarf planet” in 2006. Pluto lacks the mass to be considered a planetary" +
                " body. The dwarf planet has a diameter of 2,372 km and a mass of 1.303 x 10^22 kilograms, rendering it incapable of causing all bodies in the area to orbit it. Neptune’s" +
                " far greater mass and gravitational field dominate Pluto and influence it’s orbit. Pluto is also located in the Kuiper belt, an innumerable assemblage of small objects " +
                "orbiting the Sun beyond Neptune. The discovery of Eris, a larger dwarf planet in the Kuiper belt, produced more questions concerning Pluto’s planetary status among the " +
                "other eight larger bodies. Some scientists and cosmic viewers consistently attempt to reclassify Pluto as a planet. Pluto has been included in this application due to exciting" +
                " data obtained by the New Horizons spacecraft, which encountered Pluto in July 2015. Humanity was privileged to receive the first clear images of the dwarf planet with data" +
                " on frozen nitrogen lakes, ice volcanoes and more."
            +   "\n \nBefore New Horizons, the best images of Pluto were blurry and pixelated, none of the dwarf planet's surface features were distinguishable. On January 19, 2006, New Horizons" +
                " was launched from the Cape Canaveral Air Force Station in Florida with the primary objective of investigating Pluto and other objects in the Kuiper belt. After nearly ten" +
                " years, New Horizons passed above Pluto on July 14, 2015 and transmitted the most detailed photos of the dwarf planet. New Horizons revealed the hazy layer of the atmosphere," +
                " a region named Sputnik Planum, mountains and other topographic features. Pluto’s atmosphere is mostly nitrogen with traces of methane and carbon monoxide. Methane gas" +
                " particles are broken down by the Sun’s ultraviolet light into hydrocarbons Ethylene and acetylene. The hydrocarbons condense into ice particles, which produce haze, as " +
                "mentioned above, in the atmosphere. UV rays irradiate haze into tholins. Tholins are simple organic molecules produced at Cornell University in the 1970s by famed scientist," +
                " Carl Sagan, and his colleague Bishun Khare. The substance is dark brown and gives Pluto its red-brownish appearance. On the surface, Pluto is covered by frozen methane, " +
                "nitrogen, and carbon monoxide.  Ice water is also spread around exposed areas on the surface and it more may be hidden under other frozen materials. The “Sputnik Planum” is" +
                " an icy plain that spans 20 kilometers across. The region is segmented which may have been caused by convection currents from the interior layers of Pluto. The 3,500 meter " +
                "mountains likely formed in the last 100 million years as indicated by the lack of craters in its icy surface. New Horizons’s Log Range Reconnaissance Imager (LORRI) found a " +
                "feature that appeared to be frozen lake of nitrogen 30 km across. Two large mountain structures were also located and named Wright Mons and Picard Mons. Scientists think that" +
                " the mountains may be “cryovolcanic” structures."
            +   "\n \nPluto has five moons named Charon, Hydra, Nix, Kerberos, and Styx. Charon is the largest with a diameter of 1,212 km. It can be regarded as a binary planet due to " +
                "its large size relative to Pluto. It is also a candidate for organic life because of its mainly ice water surface and liquid water that may exist beneath the surface. " +
                "Ice methane, nitrogen, and carbon dioxide leak from Pluto’s atmosphere and settle onto Charon’s surface, but they are less prevalent. Hydra is the second largest moon with " +
                "55 km x 40 km dimensions, but it is unable to sustain a sphere structure because of its low size and mass. The moon’s composition is mainly ice water. Nix is slightly smaller" +
                " with 42 km x 36 km dimensions and a similar composition. Kerberos and Styx are even smaller with dimensions of 12 km x 4.5 km and 7 km x 3 km."
        );

        insert("Moon", "3476" , "7.35 x 10^22", "384,400", "The moon orbiting the Earth is simply known as the “Moon”. Before the Italian astronomer Galileo Galilei (1564-1642) discovered " +
                "four of Jupiter’s moons in 1610, there were no other known moons in the Cosmos. The romans referred to it as “Luna” and the Greeks called it “Selene” and “Artemis”. The chemical " +
                "constituents of the moon are 43% oxygen, 20% silicon, 19% magnesium, 10% iron, 3% calcium, 3% percent aluminum, 0.42% chromium, 0.18% titanium, and 0.12% manganese. In 1959, the " +
                "Soviet Union launched Lunar 2, which crash-landed and shattered on the moon’s surface, but it was the first spacecraft to travel to the natural satellite. On July 20 1969, " +
                "Neil Armstrong, Buzz Aldrin, and Michael Collins were the first humans to visit the orbiting rock. The Moon is critical to stability on the Earth due to the imposition of its " +
                "gravitational field which creates bulges in the Earth. The bulges create the powerful tides observed in the vast oceans on Earth. The Moon is covered in thousands of craters due to " +
                "asteroids bombarding its surface. The South Pole-Aitken basin is the largest impact zone spanning 2,414 km in diameter and 8 km in depth. Unlike the Earth, the moon has no " +
                "atmosphere (no weather) or plate tectonics to resurface the satellite. Lava once flowed on the moon 3-4 billion years ago, but that was before the majority of craters were " +
                "shaped into the surface. The average temperature on the moon is between -183 degrees (Celsius) at night to 106 degrees (Celsius) during the day. "
            +   "\n \nThe giant impact hypothesis proposes that a Mars sized protoplanet, Theia, collided with a young Earth more than 4 billion years ago.  The planetary collision caused " +
                "Earth to be titled 23.5 degrees on its axis. The catastrophic collision released an influx of energy that vaporized Theia and a large chunk of the Earth's mantle. The planetary" +
                " material was hurled into orbit around the Earth and eventually coalesced into the Moon.  Researchers at Washington University analyzed lunar samples from four Apollo missions. " +
                "Zinc isotope data suggested that melting occurred during the formation of the Moon likely due to high magnitude impact. A recent study from UCLA suggests that a large portion of" +
                " Theia may have even molded into the Earth due to the chemical similarities between the Earth and Moon rocks. Ratios of oxygen isotopes serve as signatures that are unique in the" +
                " formation of cosmic bodies. Samples from Earth and the Moon were compared and had shown little difference indicating their formation has a mutual connection."
            +   "\n \nThe early Moon is estimated to have been 20-30 km away from the Earth when it formed. Today the Moon is 15 times further away and the distance will continue to increase. " +
                "As discussed above, the Moon’s gravitational field is strong enough to cause bulging in the Earth’s surface on the side facing the Moon and on the opposite side farthest from " +
                "the Moon. The bulging is higher in the presence of water causing tides twice per day as the Earth rotates on its axis. The bulge is never perfectly under the center of the Moon" +
                " due to the Earth's rotational angular velocity, which is greater than the Moon’s orbital angular velocity. As a result, torque is displaced on the Moon and a transfer of energy" +
                " goes from the Earth to Moon. This causes an increase in the orbital angular momentum of the Moon and the body moves away from the Earth. An opposite torque is applied to the " +
                "Earth and its rotational angular momentum is reduced causing longer days on Earth. The Earth gravitational “pull” on the Moon is even greater and it distorts the shape of the " +
                "satellite. Torque is applied to the Moon and effectively slows its orbit. Today, the Moon is tidally locked with the Earth due to the reduction in its rotational angular " +
                "momentum, which is why the same side of the Moon is always facing Earth."
        );
    }
}

