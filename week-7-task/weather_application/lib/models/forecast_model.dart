class ForecastModel {
  final String date;
  final double temperature;
  final String description;
  final String icon;

  ForecastModel({
    required this.date,
    required this.temperature,
    required this.description,
    required this.icon,
  });

  factory ForecastModel.fromJson(Map<String, dynamic> json) {
    return ForecastModel(
      date: json['dt_txt'],
      temperature: json['main']['temp'].toDouble(),
      description: json['weather'][0]['description'],
      icon: json['weather'][0]['icon'],
    );
  }
}
