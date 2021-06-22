# RouteCalculator

Assuming that we have a network of warehouses, each holding some amounts of
different commodities, and an order to deliver some amount of commodity to an
end destination, write a program that will find a path from where the product is
to where we want it delivered.

The route need not be the shortest. Also assume that the product to fulfill the
order is picked from a single warehouse (so there is no need to merge product
from multiple locations). Assume that all quantities will be natural numbers.

The program should be a command line application that accepts two arguments, the
first a JSON file describing the network and the second a JSON file describing
the order. Examples of both are included.

To Run

on mac
mvn clean package
java -cp target/routecalculator-1.0-SNAPSHOT.jar com.gray.demo.routecalculator.RouteCalculator .../RouteCalculator/Files/Network.0.json .../RouteCalculator/Files/Order.0.json

on windows
mvn clean package
java -cp target/routecalculator-1.0-SNAPSHOT.jar com.gray.demo.routecalculator.RouteCalculator .../RouteCalculator/files/Network.0.json .../RouteCalculator/files/Order.0.json

where ... is local path to project
