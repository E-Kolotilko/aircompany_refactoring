import planes.ExperimentalPlane;
import models.ClassificationLevel;
import models.ExperimentalTypes;
import models.MilitaryType;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import planes.MilitaryPlane;
import planes.PassengerPlane;
import planes.Plane;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class AirportTest {
    private static final PassengerPlane planeWithMaxPassengerCapacity = new PassengerPlane("Boeing-747", 980, 16100, 70500, 242);

    private List<PassengerPlane> passengerPlanesSource;
    private List<MilitaryPlane> militaryPlanesSource;
    private List<ExperimentalPlane> experimentalPlanesSource;
    private List<Plane> planes;

    @BeforeTest
    private void createPlanes() {
        passengerPlanesSource = Arrays.asList(
                new PassengerPlane("Boeing-747", 980, 16100, 70500, 242),
                new PassengerPlane("Boeing-737", 900, 12000, 60500, 164),
                new PassengerPlane("Boeing-737-800", 940, 12300, 63870, 192),
                new PassengerPlane("Airbus A320", 930, 11800, 65500, 188),
                new PassengerPlane("Airbus A330", 990, 14800, 80500, 222),
                new PassengerPlane("Embraer 190", 870, 8100, 30800, 64),
                new PassengerPlane("Sukhoi Superjet 100", 870, 11500, 50500, 140),
                new PassengerPlane("Bombardier CS300", 920, 11000, 60700, 196)
        );
        militaryPlanesSource = Arrays.asList(
                new MilitaryPlane("B-1B Lancer", 1050, 21000, 80000, MilitaryType.BOMBER),
                new MilitaryPlane("B-2 Spirit", 1030, 22000, 70000, MilitaryType.BOMBER),
                new MilitaryPlane("B-52 Stratofortress", 1000, 20000, 80000, MilitaryType.BOMBER),
                new MilitaryPlane("F-15", 1500, 12000, 10000, MilitaryType.FIGHTER),
                new MilitaryPlane("F-22", 1550, 13000, 11000, MilitaryType.FIGHTER),
                new MilitaryPlane("C-130 Hercules", 650, 5000, 110000, MilitaryType.TRANSPORT)
        );
        experimentalPlanesSource = Arrays.asList(
                new ExperimentalPlane("Bell X-14", 277, 482, 500, ExperimentalTypes.HIGH_ALTITUDE, ClassificationLevel.SECRET),
                new ExperimentalPlane("Ryan X-13 Vertijet", 560, 307, 500, ExperimentalTypes.VTOL, ClassificationLevel.TOP_SECRET)
        );
        planes = new ArrayList<>(passengerPlanesSource);
        planes.addAll(militaryPlanesSource);
        planes.addAll(experimentalPlanesSource);
    }

    @Test
    public void testGetTransportMilitaryPlanes() {
        List<MilitaryPlane> transportMilitaryPlanes = new Airport(planes).getTransportMilitaryPlanes();

        Assert.assertEquals(transportMilitaryPlanes.size(), 1);
        Assert.assertEquals(transportMilitaryPlanes.get(0).getType(), MilitaryType.TRANSPORT);
    }

    @Test
    public void testGetPassengerPlaneWithMaxCapacity() {
        PassengerPlane expectedPlaneWithMaxPassengersCapacity = new Airport(planes).getPassengerPlaneWithMaxPassengersCapacity();
        Assert.assertEquals(expectedPlaneWithMaxPassengersCapacity, planeWithMaxPassengerCapacity);
    }

    @Test
    public void testSortByMaxLoadCapacity() {
        List<? extends Plane> planesSortedByMaxLoadCapacity = new Airport(planes).sortByMaxLoadCapacity().getPlanes();

        for (int i = 0; i < planesSortedByMaxLoadCapacity.size() - 1; i++) {
            Assert.assertTrue(planesSortedByMaxLoadCapacity.get(i).getMaxLoadCapacity()
                    <= planesSortedByMaxLoadCapacity.get(i + 1).getMaxLoadCapacity());
        }
    }

    @Test
    public void testGetBomberMilitaryPlanes() {
        List<MilitaryPlane> bomberMilitaryPlanes = new Airport(planes).getBomberMilitaryPlanes();

        Assert.assertEquals(bomberMilitaryPlanes.size(), 3);
        for (MilitaryPlane plane : bomberMilitaryPlanes) {
            Assert.assertEquals(plane.getType(), MilitaryType.BOMBER);
        }
    }

    @Test
    public void testSortByMaxDistance() {
        List<? extends Plane> planesSortedByMaxLoadCapacity = new Airport(planes).sortByMaxDistance().getPlanes();

        for (int i = 0; i < planesSortedByMaxLoadCapacity.size() - 1; i++) {
            Assert.assertTrue(planesSortedByMaxLoadCapacity.get(i).getMaxFlightDistance()
                    <= planesSortedByMaxLoadCapacity.get(i + 1).getMaxFlightDistance());
        }
    }

    @Test
    public void testSortByMaxSpeed() {
        List<? extends Plane> planesSortedByMaxLoadCapacity = new Airport(planes).sortByMaxSpeed().getPlanes();

        for (int i = 0; i < planesSortedByMaxLoadCapacity.size() - 1; i++) {
            Assert.assertTrue(planesSortedByMaxLoadCapacity.get(i).getMaxSpeed()
                    <= planesSortedByMaxLoadCapacity.get(i + 1).getMaxSpeed());
        }
    }

    @Test
    public void testGetExperimentalPlanes() {
        Assert.assertEqualsNoOrder(new Airport(planes).getExperimentalPlanes().toArray(), experimentalPlanesSource.toArray());
    }

    @Test
    public void testGetPassengerPlanes() {
        Assert.assertEqualsNoOrder(new Airport(planes).getPassengerPlanes().toArray(), passengerPlanesSource.toArray());
    }

    @Test
    public void testGetMilitaryPlanes() {
        Assert.assertEqualsNoOrder(new Airport(planes).getMilitaryPlanes().toArray(), militaryPlanesSource.toArray());
    }
}
