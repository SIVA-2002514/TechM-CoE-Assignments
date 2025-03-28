import 'package:flutter/material.dart';
import 'package:cloud_firestore/cloud_firestore.dart';
import 'package:http/http.dart' as http;
import 'dart:convert';
import '../utils/constants.dart';

class SearchScreen extends StatefulWidget {
  const SearchScreen({super.key});

  @override
  SearchScreenState createState() => SearchScreenState();
}

class SearchScreenState extends State<SearchScreen> {
  final TextEditingController _controller = TextEditingController();
  String cityName = "";
  double temperature = 0.0;
  double minTemp = 0.0;
  double maxTemp = 0.0;
  int humidity = 0;
  int pressure = 0;
  double windSpeed = 0.0;
  int cloudiness = 0;
  String windDirection = "";
  bool isLoading = false;

  Future<void> searchCity(String city) async {
    if (city.isEmpty) return;

    setState(() {
      isLoading = true;
    });

    try {
      final response = await http.get(Uri.parse(
          'https://api.openweathermap.org/data/2.5/weather?q=$city&appid=$openWeatherApiKey&units=metric'));
      final data = json.decode(response.body);

      setState(() {
        cityName = city;
        temperature = data['main']['temp'];
        minTemp = data['main']['temp_min'];
        maxTemp = data['main']['temp_max'];
        humidity = data['main']['humidity'];
        pressure = data['main']['pressure'];
        windSpeed = data['wind']['speed'];
        cloudiness = data['clouds']['all'];
        windDirection = getWindDirection(data['wind']['deg']);
        isLoading = false;
      });

      FirebaseFirestore.instance.collection("search_history").add({
        "city": city,
        "timestamp": DateTime.now(),
      });
    } catch (e) {
      debugPrint("Error searching city: $e");
      setState(() {
        isLoading = false;
      });
    }
  }

  String getWindDirection(int degree) {
    if (degree >= 337 || degree < 23) {
      return "N";
    } else if (degree < 68) {
      return "NE";
    } else if (degree < 113) {
      return "E";
    } else if (degree < 158) {
      return "SE";
    } else if (degree < 203) {
      return "S";
    } else if (degree < 248) {
      return "SW";
    } else if (degree < 293) {
      return "W";
    } else {
      return "NW";
    }
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(title: const Text("Search City")),
      body: Padding(
        padding: const EdgeInsets.all(16.0),
        child: Column(
          children: [
            TextField(
              controller: _controller,
              decoration: InputDecoration(
                labelText: "Enter City Name",
                suffixIcon: IconButton(
                  icon: const Icon(Icons.search),
                  onPressed: () => searchCity(_controller.text),
                ),
              ),
            ),
            const SizedBox(height: 20),
            isLoading
                ? const CircularProgressIndicator()
                : cityName.isNotEmpty
                    ? Card(
                        elevation: 5,
                        shape: RoundedRectangleBorder(
                          borderRadius: BorderRadius.circular(10),
                        ),
                        child: Padding(
                          padding: const EdgeInsets.all(16.0),
                          child: Column(
                            children: [
                              Text(
                                cityName,
                                style: const TextStyle(fontSize: 22, fontWeight: FontWeight.bold),
                              ),
                              Text("Temperature: ${temperature.toStringAsFixed(1)}°C"),
                              Text("Min Temp: ${minTemp.toStringAsFixed(1)}°C"),
                              Text("Max Temp: ${maxTemp.toStringAsFixed(1)}°C"),
                              Text("Humidity: $humidity%"),
                              Text("Pressure: $pressure hPa"),
                              Text("Wind: $windSpeed m/s ($windDirection)"),
                              Text("Cloudiness: $cloudiness%"),
                            ],
                          ),
                        ),
                      )
                    : const Text("Enter a city to get weather details"),
            const SizedBox(height: 20),
            Expanded(child: buildSearchHistory()),
          ],
        ),
      ),
    );
  }

  Widget buildSearchHistory() {
    return StreamBuilder(
      stream: FirebaseFirestore.instance
          .collection("search_history")
          .orderBy("timestamp", descending: true)
          .snapshots(),
      builder: (context, AsyncSnapshot<QuerySnapshot> snapshot) {
        if (!snapshot.hasData) return const CircularProgressIndicator();

        final historyDocs = snapshot.data!.docs;
        return ListView.builder(
          itemCount: historyDocs.length,
          itemBuilder: (context, index) {
            final doc = historyDocs[index];
            final city = doc["city"];
            return ListTile(
              title: Text(city),
              trailing: Row(
                mainAxisSize: MainAxisSize.min,
                children: [
                  IconButton(
                    icon: const Icon(Icons.favorite, color: Colors.red),
                    onPressed: () {
                      FirebaseFirestore.instance.collection("favorites").add({
                        "city": city,
                      });
                    },
                  ),
                  IconButton(
                    icon: const Icon(Icons.delete, color: Colors.grey),
                    onPressed: () {
                      FirebaseFirestore.instance.collection("search_history").doc(doc.id).delete();
                    },
                  ),
                ],
              ),
            );
          },
        );
      },
    );
  }
}
