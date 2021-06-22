## Problem Statement

Assuming that we have a network of warehouses, each holding some amounts of
different commodities, and an order to deliver some amount of commodity to an
end destination, write a program that will find a path from where the product is
to where we want it delivered.

The route need not be the shortest. Also assume that the product to fulfill the
order is picked from a single warehouse (so there is no need to merge product
from multiple locations). Assume that all quantities will be natural numbers.

As an example, if we have the following network:

![Network](images/Diagrams.png)

And an order to deliver 5 carrots to Fornost, then a valid answer would be:

   Hobbiton → Bree → Fornost → Delivery

The program should be a command line application that accepts two arguments, the
first a JSON file describing the network and the second a JSON file describing
the order. Examples of both are included.

## Examples

+------------------+----------------+------------------------------------------+------------------------------------+
| Network File     | Order File     | Result                                   | Notes                              |
+==================+================+==========================================+====================================+
| `Network.0.json` | `Order.0.json` | Hobbiton → Bree → Fornost                |                                    |
+------------------+----------------+------------------------------------------+------------------------------------+
| `Network.0.json` | `Order.1.json` | No Path                                  | Quantity exceeds inventory.        |
+------------------+----------------+------------------------------------------+------------------------------------+
| `Network.0.json` | `Order.2.json` | No Path                                  | Would have to pull from 2 origins. |
+------------------+----------------+-----------------------------+-------------------------------------------------+
| `Network.1.json` | `Order.3.json` | Tharbad → Rivendell → Bree → Hobbiton    |                                    |
+------------------+----------------+------------------------------------------+------------------------------------+
