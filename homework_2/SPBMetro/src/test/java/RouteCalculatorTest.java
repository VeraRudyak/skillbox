import core.Line;
import core.Station;
import jdk.jfr.Name;
import junit.framework.TestCase;
import java.util.*;
import java.util.stream.Collectors;


public class RouteCalculatorTest extends TestCase {
    private static StationIndex stationIndex;
    private RouteCalculator calculator;
   private static List<Station> routet;
   private Station from;
   private Station to;



    @Override
    protected void setUp() throws Exception {
        stationIndex = new StationIndex();
        calculator = new RouteCalculator(stationIndex);
        routet = new ArrayList<>();
        Line line1 = new Line(1, "линия 1 ");
        Line line2 = new Line(2, "линия 2 ");
        Line line3 = new Line(3, "линия 3 ");

        Station subwayStation1 = new Station("Остановка1", line1);
        Station subwayStation2 = new Station("Остановка2", line1);
        Station subwayStation3 = new Station("Остановка3", line1);
        Station subwayStation4 = new Station("Остановка4", line2);
        Station subwayStation5 = new Station("Остановка5", line2);
        Station subwayStation6 = new Station("Остановка6", line2);
        Station subwayStation7 = new Station("Остановка7", line3);
        Station subwayStation8 = new Station("Остановка8", line3);
        Station subwayStation9 = new Station("Остановка9", line3);
        Station subwayStation10 = new Station("Остановка10", line3);

        line1.addStation(subwayStation1);
        line1.addStation(subwayStation2);
        line1.addStation(subwayStation3);
        line2.addStation(subwayStation4);
        line2.addStation(subwayStation5);
        line2.addStation(subwayStation6);
        line3.addStation(subwayStation7);
        line3.addStation(subwayStation8);
        line3.addStation(subwayStation9);
        line3.addStation(subwayStation10);

        stationIndex.addLine(line1);
        stationIndex.addLine(line2);
        stationIndex.addLine(line3);

        stationIndex.addStation(subwayStation1);
        stationIndex.addStation(subwayStation2);
        stationIndex.addStation(subwayStation3);
        stationIndex.addStation(subwayStation4);
        stationIndex.addStation(subwayStation5);
        stationIndex.addStation(subwayStation6);
        stationIndex.addStation(subwayStation7);
        stationIndex.addStation(subwayStation8);
        stationIndex.addStation(subwayStation9);
        stationIndex.addStation(subwayStation10);

        List<Station> connectionLine1Line3  = new ArrayList<>();
        connectionLine1Line3.add(stationIndex.getStation("Остановка2"));
        connectionLine1Line3.add(stationIndex.getStation("Остановка8"));
        stationIndex.addConnection(connectionLine1Line3);

        List<Station>  connectionLine3Line2 = new ArrayList<>();
        connectionLine3Line2.add(stationIndex.getStation("Остановка9"));
        connectionLine3Line2.add(stationIndex.getStation("Остановка5"));
        stationIndex.addConnection(connectionLine3Line2);

        routet.add(subwayStation1);
        routet.add(subwayStation2);
        routet.add(subwayStation8);
        routet.add(subwayStation9);
        routet.add(subwayStation4);
        routet.add(subwayStation5);
    }



        @Name("Кратчайший маршрут")
        public void testGetShortestRoute () {

        }
        @Name("рассчитать продолжительность")
        public void testCalculateDuration () {
            double actual = calculator.calculateDuration(routet);
            double expected = 14.5;
            assertEquals(expected, actual);
        }

        @Name("маршрут на линиии")
        public void testGetRouteOnTheLine () {
            List<Station> expected = routet.stream().filter(station -> station.getLine().getNumber() == 1)
                    .collect(Collectors.toList());

            from = stationIndex.getStation("Остановка1");
            to = stationIndex.getStation("Остановка2");

                List<Station> actual = calculator.getShortestRoute(from, to);

                assertEquals(expected, actual);
            }


        @Name("Маршрут с одним соединением")
        public void testGetRouteWithOneConnection () {
            List<Station> expected = new ArrayList<>();
            Line line1 = new Line(1, "линия 1 ");
            Line line3 = new Line(3, "линия 3 ");

            expected.add(new Station("Остановка1", line1));
            expected.add(new Station("Остановка2", line1));
            expected.add(new Station("Остановка8", line3));

        from = stationIndex.getStation("Остановка1");
        to = stationIndex.getStation("Остановка8");

            List<Station> actual = calculator.getShortestRoute(from, to);
            double actual1 = calculator.calculateDuration(actual);
            double expected2 = 6.0;
            assertEquals(expected2, actual1);
            assertEquals(expected, actual);
        }

        @Name("Маршрут по соединенной линии")
        public void testGetRouteViaConnectedLine () {
            List<Station> expected = new ArrayList<>();
            Line line1 = new Line(1, "линия 1 ");
            Line line3 = new Line(3, "линия 3 ");
            expected.add(new Station("Остановка1", line1));
            expected.add(new Station("Остановка2", line1));
            expected.add(new Station("Остановка8", line3));
            expected.add(new Station("Остановка9", line3));

            from = stationIndex.getStation("Остановка1");
            to = stationIndex.getStation("Остановка9");

            List<Station> actual = calculator.getShortestRoute(from, to);

            double actual1 = calculator.calculateDuration(actual);
            double expected2 = 8.5;
            assertEquals(expected2, actual1);
            assertEquals(expected, actual);

        }

        @Name("Маршрут с двумя соединениями")
        public void testGetRouteWithTwoConnections () {
            List<Station> expected = new ArrayList<>();
            Line line1 = new Line(1, "линия 1 ");
            Line line2 = new Line(2, "линия 2 ");
            Line line3 = new Line(3, "линия 3 ");

            expected.add(new Station("Остановка1", line1));
            expected.add(new Station("Остановка2", line1));
            expected.add(new Station("Остановка8", line3));
            expected.add(new Station("Остановка9", line3));
            expected.add(new Station("Остановка5", line2));

            from = stationIndex.getStation("Остановка1");
            to = stationIndex.getStation("Остановка5" );

            List<Station> actual = calculator.getShortestRoute(from, to);

            assertEquals(expected, actual);

        }
        @Override
        protected void tearDown () throws Exception {

        }
    }
