class WeatherModel {
  final double temperature;
  final double minTemperature;
  final double maxTemperature;
  final int humidity;
  final double windSpeed;
  final String windDirection;
  final int cloudiness;
  final String description;

  WeatherModel({
    required this.temperature,
    required this.minTemperature,
    required this.maxTemperature,
    required this.humidity,
    required this.windSpeed,
    required this.windDirection,
    required this.cloudiness,
    required this.description,
  });

  factory WeatherModel.fromJson(Map<String, dynamic> json) {
    return WeatherModel(
      temperature: json['main']['temp'].toDouble(),
      minTemperature: json['main']['temp_min'].toDouble(),
      maxTemperature: json['main']['temp_max'].toDouble(),
      humidity: json['main']['humidity'],
      windSpeed: json['wind']['speed'].toDouble(),
      windDirection: getWindDirection(json['wind']['deg']),
      cloudiness: json['clouds']['all'],
      description: json['weather'][0]['description'],
    );
  }

  static String getWindDirection(int degree) {
    if (degree >= 337 || degree < 23) return "N";
    if (degree < 68) return "NE";
    if (degree < 113) return "E";
    if (degree < 158) return "SE";
    if (degree < 203) return "S";
    if (degree < 248) return "SW";
    if (degree < 293) return "W";
    return "NW";
  }
}
