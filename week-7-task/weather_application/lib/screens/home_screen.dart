import 'package:flutter/material.dart';
import 'package:http/http.dart' as http;
import 'dart:convert';
import '../utils/constants.dart';

class HomeScreen extends StatefulWidget {
  const HomeScreen({super.key});

  @override
  HomeScreenState createState() => HomeScreenState();
}

class HomeScreenState extends State<HomeScreen> {
  String weather = "";
  String imageUrl = "";
  double temperature = 0.0;
  double minTemp = 0.0;
  double maxTemp = 0.0;
  int humidity = 0;
  double windSpeed = 0.0;
  int cloudiness = 0;
  String windDirection = "";

  @override
  void initState() {
    super.initState();
    fetchWeatherAndImage();
  }

  Future<void> fetchWeatherAndImage() async {
    try {
      final weatherResponse = await http.get(Uri.parse(
          'https://api.openweathermap.org/data/2.5/weather?q=London&appid=$openWeatherApiKey&units=metric'));
      final weatherData = json.decode(weatherResponse.body);

      String description = weatherData['weather'][0]['description'];
      temperature = weatherData['main']['temp'];
      minTemp = weatherData['main']['temp_min'];
      maxTemp = weatherData['main']['temp_max'];
      humidity = weatherData['main']['humidity'];
      windSpeed = weatherData['wind']['speed'];
      cloudiness = weatherData['clouds']['all'];
      windDirection = getWindDirection(weatherData['wind']['deg']);

      final imageResponse = await http.get(Uri.parse(
          'https://api.pexels.com/v1/search?query=$description'),
          headers: {"Authorization": pexelsApiKey});
      final imageData = json.decode(imageResponse.body);

      setState(() {
        weather = "${temperature.toStringAsFixed(1)}°C - ${description.toUpperCase()}";
        imageUrl = imageData['photos'][0]['src']['medium'];
      });
    } catch (e) {
      debugPrint("Error: $e");
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
      appBar: AppBar(
        title: const Text("Home - Current Weather"),
        backgroundColor: Colors.blueAccent,
      ),
      bottomNavigationBar: BottomNavigationBar(
        selectedItemColor: Colors.blue,
        unselectedItemColor: Colors.grey,
        currentIndex: 0,
        items: const [
          BottomNavigationBarItem(icon: Icon(Icons.home, color: Colors.blue), label: "Home"),
          BottomNavigationBarItem(icon: Icon(Icons.calendar_today, color: Colors.green), label: "Forecast"),
          BottomNavigationBarItem(icon: Icon(Icons.search, color: Colors.orange), label: "Search"),
          BottomNavigationBarItem(icon: Icon(Icons.favorite, color: Colors.red), label: "Favorites"),
          BottomNavigationBarItem(icon: Icon(Icons.history, color: Colors.purple), label: "History"),
          BottomNavigationBarItem(icon: Icon(Icons.settings, color: Colors.teal), label: "Settings"),
        ],
      ),
      body: Center(
        child: imageUrl.isEmpty
            ? const CircularProgressIndicator()
            : Padding(
                padding: const EdgeInsets.all(16.0),
                child: Row(
                  crossAxisAlignment: CrossAxisAlignment.start,
                  children: [
                    // ✅ Weather Image on Left
                    Expanded(
                      flex: 1,
                      child: Image.network(imageUrl, fit: BoxFit.cover),
                    ),
                    const SizedBox(width: 20),

                    // ✅ Weather Details on Right
                    Expanded(
                      flex: 1,
                      child: Column(
                        crossAxisAlignment: CrossAxisAlignment.start,
                        mainAxisAlignment: MainAxisAlignment.center,
                        children: [
                          Text(weather, style: const TextStyle(fontSize: 20, fontWeight: FontWeight.bold)),
                          const SizedBox(height: 10),
                          Text("Min Temp: ${minTemp.toStringAsFixed(1)}°C",
                              style: const TextStyle(fontSize: 16)),
                          Text("Max Temp: ${maxTemp.toStringAsFixed(1)}°C",
                              style: const TextStyle(fontSize: 16)),
                          Text("Humidity: $humidity%", style: const TextStyle(fontSize: 16)),
                          Text("Wind: $windSpeed m/s ($windDirection)",
                              style: const TextStyle(fontSize: 16)),
                          Text("Cloudiness: $cloudiness%", style: const TextStyle(fontSize: 16)),
                        ],
                      ),
                    ),
                  ],
                ),
              ),
      ),
    );
  }
}
