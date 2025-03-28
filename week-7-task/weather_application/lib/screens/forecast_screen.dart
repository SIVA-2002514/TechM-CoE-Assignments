import 'package:flutter/material.dart';
import 'package:http/http.dart' as http;
import 'dart:convert';
import '../utils/constants.dart';

class ForecastScreen extends StatefulWidget {
  const ForecastScreen({super.key});

  @override
  ForecastScreenState createState() => ForecastScreenState();
}

class ForecastScreenState extends State<ForecastScreen> {
  List<dynamic> forecastData = [];

  @override
  void initState() {
    super.initState();
    fetchForecast();
  }

  Future<void> fetchForecast() async {
    try {
      final response = await http.get(Uri.parse(
          'https://api.openweathermap.org/data/2.5/forecast?q=London&appid=$openWeatherApiKey&units=metric'));
      final data = json.decode(response.body);
      setState(() {
        forecastData = data['list'].take(5).toList(); // Get 5-day forecast
      });
    } catch (e) {
      debugPrint("Error fetching forecast: $e");
    }
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(title: const Text("5-Day Forecast")),
      body: forecastData.isEmpty
          ? const Center(child: CircularProgressIndicator())
          : ListView.builder(
              itemCount: forecastData.length,
              itemBuilder: (context, index) {
                final day = forecastData[index];
                return ListTile(
                  title: Text("Date: ${day['dt_txt']}"),
                  subtitle: Text("Temp: ${day['main']['temp']}°C"),
                  trailing: Icon(Icons.wb_sunny), // Change based on weather
                );
              },
            ),
    );
  }
}
